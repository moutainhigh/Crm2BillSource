package com.al.nppm.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestUtil {
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HttpRequestUtil.class);
	public static String callRemoteForPostByJSON(String remoteUrl, String json) throws Exception {
		// 设置访问地址remoteUrl
		HttpClient httpClient = new HttpClient();
		PostMethod post = new PostMethod(remoteUrl);
		String returnJson = null;
//		headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
//		headers.add(new Header("SOAPAction", "\"\""));
//		httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
		// 设置一些访问http服务的属性
		post.setRequestEntity(new StringRequestEntity(json, "application/json; charset=UTF-8", "UTF-8"));
		// 设置访问后台HTTP服务超时 120秒
		httpClient.getParams().setSoTimeout(30 * 1000);
		// httpClient.getParams().setParameter("bizsectype", "001");
		// 设置失败的时候，重试次数, 一般不重试,填0即可
		httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));
		// 调用远端的HTTP服务
		int result = httpClient.executeMethod(post);
		// LOG_OUTPUT.debug("HTTP返回码:" + result);
		// 获取返回字符串信息
		// retxml = post.getResponseBodyAsString(); //这个在大规模压测时可能会报错：000002ac
		// HttpMethodBas W org.apache.commons.httpclient.HttpMethodBase
		// getResponseBody Going to buffer response body of large or unknown
		// size. Using getResponseBodyAsStream instead is recommended.
		InputStream inputStream = post.getResponseBodyAsStream();
		InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
		returnJson = FileCopyUtils.copyToString(reader);
		logger.debug("返回报文："+returnJson);
		post.releaseConnection();
		return returnJson;
	}

	public static String callRemoteForPostByJSON(String remoteUrl, String json, Map<String,String> headers) throws Exception {
		// 设置访问地址remoteUrl
		HttpClient httpClient = new HttpClient();
		PostMethod post = new PostMethod(remoteUrl);
		String returnJson = null;
//		headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
//		headers.add(new Header("SOAPAction", "\"\""));
//		httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
		// 设置一些访问http服务的属性
		post.setRequestEntity(new StringRequestEntity(json, "application/json; charset=UTF-8", "UTF-8"));
		// 设置访问后台HTTP服务超时 120秒
		httpClient.getParams().setSoTimeout(120 * 1000);
		// httpClient.getParams().setParameter("bizsectype", "001");
		// 设置失败的时候，重试次数, 一般不重试,填0即可
		httpClient.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(2, false));
//		post.addRequestHeader();
		for (String keyName : headers.keySet()) {
			if(!keyName.toLowerCase().equals("content-length")){
				post.addRequestHeader(keyName,headers.get(keyName).toString());
			}
		}
		// 调用远端的HTTP服务
		int result = httpClient.executeMethod(post);
		// LOG_OUTPUT.debug("HTTP返回码:" + result);
		// 获取返回字符串信息
		// retxml = post.getResponseBodyAsString(); //这个在大规模压测时可能会报错：000002ac
		// HttpMethodBas W org.apache.commons.httpclient.HttpMethodBase
		// getResponseBody Going to buffer response body of large or unknown
		// size. Using getResponseBodyAsStream instead is recommended.
		InputStream inputStream = post.getResponseBodyAsStream();
		InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
		returnJson = FileCopyUtils.copyToString(reader);
		logger.debug("返回报文："+returnJson);
		post.releaseConnection();
		return returnJson;
	}

	public static void main(String[] args) {
//		String url="http://137.0.249.205:9700/ppm-service/service/cpc_ppm_qryAttrSpecByAttrId";
		PropertiesUtil propertiesUtil=new PropertiesUtil("config/sysConfig.properties");
		String url=propertiesUtil.readProperty("attr.url");
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonArray.add(jsonObject);
		jsonObject.put("attrId",100000899);
//		String reqData="[{\"attrId\":100000899},{\"attrId\":100000898}]";

//		String url="http://127.0.0.1:8080/billing/plca/tingfuji";
//		String reqData="{\n" +
//				"\t\"orderType\": \"1002\",\n" +
//				"\t\"chgState\" :[]\n" +
//				"}";

		Map<String,String> headers=new HashMap<String, String>();
//		headers.put("appId","1234");
//		headers.put("appKey","123456");
		try {
			String result = callRemoteForPostByJSON(url, jsonArray.toJSONString(),headers);
			System.out.println(result);
			JSONObject json=(JSONObject) JSONObject.parse(result);
			JSONObject data=json.getJSONObject("resultObject").getJSONObject("resObject");
            if(data!=null){
				System.out.println(data.toJSONString());
				JSONArray array=data.getJSONArray("attrValues");
				int size=array.size();
				for(int i=0;i<size;i++){
					JSONObject obj=(JSONObject)array.get(i);
//					if("100000899".equals(obj.getString("attrId"))&&){
//						System.out.println(obj.getString("attrValue"));
//					}
					//取值逻辑待确认
				}
			}

//			url="http://137.0.249.205:9700/intf-service/service/intf_serv_queryRentInfo";
//			JSONArray jsonArray=new JSONArray();
//			JSONObject jsonObject=new JSONObject();
//			jsonArray.add(jsonObject);
//			jsonObject.put("prodInstId","384074736223");
//			jsonObject.put("addupStates",new String[]{"N","S"});
//			String result = callRemoteForPostByJSON(url, jsonArray.toJSONString());
//			System.out.println(result);
//			JSONObject json=(JSONObject) JSONObject.parse(result);
//			if(!"0".equals(json.getString("resultCode"))){
//				System.out.println("调用接口失败");
//				return ;
//			}
//			JSONArray array=json.getJSONObject("resultObject").getJSONArray("rentList");
//			JSONObject resultJson=array.getJSONObject(0);
//			System.out.println("miniCharge:"+resultJson.getString("monthFeeLimit"));
//			System.out.println("contractOfferId:"+resultJson.getString("extOfferId"));
//			System.out.println("contractEffDate:"+resultJson.getString("startDate"));
//			System.out.println("contractExpDate:"+resultJson.getString("endDate"));
//			System.out.println("contractPeriod:"+resultJson.getString("agreementTime"));




		}catch (Exception ex){
			ex.printStackTrace();
		}
	}


	

}
