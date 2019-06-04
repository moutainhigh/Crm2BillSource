package com.al.nppm.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.al.nppm.common.utils.StringUtil;

public class mqTest {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
/*		//SimpleDateFormat df = new SimpleDateFormat("YYYYMMDDHHmmss");
		SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(df.parse("2018-04-09 15:29:30").toString());
		System.out.println(d.format(df.parse("2018-04-09 15:29:30")));
		
		Date expDate = new Date(1100,1,1);
		System.out.println(df.format(expDate));
		*/
		String str ="18661263";
		System.out.print(getOFferObjRelIdForNine(str));
		
	}
	
	
	public static Long getOFferObjRelIdForNine(String offerObjRelId_str) {
		String offerObjRelId="";
		if(!StringUtil.isEmpty(offerObjRelId_str)){
			if(offerObjRelId_str.length()>9){
				int lengths = offerObjRelId_str.length();
				offerObjRelId = offerObjRelId_str.substring(0, 2);
				offerObjRelId_str = offerObjRelId_str.substring(2, lengths);
				offerObjRelId+=offerObjRelId_str.substring(lengths-9, lengths-2);
			}else{
				offerObjRelId =offerObjRelId_str;
			}
		}
		if(StringUtil.isEmpty(offerObjRelId)){
			offerObjRelId = "1";
		}
		return Long.parseLong(offerObjRelId);
	}


}
