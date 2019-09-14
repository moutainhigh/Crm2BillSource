package com.al.nppm.business.account.service.impl;

import com.al.nppm.business.account.service.ITifTaskCfgService;
import com.al.nppm.ord.ordbill.dao.TifTaskMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("tifTaskCfgService")
@Transactional
public class TifTaskCfgService  implements ITifTaskCfgService{
	public static Logger logger = Logger.getLogger(TifTaskCfgService.class);
	@Autowired
	public TifTaskMapper tifTaskDao;
	
	@Override
	public List<Map<String, Object>> getTifTaskCfgList(Map map) {
		List<Map<String, Object>> tifTaskList=tifTaskDao.getTifTaskCfgList(map);
		return tifTaskList;
	}

}
