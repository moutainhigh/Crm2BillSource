package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * ord_prod_inst_acct_rel_attr
 * @author 
 */
public class OrdProdInstAcctRelAttr implements Serializable {
    private Long rowId;

    private Long archGrpId;

    private Long orderItemId;

    private Long prodInstAcctRelAttrId;

    private Long prodInstAcctRelId;

    private Long attrId;

    private Long attrValueId;

    private String attrValue;

    private String attrName;

    private Long oldRowId;

    private String operType;

    private Date effDate;

    private Date expDate;

    private String statusCd;

    private Date statusDate;

    private Long createStaff;

    private Date createDate;

    private Long updateStaff;

    private Date updateDate;

    private Long verNum;

    private static final long serialVersionUID = 1L;

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public Long getArchGrpId() {
        return archGrpId;
    }

    public void setArchGrpId(Long archGrpId) {
        this.archGrpId = archGrpId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getProdInstAcctRelAttrId() {
        return prodInstAcctRelAttrId;
    }

    public void setProdInstAcctRelAttrId(Long prodInstAcctRelAttrId) {
        this.prodInstAcctRelAttrId = prodInstAcctRelAttrId;
    }

    public Long getProdInstAcctRelId() {
        return prodInstAcctRelId;
    }

    public void setProdInstAcctRelId(Long prodInstAcctRelId) {
        this.prodInstAcctRelId = prodInstAcctRelId;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public Long getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Long attrValueId) {
        this.attrValueId = attrValueId;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Long getOldRowId() {
        return oldRowId;
    }

    public void setOldRowId(Long oldRowId) {
        this.oldRowId = oldRowId;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
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

    public Long getVerNum() {
        return verNum;
    }

    public void setVerNum(Long verNum) {
        this.verNum = verNum;
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
        OrdProdInstAcctRelAttr other = (OrdProdInstAcctRelAttr) that;
        return (this.getRowId() == null ? other.getRowId() == null : this.getRowId().equals(other.getRowId()))
            && (this.getArchGrpId() == null ? other.getArchGrpId() == null : this.getArchGrpId().equals(other.getArchGrpId()))
            && (this.getOrderItemId() == null ? other.getOrderItemId() == null : this.getOrderItemId().equals(other.getOrderItemId()))
            && (this.getProdInstAcctRelAttrId() == null ? other.getProdInstAcctRelAttrId() == null : this.getProdInstAcctRelAttrId().equals(other.getProdInstAcctRelAttrId()))
            && (this.getProdInstAcctRelId() == null ? other.getProdInstAcctRelId() == null : this.getProdInstAcctRelId().equals(other.getProdInstAcctRelId()))
            && (this.getAttrId() == null ? other.getAttrId() == null : this.getAttrId().equals(other.getAttrId()))
            && (this.getAttrValueId() == null ? other.getAttrValueId() == null : this.getAttrValueId().equals(other.getAttrValueId()))
            && (this.getAttrValue() == null ? other.getAttrValue() == null : this.getAttrValue().equals(other.getAttrValue()))
            && (this.getAttrName() == null ? other.getAttrName() == null : this.getAttrName().equals(other.getAttrName()))
            && (this.getOldRowId() == null ? other.getOldRowId() == null : this.getOldRowId().equals(other.getOldRowId()))
            && (this.getOperType() == null ? other.getOperType() == null : this.getOperType().equals(other.getOperType()))
            && (this.getEffDate() == null ? other.getEffDate() == null : this.getEffDate().equals(other.getEffDate()))
            && (this.getExpDate() == null ? other.getExpDate() == null : this.getExpDate().equals(other.getExpDate()))
            && (this.getStatusCd() == null ? other.getStatusCd() == null : this.getStatusCd().equals(other.getStatusCd()))
            && (this.getStatusDate() == null ? other.getStatusDate() == null : this.getStatusDate().equals(other.getStatusDate()))
            && (this.getCreateStaff() == null ? other.getCreateStaff() == null : this.getCreateStaff().equals(other.getCreateStaff()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateStaff() == null ? other.getUpdateStaff() == null : this.getUpdateStaff().equals(other.getUpdateStaff()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getVerNum() == null ? other.getVerNum() == null : this.getVerNum().equals(other.getVerNum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRowId() == null) ? 0 : getRowId().hashCode());
        result = prime * result + ((getArchGrpId() == null) ? 0 : getArchGrpId().hashCode());
        result = prime * result + ((getOrderItemId() == null) ? 0 : getOrderItemId().hashCode());
        result = prime * result + ((getProdInstAcctRelAttrId() == null) ? 0 : getProdInstAcctRelAttrId().hashCode());
        result = prime * result + ((getProdInstAcctRelId() == null) ? 0 : getProdInstAcctRelId().hashCode());
        result = prime * result + ((getAttrId() == null) ? 0 : getAttrId().hashCode());
        result = prime * result + ((getAttrValueId() == null) ? 0 : getAttrValueId().hashCode());
        result = prime * result + ((getAttrValue() == null) ? 0 : getAttrValue().hashCode());
        result = prime * result + ((getAttrName() == null) ? 0 : getAttrName().hashCode());
        result = prime * result + ((getOldRowId() == null) ? 0 : getOldRowId().hashCode());
        result = prime * result + ((getOperType() == null) ? 0 : getOperType().hashCode());
        result = prime * result + ((getEffDate() == null) ? 0 : getEffDate().hashCode());
        result = prime * result + ((getExpDate() == null) ? 0 : getExpDate().hashCode());
        result = prime * result + ((getStatusCd() == null) ? 0 : getStatusCd().hashCode());
        result = prime * result + ((getStatusDate() == null) ? 0 : getStatusDate().hashCode());
        result = prime * result + ((getCreateStaff() == null) ? 0 : getCreateStaff().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateStaff() == null) ? 0 : getUpdateStaff().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getVerNum() == null) ? 0 : getVerNum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rowId=").append(rowId);
        sb.append(", archGrpId=").append(archGrpId);
        sb.append(", orderItemId=").append(orderItemId);
        sb.append(", prodInstAcctRelAttrId=").append(prodInstAcctRelAttrId);
        sb.append(", prodInstAcctRelId=").append(prodInstAcctRelId);
        sb.append(", attrId=").append(attrId);
        sb.append(", attrValueId=").append(attrValueId);
        sb.append(", attrValue=").append(attrValue);
        sb.append(", attrName=").append(attrName);
        sb.append(", oldRowId=").append(oldRowId);
        sb.append(", operType=").append(operType);
        sb.append(", effDate=").append(effDate);
        sb.append(", expDate=").append(expDate);
        sb.append(", statusCd=").append(statusCd);
        sb.append(", statusDate=").append(statusDate);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", verNum=").append(verNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}