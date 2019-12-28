package com.al.nppm.business.billdetail.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface BAccu2abmTifDao {

    int insertBAccu2abmTif(Map map);
    public long getSeq(@Param("seq_name") String seq_name);
}