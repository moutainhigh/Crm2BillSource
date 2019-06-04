package com.al.nppm.business.account.dao;

import java.util.List;
import java.util.Map;

public interface TifVpnGroupMapper {

	public List<Map<String,Object>> selectTifVpnGroup(Map map);
	public int insertTifVpnGroup(Map map);
	public long selectCntTifVpnMem(Map map);
	public int insertTifVpnMem(Map map);
	public List<Map<String,Object>> selectTifVpnMem(Map map);
	public List<Map<String,Object>> selectTifVpnMemId(Map map);
	public int updateTifVpnMem(Map map);//


}