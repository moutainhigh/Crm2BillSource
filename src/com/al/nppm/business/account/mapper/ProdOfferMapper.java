package com.al.nppm.business.account.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ProdOfferMapper {

	
	public List<Map<String,Object>> getOfferRoleId(Map map);
	public List<Map<String,Object>> getProductType(Map map);
	
	

}