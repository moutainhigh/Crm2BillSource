package com.al.nppm.business.syntomq.tool;

import com.al.nppm.common.utils.PasswordUtil;
import com.al.nppm.common.utils.PropertiesUtil;
import com.ctg.mq.api.CTGMQFactory;
import com.ctg.mq.api.IMQProducer;
import com.ctg.mq.api.PropertyKeyConst;
import com.ctg.mq.api.bean.MQMessage;
import com.ctg.mq.api.bean.MQSendResult;
import com.ctg.mq.api.bean.MQSendStatus;
import com.ctg.mq.api.exception.MQException;
import org.apache.log4j.Logger;

import java.util.Properties;


public class CTGMqTool {
	private static Logger logger = Logger.getLogger(CTGMqTool.class);
	private static PropertiesUtil propertiesUtil=new PropertiesUtil("config/sysConfig.properties");
	private static IMQProducer producer;
	private static String topic =propertiesUtil.readProperty("mq.topic");
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
			properties.setProperty(PropertyKeyConst.ProducerGroupName, propertiesUtil.readProperty("mq.ProducerGroupName"));
			properties.setProperty(PropertyKeyConst.NamesrvAddr, propertiesUtil.readProperty("mq.NamesrvAddr"));
			properties.setProperty(PropertyKeyConst.NamesrvAuthID, propertiesUtil.readProperty("mq.NamesrvAuthID"));

			properties.setProperty(PropertyKeyConst.NamesrvAuthPwd, PasswordUtil.decrypt(propertiesUtil.readProperty("mq.NamesrvAuthPwd"),"bss@2018"));
			properties.setProperty(PropertyKeyConst.MaxMessageSize,propertiesUtil.readProperty("mq.MaxMessageSize"));

			properties.setProperty(PropertyKeyConst.ClusterName, propertiesUtil.readProperty("mq.clusterName"));
			properties.setProperty(PropertyKeyConst.TenantID, propertiesUtil.readProperty("mq.TenantID"));
			properties.setProperty(PropertyKeyConst.InstanceName, propertiesUtil.readProperty("mq.InstanceName"));

			producer = CTGMQFactory.createProducer(properties);
			connect =producer.connect();
		} catch (MQException e) {
			
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * <p>发送到消息队列</p>
	 * @throws Exception 
	 */
	public static STATUS send(String messageId,String messageType,String msg) throws Exception {
        try {
        	if(connect !=0){
        		producer=CTGMQFactory.createProducer(properties);
        		connect =producer.connect();
        	}
        	
        	if(connect  != 0){
	              return STATUS.CONNECTERR;
	        }
        	long startTime=System.currentTimeMillis();
            MQMessage message = new MQMessage(topic,messageId,messageType,msg.getBytes("UTF-8"));
			System.out.println(123);
            MQSendResult sendResult = producer.send(message);

            logger.debug("发送消息耗时："+(System.currentTimeMillis()-startTime)+"毫秒");
            if (sendResult.getSendStatus()==MQSendStatus.SEND_OK) {
//            	producer.close();
				return STATUS.SENDOK;
			}else{
//				producer.close();
				
				return STATUS.SENDERR;
			}
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error(e.getMessage());
        	throw e;
        	
        }
        //return null;
	}

}


	