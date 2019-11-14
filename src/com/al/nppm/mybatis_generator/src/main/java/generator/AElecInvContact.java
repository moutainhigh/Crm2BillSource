package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * a_elec_inv_contact
 * @author 
 */
public class AElecInvContact implements Serializable {
    /**
     * 主键
     */
    private Integer contactId;

    /**
     * 帐户标识
     */
    private Long acctId;

    /**
     * 号码
     */
    private String accNbr;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private String state;

    /**
     * 状态时间
     */
    private Date stateDate;

    /**
     * 纳税人识别号
     */
    private String taxPayerNbr;

    /**
     * 纳税人名称
     */
    private String taxPayerName;

    /**
     * 纳税人地址
     */
    private String taxPayerAddr;

    /**
     * 纳税人银行帐号
     */
    private String taxPayerBank;

    /**
     * 开票标识
     */
    private Integer sendFlag;

    /**
     * 分片键
     */
    private Long routeId;

    private static final long serialVersionUID = 1L;

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Long getAcctId() {
        return acctId;
    }

    public void setAcctId(Long acctId) {
        this.acctId = acctId;
    }

    public String getAccNbr() {
        return accNbr;
    }

    public void setAccNbr(String accNbr) {
        this.accNbr = accNbr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public String getTaxPayerNbr() {
        return taxPayerNbr;
    }

    public void setTaxPayerNbr(String taxPayerNbr) {
        this.taxPayerNbr = taxPayerNbr;
    }

    public String getTaxPayerName() {
        return taxPayerName;
    }

    public void setTaxPayerName(String taxPayerName) {
        this.taxPayerName = taxPayerName;
    }

    public String getTaxPayerAddr() {
        return taxPayerAddr;
    }

    public void setTaxPayerAddr(String taxPayerAddr) {
        this.taxPayerAddr = taxPayerAddr;
    }

    public String getTaxPayerBank() {
        return taxPayerBank;
    }

    public void setTaxPayerBank(String taxPayerBank) {
        this.taxPayerBank = taxPayerBank;
    }

    public Integer getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(Integer sendFlag) {
        this.sendFlag = sendFlag;
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
        AElecInvContact other = (AElecInvContact) that;
        return (this.getContactId() == null ? other.getContactId() == null : this.getContactId().equals(other.getContactId()))
            && (this.getAcctId() == null ? other.getAcctId() == null : this.getAcctId().equals(other.getAcctId()))
            && (this.getAccNbr() == null ? other.getAccNbr() == null : this.getAccNbr().equals(other.getAccNbr()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getStateDate() == null ? other.getStateDate() == null : this.getStateDate().equals(other.getStateDate()))
            && (this.getTaxPayerNbr() == null ? other.getTaxPayerNbr() == null : this.getTaxPayerNbr().equals(other.getTaxPayerNbr()))
            && (this.getTaxPayerName() == null ? other.getTaxPayerName() == null : this.getTaxPayerName().equals(other.getTaxPayerName()))
            && (this.getTaxPayerAddr() == null ? other.getTaxPayerAddr() == null : this.getTaxPayerAddr().equals(other.getTaxPayerAddr()))
            && (this.getTaxPayerBank() == null ? other.getTaxPayerBank() == null : this.getTaxPayerBank().equals(other.getTaxPayerBank()))
            && (this.getSendFlag() == null ? other.getSendFlag() == null : this.getSendFlag().equals(other.getSendFlag()))
            && (this.getRouteId() == null ? other.getRouteId() == null : this.getRouteId().equals(other.getRouteId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getContactId() == null) ? 0 : getContactId().hashCode());
        result = prime * result + ((getAcctId() == null) ? 0 : getAcctId().hashCode());
        result = prime * result + ((getAccNbr() == null) ? 0 : getAccNbr().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getStateDate() == null) ? 0 : getStateDate().hashCode());
        result = prime * result + ((getTaxPayerNbr() == null) ? 0 : getTaxPayerNbr().hashCode());
        result = prime * result + ((getTaxPayerName() == null) ? 0 : getTaxPayerName().hashCode());
        result = prime * result + ((getTaxPayerAddr() == null) ? 0 : getTaxPayerAddr().hashCode());
        result = prime * result + ((getTaxPayerBank() == null) ? 0 : getTaxPayerBank().hashCode());
        result = prime * result + ((getSendFlag() == null) ? 0 : getSendFlag().hashCode());
        result = prime * result + ((getRouteId() == null) ? 0 : getRouteId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", contactId=").append(contactId);
        sb.append(", acctId=").append(acctId);
        sb.append(", accNbr=").append(accNbr);
        sb.append(", email=").append(email);
        sb.append(", state=").append(state);
        sb.append(", stateDate=").append(stateDate);
        sb.append(", taxPayerNbr=").append(taxPayerNbr);
        sb.append(", taxPayerName=").append(taxPayerName);
        sb.append(", taxPayerAddr=").append(taxPayerAddr);
        sb.append(", taxPayerBank=").append(taxPayerBank);
        sb.append(", sendFlag=").append(sendFlag);
        sb.append(", routeId=").append(routeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}