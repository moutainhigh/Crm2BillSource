package com.al.nppm.model;

import java.util.Date;

public class OrdBill {
    private Long archGrpId;

    private Long custOrderId;

    private Long regionId;

    private Long createOrgId;

    private Long createStaff;

    private Date createDate;

    private Date finishDate;

    private String procFlag;

    private Date procDate;

    private Long procCnt;

    private String notes;

    public Long getArchGrpId() {
        return archGrpId;
    }

    public void setArchGrpId(Long archGrpId) {
        this.archGrpId = archGrpId;
    }

    public Long getCustOrderId() {
        return custOrderId;
    }

    public void setCustOrderId(Long custOrderId) {
        this.custOrderId = custOrderId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getProcFlag() {
        return procFlag;
    }

    public void setProcFlag(String procFlag) {
        this.procFlag = procFlag == null ? null : procFlag.trim();
    }

    public Date getProcDate() {
        return procDate;
    }

    public void setProcDate(Date procDate) {
        this.procDate = procDate;
    }

    public Long getProcCnt() {
        return procCnt;
    }

    public void setProcCnt(Long procCnt) {
        this.procCnt = procCnt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }
}