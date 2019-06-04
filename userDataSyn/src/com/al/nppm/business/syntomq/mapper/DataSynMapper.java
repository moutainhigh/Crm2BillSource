package com.al.nppm.business.syntomq.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DataSynMapper {
	void insertDataSynStatistics(Map<String, Object> param);
//	List<Map<String, Object>> queryOrdBill(@Param("bDate")Date bDate,@Param("eDate")Date eDate);
}
