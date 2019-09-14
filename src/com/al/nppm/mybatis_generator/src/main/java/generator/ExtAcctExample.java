package generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExtAcctExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ExtAcctExample() {
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

        public Criteria andExtAcctIdIsNull() {
            addCriterion("EXT_ACCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andExtAcctIdIsNotNull() {
            addCriterion("EXT_ACCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExtAcctIdEqualTo(Long value) {
            addCriterion("EXT_ACCT_ID =", value, "extAcctId");
            return (Criteria) this;
        }

        public Criteria andExtAcctIdNotEqualTo(Long value) {
            addCriterion("EXT_ACCT_ID <>", value, "extAcctId");
            return (Criteria) this;
        }

        public Criteria andExtAcctIdGreaterThan(Long value) {
            addCriterion("EXT_ACCT_ID >", value, "extAcctId");
            return (Criteria) this;
        }

        public Criteria andExtAcctIdGreaterThanOrEqualTo(Long value) {
            addCriterion("EXT_ACCT_ID >=", value, "extAcctId");
            return (Criteria) this;
        }

        public Criteria andExtAcctIdLessThan(Long value) {
            addCriterion("EXT_ACCT_ID <", value, "extAcctId");
            return (Criteria) this;
        }

        public Criteria andExtAcctIdLessThanOrEqualTo(Long value) {
            addCriterion("EXT_ACCT_ID <=", value, "extAcctId");
            return (Criteria) this;
        }

        public Criteria andExtAcctIdIn(List<Long> values) {
            addCriterion("EXT_ACCT_ID in", values, "extAcctId");
            return (Criteria) this;
        }

        public Criteria andExtAcctIdNotIn(List<Long> values) {
            addCriterion("EXT_ACCT_ID not in", values, "extAcctId");
            return (Criteria) this;
        }

        public Criteria andExtAcctIdBetween(Long value1, Long value2) {
            addCriterion("EXT_ACCT_ID between", value1, value2, "extAcctId");
            return (Criteria) this;
        }

        public Criteria andExtAcctIdNotBetween(Long value1, Long value2) {
            addCriterion("EXT_ACCT_ID not between", value1, value2, "extAcctId");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNull() {
            addCriterion("CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNotNull() {
            addCriterion("CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdEqualTo(Long value) {
            addCriterion("CUST_ID =", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotEqualTo(Long value) {
            addCriterion("CUST_ID <>", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThan(Long value) {
            addCriterion("CUST_ID >", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CUST_ID >=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThan(Long value) {
            addCriterion("CUST_ID <", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThanOrEqualTo(Long value) {
            addCriterion("CUST_ID <=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(List<Long> values) {
            addCriterion("CUST_ID in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(List<Long> values) {
            addCriterion("CUST_ID not in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdBetween(Long value1, Long value2) {
            addCriterion("CUST_ID between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotBetween(Long value1, Long value2) {
            addCriterion("CUST_ID not between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNull() {
            addCriterion("PAY_CHANNEL is null");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNotNull() {
            addCriterion("PAY_CHANNEL is not null");
            return (Criteria) this;
        }

        public Criteria andPayChannelEqualTo(Integer value) {
            addCriterion("PAY_CHANNEL =", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotEqualTo(Integer value) {
            addCriterion("PAY_CHANNEL <>", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThan(Integer value) {
            addCriterion("PAY_CHANNEL >", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThanOrEqualTo(Integer value) {
            addCriterion("PAY_CHANNEL >=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThan(Integer value) {
            addCriterion("PAY_CHANNEL <", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThanOrEqualTo(Integer value) {
            addCriterion("PAY_CHANNEL <=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelIn(List<Integer> values) {
            addCriterion("PAY_CHANNEL in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotIn(List<Integer> values) {
            addCriterion("PAY_CHANNEL not in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelBetween(Integer value1, Integer value2) {
            addCriterion("PAY_CHANNEL between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotBetween(Integer value1, Integer value2) {
            addCriterion("PAY_CHANNEL not between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeIsNull() {
            addCriterion("PAY_ACCT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeIsNotNull() {
            addCriterion("PAY_ACCT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeEqualTo(String value) {
            addCriterion("PAY_ACCT_CODE =", value, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeNotEqualTo(String value) {
            addCriterion("PAY_ACCT_CODE <>", value, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeGreaterThan(String value) {
            addCriterion("PAY_ACCT_CODE >", value, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_ACCT_CODE >=", value, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeLessThan(String value) {
            addCriterion("PAY_ACCT_CODE <", value, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeLessThanOrEqualTo(String value) {
            addCriterion("PAY_ACCT_CODE <=", value, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeLike(String value) {
            addCriterion("PAY_ACCT_CODE like", value, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeNotLike(String value) {
            addCriterion("PAY_ACCT_CODE not like", value, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeIn(List<String> values) {
            addCriterion("PAY_ACCT_CODE in", values, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeNotIn(List<String> values) {
            addCriterion("PAY_ACCT_CODE not in", values, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeBetween(String value1, String value2) {
            addCriterion("PAY_ACCT_CODE between", value1, value2, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctCodeNotBetween(String value1, String value2) {
            addCriterion("PAY_ACCT_CODE not between", value1, value2, "payAcctCode");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameIsNull() {
            addCriterion("PAY_ACCT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameIsNotNull() {
            addCriterion("PAY_ACCT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameEqualTo(String value) {
            addCriterion("PAY_ACCT_NAME =", value, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameNotEqualTo(String value) {
            addCriterion("PAY_ACCT_NAME <>", value, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameGreaterThan(String value) {
            addCriterion("PAY_ACCT_NAME >", value, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_ACCT_NAME >=", value, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameLessThan(String value) {
            addCriterion("PAY_ACCT_NAME <", value, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameLessThanOrEqualTo(String value) {
            addCriterion("PAY_ACCT_NAME <=", value, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameLike(String value) {
            addCriterion("PAY_ACCT_NAME like", value, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameNotLike(String value) {
            addCriterion("PAY_ACCT_NAME not like", value, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameIn(List<String> values) {
            addCriterion("PAY_ACCT_NAME in", values, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameNotIn(List<String> values) {
            addCriterion("PAY_ACCT_NAME not in", values, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameBetween(String value1, String value2) {
            addCriterion("PAY_ACCT_NAME between", value1, value2, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andPayAcctNameNotBetween(String value1, String value2) {
            addCriterion("PAY_ACCT_NAME not between", value1, value2, "payAcctName");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgIsNull() {
            addCriterion("ACCT_OWNER_ORG is null");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgIsNotNull() {
            addCriterion("ACCT_OWNER_ORG is not null");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgEqualTo(Long value) {
            addCriterion("ACCT_OWNER_ORG =", value, "acctOwnerOrg");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgNotEqualTo(Long value) {
            addCriterion("ACCT_OWNER_ORG <>", value, "acctOwnerOrg");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgGreaterThan(Long value) {
            addCriterion("ACCT_OWNER_ORG >", value, "acctOwnerOrg");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgGreaterThanOrEqualTo(Long value) {
            addCriterion("ACCT_OWNER_ORG >=", value, "acctOwnerOrg");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgLessThan(Long value) {
            addCriterion("ACCT_OWNER_ORG <", value, "acctOwnerOrg");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgLessThanOrEqualTo(Long value) {
            addCriterion("ACCT_OWNER_ORG <=", value, "acctOwnerOrg");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgIn(List<Long> values) {
            addCriterion("ACCT_OWNER_ORG in", values, "acctOwnerOrg");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgNotIn(List<Long> values) {
            addCriterion("ACCT_OWNER_ORG not in", values, "acctOwnerOrg");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBetween(Long value1, Long value2) {
            addCriterion("ACCT_OWNER_ORG between", value1, value2, "acctOwnerOrg");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgNotBetween(Long value1, Long value2) {
            addCriterion("ACCT_OWNER_ORG not between", value1, value2, "acctOwnerOrg");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchIsNull() {
            addCriterion("ACCT_OWNER_ORG_BRANCH is null");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchIsNotNull() {
            addCriterion("ACCT_OWNER_ORG_BRANCH is not null");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchEqualTo(Long value) {
            addCriterion("ACCT_OWNER_ORG_BRANCH =", value, "acctOwnerOrgBranch");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchNotEqualTo(Long value) {
            addCriterion("ACCT_OWNER_ORG_BRANCH <>", value, "acctOwnerOrgBranch");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchGreaterThan(Long value) {
            addCriterion("ACCT_OWNER_ORG_BRANCH >", value, "acctOwnerOrgBranch");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchGreaterThanOrEqualTo(Long value) {
            addCriterion("ACCT_OWNER_ORG_BRANCH >=", value, "acctOwnerOrgBranch");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchLessThan(Long value) {
            addCriterion("ACCT_OWNER_ORG_BRANCH <", value, "acctOwnerOrgBranch");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchLessThanOrEqualTo(Long value) {
            addCriterion("ACCT_OWNER_ORG_BRANCH <=", value, "acctOwnerOrgBranch");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchIn(List<Long> values) {
            addCriterion("ACCT_OWNER_ORG_BRANCH in", values, "acctOwnerOrgBranch");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchNotIn(List<Long> values) {
            addCriterion("ACCT_OWNER_ORG_BRANCH not in", values, "acctOwnerOrgBranch");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchBetween(Long value1, Long value2) {
            addCriterion("ACCT_OWNER_ORG_BRANCH between", value1, value2, "acctOwnerOrgBranch");
            return (Criteria) this;
        }

        public Criteria andAcctOwnerOrgBranchNotBetween(Long value1, Long value2) {
            addCriterion("ACCT_OWNER_ORG_BRANCH not between", value1, value2, "acctOwnerOrgBranch");
            return (Criteria) this;
        }

        public Criteria andEffDateIsNull() {
            addCriterion("EFF_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEffDateIsNotNull() {
            addCriterion("EFF_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEffDateEqualTo(Date value) {
            addCriterion("EFF_DATE =", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotEqualTo(Date value) {
            addCriterion("EFF_DATE <>", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateGreaterThan(Date value) {
            addCriterion("EFF_DATE >", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateGreaterThanOrEqualTo(Date value) {
            addCriterion("EFF_DATE >=", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateLessThan(Date value) {
            addCriterion("EFF_DATE <", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateLessThanOrEqualTo(Date value) {
            addCriterion("EFF_DATE <=", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateIn(List<Date> values) {
            addCriterion("EFF_DATE in", values, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotIn(List<Date> values) {
            addCriterion("EFF_DATE not in", values, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateBetween(Date value1, Date value2) {
            addCriterion("EFF_DATE between", value1, value2, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotBetween(Date value1, Date value2) {
            addCriterion("EFF_DATE not between", value1, value2, "effDate");
            return (Criteria) this;
        }

        public Criteria andExpDateIsNull() {
            addCriterion("EXP_DATE is null");
            return (Criteria) this;
        }

        public Criteria andExpDateIsNotNull() {
            addCriterion("EXP_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andExpDateEqualTo(Date value) {
            addCriterion("EXP_DATE =", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotEqualTo(Date value) {
            addCriterion("EXP_DATE <>", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateGreaterThan(Date value) {
            addCriterion("EXP_DATE >", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateGreaterThanOrEqualTo(Date value) {
            addCriterion("EXP_DATE >=", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateLessThan(Date value) {
            addCriterion("EXP_DATE <", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateLessThanOrEqualTo(Date value) {
            addCriterion("EXP_DATE <=", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateIn(List<Date> values) {
            addCriterion("EXP_DATE in", values, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotIn(List<Date> values) {
            addCriterion("EXP_DATE not in", values, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateBetween(Date value1, Date value2) {
            addCriterion("EXP_DATE between", value1, value2, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotBetween(Date value1, Date value2) {
            addCriterion("EXP_DATE not between", value1, value2, "expDate");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayIsNull() {
            addCriterion("IF_CONTRACT_QUICK_PAY is null");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayIsNotNull() {
            addCriterion("IF_CONTRACT_QUICK_PAY is not null");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayEqualTo(Integer value) {
            addCriterion("IF_CONTRACT_QUICK_PAY =", value, "ifContractQuickPay");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayNotEqualTo(Integer value) {
            addCriterion("IF_CONTRACT_QUICK_PAY <>", value, "ifContractQuickPay");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayGreaterThan(Integer value) {
            addCriterion("IF_CONTRACT_QUICK_PAY >", value, "ifContractQuickPay");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayGreaterThanOrEqualTo(Integer value) {
            addCriterion("IF_CONTRACT_QUICK_PAY >=", value, "ifContractQuickPay");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayLessThan(Integer value) {
            addCriterion("IF_CONTRACT_QUICK_PAY <", value, "ifContractQuickPay");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayLessThanOrEqualTo(Integer value) {
            addCriterion("IF_CONTRACT_QUICK_PAY <=", value, "ifContractQuickPay");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayIn(List<Integer> values) {
            addCriterion("IF_CONTRACT_QUICK_PAY in", values, "ifContractQuickPay");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayNotIn(List<Integer> values) {
            addCriterion("IF_CONTRACT_QUICK_PAY not in", values, "ifContractQuickPay");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayBetween(Integer value1, Integer value2) {
            addCriterion("IF_CONTRACT_QUICK_PAY between", value1, value2, "ifContractQuickPay");
            return (Criteria) this;
        }

        public Criteria andIfContractQuickPayNotBetween(Integer value1, Integer value2) {
            addCriterion("IF_CONTRACT_QUICK_PAY not between", value1, value2, "ifContractQuickPay");
            return (Criteria) this;
        }

        public Criteria andStatusCdIsNull() {
            addCriterion("STATUS_CD is null");
            return (Criteria) this;
        }

        public Criteria andStatusCdIsNotNull() {
            addCriterion("STATUS_CD is not null");
            return (Criteria) this;
        }

        public Criteria andStatusCdEqualTo(String value) {
            addCriterion("STATUS_CD =", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotEqualTo(String value) {
            addCriterion("STATUS_CD <>", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdGreaterThan(String value) {
            addCriterion("STATUS_CD >", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_CD >=", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdLessThan(String value) {
            addCriterion("STATUS_CD <", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdLessThanOrEqualTo(String value) {
            addCriterion("STATUS_CD <=", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdLike(String value) {
            addCriterion("STATUS_CD like", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotLike(String value) {
            addCriterion("STATUS_CD not like", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdIn(List<String> values) {
            addCriterion("STATUS_CD in", values, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotIn(List<String> values) {
            addCriterion("STATUS_CD not in", values, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdBetween(String value1, String value2) {
            addCriterion("STATUS_CD between", value1, value2, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotBetween(String value1, String value2) {
            addCriterion("STATUS_CD not between", value1, value2, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusDateIsNull() {
            addCriterion("STATUS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStatusDateIsNotNull() {
            addCriterion("STATUS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStatusDateEqualTo(Date value) {
            addCriterion("STATUS_DATE =", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotEqualTo(Date value) {
            addCriterion("STATUS_DATE <>", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThan(Date value) {
            addCriterion("STATUS_DATE >", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateGreaterThanOrEqualTo(Date value) {
            addCriterion("STATUS_DATE >=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThan(Date value) {
            addCriterion("STATUS_DATE <", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateLessThanOrEqualTo(Date value) {
            addCriterion("STATUS_DATE <=", value, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateIn(List<Date> values) {
            addCriterion("STATUS_DATE in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotIn(List<Date> values) {
            addCriterion("STATUS_DATE not in", values, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateBetween(Date value1, Date value2) {
            addCriterion("STATUS_DATE between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andStatusDateNotBetween(Date value1, Date value2) {
            addCriterion("STATUS_DATE not between", value1, value2, "statusDate");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIsNull() {
            addCriterion("CREATE_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIsNotNull() {
            addCriterion("CREATE_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffEqualTo(Long value) {
            addCriterion("CREATE_STAFF =", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotEqualTo(Long value) {
            addCriterion("CREATE_STAFF <>", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThan(Long value) {
            addCriterion("CREATE_STAFF >", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_STAFF >=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThan(Long value) {
            addCriterion("CREATE_STAFF <", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_STAFF <=", value, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIn(List<Long> values) {
            addCriterion("CREATE_STAFF in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotIn(List<Long> values) {
            addCriterion("CREATE_STAFF not in", values, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffBetween(Long value1, Long value2) {
            addCriterion("CREATE_STAFF between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateStaffNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_STAFF not between", value1, value2, "createStaff");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIsNull() {
            addCriterion("UPDATE_STAFF is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIsNotNull() {
            addCriterion("UPDATE_STAFF is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffEqualTo(Long value) {
            addCriterion("UPDATE_STAFF =", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotEqualTo(Long value) {
            addCriterion("UPDATE_STAFF <>", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThan(Long value) {
            addCriterion("UPDATE_STAFF >", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffGreaterThanOrEqualTo(Long value) {
            addCriterion("UPDATE_STAFF >=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThan(Long value) {
            addCriterion("UPDATE_STAFF <", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffLessThanOrEqualTo(Long value) {
            addCriterion("UPDATE_STAFF <=", value, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIn(List<Long> values) {
            addCriterion("UPDATE_STAFF in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotIn(List<Long> values) {
            addCriterion("UPDATE_STAFF not in", values, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffBetween(Long value1, Long value2) {
            addCriterion("UPDATE_STAFF between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffNotBetween(Long value1, Long value2) {
            addCriterion("UPDATE_STAFF not between", value1, value2, "updateStaff");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdIsNull() {
            addCriterion("commision_agreement_id is null");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdIsNotNull() {
            addCriterion("commision_agreement_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdEqualTo(String value) {
            addCriterion("commision_agreement_id =", value, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdNotEqualTo(String value) {
            addCriterion("commision_agreement_id <>", value, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdGreaterThan(String value) {
            addCriterion("commision_agreement_id >", value, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdGreaterThanOrEqualTo(String value) {
            addCriterion("commision_agreement_id >=", value, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdLessThan(String value) {
            addCriterion("commision_agreement_id <", value, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdLessThanOrEqualTo(String value) {
            addCriterion("commision_agreement_id <=", value, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdLike(String value) {
            addCriterion("commision_agreement_id like", value, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdNotLike(String value) {
            addCriterion("commision_agreement_id not like", value, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdIn(List<String> values) {
            addCriterion("commision_agreement_id in", values, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdNotIn(List<String> values) {
            addCriterion("commision_agreement_id not in", values, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdBetween(String value1, String value2) {
            addCriterion("commision_agreement_id between", value1, value2, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andCommisionAgreementIdNotBetween(String value1, String value2) {
            addCriterion("commision_agreement_id not between", value1, value2, "commisionAgreementId");
            return (Criteria) this;
        }

        public Criteria andRouteIdIsNull() {
            addCriterion("ROUTE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRouteIdIsNotNull() {
            addCriterion("ROUTE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRouteIdEqualTo(Long value) {
            addCriterion("ROUTE_ID =", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotEqualTo(Long value) {
            addCriterion("ROUTE_ID <>", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdGreaterThan(Long value) {
            addCriterion("ROUTE_ID >", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ROUTE_ID >=", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdLessThan(Long value) {
            addCriterion("ROUTE_ID <", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdLessThanOrEqualTo(Long value) {
            addCriterion("ROUTE_ID <=", value, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdIn(List<Long> values) {
            addCriterion("ROUTE_ID in", values, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotIn(List<Long> values) {
            addCriterion("ROUTE_ID not in", values, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdBetween(Long value1, Long value2) {
            addCriterion("ROUTE_ID between", value1, value2, "routeId");
            return (Criteria) this;
        }

        public Criteria andRouteIdNotBetween(Long value1, Long value2) {
            addCriterion("ROUTE_ID not between", value1, value2, "routeId");
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