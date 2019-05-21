package com.al.nppm.common.restservice;

import com.al.nppm.common.exception.ApiErrorList;

/**
 * REST 服务响应工具类,基于 Response 类 进行了一层封装
 * @author Administrator
 *
 */
public class ResponseUtils {
	   
	 private static Response response;
	 
	 public static String success(String message){
		   if(response == null){
			   response = new Response();
		   }
		   response.success(message);

		   return response.toString();
	   }
	   
	   public static String success(String message,String statusCd){
		   if(response == null){
			   response = new Response();
		   }
		   response.success(message,statusCd);
		   
		   return response.toString();
	   }
	   
	   public static String success(Object data){
		   
		   if(response == null){
			   response = new Response();
		   }
		   
		   response.successNew(data);
		   
		   return response.toString();
	   }
	   
	   public static String failure(String message){

		   if(response == null){
			   response = new Response();
		   }
		   response.failure(message);
		   
		   return response.toString();
	   }
	   
	   public static String failure(String message,String statusCd){

		   if(response == null){
			   response = new Response();
		   }
		   response.failure(message, statusCd);
		   
		   return response.toString();
	   }
	   
	   public static String failure(ApiErrorList errorList){

		   if(response == null){
			   response = new Response();
		   }
		   response.failure(errorList);
		   
		   return response.toString();
	   }
	   
//	   public static String failure(ApiErrorList errorList, Object data){
//		   if(response == null){
//			   response = new Response();
//		   }
//		   response.failure(errorList,data);
//		   
//		   return response.toString();
//	   }
	   
	 public static String getResponse() {
		return response.toString();
	}
}
