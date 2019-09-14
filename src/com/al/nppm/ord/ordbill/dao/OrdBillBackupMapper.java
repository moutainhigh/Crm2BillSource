package com.al.nppm.ord.ordbill.dao;

import com.al.nppm.ord.ordbill.dao.OrdBillHisProvider;
import org.apache.ibatis.annotations.InsertProvider;

import java.util.Map;

public interface OrdBillBackupMapper {
    @InsertProvider(type= OrdBillHisProvider.class,method="insertOrdTableHis")
	public void insertOrdTableHis(Map ordMap);
}
