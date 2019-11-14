package com.al.nppm.business.account.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IProdInstMapper {

	
	public int insertProdInst(Map map);
	public int insertProdInstState(Map map);
	public int insertProdInstStateExt(Map map);
	public int insertProdInstRegion(Map map);
	public int insertProdInstPyamode(Map map);
	public int insertProdInstAccNum(Map map);
	public int insertProdInstAttr(Map map);
	public int insertProdInstRel(Map map);
	public int insertProdInstSub(Map map);
	public int insertProdInstAttrSub(Map map);
	public int insertProdInstAcctRel(Map map);
	public int insertProdInstGroup(Map map);
	
	
	public int updateProdInst(Map map);
	public int updateProdInstForDisassembe(Map map);
	public int updateProdInstState(Map map);
	public int updateProdInstStateExt(Map map);
	public int updateProdInstPaymode(Map map);
	public int updateProdInstAccNum(Map map);
	public int updateProdInstRegion(Map map);
	public int updateProdInstAttr(Map map);
	public int updateProdInstAttrNet(Map map);// add by wangbaoqiang 断网复网
	public int updateProdInstRel(Map map);
	public int updateProdInstSub(Map map);
	public int updateProdInstAttrSub_1(Map map); // add by wangbaoqiang
	public List<Map<String,Object>> updateProdInstAttrSubAttr(Map map); //add by wangbaoqiang 
	public int updateProdInstAcctRel(Map map);
	public int updateProdInstGroup(Map map);
	public int updateProdInstAttrSub(Map map);

	
	
	
	public List<Map<String,Object>> getProdInstAcctId(Map map);
    public List<Map<String,Object>> getProdInstAcctIdForNull(Map map);
    public long getProdInstAcctCnt(Map map);
	public long getProdInstAcctInvalidCnt(Map map);
    public List<Map<String,Object>> getProdInstFromAcctId(Map map); //add by wangbaoqiang
	public List<Map<String,Object>> getProdInstOBJ(Map map); 
	public List<Map<String,Object>> getProdInstRegion(Map map);
	public List<Map<String,Object>> getProdInstAccNumId(Map map);
	public List<Map<String,Object>> getProdInstStateId(Map map);
	public List<Map<String,Object>> getProdInstPaymodeId(Map map);
	public List<Map<String,Object>> getProdInstAttrId(Map map);
	public List<Map<String,Object>> getProdInstAttrIdForSub(Map map);

	public List<Map<String,Object>> getProdInstSubId(Map map);
	public List<Map<String,Object>> getProdInstStateIdEx(Map map);
	public List<Map<String,Object>> getProdInstSubIdExp(Map map); //add by wangbaoqiang 
	public List<Map<String,Object>> selectProdInstAttrMap(Map map); //add by wangbaoqiang 
	public List<Map<String,Object>> selectProdInstAttrSubMap(Map map); //add by wangbaoqiang  updateProdInstAttrSubAttr
	public List<Map<String,Object>> selectProdInstSubMap(Map map); //add by wangbaoqiang   
	public List<Map<String,Object>> getProdInstAccNumFor1000(Map map); //add by wangbaoqiang
	public Map<String,Object> getProdInstIdFromAcctRel(Map map); //add by wangbaoqiang
	public List<Map<String,Object>> getProdInstAcctRelIdFromRelId(Map map); // add by wangbaoqiang
	public List<Map<String,Object>> getProdInstRelId(Map map);

	public List<Map<String, Object>> getProdInstRelIdFromInstId(Map map);

	public List<Map<String,Object>> getProdInstAcctRelId(Map map);

	public List<Map<String,Object>> getProductType(Map map);
	
	
	
	public long getSequeId();
	public long seqProdInstAcctRelId();
	
	
	
	//===================================
	public long getForLiCount(Map map);
	public long getForLiCountFlag(Map map);
	public long getForImsiCount(Map map);
	public long getForGsmImsiCount(Map map);
	public long getForLteImsiCount(Map map);
	//add by wangbaoqiang 添加号码类型1000的判断
	public long getForCountNumType1000(Map map);
	public long getForCountByProdInstId(Map map);
	public long getMinProdInstIdFromAcctRel(Map map);
	public long getForAccNumCount(Map map);

	public long getForProdInst2hx(Map map);

	public long getForCdmaImsiCount(Map map);
	public long getForGsmImsiCount2(Map map);
	public long getForLteImsiCount2(Map map);
	public long getForProdInstCount(Map map);
	public long getProdInstCount2Ha(Map map);//add by wangbaoqiang
	public long getMinProdInstId(Map map);//add by wangbaoqiang
	public long getForProdInstSubCount(Map map);
	public List<Map<String, Object>> getProdInstSubPrimaryKeyCnt(Map map);//add bywangbaoqiang

	public long getForProdInstAttrCount(Map map);
	public long getForProdInstAttrId(Map map);

	public List<Map<String, Object>> getProdInstAttrIdFromAttrId(Map map);

	public List<Map<String, Object>> getForNetWorkId(Map map);

	public long getCntProdInstIdFromAcctRel(Map map); //add by wangbaoqiang

	public int insertProdInstStateExt2(Map map);
	public int insertProdInstAcctRelAttr(Map map);

	
	public int updateProdInstServ(Map map);

	public int updateProdInstOwnerCust(Map map);

	public int updateProdInstBeginRentDate(Map map);

	public int updateProdInstUserCust(Map map);

	public int updateProdInstNumber(@Param("accNum") String accNum ,
									@Param("prodInstId") long prodInstId,
									@Param("routeId") long routeId);

	public int updateProdInstAddress(Map map);

	public int updateProdInstAccNum2(Map map);
	public int updateProdInstStateExt2(Map map);
	public int updateAccount(Map map);
	public int updateProdInstAcctRelAttr(Map map);
	
	public List<Long> getProdInstId(Map map);
	public List<Map<String, Object>> getProdInstState(Map map);
	public List<Long> getProdInstAccNumId2(Map map);
	public List<Map<String, Object>> getProdInstStateExt(Map map);
	public List<Map<String, Object>> getProdInstAccNum(Map map);
	public List<Map<String, Object>> getProdInst(Map map);
	public List<Map<String, Object>> getProdInstStateExtFromStateExt(Map map);
	public List<Map<String, Object>> getProdInstPaymode(Map map);
	//add by wangbaoqiang 
	public List<Long> getProdInstPaymode_1(Map map);
	public Map<String, Object> selectProdInstStateExt(Map map);
	public String getEffDatefromStateExt(Map map);
	public Map<String, Object> selectProdInstAccNum(Map map);
	public Map<String, Object> selectProdInstAccNum2(Map map);
	public Map<String, Object> selectProdInstAccNum3(Map map);
	public Map<String, Object> selectProdInstAccNum4(Map map);
	public Long selectProdInstAttrId(Map map);
	public Map<String, Object> getForProdInstAcctRelId(Map map);
	public List<Map<String, Object>> selectProdInstAttr1000(Map map);//add by wangaboqiang
	public List<Map<String, Object>> selectProdInstAttr1015(Map map);//add by wangaboqiang selectProdInstGroup
	public List<Map<String, Object>> selectProdInstGroup(Map map);//add by wangaboqiang 用户群
	public int insertSendMsg(Map map);
	public List<Map<String,Object>> selectSendMsg(Map map);
	public int updateSendMsgLog(Map map);
	public long getSeq(@Param("seq_name") String seq_name);
	public String getNextMonth(String effDate);
	public String getDay(String effDate);
	public String getMonth(String effDate);

	public long selectProdInstIdFromProdInstSub(Map map);
	public long selectProdInstAttrIdFromProdInstAttr(Map map);
	public long selectProdInstAttrIdFromProdInstAttrSub(Map map);
	public long selectMinProdInstId(Map map); // add by wangbaoqiang
	public int insertPayToPlan(Map map);
    public List<Map<String, Object>> getOneItemInstId(Map map);
    public List<Map<String, Object>> getOneItemAcctId(Map map);
	//public int inserCustChange(Map map);
	public int insertServAcctChange(Map map);
	public List<Map<String, Object>> selectPayToPlan(@Param("offerInstId") long offerInstId,
													 @Param("offerId") long offerId);
	public int insertSendMsgHis(Map map);
	public List<Map<String,Object>> selectSendMsgSucc(Map map);
	public int deleteSendMsg(Map map);
	public void udalStart();
}