package com.al.nppm.ord.ordbill.dao;

import com.al.nppm.business.common.ResourceUtil;
import com.al.nppm.common.utils.StringUtil;

import java.util.Map;
import java.util.ResourceBundle;

public class OrdBillHisProvider {
//	private static PropertiesUtil propertiesUtil=new PropertiesUtil("config/tableColumns.properties");
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config/tableColumns");
//	private static String ord_party =propertiesUtil.readProperty("ord_party");
	
	public String selectOrdBill(Map map) {
		String sql = "select ARCH_GRP_ID,CUST_ORDER_ID,REGION_ID,CREATE_ORG_ID,CREATE_STAFF,CREATE_DATE,FINISH_DATE," +
				"PROC_FLAG,PROC_DATE,PROC_CNT,NOTES,CUST_ID from ord_bill " +
				"where proc_flag=1 and PROC_DATE<date_sub(now(), interval 48 hour) " +
				" and FINISH_DATE<date_sub(now(), interval 48 hour) "+
				" limit 0,1000 ";


		return sql;
	}
	
	public String insertOrdBillHis(Map map) {
		String sql="insert into ord_bill_his( ARCH_GRP_ID,CUST_ORDER_ID,REGION_ID,CREATE_ORG_ID,CREATE_STAFF,CREATE_DATE," +
				"FINISH_DATE,PROC_FLAG,PROC_DATE,PROC_CNT,NOTES,CUST_ID)" +
				" values(#{ARCH_GRP_ID},#{CUST_ORDER_ID},#{REGION_ID},#{CREATE_ORG_ID},#{CREATE_STAFF},#{CREATE_DATE}," +
				"#{FINISH_DATE},#{PROC_FLAG},#{PROC_DATE},#{PROC_CNT},#{NOTES},#{CUST_ID})";
		return sql;
	}
	
	public String deleteOrdBill(Map map) {
		String sql="delete from  ord_bill where ARCH_GRP_ID=#{ARCH_GRP_ID}";
		return sql;
	}
	
	public String selectOrdBillObj(Map map) {
		String sql="select ARCH_GRP_ID,SEQ_NO,ORDER_ITEM_ID,SERVICE_OFFER_ID,OBJ_ID,TABLE_NAME," +
				"CREATE_DATE,SERVICE_OFFER_NAME,OBJ_SPEC,OBJ_SPEC_NAME,ORDER_ITEM_CD,STATUS_CD," +
				"STATUS_DATE,REMARK from ORD_BILL_OBJ where ARCH_GRP_ID=#{ARCH_GRP_ID}";
		return sql;
	}
	
	public String insertOrdBillObjHis(Map map) {
		String sql = "insert into ORD_BILL_OBJ_HIS(ARCH_GRP_ID,SEQ_NO,ORDER_ITEM_ID,SERVICE_OFFER_ID," +
				"OBJ_ID,TABLE_NAME,CREATE_DATE,SERVICE_OFFER_NAME,OBJ_SPEC,OBJ_SPEC_NAME,ORDER_ITEM_CD," +
				"STATUS_CD,STATUS_DATE,REMARK) values(#{ARCH_GRP_ID},#{SEQ_NO},#{ORDER_ITEM_ID}," +
				"#{SERVICE_OFFER_ID},#{OBJ_ID},#{TABLE_NAME},#{CREATE_DATE},#{SERVICE_OFFER_NAME}," +
				"#{OBJ_SPEC},#{OBJ_SPEC_NAME},#{ORDER_ITEM_CD},#{STATUS_CD},#{STATUS_DATE},#{REMARK})";
		return sql;
	}
	
	public String deleteOrdBillObj(Map map) {
		String sql="delete from  ORD_BILL_OBJ where ARCH_GRP_ID=#{ARCH_GRP_ID} ";
		return sql;
	}
	
	public String selectOrdBillProdInst(Map map) {
		String sql="select ARCH_GRP_ID,PROD_INST_ID,CREATE_DATE from ORD_BILL_PROD_INST where ARCH_GRP_ID=#{ARCH_GRP_ID}";
		return sql;
	}
	
