package com.al.nppm.business.acctrh.dao;

import com.al.nppm.model.SmsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 停复机工单表
 */
@Repository
public interface SmsInfoMapper {
    int insert(SmsInfo record);
    public long getSeq(@Param("seq_name") String seq_name);
    public List<Map<String, Object>> getCrmOfferInstInfo(@Param("offerInstId") long offerInstId);

    public List<Map<String, Object>> getCrmProdInstInfo(@Param("prodInstId") long prodInstId);
}
