package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * inter_pay_to_plan
 * @author 
 */
public class InterPayToPlan implements Serializable {
    /**
     * 流水号，主键
     */
    private Long interPlanId;

    /**
     * 销售品实例标识，订购、退订为同一个 
     */
    private Long offerInstId;

    /**
     * 销售品ID
     */
    private Long offerId;

    /**
     * 金额
     */
    private Long amount;

    /**
     * 业务类型，1-ICB预存支付计划，2-预存支付计划销售品，3-预存款销售品，4-无销售品预存款，退订时同样
     */
    private Integer depositType;

    /**
     * 账户ID
     */
    private Long acctId;

    /**
     * 对象类型，1-账户ID,2-用户ID
     */
    private Integer objectType;

    /**
     * 对象值
     */
    private Long objectId;

    /**
     * 操作类型
1-订购、2-退订本金、3-退订赠送、4-退订翼支付
5-退订本金和赠送、6-退订本金和翼支付、7-退订赠送和翼支付、
8-退订本金、赠送和翼支付
     */
    private Integer operType;

    /**
     * 处理状态：0未处理、1处理成功、2处理失败、3待处理退订赠送 (由oper_state=0+oper_type=3+acct_balance.state_cd=1,继续用一个月，下月做支出)
     */
    private Integer operState;

    /**
     * 套餐生效时间
     */
    private Date effDate;

    /**
     * 订购时间
     */
    private Date orderDate;

    /**
     * 记录创建时间
     */
    private Date createDate;

    /**
     * 操作发生时间,当oper_date=3时，to_char(last_day(A.oper_date) + 3, 'YYYYMMDD') || '2350' 大于当前系统时间则执行  
     */
    private Date operDate;

    /**
     * 重复执行次数，退订3次为上限，订购不限制，默认0
     */
    private Long recount;

    private static final long serialVersionUID = 1L;

    public Long getInterPlanId() {
        return interPlanId;
    }

    public void setInterPlanId(Long interPlanId) {
        this.interPlanId = interPlanId;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getDepositType() {
        return depositType;
    }

    public void setDepositType(Integer depositType) {
        this.depositType = depositType;
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public Integer getOperState() {
        return operState;
    }

    public void setOperState(Integer operState) {
        this.operState = operState;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public Long getRecount() {
        return recount;
    }

    public void setRecount(Long recount) {
        this.recount = recount;
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
        InterPayToPlan other = (InterPayToPlan) that;
        return (this.getInterPlanId() == null ? other.getInterPlanId() == null : this.getInterPlanId().equals(other.getInterPlanId()))
            && (this.getOfferInstId() == null ? other.getOfferInstId() == null : this.getOfferInstId().equals(other.getOfferInstId()))
            && (this.getOfferId() == null ? other.getOfferId() == null : this.getOfferId().equals(other.getOfferId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getDepositType() == null ? other.getDepositType() == null : this.getDepositType().equals(other.getDepositType()))
            && (this.getAcctId() == null ? other.getAcctId() == null : this.getAcctId().equals(other.getAcctId()))
            && (this.getObjectType() == null ? other.getObjectType() == null : this.getObjectType().equals(other.getObjectType()))
            && (this.getObjectId() == null ? other.getObjectId() == null : this.getObjectId().equals(other.getObjectId()))
            && (this.getOperType() == null ? other.getOperType() == null : this.getOperType().equals(other.getOperType()))
            && (this.getOperState() == null ? other.getOperState() == null : this.getOperState().equals(other.getOperState()))
            && (this.getEffDate() == null ? other.getEffDate() == null : this.getEffDate().equals(other.getEffDate()))
            && (this.getOrderDate() == null ? other.getOrderDate() == null : this.getOrderDate().equals(other.getOrderDate()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getOperDate() == null ? other.getOperDate() == null : this.getOperDate().equals(other.getOperDate()))
            && (this.getRecount() == null ? other.getRecount() == null : this.getRecount().equals(other.getRecount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getInterPlanId() == null) ? 0 : getInterPlanId().hashCode());
        result = prime * result + ((getOfferInstId() == null) ? 0 : getOfferInstId().hashCode());
        result = prime * result + ((getOfferId() == null) ? 0 : getOfferId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getDepositType() == null) ? 0 : getDepositType().hashCode());
        result = prime * result + ((getAcctId() == null) ? 0 : getAcctId().hashCode());
        result = prime * result + ((getObjectType() == null) ? 0 : getObjectType().hashCode());
        result = prime * result + ((getObjectId() == null) ? 0 : getObjectId().hashCode());
        result = prime * result + ((getOperType() == null) ? 0 : getOperType().hashCode());
        result = prime * result + ((getOperState() == null) ? 0 : getOperState().hashCode());
        result = prime * result + ((getEffDate() == null) ? 0 : getEffDate().hashCode());
        result = prime * result + ((getOrderDate() == null) ? 0 : getOrderDate().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getOperDate() == null) ? 0 : getOperDate().hashCode());
        result = prime * result + ((getRecount() == null) ? 0 : getRecount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", interPlanId=").append(interPlanId);
        sb.append(", offerInstId=").append(offerInstId);
        sb.append(", offerId=").append(offerId);
        sb.append(", amount=").append(amount);
        sb.append(", depositType=").append(depositType);
        sb.append(", acctId=").append(acctId);
        sb.append(", objectType=").append(objectType);
        sb.append(", objectId=").append(objectId);
        sb.append(", operType=").append(operType);
        sb.append(", operState=").append(operState);
        sb.append(", effDate=").append(effDate);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", createDate=").append(createDate);
        sb.append(", operDate=").append(operDate);
        sb.append(", recount=").append(recount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}