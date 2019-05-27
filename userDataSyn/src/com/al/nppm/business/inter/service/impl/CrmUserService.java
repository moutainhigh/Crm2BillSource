package com.al.nppm.business.inter.service.impl;
//添加注释看看能不能提交
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.validation.constraints.Null;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.Margin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.al.nppm.business.inter.service.IHisService;
import com.al.nppm.business.account.dao.BTrunkBillingMapper;
import com.al.nppm.business.account.dao.CcmUserOrderMapper;
import com.al.nppm.business.account.dao.CustomerMapper;
import com.al.nppm.business.account.dao.IAccountMapper;
import com.al.nppm.business.account.dao.IProdInstMapper;
import com.al.nppm.business.account.dao.OfferInstMapper;
import com.al.nppm.business.account.dao.ProductNetworkMapper;
import com.al.nppm.business.account.dao.TifChangeAcctMapper;
import com.al.nppm.business.account.dao.TifVpnGroupMapper;
import com.al.nppm.business.inter.http.state.statePublic;
import com.al.nppm.business.syntomq.datasyn.DataSynDeal;
import com.al.nppm.business.syntomq.datasyn.Msg;
import com.al.nppm.business.syntomq.tool.CTGMqTool;
import com.al.nppm.business.syntomq.tool.STATUS;
import com.al.nppm.business.thread.SendMsgThread;
import com.al.nppm.common.utils.DateUtils;
import com.al.nppm.common.utils.StringUtil;
import com.al.nppm.model.Message;
import com.al.nppm.ord.ordbill.dao.OrdBillMapper;
import com.alibaba.druid.sql.visitor.functions.Substring;
import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xml.internal.security.keys.content.RetrievalMethod;

@Service("crmUserService")
public class CrmUserService {
	private static Logger logger = Logger.getLogger(CrmUserService.class);
	
	private static ExecutorService service = Executors.newFixedThreadPool(50);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static String  expDateString = "3000-01-01";
	
	@Autowired
	public IAccountMapper accountDao;
	@Autowired
	public IProdInstMapper prodinstDao;
	@Autowired
	public OfferInstMapper offerinstDao;
	@Autowired
	public OrdBillMapper ordBillDao;
	@Autowired
	public CustomerMapper customerMapperDao;
	@Autowired
	public ProductNetworkMapper productNetworkMapperDao;
	@Autowired
	public BTrunkBillingMapper bTrunkBillingMapperDao;
	@Autowired
    public IHisService hisService;
	@Autowired
	public TifChangeAcctMapper tifChangeAcctMapperDao;
	@Autowired
	public TifVpnGroupMapper TifVpnGroupMapperDao;
	@Autowired
	public CcmUserOrderMapper ccmUserOrderMapperDao;
	// @Autowired
	// public ProdOfferMapper prodOfferDao;
	
//	@Autowired
//	public OrdService ordService;
	

	// public long acct_id;
	public long routeCustId;

	List<Map<String, Object>> custobjList1;
	List<Map<String, Object>> acctobjList1;
	List<Map<String, Object>> taxPayerobjList1;
	List<Map<String, Object>> taxPayerAttrobjList1;
	List<Map<String, Object>> paymentPlanobjList1;
	List<Map<String, Object>> extAcctobjList1;
	List<Map<String, Object>> prodInstobjList1;
	List<Map<String, Object>> prodInstAttrobjList1;
	List<Map<String, Object>> prodInstSubobjList1;
	List<Map<String, Object>> prodInstRelobjList1;
	List<Map<String, Object>> prodInstAcctRelobjList1;
	List<Map<String, Object>> offerProdInstobjList1;
	List<Map<String, Object>> offerInstobjList1;
	List<Map<String, Object>> offerObjInstobjList1;
	List<Map<String, Object>> offerInstAttrobjList1;

	List<Map<String, Object>> prodInstRegionobjList1;
	List<Map<String, Object>> prodInstAccNumobjList1;
	List<Map<String, Object>> prodInstStateobjList1;
	List<Map<String, Object>> prodInstPaymodeobjList1;

	List<Map<String, Object>> prodInstGroupobjList1;

	List<Map<String, Object>> prodInstAttrSubobjList1;
	
	
	List<Map<String, Object>> partyList1;
	List<Map<String, Object>> partyIndList1;
	List<Map<String, Object>> partyRoleList1;
	List<Map<String, Object>> partyAttrList1;
	List<Map<String, Object>> contactsInfoList1;
	List<Map<String, Object>> contactsInfoAttrList1;
	
	List<Map<String, Object>> tifVpnGroupList1;
	List<Map<String, Object>> tifVpnMemList1;

