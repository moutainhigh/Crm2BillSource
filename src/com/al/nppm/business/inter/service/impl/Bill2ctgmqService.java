package com.al.nppm.business.inter.service.impl;

import com.al.nppm.business.account.dao.CtgMqMapper;
import com.al.nppm.business.account.dao.IProdInstMapper;
import com.al.nppm.business.account.dao.UserInfoBill2ctgmqMapper;
import com.al.nppm.business.core.SynMapContextHolder;
import com.al.nppm.business.syntomq.datasyn.DataSynDeal;
import com.al.nppm.business.syntomq.datasyn.Msg;
import com.al.nppm.model.Result;
import com.alibaba.fastjson.JSON;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bill2ctgmqService")
public class Bill2ctgmqService {
    private static Logger logger = Logger.getLogger(Bill2ctgmqService.class);
    @Autowired
    private UserInfoBill2ctgmqMapper userInfoBill2ctgmqMapper;

    @Autowired
    public IProdInstMapper prodinstDao;
    @Autowired
    public CtgMqMapper ctgMqMapper;


    /**
     * 个人定制套餐扫描接口表发送ctg-mq
     * @param args
     */
    public void scanUserInfoBill2ctgmq(String[] args){
        SimpleDateFormat yyyyMMddHHmmss_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        logger.debug("----------定时执行CRMTABLE---------------" + yyyyMMddHHmmss_sdf.format(date).toString());
        Map queryMap = new HashMap();
        List<Map<String, Object>> list = userInfoBill2ctgmqMapper.queryUserInfoBill2ctgmq(queryMap);//获取工单表数据 state默认为0 写消息表成功为1，失败为2
        if (list.size() > 0) {
            for(Map userInfoBillMap:list){
                Result result=new Result();

                WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

                try {
                    opUserInfo(userInfoBillMap,result);
                }catch (Exception ex){
                    logger.error(ex.getMessage());
                    result.setMessage(ex.getMessage());
                }
                if("0".equals(result.getStatus())){
                    userInfoBillMap.put("state",1);
                    userInfoBillMap.put("notes","消息key:"+result.getMessage());
                    transactionManager.commit(status);
                }else{
                    userInfoBillMap.put("state",2);//失败状态置为10G
                    userInfoBillMap.put("notes",result.getMessage());
                    transactionManager.rollback(status);
                }
                userInfoBill2ctgmqMapper.updateUserInfoBill2ctgmq(userInfoBillMap);
            }
        }
        logger.debug("----------定时任务完成---------------" + yyyyMMddHHmmss_sdf.format(date).toString());
    }

    public void opUserInfo(Map userInfoBillMap, Result result) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SynMapContextHolder.remove();
        SynMapContextHolder.init();
        Map<String, List<?>> synMap = new HashMap<String, List<?>>();
        SynMapContextHolder.initSynMap(synMap);
        SynMapContextHolder.put("archGrpId","GRDZ_"+userInfoBillMap.get("opSeq"));

        String tableName=String.valueOf(userInfoBillMap.get("tableName"));
        String tableColumn1=String.valueOf(userInfoBillMap.get("tableColumn1"));
        String routeId=String.valueOf(userInfoBillMap.get("routeId"));
        Integer opType=Integer.parseInt(userInfoBillMap.get("opType").toString());

        Map map=new HashMap();

