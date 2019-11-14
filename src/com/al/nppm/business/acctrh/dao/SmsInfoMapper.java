package com.al.nppm.business.acctrh.dao;

import com.al.nppm.model.SmsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 停复机工单表
 */
@Repository
public interface SmsInfoMapper {
    int insert(SmsInfo record);
    public long getSeq(@Param("seq_name") String seq_name);
}
