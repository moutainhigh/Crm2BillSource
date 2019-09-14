package com.al.nppm.business.syntomq.tool;

import com.al.nppm.common.utils.PasswordUtil;
import com.al.nppm.common.utils.PropertiesUtil;
import com.ctg.mq.api.CTGMQFactory;
import com.ctg.mq.api.IMQProducer;
import com.ctg.mq.api.PropertyKeyConst;
import com.ctg.mq.api.exception.MQException;
import org.apache.log4j.Logger;

import java.util.Properties;


public class MqTool {
	private static Logger logger = Logger.getLogger(MqTool.class);
	private static PropertiesUtil propertiesUtil=new PropertiesUtil("config/sysConfig.properties");
	private static IMQProducer producer;
	private static String topic =propertiesUtil.readProperty("mq.zj.topic");
	private static Properties properties = new Properties();
	private static int connect =-1;
	static{
		
		
       /* properties.setProperty(PropertyKeyConst.ProducerGroupName, "PID_billinginfo_queue_2_billing");
        properties.setProperty(PropertyKeyConst.NamesrvAddr, "10.1.234.73:9876;10.1.234.79:9876");
        properties.setProperty(PropertyKeyConst.NamesrvAuthID, "admin");
        properties.setProperty(PropertyKeyConst.NamesrvAuthPwd, "123456");*/
		/*
		properties.setProperty(PropertyKeyConst.ProducerGroupName, "PID_billinginfo_queue_2_billing");
        properties.setProperty(PropertyKeyConst.NamesrvAddr, "10.141.77.25:9876");
        properties.setProperty(PropertyKeyConst.NamesrvAuthID, "admin");
        properties.setProperty(PropertyKeyConst.NamesrvAuthPwd, "123456");
        */
		try {
			properties.setProperty(PropertyKeyConst.ProducerGroupName, propertiesUtil.readProperty("mq.zj.ProducerGroupName"));
			properties.setProperty(PropertyKeyConst.NamesrvAddr, propertiesUtil.readProperty("mq.zj.NamesrvAddr"));
			properties.setProperty(PropertyKeyConst.NamesrvAuthID, propertiesUtil.readProperty("mq.zj.NamesrvAuthID"));

			properties.setProperty(PropertyKeyConst.NamesrvAuthPwd, PasswordUtil.decrypt(propertiesUtil.readProperty("mq.zj.NamesrvAuthPwd"),"bss@2018"));

			producer = CTGMQFactory.createProducer(properties);
			connect =producer.connect();
		} catch (MQException e) {
			
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static IMQProducer getMqProducer()throws Exception
	{
		if(connect !=0){
			producer=CTGMQFactory.createProducer(properties);
			connect =producer.connect();
		}
		return producer;
	}


}


	