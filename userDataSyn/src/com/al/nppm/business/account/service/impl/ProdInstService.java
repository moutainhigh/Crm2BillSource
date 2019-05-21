package com.al.nppm.business.account.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.al.nppm.business.account.dao.IAccountMapper;
import com.al.nppm.business.account.dao.IProdInstMapper;
import com.al.nppm.business.account.service.IAccountService;
import com.al.nppm.business.account.service.IProdInstService;

import com.al.nppm.business.common.SpringContextHolder;
import com.al.nppm.business.common.service.impl.CommonServiceImpl;
import com.al.nppm.business.inter.http.state.statePublic;

import com.al.nppm.common.aop.DataSourceMap;
import com.al.nppm.common.exception.AiException;

import com.al.nppm.common.utils.MaptoClass;
import com.al.nppm.common.utils.StringUtil;

import com.al.nppm.model.Customer;
import com.al.nppm.model.Offer;
import com.al.nppm.model.BillServiceLog;
import com.al.nppm.model.CustAttr;
import com.al.nppm.model.CustContactInfoRel;
import com.al.nppm.model.PartyCert;
import com.al.nppm.test.untilTest;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("prodService")
@Transactional
public class ProdInstService implements IProdInstService {
	public static Logger logger = Logger.getLogger(ProdInstService.class);
	@Autowired
	public IProdInstMapper prodinstDao;
	
	
	
	//处理prod_inst相关表
	@Override
	public int doProdInst(Map map) {
		
	 try{
			if(Integer.parseInt(map.get("operType").toString())==1000)
			{	
				//1.prod_inst
				prodinstDao.insertProdInst(map);
				map.put("stopType", 0);
				//2.prod_inst_state
				prodinstDao.insertProdInstState(map);
				map.put("stopType", 0);
				map.put("state", map.get("statusCd").toString());
				//3.prod_inst_state_ext
				prodinstDao.insertProdInstStateExt(map);
				//4.prod_inst_region
				prodinstDao.insertProdInstRegion(map);
				//5.prod_inst_paymode
				prodinstDao.insertProdInstPyamode(map);
				
				
			}else{
				
				//prodinstDao.updateExtAcct(map);
			}
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("处理PROD_INST表异常:"+e.getMessage());
			return -1;
		}
		return 1;
	}
	

	
	
}
