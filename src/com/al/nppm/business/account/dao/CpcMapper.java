package com.al.nppm.business.account.dao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * cpc表操作写在这个mapper接口中
 */
@Repository
public interface CpcMapper {
    public Long getCountFromOfferCatalogLocation(Map map);
    public Long getCountFromPOfferPayplanInfo(Map map);
    public Long getShareLevel(Map map);
}
