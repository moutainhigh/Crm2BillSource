package com.al.nppm.common.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class TextUtil {

	/**
	 *function:将数据库中的bigdecimal转化为long
	 *
	 *@param pram
	 *@return
	 *@author wangb
	 */
	public static long  getLong(Object pram){
		
		if(pram != null){
			
			BigDecimal bg = new BigDecimal(pram+"");
			return bg.longValue();
		}
		return 0;
	
	}
	
	/**
	 *function:将数据库中的bigdecimal转化为long
	 *
	 *@param pram
	 *@return
	 *@author wangb
	 */
	public static short  getShort(Object pram){
		
		if(pram != null){
			
			BigDecimal bg = new BigDecimal(pram+"");
			return bg.shortValue();
		}
		return 0; 
	
	}
	
	/**
	 *function:将数据库中的bigdecimal转化为Long
	 *
	 *@param pram
	 *@return
	 *@author wangb
	 */
	public static Long  getOlong(Object pram){
		
		if(pram != null){
			
			BigDecimal bg = new BigDecimal(pram+"");
			return new Long(bg.longValue());
		}
		return null;
	
	}


	/**
	 *function:将数据库中的string类型进行null处理
	 *
	 *@param pram
	 *@return
	 *@author wangb
	 */
	public static String getString(Object pram){
		
		if(pram == null ||"null".equals(pram)){
			return "";
		}else{
			return (""+pram).trim();
		}
	}

	/**
	 *function:将数据库中的时间转化为String类型
	 *
	 *@param pram
	 *@return
	 *@author wangb
	 */
	public static String getDateFormate(Object pram){
		
		if (pram != null){
			//java.sql.Date ts = (java.sql.Date)pram;
			Timestamp ts = (Timestamp)pram;
			
			long date = ts.getTime();
			
			Date jdate = new Date(date);
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			String str = df.format(jdate);   
			
			return str;
		}else{
			
			return "";
		}
	
		
		
	}

	/**
	 *function:将string 类型转换为short类型
	 *
	 *@param obj
	 *@return
	 *@author wangb
	 */
	public  static short getShort(String obj){
		
		if (obj != null && !"".equals(obj)){
			
			return Short.parseShort(obj);
		}
		
		return 0;
		
	}
	
	/**
	 *function:将string 类型转换为short类型
	 *
	 *@param obj
	 *@return
	 *@author wangb
	 */
	public  static Short getOshort(String obj){
		
		if (obj != null && !"".equals(obj) && !"undefined".equals(obj)){
			
			return new Short(Short.parseShort(obj));
		}
		
		return null;
		
	}
	
   public  static Long getLong(String obj){
		
		if (obj != null && !"".equals(obj)){
			
			return new Long(Long.parseLong(obj));
		}
		
		return null;
		
	}
   public  static int getInt(String obj){
		
		if (obj != null && !"".equals(obj)){
			
			return Integer.parseInt(obj);
		}
		
		return 0;
		
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String a ="１";
		byte[] b = a.getBytes("GBK");
		System.out.println(b.length);
//	   String obj = " <link id='110000201012170354415940'   doLinkId=\"1598\" assignee='' doLinkId=\"159\" staffName=''/>";
//		TextUtil t = new TextUtil();
//		String a = t.replaceXmlAttribute(obj,"doLinkId","159","123");
//		System.out.println(a);
	}
	/**
	 *function:将blob转化为string类型
	 *
	 *@param blob
	 *@return
	 *@author wangb
	 */
	public static String getContentString(Blob blob) {

		try {

			BufferedInputStream bi = new BufferedInputStream(blob.getBinaryStream());
			byte[] data = new byte[(int) blob.length()];
			String outfile = "";
			bi.read(data);
			outfile = new String(data);
			bi.close();
			return outfile;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**	
	 *function:解析json对象
	 *
	 *@param req
	 *@return
	 *@throws IOException
	 *@author wangb
	 */
//	public static Map getJsonMap(HttpServletRequest req) throws IOException {
//
//		final int BUFFER_SIZE = 8 * 1024;
//		byte[] buffer = new byte[BUFFER_SIZE];
//		ServletInputStream sis = req.getInputStream();
//		int length = 0;
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		do {
//			length = sis.read(buffer);
//			if (length > 0) {
//				baos.write(buffer, 0, length);
//			}
//		} while (length != -1);
//		String bodyData = new String(baos.toByteArray(), "GBK");
//
//		return JsonUtil.toMap(bodyData);
//	}
	
	/**
	 *function:将string 类型转换为BigDecimal类型
	 *
	 *@param obj
	 *@return
	 *@author wangb
	 */
	public static BigDecimal getBigDecimal(String obj){
		
		if(obj!=null && !"".equals(obj)){
			
			return new BigDecimal(obj);
		}
		//else{
			
			//return new BigDecimal("");
		//}
		return null;
	}
	
	/**
	 *function:将string 转为Integer
	 *
	 *@param obj
	 *@return
	 *@author wangb
	 */
	public static Integer getOInteger(String obj){
		try {
			if (obj != null && !"".equals(obj)){
				
				return new Integer(obj);
			}
		} catch (Exception e) {
			return null;
		}
		
		return null;
	}
	
	/**
	 *function:获取xml属性
	 *
	 *@param data
	 *@author wangb
	 */
	public static List split(String data,String propertyName) {
		
		List resList = new ArrayList();
		data = data.toLowerCase();  
	//	 Pattern pattern2 = Pattern.compile("<customstate *?[^>]*?((>.*?</customstate>)|(/>))");
		Pattern pattern2 = Pattern.compile("<transition .*?/>");
		Matcher matcher2 = pattern2.matcher(data);
		while (matcher2.find()) {

			String data1 = matcher2.group();

			resList.add(getAttribute(data1, propertyName));
		}
		
		return resList;

	}
	
	/**
	 *function:获取xml属性
	 *
	 *@param data
	 *@author wangb
	 */
	public static List getNodeID(String data,String propertyName) {
		
		List resList = new ArrayList();
		//data = data.toLowerCase();  
	//	 Pattern pattern2 = Pattern.compile("<customstate *?[^>]*?((>.*?</customstate>)|(/>))");
		Pattern pattern2 = Pattern.compile("<task .*?>");
		Matcher matcher2 = pattern2.matcher(data);
		while (matcher2.find()) {

			String data1 = matcher2.group();

			resList.add(getAttribute(data1, propertyName));
		}
		
		return resList;

	}
	
	/**
	 *function:获取xml属性
	 *
	 *@param data
	 *@author wangb
	 */
	public static List getNode(String data,String property,String propertyName) {
		
		List resList = new ArrayList();
		//data = data.toLowerCase();  
	//	 Pattern pattern2 = Pattern.compile("<customstate *?[^>]*?((>.*?</customstate>)|(/>))");
		Pattern pattern2 = Pattern.compile("<"+property+" .*?>");
		Matcher matcher2 = pattern2.matcher(data);
		while (matcher2.find()) {

			String data1 = matcher2.group();

			resList.add(getAttribute(data1, propertyName));
		}
		
		return resList;

	}

	// public String getParameter(String data)
	// {
	// String result="";
	// StringBuffer reStr=new StringBuffer();
	// reStr.append("<");
	// // reStr.append("(.*?)");
	// // reStr.append("</");
	// // reStr.append(para);
	// // reStr.append(">");
	// Pattern pattern=Pattern.compile(reStr.toString());
	// Matcher matcher=pattern.matcher(data);
	// if(matcher.find())
	// {
	// result=matcher.group(1);
	// }
	// return result;
	// }

	/**
	 * 在给定的元素中获取指定属性的值.该元素应该从getElementsByTag方法中获取
	 * 
	 * @param elementString
	 *            String
	 * @param attributeName
	 *            String
	 * @return String
	 */
	public static String getAttribute(String elementString, String attributeName) {

		Pattern p = Pattern.compile("<[^>]+>");
		Matcher m = p.matcher(elementString);
		String tmp = m.find() ? m.group() : "";
		p = Pattern.compile("(" + attributeName + "+)\\s*=\\s*\"([^\"]+)\"");
		m = p.matcher(tmp);
		while (m.find()) {
			return m.group(2);
		}
		return "";
	}
	/**
	 *function:将obj转为int
	 *
	 *@param obj
	 *@return
	 *@author wangb
	 */
	public static int getInt(Object obj){
		
		if(obj != null && !"null".equals(obj)){
			String intValue = obj +"";
			if(!"".equals(intValue) && intValue != null){
				return Integer.parseInt(intValue);
			}
			
		}
		return 0;
	}
	
	public static Integer getInteger(Object obj){
		
		if(obj != null && !"null".equals(obj)){
			String intValue = obj +"";
			if(!"".equals(intValue) && intValue != null){
				return Integer.parseInt(intValue);
			}
			
		}else{
			return null;
		}
		return 0;
	}
	
	public static String replaceXmlAttribute(String xmlData,String fromAttribute,String toAttribute,String fromArg,String toArg){
		
		
		if(xmlData != null && !"".equals(xmlData)){
			
			xmlData=xmlData.replaceAll(""+fromAttribute+"=\""+fromArg+"\"", ""+toAttribute+"=\""+toArg+"\"");
		}
		return xmlData;
	}
	
	/**
	 *function:将数据库中的bigdecimal转化为Long
	 *
	 *@param pram
	 *@return
	 *@author wangb
	 */
	public static int  getBD2Int(Object pram){
		
		if(pram != null){
			
			BigDecimal bg = new BigDecimal(pram+"");
			return bg.intValue();
		}
		return 0;
	
	}
	/**
	 *function:判断字符长短
	 *
	 *@param arg
	 *@param maxSize
	 *@return
	 *@throws UnsupportedEncodingException
	 *@author wangb
	 */
	public static Map checkFrontNum(String arg,int maxSize) throws UnsupportedEncodingException{
		
		Map map = new HashMap();
		if(arg != null && !"".equals(arg)){
			
			byte[] b = arg.getBytes("GBK");
			
			if(maxSize<b.length){
				
				int overLength = b.length-maxSize;
				map.put("overFlag", "1");
				map.put("overLength", overLength);
				return map;
			}
			map.put("overFlag","0");
			return map;
		}
		map.put("overFlag","0");
		return map;
		
	}
	

	 /**
	 *function:file读到byte[]
	 *
	 *@param file
	 *@return
	 *@throws IOException
	 *@author wangb
	 */
	private static byte[] getBytesFromFile(File file) throws IOException {
          //file size
          long length = file.length();
          InputStream is = null;
          is = new BufferedInputStream(new FileInputStream(file));
          if (length > Integer.MAX_VALUE) {
               throw new IOException("File is to large " + file.getName());
          }
          byte[] bytes = new byte[(int) length];
          int offset = 0;
          int numRead = 0;
          while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
               offset += numRead;
          }
           if (offset < bytes.length) {
                   throw new IOException("Could not completely read file " + file.getName());
          }
          is.close();
          return bytes;

	  }
	/**
	 *function:inputstream to byte
	 *
	 *@param is
	 *@return
	 *@throws IOException
	 *@author wangb
	 */
	public static byte[] InputStreamToByte(InputStream is) throws IOException {
		
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}
	
	/**
	 * 
	 * function:Object转Long,如果Object为空或者为0,返回NULL
	 * @param o
	 * @return
	 * @author wangjs 2011-5-11
	 */
	public static Long getLongValue(Object o){
		
		if(o == null || "".equals(o) || "0".equals(o+"")|| "null".equals(o+"")){
			return null;
		}
		return Long.valueOf(o+"");
	}
	
	/**
	 *function:将object转为boolean类型
	 *
	 *@param pram
	 *@return
	 *@author wangb
	 */
	public static boolean getBoolean(Object pram){
		
		if(pram == null){
			return false;
		}else{
			if((pram+"").equals("true")){
				return true;
			}else {
				return false;
			}
		}
	}
	
	/**
	 * 判断是否是执行类型
	 * @return
	 */
	public static boolean isExcuteType(String methodName){
		if(methodName == null || methodName.equals("")){
			return false;
		}
		//jdk 1.7是改成switch
		if(isAddType(methodName)==true){
			return true;
		}else if(isModType(methodName)==true){
			return true;
		}else if(isDelType(methodName)==true){
			return true;
		}else if(isEditType(methodName)==true){
			return true;
		}else if(isOperationType(methodName)==true){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断是否是新增类型
	 * @param methodName
	 * @return
	 */
	public static boolean isAddType(String methodName){
		if(methodName.startsWith("add") || methodName.startsWith("insert") || methodName.startsWith("save")){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 判断是否为操作类型
	 * @param methodName
	 * @return
	 */
	public static boolean isOperationType(String methodName){
		if(methodName.endsWith("Operation") || methodName.endsWith("operation")  || methodName.contains("Operation")){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否是修改类型
	 * @param methodName
	 * @return
	 */
	public static boolean isModType(String methodName){
		if(methodName.startsWith("mod") || methodName.startsWith("upd")){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否是删除类型
	 * @param methodName
	 * @return
	 */
	public static boolean isDelType(String methodName){
		if(methodName.startsWith("del") || methodName.startsWith("rem")){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否是删除类型
	 * @param methodName
	 * @return
	 */
	public static boolean isEditType(String methodName){
		if(methodName.startsWith("edit")){
			return true;
		}
		return false;
	}
	
	
	public static boolean isNumeric(String str){
		for (int i = str.length();--i>=0;){   
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		  return true;
	}
	
	public static boolean isEmpty(String str){
		if(null==str || "".equals(str)){
			return true;
		}
		return false;
	}
	/**
	 * 验证字符串是否为空
	 * @param string
	 * @return
	 */
	public static boolean isEmpty2(String string) {
		boolean result = false;
		if(string == null || "".equals(string.trim())|| "null".equals(string.trim()) || "[]".equals(string.trim())){
			result = true;
		}
		return result;
	}

	/**
	 * 判断是否为数字
	 * @param des
	 * @return
	 */
	public static  boolean checkIsNum(String des){
		if(!isEmpty2(des)){
			des = des.trim();
			//Pattern pattern = Pattern.compile("[0-9]*"); 
			String rex="(^[1-9]\\d*$)|(^0$)|(^-[1-9]\\d*$)";
			Pattern pattern = Pattern.compile(rex);//modify by liangzm
			   Matcher isNum = pattern.matcher(des);
			   if(isNum.matches() ){
			       return true; 
			   }
			}
		return false;
	}
	
	/**
	  * 判断字符串是否是整数 chenyj3 2015-09-23
	  */
	 public static boolean isInteger(String value) {
	  try {
	   Integer.parseInt(value);
	   return true;
	  } catch (NumberFormatException e) {
	   return false;
	  }
	 }
	
}
