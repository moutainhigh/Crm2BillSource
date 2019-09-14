package com.al.nppm.business.inter.service.impl;

import com.al.nppm.business.account.dao.IProdInstMapper;
import com.al.nppm.business.account.dao.PlcaProdInstMapper;
import com.al.nppm.business.core.SynMapContextHolder;
import com.al.nppm.business.inter.http.state.statePublic;
import com.al.nppm.business.inter.service.IHisService;
import com.al.nppm.business.inter.service.IRouteService;
import com.al.nppm.business.syntomq.datasyn.DataSynDeal;
import com.al.nppm.business.syntomq.datasyn.Msg;
import com.al.nppm.model.Message;
import com.al.nppm.model.Result;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("plcaProdInstService")
@Transactional
public class PlcaProdInstService {
    private static Logger logger = Logger.getLogger(PlcaProdInstService.class);

    private static SimpleDateFormat yyyyMMddHHmmss_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private PlcaProdInstMapper plcaProdInstMapper;
    @Autowired
    public IProdInstMapper prodinstDao;
    @Autowired
    public IRouteService routeDao;
    @Autowired
    public IHisService hisService;



    /**
     * 首话单激活
     * @param array
     * @param result
     */
    public void shouhuadan(JSONArray array, Result result) throws Exception{
        SynMapContextHolder.remove();
        SynMapContextHolder.init();
        Map<String, List<?>> synMap = new HashMap<String, List<?>>();
        SynMapContextHolder.initSynMap(synMap);
        SynMapContextHolder.put("archGrpId","PLCA_"+prodinstDao.getSeq("SEQ_PLCA_ARCH_GRP_ID"));

        for(Object obj:array) {
            JSONObject json = (JSONObject) obj;
//            String stopType = String.valueOf(json.get("stopType"));
//            String bssState = String.valueOf(json.get("bssState"));
            String prodInstId = String.valueOf(json.get("prodInstId"));
            String opTime = String.valueOf(json.get("opTime"));
            String statusDate=sdf.format(new Date());

            Map map = new HashMap();
            map.put("prodInstId", prodInstId);
            map.put("beginRentDate", opTime);
            map.put("createDate", opTime);
            map.put("statusDate", opTime);
            Message msg=new Message();
            long routeId=routeDao.getProdInstRoute(Long.valueOf(prodInstId),msg);
            if(routeId!=-1l){
                map.put("routeId", routeId);
            }else{
                result.setMessage(msg.getMessage());
                return;
            }
            Map<String, Object> prodInstMap=plcaProdInstMapper.getProdInst(map);
            if(prodInstMap==null){
                result.setMessage("用户不存在，prod_inst_id="+prodInstId);
                return;
            }
            SynMapContextHolder.put("routeCustId",prodInstMap.get("ownerCustId").toString());


            //更新前先备份prod_inst
            if(hisService.backupProdInst(map,new HashMap(),msg)<=0){
                result.setMessage(msg.getMessage());
                return;
            }
            map.put("statusCd",100000);
            plcaProdInstMapper.updateProdInst(map);
            map.remove("statusCd");
            map.put("action",2);
            map.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
            addMap("prodInstobjList1", map,result);

            //prod_inst_state_ext
            List<Map<String, Object>> stateList = plcaProdInstMapper.getProdInstStateExt(map);
            if (stateList.size() != 1) {
                result.setMessage("用户有效状态记录不为一条！");
                return;
            }
            Map<String, Object> stateMap = stateList.get(0);
            stateMap.put("state", "140000");
            stateMap.put("expDate", opTime);
            stateMap.put("routeId",routeId);
            plcaProdInstMapper.updateProdInstStateExt(stateMap);
            stateMap.put("action",2);
            stateMap.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
            addMap("prodInstStateobjList1", stateMap,result);

            stateMap.put("prodInstStateId", prodinstDao.getSeq("SEQ_PROD_INST_STATE_ID"));
            stateMap.put("state", "100000");
            stateMap.put("effDate", opTime);
            stateMap.put("expDate", statePublic.expDate);
            plcaProdInstMapper.insertProdInstStateExt(stateMap);
            stateMap.put("action",1);
            stateMap.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
            addMap("prodInstStateobjList1", stateMap,result);

            //prod_inst_sub
            map.put("accProdInstId", prodInstId);

            List<Map<String, Object>> prodInstSubList = plcaProdInstMapper.getProdInstSub(map);
            for (Map<String, Object> prodInstSubMap : prodInstSubList) {
                prodInstSubMap.put("beginRentDate", opTime);
                prodInstSubMap.put("routeId",routeId);
                plcaProdInstMapper.updateProdInstSub(prodInstSubMap);
                prodInstSubMap.put("action",2);
                prodInstSubMap.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
                addMap("prodInstSubobjList1", prodInstSubMap,result);
            }

            map.put("objId", String.valueOf(prodInstId));
            List<Map<String, Object>> offerObjInstRelList = plcaProdInstMapper.getOfferObjInstRel(map);
            for (Map<String, Object> offerObjInstRel : offerObjInstRelList) {
                offerObjInstRel.put("effDate", opTime);
                offerObjInstRel.put("routeId",routeId);//用户级的套餐prod_inst的路由一致
                plcaProdInstMapper.updateOfferObjInstRel(offerObjInstRel);
                offerObjInstRel.put("action",2);
                offerObjInstRel.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
                addMap("offerObjInstobjList1", offerObjInstRel,result);

                //更改offer_inst
                plcaProdInstMapper.updateOfferInst(offerObjInstRel);
                offerObjInstRel.put("action",2);
                offerObjInstRel.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
                addMap("offerInstobjList1", offerObjInstRel,result);

                //取出offer_inst_attr更新
                offerObjInstRel.put("statusDate",opTime);
                List<Map<String, Object>> offerInstAttrList = plcaProdInstMapper.getOfferInstAttr(offerObjInstRel);
                for (Map<String, Object> offerInstAttr : offerInstAttrList) {
                    offerInstAttr.put("effDate", opTime);
                    offerInstAttr.put("routeId",routeId);
                    plcaProdInstMapper.updateOfferInstAttr(offerInstAttr);
                    offerInstAttr.put("action",2);
                    offerInstAttr.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
                    addMap("offerInstAttrobjList1", offerInstAttr,result);
                }
            }
        }
        addMsg(synMap, result);
        result.setStatus("0");
        result.setMessage("处理成功");
    }

