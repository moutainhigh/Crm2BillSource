package com.al.nppm.business.syntomq.datasyn;

import com.al.nppm.business.syntomq.tool.CTGMqTool;
import com.al.nppm.business.syntomq.tool.STATUS;
import com.al.nppm.common.utils.DateUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

public class DataSynDeal {
	private static SimpleDateFormat df = new SimpleDateFormat( "yyyyMMddHHmmss" );
	private static SimpleDateFormat df1= new SimpleDateFormat( "yyyy-MM-dd" );
	private static SimpleDateFormat df2= new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
	public static Map<Integer, String> actionMap=new HashMap<Integer, String>(){{
			put(1, "INSERT");
			put(2, "UPDATE");
			put(3, "DELETE");
		}};
	public static Map<String, Object> buildParam(){
		Map<String, Object> param= new HashMap<String, Object>();
		String time = DateUtils.dateString( new Date(), DateUtils.yyyyMMddTIME );
		String seq = time+( int )(Math.random()*1000*1000*1000);
		param.put( "message_id", seq );
		param.put( "message_type", 10801 );
		param.put( "message_time", time );
		param.put( "crm_orderid", 1 );
		param.put( "custSoNumber", 1 );
		param.put( "CrmMQID", 1 );
		param.put( "CrmMsgStoreTime", DateUtils.dateString( new Date(), "yyyyMMddHHmmssSSS" ) );
		return param;
	}
	//一个表是list中的一项 一项是个map 表面对应表记录集合
	public static List<Map<String, List<?>>> mapToList(Map<String, List<?>> map) throws Exception{
		List<Map<String, List<?>>> list = new ArrayList<Map<String,List<?>>>();
		Set<String> keys = map.keySet();
		for(String key: keys){
			List<?> vList = map.get( key );
			if (!vList.isEmpty()) {
				vList = to_FieldList(vList);
				Map<String, List<?>> m = new HashMap<String, List<?>>();
				m.put( key, vList );
				list.add( m );	
			}
		}
		return list;
	}
	private static int obtainObjectCnt(List<Map<String, List<?>>> list){
		int returnInt = 0;
		for(Map<String, List<?>> m:list){
			Set<String> keys = m.keySet();
			for(String key:keys){
				List<?> l = m.get( key );
				returnInt = returnInt + l.size();
			}
		}
		return returnInt;
	}
	//像表记录的行集合
	
	@SuppressWarnings("unchecked")
	private static List<Map<String, Object>> to_FieldList(List<?> vList) throws Exception{
		List<Map<String, Object>> list = (List<Map<String, Object>>)vList;
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
//		Map<String, String> relationMap =  obtainRelation(list);//拿其中一行去转换成对应关系
		for(Map<String, Object> map : list){
			if (map.isEmpty()) {
				continue;
			}
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, String> relationMap =  obtainRelation(map);
			Set<String> keys = relationMap.keySet();
			if (!keys.contains( "action" )) {
				throw new Exception(JSON.toJSONString( map )+"没有actioin");
			}
			for(String key : keys){
				String vString = relationMap.get( key );
				if (key.endsWith( "Date" )) {
					Object obj =  map.get( key );
					if(obj!=null){
						if(obj instanceof String){
							String str=(String)obj ;
							if(str.length()==10){
								resultMap.put( vString, df.format(df1.parse(str) ) );
							}else if(str.length()==19){
								resultMap.put( vString, df.format(df2.parse(str) ) );
							}else{
								resultMap.put( vString, (String)obj );
							}
						}else{
							Date d = (Date)map.get( key );
							//System.out.println(JSON.toJSON("--String--"+map));
							resultMap.put( vString, df.format( d ) );
						}
					}
				}
				//送给密集计算框架的需要包含executetime字段
//				else if (key.equals( "executetime" )) {
//					//Date d = (Date)map.get( key );
//					resultMap.put( "update_date",map.get( key ));
//				}
				else if(key.equals( "action" )){
					resultMap.put( vString, actionMap.get(map.get( key )) );
				}
				else {
					resultMap.put( vString, map.get( key ) );
				}

				/*	Date d = (Date)map.get( key );
					System.out.println( "relationMap--"+JSON.toJSONString( relationMap ) );
					System.out.println( "map--"+JSON.toJSONString( map ) );
					resultMap.put( vString, df.format( d ) );*/
				/*}else {
					resultMap.put( vString, map.get( key ) );
				}*/
			}
			resultList.add( resultMap );
		}
		return resultList;
	}

