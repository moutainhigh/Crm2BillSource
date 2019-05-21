package com.al.nppm.business.common.http;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;


public interface ICommonHttp {
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	public String getTest(HttpServletRequest req,@RequestBody String requestBody);	
	
	/**
	 * 
	 * @param req
	 * @return
	 */
	public String fetchTableData(HttpServletRequest req,@RequestBody String requestBody);	
		
}
