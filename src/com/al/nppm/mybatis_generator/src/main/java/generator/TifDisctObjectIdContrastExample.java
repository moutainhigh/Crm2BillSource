package generator;

import java.util.ArrayList;
import java.util.List;

public class TifDisctObjectIdContrastExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public TifDisctObjectIdContrastExample() {
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

        public Criteria andDisctId97IsNull() {
            addCriterion("DISCT_ID_97 is null");
            return (Criteria) this;
        }

        public Criteria andDisctId97IsNotNull() {
            addCriterion("DISCT_ID_97 is not null");
            return (Criteria) this;
        }

        public Criteria andDisctId97EqualTo(Long value) {
            addCriterion("DISCT_ID_97 =", value, "disctId97");
            return (Criteria) this;
        }

        public Criteria andDisctId97NotEqualTo(Long value) {
            addCriterion("DISCT_ID_97 <>", value, "disctId97");
            return (Criteria) this;
        }

        public Criteria andDisctId97GreaterThan(Long value) {
            addCriterion("DISCT_ID_97 >", value, "disctId97");
            return (Criteria) this;
        }

        public Criteria andDisctId97GreaterThanOrEqualTo(Long value) {
            addCriterion("DISCT_ID_97 >=", value, "disctId97");
            return (Criteria) this;
        }

        public Criteria andDisctId97LessThan(Long value) {
            addCriterion("DISCT_ID_97 <", value, "disctId97");
            return (Criteria) this;
        }

        public Criteria andDisctId97LessThanOrEqualTo(Long value) {
            addCriterion("DISCT_ID_97 <=", value, "disctId97");
            return (Criteria) this;
        }

        public Criteria andDisctId97In(List<Long> values) {
            addCriterion("DISCT_ID_97 in", values, "disctId97");
            return (Criteria) this;
        }

        public Criteria andDisctId97NotIn(List<Long> values) {
            addCriterion("DISCT_ID_97 not in", values, "disctId97");
            return (Criteria) this;
        }

        public Criteria andDisctId97Between(Long value1, Long value2) {
            addCriterion("DISCT_ID_97 between", value1, value2, "disctId97");
            return (Criteria) this;
        }

