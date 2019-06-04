package com.al.nppm.business.inter.http.state;

import java.util.Date;
import com.al.nppm.common.utils.StringUtil;
public class statePublic {
	
	public static String SUCESS ="SUCESS";
	public static String FAIL ="FAIL";
	public static int FAILFLAG=400;
	public static int SUCESSFLAG=800;
	
	
	//public static String EXPDATE="3000-01-01 00:00:00";
	
	public static Date expDate = new Date(1100,0,1);
	
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
