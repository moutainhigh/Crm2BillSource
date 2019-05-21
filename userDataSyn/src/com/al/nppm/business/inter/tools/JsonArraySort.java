package com.al.nppm.business.inter.tools;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonArraySort {

	public static JSONArray JsonArraySort(JSONArray tableArray1)  throws Exception{
		JSONArray sortJsonarr=new JSONArray();
		List<JSONObject> jsonValue=new ArrayList<JSONObject>();
		for(int i=0;i<tableArray1.size();i++){
		jsonValue.add(tableArray1.getJSONObject(i));
		}
		Collections.sort(jsonValue,new Comparator<JSONObject>() {
		private static final String key="tableOrder";
		public int compare(JSONObject a, JSONObject b) {
		Integer valA=a.getIntValue(key);
		Integer valB=b.getIntValue(key);
		return valA.compareTo(valB);
		}
		});
		for(int i=0;i<tableArray1.size();i++){
		sortJsonarr.add(jsonValue.get(i));
		}
		System.out.println(sortJsonarr.toJSONString());
		
		return sortJsonarr;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result="[{\"RANGE_area\": \"[0,15]\", \"city\": \"长沙市\", \"county\":\"天心区\",\"SUM_area\": 100,\"AVG_length\": 10 },"
				+"{ \"RANGE_area\": \"(15,30]\", \"city\": \"长沙市\",\"county\":\"天心区\",\"SUM_area\": 200,\"AVG_length\": 10 },"
				+"{ \"RANGE_area\": \"(30,60]\", \"city\": \"长沙市\", \"county\": \"天心区\",\"SUM_area\": 200, \"AVG_length\": 10 },"
				+ "{\"RANGE_area\":\"[0,15]\", \"city\": \"长沙市\", \"county\": \"开福区\", \"SUM_area\": 200, \"AVG_length\": 10 },"
				+ "{\"RANGE_area\": \"(15,30]\", \"city\": \"长沙市\", \"county\": \"开福区\", \"SUM_area\": 500, \"AVG_length\": 10 },"
				+ "{ \"RANGE_area\": \"[0,10]\", \"city\": \"衡阳市\", \"county\": \"衡阳县\", \"SUM_area\": 200, \"AVG_length\": 10 },"
				+ "{ \"RANGE_area\": \"(30,60]\", \"city\": \"衡阳市\", \"county\": \"衡阳县\", \"SUM_area\": 200, \"AVG_length\": 10 },"
				+ "{ \"RANGE_area\": \"(15,30]\", \"city\": \"衡阳市\", \"county\": \"祁东县\", \"SUM_area\": 200, \"AVG_length\": 10 },"
				+ "{ \"RANGE_area\": \"(30,60]\", \"city\": \"株洲市\", \"county\": \"醴陵市\", \"SUM_area\": 80, \"AVG_length\": 10 },"
				+ "{ \"RANGE_area\": \"[0,10]\", \"city\": \"湘潭市\", \"county\": \"茶陵市\", \"SUM_area\": 200, \"AVG_length\": 10 },]";
				JSONArray arr=JSON.parseArray(result);
				JSONArray sortJsonarr=new JSONArray();
				List<JSONObject> jsonValue=new ArrayList<JSONObject>();
				for(int i=0;i<arr.size();i++){
				jsonValue.add(arr.getJSONObject(i));
				}
				Collections.sort(jsonValue,new Comparator<JSONObject>() {
				// private static final String key="city";
				private static final String key="SUM_area";
				public int compare(JSONObject a, JSONObject b) {
				// String valA=a.getString(key);
				// String valB=b.getString(key);
				Integer valA=a.getIntValue(key);
				Integer valB=b.getIntValue(key);
				return valA.compareTo(valB);
				}
				});
				for(int i=0;i<arr.size();i++){
				sortJsonarr.add(jsonValue.get(i));
				}
				System.out.println(sortJsonarr.toJSONString());
				}
		

}
