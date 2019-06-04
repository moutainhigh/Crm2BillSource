package com.al.nppm.business.account.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.al.nppm.common.exception.AiException;
import com.al.nppm.model.BillServiceLog;
import com.al.nppm.model.CustContactInfoRel;
import com.al.nppm.model.PartyCert;

import net.sf.json.JSONObject;

public interface IProdInstService {
	
	public int doProdInst(Map map);
	
}