    /**
     * 停复机
     * @param array
     * @param result
     */
    public void tingfuji(JSONArray array, Result result) throws Exception{
        SynMapContextHolder.remove();
        SynMapContextHolder.init();
        Map<String, List<?>> synMap = new HashMap<String, List<?>>();
        SynMapContextHolder.initSynMap(synMap);
        SynMapContextHolder.put("archGrpId","PLCA_"+prodinstDao.getSeq("SEQ_PLCA_ARCH_GRP_ID"));

        for(Object obj:array) {
            JSONObject json = (JSONObject) obj;
            String stopType = String.valueOf(json.get("stopType"));
            String bssState = String.valueOf(json.get("bssState"));
            String prodInstId = String.valueOf(json.get("prodInstId"));
            String opTime = String.valueOf(json.get("opTime"));
            String statusDate=sdf.format(new Date());

            Map map = new HashMap();
            Message msg=new Message();
            long routeId=routeDao.getProdInstRoute(Long.valueOf(prodInstId),msg);
            if(routeId!=-1l){
                map.put("routeId", routeId);
            }else{
                result.setMessage(msg.getMessage());
                return;
            }
            map.put("prodInstId",prodInstId);
            Map<String, Object> prodInstMap=plcaProdInstMapper.getProdInst(map);
            if(prodInstMap==null){
                result.setMessage("用户不存在，prod_inst_id="+prodInstId);
                return;
            }
            SynMapContextHolder.put("routeCustId",prodInstMap.get("ownerCustId").toString());

            map.put("prodInstId", prodInstId);
            map.put("beginRentDate", opTime);
            map.put("createDate", opTime);
            map.put("statusDate", opTime);
            map.put("statusCd", bssState);

            //更新前先备份prod_inst
            if(hisService.backupProdInst(map,new HashMap(),msg)<=0){
                result.setMessage(msg.getMessage());
                return;
            }
            plcaProdInstMapper.updateProdInst(map);
            map.remove("statusCd");
            map.put("action",2);

            map.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
            addMap("prodInstobjList1", map,result);

            //prod_inst_state_ext
            List<Map<String, Object>> stateList = plcaProdInstMapper.getProdInstStateExt(map);
            if (stateList.size() != 1) {
                result.setMessage("用户有效状态记录不为一条！");
                return;
            }
            Map<String, Object> stateMap = stateList.get(0);
//            stateMap.put("state", "140000");
            stateMap.put("expDate", opTime);
            stateMap.put("routeId",routeId);
            plcaProdInstMapper.updateProdInstStateExt(stateMap);
            stateMap.put("action",2);
            stateMap.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
            addMap("prodInstStateobjList1", stateMap,result);

            stateMap.put("prodInstStateId", prodinstDao.getSeq("SEQ_PROD_INST_STATE_ID"));
            stateMap.put("state", bssState);
            stateMap.put("stopType", stopType);
            stateMap.put("effDate", opTime);
            stateMap.put("expDate", statePublic.expDate);
            plcaProdInstMapper.insertProdInstStateExt(stateMap);
            stateMap.put("action",1);
            stateMap.put("executetime",yyyyMMddHHmmss_sdf.format(sdf.parse(statusDate)));
            addMap("prodInstStateobjList1", stateMap,result);
        }
        addMsg(synMap, result);
        result.setStatus("0");
        result.setMessage("处理成功");
    }

