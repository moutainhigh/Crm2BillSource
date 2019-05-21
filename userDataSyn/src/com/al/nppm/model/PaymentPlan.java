package com.al.nppm.model;

import java.util.Date;

public class PaymentPlan {
    private Long paymentPlanId;

    private Long acctId;

    private Integer payAcctType;

    private Long payAcctId;

    private Long paymentMethod;

    private Integer priority;

    private Integer paymentLimitType;

    private Long paymentLimit;

    private Long upperAmount;

    private Integer ifOnlinePay;

    private String statusCd;

    private Date statusDate;

    private Date effDate;

    private Date expDate;

    private Long createStaff;

    private Date createDate;

    private Long updateStaff;

    private Date updateDate;

    public Long getPaymentPlanId() {
        return paymentPlanId;
    }

    public void setPaymentPlanId(Long paymentPlanId) {
        this.paymentPlanId = paymentPlanId;
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public Integer getPayAcctType() {
        return payAcctType;
    }

    public void setPayAcctType(Integer payAcctType) {
        this.payAcctType = payAcctType;
    }

    public Long getPayAcctId() {
        return payAcctId;
    }

    public void setPayAcctId(Long payAcctId) {
        this.payAcctId = payAcctId;
    }

    public Long getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Long paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPaymentLimitType() {
        return paymentLimitType;
    }

    public void setPaymentLimitType(Integer paymentLimitType) {
        this.paymentLimitType = paymentLimitType;
    }

    public Long getPaymentLimit() {
        return paymentLimit;
    }

    public void setPaymentLimit(Long paymentLimit) {
        this.paymentLimit = paymentLimit;
    }

    public Long getUpperAmount() {
        return upperAmount;
    }

    public void setUpperAmount(Long upperAmount) {
        this.upperAmount = upperAmount;
    }

    public Integer getIfOnlinePay() {
        return ifOnlinePay;
    }

    public void setIfOnlinePay(Integer ifOnlinePay) {
        this.ifOnlinePay = ifOnlinePay;
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
}