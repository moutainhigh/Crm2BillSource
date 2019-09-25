package com.al.nppm.business.account.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface OfferInstMapper {

	public int insertOfferInst(Map map);
	public int updateOfferInst(Map map);
	public int insertOfferInstAttr(Map map);
	public int updateOfferInstAttr(Map map);
	
	public int insertOfferObjInstRel(Map map);
	public int updateOfferObjInstRel(Map map);
	public List<Map<String,Object>> getOfferInstId(Map map);

	public List<Map<String, Object>> getOfferInstIdFromExpDate(Map map);

	public List<Map<String, Object>> getOfferInstIdFromNowDate(Map map);

	public List<Map<String,Object>> getOfferObjInstId(Map map);

	public List<Map<String, Object>> getOfferObjInstIdForObjectId(Map map);

	public List<Map<String,Object>> getOfferRoleId(Map map);
	public List<Map<String,Object>> getOfferInstAttrId(Map map);
	public List<Map<String,Object>> getOfferInstAttrIdFromAttrId(Map map);
	public List<Map<String,Object>> getOFFERID(Map map);
	
	
	
	//==========================================
	public List<Long> getOfferInstIdList(Map map);
	
	public int updateOfferInstByOfferInstId(Map map);
	
	public Long getOfferObjInstRelId(Map map);
	public Long getOfferInstAttrId1(Map map);

	public List<Map<String, Object>> getOfferObjInstIdFromObj(Map map);
	public List<Map<String, Object>> getOfferObjfromObjId(Map map);
	public List<Map<String, Object>> selectOfferRoleId(@Param("offerId") long offerId,
												 @Param("roleId") String routeId);

	public List<Map<String, Object>> selectOfferId(@Param("offerId") long offerId);

	public List<Map<String, Object>> selectOfferInstAttrIdFromInstId(@Param("offerInstId") long offerInstId,
																	 @Param("routeId") long routeId,
																	 @Param("expDate") String expDate);

	public List<Map<String, Object>> selectOfferInstFromObjInstid(@Param("offerInstId") long offerInstId,
																  @Param("routeId") long routeId);
	public List<Map<String, Object>> getOfferObjInstRel(Map map);

	public int updateOfferObjInstRelForActive(Map map);

	public int updateOfferInstForActive(Map map);

	public int updateOfferInstAttrForActive(Map map);


	public List<Map<String, Object>> getOfferInstAttr(Map map);



}