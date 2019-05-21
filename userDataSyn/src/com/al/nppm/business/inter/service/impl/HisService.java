package com.al.nppm.business.inter.service.impl;

import com.al.nppm.business.account.dao.HisMapper;
import com.al.nppm.business.account.dao.IProdInstMapper;
import com.al.nppm.business.inter.service.IHisService;
import com.al.nppm.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/1 9:25
 * @File : HisService
 * @Software: IntelliJ IDEA 2019.3.15
 */
@Service("hisService")
public class HisService implements IHisService {
	@Autowired
    public HisMapper hisDao;
	@Autowired
    public IProdInstMapper prodinstDao;
	/*
	 * @2019-04-01
	 * @nieqt
	 * */
	@Override
	public int insertCustomerHis(Map map) throws Exception {
		// TODO Auto-generated method stub
		long seqId= prodinstDao.getSeq("SEQ_CUSTOMER_HIS_ID");
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("customerSeq", seqId);
		map.put("insertDate", d.format(new Date()));
		try{
			hisDao.insertCustomerHis(map);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	/*
	 * @2019-04-01
	 * @nieqt
	 * */
	@Override
	public int insertCustContactInfoRelHis(Map map) throws Exception {
		// TODO Auto-generated method stub
		long seqId= prodinstDao.getSeq("SEQ_CUST_CONTACT_INFO_REL_HIS_ID");
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("custContactInfoRelSeq", seqId);
		map.put("insertDate", d.format(new Date()));
		try{
			hisDao.insertCustContactInfoRelHis(map);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
		return 1;
	}

	/**
	 * @2019-04-01
	 * @lih
	 * 新增账户
	 */
	@Override
	public int insertAccountHis(Map map) {
		// TODO Auto-generated method stub
		try{
			hisDao.insertAccountHis(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	/**
	 * @2019-04-01
	 * @lih
	 * 新增外部支付账户
	 */
	@Override
	public int insertExtAcctHis(Map map) {
		// TODO Auto-generated method stub
		try{
			hisDao.insertExtAcctHis(map);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return 1;
	}

	/**
	 * 获取产品实例表的信息
	 * @maozp3
	 * @param itemMap
	 * @param userMap
	 * @param msg
	 * @return
	 */
    @Override
    public int selectProdInst(Map itemMap, Map userMap, Message msg) {
        Map prodInstMap = new HashMap();
        prodInstMap = hisDao.selectProdInstHis(itemMap);
        if (prodInstMap == null ||prodInstMap.isEmpty()) {
            msg.setMessage("获取产品实例失败，prodInstId：" + itemMap.get("prodInstId"));
            return -1;
        }
        //备份原有prodInst数据
        if(insertProdInst(prodInstMap,userMap,msg)<=0){
            return -1;
        }
        return 1;
    }

	/**
	 * 增加产品实例信息到历史记录表
	 * @maozp3
	 * @param itemMap
	 * @param userMap
	 * @param msg
	 * @return
	 */
    @Override
    public int insertProdInst(Map itemMap, Map userMap, Message msg) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        //更新日期
        String insertDate = df.format(new Date());
        itemMap.put("insertDate",insertDate);
        //序列
        Long prodInstSeq = prodinstDao.getSeq("SEQ_PROD_INST_HIS_ID");
        itemMap.put("prodInstSeq",prodInstSeq);
        try{
            hisDao.insertProdInstHis(itemMap);
        }catch (Exception e){
            msg.setMessage("更新产品实例历史信息失败：prodInstId:"+itemMap.get("prodInstId"));
            e.printStackTrace();
            return -1;
        }
        return 1;
    }
}
