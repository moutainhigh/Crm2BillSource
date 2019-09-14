package com.al.nppm.business.inter.service;

import com.al.nppm.model.Message;

import java.util.Map;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/4/18 10:19
 * @File : IPayToPlanService
 * @Software: IntelliJ IDEA 2019.3.15
 */
public interface IPayToPlanService {
    /**
    * @Author maozp3 
    * @Description: 用户级销售品预存款
    * @Date: 19:27 2019/4/18
    * @Param [itemMap, userMap, msg]
    * @return int
    **/
    int acctProDeposit(Map itemMap, Message msg) throws Exception;
    /**
    * @Author maozp3 
    * @Description: 一次性费用及一次性费用预存款
    * @Date: 19:27 2019/4/18
    * @Param [itemMap, userMap, msg]
    * @return int
    **/
    int oneTimesCharge(Map itemMap, Message msg) throws Exception;
}
