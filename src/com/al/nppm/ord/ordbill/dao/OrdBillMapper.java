package com.al.nppm.ord.ordbill.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface OrdBillMapper {

	public List<Map<String, Object>> selectOrdBill(Map map);

	public int updateOrdBill(Map map);

	public List<Map<String, Object>> selectOrdBillObj(long archGrpId);

	public List<Map<String, Object>> selectBillObject(Map map);

	public List<Map<String, Object>> selectOrdCustomer(Map map);

	/*add by wangbaoqiang*/
	public List<Map<String, Object>> selectOrdCustomer_1300(Map map);

	/* add end */
	public List<Map<String, Object>> selectOrdTaxPayer(Map map);

	public List<Map<String, Object>> selectOrdTaxPayerAttr(Map map);

	public List<Map<String, Object>> selectOrdAccount(Map map);

	public List<Map<String, Object>> selectOrdPaymentPlan(Map map);

	public List<Map<String, Object>> selectOrdExtAcct(Map map);

	public List<Map<String, Object>> selectOrdProdInst(Map map);

	public List<Map<String, Object>> selectOrdProdInst1300(Map map);

	public List<Map<String, Object>> selectOrdProdInst1100(Map map);

	public List<Map<String, Object>> selectOrdProdInstState(Map map);

	public List<Map<String, Object>> selectOrdProdInstAccNum(Map map);

	public List<Map<String, Object>> selectOrdProdInstState1100(Map map);

	public List<Map<String, Object>> selectOrdProdInstSub(Map map);

	//add by wangbaoqiang begin
	public List<Map<String, Object>> selectOrdProdInstSub1300(Map map);

	// add end;
	public List<Map<String, Object>> selectOrdProdInstAttr(Map map);

	public List<Map<String, Object>> selectOrdProdInstAttrFromRowId(Map map);

	public List<Map<String, Object>> selectOrdProdInstAttrForTrunk(Map map); // add by wangbaoqiang增加中继判断

	public List<Map<String, Object>> selectOrdProdInstRel(Map map);

	public List<Map<String, Object>> selectOrdProdInstRelFromRowId(Map map);

	public Map<String, Object> selectOrdProdInstRelFrom1000(Map map); // add by wangbaoqiang

	public List<Map<String, Object>> selectOrdProdInstAcctRel(Map map);

	public List<Map<String, Object>> selectOrdProdInstAcctRel1000(Map map);

	public List<Map<String, Object>> selectOrdProdInstAcctRel1100(Map map); //add by wangbaoqiang

	public List<Map<String, Object>> selectOrdProdInstRelForGroup(Map map); //ad by wangbaoqiang

	public List<Map<String, Object>> selectOrdProdInstAttr1000(Map map); //ad by wangbaoqiang

	public List<Map<String, Object>> selectOrdProdInstAttr2000(Map map); //ad by wangbaoqiang

	public List<Map<String, Object>> selectOrdOfferInst(Map map);

	public List<Map<String, Object>> selectOrdOfferInstFromRowId(Map map); // add by wangbaoqiang

	public List<Map<String, Object>> selectOrdOfferProdInstRel(Map map);

	/*public List<Map<String, Object>> CheckOfferChangIsExists(Map map);*/
	public List<Map<String, Object>> selectOrdOfferProdInstRel1300(Map map);

	public List<Map<String, Object>> selectOrdOfferProdInstRelForProdInst(@Param("archGrpId") long archGrpId,
													 @Param("offerInstId") long offerInstId);

	public List<Map<String, Object>> selectOrdOfferProdInstRelForRole(Map map);

	public List<Map<String, Object>> selectOrdOfferObjInstRel(Map map);

	public List<Map<String, Object>> selectOrdOfferObjInstRelForObjectId(@Param("archGrpId") long archGrpId,
																		 @Param("offerInstId") long offerInstId);
	public List<Map<String, Object>> selectOrdOfferInstAttr(Map map);

	public List<Map<String, Object>> selectOrdOfferInstAttrDec(Map map);

	public List<Map<String, Object>> selectOrdOfferInstAttr1000(Map map);

	public List<Map<String, Object>> getOrdOfferInstOfferId(Map map);


	public Long getOrdAccountById(Map map);
	//判断接口成员表是否有数据
	public Long selectCntOrdOfferProdInst(Map map);


	public List<Map<String, Object>> getOfferInstAcctId(Map map);

	//	public Long getOrdAccountByAcctRel(Map map);
	public List<Map<String, Object>> getOrdAccountByAcctRel(Map map);


	//一次性费用处理
	public List<Map<String, Object>> getOneItemResult();

	public List<Map<String, Object>> getOneItemResultFromArchGrpId(@Param("archGrpId") long archGrpId,
																   @Param("orderItemId") long orderItemId);

	public List<Map<String, Object>> getOneItemResultHisFromArchGrpId(@Param("archGrpId") long archGrpId,
																   @Param("orderItemId") long orderItemId);

	public int insertPayToPlan(Map map);

	public int updateOneItemResult(Map m);

	public int insertOneItemResultHis(Map map);

	public int getPOfferPayPlan(Map m);

	public List<Map<String, Object>> insertTifFeeBill(Map map);

	public List<Map<String, Object>> getOneItemInstId(Map map);

	public List<Map<String, Object>> getOneItemAcctId(Map map);

	public List<Map<String, Object>> selectOrdProdInstByRoute(Map map);

	List<Map<String, Object>> queryOrdBill(@Param("bDate") Date bDate, @Param("eDate") Date eDate);

	List<Map<String, Object>> queryOrdBillExpDate(Map map);

	//	-------------------------- 参与人相关F_INSERT_PARTY开始   -------------------------
	public List<Map<String, Object>> getOrdParty(Map map);

	public List<Map<String, Object>> getOrdPartyInd(Map map);

	public int getCountOrdPartyInd(Map map);

	public List<Map<String, Object>> getOrdPartyRole(Map map);

	public int getCountOrdPartyRole(Map map);

	public List<Map<String, Object>> getOrdPartyAttr(Map map);

	public int getCountOrdPartyAttr(Map map);

	public List<Map<String, Object>> getOrdContactsInfo(Map map);

	public int getCountOrdContactsInfo(Map map);

	public List<Map<String, Object>> getOrdContactsInfoAttr(Map map);

	public int getCountOrdContactsInfoAttr(Map map);

	//	-------------------------- 客户相关F_INSERT_CUSTOMER开始   -------------------------
	public List<Map<String, Object>> getOrdCustContactInfoRel(Map map);

	public int getCountOrdCustContactInfoRel(Map map);

	public List<Map<String, Object>> getOrdPartyCert(Map map);

	public int getCountOrdPartyCert(Map map);

	public List<Map<String, Object>> getOrdCustAttr(Map map);

	//	-------------------------- 一般纳税相关F_INSERT_TAXPAYER开始   -------------------------
	public List<Map<String, Object>> getOrdTaxPayer(Map map);

	public int getCountOrdTaxPayer(Map map);

	public List<Map<String, Object>> getOrdTaxPayerAttr(Map map);

	public int getCountOrdTaxPayerAttr(Map map);

	//----------------------------账务关系PROD_INST_ACCT---------------------
	//operType=1000：新增       1100:修改
	public List<Map<String, Object>> getOrdProdInstAcctRelAttr(Map map);


	//----------------------------附属产品PROD_INST_SUB---------------------
	public Long getOperFlag(Map map);

	//----------------------------账户ord_account---------------------
	public List<Map<String, Object>> getAccount(Map map);

	//------------------------地域表comm_region------------------------
	public List<Map<String, Object>> insertTrunkbill(Map map);

	public List<Map<String, Object>> selectTifProdContrast(Map map);

	public List<Map<String, Object>> selectTifOrgContrast(Map map);

	public List<Map<String, Object>> selectTifProdAttribContrast(String attrId);

	public List<Map<String, Object>> selectTifSubProdContrast(String prodId);

	//----------------------------产品实例PRON_INST---------------------
	public List<Map<String, Object>> getOrdBillProdInst(Map map);

	//----------------------------总控表ORD_BILL---------------------
	public List<Map<String, Object>> getOrdBill(Map map);

	public List<Map<String, Object>> getOrdBillAndOrdBillProdInst(Map map);

	public Long getCntOrdBillObj(Map map);

	//-------------------增加停机轨迹判断--------------------------
	public Long selectCntOrdProdInstState(Map map);

	public List<Map<String, Object>> selectOrdProdInstStateFor1000(Map map);

	public List<Map<String, Object>> selectOrdProdInstStateFor1300(Map map);

	public List<Map<String, Object>> selectOrdBillCustId(long archGrpId);

	//--------------------CRM预存----------------------------------
	public List<Map<String, Object>> selectCrmRent(@Param("archGrpId") long archGrpId,
												   @Param("orderItemId") long orderItemId,
												   @Param("offerInstId") long offerInstId,
	                                               @Param("returnType") long returnType);

	public List<Map<String, Object>> selectOneItemResult(@Param("archGrpId") long archGrpId,
														 @Param("orderItemId") long orderItemId);

    public List<Map<String, Object>> selectTifObjectId(@Param("offerId") long offerId,
                                                 @Param("roleId") String roleId);
	public List<Map<String, Object>> selectTifParamId(@Param("offerId") long offerId,
													   @Param("attrId") String attrId);

	public List<Map<String,Object>> selectServiceOfferContrast(String servcieOfferId);

	public List<Map<String,Object>> selectOrdBillError(String errorNotes);

	public long selectOrdBillProdInstAcctRel(@Param("archGrpId") long archGrpId);

	public int delOrdBillProdInstAcctRel(long archGrpId);

	public List<Map<String,Object>> selectTifBreakDisct(@Param("offerId") long offerId);

	public List<Map<String,Object>> selectTifPayPlanContrast(@Param("payPlanFlag") long payPlanFlag);

	public int inserCustChange(Map map);

	public List<Map<String,Object>> selectTifNoNeedOffer(@Param("offerId") long offerId);

}