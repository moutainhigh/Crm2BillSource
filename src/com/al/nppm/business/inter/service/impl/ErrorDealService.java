package com.al.nppm.business.inter.service.impl;

import com.al.nppm.business.account.dao.IProdInstMapper;
import com.al.nppm.business.account.dao.IRouteMapper;
import com.al.nppm.business.account.dao.OfferInstMapper;
import com.al.nppm.business.acctrh.dao.SmsInfoMapper;
import com.al.nppm.business.core.SynMapContextHolder;
import com.al.nppm.business.inter.service.IErrorDealService;
import com.al.nppm.business.syntomq.datasyn.DataSynDeal;
import com.al.nppm.business.syntomq.datasyn.Msg;
import com.al.nppm.business.syntomq.tool.MqToolActiveUser;
import com.al.nppm.business.syntomq.tool.TopicType;
import com.al.nppm.common.utils.HttpRequestUtil;
import com.al.nppm.common.utils.PropertiesUtil;
import com.al.nppm.common.utils.StringUtil;
import com.al.nppm.model.Message;
import com.al.nppm.ord.ordbill.dao.OrdBillMapper;
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

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangBaoQiang
 * @ClassName: ErrorDealService
 * @description: 处理错单程序
 * @date 2019/9/1710:29
 * @Version 1.0
 */
