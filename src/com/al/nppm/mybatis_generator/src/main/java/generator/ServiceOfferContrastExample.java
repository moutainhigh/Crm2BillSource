package generator;

import java.util.ArrayList;
import java.util.List;

public class ServiceOfferContrastExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ServiceOfferContrastExample() {
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

        public Criteria andServcieOfferIdIsNull() {
            addCriterion("SERVCIE_OFFER_ID is null");
            return (Criteria) this;
        }

        public Criteria andServcieOfferIdIsNotNull() {
            addCriterion("SERVCIE_OFFER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andServcieOfferIdEqualTo(Long value) {
            addCriterion("SERVCIE_OFFER_ID =", value, "servcieOfferId");
            return (Criteria) this;
        }

        public Criteria andServcieOfferIdNotEqualTo(Long value) {
            addCriterion("SERVCIE_OFFER_ID <>", value, "servcieOfferId");
            return (Criteria) this;
        }

        public Criteria andServcieOfferIdGreaterThan(Long value) {
            addCriterion("SERVCIE_OFFER_ID >", value, "servcieOfferId");
            return (Criteria) this;
        }

        public Criteria andServcieOfferIdGreaterThanOrEqualTo(Long value) {
            addCriterion("SERVCIE_OFFER_ID >=", value, "servcieOfferId");
            return (Criteria) this;
        }

        public Criteria andServcieOfferIdLessThan(Long value) {
            addCriterion("SERVCIE_OFFER_ID <", value, "servcieOfferId");
            return (Criteria) this;
        }

        public Criteria andServcieOfferIdLessThanOrEqualTo(Long value) {
            addCriterion("SERVCIE_OFFER_ID <=", value, "servcieOfferId");
            return (Criteria) this;
        }

        public Criteria andServcieOfferIdIn(List<Long> values) {
            addCriterion("SERVCIE_OFFER_ID in", values, "servcieOfferId");
            return (Criteria) this;
        }

        public Criteria andServcieOfferIdNotIn(List<Long> values) {
            addCriterion("SERVCIE_OFFER_ID not in", values, "servcieOfferId");
            return (Criteria) this;
        }

        public Criteria andServcieOfferIdBetween(Long value1, Long value2) {
            addCriterion("SERVCIE_OFFER_ID between", value1, value2, "servcieOfferId");
            return (Criteria) this;
        }

        public Criteria andServcieOfferIdNotBetween(Long value1, Long value2) {
            addCriterion("SERVCIE_OFFER_ID not between", value1, value2, "servcieOfferId");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameIsNull() {
            addCriterion("SERVICE_OFER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameIsNotNull() {
            addCriterion("SERVICE_OFER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameEqualTo(String value) {
            addCriterion("SERVICE_OFER_NAME =", value, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameNotEqualTo(String value) {
            addCriterion("SERVICE_OFER_NAME <>", value, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameGreaterThan(String value) {
            addCriterion("SERVICE_OFER_NAME >", value, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_OFER_NAME >=", value, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameLessThan(String value) {
            addCriterion("SERVICE_OFER_NAME <", value, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_OFER_NAME <=", value, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameLike(String value) {
            addCriterion("SERVICE_OFER_NAME like", value, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameNotLike(String value) {
            addCriterion("SERVICE_OFER_NAME not like", value, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameIn(List<String> values) {
            addCriterion("SERVICE_OFER_NAME in", values, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameNotIn(List<String> values) {
            addCriterion("SERVICE_OFER_NAME not in", values, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameBetween(String value1, String value2) {
            addCriterion("SERVICE_OFER_NAME between", value1, value2, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andServiceOferNameNotBetween(String value1, String value2) {
            addCriterion("SERVICE_OFER_NAME not between", value1, value2, "serviceOferName");
            return (Criteria) this;
        }

        public Criteria andStypeTypeIsNull() {
            addCriterion("STYPE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andStypeTypeIsNotNull() {
            addCriterion("STYPE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andStypeTypeEqualTo(Long value) {
            addCriterion("STYPE_TYPE =", value, "stypeType");
            return (Criteria) this;
        }

        public Criteria andStypeTypeNotEqualTo(Long value) {
            addCriterion("STYPE_TYPE <>", value, "stypeType");
            return (Criteria) this;
        }

        public Criteria andStypeTypeGreaterThan(Long value) {
            addCriterion("STYPE_TYPE >", value, "stypeType");
            return (Criteria) this;
        }

        public Criteria andStypeTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("STYPE_TYPE >=", value, "stypeType");
            return (Criteria) this;
        }

        public Criteria andStypeTypeLessThan(Long value) {
            addCriterion("STYPE_TYPE <", value, "stypeType");
            return (Criteria) this;
        }

        public Criteria andStypeTypeLessThanOrEqualTo(Long value) {
            addCriterion("STYPE_TYPE <=", value, "stypeType");
            return (Criteria) this;
        }

        public Criteria andStypeTypeIn(List<Long> values) {
            addCriterion("STYPE_TYPE in", values, "stypeType");
            return (Criteria) this;
        }

        public Criteria andStypeTypeNotIn(List<Long> values) {
            addCriterion("STYPE_TYPE not in", values, "stypeType");
            return (Criteria) this;
        }

        public Criteria andStypeTypeBetween(Long value1, Long value2) {
            addCriterion("STYPE_TYPE between", value1, value2, "stypeType");
            return (Criteria) this;
        }

        public Criteria andStypeTypeNotBetween(Long value1, Long value2) {
            addCriterion("STYPE_TYPE not between", value1, value2, "stypeType");
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

        public Criteria andStatusCdEqualTo(Long value) {
            addCriterion("STATUS_CD =", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotEqualTo(Long value) {
            addCriterion("STATUS_CD <>", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdGreaterThan(Long value) {
            addCriterion("STATUS_CD >", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdGreaterThanOrEqualTo(Long value) {
            addCriterion("STATUS_CD >=", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdLessThan(Long value) {
            addCriterion("STATUS_CD <", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdLessThanOrEqualTo(Long value) {
            addCriterion("STATUS_CD <=", value, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdIn(List<Long> values) {
            addCriterion("STATUS_CD in", values, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotIn(List<Long> values) {
            addCriterion("STATUS_CD not in", values, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdBetween(Long value1, Long value2) {
            addCriterion("STATUS_CD between", value1, value2, "statusCd");
            return (Criteria) this;
        }

        public Criteria andStatusCdNotBetween(Long value1, Long value2) {
            addCriterion("STATUS_CD not between", value1, value2, "statusCd");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdIsNull() {
            addCriterion("BILL_STATUS_CD is null");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdIsNotNull() {
            addCriterion("BILL_STATUS_CD is not null");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdEqualTo(Long value) {
            addCriterion("BILL_STATUS_CD =", value, "billStatusCd");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdNotEqualTo(Long value) {
            addCriterion("BILL_STATUS_CD <>", value, "billStatusCd");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdGreaterThan(Long value) {
            addCriterion("BILL_STATUS_CD >", value, "billStatusCd");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdGreaterThanOrEqualTo(Long value) {
            addCriterion("BILL_STATUS_CD >=", value, "billStatusCd");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdLessThan(Long value) {
            addCriterion("BILL_STATUS_CD <", value, "billStatusCd");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdLessThanOrEqualTo(Long value) {
            addCriterion("BILL_STATUS_CD <=", value, "billStatusCd");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdIn(List<Long> values) {
            addCriterion("BILL_STATUS_CD in", values, "billStatusCd");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdNotIn(List<Long> values) {
            addCriterion("BILL_STATUS_CD not in", values, "billStatusCd");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdBetween(Long value1, Long value2) {
            addCriterion("BILL_STATUS_CD between", value1, value2, "billStatusCd");
            return (Criteria) this;
        }

        public Criteria andBillStatusCdNotBetween(Long value1, Long value2) {
            addCriterion("BILL_STATUS_CD not between", value1, value2, "billStatusCd");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
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