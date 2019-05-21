package com.al.nppm.business.thread;

import java.util.Map;
import java.util.concurrent.Callable;

import com.al.nppm.business.common.SpringContextHolder;
import com.al.nppm.business.inter.service.impl.CrmUserService;

public class SendMsgThread implements Callable<Boolean>{
	
	
	private Map synMap;

		public SendMsgThread(Map synMap){
			this.synMap=synMap;
		}
		
		/* (non-Javadoc)
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public Boolean call() throws Exception {
			CrmUserService crmUserService = (CrmUserService)SpringContextHolder.getBean("crmUserService"); 
			int flag =0;
			int iCount = -1;
			crmUserService.sendMsg(synMap);
			return true;
		}
		
		
		
	}
