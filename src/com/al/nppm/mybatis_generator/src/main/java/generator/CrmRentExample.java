package generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrmRentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public CrmRentExample() {
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

        public Criteria andRowIdIsNull() {
            addCriterion("ROW_ID is null");
            return (Criteria) this;
        }

        public Criteria andRowIdIsNotNull() {
            addCriterion("ROW_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRowIdEqualTo(Long value) {
            addCriterion("ROW_ID =", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdNotEqualTo(Long value) {
            addCriterion("ROW_ID <>", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdGreaterThan(Long value) {
            addCriterion("ROW_ID >", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ROW_ID >=", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdLessThan(Long value) {
            addCriterion("ROW_ID <", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdLessThanOrEqualTo(Long value) {
            addCriterion("ROW_ID <=", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdIn(List<Long> values) {
            addCriterion("ROW_ID in", values, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdNotIn(List<Long> values) {
            addCriterion("ROW_ID not in", values, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdBetween(Long value1, Long value2) {
            addCriterion("ROW_ID between", value1, value2, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdNotBetween(Long value1, Long value2) {
            addCriterion("ROW_ID not between", value1, value2, "rowId");
            return (Criteria) this;
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

        public Criteria andOrderItemIdIsNull() {
            addCriterion("ORDER_ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIsNotNull() {
            addCriterion("ORDER_ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdEqualTo(Long value) {
            addCriterion("ORDER_ITEM_ID =", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotEqualTo(Long value) {
            addCriterion("ORDER_ITEM_ID <>", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdGreaterThan(Long value) {
            addCriterion("ORDER_ITEM_ID >", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDER_ITEM_ID >=", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdLessThan(Long value) {
            addCriterion("ORDER_ITEM_ID <", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdLessThanOrEqualTo(Long value) {
            addCriterion("ORDER_ITEM_ID <=", value, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdIn(List<Long> values) {
            addCriterion("ORDER_ITEM_ID in", values, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotIn(List<Long> values) {
            addCriterion("ORDER_ITEM_ID not in", values, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdBetween(Long value1, Long value2) {
            addCriterion("ORDER_ITEM_ID between", value1, value2, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOrderItemIdNotBetween(Long value1, Long value2) {
            addCriterion("ORDER_ITEM_ID not between", value1, value2, "orderItemId");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdIsNull() {
            addCriterion("OFFER_INST_ID is null");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdIsNotNull() {
            addCriterion("OFFER_INST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdEqualTo(Long value) {
            addCriterion("OFFER_INST_ID =", value, "offerInstId");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdNotEqualTo(Long value) {
            addCriterion("OFFER_INST_ID <>", value, "offerInstId");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdGreaterThan(Long value) {
            addCriterion("OFFER_INST_ID >", value, "offerInstId");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OFFER_INST_ID >=", value, "offerInstId");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdLessThan(Long value) {
            addCriterion("OFFER_INST_ID <", value, "offerInstId");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdLessThanOrEqualTo(Long value) {
            addCriterion("OFFER_INST_ID <=", value, "offerInstId");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdIn(List<Long> values) {
            addCriterion("OFFER_INST_ID in", values, "offerInstId");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdNotIn(List<Long> values) {
            addCriterion("OFFER_INST_ID not in", values, "offerInstId");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdBetween(Long value1, Long value2) {
            addCriterion("OFFER_INST_ID between", value1, value2, "offerInstId");
            return (Criteria) this;
        }

        public Criteria andOfferInstIdNotBetween(Long value1, Long value2) {
            addCriterion("OFFER_INST_ID not between", value1, value2, "offerInstId");
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

        public Criteria andOfferTypeIsNull() {
            addCriterion("OFFER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOfferTypeIsNotNull() {
            addCriterion("OFFER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOfferTypeEqualTo(String value) {
            addCriterion("OFFER_TYPE =", value, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferTypeNotEqualTo(String value) {
            addCriterion("OFFER_TYPE <>", value, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferTypeGreaterThan(String value) {
            addCriterion("OFFER_TYPE >", value, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferTypeGreaterThanOrEqualTo(String value) {
            addCriterion("OFFER_TYPE >=", value, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferTypeLessThan(String value) {
            addCriterion("OFFER_TYPE <", value, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferTypeLessThanOrEqualTo(String value) {
            addCriterion("OFFER_TYPE <=", value, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferTypeLike(String value) {
            addCriterion("OFFER_TYPE like", value, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferTypeNotLike(String value) {
            addCriterion("OFFER_TYPE not like", value, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferTypeIn(List<String> values) {
            addCriterion("OFFER_TYPE in", values, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferTypeNotIn(List<String> values) {
            addCriterion("OFFER_TYPE not in", values, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferTypeBetween(String value1, String value2) {
            addCriterion("OFFER_TYPE between", value1, value2, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferTypeNotBetween(String value1, String value2) {
            addCriterion("OFFER_TYPE not between", value1, value2, "offerType");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateIsNull() {
            addCriterion("OFFER_START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateIsNotNull() {
            addCriterion("OFFER_START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateEqualTo(Date value) {
            addCriterion("OFFER_START_DATE =", value, "offerStartDate");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateNotEqualTo(Date value) {
            addCriterion("OFFER_START_DATE <>", value, "offerStartDate");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateGreaterThan(Date value) {
            addCriterion("OFFER_START_DATE >", value, "offerStartDate");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateGreaterThanOrEqualTo(Date value) {
            addCriterion("OFFER_START_DATE >=", value, "offerStartDate");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateLessThan(Date value) {
            addCriterion("OFFER_START_DATE <", value, "offerStartDate");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateLessThanOrEqualTo(Date value) {
            addCriterion("OFFER_START_DATE <=", value, "offerStartDate");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateIn(List<Date> values) {
            addCriterion("OFFER_START_DATE in", values, "offerStartDate");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateNotIn(List<Date> values) {
            addCriterion("OFFER_START_DATE not in", values, "offerStartDate");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateBetween(Date value1, Date value2) {
            addCriterion("OFFER_START_DATE between", value1, value2, "offerStartDate");
            return (Criteria) this;
        }

        public Criteria andOfferStartDateNotBetween(Date value1, Date value2) {
            addCriterion("OFFER_START_DATE not between", value1, value2, "offerStartDate");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeIsNull() {
            addCriterion("BILL_TOTAL_FEE is null");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeIsNotNull() {
            addCriterion("BILL_TOTAL_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeEqualTo(Long value) {
            addCriterion("BILL_TOTAL_FEE =", value, "billTotalFee");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeNotEqualTo(Long value) {
            addCriterion("BILL_TOTAL_FEE <>", value, "billTotalFee");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeGreaterThan(Long value) {
            addCriterion("BILL_TOTAL_FEE >", value, "billTotalFee");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("BILL_TOTAL_FEE >=", value, "billTotalFee");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeLessThan(Long value) {
            addCriterion("BILL_TOTAL_FEE <", value, "billTotalFee");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeLessThanOrEqualTo(Long value) {
            addCriterion("BILL_TOTAL_FEE <=", value, "billTotalFee");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeIn(List<Long> values) {
            addCriterion("BILL_TOTAL_FEE in", values, "billTotalFee");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeNotIn(List<Long> values) {
            addCriterion("BILL_TOTAL_FEE not in", values, "billTotalFee");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeBetween(Long value1, Long value2) {
            addCriterion("BILL_TOTAL_FEE between", value1, value2, "billTotalFee");
            return (Criteria) this;
        }

        public Criteria andBillTotalFeeNotBetween(Long value1, Long value2) {
            addCriterion("BILL_TOTAL_FEE not between", value1, value2, "billTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeIsNull() {
            addCriterion("CRM_TOTAL_FEE is null");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeIsNotNull() {
            addCriterion("CRM_TOTAL_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeEqualTo(String value) {
            addCriterion("CRM_TOTAL_FEE =", value, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeNotEqualTo(String value) {
            addCriterion("CRM_TOTAL_FEE <>", value, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeGreaterThan(String value) {
            addCriterion("CRM_TOTAL_FEE >", value, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeGreaterThanOrEqualTo(String value) {
            addCriterion("CRM_TOTAL_FEE >=", value, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeLessThan(String value) {
            addCriterion("CRM_TOTAL_FEE <", value, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeLessThanOrEqualTo(String value) {
            addCriterion("CRM_TOTAL_FEE <=", value, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeLike(String value) {
            addCriterion("CRM_TOTAL_FEE like", value, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeNotLike(String value) {
            addCriterion("CRM_TOTAL_FEE not like", value, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeIn(List<String> values) {
            addCriterion("CRM_TOTAL_FEE in", values, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeNotIn(List<String> values) {
            addCriterion("CRM_TOTAL_FEE not in", values, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeBetween(String value1, String value2) {
            addCriterion("CRM_TOTAL_FEE between", value1, value2, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andCrmTotalFeeNotBetween(String value1, String value2) {
            addCriterion("CRM_TOTAL_FEE not between", value1, value2, "crmTotalFee");
            return (Criteria) this;
        }

        public Criteria andEndTypeIsNull() {
            addCriterion("END_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andEndTypeIsNotNull() {
            addCriterion("END_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andEndTypeEqualTo(String value) {
            addCriterion("END_TYPE =", value, "endType");
            return (Criteria) this;
        }

        public Criteria andEndTypeNotEqualTo(String value) {
            addCriterion("END_TYPE <>", value, "endType");
            return (Criteria) this;
        }

        public Criteria andEndTypeGreaterThan(String value) {
            addCriterion("END_TYPE >", value, "endType");
            return (Criteria) this;
        }

        public Criteria andEndTypeGreaterThanOrEqualTo(String value) {
            addCriterion("END_TYPE >=", value, "endType");
            return (Criteria) this;
        }

        public Criteria andEndTypeLessThan(String value) {
            addCriterion("END_TYPE <", value, "endType");
            return (Criteria) this;
        }

        public Criteria andEndTypeLessThanOrEqualTo(String value) {
            addCriterion("END_TYPE <=", value, "endType");
            return (Criteria) this;
        }

        public Criteria andEndTypeLike(String value) {
            addCriterion("END_TYPE like", value, "endType");
            return (Criteria) this;
        }

        public Criteria andEndTypeNotLike(String value) {
            addCriterion("END_TYPE not like", value, "endType");
            return (Criteria) this;
        }

        public Criteria andEndTypeIn(List<String> values) {
            addCriterion("END_TYPE in", values, "endType");
            return (Criteria) this;
        }

        public Criteria andEndTypeNotIn(List<String> values) {
            addCriterion("END_TYPE not in", values, "endType");
            return (Criteria) this;
        }

        public Criteria andEndTypeBetween(String value1, String value2) {
            addCriterion("END_TYPE between", value1, value2, "endType");
            return (Criteria) this;
        }

        public Criteria andEndTypeNotBetween(String value1, String value2) {
            addCriterion("END_TYPE not between", value1, value2, "endType");
            return (Criteria) this;
        }

        public Criteria andRentMonthIsNull() {
            addCriterion("RENT_MONTH is null");
            return (Criteria) this;
        }

        public Criteria andRentMonthIsNotNull() {
            addCriterion("RENT_MONTH is not null");
            return (Criteria) this;
        }

        public Criteria andRentMonthEqualTo(Long value) {
            addCriterion("RENT_MONTH =", value, "rentMonth");
            return (Criteria) this;
        }

        public Criteria andRentMonthNotEqualTo(Long value) {
            addCriterion("RENT_MONTH <>", value, "rentMonth");
            return (Criteria) this;
        }

        public Criteria andRentMonthGreaterThan(Long value) {
            addCriterion("RENT_MONTH >", value, "rentMonth");
            return (Criteria) this;
        }

        public Criteria andRentMonthGreaterThanOrEqualTo(Long value) {
            addCriterion("RENT_MONTH >=", value, "rentMonth");
            return (Criteria) this;
        }

        public Criteria andRentMonthLessThan(Long value) {
            addCriterion("RENT_MONTH <", value, "rentMonth");
            return (Criteria) this;
        }

        public Criteria andRentMonthLessThanOrEqualTo(Long value) {
            addCriterion("RENT_MONTH <=", value, "rentMonth");
            return (Criteria) this;
        }

        public Criteria andRentMonthIn(List<Long> values) {
            addCriterion("RENT_MONTH in", values, "rentMonth");
            return (Criteria) this;
        }

        public Criteria andRentMonthNotIn(List<Long> values) {
            addCriterion("RENT_MONTH not in", values, "rentMonth");
            return (Criteria) this;
        }

        public Criteria andRentMonthBetween(Long value1, Long value2) {
            addCriterion("RENT_MONTH between", value1, value2, "rentMonth");
            return (Criteria) this;
        }

        public Criteria andRentMonthNotBetween(Long value1, Long value2) {
            addCriterion("RENT_MONTH not between", value1, value2, "rentMonth");
            return (Criteria) this;
        }

        public Criteria andOfferIdIsNull() {
            addCriterion("OFFER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOfferIdIsNotNull() {
            addCriterion("OFFER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOfferIdEqualTo(Long value) {
            addCriterion("OFFER_ID =", value, "offerId");
            return (Criteria) this;
        }

        public Criteria andOfferIdNotEqualTo(Long value) {
            addCriterion("OFFER_ID <>", value, "offerId");
            return (Criteria) this;
        }

        public Criteria andOfferIdGreaterThan(Long value) {
            addCriterion("OFFER_ID >", value, "offerId");
            return (Criteria) this;
        }

        public Criteria andOfferIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OFFER_ID >=", value, "offerId");
            return (Criteria) this;
        }

        public Criteria andOfferIdLessThan(Long value) {
            addCriterion("OFFER_ID <", value, "offerId");
            return (Criteria) this;
        }

        public Criteria andOfferIdLessThanOrEqualTo(Long value) {
            addCriterion("OFFER_ID <=", value, "offerId");
            return (Criteria) this;
        }

        public Criteria andOfferIdIn(List<Long> values) {
            addCriterion("OFFER_ID in", values, "offerId");
            return (Criteria) this;
        }

        public Criteria andOfferIdNotIn(List<Long> values) {
            addCriterion("OFFER_ID not in", values, "offerId");
            return (Criteria) this;
        }

        public Criteria andOfferIdBetween(Long value1, Long value2) {
            addCriterion("OFFER_ID between", value1, value2, "offerId");
            return (Criteria) this;
        }

        public Criteria andOfferIdNotBetween(Long value1, Long value2) {
            addCriterion("OFFER_ID not between", value1, value2, "offerId");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIsNull() {
            addCriterion("RETURN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIsNotNull() {
            addCriterion("RETURN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTypeEqualTo(Long value) {
            addCriterion("RETURN_TYPE =", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotEqualTo(Long value) {
            addCriterion("RETURN_TYPE <>", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeGreaterThan(Long value) {
            addCriterion("RETURN_TYPE >", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("RETURN_TYPE >=", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLessThan(Long value) {
            addCriterion("RETURN_TYPE <", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeLessThanOrEqualTo(Long value) {
            addCriterion("RETURN_TYPE <=", value, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeIn(List<Long> values) {
            addCriterion("RETURN_TYPE in", values, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotIn(List<Long> values) {
            addCriterion("RETURN_TYPE not in", values, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeBetween(Long value1, Long value2) {
            addCriterion("RETURN_TYPE between", value1, value2, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnTypeNotBetween(Long value1, Long value2) {
            addCriterion("RETURN_TYPE not between", value1, value2, "returnType");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdIsNull() {
            addCriterion("RETURN_RULE_ID is null");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdIsNotNull() {
            addCriterion("RETURN_RULE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdEqualTo(Long value) {
            addCriterion("RETURN_RULE_ID =", value, "returnRuleId");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdNotEqualTo(Long value) {
            addCriterion("RETURN_RULE_ID <>", value, "returnRuleId");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdGreaterThan(Long value) {
            addCriterion("RETURN_RULE_ID >", value, "returnRuleId");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("RETURN_RULE_ID >=", value, "returnRuleId");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdLessThan(Long value) {
            addCriterion("RETURN_RULE_ID <", value, "returnRuleId");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdLessThanOrEqualTo(Long value) {
            addCriterion("RETURN_RULE_ID <=", value, "returnRuleId");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdIn(List<Long> values) {
            addCriterion("RETURN_RULE_ID in", values, "returnRuleId");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdNotIn(List<Long> values) {
            addCriterion("RETURN_RULE_ID not in", values, "returnRuleId");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdBetween(Long value1, Long value2) {
            addCriterion("RETURN_RULE_ID between", value1, value2, "returnRuleId");
            return (Criteria) this;
        }

        public Criteria andReturnRuleIdNotBetween(Long value1, Long value2) {
            addCriterion("RETURN_RULE_ID not between", value1, value2, "returnRuleId");
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