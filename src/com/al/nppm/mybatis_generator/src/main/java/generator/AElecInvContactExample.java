package generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AElecInvContactExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public AElecInvContactExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andContactIdIsNull() {
            addCriterion("contact_id is null");
            return (Criteria) this;
        }

        public Criteria andContactIdIsNotNull() {
            addCriterion("contact_id is not null");
            return (Criteria) this;
        }

        public Criteria andContactIdEqualTo(Integer value) {
            addCriterion("contact_id =", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdNotEqualTo(Integer value) {
            addCriterion("contact_id <>", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdGreaterThan(Integer value) {
            addCriterion("contact_id >", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("contact_id >=", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdLessThan(Integer value) {
            addCriterion("contact_id <", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdLessThanOrEqualTo(Integer value) {
            addCriterion("contact_id <=", value, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdIn(List<Integer> values) {
            addCriterion("contact_id in", values, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdNotIn(List<Integer> values) {
            addCriterion("contact_id not in", values, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdBetween(Integer value1, Integer value2) {
            addCriterion("contact_id between", value1, value2, "contactId");
            return (Criteria) this;
        }

        public Criteria andContactIdNotBetween(Integer value1, Integer value2) {
            addCriterion("contact_id not between", value1, value2, "contactId");
            return (Criteria) this;
        }

        public Criteria andAcctIdIsNull() {
            addCriterion("acct_id is null");
            return (Criteria) this;
        }

        public Criteria andAcctIdIsNotNull() {
            addCriterion("acct_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcctIdEqualTo(Long value) {
            addCriterion("acct_id =", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotEqualTo(Long value) {
            addCriterion("acct_id <>", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThan(Long value) {
            addCriterion("acct_id >", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThanOrEqualTo(Long value) {
            addCriterion("acct_id >=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThan(Long value) {
            addCriterion("acct_id <", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThanOrEqualTo(Long value) {
            addCriterion("acct_id <=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdIn(List<Long> values) {
            addCriterion("acct_id in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotIn(List<Long> values) {
            addCriterion("acct_id not in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdBetween(Long value1, Long value2) {
            addCriterion("acct_id between", value1, value2, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotBetween(Long value1, Long value2) {
            addCriterion("acct_id not between", value1, value2, "acctId");
            return (Criteria) this;
        }

        public Criteria andAccNbrIsNull() {
            addCriterion("acc_nbr is null");
            return (Criteria) this;
        }

        public Criteria andAccNbrIsNotNull() {
            addCriterion("acc_nbr is not null");
            return (Criteria) this;
        }

        public Criteria andAccNbrEqualTo(String value) {
            addCriterion("acc_nbr =", value, "accNbr");
            return (Criteria) this;
        }

        public Criteria andAccNbrNotEqualTo(String value) {
            addCriterion("acc_nbr <>", value, "accNbr");
            return (Criteria) this;
        }

        public Criteria andAccNbrGreaterThan(String value) {
            addCriterion("acc_nbr >", value, "accNbr");
            return (Criteria) this;
        }

        public Criteria andAccNbrGreaterThanOrEqualTo(String value) {
            addCriterion("acc_nbr >=", value, "accNbr");
            return (Criteria) this;
        }

        public Criteria andAccNbrLessThan(String value) {
            addCriterion("acc_nbr <", value, "accNbr");
            return (Criteria) this;
        }

        public Criteria andAccNbrLessThanOrEqualTo(String value) {
            addCriterion("acc_nbr <=", value, "accNbr");
            return (Criteria) this;
        }

        public Criteria andAccNbrLike(String value) {
            addCriterion("acc_nbr like", value, "accNbr");
            return (Criteria) this;
        }

        public Criteria andAccNbrNotLike(String value) {
            addCriterion("acc_nbr not like", value, "accNbr");
            return (Criteria) this;
        }

        public Criteria andAccNbrIn(List<String> values) {
            addCriterion("acc_nbr in", values, "accNbr");
            return (Criteria) this;
        }

        public Criteria andAccNbrNotIn(List<String> values) {
            addCriterion("acc_nbr not in", values, "accNbr");
            return (Criteria) this;
        }

        public Criteria andAccNbrBetween(String value1, String value2) {
            addCriterion("acc_nbr between", value1, value2, "accNbr");
            return (Criteria) this;
        }

        public Criteria andAccNbrNotBetween(String value1, String value2) {
            addCriterion("acc_nbr not between", value1, value2, "accNbr");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("`state` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("`state` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("`state` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("`state` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("`state` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("`state` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("`state` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("`state` like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("`state` not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("`state` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("`state` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("`state` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("`state` not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateDateIsNull() {
            addCriterion("state_date is null");
            return (Criteria) this;
        }

        public Criteria andStateDateIsNotNull() {
            addCriterion("state_date is not null");
            return (Criteria) this;
        }

        public Criteria andStateDateEqualTo(Date value) {
            addCriterion("state_date =", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateNotEqualTo(Date value) {
            addCriterion("state_date <>", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateGreaterThan(Date value) {
            addCriterion("state_date >", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("state_date >=", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateLessThan(Date value) {
            addCriterion("state_date <", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateLessThanOrEqualTo(Date value) {
            addCriterion("state_date <=", value, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateIn(List<Date> values) {
            addCriterion("state_date in", values, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateNotIn(List<Date> values) {
            addCriterion("state_date not in", values, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateBetween(Date value1, Date value2) {
            addCriterion("state_date between", value1, value2, "stateDate");
            return (Criteria) this;
        }

        public Criteria andStateDateNotBetween(Date value1, Date value2) {
            addCriterion("state_date not between", value1, value2, "stateDate");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrIsNull() {
            addCriterion("tax_payer_nbr is null");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrIsNotNull() {
            addCriterion("tax_payer_nbr is not null");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrEqualTo(String value) {
            addCriterion("tax_payer_nbr =", value, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrNotEqualTo(String value) {
            addCriterion("tax_payer_nbr <>", value, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrGreaterThan(String value) {
            addCriterion("tax_payer_nbr >", value, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrGreaterThanOrEqualTo(String value) {
            addCriterion("tax_payer_nbr >=", value, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrLessThan(String value) {
            addCriterion("tax_payer_nbr <", value, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrLessThanOrEqualTo(String value) {
            addCriterion("tax_payer_nbr <=", value, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrLike(String value) {
            addCriterion("tax_payer_nbr like", value, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrNotLike(String value) {
            addCriterion("tax_payer_nbr not like", value, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrIn(List<String> values) {
            addCriterion("tax_payer_nbr in", values, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrNotIn(List<String> values) {
            addCriterion("tax_payer_nbr not in", values, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrBetween(String value1, String value2) {
            addCriterion("tax_payer_nbr between", value1, value2, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNbrNotBetween(String value1, String value2) {
            addCriterion("tax_payer_nbr not between", value1, value2, "taxPayerNbr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameIsNull() {
            addCriterion("tax_payer_name is null");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameIsNotNull() {
            addCriterion("tax_payer_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameEqualTo(String value) {
            addCriterion("tax_payer_name =", value, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameNotEqualTo(String value) {
            addCriterion("tax_payer_name <>", value, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameGreaterThan(String value) {
            addCriterion("tax_payer_name >", value, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameGreaterThanOrEqualTo(String value) {
            addCriterion("tax_payer_name >=", value, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameLessThan(String value) {
            addCriterion("tax_payer_name <", value, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameLessThanOrEqualTo(String value) {
            addCriterion("tax_payer_name <=", value, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameLike(String value) {
            addCriterion("tax_payer_name like", value, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameNotLike(String value) {
            addCriterion("tax_payer_name not like", value, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameIn(List<String> values) {
            addCriterion("tax_payer_name in", values, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameNotIn(List<String> values) {
            addCriterion("tax_payer_name not in", values, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameBetween(String value1, String value2) {
            addCriterion("tax_payer_name between", value1, value2, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerNameNotBetween(String value1, String value2) {
            addCriterion("tax_payer_name not between", value1, value2, "taxPayerName");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrIsNull() {
            addCriterion("tax_payer_addr is null");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrIsNotNull() {
            addCriterion("tax_payer_addr is not null");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrEqualTo(String value) {
            addCriterion("tax_payer_addr =", value, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrNotEqualTo(String value) {
            addCriterion("tax_payer_addr <>", value, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrGreaterThan(String value) {
            addCriterion("tax_payer_addr >", value, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrGreaterThanOrEqualTo(String value) {
            addCriterion("tax_payer_addr >=", value, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrLessThan(String value) {
            addCriterion("tax_payer_addr <", value, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrLessThanOrEqualTo(String value) {
            addCriterion("tax_payer_addr <=", value, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrLike(String value) {
            addCriterion("tax_payer_addr like", value, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrNotLike(String value) {
            addCriterion("tax_payer_addr not like", value, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrIn(List<String> values) {
            addCriterion("tax_payer_addr in", values, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrNotIn(List<String> values) {
            addCriterion("tax_payer_addr not in", values, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrBetween(String value1, String value2) {
            addCriterion("tax_payer_addr between", value1, value2, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerAddrNotBetween(String value1, String value2) {
            addCriterion("tax_payer_addr not between", value1, value2, "taxPayerAddr");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankIsNull() {
            addCriterion("tax_payer_bank is null");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankIsNotNull() {
            addCriterion("tax_payer_bank is not null");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankEqualTo(String value) {
            addCriterion("tax_payer_bank =", value, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankNotEqualTo(String value) {
            addCriterion("tax_payer_bank <>", value, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankGreaterThan(String value) {
            addCriterion("tax_payer_bank >", value, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankGreaterThanOrEqualTo(String value) {
            addCriterion("tax_payer_bank >=", value, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankLessThan(String value) {
            addCriterion("tax_payer_bank <", value, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankLessThanOrEqualTo(String value) {
            addCriterion("tax_payer_bank <=", value, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankLike(String value) {
            addCriterion("tax_payer_bank like", value, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankNotLike(String value) {
            addCriterion("tax_payer_bank not like", value, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankIn(List<String> values) {
            addCriterion("tax_payer_bank in", values, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankNotIn(List<String> values) {
            addCriterion("tax_payer_bank not in", values, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankBetween(String value1, String value2) {
            addCriterion("tax_payer_bank between", value1, value2, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andTaxPayerBankNotBetween(String value1, String value2) {
            addCriterion("tax_payer_bank not between", value1, value2, "taxPayerBank");
            return (Criteria) this;
        }

        public Criteria andSendFlagIsNull() {
            addCriterion("send_flag is null");
            return (Criteria) this;
        }

        public Criteria andSendFlagIsNotNull() {
            addCriterion("send_flag is not null");
            return (Criteria) this;
        }

        public Criteria andSendFlagEqualTo(Integer value) {
            addCriterion("send_flag =", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotEqualTo(Integer value) {
            addCriterion("send_flag <>", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagGreaterThan(Integer value) {
            addCriterion("send_flag >", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("send_flag >=", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagLessThan(Integer value) {
            addCriterion("send_flag <", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagLessThanOrEqualTo(Integer value) {
            addCriterion("send_flag <=", value, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagIn(List<Integer> values) {
            addCriterion("send_flag in", values, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotIn(List<Integer> values) {
            addCriterion("send_flag not in", values, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagBetween(Integer value1, Integer value2) {
            addCriterion("send_flag between", value1, value2, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andSendFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("send_flag not between", value1, value2, "sendFlag");
            return (Criteria) this;
        }

        public Criteria andRouteIdIsNull() {
            addCriterion("route_id is null");
            return (Criteria) this;
        }

        public Criteria andRouteIdIsNotNull() {
            addCriterion("route_id is not null");
            return (Criteria) this;
        }

        public Criteria andRouteIdEqualTo(Long value) {
            addCriterion("route_id =", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotEqualTo(Long value) {
            addCriterion("route_id <>", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdGreaterThan(Long value) {
            addCriterion("route_id >", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("route_id >=", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdLessThan(Long value) {
            addCriterion("route_id <", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdLessThanOrEqualTo(Long value) {
            addCriterion("route_id <=", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdIn(List<Long> values) {
            addCriterion("route_id in", values, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotIn(List<Long> values) {
            addCriterion("route_id not in", values, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdBetween(Long value1, Long value2) {
            addCriterion("route_id between", value1, value2, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotBetween(Long value1, Long value2) {
            addCriterion("route_id not between", value1, value2, "routeId");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}