/*
 * com.ailk.common.datasource.DataSourceContext.java
 *
 * 该类源代码归属福州计费研发部，仅限部门内部使用，谢绝传播。
 *
 * Copyright (c) 2013 亚信联创科技(南京)有限公司
 * All rights reserved.
 *
 */
package com.al.nppm.common.aop;

/**
 * @author thd
 * @email tongdd@asiainfo-linkage.com
 * @date 2013-9-5
 * @version 1.0
 * @description 整理
 */
public class DataSourceContext {
	
	private static final ThreadLocal context =new ThreadLocal();
	
	/**
	 * liudm 20150604 增加
	 */
	public static void setDefaultCustomer(){
		//切换回之前的数据源时，先移除当前访问的数据源
		removeDataSource();
		setDataSource(DataSourceMap.nppm);
	}
	
    public static void setDataSource(String sourceName) {
    	context.set(sourceName);
    }
    
    public static String getDataSource() {
    	return (String)context.get();
    }
    
    public static void removeDataSource() {
    	context.remove();
    }
    
}
