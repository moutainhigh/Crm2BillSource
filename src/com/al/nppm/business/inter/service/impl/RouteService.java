package com.al.nppm.business.inter.service.impl;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/22 11:01
 * @File : RouteService
 * @Software: IntelliJ IDEA 2019.3.15
 */

import com.al.nppm.business.account.dao.IRouteMapper;
import com.al.nppm.business.inter.service.IRouteService;
import com.al.nppm.common.utils.StringUtil;
import com.al.nppm.model.Message;
import com.al.nppm.ord.ordbill.dao.OrdRouteMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @Author maozp3
 * @Description:
 * @Date: 2019/5/22 11:01
 */
@Service
public class RouteService implements IRouteService {
    private static Logger logger = Logger.getLogger(RouteService.class);
    @Autowired
    OrdRouteMapper ordRouteDao;
    @Autowired
    IRouteMapper routeDao;

    /**
    * @Author maozp3 
    * @Description: 取ord_customer表的cust_id作为tax_payer/tax_payer_attr表分片的值
    * @Date: 17:22 2019/5/22
    * @Param [archGrpId, orderItemId, taxPayerId]
    * @return java.lang.Long
     * @throws Exception 
    **/
    @Override
    public Long getRouteIdForTaxPayer(Long archGrpId, Long orderItemId, Long taxPayerId, Message msg) throws Exception {
        Map<String,Object> taxPayerMap = new HashMap<String, Object>();
        Long routeId;
        taxPayerMap.put("ARCH_GRP_ID", archGrpId);
        taxPayerMap.put("ORDER_ITEM_ID", orderItemId);
        taxPayerMap.put("taxPayerId",taxPayerId);
        //取ord_customer表的cust_id作为tax_payer/tax_payer_attr表分片的值
        try {
            List<Map<String,Object>> ordCustomerList = ordRouteDao.getOrdCustomerByTaxPayerId(taxPayerMap);
            if(ordCustomerList.size() > 0){
                Map ordCustomerMap = ordCustomerList.get(0);
                routeId =  Long.valueOf(String.valueOf(ordCustomerMap.get("custId")));
            }else{
                msg.setMessage("tax_payer关联ord_customer表取routeId(cust_id)字段失败。TAX_PAYER_ID="+taxPayerId);
                return -1L;
            }
        } catch (Exception e) {
            msg.setMessage("tax_payer关联ord_customer表取routeId(cust_id)字段失败。TAX_PAYER_ID="+taxPayerId);
            e.printStackTrace();
            throw e;
        }
        return routeId;
    }

    /**
    * @Author maozp3 
    * @Description: 取ord_ACCOUNT表的acct_id字段，作为EXT_ACCT表的route_id值
    * @Date: 17:22 2019/5/22
    * @Param [archGrpId, orderItemId, extAcctId]
    * @return java.lang.Long
     * @throws Exception 
    **/
    @Override
    public Long getRouteIdForExtAcct(Long archGrpId, Long orderItemId, Long extAcctId,Message msg) throws Exception {
        Map<String,Object> extAcctMap = new HashMap<String, Object>();
        Long routeId;
        extAcctMap.put("ARCH_GRP_ID", archGrpId);
        extAcctMap.put("ORDER_ITEM_ID", orderItemId);
        extAcctMap.put("extAcctId",extAcctId);
        //取ord_account的acct_id作为ext_acct表分片的值(待crm确认是否送ord_Account表)
        try {
            List<Map<String,Object>> ordAccountList = ordRouteDao.getOrdAccountByOrdExtAcct(extAcctMap);
            if(ordAccountList.size() > 0){
                Map ordAccountMap =ordAccountList.get(0);
                routeId =  Long.valueOf(String.valueOf(ordAccountMap.get("acctId")));
            }else {
                msg.setMessage("ext_acct关联ord_account表取routeId(acct_id)字段失败。EXT_ACCT_ID="+extAcctId);
                return -1L;
            }
        } catch (Exception e) {
            msg.setMessage("ext_acct关联ord_account表取routeId(acct_id)字段失败。EXT_ACCT_ID="+extAcctId);
            e.printStackTrace();
            throw e;
        }
        return routeId;
    }

    /**
    * @Author maozp3 
    * @Description: 获取prod_inst相关表的routeId
    * @Date: 17:22 2019/5/22
    * @Param [archGrpId, orderItemId, prodInstId]
    * @return java.lang.Long
     * @throws Exception 
    **/
    @Override
    public Long getRouteIdForProdInst(Long archGrpId, Long orderItemId, Long prodInstId,Message msg) throws Exception {
        Long routeId;
        try {
            routeId = routeDao.getProdInstRoute(prodInstId);
            //如果PROD_INST_ROUTE表中没有找到routeId，则去ORD_PROD_INST_ACCT_REL中去acct_id
            if(StringUtil.isEmpty(String.valueOf(routeId))){
                Map<String,Object> prodInstMap = new HashMap<String, Object>();
                prodInstMap.put("ARCH_GRP_ID", archGrpId);
                prodInstMap.put("ORDER_ITEM_ID", orderItemId);
                prodInstMap.put("prodInstId", prodInstId);
                List<Map<String,Object>>  ordProdInstAcctRelList = ordRouteDao.getOrdProdInstAcctRelByProdInstId(prodInstMap);
                if(ordProdInstAcctRelList.size() > 0){
                    Map<String,Object> ordProdInstAcctRelMap = ordProdInstAcctRelList.get(0);
                    routeId = Long.valueOf(String.valueOf(ordProdInstAcctRelMap.get("acctId")));
                    prodInstMap.put("routeId", routeId);
                    //把新的prodInstId和routeId写入路由表PROD_INST_ROUTE中
                    routeDao.insertProdInstRoute(prodInstMap);
                    return routeId;
                }else{
                    msg.setMessage("ORD_PROD_INST_ACCT_REL取routeId(acct_id)失败。prodInstId="+prodInstId);
                    return -1L;
                }
            }
        } catch (Exception e) {
            msg.setMessage("prodInst取routeId失败。prodInstId="+prodInstId);
            e.printStackTrace();
            throw e;
        }
        return routeId;
    }


