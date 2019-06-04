package com.al.nppm.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;

/**
 * 描述：Base64工具类 作者：郑文雄
 * **/
public class Base64Util {

	/**
	 * 描述：将BASE64码密文反序列化
	 * 
	 * @param:BASE64码密文
	 * **/
	public static Serializable base64ToObject(String base64String) {
		if (base64String == null) {
			return null;
		}
		ObjectInputStream in = null;
		try {
			byte[] btyes = Base64.decodeBase64(base64String.getBytes());
			ByteArrayInputStream bis = new ByteArrayInputStream(btyes);
			in = new ObjectInputStream(bis);
			return (Serializable) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (null != in) {
					in.close();
					in = null;
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 描述：将对象BASE64序列化
	 * 
	 * @param:序列化对象
	 * **/
	public static String objectToBase64(Object obj) {
		if (obj == null) {
			return null;
		}
		ObjectOutputStream out = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bos);
			out.writeObject(obj);
			return new String(Base64.encodeBase64(bos.toByteArray()));
		} catch (IOException e) {
			return null;
		} finally {
			try {
				if (null != out) {
					out.close();
					out = null;
				}
			} catch (IOException e) {
			}
		}
	}

	/*
	 * public static void main(String[] args) { Map map = new HashedMap();
	 * map.put("TTT", 1111); String base = Base64Util.objectToBase64(map);
	 * System.out.println(base);
	 * System.out.println(Base64Util.base64ToObject(base)); }
	 */

}