    /**
     * 用户属性新增修改
     * @param array
     * @param result
     */
    public void prodInstAttr(JSONArray array, Result result) throws Exception{
        SynMapContextHolder.remove();
        SynMapContextHolder.init();
        Map<String, List<?>> synMap = new HashMap<String, List<?>>();
        SynMapContextHolder.initSynMap(synMap);
        SynMapContextHolder.put("archGrpId","PLCA_"+prodinstDao.getSeq("SEQ_PLCA_ARCH_GRP_ID"));

        for(Object obj:array) {
//            JSONObject json = (JSONObject) obj;
            Map map = (Map) obj;
            String action=String.valueOf(map.get("action"));
            Long prodInstId=Long.valueOf(map.get("prodInstId").toString());
            Message msg=new Message();
            long routeId=routeDao.getProdInstRoute(prodInstId,msg);
            if(routeId!=-1l){
                map.put("routeId", routeId);
            }else{
                result.setMessage(msg.getMessage());
                return;
            }
            Map<String, Object> prodInstMap=plcaProdInstMapper.getProdInst(map);
            if(prodInstMap==null){
                result.setMessage("用户不存在，prod_inst_id="+prodInstId);
                return;
            }
            SynMapContextHolder.put("routeCustId",prodInstMap.get("ownerCustId").toString());

            map.put("statusDate",sdf.format(new Date()));
            List<Map<String, Object>> attrList = plcaProdInstMapper.getProdInstAttr(map);

            if("A".equals(action)){//增加
                if(attrList.size()>0){
                    result.setMessage("用户已经存在有效的属性");
                    return;
                }
                //设置默认值
                map.put("parProdInstAttrId", "1");
                map.put("grpAttrNbr", 1);
                map.put("grpAttrValueNbr", 1);
                map.put("statusCd", "1000");

                map.put("prodInstAttrId", prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"));
                plcaProdInstMapper.insertProdInstAttr(map);
                map.put("action",1);
                map.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(map.get("statusDate").toString())));
                //发送ctg-mq
                addMap("prodInstAttrobjList1", map,result);

            }else if("D".equals(action)){//删除 失效属性
                if(attrList.size()==0){
                    result.setMessage("用户不存在该属性");
                    return;
                }
                Map attrMap=attrList.get(0);
                attrMap.put("statusCd", 1100);
                attrMap.put("expDate",map.get("statusDate"));
                attrMap.put("statusDate",map.get("statusDate"));
                plcaProdInstMapper.updateProdInstAttr(attrMap);
                attrMap.put("action",2);
                attrMap.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(map.get("statusDate").toString())));
                //发送ctg-mq
                addMap("prodInstAttrobjList1", attrMap,result);

            }else if("M".equals(action)){//修改
                if(attrList.size()==0){
                    result.setMessage("用户不存在该属性");
                    return;
                }
                Map attrMap=attrList.get(0);
                attrMap.put("statusCd", 1100);
                attrMap.put("expDate",map.get("effDate"));
                attrMap.put("statusDate",map.get("statusDate"));
                plcaProdInstMapper.updateProdInstAttr(attrMap);
                attrMap.put("action",2);
                attrMap.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(map.get("statusDate").toString())));
                //发送ctg-mq
                addMap("prodInstAttrobjList1", attrMap,result);

                //设置默认值
                map.put("parProdInstAttrId", "1");
                map.put("grpAttrNbr", 1);
                map.put("grpAttrValueNbr", 1);
                map.put("statusCd", "1000");
                map.put("prodInstAttrId", prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"));
                plcaProdInstMapper.insertProdInstAttr(map);
                map.put("action",1);
                map.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(map.get("statusDate").toString())));
                addMap("prodInstAttrobjList1", map,result);

            }
        }
        addMsg(synMap, result);
        result.setStatus("0");
        result.setMessage("处理成功");
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
            msgdata.put("arch_grp_id",SynMapContextHolder.get("archGrpId"));//报文中加上arch_grp_id字段

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


    /**
     * 断网属性修改
     * @param array
     * @param result
     */
    public void updateProdInstAttr(JSONArray array, Result result) throws Exception{
        SynMapContextHolder.remove();
        SynMapContextHolder.init();
        Map<String, List<?>> synMap = new HashMap<String, List<?>>();
        SynMapContextHolder.initSynMap(synMap);
        SynMapContextHolder.put("archGrpId","PLCA_"+prodinstDao.getSeq("SEQ_PLCA_ARCH_GRP_ID"));

        for(Object obj:array) {
            Map map = (Map) obj;
            Long prodInstId=Long.valueOf(map.get("prodInstId").toString());
            Message msg=new Message();
            long routeId=routeDao.getProdInstRoute(prodInstId,msg);
            if(routeId!=-1l){
                map.put("routeId", routeId);
            }else{
                result.setMessage(msg.getMessage());
                return;
            }
            Map<String, Object> prodInstMap=plcaProdInstMapper.getProdInst(map);
            if(prodInstMap==null){
                result.setMessage("用户不存在，prod_inst_id="+prodInstId);
                return;
            }
            SynMapContextHolder.put("routeCustId",prodInstMap.get("ownerCustId").toString());

            if(map.get("effDate")==null){
                map.put("statusDate",sdf.format(new Date()));
            }else{
                map.put("statusDate",map.get("effDate"));
            }

            List<Map<String, Object>> attrList = plcaProdInstMapper.getProdInstAttr(map);

            if(attrList.size()>0){
                Map attrMap=attrList.get(0);
                attrMap.put("statusCd", 1100);
                attrMap.put("expDate",map.get("statusDate"));
                attrMap.put("statusDate",map.get("statusDate"));
                plcaProdInstMapper.updateProdInstAttr(attrMap);
                attrMap.put("action",2);
                attrMap.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(map.get("statusDate").toString())));
                //发送ctg-mq
                addMap("prodInstAttrobjList1", attrMap,result);
            }
            //设置默认值
            map.put("parProdInstAttrId", "1");
            map.put("grpAttrNbr", 1);
            map.put("grpAttrValueNbr", 1);
            map.put("statusCd", "1000");
            map.put("prodInstAttrId", prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"));
            plcaProdInstMapper.insertProdInstAttr(map);
            map.put("action",1);
            map.put("executetime", yyyyMMddHHmmss_sdf.format(sdf.parse(map.get("statusDate").toString())));
            addMap("prodInstAttrobjList1", map,result);
        }

        addMsg(synMap, result);
        result.setStatus("0");
        result.setMessage("处理成功");
    }


}
