package com.al.nppm.business.inter.service.impl;
//添加注释看看能不能提交

import com.al.nppm.business.account.dao.*;
import com.al.nppm.business.acct.dao.AElecInvContactMapper;
import com.al.nppm.business.acct.dao.AcctMapper;
import com.al.nppm.business.acct.dao.vo.AElecInvContact;
import com.al.nppm.business.acctrh.dao.SmsInfoMapper;
import com.al.nppm.business.bill.dao.BTrunkBillingMapper;
import com.al.nppm.business.bill.dao.BillMapper;
import com.al.nppm.business.billdetail.dao.BAccu2abmTifDao;
import com.al.nppm.business.core.SynMapContextHolder;
import com.al.nppm.business.cpcp.dao.CpcMapper;
import com.al.nppm.business.inter.http.state.statePublic;
import com.al.nppm.business.inter.service.IHisService;
import com.al.nppm.business.syntomq.datasyn.DataSynDeal;
import com.al.nppm.business.syntomq.datasyn.Msg;
import com.al.nppm.business.syntomq.tool.CTGMqTool;
import com.al.nppm.business.syntomq.tool.MqToolAcctChange;
import com.al.nppm.business.syntomq.tool.STATUS;
import com.al.nppm.business.syntomq.tool.TopicType;
import com.al.nppm.common.errorcode.ErrorCodePublicEnum;
import com.al.nppm.common.errorcode.ResultCode;
import com.al.nppm.common.utils.DateUtils;
import com.al.nppm.common.utils.HttpRequestUtil;
import com.al.nppm.common.utils.PropertiesUtil;
import com.al.nppm.common.utils.StringUtil;
import com.al.nppm.model.Message;
import com.al.nppm.ord.ordbill.dao.OrdBillMapper;
import com.al.nppm.ord.ordbill.dao.OrdRouteMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//import javax.validation.constraints.Null;

@Service("crmUserService")
public class CrmUserService {
    private static Logger logger = Logger.getLogger(CrmUserService.class);

    //private static ExecutorService service = Executors.newFixedThreadPool(50);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String expDateString = "3000-01-01";
    private static PropertiesUtil propertiesUtil=new PropertiesUtil("config/sysConfig.properties");
    @Autowired
    public IAccountMapper accountDao;
    @Autowired
    public IProdInstMapper prodinstDao;
    @Autowired
    public OfferInstMapper offerinstDao;
    @Autowired
    public OrdBillMapper ordBillDao;
    @Autowired
    public CustomerMapper customerMapperDao;
    @Autowired
    public ProductNetworkMapper productNetworkMapperDao;
    @Autowired
    public BTrunkBillingMapper bTrunkBillingMapperDao;
    @Autowired
    public IHisService hisService;
    @Autowired
    public TifChangeAcctMapper tifChangeAcctMapperDao;
    @Autowired
    public TifVpnGroupMapper tifVpnGroupMapperDao;
    @Autowired
    public CcmUserOrderMapper ccmUserOrderMapperDao;
    @Autowired
    public RouteService routeServiceDao;
    @Autowired
    public OrdRouteMapper ordRouteMapperDao;
    @Autowired
    public IRouteMapper routeMapperDao;
    @Autowired
    public BillMapper billMapperDao;
    @Autowired
    public CpcMapper cpcMapperDao;
    @Autowired
    AcctMapper acctMapperDao;
    @Autowired
    AElecInvContactMapper aElecInvContactMapperDao;
    @Autowired
    SmsInfoMapper smsInfoMapperDao;
    @Autowired
    BAccu2abmTifDao bAccu2abmTifDao;
    // @Autowired
    // public ProdOfferMapper prodOfferDao;

//	@Autowired
//	public OrdService ordService;


    // public long acct_id;
    public long routeCustId;

    public void runTask1(Long id, Message msg, Map userMap) throws Exception {


    }


    public void runTask(String[] args) throws Exception {
        Date date = new Date();
        logger.debug("----------定时执行CRMTABLE---------------" + sdf.format(date).toString());
        int sumCount = 0;//本次处理总数
        int successCount = 0;//本次处理成功数
        long startTime = System.currentTimeMillis();

        long id = 0;

        String notes;
        // acct_id=0;
        routeCustId = 0;

        //Message msg = new Message();
        // userMap存放大对象，发往消息
        Map userMap = new HashMap();

        Map queryMap = new HashMap();
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (i == 0) {
                    queryMap.put("mod", args[0]);
                } else if (i == 1) {
                    queryMap.put("modValue", args[1]);
                } else if (i == 2) {
                    String tmp = args[2];
                    if (tmp.startsWith("(")) {
                        queryMap.put("regionFlag", "in");
                        queryMap.put("regionId", args[2].substring(1, args[2].length() - 1));
                    } else if (tmp.startsWith("!")) {
                        queryMap.put("regionFlag", "notin");
                        queryMap.put("regionId", args[2].substring(2, args[2].length() - 1));
                    } else {
                        queryMap.put("regionId", args[2]);
                    }
                } else if (i == 3) {
                    queryMap.put("proc_flag", args[3]);
                }
            }

        }
        List<Map<String, Object>> list = ordBillDao.selectOrdBill(queryMap);//获取总控表数据
        long orderStartTime = 0L;//工单开始处理
        long orderProcTime = 0L;//工单处理时长
        if (list.size() > 0) {
            sumCount = list.size();
            SynMapContextHolder.remove();
            for (int i = 0; i < list.size(); i++) {
                SynMapContextHolder.init();//初始化送密集框架的变量
                orderStartTime = System.currentTimeMillis();
                int flag = -1;
                int iCount = -1;
                String areaCode = "";
                Map<String, List<?>> synMap = new HashMap<String, List<?>>();
                Message msg = new Message();
                WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

                DataSourceTransactionManager transactionManager_acct = (DataSourceTransactionManager) contextLoader.getBean("transactionManager_acct");
                DefaultTransactionDefinition def_acct = new DefaultTransactionDefinition();
                def_acct.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status_acct = transactionManager_acct.getTransaction(def_acct); // 获得事务状态

                DataSourceTransactionManager transactionManager_bill = (DataSourceTransactionManager) contextLoader.getBean("transactionManager_bill");
                DefaultTransactionDefinition def_bill = new DefaultTransactionDefinition();
                def_bill.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status_bill = transactionManager_bill.getTransaction(def_bill); // 获得事务状态

                prodinstDao.udalStart();

                try {
                    SynMapContextHolder.initSynMap(synMap);//初始化变量

                    Map map = (Map) list.get(i);
                    SynMapContextHolder.put("routeCustId", map.get("CUST_ID").toString());
                    String strResultmsgString = "";
                    String strResultCode = "";
                    id = Long.parseLong(map.get("ARCH_GRP_ID").toString());
                    SynMapContextHolder.put("archGrpId", map.get("ARCH_GRP_ID"));
                    // 1.强制提交总控制表 改为已处理
//					iCount = tranManager(id, "");

                    long startOperTime = System.currentTimeMillis();

                    // 2.处理业务逻辑  flag=1处理成功、其他失败
                    flag = CrmUserTable(map, msg, userMap);
                    // 3.发消息
                    // DataSynDeal.autoBuildAndSendMsg(synMap);
                    // rs_storge
                    //strResultmsgString = msg.getMessage() + "。处理时长" + (System.currentTimeMillis()-startOperTime) + "ms";
                    if (flag == -2) {//-2 重复校验失败
                        // 回滚
                        transactionManager_bill.rollback(status_bill);
                        transactionManager_acct.rollback(status_acct);
                        transactionManager.rollback(status);
                    } else if (flag < 0) {
                        // 回滚
                        strResultCode = msg.getResultCode();
                        strResultmsgString = strResultCode + "=" +  msg.getMessage() + "。处理时长" + (System.currentTimeMillis() - startOperTime) + "ms";
                        transactionManager_bill.rollback(status_bill);
                        transactionManager_acct.rollback(status_acct);
                        transactionManager.rollback(status);
                        updateOrdBill(id, 2, strResultmsgString);
                    } else {
                        Map extendMap = new HashMap();
                        extendMap.put("custId", map.get("CUST_ID").toString());
                        extendMap.put("archGrpId", id);
                        flag = addMsg(synMap, extendMap, msg);
                        if (flag == 1) {
                            transactionManager_bill.commit(status_bill);
                            transactionManager_acct.commit(status_acct);
                            transactionManager.commit(status);
                            successCount++;
                            orderProcTime = System.currentTimeMillis() - orderStartTime;
                            logger.info("归档工单：" + id + "处理时长：" + orderProcTime + "毫秒");
                            strResultmsgString = "处理成功。处理时长" + (System.currentTimeMillis() - startOperTime) + "ms";
                            updateOrdBill(id, 1, strResultmsgString);
                        } else {
                            // 回滚
                            transactionManager_bill.rollback(status_bill);
                            transactionManager_acct.rollback(status_acct);
                            transactionManager.rollback(status);
                            updateOrdBill(id, 2, msg.getMessage());
                        }

                    }

                    // 3.提交处理结果
//                  iCount = tranManager(id, msg.getMessage());

//                  updateOrdBill(id,1, msg.getMessage());

                } catch (Exception e) {
                    //总控状态2失败
                    e.printStackTrace();
                    transactionManager_bill.rollback(status_bill);
                    transactionManager_acct.rollback(status_acct);
                    transactionManager.rollback(status);
                    logger.error("处理失败！" + e.getMessage());

                    updateOrdBill(id, 2, msg + e.getMessage() != null && e.getMessage().length() > 256 ? e.getMessage().substring(0, 256) : e.getMessage());
//                  iCount = tranManager(id, msg.getMessage());
                }
                // 消息发送
//                try {
//                    if (flag > 0) {
////                      DataSynDeal.autoBuildAndSendMsg(synMap);
//                        logger.debug("发送开始时间：" + sdf.format(new Date()));
////                        service.submit(new SendMsgThread(synMap));  //目前还没和密集计算框架联调，先注释掉
////                      sendMsg(synMap);
//                        logger.debug("发送结束时间：" + sdf.format(new Date()));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }
        logger.debug("本次处理开始时间：" + sdf.format(date).toString() +
                "\t花费总时间：" + (System.currentTimeMillis() - startTime) + "毫秒\t" +
                "本次处理归档数：" + sumCount +
                "\t成功数：" + successCount +
                "\t失败数：" + (sumCount - successCount));
    }

    /*发送消息到密集计算框架*/
    public void sendMsg(Map synMap, long archGrpId) {
        Map map = new HashMap();
        int sendFlag = -1;
        Msg msginfo = null;
        try {
            List<?> table = DataSynDeal.mapToList(synMap);
            Map<String, Object> params = DataSynDeal.buildParam();
            msginfo = DataSynDeal.buildMsg(table, params);
            sendFlag = DataSynDeal.sendMsg(msginfo);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            map.put("id", prodinstDao.getSeq("seq_send_msg"));
            map.put("msg", JSON.toJSON(msginfo.getMap()).toString().getBytes());
            map.put("status", sendFlag);
            map.put("messageId", msginfo.getMap().get("message_id").toString());
            map.put("messageType", msginfo.getMap().get("message_type").toString());
            map.put("send_count", 1);
            map.put("archGrpId", archGrpId);
            prodinstDao.insertSendMsg(map);
        }
    }

    /**
     * 新增消息数据到表中
     *
     * @param synMap
     * @return
     */
    public int addMsg(Map synMap, Map extendMap, Message msg) {
        Map map = new HashMap();
        int flag = -1;
        Msg msginfo = null;
        try {
            List<?> table = DataSynDeal.mapToList(synMap);
            Map<String, Object> params = DataSynDeal.buildParam();
            msginfo = DataSynDeal.buildMsg(table, params);
            JSONObject msgdata = (JSONObject) JSON.toJSON(msginfo.getMap());
            msgdata.put("arch_grp_id", extendMap.get("archGrpId").toString());//报文中加上arch_grp_id字段

            map.put("id", prodinstDao.getSeq("SEQ_SEND_MSG"));
//            map.put("msg", JSON.toJSON(msginfo.getMap()).toString().getBytes());
            map.put("msg", msgdata.toString().getBytes("UTF-8"));
            map.put("status", 0);
            map.put("messageId", msginfo.getMap().get("message_id").toString());
            map.put("messageType", msginfo.getMap().get("message_type").toString());
            map.put("sendCount", 0);
            map.put("createDate", new Date());
            map.put("custId", extendMap.get("custId"));
            map.put("archGrpId", extendMap.get("archGrpId"));
            prodinstDao.insertSendMsg(map);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /*定时器重发失败的消息*/
    public void reSendMsg(String[] args) {
        Map queryMap = new HashMap();
        Msg msginfo = new Msg();
        //多任务处理，按照cust_id区分
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (i == 0) {
                    queryMap.put("mod", args[0]);
                } else if (i == 1) {
                    queryMap.put("modValue", args[1]);
                }
            }
        }

        List<Map<String, Object>> list = prodinstDao.selectSendMsg(queryMap);
        for (Map<String, Object> map : list) {
            byte[] buffer = (byte[]) map.get("msg");
            STATUS result;
            String remark = "";
            try {
                result = CTGMqTool.send(map.get("message_id").toString(),
                        map.get("message_type").toString(),
                        new String(buffer,"UTF-8"));
                queryMap.put("id", map.get("id").toString());
                queryMap.put("status", result.getCode());
                queryMap.put("remark", remark);
//                prodinstDao.updateSendMsgLog(queryMap);
            } catch (Exception e) {
                queryMap.put("status", -1);
                remark=e.getMessage();
                if(remark.length()>128){
                    remark=e.getMessage().substring(0,128);
                }
                queryMap.put("remark", remark);
            } finally {
                queryMap.put("id", map.get("id").toString());
                prodinstDao.updateSendMsgLog(queryMap);
                if ((Integer) queryMap.get("status") != 2) {
                    break;
                }
            }
        }
    }


    /* 一次性费用处理 */
    public void oneItemrunTask() throws Exception {
        Message msg = new Message();
        String remarks = "处理中";
        System.out.println("----------定时执行一次性费用- oneItemResult--------------"
                + new Date());

        List<Map<String, Object>> oneList = ordBillDao.getOneItemResult();
        if (oneList.size() > 0) {
            for (int i = 0; i < oneList.size(); i++) {

                // 开启事务控制
                WebApplicationContext contextLoader = ContextLoader
                        .getCurrentWebApplicationContext();
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader
                        .getBean("transactionManager");
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager
                        .getTransaction(def); // 获得事务状态
                Map map = new HashMap();
                try {
                    map = oneList.get(i);

                    OneItemtranManager(
                            Long.parseLong(map.get("ARCH_GRP_ID").toString()),
                            remarks);

                    int flag = InsertItemPlan(msg, map);
                    if (flag < 0) {
                        transactionManager.rollback(status);
                    } else {
                        transactionManager.commit(status);
                    }

                    OneItemtranManager(
                            Long.parseLong(map.get("ARCH_GRP_ID").toString()),
                            msg.getMessage());

                } catch (Exception e) {
                    e.printStackTrace();
                    msg.setMessage("一次性费用处理失败");

                    transactionManager.rollback(status);
                    OneItemtranManager(
                            Long.parseLong(map.get("ARCH_GRP_ID").toString()),
                            msg.getMessage());
                }
            }
        }

    }

    /* 一次性费用处理 */
    public int oneItemrunTask(Map map, Message msg) throws Exception {
        String remarks = "处理中";
        System.out.println("----------定时执行一次性费用- oneItemResult--------------"
                + new Date());
        long archGrpId = Long.parseLong(map.get("ARCH_GRP_ID").toString());
        long ordItemId = Long.parseLong(map.get("ORDER_ITEM_ID").toString());
        long offerInstId = 0;
        List<Map<String, Object>> crmRentList = ordBillDao.selectCrmRent(archGrpId, ordItemId, offerInstId,1);
        List<Map<String, Object>> oneList = ordBillDao.getOneItemResultFromArchGrpId(archGrpId, ordItemId);
        List<Map<String, Object>> oneListHist = ordBillDao.getOneItemResultHisFromArchGrpId(archGrpId, ordItemId);
        if (oneList.size() > 0 && crmRentList.size() == 0 && oneListHist.size() == 0) {
            for (int i = 0; i < oneList.size(); i++) {

                Map mapTemp = new HashMap();
                try {
                    mapTemp = oneList.get(i);
                    int flag = InsertItemPlan(msg, mapTemp);
                    if (flag < 0) {
                        return -1;
                    }
                    ordBillDao.insertOneItemResultHis(mapTemp);
                } catch (Exception e) {
                    e.printStackTrace();
                    msg.setMessage("一次性费用处理失败");
                    throw e;
                }
            }
        }

        return 1;
    }

    public int InsertItemPlan(Message msg, Map map) throws Exception {
        try {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            Long chargeMethod = Long.parseLong(map.get("chargeMethod")
                    .toString());
            Long acctItemTypeId = Long.parseLong(map.get("acctItemTypeId").toString());
            Long archGrpId = Long.parseLong(map.get("archGrpId").toString());
            Long orderItemId = Long.parseLong(map.get("orderItemId").toString());
            Long prodInstId = Long.parseLong(map.get("prodInstId").toString());
            Long offerInstId = Long.parseLong(map.get("offerInstId").toString());
            if (chargeMethod == 6) // 转计费代收
            {
                long feeBillId = prodinstDao.getSequeId();

                Map feeBillMap = new HashMap();
                feeBillMap.put("areaCode", 111);
                feeBillMap.put("serialnumber", feeBillId);
                feeBillMap.put("feeSerial", map.get("archGrpId"));
                feeBillMap.put("orderNo", map.get("archGrpId"));
                feeBillMap.put("userId", map.get("prodInstId"));
                feeBillMap.put("acctItemType",
                        map.get("billAcctItemTypeId"));
                feeBillMap.put("payCharge", map.get("paidInAmount"));
                feeBillMap.put("payTimes", 1);
                feeBillMap.put("feeDate", map.get("createDate"));
                feeBillMap.put("effDate", map.get("createDate"));
                feeBillMap.put("state", 0);
                feeBillMap.put("notes", "CRM一次性费用");

                ordBillDao.insertTifFeeBill(feeBillMap);
            } else {
                Map oneItem = new HashMap();
                long interPlanId = prodinstDao.getSeq("SEQ_INTER_PLAN_ID");
                oneItem.put("interPlanId", interPlanId);// zhujian
				/*List<Map<String, Object>> offerProdInstList = ordBillDao.selectOrdOfferProdInstRelForProdInst(archGrpId, offerInstId);
				if (offerProdInstList.size() == 0){
					offerProdInstList = ordBillDao.selectOrdOfferObjInstRelForObjectId(archGrpId, offerInstId);
                }*/
                // 0418 offerInstId 查找acct_id
                long acctId = Long.parseLong(map.get("acctId")
                        .toString());
               /* Map mapOfferInst = new HashMap();
                mapOfferInst.put("offerInstId", offerInstId);

                List<Map<String, Object>> offerlist = ordBillDao
                        .getOneItemInstId(mapOfferInst);
                long instId = 0;
                if (offerlist.size() > 0) {
                    Map offer = offerlist.get(0);
                    instId = Long.parseLong(offer.get("prodInstId").toString());
                    oneItem.put("objectId", offer.get("prodInstId"));
                    // oneItem.put("acctId", offer.get("acctId")) ;
                } else {
                    msg.setMessage("未找到prod_inst_id");
                    return -1;
                }*/
                if (acctId < 1) {
                    Map servAcctMap = new HashMap();
                    servAcctMap.put("prodInstId", prodInstId);
                    long routeId = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, prodInstId, msg);
                    if (routeId < 0) {
                        msg.setMessage("预存取用户routeId失败");
                        return -1;
                    }
                    servAcctMap.put("routeId", routeId);
                    List<Map<String, Object>> servAcctList = prodinstDao
                            .getProdInstAcctIdForNull(servAcctMap);

                    if (servAcctList.size() > 0) {
                        Map acctMap = servAcctList.get(0);
                        oneItem.put("objectId", acctMap.get("acctId"));
                        // oneItem.put("acctId", offer.get("acctId")) ;
                    }
                } else {
                    oneItem.put("objectId", acctId);
                }
                oneItem.put("acctId", acctId);
                oneItem.put("offerInstId", map.get("offerInstId"));
                oneItem.put("offerId", map.get("offerId"));
                oneItem.put("objectType", 2);
                oneItem.put("operType", 1);
                oneItem.put("operState", 0);
                oneItem.put("effDate", map.get("createDate"));
                oneItem.put("orderDate", map.get("createDate"));
                oneItem.put("createDate", df.format(date));
                oneItem.put("operDate", df.format(date));
                oneItem.put("amount", map.get("paidInAmount"));
                oneItem.put("depositType", 4);
                prodinstDao.insertPayToPlan(oneItem);

            }
            msg.setMessage("处理成功");

        } catch (Exception e) {
            e.printStackTrace();
            msg.setMessage("一次性费用新增失败");
            throw e;
        }
        return 1;
    }

    public int CrmUserTable(Map ordMap, Message msg, Map userMap)
            throws Exception {
        Long archGrpId = Long.parseLong(ordMap.get("ARCH_GRP_ID").toString());
        Long custId = Long.parseLong(ordMap.get("CUST_ID").toString());
        //add by wangbaoqiang begin
		/*List<Map<String, Object>> tifOrgContrastMap = ordBillDao.selectTifOrgContrast(ordMap);
		if (tifOrgContrastMap.size() != 1) {
			msg.setMessage("取计费对应的org_id出错");
			return -1;
		}// add end;*/
        String tableName;
        long orderItemId;
        long serviceOfferId;
        int action; // 1 新增 2修改 3 删除
        String sOfferId;
        ErrorCodePublicEnum errorCodePublicEnum;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        //crm会送重复拆机的数据过来，做以下过滤
        /*List<Map<String,Object>> prodInstCjList = ordBillDao.getOrdBillObjCj(archGrpId);
        if (prodInstCjList.size() == 1) {
            Map prodInstCjMap = prodInstCjList.get(0);
            long cntProd2hx =   smsInfoMapperDao.selectProdInst2hx(prodInstCjMap);
            if (cntProd2hx > 0) {
                logger.error("计费产品产品实例已经拆机【prodInstId】：" + prodInstCjMap.get("objId"));
                return 1;
            }
        }*/
        List<Map<String, Object>> ordObjlist = ordBillDao
                .selectOrdBillObj(archGrpId);
        if (ordObjlist.size() == 0) {
            msg.setMessage("总控对象表无对应记录!");
            return 1;
        }
        for (int k = 0; k < ordObjlist.size(); k++) {
            // ----------------------开始处理表数据-------------------------
            Map map = (Map) ordObjlist.get(k);
            map.put("FINISH_DATE", ordMap.get("FINISH_DATE"));
            tableName = map.get("TABLE_NAME").toString();
            orderItemId = Long.parseLong(map.get("ORDER_ITEM_ID").toString());
            serviceOfferId = Long.parseLong(map.get("SERVICE_OFFER_ID")
                    .toString());
            sOfferId = map.get("SERVICE_OFFER_ID").toString();

            //检查工单先后顺序(新装用户不校验前置工单)
            if (!"4010100000".equals(sOfferId)
                    && !"4010200000".equals(sOfferId)
                    && !"4010300000".equals(sOfferId)) {
                if (workOrder(map, userMap, msg) < 0) {
                    return -1;
                }
            }
            //一次性费用和crm普通预存 先处理预存，不能放到ord_offer_inst处理单元里，普通预存款会漏掉
            //放到oneTimesChargeTask方法里，用job定时跑
			/*if (oneItemrunTask(map, msg)<0) {
				return -1;
			}*/
            // 1.ORD_CUSTOMER 处理
            if ("ORD_CUSTOMER".equals(tableName)) {
                try {
                    if (doOrdCustomer(map,msg,archGrpId,orderItemId) < 0) {
                        return -1;
                    }
                } catch (Exception e) {
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "处理客户ORD_CUSTOMER失败");
                    msg.setResultCode(ResultCode.CUSTOMER_ERROR_015);
                    msg.setMessage(exceptionMsg);
                    e.printStackTrace();
                    return -1;
                }


            }
            // 3.ORD_ACCOUNT 处理
            else if ("ORD_ACCOUNT".equals(tableName)) {
                // 3.1 ord_account
                try {
                    if (doOrdAccount(map, msg, archGrpId, orderItemId) < 0) {
                        return -1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "处理账户ORD_ACCOUNT失败");
                    msg.setResultCode(ResultCode.ACCOUNT_ERROR_022);
                    msg.setMessage(exceptionMsg);
                    return -1;
                }


            } else if ("ORD_PROD_INST".equals(tableName)) {
                try {

                    // 新装
                    if ("4010100000".equals(sOfferId)
                            || "4010200000".equals(sOfferId)
                            || "4010300000".equals(sOfferId)) {
                        if (insertProdInst(map, userMap, msg) < 0) {
                            return -1;
                        }
                        /*没有找到可以修改的成员实例
                         * if(insertProdInstState(map, userMap, msg,sOfferId)<0)
                         * { return -1; } if(insertProdInstAccNum(map, userMap,
                         * msg,sOfferId)<0) { return -1; }
                         */
                    }// 拆机
                    else if ("4020100000".equals(sOfferId)
                            || "4020300003".equals(sOfferId)) {


                        if (deleteProdInst(map, userMap, msg) < 0) {
                            return -1;
                        }


                    }// 更新
                    else {

                        if (updateProdInst(map, userMap, msg, sOfferId) < 0) {
                            return -1;
                        }

                    }
                    // 取用户功能产品，需要默认生成属性
                    List<Map<String, Object>> prodInstSubList = ordBillDao
                            .selectOrdProdInstSub(map);
                    for (Map<String, Object> prodInstSubMap : prodInstSubList) {
                        if (insertProdInstSub(prodInstSubMap, userMap, msg) < 0) {
                            return -1;
                        }
                    }

                    // 处理用户属性prod_inst_attr
                    List<Map<String, Object>> prodInstAttrList = ordBillDao
                            .selectOrdProdInstAttr(map);
                    for (Map<String, Object> prodInstAttrMap : prodInstAttrList) {
                        if (doProdInstAttr(prodInstAttrMap, userMap, msg) < 0) {
                            return -1;
                        }
                    }

					/*if (doProdInstSub(map, userMap, msg) < 0) {
						return -1;
					}*/
                    //add by wangbaoqiang 处理 用户群关系关系  暂时只处理群组
                    List<Map<String, Object>> prodInstRelList = ordBillDao
                            .selectOrdProdInstRel(map);
                    for (Map<String, Object> prodInstRelMap : prodInstRelList) {
                       /* prodInstRelMap.put("prodId", prodInstRelMap.get("aProdId"));
                        List<Map<String, Object>> tifProdContrast = ordBillDao.selectTifProdContrast(prodInstRelMap);
                        if (tifProdContrast.size() == 0) {
                            continue;
                        }else{
                            Map tifProdMap = tifProdContrast.get(0);*/
                            //if ("10D".equals(tifProdMap.get("prodType"))) {
                                if (doProdInstGroup(prodInstRelMap, userMap, msg) < 0) {
                                    return -1;
                                }
                           // }
                       // }

                    }

                   /* // 处理 prod_inst_rel关系
					if (doProdInstRel(map, userMap, msg) < 0) {
						return -1;
					}*/

                } catch (Exception e) {
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "处理账户ORD_PROD_INST失败");
                    msg.setResultCode(ResultCode.PRODINST_ERROR_001);
                    msg.setMessage(exceptionMsg);
                    e.printStackTrace();
                    return -1;
                }
            } else if ("ORD_PROD_INST_ACCT_REL".equals(tableName)) {
                try {

                    if (doProdInstAcctRel(map, userMap, msg, serviceOfferId) < 0) {
                        return -1;
                    }
                } catch (Exception e) {
                    msg.setResultCode(ResultCode.PRODACCT_ERROR_015);
                    msg.setMessage("处理账户ORD_PROD_INST_ACCT_REL失败");
                    e.printStackTrace();
                    throw e;
                }
            } else if ("ORD_OFFER_INST".equals(tableName)) {
                try {
                    //add by wangbaoqiang 按照四川模式处理 begin
                    List<Map<String, Object>> offerInstList = ordBillDao
                            .selectOrdOfferInst(map);
                    if (offerInstList.size() > 0) {
                        for (Map<String, Object> offerInstMap : offerInstList) {
                            // 先屏蔽调计费没有的套餐
                            long offerId = Long.parseLong(offerInstMap.get("offerId").toString());
                            List<Map<String, Object>> offerList = billMapperDao.selectOfferId(offerId);
                            if (offerList.size() == 0) {
                                //crm程控套餐还是按以前的落，需要进行转换
                                List<Map<String, Object>> tifContrastofferList = ordBillDao.selectTifDisctIdContrast(offerId);
                                if (tifContrastofferList.size() == 0) {
                                    continue;
                                }
                            }
                            //屏蔽计费不需要的套餐
                            List<Map<String, Object>> offerNoNeedList = ordBillDao.selectTifNoNeedOffer(offerId);
                            if (offerNoNeedList.size() > 0) {
                                continue;
                            }
                            List<Map<String, Object>> regionList = ordBillDao.selectTifOrgContrast(offerInstMap);
                            if (regionList.size() > 0) {
                                Map regionMap = new HashMap();
                                regionMap = regionList.get(0);
                                String areaCode = regionMap.get("areaCode").toString();
                                offerInstMap.put("regionId", regionMap.get("orgIdBill"));
                                String lanId = areaCode.substring(1, 4);
                                offerInstMap.put("lanId", lanId);
                            }
                            String operType = offerInstMap.get("operType").toString();
                            long routeId;
                            long offerInstId = Long.parseLong(offerInstMap.get("offerInstId").toString());
                            routeId = routeServiceDao.getRouteIdForOfferInst(archGrpId, orderItemId, offerInstId, msg);
                            if (routeId <= 0) {
                                // TODO: 2019/7/29 ivpn套餐壳子先下来，没有成员，找不到acct_Id，写以下语句，ord_bill_obj只有一条记录时就不处理,后续在成员变更时计费会补订
                                long cntOrdBillObj = ordBillDao.getCntOrdBillObj(offerInstMap);
                                if (cntOrdBillObj == 1&&!"1100".equals(operType)) {
                                    return 1;
                                } else {
                                    msg.setResultCode(ResultCode.OFFERINS_ERROR_001 );
                                    msg.setMessage("取商品实例路由表失败【offerInstId】:" + offerInstId);
                                    return -1;
                                }

                            }

                            //新装
                            if ("1000".equals(operType)
                                    && !"3020501002".equals(String.valueOf(serviceOfferId))) {
                                if (insertOfferInst(offerInstMap, userMap, msg, routeId) < 0) // 销售品实例
                                {
                                    return -1;
                                }
                            }
                            //修改销售品时间 好像没有修改销售品时间这个服务呢
							else if ("1000".equals(operType)
									&& "3020501002".equals(String.valueOf(serviceOfferId))) {
								if (doUpdateOfferInst(offerInstMap, userMap, msg, routeId) < 0) {
									return -1;
								}

							}
                            else if ("1300".equals(operType)) {
                                //添加副卡，成员纳入
                                if ("3020800000".equals(String.valueOf(serviceOfferId))) {
                                    if (insertOfferProdGroup(offerInstMap, userMap, msg, routeId) < 0) {
                                        return -1;
                                    }

                                }
                                //修改参数
                                if ("3020501001".equals(String.valueOf(serviceOfferId))) {
                                    if (doUpdateOfferInstAtrr(offerInstMap, userMap, msg, routeId) < 0) {
                                        return -1;
                                    }
                                }
                            }
                            //退订
                            else if ("1100".equals(operType)
                                    && !"3020501002".equals(String.valueOf(serviceOfferId))) {
                                if (deleteOfferInst(offerInstMap, userMap, msg, routeId) < 0) {
                                    return -1;
                                }
                            }
                            //crm活动预存 放在acctProDepositTask方法里，通过job来单独跑
							/*if (doInsertPayToPlan(offerInstMap, userMap, msg, routeId,serviceOfferId)  < 0) {
								return -1;
							}*/

                        }
                    }

                } catch (Exception e) {
                    msg.setMessage("处理ORD_OFFER_INST失败");
                    msg.setResultCode(ResultCode.OFFERINS_ERROR_002);
                    e.printStackTrace();
                    throw e;
                }
            } /*else if (tableName.equals("ORD_OFFER_PROD_INST_REL")) {
				try {
					if (insertOfferProdInst(map, userMap, msg, routeCustId) < 0) // 销售品角色
					{
						return -1;
					}

				} catch (Exception e) {
					// msg.setMessage("处理账户ORD_PROD_INST_ACCT_REL失败");
					e.printStackTrace();
					throw e;
				}
			} else if (tableName.equals("ORD_OFFER_INST_ATTR")) {
				try {
					if (insertOfferInstAtrr(map, userMap, msg, routeCustId) < 0)// 销售品对象角色
					{
						return -1;
					}

				} catch (Exception e) {
					// msg.setMessage("处理账户ORD_PROD_INST_ACCT_REL失败");
					e.printStackTrace();
					throw e;
				}
			}*/ else if ("ORD_PARTY".equals(tableName)) {

                String V_RET_CODE = insertParty(archGrpId, orderItemId, serviceOfferId, msg);
                if (!"1".equals(V_RET_CODE)) {
                    return -1;
                }


            } else if ("ORD_TAX_PAYER".equals(tableName)) {
                String V_RET_CODE = insertTaxPayer(archGrpId, orderItemId, msg);
                if (!"1".equals(V_RET_CODE)) {
                    return -1;
                }

            }

            //msg.setMessage("处理成功");
        }
        return 1;
    }

    //TODO  销售品实例属性
    public int insertOfferInstAtrr(Map itemMap, Map userMap, Message msg,
                                   long acctId) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            DecimalFormat df1=new DecimalFormat("#.#");
            List<Map<String, Object>> offerInstAttrList = ordBillDao
                    .selectOrdOfferInstAttr(itemMap);
            if (offerInstAttrList.size() > 0) {
                for (int i = 0; i < offerInstAttrList.size(); i++) {
                    Map offerInstAttrMap = new HashMap();
                    offerInstAttrMap = offerInstAttrList.get(i);
                    if (StringUtil.isEmpty(offerInstAttrMap.get("statusDate"))) {
                        offerInstAttrMap.put("executetime", d.format(new Date()));
                    } else {
                        offerInstAttrMap.put("executetime", d.format(df
                                .parse(offerInstAttrMap.get("statusDate")
                                        .toString())));
                    }

                    String operType = offerInstAttrMap.get("operType")
                            .toString();
                    offerInstAttrMap.put("routeId", acctId);
                    String attrId = String.valueOf(offerInstAttrMap.get("attrId"));
                    String offerId = String.valueOf(itemMap.get("offerId"));
                    String allowFlag = "Y";
                    String attrIdBill = "";
                    String attrValueBill = "";
                    List<Map<String,Object>> tifParamIdList = ordBillDao.selectTifParamId(Long.parseLong(offerId), attrId);
                    if (tifParamIdList.size() > 1 ) {
                        msg.setResultCode(ResultCode.OFFERINS_I_ERROR_017);
                        msg.setMessage("取对应的套餐参数编码时出错【offerId】：" + offerId + ",【attrId】：" + attrId);
                        return -1;
                    } else if (tifParamIdList.size() == 0) {
                        long cnt = billMapperDao.getofferAttrIdCnt(Long.parseLong(offerId), Long.parseLong(attrId));
                        if (cnt == 0) {
                            msg.setResultCode(ResultCode.OFFERINS_I_ERROR_018);
                            msg.setMessage("取对应的套餐参数编码时出错【offerId】：" + offerId + ",【attrId】：" + attrId);
                            return -1;
                            //continue;
                        } else {
                            attrIdBill = attrId;
                            /*msg.setResultCode(ResultCode.OFFERINS_I_ERROR_018);
                            msg.setMessage("取对应的套餐参数编码时出错【offerId】：" + offerId + ",【attrId】：" + attrId);
                            return -1;*/
                        }

                    } else {
                        Map tifParamMap = tifParamIdList.get(0);
                        allowFlag = String.valueOf(tifParamMap.get("allowFlag"));
                        attrIdBill = String.valueOf(tifParamMap.get("disctParamIdBill"));
                        attrValueBill = "";
                        //166参数转化
                        if ("Y".equals(tifParamMap.get("isRent"))) {
                            String attrValue = String.valueOf(offerInstAttrMap.get("attrValue"));
                            double dattrValue = Double.parseDouble(attrValue) * 10;
                            attrValueBill = df1.format(dattrValue);
                            offerInstAttrMap.put("attrValue", attrValueBill); }

                    }
                    if (operType.equals("1000") && "Y".equals(allowFlag)) {
                        // 判断是否存在
                        List<Map<String, Object>> oldOfferInstAttrList = offerinstDao
                                .getOfferInstAttrId(offerInstAttrMap);
                        if (oldOfferInstAttrList.size() == 0) {
                            offerInstAttrMap.put("routeId", acctId);
                            offerInstAttrMap.put("statusCd", 1000);
                            offerInstAttrMap.put("attrId", attrIdBill);
                            offerinstDao.insertOfferInstAttr(offerInstAttrMap);
                            offerInstAttrMap.put("action", 1);
                            SynMapContextHolder.addMap("offerInstAttrobjList1", offerInstAttrMap);
                        }
                    } else if (operType.equals("1100") && "Y".equals(allowFlag)) {
                        // 0414 route_id
                        Long ROUTE_ID = 0L;
                        List<Map<String, Object>> oldOfferInstAttrList1 = new ArrayList<Map<String, Object>>();
                        //先按照统一序列来查找，找不到按照attrId和实例来查找
                        oldOfferInstAttrList1 = offerinstDao.getOfferInstAttrId(offerInstAttrMap);
                        if (oldOfferInstAttrList1.size() == 0) {
                            offerInstAttrMap.put("attrId", attrIdBill);
                            oldOfferInstAttrList1 = offerinstDao.getOfferInstAttrIdFromAttrId(offerInstAttrMap);
                            if (oldOfferInstAttrList1.size() == 0) {
                                msg.setResultCode(ResultCode.OFFERINS_I_ERROR_014);
                                msg.setMessage("没有找到符合条件的销售品实例属性,无法修改【offerInstId】:" + offerInstAttrMap.get("offerInstId"));
                                return -1;
                            }
                        }

                        for (Map<String, Object> attrMap : oldOfferInstAttrList1) {
                            attrMap.put("expDate", offerInstAttrMap.get("expDate"));
                            attrMap.put("statusCd", 1100);
                            offerinstDao.updateOfferInstAttr(attrMap);
                            attrMap.put("action", 2);
                            attrMap.put("executetime", offerInstAttrMap.get("executetime"));
                            SynMapContextHolder.addMap("offerInstAttrobjList1", attrMap);

                        }

                    }


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理账户ORD_OFFER_INST_attr失败");
            msg.setResultCode(ResultCode.OFFERINS_I_ERROR_015);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    // 销售品实例属性
    public int doUpdateOfferInstAtrr(Map itemMap, Map userMap, Message msg,
                                   long acctId) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            DecimalFormat df1=new DecimalFormat("#.#");
            List<Map<String, Object>> offerInstAttrList = ordBillDao
                    .selectOrdOfferInstAttrDec(itemMap);
            if (offerInstAttrList.size() > 0) {
                for (int i = 0; i < offerInstAttrList.size(); i++) {
                    Map offerInstAttrMap = new HashMap();
                    offerInstAttrMap = offerInstAttrList.get(i);
                    if (StringUtil.isEmpty(offerInstAttrMap.get("statusDate"))) {
                        offerInstAttrMap.put("executetime", d.format(new Date()));
                    } else {
                        offerInstAttrMap.put("executetime", d.format(df
                                .parse(offerInstAttrMap.get("statusDate").toString())));
                    }
                    String operType = offerInstAttrMap.get("operType")
                            .toString();
                    offerInstAttrMap.put("routeId", acctId);
                    String attrId = String.valueOf(offerInstAttrMap.get("attrId"));
                    String offerId = String.valueOf(itemMap.get("offerId"));
                    String allowFlag = "Y";
                    String attrIdBill = "";
                    String attrValueBill = "";
                    List<Map<String,Object>> tifParamIdList = ordBillDao.selectTifParamId(Long.parseLong(offerId), attrId);
                    if (tifParamIdList.size() > 1 ) {
                        msg.setResultCode(ResultCode.OFFERATTR_ERROR_003);
                        msg.setMessage("取对应的套餐参数编码时出错【offerId】：" + offerId + ",【attrId】：" + attrId);
                        return -1;
                    } else if (tifParamIdList.size() == 0) {
                        long cnt = billMapperDao.getofferAttrIdCnt(Long.parseLong(offerId), Long.parseLong(attrId));
                        if (cnt == 0) {
                            //continue;
                            msg.setResultCode(ResultCode.OFFERINS_I_ERROR_018);
                            msg.setMessage("取对应的套餐参数编码时出错【offerId】：" + offerId + ",【attrId】：" + attrId);
                            return -1;
                        } else {
                            attrIdBill = attrId;
                           /* msg.setResultCode(ResultCode.OFFERATTR_ERROR_004);
                            msg.setMessage("取对应的套餐参数编码时出错【offerId】：" + offerId + ",【attrId】：" + attrId);
                            return -1;*/
                        }

                    } else {
                        Map tifParamMap = tifParamIdList.get(0);
                        allowFlag = String.valueOf(tifParamMap.get("allowFlag"));
                        attrIdBill = String.valueOf(tifParamMap.get("disctParamIdBill"));
                        attrValueBill = "";
                        //166参数转化
                        if ("Y".equals(tifParamMap.get("isRent"))) {
                            String attrValue = String.valueOf(offerInstAttrMap.get("attrValue"));
                            double dattrValue = Double.parseDouble(attrValue) * 10;
                            attrValueBill = df1.format(dattrValue);
                            offerInstAttrMap.put("attrValue", attrValueBill);
                        }

                    }
                    if (operType.equals("1000")&& "Y".equals(allowFlag)) {
                        // 判断是否存在
                        offerInstAttrMap.put("routeId", acctId);
                        offerInstAttrMap.put("statusCd", 1000);
                        offerInstAttrMap.put("attrId", attrIdBill);
                        offerinstDao.insertOfferInstAttr(offerInstAttrMap);
                        offerInstAttrMap.put("action", 1);
                        SynMapContextHolder.addMap("offerInstAttrobjList1", offerInstAttrMap);
                    } else if (operType.equals("1100")&& "Y".equals(allowFlag)) {
                        // 0414 route_id
                        List<Map<String, Object>> oldOfferInstAttrList1 = new ArrayList<Map<String, Object>>();
                        oldOfferInstAttrList1 = offerinstDao.getOfferInstAttrId(offerInstAttrMap);
                        if (oldOfferInstAttrList1.size() == 0) {
                            offerInstAttrMap.put("attrId", attrIdBill);
                            oldOfferInstAttrList1 = offerinstDao.getOfferInstAttrIdFromAttrId(offerInstAttrMap);
                            if (oldOfferInstAttrList1.size() == 0) {
                                // TODO: 2019/8/5 先验证数据的准确性，正式上线就不要这个校验了
                                /*msg.setResultCode(ResultCode.OFFERATTR_ERROR_001);
                                msg.setMessage("没有找到符合条件的销售品参数【offertInstId】：" + offerInstAttrMap.get("offerInstId"));
                                return -1;*/
                                logger.error("没有找到符合条件的销售品参数【offertInstId】：" + offerInstAttrMap.get("offerInstId"));
                                return 1;

                            }
                        }
                        for (Map<String, Object> attrMap : oldOfferInstAttrList1) {
                            attrMap.put("expDate", offerInstAttrMap.get("expDate"));
                            attrMap.put("statusCd", 1100);
                            attrMap.put("routeId", acctId);
                            offerinstDao.updateOfferInstAttr(attrMap);
                            attrMap.put("action", 2);
                            attrMap.put("executetime", offerInstAttrMap.get("executetime"));
                            SynMapContextHolder.addMap("offerInstAttrobjList1", attrMap);

                        }

                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "修改商品实例属性出错");
            msg.setResultCode(ResultCode.OFFERATTR_ERROR_002);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    public int deleteOfferInstAtrr(Map itemMap, Map userMap, Message msg,
                                   long acctId,String expNextFlag) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            //营业退订销售品的时候不会送属性值，所以通过销售品实例Id来退订
            List<Map<String, Object>> offerInstAttrList = ordBillDao
                    .selectOrdOfferInstAttr(itemMap);
            if (offerInstAttrList.size() > 0) {
                for (int i = 0; i < offerInstAttrList.size(); i++) {
                    Map offerInstAttrMap = new HashMap();
                    offerInstAttrMap = offerInstAttrList.get(i);
                    if (StringUtil.isEmpty(offerInstAttrMap.get("statusDate"))) {
                        offerInstAttrMap.put("executetime", d.format(new Date()));
                    } else {
                        offerInstAttrMap.put("executetime", d.format(df
                                .parse(offerInstAttrMap.get("statusDate").toString())));
                    }

                    String operType = offerInstAttrMap.get("operType")
                            .toString();
                    if ("1".equals(expNextFlag)) {
                        offerInstAttrMap.put("expDate", itemMap.get("expDate"));
                    }
                    if (operType.equals("1100")) {
                        // 0414 route_id
                        Long ROUTE_ID = 0L;
                        List<Map<String, Object>> oldOfferInstAttrList1 = offerinstDao
                                .getOfferInstAttrId(offerInstAttrMap);
                        if (oldOfferInstAttrList1.size() > 0) {
                            for (Map<String, Object> attrMap : oldOfferInstAttrList1) {
                                ROUTE_ID = Long.parseLong(attrMap.get("route_id")
                                        .toString());
                                offerInstAttrMap.put("routeId", ROUTE_ID);
                                offerinstDao.updateOfferInstAttr(offerInstAttrMap);
                                offerInstAttrMap.put("action", 2);
                                SynMapContextHolder.addMap("offerInstAttrobjList1", offerInstAttrMap);
                            }
                        }//当主键不一致时通过attrId、attrValue来定位主键
                        else if (oldOfferInstAttrList1.size() == 0) {
                            oldOfferInstAttrList1 = offerinstDao.getOfferInstAttrIdFromAttrId(offerInstAttrMap);
                            if (oldOfferInstAttrList1.size() == 0) {
                                msg.setResultCode(ResultCode.OFFERINS_D_ERROR_012);
                                msg.setMessage("没有找到符合条件的销售品参数，请检查【offerInstId】：" + offerInstAttrMap.get("offerInstId"));
                                return -1;
                            }
                            for (Map<String, Object> attrMap : oldOfferInstAttrList1) {
                                attrMap.put("expDate", offerInstAttrMap.get("expDate"));
                                attrMap.put("statusCd", offerInstAttrMap.get("statusCd"));
                                offerinstDao.updateOfferInstAttr(attrMap);
                                attrMap.put("executetime", offerInstAttrMap.get("executetime"));
                                attrMap.put("action", 2);
                                SynMapContextHolder.addMap("offerInstAttrobjList1", attrMap);
                            }
                        }

                    }


                }
            } else {
                if (itemMap.get("offerInstId") != null
                        && !"".equals(itemMap.get("offerInstId"))) {
                    long offerInstId = Long.parseLong(itemMap.get("offerInstId").toString());
                    List<Map<String, Object>> oldOfferInstAttrList = offerinstDao.selectOfferInstAttrIdFromInstId(offerInstId, acctId, itemMap.get("expDate").toString());
                    for (Map<String, Object> oldOfferInstAttrMap : oldOfferInstAttrList) {
                        oldOfferInstAttrMap.put("expDate", itemMap.get("expDate"));
                        oldOfferInstAttrMap.put("statusCd", 1100);
                        offerinstDao.updateOfferInstAttr(oldOfferInstAttrMap);
                        oldOfferInstAttrMap.put("action", 2);
                        SynMapContextHolder.addMap("offerInstAttrobjList1", oldOfferInstAttrMap);
                    }
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理账户ORD_OFFER_INST_attr失败");
            msg.setResultCode(ResultCode.OFFERINS_D_ERROR_013);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    // 添加销售品
    public int insertOfferInst(Map itemMap, Map userMap, Message msg,
                               long acctId) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat dExp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat dfhhmmss = new SimpleDateFormat("HHmmss");

            Map map = itemMap;
            List<Map<String, Object>> ordOfferList = ordBillDao.selectOrdOfferInstFromRowId(map);
            if (ordOfferList.size() != 1) {
                msg.setResultCode(ResultCode.OFFERINS_I_ERROR_001);
                msg.setMessage("订购取销售品实例接口表出错");
                return -1;
            }
            Map ordOfferInstMap = ordOfferList.get(0);
            // TODO: 2019/6/18 先屏蔽调计费没有的套餐，后续记得修改
            long offerId = Long.parseLong(ordOfferInstMap.get("offerId").toString());
            /*List<Map<String, Object>> offerList = billMapperDao.selectOfferId(offerId);
            if (offerList.size() == 0) {
                return 1;
            }*/
            String strExpDate = ordOfferInstMap.get("expDate").toString();
            String strEffDate = ordOfferInstMap.get("effDate").toString();
            ordOfferInstMap.put("routeId", acctId);
            ordOfferInstMap.put("regionId", map.get("regionId"));
            ordOfferInstMap.put("lanId", map.get("lanId"));
            if (strEffDate.equals(strExpDate)) {
                return 1;
            }
            if (strExpDate.compareTo(strEffDate) < 0) {
                logger.error("新建套餐的失效时间小于生效时间【offerInstId】：" + ordOfferInstMap.get("offerInstId"));
                /*msg.setResultCode(ResultCode.OFFERINS_I_ERROR_002);
                msg.setMessage("新建套餐的失效时间必须大于生效时间");*/
                return 1;
            }
            // 处理时间问题 23:59:59 改为第二天
            int index = strExpDate.indexOf(" ");
            String strTmp = strExpDate.substring(index + 1);

            doOfferExpDate(dExp, ordOfferInstMap, strExpDate, strTmp);
            if (StringUtil.isEmpty(ordOfferInstMap.get("statusDate"))) {
                ordOfferInstMap.put("executetime", d.format(new Date()));
            } else {
                ordOfferInstMap.put("executetime", d.format(df
                        .parse(ordOfferInstMap.get("statusDate").toString())));
            }

            String operType = ordOfferInstMap.get("operType").toString();
            Long route_id = 0L;
            //判断标资是否存在
            try {
                if (checkStandardOffer(userMap, msg, acctId, map, String.valueOf(offerId))) {
                    return 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "判断用户是否存在标准资费出错");
                msg.setResultCode(ResultCode.OFFERINS_I_ERROR_003);
                msg.setMessage(exceptionMsg);
                return -1;

            }
            // 判断是否存在prod_inst,存在不处理
            List<Map<String, Object>> oldOfferInstList = offerinstDao
                    .getOfferInstId(ordOfferInstMap);
            if (oldOfferInstList.size() > 0) {
                // TODO: 2019/7/30 ivpn壳子有时候比成员变更下来的慢，此刻计费已经补订该套餐了，所以增加了这个判断
                long cntOrdBillObj = ordBillDao.getCntOrdBillObj(ordOfferInstMap);
                if (cntOrdBillObj == 1) {
                    return 1;
                } else {
                    msg.setResultCode(ResultCode.OFFERINS_I_ERROR_004);
                    msg.setMessage("销售品实例数据已存在，不允许重复添加【offerInstId】：" + ordOfferInstMap.get("offerInstId"));
                    return -1;
                }

            }
            List<Map<String, Object>> tifContrastofferList = ordBillDao.selectTifDisctIdContrast(offerId);
            if (tifContrastofferList.size() > 0) {
                Map tifContrastMap = tifContrastofferList.get(0);
                ordOfferInstMap.put("offerId", tifContrastMap.get("DISCT_ID_BILL"));
                logger.info("计费转化crm的套餐落地【offerId】" + offerId + "，【offerInstId】：" + ordOfferInstMap.get("offerInstId"));

            }
            ordOfferInstMap.put("statusCd", 1000);
            ordOfferInstMap.put("routeId", acctId);
            offerinstDao.insertOfferInst(ordOfferInstMap);
            ordOfferInstMap.put("action", 1);

            SynMapContextHolder.addMap("offerInstobjList1", ordOfferInstMap);
            //增加商品实例成员
            if (insertOfferProdInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
                return -1;
            }
            //增加商品角色
            if (insertOfferObjInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
                return -1;
            }
            //增加参数
            if (insertOfferInstAtrr(ordOfferInstMap, userMap, msg, acctId) < 0) {
                return -1;
            }
            //翼支付增加特殊属性
            List<Map<String,Object>> pOfferPayplanInfoList= cpcMapperDao.selectPOfferPayplanInfo(ordOfferInstMap);
            if (pOfferPayplanInfoList.size() > 0) {
                long archGrpId = Long.parseLong(ordOfferInstMap.get("ARCH_GRP_ID").toString());
                long orderItemId = Long.parseLong(ordOfferInstMap.get("ORDER_ITEM_ID").toString());
                long offerInstId = Long.parseLong(ordOfferInstMap.get("offerInstId").toString());
                //翼支付红包
                List<Map<String, Object>> crmRentListHb =  ordBillDao.selectCrmRent(archGrpId, orderItemId, offerInstId, 1);
                if (crmRentListHb.size() > 0) {
                    List<Map<String, Object>> payFlagList = ordBillDao.selectTifPayPlanContrast(1);
                    for (Map<String, Object> payFlagMap : payFlagList) {
                        Map attrMap = new HashMap();
                        long offerInstAttrId = prodinstDao.getSeq("SEQ_OFFER_INST_ATTR_ID");
                        attrMap.put("offerInstAttrId", offerInstAttrId);
                        attrMap.put("offerInstId", offerInstId);
                        attrMap.put("routeId", acctId);
                        attrMap.put("effDate", ordOfferInstMap.get("effDate"));
                        attrMap.put("expDate", ordOfferInstMap.get("expDate"));
                        attrMap.put("statusCd", 1000);
                        attrMap.put("remark", "翼支付红包计费单独订购属性");
                        attrMap.put("executetime", ordOfferInstMap.get("executetime"));
                        attrMap.put("action", 1);
                        attrMap.put("attrId", payFlagMap.get("attrId"));
                        attrMap.put("attrValue",payFlagMap.get("attrValue"));
                        offerinstDao.insertOfferInstAttr(attrMap);
                        SynMapContextHolder.addMap("offerInstAttrobjList1", attrMap);
                    }
                    return 1;
                }
                //翼支付红包金
                List<Map<String, Object>>  crmRentListHbj =  ordBillDao.selectCrmRent(archGrpId, orderItemId, offerInstId, 2);
                if (crmRentListHbj.size() > 0) {
                    List<Map<String, Object>> payFlagList = ordBillDao.selectTifPayPlanContrast(2);
                    for (Map<String, Object> payFlagMap : payFlagList) {
                        Map attrMap = new HashMap();
                        long offerInstAttrId = prodinstDao.getSeq("SEQ_OFFER_INST_ATTR_ID");
                        attrMap.put("offerInstAttrId", offerInstAttrId);
                        attrMap.put("offerInstId", offerInstId);
                        attrMap.put("routeId", acctId);
                        attrMap.put("effDate", ordOfferInstMap.get("effDate"));
                        attrMap.put("expDate", ordOfferInstMap.get("expDate"));
                        attrMap.put("statusCd", 1000);
                        attrMap.put("remark", "翼支付红包金计费单独订购属性");
                        attrMap.put("executetime", ordOfferInstMap.get("executetime"));
                        attrMap.put("action", 1);
                        attrMap.put("attrId", payFlagMap.get("attrId"));
                        attrMap.put("attrValue",payFlagMap.get("attrValue"));
                        offerinstDao.insertOfferInstAttr(attrMap);
                        SynMapContextHolder.addMap("offerInstAttrobjList1", attrMap);

                    }
                    return 1;
                }


            }
        } catch (Exception e) {
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理账户ORD_OFFER_INST失败");
            msg.setResultCode(ResultCode.OFFERINS_I_ERROR_016);
            msg.setMessage(exceptionMsg);
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
/**
 * @Author WangBaoQiang
 * @Description //处理时间问题
 * @Date 22:29 2019/9/30
 * @Param [dExp, ordOfferInstMap, strExpDate, strTmp]
 * @return void
*/
    private void doOfferExpDate(SimpleDateFormat dExp, Map ordOfferInstMap, String strExpDate, String strTmp) throws ParseException {
        if ("23:59:59".equals(strTmp)) {
            Date expDate = dExp.parse(strExpDate);
            Calendar c = Calendar.getInstance();
            c.setTime(expDate);
            c.add(Calendar.DAY_OF_MONTH, 1);
            expDate = c.getTime();
            strExpDate = dExp.format(expDate);
            ordOfferInstMap.put("expDate", strExpDate);
        }
    }

    //增加修改商品逻辑
    public int doUpdateOfferInst(Map itemMap, Map userMap, Message msg,
                               long acctId) throws Exception {

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat dfhhmmss = new SimpleDateFormat("HHmmss");

            Map map = itemMap;
            List<Map<String, Object>> ordOfferList = ordBillDao
                    .selectOrdOfferInstFromRowId(map);
            if (ordOfferList.size() != 1) {
                msg.setResultCode(ResultCode.OFFERINS_U_ERROR_001);
                msg.setMessage("取订购取销售品实例接口表出错");
                return -1;
            }
            Map ordOfferInstMap = ordOfferList.get(0);
            //屏蔽掉计费不需要的套餐
			/*long offerId = Long.parseLong(ordOfferInstMap.get("offerId").toString());
			List<Map<String, Object>> offerList = billMapperDao.selectOfferId(offerId);
			if(offerList.size() == 0){
				return  1;
			}*/
            String strExpDate = ordOfferInstMap.get("expDate").toString();
            String strEffDate = ordOfferInstMap.get("effDate").toString();
            ordOfferInstMap.put("routeId", acctId);
            if (strEffDate.equals(strExpDate)) {
                return 1;
            }
            // 处理时间问题 23:59:59 改为第二天
            int index = strExpDate.indexOf(" ");
            String strTmp = strExpDate.substring(index + 1);
            doOfferExpDate(d, ordOfferInstMap, strExpDate, strTmp);

            ordOfferInstMap.put("executetime", d.format(df
                    .parse(ordOfferInstMap.get("statusDate").toString())));
            String operType = ordOfferInstMap.get("operType").toString();
            Long route_id = 0L;
            // 判断是否存在prod_inst,存在不处理
            List<Map<String, Object>> oldOfferInstList = offerinstDao
                    .getOfferInstId(ordOfferInstMap);
            if (oldOfferInstList.size() == 0) {
                ordOfferInstMap.put("statusCd", 1000);
                offerinstDao.insertOfferInst(ordOfferInstMap);
                ordOfferInstMap.put("action", 1);
                SynMapContextHolder.addMap("offerInstobjList1", ordOfferInstMap);

            } else {
                Date expDate=(Date)ordOfferInstMap.get("expDate");
                if (expDate.compareTo(new Date()) < 0) {
                    ordOfferInstMap.put("statusCd", 1100);
                }else {
                    ordOfferInstMap.put("statusCd", 1000);
                }
                offerinstDao.updateOfferInstForUpdateDate(ordOfferInstMap);
                ordOfferInstMap.put("action", 2);
                SynMapContextHolder.addMap("offerInstobjList1", ordOfferInstMap);

            }
            // 修改 ord_offer_prod_inst
            if (updateOfferProdInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
                return -1;
            }
            // 修改 ord_offer_object_rel
            if (updateOfferObjInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
                return -1;
            }
            // 修改ord_offer_inst_attr
            if (updateOfferInstAtrr(ordOfferInstMap, userMap, msg, acctId) < 0) {
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理ord_offer_inst失败");
            msg.setResultCode(ResultCode.OFFERINS_U_ERROR_006);
            msg.setMessage(exceptionMsg);
            return -1;
        }

        return 1;
    }

    public int deleteOfferInst(Map itemMap, Map userMap, Message msg,
                               long acctId) throws Exception {

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat dExp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            Map map = itemMap;
            List<Map<String, Object>> ordOfferList = ordBillDao
                    .selectOrdOfferInstFromRowId(map);
            if (ordOfferList.size() != 1) {
                msg.setResultCode(ResultCode.OFFERINS_D_ERROR_001);
                msg.setMessage("订购取销售品实例接口表出错");
                return -1;
            }
            Map ordOfferInstMap = ordOfferList.get(0);
            //屏蔽掉计费不需要的套餐
			long offerId = Long.parseLong(ordOfferInstMap.get("offerId").toString());
			/*List<Map<String, Object>> offerList = billMapperDao.selectOfferId(offerId);
			if(offerList.size() == 0){
				return  1;
			}*/
            String strExpDate = ordOfferInstMap.get("expDate").toString();
            String strEffDate = ordOfferInstMap.get("effDate").toString();
            try {
                //查询标资是否已经存在
                if (checkStandardOffer(userMap, msg, acctId, map, String.valueOf(offerId))) {
                    return 1;
                }
            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "判断用户是否存在标准资费出错");
                msg.setResultCode(ResultCode.OFFERINS_D_ERROR_002);
                msg.setMessage(exceptionMsg);
                return -1;

            }
			/*if (strEffDate.equals(strExpDate)) {
				return 1;
			}
			if (strExpDate.compareTo(strEffDate) < 0) {
				msg.setMessage("新建套餐的失效时间必须大于生效时间");
				return -1;
			}*/
            // 处理时间问题 23:59:59 改为第二天
            int index = strExpDate.indexOf(" ");
            String strTmp = strExpDate.substring(index + 1);
            doOfferExpDate(dExp, ordOfferInstMap, strExpDate, strTmp);
            String expNextFlag = "";
            //判断是否为优惠销售品,如果是当月失效，就延期到下个月
            long cntOffer = billMapperDao.dealExpDateOffer(offerId);
            //勇哥的语句有遗漏的，另外加个接口表
            if (cntOffer == 0) {
                cntOffer = ordBillDao.selectTifOfferExpDateExtend(offerId);
            }
            if(cntOffer > 0){
                //取当月第一天
                Date firstDate = DateUtils.getMonthFirstDay(0, 0);
                //获取下月的第一天
                Date lastDate =  DateUtils.getNextMonthFirstDay(new Date());
                boolean flag =  isEffectiveDate(df.parse(String.valueOf(ordOfferInstMap.get("expDate"))), firstDate, lastDate);
                Date expDate = df.parse(String.valueOf(ordOfferInstMap.get("expDate")));
                if (expDate.after(firstDate) && flag) {
                    ordOfferInstMap.put("expDate", lastDate);
                    expNextFlag = "1";
                }

            }
            if (StringUtil.isEmpty(ordOfferInstMap.get("statusDate"))) {
                ordOfferInstMap.put("executetime", d.format(new Date()));
            } else {
                ordOfferInstMap.put("executetime", d.format(df
                        .parse(ordOfferInstMap.get("statusDate").toString())));
            }
            ordOfferInstMap.put("routeId", acctId);
            String operType = ordOfferInstMap.get("operType").toString();
            // 判断是否存在prod_inst,存在不处理
            List<Map<String, Object>> oldOfferInstList = offerinstDao
                    .getOfferInstId(ordOfferInstMap);
			/*if (oldOfferInstList.size() == 0) {
				msg.setMessage("退订套餐获取原有订购实例ID出错【offerInstId】：" +  ordOfferInstMap.get("offerInstId"));
				return -1;
			} else {*/
            if (oldOfferInstList.size() > 0) {
                //Map oldOfferMap = oldOfferInstList.get(0);
                ordOfferInstMap.put("statusCd", 1100);
                offerinstDao.updateOfferInst(ordOfferInstMap);
                ordOfferInstMap.put("action", 2);
                SynMapContextHolder.addMap("offerInstobjList1", ordOfferInstMap);
            }
            List<Map<String, Object>> tifContrastofferList = ordBillDao.selectTifDisctIdContrast(offerId);
            if (tifContrastofferList.size() > 0) {
                Map tifContrastMap = tifContrastofferList.get(0);
                ordOfferInstMap.put("offerId", tifContrastMap.get("DISCT_ID_BILL"));
                logger.info("计费转化crm的套餐落地【offerId】" + offerId + "，【offerInstId】：" + ordOfferInstMap.get("offerInstId"));

            }
            // 修改 ord_offer_prod_inst
            if (deleteOfferProdInst(ordOfferInstMap, userMap, msg, acctId,expNextFlag) < 0) {
                return -1;
            }
            // 修改 ord_offer_object_rel
            if (deleteOfferObjInst(ordOfferInstMap, userMap, msg, acctId,expNextFlag) < 0) {
                return -1;
            }
            // 修改ord_offer_inst_attr
            if (deleteOfferInstAtrr(ordOfferInstMap, userMap, msg, acctId,expNextFlag) < 0) {
                return -1;
            }
            //判断如果crm没有送成员，则将所有成员的失效时间update成当前失效时间
            List<Map<String, Object>> offerProdInstList = ordBillDao
                    .selectOrdOfferProdInstRel(itemMap);
            List<Map<String, Object>> offerObjInstList = ordBillDao
                    .selectOrdOfferObjInstRel(itemMap);
            if (offerProdInstList.size() == 0 && offerObjInstList.size() == 0) {
                Map objMap = new HashMap();
                long offerInstId =  Long.parseLong(itemMap.get("offerInstId").toString());
                objMap.put("offerInstId", offerInstId);
                objMap.put("expDate", new Date());
                objMap.put("routeId", acctId);
                List<Map<String,Object>> objList =  offerinstDao.selectOfferObjfromInstId(objMap);
                for (Map<String, Object> objectMap : objList) {
                    objectMap.put("statusCd", 1100);
                    objectMap.put("expDate", objectMap.get("effDate"));
                    objectMap.put("action", 2);
                    objectMap.put("executetime", d.format(new Date()) );
                    objectMap.put("remark", "crm没有送成员,计费自己退订");
                    offerinstDao.updateOfferObjInstRel(objectMap);
                    SynMapContextHolder.addMap("offerObjInstobjList1", objectMap);
                }
            }
            //}

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理商品退订失败");
            msg.setResultCode(ResultCode.OFFERINS_D_ERROR_014);
            msg.setMessage(exceptionMsg);
            return -1;
        }

        return 1;
    }
/**
 * @Author WangBaoQiang
 * @Description //查询标资是否存在
 * @Date 22:24 2019/9/30
 * @Param [userMap, msg, acctId, map, offerId]
 * @return boolean
*/
    private boolean checkStandardOffer(Map userMap, Message msg, long acctId, Map map, String offerId) {
        if ("61".equals(offerId.substring(0, 2)) && offerId.length() == 6) {
            List<Map<String, Object>> ordOfferProdInstRel = ordBillDao.selectOrdOfferProdInstRel(map);
            Map ordOfferProdInstMap = ordOfferProdInstRel.get(0);
            map.put("objId", ordOfferProdInstMap.get("prodInstId").toString());
            map.put("routeId", acctId);
            if (checkOfferExist(userMap, map, msg, acctId, offerId) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param itemMap
     * @param userMap
     * @param msg
     * @param acctId
     * @return
     * @throws Exception
     */
    public int doInsertPayToPlan(Map itemMap, Map userMap, Message msg,
                                 long acctId, long serviceOfferId) throws Exception {

        try {
            //修改参数的不处理
            if (String.valueOf(serviceOfferId).equals("3020501002")) {
                return 1;
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat dfhhmmss = new SimpleDateFormat("HHmmss");
            Date date = new Date();
            Map map = itemMap;
            List<Map<String, Object>> ordOfferList = ordBillDao.selectOrdOfferInstFromRowId(map);
            if (ordOfferList.size() != 1) {
                msg.setMessage("订购取销售品实例接口表出错");
                return -1;
            }
            Map ordOfferInstMap = ordOfferList.get(0);
            long archGrpId = Long.parseLong(ordOfferInstMap.get("ARCH_GRP_ID").toString());
            long orderItemId = Long.parseLong(ordOfferInstMap.get("ORDER_ITEM_ID").toString());
            long offerInstId = Long.parseLong(ordOfferInstMap.get("offerInstId").toString());
            String operType = ordOfferInstMap.get("operType").toString();
            //取crmRent表
            List<Map<String, Object>> ordCrmRentList = ordBillDao.selectCrmRent(archGrpId, orderItemId, offerInstId,1);
            for (Map<String, Object> ordCrmRentMap : ordCrmRentList) {
                String state = ordCrmRentMap.get("state").toString();
                Long offerId = Long.parseLong(ordCrmRentMap.get("offerId").toString());
                String returnType = "";
                if (ordCrmRentMap.get("returnType") != null
                        && !"".equals(ordCrmRentMap.get("returnType"))) {
                    returnType = ordCrmRentMap.get("returnType").toString();
                }

                String returnRuleId = ordCrmRentMap.get("returnRuleId").toString();
                Map servAcctMap = new HashMap();
                if ("1000".equals(operType)
                        && "1".equals(state)) {
                    List<Map<String, Object>> oneItemResultList = ordBillDao.selectOneItemResult(archGrpId, orderItemId);
                    if (oneItemResultList.size() == 0) {
                        msg.setMessage("onItemResult表为空");
                        return -1;
                    }
                    for (Map<String, Object> oneItemResultMap : oneItemResultList) {
                        String prodInstId = "";
                        if (oneItemResultMap.get("prodInstId") != null
                                && !"".equals(oneItemResultMap.get("prodInstId"))) {
                            prodInstId = oneItemResultMap.get("prodInstId").toString();
                        }
                        acctId = Long.parseLong(oneItemResultMap.get("ACCT_ID").toString());
                        //取该用户下的acctId
                        if (!"".equals(prodInstId)) {
                            servAcctMap.put("prodInstId", prodInstId);
                            long routeId = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, Long.parseLong(prodInstId), msg);
                            if (routeId < 0) {
                                msg.setMessage("预存活动取用户routeId失败");
                                return -1;
                            }
                            servAcctMap.put("routeId", routeId);
                            List<Map<String, Object>> servAcctList = prodinstDao
                                    .getProdInstAcctIdForNull(servAcctMap);

                            if (servAcctList.size() > 0) {
                                Map acctMap = servAcctList.get(0);
                                acctId = Long.parseLong(acctMap.get("acctId").toString());
                            }
                        }
                        String acctItemTypeId = oneItemResultMap.get("ACCT_ITEM_TYPE_ID").toString();
                        Map oneItem = new HashMap();
                        long interPlanId = prodinstDao.getSeq("SEQ_INTER_PLAN_ID");
                        long depositType = 3;
                        if ("1".equals(returnType)) {
                            //翼支付红包
                            depositType = 1;
                        } else if ("2".equals(returnType)) {
                            //翼支付红包金
                            depositType = 2;
                        } else if ("".equals(returnType)
                                && "21101".equals(acctItemTypeId)) {
                            //活动预存款
                            depositType = 3;
                        } else if ("".equals(returnType)
                                && "10014".equals(acctItemTypeId)) {
                            //活动赠款
                            depositType = 4;
                        }
                        oneItem.put("interPlanId", interPlanId);// zhujian
                        oneItem.put("offerInstId", oneItemResultMap.get("OFFER_INST_ID"));
                        oneItem.put("offerId", oneItemResultMap.get("OFFER_ID"));
                        oneItem.put("objectType", 2);
                        oneItem.put("objectId", oneItemResultMap.get("PROD_INST_ID"));
                        oneItem.put("acctId", acctId);
                        oneItem.put("operType", 1);
                        oneItem.put("operState", 0);
                        oneItem.put("effDate", oneItemResultMap.get("CREATE_DATE"));
                        oneItem.put("orderDate", oneItemResultMap.get("CREATE_DATE"));
                        oneItem.put("createDate", df.format(date));
                        oneItem.put("operDate", df.format(date));
                        oneItem.put("amount", oneItemResultMap.get("PAID_IN_AMOUNT"));
                        oneItem.put("depositType", depositType);
                        try {
                            prodinstDao.insertPayToPlan(oneItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                            msg.setMessage("插入inter_pay_plan表失败");
                            return -1;
                        }
                    }
                } else if ("1100".equals(operType) &&
                        ("2".equals(state) || "3".equals(state))) {
                    Map oneItem = new HashMap();
                    long interPlanId = prodinstDao.getSeq("SEQ_INTER_PLAN_ID");
                    oneItem.put("interPlanId", interPlanId);
                    oneItem.put("offerInstId", ordCrmRentMap.get("offerInstId"));
                    oneItem.put("offerId", ordCrmRentMap.get("offerId"));
                    oneItem.put("objectType", 2);
                    oneItem.put("objectId", ordCrmRentMap.get("prodInstId"));
                    oneItem.put("acctId", acctId);
                    oneItem.put("operType", 3);
                    oneItem.put("operState", 0);
                    oneItem.put("createDate", df.format(date));
                    oneItem.put("operDate", df.format(date));
                    try {
                        prodinstDao.insertPayToPlan(oneItem);
                    } catch (Exception e) {
                        e.printStackTrace();
                        msg.setMessage("退订活动，插入inter_pay_plan表失败");
                        return -1;
                    }
                    /*List<Map<String, Object>> payToPlanList = prodinstDao.selectPayToPlan(offerInstId, offerId);
                    for (Map<String, Object> interToPlanMap : payToPlanList) {
                        interToPlanMap.put("operType", 3);
                        long interPlanId = prodinstDao.getSeq("SEQ_INTER_PLAN_ID");
                        interToPlanMap.put("interPlanId", interPlanId);
                        try {
                            prodinstDao.insertPayToPlan(interToPlanMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                            msg.setMessage("退订活动，插入inter_pay_plan表失败");
                            return -1;
                        }
                    }*/

                }


            }

        } catch (Exception e) {
            msg.setMessage("处理账户活动预存失败");
            e.printStackTrace();
            throw e;
        }

        return 1;
    }
    // 修改用户级销售时间
    public int updateOfferProdInst(Map itemMap, Map userMap, Message msg,
                                   long acctId) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        Map map = itemMap;
        try {
            long offerId = Long.parseLong(itemMap.get("offerId").toString());
            List<Map<String, Object>> offerProdInstList = ordBillDao
                    .selectOrdOfferProdInstRel(itemMap);
            if (offerProdInstList.size() > 0) {
                for (int i = 0; i < offerProdInstList.size(); i++) {
                    Map offerProdInstMap = new HashMap();
                    offerProdInstMap = offerProdInstList.get(i);
                    String roleId = offerProdInstMap.get("roleId").toString();
                    //虚拟销售品不落计费
                    if ("90000".equals(roleId)
                            || "99999".equals(roleId)) {
                        continue;
                    }
                    if (StringUtil.isEmpty(offerProdInstMap.get("statusDate"))) {
                        offerProdInstMap.put("executetime", d.format(new Date()));
                    } else {
                        offerProdInstMap.put("executetime", d.format(df
                                .parse(offerProdInstMap.get("statusDate")
                                        .toString())));
                    }

                    String operType = offerProdInstMap.get("operType")
                            .toString();
                    offerProdInstMap.put("routeId", acctId);

                    if (operType.equals("1000")) {
                        // 取历史旧的route_id 0414
                        Long ROUTE_ID = 0L;
                        offerProdInstMap.put("offerObjInstRelId",
                                offerProdInstMap.get("offerProdInstRelId"));
                        List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
                                .getOfferObjInstId(offerProdInstMap);
                        if (oldOfferProdInstList1.size() == 0) {
                            msg.setResultCode(ResultCode.OFFERINS_U_ERROR_002);
                            msg.setMessage("没有找到可以修改的商品实例数据【offerInstId】：" + offerProdInstMap.get("offerInstId"));
                            return -1;
                        }
                        offerProdInstMap.put("offerObjInstRelId",
                                offerProdInstMap.get("offerProdInstRelId"));// 主键
                        Date expDate=(Date)offerProdInstMap.get("expDate");
                        if (expDate.compareTo(new Date()) < 0) {
                            offerProdInstMap.put("statusCd", 1100);
                        }else {
                            offerProdInstMap.put("statusCd", 1000);
                        }
                        offerinstDao.updateOfferProdForUpdateDate(offerProdInstMap);
                        offerProdInstMap.put("action", 2);
                        offerProdInstMap.put("objType", 100000);
                        offerProdInstMap.put("objId",
                                Long.parseLong(offerProdInstMap.get(
                                        "prodInstId").toString()));
                        SynMapContextHolder.addMap("offerObjInstobjList1", offerProdInstMap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理ORD_OFFER_PROD_INST失败");
            msg.setResultCode(ResultCode.OFFERINS_U_ERROR_003);
            msg.setMessage(exceptionMsg);
            return -1;
        }

        return 1;
    }
    //修改账户级销售品时间
    public int updateOfferObjInst(Map itemMap, Map userMap, Message msg,
                                   long acctId) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
            Map map = itemMap;
            List<Map<String, Object>> offerObjInstList = ordBillDao
                    .selectOrdOfferObjInstRel(itemMap);
            long offerId = Long.parseLong(itemMap.get("offerId").toString());
            if (offerObjInstList.size() > 0) {
                for (int i = 0; i < offerObjInstList.size(); i++) {
                    Map offerProdInstMap = offerObjInstList.get(i);
                    String roleId = offerProdInstMap.get("roleId").toString();
                    long offerDetailId;
                    //虚拟销售品不落计费
                    if ("90000".equals(roleId)
                            || "99999".equals(roleId)) {
                        continue;
                    }

                    String operType = offerProdInstMap.get("operType")
                            .toString();
                    if (operType.equals("1000")) {
                        // add by 0414 取route_id

                        offerProdInstMap.put("routeId", acctId);
                        List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
                                .getOfferObjInstId(offerProdInstMap);
                        Map offerObjRelMap = new HashMap();
                        offerObjRelMap.put("routeId", acctId);
                        offerObjRelMap = oldOfferProdInstList1.get(0);
                        offerObjRelMap.put("action", 2);
                        offerObjRelMap.put("expDate", offerProdInstMap.get("expDate"));
                        offerObjRelMap.put("effDate", offerProdInstMap.get("effDate"));
                        Date expDate=(Date)offerProdInstMap.get("expDate");
                        if (expDate.compareTo(new Date()) < 0) {
                            offerObjRelMap.put("statusCd", 1100);
                        }else {
                            offerObjRelMap.put("statusCd", 1000);
                        }
                        offerObjRelMap.put("statusDate", offerProdInstMap.get("statusDate"));
                        offerinstDao.updateOfferProdForUpdateDate(offerObjRelMap);
                        if (StringUtil.isEmpty(offerProdInstMap.get("statusDate"))) {
                            offerObjRelMap.put("executetime", d.format(new Date()));
                        } else {
                            offerObjRelMap.put("executetime", d.format(df
                                    .parse(offerProdInstMap.get("statusDate").toString())));
                        }


                        SynMapContextHolder.addMap("offerObjInstobjList1", offerObjRelMap);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理ORD_OFFER_OBJ_INST_REL失败");
            msg.setResultCode(ResultCode.OFFERINS_U_ERROR_004);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }
    //修改商品实例属性时间
    public int updateOfferInstAtrr(Map itemMap, Map userMap, Message msg,
                                   long acctId) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            List<Map<String, Object>> offerInstAttrList = ordBillDao
                    .selectOrdOfferInstAttrDec(itemMap);
            if (offerInstAttrList.size() > 0) {
                for (int i = 0; i < offerInstAttrList.size(); i++) {
                    Map offerInstAttrMap = new HashMap();
                    offerInstAttrMap = offerInstAttrList.get(i);
                    String operType = offerInstAttrMap.get("operType")
                            .toString();
                    offerInstAttrMap.put("routeId", acctId);
                    if (operType.equals("1000")) {
                        // 0414 route_id
                        List<Map<String, Object>> oldOfferInstAttrList1 = new ArrayList<Map<String, Object>>();
                        oldOfferInstAttrList1 = offerinstDao.getOfferInstAttrId(offerInstAttrMap);
                        if (oldOfferInstAttrList1.size() == 0) {
                            oldOfferInstAttrList1 = offerinstDao.getOfferInstAttrIdFromAttrId(offerInstAttrMap);
                            if (oldOfferInstAttrList1.size() == 0) {
                                continue;
                            }
                        }
                        for (Map<String, Object> attrMap : oldOfferInstAttrList1) {
                            attrMap.put("expDate", offerInstAttrMap.get("expDate"));
                            attrMap.put("effDate", offerInstAttrMap.get("effDate"));
                            attrMap.put("statusCd", 1100);
                            attrMap.put("routeId", acctId);
                            offerinstDao.updateOfferInstAttrForUpdateDate(attrMap);
                            attrMap.put("action", 2);
                            if (StringUtil.isEmpty(offerInstAttrMap.get("statusDate"))) {
                                attrMap.put("executetime", d.format(new Date()));
                            } else {
                                attrMap.put("executetime", d.format(df
                                        .parse(offerInstAttrMap.get("statusDate").toString())));
                            }

                            SynMapContextHolder.addMap("offerInstAttrobjList1", attrMap);

                        }

                    }


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "修改商品实例属性出错");
            msg.setResultCode(ResultCode.OFFERINS_U_ERROR_005);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }
    // 销售品角色
    public int insertOfferProdInst(Map itemMap, Map userMap, Message msg,
                                   long acctId) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            Map map = itemMap;
            long offerId = Long.parseLong(itemMap.get("offerId").toString());
            List<Map<String, Object>> offerProdInstList = ordBillDao
                    .selectOrdOfferProdInstRel(itemMap);
            if (offerProdInstList.size() > 0) {
                for (int i = 0; i < offerProdInstList.size(); i++) {
                    Map offerProdInstMap = new HashMap();
                    offerProdInstMap = offerProdInstList.get(i);
                    String roleId = offerProdInstMap.get("roleId").toString();
                    long offerDetailId;
                    //虚拟销售品不落计费
                    if ("90000".equals(roleId)
                            || "99999".equals(roleId)) {
                        continue;
                    }
                    try {
                        Map tifObjectIdMap = new HashMap();
                        List<Map<String, Object>> tifObjectIdList = ordBillDao.selectTifObjectId(offerId, roleId);
                        //如果是上线前的销售品，按照计费的落
                        if (tifObjectIdList.size() > 0) {
                            tifObjectIdMap = tifObjectIdList.get(0);
                            if ("N".equals(String.valueOf(tifObjectIdMap.get("ALLOW_FLAG")))) {
                                continue;
                            }
                            offerDetailId = Long.parseLong(tifObjectIdMap.get("DISCT_OBJECT_ID_BILL").toString());
                        } else {//如果没有，OFFER_OBJ_REL表里取
                            tifObjectIdList = billMapperDao.selectOfferRoleId(offerId, roleId);
                            if (tifObjectIdList.size() > 0) {
                                tifObjectIdMap = tifObjectIdList.get(0);
                                offerDetailId = Long.parseLong(tifObjectIdMap.get("OFFER_OBJ_REL_ID").toString());
                            } else {//如果还没有，直接落crm送下来的
                                offerDetailId = Long.parseLong(offerProdInstMap.get("offerProdRelId").toString());

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        msg.setResultCode(ResultCode.OFFERINS_I_ERROR_005);
                        msg.setMessage("取对应的套餐对象编码时出错【offerId】:" + offerId);
                        return -1;
                    }
                    if (StringUtil.isEmpty(offerProdInstMap.get("statusDate"))) {
                        offerProdInstMap.put("executetime", d.format(new Date()));
                    } else {
                        offerProdInstMap.put("executetime", d.format(df
                                .parse(offerProdInstMap.get("statusDate")
                                        .toString())));
                    }

                    String operType = offerProdInstMap.get("operType")
                            .toString();
                    offerProdInstMap.put("routeId", acctId);

                    if (operType.equals("1000")) {
                        // 取历史旧的，做判断是否存在
                        offerProdInstMap.put("offerObjInstRelId",
                                offerProdInstMap.get("offerProdInstRelId"));// 主键
                        List<Map<String, Object>> oldOfferProdInstList = offerinstDao
                                .getOfferObjInstId(offerProdInstMap);

                        if (oldOfferProdInstList.size() == 0) {
                            long prodInstRouteId =  routeMapperDao.getProdInstRoute(Long.parseLong(String.valueOf(offerProdInstMap.get("prodInstId"))));
                            Map prodMap = new HashMap();
                            prodMap.put("routeId", prodInstRouteId);
                            prodMap.put("prodInstId", offerProdInstMap.get("prodInstId"));
                            long lcnt = prodinstDao.getForCountByProdInstId(prodMap);
                            if (lcnt <= 0) {
                                msg.setResultCode(ResultCode.OFFERINS_I_ERROR_006);
                                msg.setMessage("用户(" + offerProdInstMap.get("prodInstId") + ")不存在,不能订购商品，请检查受理情况!");
                                return -1;
                            }
                            offerProdInstMap.put("action", 1);
                            offerProdInstMap.put("objType", 100000);
                            offerProdInstMap.put(
                                    "objId",
                                    Long.parseLong(offerProdInstMap.get(
                                            "prodInstId").toString()));
                            // 取销售品角色id
                            map.put("offerInstId",
                                    Long.parseLong(offerProdInstMap.get(
                                            "offerInstId").toString()));
                            offerProdInstMap
                                    .put("offerObjRelId", offerDetailId);
                            offerProdInstMap.put("lastOrderItemId",
                                    offerDetailId);
                            offerProdInstMap.put("offerObjInstRelId",
                                    offerProdInstMap.get("offerProdInstRelId"));// 主键
                            offerProdInstMap.put("statusCd", 1000);
                            offerinstDao
                                    .insertOfferObjInstRel(offerProdInstMap);

                            SynMapContextHolder.addMap("offerObjInstobjList1", offerProdInstMap);
                        }
                    } else if (operType.equals("1100")) {
                        // 取历史旧的route_id 0414
                        Long ROUTE_ID = 0L;
                        offerProdInstMap.put("offerObjInstRelId",
                                offerProdInstMap.get("offerProdInstRelId"));
                        List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
                                .getOfferObjInstId(offerProdInstMap);
                        if (oldOfferProdInstList1.size() == 0) {
                            msg.setResultCode(ResultCode.OFFERINS_I_ERROR_007);
                            msg.setMessage("没有找到可以修改的商品实例数据【offerInstId】：" + offerProdInstMap.get("offerInstId"));
                            return -1;
                        } else if (oldOfferProdInstList1.size() > 1) {
                            msg.setResultCode(ResultCode.OFFERINS_I_ERROR_008);
                            msg.setMessage("有多条符合条件的数据");
                            return -1;
                        }


                        offerProdInstMap.put("offerObjInstRelId",
                                offerProdInstMap.get("offerProdInstRelId"));// 主键
                        offerinstDao.updateOfferObjInstRel(offerProdInstMap);
                        offerProdInstMap.put("action", 2);
                        offerProdInstMap.put("objType", 100000);
                        offerProdInstMap.put(
                                "objId",
                                Long.parseLong(offerProdInstMap.get(
                                        "prodInstId").toString()));
                        SynMapContextHolder.addMap("offerObjInstobjList1", offerProdInstMap);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理账户ORD_OFFER_PROD_INST_失败");
            msg.setResultCode(ResultCode.OFFERINS_I_ERROR_009);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }
    public int insertOfferProdGroup(Map itemMap, Map userMap, Message msg,
                                    long acctId) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            Map map = itemMap;
            List<Map<String, Object>> ordOfferProdInstList = ordBillDao
                    .selectOrdOfferInstFromRowId(map);
            if (ordOfferProdInstList.size() != 1) {
                msg.setResultCode(ResultCode.OFFERGROUP_ERROR_001);
                msg.setMessage("取销售品实例接口表出错");
                return -1;
            }
            long offerId = Long.parseLong(itemMap.get("offerId").toString());
            /*List<Map<String, Object>> offerList = billMapperDao.selectOfferId(offerId);
            if (offerList.size() == 0) {
                return 1;
            }*/
            Map ordOfferProdInsMap = ordOfferProdInstList.get(0);
            Map checkMap = new HashMap();
            map.put("routeId", acctId);
            List<Map<String, Object>> offerProdInstList = offerinstDao.getOfferInstId(map);
            //如果没有实例，那么从以前的工单表里找，因为有的时候壳子新装工单先下来，找不到路由id
            //直接返回了，变更的时候订购上该实例
            if (offerProdInstList.size() == 0) {
                //取新装实例的工单
                checkMap.put("serviceOfferId", "301");
                checkMap.put("objId", map.get("offerInstId"));
                List<Map<String, Object>> ordBillObjectList = ordBillDao.selectBillObject(checkMap);
                if (ordBillObjectList.size() > 0) {
                    Map ordBillObjectMap = ordBillObjectList.get(0);
                    checkMap.put("ARCH_GRP_ID", ordBillObjectMap.get("ARCH_GRP_ID"));
                    checkMap.put("ORDER_ITEM_ID", ordBillObjectMap.get("ORDER_ITEM_ID"));
                    checkMap.put("offerInstId", map.get("offerInstId"));
                    List<Map<String, Object>> ordOfferList = ordBillDao.getOrdOfferInstOfferId(checkMap);
                    long cntOrdBillObj = ordBillDao.getCntOrdBillObj(checkMap);
                    if (ordOfferList.size() == 1 && cntOrdBillObj == 1) {
                        Map offerMap = ordOfferList.get(0);
                        if ("1000".equals(String.valueOf(offerMap.get("operType")))) {
                            ordOfferProdInsMap.put("effDate", offerMap.get("effDate"));
                            ordOfferProdInsMap.put("remark", "成员变更计费未找到商品实例，档案接口延时添加");
                            ordOfferProdInsMap.put("action", 1);
                            ordOfferProdInsMap.put("routeId", acctId);
                            ordOfferProdInsMap.put("regionId", map.get("regionId"));
                            ordOfferProdInsMap.put("lanId", map.get("lanId"));
                            offerinstDao.insertOfferInst(ordOfferProdInsMap);
                            ordOfferProdInsMap.put("executetime", d.format(new Date()));
                            SynMapContextHolder.addMap("offerInstobjList1", ordOfferProdInsMap);
                        }
                    } else {
                        msg.setResultCode(ResultCode.OFFERGROUP_ERROR_008);
                        msg.setMessage("成员变更商品实例工单表里有多条【offerinstId】:" + map.get("offerInstId"));
                        return -1;
                    }
                }else {
                    List<Map<String, Object>> tifContrastofferList = ordBillDao.selectTifDisctIdContrast(offerId);
                    if (tifContrastofferList.size() > 0) {
                        Map tifContrastMap = tifContrastofferList.get(0);
                        offerId = Long.parseLong(String.valueOf(tifContrastMap.get("DISCT_ID_BILL")));
                    } /*else {
                        msg.setResultCode(ResultCode.OFFERGROUP_ERROR_007);
                        msg.setMessage("成员变更未找到商品实例【offerinstId】:" + map.get("offerInstId"));
                        return -1;
                    }*/
                }
            }
            List<Map<String, Object>> offerProdInstRelList = ordBillDao
                    .selectOrdOfferProdInstRel(map);
            if (offerProdInstRelList.size() > 0) {
                for (int i = 0; i < offerProdInstRelList.size(); i++) {
                    Map offerProdInstMap = new HashMap();
                    offerProdInstMap = offerProdInstRelList.get(i);
                    offerProdInstMap.put("regionId", map.get("regionId"));
                    offerProdInstMap.put("lanId", map.get("lanId"));
                    if (StringUtil.isEmpty(offerProdInstMap.get("statusDate"))) {
                        offerProdInstMap.put("executetime", d.format(new Date()));
                    } else {
                        offerProdInstMap.put("executetime", d.format(df
                                .parse(offerProdInstMap.get("statusDate")
                                        .toString())));
                    }
                    String operType = offerProdInstMap.get("operType")
                            .toString();
                    offerProdInstMap.put("routeId", acctId);
                    String roleId = offerProdInstMap.get("roleId").toString();
                    long offerDetailId = 0;
                    //虚拟销售品不落计费
                    if ("90000".equals(roleId)
                            || "99999".equals(roleId)) {
                        continue;
                    }
                    try {
                        Map tifObjectIdMap = new HashMap();
                        List<Map<String, Object>> tifObjectIdList = ordBillDao.selectTifObjectId(offerId, roleId);
                        //如果是上线前的销售品，按照计费的落
                        if (tifObjectIdList.size() > 0) {
                            tifObjectIdMap = tifObjectIdList.get(0);
                            if ("N".equals(String.valueOf(tifObjectIdMap.get("ALLOW_FLAG")))) {
                                continue;
                            }
                            offerDetailId = Long.parseLong(tifObjectIdMap.get("DISCT_OBJECT_ID_BILL").toString());
                        } else {//如果没有，OFFER_OBJ_REL表里取
                            tifObjectIdList = billMapperDao.selectOfferRoleId(offerId, roleId);
                            if (tifObjectIdList.size() > 0) {
                                tifObjectIdMap = tifObjectIdList.get(0);
                                offerDetailId = Long.parseLong(tifObjectIdMap.get("OFFER_OBJ_REL_ID").toString());
                            } else {//如果还没有，直接落crm送下来的
                                offerDetailId = Long.parseLong(offerProdInstMap.get("offerProdRelId").toString());

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        msg.setResultCode(ResultCode.OFFERGROUP_ERROR_002);
                        msg.setMessage("取对应的套餐对象编码时出错【offerId】:" + offerId);
                        return -1;
                    }
                    if (operType.equals("1000")) {
                        // 取历史旧的，做判断是否存在
                        offerProdInstList = offerinstDao.getOfferInstId(map);
                        if (offerProdInstList.size() == 0) {
                            msg.setResultCode(ResultCode.OFFERGROUP_ERROR_007);
                            msg.setMessage("成员变更未找到商品实例【offerinstId】:" + map.get("offerInstId"));
                            return -1;
                        }
                        offerProdInstMap.put("offerObjInstRelId",
                                offerProdInstMap.get("offerProdInstRelId"));// 主键
                        List<Map<String, Object>> oldOfferProdInstList = offerinstDao
                                .getOfferObjInstId(offerProdInstMap);

                        if (oldOfferProdInstList.size() == 0) {
                            offerProdInstMap.put("action", 1);
                            offerProdInstMap.put("objType", 100000);
                            offerProdInstMap.put("statusCd", 1000);
                            offerProdInstMap.put(
                                    "objId",
                                    Long.parseLong(offerProdInstMap.get(
                                            "prodInstId").toString()));
                            // 取销售品角色id
                            map.put("offerInstId",
                                    Long.parseLong(offerProdInstMap.get(
                                            "offerInstId").toString()));
                            offerProdInstMap
                                    .put("offerObjRelId", offerDetailId);
                            offerProdInstMap.put("lastOrderItemId",
                                    offerDetailId);
                            offerProdInstMap.put("offerObjInstRelId",
                                    offerProdInstMap.get("offerProdInstRelId"));// 主键
                            offerinstDao
                                    .insertOfferObjInstRel(offerProdInstMap);
                            SynMapContextHolder.addMap("offerObjInstobjList1", offerProdInstMap);
                        }
                    } else if (operType.equals("1100")) {
                        // 取历史旧的route_id 0414
                        Long ROUTE_ID = 0L;
                        offerProdInstMap.put("offerObjInstRelId",
                                offerProdInstMap.get("offerProdInstRelId"));
                        offerProdInstMap.put("offerObjRelId", offerDetailId);
                        offerProdInstMap.put("objId", offerProdInstMap.get(
                                "prodInstId").toString());
                        List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
                                .getOfferObjInstId(offerProdInstMap);
                        Map offerObjRelMap = new HashMap();
                        offerObjRelMap.put("routeId", acctId);
                        if (oldOfferProdInstList1.size() == 0) {
                            //实例不统一的情况
                            Map offerobjMap = new HashMap();
                            List<Map<String, Object>> offerObjList = checkOfferInstRelIdExist(itemMap, offerProdInstMap, msg, acctId, offerId);
                            if (offerObjList.size() == 0) {
                                continue;
                            }
                            offerobjMap = offerObjList.get(0);
                            offerProdInstMap.put("offerObjInstRelId", offerobjMap.get("offerObjInstRelId"));
                            offerProdInstMap.put("offerInstId", offerobjMap.get("offerInstId"));
                            oldOfferProdInstList1 = offerinstDao.getOfferObjInstId(offerProdInstMap);
                            if (oldOfferProdInstList1.size() == 0) {
                                msg.setResultCode(ResultCode.OFFERGROUP_ERROR_003);
                                msg.setMessage("销售品退订找不到商品成员，请检查【offerInstId】：" + offerobjMap.get("offerInstId"));
                                return -1;
                            }

                        } else if (oldOfferProdInstList1.size() > 1) {
                            msg.setResultCode(ResultCode.OFFERGROUP_ERROR_004);
                            msg.setMessage("成员变更有多条符合条件的数据【offerInstId】：" +  offerProdInstMap.get("offerInstId"));
                            return -1;
                        }
                        offerObjRelMap = oldOfferProdInstList1.get(0);
                        offerObjRelMap.put("action", 2);
                        offerObjRelMap.put("objType", 100000);
                        offerObjRelMap.put("expDate", offerProdInstMap.get("expDate"));
                        offerObjRelMap.put("statusCd", 1100);
                        offerObjRelMap.put("statusDate", offerProdInstMap.get("statusDate"));
                        offerObjRelMap.put("executetime",offerProdInstMap.get("executetime"));
                        if (offerinstDao.updateOfferObjInstRel(offerObjRelMap) < 0) {
                            msg.setResultCode(ResultCode.OFFERGROUP_ERROR_005);
                            msg.setMessage("成员变更修改成员实例失败【offerInstId】：" + offerObjRelMap.get("offerInstId"));
                            return -1;
                        }
                        SynMapContextHolder.addMap("offerObjInstobjList1", offerObjRelMap);
                        /*offerProdInstMap.put("offerObjInstRelId",
                                offerProdInstMap.get("offerProdInstRelId"));// 主键
                        offerinstDao.updateOfferObjInstRel(offerProdInstMap);
                        offerProdInstMap.put("action", 2);
                        offerProdInstMap.put("objType", 100000);
                        offerProdInstMap.put(
                                "objId",
                                Long.parseLong(offerProdInstMap.get(
                                        "prodInstId").toString()));*/

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理商品成员纳入失败");
            msg.setResultCode(ResultCode.OFFERGROUP_ERROR_006);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    public int deleteOfferProdInst(Map itemMap, Map userMap, Message msg,
                                   long acctId,String expNextFlag) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            Map map = itemMap;
            List<Map<String, Object>> offerProdInstList = ordBillDao
                    .selectOrdOfferProdInstRel(itemMap);
            long offerId = Long.parseLong(itemMap.get("offerId").toString());
            if (offerProdInstList.size() > 0) {
                for (int i = 0; i < offerProdInstList.size(); i++) {
                    Map offerProdInstMap = new HashMap();

                    long offerDetailId = 0;
                    offerProdInstMap = offerProdInstList.get(i);
                    if (StringUtil.isEmpty(offerProdInstMap.get("statusDate"))) {
                        offerProdInstMap.put("executetime", d.format(new Date()));
                    } else {
                        offerProdInstMap.put("executetime", d.format(df
                                .parse(offerProdInstMap.get("statusDate")
                                        .toString())));
                    }
                    String roleId = offerProdInstMap.get("roleId").toString();
                    //虚拟销售品不落计费
                    if ("90000".equals(roleId)
                            || "99999".equals(roleId)) {
                        continue;
                    }
                    try {
                        Map tifObjectIdMap = new HashMap();
                        List<Map<String, Object>> tifObjectIdList = ordBillDao.selectTifObjectId(offerId, roleId);
                        //如果是上线前的销售品，按照计费的落
                        if (tifObjectIdList.size() > 0) {
                            tifObjectIdMap = tifObjectIdList.get(0);
                            if ("N".equals(String.valueOf(tifObjectIdMap.get("ALLOW_FLAG")))) {
                                continue;
                            }
                            offerDetailId = Long.parseLong(tifObjectIdMap.get("DISCT_OBJECT_ID_BILL").toString());
                        } else {//如果没有，OFFER_OBJ_REL表里取
                            tifObjectIdList = billMapperDao.selectOfferRoleId(offerId, roleId);
                            if (tifObjectIdList.size() > 0) {
                                tifObjectIdMap = tifObjectIdList.get(0);
                                offerDetailId = Long.parseLong(tifObjectIdMap.get("OFFER_OBJ_REL_ID").toString());
                            } else {//如果还没有，直接落crm送下来的
                                offerDetailId = Long.parseLong(offerProdInstMap.get("offerProdRelId").toString());

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        msg.setResultCode(ResultCode.OFFERINS_D_ERROR_003);
                        msg.setMessage("取对应的套餐对象编码时出错【offerId】:" + offerId);
                        return -1;
                    }
                    String operType = offerProdInstMap.get("operType")
                            .toString();
                    if ("1".equals(expNextFlag)) {
                        offerProdInstMap.put("expDate", itemMap.get("expDate"));
                    }
                    if (operType.equals("1100")) {
                        // 取历史旧的route_id 0414
                        offerProdInstMap.put("offerObjInstRelId",
                                offerProdInstMap.get("offerProdInstRelId"));
                        offerProdInstMap.put("routeId", acctId);
                        offerProdInstMap.put("offerObjRelId", offerDetailId);
                        offerProdInstMap.put("objId", offerProdInstMap.get("prodInstId").toString());

                        List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
                                .getOfferObjInstId(offerProdInstMap);
                        Map offerObjRelMap = new HashMap();
                        offerObjRelMap.put("routeId", acctId);
                        if (oldOfferProdInstList1.size() == 0) {
                            //实例不统一的情况
                            Map offerobjMap = new HashMap();
                            List<Map<String, Object>> offerObjList = checkOfferInstRelIdExist(itemMap, offerProdInstMap, msg, acctId, offerId);
                            if (offerObjList.size() == 0) {
                                continue;
                            }
                            offerobjMap = offerObjList.get(0);
                            offerProdInstMap.put("offerObjInstRelId", offerobjMap.get("offerObjInstRelId"));
                            offerProdInstMap.put("offerInstId", offerobjMap.get("offerInstId"));
                            oldOfferProdInstList1 = offerinstDao.getOfferObjInstId(offerProdInstMap);
                            if (oldOfferProdInstList1.size() == 0) {
                                msg.setResultCode(ResultCode.OFFERINS_D_ERROR_004);
                                msg.setMessage("销售品退订找不到商品成员，请检查【offerInstId】：" + offerobjMap.get("offerInstId"));
                                return -1;
                            }
                            //以下处理商品实例
                            offerobjMap.put("expDate", itemMap.get("expDate"));
                            List<Map<String, Object>> offerInstList = offerinstDao.getOfferInstIdFromExpDate(offerobjMap);
                            for (Map<String, Object> offerInstMap : offerInstList) {
                                offerInstMap.put("expDate", itemMap.get("expDate"));
                                offerInstMap.put("statusCd", 1100);
                                offerInstMap.put("action", 2);
                                offerInstMap.put("remark", "实例不统一，计费独自退订");
                                offerinstDao.updateOfferInst(offerInstMap);
                                offerInstMap.put("executetime", offerProdInstMap.get("executetime"));
                                SynMapContextHolder.addMap("offerInstobjList1", offerInstMap);
                            }
                            //以下处理商品实例属性
                            long offerInstId = Long.parseLong(offerobjMap.get("offerInstId").toString());
                            long routeIdTmp = Long.parseLong(offerProdInstMap.get("routeId").toString());
                            String expDate = offerProdInstMap.get("expDate").toString();
                            List<Map<String, Object>> offerAttrList = offerinstDao.selectOfferInstAttrIdFromInstId(offerInstId, routeIdTmp, expDate);
                            for (Map<String, Object> offerAttrMap : offerAttrList) {
                                offerAttrMap.put("expDate", offerProdInstMap.get("expDate"));
                                offerAttrMap.put("statusCd", 1100);
                                offerAttrMap.put("remark", "实例不统一，计费独自退订");
                                offerinstDao.updateOfferInstAttr(offerAttrMap);
                                offerAttrMap.put("action", 2);
                                offerAttrMap.put("executetime", offerProdInstMap.get("executetime"));
                                SynMapContextHolder.addMap("offerInstAttrobjList1", offerAttrMap);
                            }
                        } else if (oldOfferProdInstList1.size() > 1) {
                            msg.setResultCode(ResultCode.OFFERINS_D_ERROR_005);
                            msg.setMessage("销售品成员退订有多条符合条件的数据【offerInstId】：" + offerProdInstMap.get("offerInstId"));
                            return -1;
                        }
                        offerObjRelMap = oldOfferProdInstList1.get(0);
                        offerObjRelMap.put("action", 2);
                        offerObjRelMap.put("objType", 100000);
                        offerObjRelMap.put("expDate", offerProdInstMap.get("expDate"));
                        offerObjRelMap.put("statusCd", 1100);
                        offerObjRelMap.put("executetime", offerProdInstMap.get("executetime"));
                        offerinstDao.updateOfferObjInstRel(offerObjRelMap);
                        SynMapContextHolder.addMap("offerObjInstobjList1", offerObjRelMap);

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理ord_offer_prod_inst失败");
            msg.setResultCode(ResultCode.OFFERINS_D_ERROR_007);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    // 销售品对象角色
    public int insertOfferObjInst(Map itemMap, Map userMap, Message msg,
                                  long acctId) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
            Map map = itemMap;
            long offerId = Long.parseLong(itemMap.get("offerId").toString());
            List<Map<String, Object>> offerObjInstList = ordBillDao
                    .selectOrdOfferObjInstRel(itemMap);
            if (offerObjInstList.size() > 0) {
                for (int i = 0; i < offerObjInstList.size(); i++) {
                    Map offerProdInstMap = offerObjInstList.get(i);
                    String roleId = offerProdInstMap.get("roleId").toString();
                    long offerDetailId;
                    //虚拟销售品不落计费
                    if ("90000".equals(roleId)
                            || "402".equals(roleId)) {
                        continue;
                    }
                    try {
                        Map tifObjectIdMap = new HashMap();
                        List<Map<String, Object>> tifObjectIdList = ordBillDao.selectTifObjectId(offerId, roleId);
                        //如果是上线前的销售品，按照计费的落
                        if (tifObjectIdList.size() > 0) {
                            tifObjectIdMap = tifObjectIdList.get(0);
                            if ("N".equals(String.valueOf(tifObjectIdMap.get("ALLOW_FLAG")))) {
                                continue;
                            }
                            offerDetailId = Long.parseLong(tifObjectIdMap.get("DISCT_OBJECT_ID_BILL").toString());
                        } else {//如果没有，OFFER_OBJ_REL表里取
                            tifObjectIdList = billMapperDao.selectOfferRoleId(offerId, roleId);
                            if (tifObjectIdList.size() > 0) {
                                tifObjectIdMap = tifObjectIdList.get(0);
                                offerDetailId = Long.parseLong(tifObjectIdMap.get("OFFER_OBJ_REL_ID").toString());
                            } else {//如果还没有，直接落crm送下来的
                                offerDetailId = Long.parseLong(offerProdInstMap.get("offerObjRelId").toString());

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        msg.setResultCode(ResultCode.OFFERINS_I_ERROR_010);
                        msg.setMessage("取对应的套餐对象编码时出错【offerId】:" + offerId);
                        return -1;
                    }
//					offerProdInstMap.put("executetime", offerProdInstMap.get("updateDate"));
                    if (StringUtil.isEmpty(offerProdInstMap.get("statusDate"))) {
                        offerProdInstMap.put("executetime", d.format(new Date()));
                    } else {
                        offerProdInstMap.put("executetime", d.format(df.parse(offerProdInstMap.get("statusDate").toString())));
                    }
                    String operType = offerProdInstMap.get("operType")
                            .toString();
                    offerProdInstMap.put("routeId", acctId);

                    if (operType.equals("1000")) {
                        // 取历史旧的，做判断是否存在
                        List<Map<String, Object>> oldOfferProdInstList = offerinstDao
                                .getOfferObjInstId(offerProdInstMap);
                        if (oldOfferProdInstList.size() == 0) {
                            List<Map<String, Object>> oldOfferProdInstCheck =   offerinstDao.selectOfferObjInstId(offerProdInstMap);
                            if (oldOfferProdInstCheck.size() > 0) {
                                logger.error("crm主键重复，计费自己生成实例" + offerProdInstMap.get("offerObjInstRelId"));
                                long offerObjInstRelId = prodinstDao.getSeq("SEQ_OFFER_OBJ_INST_REL_ID");
                                offerProdInstMap.put("remark", offerProdInstMap.get("offerObjInstRelId"));
                                offerProdInstMap.put("offerObjInstRelId", offerObjInstRelId);
                            }
                            offerProdInstMap.put("action", 1);
                            map.put("offerInstId",
                                    Long.parseLong(offerProdInstMap.get(
                                            "offerInstId").toString()));

                            Map routeMap = new HashMap();

                            offerProdInstMap.put("routeId", acctId);
                            offerProdInstMap
                                    .put("offerObjRelId", offerDetailId);
                            offerProdInstMap.put("lastOrderItemId", "1");
                            offerProdInstMap.put("statusCd", 1000);
                            offerProdInstMap.put("objType", 170000);
                            offerinstDao
                                    .insertOfferObjInstRel(offerProdInstMap);

                            SynMapContextHolder.addMap("offerObjInstobjList1", offerProdInstMap);
                        }
                    } else if (operType.equals("1100")) {
                        // add by 0414 取route_id
                        Long ROUTE_ID = 0L;
                        List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
                                .getOfferObjInstId(offerProdInstMap);

                        if (oldOfferProdInstList1.size() == 0) {
                            msg.setResultCode(ResultCode.OFFERINS_I_ERROR_011);
                            msg.setMessage("没有找到可以修改的商品实例数据【offerInstId】：" + offerProdInstMap.get("offerInstId"));
                            return -1;
                        } else if (oldOfferProdInstList1.size() > 1) {
                            msg.setResultCode(ResultCode.OFFERINS_I_ERROR_012);
                            msg.setMessage("有多条符合条件的商品实例数据【offerInstId】：" + offerProdInstMap.get("offerInstId"));
                            return -1;
                        }
                        offerProdInstMap.put("routeId", acctId);
                        offerinstDao.updateOfferObjInstRel(offerProdInstMap);
                        offerProdInstMap.put("action", 2);
                        SynMapContextHolder.addMap("offerObjInstobjList1", offerProdInstMap);
                    }
                }
            }
            // if(offerObjInstList.size()>0)
            // {
            // for(int i=0;i<offerObjInstList.size();i++)
            // {
            // Map offerObjInstMap = offerObjInstList.get(i);
            // String operType=offerObjInstMap.get("operType").toString();
            // if(operType.equals("1000"))
            // {
            // offerinstDao.insertOfferObjInstRel(offerObjInstMap);
            // offerObjInstMap.put("action", 1);
            // offerObjInstobjList1.add(offerObjInstMap);
            // }
            // }
            // }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理ORD_OFFER_OBJ_INST_REL失败");
            msg.setResultCode(ResultCode.OFFERINS_I_ERROR_013);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    public int deleteOfferObjInst(Map itemMap, Map userMap, Message msg,
                                  long acctId,String expNextFlag) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
            Map map = itemMap;
            List<Map<String, Object>> offerObjInstList = ordBillDao
                    .selectOrdOfferObjInstRel(itemMap);
            long offerId = Long.parseLong(itemMap.get("offerId").toString());
            if (offerObjInstList.size() > 0) {
                for (int i = 0; i < offerObjInstList.size(); i++) {
                    Map offerProdInstMap = offerObjInstList.get(i);
                    String roleId = offerProdInstMap.get("roleId").toString();
                    long offerDetailId;
                    //虚拟销售品不落计费
                    if ("90000".equals(roleId)
                            || "99999".equals(roleId)) {
                        continue;
                    }
                    try {
                        Map tifObjectIdMap = new HashMap();
                        List<Map<String, Object>> tifObjectIdList = ordBillDao.selectTifObjectId(offerId, roleId);
                        if (tifObjectIdList.size() > 0) {
                            tifObjectIdMap = tifObjectIdList.get(0);
                            offerDetailId = Long.parseLong(tifObjectIdMap.get("DISCT_OBJECT_ID_BILL").toString());
                        } else {
                            tifObjectIdList = billMapperDao.selectOfferRoleId(offerId, roleId);
                            if (tifObjectIdList.size() > 0) {
                                tifObjectIdMap = tifObjectIdList.get(0);
                                offerDetailId = Long.parseLong(tifObjectIdMap.get("OFFER_OBJ_REL_ID").toString());
                            } else {
                                offerDetailId = Long.parseLong(offerProdInstMap.get("offerObjRelId").toString());

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        msg.setResultCode(ResultCode.OFFERINS_D_ERROR_007);
                        msg.setMessage("取对应的套餐对象编码时出错【offerId】:" + offerId);
                        return -1;
                    }

                    String operType = offerProdInstMap.get("operType")
                            .toString();
                    if ("1".equals(expNextFlag)) {
                        offerProdInstMap.put("expDate", itemMap.get("expDate"));
                    }
                    if (operType.equals("1100")) {
                        // add by 0414 取route_id

                        offerProdInstMap.put("routeId", acctId);
                        offerProdInstMap.put("offerObjRelId", offerDetailId);
                        List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
                                .getOfferObjInstId(offerProdInstMap);
                        Map offerObjRelMap = new HashMap();
                        offerObjRelMap.put("routeId", acctId);
                        if (oldOfferProdInstList1.size() <= 0) {
                            //处理实例不统一的情况
                            Map offerobjMap = new HashMap();
                            List<Map<String, Object>> offerobjList = checkOfferInstRelIdExist(itemMap, offerProdInstMap, msg, acctId, offerId);
                            if (offerobjList.size() == 0) {
                                continue;
                            }
                            offerobjMap = offerobjList.get(0);
                            offerProdInstMap.put("offerObjInstRelId", offerobjMap.get("offerObjInstRelId"));
                            offerProdInstMap.put("offerInstId", offerobjMap.get("offerInstId"));
                            oldOfferProdInstList1 = offerinstDao.getOfferObjInstId(offerProdInstMap);
                            if (oldOfferProdInstList1.size() == 0) {
                                msg.setResultCode(ResultCode.OFFERINS_D_ERROR_008);
                                msg.setMessage("账户级销售品退订找不到商品成员，请检查【offerInstId】：" + offerobjMap.get("offerInstId"));
                                return -1;
                            }
                            //以下处理商品实例
                            offerobjMap.put("expDate", itemMap.get("expDate"));
                            List<Map<String, Object>> offerInstList = offerinstDao.getOfferInstIdFromExpDate(offerobjMap);
                            for (Map<String, Object> offerInstMap : offerInstList) {
                                offerInstMap.put("expDate", itemMap.get("expDate"));
                                offerInstMap.put("statusCd", 1100);
                                offerInstMap.put("action", 2);
                                offerInstMap.put("remark", "实例不统一，计费独自退订");
                                offerinstDao.updateOfferInst(offerInstMap);
                                offerInstMap.put("executetime", d.format(df
                                        .parse(offerProdInstMap.get("statusDate")
                                                .toString())));
                                SynMapContextHolder.addMap("offerInstobjList1", offerInstMap);
                            }
                            //以下处理商品实例属性
                            long offerInstId = Long.parseLong(offerobjMap.get("offerInstId").toString());
                            long routeIdTmp = Long.parseLong(offerProdInstMap.get("routeId").toString());
                            String expDate = offerProdInstMap.get("expDate").toString();
                            List<Map<String, Object>> offerAttrList = offerinstDao.selectOfferInstAttrIdFromInstId(offerInstId, routeIdTmp, expDate);
                            for (Map<String, Object> offerAttrMap : offerAttrList) {
                                offerAttrMap.put("expDate", offerProdInstMap.get("expDate"));
                                offerAttrMap.put("statusCd", 1100);
                                offerAttrMap.put("remark", "实例不统一，计费独自退订");
                                offerinstDao.updateOfferInstAttr(offerAttrMap);
                                offerAttrMap.put("executetime", d.format(df
                                        .parse(offerProdInstMap.get("statusDate")
                                                .toString())));
                                offerAttrMap.put("action", 2);
                                SynMapContextHolder.addMap("offerInstAttrobjList1", offerAttrMap);
                            }
                        } else if (oldOfferProdInstList1.size() > 1) {
                            msg.setResultCode(ResultCode.OFFERINS_D_ERROR_009);
                            msg.setMessage("销售品成员退订有多条符合条件的数据【offerInstId】：" + offerProdInstMap.get("offerInstId"));
                            return -1;
                        }
                        offerObjRelMap = oldOfferProdInstList1.get(0);
                        offerObjRelMap.put("action", 2);
                        offerObjRelMap.put("objType", 170000);
                        offerObjRelMap.put("expDate", offerProdInstMap.get("expDate"));
                        offerObjRelMap.put("statusCd", 1100);
                        offerObjRelMap.put("statusDate", offerProdInstMap.get("statusDate"));
                        if (offerinstDao.updateOfferObjInstRel(offerObjRelMap) < 0) {
                            msg.setResultCode(ResultCode.OFFERINS_D_ERROR_010);
                            msg.setMessage("成员变更修改成员实例失败");
                            return -1;
                        }
                        offerObjRelMap.put("executetime", d.format(df
                                .parse(offerProdInstMap.get("statusDate")
                                        .toString())));
                        SynMapContextHolder.addMap("offerObjInstobjList1", offerObjRelMap);
                        //以下处理商品实例
                        /*List<Map<String, Object>> offerInstList = offerinstDao.getOfferInstId(offerObjRelMap);
                        for (Map<String, Object> offerInstMap : offerInstList) {
                            offerInstMap.put("expDate", itemMap.get("expDate"));
                            offerInstMap.put("statusCd", 1100);
                            offerInstMap.put("action", 2);
                            SynMapContextHolder.addMap("offerInstobjList1", offerObjRelMap);
                        }*/
                        //以下处理商品实例属性
                        /*long offerInstId = Long.parseLong(offerObjRelMap.get("offerInstId").toString());
                        String expDate = offerObjRelMap.get("expDate").toString();
                        List<Map<String, Object>> offerAttrList = offerinstDao.selectOfferInstAttrIdFromInstId(offerInstId, acctId, expDate);
                        for (Map<String, Object> offerAttrMap : offerAttrList) {
                            offerAttrMap.put("expDate", offerObjRelMap.get("expDate"));
                            offerAttrMap.put("statusCd", 1100);
                            offerinstDao.updateOfferInstAttr(offerAttrMap);
                            offerAttrMap.put("action", 2);
                            SynMapContextHolder.addMap("offerInstAttrobjList1", offerAttrMap);
                        }*/
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理ORD_OFFER_OBJ_INST_REL失败");
            msg.setResultCode(ResultCode.OFFERINS_D_ERROR_011);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    public int doProdInstAcctRel(Map itemMap, Map userMap, Message msg, Long serviceOfferId)
            throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat dfm = new SimpleDateFormat("yyyyMM");
        Map acctRelMap = itemMap;
        String checkOldAcct = "";
        List<Map<String, Object>> prodInstAcctRelList = ordBillDao
                .selectOrdProdInstAcctRel(acctRelMap);

        List<Map<String, Object>> ordProdInstAcctRelAttrList = new ArrayList<Map<String, Object>>();
        if (prodInstAcctRelList.size() > 0) {
            for (int i = 0; i < prodInstAcctRelList.size(); i++) {

                Map servAcctMap = new HashMap();
                servAcctMap = prodInstAcctRelList.get(i);
                String acctId = servAcctMap.get("acctId").toString();
                long prodInstId = Long.parseLong(servAcctMap.get("prodInstId").toString());
                servAcctMap.put("routeId", acctId);
                long acctCnt = accountDao.getAccountById(servAcctMap);
                if (acctCnt == 0) {
                    msg.setResultCode(ResultCode.PRODACCT_ERROR_001);
                    msg.setMessage("用户(" + prodInstId + ")的支付账户(" + acctId + ")在计费不存在");
                    return -1;
                }
                if (StringUtil.isEmpty(servAcctMap.get("statusDate"))) {
                    servAcctMap.put("executetime", d.format(new Date()));
                } else {
                    servAcctMap.put("executetime", d.format(df
                            .parse(servAcctMap.get("statusDate").toString())));
                }

                long operType = Long.parseLong(servAcctMap.get("operType")
                        .toString());
                ordProdInstAcctRelAttrList = ordBillDao.getOrdProdInstAcctRelAttr(servAcctMap);
                //取接口表ord_prod_inst表，判断是否为组合产品，组合产品不加账务关系
                List<Map<String, Object>> ordProdInstList = ordBillDao.selectOrdProdInst(servAcctMap);
                if (ordProdInstList.size() == 0) {
                    ordProdInstList = ordBillDao.selectOrdProdInst1300(servAcctMap);
                    if (ordProdInstList.size() == 0) {
                        ordProdInstList = ordBillDao.selectOrdProdInstAll(servAcctMap);
                        if (ordProdInstList.size() != 1) {
                            msg.setResultCode(ResultCode.PRODACCT_ERROR_002);
                            msg.setMessage("取用户接口表ord_prod_inst数据时出错");
                            return -1;
                        }

                    }
                } else if (ordProdInstList.size() != 1) {
                    msg.setResultCode(ResultCode.PRODACCT_ERROR_003);
                    msg.setMessage("取用户接口表ord_prod_inst数据时出错");
                    return -1;
                }
                Map ordProdInstMap = ordProdInstList.get(0);
                long archGrpId = Long.parseLong(servAcctMap.get("ARCH_GRP_ID").toString());
                long orderItemId = Long.parseLong(servAcctMap.get("ORDER_ITEM_ID").toString());
                long routeId = 0;
                //routeId = routeMapperDao.getProdInstRoute(prodInstId);
                routeId = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, prodInstId, msg);
                if (routeId <= 0) {
                    msg.setResultCode(ResultCode.PRODACCT_ERROR_004);
                    msg.setMessage("取用户的routeId失败【prodInstId】:" + prodInstId);
                    return -1;
                }
                servAcctMap.put("routeId", routeId);
                List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(ordProdInstMap);
                if (tifProdList.size() == 1) {
                    Map prodConMap = new HashMap();
                    prodConMap = tifProdList.get(0);
                    if ("10C".equals(prodConMap.get("prodType"))
                            || "10D".equals(prodConMap.get("prodType"))) {
                        return 1;
                    }
                }//取接口表ord_prod_inst表，判断是否为组合产品，组合产品不加账务关系 end
                if (operType == 1000)// 直接插入账务关系
                {
                    List<Map<String, Object>> prodInstMapList = prodinstDao.getProdInstOBJ(servAcctMap);
                    if (prodInstMapList.size() != 1) {
                        msg.setResultCode(ResultCode.PRODACCT_ERROR_005);
                        msg.setMessage("取对应的用户编码时出错，不允许增加账务关系【prodInstId】：" + servAcctMap.get("prodInstId"));
                        return -1;
                    }
                    Map prodInstMap = prodInstMapList.get(0);
                    String areaCode = "";
                    if (!StringUtil.isEmpty(prodInstMap.get("regionId"))) {
                        areaCode = prodInstMap.get("regionId").toString();
                        areaCode = "0" + areaCode.substring(0, 3);
                    } else {
                        areaCode = "0431";
                    }

						/*long prodInstPayModeID = selectPaymodeId(servAcctMap);
						if (prodInstPayModeID == -1) {
							msg.setMessage("取用户的付费类型出错，不允许增加账务关系");
							return -1;
						}*/
                    Map mapExpDateMap = new HashMap();
                    List<Map<String, Object>> ordPordInstAccrelList = ordBillDao.selectOrdProdInstAcctRel1100(servAcctMap);
                    if (ordPordInstAccrelList.size() > 0) {
                        List<Map<String, Object>> ordBillexpDateList = ordBillDao.queryOrdBillExpDate(servAcctMap);
                        mapExpDateMap = ordBillexpDateList.get(0);
                        if (mapExpDateMap.get("finishDate") == null
                                || "".equals(mapExpDateMap.get("finishDate"))) {
                            msg.setResultCode(ResultCode.PRODACCT_ERROR_006);
                            msg.setMessage("取工单竣工时间出错");
                            return -1;
                        }
                    }
                    long prodInstPayModeID = Long.parseLong(ordProdInstMap.get("paymentModeCd").toString());
                    // ad by wangbaoqiang 如果没有帐户代表号,则增加  1默认 -1否
//						if ("1".equals(servAcctMap.get("ifDefaultAcctId").toString())) {
                    servAcctMap.put("routeId", acctId);
                    List<Map<String, Object>> accountMapList = accountDao.getAccout(servAcctMap);
                    for (Iterator iterator = accountMapList.iterator(); iterator
                            .hasNext(); ) {
                        Map<String, Object> map = (Map<String, Object>) iterator
                                .next();
                        acctCnt = 0;
                        long prodInstIdTemp = Long.parseLong(StringUtil.isEmpty(map.get("prodInstId")) ? "0" : String.valueOf(map.get("prodInstId")));
                        long deputyInstRoute = routeServiceDao.getProdInstRoute(prodInstIdTemp, msg);
                        map.put("routeId", deputyInstRoute);
                        if (prodInstIdTemp > 0) {
									/*long routeIdTemp = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, prodInstIdTemp, msg);
									map.put("routeId", routeIdTemp);*/
                            acctCnt = prodinstDao.getProdInstCount2Ha(map);
                            if (acctCnt == 0) {
                                try {
                                    map.put("routeId", acctId);
                                    hisService.insertAccountHis(map);
                                    Map mapTemp = new HashMap();
                                    map.put("prodInstId", prodInstId);
                                    map.put("acctId", acctId);
                                    map.put("routeId", acctId);
                                    map.put("remark", "增加用户账务关系修改代表号码1");
                                    accountDao.updateAccount(map);
                                    map.put("action", 2);
                                    map.put("executetime", servAcctMap.get("executetime"));
                                    SynMapContextHolder.addMap("acctobjList1", map);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    String exceptionMsg = "";
                                    msg.setResultCode(ResultCode.PRODACCT_ERROR_007);
                                    exceptionMsg = setErrorMsg(e.getMessage(), "修改账户代表号码失败");
                                    msg.setMessage(exceptionMsg);
                                    return -1;
                                }
                            }
                        } else {
                            //更新账户代表号码
                            try {
                                map.put("routeId", acctId);
                                Map updateMap = new HashMap();
                                updateMap.put("acctId", acctId);
                                updateMap.put("routeId", acctId);
                                updateMap.put("prodInstId", servAcctMap.get("prodInstId"));
                                Map oldAcountMap = accountDao.getAccout(updateMap).get(0);
                                hisService.insertAccountHis(map);
                                updateMap.put("remark", "增加用户账务关系修改代表号码");
                                accountDao.updateAccount(updateMap);
                                oldAcountMap.put("action", 2);
                                oldAcountMap.put("prodInstId", prodInstId);
                                oldAcountMap.put("executetime", servAcctMap.get("executetime"));
                                SynMapContextHolder.addMap("acctobjList1", oldAcountMap);
                            } catch (Exception e) {
                                String exceptionMsg = "";
                                msg.setResultCode(ResultCode.PRODACCT_ERROR_012);
                                exceptionMsg = setErrorMsg(e.getMessage(), "修改账户代表号码失败");
                                msg.setMessage(exceptionMsg);
                                return -1;
                            }
                        }
                    }
                    acctCnt = 0;
                    servAcctMap.put("routeId", routeId);
                    acctCnt = prodinstDao.getCntProdInstIdFromAcctRel(servAcctMap);
                    Map checkOldAcctMap = new HashMap();
                    if (acctCnt == 0) {
                        //crm有时没有送修改动作过来，导致计费一个用户有多个账户，做一下处理
                        List<Map<String, Object>> prodInstRelList = prodinstDao.selectProdInstAcctRel(servAcctMap);
                        if (prodInstRelList.size() > 0) {
                            Map oldAcctMap = prodInstRelList.get(0);
                            oldAcctMap.put("action", 2);
                            oldAcctMap.put("statusCd", 2);
                            if (StringUtil.isEmpty(servAcctMap.get("statusDate"))) {
                                oldAcctMap.put("expDate", oldAcctMap.get("effDate"));
                            } else {
                                oldAcctMap.put("expDate",
                                        servAcctMap.get("statusDate"));
                            }
                            oldAcctMap.put("statusDate", servAcctMap.get("statusDate"));
                            prodinstDao.updateProdInstAcctRel(oldAcctMap);
                            SynMapContextHolder.addMap("prodInstAcctRelobjList1", oldAcctMap);
                            checkOldAcct = "1";
                            checkOldAcctMap.put("prodInstId", servAcctMap.get("prodInstId"));
                            checkOldAcctMap.put("ARCH_GRP_ID", servAcctMap.get("ARCH_GRP_ID"));
                            checkOldAcctMap.put("acctId", oldAcctMap.get("acctId"));
                            logger.info("crm没有修改账户动作，计费侧做失效处理【acctId】：" + oldAcctMap.get("acctId")
                            + "，【prodInstId】：" + servAcctMap.get("prodInstId") +
                                    "，【prodInstAcctRelId】：" + oldAcctMap.get("prodInstAcctRelId"));
                        }
                        long prodInstAcctRelId = prodinstDao
                                .seqProdInstAcctRelId();
                        servAcctMap.put("prodInstAcctRelId", prodInstAcctRelId);
                        servAcctMap.put("acctItemGroupId", 1);
                        servAcctMap.put("action", 1);
                        servAcctMap.put("statusCd", 1);
                        if (!"".equals(mapExpDateMap.get("finishDate"))
                                && mapExpDateMap.get("finishDate") != null) {
                            servAcctMap.put("effDate", mapExpDateMap.get("finishDate"));
                        }else if(!StringUtil.isEmpty(servAcctMap.get("statusDate"))){
                            servAcctMap.put("effDate", servAcctMap.get("statusDate"));
                        }
                        try {
                            prodinstDao.insertProdInstAcctRel(servAcctMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                            String exceptionMsg = "";
                            exceptionMsg = setErrorMsg(e.getMessage(), "增加帐务关系时出错");
                            msg.setResultCode(ResultCode.PRODACCT_ERROR_008);
                            msg.setMessage(exceptionMsg);
                            return -1;
                        }
                        SynMapContextHolder.addMap("prodInstAcctRelobjList1", servAcctMap);
                    } else {
                        msg.setResultCode(ResultCode.PRODACCT_ERROR_009);
                        msg.setMessage("存在相同的账户:prod_inst_id=" + prodInstId + ";ACCT_ID=" + acctId + "请检查");
                        return -1;
                    }
                    //增加过户插入过户中间表  paymodecd = 2100 ocs用户

                    try {
                        List<Map<String, Object>> ordPordInstAcctReList = ordBillDao.selectOrdProdInstAcctRel1100(servAcctMap);
                        if (ordPordInstAcctReList.size() > 0 || "1".equals(checkOldAcct)) {
                            Map<String, Object> map = new HashMap<>();
                            if (ordPordInstAcctReList.size() > 0) {
                                map = ordPordInstAcctReList.get(0);
                            } else {
                                map = checkOldAcctMap;
                                logger.info("特殊处理过户信息：" + map);
                            }

                            //for (Map<String, Object> map : ordPordInstAcctReList) {
                            String oldAcctId = map.get("acctId").toString();
                            if (oldAcctId.equals(acctId)) {//过户的时候新老账户已经一致就不在处理
                                logger.error("过户的时候新老账户一致");
                                return 1;
                            }
                            map.put("routeId", routeId);
                            //插入计费过户表
                            Map servAcctChang = new HashMap();
                            long seqNum = prodinstDao.getSeq("SEQ_B2M_CHANGE_ACCT");
                            long transId = prodinstDao.getSeq("SEQ_B2M_SERV_ACCT_CHANGE");
                            servAcctChang.put("transId", transId);
                            servAcctChang.put("seqNum", seqNum);
                            servAcctChang.put("prodInstId", servAcctMap.get("prodInstId"));
                            servAcctChang.put("oldAcctId", oldAcctId);
                            servAcctChang.put("newAcctId", acctId);
                            if ("1200".equals(prodInstPayModeID) || "1201".equals(prodInstPayModeID)) {
                                //肯定还要put，之前已经有过接口过来的、put进去的
                                servAcctChang.put("serOcsType", 0);
                                prodinstDao.insertServAcctChange(servAcctChang);
                            } else {
                                //肯定还要put
                                servAcctChang.put("serOcsType", 1);
                                prodinstDao.insertServAcctChange(servAcctChang);
                            }
                            //插入过户账务topic
                            Map sendMap = new HashMap();
                            sendMap.put("areaCode", areaCode);
                            sendMap.put("createDate", d.format(new Date()));
                            sendMap.put("dealDate", d.format(new Date()));
                            sendMap.put("destAcctId", acctId);
                            sendMap.put("prodInstId", map.get("prodInstId"));
                            sendMap.put("routeId", dfm.format(new Date()));
                            sendMap.put("serialNumber", map.get("ARCH_GRP_ID"));
                            sendMap.put("sourceAcctId", oldAcctId);
                            sendMap.put("state", "0");
                            sendChangeAcctMsg(sendMap, msg);
                            logger.info("用户【prodInstId】：" + map.get("prodInstId") + "过户用户级【key】：" + msg.getMessage());
                            // }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加过户中间表出错");
                        msg.setResultCode(ResultCode.PRODACCT_ERROR_010);
                        msg.setMessage(exceptionMsg);
                        return -1;

                    }
//						}//add end;


                    //帐务定制关系附加属性
                    for (Map<String, Object> ordProdInstAcctRelAttrMap : ordProdInstAcctRelAttrList) {
                        //ordProdInstAcctRelAttrMap.put("PROD_INST_ACCT_REL_ID", prodInstAcctRelId);
                        if (prodinstDao.getForProdInstAcctRelId(ordProdInstAcctRelAttrMap) == null) {
                            ordProdInstAcctRelAttrMap.put("routeId", routeId);
                            ordProdInstAcctRelAttrMap.put("STATUS_CD", 1000);
                            prodinstDao.insertProdInstAcctRelAttr(ordProdInstAcctRelAttrMap);
                            //SynMapContextHolder.addMap("prodInstAcctRelobjList1", ordProdInstAcctRelAttrMap);
                        }

                    }


                } else if (operType == 1100)// 修改
                {
                    // TODO: 2019/6/26 过户的结束时间取得是工单的竣工时间，这个到时候还得确认一下
                    List<Map<String, Object>> ordBillexpDateList = ordBillDao.queryOrdBillExpDate(servAcctMap);
                    Map mapExpDateMap = ordBillexpDateList.get(0);
                    if (mapExpDateMap.get("finishDate") == null) {
                        msg.setResultCode(ResultCode.PRODACCT_ERROR_011);
                        msg.setMessage("取工单竣工时间出错");
                        return -1;
                    }
                    //增加账务关系判断
                       /* long prodInstCnt = prodinstDao.getProdInstAcctCnt(servAcctMap);
                        if (prodInstCnt <= 0) {
                            msg.setResultCode(ResultCode.PRODACCT_ERROR_012);
                            msg.setMessage("产品实例帐务关系在计费不存在【prodInstid】：" + servAcctMap.get("prodInstId"));
                            return -1;
                        }*/
                    List<Map<String, Object>> oldProdInstAcctRelList = new ArrayList<Map<String, Object>>();
                    oldProdInstAcctRelList = prodinstDao
                            .getProdInstAcctRelId(servAcctMap);
                    if (oldProdInstAcctRelList.size() > 0) {
                        Map oldAcctMap = oldProdInstAcctRelList.get(0);
                        servAcctMap.put("action", 2);
                        servAcctMap.put("statusCd", 2);
							/*servAcctMap.put("expDate",
									servAcctMap.get("statusDate"));*/
                        servAcctMap.put("expDate",
                                mapExpDateMap.get("finishDate"));
                        servAcctMap.put("prodInstAcctRelId",
                                oldAcctMap.get("PROD_INST_ACCT_REL_ID"));
                        servAcctMap.put("routeId",
                                oldAcctMap.get("ROUTE_ID"));
                        prodinstDao.updateProdInstAcctRel(servAcctMap);
                        SynMapContextHolder.addMap("prodInstAcctRelobjList1", servAcctMap);
                    } else {
                        long cntProdInst = prodinstDao.getForCountByProdInstId(servAcctMap);
                        if (cntProdInst == 0) {
                            logger.error("计费没有有效的用户，修改账务关系不处理【prodInstId】：" +
                                    servAcctMap.get("prodInstId"));
                            return 1;
                        }
                        msg.setResultCode(ResultCode.PRODACCT_ERROR_013);
                        msg.setMessage("产品实例帐务关系在计费不存在【prodInstId】:"
                                + servAcctMap.get("prodInstId") + ",【acctId】："
                                + servAcctMap.get("acctId"));
                        return -1;
                    }
                    // 修改账户代表号码 取账户下最小号码
                    try {
                        Map updateMap = new HashMap();
                        updateMap.put("acctId", servAcctMap.get("acctId"));
                        updateMap.put("routeId", servAcctMap.get("acctId"));
                        updateMap.put("prodInstId", servAcctMap.get("prodInstId"));
                        List<Map<String, Object>> accountMapList = accountDao
                                .getAccoutForAccNum(updateMap);
//							if ("1".equals(servAcctMap.get("ifDefaultAcctId").toString())) {
                        //看该用户是否为该账户的代表号码
                        long cntProdInst = accountDao.getCntAccoutFromProdInstId(updateMap);
                        if (cntProdInst > 0) {
                            List<Map<String, Object>> prodInstRelMapList = prodinstDao
                                    .selectProdInstIdFromAcctRel(updateMap);
                            //prodInstRelMap.put("routeId", routeId);
                            //如果该账户下有多个在用用户则取最小代表号码，如果没有保持不变
                            // List<Map<String, Object>> acctLists = accountDao.getAccoutFromProdInstId(servAcctMap);
                            if (prodInstRelMapList.size() > 0) {
                                Map prodInstRelMap = prodInstRelMapList.get(0);
                                Map accountMap = new HashMap();
                                accountMap = accountMapList.get(0);
                                accountMap.put("prodInstId", prodInstRelMap.get("prodInstId"));
                                accountMap.put("action", 2);
                                accountMap.put("remark", "修改账务关系修改账户代表号码");
                                accountDao.updateAccount(accountMap);
                                accountMap.put("executetime", d.format(df
                                        .parse(servAcctMap.get("statusDate").toString())));
                                SynMapContextHolder.addMap("acctobjList1", accountMap);

                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "过户修改账户代表号码出错");
                        msg.setResultCode(ResultCode.PRODACCT_ERROR_014);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                    //修改账务定制关系
                    //ordProdInstAcctRelAttrList = ordBillDao.getOrdProdInstAcctRelAttr(servAcctMap);
                    for (Map<String, Object> ordProdInstAcctRelAttrMap : ordProdInstAcctRelAttrList) {
                        ordProdInstAcctRelAttrMap.put("routeId", routeId);
                        prodinstDao.updateProdInstAcctRelAttr(ordProdInstAcctRelAttrMap);
                        //SynMapContextHolder.addMap("prodInstAcctRelobjList1", ordProdInstAcctRelAttrMap);
                    }

                }

            }
        }
        return 1;
    }


    /**
     * @param itemMap
     * @param userMap
     * @param msg
     * @param routeId
     * @return
     * @throws Exception
     */
    public int doProdInstGroupMem(Map itemMap, Map userMap, Message msg, long routeId)
            throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            Map instRelMap = itemMap;
            String operType = instRelMap.get("operType").toString();
            instRelMap.put("executetime", d.format(df.parse(instRelMap
                    .get("statusDate").toString())));
            String stateDate = instRelMap.get("statusDate").toString();
            if (operType.equals("1000")) {
                List<Map<String, Object>> oldProdInstRelList = prodinstDao
                        .getProdInstRelId(instRelMap);
                if (oldProdInstRelList.size() > 0) {
                    /*Map relMap = oldProdInstRelList.get(0);
                    //instRelMap.put("action", 2);
                    prodinstDao.updateProdInstRel(instRelMap);*/
                    return 1;
                } else {
                    instRelMap.put("statusCd", 1000);
                    prodinstDao.insertProdInstRel(instRelMap);
                }
                //SynMapContextHolder.addMap("prodInstRelobjList1",instRelMap);
            } else if (operType.equals("1100")) {
                List<Map<String, Object>> oldProdInstRelList = prodinstDao
                        .getProdInstRelId(instRelMap);
                if (oldProdInstRelList.size() > 0) {
                    if (oldProdInstRelList.size() == 0) {
                        oldProdInstRelList = prodinstDao.getProdInstRelIdFromInstId(instRelMap);
                        if (oldProdInstRelList.size() == 0) {
                            msg.setResultCode(ResultCode.PRODGROUP_ERROR_006);
                            msg.setMessage("没有找到prod_inst_rel群成员记录【zProdInstId】：" + instRelMap.get("zProdInstId")
                                    + "，【aProdInstId】：" + instRelMap.get("aProdInstId"));
                            return -1;
                        }
                    }
                    Map relMap = oldProdInstRelList.get(0);
                    //instRelMap.put("action", 2);
                    relMap.put("expDate", instRelMap.get("expDate"));
                    relMap.put("statusCd", 1100);
                    relMap.put("statusDate", stateDate);
                    relMap.put("updateDate", instRelMap.get("updateDate"));
                    prodinstDao.updateProdInstRel(relMap);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理账户ORD_PROD_INST_REL失败");
            msg.setResultCode(ResultCode.PRODGROUP_ERROR_007);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    /**
     * 处理用户群关系
     *
     * @param itemMap
     * @param userMap
     * @param msg
     * @return
     * @throws Exception
     */
    public int doProdInstGroup(Map itemMap, Map userMap, Message msg)
            throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

            List<Map<String, Object>> prodInstRelList = ordBillDao
                    .selectOrdProdInstRelFromRowId(itemMap);
            for (Map<String, Object> map : prodInstRelList) {
                List<Map<String, Object>> prodInstRelGroupList = ordBillDao.selectOrdProdInstRelForGroup(map);
                if (prodInstRelGroupList.size() != 1) {
                    msg.setResultCode(ResultCode.PRODGROUP_ERROR_001);
                    msg.setMessage("取群产品实例接口表出错");
                    return -1;
                }
                String operType = map.get("operType").toString();
                for (Map<String, Object> groupMap : prodInstRelGroupList) {
                    String prodInstGroupId = groupMap.get("aProdInstId").toString();
                    groupMap.put("prodId", groupMap.get("aProdId"));
                    long archGrpId = Long.parseLong(groupMap.get("ARCH_GRP_ID").toString());
                    long orderItemId = Long.parseLong(groupMap.get("ORDER_ITEM_ID").toString());
                    long prodInstId = Long.parseLong(groupMap.get("aProdInstId").toString());
                    //取产品
                    List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(groupMap);
                    Map prodConMap = new HashMap();
                    if (tifProdList.size() == 1) {
                        prodConMap = tifProdList.get(0);
                    } else {
                        msg.setResultCode(ResultCode.PRODGROUP_ERROR_008);
                        msg.setMessage("取对应的产品编码时出错");
                        return -1;
                    }
                    long routeId = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, prodInstId, msg);
                    if (routeId <= 0 || String.valueOf(routeId) == null) {
                        if (!"10D".equals(prodConMap.get("prodType"))) {
                            return 1;
                        }
                        logger.error("获取群组路由失败【prodInstId】：" + prodInstId);
                        /*msg.setResultCode(ResultCode.PRODGROUP_ERROR_002);
                        msg.setMessage("获取群组路由失败【prodInstId】：" + prodInstId);
                        return -1;*/
                    }

                    groupMap.put("GroupProdInstId", prodInstGroupId);
                    groupMap.put("routeId", routeId);
                    List<Map<String, Object>> prodInstRelGroupList1 = prodinstDao.selectProdInstGroup(groupMap);
                    if (prodInstRelGroupList1.size() == 0) {
                        groupMap.put("prodInstId", prodInstGroupId);
                        prodInstRelGroupList1 = prodinstDao.getProdInst(groupMap);
                        if (prodInstRelGroupList1.size() == 0) {
                            logger.error("查找用户群产品实例失败【prodInstId】：" + prodInstGroupId);
                            /*msg.setResultCode(ResultCode.PRODGROUP_ERROR_003);
                            msg.setMessage("查找用户群产品实例失败【prodInstId】：" + prodInstGroupId);
                            return -1;*/
                        }
                    }
                    //取prod_inst 中的acc_num
                    prodInstId = Long.parseLong(groupMap.get("zProdInstId").toString());
                    routeId = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, prodInstId, msg);
                    if (routeId <= 0 || String.valueOf(routeId) == null) {
                        List<Map<String,Object>> crmProdInstList = smsInfoMapperDao.getCrmProdInstInfo(prodInstId);
                        if (crmProdInstList.size() == 0&&"1100".equals(operType)) {
                            logger.error("找不到对应的产品实例【prodInstId】：" + prodInstId);
                            continue;
                        }
                        msg.setResultCode(ResultCode.PRODGROUP_ERROR_004);
                        msg.setMessage("取群成员路由失败【zProdInstId】：" + prodInstId);
                        return -1;
                    }
                    groupMap.put("routeId", routeId);
                    groupMap.put("prodInstId", prodInstId);
                    List<Map<String, Object>> prodInstList = prodinstDao.getProdInstOBJ(groupMap);
                    if (prodInstList.size() == 0) {
                        msg.setResultCode(ResultCode.PRODGROUP_ERROR_005);
                        msg.setMessage("查找群成员产品实例失败【prodInstId】：" + prodInstId);
                        return -1;
                    }
                    //处理prod_inst_rel表
                    if (doProdInstGroupMem(groupMap, userMap, msg, routeId) < 0) {
                        return -1;
                    }

                    //以下处理VPN
                    /*List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(groupMap);
                    Map prodConMap = new HashMap();
                    if (tifProdList.size() == 1) {
                        prodConMap = tifProdList.get(0);
                        if (!"10D".equals(prodConMap.get("prodType"))) {
                            return 1;
                        }
                    } else {
                        msg.setResultCode(ResultCode.PRODGROUP_ERROR_008);
                        msg.setMessage("取对应的产品编码时出错");
                        return -1;
                    }*/
                    if (!"10D".equals(prodConMap.get("prodType"))) {
                        return 1;
                    }

                    Map prodInstGroupMap = new HashMap();
                    if (prodInstRelGroupList1.size() > 0) {
                        prodInstGroupMap = prodInstRelGroupList1.get(0);
                    }
                    prodInstGroupMap.put("vpnCode", prodInstGroupId);
                    prodInstGroupMap.put("vpnName", "VPN用户:" + prodInstGroupId);
                    prodInstGroupMap.put("effDate", map.get("effDate"));
                    prodInstGroupMap.put("expDate", statePublic.expDate);
                    prodInstGroupMap.put("state", "10A");
                    prodInstGroupMap.put("vpnType", "1");
                    prodInstGroupMap.put("prodInstId", groupMap.get("zProdInstId"));

                    //取tif_VPN_GROUP 表
                    List<Map<String, Object>> tifVpnGroupList = tifVpnGroupMapperDao.selectTifVpnGroup(prodInstGroupMap);
                    if (tifVpnGroupList.size() == 0) {
                        try {
                            tifVpnGroupMapperDao.insertTifVpnGroup(prodInstGroupMap);
                            prodInstGroupMap.put("action", 1);
                            //prodInstGroupMap.put("routeId", groupMap.get("routeId"));
                            prodInstGroupMap.put("executetime", d.format(new Date()));
                            SynMapContextHolder.addMap("tifVpnGroupList1", prodInstGroupMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                            String exceptionMsg = "";
                            exceptionMsg = setErrorMsg(e.getMessage(), "生成VPN用户群时出错");
                            msg.setResultCode(ResultCode.PRODGROUP_ERROR_009);
                            msg.setMessage(exceptionMsg);
                            return -1;
                        }

                    }

                    Map prodInstMap = prodInstList.get(0);
                    Map vpnMemMap = new HashMap();
                    vpnMemMap.put("memNumber", prodInstMap.get("accNum"));
                    vpnMemMap.put("vpnCode", prodInstGroupId);
                    vpnMemMap.put("memSeq", "1");
                    vpnMemMap.put("offerId", "1");
                    vpnMemMap.put("vpnType", "1");
                    vpnMemMap.put("servId", groupMap.get("zProdInstId"));
                    vpnMemMap.put("effDate", d.format(new Date()));
                    vpnMemMap.put("expDate", statePublic.expDate);

                    if ("1000".equals(operType)) {
                        try {
                            long lCount = 0;
                            lCount = tifVpnGroupMapperDao.selectCntTifVpnMem(vpnMemMap);
                            if (lCount == 0) {
                                long seqVpnMemId = prodinstDao.getSeq("SEQ_VPN_MEM_ID");
                                vpnMemMap.put("vpnMemId", seqVpnMemId);
                                //vpnMemMap.put("routeId", groupMap.get("routeId"));
                                vpnMemMap.put("action", 1);
                                vpnMemMap.put("executetime", d.format(new Date()));
                                tifVpnGroupMapperDao.insertTifVpnMem(vpnMemMap);
                                SynMapContextHolder.addMap("tifVpnMemList1", vpnMemMap);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            String exceptionMsg = "";
                            exceptionMsg = setErrorMsg(e.getMessage(), "插入VPN成员出错");
                            msg.setResultCode(ResultCode.PRODGROUP_ERROR_010);
                            msg.setMessage(exceptionMsg);
                            return -1;
                        }

                    } else if ("1100".equals(operType)) {
                        List<Map<String, Object>> tifVpnMemList = tifVpnGroupMapperDao.selectTifVpnMem(vpnMemMap);
                        for (Map<String, Object> memMap : tifVpnMemList) {
                            try {
                                memMap.put("expDate", d.format(new Date()));
                                memMap.put("action", 2);
                                //memMap.put("routeId", groupMap.get("routeId"));
                                tifVpnGroupMapperDao.updateTifVpnMem(memMap);
                                memMap.put("vpnType", "1");
                                memMap.put("executetime", d.format(new Date()));
                                SynMapContextHolder.addMap("tifVpnMemList1", memMap);
                            } catch (Exception e) {
                                e.printStackTrace();
                                String exceptionMsg = "";
                                exceptionMsg = setErrorMsg(e.getMessage(), "失效VPN群成员时出错");
                                msg.setResultCode(ResultCode.PRODGROUP_ERROR_011);
                                msg.setMessage(exceptionMsg);
                                return -1;
                            }

                        }

                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理ORD_PROD_INST_REL失败");
            msg.setResultCode(ResultCode.PRODGROUP_ERROR_012);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    public int doProdInstAttr(Map itemMap, Map userMap, Message msg)
            throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String DateStr = d.format(date);
            List<Map<String, Object>> prodInstAttrList = ordBillDao
                    .selectOrdProdInstAttrFromRowId(itemMap);
            // long acctId =0;
            if (prodInstAttrList.size() > 0) {
                for (int i = 0; i < prodInstAttrList.size(); i++) {
                    Map instAttrMap = prodInstAttrList.get(i);
                    if (StringUtil.isEmpty(instAttrMap.get("statusDate"))) {
                        instAttrMap.put("executetime", d.format(new Date()));
                    } else {
                        instAttrMap.put("executetime", d.format(df
                                .parse(instAttrMap.get("statusDate").toString())));
                    }

                    String operType = String.valueOf(instAttrMap.get("operType"));
                    String attrId = String.valueOf(instAttrMap.get("attrId"));
                    long archGrpId = Long.parseLong(String.valueOf(instAttrMap.get("ARCH_GRP_ID")));
                    long orderItemId = Long.parseLong(String.valueOf(instAttrMap.get("ORDER_ITEM_ID")));
                    long routeId = 0;
                    long prodInstId = 0;
                    String prodId = "";
                    List<Map<String, Object>> tifProdAttrList = ordBillDao.selectTifProdAttribContrast(attrId);
                    if (tifProdAttrList.size() == 0) {
                        return 1;
                    }
                    long lFlag = 0;
                    List<Map<String, Object>> ordPordInstAttrList = ordBillDao.selectOrdProdInstAttr1000(instAttrMap);
                    if (ordPordInstAttrList.size() == 0) {
                        ordPordInstAttrList = ordBillDao.selectOrdProdInstAttr2000(instAttrMap);
                        if (ordPordInstAttrList.size() != 1) {
                            msg.setResultCode(ResultCode.PRODATTR_ERROR_001);
                            msg.setMessage("取用户属性接口表出错");
                            return -1;
                        }
                        //屏蔽掉计费不需要附属产品属性 begin
                        Map ordProdInstMap = ordPordInstAttrList.get(0);
                        Map tifProdSubMap = new HashMap();
                        String prodSubType = "";
                        //prodId = ordProdInstMap.get("prodId").toString();
                        instAttrMap.put("prodId", ordProdInstMap.get("prodId"));
                        instAttrMap.put("accProdInstId", ordProdInstMap.get("accProdInstId"));
                        List<Map<String, Object>> ordProdInsSubrList =
                                ordBillDao.selectTifSubProdContrast(ordProdInstMap.get("prodId").toString());
                        if (ordProdInsSubrList.size() == 0) {
                            msg.setResultCode(ResultCode.PRODATTR_ERROR_002);
                            msg.setMessage("取对应的附属产品编码时出错【prodId】：" + ordProdInstMap.get("prodId"));
                            return -1;
                        }
                        tifProdSubMap = ordProdInsSubrList.get(0);
                        prodSubType = tifProdSubMap.get("prodType").toString();
                        if ("10X".equals(prodSubType)) {
                            return 1;
                        }//end
                        prodInstId = Long.parseLong(ordProdInstMap.get("accProdInstId").toString());
                        routeId = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, prodInstId, msg);
                        if (routeId <= 0) {
                            msg.setResultCode(ResultCode.PRODATTR_ERROR_008);
                            msg.setMessage("取用户的routeId失败【prodInstId】：" + prodInstId);
                            return -1;
                        }
                        instAttrMap.put("routeId", routeId);
                        lFlag = 1;

                    } else if (ordPordInstAttrList.size() > 1) {
                        msg.setResultCode(ResultCode.PRODATTR_ERROR_003);
                        msg.setMessage("取用户接口表出错：存在多条");
                        return -1;
                    } else {
                        Map ordProdInstMap = ordPordInstAttrList.get(0);
                        //组合和群组产品用户属性不落地
                        List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(ordProdInstMap);
                        Map prodConMap = new HashMap();
                        if (tifProdList.size() == 1) {
                            prodConMap = tifProdList.get(0);
                            if ("10C".equals(prodConMap.get("prodType"))
                                    || "10D".equals(prodConMap.get("prodType"))) {
                                return 1;
                            }
                        } else {
                            msg.setResultCode(ResultCode.PRODATTR_ERROR_003);
                            msg.setMessage("取对应的产品编码时出错【prodId】：" + ordProdInstMap.get("prodId"));
                            return -1;
                        }
                        prodInstId = Long.parseLong(ordProdInstMap.get("accProdInstId").toString());
                        routeId = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, prodInstId, msg);
                        if (routeId <= 0) {
                            msg.setResultCode(ResultCode.PRODATTR_ERROR_009);
                            msg.setMessage("取用户的routeId失败【prodInstId】：" + prodInstId);
                            return -1;
                        }
                        instAttrMap.put("routeId", routeId);
                        instAttrMap.put("accProdInstId", prodInstId);
                        instAttrMap.put("prodId", ordProdInstMap.get("prodId"));

                        instAttrMap.put("routeId", routeId);

                    }
                    //处理主产皮实例属性 begin
                    if (lFlag == 0) {
                        if ("1000".equals(operType))// 先判断，是否存在，不存在旧新增
                        {
                            instAttrMap.put("expDate", instAttrMap.get("statusDate"));
                            /*List<Map<String, Object>> oldProdAttrList = prodinstDao
                                    .getProdInstAttrId(instAttrMap);*/
                            long prodAttrId = 0;
                           // if (oldProdAttrList.size() == 0) {
                                prodAttrId = prodinstDao.getForProdInstAttrId(instAttrMap);
                                if (prodAttrId == 0) {
                                    List<Map<String, Object>> oldProdAttrList = prodinstDao
                                            .getProdInstAttrId(instAttrMap);
                                    if (oldProdAttrList.size() > 0) {
                                        long seqProdInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID");
                                        instAttrMap.put("prodInstAttrId", seqProdInstAttrId);
                                    }
                                    try {
                                        instAttrMap.put("action", 1);
                                        instAttrMap.put("statusCd", 1000);
                                        instAttrMap.put("effDate",
                                                instAttrMap.get("statusDate"));
                                        instAttrMap.put("expDate", statePublic.expDate);
                                        prodinstDao.insertProdInstAttr(instAttrMap);
                                        SynMapContextHolder.addMap("prodInstAttrobjList1", instAttrMap);
                                        //吉林缓开发票
                                        if ("10020123".equals(attrId) && "2".equals(String.valueOf(instAttrMap.get("attrValue")))) {
                                            long contactId = acctMapperDao.getAcctSeq("seq_contact_id");
                                            List<Map<String,Object>> prodInstList = prodinstDao.getProdInstOBJ(instAttrMap);
                                            Map prodInstMap = new HashMap();
                                            Map prodInstAcctMap = new HashMap();
                                            AElecInvContact aElecInvContact = new AElecInvContact();
                                            if (prodInstList.size() > 0) {
                                                prodInstMap = prodInstList.get(0);
                                                List<Map<String, Object>> prodInstAcctList = prodinstDao.getProdInstAcctId(instAttrMap);
                                                if (prodInstAcctList.size() > 0) {
                                                    prodInstAcctMap = prodInstAcctList.get(0);
                                                } else {
                                                    msg.setResultCode(ResultCode.PRODATTR_ERROR_010);
                                                    msg.setMessage("缓开发票取账户标示失败【prodInstId】：" + prodInstId);
                                                    return -1;
                                                }
                                                aElecInvContact.setAcctId(Long.parseLong(String.valueOf(prodInstAcctMap.get("acctId"))));
                                                aElecInvContact.setAccNbr(String.valueOf(prodInstMap.get("accNbr")));
                                                aElecInvContact.setRouteId(Long.parseLong(String.valueOf(instAttrMap.get("routeId"))));
                                                aElecInvContact.setSendFlag(0);
                                                aElecInvContact.setState("10A");
                                                aElecInvContact.setStateDate(String.valueOf(instAttrMap.get("statusDate")));
                                                aElecInvContact.setContactId(contactId);
                                                aElecInvContactMapperDao.insertAElecInvContact(aElecInvContact);
                                            } else {
                                                msg.setResultCode(ResultCode.PRODATTR_ERROR_011);
                                                msg.setMessage("缓开发票取用户标示失败【prodInstId】：" + prodInstId);
                                                return -1;
                                            }
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        String exceptionMsg = "";
                                        exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品实例属性时出错");
                                        msg.setResultCode(ResultCode.PRODATTR_ERROR_004);
                                        msg.setMessage(exceptionMsg);
                                        return -1;
                                    }

                                }
                            //}

                        } else if ("1100".equals(operType)) {
                            instAttrMap.put("expDate", instAttrMap.get("statusDate"));
                            List<Map<String, Object>> oldProdAttrList = new ArrayList<>();//prodinstDao.getProdInstAttrId(instAttrMap);

                            //if (oldProdAttrList.size() == 0) {
                                oldProdAttrList = prodinstDao.getProdInstAttrIdFromAttrId(instAttrMap);
                                // TODO: 2019/6/28 先验证sql，以后不用做校验
                                if (oldProdAttrList.size() == 0) {
                                    logger.error("取用户属性id失败【prodInstId】：" + instAttrMap.get("accProdInstId") +
                                            ",【attrId】：" + instAttrMap.get("attrId"));
                                    /*msg.setResultCode(ResultCode.PRODATTR_ERROR_005);
                                    msg.setMessage("取用户属性id失败【prodInstId】：" + instAttrMap.get("accProdInstId") +
                                            ",【attrId】：" + instAttrMap.get("attrId"));*/
                                    return 1;
                                }
                            //}
                            try {
                                Map attrMap = oldProdAttrList.get(0);
                                instAttrMap.put("action", 2);
                                instAttrMap.put("prodInstAttrId", attrMap.get("prodInstAttrId"));
                                //instAttrMap.put("routeId",attrMap.get("routeId"));
                                instAttrMap.put("statusCd", 1100);
                                instAttrMap.put("expDate", instAttrMap.get("statusDate"));
                                prodinstDao.updateProdInstAttr(instAttrMap);
                                SynMapContextHolder.addMap("prodInstAttrobjList1", instAttrMap);
                            } catch (Exception e) {
                                e.printStackTrace();
                                String exceptionMsg = "";
                                exceptionMsg = setErrorMsg(e.getMessage(), "修改主产品实例属性时出错");
                                msg.setResultCode(ResultCode.PRODATTR_ERROR_006);
                                msg.setMessage(exceptionMsg);
                                return -1;
                            }

                        }

                    }//end

                    if (lFlag == 1) {//处理附属产品实例属性 begin
                        if ("1000".equals(operType))// 先判断，是否存在，不存在旧新增
                        {
                            List<Map<String, Object>> oldProdAttrList = prodinstDao
                                    .getProdInstAttrIdForSub(instAttrMap);
                            //修改功能产品属性的时候，crm序列会重复
                            if (oldProdAttrList.size() > 0) {
                                long prodInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); // 统一序列
                                instAttrMap.put("prodInstAttrId", prodInstAttrId);
                            }
                                instAttrMap.put("action", 1);
                                instAttrMap.put("statusCd", 1000);
                                instAttrMap.put("effDate",
                                        instAttrMap.get("statusDate"));
                                instAttrMap.put("expDate", statePublic.expDate);
                                prodinstDao.insertProdInstAttrSub(instAttrMap);
                                SynMapContextHolder.addMap("prodInstAttrSubobjList1", instAttrMap);
                        } else if ("1100".equals(operType)) {
                            List<Map<String, Object>> oldProdAttrList = new ArrayList<>();//prodinstDao.getProdInstAttrIdForSub(instAttrMap);
                           // if (oldProdAttrList.size() == 0) {
                                instAttrMap.put("effDate",
                                        instAttrMap.get("statusDate"));
                                List<Map<String, Object>> oldProdSubList = prodinstDao.selectProdInstSubMap(instAttrMap);
                                // TODO: 2019/6/28 测试阶段打开验证数据，生产的时候在关闭
                                if (oldProdSubList.size() == 0) {
                                    /*msg.setMessage("附属产品计费不存在【prodId】：" + instAttrMap.get("prodId")  +
											"【accProdInstId】：" + instAttrMap.get("accProdInstId"));
                                    return  -1;*/
                                    return 1;
                                }
                                Map oldProdSubMap = oldProdSubList.get(0);
                                oldProdSubMap.put("attrId", attrId);
                                oldProdSubMap.put("expDate", instAttrMap.get("statusDate"));
                                oldProdAttrList = prodinstDao.selectProdInstAttrSubMap(oldProdSubMap);
                                // TODO: 2019/7/2 测试阶段打开验证数据，生产的时候在关闭
                                if (oldProdAttrList.size() == 0) {
									/*msg.setMessage("附属产品属性计费不存在【prodInstId】：" + oldProdSubMap.get("prodInstId")  +
											"【attrId】：" + oldProdSubMap.get("attrId"));
									return  -1;*/
                                    return 1;
                                }

                            //}
                            Map attrMap = oldProdAttrList.get(0);
                            instAttrMap.put("expDate",
                                    instAttrMap.get("statusDate"));
                            instAttrMap.put("action", 2);
                            instAttrMap.put("routeId",
                                    attrMap.get("routeId"));
                            // 从计费库获取相应主键,并存入map中
                            instAttrMap.put("prodInstAttrId",
                                    attrMap.get("prodInstAttrId"));
                            /*instAttrMap.put("prodInstAttrId",
                                    selectProdInstAttrId(instAttrMap));*/
                            prodinstDao.updateProdInstAttrSub(instAttrMap);
                            SynMapContextHolder.addMap("prodInstAttrSubobjList1", instAttrMap);
                        }
                    }//end


                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理账户ORD_PROD_INST_attr失败");
            msg.setResultCode(ResultCode.PRODATTR_ERROR_007);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    public int deleteProdInst(Map itemMap, Map userMap, Message msg)
            throws Exception {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

        Map deleteMap = itemMap;
        List<Map<String, Object>> prodInstList = ordBillDao
                .selectOrdProdInst(deleteMap);
        Map prodInstMap = new HashMap();
        long routeId;
        long prodInstId;
        long archGrpId;
        long orderItemId;
        //add by wangbaoqiang begin
        if (prodInstList.size() > 1) {
            msg.setResultCode(ResultCode.PRODINST_D_ERROR_001);
            msg.setMessage("取用户接口表数据时出错:记录有多条");
            return -1;
        }

        if (prodInstList.size() > 0) {
            prodInstMap = prodInstList.get(0);
            String statusCd = prodInstMap.get("statusCd").toString();
            prodInstId = Long.parseLong(prodInstMap.get("accProdInstId").toString());
            archGrpId = Long.parseLong(prodInstMap.get("ARCH_GRP_ID").toString());
            orderItemId = Long.parseLong(prodInstMap.get("ORDER_ITEM_ID").toString());
            routeId = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, prodInstId, msg);
            if (routeId == -1) {
                msg.setResultCode(ResultCode.PRODINST_D_ERROR_001);
                msg.setMessage("获取用户routeId失败【prodInstId】:" + prodInstId);
                return -1;
            }
            prodInstMap.put("routeId", routeId);
            if (StringUtil.isEmpty(prodInstMap.get("statusDate"))) {
                prodInstMap.put("executetime", d.format(new Date()));
            } else {
                prodInstMap.put("executetime", d.format(df.parse(prodInstMap
                        .get("statusDate").toString())));
            }
            //add by wangbaoqiang begin
            if (!"110001".equals(statusCd)
                    && !"110002".equals(statusCd)) {
                msg.setResultCode(ResultCode.PRODINST_D_ERROR_002);
                msg.setMessage("拆机工单但状态不是拆机!");
                return -1;
            }
            //判断是否为组合产品
            try {
                List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(prodInstMap);
                if (tifProdList.size() == 1) {
                    Map prodConMap = new HashMap();
                    prodConMap = tifProdList.get(0);
                    if ("10C".equals(prodConMap.get("prodType"))
                            || "10D".equals(prodConMap.get("prodType"))) {
                        prodInstMap.put("GroupProdInstId", prodInstMap.get("prodInstId"));
                        List<Map<String, Object>> prodInstGroupList = prodinstDao.selectProdInstGroup(prodInstMap);
                        if (prodInstGroupList.size() == 1) {
                            prodinstDao.updateProdInstGroup(prodInstMap);
                            prodInstMap.put("action", 2);
                            SynMapContextHolder.addMap("prodInstGroupobjList1", prodInstMap);

                        }
                        //失效用户群
                        if (prodConMap.get("prodType").equals("10D")) {
                            Map tifVpnGroupMap = new HashMap();
                            tifVpnGroupMap.put("vpnCode", prodInstMap.get("prodInstId"));
                            List<Map<String, Object>> tifVpnGroupList = tifVpnGroupMapperDao.selectTifVpnGroup(tifVpnGroupMap);
                            if (tifVpnGroupList.size() > 0) {
                                tifVpnGroupMap = tifVpnGroupList.get(0);
                                tifVpnGroupMap.put("expDate", prodInstMap.get("statusDate"));
                                tifVpnGroupMapperDao.updateTifVpnGroup(tifVpnGroupMap);
                                tifVpnGroupMap.put("action", 2);
                                //tifVpnGroupMap.put("routeId", prodInstMap.get("routeId"));
                                tifVpnGroupMap.put("executetime", prodInstMap.get("executetime"));
                                SynMapContextHolder.addMap("tifVpnGroupList1", tifVpnGroupMap);
                            }


                        }
                        return 1;
                    }
                } else {
                    msg.setResultCode(ResultCode.PRODINST_D_ERROR_003);
                    msg.setMessage("取对应的产品编码时出错");
                    return -1;
                }

            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "失效用户群时出错");
                msg.setResultCode(ResultCode.PRODINST_D_ERROR_004);
                msg.setMessage(exceptionMsg);
                return -1;
            }


/*					Long prodInstCount = prodinstDao.getForCountByProdInstId(prodInstMap);
					if(prodInstCount == 0){
						msg.setMessage("用户"+prodInstMap.get("prodInstId")+"）在计费不存在,不能拆机!");
						return -1;
					}*/

            //计费侧如果是拆机状态的话不处理  begin
            List<Map<String, Object>> prodInstListTmp = prodinstDao.getProdInstOBJ(prodInstMap);
            //add end;
            if (prodInstListTmp.size() == 0) {
                /*msg.setResultCode(ResultCode.PRODINST_D_ERROR_004);
                msg.setMessage("用户【" + prodInstMap.get("prodInstId") + "】在计费不存在,不能拆机!");*/
                logger.error("用户【" + prodInstMap.get("prodInstId") + "】在计费不存在");
                return 1;
            }
            Map prodInstMapTemp = prodInstListTmp.get(0);
            String prodInstStatus = prodInstMapTemp.get("statusCd").toString();
            String prodInstAccNum = prodInstMapTemp.get("accNum").toString();
            if ("110001".equals(prodInstStatus)
                    || "110002".equals(prodInstStatus)
                    ||"110000".equals(prodInstStatus)) {
                logger.error("用户【" + prodInstMap.get("prodInstId") + "】已经是拆机状态");
                return 1;
            }// 计费侧如果是拆机状态的话不处理 end

            prodInstMap.put("action", 2);
            prodInstMap.put("routeId", routeId);


            //add by wangbaoqiang 按时间排序 begin
            try {
                List<Map<String, Object>> getProdInstStateExList = new ArrayList<Map<String, Object>>();
/*					getProdInstStateExList = prodinstDao.getProdInstState(prodInstMap);
					Collections.sort(getProdInstStateExList, new Comparator<Map<String, Object>>(){

						   public int compare(Map<String, Object> o1, Map<String, Object> o2) {
						    String name1 =(String)o1.get("EXP_DATE");//name1是从你list里面拿出来的一个
						    String name2= (String)o2.get("EXP_DATE"); //name1是从你list里面拿出来的第二个name
						    return name2.compareTo(name1);
					   }

						  });*/
                List<Map<String, Object>> prodInstStateExtFromStateList = prodinstDao.getProdInstStateExtFromStateExt(prodInstMap);
                if (prodInstStateExtFromStateList.size() <= 0) {
                    msg.setResultCode(ResultCode.PRODINST_D_ERROR_005);
                    msg.setMessage("取计费主产品状态时出错");
                    return -1;
                }
                Map prodInstStateExtFromStateMap = new HashMap();
                prodInstStateExtFromStateMap = prodInstStateExtFromStateList.get(0);
                prodInstStateExtFromStateMap.put("statusCd", 1100);
                prodInstStateExtFromStateMap.put("expDate", prodInstMap.get("statusDate"));
                prodInstStateExtFromStateMap.put("action", 2);
                prodinstDao.updateProdInstStateExt(prodInstStateExtFromStateMap);
                prodInstStateExtFromStateMap.put("executetime", prodInstMap.get("executetime"));
                SynMapContextHolder.addMap("prodInstStateobjList1", prodInstStateExtFromStateMap);
            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "修改主产品状态时出错");
                msg.setResultCode(ResultCode.PRODINST_D_ERROR_006);
                msg.setMessage(exceptionMsg);
                return -1;
            }
            // 增加用户状态
            try {
                //统一序列
                Map prodInstExtMap = new HashMap();
                Long prodInstStateId = prodinstDao.getSeq("SEQ_PROD_INST_STATE_ID");
                prodInstExtMap.put("prodInstStateId", prodInstStateId);
                prodInstExtMap.put("state", prodInstMap.get("statusCd"));
                prodInstExtMap.put("stopType", "0");
                prodInstExtMap.put("statusCd", "1000");
                prodInstExtMap.put("action", 1);
                prodInstExtMap.put("expDate", statePublic.expDate);
                prodInstExtMap.put("effDate", prodInstMap.get("statusDate"));
                prodInstExtMap.put("routeId", routeId);
                prodInstExtMap.put("createDate", prodInstMap.get("createDate"));
                prodInstExtMap.put("statusDate", prodInstMap.get("statusDate"));
                prodInstExtMap.put("updateDate", prodInstMap.get("updateDate"));
                prodInstExtMap.put("updateStaff", prodInstMap.get("updateStaff"));
                prodInstExtMap.put("createStaff", prodInstMap.get("createStaff"));
                prodInstExtMap.put("prodInstId", prodInstMap.get("prodInstId"));
                prodinstDao.insertProdInstStateExt(prodInstExtMap);
                prodInstExtMap.put("executetime", prodInstMap.get("executetime"));
                SynMapContextHolder.addMap("prodInstStateobjList1", prodInstExtMap);

            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品状态时出错");
                msg.setResultCode(ResultCode.PRODINST_D_ERROR_007);
                msg.setMessage(exceptionMsg);
                return -1;
            }//add end;
            long prodId = Long.parseLong(prodInstMap.get("prodId").toString());
            String trunkNum;
            String accNum = prodInstMap.get("accNum").toString();
            String areaCode;
            String prodType;
            long tCount;
            long trunkId;
            try {
                if (prodId == 280000035 && accNum.indexOf("T") >= 0) {

                    Map TrunkMap = new HashMap();
                    TrunkMap.put("expDate", prodInstMap.get("statusDate"));
                    TrunkMap.put("accNbr", accNum);
                    tCount = bTrunkBillingMapperDao.countTrunkBilling(TrunkMap);
                    if (tCount > 0) {
                        bTrunkBillingMapperDao.updateTrunkBilling(TrunkMap);
                        String smsInfo = "JLJF000042|{\"msgInfo\":\"您好！中继号码【" + accNum
                                + "】已拆机，请及时刷新系统参数！\"}";
                        sendMsgToPlca(smsInfo,2);
                        /*SmsInfo smsInfo = new SmsInfo();
                        long seqSmsId = smsInfoMapperDao.getSeq("SMS_ID");
                        smsInfo.setId(seqSmsId);
                        smsInfo.setMsisdn("17390026401");
                        smsInfo.setFlag("SEND");
                        smsInfo.setMsg("您好！中继号码【" + accNum + "】已拆机，请及时刷新系统参数！");
                        smsInfo.setSts("A");
                        smsInfo.setGetDate(new Date());
                        smsInfo.setSendDate(new Date());
                        smsInfo.setPri(0);
                        smsInfoMapperDao.insert(smsInfo);*/

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "失效中继号码失败");
                msg.setResultCode(ResultCode.PRODINST_D_ERROR_008);
                msg.setMessage(exceptionMsg);
                return -1;
            }

            //失效IMSI及号码
            try {
                List<Map<String, Object>> prodInstAccNumList = new ArrayList<Map<String, Object>>();
                prodInstAccNumList = prodinstDao.getProdInstAccNum(prodInstMap);
                for (Map prodInstAccNumMap : prodInstAccNumList) {
                    prodInstAccNumMap.put("expDate", prodInstMap.get("statusDate"));
                    prodInstAccNumMap.put("routeId", routeId);
                    prodInstAccNumMap.put("statusCd", "1100");
                    prodInstAccNumMap.put("action", 2);
                    prodinstDao.updateProdInstAccNum(prodInstAccNumMap);
                    prodInstAccNumMap.put("executetime", prodInstMap.get("executetime"));
                    SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstAccNumMap);

                }
            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "失效IMSI及号码出错");
                msg.setResultCode(ResultCode.PRODINST_D_ERROR_009);
                msg.setMessage(exceptionMsg);
                return -1;
            }
            //失效VPN成员 add by wangbaoqiang
            Map vpnMemMap = new HashMap();
            vpnMemMap.put("accNum", prodInstAccNum);
            vpnMemMap.put("statusDate", prodInstMap.get("statusDate"));
            List<Map<String, Object>> tifVpnGroupList = tifVpnGroupMapperDao.selectTifVpnMemId(vpnMemMap);
            if (tifVpnGroupList.size() == 1) {
                Map map = tifVpnGroupList.get(0);
                map.put("expDate", prodInstMap.get("statusDate"));
                if (tifVpnGroupMapperDao.updateTifVpnMem(map) < 1) {
                    msg.setResultCode(ResultCode.PRODINST_D_ERROR_010);
                    msg.setMessage("失效IVPN出错");
                    return -1;
                }
                map.put("action", 2);
                map.put("vpnType", "1");
               // map.put("routeId", prodInstMap.get("routeId"));
                map.put("executetime", prodInstMap.get("executetime"));
                SynMapContextHolder.addMap("tifVpnMemList1", map);
            }
            //更新prodInst表之前的备份操作
            // 查找旧的 prod_inst getProdInstOBJ
            Map oldProdMap = new HashMap();
            List<Map<String, Object>> oldProdInstList = prodinstDao
                    .getProdInstOBJ(prodInstMap);
            oldProdMap = oldProdInstList.get(0);
            if (hisService.backupProdInst(oldProdMap, userMap, msg) <= 0) {
                msg.setResultCode(ResultCode.PRODINST_D_ERROR_011);
                msg.setMessage("数据备份失败：prodInstId:" + prodInstMap.get("prodInstId"));
                return -1;
            }
            prodinstDao.updateProdInstForDisassembe(prodInstMap);
            SynMapContextHolder.addMap("prodInstobjList1", prodInstMap);
            //判断拆机用户是否是代表用户 add by wangbaoqiang
            List<Map<String, Object>> prodInstAcctIdList = prodinstDao.getProdInstAcctId(prodInstMap);
            if (prodInstAcctIdList.size() != 1) {
                msg.setResultCode(ResultCode.PRODINST_D_ERROR_012);
                msg.setMessage("取用户账务关系时出错【prodInstId】：" + prodInstMap.get("prodInstId"));
                return -1;
            }
            Map prodInstAcctIdMap = prodInstAcctIdList.get(0);
            long acctId = Long.parseLong(prodInstAcctIdMap.get("acctId").toString());
            Map updateMap = new HashMap();
            updateMap.put("acctId", acctId);
            updateMap.put("routeId", acctId);
            updateMap.put("prodInstId", prodInstMap.get("prodInstId"));
            long cnt = accountDao.getCntAccoutFromProdInstId(updateMap);
            if (cnt == 1) {
                try {
                    List<Map<String, Object>> accountMapList = accountDao
                            .getAccoutForAccNum(updateMap);
                    if (accountMapList.size() > 0) {
                        List<Map<String, Object>> prodInstRelMapList = prodinstDao
                                .selectProdInstIdFromAcctRel(updateMap);
                        //prodInstRelMap.put("routeId", routeId);
                        //如果该账户下有多个在用用户则取最小代表号码，如果没有保持不变
                        // List<Map<String, Object>> acctLists = accountDao.getAccoutFromProdInstId(servAcctMap);
                        if (prodInstRelMapList.size() > 0) {
                            Map prodInstRelMap = prodInstRelMapList.get(0);
                            Map accountMap = new HashMap();
                            accountMap = accountMapList.get(0);
                            hisService.insertAccountHis(accountMap);
                            accountMap.put("prodInstId", prodInstRelMap.get("prodInstId"));
                            accountMap.put("action", 2);
                            accountMap.put("remark", "拆机修改账户代表号码");
                            accountDao.updateAccount(accountMap);
                            accountMap.put("executetime", prodInstMap.get("executetime"));
                            SynMapContextHolder.addMap("acctobjList1", accountMap);

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "取帐户最早的号码时出错");
                    msg.setResultCode(ResultCode.PRODINST_D_ERROR_013);
                    msg.setMessage(exceptionMsg);
                    return -1;
                }
            }
        }

        return 1;
    }

    public int updateProdInst(Map itemMap, Map userMap, Message msg,
                              String service_offer_id) throws Exception {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

            List<Map<String, Object>> prodInstList = ordBillDao
                    .selectOrdProdInst(itemMap);
            if (prodInstList.size() == 0) {
                itemMap.put("oper_type", "1300");
                prodInstList = ordBillDao.selectOrdProdInst1300(itemMap);
                if (prodInstList.size() > 1) {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_001);
                    msg.setMessage("取用户接口表operType=1300数据时出错：记录有多条");
                    return -1;
                } else if (prodInstList.size() == 0) {
                    return 1;
                }
            } else if (prodInstList.size() > 1) {
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_002);
                msg.setMessage("取用户接口表operType=1000数据时出错：记录有多条");
                return -1;
            }
            //add by wangbaoqiang  保持状态的只处理补卡动作   begin
            if (!"4040600003".equals(service_offer_id)
                    && !"4040600000".equals(service_offer_id)
                    && !"4060201002".equals(service_offer_id)
                    && !"4070100002".equals(service_offer_id)
                    && "1300".equals(itemMap.get("oper_type"))) {
                //有的时候crm修改用户状态的动作也是1300，真是坑。做以下处理
                List<Map<String,Object>> serviceOfferCheckList = ordBillDao.selectServiceOfferContrast(service_offer_id);
                if (serviceOfferCheckList.size() == 0) {
                    return 1;
                }
            }//end

            String oldPaymode = "";
            String oldAccNum = "";
            String oldState = "";
            String oldRegionId = "";

            String paymode = "";
            String accNum = "";
            String state = "";
            String RegionId = "";
            long prodInstId = 0L;
            String oldAcctId = "";

            String stateDate = "";
            //=======================
            String cdmaAmsi = "";

            String oldStopType = "";
            String oldStateCd = "";
            String oldAccNumId = "";
            String oldAccImsi = "";
            String oldGsmImsi = "";
            String oldLteImsi = "";
            String oldCustId = "";
            String areaCode = "";
            String prodType = "";
            String remark = "";
            String stopType = "";
            String statusCd = "";
            String realNameEffDate1 = "";
            String oldStopTypeEx = "";
            String realNameEffDate2 = "";
            long routeId;
            long archGrpId;
            long orderItemId;
            Map prodInstMap = new HashMap();
            prodInstMap = prodInstList.get(0);
            //判断是否为组合产品
            try {
                List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(prodInstMap);
                if (tifProdList.size() == 1) {
                    Map prodConMap = new HashMap();
                    prodConMap = tifProdList.get(0);
                    if ("10C".equals(prodConMap.get("prodType"))
                            || "10D".equals(prodConMap.get("prodType"))) {
                        return 1;
                    }
                } else {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_003);
                    msg.setMessage("取对应的产品编码时出错");
                    return -1;
                }

            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "取对应的产品编码时出错");
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_004);
                msg.setMessage(exceptionMsg);
                return -1;
            }
            if ("".equals(prodInstMap.get("accNum")) || prodInstMap.get("accNum") == null) {
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_005);
                msg.setMessage("接入号不能为null");
                return -1;
            }
            if ("".equals(prodInstMap.get("paymentModeCd")) || prodInstMap.get("paymentModeCd") == null) {
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_006);
                msg.setMessage("付费方式不能为NULL");
                return -1;
            }

            prodInstMap.put("effDate", prodInstMap.get("statusDate"));
            stateDate = prodInstMap.get("statusDate").toString();// 用来同一更新时间
            prodInstMap.put("expDate", statePublic.expDate);
            if (StringUtil.isEmpty(prodInstMap.get("statusDate"))) {
                prodInstMap.put("executetime", d.format(new Date()));
            } else {
                prodInstMap.put("executetime", d.format(df.parse(prodInstMap
                        .get("statusDate").toString())));
            }


            prodInstId = Long.parseLong(prodInstMap.get("prodInstId")
                    .toString());
            paymode = prodInstMap.get("paymentModeCd").toString();
            accNum = prodInstMap.get("accNum").toString();
            state = prodInstMap.get("statusCd").toString();
            RegionId = prodInstMap.get("regionId").toString();
            cdmaAmsi = (String) prodInstMap.get("cdmaImsi");
            //计费侧预开通状态只有140000
            if("140000".equals(state)||"140001".equals(state)||"140002".equals(state)||"140003".equals(state)){
                state = "140000";
            }
            statusCd = state;
            String orgIdBill;
            archGrpId = Long.parseLong(prodInstMap.get("ARCH_GRP_ID").toString());
            orderItemId = Long.parseLong(prodInstMap.get("ORDER_ITEM_ID").toString());
            routeId = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, prodInstId, msg);
            if (routeId <= 0) {
                if ("4020201001".equals(service_offer_id)) {
                   List<Map<String,Object>> billProdInstList =  smsInfoMapperDao.getBillProdInstInfo(prodInstId);
                    if (billProdInstList.size() == 0) {
                        logger.error("预存机用户在计费不存在【prodInstId】：" + prodInstId);
                        return 1;
                    }
                }
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_007);
                msg.setMessage("取用户的routetId失败【prodInstId】：" + prodInstId);
                return -1;
            }
            prodInstMap.put("routeId", routeId);
            //add by wangbaoqiang 增加固话前面加区号  begin

            long prodId = Long.parseLong(prodInstMap.get("prodId").toString());
            List<Map<String, Object>> regionList = ordBillDao.selectTifOrgContrast(prodInstMap);
            if (regionList.size() > 0) {
                Map regionMap = new HashMap();
                regionMap = regionList.get(0);
                areaCode = regionMap.get("areaCode").toString();
                orgIdBill = regionMap.get("orgIdBill").toString();
            } else {
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_008);
                msg.setMessage("取对应的区号时出错");
                return -1;
            }
            prodInstMap.put("regionId", orgIdBill);
            if (prodId == 2 || prodId == 37 || prodId == 41 || prodId == 9001
                    || prodId == 9003 || prodId == 280000007
                    || prodId == 280000037 || prodId == 280000038
                    || prodId == 280000059 || prodId == 280000088) {

                accNum = areaCode + accNum;

            }//add end
            // long acctId = getAcctId(itemMap,prodInstMap,msg);
            //拆机的用户不处理
            long prodinst2hcCnt = prodinstDao.getForProdInst2hx(prodInstMap);
            if (prodinst2hcCnt == 1) {
                return 1;
            }
            // 查找旧的 prod_inst getProdInstOBJ
            List<Map<String, Object>> oldProdInstList = prodinstDao
                    .getProdInstOBJ(prodInstMap);
            if (oldProdInstList.size() <= 0) {
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_009);
                msg.setMessage("产品实例在计费不存在,不能修改【prodInstId】:" + prodInstId);
                return -1;
            }
            Map oldProdInst = oldProdInstList.get(0);
            oldPaymode = oldProdInst.get("paymentModeCd").toString();
            oldAccNum = oldProdInst.get("accNum").toString();
            oldState = oldProdInst.get("statusCd").toString();
            oldRegionId = oldProdInst.get("regionId").toString();
            //oldAcctId = oldProdInst.get("routeId").toString();
            oldCustId = oldProdInst.get("ownerCustId").toString();
            //prodInstMap.put("routeId", oldAcctId);
            //oldStopType = oldProdInst.get("STOP_TYPE").toString();
            //oldStateCd = oldProdInst.get("STATE").toString();
            //add by wangbaoqiang 按时间排序 begin
            try {
						/*List<Map<String, Object>> getProdInstStateExList = new ArrayList<Map<String,Object>>();
						getProdInstStateExList = prodinstDao.getProdInstState(oldProdInst);
						Collections.sort(getProdInstStateExList, new Comparator<Map<String, Object>>(){

							   public int compare(Map<String, Object> o1, Map<String, Object> o2) {
							    String name1 =(String)o1.get("EXP_DATE");//name1是从你list里面拿出来的一个
							    String name2= (String)o2.get("EXP_DATE"); //name1是从你list里面拿出来的第二个name
							    return name2.compareTo(name1);
						   }

							  });*/

                List<Map<String, Object>> prodInstSateExtList = prodinstDao.getProdInstStateExtFromStateExt(prodInstMap);
                if (prodInstSateExtList.size() == 0) {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_010);
                    msg.setMessage("老的主产品状态为空【prodInstId】：" + prodInstId);
                    return -1;
                } /*else if(prodInstSateExtList.size() > 1) {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_048);
                    msg.setMessage("老的主产品状态为多条：" + prodInstId);
                    return -1;
                }*/
                Map<String, Object> prodInstStateExtFromStateMap = prodInstSateExtList.get(0);
                oldStopType = prodInstStateExtFromStateMap.get("stopType").toString();
                oldStateCd = prodInstStateExtFromStateMap.get("state").toString();
            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "获取老的主产品状态时出错【prodInstId】：" + prodInstId);
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_011);
                msg.setMessage(exceptionMsg);
                return -1;
            }
            //处理计费模式改变
            //System.out.println("prodInstMap:"+prodInstMap.get("paymentModeCd"));
            //System.out.println("oldProdInst:"+oldProdInst.get("PAYMENT_MODE_CD"));
            if (!(prodInstMap.get("paymentModeCd").equals(oldProdInst.get("PAYMENT_MODE_CD")))
                    && ("4041300000").equals(service_offer_id)) {
                List<Map<String, Object>> prodInstPaymodeList = new ArrayList<Map<String, Object>>();
                prodInstPaymodeList = prodinstDao.getProdInstPaymode(prodInstMap);
                if (prodInstPaymodeList.size() <= 0) {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_012);
                    msg.setMessage("取主产品老付费方式时出错");
                    return -1;
                }
                Map<String, Object> prodInstPaymodeMap = new HashMap<String, Object>();
                for (int i = 0; i < prodInstPaymodeList.size(); i++) {
                    prodInstPaymodeMap.put("routeId", routeId);
                    prodInstPaymodeMap.put("statusCd", "1100");
                    prodInstPaymodeMap.put("paymodeId", prodInstPaymodeList.get(i));
                    prodInstPaymodeMap.put("expDate", stateDate);
                    prodInstPaymodeMap.put("statusDate", stateDate);
                    prodInstPaymodeMap.put("prodInstId", prodInstMap.get("prodInstId"));
                    //从计费库获取相应主键,并存入map中
                    prodInstPaymodeMap.put("paymodeId", selectPaymodeId(prodInstPaymodeMap));
							/*if(prodinstDao.updateProdInstPaymode(prodInstPaymodeMap)<=0){
								msg.setMessage("更新计费模式失败");
								return -1;
							}*/
                    SynMapContextHolder.addMap("prodInstPaymodeobjList1", prodInstPaymodeMap);

                }


                long paymodeId = prodinstDao.getSeq("SEQ_PAYMODE_ID");  //统一序列
                prodInstMap.put("paymodeId", paymodeId);
                prodInstMap.put("statusCd", "1000");
                if (prodinstDao.insertProdInstPyamode(prodInstMap) <= 0) {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_013);
                    msg.setMessage("增加主产品付费方式时出错");
                    return -1;
                }
                SynMapContextHolder.addMap("prodInstPaymodeobjList1", prodInstMap);

                //代码：给HB计费预后互转接口表送数据（缺少表B_TOABM_USER_INFO_OPLOG）
                //代码：在后付费转预付费时，帐户的信用度调整为0 （缺少表ACCT_CREDIT）
                //代码：后付费转预付费同时停机（缺少表ACCT.A_REAL_TIME_SERVICE）
                //代码：OCS用户转HB用户（缺少表CCM_USER_ORDER）
                //代码：HB用户转OCS用户（缺少表CCM_USER_ORDER）
            }
            // TODO: 2019/7/11   add by wangbaoqiang 处理用户激活 3.0更新prod_inst表
            if (("4042600000".equals(service_offer_id)||"4070400000".equals(service_offer_id))
                    && ("140000".equals(oldState)
                    || "140001".equals(oldState)
                    || "140002".equals(oldState)
                    || "140003".equals(oldState))
                    &&"100000".equals(state)) {
                //更新起租时间
                Map BeginRentDateMap = new HashMap();
                BeginRentDateMap.put("beginRentDate", prodInstMap.get("statusDate"));
                BeginRentDateMap.put("activateDate", prodInstMap.get("statusDate"));
                BeginRentDateMap.put("createDate", prodInstMap.get("statusDate"));
                BeginRentDateMap.put("remark", "CRM用户激活");
                BeginRentDateMap.put("routeId", routeId);
                BeginRentDateMap.put("prodInstId", prodInstMap.get("prodInstId"));
                remark = "CRM用户激活";
                prodinstDao.updateProdInstBeginRentDate(BeginRentDateMap);
                //修改用户销售品是生效时间
                if (doActiveOfferTime(prodInstMap, msg) < 0) {
                    return -1;
                }
                //老系统激活用户要给账务发送mq，赠款才能够激活
               List<Map<String, Object>> tifNoActUserList =  ordBillDao.selectTifNoActiveUser(prodInstId);
                if (tifNoActUserList.size() > 0) {
                    Map updateUserMap = new HashMap();
                    updateUserMap.put("prodInstId", prodInstId);
                    updateUserMap.put("remark", "END");
                    if (ordBillDao.updateTifNoActiveUse(updateUserMap) < 1) {
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_046);
                        msg.setMessage("更新老系统未激活用户状态失败【prodInstId】：" + prodInstId);
                        return -1;
                    }
                }

            }//
            //修改产权客户
            if ("4040400000".equals(service_offer_id)) {

                prodInstMap.put("routeId", prodInstMap.get("ownerCustId"));
                long customerCount = customerMapperDao.getForCountByOwnerCustId(prodInstMap);
                if (customerCount <= 0) {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_014);
                    msg.setMessage("产品实例对应的产权客户在计费不存在!OWNER_CUST_ID =" + prodInstMap.get("ownerCustId"));
                    return -1;
                }
                prodInstMap.put("routeId", routeId);
                prodinstDao.updateProdInstOwnerCust(prodInstMap);
                // TODO: 2019/6/20  过户插入中间表，给查询详单接口用，不一定用到
                if (!oldCustId.equals(prodInstMap.get("ownerCustId").toString())) {
                    Map custChangeMap = new HashMap();
                    custChangeMap.put("archGrpId", archGrpId);
                    custChangeMap.put("servId", prodInstMap.get("prodInstId"));
                    custChangeMap.put("oldCustId", oldCustId);
                    custChangeMap.put("newCustId", prodInstMap.get("ownerCustId"));
                    custChangeMap.put("changeDate", df.parse(stateDate));
                    long custChange = ordBillDao.inserCustChange(custChangeMap);
                    if (custChange < 0) {
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_015);
                        msg.setMessage("插入intf_serv_cust_change_contrast过户中间表出错");
                        return -1;
                    }
                }

            }
            //修改使用客户
            if ("4040001117".equals(service_offer_id)) {

                prodInstMap.put("routeId", prodInstMap.get("useCustId"));
                long customerCount = customerMapperDao.getForCountByUseCustId(prodInstMap);
                if (customerCount <= 0) {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_016);
                    msg.setMessage("产品实例对应的使用客户在计费不存在!【USE_CUST_ID】：" + prodInstMap.get("useCustId"));
                    return -1;
                }
                prodInstMap.put("routeId", routeId);
                prodinstDao.updateProdInstUserCust(prodInstMap);

            }
            //移机才修改用户地址
            if ("4030100000".equals(service_offer_id)
                    || "4030100001".equals(service_offer_id)
                    || "4030100002".equals(service_offer_id)
                    || "4040001007".equals(service_offer_id)
                    || "4040001054".equals(service_offer_id)
                    || "4040001016".equals(service_offer_id)
                    || "4042300000".equals(service_offer_id)
                    || "4101000000".equals(service_offer_id)
                    || "4010100000".equals(service_offer_id)
                    || "4010200000".equals(service_offer_id)
                    || "4050101022".equals(service_offer_id)
                    || "4050101040".equals(service_offer_id)) {

                prodinstDao.updateProdInstAddress(prodInstMap);

            }
            //
            //从配置表取订单的服务对应的状态
            if ("4060200001".equals(service_offer_id)) {
                remark = "实名制违章单停不修改用户状态";
            }
            if ("4060201003".equals(service_offer_id)) {
                remark = "一证超五卡不合规单停不修改用户状态";
            }
            try {
                if ("4070101004".equals(service_offer_id)) {
                    Map abmMap = new HashMap();
                    long tifseq = bAccu2abmTifDao.getSeq("SEQ_ACCU2ABMTIF");
                    abmMap.put("tifSeq", tifseq);
                    abmMap.put("instanceId", prodInstId);
                    abmMap.put("instanceType", 100000);
                    abmMap.put("insertDate", new Date());
                    //abmMap.put("state", 0);
                    bAccu2abmTifDao.insertBAccu2abmTif(abmMap);
                }
            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "插入计费量本表出错");
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_047);
                msg.setMessage(exceptionMsg);
                return -1;
            }
            List<Map<String,Object>> serviceOfferList = ordBillDao.selectServiceOfferContrast(service_offer_id);
            if (serviceOfferList.size() > 0) {
                Map serviceOfferMap = serviceOfferList.get(0);
                stopType = serviceOfferMap.get("stopType").toString();
                statusCd = serviceOfferMap.get("statusCd").toString();
                remark = serviceOfferMap.get("serviceOfferName").toString();
            } else {
                //4042600000 这个服务ID，对应的激活动作，可能是直接从预开通变成正常，
                // 也有可能是从140002待激活状态，变更为140001预开通，这两个状态对应计费
                // 是一个，处理时不能按照服务id进行判断，只能根据用户表和停机状态表进行判断。
                if ("4042600000".equals(service_offer_id) || "4070400000".equals(service_offer_id)) {//活卡激活
                    Map map = new HashMap();
                    map.put("prodInstId", prodInstMap.get("prodInstId"));
                    map.put("routeId", prodInstMap.get("routeId"));
                    map.put("state", "100000");
                    //如果激活过了，就不处理了
                   List<Map<String,Object>> prodInstStateList =  prodinstDao.selectProdInstStateExt(map);
                    if (prodInstStateList.size() > 0) {
                        stopType = oldStopType;
                        statusCd = oldStateCd;
                        logger.info("用户【prodInstId】：" +  prodInstMap.get("prodInstId") +
                                "，stopType：" + stopType +"，statusCd" + statusCd);
                    }else{
                        stopType = "0";
                        statusCd = state;

                    }

                } else {
                    stopType = oldStopType;
                    statusCd = oldStateCd;
                }
            }
            if (("4040600003".equals(service_offer_id)
                    || "4040600000".equals(service_offer_id))
                    && prodId == 379) {
                if (cdmaAmsi == null || cdmaAmsi.isEmpty()) {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_018);
                    msg.setMessage("CDMA_IMSI号码不能为空,不允许补卡");
                    return -1;
                }
                if (prodinstDao.getForCdmaImsiCount(prodInstMap) > 0) {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_019);
                    msg.setMessage("CDMA_IMSI号码已被占用,不允许补卡");
                    return -1;
                }
                Map<String, Object> prodInstAccNumMap1 = prodinstDao.selectProdInstAccNum(prodInstMap);
                if (prodInstAccNumMap1 == null || prodInstAccNumMap1.isEmpty()) {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_020);
                    msg.setMessage("选取CDMA_IMSI号码时出错");
                    return -1;
                }
                oldAccNumId = prodInstAccNumMap1.get("prodInstAccNumId").toString();
                oldAccImsi = prodInstAccNumMap1.get("accNum").toString();
                //如果IMSI号码没有变,则不更新
                if (!cdmaAmsi.equals(oldAccImsi)) {
                    prodInstMap.put("statusCd", "1100");
                    prodInstMap.put("prodInstAccNumId", oldAccNumId);
                    prodInstMap.put("expDate", prodInstMap.get("statusDate"));
                    if (prodinstDao.updateProdInstAccNum(prodInstMap) <= 0) {
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_021);
                        msg.setMessage("IMSI号码更新失败");
                        return -1;
                    }
                    prodInstMap.put("action", 2);
                    SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);
                    //增加CDMA_IMSI号码
                    try {
                        Long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); // 统一序列
                        prodInstMap.put("prodInstAccNumId", prodInstAccNumId); //需要处理主键prodInstRegionId
                        prodInstMap.put("accNumType", "3000");
                        String cdmaImsi = prodInstMap.get("cdmaImsi").toString();
                        prodInstMap.put("accNum", cdmaImsi);
                        prodInstMap.put("applyRegionId", "1");
                        prodInstMap.put("statusCd", "1000");
                        prodInstMap.put("platId", "1");
                        prodInstMap.put("expDate", statePublic.expDate);
                        prodinstDao.insertProdInstAccNum(prodInstMap);
                        prodInstMap.put("action", 1);
                        SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品CDMA_IMSI号码时出错");
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_022);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                }


                //GSM_IMSI
                // TODO: 2019/7/3 gsm imsi号码类型 孙哥迁移成2000，3.0落的3000，先改为2000，后续可能会改
                if (!"".equals(prodInstMap.get("gsmImsi"))) {
                    long gsmImsiCount = prodinstDao.getForGsmImsiCount2(prodInstMap);
                    if (gsmImsiCount > 0) {
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_023);
                        msg.setMessage("GSM_IMSI号码已被占用,不允许补卡");
                        return -1;
                    }
                }


                Map<String, Object> prodInstAccNumMap2 = prodinstDao.selectProdInstAccNum2(prodInstMap);
                if (prodInstAccNumMap2 != null && !prodInstAccNumMap2.isEmpty()) {
                    oldAccNumId = prodInstAccNumMap2.get("prodInstAccNumId").toString();
                    oldGsmImsi = prodInstAccNumMap2.get("accNum").toString();
                } else{
                    oldAccNumId = null;
                    oldGsmImsi = null;
                }

                    //老旧都有
                    if (!StringUtil.isEmpty(prodInstMap.get("gsmImsi"))
                            && !StringUtil.isEmpty(oldGsmImsi)
                            && !oldGsmImsi.equals(prodInstMap.get("gsmImsi"))) {
                        prodInstMap.put("prodInstAccNumId", oldAccNumId);
                        prodInstMap.put("statusCd", "1100");
                        prodInstMap.put("expDate", prodInstMap.get("statusDate"));
                        if (prodinstDao.updateProdInstAccNum(prodInstMap) <= 0) {
                            msg.setResultCode(ResultCode.PRODINST_U_ERROR_024);
                            msg.setMessage("GSM_IMSI号码更新失败");
                            return -1;
                        }
                        prodInstMap.put("action", 2);
                        SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);
                        try {
                            //增加G网IMSI
                            Long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); //统一序列
                            prodInstMap.put("prodInstAccNumId", prodInstAccNumId);//需要处理主键prodInstRegionId
                            prodInstMap.put("accNumType", "2000");
                            String gsmImsi = prodInstMap.get("gsmImsi").toString();
                            prodInstMap.put("accNum", gsmImsi);
                            prodInstMap.put("applyRegionId", "1");
                            prodInstMap.put("statusCd", "1000");
                            prodInstMap.put("platId", "1");
                            prodInstMap.put("expDate", statePublic.expDate);
                            prodinstDao.insertProdInstAccNum(prodInstMap);
                            prodInstMap.put("action", 1);
                            SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                            String exceptionMsg = "";
                            exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品GSM_IMSI号码时出错");
                            msg.setResultCode(ResultCode.PRODINST_U_ERROR_025);
                            msg.setMessage(exceptionMsg);
                            return -1;
                        }
                    }
                //老无新有
                if (StringUtil.isEmpty(oldGsmImsi) && !StringUtil.isEmpty(prodInstMap.get("gsmImsi"))) {
                    try {
                        Long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); //统一序列
                        prodInstMap.put("prodInstAccNumId", prodInstAccNumId);  //需要处理主键prodInstRegionId
                        prodInstMap.put("accNumType", "2000");
                        String gsmImsi = prodInstMap.get("gsmImsi").toString();
                        prodInstMap.put("accNum", gsmImsi);
                        prodInstMap.put("applyRegionId", "1");
                        prodInstMap.put("statusCd", "1000");
                        prodInstMap.put("platId", "1");
                        prodInstMap.put("expDate", statePublic.expDate);
                        prodinstDao.insertProdInstAccNum(prodInstMap);
                        prodInstMap.put("action", 1);
                        SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品GSM_IMSI号码时出错");
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_026);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                }

                //老有新无
                //System.out.println("测试"+ prodInstMap.get("gsmImsi").equals(""));
                //System.out.println("测试"+ prodInstMap.get("gsmImsi")=="");
                //System.out.println("测试"+ prodInstMap.get("gsmImsi1"));
                if (!StringUtil.isEmpty(oldGsmImsi) && StringUtil.isEmpty(prodInstMap.get("gsmImsi")) ) {
                    prodInstMap.put("prodInstAccNumId", oldAccNumId);
                    prodInstMap.put("statusCd", "1100");
                    prodInstMap.put("expDate", prodInstMap.get("statusDate"));
                    if (prodinstDao.updateProdInstAccNum(prodInstMap) <= 0) {
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_027);
                        msg.setMessage("GSM_IMSI号码更新失败");
                        return -1;
                    }
                    prodInstMap.put("action", 2);
                    SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);
                }

                //LTE_IMSI
                if (!StringUtil.isEmpty(prodInstMap.get("lteImsi"))  ) {
                    long lteImsiCount = prodinstDao.getForLteImsiCount2(prodInstMap);
                    if (lteImsiCount > 0) {
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_028);
                        msg.setMessage("LTE_IMSI号码已被占用,不允许补卡");
                        return -1;
                    }
                }

                Map<String, Object> prodInstAccNumMap3 = prodinstDao.selectProdInstAccNum3(prodInstMap);
                if (prodInstAccNumMap3 != null && !prodInstAccNumMap3.isEmpty()) {
                    oldAccNumId = prodInstAccNumMap3.get("prodInstAccNumId").toString();
                    oldLteImsi = prodInstAccNumMap3.get("accNum").toString();
                } else{
                    oldAccNumId = null;
                    oldLteImsi = null;
                }
                    //老旧都有
                    if (!StringUtil.isEmpty(prodInstMap.get("lteImsi"))
                            && !StringUtil.isEmpty(oldLteImsi)
                            && !oldLteImsi.equals(prodInstMap.get("lteImsi"))) {
                        prodInstMap.put("prodInstAccNumId", oldAccNumId);
                        prodInstMap.put("statusCd", "1100");
                        prodInstMap.put("expDate", prodInstMap.get("statusDate"));
                        if (prodinstDao.updateProdInstAccNum(prodInstMap) <= 0) {
                            msg.setResultCode(ResultCode.PRODINST_U_ERROR_029);
                            msg.setMessage("LTE_IMSI号码更新失败");
                            return -1;
                        }
                        prodInstMap.put("action", 2);
                        SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);

                        //增加4G到IMSI
                        try {
                            Long ProdInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); //统一序列
                            prodInstMap.put("prodInstAccNumId", ProdInstAccNumId); //需要处理主键prodInstRegionId
                            prodInstMap.put("accNumType", "5000");
                            String lteImsi = prodInstMap.get("lteImsi").toString();
                            prodInstMap.put("accNum", lteImsi);
                            prodInstMap.put("applyRegionId", "1");
                            prodInstMap.put("statusCd", "1000");
                            prodInstMap.put("platId", "1");
                            prodInstMap.put("expDate", statePublic.expDate);
                            prodinstDao.insertProdInstAccNum(prodInstMap);
                            prodInstMap.put("action", 1);
                            SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                            String exceptionMsg = "";
                            exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品LTE_IMSI号码时出错");
                            msg.setResultCode(ResultCode.PRODINST_U_ERROR_030);
                            msg.setMessage(exceptionMsg);
                            return -1;
                        }
                    }
                //老无新有  (代码：逻辑问题，如果oldAccImsi为空，则取值时就返回)
                if (StringUtil.isEmpty(oldLteImsi) && !StringUtil.isEmpty(prodInstMap.get("lteImsi"))) {
                    try {
                        Long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); //统一序列
                        prodInstMap.put("prodInstAccNumId", prodInstAccNumId);  //需要处理主键prodInstRegionId
                        prodInstMap.put("accNumType", "5000");
                        String lteImsi = prodInstMap.get("lteImsi").toString();
                        prodInstMap.put("accNum", lteImsi);
                        prodInstMap.put("applyRegionId", "1");
                        prodInstMap.put("statusCd", "1000");
                        prodInstMap.put("platId", "1");
                        prodInstMap.put("expDate", statePublic.expDate);
                        prodinstDao.insertProdInstAccNum(prodInstMap);
                        prodInstMap.put("action", 1);
                        SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品LTE_IMSI号码时出错");
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_031);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                }
                //老有新无
                if (!StringUtil.isEmpty(oldLteImsi) && StringUtil.isEmpty(prodInstMap.get("lteImsi"))) {
                    prodInstMap.put("prodInstAccNumId", oldAccNumId);
                    prodInstMap.put("statusCd", "1100");
                    prodInstMap.put("expDate", prodInstMap.get("statusDate"));
                    if (prodinstDao.updateProdInstAccNum(prodInstMap) <= 0) {
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_032);
                        msg.setMessage("LTE_IMSI号码更新失败");
                        return -1;
                    }
                    prodInstMap.put("action", 2);
                    SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);
                }
            }
            // TODO: 2019/11/14 20:00 修改账户代表号码
            try {
                List<Map<String,Object>> prodInstAcctList =  prodinstDao.getProdInstAcctId(prodInstMap);
                if (prodInstAcctList.size() == 1) {
                    Map map = prodInstAcctList.get(0);
                    Map updateMap = new HashMap();
                    updateMap.put("routeId", map.get("acctId"));
                    updateMap.put("acctId", map.get("acctId"));
                    updateMap.put("prodInstId", prodInstId);
                    updateMap.put("remark", "用户二次业务修改代表号码");
                    List<Map<String,Object>> acctList = accountDao.getAccout(updateMap);
                    if(acctList.size() > 0 ){
                        Map oldAcountMap = acctList.get(0);
                        long prodInstIdTemp = Long.parseLong(StringUtil.isEmpty(oldAcountMap.get("prodInstId"))?"0":String.valueOf(oldAcountMap.get("prodInstId")));
                        if (prodInstIdTemp == 0) {
                            int judge = hisService.insertAccountHis(oldAcountMap);//插入账户的历史表
                            accountDao.updateAccount(updateMap);
                            oldAcountMap.put("executetime", prodInstMap.get("executetime"));
                            oldAcountMap.put("action", 2);
                            oldAcountMap.put("prodInstId", prodInstId);
                            SynMapContextHolder.addMap("acctobjList1", oldAcountMap);
                        }
                    }

                }else{
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_017);
                    msg.setMessage("修改帐户代表号码时出错" + prodInstMap.get("prodInstId"));
                    return -1;
                }
            } catch (Exception e) {
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_045);
                msg.setMessage("修改帐户代表号码时出错" + prodInstMap.get("prodInstId"));
                return -1;
            }
            //号码存放到 PROD_INST_ACC_NUM
          //  if ("4040500000".equals(service_offer_id)) {//改号才修改号码
                //判断设备号码是否重复  begin
                /*try {
                    Long liCount;
                    long netWorkId;
                    Map prodInstNetmMap = new HashMap();
                    liCount = prodinstDao.getForLiCount(prodInstMap);
                    if (liCount > 0) {
                        List<Map<String, Object>> prodNetWorkList = productNetworkMapperDao.selectProdNetWork(prodInstMap);
                        if (prodNetWorkList.size() == 0) {
                            netWorkId = 1;
                        } else if (prodNetWorkList.size() > 1) {
                            msg.setResultCode(ResultCode.PRODINST_U_ERROR_033);
                            msg.setMessage("取网络标识时出错");
                            return -1;
                        } else {
                            Map prodNetmMap = new HashMap();
                            prodNetmMap = prodNetWorkList.get(0);
                            netWorkId = Long.parseLong(prodNetmMap.get("networkId").toString());
                            List<Map<String, Object>> netWorkList = prodinstDao.getForNetWorkId(prodInstMap);
                            for (Map<String, Object> netWorkMap : netWorkList) {
                                if (netWorkId == Long.parseLong(netWorkMap.get("netWorkId").toString())) {
                                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_034);
                                    msg.setMessage("此设备号码已经存在,不允许重复:prodInstId[" + prodInstId + "];accNbr<" + accNum + ">;productId[" + prodId + ']');
                                    return -1;

                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "判断设备号码时出错");
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_035);
                    msg.setMessage(exceptionMsg);
                    return -1;
                }*/
                prodInstMap.put("accNumType", "1000");
                Map<String, Object> prodInstAccNumMap4 = prodinstDao.selectProdInstAccNum4(prodInstMap);
                if (prodInstAccNumMap4 == null || prodInstAccNumMap4.isEmpty()) {
                    msg.setResultCode(ResultCode.PRODINST_U_ERROR_036);
                    msg.setMessage("获取计费主产品号码时出错,PROD_INST_ID=" + prodInstMap.get("prodInstId"));
                    return -1;
                }
                oldAccNumId = prodInstAccNumMap4.get("prodInstAccNumId").toString();
                oldAccImsi = prodInstAccNumMap4.get("accNum").toString();
                //接入变更号处理
                if (!accNum.equals(oldAccImsi)) {
                    prodInstMap.put("prodInstAccNumId", oldAccNumId);
                    prodInstMap.put("statusCd", "1100");
                    prodInstMap.put("expDate", prodInstMap.get("statusDate"));
                    if (prodinstDao.updateProdInstAccNum(prodInstMap) <= 0) {
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_037);
                        msg.setMessage("接入变更号处理更新操作失败");
                        return -1;
                    }
                    prodInstMap.put("action", 2);
                    SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);
                    try {
                        Long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); //统一序列
                        prodInstMap.put("prodInstAccNumId", prodInstAccNumId);  //需要处理主键prodInstRegionId
                        prodInstMap.put("accNumType", "1000");
                        prodInstMap.put("accNum", accNum);
                        prodInstMap.put("applyRegionId", "1");
                        prodInstMap.put("statusCd", "1000");
                        prodInstMap.put("platId", "1");
                        prodInstMap.put("expDate", statePublic.expDate);
                        prodinstDao.insertProdInstAccNum(prodInstMap);
                        prodInstMap.put("action", 1);
                        SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        msg.setResultCode(ResultCode.PRODINST_U_ERROR_038);
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品号码表时出错");
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                    prodinstDao.updateProdInstNumber(accNum, prodInstId, routeId);
                }

           // }
            //================
            // 没空处理失效的prod_inst_region 、退出群组
            //		}
            // prod_inst_region 区域不想等
            //if (!RegionId.equals(oldRegionId)) {
            // 查找region 更新
            Map mapRegion = new HashMap();
            //mapRegion.put("regionId", oldRegionId);
            mapRegion.put("prodInstId", prodInstId);
            mapRegion.put("routeId", routeId);
            List<Map<String, Object>> oldRegionObj = prodinstDao
                    .getProdInstRegion(mapRegion);

            if (oldRegionObj.size() > 0) {
                Map oldRegion = oldRegionObj.get(0);
                if (!orgIdBill.equals(oldRegion.get("regionId").toString())) {
                    oldRegion.put("expDate", prodInstMap.get("statusDate"));
                    oldRegion.put("statusCd", 1100);
                    oldRegion.put("action", 2);
                    oldRegion.put("executetime", prodInstMap.get("executetime"));
                    // oldRegion.put("routeId", oldAcctId);
                    prodinstDao.updateProdInstRegion(oldRegion);
                    SynMapContextHolder.addMap("prodInstRegionobjList1", oldRegion);
                    long prodInstRegionId = prodinstDao.getSeq("SEQ_PROD_INST_REGION_ID");
                    prodInstMap.put("prodInstRegionId", prodInstRegionId); // ////--需要修改读取序列
                    prodInstMap.put("action", 1);
                    prodInstMap.put("statusCd", 1000);
                    prodinstDao.insertProdInstRegion(prodInstMap);
                    SynMapContextHolder.addMap("prodInstRegionobjList1", prodInstMap);
                }
            } else {
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_039);
                msg.setMessage("更新prod_region失败，未找到prodInstId:"
                        + prodInstId);
                return -1;
            }
            //}
            // 号码不想等
/*			List<Map<String, Object>> prodInstAcc1000 = prodinstDao.getProdInstAccNumFor1000(prodInstMap);
			if (prodInstAcc1000.size() != 1) {
				msg.setMessage("获取计费主产品号码时出错 PROD_INST_ID = " + prodInstId);
				return -1;
			}
			Map prodInstAccMap = prodInstAcc1000.get(0);
			oldAccNum = prodInstAccMap.get("accNum").toString();
			if (!oldAccNum.equals("") && !accNum.equals("")
					&& !accNum.equals(oldAccNum)) {
				// 查找旧号码
				if (updateProdInstAccNum(itemMap, userMap, msg, oldAccNum,
						accNum, prodInstId, stateDate, oldAcctId, prodInstMap) < 0) {
					return -1;
				}
			}*/
            // 状态不相等
/*			if (!oldState.equals("") && !state.equals("")
					&& !state.equals(oldState)) */
            if (!oldState.equals("") && !statusCd.equals("")
                    && (!oldState.equals(statusCd)
                    || !stopType.equals(oldStopType))) {
                //add by wangbaoqiang  增加OCS判断  begin
                //if (!"3".equals(paymode) && !"4060300002".equals(service_offer_id)) {
                prodInstMap.put("remark", remark);
                if (updateProdInstState(itemMap, userMap, msg, prodInstId,
                        stateDate, String.valueOf(routeId), prodInstMap, stopType, statusCd) < 0) {
                    return -1;
                }
                //}
                //add by wangbaoqiang 增加OCS强停换挡取消实名制停机的动作 begin
                if ("2100".equals(paymode) && "10000".equals(statusCd) && "180000".equals(stopType)) {

                    Map map = new HashMap();
                    map.put("prodId", 999000020);
                    map.put("accProdInstId", prodInstMap.get("accProdInstId"));
                    List<Map<String, Object>> prodInstSubmMaps = prodinstDao.selectProdInstSubMap(map);
                    if (prodInstSubmMaps.size() > 0) {
                        for (int i = 0; i < prodInstSubmMaps.size(); i++) {
                            Map mapTemp = new HashMap();
                            mapTemp = prodInstSubmMaps.get(i);
                            mapTemp.put("statusCd", 110001);
                            mapTemp.put("updateDate", sdf.format(new Date()));
                            mapTemp.put("stopRentDate", prodInstMap.get("statusDate"));
                            mapTemp.put("action", 2);
                            mapTemp.put("executetime", prodInstMap.get("executetime"));
                            prodinstDao.updateProdInstSub(mapTemp);
                            SynMapContextHolder.addMap("prodInstSubobjList1", mapTemp);
                            mapTemp.put("attrId", 35214005);

                            List<Map<String, Object>> prodInstAttrSubAttrMaps = prodinstDao.selectProdInstAttrSubMap(mapTemp);
                            if (prodInstAttrSubAttrMaps.size() > 0) {
                                for (Map<String, Object> prodInstAttrSubAttrMap : prodInstAttrSubAttrMaps) {
                                    prodInstAttrSubAttrMap.put("expDate", prodInstMap.get("statusDate"));
                                    prodInstAttrSubAttrMap.put("statusCd", prodInstMap.get("1100"));
                                    prodInstAttrSubAttrMap.put("expDate", sdf.format(new Date()));
                                    prodInstAttrSubAttrMap.put("action", 2);
                                    prodInstAttrSubAttrMap.put("executetime", prodInstMap.get("executetime"));
                                    prodinstDao.updateProdInstAttrSub(prodInstAttrSubAttrMap);
                                    SynMapContextHolder.addMap("prodInstSubobjList1", prodInstAttrSubAttrMap);

                                }
                            }

                        }
                    }
                }
                //add end;


            }
            prodInstMap.put("action", 2);
            //更新prodInst表之前的备份操作
            if (hisService.backupProdInst(oldProdInst, userMap, msg) <= 0) {
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_041);
                msg.setMessage("数据备份失败：prodInstId:" + prodInstMap.get("prodInstId"));
                return -1;
            }
            Map upDateProdInstMap = new HashMap();
            //upDateProdInstMap.put("accNum", accNum);
            upDateProdInstMap.put("statusCd", statusCd);
            upDateProdInstMap.put("prodId", prodInstMap.get("prodId"));
            upDateProdInstMap.put("account", prodInstMap.get("account"));
            upDateProdInstMap.put("statusDate", prodInstMap.get("statusDate"));
            //upDateProdInstMap.put("updateDate",prodInstMap.get("updateDate"));
            //upDateProdInstMap.put("remark",prodInstMap.get("remark"));
            upDateProdInstMap.put("grpProdNbr", prodInstMap.get("grpProdNbr"));
            upDateProdInstMap.put("extProdInstId", prodInstMap.get("extProdInstId"));
            upDateProdInstMap.put("prodInstId", prodInstMap.get("prodInstId"));
            upDateProdInstMap.put("routeId", routeId);
            upDateProdInstMap.put("action", 2);
            upDateProdInstMap.put("executetime", prodInstMap.get("executetime"));
            long updateStatus = prodinstDao.updateProdInst(upDateProdInstMap);
            if (updateStatus < 0) {
                msg.setResultCode(ResultCode.PRODINST_U_ERROR_042);
                msg.setMessage("主产品实例已经拆机，不允许修改！");
                return -1;
            }
            SynMapContextHolder.addMap("prodInstobjList1", upDateProdInstMap);

        } catch (Exception e) {
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理ORD_PROD_INST更新失败");
            msg.setResultCode(ResultCode.PRODINST_U_ERROR_043);
            msg.setMessage(exceptionMsg);
            e.printStackTrace();
            return  -1;
        }

        return 1;
    }

    public long getOfferDetailId(Map map, Message msg) throws Exception {
        try {
            // List<Map<String,Object>> offerDetailList =
            // prodOfferDao.getOfferRoleId(map);
            List<Map<String, Object>> offerDetailList = offerinstDao
                    .getOfferRoleId(map);
            if (offerDetailList.size() > 0) {
                return Long.parseLong(offerDetailList.get(0)
                        .get("OFFER_OBJ_REL_ID").toString());
            } else {
                msg.setMessage("获取销售品成员角色异常");
                // throw new Exception();
                return -1;
            }
        } catch (Exception e) {
            msg.setMessage("获取销售品成员角色异常");
            e.printStackTrace();
            throw e;
        }

        // return 1;
    }

    public int updateProdInstState(Map itemMap, Map userMap, Message msg,
                                   long prodInstId, String stateDate, String routeId, Map stateMap, String stopType, String statusCd)
            throws Exception {

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            // 查找ORD最新数据insert

            /*
             * Map mState = itemMap; mState.put("prodInstId", prodInstId);
             * List<Map<String,Object>> newAccNumList =
             * ordBillDao.selectOrdProdInstState(mState); Map newStateObj = new
             * HashMap(); if(newAccNumList.size()>0) { newStateObj =
             * newAccNumList.get(0); newStateObj.put("routeId", routeId);
             * newStateObj.put("effDate", stateDate); newStateObj.put("expDate",
             * statePublic.EXPDATE); newStateObj.put("action",1);
             * prodinstDao.insertProdInstState(newStateObj);
             * prodinstDao.insertProdInstStateExt(newStateObj);
             * prodInstStateobjList1.add(newStateObj);
             *
             * }else{
             * msg.setMessage("更新ProdInstState失败,未找到ord_prod_inst_state:"+
             * prodInstId); return -1; }
             */
            // 查找旧的状态
            Map mapstate = new HashMap();
            mapstate.put("prodInstId", prodInstId);
            mapstate.put("routeId", routeId);

            List<Map<String, Object>> oldStateList = prodinstDao
                    .getProdInstStateIdEx(mapstate);
            if (oldStateList.size() > 0) {
                // 失效旧数据
                Map oldStateMap = oldStateList.get(0);
                oldStateMap.put("expDate", stateMap.get("statusDate"));
                oldStateMap.put("action", 2);
                oldStateMap.put("statusCd", 1100);
                oldStateMap.put("executetime", d.format(df.parse(stateMap.get(
                        "statusDate").toString())));
                oldStateMap.put("prodInstId", prodInstId);
                prodinstDao.updateProdInstState(oldStateMap);
                prodinstDao.updateProdInstStateExt(oldStateMap);
                SynMapContextHolder.addMap("prodInstStateobjList1", oldStateMap);
            }
            // 新增
            Map stateObj = new HashMap();
            long prodInstStateId = prodinstDao.getSeq("SEQ_PROD_INST_STATE_ID");
            stateObj.put("prodInstStateId", prodInstStateId);
            stateObj.put("routeId", routeId);
            stateObj.put("stopType", stopType);
            stateObj.put("state", statusCd);
            stateObj.put("statusCd", 1000);
            stateObj.put("action", 1);
            stateObj.put("effDate", stateMap.get("effDate"));
            stateObj.put("expDate", stateMap.get("expDate"));
            stateObj.put("prodInstId", stateMap.get("prodInstId"));
            stateObj.put("createStaff", stateMap.get("createStaff"));
            stateObj.put("updateStaff", stateMap.get("updateStaff"));
            stateObj.put("createDate", stateMap.get("createDate"));
            stateObj.put("statusDate", stateMap.get("statusDate"));
            stateObj.put("updateDate", stateMap.get("updateDate"));
            stateObj.put("remark", stateMap.get("remark"));
            stateObj.put("executetime",
                    d.format(df.parse(stateObj.get("statusDate").toString())));

            prodinstDao.insertProdInstState(stateObj);
            prodinstDao.insertProdInstStateExt(stateObj);
            SynMapContextHolder.addMap("prodInstStateobjList1", stateObj);

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            msg.setResultCode(ResultCode.PRODINST_U_ERROR_040);
            exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品状态时出错");
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }

    public int insertProdInst(Map itemMap, Map userMap, Message msg)
            throws Exception {
        List<Map<String, Object>> prodInstList = ordBillDao
                .selectOrdProdInst(itemMap);
        //add by wangbaoqiang begin
        if (prodInstList.size() > 1) {
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_001);
            msg.setMessage("取用户接口表数据时出错:记录有多条");
            return -1;
        } else if (prodInstList.size() == 0) {
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_002);
            msg.setMessage("取用户接口表数据时出错:记录为空");
            return -1;
        }//add end;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat dfFormat = new SimpleDateFormat("yyyy/MM/dd");

        String stop_type = "0";
        String state = "";
        long archGrpId;
        long orderItemId;
        long prodInstId;
        long routeId;
        //String use_cust_id;
        // prodInstMap存放prod_inst对象
        Map prodInstMap = new HashMap();
        Map prodInstNetmMap = new HashMap();
        prodInstMap = prodInstList.get(0);
        prodInstNetmMap = prodInstList.get(0);
        //计费只有这一种状态
        state = prodInstMap.get("statusCd").toString();
        if ("140001".equals(state)||"140002".equals(state)||"140003".equals(state)) {
            state="140000";
            prodInstMap.put("statusCd", state);
        }
        archGrpId = Long.parseLong(prodInstMap.get("ARCH_GRP_ID").toString());
        orderItemId = Long.parseLong(prodInstMap.get("ORDER_ITEM_ID").toString());
        prodInstId = Long.parseLong(prodInstMap.get("accProdInstId").toString());
        stop_type = "0";
        if ("0".equals(String.valueOf(prodInstMap.get("useCustId")))) {
            prodInstMap.put("useCustId", null);
        } else {
            routeId = Long.parseLong(prodInstMap.get("useCustId").toString());
            prodInstMap.put("routeId", routeId);
            long num = customerMapperDao.getForCountByUseCustId(prodInstMap);
            if (num == 0) {
                msg.setResultCode(ResultCode.PRODINST_I_ERROR_003);
                msg.setMessage("产品实例对应的使用客户在计费不存在! USE_CUST_ID =" + prodInstMap.get("useCustId"));
                return -1;
            }
        }
        prodInstMap.put("routeId", prodInstMap.get("ownerCustId"));
        long customerCount = customerMapperDao.getForCountByOwnerCustId(prodInstMap);
        if (customerCount <= 0) {
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_004);
            msg.setMessage("产品实例对应的产权客户在计费不存在!OWNER_CUST_ID =" + prodInstMap.get("ownerCustId"));
            return -1;
        }
        //预开户插入虚拟用户
        if (prodInstMap.get("addressDesc") == null) {
            prodInstMap.put("addressDesc", "吉林省");
        }
        if (prodInstMap.get("accNum") == null) {
            msg.setMessage("接入号不能为NULL");
            return -1;
        }
        if (prodInstMap.get("paymentModeCd") == null) {
            msg.setMessage("付费方式不能为NULL");
            return -1;
        }
        if ("2100".equals(String.valueOf(prodInstMap.get("paymentModeCd")))) {
            String smsInfo = "JLJF000042|{\"msgInfo\":\"用户【" + prodInstMap.get("accNum") +
                    "】为ocs用户，请检查！归档组ID【" + archGrpId +  "】\"}";
            sendMsgToPlca(smsInfo,1);
            msg.setMessage("用户" + "【" + prodInstMap.get("accNum")  + "】为OCS用户，请检查");
            return -1;
        }
        List<Map<String, Object>> ordProdInstAcctList = ordBillDao.selectOrdProdInstAcctRel1000(prodInstMap);
        if (ordProdInstAcctList.size() == 0) {
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_005);
            msg.setMessage("新装用户时未送帐务关系接口表:ORD_PROD_INST_ACCT_REL!");
            return -1;
        }
        //加入路由表 begin
        routeId = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, prodInstId, msg);
        if (routeId == -1) {
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_006);
            msg.setMessage("新装取用户的routeId失败【prodInstId】：" + prodInstId);
            return -1;
        }//end
        prodInstMap.put("routeId", routeId);
        //add by wangbaoqiang 增加中继和固话前面加区号  begin
        long prodId = Long.parseLong(prodInstMap.get("prodId").toString());
        long accPordInstId = Long.parseLong(prodInstMap.get("accProdInstId").toString());
        String paymentTypeId = prodInstMap.get("paymentModeCd").toString();
        String trunkNum;
        String accNum = prodInstMap.get("accNum").toString();
        String areaCode;
        String prodType;
        String orgIdBill;
        String lanId;
        long trunkId;
        List<Map<String, Object>> regionList = ordBillDao.selectTifOrgContrast(prodInstMap);
        if (regionList.size() > 0) {
            Map regionMap = new HashMap();
            regionMap = regionList.get(0);
            areaCode = regionMap.get("areaCode").toString();
            orgIdBill = regionMap.get("orgIdBill").toString();
            lanId = areaCode.substring(1, 4);
            trunkId = Long.parseLong(regionMap.get("trunkIdBill").toString());
        } else {
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_007);
            msg.setMessage("取对应的区号时出错");
            return -1;
        }
        prodInstMap.put("lanId",lanId);
        prodInstMap.put("regionId", orgIdBill);
        if (prodId == 2 || prodId == 37 || prodId == 41 || prodId == 9001
                || prodId == 9003 || prodId == 280000007
                || prodId == 280000037 || prodId == 280000038
                || prodId == 280000059 || prodId == 280000088) {

            accNum = areaCode + accNum;
            prodInstMap.put("accNum", accNum);

        }
        if (prodId == 280000035 && accNum.indexOf("T") >= 0) {
            try {
                List<Map<String, Object>> trunkList = ordBillDao.selectOrdProdInstAttrForTrunk(prodInstMap);
                if (trunkList.size() > 0) {
                    Map trunkAttrMap = trunkList.get(0);
                    if ("2".equals(trunkAttrMap.get("attrValue").toString())) {
                        trunkId = 25;
                    } else if ("3".equals(trunkAttrMap.get("attrValue").toString())) {
                        trunkId = 30;
                    }
                }

                Map TrunkMap = new HashMap();
                trunkNum = accNum.substring(accNum.indexOf("T") + 1);
                TrunkMap.put("switchId", trunkId);
                TrunkMap.put("trunkCode", trunkNum);
                TrunkMap.put("trunkSide", 2);
                TrunkMap.put("accNbr", accNum);
                TrunkMap.put("effDate", prodInstMap.get("statusDate"));
                TrunkMap.put("expDate", "3000/01/01");
                TrunkMap.put("remark", prodInstMap.get("useCustName"));
                bTrunkBillingMapperDao.insertTrunkbill(TrunkMap);
                String smsInfo = "JLJF000042|{\"msgInfo\":\"您好！中继号码有新增【" + accNum
                        + "】请及时刷新系统参数！\"}";
                sendMsgToPlca(smsInfo,2);
/*                SmsInfo smsInfo = new SmsInfo();
                long seqSmsId = smsInfoMapperDao.getSeq("SMS_ID");
                smsInfo.setId(seqSmsId);
                smsInfo.setMsisdn("17390026401");
                smsInfo.setFlag("SEND");
                smsInfo.setMsg("您好！中继号码有新增【" + accNum + "】请及时刷新系统参数！");
                smsInfo.setSts("A");
                smsInfo.setGetDate(new Date());
                smsInfo.setSendDate(new Date());
                smsInfo.setPri(0);
                smsInfoMapperDao.insert(smsInfo);*/
            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "中继号码信息生成失败");
                msg.setResultCode(ResultCode.PRODINST_I_ERROR_008);
                msg.setMessage(exceptionMsg);
                return -1;
            }
        }
        if (StringUtil.isEmpty(prodInstMap.get("statusDate"))) {
            prodInstMap.put("executetime", d.format(new Date()));
        }
        prodInstMap.put("executetime", d.format(df.parse(prodInstMap
                .get("statusDate").toString())));
        //判断是否为组合产品
            List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(prodInstMap);
            long prodiInstId = -1;
            if (tifProdList.size() == 1) {
                Map prodConMap = new HashMap();
                prodConMap = tifProdList.get(0);
                if ("10C".equals(prodConMap.get("prodType"))
                        || "10D".equals(prodConMap.get("prodType"))) {

                    try {
                        Map<String, Object> ordProdInstRelMap = ordBillDao.selectOrdProdInstRelFrom1000(prodInstMap);
                        ordProdInstRelMap.put("routeId", routeId);
                        prodiInstId = prodinstDao.selectMinProdInstId(ordProdInstRelMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        msg.setResultCode(ResultCode.PRODINST_I_ERROR_009);
                        exceptionMsg = setErrorMsg(e.getMessage(), "取组合产品的代表用户时出错");
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                    try {
                        prodInstMap.put("accProdInstId", prodiInstId);
                        prodInstMap.put("routeId", routeId);
                        prodinstDao.insertProdInstGroup(prodInstMap);
                        SynMapContextHolder.addMap("prodInstGroupobjList1", prodInstMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        msg.setResultCode(ResultCode.PRODINST_I_ERROR_010);
                        exceptionMsg = setErrorMsg(e.getMessage(), "生成用户群时出错");
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }

                    if (prodConMap.get("prodType").equals("10D")) {
                        //处理VPN
                        try {
                            Map vpnGroupMap = new HashMap();
                            vpnGroupMap.put("vpnCode", prodInstMap.get("prodInstId"));
                            vpnGroupMap.put("vpnName", "VPN用户" + prodInstMap.get("useCustName"));
                            vpnGroupMap.put("effDate", prodInstMap.get("statusDate"));
                            vpnGroupMap.put("expDate", expDateString);
                            vpnGroupMap.put("state", "10A");
                            vpnGroupMap.put("vpnType", "1");
                            vpnGroupMap.put("vpnNumber", accNum);
                            //vpnGroupMap.put("routeId", prodInstMap.get("routeId"));
                            vpnGroupMap.put("action", 1);
                            tifVpnGroupMapperDao.insertTifVpnGroup(vpnGroupMap);
                            vpnGroupMap.put("executetime", prodInstMap.get("executetime"));
                            SynMapContextHolder.addMap("tifVpnGroupList1", vpnGroupMap);
                        } catch (Exception e) {
                            String exceptionMsg = "";
                            exceptionMsg = setErrorMsg(e.getMessage(), "生成VPN用户群时出错");
                            msg.setResultCode(ResultCode.PRODINST_I_ERROR_011);
                            msg.setMessage(exceptionMsg);
                            e.printStackTrace();
                            return -1;
                        }

                    }
                    return 1;
                }
            } else {
                msg.setResultCode(ResultCode.PRODINST_I_ERROR_012);
                msg.setMessage("取对应的产品编码时出错");
                return -1;
            }
        // TODO: 2019/8/25 判断设备号码是否重复 这里有个问题，3.0查询会加路由id，这样的话改判断会有问题，
        // 但是不加路由id，查询会很慢，这个在讨论，先备注 begin
        try {
            Long liCount;
            long netWorkId;
            liCount = prodinstDao.getForLiCount(prodInstMap);
            Map prodNetmMap = new HashMap();
            if (liCount > 0) {
                List<Map<String, Object>> prodNetWorkList = productNetworkMapperDao.selectProdNetWork(prodInstMap);
                if (prodNetWorkList.size() == 0) {
                    netWorkId = 1;
                } else if (prodNetWorkList.size() > 1) {
                    msg.setResultCode(ResultCode.PRODINST_I_ERROR_013);
                    msg.setMessage("取网络标识时出错");
                    return -1;
                } else {
                    prodNetmMap = prodNetWorkList.get(0);
                    netWorkId = Long.parseLong(prodNetmMap.get("networkId").toString());
                }

                List<Map<String, Object>> netWorkList = prodinstDao.getForNetWorkId(prodInstMap);
                for (Map<String, Object> netWorkMap : netWorkList) {
                    if (netWorkId == Long.parseLong(netWorkMap.get("netWorkId").toString())) {
                        msg.setResultCode(ResultCode.PRODINST_I_ERROR_014);
                        msg.setMessage("此设备号码已经存在,不允许重复:prodInstId[" + prodInstId + "];accNbr<" + accNum + ">;PRODUCT[" + prodId + ']');
                        return -1;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_015);
            exceptionMsg = setErrorMsg(e.getMessage(), "判断设备号码时出错");
            msg.setMessage(exceptionMsg);
            return -1;
        }//判断设备号码是否重复  end;

        //add end;
        prodInstMap.put("action", 1);
        prodInstMap.put("effDate", prodInstMap.get("statusDate"));
        prodInstMap.put("expDate", statePublic.expDate);
        // 1新装
        try {
            Map newProdInstMap = new HashMap();
            newProdInstMap.put("grpProdNbr", prodInstMap.get("grpProdNbr"));
            newProdInstMap.put("routeId", prodInstMap.get("routeId"));
            newProdInstMap.put("prodInstId", prodInstMap.get("prodInstId"));
            newProdInstMap.put("prodId", prodInstMap.get("prodId"));
            newProdInstMap.put("accProdInstId", prodInstMap.get("accProdInstId"));
            newProdInstMap.put("prodUseType", prodInstMap.get("prodUseType"));
            newProdInstMap.put("accNum", prodInstMap.get("accNum"));
            newProdInstMap.put("account", prodInstMap.get("account"));
            newProdInstMap.put("paymentModeCd", prodInstMap.get("paymentModeCd"));
            newProdInstMap.put("addressDesc", prodInstMap.get("addressDesc"));
            newProdInstMap.put("ownerCustId", prodInstMap.get("ownerCustId"));
            newProdInstMap.put("prodInstPwd", prodInstMap.get("prodInstPwd"));
            newProdInstMap.put("exchId", prodInstMap.get("exchId"));
            newProdInstMap.put("addressId", prodInstMap.get("addressId"));
            newProdInstMap.put("regionId", prodInstMap.get("regionId"));
            newProdInstMap.put("lanId", prodInstMap.get("lanId"));
            newProdInstMap.put("actDate", prodInstMap.get("actDate"));
            newProdInstMap.put("beginRentDate", prodInstMap.get("beginRentDate"));
            newProdInstMap.put("stopRentDate", prodInstMap.get("stopRentDate"));
            newProdInstMap.put("statusCd", prodInstMap.get("statusCd"));
            newProdInstMap.put("createOrgId", prodInstMap.get("createOrgId"));
            newProdInstMap.put("createStaff", prodInstMap.get("createStaff"));
            newProdInstMap.put("updateStaff", prodInstMap.get("updateStaff"));
            newProdInstMap.put("createDate", prodInstMap.get("createDate"));
            newProdInstMap.put("statusDate", prodInstMap.get("statusDate"));
            newProdInstMap.put("updateDate", prodInstMap.get("updateDate"));
            newProdInstMap.put("firstFinishDate", prodInstMap.get("firstFinishDate"));
            newProdInstMap.put("useCustId", prodInstMap.get("useCustId"));
            newProdInstMap.put("lastOrderItemId", prodInstMap.get("lastOrderItemId"));
            newProdInstMap.put("remark", prodInstMap.get("remark"));
            newProdInstMap.put("pointOwnerId", prodInstMap.get("pointOwnerId"));
            newProdInstMap.put("extProdInstId", prodInstMap.get("extProdInstId"));
            newProdInstMap.put("executetime", prodInstMap.get("executetime"));
            newProdInstMap.put("action", 1);

            //prodInstMap.put("statusCd", 100000);

            prodinstDao.insertProdInst(newProdInstMap);
            SynMapContextHolder.addMap("prodInstobjList1", newProdInstMap);

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_016);
            exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品实例时出错");
            msg.setMessage(exceptionMsg);
            return -1;
        }
        // 2.构造prod_inst_state. prod_inst_state_ext
        try {

            long prodInstStateId = prodinstDao
                    .getSeq("SEQ_PROD_INST_STATE_ID"); // 统一序列
            prodInstMap.put("prodInstStateId", prodInstStateId);

            prodInstMap.put("stopType", stop_type);
            prodInstMap.put("state", prodInstNetmMap.get("statusCd"));
            prodInstMap.put("statusCd", 1000);
            prodinstDao.insertProdInstState(prodInstMap);
            prodinstDao.insertProdInstStateExt(prodInstMap);
            SynMapContextHolder.addMap("prodInstStateobjList1", prodInstMap);

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品状态时出错");
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_017);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        //ocs用户插入通知表
        if ("2100".equals(paymentTypeId)) {
            try {
                Map ccmUserMap = new HashMap();
                ccmUserMap.put("ccmOrderId", prodinstDao.getSeq("SEQ_CCM_ORDER_ID"));
                ccmUserMap.put("servId", prodInstMap.get("prodInstId"));
                ccmUserMap.put("accNbr", accNum);
                ccmUserMap.put("ctrlAttr", 1);
                ccmUserMap.put("effDate", prodInstMap.get("statusDate"));
                ccmUserMap.put("createdDate", prodInstMap.get("firstFinishDate"));
                ccmUserMap.put("flag", 0);
                ccmUserOrderMapperDao.insertCcmUserOrder(ccmUserMap);
            } catch (Exception e) {
                String exceptionMsg = "";
                msg.setResultCode(ResultCode.PRODINST_I_ERROR_018);
                exceptionMsg = setErrorMsg(e.getMessage(), "插入ocs通知表出错");
                msg.setMessage(exceptionMsg);
                e.printStackTrace();
                return -1;
            }


        }
        //2、新增prod_inst_region
        try {
            prodInstMap.put("statusCd", 1000);
            // 2.加入prod_inst_region
            long prodInstRegionId = prodinstDao.getSeq("SEQ_PROD_INST_REGION_ID");// 统一序列
            prodInstMap.put("prodInstRegionId", prodInstRegionId);// 需要处理主键prodInstRegionId
            prodInstMap.put("action", 1);
            //不用集团的地域id了。
            prodInstMap.put("regionId", prodInstMap.get("regionId"));
            prodinstDao.insertProdInstRegion(prodInstMap);
            SynMapContextHolder.addMap("prodInstRegionobjList1", prodInstMap);

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_019);
            exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品局向时出错");
            msg.setMessage(exceptionMsg);
            return -1;
        }
        // 3、加入prod_inst_paymode
        try {

            long paymodeId = prodinstDao.getSeq("SEQ_PAYMODE_ID");
            prodInstMap.put("paymodeId", paymodeId);// 需要处理主键paymodeId
            prodInstMap.put("statusCd", 1000);
            prodinstDao.insertProdInstPyamode(prodInstMap);
            SynMapContextHolder.addMap("prodInstPaymodeobjList1", prodInstMap);
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_020);
            exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品付费方式时出错");
            msg.setMessage(exceptionMsg);
            return -1;
        }


        // 4、add by wangbaoqiang 构造  9999 167属性 只给c网用户订购 begin
        try {
            if (prodId == 379) {
                Map attr99999 = new HashMap();
                long prodInstSubId = prodinstDao.getSeq("SEQ_PROD_INST_SUB_ID"); //统一序列
                attr99999.put("prodInstId", prodInstSubId);
                attr99999.put("prodUseType", 2000);
                attr99999.put("prodId", 99999);
                attr99999.put("accProdInstId", prodInstMap.get("accProdInstId"));
                attr99999.put("accNum", accNum);
                attr99999.put("paymentModeCd", prodInstMap.get("paymentModeCd"));
                attr99999.put("ownerCustId", prodInstMap.get("ownerCustId"));
                attr99999.put("regionId", prodInstMap.get("regionId"));
                attr99999.put("beginRentDate", prodInstMap.get("statusDate"));
                attr99999.put("stopRentDate", prodInstMap.get("stopRentDate"));
                attr99999.put("statusCd", 1000);
                attr99999.put("createOrgId", prodInstMap.get("createOrgId"));
                attr99999.put("createDate", prodInstMap.get("createDate"));
                attr99999.put("statusDate", prodInstMap.get("statusDate"));
                attr99999.put("firstFinishDate", prodInstMap.get("firstFinishDate"));
                attr99999.put("useCustId", prodInstMap.get("useCustId"));
                attr99999.put("updateDate", prodInstMap.get("updateDate"));
                attr99999.put("lanId", prodInstMap.get("lanId"));
                attr99999.put("routeId", routeId);
                attr99999.put("executetime", prodInstMap.get("executetime"));
                attr99999.put("action", 1);
                prodinstDao.insertProdInstSub(attr99999);
                SynMapContextHolder.addMap("prodInstSubobjList1", attr99999);


                Map attr167 = new HashMap();
                long prodInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); //统一序列
                attr167.put("prodInstAttrId", prodInstAttrId); // 需改为调用序列注意修改
                attr167.put("parProdInstAttrId", 1);
                attr167.put("prodInstId", prodInstSubId);
                attr167.put("attrId", 167);
                attr167.put("attrValue", 1);
                attr167.put("statusCd", 1000);
                attr167.put("routeId",
                        routeId);
                attr167.put("action", 1);
                attr167.put("effDate", prodInstMap.get("statusDate"));
                attr167.put("expDate", statePublic.expDate);
                attr167.put("updateDate", prodInstMap.get("updateDate"));
                attr167.put("executetime", prodInstMap.get("executetime"));
                prodinstDao.insertProdInstAttrSub(attr167);
                SynMapContextHolder.addMap("prodInstAttrSubobjList1", attr167);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_021);
            exceptionMsg = setErrorMsg(e.getMessage(), "增加默认附属产品实例【99999】时出错");
            msg.setMessage(exceptionMsg);
            return -1;
        }// add end;


        // 5.构造prod_inst_acc_num
        try {
            //add by wangbaoqiang 增加号码判断 吉林不做号码限制，不同的产品实例可以有相同的号码，同一个产品实例的生命
            // 周期里只能有一个代表号码  begin
				/*Long lCountFlag;
				lCountFlag = prodinstDao.getForCountNumType1000(prodInstMap);
				if (lCountFlag > 0) {
					msg.setMessage("号码表已经存在【AccNbr】：" + prodInstMap.get("accNum")
					+ "【prodInstId】：" + prodInstMap.get("prodInstId"));
					return -1;
				}//add end;*/
            long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); // 统一序列
            prodInstMap.put("prodInstAccNumId", prodInstAccNumId);
            prodInstMap.put("platId", 1);
            prodInstMap.put("accNumType", 1000);
            prodInstMap.put("applyRegionId", 1);
            prodInstMap.put("statusCd", 1000);
            prodInstMap.put("accNumType", 1000);
            prodinstDao.insertProdInstAccNum(prodInstMap);
            SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);

        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            msg.setResultCode(ResultCode.PRODINST_I_ERROR_022);
            exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品号码表时出错");
            msg.setMessage(exceptionMsg);
            return -1;
        }
        //代码：新装OCS用户，插入用户变更工具
        if ("2100".equals(prodInstMap.get("paymentModeCd"))) {
        }

        //手机增加IMSI属性
        //代码：校验手机的CDMA_IMSI号不允许为空！

        // add by wangbaoqiang 检查CDMA_IMSI号码是否为空
        Long imsiCount;
        long prodInstAccNumId;
        if (prodId == 379) {
            if ("".equals(prodInstMap.get("cdmaImsi"))
                    || prodInstMap.get("cdmaImsi") == null) {
                msg.setResultCode(ResultCode.PRODINST_I_ERROR_023);
                msg.setMessage("手机的CDMA_IMSI号不允许为空！");
                return -1;
            }//add end
            imsiCount = prodinstDao.getForImsiCount(prodInstMap);
            if (imsiCount > 0) {
                msg.setMessage("CDMA_IMSI号码已经存在,不允许重复");
                return -1;
            }
            //增加IMSI号码
            try {
                prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID");//统一序列
                prodInstMap.put("prodInstAccNumId", prodInstAccNumId);
                prodInstMap.put("accNumType", 3000);
                prodInstMap.put("applyRegionId", 1);
                prodInstMap.put("statusCd", 1000);
                prodInstMap.put("platId", 1);
                prodInstMap.put("accNum", prodInstMap.get("cdmaImsi"));
                prodinstDao.insertProdInstAccNum(prodInstMap);
                SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);

            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                msg.setResultCode(ResultCode.PRODINST_I_ERROR_024);
                exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品CDMA_IMSI号码时出错");
                msg.setMessage(exceptionMsg);
                return -1;
            }
        }

        //GSM_IMSI号码
        Long gsmImsiCount;
        if (!"".equals(prodInstMap.get("gsmImsi"))
                && prodInstMap.get("gsmImsi") != null) {
            gsmImsiCount = prodinstDao.getForGsmImsiCount(prodInstMap);
            if (gsmImsiCount > 0) {
                msg.setResultCode(ResultCode.PRODINST_I_ERROR_025);
                msg.setMessage("GSM_IMSI号码已经存在,不允许重复");
                return -1;
            }

            try {
                prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID");  //统一序列
                prodInstMap.put("prodInstAccNumId", prodInstAccNumId);
                prodInstMap.put("accNumType", 2000);
                prodInstMap.put("applyRegionId", 1);
                prodInstMap.put("statusCd", 1000);
                prodInstMap.put("platId", 1);
                prodInstMap.put("accNum", prodInstMap.get("gsmImsi"));
                prodinstDao.insertProdInstAccNum(prodInstMap);
                SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);

            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品GSM_IMSI号码时出错");
                msg.setResultCode(ResultCode.PRODINST_I_ERROR_026);
                msg.setMessage(exceptionMsg);
                return -1;
            }
        }

        //LTE_IMSI
        Long lteImsiCount;
        if (!"".equals(prodInstMap.get("lteImsi"))
                && prodInstMap.get("lteImsi") != null) {
            lteImsiCount = prodinstDao.getForLteImsiCount(prodInstMap);
            if (lteImsiCount > 0) {
                msg.setResultCode(ResultCode.PRODINST_I_ERROR_027);
                msg.setMessage("LTE_IMSI号码已经存在,不允许重复");
                return -1;
            }
            //增加LTE_IMSI号码
            try {
                prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID");  //统一序列
                prodInstMap.put("prodInstAccNumId", prodInstAccNumId);
                prodInstMap.put("accNumType", 5000);
                prodInstMap.put("applyRegionId", 1);
                prodInstMap.put("statusCd", 1000);
                prodInstMap.put("platId", 1);
                prodInstMap.put("accNum", prodInstMap.get("lteImsi"));
                prodinstDao.insertProdInstAccNum(prodInstMap);
                SynMapContextHolder.addMap("prodInstAccNumobjList1", prodInstMap);

            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "增加主产品LTE_IMSI号码时出错");
                msg.setResultCode(ResultCode.PRODINST_I_ERROR_028);
                msg.setMessage(exceptionMsg);
                return -1;
            }
        }
        return 1;
    }

    // 获取需要的 acct_id
    public long getAcctId(Map itemMap, Map objMap, Message msg)
            throws Exception {
        Long acctId = 0L;
        try {
            // 1.serv_acct查找
            List<Map<String, Object>> prodInstAcct = prodinstDao
                    .getProdInstAcctId(objMap);
            if (prodInstAcct.size() >= 1) {
                Map acctIdMap = prodInstAcct.get(0);
                acctId = Long.parseLong(acctIdMap.get("acctId").toString());
            } else {
                // 2.ord_account查找
                acctId = ordBillDao.getOrdAccountById(itemMap);// ORDER_ITEM_ID
                // 条件是否要去掉，看后期数据
                if (acctId == null || acctId == 0) {
                    // 3.ord_prod_inst_acct_rel查找
                    List<Map<String, Object>> acctMap = ordBillDao
                            .getOrdAccountByAcctRel(itemMap);
                    // acctId=ordBillDao.getOrdAccountByAcctRel(itemMap);
                    if (acctMap.size() > 0) {
                        Map mAcct = acctMap.get(0);
                        acctId = Long
                                .parseLong(mAcct.get("acct_id").toString());
                    } else {
                        msg.setMessage("处理ORD_PROD_INST新装获取acctId失败");
                        return 0;
                    }
                    // if(acctId==null||acctId==0)
                    // {
                    // msg.setMessage("处理ORD_PROD_INST新装获取acctId失败");
                    // return 0;
                    // }
                }
            }

        } catch (Exception e) {
            msg.setMessage("处理ORD_PROD_INST新装获取accty_id失败");
            e.printStackTrace();
            throw e;
        }

        return acctId;
    }

    public long getCustId(Map itemMap, long prodInstId, Message msg)
            throws Exception {

        Long custId = 0L;
        try {
            // 1.ord_prod_inst 查找
            Map prodMap = new HashMap();
            long routeId;
            prodMap.put("ARCH_GRP_ID", itemMap.get("ARCH_GRP_ID"));
            prodMap.put("prodInstId", prodInstId);
            routeId = routeMapperDao.getProdInstRoute(prodInstId);
            if (routeId < 0) {
                msg.setMessage("获取routeId失败");
                return -1;
            }
            prodMap.put("routeId", routeId);
            List<Map<String, Object>> prodInst1 = ordBillDao
                    .selectOrdProdInstByRoute(prodMap);
            if (prodInst1.size() >= 1) {
                Map acctIdMap = prodInst1.get(0);
                custId = Long
                        .parseLong(acctIdMap.get("ownerCustId").toString());
            } else {
                // 2.prod_inst查找
                List<Map<String, Object>> prodInst2 = prodinstDao
                        .getProdInstOBJ(prodMap);// ORDER_ITEM_ID
                // 条件是否要去掉，看后期数据
                if (prodInst2.size() >= 1) {
                    Map acctIdMap1 = prodInst2.get(0);
                    custId = Long.parseLong(acctIdMap1.get("OWNER_CUST_ID")
                            .toString());
                }

            }

        } catch (Exception e) {
            msg.setMessage("获取cust_id失败");
            e.printStackTrace();
            throw e;
        }

        return custId;
    }

    /*更新总控表状态*/
    public int tranManager(Long id, String notes) throws Exception {

        WebApplicationContext contextLoader = ContextLoader
                .getCurrentWebApplicationContext();
        DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader
                .getBean("transactionManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
        int i = -1;
        try {
            // 逻辑代码
            Map map = new HashMap();
            map.put("procFlag", 1);
            // map.put("procCnt",1);
            map.put("archGrpId", id);
            map.put("notes", notes);
            i = ordBillDao.updateOrdBill(map);
            transactionManager.commit(status);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
            // transactionManager.rollback(status);
        }
        return i;
    }

    /*更新总控表状态*/
    public int updateOrdBill(Long id, int procFlag, String notes) throws Exception {

        int i = -1;
        try {
            // 逻辑代码
            Map map = new HashMap();
//			map.put("procFlag", 1);
            map.put("procFlag", procFlag);
            //map.put("procCnt","");
            map.put("archGrpId", id);
            map.put("notes", notes);
            map.put("procDate", sdf.format(new Date()));
            i = ordBillDao.updateOrdBill(map);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
            // transactionManager.rollback(status);
        }
        return i;
    }

    public void OneItemtranManager(Long archGrpId, String notes)
            throws Exception {

        WebApplicationContext contextLoader = ContextLoader
                .getCurrentWebApplicationContext();
        DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader
                .getBean("transactionManager");
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

        try {
            // 逻辑代码
            Map map = new HashMap();
            map.put("archGrpId", archGrpId);
            map.put("remarks", notes);
            map.put("procFlag", 1);

            ordBillDao.updateOneItemResult(map);
            transactionManager.commit(status);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
            // transactionManager.rollback(status);

        }

    }

    //	  ----=======参与人相关=========-----
    public String insertParty(long V_ARCH_GRP_ID, long V_ORDER_ITEM_ID, long V_SERVICE_OFFER_ID, Message msg) throws Exception {
        try {
            Map queryord_partyMap = new HashMap();
            queryord_partyMap.put("arch_grp_id", V_ARCH_GRP_ID);
            queryord_partyMap.put("order_item_id", V_ORDER_ITEM_ID);
            queryord_partyMap.put("oper_type", 1000);

            Map ord_partyMap = new HashMap();
            String partyId = "";
            try {
                List<Map<String, Object>> ord_partyList = ordBillDao.getOrdParty(queryord_partyMap);
                if (ord_partyList.size() == 0) {
                    queryord_partyMap.put("oper_type", 1300);
                    ord_partyList = ordBillDao.getOrdParty(queryord_partyMap);
                    if (ord_partyList.size() == 0) {
                        return "1";
                    } else if (ord_partyList.size() > 1) {
                        msg.setResultCode(ResultCode.PARTY_ERROR_001);
                        msg.setMessage("取参与人接口表数据时出错：记录数有多条");
                        return "-1";
                    }
                }//add by wangbaoqiang begin
                else if (ord_partyList.size() > 1) {
                    msg.setResultCode(ResultCode.PARTY_ERROR_002);
                    msg.setMessage("取参与人接口表数据时出错：记录数有多条");
                    return "-1";
                }// add end;
                ord_partyMap = ord_partyList.get(0);

                partyId = ord_partyMap.get("partyId").toString();

            } catch (Exception e) {
                e.printStackTrace();
                String exceptionMsg = "";
                exceptionMsg = setErrorMsg(e.getMessage(), "取参与人接口表数据时出错");
                msg.setResultCode(ResultCode.PARTY_ERROR_003);
                msg.setMessage(exceptionMsg);
                return "-1";
            }
//  long V_PARTY_ID=Long.parseLong(ord_partyMap.get("partyId").toString());

            System.out.println(ord_partyMap.get("partyId").toString());
            ord_partyMap.put("routeId", ord_partyMap.get("partyId"));
            long V_COUNT = accountDao.getCountPartyById(ord_partyMap);
            System.out.println(V_COUNT);
            if (V_COUNT == 0) {
                //新增参与人
                ord_partyMap.put("creditId", 0);
                ord_partyMap.put("creditLimitId", 0);
                ord_partyMap.put("serviceGradeId", 0);
                ord_partyMap.put("partyNbr", "0");
                ord_partyMap.put("statusCd", 1000);
                try {
                    accountDao.insertParty(ord_partyMap);
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "新增参与人时出错");
                    msg.setResultCode(ResultCode.PARTY_ERROR_004);
                    msg.setMessage(exceptionMsg);
                    return "-1";
                    //return "新增参与人时出错";

                }
            } else {
    //  		修改参与人
                try {
                    //ord_partyMap.put("statusCd", 1100);
                    accountDao.updateParty1(ord_partyMap);//更新哪些字段xml里有控制
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "修改参与人时出错");
                    msg.setResultCode(ResultCode.PARTY_ERROR_005);
                    msg.setMessage(exceptionMsg);
                    return "-1";
                    //return "修改参与人时出错";

                }
            }
//  --2.个人域
            Map queryord_party_ind = new HashMap();
            queryord_party_ind.put("oper_type", 1000);
            queryord_party_ind.put("order_item_id", V_ORDER_ITEM_ID);
            queryord_party_ind.put("arch_grp_id", V_ARCH_GRP_ID);

            List<Map<String, Object>> ord_party_indList = ordBillDao.getOrdPartyInd(queryord_party_ind);

            for (int i = 0; i < ord_party_indList.size(); i++) {
                int ord_party_indCount = ordBillDao.getCountOrdPartyInd(queryord_party_ind);
                Map ord_party_indMap = ord_party_indList.get(i);
                ord_party_indMap.put("routeId", ord_party_indMap.get("partyId"));
                if (ord_party_indCount == 1) {
                    ord_party_indMap.put("statusCd", 1000);
                    try {
                        accountDao.insertPartyInd(ord_party_indMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加参与人_个人出错");
                        msg.setResultCode(ResultCode.PARTY_ERROR_006);
                        msg.setMessage(exceptionMsg);
                        return "-1";
                    }
                } else {
                    Map ord_party_indMapUpdate = new HashMap();
                    ord_party_indMapUpdate.put("statusCd", 1100);
                    ord_party_indMapUpdate.put("statusDate", ord_party_indMap.get("statusDate"));
                    ord_party_indMapUpdate.put("updateStaff", ord_party_indMap.get("updateStaff"));
                    ord_party_indMapUpdate.put("updateDate", ord_party_indMap.get("updateDate"));
                    ord_party_indMapUpdate.put("remark", ord_party_indMap.get("remark"));
                    ord_party_indMapUpdate.put("individualId", ord_party_indMap.get("individualId"));//更新条件

                    try {
                        accountDao.updatePartyInd(ord_party_indMapUpdate);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "修改参与人_个人出错");
                        msg.setResultCode(ResultCode.PARTY_ERROR_007);
                        msg.setMessage(exceptionMsg);
                        return "-1";
                    }
                }
            }
//  ---3,参与人角色
            Map queryord_party_roleMap = new HashMap();
            queryord_party_roleMap.put("arch_grp_id", V_ARCH_GRP_ID);
            queryord_party_roleMap.put("order_item_id", V_ORDER_ITEM_ID);
            queryord_party_roleMap.put("oper_type", 1000);
            List<Map<String, Object>> ord_party_roleList = ordBillDao.getOrdPartyRole(queryord_party_roleMap);

            for (int i = 0; i < ord_party_roleList.size(); i++) {
                Map ord_party_roleMap = ord_party_roleList.get(i);
                queryord_party_roleMap.put("party_role_id", ord_party_roleMap.get("partyRoleId"));
                int ord_party_roleCount = ordBillDao.getCountOrdPartyRole(queryord_party_roleMap);
                if (ord_party_roleCount == 1) {
                    Map ord_party_roleMapIn = new HashMap();
                    ord_party_roleMapIn.put("partyRoleId", ord_party_roleMap.get("partyRoleId"));
                    ord_party_roleMapIn.put("partyId", ord_party_roleMap.get("partyId"));
                    ord_party_roleMapIn.put("roleType", ord_party_roleMap.get("roleType"));
                    ord_party_roleMapIn.put("statusDate", ord_party_roleMap.get("statusDate"));
                    ord_party_roleMapIn.put("createStaff", ord_party_roleMap.get("createStaff"));
                    ord_party_roleMapIn.put("updateStaff", ord_party_roleMap.get("updateStaff"));
                    ord_party_roleMapIn.put("createDate", ord_party_roleMap.get("createDate"));
                    ord_party_roleMapIn.put("updateDate", ord_party_roleMap.get("updateDate"));
                    ord_party_roleMapIn.put("remark", ord_party_roleMap.get("remark"));
                    ord_party_roleMapIn.put("statusCd", 1000);
                    ord_party_roleMapIn.put("routeId", ord_party_roleMap.get("partyId"));
                    ord_party_roleMapIn.put("partyRoleType", "1");
                    try {
                        accountDao.insertPartyRole(ord_party_roleMapIn);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加参与人角色出错");
                        msg.setResultCode(ResultCode.PARTY_ERROR_008);
                        msg.setMessage(exceptionMsg);
                        return "-1";
                    }
                } else {
                    Map ord_party_roleMapUpdate = new HashMap();
                    ord_party_roleMapUpdate.put("statusCd", 1100);
                    ord_party_roleMapUpdate.put("statusDate", ord_party_roleMap.get("statusDate"));
                    ord_party_roleMapUpdate.put("updateStaff", ord_party_roleMap.get("updateStaff"));
                    ord_party_roleMapUpdate.put("updateDate", ord_party_roleMap.get("updateDate"));
                    ord_party_roleMapUpdate.put("remark", ord_party_roleMap.get("remark"));
                    ord_party_roleMapUpdate.put("partyRoleId", ord_party_roleMap.get("partyRoleId"));//更新条件
                    ord_party_roleMapUpdate.put("routeId", ord_party_roleMap.get("partyId"));

                    try {
                        accountDao.updatePartyRole(ord_party_roleMapUpdate);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "修改参与人角色出错");
                        msg.setResultCode(ResultCode.PARTY_ERROR_009);
                        msg.setMessage(exceptionMsg);
                        return "-1";
                    }
                }
            }
//  ----4,参与人属性 ORD_PARTY@DBLINK_CRM8153OTHPDB_ATTR
            Map queryord_party_attrMap = new HashMap();

            queryord_party_attrMap.put("arch_grp_id", V_ARCH_GRP_ID);
            queryord_party_attrMap.put("order_item_id", V_ORDER_ITEM_ID);
            queryord_party_attrMap.put("oper_type", 1000);
            List<Map<String, Object>> ord_party_attrList = ordBillDao.getOrdPartyAttr(queryord_party_attrMap);
            for (int i = 0; i < ord_party_attrList.size(); i++) {
                Map ord_party_attrMap = ord_party_attrList.get(i);

                ord_party_attrMap.put("routeId", ord_party_attrMap.get("partyId"));
                queryord_party_attrMap.put("party_attr_id", ord_party_attrMap.get("partyAttrId"));
                int ord_party_attrCount = ordBillDao.getCountOrdPartyAttr(queryord_party_attrMap);
                if (ord_party_attrCount == 1) {
                    ord_party_attrMap.put("statusCd", 1000);
                    try {
                        accountDao.insertPartyAttr(ord_party_attrMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加参与人角色出错");
                        msg.setResultCode(ResultCode.PARTY_ERROR_010);
                        msg.setMessage(exceptionMsg);
                        return "-1";
                    }
                } else {
                    Map ord_party_attrMapUpdate = new HashMap();
                    ord_party_attrMapUpdate.put("statusCd", 1100);
                    ord_party_attrMapUpdate.put("statusDate", ord_party_attrMap.get("statusDate"));
                    ord_party_attrMapUpdate.put("updateStaff", ord_party_attrMap.get("updateStaff"));
                    ord_party_attrMapUpdate.put("updateDate", ord_party_attrMap.get("updateDate"));
                    ord_party_attrMapUpdate.put("remark", ord_party_attrMap.get("remark"));
                    ord_party_attrMapUpdate.put("partyAttrId", ord_party_attrMap.get("partyAttrId"));//条件
                    ord_party_attrMapUpdate.put("routeId", ord_party_attrMap.get("partyId"));

                    try {
                        accountDao.updatePartyAttr(ord_party_attrMapUpdate);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加参与人角色出错");
                        msg.setResultCode(ResultCode.PARTY_ERROR_011);
                        msg.setMessage(exceptionMsg);
                        return "-1";
                    }
                }
            }
//  ord_contacts_info,contacts_info
//  ---5、参与人联系信息  CONTACTS_INFO
            Map queryord_contacts_infoMap = new HashMap();
            queryord_contacts_infoMap.put("arch_grp_id", V_ARCH_GRP_ID);
            queryord_contacts_infoMap.put("order_item_id", V_ORDER_ITEM_ID);
            queryord_contacts_infoMap.put("oper_type", 1000);
            List<Map<String, Object>> ord_contacts_infoList = ordBillDao.getOrdContactsInfo(queryord_contacts_infoMap);
            for (int i = 0; i < ord_contacts_infoList.size(); i++) {
                Map ord_contacts_infoMap = ord_contacts_infoList.get(i);
                ord_contacts_infoMap.put("routeId", ord_contacts_infoMap.get("partyId"));
                queryord_contacts_infoMap.put("contact_id", ord_contacts_infoMap.get("contactId"));
                int ord_contacts_infoCount = ordBillDao.getCountOrdContactsInfo(queryord_contacts_infoMap);
                if (ord_contacts_infoCount == 1) {
                    ord_contacts_infoMap.put("statusCd", 1000);
                    try {
                        accountDao.insertContactsInfo(ord_contacts_infoMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加联系信息出错");
                        msg.setResultCode(ResultCode.PARTY_ERROR_012);
                        msg.setMessage(exceptionMsg);
                        return "-1";
                    }
                } else {
                    Map ord_contacts_infoMapUpdate = new HashMap();
                    ord_contacts_infoMapUpdate.put("statusCd", 1100);//修改标识

                    ord_contacts_infoMapUpdate.put("contactType", ord_contacts_infoMap.get("contactType"));
                    ord_contacts_infoMapUpdate.put("contactName", ord_contacts_infoMap.get("contactName"));
                    ord_contacts_infoMapUpdate.put("contactGender", ord_contacts_infoMap.get("contactGender"));
                    ord_contacts_infoMapUpdate.put("contactAddr", ord_contacts_infoMap.get("contactAddr"));
                    ord_contacts_infoMapUpdate.put("contactEmployer", ord_contacts_infoMap.get("contactEmployer"));
                    ord_contacts_infoMapUpdate.put("homePhone", ord_contacts_infoMap.get("homePhone"));
                    ord_contacts_infoMapUpdate.put("officePhone", ord_contacts_infoMap.get("officePhone"));
                    ord_contacts_infoMapUpdate.put("mobilePhone", ord_contacts_infoMap.get("mobilePhone"));
                    ord_contacts_infoMapUpdate.put("contactDesc", ord_contacts_infoMap.get("contactDesc"));
                    ord_contacts_infoMapUpdate.put("eMail", ord_contacts_infoMap.get("eMail"));
                    ord_contacts_infoMapUpdate.put("postcode", ord_contacts_infoMap.get("postcode"));
                    ord_contacts_infoMapUpdate.put("postAddr", ord_contacts_infoMap.get("postAddr"));
                    ord_contacts_infoMapUpdate.put("fax", ord_contacts_infoMap.get("fax"));
                    ord_contacts_infoMapUpdate.put("statusDate", ord_contacts_infoMap.get("statusDate"));
                    ord_contacts_infoMapUpdate.put("updateStaff", ord_contacts_infoMap.get("updateStaff"));
                    ord_contacts_infoMapUpdate.put("updateDate", ord_contacts_infoMap.get("updateDate"));
                    ord_contacts_infoMapUpdate.put("remark", ord_contacts_infoMap.get("remark"));
                    ord_contacts_infoMapUpdate.put("routeId", partyId);

                    ord_contacts_infoMapUpdate.put("contactId", ord_contacts_infoMap.get("contactId"));//更新条件
                    try {
                        accountDao.updateContactsInfo(ord_contacts_infoMapUpdate);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "修改联系信息出错");
                        msg.setResultCode(ResultCode.PARTY_ERROR_013);
                        msg.setMessage(exceptionMsg);
                        return "-1";
                    }
                }
            }
//  ord_contacts_info_attr,contacts_info_attr
//  ---6,参与人联系信息属性 CONTACTS_INFO_ATTR
            Map queryord_contacts_info_attrMap = new HashMap();
            queryord_contacts_info_attrMap.put("arch_grp_id", V_ARCH_GRP_ID);
            queryord_contacts_info_attrMap.put("order_item_id", V_ORDER_ITEM_ID);
            queryord_contacts_info_attrMap.put("oper_type", 1000);
            List<Map<String, Object>> ord_contacts_info_attrList = ordBillDao.getOrdContactsInfoAttr(queryord_contacts_info_attrMap);
            for (int i = 0; i < ord_contacts_info_attrList.size(); i++) {
                Map ord_contacts_info_attrMap = ord_contacts_info_attrList.get(i);
                ord_contacts_info_attrMap.put("routeId", partyId);
                queryord_contacts_info_attrMap.put("connect_attr_id", ord_contacts_info_attrMap.get("connectAttrId"));
                int ord_contacts_info_attrCount = ordBillDao.getCountOrdContactsInfoAttr(queryord_contacts_info_attrMap);
                if (ord_contacts_info_attrCount == 1) {
                    ord_contacts_info_attrMap.put("statusCd", 1000);
                    try {
                        accountDao.insertContactsInfoAttr(ord_contacts_info_attrMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加联系信息属性出错");
                        msg.setResultCode(ResultCode.PARTY_ERROR_014);
                        msg.setMessage(exceptionMsg);
                        return "-1";
                    }
                } else {
                    Map contacts_info_attrUpdate = new HashMap();
                    contacts_info_attrUpdate.put("statusCd", 1100);//修改标识

                    contacts_info_attrUpdate.put("statusDate", ord_contacts_info_attrMap.get("statusDate"));
                    contacts_info_attrUpdate.put("updateStaff", ord_contacts_info_attrMap.get("updateStaff"));
                    contacts_info_attrUpdate.put("updateDate", ord_contacts_info_attrMap.get("updateDate"));
                    contacts_info_attrUpdate.put("remark", ord_contacts_info_attrMap.get("remark"));
                    contacts_info_attrUpdate.put("routeId", partyId);
                    contacts_info_attrUpdate.put("connectAttrId", ord_contacts_info_attrMap.get("connectAttrId"));//修改条件

                    try {
                        accountDao.updateContactsInfoAttr(contacts_info_attrUpdate);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "修改联系信息属性出错");
                        msg.setResultCode(ResultCode.PARTY_ERROR_015);
                        msg.setMessage(exceptionMsg);
                        return "-1";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理ord_party出错");
            msg.setResultCode(ResultCode.PARTY_ERROR_016);
            msg.setMessage(exceptionMsg);
            return "-1";

        }
        return "1";
        //CrmUserTable
        //	accountDao
    }

    //----=======一般纳税相关=========-----
    public String insertTaxPayer(long V_ARCH_GRP_ID, long V_ORDER_ITEM_ID, Message msg) throws Exception {
//	1.ord_tax_payer,tax_payer
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            Map queryord_tax_payerMap = new HashMap();
            queryord_tax_payerMap.put("arch_grp_id", V_ARCH_GRP_ID);
            queryord_tax_payerMap.put("order_item_id", V_ORDER_ITEM_ID);
            queryord_tax_payerMap.put("oper_type", 1000);
            List<Map<String, Object>> ord_tax_payerList = new ArrayList();
            try {
                ord_tax_payerList = ordBillDao.getOrdTaxPayer(queryord_tax_payerMap);
            } catch (Exception e) {
                e.printStackTrace();
                msg.setResultCode(ResultCode.TAX_ERROR_001);
                msg.setMessage("取一般纳税人接口表数据时出错");
                throw e;
            }
            if (ord_tax_payerList.size() != 1) {
                msg.setResultCode(ResultCode.TAX_ERROR_002);
                msg.setMessage("取一般纳税人接口表数据时出错");
                return "-1";
            }
            Map ord_tax_payerMap = ord_tax_payerList.get(0);
            long cust_id;
            long taxPayerId = Long.parseLong(ord_tax_payerMap.get("taxPayerId").toString());
            long routeId = routeServiceDao.getRouteIdForTaxPayer(V_ARCH_GRP_ID, V_ORDER_ITEM_ID, taxPayerId, msg);
            if (routeId <= 0) {
                msg.setResultCode(ResultCode.TAX_ERROR_003);
                msg.setMessage("取一般纳税人routeId失败【taxPayerId】：" + taxPayerId);
                return "-1";
            }
            List<Map<String, Object>> ordCustList = ordBillDao.selectOrdBillCustId(V_ARCH_GRP_ID);
            if (ordCustList.size() == 0) {
                msg.setResultCode(ResultCode.TAX_ERROR_004);
                msg.setMessage("一般纳税人取custId失败");
                return "-1";
            }
            Map ordCustMap = ordCustList.get(0);
            cust_id = Long.parseLong(ordCustMap.get("custId").toString());
            ord_tax_payerMap.put("routeId", routeId);
            ord_tax_payerMap.put("statusCd", 1000);
            ord_tax_payerMap.put("custId", cust_id);
            long tax_payerCount = accountDao.getCountTaxPayer(ord_tax_payerMap);
            //int ord_tax_payerCount=ordBillDao.getCountOrdTaxPayer(queryord_tax_payerMap);

/*  try{
  	cust_id=accountDao.getCustId(ord_tax_payerMap);
  }catch (Exception e) {
		e.printStackTrace();
		return "纳税人获取客户出错";
	}*/

            if (tax_payerCount == 0) {
          /*ord_tax_payerMap.put("statusCd", 1000);
          ord_tax_payerMap.put("routeId", cust_id);
          String tax_province_code=ord_tax_payerMap.get("taxProvinceCode").toString();
          tax_province_code=tax_province_code.equals("")?"915":tax_province_code;
          ord_tax_payerMap.put("taxProvinceCode", tax_province_code);*/
                try {
                    //CRM送的 EXP_DATE未null，置为3000年
                    ord_tax_payerMap.put("expDate", statePublic.expDate);
                    accountDao.insertTaxPayer1(ord_tax_payerMap);
                    ord_tax_payerMap.put("action",1);
                    if(ord_tax_payerMap.get("statusDate")!=null) {
                        ord_tax_payerMap.put("executetime", d.format(df.parse(ord_tax_payerMap
                                .get("statusDate").toString())));
                    }else{
                        ord_tax_payerMap.put("executetime", d.format(new Date()));
                    }

                    SynMapContextHolder.addMap("taxPayerobjList1", ord_tax_payerMap);

                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "新增一般纳税人时出错");
                    msg.setResultCode(ResultCode.TAX_ERROR_005);
                    msg.setMessage(exceptionMsg);
                    return "-1";
                }
            } else {
                Map querytax_payerMap = new HashMap();
                Map queryTaxpayerFlagMap = new HashMap();
                Map tax_payerUpdate = new HashMap();
                String oldGgeneralTaxplayer = "";
                String newGgeneralTaxplayer = "";
                querytax_payerMap.put("status_cd", 1000);
                querytax_payerMap.put("tax_payer_id", ord_tax_payerMap.get("taxPayerId"));
                querytax_payerMap.put("routeId", cust_id);
                newGgeneralTaxplayer = ord_tax_payerMap.get("generalTaxpayerFlag").toString();
                try {
                    queryTaxpayerFlagMap = accountDao.getTaxPayerInfo(querytax_payerMap);
                    oldGgeneralTaxplayer = queryTaxpayerFlagMap.get("generalTaxplayerFlag").toString();

                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "取TAX_PAYER.GENERAL_TAXPAYER_FLAG出错");
                    msg.setResultCode(ResultCode.TAX_ERROR_006);
                    msg.setMessage(exceptionMsg);
                    return "-1";
                }
                // CRM一般纳税人由是改为否，计费除接收同字段的变更外，将失效时间及状态时间置为当前时间；
                //CRM一般纳税人由否改为是，计费除接收同字段的变更外，将生效时间及状态时间置为当前时间，失效时间置为3000年。
                tax_payerUpdate.put("effDate", ord_tax_payerMap.get("effDate"));
                tax_payerUpdate.put("expDate", statePublic.expDate);
                if ("0".equals(newGgeneralTaxplayer)) {
                    tax_payerUpdate.put("expDate", new Date());
                } else if ("1".equals(newGgeneralTaxplayer)
                        && "0".equals(oldGgeneralTaxplayer)) {
                    tax_payerUpdate.put("effDate", new Date());
                }

                tax_payerUpdate.put("statusCd", 1000);//更新条件
                tax_payerUpdate.put("taxPayerId", ord_tax_payerMap.get("taxPayerId"));//更新条件

                //更新字段
                tax_payerUpdate.put("taxId", ord_tax_payerMap.get("taxId"));
                tax_payerUpdate.put("taxName", ord_tax_payerMap.get("taxName"));
                tax_payerUpdate.put("taxRelaAddr", ord_tax_payerMap.get("taxRelaAddr"));
                tax_payerUpdate.put("taxRelaTel", ord_tax_payerMap.get("taxRelaTel"));
                tax_payerUpdate.put("taxBankAcct", ord_tax_payerMap.get("taxBankAcct"));
                tax_payerUpdate.put("taxBankName", ord_tax_payerMap.get("taxBankName"));
                tax_payerUpdate.put("generalTaxpayerFlag", ord_tax_payerMap.get("generalTaxpayerFlag"));
                tax_payerUpdate.put("vatInvoicesFlag", ord_tax_payerMap.get("vatInvoicesFlag"));
                tax_payerUpdate.put("statusDate", d.format(new Date()));
                tax_payerUpdate.put("updateStaff", ord_tax_payerMap.get("updateStaff"));
                tax_payerUpdate.put("updateDate", ord_tax_payerMap.get("updateDate"));
                tax_payerUpdate.put("remark", ord_tax_payerMap.get("remark"));
                tax_payerUpdate.put("routeId", routeId);
                try {
                    accountDao.updateTaxPayer1(tax_payerUpdate);
                    ord_tax_payerMap.put("action",2);
                    if(ord_tax_payerMap.get("statusDate")!=null) {
                        ord_tax_payerMap.put("executetime", d.format(df.parse(ord_tax_payerMap
                                .get("statusDate").toString())));
                    }else{
                        ord_tax_payerMap.put("executetime", d.format(new Date()));
                    }
                    SynMapContextHolder.addMap("taxPayerobjList1", ord_tax_payerMap);
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "修改一般纳税人时出错");
                    msg.setResultCode(ResultCode.TAX_ERROR_007);
                    msg.setMessage(exceptionMsg);
                    return "-1";
                }
            }
//	2.ord_tax_payer_attr,tax_payer_attr
            Map queryord_tax_payer_attrMap = new HashMap();
            queryord_tax_payer_attrMap.put("arch_grp_id", V_ARCH_GRP_ID);
            queryord_tax_payer_attrMap.put("order_item_id", V_ORDER_ITEM_ID);
            queryord_tax_payer_attrMap.put("oper_type", 1000);
            List<Map<String, Object>> ord_tax_payer_attrList = ordBillDao.getOrdTaxPayerAttr(queryord_tax_payer_attrMap);

            for (int i = 0; i < ord_tax_payer_attrList.size(); i++) {
                Map ord_tax_payer_attrMap = ord_tax_payer_attrList.get(i);
                ord_tax_payer_attrMap.put("routeId", routeId);
                queryord_tax_payer_attrMap.put("tax_payer_attr_id", ord_tax_payer_attrMap.get("taxPayerAttrId"));
                int ord_tax_payer_attrCount = ordBillDao.getCountOrdTaxPayerAttr(queryord_tax_payer_attrMap);
                if (ord_tax_payer_attrCount == 1) {

                    int tax_payer_attrCount = accountDao.getCountTaxPayerAttr(ord_tax_payer_attrMap);
                    if (tax_payer_attrCount == 0) {
                        ord_tax_payer_attrMap.put("statusCd", 1000);
                        ord_tax_payer_attrMap.put("updateDate", d.format(new Date()));
                        try {
                            accountDao.insertTaxPayerAttr1(ord_tax_payer_attrMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                            String exceptionMsg = "";
                            exceptionMsg = setErrorMsg(e.getMessage(), "增加纳税人属性出错");
                            msg.setResultCode(ResultCode.TAX_ERROR_008);
                            msg.setMessage(exceptionMsg);
                            return "-1";
                        }
                    }
                } else {
                    Map tax_payer_attrUpdate = new HashMap();
                    tax_payer_attrUpdate.put("statusCd", 1100);

                    tax_payer_attrUpdate.put("attrValue", ord_tax_payer_attrMap.get("attrValue"));
                    tax_payer_attrUpdate.put("statusDate", ord_tax_payer_attrMap.get("statusDate"));
                    tax_payer_attrUpdate.put("updateStaff", ord_tax_payer_attrMap.get("updateStaff"));
                    tax_payer_attrUpdate.put("updateDate", df.format(new Date()));
                    tax_payer_attrUpdate.put("remark", "接口更新,ARCH_GRP_ID =" + V_ARCH_GRP_ID);
                    tax_payer_attrUpdate.put("taxPayerAttrId", ord_tax_payer_attrMap.get("taxPayerAttrId"));//更新条件
                    tax_payer_attrUpdate.put("routeId", routeId);


                    try {
                        accountDao.updateTaxPayerAttr1(tax_payer_attrUpdate);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "修改联系信息属性出错");
                        msg.setResultCode(ResultCode.TAX_ERROR_009);
                        msg.setMessage(exceptionMsg);
                        return "-1";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理ord_tax出错");
            msg.setResultCode(ResultCode.TAX_ERROR_010);
            msg.setMessage(exceptionMsg);
            return "-1";
        }
        return "1";
    }


    public int insertProdInstSub(Map itemMap, Map userMap, Message msg)
            throws Exception {

		/*List<Map<String, Object>> prodInstList = ordBillDao
				.selectOrdProdInstSub(itemMap);*/
        //add by wangbaoqiang 添加接口表逻辑判断begin
        try {
            List<Map<String, Object>> prodInstList = new ArrayList<Map<String, Object>>();
            prodInstList = ordBillDao
                    .selectOrdProdInstSub(itemMap);
            if (prodInstList.size() == 0) {
                prodInstList = ordBillDao
                        .selectOrdProdInstSub1300(itemMap);
                if (prodInstList.size() == 0) {
                    return 1;
                } else if (prodInstList.size() > 1) {
                    msg.setResultCode(ResultCode.PRODSUB_ERROR_001);
                    msg.setMessage("用户附属产品接口表数据operType=1300时出错:记录有多条");
                    return -1;
                }
            } else if (prodInstList.size() > 1) {
                msg.setResultCode(ResultCode.PRODSUB_ERROR_002);
                msg.setMessage("用户附属产品接口表数据operType=1000时出错:记录有多条");
                return -1;
            }//add end;
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
            SimpleDateFormat df3000 = new SimpleDateFormat("yyyy-MM-dd");
            // prodInstMap存放prod_inst对象
            Map prodInstMap = new HashMap();
            String prodId = "";
            String effDate = "";
            String expDate = "";
            Long routeId;
            long archGrpId = 0;
            long orderItemId = 0;
            long prodInstId = 0;

            //20G断网恢复处理变量
            long liFlag;
            long liCnt;
            prodInstMap = prodInstList.get(0);
            Map tifProdSubMap = new HashMap();
            String prodSubType = "";
            Long prodIdBill = null;
            List<Map<String, Object>> ordProdInsSubrList =
                    ordBillDao.selectTifSubProdContrast(String.valueOf(prodInstMap.get("prodId")));
            if (ordProdInsSubrList.size() == 0) {//过滤计费不需要的附属产品
                msg.setResultCode(ResultCode.PRODSUB_ERROR_003);
                msg.setMessage("取对应的功能产品编码时出错【prodId】：" + prodInstMap.get("prodId"));
                return -1;
            }
            tifProdSubMap = ordProdInsSubrList.get(0);
            prodSubType = tifProdSubMap.get("prodType").toString();
            prodIdBill = Long.parseLong(String.valueOf(tifProdSubMap.get("prodIdBill")));
            /*if ("10X".equals(prodSubType)) {
                return 1;
            }*/
            //end
            archGrpId = Long.parseLong(prodInstMap.get("ARCH_GRP_ID").toString());
            orderItemId = Long.parseLong(prodInstMap.get("ORDER_ITEM_ID").toString());
            prodInstId = Long.parseLong(prodInstMap.get("accProdInstId").toString());
            prodId = prodInstMap.get("prodId").toString();
            effDate = prodInstMap.get("statusDate").toString();
            routeId = routeServiceDao.getRouteIdForProdInst(archGrpId, orderItemId, prodInstId, msg);
            if (routeId <= 0) {
                msg.setResultCode(ResultCode.PRODSUB_ERROR_012);
                msg.setMessage("取用户的routeId失败【prodInstId】：" + prodInstId);
                return -1;
            }
            prodInstMap.put("routeId", routeId);
            //获取指定日期的下一个月
            //expDate = prodinstDao.getNextMonth(effDate);
			 /*Date dExpDate = df3000.parse(expDateString);
			 expDate = df3000.format(dExpDate);*/
            //已拆机的不处理
            /*Long prodInstCount = prodinstDao.getForProdInstCount(prodInstMap);
            if (prodInstCount <= 0) {
				*//*msg.setMessage("用户"+prodInstMap.get("accProdInstId")+"不存在,不能订购功能产品");
				return -1;*//*
                return 1;
            }*/

            //CRM失效附属产品的一条 1000，一条1100，接口只处理 OPER_TYPE = 1000的这条，
            //如果只有 OPER_TYPE = 1000计费接口为新增，如果同时有两条 则为失效
            prodInstMap.put("sysDate", df.format(new Date()));
            if (StringUtil.isEmpty(prodInstMap.get("statusDate"))) {
                prodInstMap.put("executetime", d.format(new Date()));
            } else {
                prodInstMap.put("executetime", d.format(df.parse(prodInstMap
                        .get("statusDate").toString())));
            }
            Long operFlg = ordBillDao.getOperFlag(prodInstMap);
            if (operFlg == 0) {
                // TODO: 2019/6/17 断网复网逻辑3.0暂时去掉，以备份
                //10X的不处理
                if ("10X".equals(prodSubType)) {
                    return 1;
                }
                //modify by wangbaoqiang 先取主键，取不到在按照产品实例和规格来取
               // Long prodInstSubCount = prodinstDao.getProdInstSubPrimaryKeyCnt(prodInstMap);
                //if (prodInstSubCount == 0) {
                Long prodInstSubCount = prodinstDao.getForProdInstSubCount(prodInstMap);
               // }
                if (prodInstSubCount > 0) {
                    try {
                        List<Map<String, Object>> prodInstSubMaps = prodinstDao.getProdInstSubIdExp(prodInstMap);
                        if (prodInstSubMaps.size() > 1) {
                            msg.setResultCode(ResultCode.PRODSUB_ERROR_004);
                            msg.setMessage("取附属产品实例时出错【prodId】：" + prodInstMap.get("prodId")
                                         + ",【accProdInstId】：" + prodInstMap.get("accProdInstId"));
                            return -1;
                        } else {
                            Map prodInstSubMap = prodInstSubMaps.get(0);
                            prodInstSubMap.put("statusCd", "110001");
                            prodInstSubMap.put("updateDate", df.format(new Date()));
                            prodInstSubMap.put("stopRentDate", prodInstMap.get("statusDate"));
                            prodInstSubMap.put("action", 2);
                            prodInstSubMap.put("remark", "计费检测有重复附属，档案接口做失效");
                            prodInstSubMap.put("executetime", prodInstMap.get("executetime"));
                            prodinstDao.updateProdInstSub(prodInstSubMap);
                            SynMapContextHolder.addMap("prodInstSubobjList1", prodInstSubMap);
                            //处理附属产品属性
                            List<Map<String, Object>> prodInstSubAttrList = prodinstDao.selectProdInstAttrSubMap(prodInstSubMap);
                            for (Map<String, Object> map :
                                    prodInstSubAttrList) {
                                map.put("statusCd", "1100");
                                map.put("updateDate", df.format(new Date()));
                                map.put("expDate", prodInstMap.get("statusDate"));
                                map.put("remark", "计费检测有重复附属，档案接口做失效");
                                prodinstDao.updateProdInstAttrSub(map);
                                map.put("action", 2);
                                map.put("executetime", prodInstMap.get("executetime"));
                                //prodinstDao.updateProdInstSub(prodInstSubMap);
                                SynMapContextHolder.addMap("prodInstAttrSubobjList1", map);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "修改附属产品实例附加时出错");
                        msg.setResultCode(ResultCode.PRODSUB_ERROR_005);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                }
                //if(prodInstSubCount==0){
                //增加附属产品实例 PROD_INST_SUB
                try {
						/*Long servProdId = prodinstDao.getSeq("SEQ_SERV_PROD_ID"); //获取序列
						prodInstMap.put("prodInstId", servProdId);*/
                    List<Map<String,Object>> prodInstSubPrimaryKeyCnt = prodinstDao.getProdInstSubPrimaryKeyCnt(prodInstMap);
                    if (prodInstSubPrimaryKeyCnt.size() > 0) {
                        long seqProdInstSubId = prodinstDao.getSeq("SEQ_PROD_INST_SUB_ID");
                        prodInstMap.put("prodInstId", seqProdInstSubId);
                    }
                    //取主产品实例的号码落到附属产品实例上
                    Map map = new HashMap();
                    map.put("prodInstId",prodInstMap.get("accProdInstId"));
                    map.put("routeId", prodInstMap.get("routeId"));
                    List<Map<String, Object>> prodInstObjList = prodinstDao.getProdInstOBJ(map);
                    if (prodInstObjList.size() > 0) {
                        Map prodInstObjMap = prodInstObjList.get(0);
                        prodInstMap.put("regionId", prodInstObjMap.get("regionId"));
                        prodInstMap.put("lanId", prodInstObjMap.get("lanId"));
                        prodInstMap.put("accNum",prodInstObjMap.get("accNum"));
                    }
                    prodInstMap.put("prodUseType", "2000");
                    //prodInstMap.put("beginRentDate", effDate);
                    //prodInstMap.put("stopRentDate", expDateString);
                    prodInstMap.put("statusCd", "100000");
                    //prodInstMap.put("statusDate", effDate);
                    prodInstMap.put("action", 1);
                    prodinstDao.insertProdInstSub(prodInstMap);
                    SynMapContextHolder.addMap("prodInstSubobjList1", prodInstMap);
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "增加附属产品实例时出错");
                    msg.setResultCode(ResultCode.PRODSUB_ERROR_006);
                    msg.setMessage(exceptionMsg);
                    return -1;
                }
                //附属产品属性
                if (!prodId.equals("35213013") || !prodId.equals("35214000")) {
                    try {
                        Long prodInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); //获取序列
                        prodInstMap.put("prodInstAttrId", prodInstAttrId);
                        prodInstMap.put("parProdInstAttrId", "1");
							/*Long servProdId = prodinstDao.getSeq("SEQ_SERV_PROD_ID"); //获取序列
							prodInstMap.put("prodInstId",servProdId );*/
                        prodInstMap.put("statusCd", "1000");
                        prodInstMap.put("attrId", "167");
                        prodInstMap.put("attrValueId", "1");
                        prodInstMap.put("attrValue", "1");
                        prodInstMap.put("effDate", prodInstMap.get("beginRentDate"));
                        prodInstMap.put("action", 1);
                        prodInstMap.put("expDate", statePublic.expDate);
                        prodinstDao.insertProdInstAttrSub(prodInstMap);
                        SynMapContextHolder.addMap("prodInstAttrSubobjList1", prodInstMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加167附属产品属性时出错");
                        msg.setResultCode(ResultCode.PRODSUB_ERROR_007);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                }
                // TODO: 2019/7/4 刚增加的云卡停机服务属性
                //云卡停机服务增加属性
                if (prodId.equals("1132")) {
                    Long prodInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); //获取序列
                    prodInstMap.put("prodInstAttrId", prodInstAttrId);
                    prodInstMap.put("parProdInstAttrId", "1");
							/*Long servProdId = prodinstDao.getSeq("SEQ_SERV_PROD_ID"); //获取序列
							prodInstMap.put("prodInstId",servProdId );*/
                    prodInstMap.put("attrId", "35214005");
                    prodInstMap.put("attrValueId", "1");
                    prodInstMap.put("attrValue", "1");
                    prodInstMap.put("effDate", prodInstMap.get("beginRentDate"));
                    prodInstMap.put("action", 1);
                    prodInstMap.put("expDate", statePublic.expDate);
                    prodinstDao.insertProdInstAttrSub(prodInstMap);
                    SynMapContextHolder.addMap("prodInstAttrSubobjList1", prodInstMap);
                }
            } else if (operFlg == 1) {
                //add by wangbaoqaing begin

                //TODO: 2019/6/17 断网复网逻辑3.0没有暂时去掉了
                //失效4G属性
                //effDate = prodInstMap.get("stopRentDate").toString();
                prodInstMap.put("expDate", prodInstMap.get("statusDate"));
                //失效附属产品
                List<Map<String, Object>> prodInstSubList = prodinstDao.getProdInstSubPrimaryKeyCnt(prodInstMap);
                //List<Map<String, Object>> prodInstSubList = new ArrayList<Map<String, Object>>();
                if (prodInstSubList.size() == 0) {
                    prodInstSubList = prodinstDao.getProdInstSubIdExp(prodInstMap);
                    if (prodInstSubList.size() == 1) {
                        Map prodInstSubMap = prodInstSubList.get(0);
                        prodInstMap.put("prodInstId", prodInstSubMap.get("prodInstId"));
                    } else if (prodInstSubList.size() > 1) {
                        msg.setResultCode(ResultCode.PRODSUB_ERROR_008);
                        msg.setMessage("附属产品实例时有多条【accProdInstId】：" + prodInstMap.get("accProdInstId") +
                                "产品Id【prodId】：" + prodInstMap.get("prodId"));
                        return -1;
                    } else if (prodInstSubList.size() == 0) {
                        //退订crm和计费功能产品不同的
                        prodInstMap.put("prodId", prodIdBill);
                        prodInstSubList = prodinstDao.getProdInstSubIdExp(prodInstMap);
                        if (prodInstSubList.size() == 1) {
                            Map prodInstSubMap = prodInstSubList.get(0);
                            prodInstMap.put("prodInstId", prodInstSubMap.get("prodInstId"));
                        }else if (prodInstSubList.size() > 1) {
                            msg.setResultCode(ResultCode.PRODSUB_ERROR_016);
                            msg.setMessage("附属产品实例时有多条【accProdInstId】：" + prodInstMap.get("accProdInstId") +
                                    "产品Id【prodId】：" + prodInstMap.get("prodId"));
                            return -1;
                        }
                        //不处理
                        /*return 1;*/
                    }
                }
                if (prodInstSubList.size() > 0) {
                    prodInstMap.put("statusCd", "110001");
                    //prodInstMap.put("updateDate", df.format(new Date()));
                    //prodInstMap.put("stopRentDate", prodInstMap.get("statusDate"));
                    prodInstMap.put("action", 2);
                    try {
                        prodinstDao.updateProdInstSub(prodInstMap);
                        SynMapContextHolder.addMap("prodInstSubobjList1", prodInstMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "修改附属产品实例时出错");
                        msg.setResultCode(ResultCode.PRODSUB_ERROR_009);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                    try {
                        List<Map<String, Object>> prodInstSubAttrList = prodinstDao.selectProdInstAttrSubMap(prodInstMap);
                        for (Map<String, Object> map :
                                prodInstSubAttrList) {
                            map.put("statusCd", "1100");
                            //map.put("updateDate", df.format(new Date()));
                            map.put("expDate", prodInstMap.get("stopRentDate"));
                            prodinstDao.updateProdInstAttrSub(map);
                            map.put("action", 2);
                            map.put("executetime", prodInstMap.get("executetime"));
                            SynMapContextHolder.addMap("prodInstAttrSubobjList1", map);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "修改附属产品实例属性出错");
                        msg.setResultCode(ResultCode.PRODSUB_ERROR_010);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                }
                //处理40g复网
                int dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                if (("880000011".equals(prodId) || "88000016".equals(prodId))
                        && dayOfMonth !=1 && dayOfMonth !=2) {
                    String prodType = "";
                    if ("88000016".equals(prodId)) {
                        prodType = "1";
                    }else{
                        prodType = "2";
                    }
                    if (doBreakOffer(prodInstMap, msg,prodType) < 0) {
                        return -1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理用户附属产品属性出错");
            msg.setResultCode(ResultCode.PRODSUB_ERROR_011);
            msg.setMessage(exceptionMsg);
            return -1;
        }


        return 1;
    }

    //用户加入VPN
    public int insertProdInstGroup(Map itemMap, Map userMap, Message msg)
            throws Exception {
        List<Map<String, Object>> prodInstList = ordBillDao
                .selectOrdProdInstRel(itemMap);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        // prodInstMap存放prod_inst对象
        Map prodInstMapRel = new HashMap();
        if (prodInstList.size() > 0) {
            prodInstMapRel = prodInstList.get(0);
            //
        }
        return 1;
    }

    /**
     * 检查工单先后顺序（新装用户不校验前置工单）
     *
     * @param itemMap
     * @param userMap
     * @param msg
     * @return
     * @throws Exception
     */
    public int workOrder(Map itemMap, Map userMap, Message msg)
            throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        Map<String, Object> map = new HashMap<String, Object>();
        Date ordBillFinishDate;
        ordBillFinishDate = (Date) itemMap.get("FINISH_DATE");
        List<Map<String, Object>> ordBillProdInstList = ordBillDao.getOrdBillProdInst(itemMap);
        Map ordBillProdInstMap = new HashMap();
        Long prodInstId;
        for (int k = 0; k < ordBillProdInstList.size(); k++) {
            ordBillProdInstMap = ordBillProdInstList.get(k);
            prodInstId = (Long) ordBillProdInstMap.get("PROD_INST_ID");
            map.put("effDate", ordBillFinishDate);
            map.put("prodInstId", prodInstId);
            List<Map<String, Object>> resultList = ordBillDao.getOrdBillAndOrdBillProdInst(map);
            if (resultList.size() > 0) {
                msg.setResultCode(ResultCode.PUB_ERROR_001);
                msg.setMessage("前面有处理不成功的工单,此工单暂时不处理:" + prodInstId);
                return -1;
            }
        }
        return 1;
    }

    //根据OFFER_INST_ID, OBJ_ID,OFFER_OBJ_REL_ID, EXP_DATE获取ID
    public long getOfferObjInstRelId(Map map) {
        try {
            long OfferObjInstRelId = offerinstDao.getOfferObjInstRelId(map);
            return OfferObjInstRelId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    //根据OFFER_INST_ID,ATTR_ID,ATTR_VALUE, EXP_DATE获取ID
    public long getOfferInstAttrId(Map map) {
        try {
            long OfferInstAttrId = offerinstDao.getOfferInstAttrId1(map);
            return OfferInstAttrId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 从计费库中获取PROD_INST_SUB中的主键PROD_INST_ID。
     *
     * @param map
     * @return
     * @maozp3
     */
    public long selectProdInstId(Map map) {
        try {
            Long prodInstId = prodinstDao.selectProdInstIdFromProdInstSub(map);
            if (prodInstId > 0) {
                return prodInstId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }

    /**
     * @return long
     * @Author maozp3
     * @Description: 从计费库中获取PROD_INST_ATTR中的主键PROD_INST_ATTR_ID。
     * @Date: 18:34 2019/4/2
     * @Param [map]
     **/
    public long selectProdInstAttrId(Map map) {
        try {
            Long prodInstAttrId = prodinstDao.selectProdInstAttrIdFromProdInstAttr(map);
            if (prodInstAttrId > 0) {
                return prodInstAttrId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }

    /**
     * @return long
     * @Author maozp3
     * @Description:从PROD_INST_ATTR_SUB表中获取PROD_INST_ATTR_ID，其中PROD_INST_ID来自PROD_INST_SUB表
     * @Date: 18:32 2019/4/2
     * @Param [map]
     **/
    public long selectProdInstAttrIdfromUnion(Map map) {
        try {
            Long prodInstAttrId = prodinstDao.selectProdInstAttrIdFromProdInstAttrSub(map);
            if (prodInstAttrId > 0) {
                return prodInstAttrId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }

    /**
     * @return long
     * @Author maozp3
     * @Description: 从PROD_INST_PAYMODE获取PAYMODE_ID
     * @Date: 19:51 2019/4/2
     * @Param [map]
     **/
    public long selectPaymodeId(Map map) {
        try {
            Long paymodeId;
            List<Long> paymodeIdList = prodinstDao.getProdInstPaymode_1(map);
            if (paymodeIdList.size() > 0) {
                paymodeId = paymodeIdList.get(0);
                return paymodeId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1L;
    }

    /**
     * @param errorMsg
     * @param constrantString
     * @return
     */
    public String setErrorMsg(String errorMsg, String constrantString) {
        if (errorMsg != null && !"".equals(errorMsg)) {

            return constrantString +  (errorMsg.length() > 256 ? errorMsg.substring(0, 255):errorMsg);
        }
        return constrantString;
    }
/**
 * @Author WangBaoQiang
 * @Description 当实例不一致的时候按照用户的订购来取商品实例，如果有多条取最小的实例id
 * @Date 14:40 2019/8/28
 * @Param [itemMap, userMap, msg, acctId, offerId]
 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
*/
    public List<Map<String, Object>> checkOfferInstRelIdExist(Map itemMap, Map userMap, Message msg,
                                                              long acctId, long offerId) {
        List<Map<String, Object>> offerObjInstList = offerinstDao.getOfferObjInstIdFromObj(userMap);
        List<Map<String, Object>> offerReturnList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> offerObjInstMap : offerObjInstList) {
            List<Map<String, Object>> offerInstList = offerinstDao.selectOfferInstFromObjInstid(Long.parseLong(offerObjInstMap.get("offerInstId").toString()), acctId);

            if (offerInstList.size() > 0) {
                Map offerInstMap = offerInstList.get(0);
                if (String.valueOf(offerId).equals(offerInstMap.get("offerId").toString())) {
                    Map mapReturn = new HashMap();
                    mapReturn.put("offerInstId", offerObjInstMap.get("offerInstId"));
                    mapReturn.put("offerObjInstRelId", offerObjInstMap.get("offerObjInstRelId"));
                    mapReturn.put("routeId", offerObjInstMap.get("routeId"));
                    mapReturn.put("effDate", offerObjInstMap.get("effDate"));
                    mapReturn.put("expDate", offerObjInstMap.get("expDate"));
                    offerReturnList.add(mapReturn);
                }
            }
        }
        //按照实例进行排序，返回最小实例id

        if (offerReturnList.size() > 0) {
            Collections.sort(offerReturnList, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    //name1是从你list里面拿出来的一个
                    String name1 = (String) o1.get("offerInstId").toString();
                    //name1是从你list里面拿出来的第二个name
                    String name2 = (String) o2.get("offerInstId").toString();
                    return name1.compareTo(name2);
                }

            });
        }
        return offerReturnList;
    }

    /**
     * @return int
     * @Author WangBaoQiang
     * @Description 此函数是为了判断某一套餐是否存在，为啥teledb如此蛋疼，不能表关联？还是能关联，产品部误解？
     * @Date 20:34 2019/8/27
     * @Param [itemMap, userMap, msg, acctId]
     */
    public int checkOfferExist(Map itemMap, Map userMap, Message msg, long acctId, String offerId) {
        List<Map<String, Object>> offerObjInstList = offerinstDao.getOfferObjfromObjId(userMap);
        List<Map<String, Object>> offerReturnList = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> offerObjInstMap : offerObjInstList) {
            List<Map<String, Object>> offerInstList = offerinstDao.selectOfferInstFromObjInstid(Long.parseLong(offerObjInstMap.get("offerInstId").toString()), acctId);
            if (offerInstList.size() > 0) {
                Map offerInstMap = offerInstList.get(0);
                String offerIdTmp = "";
                offerIdTmp = offerInstMap.get("offerId").toString();
                if ("61".equals(offerIdTmp.substring(0, 2)) && offerIdTmp.length() == 6) {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * @return java.lang.String
     * @Author WangBaoQiang
     * @Description 异常信息获取
     * @Date 14:21 2019/8/28
     * @Param [msg, e]
     */
    public String grepMsg(String msg, Exception e) {
        String returnMsg = "";
        if (e.getMessage() != null) {
            if (e.getMessage().length() > 256) {
                returnMsg = msg + e.getMessage().substring(0, 256);
            } else {
                returnMsg = msg + e.getMessage();
            }
        } else {
            returnMsg = msg;
        }
        return returnMsg;
    }
    public int doOrdCustomer(Map map, Message msg,long archGrpId,long orderItemId) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Map custMap = new HashMap();
        try {
            long serviceOfferId = Long.parseLong(map.get("SERVICE_OFFER_ID")
                    .toString());
            List<Map<String, Object>> custList = ordBillDao
                    .selectOrdCustomer(map);
            //add by wangbaoqiang begin
            if (custList.size() == 0) {
                custList = ordBillDao.selectOrdCustomer_1300(map);
                if (custList.size() == 0) {
                    msg.setResultCode(ResultCode.CUSTOMER_ERROR_001);
                    msg.setMessage("取客户接口表ord_customer数据时出错:记录不存在");
                    return -1;
                } else if (custList.size() > 1) {
                    msg.setResultCode(ResultCode.CUSTOMER_ERROR_002);
                    msg.setMessage("取客户接口表ord_customer数据时出错:记录有多条！");
                    return -1;
                }
            } else if (custList.size() > 1) {
                msg.setResultCode(ResultCode.CUSTOMER_ERROR_003);
                msg.setMessage("取ord_customer客户接口表数据时出错:记录有多条！");
                return -1;
            }//add end;
            if (custList.size() > 0) {
                custMap = custList.get(0);
                long cust_id = Long.parseLong(custMap.get("custId")
                        .toString());
                custMap.put("routeId", cust_id);
                if (custMap.get("statusDate") != null
                        && !"".equals("statusDate")) {
                    custMap.put("executetime", d.format(df.parse(custMap
                            .get("statusDate").toString())));
                } else {
                    custMap.put("executetime", d.format(new Date()));
                }

                custMap.put("statusCd", 1100);
                // 代表新增
                if (serviceOfferId == 1010100000) {
                    long custCount = accountDao
                            .getCustomerById(custMap);
                    //存在就直接返回了
                    if (custCount > 0) {
                        return 1;
                    }

                    try {
                        List<Map<String, Object>> regionList = ordBillDao.selectTifOrgContrast(custMap);
                        if (regionList.size() > 0) {
                            Map regionMap = new HashMap();
                            regionMap = regionList.get(0);
                            custMap.put("regionId", regionMap.get("orgIdBill"));
                        }
                        accountDao.insertCustomer(custMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(),"新增客户时出错");
                        msg.setResultCode(ResultCode.CUSTOMER_ERROR_004);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }

                    custMap.put("action", 1);

                } else {
                    // long custId =
                    // Long.parseLong(custMap.get("custId").toString());
                    long custCount = accountDao
                            .getCustomerById(custMap);
                    if (custCount >0 ) {
                        List customerHisList = accountDao.getCustomer(custMap);
                        Map customerHisMap = (Map) customerHisList.get(0);

                        try {
                            hisService.insertCustomerHis(customerHisMap);
                            accountDao.updateCustomer(custMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                            String exceptionMsg = "";
                            exceptionMsg = setErrorMsg(e.getMessage(),"修改客户表时出错");
                            msg.setResultCode(ResultCode.CUSTOMER_ERROR_005);
                            msg.setMessage(exceptionMsg);
                            return -1;
                        }
                        custMap.put("action", 2);
                    } else {
                        msg.setResultCode(ResultCode.CUSTOMER_ERROR_006);
                        msg.setMessage("客户不存在,不能修改CUST_ID：" + cust_id);
                        return -1;
                    }
                }
            }
            SynMapContextHolder.addMap("custobjList1", custMap);
            //custobjList1.add(custMap);
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(),"处理客户ORG_CUSTOMER失败");
            msg.setResultCode(ResultCode.CUSTOMER_ERROR_007);
            msg.setMessage(exceptionMsg);
            return -1;
        }
//				ord_cust_contact_info_rel,cust_contact_info_rel
//				-----(2)、客户联系信息 REC_CUST_CONTACT
        Map queryord_cust_contact_info_relMap = new HashMap();
        queryord_cust_contact_info_relMap.put("arch_grp_id", archGrpId);
        queryord_cust_contact_info_relMap.put("order_item_id", orderItemId);
        queryord_cust_contact_info_relMap.put("oper_type", 1000);
        List<Map<String, Object>> ord_cust_contact_info_relList = ordBillDao.getOrdCustContactInfoRel(queryord_cust_contact_info_relMap);

        for (int i = 0; i < ord_cust_contact_info_relList.size(); i++) {
            Map ord_cust_contact_info_relMap = ord_cust_contact_info_relList.get(i);
            queryord_cust_contact_info_relMap.put("cust_connect_id", ord_cust_contact_info_relMap.get("custConnectId"));
            int ord_cust_contact_info_relCount = ordBillDao.getCountOrdCustContactInfoRel(queryord_cust_contact_info_relMap);
            if (ord_cust_contact_info_relCount == 1) {
                ord_cust_contact_info_relMap.put("statusCd", 1000);
                ord_cust_contact_info_relMap.put("routeId", ord_cust_contact_info_relMap.get("custId"));
                try {
                    accountDao.insertCustCcontactInfoRel(ord_cust_contact_info_relMap);
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(),"增加客户联系信息出错");
                    msg.setResultCode(ResultCode.CUSTOMER_ERROR_008);
                    msg.setMessage(exceptionMsg);
                    return -1;
                }
            } else {
                Map cust_contact_info_relUpdate = new HashMap();

                cust_contact_info_relUpdate.put("statusDate", ord_cust_contact_info_relMap.get("statusDate"));
                cust_contact_info_relUpdate.put("updateStaff", ord_cust_contact_info_relMap.get("updateStaff"));
                cust_contact_info_relUpdate.put("updateDate", ord_cust_contact_info_relMap.get("updateDate"));
                cust_contact_info_relUpdate.put("remark", ord_cust_contact_info_relMap.get("remark"));
                cust_contact_info_relUpdate.put("routeId", ord_cust_contact_info_relMap.get("custId"));
                cust_contact_info_relUpdate.put("custConnectId", ord_cust_contact_info_relMap.get("custConnectId"));//修改条件

                try {
                    ord_cust_contact_info_relMap.put("routeId", ord_cust_contact_info_relMap.get("custId"));
                    List CustContactInfoRelList = accountDao.getCustContactInfoRel(ord_cust_contact_info_relMap);
                    if (CustContactInfoRelList.size() > 0) {
                        Map CustContactInfoRelMap = (Map) CustContactInfoRelList.get(0);
                        hisService.insertCustContactInfoRelHis(CustContactInfoRelMap);
                        accountDao.updateCustContactInfoRel1(cust_contact_info_relUpdate);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    msg.setResultCode(ResultCode.CUSTOMER_ERROR_009);
                    exceptionMsg = setErrorMsg(e.getMessage(),"修改客户联系信息出错");
                    msg.setMessage(exceptionMsg);
                    return -1;
                }
            }
        }
//			    ord_party_cert,party_cert
//			    --------(3)、参与人证件 PARTY_CERT
        Map queryord_party_certMap = new HashMap();
        queryord_party_certMap.put("arch_grp_id", archGrpId);
        queryord_party_certMap.put("order_item_id", orderItemId);
        queryord_party_certMap.put("oper_type", 1000);
        List<Map<String, Object>> ord_party_certList = ordBillDao.getOrdPartyCert(queryord_party_certMap);

        for (int i = 0; i < ord_party_certList.size(); i++) {
            Map ord_party_certMap = ord_party_certList.get(i);
            if(!StringUtil.isEmpty(ord_party_certMap.get("statusDate"))){
                ord_party_certMap.put("executetime", d.format(df.parse(ord_party_certMap
                        .get("statusDate").toString())));
            }else{
                ord_party_certMap.put("executetime", d.format(new Date()));
            }
            queryord_party_certMap.put("party_cert_id", ord_party_certMap.get("partyCertId"));
            ord_party_certMap.put("routeId", ord_party_certMap.get("partyId"));
            int ord_party_certCount = ordBillDao.getCountOrdPartyCert(queryord_party_certMap);
            if (ord_party_certCount == 1) {
                ord_party_certMap.put("statusCd", 1000);
                ord_party_certMap.put("routeId", ord_party_certMap.get("partyId"));
                try {
                    //因为crm的party_cert_id会有重复，所以把crm的party_cert_id放到remark字段里，更新
                    ord_party_certMap.put("remark", ord_party_certMap.get("partyCertId"));
                    long partyCertId = prodinstDao.getSeq("SEQ_PARTY_CERT_ID");
                    ord_party_certMap.put("partyCertId", partyCertId);
                    accountDao.insertPartyCert1(ord_party_certMap);
                    ord_party_certMap.put("action",1);
                    SynMapContextHolder.addMap("partyCertobjList1", ord_party_certMap);
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(),"增加参与人证件人出错");
                    msg.setResultCode(ResultCode.CUSTOMER_ERROR_010);
                    msg.setMessage(exceptionMsg);
                    return -1;
                }
            } else {
                Map party_certUpdate = new HashMap();
                party_certUpdate.put("statusCd", 1100);
                party_certUpdate.put("certType", ord_party_certMap.get("certType"));
                party_certUpdate.put("certOrg", ord_party_certMap.get("certOrg"));
                party_certUpdate.put("certAddr", ord_party_certMap.get("certAddr"));
                party_certUpdate.put("certNum", ord_party_certMap.get("certNum"));
                party_certUpdate.put("statusCd", ord_party_certMap.get("statusCd"));
                party_certUpdate.put("statusDate", ord_party_certMap.get("statusDate"));
                party_certUpdate.put("updateStaff", ord_party_certMap.get("updateStaff"));
                party_certUpdate.put("updateDate", ord_party_certMap.get("updateDate"));
                /*party_certUpdate.put("remark", ord_party_certMap.get("remark"));*/
                party_certUpdate.put("partyCertId", String.valueOf(ord_party_certMap.get("partyCertId")));
                party_certUpdate.put("routeId", ord_party_certMap.get("partyId"));

                try {
                    accountDao.updatePartyCert1(party_certUpdate);
                    ord_party_certMap.put("action",2);
                    //crm的主键放到了remark里了，需要从remark定位到计费自己的序列。
                    List<Map<String, Object>> partyCertList = accountDao.getPartyCert(party_certUpdate);
                    if (partyCertList.size() > 0) {
                        Map partyCertMap = partyCertList.get(0);
                        SynMapContextHolder.addMap("partyCertobjList1", partyCertMap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(),"修改参与人证件出错");
                    msg.setResultCode(ResultCode.CUSTOMER_ERROR_011);
                    msg.setMessage(exceptionMsg);
                    return -1;
                }
            }
        }
//			    ord_cust_attr,cust_attr
//			    ---(4)、客户属性  ORD_CUST_ATTR

        try {
            Map queryord_cust_attrMap = new HashMap();
            queryord_cust_attrMap.put("arch_grp_id", archGrpId);
            queryord_cust_attrMap.put("order_item_id", orderItemId);
            List<Map<String, Object>> ord_cust_attrList = ordBillDao.getOrdCustAttr(queryord_cust_attrMap);

            for (int i = 0; i < ord_cust_attrList.size(); i++) {
                Map ord_cust_attrMap = ord_cust_attrList.get(i);
                ord_cust_attrMap.put("routeId", ord_cust_attrMap.get("custId"));
                if ("1000".equals(ord_cust_attrMap.get("operType"))) {
                    if (!StringUtil.isEmpty(ord_cust_attrMap.get("attrValue"))) {
                        ord_cust_attrMap.put("statusCd", 1000);
                        ord_cust_attrMap.put("custId", ord_cust_attrMap.get("custId"));
                        try {
                            accountDao.insertCustAttr1(ord_cust_attrMap);
                        } catch (Exception e) {
                            e.printStackTrace();
                            String exceptionMsg = "";
                            exceptionMsg = setErrorMsg(e.getMessage(),"增加客户属性出错");
                            msg.setResultCode(ResultCode.CUSTOMER_ERROR_012);
                            msg.setMessage(exceptionMsg);
                            return -1;
                        }
                        //增加延缓打票
                        /*if (StringUtil.isEmpty(ord_cust_attrMap.get("attrId"))) {
                            String attrId = String.valueOf(ord_cust_attrMap.get("attrId"));
                            if ("10020123".equals(attrId)) {
                                if ("297077".equals(ord_cust_attrMap.get("attrValue"))) {
                                    AElecInvContact aElecInvContact = new AElecInvContact();
                                    long contactId =  acctMapperDao.getAcctSeq("seq_contact_id");
                                    aElecInvContact.setContactId(contactId);

                                    aElecInvContactMapperDao.insertAElecInvContact()
                                }
                            }
                        }*/
                    }
                } else {
                    Map cust_attrUpdate = new HashMap();
                    cust_attrUpdate.put("statusCd", 1100);
                    cust_attrUpdate.put("statusDate", ord_cust_attrMap.get("statusDate"));
                    cust_attrUpdate.put("updateStaff", ord_cust_attrMap.get("updateStaff"));
                    cust_attrUpdate.put("updateDate", ord_cust_attrMap.get("updateDate"));
                    cust_attrUpdate.put("remark", ord_cust_attrMap.get("remark"));
                    cust_attrUpdate.put("custAttrId", ord_cust_attrMap.get("custAttrId"));
                    cust_attrUpdate.put("routeId", ord_cust_attrMap.get("custId"));

                    try {
                        accountDao.updateCustAttr(cust_attrUpdate);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(),"修改客户属性出错");
                        msg.setResultCode(ResultCode.CUSTOMER_ERROR_013);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(),"处理客户属性失败");
            msg.setResultCode(ResultCode.CUSTOMER_ERROR_014);
            msg.setMessage(exceptionMsg);
            return  -1;
        }
        return 1;
    }
    public int doOrdAccount(Map map, Message msg,long archGrpId,long orderItemId) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat yyyy_sdf = new SimpleDateFormat("yyyy");
        try {
            long serviceOfferId = Long.parseLong(map.get("SERVICE_OFFER_ID")
                    .toString());
            List<Map<String, Object>> accountList = ordBillDao
                    .selectOrdAccount(map);
            //add by wangbaoqiang begin
            if (accountList.size() > 1) {
                msg.setResultCode(ResultCode.ACCOUNT_ERROR_001);
                msg.setMessage("取帐户接口表ord_account数据时出错:记录数有多条");
                return -1;
            } else if (accountList.size() == 0) {
                msg.setResultCode(ResultCode.ACCOUNT_ERROR_002);
                msg.setMessage("取帐户接口表ord_account数据时出错:记录数为空");
                return -1;
            }// add end;
            if (accountList.size() > 0) {
                Map accountMap = accountList.get(0);
                //add by wangbaoqiang增加客户id的判断
                String prodInstRegionId = "";
                //prodInstID = accountMap.get("prodInstId").toString();
                accountMap.put("routeId", accountMap.get("custId"));
                /*if (StringUtil.isEmpty(accountMap.get("prodInstId"))) {
                    accountMap.put("prodInstId", -1);
                }*/
                List<Map<String, Object>> customerList = new ArrayList<Map<String, Object>>();
                try {
                    customerList = accountDao.getCustomer(accountMap);
                    if (customerList.size() == 0) {
                        msg.setResultCode(ResultCode.ACCOUNT_ERROR_003);
                        msg.setMessage("账户对应的客户在计费不存在【CUST_ID】:" + accountMap.get("custId"));
                        return -1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    msg.setResultCode(ResultCode.ACCOUNT_ERROR_004);
                    exceptionMsg = setErrorMsg(e.getMessage(), "取帐户对应的客户的标识出错");
                    msg.setMessage(exceptionMsg);
                    return -1;
                }//add end
                Map customerMap = customerList.get(0);
                prodInstRegionId = customerMap.get("regionId").toString();

                long acctId = Long.parseLong(accountMap.get("acctId")
                        .toString());
                accountMap.put("routeId", acctId);
                // acct_id=acctId;//全局使用acct_id
                int aCount = accountDao.getAccountById(accountMap);
                accountMap.put("statusCd", 1);
                if (serviceOfferId == 2010100000) {
                    if (aCount > 0) {
                        msg.setResultCode(ResultCode.ACCOUNT_ERROR_005);
                        msg.setMessage("新增中账户已经存在ACCT_ID:" + acctId);
                        return -1;
                    }
                    accountMap.put("regionId", prodInstRegionId);
                    //add by wangbaoqiang begin
                    try {
                        accountMap.put("prodInstId", 0);
                        accountDao.insertAccount(accountMap);
                        //@nieqt增加账户时，增加A_ACCT_OWE_STATE的插入操作
                        Map aAcctOweState=new HashMap();
                        aAcctOweState.put("acctId", acctId);
                        aAcctOweState.put("modValue", "0"+(acctId % 7));
                        aAcctOweState.put("ty", yyyy_sdf.format(new Date()));
                        acctMapperDao.insertaAcctOweState(aAcctOweState);

                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加帐户表时出错");
                        msg.setResultCode(ResultCode.ACCOUNT_ERROR_006);
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }

                    accountMap.put("action", 1);
                } else {
                    // 账户是否存在判断
                    if (aCount == 0) {
                        msg.setResultCode(ResultCode.ACCOUNT_ERROR_007);
                        msg.setMessage("修改账户不存在ACCT_ID:" + acctId);
                        return -1;
                    }
                    //accountMap.put("routeId", routeCustId);

                    //add by wangbaoqiang begin
                    List<Map<String, Object>> oldAccountList = accountDao.getAccoutForAccNum(accountMap);
                    try {
                        Map oldAcountMap = oldAccountList.get(0);
                        Long servProdId = prodinstDao.getSeq("SEQ_ACCOUNT_HIS_ID"); //获取账户序列
                        oldAcountMap.put("accountSeq", servProdId);
                        int judge = hisService.insertAccountHis(oldAcountMap);//插入账户的历史表
                        if (judge < 0) {
                            msg.setResultCode(ResultCode.ACCOUNT_ERROR_008);
                            msg.setMessage("插入账户历史表失败");
                            return -1;
                        }
                        accountMap.remove("prodInstId");
                        accountDao.updateAccount(accountMap);
                        accountMap.put("prodInstId", oldAcountMap.get("prodInstId"));
                        accountMap.put("action", 2);
                    } catch (Exception e) {
                        String exceptionMsg = "";
                        msg.setResultCode(ResultCode.ACCOUNT_ERROR_009);
                        exceptionMsg = setErrorMsg(e.getMessage(), "修改帐户表时出错");
                        msg.setMessage(exceptionMsg);
                        e.printStackTrace();
                        return -1;
                    }//add end;
                }
                if (StringUtil.isEmpty(accountMap.get("statusDate"))) {
                    accountMap.put("executetime", d.format(new Date()));
                } else {
                    accountMap.put("executetime", d.format(df1.parse(accountMap.get("statusDate").toString())));
                }
                //acctobjList1.add(accountMap);
                SynMapContextHolder.addMap("acctobjList1", accountMap);

            }
        } catch (Exception e) {
            String exceptionMsg = "";
            msg.setResultCode(ResultCode.ACCOUNT_ERROR_010);
            exceptionMsg = setErrorMsg(e.getMessage(), "处理客户ORD_ACCOUNT失败");
            msg.setMessage(exceptionMsg);
            e.printStackTrace();
            return -1;
        }
        // 3.2 ord_payment_plan 判断是否存在paymentPlan 存在则失效再新增
        try {
            List<Map<String, Object>> smsPaymentList = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> paymentPlanList = ordBillDao
                    .selectOrdPaymentPlan(map);

            Map paymentMap = new HashMap();
            //add by wangbaoqiang begin
            if (paymentPlanList.size() > 1) {
                msg.setResultCode(ResultCode.ACCOUNT_ERROR_011);
                msg.setMessage("取支付方案接口表ord_payment_plan数据时出错:记录有多条");
                return -1;
            } //add end;
            if (paymentPlanList.size() > 0) {
                paymentMap = paymentPlanList.get(0);
                if (StringUtil.isEmpty(paymentMap.get("statusDate"))) {
                    paymentMap.put("executetime", d.format(new Date()));
                } else {
                    paymentMap.put("executetime", d.format(df1.parse(paymentMap.get(
                            "statusDate").toString())));
                }
                long acctId = Long.parseLong(paymentMap.get("acctId")
                        .toString());
                paymentMap.put("routeId", paymentMap.get("acctId"));
                List<Map<String, Object>> oldPaymentPlanList = accountDao
                        .getPaymentPlanByID(paymentMap);
                if (oldPaymentPlanList.size() > 0) {
                    //add by wangbaoqiang 若ord表有記錄，失效所有的生效记录  begin
                    for (int i = 0; i < oldPaymentPlanList.size(); i++) {
                        Map oldPaymentMap = oldPaymentPlanList.get(i);
                        if (!StringUtil.isEmpty(oldPaymentMap
                                .get("paymentPlanId"))) {
                            // 存在则失效
                            oldPaymentMap.put("statusCd", 2);
                            oldPaymentMap.put("expDate",
                                    paymentMap.get("effDate"));
                            try {
                                accountDao.updatePaymentPlan(oldPaymentMap);
                            } catch (Exception e) {
                                e.printStackTrace();
                                String exceptionMsg = "";
                                exceptionMsg = setErrorMsg(e.getMessage(), "获取帐户老支付方案表时出错");
                                msg.setResultCode(ResultCode.ACCOUNT_ERROR_013);
                                msg.setMessage(exceptionMsg);
                                return -1;
                            }
                            oldPaymentMap.put("executetime", paymentMap.get("executetime"));
                            oldPaymentMap.put("action", 2);
                            SynMapContextHolder.addMap("paymentPlanobjList1", oldPaymentMap);
                        }
                    }
                }
                paymentMap.put("statusCd", 1);
                //paymentMap.put("routeId", routeCustId);
                try {
                    accountDao.insertPaymentPlan(paymentMap);
                } catch (Exception e) {
                    e.printStackTrace();
                    String exceptionMsg = "";
                    exceptionMsg = setErrorMsg(e.getMessage(), "增加帐户支付方案表时出错");
                    msg.setResultCode(ResultCode.ACCOUNT_ERROR_014);
                    msg.setMessage(exceptionMsg);
                    return -1;
                }
                paymentMap.put("action", 1);
                SynMapContextHolder.addMap("paymentPlanobjList1", paymentMap);
            }
        } catch (Exception e) {
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理账户ORD_PAYMENT_PLAN失败");
            msg.setResultCode(ResultCode.ACCOUNT_ERROR_015);
            msg.setMessage(exceptionMsg);
            e.printStackTrace();
            return -1;
        }
        // 3.3 ord_ext_acct
        try {
            List<Map<String, Object>> extAcctList = ordBillDao
                    .selectOrdExtAcct(map);
            //add by wangbaoqiang begin
            if (extAcctList.size() > 1) {
                msg.setResultCode(ResultCode.ACCOUNT_ERROR_016);
                msg.setMessage("取帐户外部支付帐号接口表ord_ext_acct数据时出错:记录有多条");
                return -1;
            }
            Map extAcctMap = new HashMap();
            long routeId;
            long extAcctId;
            if (extAcctList.size() > 0) {
                extAcctMap = extAcctList.get(0);
                extAcctId = Long.parseLong(extAcctMap.get("extAcctId").toString());
                routeId = routeServiceDao.getRouteIdForExtAcct(archGrpId, orderItemId, extAcctId, msg);
                if (routeId < 0) {
                    msg.setResultCode(ResultCode.ACCOUNT_ERROR_017);
                    msg.setMessage("取外部账户路由失败【extAcctId】：" + extAcctId);
                    return -1;
                }
                int operType = Integer.parseInt(extAcctMap.get(
                        "operType").toString());
                if (StringUtil.isEmpty(extAcctMap.get("statusDate"))) {
                    extAcctMap.put("executetime", d.format(new Date()));
                } else {
                    extAcctMap.put("executetime", d.format(df1.parse(extAcctMap.get("statusDate").toString())));
                }
                extAcctMap.put("routeId", routeId);
                List<Map<String, Object>> extAcctList1 = accountDao
                        .getExtAcctID(extAcctMap);
                if (extAcctList1.size() == 0) {
                    extAcctList1 = accountDao.selectExtAcctID(extAcctMap);
                }
                if (extAcctList1.size() == 0) {
                    try {
                        extAcctMap.put("statusCd", 1);
                        accountDao.insertExtAcct(extAcctMap);
                    } catch (Exception e) {
                        String exceptionMsg = "";
                        msg.setResultCode(ResultCode.ACCOUNT_ERROR_018);
                        exceptionMsg = setErrorMsg(e.getMessage(), "增加外部支付帐号表时出错");
                        msg.setMessage(exceptionMsg);
                        e.printStackTrace();
                        return -1;
                    }//end
                    extAcctMap.put("action", 1);
                    SynMapContextHolder.addMap("extAcctobjList1", extAcctMap);
                } else if (extAcctList1.size() == 1) {

                    Map extMap = extAcctList1.get(0);
                    Long servProdId = prodinstDao.getSeq("SEQ_EXT_ACCT_HIS_ID"); //获取外部支付账户序列
                    extMap.put("extAcctSeq", servProdId);
                    int judge = 0;//插入外部支付账户的历史表
                    try {
                        judge = hisService.insertExtAcctHis(extMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                        String exceptionMsg = "";
                        msg.setResultCode(ResultCode.ACCOUNT_ERROR_019);
                        exceptionMsg = setErrorMsg(e.getMessage(), "插入外部支付账户历史表失败");
                        msg.setMessage(exceptionMsg);
                        return -1;
                    }

                    try {
                        Map updateExtAcctMap = new HashMap();
                        updateExtAcctMap.put("payChannel", extAcctMap.get("payChannel"));
                        updateExtAcctMap.put("payAcctCode", extAcctMap.get("payAcctCode"));
                        updateExtAcctMap.put("payAcctName", extAcctMap.get("payAcctName"));
                        updateExtAcctMap.put("acctOwnerOrg", extAcctMap.get("acctOwnerOrg"));
                        updateExtAcctMap.put("acctOwnerOrgBranch", extAcctMap.get("acctOwnerOrgBranch"));
                        updateExtAcctMap.put("ifContractQuickPay", extAcctMap.get("ifContractQuickPay"));
                        updateExtAcctMap.put("custId", extAcctMap.get("custId"));
                        updateExtAcctMap.put("statusDate", extAcctMap.get("statusDate"));
                        updateExtAcctMap.put("routeId", extMap.get("routeId"));
                        updateExtAcctMap.put("extAcctId", extMap.get("extAcctId"));
                        accountDao.updateExtAcct(updateExtAcctMap); // 失效时间
                    } catch (Exception e) {
                        String exceptionMsg = "";
                        msg.setResultCode(ResultCode.ACCOUNT_ERROR_020);
                        exceptionMsg = setErrorMsg(e.getMessage(), "修改外部支付帐号表时出错");
                        msg.setMessage(exceptionMsg);
                        e.printStackTrace();
                        return -1;
                    }
                    extAcctMap.put("action", 2);
                    extAcctMap.put("extAcctId", extMap.get("extAcctId"));
                    SynMapContextHolder.addMap("extAcctobjList1", extAcctMap);
                }
            }
        } catch (Exception e) {
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "处理账户ORD_EXT_ACCT失败");
            msg.setResultCode(ResultCode.ACCOUNT_ERROR_021);
            msg.setMessage(exceptionMsg);
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
    /**
     * @Author WangBaoQiang
     * @Description //断网40G复网操作
     * @Date 22:10 2019/9/20
     * @Param [map, msg]
     * @return int
    */
    public int doBreakOffer(Map map, Message msg,String prodType) throws Exception {
        Map prodSubMap = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        // 获取当月第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long offerDetailId = 0;
        String breakId = "";
        if ("1".equals(prodType)) {//40G断网复网
             breakId = "66666800";
        } else {//600元复网
            breakId = "66666666";
        }

        prodSubMap.put("objId", map.get("accProdInstId").toString());
        prodSubMap.put("routeId", map.get("routeId"));
        prodSubMap.put("prodInstId",map.get("accProdInstId"));
        prodSubMap.put("expDate", new Date());
        if ("1".equals(prodType)) {
            //取用户订购成员表
            List<Map<String, Object>> offerObjInstRelList = offerinstDao.getOfferObjfromObjId(prodSubMap);
            for (Map<String, Object> offerObjInstRelMap : offerObjInstRelList) {
                //取用户订购实例表
                List<Map<String, Object>> offerInstList = offerinstDao.getOfferInstIdFromNowDate(offerObjInstRelMap);
                if (offerInstList.size() > 0) {
                    Map offerInstMap = offerInstList.get(0);
                    long offerId = Long.parseLong(offerInstMap.get("offerId").toString());
                    //取断网标示
                    List<Map<String, Object>> tifBreakDisct = ordBillDao.selectTifBreakDisct(offerId);
                    if (tifBreakDisct.size() > 0) {
                        Map breakMap = tifBreakDisct.get(0);
                        breakId = breakMap.get("breakDisctId").toString();
                        break;
                    }

                }
            }

        }
        if ("2".equals(prodType)) {
            prodSubMap.remove("expDate");
            //取当月第一天
            Date firstDate = DateUtils.getMonthFirstDay(0, 0);
            //获取下月的第一天
            Date lastDate =  DateUtils.getNextMonthFirstDay(new Date());
            boolean flag =  isEffectiveDate(sdf.parse(String.valueOf(map.get("statusDate"))), firstDate, lastDate);
           if(flag){//一个月只能订购一次
               //取用户订购成员表
               List<Map<String, Object>> offerObjInstRelList = offerinstDao.getOfferObjfromObjId(prodSubMap);
               for (Map<String, Object> offerObjInstRelMap : offerObjInstRelList) {
                   //取用户订购实例表
                   List<Map<String, Object>> offerInstList = offerinstDao.getOfferInstIdFromNowDate(offerObjInstRelMap);
                   if (offerInstList.size() > 0) {
                       Map offerInstMap = offerInstList.get(0);
                       //本月如果订购了就返回
                       if ("66666666".equals(String.valueOf(offerInstMap.get("offerId")))) {
                           logger.error("当月已订购了该复网销售品，不允许再次订购");
                           return 1;
                       }
                   }
               }
           }
        }

        try {
            Map tifObjectIdMap = new HashMap();
            List<Map<String, Object>> tifObjectIdList = ordBillDao.selectTifObjectId(Long.parseLong(breakId), "1");
            if (tifObjectIdList.size() > 0) {
                tifObjectIdMap = tifObjectIdList.get(0);
                offerDetailId = Long.parseLong(tifObjectIdMap.get("DISCT_OBJECT_ID_BILL").toString());
            } else {
                tifObjectIdList = billMapperDao.selectOfferRoleId(Long.parseLong(breakId), "1");
                if (tifObjectIdList.size() > 0) {
                    tifObjectIdMap = tifObjectIdList.get(0);
                    offerDetailId = Long.parseLong(tifObjectIdMap.get("OFFER_OBJ_REL_ID").toString());
                } else {
                    msg.setResultCode(ResultCode.PRODSUB_ERROR_012);
                    msg.setMessage("取对应的套餐对象编码时出错【breakId】:" + breakId);
                    return -1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "取对应的套餐对象编码时出错");
            msg.setResultCode(ResultCode.PRODSUB_ERROR_013);
            msg.setMessage(exceptionMsg);
            return -1;
        }

        List<Map<String, Object>> prodInstList = prodinstDao.getProdInstOBJ(prodSubMap);
        Map prodInstMap = new HashMap();
        if (prodInstList.size() > 0) {
            prodInstMap = prodInstList.get(0);
        }
        //失效时间为当前时间加一个月
        //获取下月的第一天
        Date expDate =  DateUtils.getNextMonthFirstDay(new Date());
        //Calendar rightNow = Calendar.getInstance();
        Date effDate = new Date();
        /*rightNow.setTime(effDate);
        rightNow.add(Calendar.MONTH, 1);
        Date expDate = rightNow.getTime();*/
        String strEffDate = sdf.format(effDate);
        String strExpDate = sdf.format(expDate);
        Map instOfferInstMap = new HashMap();
        try {
            //增加商品实例表
            long offerInstId = prodinstDao.getSeq("SEQ_OFFER_INST_ID");
            instOfferInstMap.put("offerInstId", offerInstId);
            instOfferInstMap.put("offerId", breakId);
            instOfferInstMap.put("ownerCustId", prodInstMap.get("ownerCustId"));
            instOfferInstMap.put("effDate", strEffDate);
            instOfferInstMap.put("expDate", strExpDate);
            instOfferInstMap.put("offerType", 14);
            instOfferInstMap.put("offerAgreeId", 1);
            instOfferInstMap.put("lanId", prodInstMap.get("lanId"));
            instOfferInstMap.put("regionId", prodInstMap.get("regionId"));
            instOfferInstMap.put("statusCd", 1000);
            instOfferInstMap.put("createDate", strEffDate);
            instOfferInstMap.put("statusDate", strEffDate);
            instOfferInstMap.put("updateDate", strEffDate);
            instOfferInstMap.put("busiModDate", strEffDate);
            instOfferInstMap.put("createOrgId", 1);
            instOfferInstMap.put("lastOrderItemId", 0);
            instOfferInstMap.put("remark", "复网增加");
            instOfferInstMap.put("routeId", map.get("routeId"));
            offerinstDao.insertOfferInst(instOfferInstMap);
            instOfferInstMap.put("action", 1);
            instOfferInstMap.put("executetime", d.format(new Date()));
            SynMapContextHolder.addMap("offerInstobjList1", instOfferInstMap);
            //增加商品成员表
            Map offerInstRelMap = new HashMap();
            long offerInstRelId = prodinstDao.getSeq("SEQ_OFFER_OBJ_INST_REL_ID");
            offerInstRelMap.put("offerObjInstRelId", offerInstRelId);
            offerInstRelMap.put("offerInstId", offerInstId);
            offerInstRelMap.put("objType", 100000);
            offerInstRelMap.put("objId", map.get("accProdInstId"));
            offerInstRelMap.put("effDate", strEffDate);
            offerInstRelMap.put("expDate", strExpDate);
            offerInstRelMap.put("statusCd", 1000);
            offerInstRelMap.put("lastOrderItemId", 0);
            offerInstRelMap.put("offerObjRelId", offerDetailId);
            offerInstRelMap.put("roleId", 1);
            offerInstRelMap.put("routeId", map.get("routeId"));
            offerInstRelMap.put("remark", "复网增加");
            offerInstRelMap.put("action", 1);
            offerInstRelMap.put("executetime", d.format(new Date()));
            offerinstDao.insertOfferObjInstRel(offerInstRelMap);
            SynMapContextHolder.addMap("offerObjInstobjList1", offerInstRelMap);
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "增加复网销售品失败");
            msg.setResultCode(ResultCode.PRODSUB_ERROR_014);
            msg.setMessage(exceptionMsg);
            return -1;
        }

        try {
            List<Map<String, Object>> prodInstAttrList =  prodinstDao.selectProdInstAttr1015(prodSubMap);
            if (prodInstAttrList.size() > 0) {
                for (Map<String, Object> prodInstAttrMap : prodInstAttrList) {
                    prodInstAttrMap.put("attrValue", 1);
                    prodInstAttrMap.put("expDate", strExpDate);
                    prodInstAttrMap.put("statusDate", strEffDate);
                    prodInstAttrMap.put("updateDate", strEffDate);
                    prodInstAttrMap.put("statusCd", 1100);
                    prodInstAttrMap.put("action", 2);
                    prodInstAttrMap.put("executetime", d.format(new Date()));
                    prodinstDao.updateProdInstAttrNet(prodInstAttrMap);
                    SynMapContextHolder.addMap("prodInstAttrobjList1", prodInstAttrMap);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "修改1015断网属性失败");
            msg.setResultCode(ResultCode.PRODSUB_ERROR_015);
            msg.setMessage(exceptionMsg);
            return -1;
        }
        return 1;
    }
    /**
     * @Author WangBaoQiang
     * @Description //处理激活修改生效时间
     * @Date 15:05 2019/9/24
     * @Param [map, msg]
     * @return int
    */
    public int doActiveOfferTime(Map map, Message msg) throws Exception {
        SimpleDateFormat yyyyMMddHHmmss_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String statusDate=sdf.format(new Date());
        Map updateOfferIntRelMap = new HashMap();
        Map updateOfferIntMap = new HashMap();
        Map updateOfferIntAttrMap = new HashMap();
        try {
            map.put("objId", String.valueOf(map.get("prodInstId")));
            List<Map<String, Object>> offerObjInstRelList = offerinstDao.getOfferObjfromObjId(map);
            for (Map<String, Object> offerObjInstRel : offerObjInstRelList) {
                //标资不更新
                offerObjInstRel.put("routeId",map.get("routeId"));//用户级的套餐prod_inst的路由一致
                List<Map<String, Object>> offerProdInstList = offerinstDao.getOfferInstId(offerObjInstRel);
                Map offerProdInstMap = offerProdInstList.get(0);
                String offerId = offerProdInstMap.get("offerId").toString();
                if (offerId.length() == 6 && "61".equals(offerId.substring(0, 2))) {
                    continue;
                }
                updateOfferIntRelMap.put("effDate", map.get("statusDate"));
                updateOfferIntRelMap.put("routeId", map.get("routeId"));
                updateOfferIntRelMap.put("offerObjInstRelId", offerObjInstRel.get("offerObjInstRelId"));
                updateOfferIntRelMap.put("remark", "用户激活处理");
                offerinstDao.updateOfferObjInstRelForActive(updateOfferIntRelMap);
                offerObjInstRel.put("effDate", map.get("statusDate"));
                offerObjInstRel.put("action",2);
                offerObjInstRel.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
                SynMapContextHolder.addMap("offerObjInstobjList1", offerObjInstRel);

                //更改offer_inst
                updateOfferIntMap.put("effDate", map.get("statusDate"));
                updateOfferIntMap.put("routeId", map.get("routeId"));
                updateOfferIntMap.put("offerInstId", offerObjInstRel.get("offerInstId"));
                updateOfferIntMap.put("remark", "用户激活处理");
                offerinstDao.updateOfferInstForActive(updateOfferIntMap);
                offerProdInstMap.put("effDate",map.get("statusDate"));
                offerProdInstMap.put("action",2);
                offerProdInstMap.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
                SynMapContextHolder.addMap("offerInstobjList1", offerObjInstRel);
                //取出offer_inst_attr更新
                offerObjInstRel.put("statusDate",map.get("statusDate"));
                List<Map<String, Object>> offerInstAttrList = offerinstDao.getOfferInstAttr(offerObjInstRel);
                for (Map<String, Object> offerInstAttr : offerInstAttrList) {
                    updateOfferIntAttrMap.put("effDate", map.get("statusDate"));
                    updateOfferIntAttrMap.put("routeId", map.get("routeId"));
                    updateOfferIntAttrMap.put("offerInstAttrId", offerInstAttr.get("offerInstAttrId"));
                    updateOfferIntAttrMap.put("remark", "用户激活处理");
                    offerinstDao.updateOfferInstAttr(updateOfferIntAttrMap);
                    offerInstAttr.put("effDate", map.get("statusDate"));
                    offerInstAttr.put("action",2);
                    offerInstAttr.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
                    SynMapContextHolder.addMap("offerInstAttrobjList1", offerInstAttr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String exceptionMsg = "";
            exceptionMsg = setErrorMsg(e.getMessage(), "用户激活处理销售品失败");
            msg.setResultCode(ResultCode.PRODINST_U_ERROR_044);
            msg.setMessage(exceptionMsg);
            return -1;

        }

        return 1;
    }
    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author wangbaoqiang
     */
    public  boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        /*if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }*/

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * @Author WangBaoQiang
     * @Description //mq过户发送
     * @Date 20:04 2019/11/9
     * @Param [sendMap, msg]
     * @return void
    */
    public void sendChangeAcctMsg(Map sendMap,Message msg) throws Exception{
        Map msgMap =new HashMap();
        msgMap.put("methodName","acctTransfer");
        JSONObject parameterJson=new JSONObject();
        parameterJson.put("acctTransfer",sendMap);
        msgMap.put("parameter",parameterJson);
        msgMap.put("parameterName","com.asiainfo.account.model.domain.AcctTransfer");
        if(MqToolAcctChange.send(TopicType.xgzwjh,msgMap,msg)!=1){
            logger.error("发送过户消息队列数据异常！！" + JSONObject.toJSONString(sendMap));
            throw new Exception();
        }else{
            msg.setMessage("处理成功，消息key："+msg.getMessage());
        }

    }
/**
 * @Author WangBaoQiang
 * @Description //策略中心发送短信
 * @Date 12:12 2020/1/1
 * @Param [smsInfo, smsId]
 * @return int
*/
public int sendMsgToPlca(String smsInfo, long smsId) {
    SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
    Date date = new Date();
    String url = propertiesUtil.readProperty("plca.otherremind");
    List<Map<String, Object>> tifSmsInfoList = ordBillDao.selectTifSmsContrast(smsId);
    for (Map<String, Object> tifSmsInfoMap : tifSmsInfoList) {
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObjectList = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonObject.put("accNbr", tifSmsInfoMap.get("accNbr"));
            jsonObject.put("remindType", "查询中心详单查询查询提醒");
            jsonObject.put("remindCode", "JLJF000042");
            jsonObject.put("remindCreateTime", d.format(date));
            jsonObject.put("remindText", smsInfo);
            jsonObject.put("channelId", "1");
            jsonObject.put("businessId", d.format(date));
            jsonObject.put("remindServId", tifSmsInfoMap.get("servId"));
            jsonArray.add(jsonObject);
            jsonObjectList.put("msgInfo", jsonArray);

            logger.info("策略发送短信请求报文：" + jsonObjectList.toJSONString());
            String result = HttpRequestUtil.callRemoteForPostByJSON(url, jsonObjectList.toJSONString());
            logger.info("策略发送短信返回报文：" + result);
            JSONObject json = (JSONObject) JSONObject.parse(result);
        } catch (Exception ex) {
            logger.error("调用接口失败，返回码：" + ex.getMessage());
            return -1;
        }
    }

    return 1;
}
}