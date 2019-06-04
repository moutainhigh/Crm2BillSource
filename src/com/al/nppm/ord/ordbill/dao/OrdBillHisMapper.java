package com.al.nppm.ord.ordbill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
@Repository
public interface OrdBillHisMapper {
	@SelectProvider(type=OrdBillHisProvider.class,method="selectOrdBill")
	public List<Map<String,Object>> selectOrdBill(Map map);
	
	@InsertProvider(type=OrdBillHisProvider.class,method="insertOrdBillHis")
	public int insertOrdBillHis(Map map);
	
	@DeleteProvider(type=OrdBillHisProvider.class,method="deleteOrdBill")
	public int deleteOrdBill(Map map);
	
	@SelectProvider(type=OrdBillHisProvider.class,method="selectOrdBillObj")
	public List<Map<String,Object>> selectOrdBillObj(Map map);
    
	@InsertProvider(type=OrdBillHisProvider.class,method="insertOrdBillObjHis")
	public void insertOrdBillObjHis(Map ordMap);
	
	@DeleteProvider(type=OrdBillHisProvider.class,method="deleteOrdBillObj")
	public void deleteOrdBillObj(Map ordMap);
	
	@SelectProvider(type=OrdBillHisProvider.class,method="selectOrdBillProdInst")
	public List<Map<String,Object>> selectOrdBillProdInst(Map map);
	
	@InsertProvider(type=OrdBillHisProvider.class,method="insertOrdBillProdInstHis")
	public void insertOrdBillProdInstHis(Map ordMap);

	@DeleteProvider(type=OrdBillHisProvider.class,method="deleteOrdBillProdInst")
	public void deleteOrdBillProdInst(Map ordMap);
	
	
	@SelectProvider(type=OrdBillHisProvider.class,method="selectOrdTable")
	public List<Map<String,Object>> selectOrdTable(Map map);
	
	@InsertProvider(type=OrdBillHisProvider.class,method="insertOrdTableHis")
	public void insertOrdTableHis(Map ordMap);

	@DeleteProvider(type=OrdBillHisProvider.class,method="deleteOrdTable")
	public void deleteOrdTable(Map ordMap);
	
	
	
}