        Map objmap=new HashMap();
        if("OFFER_INST".equals(tableName)){
            map.put("offerInstId",tableColumn1);
            map.put("routeId",routeId);
            objmap=ctgMqMapper.getOfferInst(map);
            if(objmap==null){
                result.setMessage("OFFER_INST未查询到数据，offerInstId："+tableColumn1+",routeId:"+routeId);
                return;
            }else{
                objmap.put("action",opType);
                objmap.put("executetime",sdf.format(new Date()));
                objmap.put("route_cust_id",objmap.get("ownerCustId"));
                SynMapContextHolder.put("routeCustId",objmap.get("ownerCustId"));
                addMap("offerInstobjList1",objmap,result);
            }

        }else if("OFFER_INST_ATTR".equals(tableName)){
            map.put("offerInstAttrId",tableColumn1);
            map.put("routeId",routeId);
            objmap=ctgMqMapper.getOfferInstAttr(map);
            if(objmap==null){
                result.setMessage("OFFER_INST_ATTR未查询到数据，offerInstAttrId："+tableColumn1+",routeId:"+routeId);
                return;
            }else {
                map.put("offerInstId",objmap.get("offerInstId"));
                Map offerInstMap=ctgMqMapper.getOfferInst(map);
                objmap.put("action",opType);
                objmap.put("executetime",sdf.format(new Date()));
                objmap.put("route_cust_id",offerInstMap.get("ownerCustId"));
                SynMapContextHolder.put("routeCustId",offerInstMap.get("ownerCustId"));
                addMap("offerInstAttrobjList1", objmap, result);
            }

        }else if("OFFER_OBJ_INST_REL".equals(tableName)){
            map.put("offerObjInstRelId",tableColumn1);
            map.put("routeId",routeId);
            objmap=ctgMqMapper.getOfferObjInstRel(map);
            if(objmap==null){
                result.setMessage("OFFER_OBJ_INST_REL未查询到数据，offerObjInstRelId："+tableColumn1+",routeId:"+routeId);
                return;
            }else {
                map.put("offerInstId",objmap.get("offerInstId"));
                Map offerInstMap=ctgMqMapper.getOfferInst(map);
                objmap.put("action",opType);
                objmap.put("executetime",sdf.format(new Date()));
                objmap.put("route_cust_id",offerInstMap.get("ownerCustId"));
                SynMapContextHolder.put("routeCustId",offerInstMap.get("ownerCustId"));
                addMap("offerObjInstobjList1", objmap, result);
            }
        }
        addMsg(synMap, result);
        result.setStatus("0");
//        result.setMessage(synMap.get("message_id").toString());
    }

    /**
     * 组装ctg-mq消息，写user_info_oplog表
     * @param key
     * @param map
     * @param result
     * @throws Exception
     */
    public void addMap(String key,Map map, Result result) throws Exception{
        try {
            SynMapContextHolder.addMap(key, map);
        }catch (Exception ex){
            result.setMessage("组装ctg-mq消息异常");
            logger.error(ex.getMessage());
            throw ex;
        }
    }

    /**
     * 写bill_send_msg_log表
     * @param synMap
     * @param result
     * @throws Exception
     */
    public void addMsg(Map synMap, Result result) throws Exception{
        Map map = new HashMap();
        int flag = -1;
        Msg msginfo = null;
        try {
            List<?> table = DataSynDeal.mapToList(synMap);
            Map<String, Object> params = DataSynDeal.buildParam();
            msginfo = DataSynDeal.buildMsg(table, params);
            JSONObject msgdata=(JSONObject) JSON.toJSON(msginfo.getMap());
            msgdata.put("arch_grp_id", SynMapContextHolder.get("archGrpId"));//报文中加上arch_grp_id字段
//            synMap.put("message_id",params.get("message_id"));
            result.setMessage(params.get("message_id").toString());//返回消息id

            map.put("id", prodinstDao.getSeq("SEQ_SEND_MSG"));
//            map.put("msg", JSON.toJSON(msginfo.getMap()).toString().getBytes());
            logger.debug("归档id:"+SynMapContextHolder.get("archGrpId")+",mq消息id："+map.get("id"));
            map.put("msg", msgdata.toString().getBytes("UTF-8"));
            map.put("status", 0);
            map.put("messageId", msginfo.getMap().get("message_id").toString());
            map.put("messageType", msginfo.getMap().get("message_type").toString());
            map.put("sendCount", 0);
            map.put("createDate",new Date());
            map.put("custId",SynMapContextHolder.get("routeCustId"));
            map.put("archGrpId",SynMapContextHolder.get("archGrpId"));
            prodinstDao.insertSendMsg(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setMessage("写消息表异常");
            throw  e;
        }
    }
}
