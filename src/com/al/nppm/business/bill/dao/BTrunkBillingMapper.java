package com.al.nppm.business.bill.dao;

import java.util.Map;


public interface BTrunkBillingMapper {
	
	public int insertTrunkbill(Map map);
	public long countTrunkBilling(Map map);
	public int updateTrunkBilling(Map map);


}