package com.al.nppm.common.aop;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONObject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;

@Aspect  
@Component  
public class ParamInterceptor {
	
    @Pointcut("execution (* com.al.nppm.business.*.service.impl.*.*(..))")  
    public void handParamPointcut(){}  
  
    @Around("handParamPointcut()")  
    public Object testHandleBody(ProceedingJoinPoint pjp) throws Throwable{  
          
        Object retVal = null;  //连接点方法返回值  
          
        //获取将要执行的方法名称  
        String methodName = pjp.getSignature().getName();  
          
         //获取执行方法的参数  
        Object[] args = pjp.getArgs();
        //如果有参数，而且参数类型为map ，或者是JSONObject 处理相关的数据
        if(args!=null && args.length>0){
        	Object args1 = args[0];
        	Map<String, Object> map =null;
        	if(args1.getClass().isAssignableFrom(JSONObject.class)){
        		map = new HashMap<String, Object>();  
        	       JSONObject jsonObject = (JSONObject)args1;  
        	       Iterator it = jsonObject.keys();  
        	       while (it.hasNext()){  
        	           String key = String.valueOf(it.next());  
        	           Object value = jsonObject.get(key);  
        	           map.put(key, value);  
        	       }
			}else if(args1.getClass().isAssignableFrom(Map.class)){
				map = (Map<String, Object>)args1;
			}
        	if(map!=null){
        		int pageNo = map.get("pageNo")==null?0:Integer.parseInt(map.get("pageNo")+"");
    			int pageSize = map.get("pageSize")==null?0:Integer.parseInt(map.get("pageSize")+"");
    			if(pageNo>0 && pageSize>0){
    				PageHelper.startPage(pageNo,pageSize,true); 
    				int start= (pageNo-1)*pageSize;
    				int limit = start+pageSize;
    				map.put("start", start);
    				map.put("limit", limit);
    			}else{
    				 //PageHelper.startPage(1,-1,true);  //不分页
    			}
    			args[0] = map;
        	}
        }
        retVal = pjp.proceed(args);  
        return retVal;  
    }  
}  