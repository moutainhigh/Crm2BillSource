package com.al.nppm.business.inter.service.impl;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.al.nppm.business.account.service.impl.TifTaskCfgService;
import com.al.nppm.business.common.SpringContextHolder;
import com.al.nppm.business.inter.service.ITaskService;
import com.al.nppm.common.utils.PropertiesUtil;
import com.al.nppm.model.Message;
import com.al.nppm.ord.ordbill.dao.OrdBillHisMapper;
import com.ctg.mq.api.IMQProducer;

@Service("ordBackupService")
public class OrdBackupService {
	private static Logger logger = Logger.getLogger(OrdBackupService.class);

	private static PropertiesUtil propertiesUtil=new PropertiesUtil("config/tableColumns.properties");
	private static String taskId =propertiesUtil.readProperty("taskId");

	@Autowired
	private OrdBillHisMapper ordBillHisMapper;
	
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
	                
	                backupOrdBill(ordmap);
	                
	                List<Map<String, Object>> billObjList =ordBillHisMapper.selectOrdBillObj(ordmap);
	                if (billObjList.size() > 0) {
	                    for (int j = 0; j < billObjList.size(); j++) {
	                    	Map objMap=billObjList.get(j);
	                    	queryMap.put("TABLE_NAME", objMap.get("TABLE_NAME"));
	                    	queryMap.put("ORDER_ITEM_ID", objMap.get("ORDER_ITEM_ID"));
	                    	backupOrdTable(queryMap);
	                    	backupOrdBillObj(objMap);
	                    }
	                }
	                
	                List<Map<String, Object>> billProdInstList =ordBillHisMapper.selectOrdBillProdInst(ordmap);
	                if (billProdInstList.size() > 0) {
	                    for (int k = 0; k < billProdInstList.size(); k++) {
	                    	backupOrdBillProdInst(billProdInstList.get(k));
	                    }
	                }
	                	
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
		List<Map<String, Object>> list = ordBillHisMapper.selectOrdTable(queryMap);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map ordmap = (Map) list.get(i);
				ordmap.put("TABLE_NAME", queryMap.get("TABLE_NAME"));
				ordBillHisMapper.insertOrdTableHis(ordmap);
				ordBillHisMapper.deleteOrdTable(ordmap);
			}
		}
		return 1;
	}

}
