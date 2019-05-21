package com.al.nppm.business.account.http;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;



public interface IAccountHttp {
	
	public String getPricingParameterTree(HttpServletRequest req,@RequestBody String requestBody);	
}
