package com.al.nppm.common.init;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.al.nppm.business.account.service.IAccountService;
import com.al.nppm.business.inter.service.impl.InterService;
import com.al.nppm.model.BillServiceLog;



@Service
public class serviceTest implements InitializingBean{

      @Autowired
  	  private InterService  interService;
      @Autowired
  	  private IAccountService accountService;
      
      @Override
      public void afterPropertiesSet() throws Exception {
    	  
    	long id =1;
    	String message ="test";
//  		while(1==1)
//  		{
//  			try{
//  				//select * from ord_bill
//  				 id=id+1;
//  				 tranManager(id, message);
//  				 List<Map<String,Object>> list = interService.selectOrdBill();
//  				 for(int i=0;i<list.size();i++)
//  				 {
//  					 Map map = list.get(i);
//  					 System.out.println(i+"------"+map.get("ARCH_GRP_ID"));
//  				 }
//  				System.out.println("--测试 ---------------------------------------");
//  			}catch (Exception e) {
//  			   e.printStackTrace();
//  				System.out.println("--异常---------------------------------------");
//  			}
//  		 }
      
      }
      
      public  void  tranManager(Long id,String message){
  		
  		
  		WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
  		DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
  				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
  				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
  				TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
  				
  				try {
  				//逻辑代码
  					BillServiceLog slog = new BillServiceLog();
  					
  					slog.setId(id);
  					slog.setFlag(1);//未处理
  					slog.setMessage(message);
  					accountService.insertServiceLog(slog);
  					
  					transactionManager.commit(status);
  					
  				} catch (Exception e) {
  				transactionManager.rollback(status);
  				
  				}	
  		
  	}

}