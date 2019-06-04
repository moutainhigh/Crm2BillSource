package com.al.nppm.business.account.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.al.nppm.common.exception.AiException;
import com.al.nppm.model.Message;
import com.al.nppm.model.BillServiceLog;
import com.al.nppm.model.CustContactInfoRel;
import com.al.nppm.model.PartyCert;

import net.sf.json.JSONObject;

public interface IAccountService {
	public List<Map<String,Object>> getPricingParameterTree(Map search);
	
	//客户纳税属性
	public int doTaxPayerAttr(Map map);
	//客户纳税
	public int doTaxPayer(Map map);
	//客户证件
	public int dosertPartyCert(Map map);
	//客户属性
	public int doCustAttr(Map map);
	//客户合同关系
	public int insertcustContactInfoRel(Map crel);
	//客户
	public int insertCustomer(Map obj);
	
	//-------------------------------------账户----------------------------
	public int doAccount(Map map);
	public int doPaymentPlan(Map map);
	public int doExtAcct(Map map);
	
	
	
	public int user(String str,Message msg) throws Exception;
	
	
	
	
	
	//接口日志
	public int insertServiceLog(BillServiceLog log);
	public Long getServiceLogId();
	public int updateServiceLog(BillServiceLog log);
	
	public Long getSeqByName(Map<String, Object> map);
	public long getBillServiceLogId();
	//public List<Map<String,Object>> getMeasureList(Map search);
}
