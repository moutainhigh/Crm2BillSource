package com.al.nppm.common.utils;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.JSONUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.al.common.exception.BaseException;
import com.al.common.utils.StringUtil;

/**
 * Json-lib的封装类，实现json字符串与json对象之间的转换
 * 网友johncon提供稍作修改
 * 
 * modify：
 * 	2009-8-31 zhaoxin :增加NUMBER_NULL_JSONCONF,处理Number的子类null会默认转为0的问题
 * @author zhaoxin 
 *
 */
public class JsonUtil {

	/**
	 * 处理number及其子类的null转换
	 * 具体的使用例子:
	 * List<Order> orderList = new ArrayList<Order>();
	 * orderList.add(new Order());
	 * JSONObject json = new JSONObject();
	 * json.elementOpt("order", orderList,JsonUtil.NUMBER_NULL_JSONCONF);
	 * 通过以上的代码,order对象中的Integer属性如果有null的，就会自动转成json的null，不会默认为0
	 */
	public static final JsonConfig NUMBER_NULL_JSONCONF = createNumberNullValueJsonConfig();
	//处理number的null与日期类（日期类型有年月日）
	public static final JsonConfig DEFAULT_AND_DATTE_JSONCONF = createDefaultAndDateJsonConfig();
	
	//处理number的null与日期类(日期类型无年月日)
	public static final JsonConfig DEFAULT_AND_DATTE_JSONCONF_YMD = createDefaultAndDateJsonConfigYmd();

	
	private static Logger LOG = LoggerFactory.getLogger(JsonUtil.class);
	
	
	//chy  add by 20180324
	public static boolean parseJson(String json)
	{
		  try {
			   com.alibaba.fastjson.JSONObject jsonStr= com.alibaba.fastjson.JSONObject.parseObject(json);
		        return  true;
		   } catch (Exception e) {
		        return false;
		  }
	}
	
	//chy  add by 20180323
	public static String returnObjMsg(int serviceId,String resultMsg,String resultCode)
	{
		JSONObject obj = new JSONObject();
		obj.accumulate("serviceId", serviceId);
		obj.accumulate("resultCode", resultCode);
		obj.accumulate("resultMsg", resultMsg);
		
		return obj.toString();
		
	}
	
