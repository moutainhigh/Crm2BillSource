package com.al.nppm.common.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 * @author SYSTEM
 */
public class StrUtils{

	/**
	 * 字符串是否非空
	 * 
	 * @param str
	 *            str
	 * @return boolean
	 */
	public static final boolean isNotBlank(Object str){
		return !isBlank(str);
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param str
	 *            str
	 * @return boolean
	 */
	public static final boolean isBlank(Object str){
		return str == null || "".equals(str);
	}

	/**
	 * 得到非空值
	 * 
	 * @param obj
	 *            obj
	 * @return String
	 */
	public static final String getNotNullString(Object obj){
		if(obj == null){
			return "";
		}
		return obj.toString();
	}

	/**
	 * jdk里的replaceAll等替换子串的方法中必须是正则表达式 该方法用于替换任意子串
	 * 
	 * @param content 
	 *            执行子串替换的字符串
	 * @param expression
	 *            被替换掉的子串
	 * @param replacement
	 *            用来替换的子串
	 * @return String
	 */
	public static String replaceAll(String content,String expression,String replacement){
		if(isBlank(content)){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		int begin = 0;
		int end = content.indexOf(expression);
		int len = expression.length();
		while(end >= 0){
			sb.append(content.substring(begin,end));
			sb.append(replacement);
			begin = end + len;
			end = content.indexOf(expression,begin);
		}
		sb.append(content.substring(begin));
		return sb.toString();
	}

	/**
	 * @param content 被替换的字符串
	 *            ,map数据源
	 * @param startS 开始位置
	 * @param endS 结束位置
	 * @param map 参数
	 * @return 替换后的字符串
	 */
	public static String replaceParam(String content,String startS,String endS,Map<String,Object> map){
		if(isBlank(content.trim())){
			return "";
		}
		String _content = content;
		List<String> listParam = getSpaceParam(content,startS,endS);
		if(listParam.size() == 0){
			return content;
		}
		for(int index = 0;index < listParam.size();index++){
			String aParam = listParam.get(index);
			if(map.get(aParam) != null && map.get(aParam) != null && isNotBlank(map.get(aParam).toString())){
				String swap = startS + aParam + endS;
				_content = replaceAll(_content,swap,map.get(aParam).toString());
			}
		}
		return _content;
	}//

	/**
	 * 取出间隔符之间的变量, 如串 {{a}{b}{b}{c}}}}},分隔符 为 startS:{ ,endS:},将取出 a,b,c
	 * 
	 * @param content content
	 * @param startS startS
	 * @param endS endS
	 * @return List
	 */
	public static List<String> getSpaceParam(String content,String startS,String endS){
		if(isBlank(content.trim())){
			return null;
		}
		List<String> listParam = new ArrayList<String>();
		int slen = startS.length();
		int elen = endS.length();
		int startindex = 0;
		int endindex = 0;

		// 取出变量{{a}}
		do{
			endindex = content.indexOf(endS,startindex);
			if(endindex >= 0){
				int _startindex = content.lastIndexOf(startS,endindex);
				if(_startindex >= 0 && _startindex >= startindex){
					String aParam = content.substring(_startindex + slen,endindex);
					if(isNotBlank(aParam)){
						listParam.add(aParam);
					}
					startindex = endindex + elen;
				}else{
					startindex = startindex + elen;
				}
			}
		}while(endindex >= 0);
		return listParam;
	}

	/**
	 * 全角转半角
	 * 
	 * @param input
	 *            输入串
	 * @return String
	 */
	public static String q2bChange(String input){
		String result = "";
		char[] str = input.toCharArray();
		for(int i = 0;i < str.length;i++){
			// 获取当前字符的unicode编码
			int code = str[i];
			// 在这个unicode编码范围中的是所有的英文字母以及各种字符
			if(code >= 65281 && code <= 65373){
				// 把全角字符的unicode编码转换为对应半角字符的unicode码
				result += (char)(str[i] - 65248);
			}else if(code == 12288){
				// 空格
				result += (char)(str[i] - 12288 + 32);
			}else if(code == 65377){
				result += (char)12290;
			}else if(code == 12539){
				result += (char)183;
			}else if(code == 8226){
				// 特殊字符 ‘·’的转化
				result += (char)183;
			}else{
				result += str[i];
			}
		}
		return result;
	}

	/**
	 * 获得客户端真实IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {    
	    String ip = request.getHeader("x-forwarded-for");    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("Proxy-Client-IP");    
	    }    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("WL-Proxy-Client-IP");    
	    }    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getRemoteAddr();    
	    }    
	    return ip;    
	} 
	/**
	 * 数组对象为空或者size为0
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final boolean isArrayNullOrZero(List list){
		if(list != null && list.size()>0){
			return false;
		}else{
			return true;
		}
		
	}


	//将javabean转为map类型，然后返回一个map类型的值
	public static Map<String, Object> beanToMap(Object obj) { 
			Map<String, Object> params = new HashMap<String, Object>(0); 
			try { 
				PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
				PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj); 
				for (int i = 0; i < descriptors.length; i++) { 
					String name = descriptors[i].getName(); 
					if(!"class".equals(name)){
						params.put(name, propertyUtilsBean.getNestedProperty(obj, name)); 
					}
				} 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
			return params; 
	}
	
	public static int genPageInfo(int offset,int limit){
		int pageInfo;
		if(limit >= 0 && limit <10){
			pageInfo = limit * 100000 + offset;
		}else if(limit >= 10 && limit <100){
			pageInfo = limit * 10000 + offset;
		}else if(limit >= 100 && limit <1000){
			pageInfo = limit * 1000 + offset;
		}else{
			pageInfo = limit * 100 + offset;
		}
		
		return pageInfo;
	}
	
	public static void main(String[] args){
//		String str = "[]";
//		List list = getSpaceParam(str,"{","}");
//		Map map = new HashMap();
//		map.put("a","A");
//		map.put("c","C");
//		map.put("b","B");
//		str = replaceParam("[]","{","}",map);
	}
}
