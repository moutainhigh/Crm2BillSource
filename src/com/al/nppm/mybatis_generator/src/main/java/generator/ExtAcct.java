package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * ext_acct
 * @author 
 */
public class ExtAcct implements Serializable {
    private Long extAcctId;

    private Long custId;

    private Integer payChannel;

    private String payAcctCode;

    private String payAcctName;

    private Long acctOwnerOrg;

    private Long acctOwnerOrgBranch;

    private Date effDate;

    private Date expDate;

    private Integer ifContractQuickPay;

    private String statusCd;

    private Date statusDate;

    private Long createStaff;

    private Date createDate;

    private Long updateStaff;

    private Date updateDate;

    private String commisionAgreementId;

    private Long routeId;

    private static final long serialVersionUID = 1L;

    public Long getExtAcctId() {
        return extAcctId;
    }

    public void setExtAcctId(Long extAcctId) {
        this.extAcctId = extAcctId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public Integer getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayAcctCode() {
        return payAcctCode;
    }

    public void setPayAcctCode(String payAcctCode) {
        this.payAcctCode = payAcctCode;
    }

    public String getPayAcctName() {
        return payAcctName;
    }

    public void setPayAcctName(String payAcctName) {
        this.payAcctName = payAcctName;
    }

    public Long getAcctOwnerOrg() {
        return acctOwnerOrg;
    }

    public void setAcctOwnerOrg(Long acctOwnerOrg) {
        this.acctOwnerOrg = acctOwnerOrg;
    }

    public Long getAcctOwnerOrgBranch() {
        return acctOwnerOrgBranch;
    }

    public void setAcctOwnerOrgBranch(Long acctOwnerOrgBranch) {
        this.acctOwnerOrgBranch = acctOwnerOrgBranch;
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

    public Integer getIfContractQuickPay() {
        return ifContractQuickPay;
    }

    public void setIfContractQuickPay(Integer ifContractQuickPay) {
        this.ifContractQuickPay = ifContractQuickPay;
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

    public String getCommisionAgreementId() {
        return commisionAgreementId;
    }

    public void setCommisionAgreementId(String commisionAgreementId) {
        this.commisionAgreementId = commisionAgreementId;
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
        ExtAcct other = (ExtAcct) that;
        return (this.getExtAcctId() == null ? other.getExtAcctId() == null : this.getExtAcctId().equals(other.getExtAcctId()))
            && (this.getCustId() == null ? other.getCustId() == null : this.getCustId().equals(other.getCustId()))
            && (this.getPayChannel() == null ? other.getPayChannel() == null : this.getPayChannel().equals(other.getPayChannel()))
            && (this.getPayAcctCode() == null ? other.getPayAcctCode() == null : this.getPayAcctCode().equals(other.getPayAcctCode()))
            && (this.getPayAcctName() == null ? other.getPayAcctName() == null : this.getPayAcctName().equals(other.getPayAcctName()))
            && (this.getAcctOwnerOrg() == null ? other.getAcctOwnerOrg() == null : this.getAcctOwnerOrg().equals(other.getAcctOwnerOrg()))
            && (this.getAcctOwnerOrgBranch() == null ? other.getAcctOwnerOrgBranch() == null : this.getAcctOwnerOrgBranch().equals(other.getAcctOwnerOrgBranch()))
            && (this.getEffDate() == null ? other.getEffDate() == null : this.getEffDate().equals(other.getEffDate()))
            && (this.getExpDate() == null ? other.getExpDate() == null : this.getExpDate().equals(other.getExpDate()))
            && (this.getIfContractQuickPay() == null ? other.getIfContractQuickPay() == null : this.getIfContractQuickPay().equals(other.getIfContractQuickPay()))
            && (this.getStatusCd() == null ? other.getStatusCd() == null : this.getStatusCd().equals(other.getStatusCd()))
            && (this.getStatusDate() == null ? other.getStatusDate() == null : this.getStatusDate().equals(other.getStatusDate()))
            && (this.getCreateStaff() == null ? other.getCreateStaff() == null : this.getCreateStaff().equals(other.getCreateStaff()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateStaff() == null ? other.getUpdateStaff() == null : this.getUpdateStaff().equals(other.getUpdateStaff()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getCommisionAgreementId() == null ? other.getCommisionAgreementId() == null : this.getCommisionAgreementId().equals(other.getCommisionAgreementId()))
            && (this.getRouteId() == null ? other.getRouteId() == null : this.getRouteId().equals(other.getRouteId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExtAcctId() == null) ? 0 : getExtAcctId().hashCode());
        result = prime * result + ((getCustId() == null) ? 0 : getCustId().hashCode());
        result = prime * result + ((getPayChannel() == null) ? 0 : getPayChannel().hashCode());
        result = prime * result + ((getPayAcctCode() == null) ? 0 : getPayAcctCode().hashCode());
        result = prime * result + ((getPayAcctName() == null) ? 0 : getPayAcctName().hashCode());
        result = prime * result + ((getAcctOwnerOrg() == null) ? 0 : getAcctOwnerOrg().hashCode());
        result = prime * result + ((getAcctOwnerOrgBranch() == null) ? 0 : getAcctOwnerOrgBranch().hashCode());
        result = prime * result + ((getEffDate() == null) ? 0 : getEffDate().hashCode());
        result = prime * result + ((getExpDate() == null) ? 0 : getExpDate().hashCode());
        result = prime * result + ((getIfContractQuickPay() == null) ? 0 : getIfContractQuickPay().hashCode());
        result = prime * result + ((getStatusCd() == null) ? 0 : getStatusCd().hashCode());
        result = prime * result + ((getStatusDate() == null) ? 0 : getStatusDate().hashCode());
        result = prime * result + ((getCreateStaff() == null) ? 0 : getCreateStaff().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateStaff() == null) ? 0 : getUpdateStaff().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getCommisionAgreementId() == null) ? 0 : getCommisionAgreementId().hashCode());
        result = prime * result + ((getRouteId() == null) ? 0 : getRouteId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", extAcctId=").append(extAcctId);
        sb.append(", custId=").append(custId);
        sb.append(", payChannel=").append(payChannel);
        sb.append(", payAcctCode=").append(payAcctCode);
        sb.append(", payAcctName=").append(payAcctName);
        sb.append(", acctOwnerOrg=").append(acctOwnerOrg);
        sb.append(", acctOwnerOrgBranch=").append(acctOwnerOrgBranch);
        sb.append(", effDate=").append(effDate);
        sb.append(", expDate=").append(expDate);
        sb.append(", ifContractQuickPay=").append(ifContractQuickPay);
        sb.append(", statusCd=").append(statusCd);
        sb.append(", statusDate=").append(statusDate);
        sb.append(", createStaff=").append(createStaff);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateStaff=").append(updateStaff);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", commisionAgreementId=").append(commisionAgreementId);
        sb.append(", routeId=").append(routeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}