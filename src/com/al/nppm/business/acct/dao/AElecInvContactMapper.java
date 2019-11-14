package com.al.nppm.business.acct.dao;


import com.al.nppm.business.acct.dao.vo.AElecInvContact;

public interface AElecInvContactMapper {

    int insertAElecInvContact(AElecInvContact record);

    AElecInvContact selectByPrimaryKey(Integer contactId);

    int updateByPrimaryKeySelective(AElecInvContact record);

}