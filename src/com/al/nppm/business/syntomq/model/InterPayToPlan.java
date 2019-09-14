package com.al.nppm.business.syntomq.model;

public class InterPayToPlan
{
    private static final long serialVersionUID = 1L;
    private Long operState;
    private Long routeId;
    private Long offerInstID;
    private Long offerID;
    private Long amount;
    private Long acctID;
    private Long objectType;
    private Long objectID;
    private Long depositType;
    private Long operType;
    private String effDate;
    private String orderDate;
    private String createDate;
    private String operDate;
    private Long recount;
    private Long interPlanID;

    public Long getOperState()
    {
        return this.operState;
    }

    public void setOperState(Long operState)
    {
        this.operState = operState;
    }

    public Long getRouteId()
    {
        return this.routeId;
    }

    public void setRouteId(Long routeId)
    {
        this.routeId = routeId;
    }

    public Long getOfferInstID()
    {
        return this.offerInstID;
    }

    public void setOfferInstID(Long offerInstID)
    {
        this.offerInstID = offerInstID;
    }

    public Long getOfferID()
    {
        return this.offerID;
    }

    public void setOfferID(Long offerID)
    {
        this.offerID = offerID;
    }

    public Long getAmount()
    {
        return this.amount;
    }

    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public Long getAcctID()
    {
        return this.acctID;
    }

    public void setAcctID(Long acctID)
    {
        this.acctID = acctID;
    }

    public Long getObjectType()
    {
        return this.objectType;
    }

    public void setObjectType(Long objectType)
    {
        this.objectType = objectType;
    }

    public Long getObjectID()
    {
        return this.objectID;
    }

    public void setObjectID(Long objectID)
    {
        this.objectID = objectID;
    }

    public Long getDepositType()
    {
        return this.depositType;
    }

    public void setDepositType(Long depositType)
    {
        this.depositType = depositType;
    }

    public Long getOperType()
    {
        return this.operType;
    }

    public void setOperType(Long operType)
    {
        this.operType = operType;
    }

    public String getEffDate()
    {
        return this.effDate;
    }

    public void setEffDate(String effDate)
    {
        this.effDate = effDate;
    }

    public String getOrderDate()
    {
        return this.orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getCreateDate()
    {
        return this.createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    public String getOperDate()
    {
        return this.operDate;
    }

    public void setOperDate(String operDate)
    {
        this.operDate = operDate;
    }

    public Long getRecount()
    {
        return this.recount;
    }

    public void setRecount(Long recount)
    {
        this.recount = recount;
    }

    public Long getInterPlanID()
    {
        return this.interPlanID;
    }

    public void setInterPlanID(Long interPlanID)
    {
        this.interPlanID = interPlanID;
    }
}