	public String insertOrdBillProdInstHis(Map map) {
		String sql="insert into ORD_BILL_PROD_INST_HIS(ARCH_GRP_ID,PROD_INST_ID,CREATE_DATE) " +
				"values(#{ARCH_GRP_ID},#{PROD_INST_ID},#{CREATE_DATE})";
		return sql;
	}
	
	public String deleteOrdBillProdInst(Map map) {
		String sql="delete from  ORD_BILL_PROD_INST where ARCH_GRP_ID=#{ARCH_GRP_ID}";
		return sql;
	}
	
	public String selectOrdTable(Map map) {
		String sql=ResourceUtil.selectSql.get(map.get("tableName").toString());
		if(StringUtil.isEmpty(sql)){
			sql="select %s from %s where ARCH_GRP_ID=#{ARCH_GRP_ID} and ORDER_ITEM_ID=#{ORDER_ITEM_ID} ";
			if("ORD_BILL".equals(map.get("tableName").toString())
					||"ORD_BILL_OBJ".equals(map.get("tableName").toString())
					||"ORD_BILL_PROD_INST".equals(map.get("tableName").toString())
					|"ONE_ITEM_RESULT".equals(map.get("tableName").toString())){
				sql="select %s from %s where ARCH_GRP_ID=#{ARCH_GRP_ID}  ";
			}
			String columns=bundle.getString(map.get("tableName").toString());
			sql=String.format(sql, columns,map.get("tableName").toString());
			ResourceUtil.selectSql.put(map.get("tableName").toString(),sql);
		}
		return sql;
	}


	public String insertOrdTableHis(Map map) {
		String sql=ResourceUtil.insertSql.get(map.get("tableName").toString());
		if(StringUtil.isEmpty(sql)){
			sql="insert into TABLENAME(%s)  values(%s)";
			String columns=bundle.getString(map.get("tableName").toString());
			String values=formatValues(columns);
			sql=String.format(sql, columns,values);
			ResourceUtil.insertSql.put(map.get("tableName").toString(),sql);
		}
		return sql.replaceFirst("TABLENAME",
				map.get("tableName").toString()+"_"+map.get("MONTH").toString());
//		return sql.replaceFirst("TABLENAME",
//				map.get("TABLE_NAME").toString());
	}
	
	public String deleteOrdTable(Map map) {
		String sql="delete from  "+map.get("tableName").toString()+
				" where ARCH_GRP_ID=#{ARCH_GRP_ID} and ORDER_ITEM_ID=#{ORDER_ITEM_ID}";
		if("ORD_BILL".equals(map.get("tableName").toString())
				||"ORD_BILL_OBJ".equals(map.get("tableName").toString())
				||"ORD_BILL_PROD_INST".equals(map.get("tableName").toString())
				|"ONE_ITEM_RESULT".equals(map.get("tableName").toString())){
			sql="delete from  "+map.get("tableName").toString()+
					" where ARCH_GRP_ID=#{ARCH_GRP_ID} ";
		}
		return sql;
	}
	
	private String formatValues(String columns){
		StringBuffer result=new StringBuffer();
		String[] col=columns.split(",");
		for(int i=0;i<col.length;i++){
			result.append("#{"+col[i]+"}");
			if(i<col.length-1){
				result.append(",");
			}
		}
		return result.toString();
	}



	public String selectOneItemResult(Map map) {
		String sql="select PROC_FLAG procFlag from ONE_ITEM_RESULT "+
				" where ARCH_GRP_ID=#{ARCH_GRP_ID} and IF_CHARGE_OFF = 1 ";
		return sql;
	}
	public String selectOrdOfferInst(Map map) {
		String sql="select OFFER_INST_ID offerInstId,OFFER_ID offerId,STATUS_CD statusCd from ORD_OFFER_INST "+
				" where ARCH_GRP_ID=#{ARCH_GRP_ID} ";
		return sql;
	}

}
