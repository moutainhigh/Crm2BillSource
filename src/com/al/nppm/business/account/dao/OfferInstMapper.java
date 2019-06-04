package com.al.nppm.business.account.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface OfferInstMapper {

	public int insertOfferInst(Map map);
	public int updateOfferInst(Map map);
	public int insertOfferInstAttr(Map map);
	public int updateOfferInstAttr(Map map);
	
	public int insertOfferObjInstRel(Map map);
	public int updateOfferObjInstRel(Map map);
	public List<Map<String,Object>> getOfferInstId(Map map);
	public List<Map<String,Object>> getOfferObjInstId(Map map);
	public List<Map<String,Object>> getOfferRoleId(Map map);
	public List<Map<String,Object>> getOfferInstAttrId(Map map);
	public List<Map<String,Object>> getOfferInstAttrIdFromAttrId(Map map);
	public List<Map<String,Object>> getOFFERID(Map map);
	
	
	
	//==========================================
	public List<Long> getOfferInstIdList(Map map);
	
	public int updateOfferInstByOfferInstId(Map map);
	
	public Long getOfferObjInstRelId(Map map);
	public Long getOfferInstAttrId1(Map map);
	
	

}