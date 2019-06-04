package com.al.nppm.business.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


import com.al.nppm.model.Offer;
import com.github.pagehelper.Page;


@Repository
public interface ICommonMapper {

	/**
	 * 例子
	 */
	public Map<String,Object> getTest() throws Exception;
	
	/**
	 * 例子
	 */
	public Page fetchTableData(Map<String, Object> params) throws Exception;
	
	/**
	 * 例子
	 */
	public Integer fetchTableDataCount(Map<String, Object> params) throws Exception;
	
	/**
	 * 根据序列名称获取序列值 seq_name
	 * @param params
	 * @return
	 */
	public Long getSeqByName(Map<String, Object> params);
	
	public int  insertProductOffer(Offer offer);
	
}
