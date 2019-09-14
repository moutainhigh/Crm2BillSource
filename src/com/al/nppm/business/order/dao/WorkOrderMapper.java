package com.al.nppm.business.order.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 停复机工单表
 */
@Repository
public interface WorkOrderMapper {
    public List<Map<String,Object>> selectWorkOrder(Map map);
    public int updateWorkOrder(Map map);
}
