package com.al.nppm.model;

import java.util.Date;

public class Account {
    private Long acctId;

    private String acctName;

    private String acctCd;

    private Long custId;

    private String acctLoginName;

    private String loginPassword;

    private Integer acctBillingType;

    private Long prodInstId;

    private Date effDate;

    private Date expDate;

    private Long statusCd;

    private Date statusDate;

    private Long createStaff;

    private Date createDate;

    private Long updateStaff;

    private Date updateDate;

    private String remark;

    private String extAcctId;

    private String ext1AcctId;

    private String ext2AcctId;

    private String ext3AcctId;

    private String groupAcctId;

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName == null ? null : acctName.trim();
    }

    public String getAcctCd() {
        return acctCd;
    }

    public void setAcctCd(String acctCd) {
        this.acctCd = acctCd == null ? null : acctCd.trim();
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getAcctLoginName() {
        return acctLoginName;
    }

    public void setAcctLoginName(String acctLoginName) {
        this.acctLoginName = acctLoginName == null ? null : acctLoginName.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public Integer getAcctBillingType() {
        return acctBillingType;
    }

    public void setAcctBillingType(Integer acctBillingType) {
        this.acctBillingType = acctBillingType;
    }

    public Long getProdInstId() {
        return prodInstId;
    }

    public void setProdInstId(Long prodInstId) {
        this.prodInstId = prodInstId;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Long getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(Long statusCd) {
        this.statusCd = statusCd;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getExtAcctId() {
        return extAcctId;
    }

    public void setExtAcctId(String extAcctId) {
        this.extAcctId = extAcctId == null ? null : extAcctId.trim();
    }

    public String getExt1AcctId() {
        return ext1AcctId;
    }

    public void setExt1AcctId(String ext1AcctId) {
        this.ext1AcctId = ext1AcctId == null ? null : ext1AcctId.trim();
    }

    public String getExt2AcctId() {
        return ext2AcctId;
    }

    public void setExt2AcctId(String ext2AcctId) {
        this.ext2AcctId = ext2AcctId == null ? null : ext2AcctId.trim();
    }

    public String getExt3AcctId() {
        return ext3AcctId;
    }

    public void setExt3AcctId(String ext3AcctId) {
        this.ext3AcctId = ext3AcctId == null ? null : ext3AcctId.trim();
    }

    public String getGroupAcctId() {
        return groupAcctId;
    }

    public void setGroupAcctId(String groupAcctId) {
        this.groupAcctId = groupAcctId == null ? null : groupAcctId.trim();
    }
}