package com.al.nppm.business.syntomq.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MqUtil {
    public MqUtil() {
    }

    public static String createTopicKey(String topicName) {
        if (topicName != null && !topicName.isEmpty()) {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateString = formatDate.format(new Date())+( int )(Math.random()*1000*1000*1000);
            return topicName + "Key" + dateString;
        } else {
            return topicName;
        }
    }

    public static String createTopicTag(String topicName) {
        if (topicName != null && !topicName.isEmpty()) {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateString = formatDate.format(new Date())+( int )(Math.random()*1000*1000*1000);
            return topicName + "Tag" + dateString;
        } else {
            return topicName;
        }
    }
}