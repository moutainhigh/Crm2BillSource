 package com.al.nppm.business.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fanjl
 * @date 2019/05/28
 */
public class SynMapContextHolder {
    private static final ThreadLocal<Map<String,Object>> context=new ThreadLocal<Map<String,Object>>();
    
    /**
     * 初始化context
     */
    public static void init() {
        if(context.get()==null)
            context.set(new HashMap<String,Object>());
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
        context.get().put("tifVpnGroupList1", new ArrayList<Map<String, Object>>());
        context.get().put("tifVpnMemList1", new ArrayList<Map<String, Object>>()); 
        context.get().put("routeCustId", 0L);
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

        synMap.put("party", (List<?>)context.get().get("partyList1"));
        synMap.put("party_ind", (List<?>)context.get().get("partyIndList1"));
        synMap.put("party_role", (List<?>)context.get().get("partyRoleList1"));
        synMap.put("party_attr", (List<?>)context.get().get("partyAttrList1"));
        synMap.put("contacts_info", (List<?>)context.get().get("contactsInfoList1"));
        synMap.put("contacts_info_attr", (List<?>)context.get().get("contactsInfoAttrList1"));
        synMap.put("tif_vpn_group",  (List<?>)context.get().get("tifVpnGroupList1"));
		synMap.put("tif_vpn_mem",  (List<?>)context.get().get("tifVpnMemList1"));
    }
    
    /**
     * 将map放到对应key的list中
     * @param key
     * @param map
     */
    public static void addMap(String key,Map map) {
        if(context.get().get(key)!=null) {
            ((List)context.get().get(key)).add(map);
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
    
    

}
