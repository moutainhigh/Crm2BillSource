package generator;

import java.util.ArrayList;
import java.util.List;

public class IntfServCustChangeContrastExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public IntfServCustChangeContrastExample() {
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

        public Criteria andArchGrpIdIsNull() {
            addCriterion("ARCH_GRP_ID is null");
            return (Criteria) this;
        }

        public Criteria andArchGrpIdIsNotNull() {
            addCriterion("ARCH_GRP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andArchGrpIdEqualTo(Long value) {
            addCriterion("ARCH_GRP_ID =", value, "archGrpId");
            return (Criteria) this;
        }

        public Criteria andArchGrpIdNotEqualTo(Long value) {
            addCriterion("ARCH_GRP_ID <>", value, "archGrpId");
            return (Criteria) this;
        }

        public Criteria andArchGrpIdGreaterThan(Long value) {
            addCriterion("ARCH_GRP_ID >", value, "archGrpId");
            return (Criteria) this;
        }

        public Criteria andArchGrpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ARCH_GRP_ID >=", value, "archGrpId");
            return (Criteria) this;
        }

        public Criteria andArchGrpIdLessThan(Long value) {
            addCriterion("ARCH_GRP_ID <", value, "archGrpId");
            return (Criteria) this;
        }

        public Criteria andArchGrpIdLessThanOrEqualTo(Long value) {
            addCriterion("ARCH_GRP_ID <=", value, "archGrpId");
            return (Criteria) this;
        }

        public Criteria andArchGrpIdIn(List<Long> values) {
            addCriterion("ARCH_GRP_ID in", values, "archGrpId");
            return (Criteria) this;
        }

        public Criteria andArchGrpIdNotIn(List<Long> values) {
            addCriterion("ARCH_GRP_ID not in", values, "archGrpId");
            return (Criteria) this;
        }

        public Criteria andArchGrpIdBetween(Long value1, Long value2) {
            addCriterion("ARCH_GRP_ID between", value1, value2, "archGrpId");
            return (Criteria) this;
        }

        public Criteria andArchGrpIdNotBetween(Long value1, Long value2) {
            addCriterion("ARCH_GRP_ID not between", value1, value2, "archGrpId");
            return (Criteria) this;
        }

        public Criteria andServIdIsNull() {
            addCriterion("SERV_ID is null");
            return (Criteria) this;
        }

        public Criteria andServIdIsNotNull() {
            addCriterion("SERV_ID is not null");
            return (Criteria) this;
        }

        public Criteria andServIdEqualTo(Long value) {
            addCriterion("SERV_ID =", value, "servId");
            return (Criteria) this;
        }

        public Criteria andServIdNotEqualTo(Long value) {
            addCriterion("SERV_ID <>", value, "servId");
            return (Criteria) this;
        }

        public Criteria andServIdGreaterThan(Long value) {
            addCriterion("SERV_ID >", value, "servId");
            return (Criteria) this;
        }

