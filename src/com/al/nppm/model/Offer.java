package com.al.nppm.model;

import java.io.Serializable;
import java.util.Date;

public class Offer   implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2550863522365960624L;


	private Long offerId;
    

    private String offerType;

    private String offerSysType;

    private String offerName;

    private String offerSysName;

    private String offerSysPyName;

    private String offerNbr;

//    private String offerSysNbr;
    private String extOfferId;

    private String offerDesc;

    private Date effDate;

    private String manageGrade;

    private Date expDate;

    private String offerProviderId;

    private String brandId;

    private String valueAddedFlag;

    private Integer initialCredValue;

    private Integer pricingPlanId;

    private String isIndependent;

    private String remark;

    private Long manageRegionId;

    private String statusCd;

    private Long createStaff;

    private Long updateStaff;

    private Date createDate;

    private Date statusDate;

    private Date updateDate;
    
    //业务需要增加
    
    private Long copyOfferId;

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
        this.offerType = offerType == null ? null : offerType.trim();
    }

    public String getOfferSysType() {
        return offerSysType;
    }

    public void setOfferSysType(String offerSysType) {
        this.offerSysType = offerSysType == null ? null : offerSysType.trim();
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName == null ? null : offerName.trim();
    }

    public String getOfferSysName() {
        return offerSysName;
    }

    public void setOfferSysName(String offerSysName) {
        this.offerSysName = offerSysName == null ? null : offerSysName.trim();
    }

    public String getOfferSysPyName() {
        return offerSysPyName;
    }

    public void setOfferSysPyName(String offerSysPyName) {
        this.offerSysPyName = offerSysPyName == null ? null : offerSysPyName.trim();
    }

    public String getOfferNbr() {
        return offerNbr;
    }

    public void setOfferNbr(String offerNbr) {
        this.offerNbr = offerNbr == null ? null : offerNbr.trim();
    }
    
    

//    public String getOfferSysNbr() {
//        return offerSysNbr;
//    }
//
//    public void setOfferSysNbr(String offerSysNbr) {
//        this.offerSysNbr = offerSysNbr == null ? null : offerSysNbr.trim();
//    }

    /**
	 * @return the extOfferId
	 */
	public String getExtOfferId() {
		return extOfferId;
	}

	/**
	 * @param extOfferId the extOfferId to set
	 */
	public void setExtOfferId(String extOfferId) {
		this.extOfferId = extOfferId;
	}

	public String getOfferDesc() {
        return offerDesc;
    }

    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc == null ? null : offerDesc.trim();
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public String getManageGrade() {
        return manageGrade;
    }

    public void setManageGrade(String manageGrade) {
        this.manageGrade = manageGrade == null ? null : manageGrade.trim();
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getOfferProviderId() {
        return offerProviderId;
    }

    public void setOfferProviderId(String offerProviderId) {
        this.offerProviderId = offerProviderId == null ? null : offerProviderId.trim();
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getValueAddedFlag() {
        return valueAddedFlag;
    }

    public void setValueAddedFlag(String valueAddedFlag) {
        this.valueAddedFlag = valueAddedFlag == null ? null : valueAddedFlag.trim();
    }

    public Integer getInitialCredValue() {
        return initialCredValue;
    }

    public void setInitialCredValue(Integer initialCredValue) {
        this.initialCredValue = initialCredValue;
    }

    public Integer getPricingPlanId() {
        return pricingPlanId;
    }

    public void setPricingPlanId(Integer pricingPlanId) {
        this.pricingPlanId = pricingPlanId;
    }

    public String getIsIndependent() {
        return isIndependent;
    }

    public void setIsIndependent(String isIndependent) {
        this.isIndependent = isIndependent == null ? null : isIndependent.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getManageRegionId() {
        return manageRegionId;
    }

    public void setManageRegionId(Long manageRegionId) {
        this.manageRegionId = manageRegionId;
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

	

	
	
	/**
	 * @return the copyOfferId
	 */
	public Long getCopyOfferId() {
		return copyOfferId;
	}

	/**
	 * @param copyOfferId the copyOfferId to set
	 */
	public void setCopyOfferId(Long copyOfferId) {
		this.copyOfferId = copyOfferId;
	}
    
    
}