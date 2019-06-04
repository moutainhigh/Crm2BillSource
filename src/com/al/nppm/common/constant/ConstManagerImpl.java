/*
 * Created on 2006-11-6
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.al.nppm.common.constant;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.al.nppm.common.log.LogFactory;



/**
 * @author Hongh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class ConstManagerImpl implements ConstManager {

	@SuppressWarnings("unchecked")
	private static Map constMap = null;

	private static LogFactory logger = LogFactory.getLog(ConstManagerImpl.class);
	
	
	private static String DEFAULT_CONFIG_FILE = "nppmConst.properties";
	
	private static String CONFIG_FILE = null;
	
	private static boolean PARAM_VALID = true;
	
	private static String PRE_PARAM = "CONST_";
	
	private static ConstManager manager;
	
	private ConstManagerImpl() {
	}
	
	public static ConstManager getInstance() {
		if(manager == null) {
			manager = new ConstManagerImpl();
		}
		return manager;
	}
	

	/* 
	 * 按key取值
	 * (non-Javadoc)
	 * @see com.linkage.ppm.intf.common.ConstManager#getConst(java.lang.Object)
	 */
	public Object getConst(Object key) {
		if (constMap == null) {
			constMap = loadConst();
			if(constMap == null) {
				logger.error("配置文件" + DEFAULT_CONFIG_FILE + "不存在或读取失败！");
				throw new RuntimeException("配置文件" + DEFAULT_CONFIG_FILE + "不存在或读取失败！");
			}
			if(constMap.size() == 0) {
				logger.error("配置文件中无配置数据！");
				throw new RuntimeException("配置文件中无配置数据！");
			}
		}
		if (constMap.containsKey(key)) {
			return constMap.get(key);
		} else {
			logger.error("配置文件中没有" + key + "这个常量！");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map getConstMap() {
		if(constMap == null) {
			constMap = loadConst();
		}
		return constMap;
	}
	
    
	/**
	 * 初始化常量数据,定义了常量前缀的校验。
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	private Map loadConst() {
		String filePath ;
		if(CONFIG_FILE != null) {
			filePath = CONFIG_FILE;
		} else {
			filePath = DEFAULT_CONFIG_FILE;
		}
//		InputStream input = ClassLoader.getSystemResourceAsStream(filePath);
		InputStream input = manager.getClass().getClassLoader().getResourceAsStream(filePath);	
		Properties prop = new Properties();
		try {
			prop.load(input);
		} catch (IOException e) {
			logger.error(e.toString());
			return null;
		} catch (Exception e) {
			logger.error(e.toString());
			return null;
		} finally {
			try {
				input.close();
			} catch (Exception e) {
				logger.error(e.toString());
			}
		}
		
		Map map = new HashMap();
		Enumeration em = prop.keys();
		while (em.hasMoreElements()) {
			try {
				String id = em.nextElement().toString();
				map.put(id, new String(prop.getProperty(id).getBytes("ISO-8859-1"), "GBK"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.error("错误：",e);
			}
		}
		
		if(PARAM_VALID) {
			if(!validateConst(map)) {
				logger.error("常量定义没有用[" + PRE_PARAM + "]打头");
				throw new RuntimeException("常量定义没有用[" + PRE_PARAM + "]打头");
			}
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	private boolean validateConst(Map map){
		Set keys = map.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			if(!key.substring(0,PRE_PARAM.length()).equals(PRE_PARAM)) {
				logger.error(key+"不是用[" + PRE_PARAM + "]打头");
				return false;
			}
		}
		return true;
	}

	/**
	 * @return String
	 */
	public String getConfigFile() {
		return CONFIG_FILE;
	}

	/**
	 * @param string String
	 */
	public void setConfigFile(String string) {
		CONFIG_FILE = string;
	}

	/* (non-Javadoc)
	 * @see bss.crm.comanager.common.ConstManager#getString(java.lang.Object)
	 */
	public String getString(Object key) {
		Object obj = getConst(key);
		return obj == null ? null : obj.toString();
	}

	/* (non-Javadoc)
	 * @see bss.crm.comanager.common.ConstManager#getInteger(java.lang.Object)
	 */
	public Integer getInteger(Object key) {
		try {
			return Integer.valueOf(getString(key));
		} catch (Exception e) {
			logger.error("取常量时出错，KEY=" + key + "\n" + e.toString());
			throw new RuntimeException("取常量时出错，KEY=" + key + "\n" + e.toString());
		}
	}

	/* (non-Javadoc)
	 * @see bss.crm.comanager.common.ConstManager#getInt(java.lang.Object)
	 */
	public int getInt(Object key) {
		return getInteger(key).intValue();
	}

}
