package com.al.nppm.business.account.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PlcaProdInstMapper {
    public Map<String,Object> getProdInst(Map map);
    public int updateProdInst(Map map);

    public List<Map<String, Object>> getProdInstStateExt(Map map);

    public int insertProdInstStateExt(Map map);

    public int updateProdInstStateExt(Map map);

    public List<Map<String, Object>> getProdInstSub(Map map);

    public int updateProdInstSub(Map map);

    public List<Map<String, Object>> getOfferObjInstRel(Map map);

    public List<Map<String, Object>> getOfferObjfromObjId(Map map);

    public int updateOfferObjInstRel(Map map);

    public int updateOfferInst(Map map);
    public List<Map<String, Object>> getOfferInstId(Map map);

    public List<Map<String, Object>> getOfferInstAttr(Map map);
    public int updateOfferInstAttr(Map map);

    public List<Map<String, Object>> getProdInstAttr(Map map);
    public int insertProdInstAttr(Map map);
    public int updateProdInstAttr(Map map);






}
