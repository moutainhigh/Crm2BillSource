package com.al.nppm.business.inter.http.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.al.nppm.business.account.service.IAccountService;
import com.al.nppm.business.account.service.IProdInstService;
import com.al.nppm.business.common.SpringContextHolder;
import com.al.nppm.business.inter.http.IinterHttp;
import com.al.nppm.business.inter.service.IinterService;
import com.al.nppm.business.inter.service.impl.CrmUserService;
//import com.al.nppm.business.inter.service.impl.OrdService;
import com.al.nppm.business.syntomq.datasyn.DataSynDeal;
import com.al.nppm.common.utils.JsonUtil;
import com.al.nppm.common.utils.StringUtil;
import com.al.nppm.model.BillServiceLog;
import com.al.nppm.model.Message;

@Controller
@RequestMapping(value="billing")
public class interHttp  implements IinterHttp{

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IProdInstService prodService;
	@Autowired
	private IinterService interService;
	@Autowired
	private CrmUserService  crmUserService;
	
//	@Autowired
//	private OrdService ordService;
	
	@ResponseBody
	@RequestMapping(value ="/user/{id}",method = RequestMethod.GET )
	public String httpService(@PathVariable("id") String id) throws SchedulerException {
		System.out.println(id+"------------------------");
		return id; 
	
	}
	
	@ResponseBody
	@RequestMapping(value ="/stop",method = RequestMethod.GET )
	public String stop(@RequestParam("jobName") String jobName,@RequestParam(value="groupName",required=false) String  groupName) throws SchedulerException {
		StdScheduler   schedulerFactory=(StdScheduler )SpringContextHolder.getBean("sfb");
		if(StringUtil.isEmpty(groupName)){
			groupName="DEFAULT";
		}
		schedulerFactory.pauseJob(jobName, groupName);
//		scheduler.pauseAll() groupName
//		sfb.getScheduler()
		
//		sfb.pauseJob("fileTaskJob", "");
		
		return "true"; 
	}
	
	@ResponseBody
	@RequestMapping(value ="/start",method = RequestMethod.GET )
	public String start(@PathVariable("jobName") String jobName,@PathVariable("groupName") String  groupName ) throws SchedulerException {
		StdScheduler   schedulerFactory=(StdScheduler )SpringContextHolder.getBean("sfb");
		if(StringUtil.isEmpty(groupName)){
			groupName="DEFAULT";
		}
		schedulerFactory.resumeJob(jobName, groupName);
		
//		schedulerFactory.addJob(jobDetail, replace)
//		jobDetail
//		scheduler.pauseAll()
//		sfb.getScheduler()
		
//		sfb.pauseJob("fileTaskJob", "");
		
		return "true"; 
	}
	
	@ResponseBody
	@RequestMapping(value ="/plca/DataSyn",method = RequestMethod.POST )
	public String httpService(HttpServletRequest req,@RequestBody String requestBody ) { 
		String reqServ = req.getLocalAddr()+":"+req.getLocalPort()+req.getContextPath()+req.getServletPath();
		
		List<?> tableList= new ArrayList();
		BillServiceLog slog = new BillServiceLog();
		int flag=0;
		System.out.println(reqServ);
		System.out.println("json: "+requestBody);
		Message msg = new Message();
		try{
		//JSON格式验证
		if(!JsonUtil.parseJson(requestBody))
		{
			msg.setFlag(400);
			msg.setMessage("JSON格式不正确");

			insertTranManager( slog, reqServ, requestBody , msg);
			return JsonUtil.returnObjMsg(0,"JSON格式不正确","400");
			//return JsonUtil.returnObj(400, "JSON格式不正确");
		}
		//空json直接返回		
		JSONObject jsonObject = JSONObject.fromObject(requestBody);
		if(jsonObject.isNullObject())
		{
			
			msg.setMessage("JSON为空对象");
			msg.setFlag(400);
			insertTranManager( slog, reqServ, requestBody , msg);
			//return JsonUtil.returnObj(400, "JSON为空对象");	
			return JsonUtil.returnObjMsg(0,"JSON为空对象","400");
		}		
		   //业务处理
			flag=interService.userDataSynCol(requestBody,msg,tableList);
		
		}catch(Exception e){
		   e.printStackTrace();
		  
		   insertTranManager( slog, reqServ, requestBody , msg);
		   return JsonUtil.returnObjMsg(msg.serviceId,msg.resultMsg,msg.resultCode);
		
		}
		
		//发消息
		try {
			if(flag>0)
			{
				DataSynDeal.APISendMsg( tableList );
			}
		} catch (Exception e) {
			System.out.println("message fail .......................");
			msg.setResultMsg("入库成功，发送异常，请检查连接配置");
			msg.setResultCode("400");
			e.printStackTrace();
			insertTranManager( slog, reqServ, requestBody , msg);
			 return JsonUtil.returnObjMsg(msg.serviceId,msg.resultMsg,msg.resultCode);
			
		}
		//20180426
		try {
			insertTranManager( slog, reqServ, requestBody , msg);	
		} catch (Exception e) {
			e.printStackTrace();
			msg.setResultCode("400");
			msg.setResultMsg("入库成功，发送异常，日志异常");
			insertTranManager( slog, reqServ, requestBody , msg);
			
		}

	  return JsonUtil.returnObjMsg(msg.serviceId,msg.resultMsg,msg.resultCode);
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
					accountService.updateServiceLog(slog);	
					
					transactionManager.commit(status);
					
				} catch (Exception e) {
					e.printStackTrace();
				transactionManager.rollback(status);
				
				}	
		
	}


@ResponseBody
@RequestMapping(value ="/userServiceTest",method = RequestMethod.POST )
public String test(HttpServletRequest req,@RequestBody String requestBody) { 
	
	try{
//		crmUserService.runTask1();
	
	}catch (Exception e) {
		e.printStackTrace();
		
		}	
	
 return null;
}

//20180411 改为插入日志
public  void  insertTranManager(BillServiceLog slog,String reqServ,String requestBody ,Message msg) {
	
	WebApplicationContext contextLoader = ContextLoader.getCurrentWebApplicationContext();
	DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) contextLoader.getBean("transactionManager");
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
			TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
			try {
			//逻辑代码
				
				//long id = accountService.getServiceLogId();
				//long id=accountService.getBillServiceLogId();
			
				slog.setId(1001L);
				slog.setService(reqServ);
				slog.setJson(requestBody);
				slog.setFlag(msg.getFlag());//已处理
				slog.setMessage(msg.resultMsg);
				slog.setServiceId(msg.getServiceId());
				accountService.insertServiceLog(slog);	
				transactionManager.commit(status);
				
			} catch (Exception e) {
				e.printStackTrace();
			transactionManager.rollback(status);
			msg.setMessage("插入接口失败");
			//throw e;

			//return JsonUtil.returnObj(400, "插入接口失败");
			}	
}

}
