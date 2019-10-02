package com.al.nppm.business.inter.service.impl;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/18 10:21
 * @File : PayToPlanService
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.al.nppm.business.account.dao.IPayToPlanMapper;
import com.al.nppm.business.account.dao.IProdInstMapper;
import com.al.nppm.business.core.SynMapContextHolder;
import com.al.nppm.business.cpcp.dao.CpcMapper;
import com.al.nppm.business.inter.service.IPayToPlanService;
import com.al.nppm.business.inter.service.IRouteService;
import com.al.nppm.business.syntomq.model.InterPayToPlan;
import com.al.nppm.business.syntomq.model.MqObject;
import com.al.nppm.business.syntomq.model.MqUtil;
import com.al.nppm.business.syntomq.tool.MqTool;
import com.al.nppm.common.errorcode.ResultCode;
import com.al.nppm.common.utils.StringUtil;
import com.al.nppm.model.Message;
import com.al.nppm.ord.ordbill.dao.OrdBillMapper;
import com.al.nppm.ord.ordbill.dao.OrdPayMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ctg.mq.api.IMQProducer;
import com.ctg.mq.api.bean.MQMessage;
import com.ctg.mq.api.bean.MQSendResult;
import com.ctg.mq.api.bean.MQSendStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author maozp3
 * @Description:
 * @Date: 2019/4/18 10:21
 */