@Service("errorDealService")
public class ErrorDealService implements IErrorDealService {
    private static PropertiesUtil propertiesUtil=new PropertiesUtil("config/sysConfig.properties");
    @Autowired
    OrdBillMapper ordBillMapperDao;
    @Autowired
    IProdInstMapper prodInstMapperDao;
    @Autowired
    IRouteMapper routeMapperDao;
    @Autowired
    SmsInfoMapper smsInfoMapperDao;
    @Autowired
    OfferInstMapper offerInstMapperDao;
    private static Logger logger = Logger.getLogger(ErrorDealService.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void errorDealServiceTask(String[] args) throws Exception {
        Date date = new Date();
        logger.debug("----------定时执行errorDealServiceTask---------------" + sdf.format(date).toString());
        //处理业务逻辑。  flag=1处理成功，其他失败
        int flag = 1;
        Message msg = new Message();
        Map<String, Object> updateMap = new HashMap<String, Object>();
        //处理账务关系不存在的错单，过户的工单有的时候会有三条，需要删掉一条。
        List<Map<String, Object>> orderList = ordBillMapperDao.selectOrdBillError("PRODACCT_ERROR_013");
        if (orderList.size() > 0) {
            for (Map<String, Object> orderMap : orderList) {
                long archGrpId = Long.parseLong(orderMap.get("ARCH_GRP_ID").toString());
                long procCnt = Long.parseLong(String.valueOf(orderMap.get("PROC_CNT")));
                long listCnt = ordBillMapperDao.selectOrdBillProdInstAcctRel(archGrpId);
                WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

                    try {
                        if (listCnt > 0) {
                        flag = doErrorDeal(orderMap, msg);
                        }
                        String strResultmsgString;
                        if (flag < 0) {
                            // 回滚
                            transactionManager.rollback(status);
                        } else {
                            Map map = new HashMap();
                            map.put("archGrpId", orderMap.get("ARCH_GRP_ID"));
                            if (procCnt < 199) {
                                map.put("procFlag", 0);
                                ordBillMapperDao.updateOrdBill(map);
                            }
                            transactionManager.commit(status);
                        }
                        //}

                    } catch (Exception e) {
                        e.printStackTrace();
                        transactionManager.rollback(status);
                        logger.error("处理失败：" + e.getMessage());
                    }

            }

        }
        //处理套餐变更找不到商品实例
        flag = 1;
        List<Map<String, Object>> offergroupError007 = ordBillMapperDao.selectOrdBillCommonError("OFFERGROUP_ERROR_007");
        if (offergroupError007.size() > 0) {
            for (Map<String, Object> orderMap : offergroupError007) {
                long archGrpId = Long.parseLong(orderMap.get("ARCH_GRP_ID").toString());
                long procCnt = Long.parseLong(String.valueOf(orderMap.get("PROC_CNT")));
                String notes = String.valueOf(orderMap.get("notes"));
                int pos = notes.indexOf("。");
                String offerInstId = notes.substring(46, pos);
                List<Map<String,Object>> crmOfferList =  smsInfoMapperDao.getCrmOfferInstInfo(Long.parseLong(offerInstId));

                    WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                    DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
                    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                    TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

                    try {
                        for (Map<String, Object> crmMap : crmOfferList) {
                            crmMap.put("archGrpId", archGrpId);
                             flag = doOfferGroupErrorDeal(crmMap, msg);
                            if (flag ==1) {
                                logger.info("计费错单【OFFERGROUP_ERROR_007】处理补录商品实例成功【offerInstId】：" + offerInstId);
                            }
                        }
                        if (flag < 0) {
                            // 回滚
                            transactionManager.rollback(status);
                        } else {
                            Map map = new HashMap();
                            map.put("archGrpId", orderMap.get("ARCH_GRP_ID"));
                            if (procCnt < 199) {
                                map.put("procFlag", 0);
                                ordBillMapperDao.updateOrdBill(map);
                            }
                            transactionManager.commit(status);

                        }
                        //}

                    } catch (Exception e) {
                        e.printStackTrace();
                        transactionManager.rollback(status);
                        logger.error("处理失败【archGrpId】：" + archGrpId + "," + e.getMessage());
                    }
            }

        }

        flag = 1;
        List<Map<String, Object>> offerinsError001 = ordBillMapperDao.selectOrdBillCommonError("OFFERINS_ERROR_001");
        if (offerinsError001.size() > 0) {
            for (Map<String, Object> orderMap : offerinsError001) {
                long archGrpId = Long.parseLong(orderMap.get("ARCH_GRP_ID").toString());
                long procCnt = Long.parseLong(String.valueOf(orderMap.get("PROC_CNT")));
                String notes = String.valueOf(orderMap.get("notes"));
                int pos = notes.indexOf("。");
                String offerInstId = notes.substring(43, pos);
                List<Map<String,Object>> crmOfferList =  smsInfoMapperDao.getCrmOfferInstInfo(Long.parseLong(offerInstId));

                WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

                try {
                    for (Map<String, Object> crmMap : crmOfferList) {
                        crmMap.put("archGrpId", archGrpId);
                        flag = doOfferGroupErrorDeal(crmMap, msg);
                        if (flag ==1) {
                            logger.info("计费错单【OFFERINS_ERROR_001】处理补录商品实例成功【offerInstId】：" + offerInstId);
                        }
                    }
                    if (flag < 0) {
                        // 回滚
                        transactionManager.rollback(status);
                    } else {
                        Map map = new HashMap();
                        map.put("archGrpId", orderMap.get("ARCH_GRP_ID"));
                        if (procCnt < 199) {
                            map.put("procFlag", 0);
                            ordBillMapperDao.updateOrdBill(map);
                        }
                        transactionManager.commit(status);

                    }
                    //}

                } catch (Exception e) {
                    e.printStackTrace();
                    transactionManager.rollback(status);
                    logger.error("处理失败【archGrpId】：" + + archGrpId + "," +  e.getMessage());
                }
            }

        }
    }
    public int doOfferGroupErrorDeal(Map itemMap, Message msg) throws Exception {
        try {
            Long routeId = null;
            long offerInstId = Long.parseLong(String.valueOf(itemMap.get("offerInstId")));
            routeId = routeMapperDao.getOfferInstRoute(offerInstId);
            if (StringUtil.isEmpty(String.valueOf(routeId))) {
                if (routeMapperDao.insertOfferInstRoute(itemMap) < 1 ) {
                    logger.error("错单处理插入商品路由失败" + itemMap.get("offerInstId"));
                    return -1;
                }
            }
            itemMap.put("effDate", new Date());
            itemMap.put("remark", "计费错单补录销售品实例");
            SynMapContextHolder.remove();
            SynMapContextHolder.init();
            Map<String, List<?>> synMap = new HashMap<String, List<?>>();
            SynMapContextHolder.initSynMap(synMap);
            SynMapContextHolder.put("archGrpId", itemMap.get("archGrpId"));
            List<Map<String, Object>> regionList = ordBillMapperDao.selectTifOrgContrast(itemMap);
            if (regionList.size() > 0) {
                Map regionMap = new HashMap();
                regionMap = regionList.get(0);
                String areaCode = regionMap.get("areaCode").toString();
                String lanId = areaCode.substring(1, 4);
                itemMap.put("regionId",regionMap.get("orgIdBill").toString());
                itemMap.put("lanId", lanId);

            }
            List<Map<String,Object>> offerList = offerInstMapperDao.getOfferInstId(itemMap);
            if (offerList.size() == 0){
                if (offerInstMapperDao.insertOfferInst(itemMap) < 1) {
                    logger.error("错单处理插入商品实例失败" + itemMap.get("offerInstId"));
                    return -1;
                }
                SynMapContextHolder.put("routeCustId", itemMap.get("ownerCustId").toString());
                itemMap.put("action", 1);
                addMap("offerInstobjList1", itemMap);
                addMsg(synMap);
            }
        } catch (Exception e) {
            logger.error("错单处理群成员失败" + itemMap.get("offerInstId"));
            return -1;
        }
        return 1;
    }
    public void dealActiveServiceTask(String[] args) throws Exception {
        Date date = new Date();
        logger.debug("----------定时执行dealActiveServiceTask---------------" + sdf.format(date).toString());
        //处理业务逻辑。  flag=1处理成功，其他失败
        int flag = -1;
        Map<String, Object> updateMap = new HashMap<String, Object>();
        List<Map<String, Object>> orderList = ordBillMapperDao.selectTifActiveUser("END");
        if (orderList.size() > 0) {
            for (Map<String, Object> orderMap : orderList) {
                Message msg = new Message();
                WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                try {
                    flag = doActiveDeal(orderMap, msg);
                    String strResultmsgString;
                    if (flag < 0) {
                        // 回滚
                        transactionManager.rollback(status);
                        ordBillMapperDao.updateTifNoActiveUse(orderMap);
                    } else {
                        transactionManager.commit(status);
                        if (!StringUtil.isEmpty(msg.getMessage())) {
                            orderMap.put("remark", msg.getMessage());
                            ordBillMapperDao.updateTifNoActiveUse(orderMap);
                        }

                    }
                    //}

                } catch (Exception e) {
                    e.printStackTrace();
                    transactionManager.rollback(status);
                    ordBillMapperDao.updateTifNoActiveUse(orderMap);
                    logger.error("老用户激活处理失败【prodInstId】：" + orderMap.get("prodInstId") + "," +  e.getMessage());
                }
            }
        }

    }

    @Override
    public int doErrorDeal(Map itemMap, Message msg) throws Exception {
        long archGrpId = Long.parseLong(itemMap.get("ARCH_GRP_ID").toString());
        if (ordBillMapperDao.delOrdBillProdInstAcctRel(archGrpId) < 0) {
            return -1;
        }
        return 1;
    }

    public int doActiveDeal(Map itemMap, Message msg) throws Exception {
        itemMap.put("statusDate", new Date());
        String prodInstId = String.valueOf(itemMap.get("prodInstId"));
        Boolean flag = false;
        long routeId = routeMapperDao.getProdInstRoute(Long.parseLong(prodInstId));
        if (routeId < 0) {
            Map map = new HashMap();
            logger.error("老用户激活取用户【prodInstId】：" + itemMap.get("prodInstId") + "路由失败");
            return -1;
        }
        itemMap.put("routeId", routeId);
        List<Map<String, Object>> prodInstStateList = prodInstMapperDao.getProdInstStateExtFromStateExt(itemMap);
        Map prodInstMap = prodInstStateList.get(0);
        String stopType = String.valueOf(prodInstMap.get("stopType"));
        String state = String.valueOf(prodInstMap.get("state"));
        //取用户号码
        List<Map<String,Object>>  prodInstList =  prodInstMapperDao.getProdInstOBJ(itemMap);
        Map prodInstAccMap = prodInstList.get(0);
        String acctNum = String.valueOf(prodInstAccMap.get("accNum"));
        //开始调用场景三的用户信息查询接口
        String url=propertiesUtil.readProperty("bon3.servInfo.url");
        JSONObject jsonObject=new JSONObject();
        JSONObject jsonObjectList = new JSONObject();
        jsonObject.put("areaCode","0431");
        jsonObject.put("valueType","1");
        jsonObject.put("value",acctNum);
        jsonObject.put("queryType","1");
        jsonObjectList.put("stdCcrQueryServ", jsonObject);
        try {
            logger.info("场景三的用户信息查询请求报文：" + jsonObjectList.toJSONString());
            String result = HttpRequestUtil.callRemoteForPostByJSON(url, jsonObjectList.toJSONString());
            logger.info("场景三的用户信息查询返回报文：" + result);
            JSONObject json = (JSONObject) JSONObject.parse(result);
            if (!"0".equals(json.getString("errorCode"))) {
                logger.error("调用接口失败，返回码："+json.getString("errorCode"));
                return -1;
            }
            JSONArray array = json.getJSONObject("stdCcaQueryServRes").getJSONArray("stdCcaQueryServList");
            if (array.size() > 0) {
                JSONObject resultJson = array.getJSONObject(0);
                String abmState = resultJson.getString("servState");
                if ("0".equals(abmState)
                        ||"2".equals(abmState)) {
                    flag = true;
                }
            }
        }catch (Exception ex){
            logger.error("调用接口失败，返回码："+ex.getMessage());
            return -1;
        }//结束
        if ((("0".equals(stopType) && "100000".equals(state)) ||
                ("130001".equals(stopType) && "120000".equals(state)))
                        &&flag) {
            Map<String, Object> sendMap = new HashMap();
            sendMap.put("prodInstId", itemMap.get("prodInstId"));
            sendActiveUserToPlanMsg(sendMap, msg);
        }
        return 1;
    }

    public void sendActiveUserToPlanMsg(Map sendMap, Message msg) throws Exception {
        Map msgMap = new HashMap();
        AccountRequest accountRequest = new AccountRequest();
        msgMap.put("methodName", "triggerBalanceActivation");
        //JSONObject parameterJson = new JSONObject();
        accountRequest.setExpandField(sendMap);
        //parameterJson.put("expandField", accountRequest);
        msgMap.put("parameter", accountRequest);
        msgMap.put("parameterName", "com.asiainfo.account.model.AccountRequest");
        if (MqToolActiveUser.send(TopicType.yhjh, msgMap, msg) != 1) {
            logger.error("发送激活用户到账务失败" + JSONObject.toJSONString(sendMap));
            throw new Exception();
        } else {
            msg.setMessage("处理成功，消息key：" + msg.getMessage());
        }

    }
    public class AccountRequest implements Serializable {
        private static final long serialVersionUID = -1951419648375508632L;
        private Map<String, Object> expandField;

        public AccountRequest() {
        }

        public Map<String, Object> getExpandField() {
            return this.expandField;
        }

        public void setExpandField(Map<String, Object> expandField) {
            this.expandField = expandField;
        }
    }
    /**
     * 组装ctg-mq消息，写user_info_oplog表
     *
     * @param key
     * @param map
     * @throws Exception
     */
    public void addMap(String key, Map map) throws Exception {
        try {
            SynMapContextHolder.addMap(key, map);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    /**
     * 写bill_send_msg_log表
     *
     * @param synMap
     * @throws Exception
     */
    public void addMsg(Map synMap) throws Exception {
        Map map = new HashMap();
        int flag = -1;
        Msg msginfo = null;
        try {
            List<?> table = DataSynDeal.mapToList(synMap);
            Map<String, Object> params = DataSynDeal.buildParam();
            msginfo = DataSynDeal.buildMsg(table, params);
            JSONObject msgdata = (JSONObject) JSON.toJSON(msginfo.getMap());
            msgdata.put("arch_grp_id", SynMapContextHolder.get("archGrpId"));//报文中加上arch_grp_id字段

            map.put("id", prodInstMapperDao.getSeq("SEQ_SEND_MSG"));
//            map.put("msg", JSON.toJSON(msginfo.getMap()).toString().getBytes());
            logger.debug("错单处理归档id:" + SynMapContextHolder.get("archGrpId") + ",mq消息id：" + map.get("id"));
            map.put("msg", msgdata.toString().getBytes("UTF-8"));
            map.put("status", 0);
            map.put("messageId", msginfo.getMap().get("message_id").toString());
            map.put("messageType", msginfo.getMap().get("message_type").toString());
            map.put("sendCount", 0);
            map.put("createDate", new Date());
            map.put("custId", SynMapContextHolder.get("routeCustId"));
            map.put("archGrpId", SynMapContextHolder.get("archGrpId"));
            prodInstMapperDao.insertSendMsg(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

}
