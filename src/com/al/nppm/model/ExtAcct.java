package com.al.nppm.model;

import java.util.Date;

public class ExtAcct {
    private Long extAcctId;

    private Long custId;

    private Integer payChannel;

    private String payAcctCode;

    private String payAcctName;

    private Long acctOwnerOrg;

    private Long acctOwnerOrgBranch;

    private Date effDate;

    private Date expDate;

    private Integer ifContractQuickPay;

    private String statusCd;

    private Date statusDate;

    private Long createStaff;

    private Date createDate;

    private Long updateStaff;

    private Date updateDate;

    private String commisionAgreementId;

    public Long getExtAcctId() {
        return extAcctId;
    }

    public void setExtAcctId(Long extAcctId) {
        this.extAcctId = extAcctId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Integer getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayAcctCode() {
        return payAcctCode;
    }

    public void setPayAcctCode(String payAcctCode) {
        this.payAcctCode = payAcctCode == null ? null : payAcctCode.trim();
    }

    public String getPayAcctName() {
        return payAcctName;
    }

    public void setPayAcctName(String payAcctName) {
        this.payAcctName = payAcctName == null ? null : payAcctName.trim();
    }

    public Long getAcctOwnerOrg() {
        return acctOwnerOrg;
    }

    public void setAcctOwnerOrg(Long acctOwnerOrg) {
        this.acctOwnerOrg = acctOwnerOrg;
    }

    public Long getAcctOwnerOrgBranch() {
        return acctOwnerOrgBranch;
    }

    public void setAcctOwnerOrgBranch(Long acctOwnerOrgBranch) {
        this.acctOwnerOrgBranch = acctOwnerOrgBranch;
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

    public Integer getIfContractQuickPay() {
        return ifContractQuickPay;
    }

    public void setIfContractQuickPay(Integer ifContractQuickPay) {
        this.ifContractQuickPay = ifContractQuickPay;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
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

    public String getCommisionAgreementId() {
        return commisionAgreementId;
    }

    public void setCommisionAgreementId(String commisionAgreementId) {
        this.commisionAgreementId = commisionAgreementId == null ? null : commisionAgreementId.trim();
    }
}