        public Criteria andServIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SERV_ID >=", value, "servId");
            return (Criteria) this;
        }

        public Criteria andServIdLessThan(Long value) {
            addCriterion("SERV_ID <", value, "servId");
            return (Criteria) this;
        }

        public Criteria andServIdLessThanOrEqualTo(Long value) {
            addCriterion("SERV_ID <=", value, "servId");
            return (Criteria) this;
        }

        public Criteria andServIdIn(List<Long> values) {
            addCriterion("SERV_ID in", values, "servId");
            return (Criteria) this;
        }

        public Criteria andServIdNotIn(List<Long> values) {
            addCriterion("SERV_ID not in", values, "servId");
            return (Criteria) this;
        }

        public Criteria andServIdBetween(Long value1, Long value2) {
            addCriterion("SERV_ID between", value1, value2, "servId");
            return (Criteria) this;
        }

        public Criteria andServIdNotBetween(Long value1, Long value2) {
            addCriterion("SERV_ID not between", value1, value2, "servId");
            return (Criteria) this;
        }

        public Criteria andOldCustIdIsNull() {
            addCriterion("OLD_CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andOldCustIdIsNotNull() {
            addCriterion("OLD_CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOldCustIdEqualTo(Long value) {
            addCriterion("OLD_CUST_ID =", value, "oldCustId");
            return (Criteria) this;
        }

        public Criteria andOldCustIdNotEqualTo(Long value) {
            addCriterion("OLD_CUST_ID <>", value, "oldCustId");
            return (Criteria) this;
        }

        public Criteria andOldCustIdGreaterThan(Long value) {
            addCriterion("OLD_CUST_ID >", value, "oldCustId");
            return (Criteria) this;
        }

        public Criteria andOldCustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OLD_CUST_ID >=", value, "oldCustId");
            return (Criteria) this;
        }

        public Criteria andOldCustIdLessThan(Long value) {
            addCriterion("OLD_CUST_ID <", value, "oldCustId");
            return (Criteria) this;
        }

        public Criteria andOldCustIdLessThanOrEqualTo(Long value) {
            addCriterion("OLD_CUST_ID <=", value, "oldCustId");
            return (Criteria) this;
        }

        public Criteria andOldCustIdIn(List<Long> values) {
            addCriterion("OLD_CUST_ID in", values, "oldCustId");
            return (Criteria) this;
        }

        public Criteria andOldCustIdNotIn(List<Long> values) {
            addCriterion("OLD_CUST_ID not in", values, "oldCustId");
            return (Criteria) this;
        }

        public Criteria andOldCustIdBetween(Long value1, Long value2) {
            addCriterion("OLD_CUST_ID between", value1, value2, "oldCustId");
            return (Criteria) this;
        }

        public Criteria andOldCustIdNotBetween(Long value1, Long value2) {
            addCriterion("OLD_CUST_ID not between", value1, value2, "oldCustId");
            return (Criteria) this;
        }

        public Criteria andNewCustIdIsNull() {
            addCriterion("NEW_CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andNewCustIdIsNotNull() {
            addCriterion("NEW_CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNewCustIdEqualTo(Long value) {
            addCriterion("NEW_CUST_ID =", value, "newCustId");
            return (Criteria) this;
        }

        public Criteria andNewCustIdNotEqualTo(Long value) {
            addCriterion("NEW_CUST_ID <>", value, "newCustId");
            return (Criteria) this;
        }

        public Criteria andNewCustIdGreaterThan(Long value) {
            addCriterion("NEW_CUST_ID >", value, "newCustId");
            return (Criteria) this;
        }

        public Criteria andNewCustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("NEW_CUST_ID >=", value, "newCustId");
            return (Criteria) this;
        }

        public Criteria andNewCustIdLessThan(Long value) {
            addCriterion("NEW_CUST_ID <", value, "newCustId");
            return (Criteria) this;
        }

        public Criteria andNewCustIdLessThanOrEqualTo(Long value) {
            addCriterion("NEW_CUST_ID <=", value, "newCustId");
            return (Criteria) this;
        }

        public Criteria andNewCustIdIn(List<Long> values) {
            addCriterion("NEW_CUST_ID in", values, "newCustId");
            return (Criteria) this;
        }

        public Criteria andNewCustIdNotIn(List<Long> values) {
            addCriterion("NEW_CUST_ID not in", values, "newCustId");
            return (Criteria) this;
        }

        public Criteria andNewCustIdBetween(Long value1, Long value2) {
            addCriterion("NEW_CUST_ID between", value1, value2, "newCustId");
            return (Criteria) this;
        }

        public Criteria andNewCustIdNotBetween(Long value1, Long value2) {
            addCriterion("NEW_CUST_ID not between", value1, value2, "newCustId");
            return (Criteria) this;
        }

        public Criteria andChangeDateIsNull() {
            addCriterion("CHANGE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andChangeDateIsNotNull() {
            addCriterion("CHANGE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andChangeDateEqualTo(String value) {
            addCriterion("CHANGE_DATE =", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateNotEqualTo(String value) {
            addCriterion("CHANGE_DATE <>", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateGreaterThan(String value) {
            addCriterion("CHANGE_DATE >", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_DATE >=", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateLessThan(String value) {
            addCriterion("CHANGE_DATE <", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_DATE <=", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateLike(String value) {
            addCriterion("CHANGE_DATE like", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateNotLike(String value) {
            addCriterion("CHANGE_DATE not like", value, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateIn(List<String> values) {
            addCriterion("CHANGE_DATE in", values, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateNotIn(List<String> values) {
            addCriterion("CHANGE_DATE not in", values, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateBetween(String value1, String value2) {
            addCriterion("CHANGE_DATE between", value1, value2, "changeDate");
            return (Criteria) this;
        }

        public Criteria andChangeDateNotBetween(String value1, String value2) {
            addCriterion("CHANGE_DATE not between", value1, value2, "changeDate");
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