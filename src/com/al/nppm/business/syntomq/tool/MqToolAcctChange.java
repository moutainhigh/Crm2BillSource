package com.al.nppm.business.syntomq.tool;

import com.al.nppm.business.syntomq.model.MqUtil;
import com.al.nppm.common.utils.PasswordUtil;
import com.al.nppm.common.utils.PropertiesUtil;
import com.al.nppm.model.Message;
import com.alibaba.fastjson.JSON;
import com.ctg.mq.api.CTGMQFactory;
import com.ctg.mq.api.IMQProducer;
import com.ctg.mq.api.PropertyKeyConst;
import com.ctg.mq.api.bean.MQMessage;
import com.ctg.mq.api.bean.MQSendResult;
import com.ctg.mq.api.bean.MQSendStatus;
import com.ctg.mq.api.exception.MQException;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Properties;


public class MqToolAcctChange {
	private static Logger logger = Logger.getLogger(MqToolAcctChange.class);
	private static PropertiesUtil propertiesUtil=new PropertiesUtil("config/sysConfig.properties");
	private static IMQProducer producer;
	private static String topic =propertiesUtil.readProperty("mq.gh.topic");
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
			properties.setProperty(PropertyKeyConst.ProducerGroupName, propertiesUtil.readProperty("mq.gh.ProducerGroupName"));
			properties.setProperty(PropertyKeyConst.NamesrvAddr, propertiesUtil.readProperty("mq.gh.NamesrvAddr"));
			properties.setProperty(PropertyKeyConst.NamesrvAuthID, propertiesUtil.readProperty("mq.gh.NamesrvAuthID"));

			properties.setProperty(PropertyKeyConst.NamesrvAuthPwd, PasswordUtil.decrypt(propertiesUtil.readProperty("mq.gh.NamesrvAuthPwd"),"bss@2018"));
			properties.setProperty(PropertyKeyConst.ClusterName, propertiesUtil.readProperty("mq.gh.clusterName"));
			properties.setProperty(PropertyKeyConst.TenantID, propertiesUtil.readProperty("mq.gh.TenantID"));
			properties.setProperty(PropertyKeyConst.InstanceName, propertiesUtil.readProperty("mq.gh.InstanceName"));



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
	/**
	 * 发给账务消息
	 * @param topicType
	 * @param msgMap
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public static int send(String topicType, Map msgMap, Message msg) throws Exception {
		try {
			if(connect !=0){
				producer=CTGMQFactory.createProducer(properties);
				connect =producer.connect();
			}
			if(connect  != 0){
				msg.setMessage(STATUS.CONNECTERR.getMsg());
				return -1;
			}
			String topicName="";
			if(TopicType.zfjh.equals(topicType)){
				topicName=propertiesUtil.readProperty("mq.zj.topic");
			}else if(TopicType.smzjh.equals(topicType)){
				topicName=propertiesUtil.readProperty("mq.smzjh.topic");
			}else if(TopicType.xgzwjh.equals(topicType)){
				topicName=propertiesUtil.readProperty("mq.gh.topic");
			}

			long startTime=System.currentTimeMillis();
//			MQMessage message = new MQMessage(topic,messageId,messageType,msg.getBytes("UTF-8"));
			MQMessage message = new MQMessage();
			byte[] bytes = JSON.toJSONString(msgMap).getBytes("UTF-8");
			logger.debug("发送账务消息："+JSON.toJSONString(msgMap));
			message.setBody(bytes);
			message.setKey(MqUtil.createTopicKey(topicName+"Key"));
			message.setSourceName(topicName);
			message.setTag(MqUtil.createTopicTag(topicName));
			message.setDelayTimeLevel(0);
			MQSendResult sendResult = producer.send(message);
			logger.debug("发送消息耗时："+(System.currentTimeMillis()-startTime)+"毫秒");
			if (sendResult.getSendStatus()== MQSendStatus.SEND_OK) {
				msg.setMessage(message.getKey());
				return 1;
			}else{
				msg.setMessage(STATUS.SENDERR.getMsg());
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
	}

}


	