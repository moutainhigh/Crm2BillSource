package com.al.nppm.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.ConvertUtils;

import com.al.nppm.business.common.SpringContextHolder;


public class MaptoClass {

	/**
	 * @throws Exception
	 * 自动映射值
	 * 需要注意 map对象的key 和 实体类的属性 要对应才能转换
	 * @throws  
	 */
	public static <T> T  handData(T model, Map<String, Object> map){
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if(!checkComplexData(entry.getValue())){
				Field field = null;
				try {
					field = model.getClass().getDeclaredField(entry.getKey());
				} catch (Exception e) {
					//logger.info("异常继续循环!");
					continue;
				} 
				Object defualtValue = entry.getValue();
				
				//设置可读
				field.setAccessible(true);
				
				//对date 类型需要把字符串转成时间类型2018-03-22 15:46:10
				if(field.getType() == java.sql.Date.class || field.getType() ==java.util.Date.class){
					//20170301000000
//					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					try {
//						defualtValue = (Date)format.parse(defualtValue.toString());
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					defualtValue =StringUtil.strToDate(defualtValue+"","yyyy-MM-dd HH:mm:ss");
				}else{
					//需要进行数据转换
					if(field.getName()=="statusCd"){
//						if("1000".equals(defualtValue+"")){
//							defualtValue =1;
//						}else if("1100".equals(defualtValue+"")){
//							defualtValue =2;
//						}else if("1200".equals(defualtValue+"")){
//							defualtValue =3;
//						}
					}
					defualtValue = ConvertUtils.convert(defualtValue, field.getType());
					
				}
				try {
					field.set(model, defualtValue);
				} catch (Exception e) {
					//logger.info(entry.getKey()+"=="+entry.getValue()+"转换成"+model.getClass().getName()+"发生异常");
				} 
			}
		}
		return model;
		
	}
	
	/**
	 * 验证该数据是否位复杂对象，比如JsonObject,JsonArray,集合等数据定义为复杂对象
	 * @param object
	 * @return
	 */
	public static boolean checkComplexData(Object object){
		boolean flag =false;
		if(object!=null){
			if(JSONObject.class.isAssignableFrom(object.getClass()) ){
				flag = true;
			}else if(Collection.class.isAssignableFrom(object.getClass())){
				flag = true;
			}
		}
		return flag;
	}

	

}
