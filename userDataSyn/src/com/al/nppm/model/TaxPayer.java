package com.al.nppm.model;

import java.util.Date;

public class TaxPayer {
    private Long taxPayerId;

    private String taxId;

    private String taxName;

    private String taxProvinceCode;

    private String taxRelaTel;

    private String taxRelaAddr;

    private String taxBankName;

    private String taxBankAcct;

    private Date startDate;

    private Date taxEndDate;

    private Integer generalTaxpayerFlag;

    private Integer vatInvoicesFlag;

    private String billDeliverWay;

    private String billDeliverAddr;

    private String postcode;

    private Date effDate;

    private Date expDate;

    private Date approvalTime;

    private String statusCd;

    private Date statusDate;

    private Long createStaff;

    private Date createDate;

    private Long updateStaff;

    private Date updateDate;

    private String remark;

    public Long getTaxPayerId() {
        return taxPayerId;
    }

    public void setTaxPayerId(Long taxPayerId) {
        this.taxPayerId = taxPayerId;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId == null ? null : taxId.trim();
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName == null ? null : taxName.trim();
    }

    public String getTaxProvinceCode() {
        return taxProvinceCode;
    }

    public void setTaxProvinceCode(String taxProvinceCode) {
        this.taxProvinceCode = taxProvinceCode == null ? null : taxProvinceCode.trim();
    }

    public String getTaxRelaTel() {
        return taxRelaTel;
    }

    public void setTaxRelaTel(String taxRelaTel) {
        this.taxRelaTel = taxRelaTel == null ? null : taxRelaTel.trim();
    }

    public String getTaxRelaAddr() {
        return taxRelaAddr;
    }

    public void setTaxRelaAddr(String taxRelaAddr) {
        this.taxRelaAddr = taxRelaAddr == null ? null : taxRelaAddr.trim();
    }

    public String getTaxBankName() {
        return taxBankName;
    }

    public void setTaxBankName(String taxBankName) {
        this.taxBankName = taxBankName == null ? null : taxBankName.trim();
    }

    public String getTaxBankAcct() {
        return taxBankAcct;
    }

    public void setTaxBankAcct(String taxBankAcct) {
        this.taxBankAcct = taxBankAcct == null ? null : taxBankAcct.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getTaxEndDate() {
        return taxEndDate;
    }

    public void setTaxEndDate(Date taxEndDate) {
        this.taxEndDate = taxEndDate;
    }

    public Integer getGeneralTaxpayerFlag() {
        return generalTaxpayerFlag;
    }

    public void setGeneralTaxpayerFlag(Integer generalTaxpayerFlag) {
        this.generalTaxpayerFlag = generalTaxpayerFlag;
    }

    public Integer getVatInvoicesFlag() {
        return vatInvoicesFlag;
    }

    public void setVatInvoicesFlag(Integer vatInvoicesFlag) {
        this.vatInvoicesFlag = vatInvoicesFlag;
    }

    public String getBillDeliverWay() {
        return billDeliverWay;
    }

    public void setBillDeliverWay(String billDeliverWay) {
        this.billDeliverWay = billDeliverWay == null ? null : billDeliverWay.trim();
    }

    public String getBillDeliverAddr() {
        return billDeliverAddr;
    }

    public void setBillDeliverAddr(String billDeliverAddr) {
        this.billDeliverAddr = billDeliverAddr == null ? null : billDeliverAddr.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
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

    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}