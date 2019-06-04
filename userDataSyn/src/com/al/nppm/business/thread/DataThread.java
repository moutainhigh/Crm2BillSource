package com.al.nppm.business.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.al.nppm.business.common.SpringContextHolder;
import com.al.nppm.business.inter.service.impl.CrmUserService;
import com.al.nppm.model.Message;
import com.al.nppm.ord.ordbill.dao.OrdBillMapper;

public class DataThread implements Callable<Boolean>{
	
	
	private Long archGrpId;
	private Message msg;
	private Map userMap;

		public DataThread(Long archGrpId,Message msg,Map userMap){
			this.archGrpId=archGrpId;
			this.msg=msg;
			this.userMap=userMap;
		}
		
		/* (non-Javadoc)
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public Boolean call() throws Exception {
			
			System.out.println("archGrpId:"+archGrpId);
			
//			WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
//			DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
//			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
//			TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
			
			CrmUserService crmUserService = (CrmUserService)SpringContextHolder.getBean("crmUserService"); 
			int flag =0;
			int iCount = -1;
			crmUserService.runTask1(archGrpId, msg, userMap);
//			try {
//				
//				flag=crmUserService.CrmUserTable(archGrpId, msg, userMap);
//				if (flag < 0) {
//					// 回滚
//					transactionManager.rollback(status);
//				} else {
//					transactionManager.commit(status);
//				}
//
//				// 3.提交处理结果
//				iCount = tranManager(archGrpId, msg.getMessage());
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				transactionManager.rollback(status);
//				iCount = tranManager(archGrpId, msg.getMessage());
//				e.printStackTrace();
//				return false;
//			}finally{
//				
//			}
			return true;
		}
		
		
		public int tranManager(Long id, String notes) throws Exception {

			WebApplicationContext contextLoader = ContextLoader
					.getCurrentWebApplicationContext();
			DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader
					.getBean("transactionManager");
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
			TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

			OrdBillMapper ordBillDao = SpringContextHolder.getBean("ordBillMapper"); 
			int i = -1;
			try {
				// 逻辑代码
				Map map = new HashMap();
				map.put("procFlag", 1);
				// map.put("procCnt",1);
				map.put("archGrpId", id);
				map.put("notes", notes);
//				i = ordBillDao.updateOrdBill(map);
				transactionManager.commit(status);

			} catch (Exception e) {
				e.printStackTrace();
				throw e;
				// transactionManager.rollback(status);
			}
			return i;
		}
	}
