package com.al.nppm.common.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONObject;
import net.sf.json.util.PropertyFilter;

/**
 * 过滤不需要转换的json节点
 * 
 * @author lindl
 */
public class NotComprisePropertyFilter implements PropertyFilter{
	@SuppressWarnings("rawtypes")
    private  Class src;
	private  Set<String> filesSet = new HashSet<String>();
	private  Set<String> excludeSet = new HashSet<String>();

	public NotComprisePropertyFilter(){

	}
	public NotComprisePropertyFilter(String[] excludes){
		excludeSet.addAll(Arrays.asList(excludes));
	}

	public boolean apply(Object source,String name,Object value){
		if(src == null || !(source.getClass().equals(src))){
			src = source.getClass();
			initFileSet();
		}
		// 对象属性名不存在或者包含在过滤范围内
		if((value instanceof JSONObject) && !filesSet.contains(name) || excludeSet.contains(name)){
			return true;
		}
		return false;
	}
	private void initFileSet(){
		Field[] field = src.getDeclaredFields();
		for(Field f : field){
			filesSet.add(f.getName());
		}
	}

}
