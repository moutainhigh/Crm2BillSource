package com.al.nppm.ord.ordbill.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/22 11:10
 * @File : OrdRouteMapper
 * @Software: IntelliJ IDEA 2019.3.15
 */
@Repository
public interface OrdRouteMapper {
     List<Map<String,Object>> getOrdAccountByOrdExtAcct(Map map);
     List<Map<String,Object>> getOrdCustomerByTaxPayerId(Map map);
     List<Map<String,Object>> getOrdContactsInfoByContactId(Map map);

     List<Map<String,Object>> getOrdProdInstAcctRelByProdInstId(Map map);
     List<Map<String,Object>> getOrdOfferObjInstRelByOfferInstId(Map map);
     List<Map<String,Object>> getOrdOfferProdInstRelByOfferInstId(Map map);
}
