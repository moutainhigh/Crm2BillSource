 package com.al.nppm.business.core;

 import com.al.nppm.business.account.dao.CtgMqMapper;
 import com.al.nppm.business.account.dao.IProdInstMapper;
 import com.al.nppm.business.account.dao.UserInfoOplogMapper;
 import com.alibaba.fastjson.JSON;
 import org.apache.log4j.Logger;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;

 import java.util.*;

/**
 * @author fanjl
 * @date 2019/05/28
 */
@Component
public class SynMapContextHolder {
    private static final ThreadLocal<Map<String,Object>> context=new ThreadLocal<Map<String,Object>>();
    private static Logger logger = Logger.getLogger(SynMapContextHolder.class);

    private static CtgMqMapper ctgMqMapper;

    private static UserInfoOplogMapper userInfoOplogMapper;
    public static IProdInstMapper prodinstDao;
    @Autowired
    public void setCtgMqMapper(CtgMqMapper ctgMqMapper) {
        SynMapContextHolder.ctgMqMapper = ctgMqMapper;
    }
    @Autowired
    public void setUserInfoOplogMapper(UserInfoOplogMapper userInfoOplogMapper) {
         SynMapContextHolder.userInfoOplogMapper = userInfoOplogMapper;
     }
     @Autowired
     public void setIProdInstMapper(IProdInstMapper prodinstDao) {
         SynMapContextHolder.prodinstDao = prodinstDao;
     }
    
