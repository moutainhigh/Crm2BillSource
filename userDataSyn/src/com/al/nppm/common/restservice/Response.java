package com.al.nppm.common.restservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.PropertyNameProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.al.nppm.common.exception.ApiErrorList;
import com.al.nppm.common.utils.JsonDateValueProcessor;


/**
 * 封装反馈的报文
 * {
    "meta": {
        "statusCd": "200",   // 状态码
        "message",""		 // 异常信息
    },
    "data": ""				// 业务数据
	}
 * @author wucl5
 */
public class Response implements Serializable{

   private static final long serialVersionUID = 1L;
	
   private static final String OK = "200";
   private static final String successMsg = "OK";
   private static final String ERROR = "500";
   
   // 元数据
   private Meta meta;
   // 返回值
   private Object info;
   
   private Logger LOGGER = LoggerFactory.getLogger(Response.class);
   
   public Response success(){
	   this.meta = new Meta(successMsg, OK);
	   return this;
   }
   
   public Response success(String message){
	   this.meta = new Meta(message, OK);
	   return this;
   }
   
   public Response success(String message,String statusCd){
	   this.meta = new Meta(message, statusCd);
	   return this;
   }
   
   @SuppressWarnings("unchecked")
   public Response success(Object data){
	   this.meta = new Meta(successMsg, OK);

	   JsonConfig config = new JsonConfig();
	   config.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
	   // config.registerJavaPropertyNameProcessor(boolean.class,new JsonBooleanValueProcessor());
	   
	   if(data instanceof List){
		   data = JSONArray.fromObject(data, config);
	   }else if(data instanceof JSONObject){
		   JSONArray ja = new JSONArray();
		   ja.add(data);
		   data = JSONArray.fromObject(ja, config);
	   }else{
		   data = JSONObject.fromObject(data, config);
	   }

	   this.info = data;
	   return this;
   }
   
   @SuppressWarnings("unchecked")
   public Response successNew(Object data){
	   this.meta = new Meta(successMsg, OK);

	   JsonConfig config = new JsonConfig();
	   config.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
	   // config.registerJavaPropertyNameProcessor(boolean.class,new JsonBooleanValueProcessor());
	   
	   /*{
		    "info": {
		        "data": "",
		        "total": ""
		    },
		    "meta": {
		        "message": "OK",
		        "statusCd": "200"
		    }
	   }*/
	   JSONObject info = new JSONObject();
	   if(data instanceof List){
		   data = JSONArray.fromObject(data, config);
		   info.accumulate("data", data);
	   }else{
		   JSONObject jo = JSONObject.fromObject(data, config);
		   data = jo;
		   info.accumulate("data", data);
		   // 非列表查询时,这里统一对传入对象增加一层data.而列表时,判断total,将传入对象直接返回.
		   if(jo.containsKey("total")){
			   info = jo;
		   }
	   }
	   this.info = info;
	   return this;
   }
   
   public Response failure(String message){
	   this.meta = new Meta( message, ERROR);
	   this.info = null;
	   return this;
   }
   
   public Response failure(String message,String statusCd){
	   this.meta = new Meta(message, statusCd);
	   this.info = null;
	   return this;
   }
   
   public Response failure(ApiErrorList errorList){
	   this.meta = new Meta(errorList.getDesc(),errorList.getCode()+"");
	   this.info = null;
	   return this;
   }
   
//   public Response failure(ApiErrorList errorList, Object data){
//	   
//	   this.meta = new Meta(errorList.getDesc(),errorList.getCode()+"");
//	   JsonConfig config = new JsonConfig();
//	   config.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
//	   data = JSONObject.fromObject(data, config);
//	   this.data = data;
//	   return this;
//   }
   
   public Meta getMeta() {  
       return meta;  
   }  

   public Object getInfo() {  
       return info;  
   }  
   
   
   
   public void setMeta(Meta meta) {
	   this.meta = meta;
   }

   public void setInfo(Object data) {
	   this.info = data;
   }



/**
    * 元数据
	 * @author wucl5
	 *
	*/
   public class Meta{
	   
	    private String statusCd;
	    
	    private String message;
	   
		public Meta(boolean isSuccess){
		}
		   
		public Meta(String message){
			this.message = message;
		}
		
		public Meta(String message, String statusCd){
			this.message = message;
			this.statusCd = statusCd;
		}
	
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

		public String getStatusCd() {
			return statusCd;
		}

		public void setStatusCd(String statusCd) {
			this.statusCd = statusCd;
		}
		
		
	
   }
   
   
   @Override
	public String toString() {
//	    JsonConfig config = new JsonConfig();
//	    config.registerJavaPropertyNameProcessor(Response.class, new PropertyNameProcessor() {
//			
//			public String processPropertyName(Class arg0, String arg1) {
//				System.out.println(arg1);
//				return null;
//			}
//		});
	   	String str = JSONObject.fromObject(this).toString();	
	   	// 清空数据
	   	this.info = null;
//	   	Gson gson = new GsonBuilder().setPrettyPrinting().create();
//	   	JsonParser jp = new JsonParser();
//	   	JsonElement je = jp.parse(str);
//	   	String prettyJsonString = gson.toJson(je);
//	   	LOGGER.debug(prettyJsonString);
//		return prettyJsonString;
	   	LOGGER.debug("response：{}",str);
	   	return str;
	}
   
   
  public static void main(String[] args) {
	Response response = new Response();
	response.success("111", "200");
	
    JsonConfig config = new JsonConfig();
    config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); 
    config.registerJavaPropertyNameProcessor(Object.class, new PropertyNameProcessor() {
		
		@SuppressWarnings("unchecked")
		public String processPropertyName(Class target, String fieldName) {
			System.out.println(target);  
            System.out.println(fieldName);  
            return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);  
		}
	});
    
    //config.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
    
    JSONObject jsonObject = JSONObject.fromObject(response, config);  
    String jsonStr = jsonObject.toString();  
    System.out.println(jsonStr);  
  }
}
