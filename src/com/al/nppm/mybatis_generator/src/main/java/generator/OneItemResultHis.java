package generator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * one_item_result_his
 * @author 
 */
public class OneItemResultHis implements Serializable {
    private Long oneAcctItemId;

    private Long archGrpId;

    private Long custOrderId;

    private Long orderItemId;

    private Integer sourceType;

    private Long billId;

    private Long paymentId;

    private Long acctItemTypeId;

    private Long billAcctItemTypeId;

    private Long balanceTypeId;

    private Long billingCycleId;

    private Long itemSourceId;

    private Long acctId;

    private Long prodInstId;

    private Long offerInstId;

    private Long offerId;

    private Long realAmount;

    private Long taxRateConfigId;

    private BigDecimal taxRate;

    private Long tax;

    private Long price;

    private Integer resultItemType;

    private Date createDate;

    private Long chargeMethod;

    private Long statusCd;

    private Date statusDate;

    private Long oriAcctItemId;

    private Long lanId;

    private Integer ifChargeOff;

    private Date mergeAcctDealTime;

    private Long paidInAmount;

    private Long receiptId;

    private String busiName;

    private String extAcctItemId;

    private Long prodId;

    private Long staffId;

    private Long orgId;

    private Integer procFlag;

    private String remarks;

    private static final long serialVersionUID = 1L;

    public Long getOneAcctItemId() {
        return oneAcctItemId;
    }

    public void setOneAcctItemId(Long oneAcctItemId) {
        this.oneAcctItemId = oneAcctItemId;
    }

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

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getAcctItemTypeId() {
        return acctItemTypeId;
    }

    public void setAcctItemTypeId(Long acctItemTypeId) {
        this.acctItemTypeId = acctItemTypeId;
    }

    public Long getBillAcctItemTypeId() {
        return billAcctItemTypeId;
    }

    public void setBillAcctItemTypeId(Long billAcctItemTypeId) {
        this.billAcctItemTypeId = billAcctItemTypeId;
    }

    public Long getBalanceTypeId() {
        return balanceTypeId;
    }

    public void setBalanceTypeId(Long balanceTypeId) {
        this.balanceTypeId = balanceTypeId;
    }

    public Long getBillingCycleId() {
        return billingCycleId;
    }

    public void setBillingCycleId(Long billingCycleId) {
        this.billingCycleId = billingCycleId;
    }

    public Long getItemSourceId() {
        return itemSourceId;
    }

    public void setItemSourceId(Long itemSourceId) {
        this.itemSourceId = itemSourceId;
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public Long getProdInstId() {
        return prodInstId;
    }

    public void setProdInstId(Long prodInstId) {
        this.prodInstId = prodInstId;
    }

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

    public Long getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Long realAmount) {
        this.realAmount = realAmount;
    }

    public Long getTaxRateConfigId() {
        return taxRateConfigId;
    }