    /**
     * 初始化context
     */
    public static void init() {
        if(context.get()==null) {
            context.set(new HashMap<String,Object>());
        }
        context.get().put("custobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("acctobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("taxPayerobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("taxPayerAttrobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("paymentPlanobjList1", new ArrayList<Map<String, Object>>());
        
        context.get().put("extAcctobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("prodInstobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("prodInstAttrobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("prodInstSubobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("prodInstRelobjList1", new ArrayList<Map<String, Object>>());
        
        context.get().put("prodInstAcctRelobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("offerProdInstobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("offerInstobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("offerObjInstobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("offerInstAttrobjList1", new ArrayList<Map<String, Object>>());
               
        context.get().put("prodInstRegionobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("prodInstAccNumobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("prodInstStateobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("prodInstPaymodeobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("prodInstGroupobjList1", new ArrayList<Map<String, Object>>());
                 
        context.get().put("prodInstAttrSubobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("partyList1", new ArrayList<Map<String, Object>>());
        context.get().put("partyIndList1", new ArrayList<Map<String, Object>>());
        context.get().put("partyRoleList1", new ArrayList<Map<String, Object>>());
        context.get().put("partyAttrList1", new ArrayList<Map<String, Object>>());
        
        context.get().put("contactsInfoList1", new ArrayList<Map<String, Object>>());
        context.get().put("contactsInfoAttrList1", new ArrayList<Map<String, Object>>());
        context.get().put("partyCertobjList1", new ArrayList<Map<String, Object>>());

        //VPN
        context.get().put("tifVpnGroupList1", new ArrayList<Map<String, Object>>());
        context.get().put("tifVpnMemList1", new ArrayList<Map<String, Object>>()); 
        context.get().put("aAcctExtendobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("extAcctobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("aOweSpecialServobjList1", new ArrayList<Map<String, Object>>());
        context.get().put("routeCustId", 0L);
        context.get().put("archGrpId", 0L);

    }
    
    /**
     * 初始化synMap
     * @param synMap
     */
    public static void initSynMap(Map<String, List<?>> synMap) {
        synMap.put("customer", (List<?>)context.get().get("custobjList1"));
        synMap.put("account", (List<?>)context.get().get("acctobjList1"));
        synMap.put("prod_inst", (List<?>)context.get().get("prodInstobjList1"));
        synMap.put("prod_inst_attr", (List<?>)context.get().get("prodInstAttrobjList1"));
        synMap.put("prod_inst_sub", (List<?>)context.get().get("prodInstSubobjList1"));
        synMap.put("prod_inst_rel", (List<?>)context.get().get("prodInstRelobjList1"));
        synMap.put("prod_inst_acct_rel", (List<?>)context.get().get("prodInstAcctRelobjList1"));
        synMap.put("offer_inst", (List<?>)context.get().get("offerInstobjList1"));
        synMap.put("offer_obj_inst_rel", (List<?>)context.get().get("offerObjInstobjList1"));
        synMap.put("offer_inst_attr", (List<?>)context.get().get("offerInstAttrobjList1"));
        synMap.put("offer_prod_inst", (List<?>)context.get().get("offerProdInstobjList1"));
        synMap.put("prod_inst_region", (List<?>)context.get().get("prodInstRegionobjList1"));
        synMap.put("prod_inst_acc_num", (List<?>)context.get().get("prodInstAccNumobjList1"));
        synMap.put("prod_inst_state_ext", (List<?>)context.get().get("prodInstStateobjList1"));
        synMap.put("prod_inst_paymode", (List<?>)context.get().get("prodInstPaymodeobjList1"));
        // synMap.put( "prod_inst_group", prodInstGroupobjList1 );
        synMap.put("prod_inst_attr_sub", (List<?>)context.get().get("prodInstAttrSubobjList1"));

        //synMap.put("party", (List<?>)context.get().get("partyList1"));
        //synMap.put("party_ind", (List<?>)context.get().get("partyIndList1"));
        //synMap.put("party_role", (List<?>)context.get().get("partyRoleList1"));
        //synMap.put("party_attr", (List<?>)context.get().get("partyAttrList1"));
        //synMap.put("contacts_info", (List<?>)context.get().get("contactsInfoList1"));
        //synMap.put("contacts_info_attr", (List<?>)context.get().get("contactsInfoAttrList1"));
        synMap.put("tif_vpn_group",  (List<?>)context.get().get("tifVpnGroupList1"));
		synMap.put("tif_vpn_mem",  (List<?>)context.get().get("tifVpnMemList1"));
        //tax_payer
        synMap.put("tax_payer", (List<?>)context.get().get("taxPayerobjList1"));
        synMap.put("payment_plan", (List<?>)context.get().get("paymentPlanobjList1"));
        synMap.put("party_cert", (List<?>)context.get().get("partyCertobjList1"));

        synMap.put("a_acct_extend", (List<?>)context.get().get("aAcctExtendobjList1"));
        synMap.put("ext_acct", (List<?>)context.get().get("extAcctobjList1"));
        synMap.put("a_owe_special_serv", (List<?>)context.get().get("aOweSpecialServobjList1"));
    }
    
    /**
     * 将map放到对应key的list中
     * @param key
     * @param map
     */
    public static void addMap(String key,Map map) throws Exception{
        Map objMap=checkId(key,map);
        if(objMap==null){
            logger.error("上发ctg-mq主键对应的消息为空,"+"key:"+key+",map:"+ JSON.toJSONString(map));
            throw new Exception("上发ctg-mq主键对应的消息为空【key】：" + key);
        }
        objMap.put("route_cust_id",context.get().get("routeCustId"));
        objMap.put("action",map.get("action"));
        objMap.put("act_id",map.get("action"));
        objMap.put("executetime",map.get("executetime"));
        if(context.get().get(key)!=null) {
            ((List)context.get().get(key)).add(objMap);
        }
    }
    
    /**
     * 从context中取出key的对象
     * @param key
     * @return
     */
    public static Object get(String key) {
        if(context.get().get(key)!=null) {
            return context.get().get(key);
        }
        return null;
    }
    /**
     * 将obj对象设置到context对象的key中
     * @param key
     * @return
     */
    public static void put(String key,Object obj) {
        context.get().put(key,obj);
    }
    /**
     * 移除context中的内容
     */
    public static void remove() {
        context.remove();
    }
     /**
     *  预留，先校验下每个发ctg-mq的map中是否都有对应表的主键
     *  批价需要用档案接口的消息增量更新资料，发ctg-mq的数据要包含表的所有字段
     * @param key
     * @param map
     * @return
     * @throws Exception
     */
    public static Map checkId(String key,Map map) throws Exception {
        Map objMap=null;
        Map userInfoOplogMap=new HashMap();//写user_info_oplog的map
        userInfoOplogMap.put("opType",map.get("action"));
        boolean vpnFlag=false;
        if ("custobjList1".equals(key)) {//customer
//            map.get("custId").toString();
            userInfoOplogMap.put("tableName","CUSTOMER");
            userInfoOplogMap.put("column1","custId");
            objMap=ctgMqMapper.getCustomer(map);
        } else if ("acctobjList1".equals(key)) {//account
//            map.get("acctId").toString();
            userInfoOplogMap.put("tableName","ACCOUNT");
            userInfoOplogMap.put("column1","acctId");
            objMap=ctgMqMapper.getAccount(map);
        } else if ("prodInstobjList1".equals(key)) {//prod_inst
//            map.get("prodInstId").toString();
            userInfoOplogMap.put("tableName","PROD_INST");
            userInfoOplogMap.put("column1","prodInstId");
            objMap=ctgMqMapper.getProdInst(map);
        } else if ("prodInstAttrobjList1".equals(key)) {//prod_inst_attr
//            map.get("prodInstAttrId").toString();
            userInfoOplogMap.put("tableName","PROD_INST_ATTR");
            userInfoOplogMap.put("column1","prodInstAttrId");
            objMap=ctgMqMapper.getProdInstAttr(map);
        } else if ("prodInstSubobjList1".equals(key)) {//prod_inst_sub
//            map.get("prodInstId").toString();
            userInfoOplogMap.put("tableName","PROD_INST_SUB");
            userInfoOplogMap.put("column1","prodInstId");
            objMap=ctgMqMapper.getProdInstSub(map);
        } else if ("prodInstAcctRelobjList1".equals(key)) {//prod_inst_acct_rel
//            map.get("prodInstAcctRelId").toString();
            userInfoOplogMap.put("tableName","PROD_INST_ACCT_REL");
            userInfoOplogMap.put("column1","prodInstAcctRelId");
            objMap=ctgMqMapper.getProdInstAcctRel(map);
        } else if ("offerInstobjList1".equals(key)) {//offer_inst
//            map.get("offerInstId").toString();
            userInfoOplogMap.put("tableName","OFFER_INST");
            userInfoOplogMap.put("column1","offerInstId");
            objMap=ctgMqMapper.getOfferInst(map);
        } else if ("offerObjInstobjList1".equals(key)) {//offer_obj_inst_rel
//            map.get("offerObjInstRelId").toString();
            userInfoOplogMap.put("tableName","OFFER_OBJ_INST_REL");
            userInfoOplogMap.put("column1","offerObjInstRelId");
            objMap=ctgMqMapper.getOfferObjInstRel(map);
        } else if ("offerInstAttrobjList1".equals(key)) {//offer_inst_attr
//            map.get("offerInstAttrId").toString();
            userInfoOplogMap.put("tableName","OFFER_INST_ATTR");
            userInfoOplogMap.put("column1","offerInstAttrId");
            objMap=ctgMqMapper.getOfferInstAttr(map);
        } else if ("prodInstRegionobjList1".equals(key)) {//prod_inst_region
//            map.get("prodInstRegionId").toString();
            userInfoOplogMap.put("tableName","PROD_INST_REGION");
            userInfoOplogMap.put("column1","prodInstRegionId");
            objMap=ctgMqMapper.getProdInstRegion(map);
        } else if ("prodInstAccNumobjList1".equals(key)) {//prod_inst_acc_num
//            map.get("prodInstAccNumId").toString();
            userInfoOplogMap.put("tableName","PROD_INST_ACC_NUM");
            userInfoOplogMap.put("column1","prodInstAccNumId");
            objMap=ctgMqMapper.getProdInstAccNum(map);
        } else if ("prodInstStateobjList1".equals(key)) {//prod_inst_state_ext
//            map.get("prodInstStateId").toString();
            userInfoOplogMap.put("tableName","PROD_INST_STATE_EXT");
            userInfoOplogMap.put("column1","prodInstStateId");
            objMap=ctgMqMapper.getProdInstStateExt(map);
        } else if ("prodInstPaymodeobjList1".equals(key)) {//prod_inst_paymode
//            map.get("paymodeId").toString();
            userInfoOplogMap.put("tableName","PROD_INST_PAYMODE");
            userInfoOplogMap.put("column1","paymodeId");
            objMap=ctgMqMapper.getProdInstPaymode(map);
        } else if ("prodInstAttrSubobjList1".equals(key)) {//prod_inst_attr_sub
//            map.get("prodInstAttrId").toString();
            userInfoOplogMap.put("tableName","PROD_INST_ATTR_SUB");
            userInfoOplogMap.put("column1","prodInstAttrId");
            objMap=ctgMqMapper.getProdInstAttrSub(map);
        }else if ("taxPayerobjList1".equals(key)) {//tax_payer
            userInfoOplogMap.put("tableName","TAX_PAYER");
            userInfoOplogMap.put("column1","taxPayerId");
            objMap=ctgMqMapper.getTaxPayer(map);
        }else if ("partyCertobjList1".equals(key)) {//party_cert
            userInfoOplogMap.put("tableName","PARTY_CERT");
            userInfoOplogMap.put("column1","partyCertId");
            objMap=ctgMqMapper.getPartyCert(map);
        }else if ("paymentPlanobjList1".equals(key)) {//payment_plan
            userInfoOplogMap.put("tableName","PAYMENT_PLAN");
            userInfoOplogMap.put("column1","paymentPlanId");
            objMap=ctgMqMapper.getPaymentPlan(map);
        }else if("tifVpnGroupList1".equals(key)){//TIF_VPN_GROUP
            userInfoOplogMap.put("tableName","TIF_VPN_GROUP");
            vpnFlag=true;
            objMap=map;
            /*userInfoOplogMap.put("column1","vpnCode");
            objMap=ctgMqMapper.getTifVpnGroup(map);*/
        }else if("tifVpnMemList1".equals(key)){//TIF_VPN_MEM
            userInfoOplogMap.put("tableName","TIF_VPN_MEM");
            vpnFlag=true;
            objMap=map;
            /*userInfoOplogMap.put("column1","vpnMemId");
            objMap=ctgMqMapper.getTifVpnMem(map);*/
        }else if ("extAcctobjList1".equals(key)) {//EXT_ACCT
            userInfoOplogMap.put("tableName","EXT_ACCT");
            userInfoOplogMap.put("column1","extAcctId");
            objMap=ctgMqMapper.getExtAcct(map);
        }else{
            return  map;
        }
        if(objMap!=null&&!vpnFlag){
            userInfoOplogMap.put("tableColumn1",objMap.get(userInfoOplogMap.get("column1")));
            userInfoOplogMap.put("partitionId",1);
            userInfoOplogMap.put("modifyAcctFlag",1);
            userInfoOplogMap.put("dbopDate",new Date());

            //routeid 每张表都有routeId
            userInfoOplogMap.put("routeId",objMap.get("routeId"));
            userInfoOplogMap.put("archGrpId",context.get().get("archGrpId"));
            //取序列
            userInfoOplogMap.put("opSeq",prodinstDao.getSeq("SEQ_USER_INFO_OPLOG"));
            //山东写7张user_info_oplog表，省份可根据实际情况调整 1-3计费用，4是账务中心用
            userInfoOplogMapper.insertUserInfoOplog001(userInfoOplogMap);
            /*userInfoOplogMapper.insertUserInfoOplog002(userInfoOplogMap);
            userInfoOplogMapper.insertUserInfoOplog003(userInfoOplogMap);
            userInfoOplogMapper.insertUserInfoOplog004(userInfoOplogMap);*/
        }else if(objMap!=null&&vpnFlag){
            //取序列
            userInfoOplogMap.put("opSeq",prodinstDao.getSeq("SEQ_USER_INFO_OPLOG"));
            userInfoOplogMap.put("dbopDate",new Date());
            userInfoOplogMap.put("archGrpId",context.get().get("archGrpId"));
            if("tifVpnGroupList1".equals(key)){
//                userInfoOplogMap.put("partitionId",1);
//                userInfoOplogMap.put("modifyAcctFlag",1);
                userInfoOplogMap.put("tableColumn4",objMap.get("vpnCode"));
            }else if("tifVpnMemList1".equals(key)){
                userInfoOplogMap.put("tableColumn1",objMap.get("vpnMemId"));
                userInfoOplogMap.put("tableColumn2",objMap.get("memNumber"));
                userInfoOplogMap.put("tableColumn4",objMap.get("vpnCode"));
            }
            //山东写7张vpn_user_info_oplog表，省份可根据实际情况调整
            userInfoOplogMapper.insertVpnUserInfoOplog001(userInfoOplogMap);
        }

        return objMap;
    }

     /**
      * 从ord_bill_obj取数据
      * @param billObjmap  需要包含归档组ID、订单项ID
      * @param tableName
      * @return
      */
     public static int getFromBillObj(Map billObjmap, String tableName) {
         if (context.get().get("ordBillObjMap") != null) {
//             return ((Map) SynMapContextHolder.get("ordBillObjMap")).get(billObjmap.get("ARCH_GRP_ID") + "_" + billObjmap.get("ORDER_ITEM_ID") + "_" + tableName);
//             return context.get().get(key);
             if(((Map) SynMapContextHolder.get("ordBillObjMap")).get(billObjmap.get("ARCH_GRP_ID") + "_" + billObjmap.get("ORDER_ITEM_ID") + "_" + tableName)!=null){
                 return 1;
             }
         }
         return 0;
     }

     public static int getProductCount(String prodId) {
         if (context.get().get("productMap") != null) {
             if(((Map) context.get().get("productMap")).get(prodId)!=null){
                 return 1;
             }else{
                 return 0;
             }
         }
         return 0;
     }

}
