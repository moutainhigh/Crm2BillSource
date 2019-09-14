package generator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OneItemResultHisExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public OneItemResultHisExample() {
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

        public Criteria andOneAcctItemIdIsNull() {
            addCriterion("ONE_ACCT_ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andOneAcctItemIdIsNotNull() {
            addCriterion("ONE_ACCT_ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOneAcctItemIdEqualTo(Long value) {
            addCriterion("ONE_ACCT_ITEM_ID =", value, "oneAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOneAcctItemIdNotEqualTo(Long value) {
            addCriterion("ONE_ACCT_ITEM_ID <>", value, "oneAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOneAcctItemIdGreaterThan(Long value) {
            addCriterion("ONE_ACCT_ITEM_ID >", value, "oneAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOneAcctItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ONE_ACCT_ITEM_ID >=", value, "oneAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOneAcctItemIdLessThan(Long value) {
            addCriterion("ONE_ACCT_ITEM_ID <", value, "oneAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOneAcctItemIdLessThanOrEqualTo(Long value) {
            addCriterion("ONE_ACCT_ITEM_ID <=", value, "oneAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOneAcctItemIdIn(List<Long> values) {
            addCriterion("ONE_ACCT_ITEM_ID in", values, "oneAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOneAcctItemIdNotIn(List<Long> values) {
            addCriterion("ONE_ACCT_ITEM_ID not in", values, "oneAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOneAcctItemIdBetween(Long value1, Long value2) {
            addCriterion("ONE_ACCT_ITEM_ID between", value1, value2, "oneAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOneAcctItemIdNotBetween(Long value1, Long value2) {
            addCriterion("ONE_ACCT_ITEM_ID not between", value1, value2, "oneAcctItemId");
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

        public Criteria andCustOrderIdIsNull() {
            addCriterion("CUST_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustOrderIdIsNotNull() {
            addCriterion("CUST_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustOrderIdEqualTo(Long value) {
            addCriterion("CUST_ORDER_ID =", value, "custOrderId");
            return (Criteria) this;
        }

        public Criteria andCustOrderIdNotEqualTo(Long value) {
            addCriterion("CUST_ORDER_ID <>", value, "custOrderId");
            return (Criteria) this;
        }

        public Criteria andCustOrderIdGreaterThan(Long value) {
            addCriterion("CUST_ORDER_ID >", value, "custOrderId");
            return (Criteria) this;
        }

        public Criteria andCustOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CUST_ORDER_ID >=", value, "custOrderId");
            return (Criteria) this;
        }

        public Criteria andCustOrderIdLessThan(Long value) {
            addCriterion("CUST_ORDER_ID <", value, "custOrderId");
            return (Criteria) this;
        }

        public Criteria andCustOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("CUST_ORDER_ID <=", value, "custOrderId");
            return (Criteria) this;
        }

        public Criteria andCustOrderIdIn(List<Long> values) {
            addCriterion("CUST_ORDER_ID in", values, "custOrderId");
            return (Criteria) this;
        }

        public Criteria andCustOrderIdNotIn(List<Long> values) {
            addCriterion("CUST_ORDER_ID not in", values, "custOrderId");
            return (Criteria) this;
        }

        public Criteria andCustOrderIdBetween(Long value1, Long value2) {
            addCriterion("CUST_ORDER_ID between", value1, value2, "custOrderId");
            return (Criteria) this;
        }

        public Criteria andCustOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("CUST_ORDER_ID not between", value1, value2, "custOrderId");
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

        public Criteria andSourceTypeIsNull() {
            addCriterion("SOURCE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIsNotNull() {
            addCriterion("SOURCE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSourceTypeEqualTo(Integer value) {
            addCriterion("SOURCE_TYPE =", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotEqualTo(Integer value) {
            addCriterion("SOURCE_TYPE <>", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThan(Integer value) {
            addCriterion("SOURCE_TYPE >", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("SOURCE_TYPE >=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThan(Integer value) {
            addCriterion("SOURCE_TYPE <", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("SOURCE_TYPE <=", value, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeIn(List<Integer> values) {
            addCriterion("SOURCE_TYPE in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotIn(List<Integer> values) {
            addCriterion("SOURCE_TYPE not in", values, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeBetween(Integer value1, Integer value2) {
            addCriterion("SOURCE_TYPE between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andSourceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("SOURCE_TYPE not between", value1, value2, "sourceType");
            return (Criteria) this;
        }

        public Criteria andBillIdIsNull() {
            addCriterion("BILL_ID is null");
            return (Criteria) this;
        }

        public Criteria andBillIdIsNotNull() {
            addCriterion("BILL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBillIdEqualTo(Long value) {
            addCriterion("BILL_ID =", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotEqualTo(Long value) {
            addCriterion("BILL_ID <>", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThan(Long value) {
            addCriterion("BILL_ID >", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BILL_ID >=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThan(Long value) {
            addCriterion("BILL_ID <", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdLessThanOrEqualTo(Long value) {
            addCriterion("BILL_ID <=", value, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdIn(List<Long> values) {
            addCriterion("BILL_ID in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotIn(List<Long> values) {
            addCriterion("BILL_ID not in", values, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdBetween(Long value1, Long value2) {
            addCriterion("BILL_ID between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andBillIdNotBetween(Long value1, Long value2) {
            addCriterion("BILL_ID not between", value1, value2, "billId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIsNull() {
            addCriterion("PAYMENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIsNotNull() {
            addCriterion("PAYMENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentIdEqualTo(Long value) {
            addCriterion("PAYMENT_ID =", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotEqualTo(Long value) {
            addCriterion("PAYMENT_ID <>", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdGreaterThan(Long value) {
            addCriterion("PAYMENT_ID >", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PAYMENT_ID >=", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdLessThan(Long value) {
            addCriterion("PAYMENT_ID <", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdLessThanOrEqualTo(Long value) {
            addCriterion("PAYMENT_ID <=", value, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdIn(List<Long> values) {
            addCriterion("PAYMENT_ID in", values, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotIn(List<Long> values) {
            addCriterion("PAYMENT_ID not in", values, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdBetween(Long value1, Long value2) {
            addCriterion("PAYMENT_ID between", value1, value2, "paymentId");
            return (Criteria) this;
        }

        public Criteria andPaymentIdNotBetween(Long value1, Long value2) {
            addCriterion("PAYMENT_ID not between", value1, value2, "paymentId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdIsNull() {
            addCriterion("ACCT_ITEM_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdIsNotNull() {
            addCriterion("ACCT_ITEM_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdEqualTo(Long value) {
            addCriterion("ACCT_ITEM_TYPE_ID =", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdNotEqualTo(Long value) {
            addCriterion("ACCT_ITEM_TYPE_ID <>", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdGreaterThan(Long value) {
            addCriterion("ACCT_ITEM_TYPE_ID >", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ACCT_ITEM_TYPE_ID >=", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdLessThan(Long value) {
            addCriterion("ACCT_ITEM_TYPE_ID <", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("ACCT_ITEM_TYPE_ID <=", value, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdIn(List<Long> values) {
            addCriterion("ACCT_ITEM_TYPE_ID in", values, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdNotIn(List<Long> values) {
            addCriterion("ACCT_ITEM_TYPE_ID not in", values, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdBetween(Long value1, Long value2) {
            addCriterion("ACCT_ITEM_TYPE_ID between", value1, value2, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andAcctItemTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("ACCT_ITEM_TYPE_ID not between", value1, value2, "acctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdIsNull() {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdIsNotNull() {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdEqualTo(Long value) {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID =", value, "billAcctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdNotEqualTo(Long value) {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID <>", value, "billAcctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdGreaterThan(Long value) {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID >", value, "billAcctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID >=", value, "billAcctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdLessThan(Long value) {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID <", value, "billAcctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID <=", value, "billAcctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdIn(List<Long> values) {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID in", values, "billAcctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdNotIn(List<Long> values) {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID not in", values, "billAcctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdBetween(Long value1, Long value2) {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID between", value1, value2, "billAcctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andBillAcctItemTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("BILL_ACCT_ITEM_TYPE_ID not between", value1, value2, "billAcctItemTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdIsNull() {
            addCriterion("BALANCE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdIsNotNull() {
            addCriterion("BALANCE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdEqualTo(Long value) {
            addCriterion("BALANCE_TYPE_ID =", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdNotEqualTo(Long value) {
            addCriterion("BALANCE_TYPE_ID <>", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdGreaterThan(Long value) {
            addCriterion("BALANCE_TYPE_ID >", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BALANCE_TYPE_ID >=", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdLessThan(Long value) {
            addCriterion("BALANCE_TYPE_ID <", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("BALANCE_TYPE_ID <=", value, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdIn(List<Long> values) {
            addCriterion("BALANCE_TYPE_ID in", values, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdNotIn(List<Long> values) {
            addCriterion("BALANCE_TYPE_ID not in", values, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdBetween(Long value1, Long value2) {
            addCriterion("BALANCE_TYPE_ID between", value1, value2, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("BALANCE_TYPE_ID not between", value1, value2, "balanceTypeId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdIsNull() {
            addCriterion("BILLING_CYCLE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdIsNotNull() {
            addCriterion("BILLING_CYCLE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdEqualTo(Long value) {
            addCriterion("BILLING_CYCLE_ID =", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdNotEqualTo(Long value) {
            addCriterion("BILLING_CYCLE_ID <>", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdGreaterThan(Long value) {
            addCriterion("BILLING_CYCLE_ID >", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BILLING_CYCLE_ID >=", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdLessThan(Long value) {
            addCriterion("BILLING_CYCLE_ID <", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdLessThanOrEqualTo(Long value) {
            addCriterion("BILLING_CYCLE_ID <=", value, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdIn(List<Long> values) {
            addCriterion("BILLING_CYCLE_ID in", values, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdNotIn(List<Long> values) {
            addCriterion("BILLING_CYCLE_ID not in", values, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdBetween(Long value1, Long value2) {
            addCriterion("BILLING_CYCLE_ID between", value1, value2, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andBillingCycleIdNotBetween(Long value1, Long value2) {
            addCriterion("BILLING_CYCLE_ID not between", value1, value2, "billingCycleId");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdIsNull() {
            addCriterion("ITEM_SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdIsNotNull() {
            addCriterion("ITEM_SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdEqualTo(Long value) {
            addCriterion("ITEM_SOURCE_ID =", value, "itemSourceId");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdNotEqualTo(Long value) {
            addCriterion("ITEM_SOURCE_ID <>", value, "itemSourceId");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdGreaterThan(Long value) {
            addCriterion("ITEM_SOURCE_ID >", value, "itemSourceId");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ITEM_SOURCE_ID >=", value, "itemSourceId");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdLessThan(Long value) {
            addCriterion("ITEM_SOURCE_ID <", value, "itemSourceId");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdLessThanOrEqualTo(Long value) {
            addCriterion("ITEM_SOURCE_ID <=", value, "itemSourceId");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdIn(List<Long> values) {
            addCriterion("ITEM_SOURCE_ID in", values, "itemSourceId");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdNotIn(List<Long> values) {
            addCriterion("ITEM_SOURCE_ID not in", values, "itemSourceId");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdBetween(Long value1, Long value2) {
            addCriterion("ITEM_SOURCE_ID between", value1, value2, "itemSourceId");
            return (Criteria) this;
        }

        public Criteria andItemSourceIdNotBetween(Long value1, Long value2) {
            addCriterion("ITEM_SOURCE_ID not between", value1, value2, "itemSourceId");
            return (Criteria) this;
        }

        public Criteria andAcctIdIsNull() {
            addCriterion("ACCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAcctIdIsNotNull() {
            addCriterion("ACCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAcctIdEqualTo(Long value) {
            addCriterion("ACCT_ID =", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotEqualTo(Long value) {
            addCriterion("ACCT_ID <>", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThan(Long value) {
            addCriterion("ACCT_ID >", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ACCT_ID >=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThan(Long value) {
            addCriterion("ACCT_ID <", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThanOrEqualTo(Long value) {
            addCriterion("ACCT_ID <=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdIn(List<Long> values) {
            addCriterion("ACCT_ID in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotIn(List<Long> values) {
            addCriterion("ACCT_ID not in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdBetween(Long value1, Long value2) {
            addCriterion("ACCT_ID between", value1, value2, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotBetween(Long value1, Long value2) {
            addCriterion("ACCT_ID not between", value1, value2, "acctId");
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

        public Criteria andRealAmountIsNull() {
            addCriterion("REAL_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andRealAmountIsNotNull() {
            addCriterion("REAL_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andRealAmountEqualTo(Long value) {
            addCriterion("REAL_AMOUNT =", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountNotEqualTo(Long value) {
            addCriterion("REAL_AMOUNT <>", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountGreaterThan(Long value) {
            addCriterion("REAL_AMOUNT >", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("REAL_AMOUNT >=", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountLessThan(Long value) {
            addCriterion("REAL_AMOUNT <", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountLessThanOrEqualTo(Long value) {
            addCriterion("REAL_AMOUNT <=", value, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountIn(List<Long> values) {
            addCriterion("REAL_AMOUNT in", values, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountNotIn(List<Long> values) {
            addCriterion("REAL_AMOUNT not in", values, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountBetween(Long value1, Long value2) {
            addCriterion("REAL_AMOUNT between", value1, value2, "realAmount");
            return (Criteria) this;
        }

        public Criteria andRealAmountNotBetween(Long value1, Long value2) {
            addCriterion("REAL_AMOUNT not between", value1, value2, "realAmount");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdIsNull() {
            addCriterion("TAX_RATE_CONFIG_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdIsNotNull() {
            addCriterion("TAX_RATE_CONFIG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdEqualTo(Long value) {
            addCriterion("TAX_RATE_CONFIG_ID =", value, "taxRateConfigId");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdNotEqualTo(Long value) {
            addCriterion("TAX_RATE_CONFIG_ID <>", value, "taxRateConfigId");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdGreaterThan(Long value) {
            addCriterion("TAX_RATE_CONFIG_ID >", value, "taxRateConfigId");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TAX_RATE_CONFIG_ID >=", value, "taxRateConfigId");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdLessThan(Long value) {
            addCriterion("TAX_RATE_CONFIG_ID <", value, "taxRateConfigId");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdLessThanOrEqualTo(Long value) {
            addCriterion("TAX_RATE_CONFIG_ID <=", value, "taxRateConfigId");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdIn(List<Long> values) {
            addCriterion("TAX_RATE_CONFIG_ID in", values, "taxRateConfigId");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdNotIn(List<Long> values) {
            addCriterion("TAX_RATE_CONFIG_ID not in", values, "taxRateConfigId");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdBetween(Long value1, Long value2) {
            addCriterion("TAX_RATE_CONFIG_ID between", value1, value2, "taxRateConfigId");
            return (Criteria) this;
        }

        public Criteria andTaxRateConfigIdNotBetween(Long value1, Long value2) {
            addCriterion("TAX_RATE_CONFIG_ID not between", value1, value2, "taxRateConfigId");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNull() {
            addCriterion("TAX_RATE is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNotNull() {
            addCriterion("TAX_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateEqualTo(BigDecimal value) {
            addCriterion("TAX_RATE =", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotEqualTo(BigDecimal value) {
            addCriterion("TAX_RATE <>", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThan(BigDecimal value) {
            addCriterion("TAX_RATE >", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TAX_RATE >=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThan(BigDecimal value) {
            addCriterion("TAX_RATE <", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TAX_RATE <=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateIn(List<BigDecimal> values) {
            addCriterion("TAX_RATE in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotIn(List<BigDecimal> values) {
            addCriterion("TAX_RATE not in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TAX_RATE between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TAX_RATE not between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxIsNull() {
            addCriterion("TAX is null");
            return (Criteria) this;
        }

        public Criteria andTaxIsNotNull() {
            addCriterion("TAX is not null");
            return (Criteria) this;
        }

        public Criteria andTaxEqualTo(Long value) {
            addCriterion("TAX =", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotEqualTo(Long value) {
            addCriterion("TAX <>", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThan(Long value) {
            addCriterion("TAX >", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThanOrEqualTo(Long value) {
            addCriterion("TAX >=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThan(Long value) {
            addCriterion("TAX <", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThanOrEqualTo(Long value) {
            addCriterion("TAX <=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxIn(List<Long> values) {
            addCriterion("TAX in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotIn(List<Long> values) {
            addCriterion("TAX not in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxBetween(Long value1, Long value2) {
            addCriterion("TAX between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotBetween(Long value1, Long value2) {
            addCriterion("TAX not between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("PRICE is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Long value) {
            addCriterion("PRICE =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Long value) {
            addCriterion("PRICE <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Long value) {
            addCriterion("PRICE >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("PRICE >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Long value) {
            addCriterion("PRICE <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Long value) {
            addCriterion("PRICE <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Long> values) {
            addCriterion("PRICE in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Long> values) {
            addCriterion("PRICE not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Long value1, Long value2) {
            addCriterion("PRICE between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Long value1, Long value2) {
            addCriterion("PRICE not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeIsNull() {
            addCriterion("RESULT_ITEM_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeIsNotNull() {
            addCriterion("RESULT_ITEM_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeEqualTo(Integer value) {
            addCriterion("RESULT_ITEM_TYPE =", value, "resultItemType");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeNotEqualTo(Integer value) {
            addCriterion("RESULT_ITEM_TYPE <>", value, "resultItemType");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeGreaterThan(Integer value) {
            addCriterion("RESULT_ITEM_TYPE >", value, "resultItemType");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("RESULT_ITEM_TYPE >=", value, "resultItemType");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeLessThan(Integer value) {
            addCriterion("RESULT_ITEM_TYPE <", value, "resultItemType");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeLessThanOrEqualTo(Integer value) {
            addCriterion("RESULT_ITEM_TYPE <=", value, "resultItemType");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeIn(List<Integer> values) {
            addCriterion("RESULT_ITEM_TYPE in", values, "resultItemType");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeNotIn(List<Integer> values) {
            addCriterion("RESULT_ITEM_TYPE not in", values, "resultItemType");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeBetween(Integer value1, Integer value2) {
            addCriterion("RESULT_ITEM_TYPE between", value1, value2, "resultItemType");
            return (Criteria) this;
        }

        public Criteria andResultItemTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("RESULT_ITEM_TYPE not between", value1, value2, "resultItemType");
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

        public Criteria andChargeMethodIsNull() {
            addCriterion("CHARGE_METHOD is null");
            return (Criteria) this;
        }

        public Criteria andChargeMethodIsNotNull() {
            addCriterion("CHARGE_METHOD is not null");
            return (Criteria) this;
        }

        public Criteria andChargeMethodEqualTo(Long value) {
            addCriterion("CHARGE_METHOD =", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodNotEqualTo(Long value) {
            addCriterion("CHARGE_METHOD <>", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodGreaterThan(Long value) {
            addCriterion("CHARGE_METHOD >", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodGreaterThanOrEqualTo(Long value) {
            addCriterion("CHARGE_METHOD >=", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodLessThan(Long value) {
            addCriterion("CHARGE_METHOD <", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodLessThanOrEqualTo(Long value) {
            addCriterion("CHARGE_METHOD <=", value, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodIn(List<Long> values) {
            addCriterion("CHARGE_METHOD in", values, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodNotIn(List<Long> values) {
            addCriterion("CHARGE_METHOD not in", values, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodBetween(Long value1, Long value2) {
            addCriterion("CHARGE_METHOD between", value1, value2, "chargeMethod");
            return (Criteria) this;
        }

        public Criteria andChargeMethodNotBetween(Long value1, Long value2) {
            addCriterion("CHARGE_METHOD not between", value1, value2, "chargeMethod");
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

        public Criteria andOriAcctItemIdIsNull() {
            addCriterion("ORI_ACCT_ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andOriAcctItemIdIsNotNull() {
            addCriterion("ORI_ACCT_ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOriAcctItemIdEqualTo(Long value) {
            addCriterion("ORI_ACCT_ITEM_ID =", value, "oriAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOriAcctItemIdNotEqualTo(Long value) {
            addCriterion("ORI_ACCT_ITEM_ID <>", value, "oriAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOriAcctItemIdGreaterThan(Long value) {
            addCriterion("ORI_ACCT_ITEM_ID >", value, "oriAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOriAcctItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORI_ACCT_ITEM_ID >=", value, "oriAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOriAcctItemIdLessThan(Long value) {
            addCriterion("ORI_ACCT_ITEM_ID <", value, "oriAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOriAcctItemIdLessThanOrEqualTo(Long value) {
            addCriterion("ORI_ACCT_ITEM_ID <=", value, "oriAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOriAcctItemIdIn(List<Long> values) {
            addCriterion("ORI_ACCT_ITEM_ID in", values, "oriAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOriAcctItemIdNotIn(List<Long> values) {
            addCriterion("ORI_ACCT_ITEM_ID not in", values, "oriAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOriAcctItemIdBetween(Long value1, Long value2) {
            addCriterion("ORI_ACCT_ITEM_ID between", value1, value2, "oriAcctItemId");
            return (Criteria) this;
        }

        public Criteria andOriAcctItemIdNotBetween(Long value1, Long value2) {
            addCriterion("ORI_ACCT_ITEM_ID not between", value1, value2, "oriAcctItemId");
            return (Criteria) this;
        }

        public Criteria andLanIdIsNull() {
            addCriterion("LAN_ID is null");
            return (Criteria) this;
        }

        public Criteria andLanIdIsNotNull() {
            addCriterion("LAN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLanIdEqualTo(Long value) {
            addCriterion("LAN_ID =", value, "lanId");
            return (Criteria) this;
        }

        public Criteria andLanIdNotEqualTo(Long value) {
            addCriterion("LAN_ID <>", value, "lanId");
            return (Criteria) this;
        }

        public Criteria andLanIdGreaterThan(Long value) {
            addCriterion("LAN_ID >", value, "lanId");
            return (Criteria) this;
        }

        public Criteria andLanIdGreaterThanOrEqualTo(Long value) {
            addCriterion("LAN_ID >=", value, "lanId");
            return (Criteria) this;
        }

        public Criteria andLanIdLessThan(Long value) {
            addCriterion("LAN_ID <", value, "lanId");
            return (Criteria) this;
        }

        public Criteria andLanIdLessThanOrEqualTo(Long value) {
            addCriterion("LAN_ID <=", value, "lanId");
            return (Criteria) this;
        }

        public Criteria andLanIdIn(List<Long> values) {
            addCriterion("LAN_ID in", values, "lanId");
            return (Criteria) this;
        }

        public Criteria andLanIdNotIn(List<Long> values) {
            addCriterion("LAN_ID not in", values, "lanId");
            return (Criteria) this;
        }

        public Criteria andLanIdBetween(Long value1, Long value2) {
            addCriterion("LAN_ID between", value1, value2, "lanId");
            return (Criteria) this;
        }

        public Criteria andLanIdNotBetween(Long value1, Long value2) {
            addCriterion("LAN_ID not between", value1, value2, "lanId");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffIsNull() {
            addCriterion("IF_CHARGE_OFF is null");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffIsNotNull() {
            addCriterion("IF_CHARGE_OFF is not null");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffEqualTo(Integer value) {
            addCriterion("IF_CHARGE_OFF =", value, "ifChargeOff");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffNotEqualTo(Integer value) {
            addCriterion("IF_CHARGE_OFF <>", value, "ifChargeOff");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffGreaterThan(Integer value) {
            addCriterion("IF_CHARGE_OFF >", value, "ifChargeOff");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffGreaterThanOrEqualTo(Integer value) {
            addCriterion("IF_CHARGE_OFF >=", value, "ifChargeOff");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffLessThan(Integer value) {
            addCriterion("IF_CHARGE_OFF <", value, "ifChargeOff");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffLessThanOrEqualTo(Integer value) {
            addCriterion("IF_CHARGE_OFF <=", value, "ifChargeOff");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffIn(List<Integer> values) {
            addCriterion("IF_CHARGE_OFF in", values, "ifChargeOff");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffNotIn(List<Integer> values) {
            addCriterion("IF_CHARGE_OFF not in", values, "ifChargeOff");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffBetween(Integer value1, Integer value2) {
            addCriterion("IF_CHARGE_OFF between", value1, value2, "ifChargeOff");
            return (Criteria) this;
        }

        public Criteria andIfChargeOffNotBetween(Integer value1, Integer value2) {
            addCriterion("IF_CHARGE_OFF not between", value1, value2, "ifChargeOff");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeIsNull() {
            addCriterion("MERGE_ACCT_DEAL_TIME is null");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeIsNotNull() {
            addCriterion("MERGE_ACCT_DEAL_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeEqualTo(Date value) {
            addCriterion("MERGE_ACCT_DEAL_TIME =", value, "mergeAcctDealTime");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeNotEqualTo(Date value) {
            addCriterion("MERGE_ACCT_DEAL_TIME <>", value, "mergeAcctDealTime");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeGreaterThan(Date value) {
            addCriterion("MERGE_ACCT_DEAL_TIME >", value, "mergeAcctDealTime");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("MERGE_ACCT_DEAL_TIME >=", value, "mergeAcctDealTime");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeLessThan(Date value) {
            addCriterion("MERGE_ACCT_DEAL_TIME <", value, "mergeAcctDealTime");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeLessThanOrEqualTo(Date value) {
            addCriterion("MERGE_ACCT_DEAL_TIME <=", value, "mergeAcctDealTime");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeIn(List<Date> values) {
            addCriterion("MERGE_ACCT_DEAL_TIME in", values, "mergeAcctDealTime");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeNotIn(List<Date> values) {
            addCriterion("MERGE_ACCT_DEAL_TIME not in", values, "mergeAcctDealTime");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeBetween(Date value1, Date value2) {
            addCriterion("MERGE_ACCT_DEAL_TIME between", value1, value2, "mergeAcctDealTime");
            return (Criteria) this;
        }

        public Criteria andMergeAcctDealTimeNotBetween(Date value1, Date value2) {
            addCriterion("MERGE_ACCT_DEAL_TIME not between", value1, value2, "mergeAcctDealTime");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountIsNull() {
            addCriterion("PAID_IN_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountIsNotNull() {
            addCriterion("PAID_IN_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountEqualTo(Long value) {
            addCriterion("PAID_IN_AMOUNT =", value, "paidInAmount");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountNotEqualTo(Long value) {
            addCriterion("PAID_IN_AMOUNT <>", value, "paidInAmount");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountGreaterThan(Long value) {
            addCriterion("PAID_IN_AMOUNT >", value, "paidInAmount");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("PAID_IN_AMOUNT >=", value, "paidInAmount");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountLessThan(Long value) {
            addCriterion("PAID_IN_AMOUNT <", value, "paidInAmount");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountLessThanOrEqualTo(Long value) {
            addCriterion("PAID_IN_AMOUNT <=", value, "paidInAmount");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountIn(List<Long> values) {
            addCriterion("PAID_IN_AMOUNT in", values, "paidInAmount");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountNotIn(List<Long> values) {
            addCriterion("PAID_IN_AMOUNT not in", values, "paidInAmount");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountBetween(Long value1, Long value2) {
            addCriterion("PAID_IN_AMOUNT between", value1, value2, "paidInAmount");
            return (Criteria) this;
        }

        public Criteria andPaidInAmountNotBetween(Long value1, Long value2) {
            addCriterion("PAID_IN_AMOUNT not between", value1, value2, "paidInAmount");
            return (Criteria) this;
        }

        public Criteria andReceiptIdIsNull() {
            addCriterion("RECEIPT_ID is null");
            return (Criteria) this;
        }

        public Criteria andReceiptIdIsNotNull() {
            addCriterion("RECEIPT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptIdEqualTo(Long value) {
            addCriterion("RECEIPT_ID =", value, "receiptId");
            return (Criteria) this;
        }

        public Criteria andReceiptIdNotEqualTo(Long value) {
            addCriterion("RECEIPT_ID <>", value, "receiptId");
            return (Criteria) this;
        }

        public Criteria andReceiptIdGreaterThan(Long value) {
            addCriterion("RECEIPT_ID >", value, "receiptId");
            return (Criteria) this;
        }

        public Criteria andReceiptIdGreaterThanOrEqualTo(Long value) {
            addCriterion("RECEIPT_ID >=", value, "receiptId");
            return (Criteria) this;
        }

        public Criteria andReceiptIdLessThan(Long value) {
            addCriterion("RECEIPT_ID <", value, "receiptId");
            return (Criteria) this;
        }

        public Criteria andReceiptIdLessThanOrEqualTo(Long value) {
            addCriterion("RECEIPT_ID <=", value, "receiptId");
            return (Criteria) this;
        }

        public Criteria andReceiptIdIn(List<Long> values) {
            addCriterion("RECEIPT_ID in", values, "receiptId");
            return (Criteria) this;
        }

        public Criteria andReceiptIdNotIn(List<Long> values) {
            addCriterion("RECEIPT_ID not in", values, "receiptId");
            return (Criteria) this;
        }

        public Criteria andReceiptIdBetween(Long value1, Long value2) {
            addCriterion("RECEIPT_ID between", value1, value2, "receiptId");
            return (Criteria) this;
        }

        public Criteria andReceiptIdNotBetween(Long value1, Long value2) {
            addCriterion("RECEIPT_ID not between", value1, value2, "receiptId");
            return (Criteria) this;
        }

        public Criteria andBusiNameIsNull() {
            addCriterion("BUSI_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBusiNameIsNotNull() {
            addCriterion("BUSI_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBusiNameEqualTo(String value) {
            addCriterion("BUSI_NAME =", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameNotEqualTo(String value) {
            addCriterion("BUSI_NAME <>", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameGreaterThan(String value) {
            addCriterion("BUSI_NAME >", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameGreaterThanOrEqualTo(String value) {
            addCriterion("BUSI_NAME >=", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameLessThan(String value) {
            addCriterion("BUSI_NAME <", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameLessThanOrEqualTo(String value) {
            addCriterion("BUSI_NAME <=", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameLike(String value) {
            addCriterion("BUSI_NAME like", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameNotLike(String value) {
            addCriterion("BUSI_NAME not like", value, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameIn(List<String> values) {
            addCriterion("BUSI_NAME in", values, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameNotIn(List<String> values) {
            addCriterion("BUSI_NAME not in", values, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameBetween(String value1, String value2) {
            addCriterion("BUSI_NAME between", value1, value2, "busiName");
            return (Criteria) this;
        }

        public Criteria andBusiNameNotBetween(String value1, String value2) {
            addCriterion("BUSI_NAME not between", value1, value2, "busiName");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdIsNull() {
            addCriterion("EXT_ACCT_ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdIsNotNull() {
            addCriterion("EXT_ACCT_ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdEqualTo(String value) {
            addCriterion("EXT_ACCT_ITEM_ID =", value, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdNotEqualTo(String value) {
            addCriterion("EXT_ACCT_ITEM_ID <>", value, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdGreaterThan(String value) {
            addCriterion("EXT_ACCT_ITEM_ID >", value, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXT_ACCT_ITEM_ID >=", value, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdLessThan(String value) {
            addCriterion("EXT_ACCT_ITEM_ID <", value, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdLessThanOrEqualTo(String value) {
            addCriterion("EXT_ACCT_ITEM_ID <=", value, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdLike(String value) {
            addCriterion("EXT_ACCT_ITEM_ID like", value, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdNotLike(String value) {
            addCriterion("EXT_ACCT_ITEM_ID not like", value, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdIn(List<String> values) {
            addCriterion("EXT_ACCT_ITEM_ID in", values, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdNotIn(List<String> values) {
            addCriterion("EXT_ACCT_ITEM_ID not in", values, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdBetween(String value1, String value2) {
            addCriterion("EXT_ACCT_ITEM_ID between", value1, value2, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andExtAcctItemIdNotBetween(String value1, String value2) {
            addCriterion("EXT_ACCT_ITEM_ID not between", value1, value2, "extAcctItemId");
            return (Criteria) this;
        }

        public Criteria andProdIdIsNull() {
            addCriterion("PROD_ID is null");
            return (Criteria) this;
        }

        public Criteria andProdIdIsNotNull() {
            addCriterion("PROD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProdIdEqualTo(Long value) {
            addCriterion("PROD_ID =", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotEqualTo(Long value) {
            addCriterion("PROD_ID <>", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThan(Long value) {
            addCriterion("PROD_ID >", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PROD_ID >=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThan(Long value) {
            addCriterion("PROD_ID <", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdLessThanOrEqualTo(Long value) {
            addCriterion("PROD_ID <=", value, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdIn(List<Long> values) {
            addCriterion("PROD_ID in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotIn(List<Long> values) {
            addCriterion("PROD_ID not in", values, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdBetween(Long value1, Long value2) {
            addCriterion("PROD_ID between", value1, value2, "prodId");
            return (Criteria) this;
        }

        public Criteria andProdIdNotBetween(Long value1, Long value2) {
            addCriterion("PROD_ID not between", value1, value2, "prodId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNull() {
            addCriterion("STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andStaffIdIsNotNull() {
            addCriterion("STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andStaffIdEqualTo(Long value) {
            addCriterion("STAFF_ID =", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotEqualTo(Long value) {
            addCriterion("STAFF_ID <>", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThan(Long value) {
            addCriterion("STAFF_ID >", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdGreaterThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID >=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThan(Long value) {
            addCriterion("STAFF_ID <", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdLessThanOrEqualTo(Long value) {
            addCriterion("STAFF_ID <=", value, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdIn(List<Long> values) {
            addCriterion("STAFF_ID in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotIn(List<Long> values) {
            addCriterion("STAFF_ID not in", values, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andStaffIdNotBetween(Long value1, Long value2) {
            addCriterion("STAFF_ID not between", value1, value2, "staffId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(Long value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(Long value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(Long value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(Long value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(Long value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<Long> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<Long> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(Long value1, Long value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(Long value1, Long value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andProcFlagIsNull() {
            addCriterion("PROC_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andProcFlagIsNotNull() {
            addCriterion("PROC_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andProcFlagEqualTo(Integer value) {
            addCriterion("PROC_FLAG =", value, "procFlag");
            return (Criteria) this;
        }

        public Criteria andProcFlagNotEqualTo(Integer value) {
            addCriterion("PROC_FLAG <>", value, "procFlag");
            return (Criteria) this;
        }

        public Criteria andProcFlagGreaterThan(Integer value) {
            addCriterion("PROC_FLAG >", value, "procFlag");
            return (Criteria) this;
        }

        public Criteria andProcFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("PROC_FLAG >=", value, "procFlag");
            return (Criteria) this;
        }

        public Criteria andProcFlagLessThan(Integer value) {
            addCriterion("PROC_FLAG <", value, "procFlag");
            return (Criteria) this;
        }

        public Criteria andProcFlagLessThanOrEqualTo(Integer value) {
            addCriterion("PROC_FLAG <=", value, "procFlag");
            return (Criteria) this;
        }

        public Criteria andProcFlagIn(List<Integer> values) {
            addCriterion("PROC_FLAG in", values, "procFlag");
            return (Criteria) this;
        }

        public Criteria andProcFlagNotIn(List<Integer> values) {
            addCriterion("PROC_FLAG not in", values, "procFlag");
            return (Criteria) this;
        }

        public Criteria andProcFlagBetween(Integer value1, Integer value2) {
            addCriterion("PROC_FLAG between", value1, value2, "procFlag");
            return (Criteria) this;
        }

        public Criteria andProcFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("PROC_FLAG not between", value1, value2, "procFlag");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("REMARKS is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("REMARKS is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("REMARKS =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("REMARKS <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("REMARKS >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("REMARKS >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("REMARKS <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("REMARKS <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("REMARKS like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("REMARKS not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("REMARKS in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("REMARKS not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("REMARKS between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("REMARKS not between", value1, value2, "remarks");
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