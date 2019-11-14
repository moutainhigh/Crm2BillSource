package com.al.nppm.business.bill.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BillMapper {

    public String getofferTypeBill(Map map);
    public String getOfferObjRelId(Map map);
    public long getProdCount(Map map);
    public List<Map<String,Object>> getProductList(Map map);
    public List<Map<String, Object>> selectOfferRoleId(@Param("offerId") long offerId,
                                                       @Param("roleId") String routeId);
    public List<Map<String, Object>> selectOfferId(@Param("offerId") long offerId);

    public long getofferAttrIdCnt(@Param("offerId") long offerId,
                                  @Param("attrId") long attrId);

    public long dealExpDateOffer(@Param("offerId") long offerId);
}
