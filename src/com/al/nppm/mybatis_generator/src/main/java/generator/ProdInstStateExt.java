package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * prod_inst_state_ext
 * @author 
 */
public class ProdInstStateExt implements Serializable {
    private Long prodInstStateId;

    private Long prodInstId;

    private String stopType;

    private String state;

    private Date effDate;

    private Date expDate;

    private String stopDirection;

    private String stopReason;

    private Date eventDate;

    private String statusCd;

    private Long createStaff;

    private Long updateStaff;

    private Date createDate;

    private Date statusDate;

    private Date updateDate;

    private Long lastOrderItemId;

    private String remark;

    private Long routeId;

    private static final long serialVersionUID = 1L;

    public Long getProdInstStateId() {
        return prodInstStateId;
    }

    public void setProdInstStateId(Long prodInstStateId) {
        this.prodInstStateId = prodInstStateId;
    }

    public Long getProdInstId() {
        return prodInstId;
    }

    public void setProdInstId(Long prodInstId) {
        this.prodInstId = prodInstId;
    }

    public String getStopType() {
        return stopType;
    }

    public void setStopType(String stopType) {
        this.stopType = stopType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getStopDirection() {
        return stopDirection;
    }

    public void setStopDirection(String stopDirection) {
        this.stopDirection = stopDirection;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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
        ProdInstStateExt other = (ProdInstStateExt) that;
        return (this.getProdInstStateId() == null ? other.getProdInstStateId() == null : this.getProdInstStateId().equals(other.getProdInstStateId()))
            && (this.getProdInstId() == null ? other.getProdInstId() == null : this.getProdInstId().equals(other.getProdInstId()))
            && (this.getStopType() == null ? other.getStopType() == null : this.getStopType().equals(other.getStopType()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getEffDate() == null ? other.getEffDate() == null : this.getEffDate().equals(other.getEffDate()))
            && (this.getExpDate() == null ? other.getExpDate() == null : this.getExpDate().equals(other.getExpDate()))
            && (this.getStopDirection() == null ? other.getStopDirection() == null : this.getStopDirection().equals(other.getStopDirection()))
            && (this.getStopReason() == null ? other.getStopReason() == null : this.getStopReason().equals(other.getStopReason()))
            && (this.getEventDate() == null ? other.getEventDate() == null : this.getEventDate().equals(other.getEventDate()))
            && (this.getStatusCd() == null ? other.getStatusCd() == null : this.getStatusCd().equals(other.getStatusCd()))
            && (this.getCreateStaff() == null ? other.getCreateStaff() == null : this.getCreateStaff().equals(other.getCreateStaff()))
            && (this.getUpdateStaff() == null ? other.getUpdateStaff() == null : this.getUpdateStaff().equals(other.getUpdateStaff()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getStatusDate() == null ? other.getStatusDate() == null : this.getStatusDate().equals(other.getStatusDate()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getLastOrderItemId() == null ? other.getLastOrderItemId() == null : this.getLastOrderItemId().equals(other.getLastOrderItemId()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getRouteId() == null ? other.getRouteId() == null : this.getRouteId().equals(other.getRouteId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProdInstStateId() == null) ? 0 : getProdInstStateId().hashCode());
        result = prime * result + ((getProdInstId() == null) ? 0 : getProdInstId().hashCode());
        result = prime * result + ((getStopType() == null) ? 0 : getStopType().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getEffDate() == null) ? 0 : getEffDate().hashCode());
        result = prime * result + ((getExpDate() == null) ? 0 : getExpDate().hashCode());
        result = prime * result + ((getStopDirection() == null) ? 0 : getStopDirection().hashCode());
        result = prime * result + ((getStopReason() == null) ? 0 : getStopReason().hashCode());
        result = prime * result + ((getEventDate() == null) ? 0 : getEventDate().hashCode());
        result = prime * result + ((getStatusCd() == null) ? 0 : getStatusCd().hashCode());
        result = prime * result + ((getCreateStaff() == null) ? 0 : getCreateStaff().hashCode());
        result = prime * result + ((getUpdateStaff() == null) ? 0 : getUpdateStaff().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getStatusDate() == null) ? 0 : getStatusDate().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getLastOrderItemId() == null) ? 0 : getLastOrderItemId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getRouteId() == null) ? 0 : getRouteId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", prodInstStateId=").append(prodInstStateId);
        sb.append(", prodInstId=").append(prodInstId);
        sb.append(", stopType=").append(stopType);
        sb.append(", state=").append(state);
        sb.append(", effDate=").append(effDate);
        sb.append(", expDate=").append(expDate);
        sb.append(", stopDirection=").append(stopDirection);
        sb.append(", stopReason=").append(stopReason);
        sb.append(", eventDate=").append(eventDate);
        sb.append(", statusCd=").append(statusCd);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", createDate=").append(createDate);
        sb.append(", statusDate=").append(statusDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", lastOrderItemId=").append(lastOrderItemId);
        sb.append(", remark=").append(remark);
        sb.append(", routeId=").append(routeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}