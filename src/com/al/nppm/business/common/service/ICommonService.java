package com.al.nppm.business.common.service;

import java.util.Map;

import com.al.nppm.model.Offer;
import com.github.pagehelper.Page;



public interface ICommonService {

	/**
	 * 
	 * @param map
	 * @throws Exception
	 */
	public Map<String, Object> getTest(Map<String,Object> map) throws Exception;
	
	/**
	 * 
	 * @param map
	 * @throws Exception
	 */
	public Page<Map<String, Object>> fetchTableData(Map<String,Object> map) throws Exception;

	/**
	 * 根据序列名字获取序列
	 * @param map
	 * @return
	 */
	public Long getSeqByName(Map<String, Object> map);
	
	public Long getSeqByName(String seqName);
	
	public int insertProductOffer(Offer offer);
}