    /**
     * @Author maozp3
     * @Description: 获取offer_inst相关表的routeId
     * @Date: 17:23 2019/5/22
     * @Param [archGrpId, orderItemId, offerInstId]
     * @return java.lang.Long
     * @throws Exception 
     **/
    @Override
    public Long getRouteIdForOfferInst(Long archGrpId, Long orderItemId, Long offerInstId,Message msg) throws Exception{
        Long routeId;
        try {
            routeId = routeDao.getOfferInstRoute(offerInstId);
            //如果offer_inst_route表没有,就去查ORD_OFFER_PROD_INST_REL的PROD_INST_ID，然后再查PROD_INST_ROUTE。
            if(StringUtil.isEmpty(String.valueOf(routeId))){
                Map<String,Object> offerInstMap = new HashMap<String, Object>();
                offerInstMap.put("ARCH_GRP_ID", archGrpId);
                offerInstMap.put("ORDER_ITEM_ID", orderItemId);
                offerInstMap.put("offerInstId", offerInstId);
                List<Map<String,Object>> ordOfferProdInstRelList = ordRouteDao.getOrdOfferProdInstRelByOfferInstId(offerInstMap);
                if(ordOfferProdInstRelList.size() > 0){
                    Map<String,Object> ordOfferProdInstRelMap = ordOfferProdInstRelList.get(0);
                    Long prodInstId = Long.valueOf(String.valueOf(ordOfferProdInstRelMap.get("prodInstId")));
                    routeId = routeDao.getProdInstRoute(prodInstId);
                    //如果prod_inst_route取出来的routeId不存在。则去ORD_OFFER_OBJ_INST_REL表取obj_id,再去account表取acct_id
                    if(StringUtil.isEmpty(String.valueOf(routeId))){
                        List<Map<String,Object>> ordOfferObjInstRelList = ordRouteDao.getOrdOfferObjInstRelByOfferInstId(offerInstMap);
                        if(ordOfferObjInstRelList.size() > 0){
                            Map<String,Object> ordOfferObjInstRelMap = ordOfferObjInstRelList.get(0);
                            Long objId = Long.valueOf(String.valueOf(ordOfferObjInstRelMap.get("objId")));
                            routeId = routeDao.getAccountByObjId(objId);
                            if(!StringUtil.isEmpty(String.valueOf(routeId))){  //account表中取出的acct_id不为空
                                offerInstMap.put("routeId", routeId);
                                //将新的offerInstId和routeId(account表的acct_id字段)写入路由表OFFER_INST_ROUTE中
                                routeDao.insertOfferInstRoute(offerInstMap);
                                return routeId;
                            }else{
                                msg.setMessage("从Account表中取acct_id失败。offerInstId="+offerInstId);
                                return -1L;
                            }
                        }else{
                            msg.setMessage("从ORD_OFFER_OBJ_INST_REL取obj_Id失败。offerInstId="+offerInstId);
                            return -1L;
                        }
                    }else{
                        //把新的offerInstId和routeId(Prod_inst_route表中的routeId)写入路由表OFFER_INST_ROUTE中
                        offerInstMap.put("routeId", routeId);
                        routeDao.insertOfferInstRoute(offerInstMap);
                        return routeId;
                    }
                }
            }
        } catch (Exception e) {
            msg.setMessage("offerInst取routeId失败。offerInstId="+offerInstId);
            e.printStackTrace();
            throw e;
        }
        return routeId;
    }


    /**
    * @Author maozp3 
    * @Description: 获取CONTACTS_INFO_ATTR表的routeId
    * @Date: 17:24 2019/5/22
    * @Param [archGrpId, orderItemId, contactId]
    * @return java.lang.Long
     * @throws Exception 
    **/
    @Override
    public Long getRouteIdForContactsInfoAttr(Long archGrpId, Long orderItemId, Long contactId,Message msg) throws Exception {
        Map<String,Object> contactsInfoMap = new HashMap<String, Object>();
        Long routeId;
        contactsInfoMap.put("ARCH_GRP_ID", archGrpId);
        contactsInfoMap.put("ORDER_ITEM_ID", orderItemId);
        contactsInfoMap.put("contactId",contactId);
        try {
            List<Map<String,Object>> ordContactsInfoList = ordRouteDao.getOrdContactsInfoByContactId(contactsInfoMap);
            if(ordContactsInfoList.size() > 0){
                Map ordContactsInfoMap = ordContactsInfoList.get(0);
                routeId = Long.valueOf(String.valueOf(ordContactsInfoMap.get("partyId")));
            }else{
                msg.setMessage("ord_contacts_info_attr关联ord_contacts_info表取routeId(party_id)字段失败。CONTACT_ID="+contactId);
                return -1L;
            }
        } catch (Exception e) {
            msg.setMessage("ord_contacts_info_attr关联ord_contacts_info表取routeId(party_id)字段失败。CONTACT_ID="+contactId);
            e.printStackTrace();
            throw e;
        }
        return routeId;
    }
}
