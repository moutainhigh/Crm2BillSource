package com.al.nppm.business.account.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/5/22 11:06
 * @File : IRouteMapper
 * @Software: IntelliJ IDEA 2019.3.15
 */
@Repository
public interface IRouteMapper {
    Long getProdInstRoute(Long prodInstId);
    Long getOfferInstRoute(Long offerInstId);

    int insertProdInstRoute(Map map);
    int insertOfferInstRoute(Map map);
    int updateProdInstRoute(Map map);
    Long getAccountByObjId(Long objId);


}
