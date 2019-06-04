package com.al.nppm.test;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.impl.SLF4JLocationAwareLog;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 
 * @author chenhy  20180530 
 * 注意：
 * 
 *  使用 httpclient 需要将 jcl-over-slf4j-1.6.4.jar 删除    
 */

public class httpClient {

	public static void main(String[] args){ 
		
		String url = "http://10.6.13.142:8080/userService/inter/userService";
		String param = "{'ARCH_GRP_ID': 1,'ORDER_ITEM_ID': 1,'ACCT_ID':111,'CUST_ID':1000,'ACCOUNT':{'operType':1000,'acctId':1219,'acctName':'111','acctCd':'codi','custId':1965,'acctLoginName':'123','loginPassword':'13','acctBillingType':1,'prodInstId':1,'effDate':'2018-03-22 15:46:10','expDate':'2018-03-22 15:46:10','statusCd':12,'statusDate':'2018-03-22 15:46:10','createStaff':123,'createDate':'2018-03-22 15:46:10','updateStaff':123,'updateDate':'2018-03-22 15:46:10','remark':'test','regionId':1,'groupAcctId':'sdf'}}";
		JSONObject jobj = JSONObject.fromObject(param);
		
		 JSONObject jsonObject = doPost(url,jobj);
	     System.out.println(jsonObject.toString());
    }
	
 public static JSONObject doPost(String url,JSONObject json){
         DefaultHttpClient client = new DefaultHttpClient();
         HttpPost post = new HttpPost(url);
         JSONObject response = null;
         try {
             StringEntity s = new StringEntity(json.toString());
             s.setContentEncoding("UTF-8");
             s.setContentType("application/json");
             post.setEntity(s);
             HttpResponse res = client.execute(post);
             if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                 HttpEntity entity = res.getEntity();
                 String result = EntityUtils.toString(res.getEntity());
                 response = JSONObject.fromObject(result);
             }
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
         return response;
     }

}
