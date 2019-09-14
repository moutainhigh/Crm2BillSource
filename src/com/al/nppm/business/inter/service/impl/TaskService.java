package com.al.nppm.business.inter.service.impl;

import com.al.nppm.business.account.service.impl.TifTaskCfgService;
import com.al.nppm.business.common.SpringContextHolder;
import com.al.nppm.business.inter.service.ITaskService;
import com.al.nppm.common.utils.PropertiesUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

@Service("taskService")
public class TaskService implements ITaskService{
	private static Logger logger = Logger.getLogger(TaskService.class);
	
	private static PropertiesUtil propertiesUtil=new PropertiesUtil("config/sysConfig.properties");
	private static String taskId =propertiesUtil.readProperty("taskId");

	@Autowired
	public TifTaskCfgService tifTaskCfgService;
	
	public void scanTask() {
		//ip
		//taskId
		String ip=getLocalIPForJava();
		Map map=new HashMap<String, Object>();
		if(StringUtils.isNotBlank(taskId)){
			map.put("TASK_ID", taskId);
		}else{
//			map.put("SERVER_IP", ip);
		}
		
		
		
		List list=tifTaskCfgService.getTifTaskCfgList(map);
		String groupName="DEFAULT";
		
		Iterator<HashMap<String, Object>> it =list.iterator();
		HashMap<String, Object> taskMap=new HashMap<String, Object>();
		HashMap<String, String> jobMap=new HashMap<String, String>();
		while(it.hasNext()){
			taskMap=it.next();
			String isStart=(String)taskMap.get("isStart");
			String jobName=(String)taskMap.get("jobName");
			if("0".equals(isStart)){//停止
				stop(taskMap);
			}else if("1".equals(isStart)){//启动
				start(taskMap);
			}
			jobMap.put(jobName, isStart);
		}
		StdScheduler schedulerFactory = (StdScheduler) SpringContextHolder
				.getBean("taskcfg");
		
		try {
			String[] str=schedulerFactory.getJobNames(groupName);
			for(int i=0;i<str.length;i++){
				if(!jobMap.containsKey(str[i])){//表中不存在的需要移除
					schedulerFactory.deleteJob(str[i], groupName);
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
				
	}
	
	private void start(HashMap<String, Object> map){
		StdScheduler schedulerFactory = (StdScheduler) SpringContextHolder
				.getBean("taskcfg");
		try {
			String groupName="DEFAULT";
			String jobName=(String)map.get("jobName");
			String targetobject=(String)map.get("targetobject");
			String targetmethod=(String)map.get("targetmethod");
			String arguments=(String)map.get("arguments");
			String[] args = { };
			if(StringUtils.isNotBlank(arguments)){
				args=arguments.split("\\|");
			}
			String cronExpression=(String)map.get("cronExpression");
		
			MethodInvokingJobDetailFactoryBean f = new MethodInvokingJobDetailFactoryBean();
			f.setName(jobName);
			f.setConcurrent(false);
			f.setTargetObject(SpringContextHolder.getBean(targetobject));
			f.setTargetMethod(targetmethod);			
			f.setArguments(args);
			f.afterPropertiesSet();
			JobDetail job = f.getObject();
			
			CronTriggerBean c = new CronTriggerBean();
			c.setJobDetail(job);
			c.setCronExpression(cronExpression);
			c.setName(jobName+"_Trigger");
			
			JobDetail old_job=schedulerFactory.getJobDetail(jobName, groupName);
			
			
//			CronTriggerBean t=(CronTriggerBean) schedulerFactory.getTrigger(jobName+"_Trigger", groupName);
		    if(old_job!=null){
//		    	schedulerFactory.pauseJob(jobName, groupName);
//		    	schedulerFactory.deleteJob(jobName, groupName);
//		    	schedulerFactory.scheduleJob(job, c);
		    	
		    	MethodInvokingJobDetailFactoryBean f1=(MethodInvokingJobDetailFactoryBean) old_job.getJobDataMap().get("methodInvoker");
		    	CronTriggerBean t=(CronTriggerBean) schedulerFactory.getTrigger(jobName+"_Trigger", groupName);
//		    	t.getCronExpression();
		    	
//		    	f1.getArguments().

				String[] agrs1=new String[]{};
				if(f1.getArguments().length>0){
					 agrs1=(String[]) f1.getArguments()[0];
				}
		    	
		    	
		    	if(!Arrays.equals(args, agrs1)||!t.getCronExpression().equals(cronExpression)){
		    		schedulerFactory.deleteJob(jobName, groupName);
		    		schedulerFactory.scheduleJob(job, c);
				}else{
					int state=schedulerFactory.getTriggerState(jobName+"_Trigger", groupName);
			    	if(state==0){//状态正常不用做处理
			    		
			    	}else if(state==1){//暂停就恢复
			    		schedulerFactory.resumeTrigger(jobName+"_Trigger", groupName);
			    	}
			    	else{
			    		schedulerFactory.deleteJob(jobName, groupName);
			    		schedulerFactory.scheduleJob(job, c);
			    	}
				}
		    	
		    }else{
		    	schedulerFactory.scheduleJob(job, c);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 
		
	}
	
	private void stop(HashMap<String, Object> map){
		StdScheduler schedulerFactory = (StdScheduler) SpringContextHolder
				.getBean("taskcfg");
		try {
			String jobName=(String)map.get("jobName");
			String groupName="DEFAULT";
//			schedulerFactory.pauseJob(jobName, groupName);
//			schedulerFactory.deleteJob(jobName, groupName);
			schedulerFactory.pauseTrigger(jobName+"_Trigger", groupName);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	
	public String getLocalIPForJava(){
	    try {
	    	Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); 
	        while (en.hasMoreElements()) {
	            NetworkInterface intf = (NetworkInterface) en.nextElement();
	            Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
	            while (enumIpAddr.hasMoreElements()) {
	                 InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
	                 if (!inetAddress.isLoopbackAddress()  && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
	                	 return inetAddress.getHostAddress();
	                 }
	             }
	          }
	    } catch (SocketException e) {  }
	    return null;
	}
	

}