@Service("payToPlanService")
public class PayToPlanService implements IPayToPlanService {
    private static Logger logger = Logger.getLogger(PayToPlanService.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private IPayToPlanMapper payToPlanDao;
    @Autowired
    private IProdInstMapper prodInstDao;
    @Autowired
    private OrdPayMapper ordPayDao;
    @Autowired
    private CpcMapper cpcDao;
    @Autowired
    public IRouteService routeDao;
    @Autowired
    public OrdBillMapper ordBillDao;
    /**
     * @return void
     * @Author maozp3
     * @Description: 支付计划(通过套餐写INTER_PAY_TO_PLAN中间表)
     * @Date: 19:39 2019/4/29
     * @Param [args]
     **/
    public void acctProDepositTask(String[] args) throws Exception {
        Date date = new Date();
        logger.debug("----------定时执行acctProDepositTask---------------" + sdf.format(date).toString());
        long startTime = System.currentTimeMillis();
        int sumCount = 0;//本次处理总数
        int successCount = 0;//本次处理成功数
        //处理业务逻辑。  flag=1处理成功，其他失败
        int flag = -1;
        Message msg = new Message();
        Map<String, Object> updateMap = new HashMap<String, Object>();

        Map queryMap = new HashMap();
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (i == 0) {
                    queryMap.put("regionId", args[i]);
                }
            }
        }
        List<Map<String, Object>> orderList = ordPayDao.selectOrderlist(queryMap);
        if (orderList.size() > 0) {
            for (Map<String, Object> orderMap : orderList) {
                long startOperTime = System.currentTimeMillis();
                SynMapContextHolder.init();
                sumCount = orderList.size();
                WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                try {
                    //for (Map<String, Object> orderMap : orderList) {
                    flag = acctProDeposit(orderMap, msg);
                    updateMap = orderMap;

                    String strResultmsgString;
                    if (flag < 0) {
                        // 回滚
                        strResultmsgString = msg.getMessage() + "，处理时长" + (System.currentTimeMillis() - startOperTime) + "ms";
                        transactionManager.rollback(status);
                        updateOrdOfferInst(updateMap, 2, strResultmsgString);
                    } else {
                        msg.setMessage("处理成功");
                        transactionManager.commit(status);
                        strResultmsgString = msg.getMessage() + "，处理时长" + (System.currentTimeMillis() - startOperTime) + "ms";
                        successCount++;
                        updateOrdOfferInst(updateMap, 1, strResultmsgString);
                    }
                    //}

                } catch (Exception e) {
                    e.printStackTrace();
                    transactionManager.rollback(status);
                    logger.error("处理失败：" + e.getMessage());
                    updateOrdOfferInst(updateMap, 2, e.getMessage() != null && e.getMessage().length() > 256 ? e.getMessage().substring(0, 256) : e.getMessage());
                }

            }

        }
        logger.debug("本次处理开始时间：" + sdf.format(date).toString() +
                "\t花费总时间：" + (System.currentTimeMillis() - startTime) + "毫秒\t" +
                "本次处理归档数：" + sumCount +
                "\t成功数：" + successCount +
                "\t失败数：" + (sumCount - successCount));
    }

    @Override
    /**
     * @Author maozp3
     * @Description: 用户级销售品预存款
     * @Date: 19:37 2019/4/29
     * @Param [orderMap, userMap, msg]
     * @return int
     **/
    public int acctProDeposit(Map orderMap, Message msg) throws Exception {
        //共享级别，2-用户级，1-帐户级
        Long shareLevel = 1L;
        Long depositType = 1L;
        String amount;
        Long prodInstId = null;
        Long acctId = null;
        Long objectId;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        /*
        if(StringUtil.isEmpty(orderMap.get("OFFER_INST_ID"))
            || StringUtil.isEmpty(orderMap.get("OFFER_ID"))
            || StringUtil.isEmpty(orderMap.get("ARCH_GRP_ID"))
            || StringUtil.isEmpty(orderMap.get("EFF_DATE"))
            || StringUtil.isEmpty(orderMap.get("OPER_TYPE"))
            || StringUtil.isEmpty(orderMap.get("CREATE_DATE"))
            || StringUtil.isEmpty(orderMap.get("ORDER_ITEM_ID"))
        ){
           msg.setMessage("取基础数据报错,请检查! ARCH_GRP_ID="+orderMap.get("ARCH_GRP_ID"));
           return -1;
        }
        */
        try {
            String serviceOfferId = ordPayDao.getServiceOfferId(orderMap);
            if (StringUtil.isEmpty(serviceOfferId)) {
                msg.setResultCode(ResultCode.PAYPLAN_ERROR_001);
                msg.setMessage("取SERVICE_OFFER_ID出错!");
                return -1;
            }
            //修改参数的不处理
            if ("3020501002".equals(serviceOfferId)) {
                msg.setResultCode(ResultCode.PAYPLAN_ERROR_002);
                msg.setMessage("修改参数的不处理");
                return 1;
            }
        } catch (Exception e) {
            msg.setResultCode(ResultCode.PAYPLAN_ERROR_003);
            msg.setMessage("取SERVICE_OFFER_ID出错!");
            e.printStackTrace();
            return -1;
        }

        try {
            String flag="0";// flag=0 赠款 1红包金
            // '-1064785','-1064786'  预存支付计划类销售品
            List<Map<String,Object>> zjList = cpcDao.getCountFromOfferCatalogLocation(orderMap);
            //红包金类销售品目录
            if(zjList.size()>0){
                if("-1064785".equals(zjList.get(0).get("catalogItemId").toString())){
                    flag="0";
                }else{
                    flag="1";
                }
            // 是预存款目录的才处理
            if(zjList.size() > 0) {
                if ("1000".equals(orderMap.get("operType"))) {
                    orderMap.put("jfOperType", "1");
                } else if ("1100".equals(orderMap.get("operType"))) {
                    orderMap.put("jfOperType", "3");
                    if ("1".equals(flag)) {
                        orderMap.put("jfOperType","4");//红包金退订填4
                    }
                    }
                } else {
                    msg.setMessage("OPER_TYPE=不是1000、1100的不处理");
                    return 1;
                }
                Long counter = cpcDao.getCountFromPOfferPayplanInfo(orderMap);
                if (counter > 0) {
                    //取出余额级别
                    shareLevel = cpcDao.getShareLevel(orderMap);
                    //ICB销售品类型为1 吉林应该没有这个
                    if ("109000000913".equals(orderMap.get("offerId"))
                            || "349000007051".equals(orderMap.get("offerId"))
                            || "349000007052".equals(orderMap.get("offerId"))
                    ) {
                        depositType = 1L;
                        //ICB取CRM金额
                        //新增才取金额
                        try {
                            if ("1000".equals(orderMap.get("operType"))) {
                                amount = ordPayDao.getAttrValue(orderMap);
                            } else {
                                amount = null;
                            }
                        } catch (Exception e) {
                            msg.setMessage("取销售品金额出错,请检查!");
                            e.printStackTrace();
                            throw e;
                        }
                    } else {
                        depositType = 2L;
                        if("1".equals(flag)){
                            depositType = 10L;//红包金depositType为10其他逻辑和赠款一样
                        }
                        amount = null;
                    }
                } else {
                    //预存款销售品
                    depositType = 3L;
                    //新增才取金额
                    try {
                        if ("1000".equals(orderMap.get("operType"))) {
                            amount = ordPayDao.getAttrValue(orderMap);
                        } else {
                            amount = null;
                        }
                    } catch (Exception e) {
                        msg.setMessage("取销售品金额出错,请检查!");
                        e.printStackTrace();
                        throw e;
                    }
                }
            }
            try {
                //用户级销售品
                prodInstId = ordPayDao.getProdInstId(orderMap);
                if (StringUtil.isEmpty(prodInstId)) {
                    //账户级销售品
                    acctId = ordPayDao.getObjId(orderMap);
                    if (StringUtil.isEmpty(acctId)) {
                        msg.setResultCode(ResultCode.PAYPLAN_ERROR_004);
                        msg.setMessage("取账户级销售品对象表出错,请检查!");
                        return -1;
                    }
                }
            } catch (Exception e) {
                msg.setMessage("取用户级销售品对象表出错,请检查!");
                e.printStackTrace();
                throw e;
            }
            try {
                //取出账户ID
                if (!StringUtil.isEmpty(prodInstId)) {
                    Map map = new HashMap();
                    map.put("prodInstId", prodInstId);
                    long routeId = routeDao.getRouteIdForProdInst(Long.parseLong(orderMap.get("ARCH_GRP_ID").toString()),
                            Long.parseLong(orderMap.get("ORDER_ITEM_ID").toString()), prodInstId, msg);
                    if (routeId != -1l) {
                        map.put("routeId", routeId);
                    } else {
                        msg.setResultCode(ResultCode.PAYPLAN_ERROR_005);
                        msg.setMessage("获取routeID失败，产品实例prod_inst_id为：" + prodInstId);
                        return -1;
                    }
                    acctId = payToPlanDao.getAcctId(map);
//                        if(StringUtil.isEmpty(acctId)){
//                            acctId = 0L;
//                        }
                }
            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "取对应的帐户信息出错,请检查账务关系!");
                msg.setResultCode(ResultCode.PAYPLAN_ERROR_006);
                msg.setMessage(exceptionMsg);
                return -1;
            }

            if (shareLevel == 1) {
                objectId = acctId;
            } else {
                //用户级预存
                objectId = prodInstId;
            }
            //新增开始 0721  这个是吉林迎合2.0处理的逻辑，3.0要屏蔽掉
            if ("1000".equals(orderMap.get("operType"))) {
                orderMap.put("jfOperType", "1");
            } else if ("1100".equals(orderMap.get("operType"))) {
                orderMap.put("jfOperType", "3");
            } else {
                msg.setResultCode(ResultCode.PAYPLAN_ERROR_007);
                msg.setMessage("OPER_TYPE=不是1000、1100的不处理");
                return 1;
            }
            long archGrpId = Long.parseLong(orderMap.get("ARCH_GRP_ID").toString());
            long orderItemId = Long.parseLong(orderMap.get("ORDER_ITEM_ID").toString());
            long offerInstId = Long.parseLong(orderMap.get("offerInstId").toString());
            //取crmRent表
            List<Map<String, Object>> ordCrmRentList = ordBillDao.selectCrmRent(archGrpId, orderItemId, offerInstId);
            for (Map<String, Object> ordCrmRentMap : ordCrmRentList) {
                if ("1000".equals(orderMap.get("operType"))) {
                    List<Map<String, Object>> oneItemResultList = ordBillDao.selectOneItemResult(archGrpId, orderItemId);
                    if (oneItemResultList.size() == 0) {
                        msg.setResultCode(ResultCode.PAYPLAN_ERROR_008);
                        msg.setMessage("onItemResult表为空");
                        return -1;
                    }
                    for (Map<String, Object> oneItemResultMap : oneItemResultList) {

                        try {
                            Map oneItem = new HashMap();
                            Long seqInterPlanId = prodInstDao.getSeq("SEQ_INTER_PLAN_ID");
                            oneItem.put("interPlanId", seqInterPlanId);
                            oneItem.put("offerId", oneItemResultMap.get("OFFER_ID"));
                            oneItem.put("offerInstId", oneItemResultMap.get("OFFER_INST_ID"));
                            oneItem.put("depositType", depositType);
                            oneItem.put("acctId", acctId);
                            oneItem.put("objectType", shareLevel);
                            oneItem.put("objectId", objectId);
                            oneItem.put("amount", oneItemResultMap.get("PAID_IN_AMOUNT"));
                            oneItem.put("operType", orderMap.get("jfOperType"));
                            oneItem.put("operState", 0);
                            oneItem.put("orderDate", orderMap.get("createDate"));
                            oneItem.put("effDate", orderMap.get("effDate"));
                            payToPlanDao.insertInterPayToPlan(oneItem);
                            sendInterPayToPlanMsg(oneItem);

                        } catch (Exception e) {
                            e.printStackTrace();
                            String exceptionMsg = "";
                            exceptionMsg = setErrorMsg(e.getMessage(), "活动订购增加预存款接口表出错");
                            msg.setResultCode(ResultCode.PAYPLAN_ERROR_009);
                            msg.setMessage(exceptionMsg);
                            return -1;
                        }
                        updateOneItemResult(oneItemResultMap, 1, "预存活动处理成功");
                    }

                } else {
                    try {
                        Map oneItem = new HashMap();
                        long interPlanId = prodInstDao.getSeq("SEQ_INTER_PLAN_ID");
                        oneItem.put("interPlanId", interPlanId);
                        oneItem.put("offerInstId", ordCrmRentMap.get("offerInstId"));
                        oneItem.put("offerId", ordCrmRentMap.get("offerId"));
                        oneItem.put("objectType", shareLevel);
                        oneItem.put("objectId", ordCrmRentMap.get("prodInstId"));
                        oneItem.put("acctId", acctId);
                        oneItem.put("operType", orderMap.get("jfOperType"));
                        oneItem.put("operState", 0);
                        oneItem.put("createDate", df.format(date));
                        oneItem.put("orderDate", orderMap.get("createDate"));
                        oneItem.put("operDate", df.format(date));
                        oneItem.put("effDate", orderMap.get("effDate"));
                        oneItem.put("depositType", depositType);
                        prodInstDao.insertPayToPlan(oneItem);
                        sendInterPayToPlanMsg(oneItem);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "活动退订增加预存款接口表出错");
                        msg.setResultCode(ResultCode.PAYPLAN_ERROR_010);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                }


            }//新增结束 0722
           /* try {
                    Long seqInterPlanId = prodInstDao.getSeq("SEQ_INTER_PLAN_ID");
                    orderMap.put("interPlanId",seqInterPlanId);
                    orderMap.put("depositType",depositType);
                    orderMap.put("acctId",acctId);
                    orderMap.put("objectType",shareLevel);
                    orderMap.put("objectId",objectId);
                    orderMap.put("amount",amount);
                    orderMap.put("operType",orderMap.get("jfOperType"));
                    orderMap.put("operState",0);
                    orderMap.put("orderDate",orderMap.get("createDate"));
//                    String dateTime = df.format(new Date());
//                    Date date=new Date();
//                    orderMap.put("createDate",date);
//                    orderMap.put("operDate",date);
                    payToPlanDao.insertInterPayToPlan(orderMap);
                } catch (Exception e) {
                    msg.setMessage("增加预存款接口表出错");
                    e.printStackTrace();
                    throw e;
                }
            }else {
                msg.setMessage("非预存支付计划,不用处理!");
                return 1;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理预存失败");
            msg.setResultCode(ResultCode.PAYPLAN_ERROR_011);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    /**
     * @return void
     * @Author maozp3
     * @Description: 一次性费用及一次性费用预存款定时任务
     * @Date: 19:39 2019/4/29
     * @Param [args]
     **/
    public void oneTimesChargeTask(String[] args) throws Exception {
        Date date = new Date();
        logger.debug("----------定时执行oneTimesChargeTask---------------" + sdf.format(date).toString());
        long startTime = System.currentTimeMillis();
        int sumCount = 0;//本次处理总数
        int successCount = 0;//本次处理成功数
        //处理业务逻辑。  flag=1处理成功，其他失败
        int flag = -1;
        Message msg = new Message();
        Map<String, Object> updateMap = new HashMap<String, Object>();

        Map queryMap = new HashMap();
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (i == 0) {
                    queryMap.put("regionId", args[i]);
                }
            }
        }

        List<Map<String, Object>> oneItemList = ordPayDao.selectOneItemList(queryMap);
        if (oneItemList.size() > 0) {
            for (Map<String, Object> oneItemMap : oneItemList) {
                long archGrpId = Long.parseLong(oneItemMap.get("ARCH_GRP_ID").toString());
                long ordItemId = Long.parseLong(oneItemMap.get("ORDER_ITEM_ID").toString());
                long offerInstId = 0;
                List<Map<String, Object>> crmRentList = ordBillDao.selectCrmRent(archGrpId, ordItemId, offerInstId);
                if (crmRentList.size() == 0) {
                    long startOperTime = System.currentTimeMillis();
                    SynMapContextHolder.init();
                    sumCount = oneItemList.size();
                    WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                    DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
                    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                    TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

                    //for (Map<String, Object> oneItemMap : oneItemList) {
                    try {
                        //业务逻辑处理
                        flag = oneTimesCharge(oneItemMap, msg);
                        updateMap = oneItemMap;
                        //}
                        String strResultmsgString;
                        if (flag < 0) {
                            // 回滚
                            strResultmsgString = msg.getMessage() + ",处理时长" + (System.currentTimeMillis() - startOperTime) + "ms";
                            transactionManager.rollback(status);
                            updateOneItemResult(updateMap, 2, strResultmsgString);
                        } else {
                            msg.setMessage("处理成功");
                            transactionManager.commit(status);
                            strResultmsgString = msg.getMessage() + ",处理时长" + (System.currentTimeMillis() - startOperTime) + "ms";
                            successCount++;
                            updateOneItemResult(updateMap, 1, strResultmsgString);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        transactionManager.rollback(status);
                        logger.error("处理失败：" + e.getMessage());
                        updateOneItemResult(updateMap, 2, msg.getMessage());
                    }
                }
            }
        }
        logger.debug("本次处理开始时间：" + sdf.format(date).toString() +
                "\t花费总时间：" + (System.currentTimeMillis() - startTime) + "毫秒\t" +
                "本次处理归档数：" + sumCount +
                "\t成功数：" + successCount +
                "\t失败数：" + (sumCount - successCount));
    }

    /**
     * @return int
     * @Author maozp3
     * @Description: 一次性费用及一次性费用预存款
     * @Date: 19:38 2019/4/29
     * @Param [oneItemMap, userMap, msg]
     **/
    @Override
    public int oneTimesCharge(Map oneItemMap, Message msg) throws Exception {
        Long objectId;
        Long prodInstId;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
               /*
        if(StringUtil.isEmpty(oneItemMap.get("ARCH_GRP_ID"))
            || StringUtil.isEmpty(oneItemMap.get("ORDER_ITEM_ID"))
            || StringUtil.isEmpty(oneItemMap.get("BILL_ACCT_ITEM_TYPE_ID"))
            || StringUtil.isEmpty(oneItemMap.get("ACCT_ID"))
            || StringUtil.isEmpty(oneItemMap.get("PROD_INST_ID"))
            || StringUtil.isEmpty(oneItemMap.get("CREATE_DATE"))
            || StringUtil.isEmpty(oneItemMap.get("CHARGE_METHOD"))
            || StringUtil.isEmpty(oneItemMap.get("PAID_IN_AMOUNT"))
        ){
            msg.setMessage("取基础数据报错,请检查! ARCH_GRP_ID="+oneItemMap.get("ARCH_GRP_ID"));
            return -1;
        }
        */

        try {
            //转计费余额付费的一次性费用
            if ("6".equals(String.valueOf(oneItemMap.get("chargeMethod")))) {
                try {
                    Long seqPayPelaIdIntf = prodInstDao.getSeq("SEQ_PAY_RELA_ID_INTF");
                    oneItemMap.put("areaCode", oneItemMap.get("orgId"));
                    oneItemMap.put("serialnumber", seqPayPelaIdIntf);
                    oneItemMap.put("feeSerial", oneItemMap.get("ARCH_GRP_ID"));
                    oneItemMap.put("orderNo", oneItemMap.get("ARCH_GRP_ID"));
                    oneItemMap.put("userId", oneItemMap.get("prodInstId"));
                    oneItemMap.put("acctItemType", oneItemMap.get("billAcctItemTypeId"));
                    oneItemMap.put("payCharge", oneItemMap.get("paidInAmount"));
                    oneItemMap.put("payTimes", "1");
                    oneItemMap.put("feeDate", oneItemMap.get("createDate"));
                    oneItemMap.put("effDate", oneItemMap.get("createDate"));
                    oneItemMap.put("state", "0");
                    oneItemMap.put("notes", "CRM一次性费用");
                    payToPlanDao.insertTifFeeBill(oneItemMap);
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "增加一次性费用出错");
                    msg.setResultCode(ResultCode.PAYPLAN_ERROR_012);
                    msg.setMessage(exceptionMsg);
                    return -1;
                }
            } else {
                //一次性费用预存款
                if (StringUtil.isEmpty(oneItemMap.get("acctId")) ||
                        Long.parseLong(oneItemMap.get("acctId").toString()) < 1) {
                    try {
                        //取出账户ID
                        prodInstId = Long.parseLong(oneItemMap.get("prodInstId").toString());
                        Map map = new HashMap();
                        map.put("prodInstId", prodInstId);
                        long routeId = routeDao.getRouteIdForProdInst(Long.parseLong(oneItemMap.get("ARCH_GRP_ID").toString()),
                                Long.parseLong(oneItemMap.get("ORDER_ITEM_ID").toString()), prodInstId, msg);
                        if (routeId != -1l) {
                            map.put("routeId", routeId);
                        } else {
                            msg.setResultCode(ResultCode.PAYPLAN_ERROR_013);
                            msg.setMessage("获取routeID失败，产品实例prod_inst_id为：" + prodInstId);
                            return -1;
                        }
                        objectId = payToPlanDao.getAcctId(map);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "取对应的帐户信息出错,请检查账务关系!");
                        msg.setResultCode(ResultCode.PAYPLAN_ERROR_014);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                } else {
                    objectId = Long.parseLong(oneItemMap.get("acctId").toString());
                }

                try {
                    Long seqInTerPlanId = prodInstDao.getSeq("SEQ_INTER_PLAN_ID");
                    oneItemMap.put("interPlanId", seqInTerPlanId);
                    oneItemMap.put("depositType", "4");
                    oneItemMap.put("objectType", "1");
                    oneItemMap.put("acctId", objectId);
                    oneItemMap.put("objectId", objectId);
                    oneItemMap.put("amount", oneItemMap.get("paidInAmount"));
                    oneItemMap.put("operType", "1");
                    oneItemMap.put("operState", "0");
                    oneItemMap.put("effDate", oneItemMap.get("createDate"));
                    oneItemMap.put("orderDate", oneItemMap.get("createDate"));
                    oneItemMap.put("offerInstId", oneItemMap.get("ARCH_GRP_ID"));
//                    String dateTime = df.format(new Date());
//                    oneItemMap.put("createDate",dateTime);
//                    oneItemMap.put("operDate",dateTime);
                    payToPlanDao.insertInterPayToPlan(oneItemMap);
                    sendInterPayToPlanMsg(oneItemMap);
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "增加预存款接口表出错");
                    msg.setResultCode(ResultCode.PAYPLAN_ERROR_015);
                    msg.setMessage(exceptionMsg);
                    return -1;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "一次性费用处理失败");
            msg.setResultCode(ResultCode.PAYPLAN_ERROR_016);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }


    /**
     * @return int
     * @Author maozp3
     * @Description: 更新ORD_OFFER_INST表
     * @Date: 10:30 2019/4/19
     * @Param [id, procFlag, notes]
     **/
    public int updateOrdOfferInst(Map orderMap, int procFlag, String notes) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        int i = -1;
        try {
            Map map = new HashMap();
            map.put("ARCH_GRP_ID", orderMap.get("ARCH_GRP_ID"));
            map.put("offerInstId", orderMap.get("offerInstId"));
            map.put("statusCd", procFlag);
            map.put("remark", notes);
            map.put("updateDate", df.format(new Date()));
            i = ordPayDao.updateOrdOfferInst(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return i;
    }


    /**
     * @return int
     * @Author maozp3
     * @Description: 更新ONE_ITEM_RESULT表
     * @Date: 15:02 2019/4/19
     * @Param [oneItemMap, procFlag, notes]
     **/
    public int updateOneItemResult(Map oneItemMap, int procFlag, String notes) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        int i = -1;
        try {
            Map map = new HashMap();
            map.put("ARCH_GRP_ID", oneItemMap.get("ARCH_GRP_ID"));
            map.put("ORDER_ITEM_ID", oneItemMap.get("ORDER_ITEM_ID"));
            map.put("statusCd", procFlag);
            map.put("remarks", notes);
            map.put("statusDate", df.format(new Date()));
            i = ordPayDao.modifyOneItemResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return i;
    }

    public void sendInterPayToPlanMsg(Map map) throws Exception {
        InterPayToPlan mqEntity = new InterPayToPlan();
        mqEntity.setInterPlanID(Long.parseLong(map.get("interPlanId").toString()));
        mqEntity.setDepositType(Long.parseLong(map.get("depositType").toString()));
        if (!StringUtil.isEmpty(map.get("offerId"))) {
            mqEntity.setOfferID(Long.parseLong(map.get("offerId").toString()));
        }
        mqEntity.setOfferInstID(Long.parseLong(map.get("offerInstId").toString()));
        mqEntity.setObjectID(Long.parseLong(map.get("objectId").toString()));
        mqEntity.setObjectType(Long.parseLong(map.get("objectType").toString()));
        mqEntity.setAcctID(Long.parseLong(map.get("acctId").toString()));
        if (!StringUtil.isEmpty(map.get("amount"))) {
            mqEntity.setAmount(Long.parseLong(map.get("amount").toString()));
        }

        mqEntity.setOperType(Long.parseLong(map.get("operType").toString()));
        mqEntity.setOperState(Long.parseLong(map.get("operState").toString()));
        mqEntity.setEffDate(map.get("effDate").toString());
        mqEntity.setOrderDate(map.get("orderDate").toString());

        MqObject mqObject = new MqObject();
        mqObject.setMethodName("depositPayPlan");     //DUBBO服务要调用的方法的名称
        mqObject.setParameter(JSON.toJSONString(mqEntity));  //DUBBO服务要调用的方法的入参，转换成JSON字符串格式
        mqObject.setParameterName("com.asiainfo.account.model.domain.InterPayToPlan");  //DUBBO服务要调用方法的入参的类名全称

//            MqProducer mqProducer = MqProducer.getMqProducer();
        IMQProducer mqProducer = MqTool.getMqProducer();
        MQMessage message = new MQMessage();
        byte[] bytes = JSON.toJSONString(mqObject).getBytes("UTF-8");
        message.setBody(bytes);
        message.setKey(MqUtil.createTopicKey("depositPayPlanMqTopicKey"));
        message.setSourceName("depositPayPlanMqTopic");
        message.setTag(MqUtil.createTopicTag("depositPayPlanMqTopic"));
        message.setDelayTimeLevel(0);
        MQSendResult mqSendResult = mqProducer.send(message);
        if (mqSendResult == null ||
                mqSendResult.getSendStatus() != MQSendStatus.SEND_OK) {
            logger.error("发送预存支付计划消息队列数据异常！！" + JSONObject.toJSONString(mqEntity));
//                throw new BillException(ErrorCodePublicEnum.SENT_INTERPAYTOPLAN_MSG_ERROR);
            throw new Exception();
        }


    }

    /**
     * @param errorMsg
     * @param constrantString
     * @return
     */
    public String setErrorMsg(String errorMsg, String constrantString) {
        if (errorMsg != null && !"".equals(errorMsg)) {

            return constrantString + errorMsg.substring(0, 255);
        }
        return constrantString;
    }
}
