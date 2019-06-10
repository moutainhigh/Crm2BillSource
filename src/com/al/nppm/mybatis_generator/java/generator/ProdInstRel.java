package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * prod_inst_rel
 * @author 
 */
public class ProdInstRel implements Serializable {
    private Long prodInstRelId;

    private Long aProdInstId;

    private Long zProdInstId;

    private String relType;

    private Long roleId;

    private Date effDate;

    private Date expDate;

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

    public Long getProdInstRelId() {
        return prodInstRelId;
    }

    public void setProdInstRelId(Long prodInstRelId) {
        this.prodInstRelId = prodInstRelId;
    }

    public Long getaProdInstId() {
        return aProdInstId;
    }

    public void setaProdInstId(Long aProdInstId) {
        this.aProdInstId = aProdInstId;
    }

    public Long getzProdInstId() {
        return zProdInstId;
    }

    public void setzProdInstId(Long zProdInstId) {
        this.zProdInstId = zProdInstId;
    }

    public String getRelType() {
        return relType;
    }

    public void setRelType(String relType) {
        this.relType = relType;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
        ProdInstRel other = (ProdInstRel) that;
        return (this.getProdInstRelId() == null ? other.getProdInstRelId() == null : this.getProdInstRelId().equals(other.getProdInstRelId()))
            && (this.getaProdInstId() == null ? other.getaProdInstId() == null : this.getaProdInstId().equals(other.getaProdInstId()))
            && (this.getzProdInstId() == null ? other.getzProdInstId() == null : this.getzProdInstId().equals(other.getzProdInstId()))
            && (this.getRelType() == null ? other.getRelType() == null : this.getRelType().equals(other.getRelType()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getEffDate() == null ? other.getEffDate() == null : this.getEffDate().equals(other.getEffDate()))
            && (this.getExpDate() == null ? other.getExpDate() == null : this.getExpDate().equals(other.getExpDate()))
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
        result = prime * result + ((getProdInstRelId() == null) ? 0 : getProdInstRelId().hashCode());
        result = prime * result + ((getaProdInstId() == null) ? 0 : getaProdInstId().hashCode());
        result = prime * result + ((getzProdInstId() == null) ? 0 : getzProdInstId().hashCode());
        result = prime * result + ((getRelType() == null) ? 0 : getRelType().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getEffDate() == null) ? 0 : getEffDate().hashCode());
        result = prime * result + ((getExpDate() == null) ? 0 : getExpDate().hashCode());
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
        sb.append(", prodInstRelId=").append(prodInstRelId);
        sb.append(", aProdInstId=").append(aProdInstId);
        sb.append(", zProdInstId=").append(zProdInstId);
        sb.append(", relType=").append(relType);
        sb.append(", roleId=").append(roleId);
        sb.append(", effDate=").append(effDate);
        sb.append(", expDate=").append(expDate);
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