    public void setTaxRateConfigId(Long taxRateConfigId) {
        this.taxRateConfigId = taxRateConfigId;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getResultItemType() {
        return resultItemType;
    }

    public void setResultItemType(Integer resultItemType) {
        this.resultItemType = resultItemType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getChargeMethod() {
        return chargeMethod;
    }

    public void setChargeMethod(Long chargeMethod) {
        this.chargeMethod = chargeMethod;
    }

    public Long getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(Long statusCd) {
        this.statusCd = statusCd;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Long getOriAcctItemId() {
        return oriAcctItemId;
    }

    public void setOriAcctItemId(Long oriAcctItemId) {
        this.oriAcctItemId = oriAcctItemId;
    }

    public Long getLanId() {
        return lanId;
    }

    public void setLanId(Long lanId) {
        this.lanId = lanId;
    }

    public Integer getIfChargeOff() {
        return ifChargeOff;
    }

    public void setIfChargeOff(Integer ifChargeOff) {
        this.ifChargeOff = ifChargeOff;
    }

    public Date getMergeAcctDealTime() {
        return mergeAcctDealTime;
    }

    public void setMergeAcctDealTime(Date mergeAcctDealTime) {
        this.mergeAcctDealTime = mergeAcctDealTime;
    }

    public Long getPaidInAmount() {
        return paidInAmount;
    }

    public void setPaidInAmount(Long paidInAmount) {
        this.paidInAmount = paidInAmount;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public String getBusiName() {
        return busiName;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }

    public String getExtAcctItemId() {
        return extAcctItemId;
    }

    public void setExtAcctItemId(String extAcctItemId) {
        this.extAcctItemId = extAcctItemId;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Integer getProcFlag() {
        return procFlag;
    }

    public void setProcFlag(Integer procFlag) {
        this.procFlag = procFlag;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
        OneItemResultHis other = (OneItemResultHis) that;
        return (this.getOneAcctItemId() == null ? other.getOneAcctItemId() == null : this.getOneAcctItemId().equals(other.getOneAcctItemId()))
            && (this.getArchGrpId() == null ? other.getArchGrpId() == null : this.getArchGrpId().equals(other.getArchGrpId()))
            && (this.getCustOrderId() == null ? other.getCustOrderId() == null : this.getCustOrderId().equals(other.getCustOrderId()))
            && (this.getOrderItemId() == null ? other.getOrderItemId() == null : this.getOrderItemId().equals(other.getOrderItemId()))
            && (this.getSourceType() == null ? other.getSourceType() == null : this.getSourceType().equals(other.getSourceType()))
            && (this.getBillId() == null ? other.getBillId() == null : this.getBillId().equals(other.getBillId()))
            && (this.getPaymentId() == null ? other.getPaymentId() == null : this.getPaymentId().equals(other.getPaymentId()))
            && (this.getAcctItemTypeId() == null ? other.getAcctItemTypeId() == null : this.getAcctItemTypeId().equals(other.getAcctItemTypeId()))
            && (this.getBillAcctItemTypeId() == null ? other.getBillAcctItemTypeId() == null : this.getBillAcctItemTypeId().equals(other.getBillAcctItemTypeId()))
            && (this.getBalanceTypeId() == null ? other.getBalanceTypeId() == null : this.getBalanceTypeId().equals(other.getBalanceTypeId()))
            && (this.getBillingCycleId() == null ? other.getBillingCycleId() == null : this.getBillingCycleId().equals(other.getBillingCycleId()))
            && (this.getItemSourceId() == null ? other.getItemSourceId() == null : this.getItemSourceId().equals(other.getItemSourceId()))
            && (this.getAcctId() == null ? other.getAcctId() == null : this.getAcctId().equals(other.getAcctId()))
            && (this.getProdInstId() == null ? other.getProdInstId() == null : this.getProdInstId().equals(other.getProdInstId()))
            && (this.getOfferInstId() == null ? other.getOfferInstId() == null : this.getOfferInstId().equals(other.getOfferInstId()))
            && (this.getOfferId() == null ? other.getOfferId() == null : this.getOfferId().equals(other.getOfferId()))
            && (this.getRealAmount() == null ? other.getRealAmount() == null : this.getRealAmount().equals(other.getRealAmount()))
            && (this.getTaxRateConfigId() == null ? other.getTaxRateConfigId() == null : this.getTaxRateConfigId().equals(other.getTaxRateConfigId()))
            && (this.getTaxRate() == null ? other.getTaxRate() == null : this.getTaxRate().equals(other.getTaxRate()))
            && (this.getTax() == null ? other.getTax() == null : this.getTax().equals(other.getTax()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getResultItemType() == null ? other.getResultItemType() == null : this.getResultItemType().equals(other.getResultItemType()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getChargeMethod() == null ? other.getChargeMethod() == null : this.getChargeMethod().equals(other.getChargeMethod()))
            && (this.getStatusCd() == null ? other.getStatusCd() == null : this.getStatusCd().equals(other.getStatusCd()))
            && (this.getStatusDate() == null ? other.getStatusDate() == null : this.getStatusDate().equals(other.getStatusDate()))
            && (this.getOriAcctItemId() == null ? other.getOriAcctItemId() == null : this.getOriAcctItemId().equals(other.getOriAcctItemId()))
            && (this.getLanId() == null ? other.getLanId() == null : this.getLanId().equals(other.getLanId()))
            && (this.getIfChargeOff() == null ? other.getIfChargeOff() == null : this.getIfChargeOff().equals(other.getIfChargeOff()))
            && (this.getMergeAcctDealTime() == null ? other.getMergeAcctDealTime() == null : this.getMergeAcctDealTime().equals(other.getMergeAcctDealTime()))
            && (this.getPaidInAmount() == null ? other.getPaidInAmount() == null : this.getPaidInAmount().equals(other.getPaidInAmount()))
            && (this.getReceiptId() == null ? other.getReceiptId() == null : this.getReceiptId().equals(other.getReceiptId()))
            && (this.getBusiName() == null ? other.getBusiName() == null : this.getBusiName().equals(other.getBusiName()))
            && (this.getExtAcctItemId() == null ? other.getExtAcctItemId() == null : this.getExtAcctItemId().equals(other.getExtAcctItemId()))
            && (this.getProdId() == null ? other.getProdId() == null : this.getProdId().equals(other.getProdId()))
            && (this.getStaffId() == null ? other.getStaffId() == null : this.getStaffId().equals(other.getStaffId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getProcFlag() == null ? other.getProcFlag() == null : this.getProcFlag().equals(other.getProcFlag()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOneAcctItemId() == null) ? 0 : getOneAcctItemId().hashCode());
        result = prime * result + ((getArchGrpId() == null) ? 0 : getArchGrpId().hashCode());
        result = prime * result + ((getCustOrderId() == null) ? 0 : getCustOrderId().hashCode());
        result = prime * result + ((getOrderItemId() == null) ? 0 : getOrderItemId().hashCode());
        result = prime * result + ((getSourceType() == null) ? 0 : getSourceType().hashCode());
        result = prime * result + ((getBillId() == null) ? 0 : getBillId().hashCode());
        result = prime * result + ((getPaymentId() == null) ? 0 : getPaymentId().hashCode());
        result = prime * result + ((getAcctItemTypeId() == null) ? 0 : getAcctItemTypeId().hashCode());
        result = prime * result + ((getBillAcctItemTypeId() == null) ? 0 : getBillAcctItemTypeId().hashCode());
        result = prime * result + ((getBalanceTypeId() == null) ? 0 : getBalanceTypeId().hashCode());
        result = prime * result + ((getBillingCycleId() == null) ? 0 : getBillingCycleId().hashCode());
        result = prime * result + ((getItemSourceId() == null) ? 0 : getItemSourceId().hashCode());
        result = prime * result + ((getAcctId() == null) ? 0 : getAcctId().hashCode());
        result = prime * result + ((getProdInstId() == null) ? 0 : getProdInstId().hashCode());
        result = prime * result + ((getOfferInstId() == null) ? 0 : getOfferInstId().hashCode());
        result = prime * result + ((getOfferId() == null) ? 0 : getOfferId().hashCode());
        result = prime * result + ((getRealAmount() == null) ? 0 : getRealAmount().hashCode());
        result = prime * result + ((getTaxRateConfigId() == null) ? 0 : getTaxRateConfigId().hashCode());
        result = prime * result + ((getTaxRate() == null) ? 0 : getTaxRate().hashCode());
        result = prime * result + ((getTax() == null) ? 0 : getTax().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getResultItemType() == null) ? 0 : getResultItemType().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getChargeMethod() == null) ? 0 : getChargeMethod().hashCode());
        result = prime * result + ((getStatusCd() == null) ? 0 : getStatusCd().hashCode());
        result = prime * result + ((getStatusDate() == null) ? 0 : getStatusDate().hashCode());
        result = prime * result + ((getOriAcctItemId() == null) ? 0 : getOriAcctItemId().hashCode());
        result = prime * result + ((getLanId() == null) ? 0 : getLanId().hashCode());
        result = prime * result + ((getIfChargeOff() == null) ? 0 : getIfChargeOff().hashCode());
        result = prime * result + ((getMergeAcctDealTime() == null) ? 0 : getMergeAcctDealTime().hashCode());
        result = prime * result + ((getPaidInAmount() == null) ? 0 : getPaidInAmount().hashCode());
        result = prime * result + ((getReceiptId() == null) ? 0 : getReceiptId().hashCode());
        result = prime * result + ((getBusiName() == null) ? 0 : getBusiName().hashCode());
        result = prime * result + ((getExtAcctItemId() == null) ? 0 : getExtAcctItemId().hashCode());
        result = prime * result + ((getProdId() == null) ? 0 : getProdId().hashCode());
        result = prime * result + ((getStaffId() == null) ? 0 : getStaffId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getProcFlag() == null) ? 0 : getProcFlag().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oneAcctItemId=").append(oneAcctItemId);
        sb.append(", archGrpId=").append(archGrpId);
        sb.append(", custOrderId=").append(custOrderId);
        sb.append(", orderItemId=").append(orderItemId);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", billId=").append(billId);
        sb.append(", paymentId=").append(paymentId);
        sb.append(", acctItemTypeId=").append(acctItemTypeId);
        sb.append(", billAcctItemTypeId=").append(billAcctItemTypeId);
        sb.append(", balanceTypeId=").append(balanceTypeId);
        sb.append(", billingCycleId=").append(billingCycleId);
        sb.append(", itemSourceId=").append(itemSourceId);
        sb.append(", acctId=").append(acctId);
        sb.append(", prodInstId=").append(prodInstId);
        sb.append(", offerInstId=").append(offerInstId);
        sb.append(", offerId=").append(offerId);
        sb.append(", realAmount=").append(realAmount);
        sb.append(", taxRateConfigId=").append(taxRateConfigId);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", tax=").append(tax);
        sb.append(", price=").append(price);
        sb.append(", resultItemType=").append(resultItemType);
        sb.append(", createDate=").append(createDate);
        sb.append(", chargeMethod=").append(chargeMethod);
        sb.append(", statusCd=").append(statusCd);
        sb.append(", statusDate=").append(statusDate);
        sb.append(", oriAcctItemId=").append(oriAcctItemId);
        sb.append(", lanId=").append(lanId);
        sb.append(", ifChargeOff=").append(ifChargeOff);
        sb.append(", mergeAcctDealTime=").append(mergeAcctDealTime);
        sb.append(", paidInAmount=").append(paidInAmount);
        sb.append(", receiptId=").append(receiptId);
        sb.append(", busiName=").append(busiName);
        sb.append(", extAcctItemId=").append(extAcctItemId);
        sb.append(", prodId=").append(prodId);
        sb.append(", staffId=").append(staffId);
        sb.append(", orgId=").append(orgId);
        sb.append(", procFlag=").append(procFlag);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}