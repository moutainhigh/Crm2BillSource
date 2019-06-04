package com.al.nppm.common.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.aop.MethodBeforeAdvice;
import com.al.nppm.common.aop.DataSourceContext;

/**
 * 数据源的切换
 * @author thd
 * @email tongdd@asiainfo-linkage.com
 * @date 2015-6-4
 * @version 1.0
 * @description
 */
public class ChoiceDataSource implements MethodBeforeAdvice
{
    /** log for this **/
    private static final Logger logger = Logger.getLogger(ChoiceDataSource.class);
    
    /**
     * 方法访问前
     * @param jp
     */
	public void doAccessCheck(JoinPoint jp) { 

		String dataSourceType = "";
		try {
			jp.getTarget();
			Field[] fields = jp.getTarget().getClass().getFields();
			if (fields != null) {
				for (Field field : fields) {
					if ("search".equals(field.getName())) {
						Map<String, Object> search = (Map<String, Object>) field
								.get(jp.getTarget());
						dataSourceType = search.get("dataSourceType") == null ? ""
								: search.get("dataSourceType").toString();
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		String targetClassName = jp.getTarget().getClass().getName();
		String[] list = StringUtils.split(targetClassName, '.');
		String catalog = StringUtils.trim(list[4]);
		handJDataSource(dataSourceType, catalog);

	}
    
	public static void handJDataSource(String dataSourceType, String catalog) {

		if (dataSourceType != null && dataSourceType.equals(DataSourceMap.nppm)) {
			DataSourceContext.setDataSource(DataSourceMap.nppm);
		} else if (dataSourceType != null
				&& dataSourceType.equals(DataSourceMap.cfg)) {
			DataSourceContext.setDataSource(DataSourceMap.cfg);
		} else {
			if ("cfg".equals(catalog)) {
				DataSourceContext.setDataSource(DataSourceMap.cfg);
			} else {
				DataSourceContext.setDataSource(DataSourceMap.nppm);
			}
		}

	}
    
    /**
     * 方法访问后
     * @param jp
     */
    public void doAfterReturning(JoinPoint jp){
    	 DataSourceContext.setDefaultCustomer();
    }

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
	}
}
