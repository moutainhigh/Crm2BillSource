package generator;

import java.io.Serializable;

/**
 * service_offer_contrast
 * @author 
 */
public class ServiceOfferContrast implements Serializable {
    /**
     * 服务编码
     */
    private Long servcieOfferId;

    private String serviceOferName;

    /**
     * 停机类型
     */
    private Long stypeType;

    /**
     * 停机状态
     */
    private Long statusCd;

    /**
     * 停机状态
     */
    private Long billStatusCd;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getServcieOfferId() {
        return servcieOfferId;
    }

    public void setServcieOfferId(Long servcieOfferId) {
        this.servcieOfferId = servcieOfferId;
    }

    public String getServiceOferName() {
        return serviceOferName;
    }

    public void setServiceOferName(String serviceOferName) {
        this.serviceOferName = serviceOferName;
    }

    public Long getStypeType() {
        return stypeType;
    }

    public void setStypeType(Long stypeType) {
        this.stypeType = stypeType;
    }

    public Long getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(Long statusCd) {
        this.statusCd = statusCd;
    }

    public Long getBillStatusCd() {
        return billStatusCd;
    }

    public void setBillStatusCd(Long billStatusCd) {
        this.billStatusCd = billStatusCd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        ServiceOfferContrast other = (ServiceOfferContrast) that;
        return (this.getServcieOfferId() == null ? other.getServcieOfferId() == null : this.getServcieOfferId().equals(other.getServcieOfferId()))
            && (this.getServiceOferName() == null ? other.getServiceOferName() == null : this.getServiceOferName().equals(other.getServiceOferName()))
            && (this.getStypeType() == null ? other.getStypeType() == null : this.getStypeType().equals(other.getStypeType()))
            && (this.getStatusCd() == null ? other.getStatusCd() == null : this.getStatusCd().equals(other.getStatusCd()))
            && (this.getBillStatusCd() == null ? other.getBillStatusCd() == null : this.getBillStatusCd().equals(other.getBillStatusCd()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getServcieOfferId() == null) ? 0 : getServcieOfferId().hashCode());
        result = prime * result + ((getServiceOferName() == null) ? 0 : getServiceOferName().hashCode());
        result = prime * result + ((getStypeType() == null) ? 0 : getStypeType().hashCode());
        result = prime * result + ((getStatusCd() == null) ? 0 : getStatusCd().hashCode());
        result = prime * result + ((getBillStatusCd() == null) ? 0 : getBillStatusCd().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", servcieOfferId=").append(servcieOfferId);
        sb.append(", serviceOferName=").append(serviceOferName);
        sb.append(", stypeType=").append(stypeType);
        sb.append(", statusCd=").append(statusCd);
        sb.append(", billStatusCd=").append(billStatusCd);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}