	public static String returnObj(int flag,String message)
	{
		JSONObject obj = new JSONObject();
		obj.accumulate("flag", flag);
		obj.accumulate("message", message);
		return obj.toString();
		
	}
	/**
	 * 判断List是否为null或者空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isListEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}
	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，形如： {"id" : idValue, "name" : nameValue,
	 * "aBean" : {"aBeanId" : aBeanIdValue, ...}}
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static Object getObject(String jsonString, Class clazz) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			LOG.error("转换object异常:", e);
		}
		return JSONObject.toBean(jsonObject, clazz);
	}

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如： {"id" : idValue, "name" :
	 * nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}, beansList:[{}, {},
	 * ...]}
	 * 
	 * @param jsonString
	 * @param clazz
	 * @param map
	 *            集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" :
	 *            Bean.class)
	 * @return
	 */
	public static Object getObject(String jsonString, Class clazz, Map map) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			LOG.error("json string convert to obj error:",e);
			return null;
		}
		return JSONObject.toBean(jsonObject, clazz, map);
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static Object[] getObjectArray(String jsonString, Class clazz) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 * @return
	 */
	public static Object[] getObjectArray(String jsonString, Class clazz, Map map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz, map);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static List getObjectList(String jsonString, Class clazz) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz));
		}
		return list;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合，其中对象中包含有集合属性
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 *            集合属性的类型
	 * @return
	 */
	public static List getObjectList(String jsonString, Class clazz, Map map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz, map));
		}
		return list;
	}

	/**
	 * 从json HASH表达式中获取一个map，该map支持嵌套功能 形如：{"id" : "johncon", "name" : "小强"}
	 * 
	 * @param object
	 * @return
	 */
	public static Map getMap(String jsonString) {
		setDataFormat2JAVA();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map map = new HashMap();
		for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			map.put(key, jsonObject.get(key));
		}
		return map;
	}

	/**
	 * 从json数组中得到相应java数组 json形如：["123", "456"]
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArray(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}

	private static void setDataFormat2JAVA() {
		// 设定日期转换格式
		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss",
						"yyyy-MM-dd" }));
	}

	/**
	 * 把对象转换为json字符串 日期类型为默认的: YYYY-MM-DD HH:MM:SS
	 * 
	 * @param obj
	 * @return
	 */
	public static String getJsonString(Object obj) {
		if (obj == null) {
			return "{}";
		}
		return getJsonString(obj, DEFAULT_AND_DATTE_JSONCONF);
	}
	/**
	 * 取json字符串
	 * @param obj
	 * @param cfg
	 * @return
	 */
	public static String getJsonString(Object obj, JsonConfig cfg) {
		if (obj != null) {

			if (isArray(obj)) {
				JSONArray jsonArray = JSONArray.fromObject(obj, cfg);
				return jsonArray.toString();
			} else {

				JSONObject jsonObject = JSONObject.fromObject(obj, cfg);
				return jsonObject.toString();
			}
		}
		return "{}";
	}
	
	public static String getStringNX(JSONObject jsonO,String key){
		if(jsonO.containsKey(key)){
			return jsonO.getString(key);
		}
		return "";
	}
	/**
	 * 对象是否是数组
	 * @param obj
	 * @return
	 */
	private static boolean isArray(Object obj) {
		return obj instanceof Collection || obj.getClass().isArray();
	}
	/**
	 * 处理number及其子类的null转换
	 * @return
	 */
	public static JsonConfig createNumberNullValueJsonConfig(){
		JsonConfig conf = new JsonConfig();
		registerDefaultNullValueProcessor(conf);
		return conf;

	}
	/**
	 * 处理全部
	 * @return
	 */
	public static JsonConfig createDefaultAndDateJsonConfig(){
		JsonConfig conf = new JsonConfig();
		registerDefaultNullValueProcessor(conf);
		registerDateValueProcessor(conf);
		return conf;
	}
	
	/**
	 * 处理全部返回无年月日的类型
	 * @return
	 */
	public static JsonConfig createDefaultAndDateJsonConfigYmd(){
		JsonConfig conf = new JsonConfig();
		registerDefaultNullValueProcessor(conf);
		registerDateValueProcessorYmd(conf);
		return conf;
	}
	/**
	 * 处理日期类转换 返回时间有带时分秒
	 * @param conf
	 */
	public static void registerDateValueProcessor(JsonConfig conf){
		conf.registerJsonValueProcessor(java.util.Date.class,
				new JsonDateValueProcessor());
	}
	
	
	/**
	 * 处理日期类转换 返回时间去掉时分秒
	 * @param conf
	 */
	public static void registerDateValueProcessorYmd(JsonConfig conf){
		conf.registerJsonValueProcessor(java.util.Date.class,
				new JsonDateValueProcessorYmd());
	}
	/**
	 * 注册json的处理类
	 * @param conf
	 */
	public static void registerDefaultNullValueProcessor(JsonConfig conf){
		conf.registerDefaultValueProcessor(Number.class,new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(AtomicInteger.class,new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(BigDecimal.class,new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(BigInteger.class,new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Byte.class,new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Double.class,new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Float.class,new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Integer.class,new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Long.class,new DefaultNullValueProcessor());
		conf.registerDefaultValueProcessor(Short.class,new DefaultNullValueProcessor());		
	}
	/**
	 * null空值特殊处理类
	 * @author zhaoxin
	 *
	 */
	public static class DefaultNullValueProcessor implements DefaultValueProcessor{
		  public Object getDefaultValue(Class type){
	            return JSONNull.getInstance();
	        }
	}
	
	/**
	 * 判断入参的json数据中是否包含key，如果不存在则报错
	 * @param json
	 * @param key
	 * @param errorMsg 如果不存在key异常中的提示信息
	 * */

	public static void isContainsParam(JSONObject json, String key,String errorMsg) {
		if (!json.containsKey(key)) {
			throw new BaseException(errorMsg+"JSON数据中" + key + "为空");
		}
	}
	
	/**
	 * 从JSONObject获取Long值
	 * 
	 * @param jsonObj
	 * @param key
	 */
	public static Long getLongFromJSON(JSONObject jsonObj,String key,String errorMsg){
		checkJSONObjectNullErrorThrow(jsonObj,new StringBuffer(errorMsg).append("从JSON：").append(jsonObj).append(
		        "中获取Long型的[").append(key).append("]失败，原因是：JSON本身为Null").toString());
		if(!jsonObj.containsKey(key) || "".equals(jsonObj.getString(key)) || "null".equals(jsonObj.getString(key))){
			return null;
		}
		try{
			return jsonObj.getLong(key);
		}catch(Exception e){
			throw new BaseException(new StringBuffer().append(errorMsg)
			        .append("从JSON：").append(jsonObj).append("中获取Long型[").append(key).append("]失败，原因是：").append(
			                e.getMessage()).toString());
		}
	}

	/**
	 * 从JSONObject获取Integer值
	 * 
	 * @param jsonObj
	 * @param key
	 */
	public static Integer getIntFromJSON(JSONObject jsonObj,String key,String errorMsg){
		checkJSONObjectNullErrorThrow(jsonObj,new StringBuffer(errorMsg).append("从JSON：").append(jsonObj).append(
		        "中获取Integer型的[").append(key).append("]失败，原因是：JSON本身为Null").toString());
		if(!jsonObj.containsKey(key) || "".equals(jsonObj.getString(key)) || "".equals(jsonObj.getString(key))
		        || "null".equals(jsonObj.getString(key))){
			return null;
		}
		try{
			return jsonObj.getInt(key);
		}catch(Exception e){
			throw new BaseException(new StringBuffer()
			        .append(errorMsg).append("从JSON：").append(jsonObj).append("中获取Integer型[").append(key).append(
			                "]失败，原因是：").append(e.getMessage()).toString());
		}
	}

	/**
	 * 从JSONObject获取String值
	 * 
	 * @param jsonObj
	 * @param key
	 */
	public static String getStringFromJSON(JSONObject jsonObj,String key,String errorMsg){
		checkJSONObjectNullErrorThrow(jsonObj,new StringBuffer(errorMsg).append("从JSON：").append(jsonObj).append(
		        "中获取String型的[").append(key).append("]失败，原因是：JSON本身为Null").toString());
		if(!jsonObj.containsKey(key)){
			return null;
		}
		try{
			String s = jsonObj.getString(key);
			if("null".equals(s) || "".equals(s)){
				s = null;
			}
			return s;
		}catch(Exception e){
			throw new BaseException(new StringBuffer().append(errorMsg)
			        .append("从JSON：").append(jsonObj).append("中获取String型[")
			        .append(key).append("]失败，原因是：").append(e.getMessage()).toString());
		}
	}

	/**
	 * 从JSONObject获取Date值
	 * 
	 * @param jsonObj
	 * @param key
	 */
	public static Date getDateFromJSON(JSONObject jsonObj,String key,String errorMsg){
		checkJSONObjectNullErrorThrow(jsonObj,new StringBuffer(errorMsg).append("从JSON：").append(jsonObj).append(
		        "中获取Date型的[").append(key).append("]失败，原因是：JSON本身为Null").toString());
		if(!jsonObj.containsKey(key)){
			return null;
		}
		try{
			String s = jsonObj.getString(key);
			if("null".equals(s) || "".equals(s)){
				return null;
			}else{
				try{
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
				}catch(java.text.ParseException e){
					return new SimpleDateFormat("yyyy-MM-dd").parse(s);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new BaseException(new StringBuffer().append(errorMsg)
			        .append("从JSON：").append(jsonObj).append("中获取Date型[").append(key).append("]失败，原因是：").append(
			                e.getMessage()).toString());
		}
	}

	/**
	 * 从JSONObject获取Long值，null也抛异常
	 * 
	 * @param jsonObj
	 * @param key
	 */
	public static Long getNotNullLongFromJSON(JSONObject jsonObj,String key,String errorMsg){
		checkJSONObjectNullErrorThrow(jsonObj,new StringBuffer(errorMsg).append("从JSON：").append(jsonObj).append(
		        "中获取Long型的[").append(key).append("]失败，原因是：不存在该属性JSON本身为Null").toString());
		if(!jsonObj.containsKey(key) || "".equals(jsonObj.getString(key)) || "null".equals(jsonObj.getString(key))){
			throw new BaseException(new StringBuffer().append(errorMsg)
			        .append("从JSON：").append(jsonObj).append("中获取非NUll的Long型[").append(key).append(
			                "]失败，原因是：不存在该属性或者值为NULL").toString());

		}else{
			try{
				return jsonObj.getLong(key);
			}catch(Exception e){
				throw new BaseException(new StringBuffer().append(
				        errorMsg).append("从JSON：").append(jsonObj)
				        .append("中获取Long型的[").append(key).append("]失败，原因是：")
				        .append(e.getMessage()).toString());
			}
		}
	}

	public static Long getDataFromJSON(JSONObject jsonObj,String key,String errorMsg){

		if(!jsonObj.containsKey(key) || "".equals(jsonObj.getString(key)) || "null".equals(jsonObj.getString(key))){
			return null;
		}else{
			try{
				return jsonObj.getLong(key);
			}catch(Exception e){
				throw new BaseException(new StringBuffer().append(
				        errorMsg).append("从JSON：").append(jsonObj).append("中获取[").append(key).append("]失败，原因是：")
				        .append(e.getMessage()).toString());
			}
		}
	}

	/**
	 * 从JSONObject获取Integer值，null也抛异常
	 * 
	 * @param jsonObj
	 * @param key
	 */
	public static Integer getNotNullIntFromJSON(JSONObject jsonObj,String key,String errorMsg){
		checkJSONObjectNullErrorThrow(jsonObj,new StringBuffer(errorMsg).append("从JSON：").append(jsonObj).append(
		        "中获取Integer型的[").append(key).append("]失败，原因是：JSON本身为Null").toString());
		if(!jsonObj.containsKey(key) || "".equals(jsonObj.getString(key)) || "null".equals(jsonObj.getString(key))){
			throw new BaseException(new StringBuffer()
			        .append(errorMsg).append("从JSON：").append(jsonObj)
			        .append("中获取非NUll的Integer型[").append(key).append(
			                "]失败，原因是：不存在该属性或者值为NULL").toString());

		}else{
			try{
				return jsonObj.getInt(key);
			}catch(Exception e){
				throw new BaseException(new StringBuffer().append(
				        errorMsg).append("从JSON：").append(jsonObj).append("中获取Integer型的[").append(key).append(
				        "]失败，原因是：").append(e.getMessage()).toString());
			}
		}
	}

	/**
	 * 从JSONObject获取String值，null也抛异常
	 * 
	 * @param jsonObj
	 * @param key
	 */
	public static String getNotNullStringFromJSON(JSONObject jsonObj,String key,String errorMsg){
		checkJSONObjectNullErrorThrow(jsonObj,new StringBuffer(errorMsg).append("从JSON：").append(jsonObj).append(
		        "中获取String型的[").append(key).append("]失败，原因是：JSON本身为Null").toString());
		if(!jsonObj.containsKey(key) || "".equals(jsonObj.getString(key)) || "null".equals(jsonObj.getString(key))){
			throw new BaseException(new StringBuffer().append(errorMsg)
			        .append("从JSON：").append(jsonObj).append("中获取非NUll的String型[").append(key).append(
			                "]失败，原因是：不存在该属性或者值为NULL").toString());
		}
		try{
			return jsonObj.getString(key);
		}catch(Exception e){
			throw new BaseException(new StringBuffer().append(errorMsg)
			        .append("从JSON：").append(jsonObj).append("中获取String型的[")
			        .append(key).append("]失败，原因是：").append(e.getMessage()).toString());
		}
	}

	/**
	 * 从JSONObject获取Date值
	 * 
	 * @param jsonObj
	 * @param key
	 */
	public static Date getNotNullDateFromJSON(JSONObject jsonObj,String key,String errorMsg){
		checkJSONObjectNullErrorThrow(jsonObj,new StringBuffer(errorMsg).append("从JSON：").append(jsonObj).append(
		        "中获取Date型的[").append(key).append("]失败，原因是：JSON本身为Null").toString());
		if(!jsonObj.containsKey(key) || "".equals(jsonObj.getString(key)) || "null".equals(jsonObj.getString(key))){
			throw new BaseException(new StringBuffer(errorMsg).append(
			        "从JSON：").append(jsonObj).append("中获取非NUll的Date型[").append(key)
			        .append("]失败，原因是：不存在该属性或者值为NULL").toString());
		}
		try{
			String s = jsonObj.getString(key);
			try{
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
			}catch(java.text.ParseException e){
				return new SimpleDateFormat("yyyy-MM-dd").parse(s);
			}
		}catch(Exception e){
			throw new BaseException(new StringBuffer(errorMsg).append(
			        "从JSON：").append(jsonObj).append("中获取Date型的[").append(key).append("]失败，原因是：")
			        .append(e.getMessage()).toString());
		}
	}

	public static void checkJSONObjectNullErrorThrow(JSONObject jsonObj,String errorMsg){
		if(jsonObj == null){
			throw new BaseException(errorMsg);
		}
	}

	public static JSONObject getOrCreateJSONObject(JSONObject jsonObj,String key){
		if(!jsonObj.containsKey(key)){
			jsonObj.element(key,new JSONObject());
			return jsonObj.getJSONObject(key);
		}else{
			return jsonObj.getJSONObject(key);
		}
	}

	public static JSONArray getOrCreateJSONArray(JSONObject jsonObj,String key){
		if(!jsonObj.containsKey(key)){
			jsonObj.element(key,new JSONArray());
			return jsonObj.getJSONArray(key);
		}else{
			return jsonObj.getJSONArray(key);
		}
	}

	/**
	 * 移除json的key在这个set中的子节点
	 * 
	 * @param json
	 * @param keys
	 */
	public static void removeJSONKeysInTheSet(JSONObject json,Set<String> keys){
		for(Iterator<String> i = keys.iterator();i.hasNext();){
			String key = i.next();
			json.remove(key);
		}
	}

	/**
	 * 移除json的key不在这个set中的子节点
	 * 
	 * @param json
	 * @param keys
	 */
	public static void removeJSONKeysOutTheSet(JSONObject json,Set<String> keys){
		Set<String> removeSet = new HashSet<String>();
		for(Iterator<String> k = json.keys();k.hasNext();){
			String key = k.next();
			if(!keys.contains(key)){
				removeSet.add(key);
			}
		}
		for(Iterator<String> k = removeSet.iterator();k.hasNext();){
			String key = k.next();
			json.remove(key);
		}
	}

	/**
	 * <pre>
	 * 根据名字从jsonObject中获得对应的属性值,
	 * 若果不存在对应的key，抛出SMO运行时异常
	 *  主要用来处理页面的必输入项
	 * </pre>
	 * 
	 * @param obj
	 * @param name
	 * @return
	 */
	public static Long getNotNullLongByKey(JSONObject obj,String name){
		if(!obj.has(name)){
			throw new BaseException("该json对象中没有该键值/属性(" + name + ")");
		}
		return Long.valueOf(obj.getLong(name));
	}

	/**
	 * 根据名字从jsonObject中获得对应的属性值,若果不存在对应的key，返回 null
	 * 
	 * @param json
	 * @param key
	 * @return int
	 */
	public static Integer getIntByKey(JSONObject jsonObj,String strKey){
		if(jsonObj.containsKey(strKey)){
			Object obj = jsonObj.get(strKey);
			if(obj == null){
				return null;
			}else if(JSONNull.getInstance().equals(obj)){
				return null;
			}else if(jsonObj.getString(strKey).toString().equals("")){
				return null;
			}else{
				return Integer.valueOf(jsonObj.getString(strKey));
			}
		}else{
			return null;
		}
	}

	/**
	 * 填充json可选值接口
	 * 
	 * @param keyMap
	 *            { key json节点title及全大写后为valueMap的key value 数值值 }
	 * @param valueMap
	 *            { value 属性值名称 }
	 * @param jo
	 *            jo
	 * @param jo
	 * @return JSONObject
	 */
	public static JSONObject fillSimpleJson(Map<String,Object> keyMap,Map<String,String> valueMap,JSONObject jo){
		for(Object key : keyMap.keySet()){
			if(JsonUtil.isNotBlank(keyMap.get(key))){
				String resKey = key.toString().toUpperCase();
				String vaule = valueMap.containsKey(resKey) ? String.valueOf(valueMap.get(resKey)) : "";
				JsonUtil.fillSimpleJO(jo,keyMap.get(key),vaule,key.toString());
			}
		}
		return jo;
	}

	public static JSONObject fillSimpleJO(JSONObject jo,Object cd,Map<String,String> joinMap,String title){
		/**
		 * 向源json加入K-V格式json,如加入 "prodCategoryCode": { "prodCategoryCodeCd":
		 * "123", "prodCategoryCodeText": "名称" }
		 */
		JSONObject joTmp = new JSONObject();
		if(cd != null && !StringUtil.isEmpty(cd.toString())){
			joTmp.accumulate(title + "Cd",cd);
			joTmp.accumulate(title + "Text",joinMap.get(cd.toString()));
		}else{
			joTmp.accumulate(title + "Cd","");
			joTmp.accumulate(title + "Text","");
		}
		jo.put(title,joTmp);
		return jo;

	}

	public static JSONObject fillSimpleJO(JSONObject jo,Object cd,String text,String title){
		JSONObject joTmp = new JSONObject();
		if(cd != null && !StringUtil.isEmpty(cd.toString())){
			joTmp.accumulate("cd",cd);
			joTmp.accumulate("text",text);
//			joTmp.accumulate(title + "Cd",cd);
//			joTmp.accumulate(title + "Text",text);
		}else{
			joTmp.accumulate("cd","");
			joTmp.accumulate("text","");
//			joTmp.accumulate(title + "Cd","");
//			joTmp.accumulate(title + "Text","");
		}
		jo.put(title,joTmp);
		return jo;

	}

	// 界面要求取得编码的形式
	public static JSONObject fillSimpleJO(JSONObject jo,Object id,
			Object name,Object code,Object outTitle,String inTitle){
		JSONObject joTmp = new JSONObject();
		if(id != null && !StringUtil.isEmpty(id.toString())){
			joTmp.accumulate(inTitle + "Id",id);
			joTmp.accumulate(inTitle + "Name",name);
			joTmp.accumulate(inTitle + "Code",code);
		}else{
			joTmp.accumulate(inTitle + "Id","");
			joTmp.accumulate(inTitle + "Name","");
			joTmp.accumulate(inTitle + "Code","");
		}
		jo.put(outTitle,joTmp);
		return jo;
	}

	public static JSONObject fillSimpleJO(JSONObject jo,Object id,Object name,Object code,String title){
		return fillSimpleJO(jo,id,name,code,title,title);
	}

	public static JSONObject getJSONObject(JSONObject jo,String title){
		if(jo.has(title)){
			JSONObject resJo = jo.getJSONObject(title);
			if(isNotBlank(resJo) && !resJo.isEmpty()){
				return resJo;
			}
			return null;
		}else{
			return null;
		}
	}

	public static JSONArray getJSONArray(JSONObject jo,String title){
		if(jo.has(title)){
			Object resJo = jo.get(title);
			if(isNotBlank(resJo) && resJo instanceof JSONArray){
				return (JSONArray)resJo;
			}
		}
		return new JSONArray();
	}
	
	public static JSONArray getMustJSONArray(JSONObject jo,String title){
		JSONArray result = new JSONArray();
		if(jo.has(title)){
			Object resJo = jo.get(title);
			if(isNotBlank(resJo)){
				if(resJo instanceof JSONArray){
					result = (JSONArray)resJo;
				}else if(resJo instanceof JSONObject){
					result.add(resJo);
				}
			}
		}
		return result;
	}

	// --清理
	public static JSONObject clearAtrr(JSONObject jo,String title){
		if(jo.has(title) && isBlank(jo.get(title))){
			jo.remove(title);
		}
		return jo;
	}

	public static JSONObject clearAtrr(JSONObject jo,String[] titles){
		for(String title : titles){
			clearAtrr(jo,title);
		}
		return jo;
	}

	// --净化
	public static JSONObject clearCd(JSONObject jo,String title){
		if(jo.has(title)){
			JSONObject joParam = getJSONObject(jo,title);
			if(joParam != null){
				Object cd = joParam.get(title + "Cd");
				jo.put(title,cd);
			}
		}
		return jo;

	}

	public static JSONObject clearCd(JSONObject jo){
		if(jo == null){
			return null;
		}
		Iterator iter = jo.keys();
		while(iter.hasNext()){
			String title = iter.next().toString();
			Object o = jo.get(title);
			if(o instanceof JSONObject){
				JSONObject jsonObject = (JSONObject)o;
				if((jsonObject.containsKey(title + "Cd") 
						&& jsonObject.containsKey(title + "Text") && jsonObject.size() == 2) 
							|| (jsonObject.containsKey(title + "Cd") &&  jsonObject.size() == 1)){
					jo.put(title,jsonObject.get(title + "Cd"));
				}
			}
		}
		return jo;
	}

	public static JSONObject clearId(JSONObject jo,String title){
		return clearId(jo,title,title);
	}

	public static JSONObject clearId(JSONObject jo,String outTitle,String inTitle){
		if(jo.has(outTitle)){
			JSONObject joParam = getJSONObject(jo,outTitle);
			if(joParam != null){
				Object id = joParam.get(inTitle + "Id");
				jo.put(inTitle + "Id",id);
			}
			jo.remove(outTitle);
		}
		return jo;
	}

	public static JSONObject adornToJOClass(JSONObject jo,String comClassId){
		JSONObject joClass = new JSONObject();
		joClass.accumulate("ComClassId",comClassId);
		joClass.accumulate("param",jo);
		return joClass;
	}

	public static JSONObject remove(JSONObject jo,String title){
		if(jo.has(title)){
			jo.remove(title);
		}
		return jo;
	}

	public static JSONObject remove(JSONObject jo,String[] titles){
		if(titles == null || titles.length == 0){
			return jo;
		}
		for(String title : titles){
			remove(jo,title);
		}
		return jo;
	}

	@SuppressWarnings("rawtypes")
	public static Object toBean(JSONObject jo,Class rootClass,Map classMap,JsonConfig jsonConfig,String[] excludes){
		if(jo == null){
			return null;
		}
		if(jsonConfig == null){
			jsonConfig = new JsonConfig();
		}
		if(rootClass != null){
			jsonConfig.setRootClass(rootClass);
		}
		if(classMap != null){
			jsonConfig.setClassMap(classMap);
		}
		NotComprisePropertyFilter filter = null;
		if(excludes == null){
			filter = new NotComprisePropertyFilter();
		}else{
			filter = new NotComprisePropertyFilter(excludes);
		}
		jsonConfig.setJavaPropertyFilter(filter);
		String[] dateFormats = new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"};
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats),true);
		Object res = JSONObject.toBean(jo,jsonConfig);
		return res;
	}

	@SuppressWarnings("rawtypes")
	public static Object toBean(JSONObject jo,Class rootClass,Map classMap){
		return toBean(jo,rootClass,classMap,null,null);
	}

	@SuppressWarnings("rawtypes")
	public static Object toBean(JSONObject jo,Class rootClass,String[] excludes){
		return toBean(jo,rootClass,null,null,excludes);
	}

	@SuppressWarnings("rawtypes")
	public static Object toBean(JSONObject jo,Class rootClass){
		return toBean(jo,rootClass,null,null,null);
	}

	@SuppressWarnings("rawtypes")
	public static Object toBean(JSONObject jo,Class rootClass,Map classMap,String[] excludes){
		return toBean(jo,rootClass,classMap,null,excludes);
	}

	@SuppressWarnings({"rawtypes","deprecation","static-access"})
	public static List toList(JSONArray jsonArray,Class rootClass,Map classMap,JsonConfig jsonConfig,String[] excludes){
		if(jsonArray == null || jsonArray.isEmpty()){
			return null;
		}
		if(jsonConfig == null){
			jsonConfig = new JsonConfig();
		}
		if(rootClass != null){
			jsonConfig.setRootClass(rootClass);
		}
		if(classMap != null){
			jsonConfig.setClassMap(classMap);
		}
		NotComprisePropertyFilter filter = null;
		if(excludes == null){
			filter = new NotComprisePropertyFilter();
		}else{
			filter = new NotComprisePropertyFilter(excludes);
		}
		jsonConfig.setJavaPropertyFilter(filter);
		List list = jsonArray.toList(jsonArray,jsonConfig);
		return list;
	}

	@SuppressWarnings("rawtypes")
	public static List toList(JSONArray jsonArray,Class rootClass,String[] excludes){
		return toList(jsonArray,rootClass,null,null,excludes);
	}

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
		if(str != null){
			String s = str.toString();
			return "".equals(s) || "{}".equals(s) || "[]".equals(s) || "null".equals(s);
		}else{
			return true;
		}
	}
	
	public static final boolean isBlank(JSONObject json,String key){
		if(isBlank(json) || isBlank(key)){
			return true;
		}else{
			return !json.containsKey(key) || isBlank(json.get(key));
		}
	}
	public static final boolean isNotBlank(JSONObject json,String key){
		return !isBlank(json,key);
	}	
	
	/**
	 * 向指定路径的json写入数据
	 * 
	 * @param json
	 *            json
	 * @param pathName
	 *            pathName
	 * @param value
	 *            value
	 * @return Object
	 * @throws BmoException
	 */
	public static Object putObjectByPathKey(JSONObject json,String pathName,Object value){
		if(json == null || isBlank(pathName)){
			return "";
		}
		String[] keys = pathName.split("\\.");
		if(keys.length == 1){
			if(!json.containsKey(keys[0])) {
				json.put(keys[0],value);
			} else {
				return "";
			}
		}else if(json.containsKey(keys[0])){
			if(json.get(keys[0]) instanceof JSONObject){
				JSONObject jo = json.getJSONObject(keys[0]);
				Object obj = putObjectByPathKey(json.getJSONObject(keys[0]),pathName.substring(keys[0].length() + 1),value);
				if(!"".equals(obj)){
					json.put(keys[0],obj);
				} else {
					json.put(keys[0],jo);
					return "";
				}
			} else if (json.get(keys[0]) instanceof JSONArray){
				JSONArray ja = (JSONArray)json.get(keys[0]);
				if(ja.size() == 1){
					putObjectByPathKey((JSONObject)ja.get(0), pathName.substring(keys[0].length() + 1),value);
				} else {
					for(Object obj : ja){
						JSONObject jo = (JSONObject)obj;
						if(!"".equals(putObjectByPathKey(jo,pathName.substring(keys[0].length() + 1),value))){
							break;
						}
					}
				}
			}
		}else{
			json.put(keys[0],new JSONObject());
			return putObjectByPathKey(json,pathName,value);
		}
		return json;
	}

	/**
	 * B类视图填充专用
	 * 向指定路径的json写入数据(节点可能在数组中)
	 * 
	 * @param json 数据
	 * @param pathName 路径
	 * @param value 值
	 * @param equalExpression 数组等价条件:参照 MEA_2_JSON_PATH_CFG表
	 * @return ..
	 */
	public static Object putObjectWithArrayByPathKey(JSONObject json,String pathName,Object value,String equalExpression){
		if(json == null || isBlank(pathName)){
			return "";
		}
		String[] keys = pathName.split("\\.");
		if(keys.length == 1){
			if(!json.containsKey(keys[0])){
				json.put(keys[0],"");
			}
			json.put(keys[0],formatData(json.getString(keys[0]),value.toString()));
		}else if(json.containsKey(keys[0])){
			if(json.get(keys[0]) instanceof JSONObject){
				json.put(keys[0],
					putObjectWithArrayByPathKey(json.getJSONObject(keys[0]),
										pathName.substring(keys[0].length() + 1),value,equalExpression));
			}else if (json.get(keys[0]) instanceof JSONArray){
				JSONArray ja = (JSONArray)json.get(keys[0]);
				// 数组长度等于1
				if(ja.size() == 1 
					// B类填充定价信息对应的配置能力的值
					// beyondScript-->beyondProcMode.procModeAttr[0].procModeInstAttrValue
					// pricingScript-->pricingProcMode.procModeAttr[0].procModeInstAttrValue
					|| (ja.size()>0 && ja.getJSONObject(0).containsKey("attrCode"))){
					putObjectWithArrayByPathKey((JSONObject)ja.get(0),
										pathName.substring(keys[0].length() + 1),value,equalExpression);
				}else{
					int i = 0;
					// 数组长度大于1
					for(Object obj : ja){
						JSONObject jo = (JSONObject)obj;
						String[] conditons = equalExpression.split("==");
						if(conditons.length > 1){
							List<String> equalValues = Arrays.asList(conditons[1].split(";"));
							// json串中含有数组的情况下，数组筛选条件(单个多个)，示例如下：
							// pricingClassify.cd==101100  单个
							// pricingClassify.cd==102000;102100;102330;102400 多个
							if(equalValues.contains(getValueByPath(jo,conditons[0]))){
								putObjectWithArrayByPathKey((JSONObject)((JSONArray)json.get(keys[0])).get(i),
										pathName.substring(keys[0].length() + 1),value,equalExpression);
								if(conditons[1].equals("102000;102100;102330;102400")){
									break;
								}
							// json串中含有数组的情况下，数组筛选条件(全部)，示例如下：
							// pricingClassify.cd==all 该数组下所有内容，不再区分
							}else if(equalValues.contains("all")){
								putObjectWithArrayByPathKey((JSONObject)((JSONArray)json.get(keys[0])).get(i),
										pathName.substring(keys[0].length() + 1),value,equalExpression);
								if(conditons[1].equals("102000;102100;102330;102400")){
									break;
								}
							}
						}
						i++;
					}
				}
			}
		}else{
			json.put(keys[0],new JSONObject());
			return putObjectWithArrayByPathKey(json,pathName,value,equalExpression);
		}
		return json;
	}
	
	/**
	 * 根据路径获取节点数据
	 * @param jo 数据源
	 * @param path 路径
	 * @return 返回数据
	 */
	public static String getValueByPath(JSONObject jo,String path) {
		String[] pathNodes = path.split("\\.");
		if(pathNodes.length > 1){
			return getValueByPath(jo.getJSONObject(pathNodes[0]),path.substring(pathNodes[0].length() + 1));
		}
		
		return jo.getString(pathNodes[0]);
	}
	
	/**
	 *  提取数值进行修正
	 * @param data 模板数据
	 * @param realData EXCEL数据
	 * @return ..
	 */
	private static String formatData(String data,String realData) {
		Pattern p=Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?");
		Matcher m=p.matcher(data); 
		// data.indexOf("-") 过滤标题的横杠
		if(m.find() && data.indexOf("-") < 0 && !"".equals(realData) && !m.group().equals("0")){
			data = data.replace(m.group(), realData);   
		}else if("".equals(realData)){
			data = "";
		}else{
			data = realData;
		} 
		return data;
	}

	/**
	 * 获取指定路径的json数据
	 * @param json 数据
	 * @param pathName 路径
	 * @param equalExpression 数组等价条件:参照 BIZ_REPORT_JSON_PATH_CFG表
	 * @param returnVal 返回值
	 * @return ..
	 */
	public static String getDataWithArrayByPathKey(JSONObject json,String pathName,
			String equalExpression,Map<String, String> returnVal){
		
		if(json == null || isBlank(pathName)){
			return "";
		}
		String[] keys = pathName.split("\\.");
		if(keys.length == 1){
			if(!json.containsKey(keys[0])){
				return "";
			}
			returnVal.put("val", json.getString(keys[0]).toString());
		}else if(json.containsKey(keys[0])){
			if(json.get(keys[0]) instanceof JSONObject){
				returnVal.put(keys[0],
						getDataWithArrayByPathKey(json.getJSONObject(keys[0]),
										pathName.substring(keys[0].length() + 1),equalExpression,returnVal));
			}else if (json.get(keys[0]) instanceof JSONArray){
				int i = 0;
				JSONArray ja = (JSONArray)json.get(keys[0]);
				// 数组长度等于1
				if(ja.size() == 1){
					getDataWithArrayByPathKey((JSONObject)ja.get(0),
										pathName.substring(keys[0].length() + 1),equalExpression,returnVal);
				}else{
					// 数组长度大于1
					for(Object obj : ja){
						JSONObject jo = (JSONObject)obj;
						String[] conditons = equalExpression.split("==");
						if(conditons.length > 1){
							List<String> equalValues = Arrays.asList(conditons[1].split(";"));
							if(equalValues.contains(getValueByPath(jo,conditons[0]))){
								getDataWithArrayByPathKey((JSONObject)((JSONArray)json.get(keys[0])).get(i),
										pathName.substring(keys[0].length() + 1),equalExpression,returnVal);
								break;
							}
						}
						i++;
					}
				}
			}
		}else{
			return returnVal.get("val").toString();
		}
		
		return returnVal.get("val").toString();
	}
	
	
	/**
	 * 获取json数组对象
	 * @param jo 源
	 * @param path 路径
	 * @return json数组对象
	 */
	public static JSONArray getJsonArrayObjByPath(JSONObject jo,String path){
		String[] pathNodes = path.split("\\.");
		if(pathNodes.length > 1){
			return getJsonArrayObjByPath(jo.getJSONObject(pathNodes[0]),path.substring(pathNodes[0].length() + 1));
		}
		
		//return json.getString(pathNodes[0]);
		return jo.getJSONArray(pathNodes[0]);
	}
	

}
