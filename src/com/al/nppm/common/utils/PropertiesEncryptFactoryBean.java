package com.al.nppm.common.utils;


import org.springframework.beans.factory.FactoryBean;

import java.util.Properties;



public class PropertiesEncryptFactoryBean implements FactoryBean {
	private Properties properties;  
	private String passwd="bss@2018";//加解密的密码
	public Object getObject() throws Exception {  
        return getProperties();  
    }  
  
    public Class getObjectType() {  
        return java.util.Properties.class;  
    }  
  
    public boolean isSingleton() {  
        return true;  
    }  
  
    public Properties getProperties() {  
        return properties;  
    }  
    public void setProperties(Properties inProperties) throws Exception {  
        this.properties = inProperties;  
        String  originalPassword= properties.getProperty("password");  
        
        if (originalPassword != null){  
            String newPassword = PasswordUtil.decrypt(originalPassword, passwd);
            properties.put("password", newPassword.trim());  
        }  
    }
    
    public static void main(String[] args) throws Exception {
//    	System.out.println(PasswordUtil.encrypt("12131111111", "12131111111"));
    	System.out.println(PasswordUtil.encrypt("1hbls_qt", "bss@2018"));
    	System.out.println(PasswordUtil.decrypt("ea7293baf146bc552b3501d4388d0e61", "bss@2018"));
    	
//    	String str=new String(Base64.decodeBase64("VGNzXzIZNDUK".getBytes()));
//    	System.out.println(new BASE64Encoder().encode(originalPassword.getBytes()));
    }
    

}
