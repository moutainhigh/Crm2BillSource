package com.al.nppm.business.account.http.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.al.nppm.business.account.http.IAccountHttp;
import com.al.nppm.business.account.service.IAccountService;
import com.al.nppm.common.aop.DataSourceContext;
import com.al.nppm.common.restservice.ResponseUtils;

@Controller
@RequestMapping(value="account")
public class AccountHttp implements IAccountHttp {

	@Autowired
	private IAccountService accountService;
	
	
/*	@ResponseBody
	@RequestMapping(value ="/getAllPimplTemplate",method = RequestMethod.POST )
	public String getAllPimplTemplate(HttpServletRequest req,@RequestBody String requestBody) {
		
	}*/
	
	@ResponseBody
	@RequestMapping(value ="/getPricingParameterTree",method = RequestMethod.POST )
	public String getPricingParameterTree(HttpServletRequest req,@RequestBody String requestBody) {
		JSONObject jsonObject = JSONObject.fromObject(requestBody);
		List<Map<String,Object>> returnMap = accountService.getPricingParameterTree(jsonObject);
		JSONArray configOrderJa = JSONArray.fromObject(returnMap);
		return ResponseUtils.success(configOrderJa);
	}

}
