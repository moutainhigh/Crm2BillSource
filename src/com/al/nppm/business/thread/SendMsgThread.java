package com.al.nppm.business.thread;

import com.al.nppm.business.common.SpringContextHolder;
import com.al.nppm.business.inter.service.impl.CrmUserService;

import java.util.Map;
import java.util.concurrent.Callable;

public class SendMsgThread implements Callable<Boolean>{
	
	
	private Map synMap;
	private long archGrpId;

		public SendMsgThread(Map synMap, long archGrpId){
			this.synMap=synMap;
			this.archGrpId = archGrpId;
		}
		
		/* (non-Javadoc)
		 * @see java.util.concurrent.Callable#call()
		 */
		@Override
		public Boolean call() throws Exception {
			CrmUserService crmUserService = (CrmUserService)SpringContextHolder.getBean("crmUserService"); 
			int flag =0;
			int iCount = -1;
			crmUserService.sendMsg(synMap, archGrpId);
			return true;
		}
		
		
		
	}
