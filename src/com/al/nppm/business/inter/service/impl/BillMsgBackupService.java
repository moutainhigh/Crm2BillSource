package com.al.nppm.business.inter.service.impl;

import com.al.nppm.business.account.dao.IProdInstMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("billMsgBackupService")
public class BillMsgBackupService {
	private static Logger logger = Logger.getLogger(BillMsgBackupService.class);
//	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

	@Autowired
	private IProdInstMapper prodInstMapper;
	
	public void backupData() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		HashMap<String, String> map=new HashMap<String, String>();
		List<Map<String, Object>> list = prodInstMapper.selectSendMsgSucc(map);//获取发送成功消息
		if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
            	WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                try{
	                Map msgMap = (Map) list.get(i);
	                String month=sdf.format(msgMap.get("createDate"));
					msgMap.put("month",month);
					msgMap.remove("msg");
					prodInstMapper.insertSendMsgHis(msgMap);
					prodInstMapper.deleteSendMsg(msgMap);
	                transactionManager.commit(status);
	            } catch (Exception e) {
	            	e.printStackTrace();
	                transactionManager.rollback(status);
	                logger.error(e.getMessage());
	            }
            }
		}
	}
}
