package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * offer
 * @author 
 */
public class Offer implements Serializable {
    /**
     * 记录套餐标志。 主要有：1-组合套餐，2-单一套餐，3-基础销售品，0-品牌类销售品
     */
    private Long offerId;

    /**
     * LOVB=OFF-0005
     */
    private String offerType;

    /**
     * 记录内部的系统分类，如可选包分单接入、多接入等对销售品类型更进一步的划分。LOVB=OFF-C-006
     */
    private String offerSysType;

    /**
     * 记录市场部定义的销售品名称，通常用于宣传口径。
     */
    private String offerName;

    /**
     * 记录IT内部的销售品名称，通常包括：上市年月+销售品名称+资费等内容。
     */
    private String offerSysName;

    /**
     * 记录IT内部的销售品名称的拼音缩写。
     */
    private String offerSysPyName;

    /**
     * 记录销售品业务编码。
     */
    private String offerNbr;

    /**
     * 记录销售品外部标识。与原集团EXT_PROD_OFFER_ID与其对应。
     */
    private String extOfferId;

    /**
     * 记录销售品名称
     */
    private String offerDesc;

    /**
     * 记录生效时间
     */
    private Date effDate;

    /**
     * 记录销售品的管理级别，LOVB=OFF-0004
     */
    private String manageGrade;

    /**
     * 记录失效日期
     */
    private Date expDate;

    /**
     * 记录销售品管理部门信息
     */
    private String offerProviderId;

    /**
     * 品牌标识，外键，指向品牌实体【BRAND】
     */
    private Long brandId;

    /**
     * 增值销售品类型，LOVB=OFF-C-0057
     */
    private String valueAddedFlag;

    /**
     * 销售品初始信用额度
     */
    private Long initialCredValue;

    /**
     * 定价计划标识
     */
    private Long pricingPlanId;

    /**
     * 可独立订购标记，LOVB=OFF-C-0029
     */
    private String isIndependent;

    /**
     * 备注
     */
    private String remark;

    /**
     * 记录销售品的管理区域，如省级区域、集团级区域，来源COMMON_REGION_ID
     */
    private Long manageRegionId;

    /**
     * 记录销售品状态,LOVB=OFF-0003
     */
    private String statusCd;

    /**
     * 记录创建的员工
     */
    private Long createStaff;

    /**
     * 记录修改的员工
     */
    private Long updateStaff;

    /**
     * 记录创建的时间
     */
    private Date createDate;

    /**
     * 状态变更的时间
     */
    private Date statusDate;

    /**
     * 记录修改的时间
     */
    private Date updateDate;

    private static final long serialVersionUID = 1L;

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

    public String getOfferSysType() {
        return offerSysType;
    }

