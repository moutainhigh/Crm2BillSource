package com.al.nppm.test;

//import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class jsonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String  ret ="{\"content\":{\"disk\":{\"dis\":9},{\"dis\":3}},\"head\":{\"reqseq\":\"11201809081002101041\",\"respcode\":\"0\",\"resptime\":\"20180908100249\",\"resptype\":\"1\"}}";
		//JSONObject json1 = JSONObject.parseObject(ret);

		JSONObject json = JSONObject.fromObject(ret);
		System.out.println(json.toString());
		JSONObject cotent = json.getJSONObject("content");
		System.out.println(cotent.toString());
    	JSONArray item = cotent.getJSONArray("disk");
	}

}
