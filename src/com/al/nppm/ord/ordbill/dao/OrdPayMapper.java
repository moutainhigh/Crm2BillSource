package com.al.nppm.ord.ordbill.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrdPayMapper {
	public String getServiceOfferId (Map map);
	public String getAttrValue(Map map);
	public Long getProdInstId(Map map);
	public Long getObjId(Map map);
	public List<Map<String,Object>> selectOrderlist(Map map);
	public int updateOrdOfferInst(Map map);
	public List<Map<String,Object>> selectOneItemList(Map map);
	public int modifyOneItemResult(Map map);



	
}