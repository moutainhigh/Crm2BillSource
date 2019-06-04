package com.al.nppm.business.inter.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;

public class JsonObjectTools {

	
	  public static String firstCharacterToUpper(String srcStr) {
		   return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);
		}
		/**
		* 替换字符串并让它的下一个字母为大写
		*/
		public static String replaceUnderlineAndfirstToUpper(String srcStr,String org,String ob)
		{
		   String newString = "";
		   int first=0;
		   while(srcStr.indexOf(org)!=-1)
		   {
		    first=srcStr.indexOf(org);
		    if(first!=srcStr.length())
		    {
		     newString=newString+srcStr.substring(0,first)+ob;
		     srcStr=srcStr.substring(first+org.length(),srcStr.length());
		     srcStr=firstCharacterToUpper(srcStr);
		    }
		   }
		   newString=newString+srcStr;
		   return newString;
		}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(replaceUnderlineAndfirstToUpper("ntyi_r4hao_abc","_",""));
		String str="{\"columnList\": [{\"columnName\": \"cust_id\", \"columnValue\": 123}, {\"columnName\": \"cust_name\",  \"columnValue\": \"chen\"}]}";
		JSONObject jsonObject = JSONObject.fromObject(str);
		
	  net.sf.json.JSONArray arry = jsonObject.getJSONArray("columnList");
	    
	  JSONObject a =   doJsonAray(arry);
	  
	 
		 
		  System.out.println(a.get("custId").toString()+"---"+a.get("custName").toString() +"----8888---");
		  
	 
	}
	/**
	 * 将arryJSON转换为json对象，并且替换字符串并让它的下一个字母为大写，入库使用
	 */
	public static JSONObject doJsonAray(net.sf.json.JSONArray arry)throws Exception{
		
		JSONObject nobj = new JSONObject();
		if(arry.size()>0)
		{
			for(int i=0;i<arry.size();i++)
			{
				JSONObject jobj = (JSONObject) arry.get(i) ;
				String key = jobj.get("columnName").toString();
				nobj.put(replaceUnderlineAndfirstToUpper(key,"_",""),jobj.get("columnValue"));

			}
		}
		return nobj;
	}
	/**
	 * 将arryJSON转换为json对象，发往密集框架使用
	 */
	public static JSONObject doJsonAraySend(net.sf.json.JSONArray arry)throws Exception{
		
		JSONObject nobj = new JSONObject();
		if(arry.size()>0)
		{
			for(int i=0;i<arry.size();i++)
			{
				JSONObject jobj = (JSONObject) arry.get(i) ;
				nobj.put(jobj.get("columnName"),jobj.get("columnValue"));

			}
		}
		return nobj;
	}
	
	public static String doKey (String columnName)throws Exception{
		String newKey =null;
		char[] cs = columnName.toCharArray();
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<cs.length;i++){
			
			if( String.valueOf(cs[i])=="_"||String.valueOf(cs[i]).equals("_")  ){//去掉下划线
				builder.append( String.valueOf(cs[i]).toUpperCase() );
			}else {
				builder.append( cs[i] );
			}
		}
		return newKey ;
	}
	
	/**
	 * 去掉_线，后转大写字母
	 * 
	 */
	private static Map<String, String>JsonToLine(List<Map<String, Object>> list) throws Exception{
		Map<String, Object> raw = null;
		for(Map<String, Object> m:list){
			if (!m.isEmpty()) {
				raw = m;
				break;
			}
		}
		Set<String> keys = raw.keySet();
		Map<String, String> map = new HashMap<String, String>();
		for(String key : keys){
			
				char[] cs = key.toCharArray();
				StringBuilder builder = new StringBuilder();
				for(int i=0;i<cs.length;i++){
					
					if( String.valueOf(cs[i])=="_"||String.valueOf(cs[i]).equals("_")  ){//去掉下划线
						builder.append( String.valueOf(cs[i]).toUpperCase() );
					}else {
						builder.append( cs[i] );
					}
				}
				map.put( key, builder.toString().toLowerCase() );
		}
		return 	map;
	}
	

}
