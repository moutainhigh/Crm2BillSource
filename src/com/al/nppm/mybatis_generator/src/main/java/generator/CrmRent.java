package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * crm_rent
 * @author 
 */
public class CrmRent implements Serializable {
    /**
     * 记录数据的行号，主键
     */
    private Long rowId;

    private Long archGrpId;

    /**
     * 记录订单项标识
     */
    private Long orderItemId;

    /**
     * 租机和划拨活动的实例ID
     */
    private Long offerInstId;

    /**
     * 产品实例ID
     */
    private Long prodInstId;

    /**
     * 租机和划拨活动区分，Z为租机活动，L为礼券类划拨需要按协议期解约，LQHB为礼券类划拨活动，DQHB为抵用券划拨活动，HB为普通划拨活动
     */
    private String offerType;

    private Date offerStartDate;

    /**
     * 计费提供的产品实例从活动开始到当前的累计消费金额
     */
    private Long billTotalFee;

    /**
     * CRM配置的协议金额：按协议金额解约时，比较协议金额与计费提供的累积消费金额
     */
    private String crmTotalFee;

    /**
     * 解约类型，1：表示按协议期解约；2：表示按协议金额解约；3：划拨类直接解约；-1：错误数据，不处理
     */
    private String endType;

    /**
     * 协议期：按协议期解约时，比较实例生效时间与系统当前时间是否超过协议期
     */
    private Long rentMonth;

    /**
     * 销售品规格ID
     */
    private Long offerId;

    /**
     * 返回类型，取JSON
     */
    private Long returnType;

    /**
     * 划拨规则ID，调PPM接口
     */
    private Long returnRuleId;

    /**
     * 冲正类型,订购 1 ，退订时当51=1时为红包，state=2，退订时51=Y,J时为翼支付，state=3
     */
    private String state;

    /**
     * 记录状态
     */
    private Long statusCd;

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

    public Long getOfferInstId() {
        return offerInstId;
    }

    public void setOfferInstId(Long offerInstId) {
        this.offerInstId = offerInstId;
    }

    public Long getProdInstId() {
        return prodInstId;
    }

    public void setProdInstId(Long prodInstId) {
        this.prodInstId = prodInstId;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public Date getOfferStartDate() {
        return offerStartDate;
    }

    public void setOfferStartDate(Date offerStartDate) {
        this.offerStartDate = offerStartDate;
    }

    public Long getBillTotalFee() {
        return billTotalFee;
    }

    public void setBillTotalFee(Long billTotalFee) {
        this.billTotalFee = billTotalFee;
    }

    public String getCrmTotalFee() {
        return crmTotalFee;
    }

    public void setCrmTotalFee(String crmTotalFee) {
        this.crmTotalFee = crmTotalFee;
    }

    public String getEndType() {
        return endType;
    }

    public void setEndType(String endType) {
        this.endType = endType;
    }

    public Long getRentMonth() {
        return rentMonth;
    }

    public void setRentMonth(Long rentMonth) {
        this.rentMonth = rentMonth;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getReturnType() {
        return returnType;
    }

    public void setReturnType(Long returnType) {
        this.returnType = returnType;
    }

    public Long getReturnRuleId() {
        return returnRuleId;
    }

    public void setReturnRuleId(Long returnRuleId) {
        this.returnRuleId = returnRuleId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(Long statusCd) {
        this.statusCd = statusCd;
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
        CrmRent other = (CrmRent) that;
        return (this.getRowId() == null ? other.getRowId() == null : this.getRowId().equals(other.getRowId()))
            && (this.getArchGrpId() == null ? other.getArchGrpId() == null : this.getArchGrpId().equals(other.getArchGrpId()))
            && (this.getOrderItemId() == null ? other.getOrderItemId() == null : this.getOrderItemId().equals(other.getOrderItemId()))
            && (this.getOfferInstId() == null ? other.getOfferInstId() == null : this.getOfferInstId().equals(other.getOfferInstId()))
            && (this.getProdInstId() == null ? other.getProdInstId() == null : this.getProdInstId().equals(other.getProdInstId()))
            && (this.getOfferType() == null ? other.getOfferType() == null : this.getOfferType().equals(other.getOfferType()))
            && (this.getOfferStartDate() == null ? other.getOfferStartDate() == null : this.getOfferStartDate().equals(other.getOfferStartDate()))
            && (this.getBillTotalFee() == null ? other.getBillTotalFee() == null : this.getBillTotalFee().equals(other.getBillTotalFee()))
            && (this.getCrmTotalFee() == null ? other.getCrmTotalFee() == null : this.getCrmTotalFee().equals(other.getCrmTotalFee()))
            && (this.getEndType() == null ? other.getEndType() == null : this.getEndType().equals(other.getEndType()))
            && (this.getRentMonth() == null ? other.getRentMonth() == null : this.getRentMonth().equals(other.getRentMonth()))
            && (this.getOfferId() == null ? other.getOfferId() == null : this.getOfferId().equals(other.getOfferId()))
            && (this.getReturnType() == null ? other.getReturnType() == null : this.getReturnType().equals(other.getReturnType()))
            && (this.getReturnRuleId() == null ? other.getReturnRuleId() == null : this.getReturnRuleId().equals(other.getReturnRuleId()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getStatusCd() == null ? other.getStatusCd() == null : this.getStatusCd().equals(other.getStatusCd()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRowId() == null) ? 0 : getRowId().hashCode());
        result = prime * result + ((getArchGrpId() == null) ? 0 : getArchGrpId().hashCode());
        result = prime * result + ((getOrderItemId() == null) ? 0 : getOrderItemId().hashCode());
        result = prime * result + ((getOfferInstId() == null) ? 0 : getOfferInstId().hashCode());
        result = prime * result + ((getProdInstId() == null) ? 0 : getProdInstId().hashCode());
        result = prime * result + ((getOfferType() == null) ? 0 : getOfferType().hashCode());
        result = prime * result + ((getOfferStartDate() == null) ? 0 : getOfferStartDate().hashCode());
        result = prime * result + ((getBillTotalFee() == null) ? 0 : getBillTotalFee().hashCode());
        result = prime * result + ((getCrmTotalFee() == null) ? 0 : getCrmTotalFee().hashCode());
        result = prime * result + ((getEndType() == null) ? 0 : getEndType().hashCode());
        result = prime * result + ((getRentMonth() == null) ? 0 : getRentMonth().hashCode());
        result = prime * result + ((getOfferId() == null) ? 0 : getOfferId().hashCode());
        result = prime * result + ((getReturnType() == null) ? 0 : getReturnType().hashCode());
        result = prime * result + ((getReturnRuleId() == null) ? 0 : getReturnRuleId().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getStatusCd() == null) ? 0 : getStatusCd().hashCode());
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
        sb.append(", offerInstId=").append(offerInstId);
        sb.append(", prodInstId=").append(prodInstId);
        sb.append(", offerType=").append(offerType);
        sb.append(", offerStartDate=").append(offerStartDate);
        sb.append(", billTotalFee=").append(billTotalFee);
        sb.append(", crmTotalFee=").append(crmTotalFee);
        sb.append(", endType=").append(endType);
        sb.append(", rentMonth=").append(rentMonth);
        sb.append(", offerId=").append(offerId);
        sb.append(", returnType=").append(returnType);
        sb.append(", returnRuleId=").append(returnRuleId);
        sb.append(", state=").append(state);
        sb.append(", statusCd=").append(statusCd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}