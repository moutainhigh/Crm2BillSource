package com.al.nppm.business.inter.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.al.nppm.business.account.dao.IAccountMapper;
import com.al.nppm.business.account.dao.IProdInstMapper;

import com.al.nppm.business.account.dao.OfferInstMapper;
import com.al.nppm.business.account.mapper.ProdOfferMapper;
import com.al.nppm.business.inter.http.state.statePublic;
import com.al.nppm.business.inter.service.ICrmUserService;
import com.al.nppm.business.inter.service.IinterService;
import com.al.nppm.business.syntomq.datasyn.DataSynDeal;
import com.al.nppm.common.utils.StringUtil;
import com.al.nppm.model.Message;
import com.al.nppm.model.BillServiceLog;
import com.al.nppm.model.CustAttr;
import com.al.nppm.ord.ordbill.dao.OrdBillMapper;
//@Service("crmUserService")
//@Transactional
public class UserServiceTest  {

/*	@Autowired
	public IAccountMapper accountDao;
	@Autowired
	public IProdInstMapper prodinstDao;
	@Autowired
	public offerinstMapper offerinstDao;
	@Autowired
	public OrdBillMapper  ordBillDao;
//	@Autowired
//	public ProdOfferMapper prodOfferDao;
	
	public long acct_id;

	List<Map<String,Object>> custobjList1 ;
	List<Map<String,Object>> acctobjList1 ;
	List<Map<String,Object>> taxPayerobjList1 ;
	List<Map<String,Object>> taxPayerAttrobjList1 ;
	List<Map<String,Object>> paymentPlanobjList1 ;
	List<Map<String,Object>> extAcctobjList1 ;
	List<Map<String,Object>> prodInstobjList1 ;
	List<Map<String,Object>> prodInstAttrobjList1 ;
	List<Map<String,Object>> prodInstSubobjList1 ;
	List<Map<String,Object>> prodInstRelobjList1 ;
	List<Map<String,Object>> prodInstAcctRelobjList1 ;
	List<Map<String,Object>> offerProdInstobjList1 ;
	List<Map<String,Object>> offerInstobjList1 ;
	List<Map<String,Object>> offerObjInstobjList1 ;
	List<Map<String,Object>> offerInstAttrobjList1 ;
	
	List<Map<String,Object>> prodInstRegionobjList1 ;
	List<Map<String,Object>> prodInstAccNumobjList1 ;
	List<Map<String,Object>> prodInstStateobjList1 ;
	List<Map<String,Object>> prodInstPaymodeobjList1 ;
	
	List<Map<String,Object>> prodInstGroupobjList1 ;
	
	List<Map<String,Object>> prodInstAttrSubobjList1 ;
	
	
	public void runTask1(){}
	public void runTask() throws Exception
	{
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		 Date date = new Date();
		System.out.println("----------定时执行CRMTABLE---------------"+df.format(date).toString());
		
		long id=0 ;
		String notes;
		acct_id=0;
		
		
		Message msg = new Message();
		//userMap存放大对象，发往消息
		Map userMap = new HashMap();
		
		List<Map<String,Object>> list = ordBillDao.selectOrdBill();
		if(list.size()>0)
		{
				for(int i=0;i<list.size();i++)
				{
					int flag=0;
					Map<String, List<?>> synMap = new HashMap<String, List<?>>();
					
					WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
					DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
							DefaultTransactionDefinition def = new DefaultTransactionDefinition();
							def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
							TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
					
					try{
				
						custobjList1 = new ArrayList<Map<String,Object>>();
						acctobjList1 = new ArrayList<Map<String,Object>>();
						taxPayerobjList1 = new ArrayList<Map<String,Object>>();
						taxPayerAttrobjList1= new ArrayList<Map<String,Object>>();
						paymentPlanobjList1 = new ArrayList<Map<String,Object>>();
						extAcctobjList1 = new ArrayList<Map<String,Object>>();
						prodInstobjList1= new ArrayList<Map<String,Object>>();
						prodInstAttrobjList1 = new ArrayList<Map<String,Object>>()  ;
						prodInstSubobjList1 = new ArrayList<Map<String,Object>>()  ;
						prodInstRelobjList1= new ArrayList<Map<String,Object>>();
						prodInstAcctRelobjList1 = new ArrayList<Map<String,Object>>();
						offerInstobjList1 = new ArrayList<Map<String,Object>>();
						offerObjInstobjList1= new ArrayList<Map<String,Object>>();
						offerInstAttrobjList1 = new ArrayList<Map<String,Object>>();
						offerProdInstobjList1= new ArrayList<Map<String,Object>>();
						
						prodInstRegionobjList1 = new ArrayList<Map<String,Object>> ();
						prodInstAccNumobjList1 = new ArrayList<Map<String,Object>>();
						prodInstStateobjList1 = new ArrayList<Map<String,Object>>();
						prodInstPaymodeobjList1 = new ArrayList<Map<String,Object>>();
						prodInstGroupobjList1 = new ArrayList<Map<String,Object>>();
						
						prodInstAttrSubobjList1= new ArrayList<Map<String,Object>>();
						
						
						
						synMap.put( "customer", custobjList1 );
						synMap.put( "account", acctobjList1 );
						synMap.put( "prod_inst", prodInstobjList1 );
						synMap.put( "prod_inst_attr", prodInstAttrobjList1 );
						synMap.put( "prod_inst_sub", prodInstSubobjList1 );
						synMap.put( "prod_inst_rel", prodInstRelobjList1 );
						synMap.put( "prod_inst_acct_rel", prodInstAcctRelobjList1 );
						synMap.put( "offer_inst", offerInstobjList1 );
						synMap.put( "offer_obj_inst_rel", offerObjInstobjList1 );
						synMap.put( "offer_inst_attr", offerInstAttrobjList1 );
						synMap.put( "offer_prod_inst", offerProdInstobjList1 );
						synMap.put( "prod_inst_region", prodInstRegionobjList1 );
						synMap.put( "prod_inst_acc_num", prodInstAccNumobjList1 );
						synMap.put( "prod_inst_state_ext", prodInstStateobjList1 );
						synMap.put( "prod_inst_paymode", prodInstPaymodeobjList1 );
						//synMap.put( "prod_inst_group", prodInstGroupobjList1 );
						synMap.put( "prod_inst_attr_sub", prodInstAttrSubobjList1 );
						
						Map map = (Map) list.get(i);
						id = Long.parseLong(map.get("ARCH_GRP_ID").toString());
						//1.强制提交总控制表 改为已处理
						tranManager(id, "");
						//2.处理业务逻辑
						 flag =CrmUserTable(id,msg,userMap);
						//3.发消息
						//DataSynDeal.autoBuildAndSendMsg(synMap);
						//rs_storge
						if(flag<0)
						{
							//回滚
							//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							transactionManager.rollback(status);
						}else{
							transactionManager.commit(status);
						}
	
						//3.提交处理结果
						tranManager(id, msg.getMessage());
						
					}catch(Exception e)
					{
						e.printStackTrace();
//						throw e;
						//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						transactionManager.rollback(status);
						tranManager(id, msg.getMessage());
					}
					//消息发送
					try{
						
					    if(flag>0)
					    {
						DataSynDeal.autoBuildAndSendMsg(synMap);
					    }
						
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			
		}
		

}
	
 一次性费用处理 
public void oneItemrunTask() throws Exception
{
	Message msg = new Message();
	String remarks = "处理中";
	System.out.println("----------定时执行一次性费用- oneItemResult--------------"+new Date());
	
		
		List<Map<String,Object>> oneList = ordBillDao.getOneItemResult();
		if(oneList.size()>0)
		{
			for(int i=0;i<oneList.size();i++)
			{
				
					//开启事务控制
					WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
					DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
					DefaultTransactionDefinition def = new DefaultTransactionDefinition();
					def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
					TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
					Map map = new HashMap();
					try{	
					map = oneList.get(i);
					
					OneItemtranManager(Long.parseLong(map.get("ARCH_GRP_ID").toString()),remarks);
					
					int flag  = InsertItemPlan(msg,map);
					if(flag<0)
					{
						transactionManager.rollback(status);
					}else{
						transactionManager.commit(status);
					}
					
					OneItemtranManager(Long.parseLong(map.get("ARCH_GRP_ID").toString()),msg.getMessage());
					
					
					
				}catch(Exception e)
				{
					e.printStackTrace();
					msg.setMessage("一次性费用处理失败");
					
					transactionManager.rollback(status);
					OneItemtranManager(Long.parseLong(map.get("ARCH_GRP_ID").toString()),msg.getMessage());
				}
			}
		}
	

}

public int InsertItemPlan(Message msg,Map map)throws Exception
{
	try{
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Long chargeMethod = Long.parseLong(map.get("CHARGE_METHOD").toString());
		if(chargeMethod==6) //转计费代收
		{
			long feeBillId = prodinstDao.getSequeId();
			
			Map feeBillMap = new HashMap();
			feeBillMap.put("areaCode", 111);
			feeBillMap.put("serialnumber", feeBillId);
			feeBillMap.put("feeSerial", map.get("ARCH_GRP_ID"));
			feeBillMap.put("orderNo", map.get("ARCH_GRP_ID"));
			feeBillMap.put("userId", map.get("PROD_INST_ID"));
			feeBillMap.put("acctItemType", map.get("BILL_ACCT_ITEM_TYPE_ID"));
			feeBillMap.put("payCharge", map.get("PAID_IN_AMOUNT"));
			feeBillMap.put("payTimes", 1);
			feeBillMap.put("feeDate", map.get("CREATE_DATE"));
			feeBillMap.put("effDate",  map.get("CREATE_DATE"));
			feeBillMap.put("state", 0);
			feeBillMap.put("notes", "CRM一次性费用");
		  
			ordBillDao.insertTifFeeBill(feeBillMap);
			}else{
				
				Map oneItem = new HashMap();
				long interPlanId = prodinstDao.getSequeId();
				oneItem.put("interPlanId", interPlanId) ;//zhujian
				//0418 offerInstId 查找acct_id
				long offerInstId = Long.parseLong(map.get("OFFER_INST_ID").toString());
				Map mapOfferInst = new HashMap();
				mapOfferInst.put("offerInstId", offerInstId);
				
				List<Map<String,Object>> offerlist = ordBillDao.getOneItemInstId(mapOfferInst);
				long instId = 0;
				if(offerlist.size()>0)
				{
					Map offer = offerlist.get(0);
					instId=Long.parseLong(offer.get("prodInstId").toString());
					oneItem.put("objectId",offer.get("prodInstId")) ;
					//oneItem.put("acctId", offer.get("acctId")) ;
				}else{
					msg.setMessage("未找到prod_inst_id");
					return -1;
				}
				Map servAcctMap = new HashMap();
				servAcctMap.put("prodInstId", instId);
				List<Map<String,Object>> servAcctList = ordBillDao.getOneItemAcctId(servAcctMap);
				
				if(servAcctList.size()>0)
				{
					Map acctMap = servAcctList.get(0);
					oneItem.put("objectId",acctMap.get("acctId")) ;
					//oneItem.put("acctId", offer.get("acctId")) ;
				}else{
					msg.setMessage("未找到Acct_id");
					return -1;
				}
				
				
				
				oneItem.put("offerInstId", map.get("OFFER_INST_ID")) ;
				oneItem.put("offerId", map.get("OFFER_ID")) ;
				oneItem.put("objectType", 2) ;
				//oneItem.put("objectId",map.get("PROD_INST_ID")) ;
				//oneItem.put("acctId", map.get("ACCT_ID")) ;
				oneItem.put("operType", 1) ;
				oneItem.put("operState", 0) ;
				oneItem.put("effDate", map.get("CREATE_DATE")) ;
				oneItem.put("orderDate", map.get("CREATE_DATE")) ;
				oneItem.put("createDate", df.format(date)) ;
				oneItem.put("operDate", df.format(date)) ;
				oneItem.put("amount", map.get("PAID_IN_AMOUNT")) ;
				
				ordBillDao.insertPayToPlan(oneItem);
				
			}
		msg.setMessage("处理成功");
	
	}catch(Exception e)
	{
		e.printStackTrace();
		msg.setMessage("一次性费用新增失败");
		throw e;
	}
	return 1;
}

public int CrmUserTable(Long archGrpId ,Message msg,Map userMap) throws Exception
{
	//Map userMap = new HashMap();
	String tableName;
	long orderItemId;
	long serviceOfferId;
	int action; //1 新增  2修改 3 删除
	String sOfferId;
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
	List<Map<String,Object>> ordObjlist = ordBillDao.selectOrdBillObj(archGrpId);
	for(int k=0;k<ordObjlist.size();k++)
	{
		//----------------------开始处理表数据-------------------------
		Map map = (Map) ordObjlist.get(k);
		tableName = map.get("TABLE_NAME").toString();
		orderItemId = Long.parseLong(map.get("ORDER_ITEM_ID").toString());
		serviceOfferId = Long.parseLong(map.get("SERVICE_OFFER_ID").toString());
		sOfferId=map.get("SERVICE_OFFER_ID").toString();
		
		//1.ORD_CUSTOMER 处理
		if(tableName.equals("ORD_CUSTOMER"))
		{
			Map custMap = new HashMap();
			try{
				List<Map<String,Object>> custList = ordBillDao.selectOrdCustomer(map);
				if(custList.size()>0)
				{
					custMap = custList.get(0);
					long cust_id =  Long.parseLong(custMap.get("custId").toString());
					custMap.put("routeId", cust_id);
					custMap.put("executetime", d.format(df.parse(custMap.get("updateDate").toString())));
					custMap.put("statusCd", 1100);
					//代表新增
					if(serviceOfferId==1010100000)
					{	
						long custCount = accountDao.getCustomerById(cust_id);
						if(custCount>0)
						{
							msg.setMessage("新增客户失败已经存在cust_id:"+cust_id);
							return -1;
						}
						accountDao.insertCustomer(custMap);
						
						custMap.put("action", 1);
						
					}else{
						//long custId = Long.parseLong(custMap.get("custId").toString());
						long custCount = accountDao.getCustomerById(cust_id);
						if(custCount==1)
						{	
							custMap.put("routeId", custMap.get("custId"));
							accountDao.updateCustomer(custMap);
							custMap.put("action", 2);
							
						}else{
							System.out.println("custId:"+cust_id +" 不存在 或  存在多个");
							msg.setMessage("处理客户ORG_CUSTOMER失败,不存在 或  存在多个cust_id:"+cust_id);
							
							return -1;
						}
					}
				}
				
				custobjList1.add(custMap);
			}catch(Exception e){
				msg.setFlag(statePublic.FAILFLAG);
				msg.setMessage("处理客户ORG_CUSTOMER失败");
				e.printStackTrace();
				throw e;
			}
		//2.ORD_TAX_PAYER 处理
		}else if(tableName.equals("ORD_TAX_PAYER"))
		{
			Map taxPayerMap= new HashMap();
			try{
				List<Map<String,Object>> taxPayerList = ordBillDao.selectOrdTaxPayer(map);
				
				if(taxPayerList.size()>0) //等于1 代表新增 大于1 代表修改
				{
					
					for(int i=0;i<taxPayerList.size();i++)  //operType=1000的进行赋值
					{
					    Map tempMap = taxPayerMap = taxPayerList.get(i);
					    if(Integer.parseInt(tempMap.get("operType").toString())==1000)
					    {
					    	taxPayerMap=tempMap;
					    }
					}
						if(taxPayerList.size()==1) 
						{	
							accountDao.insertTaxPayer(taxPayerMap);
							taxPayerMap.put("action",1);
						}else{   
							accountDao.updateTaxPayer(taxPayerMap);
							taxPayerMap.put("action",2);
						}
				}
				taxPayerobjList1.add(taxPayerMap);
				//明天接着干 ORD_TAX_PAYER_ATTR
				Map taxOperMap = map;
				taxOperMap.put("operType", 1000);
				List<Map<String,Object>> taxPayerAttrList = ordBillDao.selectOrdTaxPayerAttr(taxOperMap);
				if(taxPayerAttrList.size()>0)
				{	Map taxTmp = map;
					for(int i=0;i<taxPayerAttrList.size();i++)
					{
					    Map taxTmpObj = taxPayerAttrList.get(i);
					   
					    Map obj = taxTmpObj;
					    obj.put("operType",null);
					    List<Map<String,Object>> taxPayerAttrObjList = ordBillDao.selectOrdTaxPayerAttr(obj);
					    if(taxPayerAttrObjList.size()==1)
					    {
					    	accountDao.insertTaxPayerAttr(taxTmpObj);
					    	//taxTmpObj.put("action", 1);
					    }else{
					    	accountDao.updateTaxPayerAttr(taxTmpObj);
					    	
					    }
					    taxTmpObj.put("action", 1);
					    taxPayerAttrobjList1.add(taxTmpObj);
					}
				}
				
			}catch(Exception e)
			{  // msg.setMessage("处理客户ORD_TAX_PAYER失败");
				e.printStackTrace();
				throw e;
			}
		}
		//3.ORD_ACCOUNT 处理
		else if(tableName.equals("ORD_ACCOUNT"))
		{
			//3.1 ord_account
			try{
			List<Map<String,Object>> accountList = ordBillDao.selectOrdAccount(map);
			
			if(accountList.size()>0)
			{	
				Map accountMap = accountList.get(0);
				long acctId = Long.parseLong(accountMap.get("acctId").toString());
				acct_id=acctId;//全局使用acct_id
				int aCount =accountDao.getAccountById(acctId);
				accountMap.put("statusCd", 1);
				if(serviceOfferId==2010100000)
				{   
					if(aCount>0)
					{
						System.out.println("账户已经存在ACCT_ID:"+acctId);
						msg.setMessage("新增中账户已经存在ACCT_ID:"+acctId);
						return -1;
					}
					accountMap.put("routeId", acctId);
					accountDao.insertAccount(accountMap);
					
					accountMap.put("action", 1);
				}else{
					//账户是否存在判断
					if(aCount==0)
					{
						System.out.println("修改账户不存在ACCT_ID:"+acctId);
						msg.setMessage("修改中账户不存在ACCT_ID:"+acctId);
						return -1;
					}
					accountMap.put("routeId", acctId);
					accountDao.updateAccount(accountMap);
					
					accountMap.put("action", 2);
				}
				accountMap.put("executetime", d.format(df.parse(accountMap.get("updateDate").toString())));
				acctobjList1.add(accountMap);
			}
			}catch(Exception e)
			{   msg.setMessage("处理客户ORD_ACCOUNT失败");
				e.printStackTrace();
				throw e;
			}
			//3.2 ord_payment_plan  判断是否存在paymentPlan 存在则失效再新增
		   try{
			   List<Map<String,Object>> smsPaymentList = new  ArrayList<Map<String,Object>> ();
			   List<Map<String,Object>> paymentPlanList = ordBillDao.selectOrdPaymentPlan(map);
			   
			   Map paymentMap = new HashMap();
			   if(paymentPlanList.size()>0)
			   {
				   paymentMap = paymentPlanList.get(0);
				   long acctId = Long.parseLong(paymentMap.get("acctId").toString());
				   
				   List<Map<String,Object>> oldPaymentPlanList= accountDao.getPaymentPlanByID(acctId);
				   if(oldPaymentPlanList.size()>0)
				   {
					   Map oldPaymentMap=oldPaymentPlanList.get(0);
					   if(!StringUtil.isEmpty(oldPaymentMap.get("paymentPlanId")))
					   {
						   //存在则失效
						  
						   oldPaymentMap.put("statusCd", 2);
						   oldPaymentMap.put("expDate", paymentMap.get("effDate"));
						   accountDao.updatePaymentPlan(oldPaymentMap);
						   oldPaymentMap.put("executetime", d.format(df.parse(paymentMap.get("updateDate").toString())));
						   paymentPlanobjList1.add(oldPaymentMap);
					   }
				   }
				  //不存在，直接插入
				   paymentMap.put("statusCd", 1);
				   paymentMap.put("routeId", acctId);
				   accountDao.insertPaymentPlan(paymentMap);
				   paymentMap.put("executetime", d.format(df.parse(paymentMap.get("updateDate").toString())));
				   paymentPlanobjList1.add(paymentMap);
			    }
		   }catch(Exception e)
			{   msg.setMessage("处理账户ORD_PAYMENT_PLAN失败");
				e.printStackTrace();
				throw e;
			}
		  //3.3 ord_ext_acct
		   try{
			   List<Map<String,Object>> extAcctList = ordBillDao.selectOrdExtAcct(map);
			   Map extAcctMap = new HashMap();
			   if(extAcctList.size()>0)
			   {
				   extAcctMap = extAcctList.get(0);
				   int operType = Integer.parseInt(extAcctMap.get("operType").toString());
				   extAcctMap.put("executetime", d.format(df.parse(extAcctMap.get("updateDate").toString())));
				   if(operType==1000)
				   {
					   extAcctMap.put("routeId", acct_id);
					   accountDao.insertExtAcct(extAcctMap);
					   
					   extAcctMap.put("action", 1);
				   }else{
					   //Long ROUTE_ID=0L;
					   List<Map<String,Object>> extAcctList1 = accountDao.getExtAcctID(extAcctMap);
					   if(extAcctList1.size()>0)
					   {
						   Map extMap = extAcctList1.get(0);
						   extMap.put("expDate", extAcctMap.get("updateDate"));
						   accountDao.updateExtAcct(extMap); //失效时间
						   extAcctMap.put("action", 2);
					   }
					  
				   }
				   extAcctobjList1.add(extAcctMap);
			   }
			  
		   }catch(Exception e)
			{ 
			msg.setMessage("处理账户ORD_EXT_ACCT失败");
			e.printStackTrace();
			throw e;
		}
				
		}else if(tableName.equals("ORD_PROD_INST"))
		{
			try{
			
				//新装
				if(sOfferId.equals("4010100000")||sOfferId.equals("4010200000")||sOfferId.equals("4010300000"))
				{
					if(insertProdInst(map, userMap, msg)<0)
					{
						return -1;
					}
					if(insertProdInstState(map, userMap, msg,sOfferId)<0)
					{
						return -1;
					}
					if(insertProdInstAccNum(map, userMap, msg,sOfferId)<0)
					{
						return -1;
					}
				}//拆机
				else if (sOfferId.equals("4020100000")||sOfferId.equals("4020100001")||sOfferId.equals("4020200000")||sOfferId.equals("4020201001")||
						sOfferId.equals("4020300000")||sOfferId.equals("4020300001")||sOfferId.equals("4020300002")||sOfferId.equals("4020300003")||sOfferId.equals("4020400000")||	
						sOfferId.equals("4020500000")||sOfferId.equals("4020600000"))
				{
					if(deleteProdInst(map, userMap, msg)<0)
					{
						return -1;
					}
					if(updateProdInst(map, userMap, msg,sOfferId)<0)
					{
						return -1;
					}
					
				}//更新
				else{
					if(updateProdInst(map, userMap, msg,sOfferId)<0)
					{
						return -1;
					}
				}
				
				//处理用户属性prod_inst_attr
				if(doProdInstAttr(map,userMap, msg)<0)
				{
					return -1;
				}
				//取用户功能产品，需要默认生成属性
				if(doProdInstSub(map, userMap, msg)<0)
				{
					return -1;
				}
				
				//处理 prod_inst_rel关系
				if(doProdInstRel(map, userMap, msg)<0)
				{
					return -1;
				}
				
			}catch(Exception e)
			{   
			//msg.setMessage("处理账户ORD_PROD_INST失败");
			e.printStackTrace();
			throw e;
			}
		}else if(tableName.equals("ORD_PROD_INST_ACCT_REL"))
		{
		  try{
				if(doProdInstAcctRel(map, userMap, msg)<0)
				{
					return -1;
				}
			}catch(Exception e)
			{   
			//msg.setMessage("处理账户ORD_PROD_INST_ACCT_REL失败");
			e.printStackTrace();
			throw e;
			}
		}else if(tableName.equals("ORD_OFFER_INST"))
		{
			try{
		           //取acct_id
				// 判断下acct_id为空，则从offer_inst offer_inst_obj_rel offer_inst_attr 一级一级的找
				
				
				  if(acct_id==0L)
				  {
					  List<Map<String,Object>> offerInstList = ordBillDao.selectOrdOfferInst(map);
					  if(offerInstList.size()>0)
					  {
						  for(int i=0;i<offerInstList.size();i++)
						  {
							  //List<Map<String,Object>> oldOfferInstList = offerinstDao.getOfferInstId(offerInstMap);
						  }
					  }
					 
				  }
				if(acct_id==0) //获取
				{
					List<Map<String,Object>> acctIdList = ordBillDao.getOfferInstAcctId(map);
					if(acctIdList.size()>0)
					{
						Map acctMap = acctIdList.get(0);
						
						acct_id=getAcctId(map,acctMap, msg); 
						
					}
					
				}
					if(insertOfferInst(map, userMap, msg,acct_id)<0)  //销售品实例
					{
						return -1;
					}
					if(insertOfferProdInst(map, userMap, msg,acct_id)<0) //销售品角色
					{
						return -1;
					}
//					if(insertOfferObjInst(map, userMap, msg)<0)//销售品对象角色
//					{
//						return -1;
//					}
					if(insertOfferInstAtrr(map, userMap, msg,acct_id)<0)//销售品对象角色
					{
						return -1;
					}
			
			
			}catch(Exception e)
			{   
			//msg.setMessage("处理ORD_OFFER_INST失败");
			e.printStackTrace();
			throw e;
			}
		}
		
		msg.setMessage("处理成功");
	}
	return 1;
}
//销售品实例属性
public int insertOfferInstAtrr(Map itemMap,Map userMap,Message msg,long acctId) throws Exception
{
	try{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		
		List<Map<String,Object>> offerInstAttrList = ordBillDao.selectOrdOfferInstAttr(itemMap);
		if(offerInstAttrList.size()>0)
		{
			for(int i=0;i<offerInstAttrList.size();i++)
			{
				Map offerInstAttrMap = new HashMap();
				offerInstAttrMap=offerInstAttrList.get(i);
				offerInstAttrMap.put("executetime", d.format(df.parse(offerInstAttrMap.get("updateDate").toString())));
				String operType=offerInstAttrMap.get("operType").toString();
				if(operType.equals("1000"))
				{
					//判断是否存在
					List<Map<String,Object>> oldOfferInstAttrList = offerinstDao.getOfferInstAttrId(offerInstAttrMap);
					if(oldOfferInstAttrList.size()==0)
					{
						offerInstAttrMap.put("routeId", acctId);
						if(acctId==0) //可能存在对
						{
							List<Map<String,Object>> routeIdList = offerinstDao.getOfferInstId(offerInstAttrMap);
							if(routeIdList.size()>0)
							{
								Map routeMap = routeIdList.get(0);
								offerInstAttrMap.put("routeId", routeMap.get("route_id").toString());
							}
						}
					offerinstDao.insertOfferInstAttr(offerInstAttrMap);
					offerInstAttrMap.put("action", 1);
					offerInstAttrobjList1.add(offerInstAttrMap);
					}
				}else if(operType.equals("1100"))
				{
					//0414 route_id
					Long ROUTE_ID=0L;
					List<Map<String,Object>> oldOfferInstAttrList1 = offerinstDao.getOfferInstAttrId(offerInstAttrMap);
					if(oldOfferInstAttrList1.size()>0)
					{
						Map attrMap = oldOfferInstAttrList1.get(0);
						ROUTE_ID= Long.parseLong(attrMap.get("route_id").toString());
					}else{
						msg.setMessage("销售品属性修改失败未取到ROUTE_ID");
						return -1;
					}
					offerInstAttrMap.put("routeId", ROUTE_ID);
					offerinstDao.updateOfferInstAttr(offerInstAttrMap);
					offerInstAttrMap.put("action", 2);
				}
				offerInstAttrobjList1.add(offerInstAttrMap);
				
			}
		}	
		
	}catch(Exception e)
	{   
	msg.setMessage("处理账户ORD_OFFER_INST_attr失败");
	e.printStackTrace();
	throw e;
	}
	return 1;
}

//添加销售品
public int insertOfferInst(Map itemMap,Map userMap,Message msg,long acctId) throws Exception
{
	try{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
				
		Map map = itemMap;
		
		List<Map<String,Object>> offerInstList = ordBillDao.selectOrdOfferInst(map);
		if(offerInstList.size()>0)
		{
			
			
			for(int i=0;i<offerInstList.size();i++)
			{
				Map offerInstMap = new HashMap();
				offerInstMap=offerInstList.get(i);
				offerInstMap.put("executetime", d.format(df.parse(offerInstMap.get("updateDate").toString())));
				String operType=offerInstMap.get("operType").toString();
				Long route_id =0L;
				if(operType.equals("1000"))
				{
					//判断是否存在prod_inst,存在不处理
					List<Map<String,Object>> oldOfferInstList = offerinstDao.getOfferInstId(offerInstMap);
				    
					if(oldOfferInstList.size()==0)
					{
						
						offerInstMap.put("routeId", acctId);
						offerinstDao.insertOfferInst(offerInstMap);
						offerInstMap.put("action", 1);
						
						offerInstobjList1.add(offerInstMap);
						//判断一次性费用 根据offer_id判断 注释先不用 20180412
						int plancount = ordBillDao.getPOfferPayPlan(offerInstMap);
						if(plancount>0){
							
							Date date = new Date();
							
							Map oneItem = new HashMap();
							long interPlanId = prodinstDao.getSequeId();
							oneItem.put("interPlanId", interPlanId) ;
							oneItem.put("offerInstId", Long.parseLong(offerInstMap.get("offerInstId").toString())) ;
							oneItem.put("offerId", Long.parseLong(offerInstMap.get("offerId").toString())) ;
							oneItem.put("objectType", 1) ;
							oneItem.put("objectId", acct_id) ;
							oneItem.put("operType", 1) ;
							oneItem.put("operState", 0) ;
							oneItem.put("effDate", offerInstMap.get("effDate")) ;
							oneItem.put("orderDate", offerInstMap.get("effDate")) ;
							oneItem.put("createDate", df.format(date)) ;
							oneItem.put("operDate", df.format(date)) ;
							
							ordBillDao.insertPayToPlan(oneItem);
						}
					}else{
						 
					}
					
				}else if(operType.equals("1100"))
				{
					List<Map<String,Object>> oldOfferInstList1 = offerinstDao.getOfferInstId(offerInstMap);
					if(oldOfferInstList1.size()>0)
					{
						Map oldOfferMap =oldOfferInstList1.get(0);
						route_id=Long.parseLong(oldOfferMap.get("route_id").toString());
						offerInstMap.put("routeId", route_id);
					}else{
						msg.setMessage("库中未找到销售品实例");
						return -1 ;
					}
					
					if(route_id!=0||route_id!=null)
					{
						offerinstDao.updateOfferInst(offerInstMap);
						offerInstMap.put("action", 2);
						offerInstobjList1.add(offerInstMap);
						
					}else{
						msg.setMessage("未找到销售品实例route_id");
						return -1 ;
					}
					
				}else if(operType.equals("1300"))
				{
					//直接成员角色加入 场景 主副卡 等
					if(insertOfferObjInst(itemMap, userMap, msg,acctId)<0)
					{
						return -1;
					}
				}
				
			}
		}	
		
	}catch(Exception e)
	{   
	msg.setMessage("处理账户ORD_OFFER_INST失败");
	e.printStackTrace();
	throw e;
	}
	return 1;
}
//销售品角色
public int insertOfferProdInst(Map itemMap,Map userMap,Message msg,long acctId) throws Exception
{
	try {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		Map map = itemMap;
		List<Map<String,Object>> offerProdInstList = ordBillDao.selectOrdOfferProdInstRel(itemMap);
		if(offerProdInstList.size()>0)
		{
			for(int i=0;i<offerProdInstList.size();i++)
			{
				Map offerProdInstMap = new HashMap();
				offerProdInstMap = offerProdInstList.get(i);
				offerProdInstMap.put("executetime", d.format(df.parse(offerProdInstMap.get("updateDate").toString())));
				String operType=offerProdInstMap.get("operType").toString();
				if(operType.equals("1000"))
				{
					//取历史旧的，做判断是否存在
					offerProdInstMap.put("offerObjInstRelId", offerProdInstMap.get("offerProdInstRelId"));//主键
					List<Map<String,Object>> oldOfferProdInstList =offerinstDao.getOfferObjInstId(offerProdInstMap);
					if(oldOfferProdInstList.size()==0)
					{
					offerProdInstMap.put("action", 1);
					offerProdInstMap.put("objType", 100000);
					offerProdInstMap.put("objId", Long.parseLong(offerProdInstMap.get("prodInstId").toString()));
					//取销售品角色id
					map.put("offerInstId", Long.parseLong(offerProdInstMap.get("offerInstId").toString()));
					
					long offerDetailId = statePublic.getOFferObjRelIdForNine(offerProdInstMap.get("offerProdRelId").toString());
					
					if(offerDetailId==-1)
					{
						List<Map<String,Object>> offerIdList = ordBillDao.getOrdOfferInstOfferId(map);
						Map offerIdMap = new HashMap();
						if(offerIdList.size()>0)//取offerId
						{
							offerIdMap = offerIdList.get(0); 
							offerProdInstMap.put("offerId",offerIdMap.get("offerId").toString());
						}else{
							msg.setMessage("销售品实例角色中未取到offer_id,offer_inst_id:"+Long.parseLong(offerProdInstMap.get("offerInstId").toString()));
							return -1;
						}
						
						offerDetailId = getOfferDetailId(offerProdInstMap,msg);
						if(offerDetailId<0)
						{
							msg.setMessage("取销售品成员角色失败offer_id:"+offerIdMap.get("offerId").toString());
							return -1;
						}
					}
					
					//修改getOrdOfferInstOfferId  sql 去掉operType=1000的过滤20180410
					//直接取crm的offer_detail_id 
					List<Map<String,Object>> offerIdList = ordBillDao.getOrdOfferInstOfferId(map);
					Map offerIdMap = new HashMap();
					if(offerIdList.size()>0)//取offerId
					{
						offerIdMap = offerIdList.get(0); 
						offerProdInstMap.put("offerId",offerIdMap.get("offerId").toString());
					}else{
						msg.setMessage("销售品实例角色中未取到offer_id,offer_inst_id:"+Long.parseLong(offerProdInstMap.get("offerInstId").toString()));
						return -1;
					}
					
					long offerDetailId = getOfferDetailId(offerProdInstMap,msg);
					if(offerDetailId<0)
					{
						msg.setMessage("取销售品成员角色失败offer_id:"+offerIdMap.get("offerId").toString());
						return -1;
					}
					
					//取acct_id  
					if(acctId==0)
					{   
						Map acctMap = new HashMap() ;
					    acctMap.put("prodInstId", Long.parseLong(offerProdInstMap.get("prodInstId").toString()));
					    acctId =  getAcctId( itemMap,acctMap, msg) ;
					}
					//销售品成员角色直接用20180413
					//long offerDetailId = statePublic.getOFferObjRelIdForNine(offerProdInstMap.get("offerProdRelId").toString());
					offerProdInstMap.put("routeId", acctId);
					offerProdInstMap.put("offerObjRelId",offerDetailId );
					offerProdInstMap.put("lastOrderItemId", offerDetailId);
					offerProdInstMap.put("offerObjInstRelId", offerProdInstMap.get("offerProdInstRelId"));//主键
					offerinstDao.insertOfferObjInstRel(offerProdInstMap);

					offerObjInstobjList1.add(offerProdInstMap);
					}
				}else if (operType.equals("1100"))
				{
					//取历史旧的route_id 0414
					Long ROUTE_ID=0L;
					offerProdInstMap.put("offerObjInstRelId", offerProdInstMap.get("offerProdInstRelId"));
					List<Map<String,Object>> oldOfferProdInstList1 =offerinstDao.getOfferObjInstId(offerProdInstMap);
					if(oldOfferProdInstList1.size()>0)
					{
					   Map olddetailMap = oldOfferProdInstList1.get(0);
					   ROUTE_ID = Long.parseLong(olddetailMap.get("ROUTE_ID").toString());
					   
					}else{
						msg.setMessage("销售品成员角色中未找到route_id");
						return -1;
					}
					offerProdInstMap.put("routeId", ROUTE_ID);
					offerProdInstMap.put("offerObjInstRelId", offerProdInstMap.get("offerProdInstRelId"));//主键
					offerinstDao.updateOfferObjInstRel(offerProdInstMap);
					offerProdInstMap.put("action", 2);
					offerProdInstMap.put("objType", 100000);
					offerProdInstMap.put("objId", Long.parseLong(offerProdInstMap.get("prodInstId").toString()));
					offerObjInstobjList1.add(offerProdInstMap);
				}
			}
		}
		
	}catch(Exception e)
	{   
	msg.setMessage("处理账户ORD_OFFER_PROD_INST_失败");
	e.printStackTrace();
	throw e;
	}
	return 1;
}
//销售品对象角色
public int insertOfferObjInst(Map itemMap,Map userMap,Message msg,long acctId) throws Exception
{
	try {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		Map map = itemMap;
		List<Map<String,Object>> offerObjInstList = ordBillDao.selectOrdOfferObjInstRel(itemMap);
		if(offerObjInstList.size()>0)
		{
			for(int i=0;i<offerObjInstList.size();i++)
			{
				Map offerProdInstMap = offerObjInstList.get(i);
				offerProdInstMap.put("executetime", d.format(df.parse(offerProdInstMap.get("updateDate").toString())));
				String operType=offerProdInstMap.get("operType").toString();
				if(operType.equals("1000"))
				{
					//取历史旧的，做判断是否存在
					List<Map<String,Object>> oldOfferProdInstList =offerinstDao.getOfferObjInstId(offerProdInstMap);
					if(oldOfferProdInstList.size()==0)
					{
					offerProdInstMap.put("action", 1);
					
					//取销售品角色id 可能不需要取 需确认
					//long offerDetailId = statePublic.getOFferObjRelIdForNine(offerProdInstMap.get("offerObjRelId").toString());
					map.put("offerInstId", Long.parseLong(offerProdInstMap.get("offerInstId").toString()));
					
					List<Map<String,Object>> offerIdList = ordBillDao.getOrdOfferInstOfferId(map);
					if(offerIdList.size()>0)//取offerId
					{
						Map offerIdMap = offerIdList.get(0); 
						offerProdInstMap.put("offerId",offerIdMap.get("offerId").toString());
					}
					 offerDetailId = getOfferDetailId(offerProdInstMap,msg);
					if(offerDetailId<0)
					{
						msg.setMessage("销售品对象成员角色取值出错");
						return -1;
					}
					offerProdInstMap.put("offerObjRelId", offerDetailId);
					
					
					Map routeMap = new HashMap();
					
					offerProdInstMap.put("routeId", acctId);
					long offerDetailId = statePublic.getOFferObjRelIdForNine(offerProdInstMap.get("offerObjRelId").toString());
					offerProdInstMap.put("offerObjRelId", offerDetailId) ;
					offerProdInstMap.put("lastOrderItemId", "1") ;
					offerinstDao.insertOfferObjInstRel(offerProdInstMap);

					offerObjInstobjList1.add(offerProdInstMap);
					}
				}else if (operType.equals("1100"))
				{
					//add by 0414 取route_id 
					Long ROUTE_ID=0L;
					List<Map<String,Object>> oldOfferProdInstList1 =offerinstDao.getOfferObjInstId(offerProdInstMap);
					if(oldOfferProdInstList1.size()>0)
					{
						Map oldObjMap = oldOfferProdInstList1.get(0);
						ROUTE_ID=Long.parseLong(oldObjMap.get("ROUTE_ID").toString());
						
					}else{
						msg.setMessage("修改销售品角色ojb未找到route_id");
						return -1;
					}
					offerProdInstMap.put("routeId", ROUTE_ID);
					offerinstDao.updateOfferObjInstRel(offerProdInstMap);
					offerProdInstMap.put("action", 2);
					offerObjInstobjList1.add(offerProdInstMap);
				}
			}
		}
//		if(offerObjInstList.size()>0)
//		{
//			for(int i=0;i<offerObjInstList.size();i++)
//			{
//				Map offerObjInstMap = offerObjInstList.get(i);
//				String operType=offerObjInstMap.get("operType").toString();
//				if(operType.equals("1000"))
//				{
//					offerinstDao.insertOfferObjInstRel(offerObjInstMap);
//					offerObjInstMap.put("action", 1);
//					offerObjInstobjList1.add(offerObjInstMap);
//				}
//			}
//		}
	}catch(Exception e)
	{   
	msg.setMessage("处理账户ORD_OFFER_PROD_INST_失败");
	e.printStackTrace();
	throw e;
	}
	return 1;
}
public int doProdInstAcctRel(Map itemMap,Map userMap,Message msg) throws Exception
{
	try{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		Map acctRelMap = itemMap;
		
		List<Map<String,Object>> prodInstAcctRelList = ordBillDao.selectOrdProdInstAcctRel(acctRelMap);
		if(prodInstAcctRelList.size()>0)
		{
			for(int i=0;i<prodInstAcctRelList.size();i++)
			{
				Map servAcctMap = new HashMap();
				servAcctMap=prodInstAcctRelList.get(i);
				servAcctMap.put("executetime", d.format(df.parse(servAcctMap.get("updateDate").toString())));
				long operType = Long.parseLong(servAcctMap.get("operType").toString());
				if(operType==1000)//直接插入账务关系
				{   
					long prodInstAcctRelId = prodinstDao.seqProdInstAcctRelId();
					servAcctMap.put("prodInstAcctRelId", prodInstAcctRelId);
					servAcctMap.put("action", 1);
					servAcctMap.put("statusCd", 1);
					servAcctMap.put("routeId", servAcctMap.get("acctId"));
					prodinstDao.insertProdInstAcctRel(servAcctMap);
				}else if(operType==1100)//修改
				{
					List<Map<String,Object>> oldProdInstAcctRelList = prodinstDao.getProdInstAcctRelId(servAcctMap);
					if(oldProdInstAcctRelList.size()>0)
					{
						Map oldAcctMap = oldProdInstAcctRelList.get(0);
						servAcctMap.put("action", 2);
						servAcctMap.put("statusCd", 2);
						servAcctMap.put("expDate", servAcctMap.get("statusDate"));
						servAcctMap.put("prodInstAcctRelId", oldAcctMap.get("prodInstAcctRelId"));
						prodinstDao.updateProdInstAcctRel(servAcctMap);
					}else{
						msg.setMessage("未找到要修改的账务关系serc_acct_id:"+servAcctMap.get("prodInstAcctRelId").toString());
						return -1;
					}
				}
				//修改不按crm主键进行判断20180413
				List<Map<String,Object>> oldProdInstAcctRelList = prodinstDao.getProdInstAcctRelId(servAcctMap);
				if(oldProdInstAcctRelList.size()>0)//存在则修改
				{
					//servAcctMap.put("statusCd", 2);
					servAcctMap.put("action", 2);
					servAcctMap.put("expDate", servAcctMap.get("statusDate"));
					prodinstDao.updateProdInstAcctRel(servAcctMap);
				}else{
					servAcctMap.put("action", 1);
					servAcctMap.put("routeId", servAcctMap.get("acctId"));
					prodinstDao.insertProdInstAcctRel(servAcctMap);
				}
				prodInstAcctRelobjList1.add(servAcctMap);
			}
		}
		
		
	}catch(Exception e)
	{   
	msg.setMessage("处理账户ORD_PROD_INST_REL失败");
	e.printStackTrace();
	throw e;
	}
	return 1;
}

public int doProdInstRel(Map itemMap,Map userMap,Message msg) throws Exception
{
	try{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		
		
	List<Map<String,Object>> prodInstRelList = ordBillDao.selectOrdProdInstRel(itemMap);
	//long acctId =0;
		if(prodInstRelList.size()>0)
		{
			for(int i=0;i<prodInstRelList.size();i++)
			{
				Map  instRelMap = prodInstRelList.get(i);
				instRelMap.put("executetime", d.format(df.parse(instRelMap.get("updateDate").toString())));
				String operType = instRelMap.get("operType").toString();
				String stateDate =  instRelMap.get("statusDate").toString();
				if(acct_id==0)
				{
					instRelMap.put("prodInstId", instRelMap.get("aProdInstId").toString());//注意需查看取那个是产品实例id
					acct_id = getAcctId(itemMap,instRelMap,msg);
				}
				if(operType.equals("1000"))//先判断，是否存在，不存在旧新增
				{
					List<Map<String,Object>> oldProdInstRelList = prodinstDao.getProdInstRelId(instRelMap);
					if(oldProdInstRelList.size()>0)
					{
						Map relMap= oldProdInstRelList.get(0);
						instRelMap.put("action", 2);
						instRelMap.put("routeId", relMap.get("routeId"));
						prodinstDao.updateProdInstRel(instRelMap);
						//prodinstDao.updateProdInstSub(instRelMap);
					}else{
						instRelMap.put("routeId", acct_id);
						instRelMap.put("action", 1);
						
						prodinstDao.insertProdInstRel(instRelMap);
					}
					prodInstRelobjList1.add(instRelMap);
				}
				
			}
			
		}
	
	}catch(Exception e)
	{   
	msg.setMessage("处理账户ORD_PROD_INST_REL失败");
	e.printStackTrace();
	throw e;
	}
	return 1;
}
public int doProdInstAttr(Map itemMap,Map userMap,Message msg) throws Exception
{
	try{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String DateStr = d.format(date);
	List<Map<String,Object>> prodInstAttrList = ordBillDao.selectOrdProdInstAttr(itemMap);
	//long acctId =0;
		if(prodInstAttrList.size()>0)
		{
			for(int i=0;i<prodInstAttrList.size();i++)
			{
				Map  instAttrMap = prodInstAttrList.get(i);
				instAttrMap.put("executetime", d.format(df.parse(instAttrMap.get("updateDate").toString())));
				//instAttrMap.put("updateDate", instAttrMap.get("createDate"));
				String operType = instAttrMap.get("operType").toString();
				
				//String stateDate =  instAttrMap.get("statusDate").toString();
				if(acct_id==0)
				{
					acct_id = getAcctId(itemMap,instAttrMap,msg);
				}
				if(operType.equals("1000"))//先判断，是否存在，不存在旧新增
				{
					List<Map<String,Object>> oldProdAttrList = prodinstDao.getProdInstAttrId(instAttrMap);
					if(oldProdAttrList.size()>0)
					{
						Map attrMap= oldProdAttrList.get(0);
						
						instAttrMap.put("expDate", instAttrMap.get("statusDate"));
						instAttrMap.put("action", 2);
						instAttrMap.put("routeId",attrMap.get("routeId"));
						prodinstDao.updateProdInstAttr(instAttrMap);
					}else{
						instAttrMap.put("routeId", acct_id);
						instAttrMap.put("action", 1);
						instAttrMap.put("effDate", instAttrMap.get("statusDate"));
						instAttrMap.put("expDate", statePublic.expDate);
						prodinstDao.insertProdInstAttr(instAttrMap);
					}
					prodInstAttrobjList1.add(instAttrMap);
				}
				
			}
			
		}
	
	}catch(Exception e)
	{   
	msg.setMessage("处理账户ORD_PROD_INST_attr失败");
	e.printStackTrace();
	throw e;
	}
	return 1;
}

public int doProdInstSub(Map itemMap,Map userMap,Message msg) throws Exception
{
	try{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		 
		
	List<Map<String,Object>> prodInstSubList = ordBillDao.selectOrdProdInstSub(itemMap);
	//long acctId =0;
		if(prodInstSubList.size()>0)
		{
			for(int i=0;i<prodInstSubList.size();i++)
			{
				HashMap  instSubMap = (HashMap)prodInstSubList.get(i);
				
				HashMap  tmpMap = new HashMap();
				tmpMap=	(HashMap) instSubMap.clone();
				
				tmpMap.put("prodInstId", instSubMap.get("accProdInstId"));
				instSubMap.put("executetime", d.format(df.parse(instSubMap.get("updateDate").toString())));
				String operType = instSubMap.get("operType").toString();
				if(acct_id==0)
				{
					acct_id = getAcctId(itemMap,tmpMap,msg);
				}
				if(operType.equals("1000"))//新增
				{
					List<Map<String,Object>> oldProdInstSubList = prodinstDao.getProdInstSubId(instSubMap);
					if(oldProdInstSubList.size()>0)
					{ 
						Map msubMap = oldProdInstSubList.get(0);
						instSubMap.put("action", 2);
						instSubMap.put("routeId", msubMap.get("routeId"));
						prodinstDao.updateProdInstSub(instSubMap);
					}else{
					//获取序列
						String prod_id_str= instSubMap.get("prodId").toString();
						if(prod_id_str.length()>9)
						{
							instSubMap.put("prodId", 20020022);
						}
						instSubMap.put("action", 1);
						instSubMap.put("routeId", acct_id);
						
						prodinstDao.insertProdInstSub(instSubMap);
					}
					prodInstSubobjList1.add(instSubMap);
				}
				
			}
			
		}
	
	}catch(Exception e)
	{   
	msg.setMessage("处理账户ORD_PROD_INST_SUB失败");
	e.printStackTrace();
	throw e;
	}
	return 1;
}
public int deleteProdInst(Map itemMap,Map userMap,Message msg) throws Exception
{
	try{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Map deleteMap = itemMap ;
		List<Map<String,Object>> prodInstList = ordBillDao.selectOrdProdInst(deleteMap);
		Map prodInstMap = new HashMap();
		
		
		if(prodInstList.size()>0)
		{
		prodInstMap = prodInstList.get(0);	
		long acctId = getAcctId(itemMap,prodInstMap,msg);
		prodInstMap.put("action", 2);
		prodInstMap.put("routeId",acctId);
		String stateDate = prodInstMap.get("statusDate").toString();
		prodInstMap.put("executetime", d.format(df.parse(prodInstMap.get("updateDate").toString())));
		prodinstDao.updateProdInst(prodInstMap);
		prodInstobjList1.add(prodInstMap);
		//没空处理失效的prod_inst_region 、退出群组
		deleteMap.put("prodInstId", Long.parseLong(prodInstMap.get("prodInstId").toString()));
		List<Map<String,Object>> prodInst1100List = ordBillDao.selectOrdProdInstState1100(deleteMap);
			if(prodInst1100List.size()>0)
			{
				Map oldStateMap = prodInst1100List.get(0);
				oldStateMap.put("statusCd", 1100);
				oldStateMap.put("expDate", stateDate);
				oldStateMap.put("action", 2);
				oldStateMap.put("executetime", d.format(df.parse(prodInstMap.get("updateDate").toString())));
				prodinstDao.updateProdInstState(oldStateMap);
				prodinstDao.updateProdInstStateExt(oldStateMap);
				prodInstStateobjList1.add(oldStateMap);
			}
			List<Map<String,Object>> prodInstNewList = ordBillDao.selectOrdProdInstState(deleteMap);
			if(prodInstNewList.size()>0)
			{
				Map newStateMap = prodInstNewList.get(0);
				//newStateMap.是否需要更改eff_date
				newStateMap.put("effDate", stateDate);
				newStateMap.put("action", 1);
				newStateMap.put("routeId", acctId);
				prodinstDao.insertProdInstState(newStateMap);
				prodinstDao.insertProdInstStateExt(newStateMap);
				prodInstStateobjList1.add(newStateMap);
			}
		}
		//prod_inst_group 退出
		prodinstDao.updateProdInstGroup(prodInstMap);
		
		}catch(Exception e)
		{   
			msg.setMessage("处理ORD_PROD_INST更新失败");
			e.printStackTrace();
			throw e;
		}
	
	return 1;
}

public int updateProdInst(Map itemMap,Map userMap,Message msg,String service_offer_id) throws Exception
{
	try{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		
		List<Map<String,Object>> prodInstList = ordBillDao.selectOrdProdInst(itemMap);
		String oldPaymode="";
		String oldAccNum="";
		String oldState="";
		String oldRegionId="";
		
		String paymode="";
		String accNum="";
		String state="";
		String RegionId="";
		long prodInstId=0L;
		String oldAcctId="";
		
		String stateDate="";
		
		Map prodInstMap = new HashMap();
		if(prodInstList.size()>0)
		{
		prodInstMap = prodInstList.get(0);	
		prodInstMap.put("effDate", prodInstMap.get("statusDate"));
		stateDate=prodInstMap.get("statusDate").toString();//用来同一更新时间
		prodInstMap.put("expDate", statePublic.expDate);
		prodInstMap.put("executetime", d.format(df.parse(prodInstMap.get("updateDate").toString())));
		
		prodInstId = Long.parseLong(prodInstMap.get("prodInstId").toString());
		paymode= prodInstMap.get("paymentModeCd").toString();
		accNum= prodInstMap.get("accNum").toString();
		state= prodInstMap.get("statusCd").toString();
		RegionId = prodInstMap.get("regionId").toString();
		
		//long acctId = getAcctId(itemMap,prodInstMap,msg);
		
		//查找旧的 prod_inst  getProdInstOBJ
		List<Map<String,Object>> oldProdInstList = prodinstDao.getProdInstOBJ(prodInstId);
		if(oldProdInstList.size()>0)
		{
			Map oldProdInst = oldProdInstList.get(0);
			oldPaymode= oldProdInst.get("PAYMENT_MODE_CD").toString();
			oldAccNum= oldProdInst.get("ACC_NUM").toString();
			oldState= oldProdInst.get("STATUS_CD").toString();
			oldRegionId = oldProdInst.get("REGION_ID").toString();
			oldAcctId = oldProdInst.get("ROUTE_ID").toString();
			
			
		}else{
			msg.setMessage("处理ORD_PROD_INST更新失败,未找到用户prodInstId:"+prodInstId);
			return -1;
		}
		prodInstMap.put("routeId", oldAcctId);
		prodInstMap.put("action", 2);
		prodinstDao.updateProdInst(prodInstMap);
		prodInstobjList1.add(prodInstMap);
		//没空处理失效的prod_inst_region 、退出群组
		}
		//prod_inst_region  区域不想等
		if(!RegionId.equals(oldRegionId))
		{
			//查找region 更新
			Map mapRegion = new HashMap();
			mapRegion.put("regionId", oldRegionId);
			mapRegion.put("prodInstId", prodInstId);
			List<Map<String,Object>> oldRegionObj =	prodinstDao.getProdInstRegion(mapRegion);
			
			if(oldRegionObj.size()>0)
			{
				Map oldRegion = oldRegionObj.get(0);
				oldRegion.put("expDate", state);
				oldRegion.put("statusCd", 1100);
				oldRegion.put("action", 2);
				oldRegion.put("executetime", d.format(df.parse(prodInstMap.get("updateDate").toString())));
				//oldRegion.put("routeId", oldAcctId);
				prodinstDao.updateProdInstRegion(oldRegion);
				prodInstRegionobjList1.add(oldRegion);
				
				long prodInstRegionId = prodinstDao.getSequeId();
				prodInstMap.put("prodInstRegionId", prodInstRegionId); //////--------------------------需要修改读取序列
				prodInstMap.put("routeId", oldAcctId);
				prodInstMap.put("action", 1);
				prodinstDao.insertProdInstRegion(prodInstMap);
				prodInstRegionobjList1.add(prodInstMap);
			
			}else{
				msg.setMessage("更新ord_prod_region失败，未找到prodInstId:"+prodInstId);
			   return -1;
			}
		}
		//号码不想等
		if(!oldAccNum.equals("")&&!accNum.equals("")&&!accNum.equals(oldAccNum))
		{
			//查找旧号码
			if(updateProdInstAccNum(itemMap, userMap, msg, oldAccNum, accNum, prodInstId , stateDate,oldAcctId,prodInstMap)<0)
			{
				return -1;
			}
		}
		//状态不相等
		if(!oldState.equals("")&&!state.equals("")&&!state.equals(oldState))
		{
			if(updateProdInstState(itemMap, userMap, msg, prodInstId , stateDate,oldAcctId,prodInstMap)<0)
			{
				return -1;
			}
		}
		
		//付费模式不想等
		if(!paymode.equals(oldPaymode))
		{
			Map mPaymode = prodInstMap;
			mPaymode.put("prodInstId", prodInstId);
			List<Map<String,Object>> oldPaymodeList = prodinstDao.getProdInstPaymodeId(mPaymode);
			if(oldPaymodeList.size()>0)
			{
				Map oldPaymode1 = oldPaymodeList.get(0);
				oldPaymode1.put("paymodeId", oldPaymodeList.get(0).get("paymodeId").toString());
				oldPaymode1.put("expDate",prodInstMap.get("statusDate") );
				oldPaymode1.put("statusCd", 1100);
				oldPaymode1.put("action", 2);
				oldPaymode1.put("prodInstId", prodInstId);
				
				prodinstDao.updateProdInstPaymode(oldPaymode1);
				prodInstPaymodeobjList1.add(oldPaymode1);
			}
			//新增
			mPaymode.put("executetime", d.format(df.parse(prodInstMap.get("updateDate").toString())));
			mPaymode.put("routeId", oldAcctId);
			mPaymode.put("effDate", prodInstMap.get("statusDate"));
			mPaymode.put("statusCd", 1000);
			mPaymode.put("expDate", statePublic.expDate);
			mPaymode.put("paymentModeCd", paymode);
			long payModeId = prodinstDao.getSequeId();
			mPaymode.put("paymodeId", payModeId);  //序列id
			mPaymode.put("action", 1);
			
			prodinstDao.insertProdInstPyamode(mPaymode);
			prodInstPaymodeobjList1.add(mPaymode);
			
		}
		}catch(Exception e)
		{   
			msg.setMessage("处理ORD_PROD_INST更新失败");
			e.printStackTrace();
			throw e;
		}
	
	return 1;
}

public long getOfferDetailId(Map map,Message msg) throws Exception
{
   try{
	//List<Map<String,Object>> offerDetailList = prodOfferDao.getOfferRoleId(map);
	List<Map<String,Object>> offerDetailList = offerinstDao.getOfferRoleId(map);
	if(offerDetailList.size()>0)
	{
		return Long.parseLong(offerDetailList.get(0).get("OFFER_OBJ_REL_ID").toString());
	}else{
		msg.setMessage("获取销售品成员角色异常");
//		throw new Exception();
		return -1;
	}
   }catch(Exception e)
	{   
		msg.setMessage("获取销售品成员角色异常");
		e.printStackTrace();
		throw e;
	}
	
	//return 1;
}

public int updateProdInstState(Map itemMap,Map userMap,Message msg,long prodInstId ,String stateDate,String routeId,Map stateMap) throws Exception
{
	
	try{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
	//查找ORD最新数据insert 
	
	Map mState = itemMap;
	mState.put("prodInstId", prodInstId);
	List<Map<String,Object>> newAccNumList = ordBillDao.selectOrdProdInstState(mState);
	Map newStateObj = new HashMap();
	if(newAccNumList.size()>0)
	{
	  newStateObj = newAccNumList.get(0);
	  newStateObj.put("routeId", routeId);
	  newStateObj.put("effDate", stateDate);
	  newStateObj.put("expDate", statePublic.EXPDATE);
	  newStateObj.put("action",1);
	  prodinstDao.insertProdInstState(newStateObj);
	  prodinstDao.insertProdInstStateExt(newStateObj);
	  prodInstStateobjList1.add(newStateObj);
	  
	}else{
		msg.setMessage("更新ProdInstState失败,未找到ord_prod_inst_state:"+prodInstId);
		return -1;
	}
	//查找旧的状态
	Map mapstate = new HashMap();
	mapstate.put("prodInstId", prodInstId);
	List<Map<String,Object>> oldStateList = prodinstDao.getProdInstStateId(mapstate);
	if(oldStateList.size()>0)
	{
		//失效旧数据
		Map oldStateMap = oldStateList.get(0);
		oldStateMap.put("expDate", stateMap.get("statusDate"));
		oldStateMap.put("action", 2);
		oldStateMap.put("statusCd", 1100);
		oldStateMap.put("executetime", d.format(df.parse(stateMap.get("updateDate").toString())));
		oldStateMap.put("prodInstId", prodInstId);
		
		prodinstDao.updateProdInstState(oldStateMap);
		prodinstDao.updateProdInstStateExt(oldStateMap);
		
		prodInstStateobjList1.add(oldStateMap);
	}
	//新增
	Map stateObj = stateMap;
	long prodInstStateId = prodinstDao.getSequeId();
	stateObj.put("prodInstStateId", prodInstStateId);
	stateObj.put("routeId", routeId);
	stateObj.put("stopType", 0);
	stateObj.put("state",stateObj.get("statusCd"));
	stateObj.put("statusCd", 100000);
	stateObj.put("executetime", d.format(df.parse(stateObj.get("updateDate").toString())));
	
	prodinstDao.insertProdInstState(stateObj);
	prodinstDao.insertProdInstStateExt(stateObj);
	prodInstStateobjList1.add(stateObj);
	
	}catch(Exception e)
	{   
		msg.setMessage("处理PROD_INST_STATE更新失败");
		e.printStackTrace();
		throw e;
	}
	return 1;
}
public int updateProdInstAccNum(Map itemMap,Map userMap,Message msg,String oldAccNum,String accNum,long prodInstId ,String stateDate1,String routeId,Map accNumMap) throws Exception
{
	try{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		
			//查找accNum
			Map mapAccNum = new HashMap();
			mapAccNum.put("accNum", oldAccNum);
			mapAccNum.put("prodInstId", prodInstId);
			//旧数据
			List<Map<String,Object>> oldAccNumObj = prodinstDao.getProdInstAccNumId(mapAccNum);
			if(oldAccNumObj.size()>0)
			{  
				Map oldAccNumMap = oldAccNumObj.get(0);
				oldAccNumMap.put("expDate", accNumMap.get("statusDate"));
				oldAccNumMap.put("statusCd", 1100);
				oldAccNumMap.put("action", 2);
				oldAccNumMap.put("executetime", d.format(df.parse(accNumMap.get("updateDate").toString())));
				
				prodinstDao.updateProdInstAccNum(oldAccNumMap);
				prodInstAccNumobjList1.add(oldAccNumMap);
				//取新的数据stateDate  accNumMap
				Map accNumObj = accNumMap;
				long prodInstAccNumId = prodinstDao.getSequeId();
				accNumObj.put("action", 1);
				accNumObj.put("prodInstAccNumId", prodInstAccNumId);
				accNumObj.put("routeId", routeId);
				accNumObj.put("platId", 1);
				accNumObj.put("accNumType", 1000);
				accNumObj.put("applyRegionId", 1);
				accNumObj.put("statusCd", 1000);
				prodinstDao.insertProdInstAccNum(accNumObj);
				prodInstAccNumobjList1.add(accNumObj);
				Map mAccNum = itemMap;
				mAccNum.put("accNum", accNum);
				List<Map<String,Object>> newAccNumList = ordBillDao.selectOrdProdInstAccNum(mAccNum);
				if(newAccNumList.size()>0)
				{
					Map mapAccNumObj = newAccNumList.get(0);
					mapAccNumObj.put("action", 1);
					mapAccNumObj.put("routeId", routeId);
					mapAccNumObj.put("effDate", stateDate);
					mapAccNumObj.put("expDate", statePublic.EXPDATE);
					prodinstDao.insertProdInstAccNum(mapAccNumObj);
					prodInstAccNumobjList1.add(mapAccNumObj);
					
				}
				
			}else{
				msg.setMessage("更新ord_prod_inst_acc_num失败，未找到prodInstId:"+prodInstId);
				   return -1;
			}
	
	
	}catch(Exception e)
	{   
		msg.setMessage("更新ord_prod_inst_acc_num失败prodInstId:"+prodInstId);
		e.printStackTrace();
		throw e;
	}
	
	
	return 1;
}
public int insertProdInst(Map itemMap,Map userMap,Message msg ) throws Exception
{
	List<Map<String,Object>> prodInstList = ordBillDao.selectOrdProdInst(itemMap);
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
	//prodInstMap存放prod_inst对象
	Map prodInstMap = new HashMap();
	if(prodInstList.size()>0)
	{
			prodInstMap = prodInstList.get(0);
			if(acct_id ==0)
			{
				long acctId = getAcctId(itemMap,prodInstMap,msg);
				acct_id=acctId;
				if(acct_id==0)
				{
					msg.setMessage("新增prod_inst获取acct_id失败");
					return -1;
				}
			}
			//1新装
				try{	
					prodInstMap.put("action", 1);
					prodInstMap.put("effDate", prodInstMap.get("statusDate"));
					prodInstMap.put("expDate",statePublic.expDate);
					prodInstMap.put("routeId", acct_id);
					prodInstMap.put("executetime", d.format(df.parse(prodInstMap.get("updateDate").toString())));
					prodInstMap.put("statusCd", 100000);
					prodinstDao.insertProdInst(prodInstMap);
					prodInstobjList1.add(prodInstMap);
					long prodId = Long.parseLong(prodInstMap.get("prodId").toString());
					//1.判断产品是否为组合产品 ---------------需要特殊处理 prodOfferDao
					List<Map<String,Object>> productTypeList = prodinstDao.getProductType(prodInstMap);
//					List<Map<String,Object>> productTypeList = prodOfferDao.getProductType(prodInstMap);
					if(productTypeList.size()>0)
					{
						Map typeMap = productTypeList.get(0);
						String productType = typeMap.get("PROD_COMP_TYPE").toString();
						if(productType.equals("13"))
						{
							prodinstDao.insertProdInstGroup(prodInstMap);
							prodInstGroupobjList1.add(prodInstMap);
						}
					}else{
						先注释掉  20180410
						 * msg.setMessage("PROD_INST处理取prod类型失败");
						return -1;
					}
				}catch(Exception e)
				{   
					msg.setMessage("处理ORD_PROD_INST新装失败");
					e.printStackTrace();
					throw e;
				}
				
				
				try{
					prodInstMap.put("statusCd", 1000);
					//2.加入prod_inst_region
					long prodInstRegionId = prodinstDao.getSequeId();//统一序列
					prodInstMap.put("prodInstRegionId", prodInstRegionId);//需要处理主键prodInstRegionId
					prodInstMap.put("action", 1);
					prodinstDao.insertProdInstRegion(prodInstMap);
					prodInstRegionobjList1.add(prodInstMap);
					
					
					//3.构造167属性
					Map  attr167 = new HashMap();
					long prodInstAttrId = prodinstDao.getSequeId(); //统一序列
					attr167.put("prodInstAttrId",prodInstAttrId);   //  需改为调用序列注意修改
					attr167.put("parProdInstAttrId", 1);
					attr167.put("prodInstId",prodInstMap.get("prodInstId"));
					attr167.put("attrId", 167);
					attr167.put("attrValueId", 1);
					attr167.put("statusCd", 1000);
					attr167.put("routeId", acct_id);
					attr167.put("action", 1);
					attr167.put("effDate", prodInstMap.get("statusDate"));
					attr167.put("expDate",statePublic.expDate);
					attr167.put("updateDate", prodInstMap.get("updateDate"));
					attr167.put("executetime", d.format(df.parse(prodInstMap.get("updateDate").toString())));
					prodinstDao.insertProdInstAttrSub(attr167);
					prodInstAttrSubobjList1.add(attr167);
					//4.加入prod_inst_paymode
					long paymodeId = prodinstDao.getSequeId();
					prodInstMap.put("paymodeId", paymodeId);//需要处理主键paymodeId
					prodInstMap.put("statusCd", 1000);
					prodinstDao.insertProdInstPyamode(prodInstMap);
					
					prodInstPaymodeobjList1.add(prodInstMap);
					}catch(Exception e)
					{   
						msg.setMessage("处理prod_inst_region构造失败");
						e.printStackTrace();
						throw e;
					}
				
				//4.构造prod_inst_acc_num
				try{
					long prodInstAccNumId = prodinstDao.getSequeId(); //统一序列
					prodInstMap.put("prodInstAccNumId", prodInstAccNumId);
					prodInstMap.put("platId", 1);
					prodInstMap.put("accNumType", 1000);
					prodInstMap.put("applyRegionId", 1);
					prodInstMap.put("statusCd", 1000);
					prodInstMap.put("accNumType", 1000);
					prodinstDao.insertProdInstAccNum(prodInstMap);
					prodInstAccNumobjList1.add(prodInstMap);
				}catch(Exception e)
				{   
					msg.setMessage("新装prod_inst_acc_num构造失败");
					e.printStackTrace();
					throw e;
				}
				//5.构造prod_inst_state. prod_inst_state_ext
				try{
					
				long prodInstStateId = prodinstDao.getSequeId(); //统一序列
				prodInstMap.put("prodInstStateId", prodInstStateId);
				prodInstMap.put("stopType", 0);
				prodInstMap.put("state",prodInstMap.get("statusCd"));
				prodInstMap.put("statusCd", 100000);
				prodinstDao.insertProdInstState(prodInstMap);
				prodinstDao.insertProdInstStateExt(prodInstMap);
				prodInstStateobjList1.add(prodInstMap);
				}catch(Exception e)
				{   
					msg.setMessage("新装prod_inst_state构造失败");
					e.printStackTrace();
					throw e;
				}
	}
	return 1;
}
public int insertProdInstState(Map itemMap,Map userMap,Message msg,String service_offer_id) throws Exception
{
	try{
		List<Map<String,Object>> prodInstStateList = new ArrayList<Map<String,Object>>();
		prodInstStateList=ordBillDao.selectOrdProdInstState(itemMap);
		
		if(prodInstStateList.size()>0)
		{
			Map stateMap = prodInstStateList.get(0);
			long acctId = getAcctId(itemMap,stateMap,msg);
			stateMap.put("routeId", acctId);
			stateMap.put("action", 1);
			prodinstDao.insertProdInstState(stateMap);
			prodinstDao.insertProdInstStateExt(stateMap);
			prodInstStateobjList1.add(stateMap);
		}
		
	}catch(Exception e)
	{   
		msg.setMessage("处理ORD_PROD_INST_state新装失败");
		e.printStackTrace();
		throw e;
	}
	return 1;
}

public int insertProdInstAccNum(Map itemMap,Map userMap,Message msg,String service_offer_id) throws Exception
{
	try{
		
		List<Map<String,Object>> prodInstAccNumList = new ArrayList<Map<String,Object>>();
		prodInstAccNumList = ordBillDao.selectOrdProdInstAccNum(itemMap);
		if(prodInstAccNumList.size()>0)
		{
			Map accNumMap = prodInstAccNumList.get(0);
			long acctId = getAcctId(itemMap,accNumMap,msg);
			accNumMap.put("routeId", acctId);
			accNumMap.put("action", 1);
			accNumMap.put("effDate", accNumMap.get("statusDate"));
			//accNumMap.put("expDate", statePublic.EXPDATE);
			prodinstDao.insertProdInstAccNum(accNumMap);
			prodInstAccNumobjList1.add(accNumMap);
			
		}
		
	}catch(Exception e)
	{   
		msg.setMessage("处理ORD_PROD_INST_ACCNUM新装失败");
		e.printStackTrace();
		throw e;
	}
	return 1;
}
//public int insertProdInstPaymode(Map itemMap,Map userMap,Message msg,String service_offer_id) throws Exception
//{
//	try{
//		
//		List<Map<String,Object>> prodInstAccNumList = new ArrayList<Map<String,Object>>();
//		prodInstAccNumList = ordBillDao.selectOrdProdInstAccNum(itemMap);
//		if(prodInstAccNumList.size()>0)
//		{
//			Map accNumMap = prodInstAccNumList.get(0);
//			long acctId = getAcctId(itemMap,accNumMap,msg);
//			accNumMap.put("routeId", acctId);
//			accNumMap.put("action", 1);
//			accNumMap.put("effDate", accNumMap.get("statusDate"));
//			accNumMap.put("expDate", statePublic.EXPDATE);
//			prodinstDao.insertProdInstAccNum(accNumMap);
//			prodinstDao.updateProdInstAccNum(accNumMap);
//		}
//		
//	}catch(Exception e)
//	{   
//		msg.setMessage("处理ORD_PROD_INST_Paymode新装失败");
//		e.printStackTrace();
//		throw e;
//	}
//	return 1;
//}
//获取需要的 acct_id
public long getAcctId(Map itemMap,Map objMap,Message msg) throws Exception
{
	Long acctId = 0L;
	try{
		//1.serv_acct查找
		List<Map<String,Object>> prodInstAcct = prodinstDao.getProdInstAcctId(objMap);
		if(prodInstAcct.size()>=1)
		{
			Map acctIdMap = prodInstAcct.get(0);
			acctId = Long.parseLong(acctIdMap.get("acctId").toString());
		}else{
			//2.ord_account查找
			acctId=ordBillDao.getOrdAccountById(itemMap);//ORDER_ITEM_ID 条件是否要去掉，看后期数据
			if(acctId==null||acctId==0)
			{
				//3.ord_prod_inst_acct_rel查找
				List<Map<String,Object>> acctMap = ordBillDao.getOrdAccountByAcctRel(itemMap);
				//acctId=ordBillDao.getOrdAccountByAcctRel(itemMap);
				if(acctMap.size()>0)
				{
					Map mAcct = acctMap.get(0);
					acctId=Long.parseLong(mAcct.get("acct_id").toString());
				}else{
					msg.setMessage("处理ORD_PROD_INST新装获取acctId失败");
					return 0;
				}
//				if(acctId==null||acctId==0)
//				{
//					msg.setMessage("处理ORD_PROD_INST新装获取acctId失败");
//					return 0;
//				}
			}
		}
		
	}catch(Exception e)
	{   
		msg.setMessage("处理ORD_PROD_INST新装获取accty_id失败");
		e.printStackTrace();
		throw e;
	}
	
	return acctId;
}
public  void  tranManager(Long id,String notes) throws Exception{
		
		
		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
		DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
				TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
				
				try {
				//逻辑代码
					Map map = new HashMap();
					map.put("procFlag", 1);
					//map.put("procCnt",1);
					map.put("archGrpId", id);
					map.put("notes", notes);
					ordBillDao.updateOrdBill(map);
					transactionManager.commit(status);
					
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
					//transactionManager.rollback(status);
				   
				}	
		
	}
public  void  OneItemtranManager(Long archGrpId,String notes) throws Exception{
	
	
	WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
			TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
			
			try {
			//逻辑代码
				Map map = new HashMap();
				map.put("archGrpId", archGrpId);
				map.put("remarks", notes);
				map.put("procFlag", 1);
				
				ordBillDao.updateOneItemResult(map);
				transactionManager.commit(status);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
				//transactionManager.rollback(status);
			   
			}	
	
}*/

}

