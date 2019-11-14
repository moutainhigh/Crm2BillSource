package com.al.nppm.business.cpcp.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * cpc表操作写在这个mapper接口中
 */
@Repository
public interface CpcMapper {
    /**
     * 赠款目录
     * @param map
     * @return
     */
    public List<Map<String,Object>> getCountFromOfferCatalogLocation(Map map);

    /**
     * 红包金目录
     * @param map
     * @return
     */
    public Long getCountFromOfferCatalogLocation_hongbao(Map map);
    public Long getCountFromPOfferPayplanInfo(Map map);
    public Long getShareLevel(Map map);
    public List<Map<String,Object>> selectPOfferPayplanInfo(Map map);

}
