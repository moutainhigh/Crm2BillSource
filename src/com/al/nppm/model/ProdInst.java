package com.al.nppm.model;

import java.util.Date;

public class ProdInst {
    private Long prodInstId;

    private Long prodId;

    private Long accProdInstId;

    private String prodUseType;

    private String accNum;

    private String account;

    private String paymentModeCd;

    private String addressDesc;

    private Long ownerCustId;

    private String prodInstPwd;

    private Long exchId;

    private Long addressId;

    private Long regionId;

    private Long lanId;

    private Date actDate;

    private Date beginRentDate;

    private Date stopRentDate;

    private String statusCd;

    private Long createOrgId;

    private Long createStaff;

    private Long updateStaff;

    private Date createDate;

    private Date statusDate;

    private Date updateDate;

    private Date firstFinishDate;

    private Date busiModDate;

    private Long useCustId;

    private Long lastOrderItemId;

    private String remark;

    private Long pointOwnerId;

    private Long extProdInstId;

    public Long getProdInstId() {
        return prodInstId;
    }

    public void setProdInstId(Long prodInstId) {
        this.prodInstId = prodInstId;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getAccProdInstId() {
        return accProdInstId;
    }

    public void setAccProdInstId(Long accProdInstId) {
        this.accProdInstId = accProdInstId;
    }

    public String getProdUseType() {
        return prodUseType;
    }

    public void setProdUseType(String prodUseType) {
        this.prodUseType = prodUseType == null ? null : prodUseType.trim();
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum == null ? null : accNum.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPaymentModeCd() {
        return paymentModeCd;
    }

    public void setPaymentModeCd(String paymentModeCd) {
        this.paymentModeCd = paymentModeCd == null ? null : paymentModeCd.trim();
    }

    public String getAddressDesc() {
        return addressDesc;
    }

    public void setAddressDesc(String addressDesc) {
        this.addressDesc = addressDesc == null ? null : addressDesc.trim();
    }

    public Long getOwnerCustId() {
        return ownerCustId;
    }

    public void setOwnerCustId(Long ownerCustId) {
        this.ownerCustId = ownerCustId;
    }

    public String getProdInstPwd() {
        return prodInstPwd;
    }

    public void setProdInstPwd(String prodInstPwd) {
        this.prodInstPwd = prodInstPwd == null ? null : prodInstPwd.trim();
    }

    public Long getExchId() {
        return exchId;
    }

    public void setExchId(Long exchId) {
        this.exchId = exchId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getLanId() {
        return lanId;
    }

    public void setLanId(Long lanId) {
        this.lanId = lanId;
    }

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }

    public Date getBeginRentDate() {
        return beginRentDate;
    }

    public void setBeginRentDate(Date beginRentDate) {
        this.beginRentDate = beginRentDate;
    }

    public Date getStopRentDate() {
        return stopRentDate;
    }

    public void setStopRentDate(Date stopRentDate) {
        this.stopRentDate = stopRentDate;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public Long getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(Long createOrgId) {
        this.createOrgId = createOrgId;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getFirstFinishDate() {
        return firstFinishDate;
    }

    public void setFirstFinishDate(Date firstFinishDate) {
        this.firstFinishDate = firstFinishDate;
    }

    public Date getBusiModDate() {
        return busiModDate;
    }

    public void setBusiModDate(Date busiModDate) {
        this.busiModDate = busiModDate;
    }

    public Long getUseCustId() {
        return useCustId;
    }

    public void setUseCustId(Long useCustId) {
        this.useCustId = useCustId;
    }

    public Long getLastOrderItemId() {
        return lastOrderItemId;
    }

    public void setLastOrderItemId(Long lastOrderItemId) {
        this.lastOrderItemId = lastOrderItemId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getPointOwnerId() {
        return pointOwnerId;
    }

    public void setPointOwnerId(Long pointOwnerId) {
        this.pointOwnerId = pointOwnerId;
    }

    public Long getExtProdInstId() {
        return extProdInstId;
    }

    public void setExtProdInstId(Long extProdInstId) {
        this.extProdInstId = extProdInstId;
    }
}