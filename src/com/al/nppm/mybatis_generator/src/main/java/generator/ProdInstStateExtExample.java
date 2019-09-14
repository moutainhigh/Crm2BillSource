package generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProdInstStateExtExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public ProdInstStateExtExample() {
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

        public Criteria andProdInstStateIdIsNull() {
            addCriterion("PROD_INST_STATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andProdInstStateIdIsNotNull() {
            addCriterion("PROD_INST_STATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProdInstStateIdEqualTo(Long value) {
            addCriterion("PROD_INST_STATE_ID =", value, "prodInstStateId");
            return (Criteria) this;
        }

        public Criteria andProdInstStateIdNotEqualTo(Long value) {
            addCriterion("PROD_INST_STATE_ID <>", value, "prodInstStateId");
            return (Criteria) this;
        }

        public Criteria andProdInstStateIdGreaterThan(Long value) {
            addCriterion("PROD_INST_STATE_ID >", value, "prodInstStateId");
            return (Criteria) this;
        }

        public Criteria andProdInstStateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROD_INST_STATE_ID >=", value, "prodInstStateId");
            return (Criteria) this;
        }

        public Criteria andProdInstStateIdLessThan(Long value) {
            addCriterion("PROD_INST_STATE_ID <", value, "prodInstStateId");
            return (Criteria) this;
        }

        public Criteria andProdInstStateIdLessThanOrEqualTo(Long value) {
            addCriterion("PROD_INST_STATE_ID <=", value, "prodInstStateId");
            return (Criteria) this;
        }

        public Criteria andProdInstStateIdIn(List<Long> values) {
            addCriterion("PROD_INST_STATE_ID in", values, "prodInstStateId");
            return (Criteria) this;
        }

        public Criteria andProdInstStateIdNotIn(List<Long> values) {
            addCriterion("PROD_INST_STATE_ID not in", values, "prodInstStateId");
            return (Criteria) this;
        }

        public Criteria andProdInstStateIdBetween(Long value1, Long value2) {
            addCriterion("PROD_INST_STATE_ID between", value1, value2, "prodInstStateId");
            return (Criteria) this;
        }

        public Criteria andProdInstStateIdNotBetween(Long value1, Long value2) {
            addCriterion("PROD_INST_STATE_ID not between", value1, value2, "prodInstStateId");
            return (Criteria) this;
        }

        public Criteria andProdInstIdIsNull() {
            addCriterion("PROD_INST_ID is null");
            return (Criteria) this;
        }

        public Criteria andProdInstIdIsNotNull() {
            addCriterion("PROD_INST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProdInstIdEqualTo(Long value) {
            addCriterion("PROD_INST_ID =", value, "prodInstId");
            return (Criteria) this;
        }

        public Criteria andProdInstIdNotEqualTo(Long value) {
            addCriterion("PROD_INST_ID <>", value, "prodInstId");
            return (Criteria) this;
        }

        public Criteria andProdInstIdGreaterThan(Long value) {
            addCriterion("PROD_INST_ID >", value, "prodInstId");
            return (Criteria) this;
        }

        public Criteria andProdInstIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROD_INST_ID >=", value, "prodInstId");
            return (Criteria) this;
        }

        public Criteria andProdInstIdLessThan(Long value) {
            addCriterion("PROD_INST_ID <", value, "prodInstId");
            return (Criteria) this;
        }

        public Criteria andProdInstIdLessThanOrEqualTo(Long value) {
            addCriterion("PROD_INST_ID <=", value, "prodInstId");
            return (Criteria) this;
        }

        public Criteria andProdInstIdIn(List<Long> values) {
            addCriterion("PROD_INST_ID in", values, "prodInstId");
            return (Criteria) this;
        }

        public Criteria andProdInstIdNotIn(List<Long> values) {
            addCriterion("PROD_INST_ID not in", values, "prodInstId");
            return (Criteria) this;
        }

        public Criteria andProdInstIdBetween(Long value1, Long value2) {
            addCriterion("PROD_INST_ID between", value1, value2, "prodInstId");
            return (Criteria) this;
        }

        public Criteria andProdInstIdNotBetween(Long value1, Long value2) {
            addCriterion("PROD_INST_ID not between", value1, value2, "prodInstId");
            return (Criteria) this;
        }

        public Criteria andStopTypeIsNull() {
            addCriterion("STOP_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andStopTypeIsNotNull() {
            addCriterion("STOP_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andStopTypeEqualTo(String value) {
            addCriterion("STOP_TYPE =", value, "stopType");
            return (Criteria) this;
        }

        public Criteria andStopTypeNotEqualTo(String value) {
            addCriterion("STOP_TYPE <>", value, "stopType");
            return (Criteria) this;
        }

        public Criteria andStopTypeGreaterThan(String value) {
            addCriterion("STOP_TYPE >", value, "stopType");
            return (Criteria) this;
        }

        public Criteria andStopTypeGreaterThanOrEqualTo(String value) {
            addCriterion("STOP_TYPE >=", value, "stopType");
            return (Criteria) this;
        }

        public Criteria andStopTypeLessThan(String value) {
            addCriterion("STOP_TYPE <", value, "stopType");
            return (Criteria) this;
        }

        public Criteria andStopTypeLessThanOrEqualTo(String value) {
            addCriterion("STOP_TYPE <=", value, "stopType");
            return (Criteria) this;
        }

        public Criteria andStopTypeLike(String value) {
            addCriterion("STOP_TYPE like", value, "stopType");
            return (Criteria) this;
        }

        public Criteria andStopTypeNotLike(String value) {
            addCriterion("STOP_TYPE not like", value, "stopType");
            return (Criteria) this;
        }

        public Criteria andStopTypeIn(List<String> values) {
            addCriterion("STOP_TYPE in", values, "stopType");
            return (Criteria) this;
        }

        public Criteria andStopTypeNotIn(List<String> values) {
            addCriterion("STOP_TYPE not in", values, "stopType");
            return (Criteria) this;
        }

        public Criteria andStopTypeBetween(String value1, String value2) {
            addCriterion("STOP_TYPE between", value1, value2, "stopType");
            return (Criteria) this;
        }

        public Criteria andStopTypeNotBetween(String value1, String value2) {
            addCriterion("STOP_TYPE not between", value1, value2, "stopType");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("`STATE` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`STATE` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("`STATE` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("`STATE` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("`STATE` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("`STATE` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("`STATE` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("`STATE` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("`STATE` like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("`STATE` not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("`STATE` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("`STATE` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("`STATE` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("`STATE` not between", value1, value2, "state");
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

        public Criteria andStopDirectionIsNull() {
            addCriterion("STOP_DIRECTION is null");
            return (Criteria) this;
        }

        public Criteria andStopDirectionIsNotNull() {
            addCriterion("STOP_DIRECTION is not null");
            return (Criteria) this;
        }

        public Criteria andStopDirectionEqualTo(String value) {
            addCriterion("STOP_DIRECTION =", value, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopDirectionNotEqualTo(String value) {
            addCriterion("STOP_DIRECTION <>", value, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopDirectionGreaterThan(String value) {
            addCriterion("STOP_DIRECTION >", value, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopDirectionGreaterThanOrEqualTo(String value) {
            addCriterion("STOP_DIRECTION >=", value, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopDirectionLessThan(String value) {
            addCriterion("STOP_DIRECTION <", value, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopDirectionLessThanOrEqualTo(String value) {
            addCriterion("STOP_DIRECTION <=", value, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopDirectionLike(String value) {
            addCriterion("STOP_DIRECTION like", value, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopDirectionNotLike(String value) {
            addCriterion("STOP_DIRECTION not like", value, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopDirectionIn(List<String> values) {
            addCriterion("STOP_DIRECTION in", values, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopDirectionNotIn(List<String> values) {
            addCriterion("STOP_DIRECTION not in", values, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopDirectionBetween(String value1, String value2) {
            addCriterion("STOP_DIRECTION between", value1, value2, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopDirectionNotBetween(String value1, String value2) {
            addCriterion("STOP_DIRECTION not between", value1, value2, "stopDirection");
            return (Criteria) this;
        }

        public Criteria andStopReasonIsNull() {
            addCriterion("STOP_REASON is null");
            return (Criteria) this;
        }

        public Criteria andStopReasonIsNotNull() {
            addCriterion("STOP_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andStopReasonEqualTo(String value) {
            addCriterion("STOP_REASON =", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonNotEqualTo(String value) {
            addCriterion("STOP_REASON <>", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonGreaterThan(String value) {
            addCriterion("STOP_REASON >", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonGreaterThanOrEqualTo(String value) {
            addCriterion("STOP_REASON >=", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonLessThan(String value) {
            addCriterion("STOP_REASON <", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonLessThanOrEqualTo(String value) {
            addCriterion("STOP_REASON <=", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonLike(String value) {
            addCriterion("STOP_REASON like", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonNotLike(String value) {
            addCriterion("STOP_REASON not like", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonIn(List<String> values) {
            addCriterion("STOP_REASON in", values, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonNotIn(List<String> values) {
            addCriterion("STOP_REASON not in", values, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonBetween(String value1, String value2) {
            addCriterion("STOP_REASON between", value1, value2, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonNotBetween(String value1, String value2) {
            addCriterion("STOP_REASON not between", value1, value2, "stopReason");
            return (Criteria) this;
        }

        public Criteria andEventDateIsNull() {
            addCriterion("EVENT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEventDateIsNotNull() {
            addCriterion("EVENT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEventDateEqualTo(Date value) {
            addCriterion("EVENT_DATE =", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateNotEqualTo(Date value) {
            addCriterion("EVENT_DATE <>", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateGreaterThan(Date value) {
            addCriterion("EVENT_DATE >", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateGreaterThanOrEqualTo(Date value) {
            addCriterion("EVENT_DATE >=", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateLessThan(Date value) {
            addCriterion("EVENT_DATE <", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateLessThanOrEqualTo(Date value) {
            addCriterion("EVENT_DATE <=", value, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateIn(List<Date> values) {
            addCriterion("EVENT_DATE in", values, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateNotIn(List<Date> values) {
            addCriterion("EVENT_DATE not in", values, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateBetween(Date value1, Date value2) {
            addCriterion("EVENT_DATE between", value1, value2, "eventDate");
            return (Criteria) this;
        }

        public Criteria andEventDateNotBetween(Date value1, Date value2) {
            addCriterion("EVENT_DATE not between", value1, value2, "eventDate");
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

        public Criteria andLastOrderItemIdIsNull() {
            addCriterion("LAST_ORDER_ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andLastOrderItemIdIsNotNull() {
            addCriterion("LAST_ORDER_ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLastOrderItemIdEqualTo(Long value) {
            addCriterion("LAST_ORDER_ITEM_ID =", value, "lastOrderItemId");
            return (Criteria) this;
        }

        public Criteria andLastOrderItemIdNotEqualTo(Long value) {
            addCriterion("LAST_ORDER_ITEM_ID <>", value, "lastOrderItemId");
            return (Criteria) this;
        }

        public Criteria andLastOrderItemIdGreaterThan(Long value) {
            addCriterion("LAST_ORDER_ITEM_ID >", value, "lastOrderItemId");
            return (Criteria) this;
        }

        public Criteria andLastOrderItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("LAST_ORDER_ITEM_ID >=", value, "lastOrderItemId");
            return (Criteria) this;
        }

        public Criteria andLastOrderItemIdLessThan(Long value) {
            addCriterion("LAST_ORDER_ITEM_ID <", value, "lastOrderItemId");
            return (Criteria) this;
        }

        public Criteria andLastOrderItemIdLessThanOrEqualTo(Long value) {
            addCriterion("LAST_ORDER_ITEM_ID <=", value, "lastOrderItemId");
            return (Criteria) this;
        }

        public Criteria andLastOrderItemIdIn(List<Long> values) {
            addCriterion("LAST_ORDER_ITEM_ID in", values, "lastOrderItemId");
            return (Criteria) this;
        }

        public Criteria andLastOrderItemIdNotIn(List<Long> values) {
            addCriterion("LAST_ORDER_ITEM_ID not in", values, "lastOrderItemId");
            return (Criteria) this;
        }

        public Criteria andLastOrderItemIdBetween(Long value1, Long value2) {
            addCriterion("LAST_ORDER_ITEM_ID between", value1, value2, "lastOrderItemId");
            return (Criteria) this;
        }

        public Criteria andLastOrderItemIdNotBetween(Long value1, Long value2) {
            addCriterion("LAST_ORDER_ITEM_ID not between", value1, value2, "lastOrderItemId");
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