	/*@SuppressWarnings("unchecked")
	private static List<Map<String, Object>> to_FieldList(List<?> vList) throws Exception{
		List<Map<String, Object>> list = (List<Map<String, Object>>)vList;
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String, String> relationMap =  obtainRelation(list.get( 0 ));//拿其中一行去转换成对应关系
		for(Map<String, Object> map : list){
			Map<String, Object> resultMap = new HashMap<String, Object>();
			System.out.println(JSON.toJSON("--resultMap--"+resultMap));
			Set<String> keys = relationMap.keySet();
			if (!keys.contains( "action" )) {
				throw new Exception(JSON.toJSONString( map )+"没有actioin");
			}
			for(String key : keys){
				String vString = relationMap.get( key );
				if (key.endsWith( "Date" )) {
					Object obj =  map.get( key );
					if(obj!=null)
					{
						Date d = (Date)map.get( key );
						System.out.println(JSON.toJSON("--String--"+map));
						resultMap.put( vString, df.format( d ) );
					}
				}else {
					resultMap.put( vString, map.get( key ) );
				}
			}
			resultList.add( resultMap );
		}
		return resultList;
	}*/
	//字段转下划线
	private static Map<String, String> obtainRelation(List<Map<String, Object>> list) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> raw = null;
		for(Map<String, Object> m:list){
			if (!m.isEmpty()) {
				raw = m;
				break;
			}
		}
		if(null==raw){
			return map;
		}
		Set<String> keys = raw.keySet();
		if (!keys.contains( "action" )) {
			throw new Exception(JSON.toJSONString( raw )+"没有actioin");
		}
		
