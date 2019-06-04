package com.al.nppm.business.account.dao;

import java.util.List;
import java.util.Map;


public interface BTrunkBillingMapper {
	
	public List<Map<String,Object>> insertTrunkbill(Map map);
	public long countTrunkBilling(Map map);
	public int updateTrunkBilling(Map map);


}