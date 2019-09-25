package com.al.nppm.business.inter.service;

import com.al.nppm.model.Message;

import java.util.Map;

public interface IErrorDealService {

    int doErrorDeal(Map itemMap, Message msg) throws Exception;
}
