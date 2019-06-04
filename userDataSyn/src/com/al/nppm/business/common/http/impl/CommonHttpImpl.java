package com.al.nppm.business.common.http.impl;

import java.util.HashMap;
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

import com.al.nppm.business.common.http.ICommonHttp;
import com.al.nppm.business.common.service.ICommonService;
import com.al.nppm.common.restservice.ResponseUtils;
import com.al.nppm.common.utils.StringUtil;
import com.al.nppm.model.Offer;

import com.github.pagehelper.Page;



@Controller
@RequestMapping(value="common")
public class CommonHttpImpl implements ICommonHttp {
	
	@Autowired
	private ICommonService commonService;

	@ResponseBody
	@RequestMapping(value ="/getTest",method = RequestMethod.POST )
	public String getTest(HttpServletRequest req,@RequestBody String requestBody) {
		//D:\product\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\nppm-bill-service
		
		JSONObject jsona = new JSONObject();
		try {
			System.out.println("requestBody:"+requestBody);
			JSONObject jsonObject = JSONObject.fromObject(requestBody);
			if(StringUtil.isEmpty(jsonObject))
			{
			  
			}
			System.out.println("11111111111111"+jsonObject.getString("masterObject"));
			JSONArray arry = jsonObject.getJSONArray("masterObject");
			for(int i=0;i<arry.size();i++)
			{
				JSONObject job = arry.getJSONObject(i);
				System.out.println("array"+job.getString("masterID"));
				Offer of = new Offer();
				of.setOfferName(job.getString("masterID"));
				of.setOfferId(Long.parseLong("111"));
				commonService.insertProductOffer(of);
			}
			Map<String,Object> returnMap = commonService.getTest(new HashMap<String, Object>());
			JSONArray configOrderJa = JSONArray.fromObject(returnMap);
			//System.out.println("11111111111111"+configOrderJa.getString(index));
			//jsona.accumulate("data", configOrderJa);
		
			return ResponseUtils.success(jsona);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value ="/fetchTableData",method = RequestMethod.POST )
	public String fetchTableData(HttpServletRequest req,@RequestBody String requestBody) {
		JSONObject jsona = new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(requestBody);
			Page<Map<String, Object>> returnMap = commonService.fetchTableData(jsonObject);
			JSONArray configOrderJa = JSONArray.fromObject(returnMap);
			jsona.accumulate("data", configOrderJa);
			jsona.accumulate("total", returnMap.getTotal());
			return ResponseUtils.success(jsona);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