    public void setOfferSysType(String offerSysType) {
        this.offerSysType = offerSysType;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferSysName() {
        return offerSysName;
    }

    public void setOfferSysName(String offerSysName) {
        this.offerSysName = offerSysName;
    }

    public String getOfferSysPyName() {
        return offerSysPyName;
    }

    public void setOfferSysPyName(String offerSysPyName) {
        this.offerSysPyName = offerSysPyName;
    }

    public String getOfferNbr() {
        return offerNbr;
    }

    public void setOfferNbr(String offerNbr) {
        this.offerNbr = offerNbr;
    }

    public String getExtOfferId() {
        return extOfferId;
    }

    public void setExtOfferId(String extOfferId) {
        this.extOfferId = extOfferId;
    }

    public String getOfferDesc() {
        return offerDesc;
    }

    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc;
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
        this.manageGrade = manageGrade;
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
        this.offerProviderId = offerProviderId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getValueAddedFlag() {
        return valueAddedFlag;
    }

    public void setValueAddedFlag(String valueAddedFlag) {
        this.valueAddedFlag = valueAddedFlag;
    }

    public Long getInitialCredValue() {
        return initialCredValue;
    }

    public void setInitialCredValue(Long initialCredValue) {
        this.initialCredValue = initialCredValue;
    }

    public Long getPricingPlanId() {
        return pricingPlanId;
    }

    public void setPricingPlanId(Long pricingPlanId) {
        this.pricingPlanId = pricingPlanId;
    }

    public String getIsIndependent() {
        return isIndependent;
    }

    public void setIsIndependent(String isIndependent) {
        this.isIndependent = isIndependent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        Offer other = (Offer) that;
        return (this.getOfferId() == null ? other.getOfferId() == null : this.getOfferId().equals(other.getOfferId()))
            && (this.getOfferType() == null ? other.getOfferType() == null : this.getOfferType().equals(other.getOfferType()))
            && (this.getOfferSysType() == null ? other.getOfferSysType() == null : this.getOfferSysType().equals(other.getOfferSysType()))
            && (this.getOfferName() == null ? other.getOfferName() == null : this.getOfferName().equals(other.getOfferName()))
            && (this.getOfferSysName() == null ? other.getOfferSysName() == null : this.getOfferSysName().equals(other.getOfferSysName()))
            && (this.getOfferSysPyName() == null ? other.getOfferSysPyName() == null : this.getOfferSysPyName().equals(other.getOfferSysPyName()))
            && (this.getOfferNbr() == null ? other.getOfferNbr() == null : this.getOfferNbr().equals(other.getOfferNbr()))
            && (this.getExtOfferId() == null ? other.getExtOfferId() == null : this.getExtOfferId().equals(other.getExtOfferId()))
            && (this.getOfferDesc() == null ? other.getOfferDesc() == null : this.getOfferDesc().equals(other.getOfferDesc()))
            && (this.getEffDate() == null ? other.getEffDate() == null : this.getEffDate().equals(other.getEffDate()))
            && (this.getManageGrade() == null ? other.getManageGrade() == null : this.getManageGrade().equals(other.getManageGrade()))
            && (this.getExpDate() == null ? other.getExpDate() == null : this.getExpDate().equals(other.getExpDate()))
            && (this.getOfferProviderId() == null ? other.getOfferProviderId() == null : this.getOfferProviderId().equals(other.getOfferProviderId()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getValueAddedFlag() == null ? other.getValueAddedFlag() == null : this.getValueAddedFlag().equals(other.getValueAddedFlag()))
            && (this.getInitialCredValue() == null ? other.getInitialCredValue() == null : this.getInitialCredValue().equals(other.getInitialCredValue()))
            && (this.getPricingPlanId() == null ? other.getPricingPlanId() == null : this.getPricingPlanId().equals(other.getPricingPlanId()))
            && (this.getIsIndependent() == null ? other.getIsIndependent() == null : this.getIsIndependent().equals(other.getIsIndependent()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getManageRegionId() == null ? other.getManageRegionId() == null : this.getManageRegionId().equals(other.getManageRegionId()))
            && (this.getStatusCd() == null ? other.getStatusCd() == null : this.getStatusCd().equals(other.getStatusCd()))
            && (this.getCreateStaff() == null ? other.getCreateStaff() == null : this.getCreateStaff().equals(other.getCreateStaff()))
            && (this.getUpdateStaff() == null ? other.getUpdateStaff() == null : this.getUpdateStaff().equals(other.getUpdateStaff()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getStatusDate() == null ? other.getStatusDate() == null : this.getStatusDate().equals(other.getStatusDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOfferId() == null) ? 0 : getOfferId().hashCode());
        result = prime * result + ((getOfferType() == null) ? 0 : getOfferType().hashCode());
        result = prime * result + ((getOfferSysType() == null) ? 0 : getOfferSysType().hashCode());
        result = prime * result + ((getOfferName() == null) ? 0 : getOfferName().hashCode());
        result = prime * result + ((getOfferSysName() == null) ? 0 : getOfferSysName().hashCode());
        result = prime * result + ((getOfferSysPyName() == null) ? 0 : getOfferSysPyName().hashCode());
        result = prime * result + ((getOfferNbr() == null) ? 0 : getOfferNbr().hashCode());
        result = prime * result + ((getExtOfferId() == null) ? 0 : getExtOfferId().hashCode());
        result = prime * result + ((getOfferDesc() == null) ? 0 : getOfferDesc().hashCode());
        result = prime * result + ((getEffDate() == null) ? 0 : getEffDate().hashCode());
        result = prime * result + ((getManageGrade() == null) ? 0 : getManageGrade().hashCode());
        result = prime * result + ((getExpDate() == null) ? 0 : getExpDate().hashCode());
        result = prime * result + ((getOfferProviderId() == null) ? 0 : getOfferProviderId().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getValueAddedFlag() == null) ? 0 : getValueAddedFlag().hashCode());
        result = prime * result + ((getInitialCredValue() == null) ? 0 : getInitialCredValue().hashCode());
        result = prime * result + ((getPricingPlanId() == null) ? 0 : getPricingPlanId().hashCode());
        result = prime * result + ((getIsIndependent() == null) ? 0 : getIsIndependent().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getManageRegionId() == null) ? 0 : getManageRegionId().hashCode());
        result = prime * result + ((getStatusCd() == null) ? 0 : getStatusCd().hashCode());
        result = prime * result + ((getCreateStaff() == null) ? 0 : getCreateStaff().hashCode());
        result = prime * result + ((getUpdateStaff() == null) ? 0 : getUpdateStaff().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getStatusDate() == null) ? 0 : getStatusDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", offerId=").append(offerId);
        sb.append(", offerType=").append(offerType);
        sb.append(", offerSysType=").append(offerSysType);
        sb.append(", offerName=").append(offerName);
        sb.append(", offerSysName=").append(offerSysName);
        sb.append(", offerSysPyName=").append(offerSysPyName);
        sb.append(", offerNbr=").append(offerNbr);
        sb.append(", extOfferId=").append(extOfferId);
        sb.append(", offerDesc=").append(offerDesc);
        sb.append(", effDate=").append(effDate);
        sb.append(", manageGrade=").append(manageGrade);
        sb.append(", expDate=").append(expDate);
        sb.append(", offerProviderId=").append(offerProviderId);
        sb.append(", brandId=").append(brandId);
        sb.append(", valueAddedFlag=").append(valueAddedFlag);
        sb.append(", initialCredValue=").append(initialCredValue);
        sb.append(", pricingPlanId=").append(pricingPlanId);
        sb.append(", isIndependent=").append(isIndependent);
        sb.append(", remark=").append(remark);
        sb.append(", manageRegionId=").append(manageRegionId);
        sb.append(", statusCd=").append(statusCd);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", createDate=").append(createDate);
        sb.append(", statusDate=").append(statusDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}