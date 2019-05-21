package com.al.nppm.business.inter.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.al.nppm.business.account.dao.IAccountMapper;
import com.al.nppm.business.account.dao.IProdInstMapper;
import com.al.nppm.business.account.dao.OfferInstMapper;
import com.al.nppm.business.account.service.impl.AccountService;
import com.al.nppm.business.inter.http.state.statePublic;
import com.al.nppm.business.inter.service.IinterService;
import com.al.nppm.business.inter.tools.JsonObjectTools;
import com.al.nppm.common.utils.StringUtil;
import com.al.nppm.model.Customer;
import com.al.nppm.model.Message;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
import com.al.nppm.ord.ordbill.dao.OrdBillMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("interService")
@Transactional
public class InterService implements IinterService {
	public static Logger logger = Logger.getLogger(AccountService.class);
	@Autowired
	public IAccountMapper accountDao;
	@Autowired
	public IProdInstMapper prodinstDao;
	@Autowired
	public OfferInstMapper offerinstDao;
	@Autowired
	public OrdBillMapper  ordBillDao;
	
	//同步方案2 读取表处理
	

	//读取总控表数据
	public List<Map<String,Object>> selectOrdBill()
	{
		Map queryMap = new HashMap();
		List<Map<String,Object>> ordBillList =  ordBillDao.selectOrdBill(queryMap);
		return  ordBillList;
	}
	//同步方案1 http接口调用 OpenApI
	public int userDataSynCol(String str,Message msg,List list) throws Exception
	{
		int serviceId=0;
		try{
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject jsonObject = JSONObject.fromObject(str);
		//操作人日志
		if(!StringUtil.isEmpty(jsonObject.get("operAttrStruct")))
		{
			JSONObject operAttrObj = (JSONObject) jsonObject.get("operAttrStruct") ;
			accountDao.insertoperattrstruct(operAttrObj);
		}
		if(!StringUtil.isEmpty(jsonObject.get("tableDataList")))
		{
			serviceId= Integer.parseInt(jsonObject.get("serviceId").toString());
			JSONArray  tableArray = jsonObject.getJSONArray("tableDataList") ;
			//添加工具类对，tableArray按 tableOrder 字段从小到大排序，返回  JSONArray 
			//JSONArray tableArray1 = JsonArraySort.JsonArraySort(tableArray);
			
			for(int i=0;i<tableArray.size();i++)
			{
				JSONObject tableObj = (JSONObject) tableArray.get(i) ;
				String tableName = tableObj.get("tableName").toString().toUpperCase();
				JSONArray  rowDateArry = tableObj.getJSONArray("rowDataList");
				
				//封装json 送密集框架-----------
				
				Map<String, List<?>> map = new HashMap<String, List<?>>();
				List<Map<String, Object>> listM = new ArrayList<Map<String,Object>>();
				
				map.put(tableName, listM);
				list.add(map);
				//--------------
				
				for(int j=0;j<rowDateArry.size();j++)
				{
					JSONObject rowData = (JSONObject) rowDateArry.get(j);
					
					int operType = Integer.parseInt(rowData.get("operType").toString());
					JSONArray columnArry = rowData.getJSONArray("columnList");
					
					JSONObject rowTable = JsonObjectTools.doJsonAray(columnArry);
					//密集框架使用
					JSONObject openJson = JsonObjectTools.doJsonAraySend(columnArry);
					openJson.put("act_id", operType);
					listM.add(openJson);
					
					if(operType==1)
					{
						if(tableName.equals("CUSTOMER"))
						{
							accountDao.insertCustomer(rowTable);
						}else if (tableName.equals("ACCOUNT"))
						{
							///rowTable.put("regionId", 1);
							accountDao.insertAccount(rowTable);
						}else if (tableName.equals("PAYMENT_PLAN"))
						{
							accountDao.insertPaymentPlan(rowTable);
						}
						else if (tableName.equals("EXT_ACCT"))
						{
							accountDao.insertExtAcct(rowTable);
						}else if (tableName.equals("PROD_INST"))
						{
							prodinstDao.insertProdInst(rowTable);
						}else if (tableName.equals("PROD_INST_REGION"))
						{
							prodinstDao.insertProdInstRegion(rowTable);
						}else if (tableName.equals("PROD_INST_GROUP"))
						{
							prodinstDao.insertProdInstGroup(rowTable);
						}else if (tableName.equals("PROD_INST_STATE"))
						{
							prodinstDao.insertProdInstState(rowTable);
						}else if (tableName.equals("PROD_INST_STATE_EXT"))
						{
							prodinstDao.insertProdInstStateExt(rowTable);
						}else if (tableName.equals("PROD_INST_PAYMODE"))
						{
							prodinstDao.insertProdInstPyamode(rowTable);
						}else if (tableName.equals("PROD_INST_ACC_NUM"))
						{
							prodinstDao.insertProdInstAccNum(rowTable);
						}else if (tableName.equals("PROD_INST_ATTR"))
						{
							prodinstDao.insertProdInstAttr(rowTable);
						}else if (tableName.equals("PROD_INST_ACCT_REL"))
						{
							prodinstDao.insertProdInstAcctRel(rowTable);
						}else if (tableName.equals("PROD_INST_REL"))
						{
							prodinstDao.insertProdInstRel(rowTable);
						}else if (tableName.equals("PROD_INST_SUB"))
						{
							prodinstDao.insertProdInstSub(rowTable);
						}else if (tableName.equals("PROD_INST_ATTR_SUB"))
						{
							prodinstDao.insertProdInstAttrSub(rowTable);
						}else if (tableName.equals("OFFER_INST"))
						{
							offerinstDao.insertOfferInst(rowTable);
						}else if (tableName.equals("OFFER_OBJ_INST_REL"))
						{
							offerinstDao.insertOfferObjInstRel(rowTable);
						}else if (tableName.equals("OFFER_INST_ATTR"))
						{
							offerinstDao.insertOfferInstAttr(rowTable);
						}
					}
					else if (operType==2||operType==3)
					{
						if(tableName.equals("CUSTOMER"))
						{
							accountDao.updateCustomer(rowTable);
						}else if (tableName.equals("ACCOUNT"))
						{
							rowTable.put("regionId", 1);
							accountDao.updateAccount(rowTable);
						}else if (tableName.equals("PAYMENT_PLAN"))
						{
							accountDao.updatePaymentPlan(rowTable);
						}
						else if (tableName.equals("EXT_ACCT"))
						{
							accountDao.updateExtAcct(rowTable);
						}else if (tableName.equals("PROD_INST"))
						{
							prodinstDao.updateProdInst(rowTable);
						}else if (tableName.equals("PROD_INST_REGION"))
						{
							prodinstDao.updateProdInstRegion(rowTable);
						}else if (tableName.equals("PROD_INST_GROUP"))
						{
							prodinstDao.updateProdInstGroup(rowTable);
						}else if (tableName.equals("PROD_INST_STATE"))
						{
							prodinstDao.updateProdInstState(rowTable);
						}else if (tableName.equals("PROD_INST_STATE_EXT"))
						{
							prodinstDao.updateProdInstStateExt(rowTable);
						}else if (tableName.equals("PROD_INST_PAYMODE"))
						{
							prodinstDao.updateProdInstPaymode(rowTable);
						}else if (tableName.equals("PROD_INST_ACC_NUM"))
						{
							prodinstDao.updateProdInstAccNum(rowTable);
						}else if (tableName.equals("PROD_INST_ATTR"))
						{
							prodinstDao.updateProdInstAttr(rowTable);
						}else if (tableName.equals("PROD_INST_ACCT_REL"))
						{
							prodinstDao.updateProdInstAcctRel(rowTable);
						}else if (tableName.equals("PROD_INST_REL"))
						{
							prodinstDao.updateProdInstRel(rowTable);
						}else if (tableName.equals("PROD_INST_SUB"))
						{
							prodinstDao.updateProdInstSub(rowTable);
						}else if (tableName.equals("PROD_INST_ATTR_SUB"))
						{
							prodinstDao.updateProdInstAttrSub(rowTable);
						}else if (tableName.equals("OFFER_INST"))
						{
							offerinstDao.updateOfferInst(rowTable);
						}else if (tableName.equals("OFFER_OBJ_INST_REL"))
						{
							offerinstDao.updateOfferObjInstRel(rowTable);
						}else if (tableName.equals("OFFER_INST_ATTR"))
						{
							offerinstDao.updateOfferInstAttr(rowTable);
						}
					}
					
					else{}
					
				}
				
			}
			
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			msg.setFlag(statePublic.FAILFLAG);
			//msg.setMessage("处理失败");
			msg.setResultCode("400");
			msg.setResultMsg("FAIL");
			msg.setServiceId(serviceId);
			throw e;
		}
		msg.setFlag(statePublic.SUCESSFLAG);
		//msg.setMessage("SUCESS");
		msg.setResultCode("800");
		msg.setResultMsg("SUCESS");
		msg.setServiceId(serviceId);
		return 1;
	}
	public int userServiceCol(String str,Message msg) throws Exception
	{
		int operType=0;
		JSONObject jsonObject = JSONObject.fromObject(str);
		
		long custId = Long.parseLong(jsonObject.get("CUST_ID").toString());
		long acctId = Long.parseLong(jsonObject.get("ACCT_ID").toString());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//定义对象
		JSONObject prodObj= new JSONObject();
		
		//------------------------------客户相关---------------------------------------------
		//CUSTOMER
		if(!StringUtil.isEmpty(jsonObject.get("CUSTOMER")))
		{
			JSONObject cobj = (JSONObject) jsonObject.get("CUSTOMER") ;
			Customer cust = new Customer();
			try{
				cust.setRouteId(custId);
				
				if(Integer.parseInt(cobj.get("operType").toString())==1000)
				{
					cobj.put("routeId", Long.parseLong(cobj.get("custId").toString()));
					
					accountDao.insertCustomer(cobj);
				}else if(Integer.parseInt(cobj.get("operType").toString())==2000)
				{
					accountDao.updateCustomer(cobj);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				msg.setFlag(statePublic.FAILFLAG);
				msg.setMessage("CUSTOMER处理失败");
				throw e;
			}
			
		}	
 		//TaxPayer
		/*if(!StringUtil.isEmpty(jsonObject.get("TAX_PAYER")))
		{
			JSONObject taxObj = (JSONObject) jsonObject.get("TAX_PAYER") ;
		
				try{
					
					if(Integer.parseInt(taxObj.get("operType").toString())==1000)	
					{   
						taxObj.put("routeId", acctId);
						
						accountDao.insertTaxPayer(taxObj);
					}else if(Integer.parseInt(taxObj.get("operType").toString())==2000)
					{
						
						accountDao.updateTaxPayer(taxObj);
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					msg.setFlag(statePublic.FAILFLAG);
					msg.setMessage("TAX_PAYER处理失败");
					throw e;
				}
		}
		//TaxPayerAttr
		if(!StringUtil.isEmpty(jsonObject.get("TAX_PAYER_ATTR")))
		{
			
			JSONArray  taxpayerAttrArry = jsonObject.getJSONArray("TAX_PAYER_ATTR") ;//处理空的问题
			
			for(int i=0;i<taxpayerAttrArry.size();i++)
			{
				JSONObject taxPayerAttrObj = (JSONObject) taxpayerAttrArry.get(i) ;
				if(!StringUtil.isEmpty(taxPayerAttrObj))
				{
					try{
						if(Integer.parseInt(taxPayerAttrObj.get("operType").toString())==1000)	
						{
							taxPayerAttrObj.put("routeId", acctId);
							accountDao.insertTaxPayerAttr(taxPayerAttrObj);
						}else if(Integer.parseInt(taxPayerAttrObj.get("operType").toString())==2000)
						{
							accountDao.updateTaxPayerAttr(taxPayerAttrObj);
						}	
						
					}catch(Exception e)
					{
						e.printStackTrace();
						msg.setFlag(statePublic.FAILFLAG);
						msg.setMessage("TAX_PAYER_ATTR处理失败");
						throw e;
					}
				}
			}
		}*/
		//------------------------------账户相关---------------------------------------------
		//ACCOUNT
		if(!StringUtil.isEmpty(jsonObject.get("ACCOUNT")))
		{
			JSONObject acctObj = (JSONObject) jsonObject.get("ACCOUNT") ;
			if(!acctObj.isNullObject())
			{
				try{
					if(Integer.parseInt(acctObj.get("operType").toString())==1000)	
					{
						acctObj.put("routeId", acctId);
						acctObj.put("regionId", 1);
						accountDao.insertAccount(acctObj);
					}else if(Integer.parseInt(acctObj.get("operType").toString())==2000)
					{
						accountDao.updateAccount(acctObj);
					}	
				}catch(Exception e)
					{
						e.printStackTrace();
						msg.setFlag(statePublic.FAILFLAG);
						msg.setMessage("ACCOUNT处理失败");
						throw e;
					}
			}
		}
		//PAYMENT_PLAN
		if(!StringUtil.isEmpty(jsonObject.get("PAYMENT_PLAN")))
		{
			JSONObject paymentObj = (JSONObject) jsonObject.get("PAYMENT_PLAN") ;
			if(!paymentObj.isNullObject())
			{
				try{
					if(Integer.parseInt(paymentObj.get("operType").toString())==1000)	
					{
						paymentObj.put("routeId", acctId);
						accountDao.insertPaymentPlan(paymentObj);
					}else if(Integer.parseInt(paymentObj.get("operType").toString())==2000)
					{
						accountDao.updatePaymentPlan(paymentObj);
					}	
					
				}catch(Exception e)
					{
						e.printStackTrace();
						msg.setFlag(statePublic.FAILFLAG);
						msg.setMessage("PAYMENT_PLAN处理失败");
						throw e;
					}
			}
		}
		
		//EXT_ACCT
		if(!StringUtil.isEmpty(jsonObject.get("EXT_ACCT")))
		{
			JSONObject extObj = (JSONObject) jsonObject.get("EXT_ACCT") ;
			if(!extObj.isNullObject())
			{
				try{
					if(Integer.parseInt(extObj.get("operType").toString())==1000)	
					{	
						extObj.put("routeId", acctId);
						accountDao.insertExtAcct(extObj);
					}else if(Integer.parseInt(extObj.get("operType").toString())==2000)
					{
						accountDao.updateExtAcct(extObj);
					}	
					
				}catch(Exception e)
					{
						e.printStackTrace();
						msg.setFlag(statePublic.FAILFLAG);
						msg.setMessage("EXT_ACCT处理失败");
						throw e;
					}
			}
		}
		
		//------------------------------用户相关---------------------------------------------
		//PROD_INST
		if(!StringUtil.isEmpty(jsonObject.get("PROD_INST")))
		{
			try{
				     prodObj = (JSONObject) jsonObject.get("PROD_INST") ;
				     operType=Integer.parseInt(prodObj.get("operType").toString());
					if(operType==1000)
					{	prodObj.put("expDate", "3000-01-01 00:00:00");
						prodObj.put("routeId", acctId);
						//1.prod_inst
						prodinstDao.insertProdInst(prodObj);
						//2.prod_inst_region
						/*prodObj.put("effDate", prodObj.get("statusDate"));
						long prodInstRegionId = prodinstDao.getSequeId();
						prodObj.put("prodInstRegionId", prodInstRegionId);
						prodinstDao.insertProdInstRegion(prodObj);*/
						//3.判断prodId 是否为群组产品 PROD_COMP_TYPE =13 代表群组产品----------------------
						
						/*List<Map<String,Object>> productTypeList = prodinstDao.getProductType(prodObj);
						if(productTypeList.size()>0)
						{
							Map typeMap = productTypeList.get(0);
							String productType = typeMap.get("PROD_COMP_TYPE").toString();
							if(productType.equals("13"))
							{
								prodinstDao.insertProdInstGroup(prodObj);
							}
						}else{
							msg.setMessage("PROD_INST处理取prod类型失败");
							return -1;
						}*/
						
						//4.默认构造一条167属性
						/*long prodInstAttrId= prodinstDao.getSequeId();
						prodObj.put("prodInstAttrId", prodInstAttrId);   
						prodObj.put("parProdInstAttrId", 1);
						prodObj.put("attrId", 167);
						prodObj.put("attrValueId", 1);
						prodinstDao.insertProdInstAttrSub(prodObj);*/
						
					}else if(operType==2000)
					{
						prodinstDao.updateProdInst(prodObj);
						/*prodObj.put("expDate", prodObj.get("statusDate"));
						prodinstDao.updateProdInstRegion(prodObj);*/
					}else if(operType==3000)
					{
						prodinstDao.updateProdInst(prodObj);
					/*	prodObj.put("expDate", prodObj.get("statusDate"));
						prodinstDao.updateProdInstRegion(prodObj);*/
						//失效群组
//						prodinstDao.updateProdInstGroup(prodObj);
					}
				
			}catch(Exception e)
			{
				e.printStackTrace();
				msg.setMessage("PROD_INST处理失败");
				throw e;
			}
		}
		//PROD_INST_STATE
		//JSONArray  stateOBJ = jsonObject.getJSONArray("PROD_INST_STATE") ;//处理空的问题
		if(!StringUtil.isEmpty(jsonObject.get("PROD_INST_REGION")))
		{
			JSONArray  regionArry = jsonObject.getJSONArray("PROD_INST_REGION") ;
			if(regionArry.size()>0)
			{
				for(int i=0;i<regionArry.size();i++)
				{
					JSONObject regionObj = (JSONObject) regionArry.get(i) ;
					if(!StringUtil.isEmpty(regionObj))
					{
						
						operType=Integer.parseInt(regionObj.get("operType").toString());
						if(operType==1000)
						{
							regionObj.put("routeId", acctId);
							prodinstDao.insertProdInstRegion(regionObj);
							
						}else if(operType==2000)
						{
							prodinstDao.updateProdInstRegion(regionObj);
						}
					}
				}
			}
			
		}
		if(!StringUtil.isEmpty(jsonObject.get("PROD_INST_GROUP")))
		{
			JSONArray  groupArry = jsonObject.getJSONArray("PROD_INST_GROUP") ;
			if(groupArry.size()>0)
			{
				for(int i=0;i<groupArry.size();i++)
				{
					JSONObject groupObj = (JSONObject) groupArry.get(i) ;
					if(!StringUtil.isEmpty(groupObj))
					{
						
						operType=Integer.parseInt(groupObj.get("operType").toString());
						if(operType==1000)
						{
							groupObj.put("routeId", acctId);
							prodinstDao.insertProdInstGroup(groupObj);
							
						}else if(operType==2000)
						{
							prodinstDao.updateProdInstGroup(groupObj);
						}
					}
				}
			}
			
		}
		if(!StringUtil.isEmpty(jsonObject.get("PROD_INST_STATE")))
		{
			JSONArray  stateArry = jsonObject.getJSONArray("PROD_INST_STATE") ;
			String expDate ="";
			
			for(int i=0;i<stateArry.size();i++)
			{
				JSONObject stateObj = (JSONObject) stateArry.get(i) ;
				if(!StringUtil.isEmpty(stateObj))
				{
					try{
						stateObj.put("routeId", acctId);
						operType=Integer.parseInt(stateObj.get("operType").toString());
						if(operType==1000)	
						{  
							/*expDate = stateObj.getString("statusDate");//赋值若是存在时先赋值
							stateObj.put("effDate", stateObj.get("statusDate"));
							
							stateObj.put("expDate", "3000-01-01 00:00:00");*/
							prodinstDao.insertProdInstState(stateObj);
							stateObj.put("state", stateObj.get("statusCd").toString());
							prodinstDao.insertProdInstStateExt(stateObj);
						}else if(operType==2000||operType==3000)//状态变更
						{
							
							/*if(expDate==""||expDate==null){
								stateObj.put("expDate", stateObj.get("statusDate"));
							}else{
								stateObj.put("expDate", expDate);
							}*/
							//变更处理
							prodinstDao.updateProdInstState(stateObj);
							prodinstDao.updateProdInstStateExt(stateObj);
							
						}	
					}catch(Exception e)
						{
							e.printStackTrace();
							msg.setFlag(statePublic.FAILFLAG);
							msg.setMessage("PROD_INST_STATE处理失败");
							throw e;
						}
				}
			}
		}
		//PROD_INST_PAYMODE
		if(!StringUtil.isEmpty(jsonObject.get("PROD_INST_PAYMODE")))
		{
			String expDate="";
			
					try{
						JSONArray  paymodeArry = jsonObject.getJSONArray("PROD_INST_PAYMODE") ;
						for(int i=0;i<paymodeArry.size();i++)
						{
							JSONObject paymodedObj = (JSONObject) paymodeArry.get(i) ;
							if(!StringUtil.isEmpty(paymodedObj))
							{    operType = Integer.parseInt(paymodedObj.get("operType").toString());
								if(operType==1000)
								{	
									/*expDate = paymodedObj.getString("statusDate");//赋值若是变更需要
									paymodedObj.put("effDate", paymodedObj.get("statusDate"));
									paymodedObj.put("expDate", "3000-01-01 00:00:00");*/
									paymodedObj.put("routeId", acctId);
									prodinstDao.insertProdInstPyamode(paymodedObj);
								}else if(operType==2000||operType==3000)
								{
									/*if(expDate==""||expDate==null){
										paymodedObj.put("expDate", paymodedObj.get("statusDate"));
									}else{
										paymodedObj.put("expDate", expDate);
									}*/
									//修改
									prodinstDao.updateProdInstPaymode(paymodedObj);
								}
							}
						}
					}catch(Exception e)
					{
						e.printStackTrace();
						msg.setMessage("PROD_INST_PAYMODE处理失败");
						throw e;
					}
		}
		//PROD_INST_ACC_NUM
		if(!StringUtil.isEmpty(jsonObject.get("PROD_INST_ACC_NUM")))
		{
			JSONArray  accnumArry = jsonObject.getJSONArray("PROD_INST_ACC_NUM") ;
			String expDate="";
			
				try{
						for(int i=0;i<accnumArry.size();i++)
						{
							JSONObject accnumdObj = (JSONObject) accnumArry.get(i) ;
							if(!StringUtil.isEmpty(accnumdObj))
							{     operType =Integer.parseInt(accnumdObj.get("operType").toString());
								if(operType==1000)
									{
										/*expDate = accnumdObj.getString("statusDate");//赋值若是变更需要
										accnumdObj.put("expDate", "3000-01-01 00:00:00"); //TIMESTAMP类型插入不了，date可行
										accnumdObj.put("effDate", accnumdObj.get("statusDate"));*/
										accnumdObj.put("routeId", acctId);
										prodinstDao.insertProdInstAccNum(accnumdObj);
									}else if(operType==2000||operType==3000)
									{
										/*if(expDate==""||expDate==null){
											accnumdObj.put("expDate", accnumdObj.get("statusDate"));
										}else{
											accnumdObj.put("expDate", expDate);
										}*/
										//修改
										prodinstDao.updateProdInstAccNum(accnumdObj);
									}
							}
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					    msg.setMessage("PROD_INST_acc_num处理失败");
						throw e;
					}
		 }
		
		//PROD_INST_ATTR
			if(!StringUtil.isEmpty(jsonObject.get("PROD_INST_ATTR")))
			{
				try{
					JSONArray  prodAttrArry = jsonObject.getJSONArray("PROD_INST_ATTR") ;
					String expDate="";
					
					for(int i=0;i<prodAttrArry.size();i++)
					{

						JSONObject prodAttrObj = (JSONObject) prodAttrArry.get(i) ;
						if(!StringUtil.isEmpty(prodAttrObj))
						{     
							operType=Integer.parseInt(prodAttrObj.get("operType").toString());
							if(operType==1000)
							{	
								/*expDate = prodAttrObj.getString("statusDate");//赋值若是变更需要
								prodAttrObj.put("expDate", "3000-01-01 00:00:00"); //TIMESTAMP类型插入不了，date可行
								prodAttrObj.put("effDate", prodAttrObj.get("statusDate"));*/
								prodAttrObj.put("routeId", acctId);
								prodinstDao.insertProdInstAttr(prodAttrObj);
							}else if(operType==2000||operType==3000)
							{
								/*if(expDate==""||expDate==null){
									prodAttrObj.put("expDate", prodAttrObj.get("statusDate"));
								}else{
									prodAttrObj.put("expDate", expDate);
								}*/
								
								prodinstDao.updateProdInstAttr(prodAttrObj);
							}
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					msg.setMessage("PROD_INST_ATTR处理失败");
					throw e;
				}
			}
		//PROD_INST_REL
			if(!StringUtil.isEmpty(jsonObject.get("PROD_INST_REL")))
			{
				try{
					JSONArray  prodrelArry = jsonObject.getJSONArray("PROD_INST_REL") ;
					String expDate="";
					for(int i=0;i<prodrelArry.size();i++)
					{
						JSONObject prodrelObj = (JSONObject) prodrelArry.get(i) ;
						if(!StringUtil.isEmpty(prodrelObj))
						{     
							operType=Integer.parseInt(prodrelObj.get("operType").toString());
							if(operType==1000)
							{	
								/*expDate = prodrelObj.getString("statusDate");//赋值若是变更需要
								prodrelObj.put("expDate", "3000-01-01 00:00:00"); //TIMESTAMP类型插入不了，date可行
								prodrelObj.put("effDate", prodrelObj.get("statusDate"));*/
								prodrelObj.put("routeId", acctId);
								prodinstDao.insertProdInstRel(prodrelObj);
							}else if(operType==2000||operType==3000)
							{
								/*if(expDate==""||expDate==null){
									prodrelObj.put("expDate", prodrelObj.get("statusDate"));
								}else{
									prodrelObj.put("expDate", expDate);
								}*/
								
								prodinstDao.updateProdInstRel(prodrelObj);
							}
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					msg.setMessage("PROD_INST_REL处理失败");
					throw e;
				}
			}
			
			//PROD_INST_SUB
			if(!StringUtil.isEmpty(jsonObject.get("PROD_INST_SUB")))
			{
				try{
					JSONArray  prodsubArry = jsonObject.getJSONArray("PROD_INST_SUB") ;
					
					for(int i=0;i<prodsubArry.size();i++)
					{
						JSONObject prodsubObj = (JSONObject) prodsubArry.get(i) ;
						if(!StringUtil.isEmpty(prodsubObj)) 
						{     operType=Integer.parseInt(prodsubObj.get("operType").toString());
							if(operType==1000)
							{	
								prodsubObj.put("routeId", acctId);
								prodinstDao.insertProdInstSub(prodsubObj);
							
							}else if(operType==2000||operType==3000)
							{
								prodinstDao.updateProdInstSub(prodsubObj);
							}
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					
					msg.setMessage("PROD_INST_SUB处理失败");
					throw e;
				}
			}
			
			//PROD_INST__ATTR_SUB
			if(!StringUtil.isEmpty(jsonObject.get("PROD_INST_ATTR_SUB")))
			{
				try{
					JSONArray  prodsubAttrArry = jsonObject.getJSONArray("PROD_INST_ATTR_SUB") ;
					
					for(int i=0;i<prodsubAttrArry.size();i++)
					{
						JSONObject prodsubAttrObj = (JSONObject) prodsubAttrArry.get(i) ;
						if(!StringUtil.isEmpty(prodsubAttrObj)) //判断prod_inst是否为新增若为新增，默认构造
						{     operType=Integer.parseInt(prodsubAttrObj.get("operType").toString());
							if(operType==1000)
							{	
								prodsubAttrObj.put("routeId", acctId);
								prodinstDao.insertProdInstAttrSub(prodsubAttrObj);
								
							}else if(operType==2000||operType==3000)
							{
								
							}
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					
					msg.setMessage("PROD_INST_ATTR_SUB处理失败");
					throw e;
				}
			}
			//PROD_INST_ACCT_REL
			if(!StringUtil.isEmpty(jsonObject.get("PROD_INST_ACCT_REL")))
			{
				try{
					JSONArray  prodAcctArry = jsonObject.getJSONArray("PROD_INST_ACCT_REL") ;
					
					for(int i=0;i<prodAcctArry.size();i++)
					{
						JSONObject prodAcctObj = (JSONObject) prodAcctArry.get(i) ;
						if(!StringUtil.isEmpty(prodAcctObj)) 
						{     operType=Integer.parseInt(prodAcctObj.get("operType").toString());
							if(operType==1000)
							{	
								prodAcctObj.put("routeId", acctId);
								prodinstDao.insertProdInstAcctRel(prodAcctObj);
								
							}else if(operType==2000||operType==3000)
							{
								
								prodinstDao.updateProdInstAcctRel(prodAcctObj);
							}
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					
					msg.setMessage("PROD_INST_ACCT_REL处理失败");
					throw e;
				}
			}
			
		 //offerinst
		if(!StringUtil.isEmpty(jsonObject.get("OFFER_INST")))
			{
				try{
					JSONArray  offerInstArry = jsonObject.getJSONArray("OFFER_INST") ;
					
					for(int i=0;i<offerInstArry.size();i++)
					{
						JSONObject offerInstObj = (JSONObject) offerInstArry.get(i) ;
						if(!StringUtil.isEmpty(offerInstObj)) 
						{     operType=Integer.parseInt(offerInstObj.get("operType").toString());
							if(operType==1000)
							{	
								offerInstObj.put("routeId", acctId);
								offerinstDao.insertOfferInst(offerInstObj);
								
							}else if(operType==2000||operType==3000)
							{
								offerinstDao.updateOfferInst(offerInstObj);
							}
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					
					msg.setMessage("OFFER_INST处理失败");
					throw e;
				}
			}
		
		 //OFFER_OBJ_INST_REL 来源于 OFFER_PROD_INST_REL 有产品订购
		if(!StringUtil.isEmpty(jsonObject.get("OFFER_PROD_INST_REL")))
			{
				try{
					JSONArray  offerObjArry = jsonObject.getJSONArray("OFFER_PROD_INST_REL") ;
					
					for(int i=0;i<offerObjArry.size();i++)
					{
						JSONObject offerObjObj = (JSONObject) offerObjArry.get(i) ;
						if(!StringUtil.isEmpty(offerObjObj)) 
						{     
							operType=Integer.parseInt(offerObjObj.get("operType").toString());
							if(operType==1000)
							{	
								offerObjObj.put("routeId", acctId);
								//需要特殊处理-------------------------需要特殊处理----------------------------------------
								//获取offerObjRelId 转换为计费需要的offer_detail_id 通过OFFER_ID  和 role_id 查找表offer_obj_rel 找到计费要的
								Long CrmDetailId=offerObjObj.getLong("offerObjRelId");
								//offerObjObj.put("offerObjRelId", 111);
								//需要特殊处理-------------------------需要特殊处理----------------------------------------
								offerinstDao.insertOfferObjInstRel(offerObjObj);
								
							}else if(operType==2000||operType==3000)
							{
								offerinstDao.updateOfferObjInstRel(offerObjObj);
							}
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					
					msg.setMessage("OFFER_PROD_INST_REL处理失败");
					throw e;
				}
			}
		
		 //OFFER_OBJ_INST_REL 来源于 ORD_OFFER_OBJ_INST_REL 由 账户 客户对象订购
		if(!StringUtil.isEmpty(jsonObject.get("OFFER_OBJ_INST_REL")))
			{
				try{
					JSONArray  offerObjArry = jsonObject.getJSONArray("OFFER_OBJ_INST_REL") ;
					
					for(int i=0;i<offerObjArry.size();i++)
					{
						JSONObject offerObjObj = (JSONObject) offerObjArry.get(i) ;
						if(!StringUtil.isEmpty(offerObjObj)) 
						{     
							operType=Integer.parseInt(offerObjObj.get("operType").toString());
							if(operType==1000)
							{	
								offerObjObj.put("routeId", acctId);
								//需要特殊处理------暂时直接用------------------------------------
								//获取offerObjRelId 转换为计费需要的offer_detail_id 通过OFFER_ID  和 role_id 查找表offer_obj_rel 找到计费要的
								//Long CrmDetailId=offerObjObj.getLong("offerObjRelId");
								//offerObjObj.put("offerObjRelId", 111);
								//需要特殊处理-------------------------需要特殊处理----------------------------------------
								offerinstDao.insertOfferObjInstRel(offerObjObj);
								
							}else if(operType==2000||operType==3000)
							{
								offerinstDao.updateOfferObjInstRel(offerObjObj);
							}
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					
					msg.setMessage("OFFER_OBJ_INST_REL处理失败");
					throw e;
				}
			}
		 //OFFER_INST_ATTR
		if(!StringUtil.isEmpty(jsonObject.get("OFFER_INST_ATTR")))
			{
				try{
					JSONArray  offerInstAttrArry = jsonObject.getJSONArray("OFFER_INST_ATTR") ;
					
					for(int i=0;i<offerInstAttrArry.size();i++)
					{
						JSONObject offerInstAttrObj = (JSONObject) offerInstAttrArry.get(i) ;
						if(!StringUtil.isEmpty(offerInstAttrObj)) 
						{     
							operType=Integer.parseInt(offerInstAttrObj.get("operType").toString());
							if(operType==1000)
							{	
								offerInstAttrObj.put("routeId", acctId);
								offerinstDao.insertOfferInstAttr(offerInstAttrObj);
								
							}else if(operType==2000||operType==3000)
							{
								offerinstDao.updateOfferInstAttr(offerInstAttrObj);
							}
						}
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					msg.setFlag(statePublic.FAILFLAG);
					msg.setMessage("OFFER_INST处理失败");
					throw e;
				}
			}
		
		msg.setFlag(statePublic.SUCESSFLAG);
		msg.setMessage("SUCESS");

		return 1;
	}
public long getSequenId()
{
	return prodinstDao.getSequeId();
}
}
