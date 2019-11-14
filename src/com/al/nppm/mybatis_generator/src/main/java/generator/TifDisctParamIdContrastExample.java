package generator;

import java.util.ArrayList;
import java.util.List;

public class TifDisctParamIdContrastExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public TifDisctParamIdContrastExample() {
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

        public Criteria andIdNoIsNull() {
            addCriterion("ID_NO is null");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNotNull() {
            addCriterion("ID_NO is not null");
            return (Criteria) this;
        }

        public Criteria andIdNoEqualTo(Long value) {
            addCriterion("ID_NO =", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotEqualTo(Long value) {
            addCriterion("ID_NO <>", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThan(Long value) {
            addCriterion("ID_NO >", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThanOrEqualTo(Long value) {
            addCriterion("ID_NO >=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThan(Long value) {
            addCriterion("ID_NO <", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThanOrEqualTo(Long value) {
            addCriterion("ID_NO <=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoIn(List<Long> values) {
            addCriterion("ID_NO in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotIn(List<Long> values) {
            addCriterion("ID_NO not in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoBetween(Long value1, Long value2) {
            addCriterion("ID_NO between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotBetween(Long value1, Long value2) {
            addCriterion("ID_NO not between", value1, value2, "idNo");
            return (Criteria) this;
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

        public Criteria andDisctParamId97IsNull() {
            addCriterion("DISCT_PARAM_ID_97 is null");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97IsNotNull() {
            addCriterion("DISCT_PARAM_ID_97 is not null");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97EqualTo(String value) {
            addCriterion("DISCT_PARAM_ID_97 =", value, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97NotEqualTo(String value) {
            addCriterion("DISCT_PARAM_ID_97 <>", value, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97GreaterThan(String value) {
            addCriterion("DISCT_PARAM_ID_97 >", value, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97GreaterThanOrEqualTo(String value) {
            addCriterion("DISCT_PARAM_ID_97 >=", value, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97LessThan(String value) {
            addCriterion("DISCT_PARAM_ID_97 <", value, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97LessThanOrEqualTo(String value) {
            addCriterion("DISCT_PARAM_ID_97 <=", value, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97Like(String value) {
            addCriterion("DISCT_PARAM_ID_97 like", value, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97NotLike(String value) {
            addCriterion("DISCT_PARAM_ID_97 not like", value, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97In(List<String> values) {
            addCriterion("DISCT_PARAM_ID_97 in", values, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97NotIn(List<String> values) {
            addCriterion("DISCT_PARAM_ID_97 not in", values, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97Between(String value1, String value2) {
            addCriterion("DISCT_PARAM_ID_97 between", value1, value2, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamId97NotBetween(String value1, String value2) {
            addCriterion("DISCT_PARAM_ID_97 not between", value1, value2, "disctParamId97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97IsNull() {
            addCriterion("DISCT_PARAM_NAME_97 is null");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97IsNotNull() {
            addCriterion("DISCT_PARAM_NAME_97 is not null");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97EqualTo(String value) {
            addCriterion("DISCT_PARAM_NAME_97 =", value, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97NotEqualTo(String value) {
            addCriterion("DISCT_PARAM_NAME_97 <>", value, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97GreaterThan(String value) {
            addCriterion("DISCT_PARAM_NAME_97 >", value, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97GreaterThanOrEqualTo(String value) {
            addCriterion("DISCT_PARAM_NAME_97 >=", value, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97LessThan(String value) {
            addCriterion("DISCT_PARAM_NAME_97 <", value, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97LessThanOrEqualTo(String value) {
            addCriterion("DISCT_PARAM_NAME_97 <=", value, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97Like(String value) {
            addCriterion("DISCT_PARAM_NAME_97 like", value, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97NotLike(String value) {
            addCriterion("DISCT_PARAM_NAME_97 not like", value, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97In(List<String> values) {
            addCriterion("DISCT_PARAM_NAME_97 in", values, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97NotIn(List<String> values) {
            addCriterion("DISCT_PARAM_NAME_97 not in", values, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97Between(String value1, String value2) {
            addCriterion("DISCT_PARAM_NAME_97 between", value1, value2, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamName97NotBetween(String value1, String value2) {
            addCriterion("DISCT_PARAM_NAME_97 not between", value1, value2, "disctParamName97");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillIsNull() {
            addCriterion("DISCT_PARAM_ID_BILL is null");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillIsNotNull() {
            addCriterion("DISCT_PARAM_ID_BILL is not null");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillEqualTo(Long value) {
            addCriterion("DISCT_PARAM_ID_BILL =", value, "disctParamIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillNotEqualTo(Long value) {
            addCriterion("DISCT_PARAM_ID_BILL <>", value, "disctParamIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillGreaterThan(Long value) {
            addCriterion("DISCT_PARAM_ID_BILL >", value, "disctParamIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillGreaterThanOrEqualTo(Long value) {
            addCriterion("DISCT_PARAM_ID_BILL >=", value, "disctParamIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillLessThan(Long value) {
            addCriterion("DISCT_PARAM_ID_BILL <", value, "disctParamIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillLessThanOrEqualTo(Long value) {
            addCriterion("DISCT_PARAM_ID_BILL <=", value, "disctParamIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillIn(List<Long> values) {
            addCriterion("DISCT_PARAM_ID_BILL in", values, "disctParamIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillNotIn(List<Long> values) {
            addCriterion("DISCT_PARAM_ID_BILL not in", values, "disctParamIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillBetween(Long value1, Long value2) {
            addCriterion("DISCT_PARAM_ID_BILL between", value1, value2, "disctParamIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamIdBillNotBetween(Long value1, Long value2) {
            addCriterion("DISCT_PARAM_ID_BILL not between", value1, value2, "disctParamIdBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillIsNull() {
            addCriterion("DISCT_PARAM_NAME_BILL is null");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillIsNotNull() {
            addCriterion("DISCT_PARAM_NAME_BILL is not null");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillEqualTo(String value) {
            addCriterion("DISCT_PARAM_NAME_BILL =", value, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillNotEqualTo(String value) {
            addCriterion("DISCT_PARAM_NAME_BILL <>", value, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillGreaterThan(String value) {
            addCriterion("DISCT_PARAM_NAME_BILL >", value, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillGreaterThanOrEqualTo(String value) {
            addCriterion("DISCT_PARAM_NAME_BILL >=", value, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillLessThan(String value) {
            addCriterion("DISCT_PARAM_NAME_BILL <", value, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillLessThanOrEqualTo(String value) {
            addCriterion("DISCT_PARAM_NAME_BILL <=", value, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillLike(String value) {
            addCriterion("DISCT_PARAM_NAME_BILL like", value, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillNotLike(String value) {
            addCriterion("DISCT_PARAM_NAME_BILL not like", value, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillIn(List<String> values) {
            addCriterion("DISCT_PARAM_NAME_BILL in", values, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillNotIn(List<String> values) {
            addCriterion("DISCT_PARAM_NAME_BILL not in", values, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillBetween(String value1, String value2) {
            addCriterion("DISCT_PARAM_NAME_BILL between", value1, value2, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andDisctParamNameBillNotBetween(String value1, String value2) {
            addCriterion("DISCT_PARAM_NAME_BILL not between", value1, value2, "disctParamNameBill");
            return (Criteria) this;
        }

        public Criteria andAllowFlagIsNull() {
            addCriterion("ALLOW_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andAllowFlagIsNotNull() {
            addCriterion("ALLOW_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andAllowFlagEqualTo(String value) {
            addCriterion("ALLOW_FLAG =", value, "allowFlag");
            return (Criteria) this;
        }

        public Criteria andAllowFlagNotEqualTo(String value) {
            addCriterion("ALLOW_FLAG <>", value, "allowFlag");
            return (Criteria) this;
        }

        public Criteria andAllowFlagGreaterThan(String value) {
            addCriterion("ALLOW_FLAG >", value, "allowFlag");
            return (Criteria) this;
        }

        public Criteria andAllowFlagGreaterThanOrEqualTo(String value) {
            addCriterion("ALLOW_FLAG >=", value, "allowFlag");
            return (Criteria) this;
        }

        public Criteria andAllowFlagLessThan(String value) {
            addCriterion("ALLOW_FLAG <", value, "allowFlag");
            return (Criteria) this;
        }

        public Criteria andAllowFlagLessThanOrEqualTo(String value) {
            addCriterion("ALLOW_FLAG <=", value, "allowFlag");
            return (Criteria) this;
        }

        public Criteria andAllowFlagLike(String value) {
            addCriterion("ALLOW_FLAG like", value, "allowFlag");
            return (Criteria) this;
        }

        public Criteria andAllowFlagNotLike(String value) {
            addCriterion("ALLOW_FLAG not like", value, "allowFlag");
            return (Criteria) this;
        }

        public Criteria andAllowFlagIn(List<String> values) {
            addCriterion("ALLOW_FLAG in", values, "allowFlag");
            return (Criteria) this;
        }

        public Criteria andAllowFlagNotIn(List<String> values) {
            addCriterion("ALLOW_FLAG not in", values, "allowFlag");
            return (Criteria) this;
        }

        public Criteria andAllowFlagBetween(String value1, String value2) {
            addCriterion("ALLOW_FLAG between", value1, value2, "allowFlag");
            return (Criteria) this;
        }

        public Criteria andAllowFlagNotBetween(String value1, String value2) {
            addCriterion("ALLOW_FLAG not between", value1, value2, "allowFlag");
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

        public Criteria andIsRentIsNull() {
            addCriterion("IS_RENT is null");
            return (Criteria) this;
        }

        public Criteria andIsRentIsNotNull() {
            addCriterion("IS_RENT is not null");
            return (Criteria) this;
        }

        public Criteria andIsRentEqualTo(String value) {
            addCriterion("IS_RENT =", value, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsRentNotEqualTo(String value) {
            addCriterion("IS_RENT <>", value, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsRentGreaterThan(String value) {
            addCriterion("IS_RENT >", value, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsRentGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RENT >=", value, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsRentLessThan(String value) {
            addCriterion("IS_RENT <", value, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsRentLessThanOrEqualTo(String value) {
            addCriterion("IS_RENT <=", value, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsRentLike(String value) {
            addCriterion("IS_RENT like", value, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsRentNotLike(String value) {
            addCriterion("IS_RENT not like", value, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsRentIn(List<String> values) {
            addCriterion("IS_RENT in", values, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsRentNotIn(List<String> values) {
            addCriterion("IS_RENT not in", values, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsRentBetween(String value1, String value2) {
            addCriterion("IS_RENT between", value1, value2, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsRentNotBetween(String value1, String value2) {
            addCriterion("IS_RENT not between", value1, value2, "isRent");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeIsNull() {
            addCriterion("IS_NEXT_CHANGE is null");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeIsNotNull() {
            addCriterion("IS_NEXT_CHANGE is not null");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeEqualTo(Long value) {
            addCriterion("IS_NEXT_CHANGE =", value, "isNextChange");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeNotEqualTo(Long value) {
            addCriterion("IS_NEXT_CHANGE <>", value, "isNextChange");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeGreaterThan(Long value) {
            addCriterion("IS_NEXT_CHANGE >", value, "isNextChange");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeGreaterThanOrEqualTo(Long value) {
            addCriterion("IS_NEXT_CHANGE >=", value, "isNextChange");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeLessThan(Long value) {
            addCriterion("IS_NEXT_CHANGE <", value, "isNextChange");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeLessThanOrEqualTo(Long value) {
            addCriterion("IS_NEXT_CHANGE <=", value, "isNextChange");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeIn(List<Long> values) {
            addCriterion("IS_NEXT_CHANGE in", values, "isNextChange");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeNotIn(List<Long> values) {
            addCriterion("IS_NEXT_CHANGE not in", values, "isNextChange");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeBetween(Long value1, Long value2) {
            addCriterion("IS_NEXT_CHANGE between", value1, value2, "isNextChange");
            return (Criteria) this;
        }

        public Criteria andIsNextChangeNotBetween(Long value1, Long value2) {
            addCriterion("IS_NEXT_CHANGE not between", value1, value2, "isNextChange");
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