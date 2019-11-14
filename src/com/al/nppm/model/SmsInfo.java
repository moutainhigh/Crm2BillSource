package com.al.nppm.model;

import java.io.Serializable;
import java.util.Date;

/**
 * SMS_INFO_0605
 * @author 
 */
public class SmsInfo implements Serializable {
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
}