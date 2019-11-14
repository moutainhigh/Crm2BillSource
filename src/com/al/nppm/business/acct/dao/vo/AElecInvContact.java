package com.al.nppm.business.acct.dao.vo;

/**
 * a_elec_inv_contact
 * @author 
 */
public class AElecInvContact  {
    /**
     * 主键
     */
    private long contactId;

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
    private String stateDate;

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

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
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

    public String getStateDate() {
        return stateDate;
    }

    public void setStateDate(String stateDate) {
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

}