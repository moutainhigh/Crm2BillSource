package com.al.nppm.business.inter.service;

import java.util.List;
import java.util.Map;

import com.al.nppm.model.Message;

public interface IinterService {

	
	public int userServiceCol(String str,Message msg) throws Exception;
	
	//public int userDataSynCol(String str,Message msg) throws Exception;
	public int userDataSynCol(String str,Message msg,List list) throws Exception;
	
	public List<Map<String,Object>> selectOrdBill();
	
	public long getSequenId();
}
