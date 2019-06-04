package com.al.nppm.business.account.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface HisMapper {
	//=================产品实例PROD_INST===============
    public Map selectProdInstHis(Map map);
	public int insertProdInstHis(Map map);
	//-----------------------------客户相关-----------------------------
	public int insertCustomerHis(Map map);
	public int insertCustContactInfoRelHis(Map map);
	//-----------------------------账户相关-----------------------------
	public int insertAccountHis(Map map);
	public int insertExtAcctHis(Map map);
}