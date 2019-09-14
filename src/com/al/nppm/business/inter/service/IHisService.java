package com.al.nppm.business.inter.service;

import com.al.nppm.model.Message;

import java.util.Map;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/1 9:26
 * @File : IHisService
 * @Software: IntelliJ IDEA 2019.3.15
 */
public interface IHisService {
     int backupProdInst(Map itemMap, Map userMap, Message msg);
//     int insertProdInst(Map itemMap, Map userMap, Message msg);
	public int insertCustomerHis(Map map) throws Exception;
	public int insertCustContactInfoRelHis(Map map) throws Exception;
	public int insertAccountHis(Map map);//账户域
	public int insertExtAcctHis(Map map);
}
