package com.al.nppm.business.inter.service.impl;

import com.al.nppm.business.account.dao.CpcMapper;
import com.al.nppm.ord.ordbill.dao.OrdBillBackupMapper;
import com.al.nppm.ord.ordbill.dao.OrdBillHisMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("ordBackupService")
public class OrdBackupService {
	private static Logger logger = Logger.getLogger(OrdBackupService.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config/tableColumns");
//	private static PropertiesUtil propertiesUtil=new PropertiesUtil("config/tableColumns.properties");
//	private static String taskId =bundle.getString("taskId");

	@Autowired
	private OrdBillHisMapper ordBillHisMapper;
	@Autowired
	private OrdBillBackupMapper ordBillBackupMapper;
    @Autowired
    private CpcMapper cpcDao;
	
	public void backupData() {
		HashMap<String, String> map=new HashMap<String, String>();
		List<Map<String, Object>> list = ordBillHisMapper.selectOrdBill(map);//获取总控表数据
		if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
            	WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager_ord");
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                try{
	                Map ordmap = (Map) list.get(i);
	                
	                Map queryMap=new HashMap<String, Object>();
	                queryMap.put("ARCH_GRP_ID",ordmap.get("ARCH_GRP_ID"));
					queryMap.put("FINISH_DATE",ordmap.get("FINISH_DATE"));

					//如果归档组下存在ONE_ITEM_RESULT.IF_CHARGE_OFF = 1且PROC_FLAG   != 1的数据，则不迁移,继续下一个归档组
                    if(!checkOneItemResult(queryMap)){
                        continue;
                    }
					//如果归档组下存在ORD_OFFER_INST是租机套餐，且STATUS_CD！=1的数据，则不迁移
                    if(!checkOrdOfferInst(queryMap)){
                        continue;
                    }

					backupOrdTable(queryMap);
	                
//	                backupOrdBill(ordmap);
	                
//	                List<Map<String, Object>> billObjList =ordBillHisMapper.selectOrdBillObj(ordmap);
//	                if (billObjList.size() > 0) {
//	                    for (int j = 0; j < billObjList.size(); j++) {
//	                    	Map objMap=billObjList.get(j);
//	                    	queryMap.put("TABLE_NAME", objMap.get("TABLE_NAME"));
//	                    	queryMap.put("ORDER_ITEM_ID", objMap.get("ORDER_ITEM_ID"));
//	                    	backupOrdTable(queryMap);
//	                    	backupOrdBillObj(objMap);
//	                    }
//	                }
	                
//	                List<Map<String, Object>> billProdInstList =ordBillHisMapper.selectOrdBillProdInst(ordmap);
//	                if (billProdInstList.size() > 0) {
//	                    for (int k = 0; k < billProdInstList.size(); k++) {
//	                    	backupOrdBillProdInst(billProdInstList.get(k));
//	                    }
//	                }
	                	
	                transactionManager.commit(status);
	            } catch (Exception e) {
	            	e.printStackTrace();
	                transactionManager.rollback(status);
	                logger.error(e.getMessage());
	            }
            }
		}
	}
	
	/**
	 * 迁移OrdBill表
	 */
	public int backupOrdBill(Map ordMap)
            throws Exception {
		 ordBillHisMapper.insertOrdBillHis(ordMap);
		 ordBillHisMapper.deleteOrdBill(ordMap);
		 return 1;
	}
	
	/**
	 * 迁移OrdBill表
	 */
	public int backupOrdBillObj(Map ordMap)
            throws Exception {
		 ordBillHisMapper.insertOrdBillObjHis(ordMap);
		 ordBillHisMapper.deleteOrdBillObj(ordMap);
		 return 1;
	}
	
	/**
	 * 迁移OrdBillProdInst表
	 */
	public int backupOrdBillProdInst(Map ordMap)
            throws Exception {
		 ordBillHisMapper.insertOrdBillProdInstHis(ordMap);
		 ordBillHisMapper.deleteOrdBillProdInst(ordMap);
		 return 1;
	}
	
	public int backupOrdTable(Map queryMap) throws Exception {
		String month=sdf.format(queryMap.get("FINISH_DATE"));
		queryMap.put("MONTH",month);
		List<String> tableNames= Arrays.asList(bundle.getString("tableList").split(","));
		if (tableNames.size() > 0) {
			for(String table:tableNames){
				System.out.println(table);
				queryMap.put("TABLE_NAME",table);

				List<Map<String, Object>> list = ordBillHisMapper.selectOrdTable(queryMap);
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						Map ordmap = (Map) list.get(i);
						ordmap.put("TABLE_NAME", queryMap.get("TABLE_NAME"));
						ordmap.put("MONTH", month);
						ordBillBackupMapper.insertOrdTableHis(ordmap);
						ordBillHisMapper.deleteOrdTable(ordmap);
					}
				}


			}
		}

//		List<Map<String, Object>> list = ordBillHisMapper.selectOrdTable(queryMap);
//		if (list.size() > 0) {
//			for (int i = 0; i < list.size(); i++) {
//				Map ordmap = (Map) list.get(i);
//				ordmap.put("TABLE_NAME", queryMap.get("TABLE_NAME"));
//				ordBillHisMapper.insertOrdTableHis(ordmap);
//				ordBillHisMapper.deleteOrdTable(ordmap);
//			}
//		}
		return 1;
	}

	private boolean checkOneItemResult(Map  queryMap){
        //如果归档组下存在ONE_ITEM_RESULT.IF_CHARGE_OFF = 1且PROC_FLAG   != 1的数据，则不迁移
        boolean flag=true;
        List<Map<String,Object>> oneItemResultList=ordBillHisMapper.selectOneItemResult(queryMap);
        if(oneItemResultList.size()>0){
            for(Map oneItemMap:oneItemResultList){
                if(!"1".equals(oneItemMap.get("procFlag"))){
                    flag=false;
                    break;
                }
            }
        }
        return  flag;
    }


    private boolean checkOrdOfferInst(Map  queryMap){
        //如果归档组下存在ORD_OFFER_INST是租机套餐，且STATUS_CD！=1的数据，则不迁移
        boolean flag=true;
        List<Map<String,Object>> OrdOfferInstList=ordBillHisMapper.selectOrdOfferInst(queryMap);
        if(OrdOfferInstList.size()>0){
            for(Map offerInstMap:OrdOfferInstList){
//                if(!"1".equals(oneItemMap.get("PROC_FLAG").toString())){
//                    flag=false;
//                    break;
//                }
                //租机套餐配置在OFFER_CATALOG_LOCATION表中
                Long count = cpcDao.getCountFromOfferCatalogLocation(offerInstMap);
                if (count>0&&!"1".equals(offerInstMap.get("statusCd"))){
                    flag=false;
                    break;
                }
            }
        }
        return  flag;
    }

}
