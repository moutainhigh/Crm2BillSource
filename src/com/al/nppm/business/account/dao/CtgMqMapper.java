package com.al.nppm.business.account.dao;

import java.util.Map;

public interface CtgMqMapper {
    public Map<String,Object> getCustomer(Map map);
    public Map<String,Object> getAccount(Map map);
    public Map<String,Object> getProdInst(Map map);
    public Map<String,Object> getProdInstAttr(Map map);
    public Map<String,Object> getProdInstSub(Map map);
    public Map<String,Object> getProdInstAcctRel(Map map);
    public Map<String,Object> getOfferInst(Map map);
    public Map<String,Object> getOfferObjInstRel(Map map);
    public Map<String,Object> getOfferInstAttr(Map map);
    public Map<String,Object> getProdInstRegion(Map map);
    public Map<String,Object> getProdInstAccNum(Map map);
    public Map<String,Object> getProdInstStateExt(Map map);
    public Map<String,Object> getProdInstPaymode(Map map);
    public Map<String,Object> getProdInstAttrSub(Map map);










}