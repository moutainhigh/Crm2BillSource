package com.al.nppm.business.account.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CustomerMapper {

    Long getForCountByUseCustId(Map<String, Object> map);
    Long getForCountByOwnerCustId(Map<String, Object> map);

}