        public Criteria andDisctId97NotBetween(Long value1, Long value2) {
            addCriterion("DISCT_ID_97 not between", value1, value2, "disctId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97IsNull() {
            addCriterion("DISCT_OBJECT_ID_97 is null");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97IsNotNull() {
            addCriterion("DISCT_OBJECT_ID_97 is not null");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97EqualTo(String value) {
            addCriterion("DISCT_OBJECT_ID_97 =", value, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97NotEqualTo(String value) {
            addCriterion("DISCT_OBJECT_ID_97 <>", value, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97GreaterThan(String value) {
            addCriterion("DISCT_OBJECT_ID_97 >", value, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97GreaterThanOrEqualTo(String value) {
            addCriterion("DISCT_OBJECT_ID_97 >=", value, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97LessThan(String value) {
            addCriterion("DISCT_OBJECT_ID_97 <", value, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97LessThanOrEqualTo(String value) {
            addCriterion("DISCT_OBJECT_ID_97 <=", value, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97Like(String value) {
            addCriterion("DISCT_OBJECT_ID_97 like", value, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97NotLike(String value) {
            addCriterion("DISCT_OBJECT_ID_97 not like", value, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97In(List<String> values) {
            addCriterion("DISCT_OBJECT_ID_97 in", values, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97NotIn(List<String> values) {
            addCriterion("DISCT_OBJECT_ID_97 not in", values, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97Between(String value1, String value2) {
            addCriterion("DISCT_OBJECT_ID_97 between", value1, value2, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectId97NotBetween(String value1, String value2) {
            addCriterion("DISCT_OBJECT_ID_97 not between", value1, value2, "disctObjectId97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97IsNull() {
            addCriterion("DISCT_OBJECT_NAME_97 is null");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97IsNotNull() {
            addCriterion("DISCT_OBJECT_NAME_97 is not null");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97EqualTo(String value) {
            addCriterion("DISCT_OBJECT_NAME_97 =", value, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97NotEqualTo(String value) {
            addCriterion("DISCT_OBJECT_NAME_97 <>", value, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97GreaterThan(String value) {
            addCriterion("DISCT_OBJECT_NAME_97 >", value, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97GreaterThanOrEqualTo(String value) {
            addCriterion("DISCT_OBJECT_NAME_97 >=", value, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97LessThan(String value) {
            addCriterion("DISCT_OBJECT_NAME_97 <", value, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97LessThanOrEqualTo(String value) {
            addCriterion("DISCT_OBJECT_NAME_97 <=", value, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97Like(String value) {
            addCriterion("DISCT_OBJECT_NAME_97 like", value, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97NotLike(String value) {
            addCriterion("DISCT_OBJECT_NAME_97 not like", value, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97In(List<String> values) {
            addCriterion("DISCT_OBJECT_NAME_97 in", values, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97NotIn(List<String> values) {
            addCriterion("DISCT_OBJECT_NAME_97 not in", values, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97Between(String value1, String value2) {
            addCriterion("DISCT_OBJECT_NAME_97 between", value1, value2, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectName97NotBetween(String value1, String value2) {
            addCriterion("DISCT_OBJECT_NAME_97 not between", value1, value2, "disctObjectName97");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillIsNull() {
            addCriterion("DISCT_OBJECT_ID_BILL is null");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillIsNotNull() {
            addCriterion("DISCT_OBJECT_ID_BILL is not null");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillEqualTo(Long value) {
            addCriterion("DISCT_OBJECT_ID_BILL =", value, "disctObjectIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillNotEqualTo(Long value) {
            addCriterion("DISCT_OBJECT_ID_BILL <>", value, "disctObjectIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillGreaterThan(Long value) {
            addCriterion("DISCT_OBJECT_ID_BILL >", value, "disctObjectIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillGreaterThanOrEqualTo(Long value) {
            addCriterion("DISCT_OBJECT_ID_BILL >=", value, "disctObjectIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillLessThan(Long value) {
            addCriterion("DISCT_OBJECT_ID_BILL <", value, "disctObjectIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillLessThanOrEqualTo(Long value) {
            addCriterion("DISCT_OBJECT_ID_BILL <=", value, "disctObjectIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillIn(List<Long> values) {
            addCriterion("DISCT_OBJECT_ID_BILL in", values, "disctObjectIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillNotIn(List<Long> values) {
            addCriterion("DISCT_OBJECT_ID_BILL not in", values, "disctObjectIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillBetween(Long value1, Long value2) {
            addCriterion("DISCT_OBJECT_ID_BILL between", value1, value2, "disctObjectIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectIdBillNotBetween(Long value1, Long value2) {
            addCriterion("DISCT_OBJECT_ID_BILL not between", value1, value2, "disctObjectIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillIsNull() {
            addCriterion("DISCT_OBJECT_NAME_BILL is null");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillIsNotNull() {
            addCriterion("DISCT_OBJECT_NAME_BILL is not null");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillEqualTo(String value) {
            addCriterion("DISCT_OBJECT_NAME_BILL =", value, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillNotEqualTo(String value) {
            addCriterion("DISCT_OBJECT_NAME_BILL <>", value, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillGreaterThan(String value) {
            addCriterion("DISCT_OBJECT_NAME_BILL >", value, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillGreaterThanOrEqualTo(String value) {
            addCriterion("DISCT_OBJECT_NAME_BILL >=", value, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillLessThan(String value) {
            addCriterion("DISCT_OBJECT_NAME_BILL <", value, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillLessThanOrEqualTo(String value) {
            addCriterion("DISCT_OBJECT_NAME_BILL <=", value, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillLike(String value) {
            addCriterion("DISCT_OBJECT_NAME_BILL like", value, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillNotLike(String value) {
            addCriterion("DISCT_OBJECT_NAME_BILL not like", value, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillIn(List<String> values) {
            addCriterion("DISCT_OBJECT_NAME_BILL in", values, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillNotIn(List<String> values) {
            addCriterion("DISCT_OBJECT_NAME_BILL not in", values, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillBetween(String value1, String value2) {
            addCriterion("DISCT_OBJECT_NAME_BILL between", value1, value2, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctObjectNameBillNotBetween(String value1, String value2) {
            addCriterion("DISCT_OBJECT_NAME_BILL not between", value1, value2, "disctObjectNameBill");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
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