	public void runTask1(Long id,Message msg, Map userMap) throws Exception {
		
		

	}
    
	
	public void runTask(String[] args) throws Exception {
		Date date = new Date();
		logger.debug("----------定时执行CRMTABLE---------------"+ sdf.format(date).toString());
        int sumCount=0;//本次处理总数
          int successCount=0;//本次处理成功数
        long startTime=System.currentTimeMillis();
		
		long id = 0;
		
		String notes;
		// acct_id=0;
		routeCustId = 0;

		Message msg = new Message();
		// userMap存放大对象，发往消息
		Map userMap = new HashMap();
		
		Map queryMap = new HashMap();
		if(args.length>0){
			for(int i=0;i<args.length;i++){
				if(i==0){
					queryMap.put("mod", args[0]);
				}else if(i==1){
					queryMap.put("modValue", args[1]);
				}
				else if(i==2){
					queryMap.put("regionId", args[2]);
				}else if(i==3){
					queryMap.put("proc_flag", args[3]);
				}
			}
			
		}
		List<Map<String, Object>> list = ordBillDao.selectOrdBill(queryMap);//获取总控表数据
		if (list.size() > 0) {
        	sumCount=list.size();
			for (int i = 0; i < list.size(); i++) {
                int flag = -1;
				int iCount = -1;
				String areaCode = "";
				Map<String, List<?>> synMap = new HashMap<String, List<?>>();

				WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
				DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
				TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

				try {
					custobjList1 = new ArrayList<Map<String, Object>>();
					acctobjList1 = new ArrayList<Map<String, Object>>();
					taxPayerobjList1 = new ArrayList<Map<String, Object>>();
					taxPayerAttrobjList1 = new ArrayList<Map<String, Object>>();
					paymentPlanobjList1 = new ArrayList<Map<String, Object>>();
					extAcctobjList1 = new ArrayList<Map<String, Object>>();
					prodInstobjList1 = new ArrayList<Map<String, Object>>();
					prodInstAttrobjList1 = new ArrayList<Map<String, Object>>();
					prodInstSubobjList1 = new ArrayList<Map<String, Object>>();
					prodInstRelobjList1 = new ArrayList<Map<String, Object>>();
					prodInstAcctRelobjList1 = new ArrayList<Map<String, Object>>();
					offerInstobjList1 = new ArrayList<Map<String, Object>>();
					offerObjInstobjList1 = new ArrayList<Map<String, Object>>();
					offerInstAttrobjList1 = new ArrayList<Map<String, Object>>();
					offerProdInstobjList1 = new ArrayList<Map<String, Object>>();

					prodInstRegionobjList1 = new ArrayList<Map<String, Object>>();
					prodInstAccNumobjList1 = new ArrayList<Map<String, Object>>();
					prodInstStateobjList1 = new ArrayList<Map<String, Object>>();
					prodInstPaymodeobjList1 = new ArrayList<Map<String, Object>>();
					prodInstGroupobjList1 = new ArrayList<Map<String, Object>>();

					prodInstAttrSubobjList1 = new ArrayList<Map<String, Object>>();
					
					partyList1= new ArrayList<Map<String, Object>>();
					partyIndList1= new ArrayList<Map<String, Object>>();
					partyRoleList1= new ArrayList<Map<String, Object>>();
					partyAttrList1= new ArrayList<Map<String, Object>>();
					contactsInfoList1= new ArrayList<Map<String, Object>>();
					contactsInfoAttrList1= new ArrayList<Map<String, Object>>();
					tifVpnGroupList1 = new ArrayList<Map<String,Object>>();
					tifVpnMemList1  = new ArrayList<Map<String,Object>>();
					synMap.put("CUSTOMER", custobjList1);
					synMap.put("ACCOUNT", acctobjList1);
					synMap.put("PROD_INST", prodInstobjList1);
					synMap.put("PROD_INST_ATTR", prodInstAttrobjList1);
					synMap.put("PROD_INST_SUB", prodInstSubobjList1);
					synMap.put("PROD_INST_REL", prodInstRelobjList1);
					synMap.put("PROD_INST_ACCT_REL", prodInstAcctRelobjList1);
					synMap.put("OFFER_INST", offerInstobjList1);
					synMap.put("OFFER_OBJ_INST_REL", offerObjInstobjList1);
					synMap.put("OFFER_INST_ATTR", offerInstAttrobjList1);
					synMap.put("OFFER_PROD_INST", offerProdInstobjList1);
					synMap.put("PROD_INST_REGION", prodInstRegionobjList1);
					synMap.put("PROD_INST_ACC_NUM", prodInstAccNumobjList1);
					synMap.put("PROD_INST_STATE_EXT", prodInstStateobjList1);
					synMap.put("PROD_INST_PAYMODE", prodInstPaymodeobjList1);
					// synMap.put( "prod_inst_group", prodInstGroupobjList1 );
					synMap.put("PROD_INST_ATTR_SUB", prodInstAttrSubobjList1);
					
					synMap.put("PARTY", partyList1);
					synMap.put("PARTY_IND", partyIndList1);
					synMap.put("PARTY_ROLE", partyRoleList1);
					synMap.put("PARTY_ATTR", partyAttrList1);
					synMap.put("CONTACTS_INFO", contactsInfoList1);
					synMap.put("CONTACTS_INFO_ATTR", contactsInfoAttrList1);
					synMap.put("TIF_VPN_GROUP", tifVpnGroupList1);
					synMap.put("TIF_VPN_MEM", tifVpnMemList1);
					Map map = (Map) list.get(i);

					id = Long.parseLong(map.get("ARCH_GRP_ID").toString());
					// 1.强制提交总控制表 改为已处理
//					iCount = tranManager(id, "");
					
					
					// 2.处理业务逻辑  flag=1处理成功、其他失败
					flag = CrmUserTable(map, msg, userMap);
					// 3.发消息
					// DataSynDeal.autoBuildAndSendMsg(synMap);
					// rs_storge
					if (flag < 0) {
						// 回滚
						transactionManager.rollback(status);
						updateOrdBill(id,2, msg.getMessage());
					} else {
						transactionManager.commit(status);
                        successCount++;
						updateOrdBill(id,1, msg.getMessage());
					}

					// 3.提交处理结果
//					iCount = tranManager(id, msg.getMessage());
					
//					updateOrdBill(id,1, msg.getMessage());

				} catch (Exception e) {
					//总控状态2失败
					e.printStackTrace();
					transactionManager.rollback(status);
                    logger.error("处理失败！"+e.getMessage());
                    if (e.getMessage() != null && !"".equals(e.getMessage())) {
                    	updateOrdBill(id,2, msg.getMessage()+ e.getMessage().substring(0,256));
					}
                    else {
                    	updateOrdBill(id,2, msg.getMessage());
					}
					
					//updateOrdBill(id,2, msg.getMessage());
//					iCount = tranManager(id, msg.getMessage());
				}
				// 消息发送
				try {
					if (flag > 0) {
//						DataSynDeal.autoBuildAndSendMsg(synMap);
						logger.debug("发送开始时间："+sdf.format(new Date()));
						service.submit(new SendMsgThread(synMap));
//						sendMsg(synMap);
						logger.debug("发送结束时间："+sdf.format(new Date()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
        logger.debug("本次处理开始时间："+sdf.format(date).toString()+
        		"\t花费总时间："+(System.currentTimeMillis()-startTime)+"毫秒\t"+
        		"本次处理归档数："+sumCount+
        		"\t成功数："+successCount+
        		"\t失败数："+(sumCount-successCount));
	}
	
	/*发送消息到密集计算框架*/
	public void sendMsg(Map synMap) {
		Map map=new HashMap();
		int sendFlag=-1;
		Msg msginfo=null;
		try {
			List<?> table = DataSynDeal.mapToList(synMap);
			Map<String, Object> params=DataSynDeal.buildParam();
			msginfo = DataSynDeal.buildMsg(table,params);
			sendFlag=DataSynDeal.sendMsg(msginfo);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}finally{
			map.put("id", prodinstDao.getSeq("seq_send_msg"));
			map.put("msg", JSON.toJSON( msginfo.getMap() ).toString());
			map.put("status", sendFlag);
			map.put("messageId", msginfo.getMap().get("message_id").toString());
			map.put("messageType", msginfo.getMap().get("message_type").toString());
			map.put("send_count", 1);
			prodinstDao.insertSendMsg(map);
		}
	}
	
	/*定时器重发失败的消息*/
	public void reSendMsg()  {
		Map queryMap=new HashMap();
		Msg msginfo=new Msg();
		
		List<Map<String, Object>> list = prodinstDao.selectSendMsg(queryMap);
		for (Map<String, Object> map : list) {
			byte[] buffer = (byte[]) map.get("msg");
			STATUS result;
			try {
				result = CTGMqTool.send( map.get("message_id").toString(), 
						map.get("message_type").toString(), 
						        new String(buffer) );
				queryMap.put("id", map.get("id").toString());
				queryMap.put("status", result.getCode());
				prodinstDao.updateSendMsgLog(queryMap);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				queryMap.put("id", map.get("id").toString());
				prodinstDao.updateSendMsgLog(queryMap);
			}			
		}
	}
	
	

	/* 一次性费用处理 */
	public void oneItemrunTask() throws Exception {
		Message msg = new Message();
		String remarks = "处理中";
		System.out.println("----------定时执行一次性费用- oneItemResult--------------"
				+ new Date());

		List<Map<String, Object>> oneList = ordBillDao.getOneItemResult();
		if (oneList.size() > 0) {
			for (int i = 0; i < oneList.size(); i++) {

				// 开启事务控制
				WebApplicationContext contextLoader = ContextLoader
						.getCurrentWebApplicationContext();
				DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader
						.getBean("transactionManager");
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
				TransactionStatus status = transactionManager
						.getTransaction(def); // 获得事务状态
				Map map = new HashMap();
				try {
					map = oneList.get(i);

					OneItemtranManager(
							Long.parseLong(map.get("ARCH_GRP_ID").toString()),
							remarks);

					int flag = InsertItemPlan(msg, map);
					if (flag < 0) {
						transactionManager.rollback(status);
					} else {
						transactionManager.commit(status);
					}

					OneItemtranManager(
							Long.parseLong(map.get("ARCH_GRP_ID").toString()),
							msg.getMessage());

				} catch (Exception e) {
					e.printStackTrace();
					msg.setMessage("一次性费用处理失败");

					transactionManager.rollback(status);
					OneItemtranManager(
							Long.parseLong(map.get("ARCH_GRP_ID").toString()),
							msg.getMessage());
				}
			}
		}

	}

	public int InsertItemPlan(Message msg, Map map) throws Exception {
		try {

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			Long chargeMethod = Long.parseLong(map.get("CHARGE_METHOD")
					.toString());
			if (chargeMethod == 6) // 转计费代收
			{
				long feeBillId = prodinstDao.getSequeId();

				Map feeBillMap = new HashMap();
				feeBillMap.put("areaCode", 111);
				feeBillMap.put("serialnumber", feeBillId);
				feeBillMap.put("feeSerial", map.get("ARCH_GRP_ID"));
				feeBillMap.put("orderNo", map.get("ARCH_GRP_ID"));
				feeBillMap.put("userId", map.get("PROD_INST_ID"));
				feeBillMap.put("acctItemType",
						map.get("BILL_ACCT_ITEM_TYPE_ID"));
				feeBillMap.put("payCharge", map.get("PAID_IN_AMOUNT"));
				feeBillMap.put("payTimes", 1);
				feeBillMap.put("feeDate", map.get("CREATE_DATE"));
				feeBillMap.put("effDate", map.get("CREATE_DATE"));
				feeBillMap.put("state", 0);
				feeBillMap.put("notes", "CRM一次性费用");

				ordBillDao.insertTifFeeBill(feeBillMap);
			} else {

				Map oneItem = new HashMap();
				long interPlanId = prodinstDao.getSequeId();
				oneItem.put("interPlanId", interPlanId);// zhujian

				// 0418 offerInstId 查找acct_id
				long offerInstId = Long.parseLong(map.get("OFFER_INST_ID")
						.toString());
				Map mapOfferInst = new HashMap();
				mapOfferInst.put("offerInstId", offerInstId);

				List<Map<String, Object>> offerlist = ordBillDao
						.getOneItemInstId(mapOfferInst);
				long instId = 0;
				if (offerlist.size() > 0) {
					Map offer = offerlist.get(0);
					instId = Long.parseLong(offer.get("prodInstId").toString());
					oneItem.put("objectId", offer.get("prodInstId"));
					// oneItem.put("acctId", offer.get("acctId")) ;
				} else {
					msg.setMessage("未找到prod_inst_id");
					return -1;
				}
				Map servAcctMap = new HashMap();
				servAcctMap.put("prodInstId", instId);
				List<Map<String, Object>> servAcctList = ordBillDao
						.getOneItemAcctId(servAcctMap);

				if (servAcctList.size() > 0) {
					Map acctMap = servAcctList.get(0);
					oneItem.put("objectId", acctMap.get("acctId"));
					// oneItem.put("acctId", offer.get("acctId")) ;
				} else {
					msg.setMessage("未找到Acct_id");
					return -1;
				}

				oneItem.put("offerInstId", map.get("OFFER_INST_ID"));
				oneItem.put("offerId", map.get("OFFER_ID"));
				oneItem.put("objectType", 2);
				// oneItem.put("objectId",map.get("PROD_INST_ID")) ;
				// oneItem.put("acctId", map.get("ACCT_ID")) ;
				oneItem.put("operType", 1);
				oneItem.put("operState", 0);
				oneItem.put("effDate", map.get("CREATE_DATE"));
				oneItem.put("orderDate", map.get("CREATE_DATE"));
				oneItem.put("createDate", df.format(date));
				oneItem.put("operDate", df.format(date));
				oneItem.put("amount", map.get("PAID_IN_AMOUNT"));

				ordBillDao.insertPayToPlan(oneItem);

			}
			msg.setMessage("处理成功");

		} catch (Exception e) {
			e.printStackTrace();
			msg.setMessage("一次性费用新增失败");
			throw e;
		}
		return 1;
	}

	public int CrmUserTable(Map ordMap, Message msg, Map userMap)
			throws Exception {
		Long archGrpId=Long.parseLong(ordMap.get("ARCH_GRP_ID").toString());
		Long custId=Long.parseLong(ordMap.get("CUST_ID").toString());
		//add by wangbaoqiang begin
		List<Map<String, Object>> tifOrgContrastMap = ordBillDao.selectTifOrgContrast(ordMap);
		if (tifOrgContrastMap.size() != 1) {
			msg.setMessage("取计费对应的org_id出错");
			return -1;
		}// add end;
		String tableName;
		long orderItemId;
		long serviceOfferId;
		int action; // 1 新增 2修改 3 删除
		String sOfferId;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat df1= new SimpleDateFormat( "yyyy-MM-dd" );
		List<Map<String, Object>> ordObjlist = ordBillDao
				.selectOrdBillObj(archGrpId);
		if(ordObjlist.size()==0){
			msg.setMessage("总控对象表无对应记录!");
			return 1;
		}
		for (int k = 0; k < ordObjlist.size(); k++) {
			// ----------------------开始处理表数据-------------------------
			Map map = (Map) ordObjlist.get(k);
			tableName = map.get("TABLE_NAME").toString();
			orderItemId = Long.parseLong(map.get("ORDER_ITEM_ID").toString());
			serviceOfferId = Long.parseLong(map.get("SERVICE_OFFER_ID")
					.toString());
			sOfferId = map.get("SERVICE_OFFER_ID").toString();

            //检查工单先后顺序(新装用户不校验前置工单)
            if (!"4010100000".equals(sOfferId)
                    && !"4010200000".equals(sOfferId)
                    && !"4010300000".equals(sOfferId)) {
                if (workOrder(map, userMap, msg) < 0) {
                    return -1;
                }
            }
            // 1.ORD_CUSTOMER 处理
            if (tableName.equals("ORD_CUSTOMER")) {
                Map custMap = new HashMap();
                try {
                    List<Map<String, Object>> custList = ordBillDao
                            .selectOrdCustomer(map);
					//add by wangbaoqiang begin					
					if (custList.size()== 0) {
						custList = ordBillDao.selectOrdCustomer_1300(map);
						if (custList.size() == 0) {
							msg.setMessage("取客户接口表ord_customer数据时出错:记录不存在");
							return -1;
						}
						else if (custList.size() > 1) {
							msg.setMessage("取客户接口表ord_customer数据时出错:记录有多条！");
							return -1;
						}
					}
					else if (custList.size() > 1) {
						msg.setMessage("取ord_customer客户接口表数据时出错:记录有多条！");
						return -1;
					}//add end;
                    if (custList.size() > 0) {
                        custMap = custList.get(0);
                        long cust_id = Long.parseLong(custMap.get("custId")
                                .toString());
                        custMap.put("routeId", cust_id);
                        custMap.put("executetime", d.format(df.parse(custMap
                                .get("updateDate").toString())));
                        custMap.put("statusCd", 1100);
                        // 代表新增
                        if (serviceOfferId == 1010100000) {
                            long custCount = accountDao
                                    .getCustomerById(custMap);
                            if (custCount > 0) {
                                msg.setMessage("新增客户失败已经存在cust_id:" + cust_id);
                                return -1;
                            }
                            accountDao.insertCustomer(custMap);

							custMap.put("action", 1);

						} else {
							// long custId =
							// Long.parseLong(custMap.get("custId").toString());
							long custCount = accountDao
									.getCustomerById(custMap);
							if (custCount == 1) {
								custMap.put("routeId", custMap.get("custId"));
                            	List customerHisList=accountDao.getCustomer(custMap);
                            	Map customerHisMap=(Map) customerHisList.get(0);
                            	hisService.insertCustomerHis(customerHisMap);
                            	
								accountDao.updateCustomer(custMap);
								custMap.put("action", 2);

							} else {
								System.out.println("custId:" + cust_id
										+ " 不存在 或  存在多个");
								msg.setMessage("处理客户ORG_CUSTOMER失败,不存在 或  存在多个cust_id:"
										+ cust_id);

								return -1;
							}
						}
					}

					custobjList1.add(custMap);
				} catch (Exception e) {
					msg.setFlag(statePublic.FAILFLAG);
					msg.setMessage("处理客户ORG_CUSTOMER失败");
					e.printStackTrace();
					throw e;
				}
//				ord_cust_contact_info_rel,cust_contact_info_rel
//				-----(2)、客户联系信息 REC_CUST_CONTACT				
				Map queryord_cust_contact_info_relMap = new HashMap();
				queryord_cust_contact_info_relMap.put("arch_grp_id", archGrpId);
				queryord_cust_contact_info_relMap.put("order_item_id", orderItemId);
				queryord_cust_contact_info_relMap.put("oper_type", 1000); 
			    List<Map<String, Object>> ord_cust_contact_info_relList =ordBillDao.getOrdCustContactInfoRel(queryord_cust_contact_info_relMap);
			    
			    for(int i=0;i<ord_cust_contact_info_relList.size();i++){
			    	Map ord_cust_contact_info_relMap=ord_cust_contact_info_relList.get(i);
			    	queryord_cust_contact_info_relMap.put("cust_connect_id", ord_cust_contact_info_relMap.get("custConnectId"));
			        int ord_cust_contact_info_relCount=ordBillDao.getCountOrdCustContactInfoRel(queryord_cust_contact_info_relMap);
			        if(ord_cust_contact_info_relCount==1){
			        	ord_cust_contact_info_relMap.put("statusCd", 1000);
			        	ord_cust_contact_info_relMap.put("routeId", queryord_cust_contact_info_relMap.get("custId"));
			        	try{
			    			accountDao.insertCustCcontactInfoRel(ord_cust_contact_info_relMap);
			    		}catch (Exception e) {
			        		e.printStackTrace();
			        		msg.setMessage("增加客户联系信息出错");
			        		return -1;
			        	}
			        }else{
			        	Map cust_contact_info_relUpdate=new HashMap();
			        	
			        	cust_contact_info_relUpdate.put("statusDate", ord_cust_contact_info_relMap.get("statusDate"));
			        	cust_contact_info_relUpdate.put("updateStaff", ord_cust_contact_info_relMap.get("updateStaff"));
			        	cust_contact_info_relUpdate.put("updateDate", ord_cust_contact_info_relMap.get("updateDate"));
			        	cust_contact_info_relUpdate.put("remark", ord_cust_contact_info_relMap.get("remark"));
			        	cust_contact_info_relUpdate.put("routeId", ord_cust_contact_info_relMap.get("custId"));
			        	cust_contact_info_relUpdate.put("custConnectId", ord_cust_contact_info_relMap.get("custConnectId"));//修改条件
			    		
			        	try{
                        	List CustContactInfoRelList=accountDao.getCustContactInfoRel(ord_cust_contact_info_relMap);
                            Map CustContactInfoRelMap=(Map) CustContactInfoRelList.get(0);
                            hisService.insertCustContactInfoRelHis(CustContactInfoRelMap);
			    			accountDao.updateCustContactInfoRel1(cust_contact_info_relUpdate);
			    		}catch (Exception e) {
			        		e.printStackTrace();
			        		msg.setMessage("修改客户联系信息出错");
			        		return -1;
			        	}
			        }
			    } 				
//			    ord_party_cert,party_cert
//			    --------(3)、参与人证件 PARTY_CERT				
				Map queryord_party_certMap = new HashMap();
				queryord_party_certMap.put("arch_grp_id", archGrpId);
				queryord_party_certMap.put("order_item_id", orderItemId);
				queryord_party_certMap.put("oper_type", 1000); 
			    List<Map<String, Object>> ord_party_certList =ordBillDao.getOrdPartyCert(queryord_party_certMap);
			    
			    for(int i=0;i<ord_party_certList.size();i++){
			    	Map ord_party_certMap=ord_party_certList.get(i);
			    	queryord_party_certMap.put("party_cert_id", ord_party_certMap.get("partyCertId"));
		        	ord_party_certMap.put("routeId", ord_party_certMap.get("partyId"));
			        int ord_party_certCount=ordBillDao.getCountOrdPartyCert(queryord_party_certMap);
			        if(ord_party_certCount==1){
			        	ord_party_certMap.put("statusCd", 1000);
			        	ord_party_certMap.put("routeId", queryord_party_certMap.get("partyId"));
			        	try{
			    			accountDao.insertPartyCert1(ord_party_certMap);
			    		}catch (Exception e) {
			        		e.printStackTrace();
			        		msg.setMessage("增加参与人证件人出错");
			        		return -1;
			        	}
			        }else{
			        	Map party_certUpdate=new HashMap();
			        	party_certUpdate.put("statusCd", 1100);
			        	party_certUpdate.put("certType", ord_party_certMap.get("certType"));
			        	party_certUpdate.put("certOrg", ord_party_certMap.get("certOrg"));
			        	party_certUpdate.put("certAddr", ord_party_certMap.get("certAddr"));
			        	party_certUpdate.put("certNum", ord_party_certMap.get("certNum"));
			        	party_certUpdate.put("statusCd", ord_party_certMap.get("statusCd"));
			        	party_certUpdate.put("statusDate", ord_party_certMap.get("statusDate"));
			        	party_certUpdate.put("updateStaff", ord_party_certMap.get("updateStaff"));
			        	party_certUpdate.put("updateDate", ord_party_certMap.get("updateDate"));
			        	party_certUpdate.put("remark", ord_party_certMap.get("remark"));
			        	party_certUpdate.put("partyCertId", ord_party_certMap.get("partyCertId"));
			        	party_certUpdate.put("routeId", ord_party_certMap.get("custId"));
			    		
			        	try{
			    			accountDao.updatePartyCert1(party_certUpdate);
			    		}catch (Exception e) {
			        		e.printStackTrace();
			        		msg.setMessage("修改参与人证件出错");
			        		return -1;
			        	}
			        }
			    } 						
//			    ord_cust_attr,cust_attr
//			    ---(4)、客户属性  ORD_CUST_ATTR	
				Map queryord_cust_attrMap = new HashMap();
				queryord_cust_attrMap.put("arch_grp_id", archGrpId);
				queryord_cust_attrMap.put("order_item_id", orderItemId);
			    List<Map<String, Object>> ord_cust_attrList =ordBillDao.getOrdCustAttr(queryord_cust_attrMap);
			    
			    for(int i=0;i<ord_cust_attrList.size();i++){
			    	Map ord_cust_attrMap=ord_cust_attrList.get(i);
			    	ord_cust_attrMap.put("routeId", ord_cust_attrMap.get("custId"));
			    	if(ord_cust_attrMap.get("operType").toString().equals("1000")){
			    		String attrValue= ord_cust_attrMap.get("attrValue").toString();
				        if(!(attrValue==null||attrValue.equals(""))){
				        	ord_cust_attrMap.put("statusCd", 1000);
				        	ord_cust_attrMap.put("custId", ord_cust_attrMap.get("custId"));
				        	try{
				    			accountDao.insertCustAttr1(ord_cust_attrMap);
				    		}catch (Exception e) {
				        		e.printStackTrace();
				        		msg.setMessage("增加客户属性出错");
				        		return -1;
				        	}
				        }else{
				        	
				        }
			    	}else{
			    		Map cust_attrUpdate=new HashMap();
			    		cust_attrUpdate.put("statusCd", 1100);
			    		cust_attrUpdate.put("statusDate", ord_cust_attrMap.get("statusDate"));
			    		cust_attrUpdate.put("updateStaff", ord_cust_attrMap.get("updateStaff"));
			    		cust_attrUpdate.put("updateDate", ord_cust_attrMap.get("updateDate"));
			    		cust_attrUpdate.put("remark", ord_cust_attrMap.get("remark"));
			    		cust_attrUpdate.put("custAttrId", ord_cust_attrMap.get("custAttrId"));
			    		cust_attrUpdate.put("routeId", ord_cust_attrMap.get("custId"));
			    		
			        	try{
			    			accountDao.updateCustAttr(cust_attrUpdate);
			    		}catch (Exception e) {
			        		e.printStackTrace();
			        		msg.setMessage("修改客户属性出错");
			        		return -1;
			        	}
			    	}	    		

			    } 		
				// 2.ORD_TAX_PAYER 处理
			}/*
			 * else if(tableName.equals("ORD_TAX_PAYER")) { Map taxPayerMap= new
			 * HashMap(); try{ List<Map<String,Object>> taxPayerList =
			 * ordBillDao.selectOrdTaxPayer(map);
			 * 
			 * if(taxPayerList.size()>0) //等于1 代表新增 大于1 代表修改 {
			 * 
			 * for(int i=0;i<taxPayerList.size();i++) //operType=1000的进行赋值 { Map
			 * tempMap = taxPayerMap = taxPayerList.get(i);
			 * if(Integer.parseInt(tempMap.get("operType").toString())==1000) {
			 * taxPayerMap=tempMap; } } if(taxPayerList.size()==1) {
			 * accountDao.insertTaxPayer(taxPayerMap);
			 * taxPayerMap.put("action",1); }else{
			 * accountDao.updateTaxPayer(taxPayerMap);
			 * taxPayerMap.put("action",2); } }
			 * taxPayerobjList1.add(taxPayerMap); //明天接着干 ORD_TAX_PAYER_ATTR Map
			 * taxOperMap = map; taxOperMap.put("operType", 1000);
			 * List<Map<String,Object>> taxPayerAttrList =
			 * ordBillDao.selectOrdTaxPayerAttr(taxOperMap);
			 * if(taxPayerAttrList.size()>0) { Map taxTmp = map; for(int
			 * i=0;i<taxPayerAttrList.size();i++) { Map taxTmpObj =
			 * taxPayerAttrList.get(i);
			 * 
			 * Map obj = taxTmpObj; obj.put("operType",null);
			 * List<Map<String,Object>> taxPayerAttrObjList =
			 * ordBillDao.selectOrdTaxPayerAttr(obj);
			 * if(taxPayerAttrObjList.size()==1) {
			 * accountDao.insertTaxPayerAttr(taxTmpObj);
			 * //taxTmpObj.put("action", 1); }else{
			 * accountDao.updateTaxPayerAttr(taxTmpObj);
			 * 
			 * } taxTmpObj.put("action", 1);
			 * taxPayerAttrobjList1.add(taxTmpObj); } }
			 * 
			 * }catch(Exception e) { // msg.setMessage("处理客户ORD_TAX_PAYER失败");
			 * e.printStackTrace(); throw e; } }
			 */
			// 3.ORD_ACCOUNT 处理
			else if (tableName.equals("ORD_ACCOUNT")) {
				// 3.1 ord_account
				try {
					List<Map<String, Object>> accountList = ordBillDao
							.selectOrdAccount(map);
					//add by wangbaoqiang begin
					if (accountList.size() > 1) {
						msg.setMessage("取帐户接口表ord_account数据时出错:记录数有多条");
						return -1;
					}
					else if (accountList.size() == 0) {
						msg.setMessage("取帐户接口表ord_account数据时出错:记录数为空");
						return -1;
					}// add end;
					if (accountList.size() > 0) {
						Map accountMap = accountList.get(0);
						//add by wangbaoqiang增加客户id的判断
						String prodInstID = "";
						String prodInstRegionId = "";
						prodInstID = accountMap.get("prodInstId").toString();
						if ("".equals(prodInstID)) {
							prodInstID = "-1"; 
						}
						List<Map<String, Object>> customerList = new ArrayList<Map<String, Object>>();
						try {
							customerList = accountDao.getCustomer(accountMap);
							if (customerList.size() == 0) {
								msg.setMessage("账户对应的客户在计费不存在! CUST_ID:" + accountMap.get("custId"));	
								return -1;
							}
						} catch (Exception e) {
							msg.setMessage("取帐户对应的客户的区域标识出错");
							e.printStackTrace();
							throw e;
						}//add end
						Map customerMap = customerList.get(0);
						prodInstRegionId = customerMap.get("regionId").toString();
						
						long acctId = Long.parseLong(accountMap.get("acctId")
								.toString());
						routeCustId = Long.parseLong(accountMap.get("custId")
								.toString());
						accountMap.put("routeId", accountMap.get("custId"));
						// acct_id=acctId;//全局使用acct_id
						int aCount = accountDao.getAccountById(accountMap);
						accountMap.put("statusCd", 1);
						if (serviceOfferId == 2010100000) {
							if (aCount > 0) {
								System.out.println("账户已经存在ACCT_ID:" + acctId);
								msg.setMessage("新增中账户已经存在ACCT_ID:" + acctId);
								return -1;
							}
							accountMap.put("routeId", routeCustId);
							accountMap.put("regionId", prodInstRegionId);
							//add by wangbaoqiang begin
							try {
								accountDao.insertAccount(accountMap);
								
							} catch (Exception e) {
								msg.setMessage("增加帐户表时出错");
								e.printStackTrace();
								throw e;
							}

							accountMap.put("action", 1);
						} else {
							// 账户是否存在判断
							if (aCount == 0) {
								System.out.println("修改账户不存在ACCT_ID:" + acctId);
								msg.setMessage("修改中账户不存在ACCT_ID:" + acctId);
								return -1;
							}//add by wangbaoqiang begin
							else if (aCount > 1) {
								msg.setMessage("修改中账户存在多条ACCT_ID:" + acctId);
								return -1;
							}//add end;
							//accountMap.put("routeId", routeCustId);
							
							//add by wangbaoqiang begin
							
							try {
							Long servProdId = prodinstDao.getSeq("SEQ_ACCOUNT_HIS_ID"); //获取账户序列
                            accountMap.put("accountSeq", servProdId);
                            int judge = hisService.insertAccountHis(accountMap);//插入账户的历史表
								accountDao.updateAccount(accountMap);
							} catch (Exception e) {
								msg.setMessage("修改帐户表时出错");
								e.printStackTrace();
								throw e;
							}//add end;

							accountMap.put("action", 2);
						}
						accountMap.put("executetime", d.format(df1.parse(accountMap.get("updateDate").toString())));
						acctobjList1.add(accountMap);
					}
				} catch (Exception e) {
					msg.setMessage("处理客户ORD_ACCOUNT失败");
					e.printStackTrace();
					throw e;
				}
				// 3.2 ord_payment_plan 判断是否存在paymentPlan 存在则失效再新增
				try {
					List<Map<String, Object>> smsPaymentList = new ArrayList<Map<String, Object>>();
					List<Map<String, Object>> paymentPlanList = ordBillDao
							.selectOrdPaymentPlan(map);

					Map paymentMap = new HashMap();
					//add by wangbaoqiang begin
					if (paymentPlanList.size() > 1) {
						msg.setMessage("取支付方案接口表ord_payment_plan数据时出错:记录有多条");
						return -1;
					}
					else if (paymentPlanList.size() == 0) {
						msg.setMessage("取支付方案接口表ord_payment_plan数据时出错:记录不存在");
						return -1;
					}//add end;
					if (paymentPlanList.size() > 0) {
						paymentMap = paymentPlanList.get(0);
						long acctId = Long.parseLong(paymentMap.get("acctId")
								.toString());
						paymentMap.put("routeId", paymentMap.get("acctId"));
						List<Map<String, Object>> oldPaymentPlanList = accountDao
								.getPaymentPlanByID(paymentMap);
						if (oldPaymentPlanList.size() > 0) {
							//add by wangbaoqiang 若ord表有記錄，失效所有的生效记录  begin
							for (int i = 0; i < oldPaymentPlanList.size(); i++) {
								Map oldPaymentMap = oldPaymentPlanList.get(i);
								if (!StringUtil.isEmpty(oldPaymentMap
										.get("paymentPlanId"))) {
									// 存在则失效
									oldPaymentMap.put("statusCd", 2);
									oldPaymentMap.put("expDate",
									paymentMap.get("effDate"));
									accountDao.updatePaymentPlan(oldPaymentMap);
									oldPaymentMap.put(
											"executetime",
											d.format(df1.parse(paymentMap.get(
													"updateDate").toString())));
									paymentPlanobjList1.add(oldPaymentMap);
								}
							}
						}
						paymentMap.put("statusCd", 1);
						//paymentMap.put("routeId", routeCustId);
						accountDao.insertPaymentPlan(paymentMap);
						paymentMap.put(
								"executetime",
								d.format(df1.parse(paymentMap.get(
										"updateDate").toString())));
						paymentPlanobjList1.add(paymentMap); //add end;
/*							 Map oldPaymentMap=oldPaymentPlanList.get(0);
							 if(!StringUtil
							 .isEmpty(oldPaymentMap.get("paymentPlanId"))) {
							 //存在则失效
							 
							 oldPaymentMap.put("statusCd", 2);
							 oldPaymentMap.put("expDate",
							 paymentMap.get("effDate"));
							 accountDao.updatePaymentPlan(oldPaymentMap);
							 oldPaymentMap.put("executetime", d.format(df1.parse(paymentMap.get("updateDate").toString())));
							 paymentPlanobjList1.add(oldPaymentMap); }
							 
						} else {
							// 不存在，直接插入
							paymentMap.put("statusCd", 1);
							paymentMap.put("routeId", routeCustId);
							accountDao.insertPaymentPlan(paymentMap);
							paymentMap.put("executetime", d.format(df1.parse(paymentMap.get("updateDate").toString())));
							paymentPlanobjList1.add(paymentMap);
						}*/

					}
				} catch (Exception e) {
					msg.setMessage("处理账户ORD_PAYMENT_PLAN失败");
					e.printStackTrace();
					throw e;
				}
				// 3.3 ord_ext_acct
				try {
					List<Map<String, Object>> extAcctList = ordBillDao
							.selectOrdExtAcct(map);
					//add by wangbaoqiang begin
/*					if (extAcctList.size() > 1) {
						msg.setMessage("取帐户外部支付帐号接口表ord_ext_acct数据时出错:记录有多条");
						return -1;
					}
					else if (extAcctList.size() == 0) {
						msg.setMessage("取帐户外部支付帐号接口表ord_ext_acct数据时出错:记录为空");
						return -1;
					}*///add end;
					Map extAcctMap = new HashMap();
					if (extAcctList.size() > 0) {
						extAcctMap = extAcctList.get(0);
						int operType = Integer.parseInt(extAcctMap.get(
								"operType").toString());
						extAcctMap.put("executetime", d.format(df1.parse(extAcctMap.get("updateDate").toString())));
						extAcctMap.put("routeId", extAcctMap.get("extAcctId"));
						if (operType == 1000) {
							//add by wangbaoqiang begin
							try {
								//extAcctMap.put("routeId", routeCustId);
								extAcctMap.put("statusCd", 1);
								accountDao.insertExtAcct(extAcctMap);
							} catch (Exception e) {
								msg.setMessage("增加外部支付帐号表时出错");
								e.printStackTrace();
								throw e;
							}//end
							extAcctMap.put("action", 1);
							extAcctobjList1.add(extAcctMap);
						} else {
							// Long ROUTE_ID=0L;
							List<Map<String, Object>> extAcctList1 = accountDao
									.getExtAcctID(extAcctMap);
							//add by wangbaoqiang begin
							if (extAcctList1.size() > 1) {
								msg.setMessage("修改外部支付账号表出错:记录有多条");
								return -1;
							}
							else if (extAcctList1.size() == 0) {
								msg.setMessage("修改外部支付账号表出错:记录为空");
								return -1;
							}// add end;
							if (extAcctList1.size() > 0) {
								Map extMap = extAcctList1.get(0);
								//modify by wangbaoqiang 将updateDate 修改为expdate
								extMap.put("expDate",extAcctMap.get("expdate"));
								extMap.put("statusCd", 2);
                                Long servProdId = prodinstDao.getSeq("SEQ_EXT_ACCT_HIS_ID"); //获取外部支付账户序列
                                extMap.put("extAcctSeq", servProdId);
                                int judge = hisService.insertExtAcctHis(extMap);//插入外部支付账户的历史表
								accountDao.updateExtAcct(extMap); // 失效时间
								extAcctMap.put("action", 2);
								extAcctobjList1.add(extAcctMap);
								//add by wangbaoqiang begin
								try {
									extAcctMap.put("routeId", extAcctMap.get("extAcctId"));
									extAcctMap.put("statusCd", 1);
									accountDao.insertExtAcct(extAcctMap);
									extAcctMap.put("action", 1);
									extAcctobjList1.add(extAcctMap);

								} catch (Exception e) {
									msg.setMessage("增加外部支付帐号表时出错");
									e.printStackTrace();
									throw e;
								}//end;
							}

						}
						//extAcctobjList1.add(extAcctMap);
					}

				} catch (Exception e) {
					msg.setMessage("处理账户ORD_EXT_ACCT失败");
					e.printStackTrace();
					throw e;
				}

			} else if (tableName.equals("ORD_PROD_INST")) {
				try {

					// 新装
					if (sOfferId.equals("4010100000")
							|| sOfferId.equals("4010200000")
							|| sOfferId.equals("4010300000")) {
						if (insertProdInst(map, userMap, msg) < 0) {
							return -1;
						}
						/*
						 * if(insertProdInstState(map, userMap, msg,sOfferId)<0)
						 * { return -1; } if(insertProdInstAccNum(map, userMap,
						 * msg,sOfferId)<0) { return -1; }
						 */
					}// 拆机
					else if (sOfferId.equals("4020100000")
							|| sOfferId.equals("4020100001") 
							|| sOfferId.equals("4020300000")
							|| sOfferId.equals("4020300001")
							|| sOfferId.equals("4020300002")
							|| sOfferId.equals("4020300003")
							|| sOfferId.equals("4020400000")
							|| sOfferId.equals("4020500000")
							|| sOfferId.equals("4020600000")) {

						
						  if(deleteProdInst(map, userMap, msg)<0) {
						  return -1; }
						 

					}// 更新
					else {
						
						  if(updateProdInst(map, userMap, msg,sOfferId)<0) {
						  return -1; }
						 
					}

					// 处理用户属性prod_inst_attr
					if (doProdInstAttr(map, userMap, msg) < 0) {
						return -1;
					}
					// 取用户功能产品，需要默认生成属性
					if(insertProdInstSub(map, userMap, msg) < 0){
						return -1;
					}
					/*if (doProdInstSub(map, userMap, msg) < 0) {
						return -1;
					}*/

					// 处理 prod_inst_rel关系
/*					if (doProdInstRel(map, userMap, msg) < 0) {
						return -1;
					}*/
					//add by wangbaoqiang 处理 用户群关系关系 
					if (doProdInstGroup(map, userMap, msg) < 0) {
						return -1;
					}

				} catch (Exception e) {
					 msg.setMessage("处理账户ORD_PROD_INST失败");
					e.printStackTrace();
					throw e;
				}
			} else if (tableName.equals("ORD_PROD_INST_ACCT_REL")) {
				try {
					
					if (doProdInstAcctRel(map, userMap, msg,serviceOfferId) < 0) {
						return -1;
					}
				} catch (Exception e) {
					// msg.setMessage("处理账户ORD_PROD_INST_ACCT_REL失败");
					e.printStackTrace();
					throw e;
				}
			} else if (tableName.equals("ORD_OFFER_INST")) {
				try {

					/*
					 * if(routeCustId==0) //获取 { List<Map<String,Object>>
					 * acctIdList = ordBillDao.getOfferInstAcctId(map);
					 * if(acctIdList.size()>0) { Map acctMap =
					 * acctIdList.get(0);
					 * 
					 * acct_id=getAcctId(map,acctMap, msg);
					 * 
					 * }
					 * 
					 * }
					 */
					//add by wangbaoqiang 按照四川模式处理 begin
					List<Map<String, Object>> offerInstList = ordBillDao
							.selectOrdOfferInst(map);
					if (offerInstList.size() > 0) {
						for (Map<String, Object> offerInstMap : offerInstList) {
							String operType = offerInstMap.get("operType").toString();
							//新装
							if (operType.equals("1000")
									&& !String.valueOf(serviceOfferId).equals("3020501002")) {
								if (insertOfferInst(offerInstMap, userMap, msg, routeCustId) < 0) // 销售品实例
								{
									return -1;
								}
							}
							//修改销售品时间
							else if (operType.equals("1000") 
									&& String.valueOf(serviceOfferId).equals("3020501002")) {
								if (UpdateOfferInst(offerInstMap, userMap, msg, routeCustId) < 0) {
									return -1;
								}
								
							}else if (operType.equals("1300")) {
								//添加副卡
								if (String.valueOf(serviceOfferId).equals("3020800000")) {
									if (insertOfferProdGroup(offerInstMap, userMap, msg, routeCustId) < 0) {
										return -1;
									}
									
								}
								//修改参数
								if (String.valueOf(serviceOfferId).equals("3020501001")) {
									if (updateOfferInstAtrr(offerInstMap, userMap, msg, routeCustId) < 0) {
										return -1;
									}
																	}
							}
							else if (operType.equals("1100")
									&& !String.valueOf(serviceOfferId).equals("3020501002")) {
								if (deleteOfferInst(offerInstMap, userMap, msg, routeCustId) < 0) {
									return -1;
								}
							}
						}
					}

				} catch (Exception e) {
					// msg.setMessage("处理ORD_OFFER_INST失败");
					e.printStackTrace();
					throw e;
				}
			} /*else if (tableName.equals("ORD_OFFER_PROD_INST_REL")) {
				try {
					if (insertOfferProdInst(map, userMap, msg, routeCustId) < 0) // 销售品角色
					{
						return -1;
					}

				} catch (Exception e) {
					// msg.setMessage("处理账户ORD_PROD_INST_ACCT_REL失败");
					e.printStackTrace();
					throw e;
				}
			} else if (tableName.equals("ORD_OFFER_INST_ATTR")) {
				try {
					if (insertOfferInstAtrr(map, userMap, msg, routeCustId) < 0)// 销售品对象角色
					{
						return -1;
					}

				} catch (Exception e) {
					// msg.setMessage("处理账户ORD_PROD_INST_ACCT_REL失败");
					e.printStackTrace();
					throw e;
				}
			}*/else if (tableName.equals("ORD_PARTY")) {
								
				String V_RET_CODE=insertParty(archGrpId,orderItemId,serviceOfferId);
				if(!V_RET_CODE.equals("1")){
					msg.setMessage(V_RET_CODE);
					return -1;
				}
			
			
			}else if (tableName.equals("ORD_TAX_PAYER")){
				String V_RET_CODE=insertTaxPayer(archGrpId,orderItemId);
				if(!V_RET_CODE.equals("1")){
					msg.setMessage(V_RET_CODE);
					return -1;
				}
				
			}				

			msg.setMessage("处理成功");
		}
		return 1;
	}

	// 销售品实例属性
	public int insertOfferInstAtrr(Map itemMap, Map userMap, Message msg,
			long acctId) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

			List<Map<String, Object>> offerInstAttrList = ordBillDao
					.selectOrdOfferInstAttr(itemMap);
			if (offerInstAttrList.size() > 0) {
				for (int i = 0; i < offerInstAttrList.size(); i++) {
					Map offerInstAttrMap = new HashMap();
					offerInstAttrMap = offerInstAttrList.get(i);
					offerInstAttrMap.put("executetime", d.format(df
							.parse(offerInstAttrMap.get("updateDate")
									.toString())));
					String operType = offerInstAttrMap.get("operType")
							.toString();
					if (operType.equals("1000")) {
						// 判断是否存在
						List<Map<String, Object>> oldOfferInstAttrList = offerinstDao
								.getOfferInstAttrId(offerInstAttrMap);
						if (oldOfferInstAttrList.size() == 0) {

							if (acctId == 0) // 可能存在对
							{
								/*
								 * List<Map<String,Object>> routeIdList =
								 * offerinstDao
								 * .getOfferInstId(offerInstAttrMap);
								 * if(routeIdList.size()>0) { Map routeMap =
								 * routeIdList.get(0);
								 * offerInstAttrMap.put("routeId",
								 * routeMap.get("route_id").toString()); }
								 */

							}
							offerInstAttrMap.put("routeId", acctId);
							offerInstAttrMap.put("statusCd", 1000);
							offerinstDao.insertOfferInstAttr(offerInstAttrMap);
							offerInstAttrMap.put("action", 1);
							offerInstAttrobjList1.add(offerInstAttrMap);
						}
					} else if (operType.equals("1100")) {
						// 0414 route_id
						Long ROUTE_ID = 0L;
						List<Map<String, Object>> oldOfferInstAttrList1 = offerinstDao
								.getOfferInstAttrId(offerInstAttrMap);
						if (oldOfferInstAttrList1.size() > 0) {
							for (Map<String, Object> attrMap : oldOfferInstAttrList1) {
								ROUTE_ID = Long.parseLong(attrMap.get("route_id")
										.toString());
								offerInstAttrMap.put("routeId", ROUTE_ID);
								offerinstDao.updateOfferInstAttr(offerInstAttrMap);
								offerInstAttrMap.put("action", 2);
								offerInstAttrobjList1.add(offerInstAttrMap);
							}
						}//当主键不一致时通过attrId、attrValue来定位主键
						else if (oldOfferInstAttrList1.size() == 0) {
							oldOfferInstAttrList1 = offerinstDao.getOfferInstAttrIdFromAttrId(offerInstAttrMap);
							if (oldOfferInstAttrList1.size() == 0) {
								msg.setMessage("没有找到符合条件的参数");
								return -1;
							}
							for (Map<String, Object> attrMap : oldOfferInstAttrList1) {
								ROUTE_ID = Long.parseLong(attrMap.get("route_id")
										.toString());
								attrMap.put("expDate", offerInstAttrMap.get("expDate"));
								attrMap.put("statusCd", offerInstAttrMap.get("statusCd"));
								offerinstDao.updateOfferInstAttr(attrMap);
								offerInstAttrMap.put("action", 2);
								offerInstAttrobjList1.add(attrMap);
							}
						}	
						
					}
					

				}
			}

		} catch (Exception e) {
			msg.setMessage("处理账户ORD_OFFER_INST_attr失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	// 销售品实例属性
		public int updateOfferInstAtrr(Map itemMap, Map userMap, Message msg,
				long acctId) throws Exception {
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

				List<Map<String, Object>> offerInstList = ordBillDao
						.selectOrdOfferInstFromRowId(itemMap);
				if (offerInstList.size() != 1) 
				{
					msg.setMessage("取销售品实例接口表出错");
					return -1;
			
				}
				if (acctId == 0) {
					Map offerInstMap = offerInstList.get(0);
					acctId = Long.parseLong(offerInstMap.get("ownerCustId").toString());
				}
				String priceObjectId = "";
				String roleId = "";
				String attrinstId = "";
				String attrId = "";
				String attrValueId = "";
				String attrValue = "";
				String attrCode = "";

						Map offerInstMap = new HashMap();
						Map offerInstTmp = new HashMap();
						offerInstMap = offerInstList.get(0);
						List<Map<String, Object>> offerInstRelList = ordBillDao.selectOrdOfferProdInstRel1300(offerInstMap);
						for (Map<String, Object> map : offerInstRelList) {
							 priceObjectId = map.get("prodInstId").toString();
							 roleId = map.get("roleId").toString();
							if ("".equals(priceObjectId)) {
								msg.setMessage("销售品对象接口表无数据OFFER_INST_ID" + offerInstMap.get("offerInstId"));
								return -1;
							}
							if (prodinstDao.getProdInstCount2Ha(map) > 0) {
								break;
							}
						}
						if ("".equals(roleId)) {
							msg.setMessage("套餐对象没有送到接口表,CRM_OFFER_INST_ID" + offerInstMap.get("offerInstId"));
						}
						List<Map<String, Object>> offerInstAttrList = ordBillDao
								.selectOrdOfferInstAttr1000(itemMap);
						for (Map<String, Object> map : offerInstAttrList) {
							Long ROUTE_ID = 0L;
							List<Map<String, Object>> oldOfferInstAttrList = offerinstDao
									.getOfferInstAttrId(map);
							if (oldOfferInstAttrList.size() > 0) {
								   Map attrMap = oldOfferInstAttrList.get(0);
									ROUTE_ID = Long.parseLong(attrMap.get("route_id")
											.toString());
									map.put("routeId", ROUTE_ID);
									map.put("statuscD", 1100);
									offerinstDao.updateOfferInstAttr(map);
									map.put("action", 2);
									offerInstAttrobjList1.add(map);

							}//当主键不一致时通过attrId、attrValue来定位主键
							else if (oldOfferInstAttrList.size() == 0) {
								oldOfferInstAttrList = offerinstDao.getOfferInstAttrIdFromAttrId(map);
								if (oldOfferInstAttrList.size() == 0) {
									msg.setMessage("没有找到符合条件的参数");
									return -1;
								}
								for (Map<String, Object> attrMap : oldOfferInstAttrList) {
									ROUTE_ID = Long.parseLong(attrMap.get("routeId")
											.toString());
									attrMap.put("expDate", map.get("effDate"));
									attrMap.put("statusCd", map.get("statusCd"));
									offerinstDao.updateOfferInstAttr(attrMap);
									attrMap.put("action", 2);
									offerInstAttrobjList1.add(attrMap);
								}
							}	
							
							map.put("executetime", d.format(df
									.parse(map.get("updateDate")
											.toString())));
								// 判断是否存在
							map.put("routeId", acctId);
							map.put("statusCd", 1000);
									offerinstDao.insertOfferInstAttr(map);
							map.put("action", 1);
						    offerInstAttrobjList1.add(map);
						}

			} catch (Exception e) {
				msg.setMessage("处理账户ORD_OFFER_INST_attr失败");
				e.printStackTrace();
				throw e;
			}
			return 1;
		}
	public int deleteOfferInstAtrr(Map itemMap, Map userMap, Message msg,
			long acctId) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

			List<Map<String, Object>> offerInstAttrList = ordBillDao
					.selectOrdOfferInstAttr(itemMap);
			if (offerInstAttrList.size() > 0) {
				for (int i = 0; i < offerInstAttrList.size(); i++) {
					Map offerInstAttrMap = new HashMap();
					offerInstAttrMap = offerInstAttrList.get(i);
					offerInstAttrMap.put("executetime", d.format(df
							.parse(offerInstAttrMap.get("updateDate")
									.toString())));
					String operType = offerInstAttrMap.get("operType")
							.toString();
			        if (operType.equals("1100")) {
						// 0414 route_id
						Long ROUTE_ID = 0L;
						List<Map<String, Object>> oldOfferInstAttrList1 = offerinstDao
								.getOfferInstAttrId(offerInstAttrMap);
						if (oldOfferInstAttrList1.size() > 0) {
							for (Map<String, Object> attrMap : oldOfferInstAttrList1) {
								ROUTE_ID = Long.parseLong(attrMap.get("route_id")
										.toString());
								offerInstAttrMap.put("routeId", ROUTE_ID);
								offerinstDao.updateOfferInstAttr(offerInstAttrMap);
								offerInstAttrMap.put("action", 2);
								offerInstAttrobjList1.add(offerInstAttrMap);
							}
						}//当主键不一致时通过attrId、attrValue来定位主键
						else if (oldOfferInstAttrList1.size() == 0) {
							oldOfferInstAttrList1 = offerinstDao.getOfferInstAttrIdFromAttrId(offerInstAttrMap);
							if (oldOfferInstAttrList1.size() == 0) {
								msg.setMessage("没有找到符合条件的参数");
								return -1;
							}
							for (Map<String, Object> attrMap : oldOfferInstAttrList1) {
								ROUTE_ID = Long.parseLong(attrMap.get("route_id")
										.toString());
								attrMap.put("expDate", offerInstAttrMap.get("expDate"));
								attrMap.put("statusCd", offerInstAttrMap.get("statusCd"));
								offerinstDao.updateOfferInstAttr(attrMap);
								offerInstAttrMap.put("action", 2);
								offerInstAttrobjList1.add(attrMap);
							}
						}	
						
					}
					

				}
			}

		} catch (Exception e) {
			msg.setMessage("处理账户ORD_OFFER_INST_attr失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}

	// 添加销售品
	public int insertOfferInst(Map itemMap, Map userMap, Message msg,
			long acctId) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat dfhhmmss = new SimpleDateFormat("HHmmss");

			Map map = itemMap;
					List<Map<String, Object>> ordOfferList = ordBillDao.selectOrdOfferInstFromRowId(map);
					if (ordOfferList.size() != 1) {
						msg.setMessage("订购取销售品实例接口表出错");
						return -1;
					}
					Map ordOfferInstMap = ordOfferList.get(0);
					String strExpDate = ordOfferInstMap.get("expDate").toString();
					String strEffDate = ordOfferInstMap.get("effDate").toString();
					if (strEffDate.equals(strExpDate)) {
						return 1;
					}
					if (strExpDate.compareTo(strEffDate) < 0) {
						msg.setMessage("新建套餐的失效时间必须大于生效时间");
						return -1;
					}
					//处理时间问题
					int index = strExpDate.indexOf(" ");
					String strTmp = strExpDate.substring(index + 1);
					if (strTmp.equals("23:59:59")) {
						Date expDate = d.parse(strExpDate);
						Calendar c = Calendar.getInstance();  
			             c.setTime(expDate);  
			             c.add(Calendar.DAY_OF_MONTH, 1);  
			             expDate = c.getTime();
			             strExpDate = d.format(expDate);
					}
					ordOfferInstMap.put("executetime", d.format(df
							.parse(ordOfferInstMap.get("updateDate").toString())));
					String operType = ordOfferInstMap.get("operType").toString();
					Long route_id = 0L;
						// 判断是否存在prod_inst,存在不处理
						List<Map<String, Object>> oldOfferInstList = offerinstDao
								.getOfferInstId(ordOfferInstMap);
						if (oldOfferInstList.size() > 0) {
							msg.setMessage("销售品实例数据已存在，不允许重复添加");
							return - 1;
						}
						if (oldOfferInstList.size() == 0) {
							if (acctId == 0) {
								routeCustId = Long.parseLong(ordOfferInstMap.get(
										"ownerCustId").toString());
							}
							ordOfferInstMap.put("statusCd", 1000);
							ordOfferInstMap.put("routeId", routeCustId);
							offerinstDao.insertOfferInst(ordOfferInstMap);
							ordOfferInstMap.put("action", 1);
							offerInstobjList1.add(ordOfferInstMap);
							
						}
						//增加商品实例成员 
						if (insertOfferProdInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
							return -1;
						}
						//增加商品角色
						if (insertOfferObjInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
							return -1;
						}
						//增加参数
						if (insertOfferInstAtrr(ordOfferInstMap, userMap, msg, acctId) < 0) {
							return -1;
						}

					
					/*else if (operType.equals("1100")) {
						List<Map<String, Object>> oldOfferInstList1 = offerinstDao
								.getOfferInstId(ordOfferInstMap);
						if (acctId == 0) {
							routeCustId = Long.parseLong(ordOfferInstMap.get(
									"ownerCustId").toString());
						}
						ordOfferInstMap.put("routeId", routeCustId);
						if (oldOfferInstList1.size() == 0) {
							
							offerinstDao.insertOfferInst(ordOfferInstMap);
							ordOfferInstMap.put("action", 1);
						}
						if (oldOfferInstList1.size() > 0) {
							Map oldOfferMap = oldOfferInstList1.get(0);
							route_id = Long.parseLong(oldOfferMap.get(
									"route_id").toString());
							ordOfferInstMap.put("routeId", route_id);
						} 
						if (route_id != 0 || route_id != null) {
							offerinstDao.updateOfferInst(ordOfferInstMap);
							ordOfferInstMap.put("action", 2);
							offerInstobjList1.add(ordOfferInstMap);

						} else {
							msg.setMessage("未找到销售品实例route_id");
							return -1;
						}
						//增加商品实例成员 
						if (insertOfferProdInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
							return -1;
						}
						//增加商品角色
						if (insertOfferObjInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
							return -1;
						}
						//增加参数
						if (insertOfferInstAtrr(ordOfferInstMap, userMap, msg, acctId) < 0) {
							return -1;
						}
						
					}*/
				/*for (int i = 0; i < offerInstList.size(); i++) {
					Map offerInstMap = new HashMap();
					offerInstMap = offerInstList.get(i);
					offerInstMap.put("executetime", d.format(df
							.parse(offerInstMap.get("updateDate").toString())));
					String operType = offerInstMap.get("operType").toString();
					Long route_id = 0L;
					if (operType.equals("1000")) {
						// 判断是否存在prod_inst,存在不处理
						List<Map<String, Object>> oldOfferInstList = offerinstDao
								.getOfferInstId(offerInstMap);
						if (oldOfferInstList.size() > 0) {
							msg.setMessage("销售品实例数据已存在，不允许重复添加");
							return - 1;
						}
						if (oldOfferInstList.size() == 0) {
							if (acctId == 0) {
								routeCustId = Long.parseLong(offerInstMap.get(
										"ownerCustId").toString());
							}

							offerInstMap.put("routeId", routeCustId);
							offerinstDao.insertOfferInst(offerInstMap);
							offerInstMap.put("action", 1);

							offerInstobjList1.add(offerInstMap);
							
						} else {

						}

					} else if (operType.equals("1100")) {
						List<Map<String, Object>> oldOfferInstList1 = offerinstDao
								.getOfferInstId(offerInstMap);
						if (oldOfferInstList1.size() > 0) {
							Map oldOfferMap = oldOfferInstList1.get(0);
							route_id = Long.parseLong(oldOfferMap.get(
									"route_id").toString());
							offerInstMap.put("routeId", route_id);
						} else {
							msg.setMessage("库中未找到销售品实例");
							return -1;
						}

						if (route_id != 0 || route_id != null) {
							offerinstDao.updateOfferInst(offerInstMap);
							offerInstMap.put("action", 2);
							offerInstobjList1.add(offerInstMap);

						} else {
							msg.setMessage("未找到销售品实例route_id");
							return -1;
						}

					} else if (operType.equals("1300")) {
						// 直接成员角色加入 场景 主副卡 等
						if (insertOfferObjInst(itemMap, userMap, msg,
								routeCustId) < 0) {
							return -1;
						}
					}

				}*/


		} catch (Exception e) {
			msg.setMessage("处理账户ORD_OFFER_INST失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	//增加修改商品逻辑
	public int UpdateOfferInst(Map itemMap, Map userMap, Message msg,
			long acctId) throws Exception {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat dfhhmmss = new SimpleDateFormat("HHmmss");

			Map map = itemMap;
			List<Map<String, Object>> ordOfferList = ordBillDao
					.selectOrdOfferInstFromRowId(map);
			if (ordOfferList.size() != 1) {
				msg.setMessage("订购取销售品实例接口表出错");
				return -1;
			}
			Map ordOfferInstMap = ordOfferList.get(0);
			String strExpDate = ordOfferInstMap.get("expDate").toString();
			String strEffDate = ordOfferInstMap.get("effDate").toString();
			if (strEffDate.equals(strExpDate)) {
				return 1;
			}
			if (strExpDate.compareTo(strEffDate) < 0) {
				msg.setMessage("新建套餐的失效时间必须大于生效时间");
				return -1;
			}
			// 处理时间问题
			int index = strExpDate.indexOf(" ");
			String strTmp = strExpDate.substring(index + 1);
			if (strTmp.equals("23:59:59")) {
				Date expDate = d.parse(strExpDate);
				Calendar c = Calendar.getInstance();
				c.setTime(expDate);
				c.add(Calendar.DAY_OF_MONTH, 1);
				expDate = c.getTime();
				strExpDate = d.format(expDate);
			}
			ordOfferInstMap.put("executetime", d.format(df
					.parse(ordOfferInstMap.get("updateDate").toString())));
			String operType = ordOfferInstMap.get("operType").toString();
			Long route_id = 0L;
			// 判断是否存在prod_inst,存在不处理
			List<Map<String, Object>> oldOfferInstList = offerinstDao
					.getOfferInstId(ordOfferInstMap);
			if (oldOfferInstList.size() == 0) {
				if (acctId == 0) {
					routeCustId = Long.parseLong(ordOfferInstMap.get(
							"ownerCustId").toString());
				}

				ordOfferInstMap.put("routeId", routeCustId);
				offerinstDao.insertOfferInst(ordOfferInstMap);
				ordOfferInstMap.put("action", 1);
				offerInstobjList1.add(ordOfferInstMap);

			} else {

				Map oldOfferMap = oldOfferInstList.get(0);
				route_id = Long.parseLong(oldOfferMap.get("route_id")
						.toString());
				ordOfferInstMap.put("routeId", route_id);

				if (route_id != 0 || route_id != null) {
					offerinstDao.updateOfferInst(ordOfferInstMap);
					ordOfferInstMap.put("action", 2);
					offerInstobjList1.add(ordOfferInstMap);

				} else {
					msg.setMessage("未找到销售品实例route_id");
					return -1;
				}
			}
			// 增加商品实例成员 ord_offer_prod_inst
			if (insertOfferProdInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
				return -1;
			}
			// 增加商品角色 ord_offer_object_rel
			if (insertOfferObjInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
				return -1;
			}
			// 增加参数 ord_offer_inst_attr
			if (insertOfferInstAtrr(ordOfferInstMap, userMap, msg, acctId) < 0) {
				return -1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			msg.setMessage("处理账户ORD_OFFER_PROD_INST_失败");
			e.printStackTrace();
			throw e;
		}

		return 1;
	}
	public int deleteOfferInst(Map itemMap, Map userMap, Message msg,
			long acctId) throws Exception {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			SimpleDateFormat dfhhmmss = new SimpleDateFormat("HHmmss");

			Map map = itemMap;
			List<Map<String, Object>> ordOfferList = ordBillDao
					.selectOrdOfferInstFromRowId(map);
			if (ordOfferList.size() != 1) {
				msg.setMessage("订购取销售品实例接口表出错");
				return -1;
			}
			Map ordOfferInstMap = ordOfferList.get(0);
			String strExpDate = ordOfferInstMap.get("expDate").toString();
			String strEffDate = ordOfferInstMap.get("effDate").toString();
			/*if (strEffDate.equals(strExpDate)) {
				return 1;
			}
			if (strExpDate.compareTo(strEffDate) < 0) {
				msg.setMessage("新建套餐的失效时间必须大于生效时间");
				return -1;
			}*/
			// 处理时间问题
			int index = strExpDate.indexOf(" ");
			String strTmp = strExpDate.substring(index + 1);
			if (strTmp.equals("23:59:59")) {
				Date expDate = d.parse(strExpDate);
				Calendar c = Calendar.getInstance();
				c.setTime(expDate);
				c.add(Calendar.DAY_OF_MONTH, 1);
				expDate = c.getTime();
				strExpDate = d.format(expDate);
			}
			ordOfferInstMap.put("executetime", d.format(df
					.parse(ordOfferInstMap.get("updateDate").toString())));
			String operType = ordOfferInstMap.get("operType").toString();
			Long route_id = 0L;
			// 判断是否存在prod_inst,存在不处理
			List<Map<String, Object>> oldOfferInstList = offerinstDao
					.getOfferInstId(ordOfferInstMap);
			if (oldOfferInstList.size() == 0) {
				msg.setMessage("退订套餐获取原有订购实例ID出错");
				return -1;
			} else {

				Map oldOfferMap = oldOfferInstList.get(0);
				route_id = Long.parseLong(oldOfferMap.get("route_id")
						.toString());
				ordOfferInstMap.put("routeId", route_id);

				if (route_id != 0 || route_id != null) {
					
					ordOfferInstMap.put("statusCd", 1100);
					offerinstDao.updateOfferInst(ordOfferInstMap);
					ordOfferInstMap.put("action", 2);
					offerInstobjList1.add(ordOfferInstMap);

				} else {
					msg.setMessage("未找到销售品实例route_id");
					return -1;
				}
				// 修改 ord_offer_prod_inst
				if (deleteOfferProdInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
					return -1;
				}
				// 修改 ord_offer_object_rel
				if (deleteOfferObjInst(ordOfferInstMap, userMap, msg, acctId) < 0) {
					return -1;
				}
				// 修改ord_offer_inst_attr
				if (deleteOfferInstAtrr(ordOfferInstMap, userMap, msg, acctId) < 0) {
					return -1;
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			msg.setMessage("处理账户ORD_OFFER_PROD_INST_失败");
			e.printStackTrace();
			throw e;
		}

		return 1;
	}
	// 销售品角色
	public int insertOfferProdInst(Map itemMap, Map userMap, Message msg,
			long acctId) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
			Map map = itemMap;
			List<Map<String, Object>> offerProdInstList = ordBillDao
					.selectOrdOfferProdInstRel(itemMap);
			if (offerProdInstList.size() > 0) {
				for (int i = 0; i < offerProdInstList.size(); i++) {
					Map offerProdInstMap = new HashMap();
					offerProdInstMap = offerProdInstList.get(i);
					offerProdInstMap.put("executetime", d.format(df
							.parse(offerProdInstMap.get("updateDate")
									.toString())));
					String operType = offerProdInstMap.get("operType")
							.toString();
					if (operType.equals("1000")) {
						// 取历史旧的，做判断是否存在
						offerProdInstMap.put("offerObjInstRelId",
								offerProdInstMap.get("offerProdInstRelId"));// 主键
						List<Map<String, Object>> oldOfferProdInstList = offerinstDao
								.getOfferObjInstId(offerProdInstMap);
						
						if (oldOfferProdInstList.size() == 0) {
							
							/*long lcnt = prodinstDao.getForCountByProdInstId(offerProdInstMap);
							if (lcnt <= 0) {
								msg.setMessage("用户(" + offerProdInstMap.get("prodInstId") + ")不存在,不能订购商品，请检查受理情况!");
								return -1;
							}*/
							offerProdInstMap.put("action", 1);
							offerProdInstMap.put("objType", 100000);
							offerProdInstMap.put(
									"objId",
									Long.parseLong(offerProdInstMap.get(
											"prodInstId").toString()));
							// 取销售品角色id
							map.put("offerInstId",
									Long.parseLong(offerProdInstMap.get(
											"offerInstId").toString()));

							long offerDetailId = Long
									.parseLong(offerProdInstMap.get(
											"offerProdRelId").toString());
							/*
							 * 20180511 chenhy long offerDetailId =
							 * statePublic.getOFferObjRelIdForNine
							 * (offerProdInstMap
							 * .get("offerProdRelId").toString());
							 * 
							 * if(offerDetailId==-1) { List<Map<String,Object>>
							 * offerIdList =
							 * ordBillDao.getOrdOfferInstOfferId(map); Map
							 * offerIdMap = new HashMap();
							 * if(offerIdList.size()>0)//取offerId { offerIdMap =
							 * offerIdList.get(0);
							 * offerProdInstMap.put("offerId"
							 * ,offerIdMap.get("offerId").toString()); }else{
							 * msg
							 * .setMessage("销售品实例角色中未取到offer_id,offer_inst_id:"
							 * +Long
							 * .parseLong(offerProdInstMap.get("offerInstId"
							 * ).toString())); return -1; }
							 * 
							 * offerDetailId =
							 * getOfferDetailId(offerProdInstMap,msg);
							 * if(offerDetailId<0) {
							 * msg.setMessage("取销售品成员角色失败offer_id:"
							 * +offerIdMap.get("offerId").toString()); return
							 * -1; } }
							 */

							// 修改getOrdOfferInstOfferId sql
							// 去掉operType=1000的过滤20180410
							// 直接取crm的offer_detail_id
							/*
							 * List<Map<String,Object>> offerIdList =
							 * ordBillDao.getOrdOfferInstOfferId(map); Map
							 * offerIdMap = new HashMap();
							 * if(offerIdList.size()>0)//取offerId { offerIdMap =
							 * offerIdList.get(0);
							 * offerProdInstMap.put("offerId"
							 * ,offerIdMap.get("offerId").toString()); }else{
							 * msg
							 * .setMessage("销售品实例角色中未取到offer_id,offer_inst_id:"
							 * +Long
							 * .parseLong(offerProdInstMap.get("offerInstId"
							 * ).toString())); return -1; }
							 * 
							 * long offerDetailId =
							 * getOfferDetailId(offerProdInstMap,msg);
							 * if(offerDetailId<0) {
							 * msg.setMessage("取销售品成员角色失败offer_id:"
							 * +offerIdMap.get("offerId").toString()); return
							 * -1; }
							 */
							// 取acct_id
							if (acctId == 0) {
								/*
								 * Map acctMap = new HashMap() ;
								 * acctMap.put("prodInstId",
								 * Long.parseLong(offerProdInstMap
								 * .get("prodInstId").toString())); acctId =
								 * getAcctId( itemMap,acctMap, msg) ;
								 */
								long prodInstRouteId = Long
										.parseLong(offerProdInstMap.get(
												"prodInstId").toString());
								routeCustId = getCustId(itemMap,
										prodInstRouteId, msg);
							}
							// 销售品成员角色直接用20180413
							// long offerDetailId =
							// statePublic.getOFferObjRelIdForNine(offerProdInstMap.get("offerProdRelId").toString());
							offerProdInstMap.put("routeId", routeCustId);
							offerProdInstMap
									.put("offerObjRelId", offerDetailId);
							offerProdInstMap.put("lastOrderItemId",
									offerDetailId);
							offerProdInstMap.put("offerObjInstRelId",
									offerProdInstMap.get("offerProdInstRelId"));// 主键
							offerProdInstMap.put("statusCd", 1000);
							offerinstDao
									.insertOfferObjInstRel(offerProdInstMap);

							offerObjInstobjList1.add(offerProdInstMap);
						}
					} else if (operType.equals("1100")) {
						// 取历史旧的route_id 0414
						Long ROUTE_ID = 0L;
						offerProdInstMap.put("offerObjInstRelId",
								offerProdInstMap.get("offerProdInstRelId"));
						List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
								.getOfferObjInstId(offerProdInstMap);
						if (oldOfferProdInstList1.size() < 0) {
							msg.setMessage("没有找到可以修改的数据");
							return -1;
						}else if (oldOfferProdInstList1.size() > 1) {
							msg.setMessage("有多条符合条件的数据");
							return -1;
						}
						
						if (oldOfferProdInstList1.size() > 0) {
							Map olddetailMap = oldOfferProdInstList1.get(0);
							ROUTE_ID = Long.parseLong(olddetailMap.get(
									"ROUTE_ID").toString());

						} else {
							msg.setMessage("销售品成员角色中未找到route_id");
							return -1;
						}
						offerProdInstMap.put("routeId", ROUTE_ID);
						offerProdInstMap.put("offerObjInstRelId",
								offerProdInstMap.get("offerProdInstRelId"));// 主键
						offerinstDao.updateOfferObjInstRel(offerProdInstMap);
						offerProdInstMap.put("action", 2);
						offerProdInstMap.put("objType", 100000);
						offerProdInstMap.put(
								"objId",
								Long.parseLong(offerProdInstMap.get(
										"prodInstId").toString()));
						offerObjInstobjList1.add(offerProdInstMap);
					}
				}
			}

		} catch (Exception e) {
			msg.setMessage("处理账户ORD_OFFER_PROD_INST_失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	public int insertOfferProdGroup(Map itemMap, Map userMap, Message msg,
			long acctId) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
			Map map = itemMap;
			List<Map<String, Object>> ordOfferProdInstList = ordBillDao
					.selectOrdOfferInstFromRowId(map);
			if (ordOfferProdInstList.size() != 1) {
				msg.setMessage("取销售品实例接口表出错");
				return -1;
			}
			
			List<Map<String, Object>> offerProdInstList =  offerinstDao.getOfferInstId(map);
			if (offerProdInstList.size() != 1) {
				msg.setMessage("修改订购成员获取原订购实例出错");
				return -1;
			}
			List<Map<String, Object>> offerProdInstRelList = ordBillDao
					.selectOrdOfferProdInstRel(map);
			if (offerProdInstRelList.size() > 0) {
				for (int i = 0; i < offerProdInstRelList.size(); i++) {
					Map offerProdInstMap = new HashMap();
					offerProdInstMap = offerProdInstRelList.get(i);
					offerProdInstMap.put("executetime", d.format(df
							.parse(offerProdInstMap.get("updateDate")
									.toString())));
					String operType = offerProdInstMap.get("operType")
							.toString();
					if (operType.equals("1000")) {
						// 取历史旧的，做判断是否存在
						offerProdInstMap.put("offerObjInstRelId",
								offerProdInstMap.get("offerProdInstRelId"));// 主键
						List<Map<String, Object>> oldOfferProdInstList = offerinstDao
								.getOfferObjInstId(offerProdInstMap);
						
						if (oldOfferProdInstList.size() == 0) {
							offerProdInstMap.put("action", 1);
							offerProdInstMap.put("objType", 100000);
							offerProdInstMap.put("statusCd", 1000);
							offerProdInstMap.put(
									"objId",
									Long.parseLong(offerProdInstMap.get(
											"prodInstId").toString()));
							// 取销售品角色id
							map.put("offerInstId",
									Long.parseLong(offerProdInstMap.get(
											"offerInstId").toString()));

							long offerDetailId = Long
									.parseLong(offerProdInstMap.get(
											"offerProdRelId").toString());
							/*
							 * 20180511 chenhy long offerDetailId =
							 * statePublic.getOFferObjRelIdForNine
							 * (offerProdInstMap
							 * .get("offerProdRelId").toString());
							 * 
							 * if(offerDetailId==-1) { List<Map<String,Object>>
							 * offerIdList =
							 * ordBillDao.getOrdOfferInstOfferId(map); Map
							 * offerIdMap = new HashMap();
							 * if(offerIdList.size()>0)//取offerId { offerIdMap =
							 * offerIdList.get(0);
							 * offerProdInstMap.put("offerId"
							 * ,offerIdMap.get("offerId").toString()); }else{
							 * msg
							 * .setMessage("销售品实例角色中未取到offer_id,offer_inst_id:"
							 * +Long
							 * .parseLong(offerProdInstMap.get("offerInstId"
							 * ).toString())); return -1; }
							 * 
							 * offerDetailId =
							 * getOfferDetailId(offerProdInstMap,msg);
							 * if(offerDetailId<0) {
							 * msg.setMessage("取销售品成员角色失败offer_id:"
							 * +offerIdMap.get("offerId").toString()); return
							 * -1; } }
							 */

							// 修改getOrdOfferInstOfferId sql
							// 去掉operType=1000的过滤20180410
							// 直接取crm的offer_detail_id
							/*
							 * List<Map<String,Object>> offerIdList =
							 * ordBillDao.getOrdOfferInstOfferId(map); Map
							 * offerIdMap = new HashMap();
							 * if(offerIdList.size()>0)//取offerId { offerIdMap =
							 * offerIdList.get(0);
							 * offerProdInstMap.put("offerId"
							 * ,offerIdMap.get("offerId").toString()); }else{
							 * msg
							 * .setMessage("销售品实例角色中未取到offer_id,offer_inst_id:"
							 * +Long
							 * .parseLong(offerProdInstMap.get("offerInstId"
							 * ).toString())); return -1; }
							 * 
							 * long offerDetailId =
							 * getOfferDetailId(offerProdInstMap,msg);
							 * if(offerDetailId<0) {
							 * msg.setMessage("取销售品成员角色失败offer_id:"
							 * +offerIdMap.get("offerId").toString()); return
							 * -1; }
							 */
							// 取acct_id
							if (acctId == 0) {
								/*
								 * Map acctMap = new HashMap() ;
								 * acctMap.put("prodInstId",
								 * Long.parseLong(offerProdInstMap
								 * .get("prodInstId").toString())); acctId =
								 * getAcctId( itemMap,acctMap, msg) ;
								 */
								long prodInstRouteId = Long
										.parseLong(offerProdInstMap.get(
												"prodInstId").toString());
								routeCustId = getCustId(itemMap,
										prodInstRouteId, msg);
							}
							// 销售品成员角色直接用20180413
							// long offerDetailId =
							// statePublic.getOFferObjRelIdForNine(offerProdInstMap.get("offerProdRelId").toString());
							offerProdInstMap.put("routeId", routeCustId);
							offerProdInstMap
									.put("offerObjRelId", offerDetailId);
							offerProdInstMap.put("lastOrderItemId",
									offerDetailId);
							offerProdInstMap.put("offerObjInstRelId",
									offerProdInstMap.get("offerProdInstRelId"));// 主键
							offerinstDao
									.insertOfferObjInstRel(offerProdInstMap);

							offerObjInstobjList1.add(offerProdInstMap);
						}
					} else if (operType.equals("1100")) {
						// 取历史旧的route_id 0414
						Long ROUTE_ID = 0L;
						offerProdInstMap.put("offerObjInstRelId",
								offerProdInstMap.get("offerProdInstRelId"));
						List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
								.getOfferObjInstId(offerProdInstMap);
						if (oldOfferProdInstList1.size() < 0) {
							msg.setMessage("没有找到可以修改的数据");
							return -1;
						}else if (oldOfferProdInstList1.size() > 1) {
							msg.setMessage("有多条符合条件的数据");
							return -1;
						}
						
						if (oldOfferProdInstList1.size() > 0) {
							Map olddetailMap = oldOfferProdInstList1.get(0);
							ROUTE_ID = Long.parseLong(olddetailMap.get(
									"ROUTE_ID").toString());

						} else {
							msg.setMessage("销售品成员角色中未找到route_id");
							return -1;
						}
						offerProdInstMap.put("routeId", ROUTE_ID);
						offerProdInstMap.put("offerObjInstRelId",
								offerProdInstMap.get("offerProdInstRelId"));// 主键
						offerinstDao.updateOfferObjInstRel(offerProdInstMap);
						offerProdInstMap.put("action", 2);
						offerProdInstMap.put("objType", 100000);
						offerProdInstMap.put(
								"objId",
								Long.parseLong(offerProdInstMap.get(
										"prodInstId").toString()));
						offerObjInstobjList1.add(offerProdInstMap);
					}
				}
			}

		} catch (Exception e) {
			msg.setMessage("处理账户ORD_OFFER_PROD_INST_失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	public int deleteOfferProdInst(Map itemMap, Map userMap, Message msg,
			long acctId) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
			Map map = itemMap;
			List<Map<String, Object>> offerProdInstList = ordBillDao
					.selectOrdOfferProdInstRel(itemMap);
			if (offerProdInstList.size() > 0) {
				for (int i = 0; i < offerProdInstList.size(); i++) {
					Map offerProdInstMap = new HashMap();
					offerProdInstMap = offerProdInstList.get(i);
					offerProdInstMap.put("executetime", d.format(df
							.parse(offerProdInstMap.get("updateDate")
									.toString())));
					String operType = offerProdInstMap.get("operType")
							.toString();
					//if (operType.equals("1100")) {
						// 取历史旧的route_id 0414
						Long ROUTE_ID = 0L;
						offerProdInstMap.put("offerObjInstRelId",
								offerProdInstMap.get("offerProdInstRelId"));
						List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
								.getOfferObjInstId(offerProdInstMap);
						if (oldOfferProdInstList1.size() < 0) {
							msg.setMessage("没有找到可以修改的数据");
							return -1;
						}else if (oldOfferProdInstList1.size() > 1) {
							msg.setMessage("有多条符合条件的数据");
							return -1;
						}
						
						if (oldOfferProdInstList1.size() > 0) {
							Map olddetailMap = oldOfferProdInstList1.get(0);
							ROUTE_ID = Long.parseLong(olddetailMap.get(
									"ROUTE_ID").toString());

						} else {
							msg.setMessage("销售品成员角色中未找到route_id");
							return -1;
						}
						offerProdInstMap.put("routeId", ROUTE_ID);
						offerProdInstMap.put("offerObjInstRelId",
								offerProdInstMap.get("offerProdInstRelId"));// 主键
						try {
							offerinstDao.updateOfferObjInstRel(offerProdInstMap);

						} catch (Exception e) {
							msg.setMessage("修改销售品成员失败：offerObjInstRelId=" + offerProdInstMap.get("offerProdInstRelId"));
							e.printStackTrace();
							throw e;
						}
						offerProdInstMap.put("action", 2);
						offerProdInstMap.put("objType", 100000);
						offerProdInstMap.put(
								"objId",
								Long.parseLong(offerProdInstMap.get(
										"prodInstId").toString()));
						offerObjInstobjList1.add(offerProdInstMap);
					//}
				}
			}

		} catch (Exception e) {
			msg.setMessage("处理账户ORD_OFFER_PROD_INST_失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	// 销售品对象角色
	public int insertOfferObjInst(Map itemMap, Map userMap, Message msg,
			long acctId) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat df1= new SimpleDateFormat( "yyyy-MM-dd" );
			Map map = itemMap;
			List<Map<String, Object>> offerObjInstList = ordBillDao
					.selectOrdOfferObjInstRel(itemMap);
			if (offerObjInstList.size() > 0) {
				for (int i = 0; i < offerObjInstList.size(); i++) {
					Map offerProdInstMap = offerObjInstList.get(i);
//					offerProdInstMap.put("executetime", offerProdInstMap.get("updateDate"));
					offerProdInstMap.put("executetime", d.format(df1.parse(offerProdInstMap.get("updateDate").toString())));
					String operType = offerProdInstMap.get("operType")
							.toString();
					if (operType.equals("1000")) {
						// 取历史旧的，做判断是否存在
						List<Map<String, Object>> oldOfferProdInstList = offerinstDao
								.getOfferObjInstId(offerProdInstMap);
						if (oldOfferProdInstList.size() == 0) {
							offerProdInstMap.put("action", 1);

							// 取销售品角色id 可能不需要取 需确认
							// long offerDetailId =
							// statePublic.getOFferObjRelIdForNine(offerProdInstMap.get("offerObjRelId").toString());
							map.put("offerInstId",
									Long.parseLong(offerProdInstMap.get(
											"offerInstId").toString()));

							/*
							 * List<Map<String,Object>> offerIdList =
							 * ordBillDao.getOrdOfferInstOfferId(map);
							 * if(offerIdList.size()>0)//取offerId { Map
							 * offerIdMap = offerIdList.get(0);
							 * offerProdInstMap.
							 * put("offerId",offerIdMap.get("offerId"
							 * ).toString()); } offerDetailId =
							 * getOfferDetailId(offerProdInstMap,msg);
							 * if(offerDetailId<0) {
							 * msg.setMessage("销售品对象成员角色取值出错"); return -1; }
							 * offerProdInstMap.put("offerObjRelId",
							 * offerDetailId);
							 */

							Map routeMap = new HashMap();

							offerProdInstMap.put("routeId", acctId);
							long offerDetailId = statePublic
									.getOFferObjRelIdForNine(offerProdInstMap
											.get("offerObjRelId").toString());
							offerProdInstMap
									.put("offerObjRelId", offerDetailId);
							offerProdInstMap.put("lastOrderItemId", "1");
							offerProdInstMap.put("statusCd", 1000);
							offerProdInstMap.put("objType", 170000);
							offerinstDao
									.insertOfferObjInstRel(offerProdInstMap);

							offerObjInstobjList1.add(offerProdInstMap);
						}
					} else if (operType.equals("1100")) {
						// add by 0414 取route_id
						Long ROUTE_ID = 0L;
						List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
								.getOfferObjInstId(offerProdInstMap);
						if (oldOfferProdInstList1.size() > 0) {
							Map oldObjMap = oldOfferProdInstList1.get(0);
							ROUTE_ID = Long.parseLong(oldObjMap.get("ROUTE_ID")
									.toString());

						} else {
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
			// if(offerObjInstList.size()>0)
			// {
			// for(int i=0;i<offerObjInstList.size();i++)
			// {
			// Map offerObjInstMap = offerObjInstList.get(i);
			// String operType=offerObjInstMap.get("operType").toString();
			// if(operType.equals("1000"))
			// {
			// offerinstDao.insertOfferObjInstRel(offerObjInstMap);
			// offerObjInstMap.put("action", 1);
			// offerObjInstobjList1.add(offerObjInstMap);
			// }
			// }
			// }
		} catch (Exception e) {
			msg.setMessage("处理账户ORD_OFFER_PROD_INST_失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	public int deleteOfferObjInst(Map itemMap, Map userMap, Message msg,
			long acctId) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			Map map = itemMap;
			List<Map<String, Object>> offerObjInstList = ordBillDao
					.selectOrdOfferObjInstRel(itemMap);
			if (offerObjInstList.size() > 0) {
				for (int i = 0; i < offerObjInstList.size(); i++) {
					Map offerProdInstMap = offerObjInstList.get(i);

					offerProdInstMap.put(
							"executetime",
							d.format(df1.parse(offerProdInstMap.get(
									"updateDate").toString())));
					String operType = offerProdInstMap.get("operType")
							.toString();
					if (operType.equals("1100")) {
						// add by 0414 取route_id
						Long ROUTE_ID = 0L;
						List<Map<String, Object>> oldOfferProdInstList1 = offerinstDao
								.getOfferObjInstId(offerProdInstMap);
						if (oldOfferProdInstList1.size() > 0) {
							Map oldObjMap = oldOfferProdInstList1.get(0);
							ROUTE_ID = Long.parseLong(oldObjMap.get("ROUTE_ID")
									.toString());

						} else {
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
		} catch (Exception e) {
			msg.setMessage("处理账户ORD_OFFER_PROD_INST_失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	public int doProdInstAcctRel(Map itemMap, Map userMap, Message msg,Long serviceOfferId)
			throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
			Map acctRelMap = itemMap;

			List<Map<String, Object>> prodInstAcctRelList = ordBillDao
					.selectOrdProdInstAcctRel(acctRelMap);

			List<Map<String, Object>> ordProdInstAcctRelAttrList =new ArrayList<Map<String,Object>>();
			if (prodInstAcctRelList.size() > 0) {
				for (int i = 0; i < prodInstAcctRelList.size(); i++) {

					Map servAcctMap = new HashMap();
					servAcctMap = prodInstAcctRelList.get(i);
					servAcctMap.put("executetime", d.format(df
							.parse(servAcctMap.get("updateDate").toString())));
					long operType = Long.parseLong(servAcctMap.get("operType")
							.toString());

					if (routeCustId == 0) {
						long prodInstRouteId = Long.parseLong(servAcctMap.get(
								"prodInstId").toString());
						routeCustId = getCustId(itemMap, prodInstRouteId, msg);
					}
					ordProdInstAcctRelAttrList = ordBillDao.getOrdProdInstAcctRelAttr(servAcctMap);
					String acctId = servAcctMap.get("acctId").toString();
					//取接口表ord_prod_inst表，判断是否为组合产品，组合产品不加账务关系
					List<Map<String, Object>> ordProdInstList = ordBillDao.selectOrdProdInst(servAcctMap);
					if (ordProdInstList.size() == 0) {
						ordProdInstList = ordBillDao.selectOrdProdInst1300(servAcctMap);
						if (ordProdInstList.size() != 1) {
							msg.setMessage("取用户接口表ord_prod_inst数据时出错");
							return -1;
						}
					}else if (ordProdInstList.size() != 1) {
						msg.setMessage("取用户接口表ord_prod_inst数据时出错");
						return -1;
					}
					Map ordProdInstMap = ordProdInstList.get(0);
					List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(ordProdInstMap);
					if (tifProdList.size() == 1) {
						Map prodConMap = new HashMap();
						prodConMap = tifProdList.get(0);
						if (prodConMap.get("prodType").equals("10C")
								||prodConMap.get("prodType").equals("10D")) 
							return 1;
					}//取接口表ord_prod_inst表，判断是否为组合产品，组合产品不加账务关系 end
					if (operType == 1000)// 直接插入账务关系
					{
						long acctCnt = accountDao.getAccountById(servAcctMap);//要在确认一下
						String prodInstId = servAcctMap.get("prodInstId").toString();
						if (acctCnt == 0) {
							msg.setMessage("用户(" + prodInstId + ")的支付账户(" + acctId + ")在计费不存在") ;
							return -1;
						}
						/*之前增加组合产品判断的位置*/
						List<Map<String, Object>> prodInstMapList = prodinstDao.getProdInstOBJ(Long.parseLong(prodInstId));
						if (prodInstMapList.size() != 1) {
							msg.setMessage("取对应的用户编码时出错，不允许增加账务关系");
							return -1;
						}
						/*long prodInstPayModeID = selectPaymodeId(servAcctMap);
						if (prodInstPayModeID == -1) {
							msg.setMessage("取用户的付费类型出错，不允许增加账务关系");
							return -1;
						}*/
						Map mapExpDateMap = new HashMap();
						List<Map<String, Object>> ordPordInstAccrelList = ordBillDao.selectOrdProdInstAcctRel1100(servAcctMap);
						if (ordPordInstAccrelList.size() > 0 ) {
							List<Map<String, Object>> ordBillexpDateList = ordBillDao.queryOrdBillExpDate(servAcctMap);
							 mapExpDateMap = ordBillexpDateList.get(0);
							 if (mapExpDateMap.get("finishDate") == null 
									 ||"".equals(mapExpDateMap.get("finishDate")) ) {
								msg.setMessage("取工单竣工时间出错");
								return -1;
							}
						}
						long prodInstPayModeID = Long.parseLong(ordProdInstMap.get("paymentModeCd").toString());
						// ad by wangbaoqiang 如果没有帐户代表号,则增加  1默认 -1否
//						if ("1".equals(servAcctMap.get("ifDefaultAcctId").toString())) {
						List<Map<String, Object>> accountMapList = accountDao.getAccout(Long.parseLong(acctId));
							for (Iterator iterator = accountMapList.iterator(); iterator
									.hasNext();) {
								Map<String, Object> map = (Map<String, Object>) iterator
										.next();
								acctCnt = 0;
								acctCnt =  prodinstDao.getProdInstCount2Ha(map);
								if (acctCnt == 0) {
									try {
										Map mapTemp = new HashMap();
										mapTemp.put("prodInstId", prodInstId);
										mapTemp.put("acctId", acctId);
										mapTemp.put("routeId", map.get("routeId"));
										accountDao.updateAccount(mapTemp);
										map.put("action", 2);
										acctobjList1.add(map);
									} catch (Exception e) {
										msg.setMessage("增加帐务关系时出错");
										e.printStackTrace();
										throw e;
									}

								}
							}
							acctCnt = 0;
							acctCnt = prodinstDao.getCntProdInstIdFromAcctRel(servAcctMap);
							if (acctCnt == 0) {
								/*long prodInstAcctRelId = prodinstDao
										.seqProdInstAcctRelId();
								servAcctMap.put("prodInstAcctRelId", prodInstAcctRelId);*/
								servAcctMap.put("acctItemGroupId", 1);
								servAcctMap.put("action", 1);
								servAcctMap.put("statusCd", 1);
								servAcctMap.put("routeId", routeCustId);
								if (!"".equals(mapExpDateMap.get("finishDate"))
										&& mapExpDateMap.get("finishDate") != null ) {
									servAcctMap.put("effDate", mapExpDateMap.get("finishDate"));
								}
								try {
									prodinstDao.insertProdInstAcctRel(servAcctMap);
								} catch (Exception e) {
									msg.setMessage("增加帐务关系时出错");
									e.printStackTrace();
									throw e;
								}
								;
								prodInstAcctRelobjList1.add(servAcctMap);
							}
							else {
								msg.setMessage("存在相同的账户:prod_inst_id=" + prodInstId + ";ACCT_ID=" + acctId + "请检查");
							}
							//增加过户插入过户中间表  paymodecd = 2100 ocs用户
							try {
								List<Map<String, Object>> ordPordInstAcctReList = ordBillDao.selectOrdProdInstAcctRel1100(servAcctMap);
								for (Map<String, Object> map : ordPordInstAcctReList) {
									String oldAcctId = map.get("acctId").toString();
									boolean isProdInstExists = false; //判断老账户下是否有在用用户
									//判断老账户下是否有在用用户 begin
									List<Map<String,Object>> acctProdInstList = prodinstDao.getProdInstFromAcctId(map);
									for (Map<String, Object> acctProdMap : acctProdInstList) {
										long lCount = prodinstDao.getProdInstCount2Ha(acctProdMap);
										if (lCount > 0) {
											isProdInstExists = true;
											break;
										}
									}//判断老账户下是否有在用用户 end
									Map mapTmp = new HashMap();
									String state = "";
									if (prodInstPayModeID == 2100) {
										 state = "3";
									}else {
										state = "0";
									}
									mapTmp.put("areaCode", 0431);
									mapTmp.put("serialnumber", map.get("ARCH_GRP_ID"));
									mapTmp.put("servId", map.get("prodInstId"));
									mapTmp.put("orgAcctId", oldAcctId);
									mapTmp.put("dstAcctId", acctId);
									mapTmp.put("inputDate", df.format(new Date()));
									mapTmp.put("state", state);
									//用户级
									tifChangeAcctMapperDao.insertTifChangeAcct(mapTmp);
									if (prodInstPayModeID == 2100) {
										tifChangeAcctMapperDao.insertTifOcsChangeAcct(mapTmp);
									}
									//如果没有有效用户，插入过户中间表
									if (!isProdInstExists) {
										
										//账户级
										mapTmp.put("servId", null);
										tifChangeAcctMapperDao.insertTifChangeAcct(mapTmp);
										if (prodInstPayModeID == 1200) {
											tifChangeAcctMapperDao.insertTifOcsChangeAcct(mapTmp);

										}
									}
									
								}
							} catch (Exception e) {
								msg.setMessage("增加过户中间表出错");
								e.printStackTrace();
								throw e;

							
							}
//						}//add end;
						
						
						//帐务定制关系附加属性
						for(Map<String, Object> ordProdInstAcctRelAttrMap : ordProdInstAcctRelAttrList){
							//ordProdInstAcctRelAttrMap.put("PROD_INST_ACCT_REL_ID", prodInstAcctRelId);
							if(prodinstDao.getForProdInstAcctRelId(ordProdInstAcctRelAttrMap)==null){
								prodinstDao.insertProdInstAcctRelAttr(ordProdInstAcctRelAttrMap);
								prodInstAcctRelobjList1.add(ordProdInstAcctRelAttrMap);
							}

						}
							
						
					} else if (operType == 1100)// 修改
					{
						
						List<Map<String, Object>> ordBillexpDateList = ordBillDao.queryOrdBillExpDate(servAcctMap);
						Map mapExpDateMap = ordBillexpDateList.get(0);
						 if (mapExpDateMap.get("finishDate") == null) {
							msg.setMessage("取工单竣工时间出错");
							return -1;
						}
						//modify by wangbaoqiang 先取序列，取不到值，再按用户取
						List<Map<String, Object>> oldProdInstAcctRelList = prodinstDao
								.getProdInstAcctRelIdFromRelId(servAcctMap);
						if (oldProdInstAcctRelList.size() == 0) {
							
							oldProdInstAcctRelList = prodinstDao
									.getProdInstAcctRelId(servAcctMap);
						}
						// 修改账户代表号码 取账户下最小号码
						try {
							List<Map<String, Object>> accountMapList = accountDao
									.getAccoutForAccNum(Long.parseLong(acctId));
//							if ("1".equals(servAcctMap.get("ifDefaultAcctId").toString())) {
								if (accountMapList.size() > 0) {
									Map<String, Object> prodInstRelMap = prodinstDao
											.getProdInstIdFromAcctRel(servAcctMap);
									//如果该账户下有多个在用用户则取最小代表号码，如果没有保持不变
									long prodInstId = prodinstDao.getMinProdInstId(prodInstRelMap);
									if (prodInstId != -1) {
										Map accountMap = new HashMap();
										accountMap = accountMapList.get(0);
										accountMap.put("prodInstId", prodInstId);
										accountMap.put("action", 2);
										accountDao.updateAccount(accountMap);
										acctobjList1.add(accountMap);
									}
							}
						} catch (Exception e) {
							// TODO: handle exception
							msg.setMessage("修改账户代表号码 取账户下最小号码");
							e.printStackTrace();
							throw e;
						}
						
						if (oldProdInstAcctRelList.size() > 0) {
							Map oldAcctMap = oldProdInstAcctRelList.get(0);
							servAcctMap.put("action", 2);
							servAcctMap.put("statusCd", 2);
							/*servAcctMap.put("expDate",
									servAcctMap.get("statusDate"));*/
							servAcctMap.put("expDate",
									mapExpDateMap.get("finishDate"));
							servAcctMap.put("prodInstAcctRelId",
									oldAcctMap.get("PROD_INST_ACCT_REL_ID"));
							servAcctMap.put("routeId",
									oldAcctMap.get("ROUTE_ID"));
							prodinstDao.updateProdInstAcctRel(servAcctMap);
							prodInstAcctRelobjList1.add(servAcctMap);
							
							//修改账务定制关系
							//ordProdInstAcctRelAttrList = ordBillDao.getOrdProdInstAcctRelAttr(servAcctMap);
							for(Map<String, Object> ordProdInstAcctRelAttrMap : ordProdInstAcctRelAttrList){
								prodinstDao.updateProdInstAcctRelAttr(ordProdInstAcctRelAttrMap);
								prodInstAcctRelobjList1.add(ordProdInstAcctRelAttrMap);
							}
						} else {
							msg.setMessage("未找到要修改的账务关系serc_acct_id:"
									+ servAcctMap.get("prodInstAcctRelId")
											.toString());
							return -1;
						}
					}

				}
			}

		} catch (Exception e) {
			msg.setMessage("处理账户ORD_PROD_INST_REL失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	
	public int deleteProdInstAcctRel(Map itemMap, Map userMap, Message msg,Long serviceOfferId)
			throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
			Map acctRelMap = itemMap;

			List<Map<String, Object>> prodInstAcctRelList = ordBillDao
					.selectOrdProdInstAcctRel(acctRelMap);

			List<Map<String, Object>> ordProdInstAcctRelAttrList =new ArrayList<Map<String,Object>>();
			if (prodInstAcctRelList.size() > 0) {
				for (int i = 0; i < prodInstAcctRelList.size(); i++) {

					Map servAcctMap = new HashMap();
					servAcctMap = prodInstAcctRelList.get(i);
					servAcctMap.put("executetime", d.format(df
							.parse(servAcctMap.get("updateDate").toString())));
					long operType = Long.parseLong(servAcctMap.get("operType")
							.toString());

					if (routeCustId == 0) {
						long prodInstRouteId = Long.parseLong(servAcctMap.get(
								"prodInstId").toString());
						routeCustId = getCustId(itemMap, prodInstRouteId, msg);
					}
					ordProdInstAcctRelAttrList = ordBillDao.getOrdProdInstAcctRelAttr(servAcctMap);
					String acctId = servAcctMap.get("acctId").toString();
					if (operType == 1000)// 直接插入账务关系
					{
						long acctCnt = accountDao.getAccountById(servAcctMap);//要在确认
						String prodInstId = servAcctMap.get("prodInstId").toString();
						if (acctCnt == 0) {
							msg.setMessage("用户(" + prodInstId + ")的支付账户(" + acctId + ")在计费不存在") ;
							return -1;
						}
						//取接口表ord_prod_inst表，判断是否为组合产品，组合产品不加账务关系
						List<Map<String, Object>> ordProdInstList = ordBillDao.selectOrdProdInst(servAcctMap);
						if (ordProdInstList.size() == 0) {
							ordProdInstList = ordBillDao.selectOrdProdInst1300(servAcctMap);
							if (ordProdInstList.size() != 1) {
								msg.setMessage("取用户接口表ord_prod_inst数据时出错");
								return -1;
							}
						}else if (ordProdInstList.size() != 1) {
							msg.setMessage("取用户接口表ord_prod_inst数据时出错");
							return -1;
						}
						Map ordProdInstMap = ordProdInstList.get(0);
						List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(ordProdInstMap);
						if (tifProdList.size() == 1) {
							Map prodConMap = new HashMap();
							prodConMap = tifProdList.get(0);
							if (prodConMap.get("prodType").equals("10C")
									||prodConMap.get("prodType").equals("10D")) 
								return 1;
						}//取接口表ord_prod_inst表，判断是否为组合产品，组合产品不加账务关系 end
						List<Map<String, Object>> prodInstMapList = prodinstDao.getProdInstOBJ(Long.parseLong(prodInstId));
						if (prodInstMapList.size() != 1) {
							msg.setMessage("取对应的用户编码时出错，不允许增加账务关系");
							return -1;
						}
						/*long prodInstPayModeID = selectPaymodeId(servAcctMap);
						if (prodInstPayModeID == -1) {
							msg.setMessage("取用户的付费类型出错，不允许增加账务关系");
							return -1;
						}*/
						long prodInstPayModeID = Long.parseLong(ordProdInstMap.get("paymentModeCd").toString());
						// ad by wangbaoqiang 如果没有帐户代表号,则增加  1默认 -1否
//						if ("1".equals(servAcctMap.get("ifDefaultAcctId").toString())) {
						List<Map<String, Object>> accountMapList = accountDao.getAccout(Long.parseLong(acctId));
							for (Iterator iterator = accountMapList.iterator(); iterator
									.hasNext();) {
								Map<String, Object> map = (Map<String, Object>) iterator
										.next();
								acctCnt = 0;
								acctCnt =  prodinstDao.getProdInstCount2Ha(map);
								if (acctCnt == 0) {
									try {
										Map mapTemp = new HashMap();
										mapTemp.put("prodInstId", prodInstId);
										mapTemp.put("acctId", acctId);
										mapTemp.put("routeId", map.get("routeId"));
										accountDao.updateAccount(mapTemp);
										map.put("action", 1);
										acctobjList1.add(map);
									} catch (Exception e) {
										msg.setMessage("增加帐务关系时出错");
										e.printStackTrace();
										throw e;
									}

								}
							}
							acctCnt = 0;
							acctCnt = prodinstDao.getCntProdInstIdFromAcctRel(servAcctMap);
							if (acctCnt == 0) {
								/*long prodInstAcctRelId = prodinstDao
										.seqProdInstAcctRelId();
								servAcctMap.put("prodInstAcctRelId", prodInstAcctRelId);*/
								servAcctMap.put("acctItemGroupId", 1);
								servAcctMap.put("action", 1);
								servAcctMap.put("statusCd", 1);
								servAcctMap.put("routeId", routeCustId);
								try {
									prodinstDao.insertProdInstAcctRel(servAcctMap);
								} catch (Exception e) {
									msg.setMessage("增加帐务关系时出错");
									e.printStackTrace();
									throw e;
								}
								;
								prodInstAcctRelobjList1.add(servAcctMap);
							}
							else {
								msg.setMessage("存在相同的账户:prod_inst_id=" + prodInstId + ";ACCT_ID=" + acctId + "请检查");
							}
							//增加过户插入过户中间表  paymodecd = 2100 ocs用户
							try {
								List<Map<String, Object>> ordPordInstAcctReList = ordBillDao.selectOrdProdInstAcctRel1100(servAcctMap);
								for (Map<String, Object> map : ordPordInstAcctReList) {
									String oldAcctId = map.get("acctId").toString();
									boolean isProdInstExists = false; //判断老账户下是否有在用用户
									//判断老账户下是否有在用用户 begin
									List<Map<String,Object>> acctProdInstList = prodinstDao.getProdInstFromAcctId(map);
									for (Map<String, Object> acctProdMap : acctProdInstList) {
										long lCount = prodinstDao.getProdInstCount2Ha(acctProdMap);
										if (lCount > 0) {
											isProdInstExists = true;
											break;
										}
									}//判断老账户下是否有在用用户 end
									Map mapTmp = new HashMap();
									String state = "";
									if (prodInstPayModeID == 2100) {
										 state = "3";
									}else {
										state = "0";
									}
									mapTmp.put("areaCode", 0431);
									mapTmp.put("serialnumber", map.get("ARCH_GRP_ID"));
									mapTmp.put("servId", map.get("prodInstId"));
									mapTmp.put("orgAcctId", oldAcctId);
									mapTmp.put("dstAcctId", acctId);
									mapTmp.put("inputDate", df.format(new Date()));
									mapTmp.put("state", state);
									//用户级
									tifChangeAcctMapperDao.insertTifChangeAcct(mapTmp);
									if (prodInstPayModeID == 2100) {
										tifChangeAcctMapperDao.insertTifOcsChangeAcct(mapTmp);
									}
									//如果没有有效用户，插入过户中间表
									if (!isProdInstExists) {
										
										//账户级
										mapTmp.put("servId", null);
										tifChangeAcctMapperDao.insertTifChangeAcct(mapTmp);
										if (prodInstPayModeID == 1200) {
											tifChangeAcctMapperDao.insertTifOcsChangeAcct(mapTmp);

										}
									}
									
								}
							} catch (Exception e) {
								msg.setMessage("增加过户中间表出错");
								e.printStackTrace();
								throw e;

							
							}
//						}//add end;
						
						
						//帐务定制关系附加属性
						for(Map<String, Object> ordProdInstAcctRelAttrMap : ordProdInstAcctRelAttrList){
							//ordProdInstAcctRelAttrMap.put("PROD_INST_ACCT_REL_ID", prodInstAcctRelId);
							if(prodinstDao.getForProdInstAcctRelId(ordProdInstAcctRelAttrMap)==null){
								prodinstDao.insertProdInstAcctRelAttr(ordProdInstAcctRelAttrMap);
								prodInstAcctRelobjList1.add(ordProdInstAcctRelAttrMap);
							}

						}
							
						
					} else if (operType == 1100)// 修改
					{
						//modify by wangbaoqiang 先取序列，取不到值，再按用户取
						List<Map<String, Object>> oldProdInstAcctRelList = prodinstDao
								.getProdInstAcctRelIdFromRelId(servAcctMap);
						if (oldProdInstAcctRelList.size() == 0) {
							
							oldProdInstAcctRelList = prodinstDao
									.getProdInstAcctRelId(servAcctMap);
						}
						// 修改账户代表号码 取账户下最小号码
						try {
							List<Map<String, Object>> accountMapList = accountDao
									.getAccoutForAccNum(Long.parseLong(acctId));
//							if ("1".equals(servAcctMap.get("ifDefaultAcctId").toString())) {
								if (accountMapList.size() > 0) {
									Map<String, Object> prodInstRelMap = prodinstDao
											.getProdInstIdFromAcctRel(servAcctMap);
									//如果该账户下有多个在用用户则取最小代表号码，如果没有保持不变
									long prodInstId = prodinstDao.getMinProdInstId(prodInstRelMap);
									if (prodInstId != -1) {
										Map accountMap = new HashMap();
										accountMap = accountMapList.get(0);
										accountMap.put("prodInstId", prodInstId);
										accountMap.put("action", 2);
										accountDao.updateAccount(accountMap);
										acctobjList1.add(accountMap);
									}
							}
						} catch (Exception e) {
							// TODO: handle exception
							msg.setMessage("修改账户代表号码 取账户下最小号码");
							e.printStackTrace();
							throw e;
						}
						
						if (oldProdInstAcctRelList.size() > 0) {
							Map oldAcctMap = oldProdInstAcctRelList.get(0);
							servAcctMap.put("action", 2);
							servAcctMap.put("statusCd", 2);
							servAcctMap.put("expDate",
									servAcctMap.get("statusDate"));
							servAcctMap.put("prodInstAcctRelId",
									oldAcctMap.get("PROD_INST_ACCT_REL_ID"));
							servAcctMap.put("routeId",
									oldAcctMap.get("ROUTE_ID"));
							prodinstDao.updateProdInstAcctRel(servAcctMap);
							prodInstAcctRelobjList1.add(servAcctMap);
							
							//修改账务定制关系
							//ordProdInstAcctRelAttrList = ordBillDao.getOrdProdInstAcctRelAttr(servAcctMap);
							for(Map<String, Object> ordProdInstAcctRelAttrMap : ordProdInstAcctRelAttrList){
								prodinstDao.updateProdInstAcctRelAttr(ordProdInstAcctRelAttrMap);
								prodInstAcctRelobjList1.add(ordProdInstAcctRelAttrMap);
							}
						} else {
							msg.setMessage("未找到要修改的账务关系serc_acct_id:"
									+ servAcctMap.get("prodInstAcctRelId")
											.toString());
							return -1;
						}
					}

				}
			}

		} catch (Exception e) {
			msg.setMessage("处理账户ORD_PROD_INST_REL失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	public int doProdInstRel(Map itemMap, Map userMap, Message msg)
			throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

			List<Map<String, Object>> prodInstRelList = ordBillDao
					.selectOrdProdInstRel(itemMap);
/*			// long acctId =0;
			if (prodInstRelList.size() != 1) {
				msg.setMessage("取群产品实例接口表出错");
				return -1;
			}*/
			if (prodInstRelList.size() > 0) {
				for (int i = 0; i < prodInstRelList.size(); i++) {
					Map instRelMap = prodInstRelList.get(i);
					instRelMap.put("executetime", d.format(df.parse(instRelMap
							.get("updateDate").toString())));
					String operType = instRelMap.get("operType").toString();
					String stateDate = instRelMap.get("statusDate").toString();
					if (routeCustId == 0) {
						instRelMap.put("prodInstId",
								instRelMap.get("aProdInstId").toString());// 注意需查看取那个是产品实例id
						long prodInstRouteId = Long.parseLong(instRelMap.get(
								"aProdInstId").toString());
						routeCustId = getCustId(itemMap, prodInstRouteId, msg);
					}
					if (operType.equals("1000"))// 先判断，是否存在，不存在旧新增
					{
						List<Map<String, Object>> oldProdInstRelList = prodinstDao
								.getProdInstRelId(instRelMap);
						if (oldProdInstRelList.size() > 0) {
							Map relMap = oldProdInstRelList.get(0);
							instRelMap.put("action", 2);
							instRelMap.put("routeId", relMap.get("routeId"));
							prodinstDao.updateProdInstRel(instRelMap);
							// prodinstDao.updateProdInstSub(instRelMap);
						} else {
							instRelMap.put("routeId", routeCustId);
							instRelMap.put("action", 1);

							prodinstDao.insertProdInstRel(instRelMap);
						}
						prodInstRelobjList1.add(instRelMap);
					}

				}

			}

		} catch (Exception e) {
			msg.setMessage("处理账户ORD_PROD_INST_REL失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
/**
 * 处理用户群关系
 * @param itemMap
 * @param userMap
 * @param msg
 * @return
 * @throws Exception
 */
	public int doProdInstGroup(Map itemMap, Map userMap, Message msg)
			throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

			List<Map<String, Object>> prodInstRelList = ordBillDao
					.selectOrdProdInstRel(itemMap);
			for (Map<String, Object> map : prodInstRelList) {
				String operType = map.get("operType").toString();
				List<Map<String, Object>> prodInstRelGroupList = ordBillDao.selectOrdProdInstRelForGroup(map);
				if (prodInstRelGroupList.size() != 1) {
					msg.setMessage("取群产品实例接口表出错");
					return -1;
				}
				
				
				for (Map<String, Object> groupMap : prodInstRelGroupList) {
					String prodInstGroupId =  groupMap.get("aProdInstId").toString();
					groupMap.put("prodId", groupMap.get("aProdId"));
					List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(groupMap);
					if (tifProdList.size() == 1) {
						Map prodConMap = new HashMap();
						prodConMap = tifProdList.get(0);
						if (!prodConMap.get("prodType").equals("10D")) 
							return 1;
					}else {
						msg.setMessage("取对应的产品编码时出错");
						return -1;	
					}
					groupMap.put("GroupProdInstId", prodInstGroupId);
					List<Map<String, Object>> prodInstRelGroupList1 = prodinstDao.selectProdInstGroup(groupMap);
					if (prodInstRelGroupList1.size() != 1) {
						msg.setMessage("查找群号码时出错PROD_INST_ID=" + prodInstGroupId);
						return -1;
					}
					Map prodInstGroupMap = prodInstRelGroupList1.get(0);
					prodInstGroupMap.put("vpnCode", prodInstGroupId);
					prodInstGroupMap.put("vpnName", "VPN用户:" + prodInstGroupId);
					prodInstGroupMap.put("effDate", "1900/01/01");
					prodInstGroupMap.put("expDate", "3000/01/01");
					prodInstGroupMap.put("state", "10A");
					prodInstGroupMap.put("vpnType", "1");
					prodInstGroupMap.put("prodInstId", map.get("zProdInstId"));
					
					//取tif_VPN_GROUP 表
					List<Map<String, Object>> tifVpnGroupList = TifVpnGroupMapperDao.selectTifVpnGroup(prodInstGroupMap);
					if (tifVpnGroupList.size() == 0) {
						try {
							TifVpnGroupMapperDao.insertTifVpnGroup(prodInstGroupMap);
							tifVpnGroupList1.add(prodInstGroupMap);
						} catch (Exception e) {
							msg.setMessage("生成VPN用户群时出错");
							e.printStackTrace();
							throw e;
						}
						
					}
					//取prod_inst 中的acc_num
					long prodInstId = Long.parseLong(map.get("zProdInstId").toString());
					List<Map<String, Object>> prodInstList = prodinstDao.getProdInstOBJ(prodInstId);
					if (prodInstList.size() == 0) {
						msg.setMessage("查找成员号码时出错");
						return -1;
					}
					Map prodInstMap = prodInstList.get(0);
					Map vpnMemMap = new HashMap();
					vpnMemMap.put("memNumber",prodInstMap.get("accNum") );
					vpnMemMap.put("vpnCode", prodInstGroupId);
					vpnMemMap.put("memSeq", "1");
					vpnMemMap.put("offerId", "1");
					vpnMemMap.put("servId", map.get("zProdInstId"));
					vpnMemMap.put("effDate", new Date());
					vpnMemMap.put("expDate", statePublic.expDate);

					
					if ("1000".equals(operType)) {
						try {
							long lCount = 0;
							lCount = TifVpnGroupMapperDao.selectCntTifVpnMem(vpnMemMap);
							if (lCount == 0) {
								long seqVpnMemId = 	prodinstDao.getSeq("SEQ_VPN_MEM_ID");
								vpnMemMap.put("vpnMemId", seqVpnMemId);
								vpnMemMap.put("action", 1);
								TifVpnGroupMapperDao.insertTifVpnMem(vpnMemMap);	
								tifVpnMemList1.add(vpnMemMap);
							}
						} catch (Exception e) {
							msg.setMessage("插入VPN成员出错");
							e.printStackTrace();
							throw e;
						}
						
					}
					else if ("1100".equals(operType)) {
						List<Map<String, Object>>  tifVpnMemList = TifVpnGroupMapperDao.selectTifVpnMem(vpnMemMap);
						for (Map<String, Object> memMap : tifVpnMemList) {
							try {
								memMap.put("expDate", df.format(new Date()));
								memMap.put("action", 2);
								TifVpnGroupMapperDao.updateTifVpnMem(memMap);
								tifVpnMemList1.add(memMap);
							} catch (Exception e) {
								// TODO: handle exception
								msg.setMessage("失效VPN群成员时出错");
								e.printStackTrace();
								throw e;
							}
							
						}

					}
					
				}
				
			}
			// long acctId =0;
	/*		if (prodInstRelList.size() != 1) {
				msg.setMessage("取群产品实例接口表出错");
				return -1;
			}
			if (prodInstRelList.size() > 0) {
				for (int i = 0; i < prodInstRelList.size(); i++) {
					Map instRelMap = prodInstRelList.get(i);
					instRelMap.put("executetime", d.format(df.parse(instRelMap
							.get("updateDate").toString())));
					String operType = instRelMap.get("operType").toString();
					String stateDate = instRelMap.get("statusDate").toString();
					if (routeCustId == 0) {
						instRelMap.put("prodInstId",
								instRelMap.get("aProdInstId").toString());// 注意需查看取那个是产品实例id
						long prodInstRouteId = Long.parseLong(instRelMap.get(
								"aProdInstId").toString());
						routeCustId = getCustId(itemMap, prodInstRouteId, msg);
					}
					if (operType.equals("1000"))// 先判断，是否存在，不存在旧新增
					{
						List<Map<String, Object>> oldProdInstRelList = prodinstDao
								.getProdInstRelId(instRelMap);
						if (oldProdInstRelList.size() > 0) {
							Map relMap = oldProdInstRelList.get(0);
							instRelMap.put("action", 2);
							instRelMap.put("routeId", relMap.get("routeId"));
							prodinstDao.updateProdInstRel(instRelMap);
							// prodinstDao.updateProdInstSub(instRelMap);
						} else {
							instRelMap.put("routeId", routeCustId);
							instRelMap.put("action", 1);

							prodinstDao.insertProdInstRel(instRelMap);
						}
						prodInstRelobjList1.add(instRelMap);
					}

				}

			}*/

		} catch (Exception e) {
			msg.setMessage("处理账户ORD_PROD_INST_REL失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	public int doProdInstAttr(Map itemMap, Map userMap, Message msg)
			throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			String DateStr = d.format(date);
			List<Map<String, Object>> prodInstAttrList = ordBillDao
					.selectOrdProdInstAttr(itemMap);
			// long acctId =0;
			if (prodInstAttrList.size() > 0) {
				for (int i = 0; i < prodInstAttrList.size(); i++) {
					Map instAttrMap = prodInstAttrList.get(i);
					instAttrMap.put("executetime", d.format(df
							.parse(instAttrMap.get("updateDate").toString())));
					String operType = instAttrMap.get("operType").toString();
					String attrId = instAttrMap.get("attrId").toString();
					List<Map<String, Object>> tifProdAttrList = ordBillDao.selectTifProdAttribContrast(attrId);
					if (tifProdAttrList.size() == 0) {
						return 1;
					}
					// String stateDate =
					// instAttrMap.get("statusDate").toString();
					if (routeCustId == 0) {
						// acct_id = getAcctId(itemMap,instAttrMap,msg);
						long prodInstRouteId = Long.parseLong(instAttrMap.get(
								"prodInstId").toString());
						routeCustId = getCustId(itemMap, prodInstRouteId, msg);
					}
					long lFlag = 0;
					List<Map<String, Object>> ordPordInstAttrList = ordBillDao.selectOrdProdInstAttr1000(instAttrMap);
					if (ordPordInstAttrList.size() == 0) {
						ordPordInstAttrList = ordBillDao.selectOrdProdInstAttr2000(instAttrMap);
						if (ordPordInstAttrList.size() != 1) {
							msg.setMessage("附属产品实例在计费不存在");
							return -1;
						}
						//屏蔽掉计费不需要附属产品属性 begin
						Map ordProdInstMap = ordPordInstAttrList.get(0);
						Map tifProdSubMap = new HashMap();
						String prodSubType = "";
						List<Map<String,Object>>  ordProdInsSubrList= 
								ordBillDao.selectTifSubProdContrast(ordProdInstMap.get("prodId").toString());
						if (ordProdInsSubrList.size() == 0) {
							msg.setMessage("取对应的产品编码时出错1");	
							return -1;
						}
						tifProdSubMap = ordProdInsSubrList.get(0);
						prodSubType = tifProdSubMap.get("prodType").toString();
						if ("10X".equals(prodSubType)) {
							return 1;
						}//end
						lFlag = 1;
						
					}else if (ordPordInstAttrList.size() > 1) {
						msg.setMessage("主产品实例在计费不存在");
						return -1;
					}else {
						Map ordProdInstMap = ordPordInstAttrList.get(0);
						//组合和群组产品用户属性不落地
						List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(ordProdInstMap);
						if (tifProdList.size() == 1) {
							Map prodConMap = new HashMap();
							prodConMap = tifProdList.get(0);
							if (prodConMap.get("prodType").equals("10C")
									||prodConMap.get("prodType").equals("10D")) 
								return 1;
						}else {
							msg.setMessage("取对应的产品编码时出错");
							return -1;	
						}
					}
					//处理主产皮实例属性 begin
					if (lFlag == 0) {
						if (operType.equals("1000"))// 先判断，是否存在，不存在旧新增
						{
							List<Map<String, Object>> oldProdAttrList = prodinstDao
									.getProdInstAttrId(instAttrMap);
							if (oldProdAttrList.size() == 0) {
								try {
									instAttrMap.put("routeId", routeCustId);
									instAttrMap.put("action", 1);
									instAttrMap.put("effDate",
											instAttrMap.get("statusDate"));
									instAttrMap.put("expDate", statePublic.expDate);
									prodinstDao.insertProdInstAttr(instAttrMap);
									prodInstAttrobjList1.add(instAttrMap);
								} catch (Exception e) {
									msg.setMessage("增加主产品实例属性时出错");
									e.printStackTrace();
									throw e;
								}
								
							}
							/*if (oldProdAttrList.size() > 0) {
								Map attrMap = oldProdAttrList.get(0);

								instAttrMap.put("expDate",
										instAttrMap.get("statusDate"));
								instAttrMap.put("action", 2);
								instAttrMap.put("routeId",
										attrMap.get("routeId"));
								// 从计费库获取相应主键,并存入map中
								instAttrMap.put("prodInstAttrId",
										selectProdInstAttrId(instAttrMap));
								prodinstDao.updateProdInstAttr(instAttrMap);
							} else {
								instAttrMap.put("routeId", routeCustId);
								instAttrMap.put("action", 1);
								instAttrMap.put("effDate",
										instAttrMap.get("statusDate"));
								instAttrMap.put("expDate", statePublic.expDate);
								prodinstDao.insertProdInstAttr(instAttrMap);
							}
							prodInstAttrobjList1.add(instAttrMap);*/
						}else if (operType.equals("1100")) {
							List<Map<String, Object>> oldProdAttrList = prodinstDao
									.getProdInstAttrId(instAttrMap);
							if (oldProdAttrList.size() == 0) {
								return 1;
							}
							try {
								Map attrMap = oldProdAttrList.get(0);
								instAttrMap.put("action", 2);
								instAttrMap.put("routeId",attrMap.get("routeId"));
								instAttrMap.put("statusCd", 1100);
								instAttrMap.put("expDate", instAttrMap.get("statusDate"));
								prodinstDao.updateProdInstAttr(instAttrMap);
							} catch (Exception e) {
								// TODO: handle exception
								msg.setMessage("修改主产品实例属性时出错");
							}
							
						}
						
					}//end
					
					if (lFlag == 1) {//处理附属产品实例属性 begin
						if (operType.equals("1000"))// 先判断，是否存在，不存在旧新增
						{
							List<Map<String, Object>> oldProdAttrList = prodinstDao
									.getProdInstAttrIdForSub(instAttrMap);
							if (oldProdAttrList.size() == 0) {
								instAttrMap.put("routeId", routeCustId);
								instAttrMap.put("action", 1);
								instAttrMap.put("effDate",
										instAttrMap.get("statusDate"));
								instAttrMap.put("expDate", statePublic.expDate);
								prodinstDao.insertProdInstAttrSub(instAttrMap);
								prodInstAttrSubobjList1.add(instAttrMap);
							}
							/*if (oldProdAttrList.size() > 0) {
								Map attrMap = oldProdAttrList.get(0);

								instAttrMap.put("expDate",
										instAttrMap.get("statusDate"));
								instAttrMap.put("action", 2);
								instAttrMap.put("routeId",
										attrMap.get("routeId"));
								// 从计费库获取相应主键,并存入map中
								instAttrMap.put("prodInstAttrId",
										selectProdInstAttrId(instAttrMap));
								prodinstDao.updateProdInstAttr(instAttrMap);
							} else {
								instAttrMap.put("routeId", routeCustId);
								instAttrMap.put("action", 1);
								instAttrMap.put("effDate",
										instAttrMap.get("statusDate"));
								instAttrMap.put("expDate", statePublic.expDate);
								prodinstDao.insertProdInstAttrSub(instAttrMap);
							}
							prodInstAttrSubobjList1.add(instAttrMap);*/
						}
						else if (operType.equals("1100")) {
							List<Map<String, Object>> oldProdAttrList = prodinstDao
									.getProdInstAttrIdForSub(instAttrMap);
							if (oldProdAttrList.size() > 0) {
								Map attrMap = oldProdAttrList.get(0);
								instAttrMap.put("expDate",
										instAttrMap.get("statusDate"));
								instAttrMap.put("action", 2);
								instAttrMap.put("routeId",
										attrMap.get("routeId"));
								// 从计费库获取相应主键,并存入map中
								instAttrMap.put("prodInstAttrId",
										selectProdInstAttrId(instAttrMap));
								prodinstDao.updateProdInstAttr(instAttrMap);
							}
						}
					}//end
					

				}

			}

		} catch (Exception e) {
			msg.setMessage("处理账户ORD_PROD_INST_attr失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}

	public int doProdInstSub(Map itemMap, Map userMap, Message msg)
			throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

			List<Map<String, Object>> prodInstSubList = ordBillDao
					.selectOrdProdInstSub(itemMap);
			// long acctId =0;
			if (prodInstSubList.size() > 0) {
				for (int i = 0; i < prodInstSubList.size(); i++) {
					HashMap instSubMap = (HashMap) prodInstSubList.get(i);

					HashMap tmpMap = new HashMap();
					tmpMap = (HashMap) instSubMap.clone();

					tmpMap.put("prodInstId", instSubMap.get("accProdInstId"));
					instSubMap.put("executetime", d.format(df.parse(instSubMap
							.get("updateDate").toString())));
					String operType = instSubMap.get("operType").toString();
					if (routeCustId == 0) {
						// acct_id = getAcctId(itemMap,tmpMap,msg);
						routeCustId = Long.parseLong(instSubMap.get(
								"ownerCustId").toString());
					}
					if (operType.equals("1000"))// 新增
					{
						List<Map<String, Object>> oldProdInstSubList = prodinstDao
								.getProdInstSubId(instSubMap);
						if (oldProdInstSubList.size() > 0) {
							Map msubMap = oldProdInstSubList.get(0);
							instSubMap.put("action", 2);
							instSubMap.put("routeId", msubMap.get("routeId"));
                            //从计费库获取相应主键,并存入map中
                            instSubMap.put("prodInstId",selectProdInstId(instSubMap));
                            prodinstDao.updateProdInstSub(instSubMap);

						} else {
							// 获取序列
							String prod_id_str = instSubMap.get("prodId")
									.toString();
							if (prod_id_str.length() > 9) {
								instSubMap.put("prodId", 20020022);
							}
							instSubMap.put("action", 1);
							instSubMap.put("routeId", routeCustId);

							prodinstDao.insertProdInstSub(instSubMap);
							// add by 新增attr_id =167 属性
							Map attr167 = new HashMap();
							long prodInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); // 统一序列
							attr167.put("prodInstAttrId", prodInstAttrId); // 需改为调用序列注意修改
							attr167.put("parProdInstAttrId", 1);
							attr167.put("prodInstId",
									instSubMap.get("accProdInstId"));
							attr167.put("attrId", 167);
							attr167.put("attrValueId", 1);
							attr167.put("statusCd", 1000);
							attr167.put("routeId", routeCustId);
							attr167.put("action", 1);
							attr167.put("effDate", instSubMap.get("statusDate"));
							attr167.put("expDate", statePublic.expDate);
							attr167.put("updateDate",
									instSubMap.get("updateDate"));
							attr167.put(
									"executetime",
									d.format(df.parse(instSubMap.get(
											"updateDate").toString())));
							prodinstDao.insertProdInstAttrSub(attr167);
							prodInstAttrSubobjList1.add(attr167);
							
						}
						prodInstSubobjList1.add(instSubMap);
					}

				}

			}

		} catch (Exception e) {
			msg.setMessage("处理账户ORD_PROD_INST_SUB失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}

	public int deleteProdInst(Map itemMap, Map userMap, Message msg)
			throws Exception {
		
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

			Map deleteMap = itemMap;
			List<Map<String, Object>> prodInstList = ordBillDao
					.selectOrdProdInst(deleteMap);
			Map prodInstMap = new HashMap();
			//add by wangbaoqiang begin 
			if (prodInstList.size() > 1) {
				msg.setMessage("取用户接口表数据时出错:记录有多条");
				return -1;
			}

			if (prodInstList.size() > 0) {
				prodInstMap = prodInstList.get(0);
				String statusCd = prodInstMap.get("statusCd").toString();
				long routeId = (Long) prodInstMap.get("ownerCustId");
					//add by wangbaoqiang begin
					if (!"110001".equals(statusCd) 
							&&!"110002".equals(statusCd) ) {
						
						msg.setMessage("拆机工单但状态不是拆机!");
						return -1;
					}
					//判断是否为组合产品
					try {
						List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(prodInstMap);
						if (tifProdList.size() == 1) {
							Map prodConMap = new HashMap();
							prodConMap = tifProdList.get(0);
							if (prodConMap.get("prodType").equals("10C")) {
								List<Map<String, Object>> prodInstGroupList = prodinstDao.selectProdInstGroup(prodInstMap);
								if (prodInstGroupList.size() == 1) {
									prodinstDao.updateProdInstGroup(prodInstMap);
									prodInstGroupobjList1.add(prodInstMap);	
									return 1;
								}

							}
						}
						else {
							msg.setMessage("取对应的产品编码时出错");
							return -1;
						}
						
					} catch (Exception e) {
						msg.setMessage("失效用户群时出错");
						e.printStackTrace();
						throw e;
					}
					
					
/*					Long prodInstCount = prodinstDao.getForCountByProdInstId(prodInstMap);
					if(prodInstCount == 0){
						msg.setMessage("用户"+prodInstMap.get("prodInstId")+"）在计费不存在,不能拆机!");
						return -1;
					}*/
					
					//计费侧如果是拆机状态的话不处理  begin
					long prodInstId = Long.parseLong(prodInstMap.get("prodInstId").toString());
					List<Map<String, Object>> prodInstListTmp   = prodinstDao.getProdInstOBJ(prodInstId);
					//add end;
					if (prodInstListTmp.size() == 0) {
						msg.setMessage("用户"+prodInstMap.get("prodInstId")+"）在计费不存在,不能拆机!");
						return -1;
					}
					Map prodInstMapTemp = prodInstListTmp.get(0);
					String prodInstStatus = prodInstMapTemp.get("statusCd").toString();
					String prodInstAccNum = prodInstMapTemp.get("accNum").toString();
					if ("110001".equals(prodInstStatus)
							|| "110002".equals(prodInstStatus)) {
						return 1;
					}// 计费侧如果是拆机状态的话不处理 end
					
					prodInstMap.put("action", 2);
					prodInstMap.put("routeId", routeId);
					String stateDate = prodInstMap.get("statusDate").toString();
					prodInstMap.put("executetime", d.format(df.parse(prodInstMap
							.get("updateDate").toString())));
                //更新prodInst表之前的备份操作
                if(hisService.selectProdInst(prodInstMap,userMap,msg)<=0){
                    msg.setMessage("数据备份失败：prodInstId:"+prodInstMap.get("prodInstId"));
                    return -1;
                }
					prodinstDao.updateProdInst(prodInstMap);
					prodInstobjList1.add(prodInstMap);
				//add by wangbaoqiang 按时间排序 begin
				try {
					List<Map<String, Object>> getProdInstStateExList = new ArrayList<Map<String,Object>>();
/*					getProdInstStateExList = prodinstDao.getProdInstState(prodInstMap);
					Collections.sort(getProdInstStateExList, new Comparator<Map<String, Object>>(){
						 
						   public int compare(Map<String, Object> o1, Map<String, Object> o2) {
						    String name1 =(String)o1.get("EXP_DATE");//name1是从你list里面拿出来的一个
						    String name2= (String)o2.get("EXP_DATE"); //name1是从你list里面拿出来的第二个name    
						    return name2.compareTo(name1);  
					   }
						   
						  });*/
					Map<String, Object> prodInstStateExtFromStateMap = prodinstDao.getProdInstStateExtFromStateExt(prodInstMap).get(0);
					if(prodInstStateExtFromStateMap.size()<=0){
						msg.setMessage("获取老的主产品状态时出错");
						return -1;
					}
					prodInstStateExtFromStateMap.put("statusCd", 1100);
					prodInstStateExtFromStateMap.put("expDate", prodInstMap.get("statusDate"));
					prodInstStateExtFromStateMap.put("action", 2);
					prodinstDao.updateProdInstStateExt(prodInstStateExtFromStateMap);
					prodInstStateobjList1.add(prodInstStateExtFromStateMap);
				}catch (Exception e) {
					msg.setMessage("修改prod_inst_state_ext失败");
					e.printStackTrace();
					throw e;
				}
				// 增加用户状态
				try {
					Long  prodInstStateId = prodinstDao.getSeq("SEQ_PROD_INST_STATE_ID");//统一序列
					prodInstMap.put("prodInstStateId", prodInstStateId);
					prodInstMap.put("state", prodInstMap.get("statusCd"));
					prodInstMap.put("stopType", "0");
					prodInstMap.put("statusCd", "1000");
					prodInstMap.put("action", 1);
					prodInstMap.put("expDate", statePublic.expDate);
					prodInstMap.put("effDate",prodInstMap.get("statusDate") );
					prodinstDao.insertProdInstStateExt(prodInstMap);			
					prodInstStateobjList1.add(prodInstMap);
				} catch (Exception e) {
					msg.setMessage("增加主产品状态时出错");
					e.printStackTrace();
					throw e;
				}//add end;
				
/*				try {
					List<Map<String, Object>> prodInstStateExtList = new ArrayList<Map<String,Object>>();
					prodInstStateExtList = prodinstDao.getProdInstStateExt(prodInstMap);
					for(Map prodInstStateExtMap : prodInstStateExtList ){
						prodInstStateExtMap.put("routeId", routeId);
						prodInstStateExtMap.put("expDate", prodInstMap.get("statusDate"));
						prodinstDao.updateProdInstStateExt(prodInstStateExtMap);
						prodInstStateobjList1.add(prodInstStateExtMap);
					}
				} catch (Exception e) {
					msg.setMessage("获取老的主产品状态时出错");
					e.printStackTrace();
					throw e;
				}
				
				try {
					Long  prodInstStateId = prodinstDao.getSequeId();//统一序列
					prodInstMap.put("prodInstStateId", prodInstStateId);
					prodInstMap.put("stopType", "0");
					prodInstMap.put("statusCd", "1000");
					prodinstDao.insertProdInstStateExt(prodInstMap);			
					prodInstStateobjList1.add(prodInstMap);
				} catch (Exception e) {
					msg.setMessage("增加主产品状态时出错");
					e.printStackTrace();
					throw e;
				}
				*/
				long  prodId = Long.parseLong(prodInstMap.get("prodId").toString());
				String  trunkNum;
				String	accNum = prodInstMap.get("accNum").toString();
				String  areaCode;
				String prodType;
				long tCount;
				long trunkId;
				try {
					if (prodId == 280000035 && accNum.indexOf("T")>= 0) {
						
						Map TrunkMap = new HashMap();
						TrunkMap.put("expDate", prodInstMap.get("statusDate"));
						TrunkMap.put("accNbr", accNum);
						tCount = bTrunkBillingMapperDao.countTrunkBilling(prodInstMap);
						if (tCount > 0) {
							bTrunkBillingMapperDao.updateTrunkBilling(TrunkMap);
						}
					}
					
				} catch (Exception e) {
					msg.setMessage("失效中继号码失败");
					e.printStackTrace();
					throw e;
				}

				//失效IMSI及号码
				try {
					List<Map<String, Object>> prodInstAccNumList = new ArrayList<Map<String,Object>>();
					prodInstAccNumList = prodinstDao.getProdInstAccNum(prodInstMap);
					for(Map prodInstAccNumMap : prodInstAccNumList){
						prodInstAccNumMap.put("expDate", prodInstMap.get("statusDate"));
						prodInstAccNumMap.put("routeId", routeId);
						prodInstAccNumMap.put("statusCd", "1100");
						prodinstDao.updateProdInstAccNum(prodInstAccNumMap);
						prodInstAccNumobjList1.add(prodInstAccNumMap);
					}
				} catch (Exception e) {
					msg.setMessage("失效IMSI及号码出错");
					e.printStackTrace();
					throw e;
 				}
				//代码：失效跨域G总机
				//代码：备份主产品实例（PROD_INST==》PROD_INST_HIS）
				
				//修改主产品实例
/*				try {
					String statusDate =d.format(new Date()); 
					prodInstMap.put("statusDate", statusDate);
					prodInstMap.put("routeId", routeId);
					prodInstMap.put("statusCd", statusCd);
					prodinstDao.updateProdInst(prodInstMap);
					prodInstobjList1.add(prodInstMap);
				} catch (Exception e) {
					msg.setMessage("修改主产品实例");
					e.printStackTrace();
					throw e;
				}*/
				
				//账户代表号码
				//当拆机号码是代表号码时，修改账户代表号
			
/*			List<Map<String, Object>> accountList = new ArrayList<Map<String, Object>>();
			//accountList = ordBillDao.getAccount(prodInstMap);
			accountList = accountDao.getAccoutFromProdInstId(prodInstMap);
			for (Map map : accountList) {
				long newAcctId = (Long) map.get("acctId");
				Map acctMap = map;
				acctMap.put("newAcctId", newAcctId);
				acctMap.put("action", 2);
				long minProdInstId = prodinstDao
						.getMinProdInstIdFromAcctRel(prodInstMap);
				if (minProdInstId < 0) {
					msg.setMessage("取此帐户下最小的号码时出错(限制副卡)");
					return -1;
				} else if (minProdInstId == 0) {
					minProdInstId = -1;
				}if (minProdInstId != -1) {
					acctMap.put("minProdInstId", minProdInstId);
					try {
						prodinstDao.updateAccount(acctMap);
						acctMap.put("prodInstId", minProdInstId);
						acctobjList1.add(acctMap);
					} catch (Exception e) {
						msg.setMessage("更新帐户的代表用户出错");
						e.printStackTrace();
						throw e;
					}
				}
				

			}*/
					
				//代码：用户级分账付费 ，缺失表（A_SERV_ACCT_EX）
				//代码：账户级分账付费，缺失表（A_SERV_ACCT_EX）
				//代码：失效云堤业务，缺失表(SERV_EXT)
				//代码：格式八写接口表
				
			//失效VPN成员 add by wangbaoqiang
		    Map vpnMemMap = new HashMap();
		    vpnMemMap.put("accNum", prodInstAccNum);	
		    vpnMemMap.put("statusDate", prodInstMap.get("statusDate"));
			List<Map<String, Object>> tifVpnGroupList = TifVpnGroupMapperDao.selectTifVpnMemId(vpnMemMap);
				if (tifVpnGroupList.size() == 1) {
					Map map = tifVpnGroupList.get(0);
					map.put("expDate",prodInstMap.get("statusDate") );
					if (TifVpnGroupMapperDao.updateTifVpnMem(map) < 1) {
						msg.setMessage("失效IVPN出错");
						return -1;
					}
					map.put("action", 2);
					tifVpnMemList1.add(map);
				}
			 //判断拆机用户是否是代表用户 add by wangbaoqiang
			List<Map<String, Object>> prodInstAcctIdList = prodinstDao.getProdInstAcctId(prodInstMap);
			if (prodInstAcctIdList.size() != 1) {
				msg.setMessage("取用户账务关系时出错");
				return -1;
			}
			Map prodInstAcctIdMap = prodInstAcctIdList.get(0);
			long acctId = Long.parseLong(prodInstAcctIdMap.get("acctId").toString());
			prodInstMap.put("acctId", acctId);
			long cnt = accountDao.getCntAccoutFromProdInstId(prodInstMap);
			if (cnt == 1) {
				try {
					List<Map<String, Object>> accountMapList = accountDao
							.getAccoutForAccNum(acctId);
						if (accountMapList.size() > 0) {
							Map<String, Object> prodInstRelMap = prodinstDao
									.getProdInstIdFromAcctRel(prodInstMap);
							//如果该账户下有多个在用用户则取最小代表号码，如果没有保持不变
							long prodInstIdTemp = prodinstDao.getMinProdInstId(prodInstRelMap);
							if (prodInstIdTemp != -1) {
								Map accountMap = new HashMap();
								accountMap = accountMapList.get(0);
								accountMap.put("prodInstId", prodInstIdTemp);
								accountMap.put("action", 2);
								accountDao.updateAccount(accountMap);
								acctobjList1.add(accountMap);
							}
					}
				} catch (Exception e) {
					// TODO: handle exception
					msg.setMessage("取帐户最早的号码时出错");
					e.printStackTrace();
					throw e;
				}
			}
			}

		return 1;
	}

	public int updateProdInst(Map itemMap, Map userMap, Message msg,
			String service_offer_id) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

			List<Map<String, Object>> prodInstList = ordBillDao
					.selectOrdProdInst(itemMap);
			if(prodInstList.size()==0){
				itemMap.put("oper_type","1300");
				prodInstList = ordBillDao.selectOrdProdInst1300(itemMap);
				if (prodInstList.size()>1) {
					msg.setMessage("取用户接口表数据时出错：记录有多条");
					return -1;
				}
				else if (prodInstList.size() == 0) {
					return 1;
				}
			}
			else if (prodInstList.size() > 1) {
				msg.setMessage("取用户接口表数据时出错：记录有多条");
				return -1;
			}
			//add by wangbaoqiang  保持状态的只处理补卡动作   begin
				if (!"4040600003".equals(service_offer_id) 
						&&!"4040600000".equals(service_offer_id)
						&&!"4060201002".equals(service_offer_id)
						&&!"4070100002".equals(service_offer_id)
						&&"1300".equals(itemMap.get("oper_type"))) {
					return 1;
				}//end
			String oldPaymode = "";
			String oldAccNum = "";
			String oldState = "";
			String oldRegionId = "";

			String paymode = "";
			String accNum = "";
			String state = "";
			String RegionId = "";
			long prodInstId = 0L;
			String oldAcctId = "";

			String stateDate = "";
			//=======================
			String cdmaAmsi = "";
			
			String oldStopType="";
			String oldStateCd="";
			String oldAccNumId = "";
			String oldAccImsi = "";
			String  areaCode = "";
			String prodType = "";
			
			String stopType="";
			String statusCd="";
			String realNameEffDate1="";
			String oldStopTypeEx="";
			String realNameEffDate2="";
			Map prodInstMap = new HashMap();
			if (prodInstList.size() > 0) {
				prodInstMap = prodInstList.get(0);
				
				if("".equals(prodInstMap.get("accNum"))||prodInstMap.get("accNum") == null){
					msg.setMessage("接入号不能为null");
					return -1;
				}
				if("".equals(prodInstMap.get("paymentModeCd"))||prodInstMap.get("paymentModeCd")==null){
					msg.setMessage("付费方式不能为NULL");
					return -1;
				}
				
				prodInstMap.put("effDate", prodInstMap.get("statusDate"));
				stateDate = prodInstMap.get("statusDate").toString();// 用来同一更新时间
				prodInstMap.put("expDate", statePublic.expDate);
				prodInstMap.put("executetime", d.format(df.parse(prodInstMap
						.get("updateDate").toString())));

				prodInstId = Long.parseLong(prodInstMap.get("prodInstId")
						.toString());
				paymode = prodInstMap.get("paymentModeCd").toString();
				accNum = prodInstMap.get("accNum").toString();
				state = prodInstMap.get("statusCd").toString();
				RegionId = prodInstMap.get("regionId").toString();
				cdmaAmsi = (String) prodInstMap.get("cdmaImsi");
				//add by wangbaoqiang 增加固话前面加区号  begin
				//判断是否为组合产品
				try {
					List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(prodInstMap);
					if (tifProdList.size() == 1) {
						Map prodConMap = new HashMap();
						prodConMap = tifProdList.get(0);
						if (prodConMap.get("prodType").equals("10C")
								||prodConMap.get("prodType").equals("10D")) {
							return 1;
						}
					}
					else {
						msg.setMessage("取对应的产品编码时出错");
						return -1;
					}
					
				} catch (Exception e) {
					msg.setMessage("取对应的产品编码时出错");
					e.printStackTrace();
					throw e;
				}
				long  prodId = Long.parseLong(prodInstMap.get("prodId").toString());
				List<Map<String, Object>> regionList = ordBillDao.selectTifOrgContrast(prodInstMap);
				if (regionList.size() > 0) {
					Map regionMap = new HashMap();
					regionMap = regionList.get(0);
					areaCode = regionMap.get("areaCode").toString();
				}
				else {
					msg.setMessage("取对应的区号时出错");
					return -1;
				}
				if (prodId == 2 || prodId == 37 || prodId == 41 || prodId == 9001
						|| prodId == 9003 || prodId == 280000007
						|| prodId == 280000037 || prodId == 280000038
						|| prodId == 280000059 || prodId == 280000088) {
					
					accNum = areaCode + accNum;

				}//add end 
				// long acctId = getAcctId(itemMap,prodInstMap,msg);

				// 查找旧的 prod_inst getProdInstOBJ
				List<Map<String, Object>> oldProdInstList = prodinstDao
						.getProdInstOBJ(prodInstId);
				if (oldProdInstList.size() > 0) {
					Map oldProdInst = oldProdInstList.get(0);
					oldPaymode = oldProdInst.get("paymentModeCd").toString();
					oldAccNum = oldProdInst.get("accNum").toString();
					oldState = oldProdInst.get("statusCd").toString();
					oldRegionId = oldProdInst.get("regionId").toString();
					oldAcctId = oldProdInst.get("routeId").toString();
										
					prodInstMap.put("routeId", oldAcctId);
					//oldStopType = oldProdInst.get("STOP_TYPE").toString();
					//oldStateCd = oldProdInst.get("STATE").toString();

					if("110001".equals(oldProdInst.get("statusCd"))||"110002".equals(oldProdInst.get("statusCd"))){
						msg.setMessage("已经拆机的用户修改工单不处理");
						return -1;
					}
					//add by wangbaoqiang 按时间排序 begin
					try {
						/*List<Map<String, Object>> getProdInstStateExList = new ArrayList<Map<String,Object>>();
						getProdInstStateExList = prodinstDao.getProdInstState(oldProdInst);
						Collections.sort(getProdInstStateExList, new Comparator<Map<String, Object>>(){
							 
							   public int compare(Map<String, Object> o1, Map<String, Object> o2) {
							    String name1 =(String)o1.get("EXP_DATE");//name1是从你list里面拿出来的一个
							    String name2= (String)o2.get("EXP_DATE"); //name1是从你list里面拿出来的第二个name    
							    return name2.compareTo(name1);  
						   }
							   
							  });*/
						Map<String, Object> prodInstStateExtFromStateMap = prodinstDao.getProdInstStateExtFromStateExt(oldProdInst).get(0);
						if(prodInstStateExtFromStateMap.size()<=0){
							msg.setMessage("获取老的主产品状态时出错");
							return -1;
						}
						oldStopType = prodInstStateExtFromStateMap.get("stopType").toString();
						oldStateCd = prodInstStateExtFromStateMap.get("state").toString();
					} catch (Exception e) {
						msg.setMessage("获取老的主产品状态时出错");
						e.printStackTrace();
						throw e;
					}
					//代码：非CRMA手机加区号（缺TIF_CDMA_PROD_CONTRAST表）
					
					//检查设备号码是否已经存在，不允许同一号码2次。
					if(prodinstDao.getForAccNumCount(prodInstMap)>0){
						msg.setMessage("此设备号码已经被占用:accNum="+prodInstMap.get("accNum"));
						return -1;
					}
					
					//处理计费模式改变
					//System.out.println("prodInstMap:"+prodInstMap.get("paymentModeCd"));
					//System.out.println("oldProdInst:"+oldProdInst.get("PAYMENT_MODE_CD"));
					if(  !(prodInstMap.get("paymentModeCd").equals(oldProdInst.get("PAYMENT_MODE_CD"))) 
							&&("4041300000").equals(service_offer_id)){
						List<Map<String, Object>> prodInstPaymodeList = new ArrayList<Map<String,Object>>();
						prodInstPaymodeList = prodinstDao.getProdInstPaymode(prodInstMap);
						if(prodInstPaymodeList.size()<=0){
							msg.setMessage("取主产品老付费方式时出错");
							return -1;
						}
						Map<String, Object> prodInstPaymodeMap = new HashMap<String, Object>();
						for(int i=0;i<prodInstPaymodeList.size();i++){
							prodInstPaymodeMap.put("routeId", oldAcctId);
							prodInstPaymodeMap.put("statusCd", "1100");
							prodInstPaymodeMap.put("paymodeId", prodInstPaymodeList.get(i));
							prodInstPaymodeMap.put("expDate", stateDate);
                            prodInstPaymodeMap.put("statusDate",stateDate);
                            prodInstPaymodeMap.put("prodInstId",prodInstMap.get("prodInstId"));
                            //从计费库获取相应主键,并存入map中
                            prodInstPaymodeMap.put("paymodeId",selectPaymodeId(prodInstPaymodeMap));
							if(prodinstDao.updateProdInstPaymode(prodInstPaymodeMap)<=0){
								msg.setMessage("更新计费模式失败");
								return -1;
							}
							prodInstPaymodeobjList1.add(prodInstPaymodeMap);
						}
						
						
						long paymodeId = prodinstDao.getSeq("SEQ_PAYMODE_ID");  //统一序列
						prodInstMap.put("paymodeId", paymodeId);
						prodInstMap.put("statusCd", "1000");
						if(prodinstDao.insertProdInstPyamode(prodInstMap)<=0){
							msg.setMessage("增加主产品付费方式时出错");
							return -1;
						}
						prodInstPaymodeobjList1.add(prodInstMap);
						
						//代码：给HB计费预后互转接口表送数据（缺少表B_TOABM_USER_INFO_OPLOG）
						//代码：在后付费转预付费时，帐户的信用度调整为0 （缺少表ACCT_CREDIT）
						//代码：后付费转预付费同时停机（缺少表ACCT.A_REAL_TIME_SERVICE）
						//代码：OCS用户转HB用户（缺少表CCM_USER_ORDER）
						//代码：HB用户转OCS用户（缺少表CCM_USER_ORDER）
					}
					// add by wangbaoqiang 处理用户激活
					if ("4041600000".equals(service_offer_id) && ("140000".equals(oldState)
							||"140001".equals(oldState)
							||"140002".equals(oldState))) {
						
					}// 待处理
					
					//过客户才修改客户资料
					if(prodInstMap.get("ownerCustId").equals(oldProdInst.get("OWNER_CUST_ID"))
							&&"4040400000".equals(service_offer_id)){
						long customerCount = customerMapperDao.getForCountByOwnerCustId(prodInstMap);
						if(customerCount<=0){
							msg.setMessage("产品实例对应的产权客户在计费不存在!OWNER_CUST_ID ="+prodInstMap.get("ownerCustId"));
							return -1;
						}
						
						if(prodInstMap.get("ownerCustId").equals(oldProdInst.get("OWNER_CUST_ID"))){
							List<Long> offerInstIdList = offerinstDao.getOfferInstIdList(prodInstMap);
							if(offerInstIdList.size()<=0){
								msg.setMessage("获取产品实例ID失败");
								return -1;
							}
							for(long offerInstId :offerInstIdList){
								prodInstMap.put("offerInstId", offerInstId);
								prodInstMap.put("routeId", oldAcctId);
								if(offerinstDao.updateOfferInstByOfferInstId(prodInstMap)<=0){
									msg.setMessage("更新产品实例失败");
									return -1;
								}
								offerInstobjList1.add(prodInstMap);
							}
						}
					}
					
					
						 /*stopType="";
						 statusCd="";
						 realNameEffDate1="";
						 oldStopTypeEx="";
						 realNameEffDate2="";*/
						//代码：互联网卡实名（缺失表A_REAL_NAME_ACTIVATION）
						/*if("140003".equals(oldState)&&"100000".equals(state)){
							stopType="0";
							statusCd="140002";
							//代码：用激活程序来处理状态（缺失表A_REAL_NAME_ACTIVATION）
							
						}
						else if("140000".equals(oldState)&&"140002".equals(state)){
							//普通活卡实名
							stopType="0";
							statusCd="140002";
						}
						//modify by wnagbaoqiang 非实名制复机处理
						else if("4070201005".equals(service_offer_id)){
							//实名制复机
							realNameEffDate1=null;
							Map<String, Object> prodInstStateExtMap = prodinstDao.selectProdInstStateExt(prodInstMap);
							if(prodInstStateExtMap==null||prodInstStateExtMap.isEmpty()){
								msg.setMessage("实名制复机获取未知名状态信息出错");
								return -1;
							}
							oldStopTypeEx = (String) prodInstStateExtMap.get("STOP_TYPE");
							//realNameEffDate1 = (String) prodInstStateExtMap.get("EFF_DATE");
							//判断未实名前是否做过停机保号业务
							realNameEffDate2=null;
							prodInstMap.put("realNameEffDate1", prodInstStateExtMap.get("EFF_DATE"));
							try {
								realNameEffDate2 = prodinstDao.getEffDatefromStateExt(prodInstMap);
							} catch (Exception e) {
								msg.setMessage("实名复机获取停机保号生效时间出错");
								e.printStackTrace();
								throw e;
							}
							//用户做过停机保号业务
							if(realNameEffDate2!=null){
								//代码：停机保号和实名复机在同月，需要续补状态
							}else{
								//没有停机保号业务
							}
							if (!"3".equals(oldPaymode)) {
								//long prodInstAttrId = selectProdInstAttrId(prodInstMap);
								List<Map<String, Object>> prodInstAttrMaps = prodinstDao.selectProdInstAttrMap(prodInstMap);
								if (prodInstAttrMaps.size() == 1) {
									Map map = new HashMap();
									map = prodInstAttrMaps.get(0);
									map.put("statusCd", 1100);
									map.put("attrId", 1998);
									map.put("expDate", prodInstMap.get("statusDate"));
									map.put("action", 2);
									prodinstDao.updateProdInstAttr(map);
									prodInstAttrobjList1.add(map);
								}
								else {
									
									return 1;
								}

							}
							else {
								Map map = new HashMap();
								map.put("prodId", 999000020);
								map.put("accProdInstId", prodInstMap.get("accProdInstId"));
								List<Map<String, Object>> prodInstSubmMaps = prodinstDao.selectProdInstSubMap(map);
								if (prodInstSubmMaps.size() > 0) {
									for (int i = 0; i < prodInstSubmMaps.size(); i++) {
										Map mapTemp = new HashMap();
										mapTemp = prodInstSubmMaps.get(i);
										mapTemp.put("statusCd", 1100);
										mapTemp.put("updateDate", sdf.format(new Date()));
										mapTemp.put("stopRentDate", prodInstMap.get("statusDate"));
										mapTemp.put("action", 2);
										prodinstDao.updateProdInstSub(mapTemp);
										prodInstSubobjList1.add(mapTemp);
										mapTemp.put("attrId", 35214005);
										
										List<Map<String, Object>> prodInstAttrSubAttrMaps = prodinstDao.selectProdInstAttrSubMap(mapTemp);
										if (prodInstAttrSubAttrMaps.size() > 0) {
											for (Map<String, Object> prodInstAttrSubAttrMap : prodInstAttrSubAttrMaps) {
												prodInstAttrSubAttrMap.put("expDate", prodInstMap.get("statusDate"));
												prodInstAttrSubAttrMap.put("statusCd", prodInstMap.get("1100"));
												prodInstAttrSubAttrMap.put("expDate", sdf.format(new Date()));
												prodInstAttrSubAttrMap.put("action", 2);
												prodinstDao.updateProdInstAttrSub(prodInstAttrSubAttrMap);
												prodInstSubobjList1.add(prodInstAttrSubAttrMap);
											}
										}
										
										
										
									}
								}
								
							}
							
						}else if("4070100002".equals(service_offer_id)
								||"4070100001".equals(service_offer_id)
								||"4070200000".equals(service_offer_id)
								||"4041600000".equals(service_offer_id)){
							//
							stopType="0";
							statusCd="100000";
						}else if("4060100002".equals(service_offer_id)){
							//停机保号
							stopType="120000";
							statusCd="120000";
						}else if("4060200000".equals(service_offer_id)){
							//用户违章停机
							stopType="150000";
							statusCd="120000";
						}else if("4060100001".equals(service_offer_id)){
							//挂失
							stopType="110000";
							statusCd="120000";
						}else if("4060200001".equals(service_offer_id)){
							//未实名单停
							stopType="150001";
							statusCd="120000";
						}else if("4060200002".equals(service_offer_id)){
							//未实名双停
							stopType="150002";
							statusCd="120000";
						}else if("4020200000".equals(service_offer_id)
								||"4020200001".equals(service_offer_id)
								||"4020201001".equals(service_offer_id)){
							//预拆机
							stopType="0";
							statusCd="110000";
						}else {
							stopType=oldStopType;
							statusCd=oldStateCd;
						}		*/			
						//stopType = 
						
						/*if ("100000".equals(state)
								||"110001".equals(state)
								||"140000".equals(state)
								||"110000".equals(state)
								||"130000".equals(state)
								||"140000".equals(state)
								||"140001".equals(state)
								||"140002".equals(state)
								||"150000".equals(state)) {
							stopType = "0";
							statusCd = state;
						}*/

						if ("4070100002".equals(service_offer_id)) {//停机保号恢复
							stopType = "0";
							statusCd = "100000";
						}else if ("4060100002".equals(service_offer_id)) {//停机保号
							stopType = "120000";
							statusCd = "120000";
						}else if ("4060200000".equals(service_offer_id)) {//用户违章停机
							stopType = "150000";
							statusCd = "120000";
						}else if ("4070200000".equals(service_offer_id)
								||"4070300002".equals(service_offer_id)) {//用户违章复机 /双停复机
							stopType = "0";
							statusCd = "100000";
						}else if ("4060300002".equals(service_offer_id)) {//用户双停业务
							stopType = "130002";
							statusCd = "120000";
						}else if ("4020201001".equals(service_offer_id)) {//预拆机
							stopType = "140000";
							statusCd = "120000";
						}else if ("4070101004".equals(service_offer_id)) {//预拆机复机
							stopType = "0";
							statusCd = "100000";
						}else if ("4060100001".equals(service_offer_id)) {//挂失
							stopType = "120000";
							statusCd = "110000";
						}else if ("4070100001".equals(service_offer_id)) {//解挂
							stopType = "0";
							statusCd = "100000";
						}else if ("4060300001".equals(service_offer_id)) {//解挂
							stopType = "130001";
							statusCd = "120000";
						}else if ("4060201003".equals(service_offer_id)) {//一证超五卡不合规单停
							stopType = "150003";
							statusCd = "120000";
						}else if ("4060201004".equals(service_offer_id)) {//一证超五卡不合规双停
							stopType = "150004";
							statusCd = "120000";
						}else if ("4070201003".equals(service_offer_id)) {//一证超五卡不合规单停复机
							stopType = "0";
							statusCd = "100000";
						}else if ("4070201004".equals(service_offer_id)) {//一证超五卡不合规双停复机
							stopType = "0";
							statusCd = "100000";
						}
						else {
							stopType = oldStopType;
							statusCd = oldStateCd;
						}
						
						/*if ("120000".equals(prodInstMap.get("statusCd"))) {
							
							long lcnt = ordBillDao.selectCntOrdProdInstState(prodInstMap);
							if (lcnt ==0) {
								
							}
							else {
								List<Map<String, Object>> ordProdInstStateList = ordBillDao.selectOrdProdInstStateFor1000(prodInstMap);
								if (ordProdInstStateList.size() == 0) {
									ordProdInstStateList = ordBillDao.selectOrdProdInstStateFor1300(prodInstMap);
								}
								Map prodStateMap = ordProdInstStateList.get(0);
								stopType = prodStateMap.get("stopType").toString();
								statusCd = "120000";
							}
						}*/
						prodInstMap.put("routeId", oldAcctId);
						prodInstMap.put("action", 2);
		                //更新prodInst表之前的备份操作
		                if(hisService.selectProdInst(prodInstMap,userMap,msg)<=0){
		                    msg.setMessage("数据备份失败：prodInstId:"+prodInstMap.get("prodInstId"));
		                    return -1;
		                }
		                prodInstMap.put("accNum", accNum);
		                prodInstMap.put("statusCd", statusCd);
						prodinstDao.updateProdInst(prodInstMap);
						prodInstobjList1.add(prodInstMap);
					//实名制复机
						if("4070201005".equals(service_offer_id)){
							//实名制复机
							/*realNameEffDate1=null;
							Map<String, Object> prodInstStateExtMap = prodinstDao.selectProdInstStateExt(prodInstMap);
							if(prodInstStateExtMap==null||prodInstStateExtMap.isEmpty()){
								msg.setMessage("实名制复机获取未知名状态信息出错");
								return -1;
							}
							oldStopTypeEx = (String) prodInstStateExtMap.get("STOP_TYPE");
							//realNameEffDate1 = (String) prodInstStateExtMap.get("EFF_DATE");
							//判断未实名前是否做过停机保号业务
							realNameEffDate2=null;
							prodInstMap.put("realNameEffDate1", prodInstStateExtMap.get("EFF_DATE"));
							try {
								realNameEffDate2 = prodinstDao.getEffDatefromStateExt(prodInstMap);
							} catch (Exception e) {
								msg.setMessage("实名复机获取停机保号生效时间出错");
								e.printStackTrace();
								throw e;
							}
							//用户做过停机保号业务
							if(realNameEffDate2!=null){
								//代码：停机保号和实名复机在同月，需要续补状态
							}else{
								//没有停机保号业务
							}*/
							if (!"3".equals(oldPaymode)) {
								//long prodInstAttrId = selectProdInstAttrId(prodInstMap);
								List<Map<String, Object>> prodInstAttrMaps = prodinstDao.selectProdInstAttrMap(prodInstMap);
								if (prodInstAttrMaps.size() == 1) {
									Map map = new HashMap();
									map = prodInstAttrMaps.get(0);
									map.put("statusCd", 1100);
									map.put("attrId", 1998);
									map.put("expDate", prodInstMap.get("statusDate"));
									map.put("action", 2);
									prodinstDao.updateProdInstAttr(map);
									prodInstAttrobjList1.add(map);
								}
								else {
									
									return 1;
								}

							}
							else {
								Map map = new HashMap();
								map.put("prodId", 999000020);
								map.put("accProdInstId", prodInstMap.get("accProdInstId"));
								List<Map<String, Object>> prodInstSubmMaps = prodinstDao.selectProdInstSubMap(map);
								if (prodInstSubmMaps.size() > 0) {
									for (int i = 0; i < prodInstSubmMaps.size(); i++) {
										Map mapTemp = new HashMap();
										mapTemp = prodInstSubmMaps.get(i);
										mapTemp.put("statusCd", 1100);
										mapTemp.put("updateDate", sdf.format(new Date()));
										mapTemp.put("stopRentDate", prodInstMap.get("statusDate"));
										mapTemp.put("action", 2);
										prodinstDao.updateProdInstSub(mapTemp);
										prodInstSubobjList1.add(mapTemp);
										mapTemp.put("attrId", 35214005);
										
										List<Map<String, Object>> prodInstAttrSubAttrMaps = prodinstDao.selectProdInstAttrSubMap(mapTemp);
										if (prodInstAttrSubAttrMaps.size() > 0) {
											for (Map<String, Object> prodInstAttrSubAttrMap : prodInstAttrSubAttrMaps) {
												prodInstAttrSubAttrMap.put("expDate", prodInstMap.get("statusDate"));
												prodInstAttrSubAttrMap.put("statusCd", prodInstMap.get("1100"));
												prodInstAttrSubAttrMap.put("expDate", sdf.format(new Date()));
												prodInstAttrSubAttrMap.put("action", 2);
												prodinstDao.updateProdInstAttrSub(prodInstAttrSubAttrMap);
												prodInstSubobjList1.add(prodInstAttrSubAttrMap);
											}
										}
										
										
										
									}
								}
								if ("18000".equals(oldStopType)
										&&"12000".equals(oldStateCd)) {
									prodInstMap.put("statusCd", "10000");
									prodinstDao.updateProdInst(prodInstMap);
									prodInstobjList1.add(prodInstMap);
									
									List<Map<String, Object>> prodInstExtList = prodinstDao.getProdInstStateIdEx(prodInstMap);
									Map prodInstExtMap = prodInstExtList.get(0);
									prodInstExtMap.put("state", 10000);
									prodInstExtMap.put("expDate", prodInstMap.get("StatusDate"));
									prodInstExtMap.put("stopType", 0);
									prodInstExtMap.put("statusCd", 1000);
									prodinstDao.updateProdInstStateExt(prodInstExtMap);
									prodInstStateobjList1.add(prodInstExtMap);
								}
								
								
							}
							
						}
				    //非实名停机
						if ("4060201001".equals(service_offer_id)) {
							if (!"3".equals(oldPaymode)) {
								List<Map<String, Object>> prodInstAttrMaps = prodinstDao.selectProdInstAttrMap(prodInstMap);
								if (prodInstAttrMaps.size() == 1) {
									Map map = new HashMap();
									map = prodInstAttrMaps.get(0);
									map.put("statusCd", 1100);
									map.put("attrId", 1998);
									map.put("expDate", prodInstMap.get("statusDate"));
									map.put("action", 2);
									prodinstDao.updateProdInstAttr(map);
									prodInstAttrobjList1.add(map);
								}
								prodInstMap.put("attrId", "1998");
								prodInstMap.put("attrValue", 1);
								prodinstDao.insertProdInstAttr(prodInstMap);
								prodInstAttrobjList1.add(prodInstMap);
							}else {
								Map map = new HashMap();
								map.put("prodId", 999000020);
								map.put("accProdInstId", prodInstMap.get("accProdInstId"));
								List<Map<String, Object>> prodInstSubmMaps = prodinstDao.selectProdInstSubMap(map);
								if (prodInstSubmMaps.size() == 0) {
									prodinstDao.insertProdInstAttrSub(map);
									prodInstSubobjList1.add(map);
									//构造167属性
									Map attr167 = new HashMap(); 
									long prodInstAttrId =prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); //统一序列
									attr167.put("prodInstAttrId",prodInstAttrId); // 需改为调用序列注意修改
									attr167.put("parProdInstAttrId", 1);
									attr167.put("prodInstId",prodInstMap.get("accProdInstId"));
									attr167.put("attrId", 167); 
									attr167.put("attrValue", 1);
									attr167.put("statusCd", 1000); 
									attr167.put("routeId",
									routeCustId); attr167.put("action", 1);
									attr167.put("effDate", prodInstMap.get("statusDate"));
									attr167.put("expDate",statePublic.expDate);
									attr167.put("updateDate", prodInstMap.get("updateDate"));
									attr167.put("executetime",
									d.format(df.parse(prodInstMap.get("updateDate"
									).toString()))); 
									prodinstDao.insertProdInstAttrSub(attr167);
									prodInstAttrSubobjList1.add(attr167);
									
								}
								//如果是强制停机状态改为正常
								if ("18000".equals(oldStopType)
										&&"12000".equals(oldStateCd)) {
									prodInstMap.put("statusCd", "10000");
									prodinstDao.updateProdInst(prodInstMap);
									prodInstobjList1.add(prodInstMap);
									
									List<Map<String, Object>> prodInstExtList = prodinstDao.getProdInstStateIdEx(prodInstMap);
									Map prodInstExtMap = prodInstExtList.get(0);
									prodInstExtMap.put("state", 10000);
									prodInstExtMap.put("expDate", prodInstMap.get("StatusDate"));
									prodInstExtMap.put("stopType", 0);
									prodInstExtMap.put("statusCd", 1000);
									prodinstDao.updateProdInstStateExt(prodInstExtMap);
									prodInstStateobjList1.add(prodInstExtMap);
									
									Map stateObj = new HashMap();
									long prodInstStateId = prodinstDao.getSeq("SEQ_PROD_INST_STATE_ID");
									stateObj.put("prodInstStateId", prodInstStateId);
									stateObj.put("routeId", routeCustId);
									stateObj.put("stopType", stopType);
									stateObj.put("state", statusCd);
									stateObj.put("statusCd", 1000);
									stateObj.put("action", 1);
									stateObj.put("effDate", prodInstMap.get("effDate"));
									stateObj.put("expDate", prodInstMap.get("expDate"));
									stateObj.put("prodInstId", prodInstMap.get("prodInstId"));
									stateObj.put("createStaff", prodInstMap.get("createStaff"));
									stateObj.put("updateStaff", prodInstMap.get("updateStaff"));
									stateObj.put("createDate", prodInstMap.get("createDate"));
									stateObj.put("statusDate", prodInstMap.get("statusDate"));
									stateObj.put("updateDate", prodInstMap.get("updateDate"));
									stateObj.put("executetime",
											d.format(df.parse(stateObj.get("updateDate").toString())));
									prodinstDao.insertProdInstStateExt(stateObj);
									prodInstStateobjList1.add(stateObj);
									
									
								}
							}
						}
					if("4040600003".equals(service_offer_id)
						||"4040600000".equals(service_offer_id)){
						if(cdmaAmsi==null ||cdmaAmsi.isEmpty()){
							msg.setMessage("CDMA_IMSI号码不能为空,不允许补卡");
							return -1;
						}
						if(prodinstDao.getForCdmaImsiCount(prodInstMap)>0){
							msg.setMessage("CDMA_IMSI号码已被占用,不允许补卡");
							return -1;
						}
						Map<String, Object> prodInstAccNumMap1=prodinstDao.selectProdInstAccNum(prodInstMap);
						if(prodInstAccNumMap1==null ||prodInstAccNumMap1.isEmpty()){
							msg.setMessage("选取CDMA_IMSI号码时出错");
							return -1;
						}
						oldAccNumId = prodInstAccNumMap1.get("prodInstAccNumId").toString();
						oldAccImsi = prodInstAccNumMap1.get("accNum").toString();
						//如果IMSI号码没有变,则不更新
						if(!cdmaAmsi.equals(oldAccImsi)){
							prodInstMap.put("statusCd", "1100");
							prodInstMap.put("prodInstAccNumId", oldAccNumId);
							if(prodinstDao.updateProdInstAccNum(prodInstMap)<=0){
								msg.setMessage("IMSI号码更新失败");
								return -1;
							}
							
						//增加CDMA_IMSI号码
							try {
								Long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); // 统一序列  
								prodInstMap.put("prodInstAccNumId", prodInstAccNumId); //需要处理主键prodInstRegionId
								prodInstMap.put("accNumType", "9000");
								String cdmaImsi = prodInstMap.get("cdmaImsi").toString();
								prodInstMap.put("accNum", cdmaImsi);
								prodInstMap.put("applyRegionId", "1");
								prodInstMap.put("statusCd", "1000");
								prodInstMap.put("platId", "1");
								prodInstMap.put("expDate", statePublic.expDate);
								prodinstDao.insertProdInstAccNum(prodInstMap); 
							} catch (Exception e) {
								msg.setMessage("增加主产品CDMA_IMSI号码时出错");
								e.printStackTrace();
								throw e;
							}
						}
						
						
						//GSM_IMSI
						if(!"".equals(prodInstMap.get("gsmImsi"))){
							long gsmImsiCount = prodinstDao.getForGsmImsiCount2(prodInstMap);
							if(gsmImsiCount>0){
								msg.setMessage("GSM_IMSI号码已被占用,不允许补卡");
								return -1;
							}
						}
						

							Map<String, Object> prodInstAccNumMap2 = prodinstDao.selectProdInstAccNum2(prodInstMap);
							if(prodInstAccNumMap2==null ||prodInstAccNumMap2.isEmpty()){
								msg.setMessage("选取IMSI号码时出错");
								return -1;
							}
							oldAccNumId = prodInstAccNumMap2.get("prodInstAccNumId").toString();
							oldAccImsi = prodInstAccNumMap2.get("accNum").toString();
							
							//老旧都有
							if(!"".equals(prodInstMap.get("gsmImsi"))
								&& !oldAccImsi.equals(prodInstMap.get("gsmImsi")) 
								&& oldAccImsi!=null ){
								prodInstMap.put("prodInstAccNumId", oldAccNumId);
								prodInstMap.put("statusCd", "1100");
								String statusDate = prodInstMap.get("statusDate").toString();
								prodInstMap.put("expDate", statusDate);
								prodInstMap.put("routeId", oldAcctId);
								if(prodinstDao.updateProdInstAccNum(prodInstMap)<=0){
									msg.setMessage("GSM_IMSI号码更新失败");
									return -1;
								}
							try {
								//增加G网IMSI
								Long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); //统一序列
								prodInstMap.put("prodInstAccNumId", prodInstAccNumId);//需要处理主键prodInstRegionId
								prodInstMap.put("accNumType", "9000");
								String gsmImsi = prodInstMap.get("gsmImsi").toString();
								prodInstMap.put("accNum", gsmImsi);
								prodInstMap.put("applyRegionId", "1");
								prodInstMap.put("statusCd", "1000");
								prodInstMap.put("platId", "1");
								prodinstDao.insertProdInstAccNum(prodInstMap); 
							} catch (Exception e) {
								msg.setMessage("增加主产品GSM_IMSI号码时出错");
								e.printStackTrace();
								throw e;
							}
						}
						//老无新有
						if("".equals(oldAccImsi) && !"".equals(prodInstMap.get("gsmImsi")) ){
							try {
								Long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); //统一序列
								prodInstMap.put("prodInstAccNumId", prodInstAccNumId);  //需要处理主键prodInstRegionId
								prodInstMap.put("accNumType", "9000");
								String gsmImsi = prodInstMap.get("gsmImsi").toString();
								prodInstMap.put("accNum", gsmImsi);
								prodInstMap.put("applyRegionId", "1");
								prodInstMap.put("statusCd", "1000");
								prodInstMap.put("platId", "1");
								prodinstDao.insertProdInstAccNum(prodInstMap); 
							} catch (Exception e) {
								msg.setMessage("增加主产品GSM_IMSI号码时出错");
								e.printStackTrace();
								throw e;
							}
						}
						
						//老有新无
						//System.out.println("测试"+ prodInstMap.get("gsmImsi").equals(""));
						//System.out.println("测试"+ prodInstMap.get("gsmImsi")=="");
						//System.out.println("测试"+ prodInstMap.get("gsmImsi1"));
						if(!"".equals(oldAccImsi) && "".equals(prodInstMap.get("gsmImsi"))){
							prodInstMap.put("prodInstAccNumId", oldAccNumId);
							prodInstMap.put("statusCd", "1100");
							String statusDate = prodInstMap.get("statusDate").toString();
							prodInstMap.put("expDate", statusDate);
							prodInstMap.put("routeId", oldAcctId);
							if(prodinstDao.updateProdInstAccNum(prodInstMap)<=0){
								msg.setMessage("GSM_IMSI号码更新失败");
								return -1;
							}
						}
						
						//LTE_IMSI
						if("".equals(prodInstMap.get("lteImsi"))){
							long lteImsiCount = prodinstDao.getForLteImsiCount2(prodInstMap);
							if(lteImsiCount>0){
								msg.setMessage("LTE_IMSI号码已被占用,不允许补卡");
								return -1;
							}
						}
						
						Map<String, Object> prodInstAccNumMap3 = prodinstDao.selectProdInstAccNum3(prodInstMap);
						if(prodInstAccNumMap3==null ||prodInstAccNumMap3.isEmpty()){
							msg.setMessage("选取LTE_IMSI号码时出错");
							return -1;
						}
						oldAccNumId = prodInstAccNumMap3.get("prodInstAccNumId").toString();
						oldAccImsi = prodInstAccNumMap3.get("accNum").toString();
						
						//老旧都有
						if(!"".equals(prodInstMap.get("lteImsi")) 
								&& !oldAccImsi.equals(prodInstMap.get("lteImsi")) 
								&& !"".equals(oldAccImsi)){
							prodInstMap.put("prodInstAccNumId", oldAccNumId);
							prodInstMap.put("statusCd", "1100");
							String statusDate = prodInstMap.get("statusDate").toString();
							prodInstMap.put("expDate", statusDate);
							prodInstMap.put("routeId", oldAcctId);
							if(prodinstDao.updateProdInstAccNum(prodInstMap)<=0){
								msg.setMessage("LTE_IMSI号码更新失败");
								return -1;
							}
							
							//增加4G到IMSI
							try {
								Long ProdInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); //统一序列
								prodInstMap.put("prodInstAccNumId", ProdInstAccNumId); //需要处理主键prodInstRegionId
								prodInstMap.put("accNumType", "9000");
								String lteImsi = prodInstMap.get("lteImsi").toString();
								prodInstMap.put("accNum", lteImsi);
								prodInstMap.put("applyRegionId", "1");
								prodInstMap.put("statusCd", "1000");
								prodInstMap.put("platId", "1");
								prodinstDao.insertProdInstAccNum(prodInstMap); 
							} catch (Exception e) {
								msg.setMessage("增加主产品LTE_IMSI号码时出错");
								e.printStackTrace();
								throw e;
							}
						}
						//老无新有  (代码：逻辑问题，如果oldAccImsi为空，则取值时就返回)
						if("".equals(oldAccImsi) && !"".equals(prodInstMap.get("lteImsi")) ){
							try {
								Long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); //统一序列
								prodInstMap.put("prodInstAccNumId", prodInstAccNumId);  //需要处理主键prodInstRegionId
								prodInstMap.put("accNumType", "9000");
								String lteImsi = prodInstMap.get("lteImsi").toString();
								prodInstMap.put("accNum", lteImsi);
								prodInstMap.put("applyRegionId", "1");
								prodInstMap.put("statusCd", "1000");
								prodInstMap.put("platId", "1");
								prodinstDao.insertProdInstAccNum(prodInstMap); 
							} catch (Exception e) {
								msg.setMessage("增加主产品LTE_IMSI号码时出错");
								e.printStackTrace();
								throw e;
							}
						}
						//老有新无
						if(!"".equals(oldAccImsi)&& "".equals(prodInstMap.get("lteImsi"))){
							prodInstMap.put("prodInstAccNumId", oldAccNumId);
							prodInstMap.put("statusCd", "1100");
							String statusDate = prodInstMap.get("statusDate").toString();
							prodInstMap.put("expDate", statusDate);
							prodInstMap.put("routeId", oldAcctId);
							if(prodinstDao.updateProdInstAccNum(prodInstMap)<=0){
								msg.setMessage("LTE_IMSI号码更新失败");
								return -1;
							}
						}
					}
					//号码存放到 PROD_INST_ACC_NUM
					prodInstMap.put("accNumType", "1000");
					Map<String, Object>  prodInstAccNumMap4 = prodinstDao.selectProdInstAccNum4(prodInstMap);
					if(prodInstAccNumMap4==null ||prodInstAccNumMap4.isEmpty()){
						msg.setMessage("获取计费主产品号码时出错,PROD_INST_ID="+prodInstMap.get("prodInstId"));
						return -1;
					}
					 oldAccNumId = prodInstAccNumMap4.get("prodInstAccNumId").toString();
					 oldAccImsi = prodInstAccNumMap4.get("accNum").toString();
					 //接入变更号处理
					if(!accNum.equals(oldAccImsi)){
						prodInstMap.put("prodInstAccNumId", oldAccNumId);
						prodInstMap.put("statusCd", "1100");
						String statusDate = prodInstMap.get("statusDate").toString();
						prodInstMap.put("expDate", statusDate);
						prodInstMap.put("routeId", oldAcctId);
						if(prodinstDao.updateProdInstAccNum(prodInstMap)<=0){
							msg.setMessage("接入变更号处理更新操作失败");
							return -1;
						}
						try {
							Long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); //统一序列
							prodInstMap.put("prodInstAccNumId", prodInstAccNumId);  //需要处理主键prodInstRegionId
							prodInstMap.put("accNumType", "1000");
							prodInstMap.put("accNum", accNum);
							prodInstMap.put("applyRegionId", "1");
							prodInstMap.put("statusCd", "1000");
							prodInstMap.put("platId", "1");
							prodInstMap.put("expDate", "3000-01-01");
							prodinstDao.insertProdInstAccNum(prodInstMap);
						} catch (Exception e) {
							msg.setMessage("增加主产品号码表时出错");
							e.printStackTrace();
							throw e;
						}
					}
					
					//================

				} else {
					msg.setMessage("处理ORD_PROD_INST更新失败,未找到用户prodInstId:"
							+ prodInstId);
					return -1;
				}
/*				prodInstMap.put("routeId", oldAcctId);
				prodInstMap.put("action", 2);
                //更新prodInst表之前的备份操作
                if(hisService.selectProdInst(prodInstMap,userMap,msg)<=0){
                    msg.setMessage("数据备份失败：prodInstId:"+prodInstMap.get("prodInstId"));
                    return -1;
                }
                prodInstMap.put("accNum", accNum);
				prodinstDao.updateProdInst(prodInstMap);
				prodInstobjList1.add(prodInstMap);*/
				// 没空处理失效的prod_inst_region 、退出群组
			}
			// prod_inst_region 区域不想等
			if (!RegionId.equals(oldRegionId)) {
				// 查找region 更新
				Map mapRegion = new HashMap();
				mapRegion.put("regionId", oldRegionId);
				mapRegion.put("prodInstId", prodInstId);
				List<Map<String, Object>> oldRegionObj = prodinstDao
						.getProdInstRegion(mapRegion);

				if (oldRegionObj.size() > 0) {
					Map oldRegion = oldRegionObj.get(0);
					oldRegion.put("expDate", state);
					oldRegion.put("statusCd", 1100);
					oldRegion.put("action", 2);
					oldRegion.put("executetime", d.format(df.parse(prodInstMap
							.get("updateDate").toString())));
					// oldRegion.put("routeId", oldAcctId);
					prodinstDao.updateProdInstRegion(oldRegion);
					prodInstRegionobjList1.add(oldRegion);

					long prodInstRegionId = prodinstDao.getSeq("SEQ_PROD_INST_REGION_ID");
					prodInstMap.put("prodInstRegionId", prodInstRegionId); // ////--需要修改读取序列
					prodInstMap.put("routeId", oldAcctId);
					prodInstMap.put("action", 1);
					prodinstDao.insertProdInstRegion(prodInstMap);
					prodInstRegionobjList1.add(prodInstMap);

				} else {
					msg.setMessage("更新ord_prod_region失败，未找到prodInstId:"
							+ prodInstId);
					return -1;
				}
			}
			// 号码不想等
/*			List<Map<String, Object>> prodInstAcc1000 = prodinstDao.getProdInstAccNumFor1000(prodInstMap);
			if (prodInstAcc1000.size() != 1) {
				msg.setMessage("获取计费主产品号码时出错 PROD_INST_ID = " + prodInstId);
				return -1;
			}
			Map prodInstAccMap = prodInstAcc1000.get(0);
			oldAccNum = prodInstAccMap.get("accNum").toString();
			if (!oldAccNum.equals("") && !accNum.equals("")
					&& !accNum.equals(oldAccNum)) {
				// 查找旧号码
				if (updateProdInstAccNum(itemMap, userMap, msg, oldAccNum,
						accNum, prodInstId, stateDate, oldAcctId, prodInstMap) < 0) {
					return -1;
				}
			}*/
			// 状态不相等
/*			if (!oldState.equals("") && !state.equals("")
					&& !state.equals(oldState)) */
				if (!oldState.equals("") && !state.equals("")
						&& (!oldState.equals(state)
						 ||!stopType.equals(oldStopType))){
				//add by wangbaoqiang  增加OCS判断  begin
					if (!"3".equals(paymode) && !"4060300002".equals(service_offer_id)) {
						if (updateProdInstState(itemMap, userMap, msg, prodInstId,
								stateDate, oldAcctId, prodInstMap,stopType,statusCd) < 0) {
							return -1;
						}
					}
					//add by wangbaoqiang 增加OCS强停换挡取消实名制停机的动作 begin
					if ("3".equals(paymode) && "10000".equals(statusCd) && "180000".equals(stopType)) {
						
						Map map = new HashMap();
						map.put("prodId", 999000020);
						map.put("accProdInstId", prodInstMap.get("accProdInstId"));
						List<Map<String, Object>> prodInstSubmMaps = prodinstDao.selectProdInstSubMap(map);
						if (prodInstSubmMaps.size() > 0) {
							for (int i = 0; i < prodInstSubmMaps.size(); i++) {
								Map mapTemp = new HashMap();
								mapTemp = prodInstSubmMaps.get(i);
								mapTemp.put("statusCd", 1100);
								mapTemp.put("updateDate", sdf.format(new Date()));
								mapTemp.put("stopRentDate", prodInstMap.get("statusDate"));
								mapTemp.put("action", 2);
								prodinstDao.updateProdInstSub(mapTemp);
								prodInstSubobjList1.add(mapTemp);
								mapTemp.put("attrId", 35214005);
								
								List<Map<String, Object>> prodInstAttrSubAttrMaps = prodinstDao.selectProdInstAttrSubMap(mapTemp);
								if (prodInstAttrSubAttrMaps.size() > 0) {
									for (Map<String, Object> prodInstAttrSubAttrMap : prodInstAttrSubAttrMaps) {
										prodInstAttrSubAttrMap.put("expDate", prodInstMap.get("statusDate"));
										prodInstAttrSubAttrMap.put("statusCd", prodInstMap.get("1100"));
										prodInstAttrSubAttrMap.put("expDate", sdf.format(new Date()));
										prodInstAttrSubAttrMap.put("action", 2);
										prodinstDao.updateProdInstAttrSub(prodInstAttrSubAttrMap);
										prodInstSubobjList1.add(prodInstAttrSubAttrMap);
									}
								}
		
							}
						}
					}
					 //add end;

				
			}

			// 付费模式不想等
			/*
			 * if(!paymode.equals(oldPaymode)) { Map mPaymode = prodInstMap;
			 * mPaymode.put("prodInstId", prodInstId); List<Map<String,Object>>
			 * oldPaymodeList = prodinstDao.getProdInstPaymodeId(mPaymode);
			 * if(oldPaymodeList.size()>0) { Map oldPaymode1 =
			 * oldPaymodeList.get(0); oldPaymode1.put("paymodeId",
			 * oldPaymodeList.get(0).get("paymodeId").toString());
			 * oldPaymode1.put("expDate",prodInstMap.get("statusDate") );
			 * oldPaymode1.put("statusCd", 1100); oldPaymode1.put("action", 2);
			 * oldPaymode1.put("prodInstId", prodInstId);
			 * 
			 * prodinstDao.updateProdInstPaymode(oldPaymode1);
			 * prodInstPaymodeobjList1.add(oldPaymode1); } //新增
			 * mPaymode.put("executetime",
			 * d.format(df.parse(prodInstMap.get("updateDate").toString())));
			 * mPaymode.put("routeId", oldAcctId); mPaymode.put("effDate",
			 * prodInstMap.get("statusDate")); mPaymode.put("statusCd", 1000);
			 * mPaymode.put("expDate", statePublic.expDate);
			 * mPaymode.put("paymentModeCd", paymode); long payModeId =
			 * prodinstDao.getSequeId(); mPaymode.put("paymodeId", payModeId);
			 * //序列id mPaymode.put("action", 1);
			 * 
			 * prodinstDao.insertProdInstPyamode(mPaymode);
			 * prodInstPaymodeobjList1.add(mPaymode);
			 * 
			 * }
			 */

		} catch (Exception e) {
			msg.setMessage("处理ORD_PROD_INST更新失败");
			e.printStackTrace();
			throw e;
		}

		return 1;
	}

	public long getOfferDetailId(Map map, Message msg) throws Exception {
		try {
			// List<Map<String,Object>> offerDetailList =
			// prodOfferDao.getOfferRoleId(map);
			List<Map<String, Object>> offerDetailList = offerinstDao
					.getOfferRoleId(map);
			if (offerDetailList.size() > 0) {
				return Long.parseLong(offerDetailList.get(0)
						.get("OFFER_OBJ_REL_ID").toString());
			} else {
				msg.setMessage("获取销售品成员角色异常");
				// throw new Exception();
				return -1;
			}
		} catch (Exception e) {
			msg.setMessage("获取销售品成员角色异常");
			e.printStackTrace();
			throw e;
		}

		// return 1;
	}

	public int updateProdInstState(Map itemMap, Map userMap, Message msg,
			long prodInstId, String stateDate, String routeId, Map stateMap,String stopType,String statusCd)
			throws Exception {

		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
			// 查找ORD最新数据insert

			/*
			 * Map mState = itemMap; mState.put("prodInstId", prodInstId);
			 * List<Map<String,Object>> newAccNumList =
			 * ordBillDao.selectOrdProdInstState(mState); Map newStateObj = new
			 * HashMap(); if(newAccNumList.size()>0) { newStateObj =
			 * newAccNumList.get(0); newStateObj.put("routeId", routeId);
			 * newStateObj.put("effDate", stateDate); newStateObj.put("expDate",
			 * statePublic.EXPDATE); newStateObj.put("action",1);
			 * prodinstDao.insertProdInstState(newStateObj);
			 * prodinstDao.insertProdInstStateExt(newStateObj);
			 * prodInstStateobjList1.add(newStateObj);
			 * 
			 * }else{
			 * msg.setMessage("更新ProdInstState失败,未找到ord_prod_inst_state:"+
			 * prodInstId); return -1; }
			 */
			// 查找旧的状态
			Map mapstate = new HashMap();
			mapstate.put("prodInstId", prodInstId);
			List<Map<String, Object>> oldStateList = prodinstDao
					.getProdInstStateIdEx(mapstate);
			if (oldStateList.size() > 0) {
				// 失效旧数据
				Map oldStateMap = oldStateList.get(0);
				oldStateMap.put("expDate", stateMap.get("statusDate"));
				oldStateMap.put("action", 2);
				oldStateMap.put("statusCd", 1100);
				oldStateMap.put("executetime", d.format(df.parse(stateMap.get(
						"updateDate").toString())));
				oldStateMap.put("prodInstId", prodInstId);
				oldStateMap.put("expDate", stateMap.get("statusDate"));
				prodinstDao.updateProdInstState(oldStateMap);
				prodinstDao.updateProdInstStateExt(oldStateMap);

				prodInstStateobjList1.add(oldStateMap);
			}
			// 新增
			Map stateObj = new HashMap();
			long prodInstStateId = prodinstDao.getSeq("SEQ_PROD_INST_STATE_ID");
			stateObj.put("prodInstStateId", prodInstStateId);
			stateObj.put("routeId", routeId);
			stateObj.put("stopType", stopType);
			stateObj.put("state", statusCd);
			stateObj.put("statusCd", 1000);
			stateObj.put("action", 1);
			stateObj.put("effDate", stateMap.get("effDate"));
			stateObj.put("expDate", stateMap.get("expDate"));
			stateObj.put("prodInstId", stateMap.get("prodInstId"));
			stateObj.put("createStaff", stateMap.get("createStaff"));
			stateObj.put("updateStaff", stateMap.get("updateStaff"));
			stateObj.put("createDate", stateMap.get("createDate"));
			stateObj.put("statusDate", stateMap.get("statusDate"));
			stateObj.put("updateDate", stateMap.get("updateDate"));
			stateObj.put("executetime",
					d.format(df.parse(stateObj.get("updateDate").toString())));

			prodinstDao.insertProdInstState(stateObj);
			prodinstDao.insertProdInstStateExt(stateObj);
			prodInstStateobjList1.add(stateObj);

		} catch (Exception e) {
			msg.setMessage("处理PROD_INST_STATE更新失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}

	public int updateProdInstAccNum(Map itemMap, Map userMap, Message msg,
			String oldAccNum, String accNum, long prodInstId,
			String stateDate1, String routeId, Map accNumMap) throws Exception {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");

			// 查找accNum
			Map mapAccNum = new HashMap();
			mapAccNum.put("accNum", oldAccNum);
			mapAccNum.put("prodInstId", prodInstId);
			// 旧数据
			List<Map<String, Object>> oldAccNumObj = prodinstDao
					.getProdInstAccNumId(mapAccNum);
			if (oldAccNumObj.size() > 0) {
				Map oldAccNumMap = oldAccNumObj.get(0);
				oldAccNumMap.put("expDate", accNumMap.get("statusDate"));
				oldAccNumMap.put("statusCd", 1100);
				oldAccNumMap.put("action", 2);
				oldAccNumMap.put("executetime", d.format(df.parse(accNumMap
						.get("updateDate").toString())));

				prodinstDao.updateProdInstAccNum(oldAccNumMap);
				prodInstAccNumobjList1.add(oldAccNumMap);
				// 取新的数据stateDate accNumMap
				Map accNumObj = accNumMap;
				long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID");
				accNumObj.put("action", 1);
				accNumObj.put("prodInstAccNumId", prodInstAccNumId);
				accNumObj.put("routeId", routeId);
				accNumObj.put("platId", 1);
				accNumObj.put("accNumType", 1000);
				accNumObj.put("applyRegionId", 1);
				accNumObj.put("statusCd", 1000);
				prodinstDao.insertProdInstAccNum(accNumObj);
				prodInstAccNumobjList1.add(accNumObj);
				/*
				 * Map mAccNum = itemMap; mAccNum.put("accNum", accNum);
				 * List<Map<String,Object>> newAccNumList =
				 * ordBillDao.selectOrdProdInstAccNum(mAccNum);
				 * if(newAccNumList.size()>0) { Map mapAccNumObj =
				 * newAccNumList.get(0); mapAccNumObj.put("action", 1);
				 * mapAccNumObj.put("routeId", routeId);
				 * mapAccNumObj.put("effDate", stateDate);
				 * mapAccNumObj.put("expDate", statePublic.EXPDATE);
				 * prodinstDao.insertProdInstAccNum(mapAccNumObj);
				 * prodInstAccNumobjList1.add(mapAccNumObj);
				 * 
				 * }
				 */

			} else {
				msg.setMessage("更新ord_prod_inst_acc_num失败，未找到prodInstId:"
						+ prodInstId);
				return -1;
			}

		} catch (Exception e) {
			msg.setMessage("更新ord_prod_inst_acc_num失败prodInstId:" + prodInstId);
			e.printStackTrace();
			throw e;
		}

		return 1;
	}

	public int insertProdInst(Map itemMap, Map userMap, Message msg)
			throws Exception {
		List<Map<String, Object>> prodInstList = ordBillDao
				.selectOrdProdInst(itemMap);
		//add by wangbaoqiang begin
		if (prodInstList.size() > 1) {
			msg.setMessage("取用户接口表数据时出错:记录有多条");
			return -1;
		}
		else if (prodInstList.size() == 0) {
			msg.setMessage("取用户接口表数据时出错:记录为空");
			return -1;
		}//add end;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dfFormat = new SimpleDateFormat("yyyy/MM/dd");

		String stop_type = "0";
		String state = "";
		//String use_cust_id;
		// prodInstMap存放prod_inst对象
		Map prodInstMap = new HashMap();
		Map prodInstNetmMap = new HashMap();
		if (prodInstList.size() > 0) {
			prodInstMap = prodInstList.get(0);
			prodInstNetmMap = prodInstList.get(0);
			state = prodInstMap.get("statusCd").toString();
			if (routeCustId == 0) {
				/*
				 * long acctId = getAcctId(itemMap,prodInstMap,msg);
				 * acct_id=acctId; if(acct_id==0) {
				 * msg.setMessage("新增prod_inst获取acct_id失败"); return -1; }
				 */
				routeCustId = Long.parseLong(prodInstMap.get("ownerCustId")
						.toString());
			}
			stop_type = "0";
			//客户校验 modify by wangbaoqiang 
			/*if ("100000".equals(state)
					||"110001".equals(state)
					||"140000".equals(state)
					||"110000".equals(state)
					||"130000".equals(state)
					||"140000".equals(state)
					||"140001".equals(state)
					||"140002".equals(state)
					||"150000".equals(state)) {
				stop_type = "0";
			}
			if ("120000".equals(prodInstMap.get("statusCd"))) {
				
				long lcnt = ordBillDao.selectCntOrdProdInstState(prodInstMap);
				if (lcnt ==0) {
					
				}
				else {
					List<Map<String, Object>> ordProdInstStateList = ordBillDao.selectOrdProdInstStateFor1000(prodInstMap);
					if (ordProdInstStateList.size() == 0) {
						ordProdInstStateList = ordBillDao.selectOrdProdInstStateFor1300(prodInstMap);
					}
					Map prodStateMap = ordProdInstStateList.get(0);
					stop_type = prodStateMap.get("stopType").toString();
				}
			}*/
/*			if("140000".equals(String.valueOf(prodInstMap.get("statusCd")))
					||"140003".equals(String.valueOf(prodInstMap.get("statusCd")))){
				stop_type = "180000";
			} else {
				stop_type = "0";
			}*/
			if("0".equals(String.valueOf(prodInstMap.get("useCustId")))){
				prodInstMap.put("useCustId", null);
			}else{
				long num = customerMapperDao.getForCountByUseCustId(prodInstMap);
				if(num == 0){
					msg.setMessage("产品实例对应的使用客户在计费不存在! USE_CUST_ID ="+prodInstMap.get("useCustId"));
				return -1;
				}
			}
			
			//预开户插入虚拟用户
			if(prodInstMap.get("addressDesc") == null){
				prodInstMap.put("prodInstMap", "用户虚拟地址");
			}
			if(prodInstMap.get("accNum") == null){
				msg.setMessage("接入号不能为NULL");
				return -1;
			}
			if(prodInstMap.get("paymentModeCd") == null){
				msg.setMessage("付费方式不能为NULL");
				return -1;
			}
			//add by wangbaoqiang 增加中继和固话前面加区号  begin
			long  prodId = Long.parseLong(prodInstMap.get("prodId").toString());
			long  accPordInstId = Long.parseLong(prodInstMap.get("accProdInstId").toString());
			String paymentTypeId = prodInstMap.get("paymentModeCd").toString();
			String  trunkNum;
			String	accNum = prodInstMap.get("accNum").toString();
			String  areaCode;
			String prodType;
			long trunkId;
			List<Map<String, Object>> regionList = ordBillDao.selectTifOrgContrast(prodInstMap);
			if (regionList.size() > 0) {
				Map regionMap = new HashMap();
				regionMap = regionList.get(0);
				areaCode = regionMap.get("areaCode").toString();
				trunkId = Long.parseLong(regionMap.get("trunkIdBill").toString());
			}
			else {
				msg.setMessage("取对应的区号时出错");
				return -1;
			}
			if (prodId == 2 || prodId == 37 || prodId == 41 || prodId == 9001
					|| prodId == 9003 || prodId == 280000007
					|| prodId == 280000037 || prodId == 280000038
					|| prodId == 280000059 || prodId == 280000088) {
				
				accNum = areaCode + accNum;
				prodInstMap.put("accNum", accNum);

			}
			if (prodId == 280000035 && accNum.indexOf("T")>= 0) {
				try {
					List<Map<String, Object>> trunkList =  ordBillDao.selectOrdProdInstAttrForTrunk(prodInstMap);
					if (trunkList.size() > 0) {
						Map trunkAttrMap = trunkList.get(0);
						if ("2".equals(trunkAttrMap.get("attrValue").toString())) {
							trunkId = 25;
						}else if ("3".equals(trunkAttrMap.get("attrValue").toString())) {
							trunkId = 30;
						}
					}

					Map TrunkMap = new HashMap();
					trunkNum = accNum.substring(accNum.indexOf("T")+1);
					TrunkMap.put("switchId", trunkId);
					TrunkMap.put("trunkCode", trunkNum);
					TrunkMap.put("trunkSide", 2);
					TrunkMap.put("accNbr", accNum);
					TrunkMap.put("effDate", prodInstMap.get("statusDate"));
					TrunkMap.put("expDate", "3000/01/01");
					TrunkMap.put("remark", prodInstMap.get("useCustName"));
					bTrunkBillingMapperDao.insertTrunkbill(TrunkMap);
				} catch (Exception e) {
					msg.setMessage("中继号码信息生成失败");
					e.printStackTrace();
					throw e;
				}	
			}
			//判断是否为组合产品
			try {
				List<Map<String, Object>> tifProdList = ordBillDao.selectTifProdContrast(prodInstMap);
				long prodiInstId = -1;
				if (tifProdList.size() == 1) {
					Map prodConMap = new HashMap();
					prodConMap = tifProdList.get(0);
					if (prodConMap.get("prodType").equals("10C")
							||prodConMap.get("prodType").equals("10D")) {
						
						try {
							Map<String, Object> ordPordInstRelMap = ordBillDao.selectOrdProdInstRelFrom1000(prodInstMap);
							prodiInstId = prodinstDao.selectMinProdInstId(ordPordInstRelMap);
						} catch (Exception e) {
							msg.setMessage("取组合产品的代表用户时出错");
							e.printStackTrace();
							throw e;
						}
						try {
							prodInstMap.put("accProdInstId", prodiInstId);
							prodInstMap.put("routeId", routeCustId);
							prodinstDao.insertProdInstGroup(prodInstMap);
							prodInstGroupobjList1.add(prodInstMap);	
						} catch (Exception e) {
							msg.setMessage("生成用户群时出错");
							e.printStackTrace();
							throw e;
						}
						
						if (prodConMap.get("prodType").equals("10D")) {
							//处理VPN
							try {
								Map vpnGroupMap = new HashMap();
								vpnGroupMap.put("vpnCode", prodInstMap.get("prodInstId"));
								vpnGroupMap.put("vpnName", "VPN用户" + prodInstMap.get("useCustName"));
								vpnGroupMap.put("effDate", prodInstMap.get("statusDate"));
								vpnGroupMap.put("expDate", expDateString);
								vpnGroupMap.put("state", "10A");
								vpnGroupMap.put("vpnType", "1");
								vpnGroupMap.put("vpnNumber", accNum);
								TifVpnGroupMapperDao.insertTifVpnGroup(vpnGroupMap);

							} catch (Exception e) {
								// TODO: handle exception
								msg.setMessage("生成VPN用户群时出错");
								e.printStackTrace();
								throw e;
							}
							
						}
						return 1;
					}
				}
				else {
					msg.setMessage("取对应的产品编码时出错");
					return -1;
				}
				
			} catch (Exception e) {
				msg.setMessage("生成用户群时出错");
				e.printStackTrace();
				throw e;
			}
			//判断设备号码是否重复  begin
			try {
				Long liCount;
				long netWorkId;
				long prodInstId = Long.parseLong(prodInstMap.get("accProdInstId").toString());
				liCount = prodinstDao.getForLiCount(prodInstMap);
				if (liCount > 0) {
					List<Map<String, Object>> prodNetWorkList = productNetworkMapperDao.selectProdNetWork(prodInstMap);
					if (prodNetWorkList.size() == 0 ) {
						netWorkId = 1;
					}
					else if (prodNetWorkList.size() > 1) {
						msg.setMessage("取网络标识时出错");
						return -1;
					}
					else {
						Map prodNetmMap = new HashMap();
						prodNetmMap = prodNetWorkList.get(0);
						netWorkId = Long.parseLong(prodNetmMap.get("networkId").toString());
						prodInstNetmMap.put("prodId", netWorkId);
						if (prodinstDao.getForLiCountFlag(prodInstNetmMap) > 0) {
							msg.setMessage("此设备号码已经存在,不允许重复:prodInstId[" + prodInstId + "];accNbr<" + accNum + ">;PRODUCT["  + prodId + ']');
						}
					}
				}	
			} catch (Exception e) {
				msg.setMessage("判断设备号码时出错");
				e.printStackTrace();
				throw e;
			}//判断设备号码是否重复  end; 
			
			//add end;
			//代码：电渠受理的新装用户
			//modify by wangbaoqiang 吉林不需要该判断
/*			Long liCount;
			Long liCountFlag;
			List<Long> prodInstIdList = new ArrayList<Long>();
			List<Long> ProdInstAccNumIdList = new ArrayList<Long>();
			liCount = prodinstDao.getForLiCount(prodInstMap);
			if(liCount>0){
				//如果新装号码，在SERV表有，而PRODUCT_ID=829,将把SERV表的这个改成2IX
				liCountFlag = prodinstDao.getForLiCountFlag(prodInstMap);
				if(liCount == liCountFlag){
					prodInstIdList=prodinstDao.getProdInstId(prodInstMap);
					for( Long getInfo : prodInstIdList){
						//修改主实例表SERV
						prodInstMap.put("getInfo", getInfo);
						if(prodinstDao.updateProdInstServ(prodInstMap)<=0){
							msg.setMessage("(拆机)修改主产品实例时出错");
							return -1;
						}
						List<Map<String, Object>> getProdInstStateList = new ArrayList<Map<String,Object>>();
						getProdInstStateList = prodinstDao.getProdInstState(prodInstMap);
						for(Map<String, Object> prodInstStateMap : getProdInstStateList ){
							if(prodinstDao.updateProdInstStateExt2(prodInstStateMap)<=0){
								msg.setMessage("ProdInstStateExt2状态更新失败");
								return -1;
							}
						}
					//代码:从序列中（DUAL）取出数据
						
						
						if(prodinstDao.insertProdInstStateExt2(prodInstMap)!=1){
							msg.setMessage("修改SERV_STATE_ATTR表主产品实例状态时出错");
							return -1;
						}
						ProdInstAccNumIdList=prodinstDao.getProdInstAccNumId2(prodInstMap);
						for(Long curServImsi : ProdInstAccNumIdList){
							prodInstMap.put("curServImsi", curServImsi);
							if(prodinstDao.updateProdInstAccNum2(prodInstMap)<=0){
								msg.setMessage("失效用户对应的IMSI等出错");
								return -1;
							}
						}
						
					}
				}else{
					msg.setMessage("此设备号码已经存在,不允许重复(PRODUCT_ID<>829),"+prodInstMap.get("accNum"));
					return -1;
				}
			} *///modify end;
			
			
			//代码：V_GROUP_PROD_TYPE从这个TIF_PROD_CONTRAST表里面取出的数据
			
			/*
			//生成用户群
			Object prodUseType =prodInstMap.get("prodUseType");
			Object statusCd = prodInstMap.get("statusCd");
			prodInstMap.put("prodUseType", 3000);
			prodInstMap.put("statusCd", 1000);
			if(prodinstDao.insertProdInstGroup(prodInstMap)<=0){
				msg.setMessage("生成用户群时出错");
				return -1;
			}
			prodInstMap.put("prodUseType", 1000);
			prodInstMap.put("statusCd", statusCd);
			if(prodinstDao.insertProdInst(prodInstMap)<=0){
				msg.setMessage("增加主产品实例时出错");
				return -1;
			}
			*/
			
			
			
			// 1新装
			try {
				prodInstMap.put("action", 1);
				prodInstMap.put("effDate", prodInstMap.get("statusDate"));
				prodInstMap.put("expDate", statePublic.expDate);
				prodInstMap.put("routeId", routeCustId);
				prodInstMap.put("executetime", d.format(df.parse(prodInstMap
						.get("updateDate").toString())));
				//prodInstMap.put("statusCd", 100000);
				
				prodinstDao.insertProdInst(prodInstMap);
				prodInstobjList1.add(prodInstMap);
				/*long prodId = Long.parseLong(prodInstMap.get("prodId")
						.toString());*/
				// 1.判断产品是否为组合产品 ---------------需要特殊处理 prodOfferDao
/*				List<Map<String, Object>> productTypeList = prodinstDao
						.getProductType(prodInstMap);
				if (productTypeList.size() > 0) {
					Map typeMap = productTypeList.get(0);
					String productType = typeMap.get("PROD_COMP_TYPE")
							.toString();
					if (productType.equals("13")) {
						prodinstDao.insertProdInstGroup(prodInstMap);
						prodInstGroupobjList1.add(prodInstMap);
					}
				} else {
						
				}*/
			} catch (Exception e) {
				msg.setMessage("处理ORD_PROD_INST新装失败");
				e.printStackTrace();
				throw e;
			}
			// 2.构造prod_inst_state. prod_inst_state_ext
			try {

				long prodInstStateId = prodinstDao
						.getSeq("SEQ_PROD_INST_STATE_ID"); // 统一序列
				prodInstMap.put("prodInstStateId", prodInstStateId);

				prodInstMap.put("stopType", stop_type);
				prodInstMap.put("state", prodInstNetmMap.get("statusCd"));
				prodInstMap.put("statusCd", 1000);
				prodinstDao.insertProdInstState(prodInstMap);
				prodinstDao.insertProdInstStateExt(prodInstMap);
				prodInstStateobjList1.add(prodInstMap);
			} catch (Exception e) {
				msg.setMessage("新装prod_inst_state构造失败");
				e.printStackTrace();
				throw e;
			}
			//ocs用户插入通知表
			if ("2100".equals(paymentTypeId)) {
				try {
					Map ccmUserMap = new HashMap();
					ccmUserMap.put("ccmOrderId", prodinstDao.getSeq("SEQ_CCM_ORDER_ID"));
					ccmUserMap.put("servId", prodInstMap.get("prodInstId"));
					ccmUserMap.put("accNbr", accNum);
					ccmUserMap.put("ctrlAttr", 1);
					ccmUserMap.put("effDate", prodInstMap.get("statusDate"));
					ccmUserMap.put("createdDate", prodInstMap.get("firstFinishDate"));
					ccmUserMap.put("flag", 0);
					ccmUserOrderMapperDao.insertCcmUserOrder(ccmUserMap);
				} catch (Exception e) {
					msg.setMessage("插入ocs通知表出错");
					e.printStackTrace();
					throw e;
				}
				
				
			}
			//2、新增prod_inst_region
			try {
				prodInstMap.put("statusCd", 1000);
				// 2.加入prod_inst_region
				long prodInstRegionId = prodinstDao.getSeq("SEQ_PROD_INST_REGION_ID");// 统一序列
				prodInstMap.put("prodInstRegionId", prodInstRegionId);// 需要处理主键prodInstRegionId
				prodInstMap.put("action", 1);
				prodinstDao.insertProdInstRegion(prodInstMap);
				prodInstRegionobjList1.add(prodInstMap);
				} 
			catch (Exception e) {
				msg.setMessage("处理prod_inst_region构造失败");
				e.printStackTrace();
				throw e;
			}
			// 3、加入prod_inst_paymode
			try {

				long paymodeId = prodinstDao.getSeq("SEQ_PAYMODE_ID");
				prodInstMap.put("paymodeId", paymodeId);// 需要处理主键paymodeId
				prodInstMap.put("statusCd", 1000);
				prodinstDao.insertProdInstPyamode(prodInstMap);

				prodInstPaymodeobjList1.add(prodInstMap);
			} catch (Exception e) {
				msg.setMessage("处理prod_inst_paymode构造失败");
				e.printStackTrace();
				throw e;
			}
				
			
			// 4、add by wangbaoqiang 构造  9999 167属性 begin
			try {
				Map attr99999 = new HashMap(); 
				long prodInstSubId =prodinstDao.getSeq("SEQ_PROD_INST_SUB_ID"); //统一序列
				attr99999.put("prodInstId", prodInstSubId);
				attr99999.put("prodUseType", 2000);
				attr99999.put("prodId", 99999);
				attr99999.put("accProdInstId", prodInstMap.get("accProdInstId"));
				attr99999.put("accNum", accNum);
				attr99999.put("paymentModeCd", prodInstMap.get("paymentModeCd"));
				attr99999.put("ownerCustId", prodInstMap.get("ownerCustId"));
				attr99999.put("regionId", prodInstMap.get("regionId"));
				attr99999.put("beginRentDate", prodInstMap.get("statusDate"));
				attr99999.put("stopRentDate", prodInstMap.get("stopRentDate"));
				attr99999.put("statusCd", 1000);
				attr99999.put("createOrgId", prodInstMap.get("createOrgId"));
				attr99999.put("createDate", prodInstMap.get("createDate"));
				attr99999.put("statusDate", prodInstMap.get("statusDate"));
				attr99999.put("firstFinishDate", prodInstMap.get("firstFinishDate"));
				attr99999.put("useCustId", prodInstMap.get("useCustId"));
				attr99999.put("updateDate", prodInstMap.get("updateDate"));
				attr99999.put("executetime",
				d.format(df.parse(prodInstMap.get("updateDate"
				).toString()))); 
				attr99999.put("action", 1);
				prodinstDao.insertProdInstSub(attr99999);
			    prodInstSubobjList1.add(attr99999);
				
				
				Map attr167 = new HashMap(); 
				long prodInstAttrId =prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); //统一序列
				attr167.put("prodInstAttrId",prodInstAttrId); // 需改为调用序列注意修改
				attr167.put("parProdInstAttrId", 1);
				attr167.put("prodInstId",prodInstSubId);
				attr167.put("attrId", 167); 
				attr167.put("attrValue", 1);
				attr167.put("statusCd", 1000); 
				attr167.put("routeId",
				routeCustId); attr167.put("action", 1);
				attr167.put("effDate", prodInstMap.get("statusDate"));
				attr167.put("expDate",statePublic.expDate);
				attr167.put("updateDate", prodInstMap.get("updateDate"));
				attr167.put("executetime",
				d.format(df.parse(prodInstMap.get("updateDate"
				).toString()))); 
				prodinstDao.insertProdInstAttrSub(attr167);
				 prodInstAttrSubobjList1.add(attr167);
			} catch (Exception e) {
				msg.setMessage("增加默认附属产品实例【99999】时出错");
				e.printStackTrace();
				throw e;
			}// add end;
			
			 
			// 5.构造prod_inst_acc_num
			try {
				//add by wangbaoqiang 增加号码判断 begin
				Long lCountFlag;
				lCountFlag = prodinstDao.getForCountNumType1000(prodInstMap);
				if (lCountFlag > 0) {
					msg.setMessage("号码表已经存在,请修改");
					return -1;
				}//add end;
				long prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID"); // 统一序列
				prodInstMap.put("prodInstAccNumId", prodInstAccNumId);
				prodInstMap.put("platId", 1);
				prodInstMap.put("accNumType", 1000);
				prodInstMap.put("applyRegionId", 1);
				prodInstMap.put("statusCd", 1000);
				prodInstMap.put("accNumType", 1000);
				prodinstDao.insertProdInstAccNum(prodInstMap);
				prodInstAccNumobjList1.add(prodInstMap);
			} catch (Exception e) {
				msg.setMessage("新装prod_inst_acc_num构造失败");
				e.printStackTrace();
				throw e;
			}
			
			
			
			//代码：机卡分离小灵通写到SERV_ATTR
			//System.out.println("测试"+prodInstMap.get("prodId"));
/*			if("312".equals(prodInstMap.get("prodId").toString())){
				try {
					long prodInstAttrId = prodinstDao.getSequeId(); //统一序列
					prodInstMap.put("prodInstAttrId", prodInstAttrId);
					prodInstMap.put("parProdInstAttrId", 1);
					prodInstMap.put("attrId", 167);
					prodInstMap.put("attrValueId", 1);
					prodInstMap.put("attrValue", 1);
					prodInstMap.put("statusCd", 1000);
					prodinstDao.insertProdInstAttr(prodInstMap);
					prodInstAttrobjList1.add(prodInstMap);
				} catch (Exception e) {
					msg.setMessage("机卡分离小灵通写到SERV_ATTR出错");
					e.printStackTrace();
					throw e;
				}
			}
			//代码：VPN专线标志写到SERV_ATTR
			if("381".equals(prodInstMap.get("prodId").toString())){	
				try {
					long prodInstAttrId = prodinstDao.getSequeId(); //统一序列
					prodInstMap.put("prodInstAttrId", prodInstAttrId);
					prodInstMap.put("parProdInstAttrId", 1);
					prodInstMap.put("attrId", 360);
					prodInstMap.put("attrValueId", 1);
					prodInstMap.put("attrValue", 1);
					prodInstMap.put("statusCd", 1000);
					prodinstDao.insertProdInstAttr(prodInstMap);
					prodInstAttrobjList1.add(prodInstMap);
				} catch (Exception e) {
					msg.setMessage("VPN专线标志写到SERV_ATTR中出错");
					e.printStackTrace();
					throw e;
				}
			}*/
			//代码：258属性，接入号码
			//代码：上网账号
			
			
			//代码：新装OCS用户，插入用户变更工具
			if("2100".equals(prodInstMap.get("paymentModeCd"))){
			}
			
			//手机增加IMSI属性
			//代码：校验手机的CDMA_IMSI号不允许为空！
				
			// add by wangbaoqiang 检查CDMA_IMSI号码是否为空
			Long imsiCount;
			long prodInstAccNumId;
			if (prodId == 379) {
				if ("".equals(prodInstMap.get("cdmaImsi").toString())
					||prodInstMap.get("cdmaImsi").toString() ==null) {
				msg.setMessage("手机的CDMA_IMSI号不允许为空！");
				return -1;
			}//add end
			imsiCount = prodinstDao.getForImsiCount(prodInstMap);
			if(imsiCount>0){
				msg.setMessage("CDMA_IMSI号码已经存在,不允许重复");
				return -1;
			}
			//增加IMSI号码
			try {
				prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID");//统一序列
				prodInstMap.put("prodInstAccNumId", prodInstAccNumId);
				prodInstMap.put("accNumType", 9000);
				prodInstMap.put("applyRegionId", 1);
				prodInstMap.put("statusCd", 1000);
				prodInstMap.put("platId", 1);
				prodInstMap.put("accNum", prodInstMap.get("cdmaImsi"));
				prodinstDao.insertProdInstAccNum(prodInstMap);
				prodInstAccNumobjList1.add(prodInstMap);
			} catch (Exception e) {
				msg.setMessage("增加主产品CDMA_IMSI号码时出错");
				e.printStackTrace();
				throw e;
			}
			}
			
			//GSM_IMSI号码
			Long gsmImsiCount;
			if(!"".equals(prodInstMap.get("gsmImsi")) 
					&& prodInstMap.get("gsmImsi") !=null ){
				gsmImsiCount = prodinstDao.getForGsmImsiCount(prodInstMap);
				if (gsmImsiCount > 0) {
					msg.setMessage("GSM_IMSI号码已经存在,不允许重复");
					return -1;
				}
				
				try {
					 prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID");  //统一序列
					 prodInstMap.put("prodInstAccNumId", prodInstAccNumId);
					 prodInstMap.put("accNumType", 9000);
					 prodInstMap.put("applyRegionId", 1);
					 prodInstMap.put("statusCd", 1000);
					 prodInstMap.put("platId", 1);
					 prodInstMap.put("accNum", prodInstMap.get("gsmImsi"));
					 prodinstDao.insertProdInstAccNum(prodInstMap);
					 prodInstAccNumobjList1.add(prodInstMap);
				} catch (Exception e) {
					msg.setMessage("增加主产品GSM_IMSI号码时出错");
					e.printStackTrace();
					throw e;
				}
			}
			
			//LTE_IMSI
			Long lteImsiCount;
			if(!"".equals(prodInstMap.get("lteImsi"))
					&& prodInstMap.get("lteImsi")!=null){
				lteImsiCount = prodinstDao.getForLteImsiCount(prodInstMap);
				if(lteImsiCount > 0){
					msg.setMessage("LTE_IMSI号码已经存在,不允许重复");
					return -1;
				}
			//增加LTE_IMSI号码
				try {
					prodInstAccNumId = prodinstDao.getSeq("SEQ_PROD_INST_ACC_NUM_ID");  //统一序列
					prodInstMap.put("prodInstAccNumId", prodInstAccNumId);
					prodInstMap.put("accNumType", 9000);
					prodInstMap.put("applyRegionId", 1);
					prodInstMap.put("statusCd", 1000);
					prodInstMap.put("platId", 1);
					prodInstMap.put("accNum", prodInstMap.get("lteImsi"));
					prodinstDao.insertProdInstAccNum(prodInstMap);
					prodInstAccNumobjList1.add(prodInstMap);
				} catch (Exception e) {
					// TODO: handle exception
					msg.setMessage("增加主产品LTE_IMSI号码时出错");
					e.printStackTrace();
					throw e;
				}
			}
			
			//代码：云堤业务？销售品实例？
		}

		return 1;
	}

	public int insertProdInstState(Map itemMap, Map userMap, Message msg,
			String service_offer_id) throws Exception {
		try {
			List<Map<String, Object>> prodInstStateList = new ArrayList<Map<String, Object>>();
			prodInstStateList = ordBillDao.selectOrdProdInstState(itemMap);

			if (prodInstStateList.size() > 0) {
				Map stateMap = prodInstStateList.get(0);
				long acctId = getAcctId(itemMap, stateMap, msg);
				stateMap.put("routeId", acctId);
				stateMap.put("action", 1);
				prodinstDao.insertProdInstState(stateMap);
				prodinstDao.insertProdInstStateExt(stateMap);
				prodInstStateobjList1.add(stateMap);
			}

		} catch (Exception e) {
			msg.setMessage("处理ORD_PROD_INST_state新装失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}

	public int insertProdInstAccNum(Map itemMap, Map userMap, Message msg,
			String service_offer_id) throws Exception {
		try {

			List<Map<String, Object>> prodInstAccNumList = new ArrayList<Map<String, Object>>();
			prodInstAccNumList = ordBillDao.selectOrdProdInstAccNum(itemMap);
			if (prodInstAccNumList.size() > 0) {
				Map accNumMap = prodInstAccNumList.get(0);
				long acctId = getAcctId(itemMap, accNumMap, msg);
				accNumMap.put("routeId", acctId);
				accNumMap.put("action", 1);
				accNumMap.put("effDate", accNumMap.get("statusDate"));
				// accNumMap.put("expDate", statePublic.EXPDATE);
				prodinstDao.insertProdInstAccNum(accNumMap);
				prodInstAccNumobjList1.add(accNumMap);

			}

		} catch (Exception e) {
			msg.setMessage("处理ORD_PROD_INST_ACCNUM新装失败");
			e.printStackTrace();
			throw e;
		}
		return 1;
	}

	// public int insertProdInstPaymode(Map itemMap,Map userMap,Message
	// msg,String service_offer_id) throws Exception
	// {
	// try{
	//
	// List<Map<String,Object>> prodInstAccNumList = new
	// ArrayList<Map<String,Object>>();
	// prodInstAccNumList = ordBillDao.selectOrdProdInstAccNum(itemMap);
	// if(prodInstAccNumList.size()>0)
	// {
	// Map accNumMap = prodInstAccNumList.get(0);
	// long acctId = getAcctId(itemMap,accNumMap,msg);
	// accNumMap.put("routeId", acctId);
	// accNumMap.put("action", 1);
	// accNumMap.put("effDate", accNumMap.get("statusDate"));
	// accNumMap.put("expDate", statePublic.EXPDATE);
	// prodinstDao.insertProdInstAccNum(accNumMap);
	// prodinstDao.updateProdInstAccNum(accNumMap);
	// }
	//
	// }catch(Exception e)
	// {
	// msg.setMessage("处理ORD_PROD_INST_Paymode新装失败");
	// e.printStackTrace();
	// throw e;
	// }
	// return 1;
	// }
	// 获取需要的 acct_id
	public long getAcctId(Map itemMap, Map objMap, Message msg)
			throws Exception {
		Long acctId = 0L;
		try {
			// 1.serv_acct查找
			List<Map<String, Object>> prodInstAcct = prodinstDao
					.getProdInstAcctId(objMap);
			if (prodInstAcct.size() >= 1) {
				Map acctIdMap = prodInstAcct.get(0);
				acctId = Long.parseLong(acctIdMap.get("acctId").toString());
			} else {
				// 2.ord_account查找
				acctId = ordBillDao.getOrdAccountById(itemMap);// ORDER_ITEM_ID
																// 条件是否要去掉，看后期数据
				if (acctId == null || acctId == 0) {
					// 3.ord_prod_inst_acct_rel查找
					List<Map<String, Object>> acctMap = ordBillDao
							.getOrdAccountByAcctRel(itemMap);
					// acctId=ordBillDao.getOrdAccountByAcctRel(itemMap);
					if (acctMap.size() > 0) {
						Map mAcct = acctMap.get(0);
						acctId = Long
								.parseLong(mAcct.get("acct_id").toString());
					} else {
						msg.setMessage("处理ORD_PROD_INST新装获取acctId失败");
						return 0;
					}
					// if(acctId==null||acctId==0)
					// {
					// msg.setMessage("处理ORD_PROD_INST新装获取acctId失败");
					// return 0;
					// }
				}
			}

		} catch (Exception e) {
			msg.setMessage("处理ORD_PROD_INST新装获取accty_id失败");
			e.printStackTrace();
			throw e;
		}

		return acctId;
	}

	public long getCustId(Map itemMap, long prodInstId, Message msg)
			throws Exception {

		Long custId = 0L;
		try {
			// 1.ord_prod_inst 查找
			Map prodMap = new HashMap();
			prodMap.put("ARCH_GRP_ID", itemMap.get("ARCH_GRP_ID"));
			prodMap.put("prodInstId", prodInstId);
			List<Map<String, Object>> prodInst1 = ordBillDao
					.selectOrdProdInstByRoute(prodMap);
			if (prodInst1.size() >= 1) {
				Map acctIdMap = prodInst1.get(0);
				custId = Long
						.parseLong(acctIdMap.get("ownerCustId").toString());
			} else {
				// 2.prod_inst查找
				List<Map<String, Object>> prodInst2 = prodinstDao
						.getProdInstOBJ(prodInstId);// ORDER_ITEM_ID
													// 条件是否要去掉，看后期数据
				if (prodInst2.size() >= 1) {
					Map acctIdMap1 = prodInst2.get(0);
					custId = Long.parseLong(acctIdMap1.get("OWNER_CUST_ID")
							.toString());
				}

			}

		} catch (Exception e) {
			msg.setMessage("获取cust_id失败");
			e.printStackTrace();
			throw e;
		}

		return custId;
	}

	/*更新总控表状态*/
	public int tranManager(Long id, String notes) throws Exception {

		WebApplicationContext contextLoader = ContextLoader
				.getCurrentWebApplicationContext();
		DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader
				.getBean("transactionManager");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
		int i = -1;
		try {
			// 逻辑代码
			Map map = new HashMap();
			map.put("procFlag", 1);
			// map.put("procCnt",1);
			map.put("archGrpId", id);
			map.put("notes", notes);
			i = ordBillDao.updateOrdBill(map);
			transactionManager.commit(status);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// transactionManager.rollback(status);
		}
		return i;
	}
	
	/*更新总控表状态*/
	public int updateOrdBill(Long id,int procFlag, String notes) throws Exception {

		int i = -1;
		try {
			// 逻辑代码
			Map map = new HashMap();
//			map.put("procFlag", 1);
			map.put("procFlag", procFlag);
		    //map.put("procCnt","");
			map.put("archGrpId", id);
			map.put("notes", notes);
			map.put("procDate", sdf.format(new Date()));			
			i = ordBillDao.updateOrdBill(map);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// transactionManager.rollback(status);
		}
		return i;
	}
	public void OneItemtranManager(Long archGrpId, String notes)
			throws Exception {

		WebApplicationContext contextLoader = ContextLoader
				.getCurrentWebApplicationContext();
		DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader
				.getBean("transactionManager");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

		try {
			// 逻辑代码
			Map map = new HashMap();
			map.put("archGrpId", archGrpId);
			map.put("remarks", notes);
			map.put("procFlag", 1);

			ordBillDao.updateOneItemResult(map);
			transactionManager.commit(status);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			// transactionManager.rollback(status);

		}

	}
//	  ----=======参与人相关=========-----
public String insertParty(long V_ARCH_GRP_ID,long V_ORDER_ITEM_ID,long V_SERVICE_OFFER_ID){
  Map queryord_partyMap=new HashMap();
  queryord_partyMap.put("arch_grp_id", V_ARCH_GRP_ID);
  queryord_partyMap.put("order_item_id", V_ORDER_ITEM_ID);
  queryord_partyMap.put("oper_type", 1000);
  
  Map ord_partyMap=new HashMap();
  try{
	    List<Map<String, Object>> ord_partyList =ordBillDao.getOrdParty(queryord_partyMap);
	    if(ord_partyList.size()==0){
	    	  queryord_partyMap.put("oper_type", 1300);
	    	  ord_partyList =ordBillDao.getOrdParty(queryord_partyMap);
	    	  if (ord_partyList.size() == 0) {
				return "1" ;
	    	  }else if (ord_partyList.size() > 1) {
				return "取参与人接口表数据时出错：记录数有多条";
			}
	    }//add by wangbaoqiang begin
	    else if (ord_partyList.size() > 1) {
			return "取参与人接口表数据时出错：记录数有多条";
		}// add end;
  	ord_partyMap=ord_partyList.get(0);
  }catch (Exception e) {
		e.printStackTrace();
		return "取参与人接口表数据时出错";
	}
//  long V_PARTY_ID=Long.parseLong(ord_partyMap.get("partyId").toString());

	System.out.println(ord_partyMap.get("partyId").toString());
  	long V_COUNT= accountDao.getCountPartyById(ord_partyMap);
	ord_partyMap.put("routeId", ord_partyMap.get("partyId"));
  	System.out.println(V_COUNT);
  	if(V_COUNT ==0){
  		//新增参与人
		ord_partyMap.put("creditId", 0);
		ord_partyMap.put("creditLimitId", 0);
		ord_partyMap.put("serviceGradeId", 0);
		ord_partyMap.put("partyNbr", "0");
		ord_partyMap.put("statusCd", 1000);
  	try{
  		accountDao.insertParty(ord_partyMap);
  	}catch (Exception e) {
  		e.printStackTrace();
  		return "新增参与人时出错";
  	}
  	}else{
//  		修改参与人
  	try{
  		ord_partyMap.put("statusCd", 1100);
  		accountDao.updateParty1(ord_partyMap);//更新哪些字段xml里有控制
  	}catch (Exception e) {
  		e.printStackTrace();
  		return "修改参与人时出错";
  	}
  }
//  --2.个人域
  Map queryord_party_ind=new HashMap();
  queryord_party_ind.put("oper_type", 1000);
  queryord_party_ind.put("order_item_id", V_ORDER_ITEM_ID);
  queryord_party_ind.put("arch_grp_id", V_ARCH_GRP_ID);
  
  List<Map<String, Object>> ord_party_indList =ordBillDao.getOrdPartyInd(queryord_party_ind);
  
  for(int i=0;i<ord_party_indList.size();i++){
  	int ord_party_indCount=ordBillDao.getCountOrdPartyInd(queryord_party_ind);
  	Map ord_party_indMap=ord_party_indList.get(i);
		ord_party_indMap.put("routeId", ord_party_indMap.get("partyId"));
  	if(ord_party_indCount==1){
  		ord_party_indMap.put("statusCd", 1000);
  		try{
  		accountDao.insertPartyInd(ord_party_indMap);
  		}catch (Exception e) {
      		e.printStackTrace();
      		return "增加参与人_个人出错";
      	}
  	}else{
  		Map ord_party_indMapUpdate=new HashMap();
  		ord_party_indMapUpdate.put("statusCd", 1100);
  		ord_party_indMapUpdate.put("statusDate", ord_party_indMap.get("statusDate"));
  		ord_party_indMapUpdate.put("updateStaff", ord_party_indMap.get("updateStaff"));
  		ord_party_indMapUpdate.put("updateDate", ord_party_indMap.get("updateDate"));
  		ord_party_indMapUpdate.put("remark", ord_party_indMap.get("remark"));
  		ord_party_indMapUpdate.put("individualId", ord_party_indMap.get("individualId"));//更新条件
  		
  		try{
      		accountDao.updatePartyInd(ord_party_indMapUpdate);
      		}catch (Exception e) {
          		e.printStackTrace();
          		return "修改参与人_个人出错";
          	}
  	}
  }
//  ---3,参与人角色
  Map queryord_party_roleMap=new HashMap();
  queryord_party_roleMap.put("arch_grp_id", V_ARCH_GRP_ID);
  queryord_party_roleMap.put("order_item_id", V_ORDER_ITEM_ID);
  queryord_party_roleMap.put("oper_type", 1000);    
  List<Map<String, Object>> ord_party_roleList =ordBillDao.getOrdPartyRole(queryord_party_roleMap);
  
  for(int i=0;i<ord_party_roleList.size();i++){
  	Map ord_party_roleMap=ord_party_roleList.get(i);
  	queryord_party_roleMap.put("party_role_id", ord_party_roleMap.get("partyRoleId"));
      int ord_party_roleCount=ordBillDao.getCountOrdPartyRole(queryord_party_roleMap);
  	if(ord_party_roleCount==1){
  		Map ord_party_roleMapIn=new HashMap();
  		ord_party_roleMapIn.put("partyRoleId", ord_party_roleMap.get("partyRoleId"));
  		ord_party_roleMapIn.put("partyId", ord_party_roleMap.get("partyId"));
  		ord_party_roleMapIn.put("roleType", ord_party_roleMap.get("roleType"));
  		ord_party_roleMapIn.put("statusDate", ord_party_roleMap.get("statusDate"));
  		ord_party_roleMapIn.put("createStaff", ord_party_roleMap.get("createStaff"));
  		ord_party_roleMapIn.put("updateStaff", ord_party_roleMap.get("updateStaff"));
  		ord_party_roleMapIn.put("createDate", ord_party_roleMap.get("createDate"));
  		ord_party_roleMapIn.put("updateDate", ord_party_roleMap.get("updateDate"));
  		ord_party_roleMapIn.put("remark", ord_party_roleMap.get("remark"));    		
  		ord_party_roleMapIn.put("statusCd", 1000);
  		ord_party_roleMapIn.put("routeId", ord_party_roleMap.get("partyId"));
  		try{
  			accountDao.insertPartyRole(ord_party_roleMapIn);
  		}catch (Exception e) {
      		e.printStackTrace();
      		return "增加参与人角色出错";
      	}
      }else{
      	Map ord_party_roleMapUpdate=new HashMap();
      	ord_party_roleMapUpdate.put("statusCd", 1100);
      	ord_party_roleMapUpdate.put("statusDate", ord_party_roleMap.get("statusDate"));
      	ord_party_roleMapUpdate.put("updateStaff", ord_party_roleMap.get("updateStaff"));
      	ord_party_roleMapUpdate.put("updateDate", ord_party_roleMap.get("updateDate"));
      	ord_party_roleMapUpdate.put("remark", ord_party_roleMap.get("remark"));
      	ord_party_roleMapUpdate.put("partyRoleId", ord_party_roleMap.get("partyRoleId"));//更新条件
      	ord_party_roleMapUpdate.put("routeId", ord_party_roleMap.get("partyId"));
      	
      	try{
  			accountDao.updatePartyRole(ord_party_roleMapUpdate);
  		}catch (Exception e) {
      		e.printStackTrace();
      		return "修改参与人角色出错";
      	}
      }
  }
//  ----4,参与人属性 ORD_PARTY@DBLINK_CRM8153OTHPDB_ATTR  
  Map queryord_party_attrMap=new HashMap();
  String partyId = "";
  queryord_party_attrMap.put("arch_grp_id", V_ARCH_GRP_ID);
  queryord_party_attrMap.put("order_item_id", V_ORDER_ITEM_ID);
  queryord_party_attrMap.put("oper_type", 1000);    
  List<Map<String, Object>> ord_party_attrList =ordBillDao.getOrdPartyAttr(queryord_party_attrMap);
  for(int i=0;i<ord_party_attrList.size();i++){
  	Map ord_party_attrMap=ord_party_attrList.get(i);
  	partyId = ord_party_attrMap.get("partyId").toString();
  	ord_party_attrMap.put("routeId", ord_party_attrMap.get("partyId"));
  	queryord_party_attrMap.put("party_attr_id", ord_party_attrMap.get("partyAttrId"));
      int ord_party_attrCount=ordBillDao.getCountOrdPartyAttr(queryord_party_attrMap);
      if(ord_party_attrCount==1){
      	ord_party_attrMap.put("statusCd", 1000);
      	try{
  			accountDao.insertPartyAttr(ord_party_attrMap);
  		}catch (Exception e) {
      		e.printStackTrace();
      		return "增加参与人角色出错";
      	}
      }else{
      	Map ord_party_attrMapUpdate=new HashMap();
  		ord_party_attrMapUpdate.put("statusCd", 1100);
  		ord_party_attrMapUpdate.put("statusDate", ord_party_attrMap.get("statusDate"));
  		ord_party_attrMapUpdate.put("updateStaff", ord_party_attrMap.get("updateStaff"));
  		ord_party_attrMapUpdate.put("updateDate", ord_party_attrMap.get("updateDate"));
  		ord_party_attrMapUpdate.put("remark", ord_party_attrMap.get("remark"));
  		ord_party_attrMapUpdate.put("partyAttrId", ord_party_attrMap.get("partyAttrId"));//条件        	
  		ord_party_attrMapUpdate.put("routeId", ord_party_attrMap.get("partyId"));        	

      	try{
  			accountDao.updatePartyAttr(ord_party_attrMapUpdate);
  		}catch (Exception e) {
      		e.printStackTrace();
      		return "增加参与人角色出错";
      	}
      }
  }
//  ord_contacts_info,contacts_info
//  ---5、参与人联系信息  CONTACTS_INFO
  Map queryord_contacts_infoMap=new HashMap();
  queryord_contacts_infoMap.put("arch_grp_id", V_ARCH_GRP_ID);
  queryord_contacts_infoMap.put("order_item_id", V_ORDER_ITEM_ID);
  queryord_contacts_infoMap.put("oper_type", 1000);    
  List<Map<String, Object>> ord_contacts_infoList =ordBillDao.getOrdContactsInfo(queryord_contacts_infoMap);
  for(int i=0;i<ord_contacts_infoList.size();i++){
  	Map ord_contacts_infoMap=ord_contacts_infoList.get(i);
  	ord_contacts_infoMap.put("routeId", partyId);
  	queryord_contacts_infoMap.put("contact_id", ord_contacts_infoMap.get("contactId"));
      int ord_contacts_infoCount=ordBillDao.getCountOrdContactsInfo(queryord_contacts_infoMap);
      if(ord_contacts_infoCount==1){
      	ord_contacts_infoMap.put("statusCd", 1000);
      	try{
  			accountDao.insertContactsInfo(ord_contacts_infoMap);
  		}catch (Exception e) {
      		e.printStackTrace();
      		return "增加联系信息出错";
      	}
      }else{
      	Map ord_contacts_infoMapUpdate=new HashMap();
  		ord_contacts_infoMapUpdate.put("statusCd", 1100);//修改标识
  		
  		ord_contacts_infoMapUpdate.put("contactType", ord_contacts_infoMap.get("contactType"));
  		ord_contacts_infoMapUpdate.put("contactName", ord_contacts_infoMap.get("contactName"));
  		ord_contacts_infoMapUpdate.put("contactGender", ord_contacts_infoMap.get("contactGender"));
  		ord_contacts_infoMapUpdate.put("contactAddr", ord_contacts_infoMap.get("contactAddr"));
  		ord_contacts_infoMapUpdate.put("contactEmployer", ord_contacts_infoMap.get("contactEmployer"));
  		ord_contacts_infoMapUpdate.put("homePhone", ord_contacts_infoMap.get("homePhone"));
  		ord_contacts_infoMapUpdate.put("officePhone", ord_contacts_infoMap.get("officePhone"));
  		ord_contacts_infoMapUpdate.put("mobilePhone", ord_contacts_infoMap.get("mobilePhone"));
  		ord_contacts_infoMapUpdate.put("contactDesc", ord_contacts_infoMap.get("contactDesc"));
  		ord_contacts_infoMapUpdate.put("eMail", ord_contacts_infoMap.get("eMail"));
  		ord_contacts_infoMapUpdate.put("postcode", ord_contacts_infoMap.get("postcode"));
  		ord_contacts_infoMapUpdate.put("postAddr", ord_contacts_infoMap.get("postAddr"));
  		ord_contacts_infoMapUpdate.put("fax", ord_contacts_infoMap.get("fax"));
  		ord_contacts_infoMapUpdate.put("statusDate", ord_contacts_infoMap.get("statusDate"));
  		ord_contacts_infoMapUpdate.put("updateStaff", ord_contacts_infoMap.get("updateStaff"));
  		ord_contacts_infoMapUpdate.put("updateDate", ord_contacts_infoMap.get("updateDate"));
  		ord_contacts_infoMapUpdate.put("remark", ord_contacts_infoMap.get("remark"));
  		ord_contacts_infoMapUpdate.put("routeId", partyId);
  		
  		ord_contacts_infoMapUpdate.put("contactId", ord_contacts_infoMap.get("contactId"));//更新条件
      	try{
  			accountDao.updateContactsInfo(ord_contacts_infoMapUpdate);
  		}catch (Exception e) {
      		e.printStackTrace();
      		return "修改联系信息出错";
      	}
      }
  }   
//  ord_contacts_info_attr,contacts_info_attr
//  ---6,参与人联系信息属性 CONTACTS_INFO_ATTR
  Map queryord_contacts_info_attrMap=new HashMap();
  queryord_contacts_info_attrMap.put("arch_grp_id", V_ARCH_GRP_ID);
  queryord_contacts_info_attrMap.put("order_item_id", V_ORDER_ITEM_ID);
  queryord_contacts_info_attrMap.put("oper_type", 1000);    
  List<Map<String, Object>> ord_contacts_info_attrList =ordBillDao.getOrdContactsInfoAttr(queryord_contacts_info_attrMap);
  for(int i=0;i<ord_contacts_info_attrList.size();i++){
  	Map ord_contacts_info_attrMap=ord_contacts_info_attrList.get(i);
  	ord_contacts_info_attrMap.put("routeId", ord_contacts_info_attrMap.get("partyId"));
  	queryord_contacts_info_attrMap.put("connect_attr_id", ord_contacts_info_attrMap.get("connectAttrId"));
      int ord_contacts_info_attrCount=ordBillDao.getCountOrdContactsInfoAttr(queryord_contacts_info_attrMap);
      if(ord_contacts_info_attrCount==1){
      	ord_contacts_info_attrMap.put("statusCd", 1000);
      	try{
  			accountDao.insertContactsInfoAttr(ord_contacts_info_attrMap);
  		}catch (Exception e) {
      		e.printStackTrace();
      		return "增加联系信息属性出错";
      	}
      }else{
      	Map contacts_info_attrUpdate=new HashMap();
      	contacts_info_attrUpdate.put("statusCd", 1100);//修改标识
      	
      	contacts_info_attrUpdate.put("statusDate", ord_contacts_info_attrMap.get("statusDate"));
      	contacts_info_attrUpdate.put("updateStaff", ord_contacts_info_attrMap.get("updateStaff"));
      	contacts_info_attrUpdate.put("updateDate", ord_contacts_info_attrMap.get("updateDate"));
      	contacts_info_attrUpdate.put("remark", ord_contacts_info_attrMap.get("remark"));
      	contacts_info_attrUpdate.put("routeId", ord_contacts_info_attrMap.get("partyId"));
      	contacts_info_attrUpdate.put("connectAttrId", ord_contacts_info_attrMap.get("connectAttrId"));//修改条件
  		
      	try{
  			accountDao.updateContactsInfoAttr(contacts_info_attrUpdate);
  		}catch (Exception e) {
      		e.printStackTrace();
      		return "修改联系信息属性出错";
      	}
      }
  }       
	return "1";
	//CrmUserTable
	//	accountDao
}
//----=======一般纳税相关=========-----
public String insertTaxPayer(long V_ARCH_GRP_ID,long V_ORDER_ITEM_ID){
//	1.ord_tax_payer,tax_payer
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
	Map queryord_tax_payerMap=new HashMap();
	queryord_tax_payerMap.put("arch_grp_id", V_ARCH_GRP_ID);
	queryord_tax_payerMap.put("order_item_id", V_ORDER_ITEM_ID);
	queryord_tax_payerMap.put("oper_type", 1000);    
	List<Map<String, Object>> ord_tax_payerList=new ArrayList();
	try{
  ord_tax_payerList =ordBillDao.getOrdTaxPayer(queryord_tax_payerMap);
	}catch (Exception e) {
		e.printStackTrace();
		return "取一般纳税人接口表数据时出错";
	}
	if (ord_tax_payerList.size() !=1 ) {
		return "取一般纳税人接口表数据时出错";
	}
  Map ord_tax_payerMap=ord_tax_payerList.get(0);
  long cust_id;
  List<Map<String, Object>> ordCustList = ordBillDao.selectOrdBillCustId(V_ARCH_GRP_ID);
  Map ordCustMap = ordCustList.get(0);
  cust_id = Long.parseLong(ordCustMap.get("custId").toString());
  ord_tax_payerMap.put("routeId", cust_id);
  ord_tax_payerMap.put("stautsCd", 1000);
  ord_tax_payerMap.put("custId", cust_id);
  long tax_payerCount=accountDao.getCountTaxPayer(ord_tax_payerMap);
  //int ord_tax_payerCount=ordBillDao.getCountOrdTaxPayer(queryord_tax_payerMap);

/*  try{
  	cust_id=accountDao.getCustId(ord_tax_payerMap);
  }catch (Exception e) {
		e.printStackTrace();
		return "纳税人获取客户出错";
	}*/

  if(tax_payerCount==0){
	  /*ord_tax_payerMap.put("statusCd", 1000);
  	ord_tax_payerMap.put("routeId", cust_id);
  	String tax_province_code=ord_tax_payerMap.get("taxProvinceCode").toString();
  	tax_province_code=tax_province_code.equals("")?"915":tax_province_code;
  	ord_tax_payerMap.put("taxProvinceCode", tax_province_code);*/   	
  	try{
			accountDao.insertTaxPayer1(ord_tax_payerMap);
			
		}catch (Exception e) {
  		e.printStackTrace();
  		return "新增一般纳税人时出错";
  	}
  }else{
  	Map querytax_payerMap=new HashMap();
  	Map queryTaxpayerFlagMap=new HashMap();
  	Map tax_payerUpdate=new HashMap();
  	String oldGgeneralTaxplayer = "";
  	String newGgeneralTaxplayer = "";
  	querytax_payerMap.put("status_cd", 1000);
  	querytax_payerMap.put("tax_payer_id", ord_tax_payerMap.get("taxPayerId"));
  	querytax_payerMap.put("routeId", cust_id);
  	newGgeneralTaxplayer = ord_tax_payerMap.get("generalTaxpayerFlag").toString();
  	try {
  	  	queryTaxpayerFlagMap=accountDao.getTaxPayerInfo(querytax_payerMap);
  	    oldGgeneralTaxplayer = queryTaxpayerFlagMap.get("generalTaxplayerFlag").toString();

	} catch (Exception e) {
		e.printStackTrace();
  		return "取TAX_PAYER.GENERAL_TAXPAYER_FLAG出错";
	}
  	if ("0".equals(newGgeneralTaxplayer)) {
  		tax_payerUpdate.put("expDate", new Date());
	}else if ("1".equals(newGgeneralTaxplayer)
			&&"0".equals(oldGgeneralTaxplayer)) {
  		tax_payerUpdate.put("effDate", new Date());
	}
  	
  	tax_payerUpdate.put("statusCd", 1000);//更新条件
  	tax_payerUpdate.put("taxPayerId", ord_tax_payerMap.get("taxPayerId"));//更新条件

  	//更新字段
  	tax_payerUpdate.put("taxId", ord_tax_payerMap.get("taxId"));
  	tax_payerUpdate.put("taxName", ord_tax_payerMap.get("taxName"));
  	tax_payerUpdate.put("taxRelaAddr", ord_tax_payerMap.get("taxRelaAddr"));
  	tax_payerUpdate.put("taxRelaTel", ord_tax_payerMap.get("taxRelaTel"));
  	tax_payerUpdate.put("taxBankAcct", ord_tax_payerMap.get("taxBankAcct"));
  	tax_payerUpdate.put("taxBankName", ord_tax_payerMap.get("taxBankName"));
  	tax_payerUpdate.put("generalTaxpayerFlag", ord_tax_payerMap.get("generalTaxpayerFlag"));
  	tax_payerUpdate.put("vatInvoicesFlag", ord_tax_payerMap.get("vatInvoicesFlag"));
  	tax_payerUpdate.put("statusDate", d.format(new Date()));
  	tax_payerUpdate.put("effDate", ord_tax_payerMap.get("effDate"));
  	tax_payerUpdate.put("expDate", ord_tax_payerMap.get("expDate"));
  	tax_payerUpdate.put("updateStaff", ord_tax_payerMap.get("updateStaff"));
  	tax_payerUpdate.put("updateDate", ord_tax_payerMap.get("updateDate"));
  	tax_payerUpdate.put("remark", ord_tax_payerMap.get("remark"));	
  	tax_payerUpdate.put("routeId", cust_id);		
  	try{
			accountDao.updateTaxPayer1(tax_payerUpdate);
		}catch (Exception e) {
  		e.printStackTrace();
  		return "修改一般纳税人时出错";
  	}
  }
//	2.ord_tax_payer_attr,tax_payer_attr   
  Map queryord_tax_payer_attrMap=new HashMap();
  queryord_tax_payer_attrMap.put("arch_grp_id", V_ARCH_GRP_ID);
  queryord_tax_payer_attrMap.put("order_item_id", V_ORDER_ITEM_ID);
  queryord_tax_payer_attrMap.put("oper_type", 1000);    
  List<Map<String, Object>> ord_tax_payer_attrList =ordBillDao.getOrdTaxPayerAttr(queryord_tax_payer_attrMap);
  
  for(int i=0;i<ord_tax_payer_attrList.size();i++){
  	Map ord_tax_payer_attrMap=ord_tax_payer_attrList.get(i);
  	ord_tax_payer_attrMap.put("routeId", cust_id);
  	queryord_tax_payer_attrMap.put("tax_payer_attr_id", ord_tax_payer_attrMap.get("taxPayerAttrId"));
      int ord_tax_payer_attrCount=ordBillDao.getCountOrdTaxPayerAttr(queryord_tax_payer_attrMap);
      if(ord_tax_payer_attrCount==1){
      	
      	int tax_payer_attrCount=accountDao.getCountTaxPayerAttr(ord_tax_payer_attrMap);
      	if(tax_payer_attrCount==0){
      		ord_tax_payer_attrMap.put("statusCd", 1000);
      		ord_tax_payer_attrMap.put("updateDate", d.format(new Date()));
          	try{
      			accountDao.insertTaxPayerAttr1(ord_tax_payer_attrMap);
      		}catch (Exception e) {
          		e.printStackTrace();
          		return "增加纳税人属性出错";
          	}
      	}
      }else{
      	Map tax_payer_attrUpdate=new HashMap();
      	tax_payer_attrUpdate.put("statusCd", 1100);
      	
      	tax_payer_attrUpdate.put("attrValue", ord_tax_payer_attrMap.get("attrValue"));
      	tax_payer_attrUpdate.put("statusDate", ord_tax_payer_attrMap.get("statusDate"));
      	tax_payer_attrUpdate.put("updateStaff", ord_tax_payer_attrMap.get("updateStaff"));
      	tax_payer_attrUpdate.put("updateDate", df.format(new Date()));
      	tax_payer_attrUpdate.put("remark", "接口更新,ARCH_GRP_ID ="+V_ARCH_GRP_ID);
      	tax_payer_attrUpdate.put("taxPayerAttrId", ord_tax_payer_attrMap.get("taxPayerAttrId"));//更新条件
      	tax_payer_attrUpdate.put("routeId", cust_id);

  		
      	try{
  			accountDao.updateTaxPayerAttr1(tax_payer_attrUpdate);
  		}catch (Exception e) {
      		e.printStackTrace();
      		return "修改联系信息属性出错";
      	}
      }
  }       	
	return "1";
}
	

	public int insertProdInstSub(Map itemMap, Map userMap, Message msg)
		throws Exception{
		
		List<Map<String, Object>> prodInstList = ordBillDao
					.selectOrdProdInstSub(itemMap);			
		//add by wangbaoqiang 添加接口表逻辑判断begin
		if (prodInstList.size() > 1) {
			msg.setMessage("取用户附属产品接口表数据operType=1000时出错:记录有多条");
			return -1;
		}
		else if (prodInstList.size() == 0) {
			prodInstList = ordBillDao
					.selectOrdProdInstSub1300(itemMap);
			if (prodInstList.size() == 0) {
				return 1;
			}
			else if (prodInstList.size() > 1) {
				msg.setMessage("用户附属产品接口表数据operType=1300时出错:记录有多条");
				return -1;
			}
			
		}//add end;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat df3000 = new SimpleDateFormat("yyyy-MM-dd");
		// prodInstMap存放prod_inst对象
		Map prodInstMap = new HashMap();
		String prodId="";
		String effDate = "";
		String expDate = "";
		Long routeId;
		
		//20G断网恢复处理变量
		long liFlag;
		long liCnt;
		if(prodInstList.size()>0){
			prodInstMap = prodInstList.get(0);
			
			prodId = prodInstMap.get("prodId").toString();
			effDate = prodInstMap.get("statusDate").toString();
			routeId =  Long.parseLong(prodInstMap.get("ownerCustId").toString());
			//获取指定日期的下一个月
			//expDate = prodinstDao.getNextMonth(effDate);
			 /*Date dExpDate = df3000.parse(expDateString);
			 expDate = df3000.format(dExpDate);*/
			Long prodInstCount = prodinstDao.getForProdInstCount(prodInstMap);
			//过滤掉计费不存在的用户的功能产品
			if(prodInstCount<=0){
				/*msg.setMessage("用户"+prodInstMap.get("accProdInstId")+"不存在,不能订购功能产品");
				return -1;*/
				return 1;
			}
			Map tifProdSubMap = new HashMap();
			String prodSubType = "";
			List<Map<String,Object>>  ordProdInsSubrList= 
					ordBillDao.selectTifSubProdContrast(prodInstMap.get("prodId").toString());
			if (ordProdInsSubrList.size() == 0) {//过滤计费不需要的附属产品
				return 1;
			}
			tifProdSubMap = ordProdInsSubrList.get(0);
			prodSubType = tifProdSubMap.get("prodType").toString();
			if ("10X".equals(prodSubType)) {
				return 1;
			}//end
			//CRM失效附属产品的一条 1000，一条1100，接口只处理 OPER_TYPE = 1000的这条，
			//如果只有 OPER_TYPE = 1000计费接口为新增，如果同时有两条 则为失效
			prodInstMap.put("sysDate", df.format(new Date()));
			Long operFlg = ordBillDao.getOperFlag(prodInstMap);
			if(operFlg==0){
				//20g断网复网
				if("88000014".equals(prodId)){
					try {
						//20G断网需求新增流程，CRM以附属产品的形式同步，计费以产品属性的形式存储
						List<Map<String, Object>> prodInstAttr1000List = prodinstDao.selectProdInstAttr1000(prodInstMap);
						if (prodInstAttr1000List.size() == 1) {
							Map prodInstAttr1000Map = new HashMap();
							prodInstAttr1000Map = prodInstAttr1000List.get(0);
							String attrValue = prodInstAttr1000Map.get("attrValue").toString();
							if (attrValue.equals("1")) {
								attrValue = "0";
								prodInstAttr1000Map.put("attrValue", attrValue);
								prodinstDao.updateProdInstAttrNet(prodInstAttr1000Map);
								prodInstAttrobjList1.add(prodInstAttr1000Map);	
								return 1;
							}
						}
/*						Long prodInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); //获取序列
						prodInstMap.put("prodInstAttrId", prodInstAttrId);
						prodInstMap.put("parProdInstAttrId", "1");
						prodInstMap.put("prodInstId", prodInstMap.get("accProdInstId"));
						prodInstMap.put("attrId", "1739");
						prodInstMap.put("attrValueId", "1");
						prodInstMap.put("attrValue", "1");
						prodInstMap.put("effDate", effDate);
						prodInstMap.put("expDate", expDate);
						prodInstMap.put("statusCd", "1000");
						prodinstDao.insertProdInstAttr(prodInstMap);*/
					} catch (Exception e) {
						msg.setMessage("[20G]增加产品属性出错");
						e.printStackTrace();
						throw e;
					}
				}
				else if("88000015".equals(prodId)){
					//增加15G断网复网
					try {
						List<Map<String, Object>> prodInstAttr1415List = prodinstDao.selectProdInstAttr1415(prodInstMap);
						if (prodInstAttr1415List.size() == 1) {
							Map prodInstAttr1415Map = new HashMap();
							prodInstAttr1415Map = prodInstAttr1415List.get(0);
							String attrValue = prodInstAttr1415Map.get("attrValue").toString();
							if (attrValue.equals("1")) {
								prodInstAttr1415Map.put("expDate", prodInstMap.get("effDate"));
								prodinstDao.updateProdInstAttrNet(prodInstAttr1415Map);
								prodInstAttrobjList1.add(prodInstAttr1415Map);	
								
								Long prodInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); //获取序列
								prodInstMap.put("prodInstAttrId", prodInstAttrId);
								prodInstMap.put("parProdInstAttrId", "-1");
								prodInstMap.put("prodInstId", prodInstMap.get("accProdInstId"));
								prodInstMap.put("attrId", "1415");
								prodInstMap.put("attrValueId", "1");
								prodInstMap.put("attrValue", "3");
								prodInstMap.put("effDate", effDate);
								prodInstMap.put("expDate",expDateString);
								prodInstMap.put("statusCd", "1000");
								prodinstDao.insertProdInstAttr(prodInstMap);
								prodInstAttrobjList1.add(prodInstMap);	

								return 1;
							}
						}
						
/*						Long prodInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); //获取序列
						prodInstMap.put("prodInstAttrId", prodInstAttrId);
						prodInstMap.put("parProdInstAttrId", "1");
						prodInstMap.put("prodInstId", prodInstMap.get("accProdInstId"));
						prodInstMap.put("attrId", "10");
						prodInstMap.put("attrValueId", "1");
						prodInstMap.put("attrValue", "1");
						prodInstMap.put("effDate", effDate);
						prodInstMap.put("expDate", expDate);
						prodInstMap.put("statusCd", "1000");
						prodinstDao.insertProdInstAttr(prodInstMap);*/
					} catch (Exception e) {
						msg.setMessage("增加15G断网复网时出错");
						e.printStackTrace();
						throw e;
					}
				}
				//modify by wangbaoqiang 先取主键，取不到在按照产品实例和规格来取
				Long prodInstSubCount = prodinstDao.getProdInstSubPrimaryKeyCnt(prodInstMap);
				if (prodInstSubCount ==0) {
					prodInstSubCount = prodinstDao.getForProdInstSubCount(prodInstMap);
				}
				if(prodInstSubCount==0){
					//增加附属产品实例 PROD_INST_SUB
					try {
						/*Long servProdId = prodinstDao.getSeq("SEQ_SERV_PROD_ID"); //获取序列
						prodInstMap.put("prodInstId", servProdId);*/
						prodInstMap.put("prodUseType", "2000");
						//prodInstMap.put("beginRentDate", effDate);
						prodInstMap.put("stopRentDate", expDateString);
						prodInstMap.put("statusCd", "1000");
						//prodInstMap.put("statusDate", effDate);
						prodInstMap.put("action", 1);
						prodinstDao.insertProdInstSub(prodInstMap);
						prodInstSubobjList1.add(prodInstMap);
						
					} catch (Exception e) {
						msg.setMessage("增加附属产品实例时出错");
						e.printStackTrace();
						throw e;
					}	
					//附属产品属性
					if (!prodId.equals("35213013")  || !prodId.equals("35214000")) {
						try {
							Long prodInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); //获取序列
							prodInstMap.put("prodInstAttrId", prodInstAttrId);
							prodInstMap.put("parProdInstAttrId", "1");
							/*Long servProdId = prodinstDao.getSeq("SEQ_SERV_PROD_ID"); //获取序列
							prodInstMap.put("prodInstId",servProdId );*/
							prodInstMap.put("attrId","167" );
							prodInstMap.put("attrValueId","1" );
							prodInstMap.put("attrValue","1" );
							prodInstMap.put("effDate",prodInstMap.get("statusDate") );
							prodInstMap.put("action", 1);
							prodInstMap.put("expDate",expDateString );
							prodinstDao.insertProdInstAttrSub(prodInstMap);
							prodInstAttrSubobjList1.add(prodInstMap);
						} catch (Exception e) {
							msg.setMessage("增加167附属产品属性时出错");
							e.printStackTrace();
							throw e;
						}
					}
				}
				//add by wangbaoqiang 有存在的功能型产品，先失效在增加 begin  屏蔽这段，主键会有冲突
/*				else {
					try {
						List<Map<String, Object>> prodInstSubMaps = prodinstDao.getProdInstSubIdExp(prodInstMap);
						if (prodInstSubMaps.size() > 1) {
							msg.setMessage("取附属产品实例时出错");
							return -1;
						}
						else {
							Map prodInstSubMap = prodInstSubMaps.get(0);
							prodInstSubMap.put("statusCd", "1100");
							prodInstSubMap.put("updateDate", df.format(new Date()));
							prodInstSubMap.put("stopRentDate", prodInstMap.get("effDate"));
							prodInstSubMap.put("action", 2);
							prodinstDao.updateProdInstSub(prodInstSubMap);
							prodInstSubobjList1.add(prodInstMap);
						}
					} catch (Exception e) {
						msg.setMessage("修改附属产品实例附加属性时出错");
						return -1;
					}
					try {
						Long servProdId = prodinstDao.getSeq("SEQ_SERV_PROD_ID"); //获取序列
						prodInstMap.put("prodInstId", servProdId);
						prodInstMap.put("prodUseType", "2000");
						prodInstMap.put("beginRentDate", effDate);
						prodInstMap.put("stopRentDate", expDateString);
						prodInstMap.put("statusCd", "1000");
						prodInstMap.put("statusDate", effDate);
						prodInstMap.put("action", 1);
						prodinstDao.insertProdInstSub(prodInstMap);
						prodInstSubobjList1.add(prodInstMap);
						
					} catch (Exception e) {
						msg.setMessage("增加附属产品实例时出错");
						e.printStackTrace();
						throw e;
					}
				}*/// add end;
				
				if (prodId.equals("999000020")) {
					
				}
				
			}else if(operFlg==1){
				//add by wangbaoqaing begin 
				if("88000014".equals(prodId)){
					try {
						//20G断网需求新增流程，CRM以附属产品的形式同步，计费以产品属性的形式存储
						List<Map<String, Object>> prodInstAttr1000List = prodinstDao.selectProdInstAttr1000(prodInstMap);
						if (prodInstAttr1000List.size() == 1) {
							Map prodInstAttr1000Map = new HashMap();
							prodInstAttr1000Map = prodInstAttr1000List.get(0);
							String attrValue = prodInstAttr1000Map.get("attrValue").toString();
							if (attrValue.equals("0")) {
								attrValue = "3";
								prodInstAttr1000Map.put("attrValue", attrValue);
								prodinstDao.updateProdInstAttrNet(prodInstAttr1000Map);
								prodInstAttrobjList1.add(prodInstAttr1000Map);	
								return 1;
							}
						}
					} catch (Exception e) {
						msg.setMessage("[20G]增加产品属性出错");
						e.printStackTrace();
						throw e;
					}
				}
				else if("88000015".equals(prodId)){
					//增加15G断网复网
					try {
						List<Map<String, Object>> prodInstAttr1415List = prodinstDao.selectProdInstAttr1415(prodInstMap);
						if (prodInstAttr1415List.size() == 1) {
							Map prodInstAttr1415Map = new HashMap();
							prodInstAttr1415Map = prodInstAttr1415List.get(0);
							String attrValue = prodInstAttr1415Map.get("attrValue").toString();
							if (attrValue.equals("1")) {
								prodInstAttr1415Map.put("expDate", prodInstMap.get("effDate"));
								prodinstDao.updateProdInstAttrNet(prodInstAttr1415Map);
								prodInstAttrobjList1.add(prodInstAttr1415Map);	
								
								Long prodInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); //获取序列
								prodInstMap.put("prodInstAttrId", prodInstAttrId);
								prodInstMap.put("parProdInstAttrId", "-1");
								prodInstMap.put("prodInstId", prodInstMap.get("accProdInstId"));
								prodInstMap.put("attrId", "1415");
								prodInstMap.put("attrValueId", "1");
								prodInstMap.put("attrValue", "3");
								prodInstMap.put("effDate", effDate);
								prodInstMap.put("expDate",expDate);
								prodInstMap.put("statusCd", "1000");
								prodinstDao.insertProdInstAttr(prodInstMap);
								prodInstAttrobjList1.add(prodInstMap);	

								return 1;
							}
						}
						
					} catch (Exception e) {
						msg.setMessage("增加15G断网复网时出错");
						e.printStackTrace();
						throw e;
					}
				}//add end
				//20G断网后恢复
				if("6600083017".equals(prodId)){
					liFlag=0;
					liCnt=0;
					//获取日期中的day
					String dayStr = prodinstDao.getDay(effDate);
					if("01".equals(dayStr)||"1".equals(dayStr)){
						prodInstMap.put("attrId", "1739");
						liCnt = prodinstDao.getForProdInstAttrCount(prodInstMap);
						if(liCnt>0){
							liFlag = 1;
						}
					}else{
						liFlag = 1;
					}
					
					if(liFlag == 1){
						try {
							long prodInstAttrId = prodinstDao.getSeq("SEQ_PROD_INST_ATTR_ID"); //获取序列
							prodInstMap.put("prodInstAttrId", prodInstAttrId);
							prodInstMap.put("parProdInstAttrId", "1");
							prodInstMap.put("prodInstId", prodInstMap.get("accProdInstId"));
							prodInstMap.put("attrId", "1740");
							prodInstMap.put("attrValueId", "1");
							prodInstMap.put("attrValue", "1");
							prodInstMap.put("effDate", prodInstMap.get("statusDate"));
							prodInstMap.put("expDate", expDate);
							prodInstMap.put("statusCd", "1000");
							prodinstDao.insertProdInstAttr(prodInstMap);
						} catch (Exception e) {
							msg.setMessage("[20G]增加产品属性出错");
							e.printStackTrace();
							throw e;
						}
					}
					
				}
				
				//失效4G属性
				 effDate = prodInstMap.get("stopRentDate").toString();
				 if("6600016000".equals(prodId)){
					 prodInstMap.put("attrId", "10");
					 prodInstMap.put("effDate",effDate);
					 Long oldInstAttrId = prodinstDao.getForProdInstAttrId(prodInstMap);
					 if(oldInstAttrId<=0){
						 msg.setMessage("获取4G属性出错");
						 return -1;
					 }
					 
					 String statusDate = prodInstMap.get("prodInstMap").toString();
					 String updateDate = prodInstMap.get("updateDate").toString();
					 try {
						prodInstMap.put("expDate", effDate);
						prodInstMap.put("statusCd", "1100");
						prodInstMap.put("routeId",routeId );
						prodInstMap.remove("statusDate");
						prodInstMap.remove("updateDate");
						prodinstDao.updateProdInstAttr(prodInstMap);
					} catch (Exception e) {
						msg.setMessage("更新产品属性失败");
						e.printStackTrace();
						throw e;
					}
					 prodInstMap.put("statusDate", statusDate);
					 prodInstMap.put("updateDate", updateDate); 
				 }
				 //失效附属产品
				 Long prodInstSubCount = prodinstDao.getProdInstSubPrimaryKeyCnt(prodInstMap);
					if (prodInstSubCount ==0) {
						List<Map<String, Object>> prodInstSubList = prodinstDao.getProdInstSubIdExp(prodInstMap);
						if (prodInstSubList.size() == 1) {
							Map prodInstSubMap = prodInstSubList.get(0);
							prodInstMap.put("prodInstId", prodInstSubMap.get("prodInstId"));
						}else if(prodInstSubList.size() > 1) {
							msg.setMessage("取附属产品实例时出错");
							return -1;
						}else if (prodInstSubList.size() == 0) {
							return 1;
						}
					}
					prodInstMap.put("statusCd", "1100");
					prodInstMap.put("updateDate", df.format(new Date()));
					prodInstMap.put("stopRentDate", prodInstMap.get("statusDate"));
					prodInstMap.put("action", 2);
					try {
						prodinstDao.updateProdInstSub(prodInstMap);
						prodInstSubobjList1.add(prodInstMap);
					} catch (Exception e) {
						msg.setMessage("修改附属产品实例时出错");
						e.printStackTrace();
						throw e;
					}
			}
			
			//
			
		}
		
		return 1;
	}
	
	//用户加入VPN
	public int insertProdInstGroup(Map itemMap, Map userMap, Message msg)
			throws Exception{
			List<Map<String, Object>> prodInstList = ordBillDao
					.selectOrdProdInstRel(itemMap);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
			// prodInstMap存放prod_inst对象
			Map prodInstMapRel = new HashMap();
			if(prodInstList.size()>0){
				prodInstMapRel = prodInstList.get(0);
				//
			}
			return 1;
	}
			
    /**
     * 检查工单先后顺序（新装用户不校验前置工单）
     *
     * @param itemMap
     * @param userMap
     * @param msg
     * @return
     * @throws Exception
     */
    public int workOrder(Map itemMap, Map userMap, Message msg)
            throws Exception {
        List<Map<String, Object>> ordBillList = ordBillDao.getOrdBill();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        Map<String, Object> map = new HashMap<String, Object>();
        Date effDate;
        Map<String, Object> ordBillMap = new HashMap<String, Object>();
        for (int i = 0; i < ordBillList.size(); i++) {
            ordBillMap = ordBillList.get(i);
            effDate = (Date) ordBillMap.get("FINISH_DATE");
            List<Map<String, Object>> ordBillProdInstList = ordBillDao.getOrdBillProdInst(ordBillMap);
            Map ordBillProdInstMap = new HashMap();
            Long prodInstId;
            for (int k = 0; k < ordBillProdInstList.size(); k++) {
                ordBillProdInstMap = ordBillProdInstList.get(k);
                prodInstId = (Long) ordBillProdInstMap.get("PROD_INST_ID");
                map.put("effDate", effDate);
                map.put("prodInstId", prodInstId);
                List<Map<String, Object>> resultList = ordBillDao.getOrdBillAndOrdBillProdInst(map);
                if (resultList.size() > 0) {
                    msg.setMessage("前面有处理不成功的工单,此工单暂时不处理:" + prodInstId);
                    return -1;
                }
            }
        }
			return 1;
	}
    //根据OFFER_INST_ID, OBJ_ID,OFFER_OBJ_REL_ID, EXP_DATE获取ID
    public long getOfferObjInstRelId(Map map){
    	try{
    		long OfferObjInstRelId=offerinstDao.getOfferObjInstRelId(map);
    		return OfferObjInstRelId;
    	}catch(Exception e){
    		e.printStackTrace();
    		return -1;
    	}	
    }
    //根据OFFER_INST_ID,ATTR_ID,ATTR_VALUE, EXP_DATE获取ID
    public long getOfferInstAttrId(Map map){
    	try{
    		long OfferInstAttrId=offerinstDao.getOfferInstAttrId1(map);
    		return OfferInstAttrId;
    	}catch(Exception e){
    		e.printStackTrace();
    		return -1;
    	}
    }

    /**
     * 从计费库中获取PROD_INST_SUB中的主键PROD_INST_ID。
     * @maozp3
     * @param map
     * @return
     */
    public long selectProdInstId(Map map){
        try {
            Long prodInstId = prodinstDao.selectProdInstIdFromProdInstSub(map);
            if(prodInstId > 0 ){
                return prodInstId;
            }
        }catch ( Exception e){
            e.printStackTrace();
        }
        return -1L;
    }
    
    /**
    * @Author maozp3
    * @Description: 从计费库中获取PROD_INST_ATTR中的主键PROD_INST_ATTR_ID。
    * @Date: 18:34 2019/4/2
    * @Param [map]
    * @return long
    **/
    public long selectProdInstAttrId(Map map){
        try{
            Long prodInstAttrId = prodinstDao.selectProdInstAttrIdFromProdInstAttr(map);
            if(prodInstAttrId > 0){
                return prodInstAttrId;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1L;
    }

    /**
    * @Author maozp3
    * @Description:从PROD_INST_ATTR_SUB表中获取PROD_INST_ATTR_ID，其中PROD_INST_ID来自PROD_INST_SUB表
    * @Date: 18:32 2019/4/2
    * @Param [map]
    * @return long
    **/
    public long selectProdInstAttrIdfromUnion(Map map){
        try{
            Long prodInstAttrId = prodinstDao.selectProdInstAttrIdFromProdInstAttrSub(map);
            if(prodInstAttrId > 0){
                return prodInstAttrId;
            }
        }catch ( Exception e){
            e.printStackTrace();
        }
        return -1L;
    }

    /**
    * @Author maozp3
    * @Description: 从PROD_INST_PAYMODE获取PAYMODE_ID
    * @Date: 19:51 2019/4/2
    * @Param [map]
    * @return long
    **/
    public long selectPaymodeId(Map map){
        try {
            Long paymodeId ;
            List<Long> paymodeIdList = prodinstDao.getProdInstPaymode_1(map);
            if(paymodeIdList.size()>0){
                paymodeId = paymodeIdList.get(0);
                return paymodeId;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1L;
    }

}
