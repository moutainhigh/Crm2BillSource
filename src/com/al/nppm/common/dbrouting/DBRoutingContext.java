package com.al.nppm.common.dbrouting;

import java.util.HashMap;
import java.util.Map;

public class DBRoutingContext {
	public final static String CONST_DBROUTING_KEY_AREA = "areaId";
	
	private static final ThreadLocal<Map<String,String>> contextHolder=new ThreadLocal<Map<String,String>>();
	public static void setKeyValue(String key,String value){
		if(contextHolder.get()==null)
			contextHolder.set(new HashMap<String,String>());
		contextHolder.get().put(key, value);
	}
	public static String getValue(String key){
		return contextHolder.get().get(key);
	}
	public static void clear(){
		contextHolder.remove();
	}
}
