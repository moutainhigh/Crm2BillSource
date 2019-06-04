package com.al.nppm.business.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.al.nppm.business.common.dao.ICommonMapper;
import com.al.nppm.business.common.service.ICommonService;
import com.al.nppm.model.Offer;
import com.github.pagehelper.Page;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements ICommonService {
	
	public static Logger logger = Logger.getLogger(CommonServiceImpl.class);
	
	@Autowired
	protected ICommonMapper commonMapper;
	
	/**
	 * @return the commonMapper
	 */
	public ICommonMapper getCommonMapper() {
		return commonMapper;
	}

	/**
	 * @param commonMapper the commonMapper to set
	 */
	public void setCommonMapper(ICommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}

	@Override
	public Map<String, Object> getTest(Map<String, Object> map) throws Exception {
		System.out.println("====================================华丽丽的分割线================================");
		System.out.println("进入getTest方法");
		Map<String,Object> returnMap= commonMapper.getTest();		
		System.out.println("得到商品ID:"+returnMap.get("OFFER_ID"));
		System.out.println("得到商品ID:"+returnMap.get("OFFER_NAME"));
		System.out.println("====================================华丽丽的分割线================================");
		return returnMap;
	}
	
	@Override
	public Page fetchTableData(Map<String, Object> map)
			throws Exception {
	  // PageHelper.startPage(1,5,true);  //第一页 每页显示10条
	   Page page=commonMapper.fetchTableData(map);
	   return page;
	}
	
	@Override
	public int insertProductOffer(Offer offer)
	{
	commonMapper.insertProductOffer(offer);
		return 1;
	}
	
	//获取序列
	public Long getSeqByName(Map<String, Object> map){
		return commonMapper.getSeqByName(map);
	}
	
	//获取序列
	public Long getSeqByName(String seqName){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("seq_name", seqName);
		return commonMapper.getSeqByName(map);
	}

}
