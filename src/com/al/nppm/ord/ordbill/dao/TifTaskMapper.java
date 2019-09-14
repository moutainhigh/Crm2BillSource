package com.al.nppm.ord.ordbill.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TifTaskMapper {
	
	public List<Map<String,Object>> getTifTaskCfgList(Map map);
	
}