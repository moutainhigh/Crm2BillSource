package com.al.nppm.business.acct.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface AcctMapper {

    public int insertInterPayToPlan(Map map);
    public int 	insertaAcctOweState(Map map);
    int insertTifChangeAcct(Map map);
    public long getAcctSeq(@Param("seq_name") String seq_name);

}
