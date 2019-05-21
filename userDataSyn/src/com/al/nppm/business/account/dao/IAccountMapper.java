package com.al.nppm.business.account.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.al.nppm.model.BillServiceLog;
import com.al.nppm.model.CustAttr;
import com.al.nppm.model.CustContactInfoRel;
import com.al.nppm.model.PartyCert;



@Repository
public interface IAccountMapper {
   
	
	//---------------------------------客户相关-------------------------------------
	//新增客户属性
	public int insertCustAttr(CustAttr custAttr);
	//修改客户属性
	public int updateCustAtrr(CustAttr custAttr);
	//修改客户合同关系
	public int updateCustContactInfoRel(CustContactInfoRel crel);
	//获得客户合同关系
	public List<Map<String,Object>> getCustContactInfoRel(Map map);
	//新增客户合同关系
	public int insertcustContactInfoRel(CustContactInfoRel crel);
	//查询用户
	public List<Map<String,Object>> getCustomer(Map map);
	//新增客户
	public int insertCustomer(Map cust);
	//修改客户
	public int updateCustomer(Map cust);
	//新增客户证件
	public int insertPartyCert(PartyCert partyCert);
	public int updatePartyCert(Map map);
	//客户纳税识别
	public int insertTaxPayer(Map map);
	public int updateTaxPayer(Map map);
	public int insertTaxPayerAttr(Map map);
	public int updateTaxPayerAttr(Map map);
	
	public long getCustomerById(Map map);
	
	//---------------------------------客户相关-------------------------------------
	
	//---------------------------------账户相关-------------------------------------
	//Account
	public int insertAccount(Map map);
	public int updateAccount(Map map);
	//PAYMENT_PLAN
	public List<Map<String,Object>> getPaymentPlanByAcct(Map map);
	public int insertPaymentPlan(Map map);
	public int updatePaymentPlan(Map map);
	//EXT_ACCT
	public int insertExtAcct(Map map);
	public int updateExtAcct(Map map);
	
	public int getAccountById(long accountId);
	public List<Map<String,Object>> getPaymentPlanByID(long paymentPlanId);
	public List<Map<String,Object>> getAccout(long acctId);
	public List<Map<String,Object>> getAccoutFromProdInstId(Map map);
	public List<Map<String,Object>> getAccoutForAccNum(long acctId);
	public long getCntAccoutFromProdInstId(Map map);


	
	
	//---------------------------------账户相关-------------------------------------	
	//接口日志
	public int insertServiceLog(BillServiceLog log);
	//获取接口日志ID
	public Long getServiceLogId();
	//json日志
	public int insertoperattrstruct(Map map);
	
	public void updateServiceLog(BillServiceLog log);

	public List<Map<String,Object>> getMeasureList(Map search);
	public Long getSeqByName(Map<String, Object> map);
	
	
	
	
	//----------------------------------构造数据测试-----------------------------
	public List<Map<String,Object>> getProdInstTest(Map map);
	public List<Map<String,Object>> getProdInstStateTest(Map map);
	public List<Map<String,Object>> getProdInstAccNumTest(Map map);
	public List<Map<String,Object>> getProdInstPaymodeTest(Map map);
	public List<Map<String,Object>> getProdInstRegionTest(Map map);
	public List<Map<String,Object>> getOfferInstTest(Map map);
	public List<Map<String,Object>> getOfferInstObjTest(Map map);
	public List<Map<String,Object>> getOfferInstAttrTest(Map map);
	public List<Map<String,Object>> getProdInstAcctRelTest(Map map);
	
	//序列
	public long getBillServiceLogId();
	
	
	///route_id
	public List<Map<String,Object>> getExtAcctID(Map map);
	
	//	-------------------------- 参与人相关F_INSERT_PARTY开始   -------------------------

	public long getCountPartyById(Map map);
	public int insertParty(Map map);
	public int updateParty(Map map);
	public int updateParty1(Map map);
	

	public int insertPartyInd(Map map);
	public int updatePartyInd(Map map);	
	

	public int insertPartyRole(Map map);
	public int updatePartyRole(Map map);
	

	public int insertPartyAttr(Map map);
	public int updatePartyAttr(Map map);
	

	public int insertContactsInfo(Map map);
	public int updateContactsInfo(Map map);	
	

	public int insertContactsInfoAttr(Map map);
	public int updateContactsInfoAttr(Map map);

	
	
	//	-------------------------- 参与人相关F_INSERT_PARTY结束  -------------------------	
	//	-------------------------- 客户相关F_INSERT_CUSTOMER开始   -------------------------

	public int insertCustCcontactInfoRel(Map map);
	public int updateCustContactInfoRel1(Map map);	
	

	public int insertPartyCert1(Map map);
	public int updatePartyCert1(Map map);
	

	public int insertCustAttr1(Map map);
	public int updateCustAttr(Map map);	
	
	
	
	//	-------------------------- 客户相关F_INSERT_CUSTOMER结束  -------------------------
	
	//	-------------------------- 一般纳税相关F_INSERT_TAXPAYER开始   -------------------------

	public long getCustId(Map map);
	public int insertTaxPayer1(Map map);
	public long getCountTaxPayer(Map map);
	public int updateTaxPayer1(Map map);

	

	public int getCountTaxPayerAttr(Map map);
	public int insertTaxPayerAttr1(Map map);
	public int updateTaxPayerAttr1(Map map);
	
	
	
	//	-------------------------- 一般纳税相关F_INSERT_TAXPAYER结束  -------------------------	
	

}