		for(String key : keys){
			if (key.equals( "action" )) {
				map.put( key, "act_id" );
			}else if (key.equals( "ARCH_GRP_ID" ) || key.equals( "ORDER_ITEM_ID" )) {//去除这两个
				
			}else {
				char[] cs = key.toCharArray();
				StringBuilder builder = new StringBuilder();
				for(int i=0;i<cs.length;i++){
					if( 65<=cs[i] && cs[i]<=90 ){//大写
						builder.append( "_" ).append( cs[i] );
					}else {
						builder.append( cs[i] );
					}
				}
				map.put( key, builder.toString().toUpperCase() );
			}
		}
		return map;
	}

	
	private static Map<String, String> obtainRelation(Map<String, Object> raw) throws Exception{
		Set<String> keys = raw.keySet();
		if (!keys.contains( "action" )) {
			throw new Exception(JSON.toJSONString( raw )+"没有actioin");
		}
		Map<String, String> map = new HashMap<String, String>();
		for(String key : keys){
			if (key.equals( "action" )) {
				map.put( key, "action" );
			}else if (key.equals( "act_id" )) {
				map.put( key, "act_id" );
			}else if(key.equals( "route_cust_id" )){
                map.put( key, "route_cust_id" );
            }else if(key.equals( "executetime" )){
			map.put( key, "executetime" );
			}else if (key.equals( "ARCH_GRP_ID" ) || key.equals( "ORDER_ITEM_ID" )) {//去除这两个

			}else {
				char[] cs = key.toCharArray();
				StringBuilder builder = new StringBuilder();
				for(int i=0;i<cs.length;i++){
					if( 65<=cs[i] && cs[i]<=90 ){//大写
						builder.append( "_" ).append( cs[i] );
					}else {
						builder.append( cs[i] );
					}
				}
				map.put( key, builder.toString().toUpperCase() );
			}
		}
		return map;
	}
	//组装消息
	@SuppressWarnings("unchecked")
	public static Msg buildMsg(List<?> table,Map<String, Object> params){
		int cnt = obtainObjectCnt((List<Map<String, List<?>>>)table);
		params.put( "object_count", cnt );
		Msg msg = new Msg();
		msg.setstorage_rs( table );
		msg.merge( params );
		System.out.println( "---------------------------------打印报文----------------------------------------------------" );
		System.out.println( JSON.toJSON( msg.getMap() ).toString() );
		return msg;
	}
	//-----------------------
	//openApi 调用
	public static STATUS APISendMsg(List table) throws Exception{
		return APIbuildSendMsg(table,buildParam());
	}
	public static STATUS APIbuildSendMsg(List table,Map<String, Object> params) throws Exception{
		Msg msg = buildMsg(table,params);
		if (msg.check() == null) {
			return CTGMqTool.send( params.get( "message_id" ).toString(), 
					        params.get( "message_type" ).toString(), 
					        JSON.toJSON( msg.getMap() ).toString() );
		}
		return STATUS.MSGCHECKERR;
	}
	//openApi 调用----------------------
	public static Msg autoBuildMsg(Map<String, List<?>> synMap) throws Exception{
		return buildMsg(mapToList(synMap),buildParam());
	}
	public static STATUS autoBuildAndSendMsg(Map<String, List<?>> synMap) throws Exception{
		return buildAndSendMsg(mapToList(synMap),buildParam());
	}
	public static STATUS autoBuildAndSendMsg(String msg) throws Exception{
		return buildAndSendMsg(mapToList(opJsonString(msg)),buildParam());
	}
	//调用组装和发送函数
	public static STATUS buildAndSendMsg(List<?> table,Map<String, Object> params) throws Exception{
		Msg msg = buildMsg(table,params);
		if (msg.check() == null) {
			return CTGMqTool.send( params.get( "message_id" ).toString(), 
					        params.get( "message_type" ).toString(), 
					        JSON.toJSON( msg.getMap() ).toString() );
		}
		
		return STATUS.MSGCHECKERR;
	}
	//调用组装和发送函数
	public static int sendMsg(Msg msg) throws Exception{
		STATUS result;
		if (msg.check() == null) {
			result=CTGMqTool.send( msg.getMap().get("message_id").toString(), 
						msg.getMap().get("message_type").toString(), 
						        JSON.toJSON( msg.getMap() ).toString() );
		}else{
			result=STATUS.MSGCHECKERR;
		}
		
		return result.getCode();
	}	
	
	//--------------HTTP-----------------------------------
	private static Map<String, List<?>> opJsonString(String msg) throws Exception{
		//String msg = FileUtils.readFileToString( new File("F:\\Document\\Tencent\\390291060\\FileRecv\\json") );
		JSONObject object = JSON.parseObject( msg );
		removeOther(object);
		//---表  一key一表--
		Map<String, List<?>> resultMap = new HashMap<String, List<?>>();
		for(String key:object.keySet()){
			Object o = object.get( key );
			if (isArrObject(o)) {
				JSONArray oArray = (JSONArray)o;
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				for(Object jo:oArray){
					list.add( opDate((JSONObject)jo) );
				}
				resultMap.put( key.toLowerCase(), list );
			}else {
				resultMap.put( key.toLowerCase(), bulidOneList(opDate((JSONObject)o)) );
			}
		}
		return resultMap;
	}
	private static JSONObject removeOther(JSONObject obj){
		obj.remove( "ARCH_GRP_ID" );
		obj.remove( "ORDER_ITEM_ID" );
		obj.remove( "ACCT_ID" );
		obj.remove( "CUST_ID" );
		obj.remove( "FLAG" );
		obj.remove( "EXT_ACCT" );
		obj.remove( "PROD_INST_GROUP" );
		obj.remove( "TAX_PAYER" );
		obj.remove( "TAX_PAYER_ATTR" );
		obj.remove( "PAYMENT_PLAN" );
		obj.remove( "PROD_INST_STATE" );
		return obj;
	}
	private static boolean isArrObject(Object o){
		if (o.getClass()==JSONArray.class) {
			return true;
		}
		return false;
	}
	private static Map<String, Object> opDate(JSONObject obj) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Map<String, Object> oMap = new HashMap<String, Object>();
		for(String key:obj.keySet()){
			if (key.endsWith( "Date" )) {
				oMap.put( key, df.parse( obj.getString( key ) ) );
			}else if (key.equals( "operType" )) {
				oMap.put( "action" , obj.getInteger( key ).compareTo( 1000 )==0? 1:2 );
			}else {
				oMap.put( key, obj.get( key ) );
			}
		}
		return oMap;
	}
	private static List<Map<String, Object>> bulidOneList(Map<String, Object> map){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		list.add( map );
		return list;
	}
	
}
