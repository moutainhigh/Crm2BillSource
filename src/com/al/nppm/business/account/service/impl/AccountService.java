package com.al.nppm.business.account.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.al.nppm.business.account.dao.IAccountMapper;
import com.al.nppm.business.account.dao.IProdInstMapper;
import com.al.nppm.business.account.service.IAccountService;

import com.al.nppm.business.common.SpringContextHolder;
import com.al.nppm.business.common.service.impl.CommonServiceImpl;
import com.al.nppm.business.inter.http.state.statePublic;
import com.al.nppm.business.syntomq.datasyn.DataSynDeal;

import com.al.nppm.common.aop.DataSourceMap;
import com.al.nppm.common.exception.AiException;

import com.al.nppm.common.utils.JsonUtil;
import com.al.nppm.common.utils.MaptoClass;
import com.al.nppm.common.utils.StringUtil;

import com.al.nppm.model.Customer;
import com.al.nppm.model.Message;
import com.al.nppm.model.Offer;
import com.al.nppm.model.BillServiceLog;
import com.al.nppm.model.CustAttr;
import com.al.nppm.model.CustContactInfoRel;
import com.al.nppm.model.PartyCert;
import com.al.nppm.test.untilTest;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("accountService")
@Transactional
public class AccountService implements IAccountService {
	public static Logger logger = Logger.getLogger(AccountService.class);
	@Autowired
	public IAccountMapper accountDao;
	@Autowired
	public IProdInstMapper prodinstDao;

	
	public int user(String str,Message msg) throws Exception
	{
		JSONObject jsonObject = JSONObject.fromObject(str);
		if(!StringUtil.isEmpty(jsonObject.get("CUSTOMER")))
		{
			JSONObject cobj = (JSONObject) jsonObject.get("CUSTOMER") ;
			Customer cust = new Customer();
			
				//MaptoClass.handData(cust, cobj);
			if(accountDao.insertCustomer(cobj)<0)
			{
				return -1;
			}
		}	
		
		if(!StringUtil.isEmpty(jsonObject.get("PAYMENT_PLAN")))
		{
			JSONObject accObj = (JSONObject) jsonObject.get("PAYMENT_PLAN") ;
			if(!accObj.isNullObject())
			{
				if(accountDao.insertPaymentPlan(accObj)<0)
				{
					//tranManager(id,"账户信息PAYMENT_PLAN处理失败");
					return -1;
				}
			}
		}
		if(!StringUtil.isEmpty(jsonObject.get("PROD_INST")))
		{
			try{
				JSONObject prodObj = (JSONObject) jsonObject.get("PROD_INST") ;
				if(!prodObj.isNullObject())
				{
					if(Integer.parseInt(prodObj.get("operType").toString())==1000)
					{	
						//1.prod_inst
						prodinstDao.insertProdInst(prodObj);
						prodObj.put("stopType", 0);
						//2.prod_inst_state
						prodinstDao.insertProdInstState(prodObj);
						prodObj.put("stopType", 0);
						prodObj.put("state", prodObj.get("statusCd").toString());
						//3.prod_inst_state_ext
						prodinstDao.insertProdInstStateExt(prodObj);
						//4.prod_inst_region
						prodinstDao.insertProdInstRegion(prodObj);
						//5.prod_inst_paymode
						prodinstDao.insertProdInstPyamode(prodObj);
						
						
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				msg.setMessage("PROD_INSTchulishibai");
				throw e;
			}
		}
		
		return 1;
	}
	
	
	
	public int doExtAcct(Map map)
	{
		try{
			
			if(Integer.parseInt(map.get("operType").toString())==1000)
			{	map.put("statusCd", 1);
				accountDao.insertExtAcct(map);
						
			}else{
				map.put("statusCd", 2);
				accountDao.updateExtAcct(map);
			}
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("处理ExtAcct异常:"+e.getMessage());
			return -1;
		}
		return 1;
	}
	
	public int doPaymentPlan(Map map)
	{
		try{
			//1.判断是否存在，先失效，后新增
			List<Map<String,Object>> paymentList =  accountDao.getPaymentPlanByAcct(map);
			if(paymentList!=null && paymentList.size()>0)
			{	
				Map paymenMap = new HashMap();
				for(int i=0;i<paymentList.size();i++)
				{
					paymenMap.put("paymentPlanId", paymentList.get(i).get("paymentPlanId"));
					paymenMap.put("statusCd", 2);
					paymenMap.put("expDate", map.get("effDate"));
					accountDao.updatePaymentPlan(paymenMap);
				}
			}
//				if(Integer.parseInt(map.get("operType").toString())==1000)
				//map.put("statusCd", 1);
				accountDao.insertPaymentPlan(map);
			
				
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("处理PaymentPlan账户异常:"+e.getMessage());
			return -1;
		}
		return 1;
	}
	
	public int doAccount(Map map)
	{
		try{
			
			if(Integer.parseInt(map.get("operType").toString())==1000)
			{	//map.put("statusCd", 1);
				accountDao.insertAccount(map);
				
			}else{
				accountDao.updateAccount(map);
			}
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("处理Account账户异常:"+e.getMessage());
			return -1;
		}
		return 1;
	}
	
	public int doTaxPayerAttr(Map map)
	{
		try{
			
			if(Integer.parseInt(map.get("operType").toString())==1000)
			{
				accountDao.insertTaxPayerAttr(map);
			}else{
				accountDao.updateTaxPayerAttr(map);
			}
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("处理TaxPayerAttr异常:"+e.getMessage());
			return -1;
		}
		return 1;
	}
	
	public int doTaxPayer(Map map)
	{
		try{
			
			if(Integer.parseInt(map.get("operType").toString())==1000)
			{
				accountDao.insertTaxPayer(map);
			}else{
				accountDao.updateTaxPayer(map);
			}
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("处理TaxPayer异常:"+e.getMessage());
			return -1;
		}
		return 1;
	}
	
	public int dosertPartyCert(Map map)
	{
		PartyCert party = new PartyCert();
		try{
			MaptoClass.handData(party, map);
			if(Integer.parseInt(map.get("operType").toString())==1000)
			{
				accountDao.insertPartyCert(party);
			}else{
				accountDao.updatePartyCert(map);
			}
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("处理party_cert异常:"+e.getMessage());
			return -1;
		}
		return 1;
	}
	//获取序列
	public Long getSeqByName(Map<String, Object> map){
			return accountDao.getSeqByName(map);
	}
	@Override
	public int doCustAttr(Map map)
	{
		
		CustAttr custAttr = new CustAttr();
		try{
			MaptoClass.handData(custAttr, map);
			if(Integer.parseInt(map.get("operType").toString())==1000)
			{
				accountDao.insertCustAttr(custAttr);
			}else
			{
				accountDao.updateCustAtrr(custAttr);
			}
		
		}catch(Exception e){
			e.printStackTrace();
			//logger.debug("错误44444信息-");
			return -1;
		}
		return 1;
	}
	
	@Override
	public int insertcustContactInfoRel(Map map)
	{
//		logger.info("错误信息----------------------------");
//		logger.debug("错误44444信息----------------------------");
		try{
		CustContactInfoRel crel = new CustContactInfoRel();	
		MaptoClass.handData(crel, map);
		//operType =1000  代表新增
		if(Integer.parseInt(map.get("operType").toString())==1000)
		{
		
			accountDao.insertcustContactInfoRel(crel);
		}else{//修改
			
			accountDao.updateCustContactInfoRel(crel);
		}

		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	@Override
	public int insertCustomer(Map cobj)  {
		Customer cust = new Customer();
		try {
			MaptoClass.handData(cust, cobj);
			//serviceOfferId=1010100000 代表新增客户
			if(Integer.parseInt(cobj.get("operType").toString())==1000)
			{
				accountDao.insertCustomer(cobj);
			}else{//修改客户
				accountDao.updateCustomer(cobj);
			}
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}

		return 1;
	}
	@Override
	public int insertServiceLog(BillServiceLog log){
		
	   accountDao.insertServiceLog(log);
		return 1;
	}

	@Override
	public Long getServiceLogId() {
		
		return accountDao.getServiceLogId();
	}

	@Override
	public int updateServiceLog(BillServiceLog log){
		try {
		accountDao.updateServiceLog(log);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return 1;
	}


	
	@Override
	public List<Map<String, Object>> getPricingParameterTree(Map search) {
		
		List treeList = new ArrayList();
				
		try{
			//通过策略ID，找下有多少度量，并按measureSeq排序
		
			List<Map<String,Object>> measureList = accountDao.getMeasureList(search);
			
			//如果有度量把度量取出来
			if(measureList!=null && measureList.size()>0){				
				 for(int i=0;i<measureList.size();i++){
					 System.out.println(measureList.get(i).get("MEASURE_SEQ"));
				 }
			}
		}catch( Exception e){
			e.printStackTrace();
		}
		return treeList;	
	}
	
	
	public void testSMS()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put( "prodInstId", 1 );
		map.put( "offerObjInstRelId", 1 );
		map.put( "offerInstAttrId", 1 );
		map.put( "prodInstAcctRelId", 1 );
		
		List<Map<String,Object>>  prodInstList = accountDao.getProdInstTest( map );
		List<Map<String,Object>>  prodInstStateList = accountDao.getProdInstStateTest(map);
		List<Map<String,Object>>  prodInstAccNumList = accountDao.getProdInstAccNumTest(map);
		List<Map<String,Object>>  prodInstPayModeList = accountDao.getProdInstPaymodeTest(map);
		List<Map<String,Object>>  prodInstRegionList = accountDao.getProdInstRegionTest(map);
		List<Map<String,Object>>  prodInstAcctRelList = accountDao.getProdInstAcctRelTest(map);
		
		List<Map<String,Object>>  offerInstList = accountDao.getOfferInstTest(map);
		List<Map<String,Object>>  offerInstObjList = accountDao.getOfferInstObjTest(map);
		List<Map<String,Object>>  offerInstAttrList = accountDao.getOfferInstAttrTest(map);
		
		Map<String, List<?>> synMap = new HashMap<String, List<?>>();
		/*synMap.put( "CUST", custobjList1 );
		synMap.put( "ACCT", acctobjList1 );
		synMap.put( "TAX_PAYER", taxPayerobjList1 );
		synMap.put( "TAX_PAYER_ATTR", taxPayerAttrobjList1 );
		synMap.put( "PAYMENT_PLAN", paymentPlanobjList1 );
		synMap.put( "EXT_ACCT", extAcctobjList1 );*/
		synMap.put( "PROD_INST", prodInstList );
//		synMap.put( "PROD_INST_ATTR", prodInstAttrobjList1 );
//		synMap.put( "PROD_INST_SUB", prodInstSubobjList1 );
//		synMap.put( "PROD_INST_REL", prodInstRelobjList1 );
		synMap.put( "PROD_INST_ACCT_REL", prodInstAcctRelList );
		synMap.put( "OFFER_INST", offerInstList );
		synMap.put( "OFFER_OBJ_INST_REL", offerInstObjList );
		synMap.put( "OFFER_INST_ATTR", offerInstAttrList );
		synMap.put( "OFFER_INST", offerInstList );
		synMap.put( "PROD_INST_REGION", prodInstRegionList );
		synMap.put( "PROD_INST_ACC_NUM", prodInstAccNumList );
		synMap.put( "PROD_INST_STATE", prodInstStateList );
		synMap.put( "PROD_INST_PAYMODE", prodInstPayModeList );
//		synMap.put( "PROD_INST_GROUP", prodInstGroupobjList1 );
//		DataSynDeal.autoBuildAndSendMsg(synMap);
		
	}
	public long getBillServiceLogId()
	{
		return accountDao.getBillServiceLogId();
	}
}
