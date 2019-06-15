package com.al.nppm.business.account.dao;

import java.util.Map;


public interface BTrunkBillingMapper {
	
	public int insertTrunkbill(Map map);
	public long countTrunkBilling(Map map);
	public int updateTrunkBilling(Map map);


}