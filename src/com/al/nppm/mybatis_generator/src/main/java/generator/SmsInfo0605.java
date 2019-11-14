package generator;

import java.io.Serializable;
import java.util.Date;

/**
 * SMS_INFO_0605
 * @author 
 */
public class SmsInfo0605 implements Serializable {
    private Long id;

    private String msisdn;

    private String flag;

    private String msg;

    private String sts;

    private Date getDate;

    private String para1;

    private String para2;

    private String para3;

    private Date sendDate;

    private String msgId;

    private Integer pri;

    private Short accId;

    private String recv;

    private Date saveHisDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public Date getGetDate() {
        return getDate;
    }

    public void setGetDate(Date getDate) {
        this.getDate = getDate;
    }

    public String getPara1() {
        return para1;
    }

    public void setPara1(String para1) {
        this.para1 = para1;
    }

    public String getPara2() {
        return para2;
    }

    public void setPara2(String para2) {
        this.para2 = para2;
    }

    public String getPara3() {
        return para3;
    }

    public void setPara3(String para3) {
        this.para3 = para3;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Integer getPri() {
        return pri;
    }

    public void setPri(Integer pri) {
        this.pri = pri;
    }

    public Short getAccId() {
        return accId;
    }

    public void setAccId(Short accId) {
        this.accId = accId;
    }

    public String getRecv() {
        return recv;
    }

    public void setRecv(String recv) {
        this.recv = recv;
    }

    public Date getSaveHisDate() {
        return saveHisDate;
    }

    public void setSaveHisDate(Date saveHisDate) {
        this.saveHisDate = saveHisDate;
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
        SmsInfo0605 other = (SmsInfo0605) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMsisdn() == null ? other.getMsisdn() == null : this.getMsisdn().equals(other.getMsisdn()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
            && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()))
            && (this.getSts() == null ? other.getSts() == null : this.getSts().equals(other.getSts()))
            && (this.getGetDate() == null ? other.getGetDate() == null : this.getGetDate().equals(other.getGetDate()))
            && (this.getPara1() == null ? other.getPara1() == null : this.getPara1().equals(other.getPara1()))
            && (this.getPara2() == null ? other.getPara2() == null : this.getPara2().equals(other.getPara2()))
            && (this.getPara3() == null ? other.getPara3() == null : this.getPara3().equals(other.getPara3()))
            && (this.getSendDate() == null ? other.getSendDate() == null : this.getSendDate().equals(other.getSendDate()))
            && (this.getMsgId() == null ? other.getMsgId() == null : this.getMsgId().equals(other.getMsgId()))
            && (this.getPri() == null ? other.getPri() == null : this.getPri().equals(other.getPri()))
            && (this.getAccId() == null ? other.getAccId() == null : this.getAccId().equals(other.getAccId()))
            && (this.getRecv() == null ? other.getRecv() == null : this.getRecv().equals(other.getRecv()))
            && (this.getSaveHisDate() == null ? other.getSaveHisDate() == null : this.getSaveHisDate().equals(other.getSaveHisDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMsisdn() == null) ? 0 : getMsisdn().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        result = prime * result + ((getSts() == null) ? 0 : getSts().hashCode());
        result = prime * result + ((getGetDate() == null) ? 0 : getGetDate().hashCode());
        result = prime * result + ((getPara1() == null) ? 0 : getPara1().hashCode());
        result = prime * result + ((getPara2() == null) ? 0 : getPara2().hashCode());
        result = prime * result + ((getPara3() == null) ? 0 : getPara3().hashCode());
        result = prime * result + ((getSendDate() == null) ? 0 : getSendDate().hashCode());
        result = prime * result + ((getMsgId() == null) ? 0 : getMsgId().hashCode());
        result = prime * result + ((getPri() == null) ? 0 : getPri().hashCode());
        result = prime * result + ((getAccId() == null) ? 0 : getAccId().hashCode());
        result = prime * result + ((getRecv() == null) ? 0 : getRecv().hashCode());
        result = prime * result + ((getSaveHisDate() == null) ? 0 : getSaveHisDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", msisdn=").append(msisdn);
        sb.append(", flag=").append(flag);
        sb.append(", msg=").append(msg);
        sb.append(", sts=").append(sts);
        sb.append(", getDate=").append(getDate);
        sb.append(", para1=").append(para1);
        sb.append(", para2=").append(para2);
        sb.append(", para3=").append(para3);
        sb.append(", sendDate=").append(sendDate);
        sb.append(", msgId=").append(msgId);
        sb.append(", pri=").append(pri);
        sb.append(", accId=").append(accId);
        sb.append(", recv=").append(recv);
        sb.append(", saveHisDate=").append(saveHisDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}