package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * offer_inst
 * @author 
 */
public class OfferInst implements Serializable {
    private Long offerInstId;

    private Long offerId;

    private String offerType;

    private Long ownerCustId;

    private Date effDate;

    private Date expDate;

    private Long offerAgreeId;

    private Long createOrgId;

    private String expProcMethod;

    private Long lanId;

    private Long regionId;

    private String statusCd;

    private Long createStaff;

    private Long updateStaff;

    private Date createDate;

    private Date statusDate;

    private Date updateDate;

    private Date busiModDate;

    private Long lastOrderItemId;

    private String remark;

    private String extOfferInstId;

    private Long routeId;

    private static final long serialVersionUID = 1L;

    public Long getOfferInstId() {
        return offerInstId;
    }

    public void setOfferInstId(Long offerInstId) {
        this.offerInstId = offerInstId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public Long getOwnerCustId() {
        return ownerCustId;
    }

    public void setOwnerCustId(Long ownerCustId) {
        this.ownerCustId = ownerCustId;
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

    public Long getOfferAgreeId() {
        return offerAgreeId;
    }

    public void setOfferAgreeId(Long offerAgreeId) {
        this.offerAgreeId = offerAgreeId;
    }

    public Long getCreateOrgId() {
        return createOrgId;
    }

    public void setCreateOrgId(Long createOrgId) {
        this.createOrgId = createOrgId;
    }

    public String getExpProcMethod() {
        return expProcMethod;
    }

    public void setExpProcMethod(String expProcMethod) {
        this.expProcMethod = expProcMethod;
    }

    public Long getLanId() {
        return lanId;
    }

    public void setLanId(Long lanId) {
        this.lanId = lanId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
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

    public Date getBusiModDate() {
        return busiModDate;
    }

    public void setBusiModDate(Date busiModDate) {
        this.busiModDate = busiModDate;
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
        this.remark = remark;
    }

    public String getExtOfferInstId() {
        return extOfferInstId;
    }

    public void setExtOfferInstId(String extOfferInstId) {
        this.extOfferInstId = extOfferInstId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OfferInst other = (OfferInst) that;
        return (this.getOfferInstId() == null ? other.getOfferInstId() == null : this.getOfferInstId().equals(other.getOfferInstId()))
            && (this.getOfferId() == null ? other.getOfferId() == null : this.getOfferId().equals(other.getOfferId()))
            && (this.getOfferType() == null ? other.getOfferType() == null : this.getOfferType().equals(other.getOfferType()))
            && (this.getOwnerCustId() == null ? other.getOwnerCustId() == null : this.getOwnerCustId().equals(other.getOwnerCustId()))
            && (this.getEffDate() == null ? other.getEffDate() == null : this.getEffDate().equals(other.getEffDate()))
            && (this.getExpDate() == null ? other.getExpDate() == null : this.getExpDate().equals(other.getExpDate()))
            && (this.getOfferAgreeId() == null ? other.getOfferAgreeId() == null : this.getOfferAgreeId().equals(other.getOfferAgreeId()))
            && (this.getCreateOrgId() == null ? other.getCreateOrgId() == null : this.getCreateOrgId().equals(other.getCreateOrgId()))
            && (this.getExpProcMethod() == null ? other.getExpProcMethod() == null : this.getExpProcMethod().equals(other.getExpProcMethod()))
            && (this.getLanId() == null ? other.getLanId() == null : this.getLanId().equals(other.getLanId()))
            && (this.getRegionId() == null ? other.getRegionId() == null : this.getRegionId().equals(other.getRegionId()))
            && (this.getStatusCd() == null ? other.getStatusCd() == null : this.getStatusCd().equals(other.getStatusCd()))
            && (this.getCreateStaff() == null ? other.getCreateStaff() == null : this.getCreateStaff().equals(other.getCreateStaff()))
            && (this.getUpdateStaff() == null ? other.getUpdateStaff() == null : this.getUpdateStaff().equals(other.getUpdateStaff()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getStatusDate() == null ? other.getStatusDate() == null : this.getStatusDate().equals(other.getStatusDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getBusiModDate() == null ? other.getBusiModDate() == null : this.getBusiModDate().equals(other.getBusiModDate()))
            && (this.getLastOrderItemId() == null ? other.getLastOrderItemId() == null : this.getLastOrderItemId().equals(other.getLastOrderItemId()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getExtOfferInstId() == null ? other.getExtOfferInstId() == null : this.getExtOfferInstId().equals(other.getExtOfferInstId()))
            && (this.getRouteId() == null ? other.getRouteId() == null : this.getRouteId().equals(other.getRouteId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOfferInstId() == null) ? 0 : getOfferInstId().hashCode());
        result = prime * result + ((getOfferId() == null) ? 0 : getOfferId().hashCode());
        result = prime * result + ((getOfferType() == null) ? 0 : getOfferType().hashCode());
        result = prime * result + ((getOwnerCustId() == null) ? 0 : getOwnerCustId().hashCode());
        result = prime * result + ((getEffDate() == null) ? 0 : getEffDate().hashCode());
        result = prime * result + ((getExpDate() == null) ? 0 : getExpDate().hashCode());
        result = prime * result + ((getOfferAgreeId() == null) ? 0 : getOfferAgreeId().hashCode());
        result = prime * result + ((getCreateOrgId() == null) ? 0 : getCreateOrgId().hashCode());
        result = prime * result + ((getExpProcMethod() == null) ? 0 : getExpProcMethod().hashCode());
        result = prime * result + ((getLanId() == null) ? 0 : getLanId().hashCode());
        result = prime * result + ((getRegionId() == null) ? 0 : getRegionId().hashCode());
        result = prime * result + ((getStatusCd() == null) ? 0 : getStatusCd().hashCode());
        result = prime * result + ((getCreateStaff() == null) ? 0 : getCreateStaff().hashCode());
        result = prime * result + ((getUpdateStaff() == null) ? 0 : getUpdateStaff().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getStatusDate() == null) ? 0 : getStatusDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getBusiModDate() == null) ? 0 : getBusiModDate().hashCode());
        result = prime * result + ((getLastOrderItemId() == null) ? 0 : getLastOrderItemId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getExtOfferInstId() == null) ? 0 : getExtOfferInstId().hashCode());
        result = prime * result + ((getRouteId() == null) ? 0 : getRouteId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", offerInstId=").append(offerInstId);
        sb.append(", offerId=").append(offerId);
        sb.append(", offerType=").append(offerType);
        sb.append(", ownerCustId=").append(ownerCustId);
        sb.append(", effDate=").append(effDate);
        sb.append(", expDate=").append(expDate);
        sb.append(", offerAgreeId=").append(offerAgreeId);
        sb.append(", createOrgId=").append(createOrgId);
        sb.append(", expProcMethod=").append(expProcMethod);
        sb.append(", lanId=").append(lanId);
        sb.append(", regionId=").append(regionId);
        sb.append(", statusCd=").append(statusCd);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", createDate=").append(createDate);
        sb.append(", statusDate=").append(statusDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", busiModDate=").append(busiModDate);
        sb.append(", lastOrderItemId=").append(lastOrderItemId);
        sb.append(", remark=").append(remark);
        sb.append(", extOfferInstId=").append(extOfferInstId);
        sb.append(", routeId=").append(routeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}