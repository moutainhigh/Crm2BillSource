package com.al.nppm.model;

import java.util.Date;

public class PartyCert {
    private Long partyCertId;

    private Long partyId;

    private String isRealnameCert;

    private String certType;

    private String certOrg;

    private String certAddr;

    private String certNum;

    private Long checker;

    private Date checkTime;

    private String certCheckResult;

    private Date effDate;

    private Date expDate;

    private Date createDate;

    private String statusCd;

    private Long createStaff;

    private Long updateStaff;

    private Date statusDate;

    private Date updateDate;

    private String remark;

    public Long getPartyCertId() {
        return partyCertId;
    }

    public void setPartyCertId(Long partyCertId) {
        this.partyCertId = partyCertId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getIsRealnameCert() {
        return isRealnameCert;
    }

    public void setIsRealnameCert(String isRealnameCert) {
        this.isRealnameCert = isRealnameCert == null ? null : isRealnameCert.trim();
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType == null ? null : certType.trim();
    }

    public String getCertOrg() {
        return certOrg;
    }

    public void setCertOrg(String certOrg) {
        this.certOrg = certOrg == null ? null : certOrg.trim();
    }

    public String getCertAddr() {
        return certAddr;
    }

    public void setCertAddr(String certAddr) {
        this.certAddr = certAddr == null ? null : certAddr.trim();
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum == null ? null : certNum.trim();
    }

    public Long getChecker() {
        return checker;
    }

    public void setChecker(Long checker) {
        this.checker = checker;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCertCheckResult() {
        return certCheckResult;
    }

    public void setCertCheckResult(String certCheckResult) {
        this.certCheckResult = certCheckResult == null ? null : certCheckResult.trim();
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}