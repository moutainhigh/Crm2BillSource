package com.al.nppm.business.account.dao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/18 13:57
 * @File : IPayToPlan
 * @Software: IntelliJ IDEA 2019.3.15
 */
@Repository
public interface IPayToPlanMapper {

    public int insertInterPayToPlan(Map map);
    public int insertTifFeeBill(Map map);
    public Long getAcctId(Map map);

}
