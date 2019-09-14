package com.al.nppm.business.inter.service;

import com.al.nppm.model.Message;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/22 10:46
 * @File : IRouteService
 * @Software: IntelliJ IDEA 2019.3.15
 */
public interface IRouteService {
    /**
    * @Author maozp3
    * @Description: 取ord_customer表的cust_id作为tax_payer/tax_payer_attr表分片的值
    * @Date: 14:01 2019/5/22
    * @Param [archGrpId, orderItemId, taxPayerId]
    * @return java.lang.Long
     * @throws Exception 
    **/
    Long getRouteIdForTaxPayer(Long archGrpId, Long orderItemId, Long taxPayerId, Message msg);
    /**
    * @Author maozp3
    * @Description: 取ord_ACCOUNT表的acct_id字段，作为EXT_ACCT表的route_id值
    * @Date: 11:27 2019/5/22
    * @Param [archGrpId, orderItemId, extAcctId]
    * @return java.lang.Long
     * @throws Exception 
    **/
    Long getRouteIdForExtAcct(Long archGrpId, Long orderItemId, Long extAcctId, Message msg);
    Long getRouteIdForProdInst(Long archGrpId,Long orderItemId,Long prodInstId,Message msg);
    Long getRouteIdForOfferInst(Long archGrpId,Long orderItemId,Long offerInstId,Message msg);
    Long getRouteIdForContactsInfoAttr(Long archGrpId,Long orderItemId,Long contactId,Message msg);
    public Long getProdInstRoute(Long prodInstId,Message msg) ;

}
