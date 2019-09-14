package generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public OfferExample() {
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

        public Criteria andOfferSysTypeIsNull() {
            addCriterion("OFFER_SYS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeIsNotNull() {
            addCriterion("OFFER_SYS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeEqualTo(String value) {
            addCriterion("OFFER_SYS_TYPE =", value, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeNotEqualTo(String value) {
            addCriterion("OFFER_SYS_TYPE <>", value, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeGreaterThan(String value) {
            addCriterion("OFFER_SYS_TYPE >", value, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeGreaterThanOrEqualTo(String value) {
            addCriterion("OFFER_SYS_TYPE >=", value, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeLessThan(String value) {
            addCriterion("OFFER_SYS_TYPE <", value, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeLessThanOrEqualTo(String value) {
            addCriterion("OFFER_SYS_TYPE <=", value, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeLike(String value) {
            addCriterion("OFFER_SYS_TYPE like", value, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeNotLike(String value) {
            addCriterion("OFFER_SYS_TYPE not like", value, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeIn(List<String> values) {
            addCriterion("OFFER_SYS_TYPE in", values, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeNotIn(List<String> values) {
            addCriterion("OFFER_SYS_TYPE not in", values, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeBetween(String value1, String value2) {
            addCriterion("OFFER_SYS_TYPE between", value1, value2, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferSysTypeNotBetween(String value1, String value2) {
            addCriterion("OFFER_SYS_TYPE not between", value1, value2, "offerSysType");
            return (Criteria) this;
        }

        public Criteria andOfferNameIsNull() {
            addCriterion("OFFER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOfferNameIsNotNull() {
            addCriterion("OFFER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOfferNameEqualTo(String value) {
            addCriterion("OFFER_NAME =", value, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferNameNotEqualTo(String value) {
            addCriterion("OFFER_NAME <>", value, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferNameGreaterThan(String value) {
            addCriterion("OFFER_NAME >", value, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferNameGreaterThanOrEqualTo(String value) {
            addCriterion("OFFER_NAME >=", value, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferNameLessThan(String value) {
            addCriterion("OFFER_NAME <", value, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferNameLessThanOrEqualTo(String value) {
            addCriterion("OFFER_NAME <=", value, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferNameLike(String value) {
            addCriterion("OFFER_NAME like", value, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferNameNotLike(String value) {
            addCriterion("OFFER_NAME not like", value, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferNameIn(List<String> values) {
            addCriterion("OFFER_NAME in", values, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferNameNotIn(List<String> values) {
            addCriterion("OFFER_NAME not in", values, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferNameBetween(String value1, String value2) {
            addCriterion("OFFER_NAME between", value1, value2, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferNameNotBetween(String value1, String value2) {
            addCriterion("OFFER_NAME not between", value1, value2, "offerName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameIsNull() {
            addCriterion("OFFER_SYS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameIsNotNull() {
            addCriterion("OFFER_SYS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameEqualTo(String value) {
            addCriterion("OFFER_SYS_NAME =", value, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameNotEqualTo(String value) {
            addCriterion("OFFER_SYS_NAME <>", value, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameGreaterThan(String value) {
            addCriterion("OFFER_SYS_NAME >", value, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameGreaterThanOrEqualTo(String value) {
            addCriterion("OFFER_SYS_NAME >=", value, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameLessThan(String value) {
            addCriterion("OFFER_SYS_NAME <", value, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameLessThanOrEqualTo(String value) {
            addCriterion("OFFER_SYS_NAME <=", value, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameLike(String value) {
            addCriterion("OFFER_SYS_NAME like", value, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameNotLike(String value) {
            addCriterion("OFFER_SYS_NAME not like", value, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameIn(List<String> values) {
            addCriterion("OFFER_SYS_NAME in", values, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameNotIn(List<String> values) {
            addCriterion("OFFER_SYS_NAME not in", values, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameBetween(String value1, String value2) {
            addCriterion("OFFER_SYS_NAME between", value1, value2, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysNameNotBetween(String value1, String value2) {
            addCriterion("OFFER_SYS_NAME not between", value1, value2, "offerSysName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameIsNull() {
            addCriterion("OFFER_SYS_PY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameIsNotNull() {
            addCriterion("OFFER_SYS_PY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameEqualTo(String value) {
            addCriterion("OFFER_SYS_PY_NAME =", value, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameNotEqualTo(String value) {
            addCriterion("OFFER_SYS_PY_NAME <>", value, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameGreaterThan(String value) {
            addCriterion("OFFER_SYS_PY_NAME >", value, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameGreaterThanOrEqualTo(String value) {
            addCriterion("OFFER_SYS_PY_NAME >=", value, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameLessThan(String value) {
            addCriterion("OFFER_SYS_PY_NAME <", value, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameLessThanOrEqualTo(String value) {
            addCriterion("OFFER_SYS_PY_NAME <=", value, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameLike(String value) {
            addCriterion("OFFER_SYS_PY_NAME like", value, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameNotLike(String value) {
            addCriterion("OFFER_SYS_PY_NAME not like", value, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameIn(List<String> values) {
            addCriterion("OFFER_SYS_PY_NAME in", values, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameNotIn(List<String> values) {
            addCriterion("OFFER_SYS_PY_NAME not in", values, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameBetween(String value1, String value2) {
            addCriterion("OFFER_SYS_PY_NAME between", value1, value2, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferSysPyNameNotBetween(String value1, String value2) {
            addCriterion("OFFER_SYS_PY_NAME not between", value1, value2, "offerSysPyName");
            return (Criteria) this;
        }

        public Criteria andOfferNbrIsNull() {
            addCriterion("OFFER_NBR is null");
            return (Criteria) this;
        }

        public Criteria andOfferNbrIsNotNull() {
            addCriterion("OFFER_NBR is not null");
            return (Criteria) this;
        }

        public Criteria andOfferNbrEqualTo(String value) {
            addCriterion("OFFER_NBR =", value, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andOfferNbrNotEqualTo(String value) {
            addCriterion("OFFER_NBR <>", value, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andOfferNbrGreaterThan(String value) {
            addCriterion("OFFER_NBR >", value, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andOfferNbrGreaterThanOrEqualTo(String value) {
            addCriterion("OFFER_NBR >=", value, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andOfferNbrLessThan(String value) {
            addCriterion("OFFER_NBR <", value, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andOfferNbrLessThanOrEqualTo(String value) {
            addCriterion("OFFER_NBR <=", value, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andOfferNbrLike(String value) {
            addCriterion("OFFER_NBR like", value, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andOfferNbrNotLike(String value) {
            addCriterion("OFFER_NBR not like", value, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andOfferNbrIn(List<String> values) {
            addCriterion("OFFER_NBR in", values, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andOfferNbrNotIn(List<String> values) {
            addCriterion("OFFER_NBR not in", values, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andOfferNbrBetween(String value1, String value2) {
            addCriterion("OFFER_NBR between", value1, value2, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andOfferNbrNotBetween(String value1, String value2) {
            addCriterion("OFFER_NBR not between", value1, value2, "offerNbr");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdIsNull() {
            addCriterion("EXT_OFFER_ID is null");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdIsNotNull() {
            addCriterion("EXT_OFFER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdEqualTo(String value) {
            addCriterion("EXT_OFFER_ID =", value, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdNotEqualTo(String value) {
            addCriterion("EXT_OFFER_ID <>", value, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdGreaterThan(String value) {
            addCriterion("EXT_OFFER_ID >", value, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXT_OFFER_ID >=", value, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdLessThan(String value) {
            addCriterion("EXT_OFFER_ID <", value, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdLessThanOrEqualTo(String value) {
            addCriterion("EXT_OFFER_ID <=", value, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdLike(String value) {
            addCriterion("EXT_OFFER_ID like", value, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdNotLike(String value) {
            addCriterion("EXT_OFFER_ID not like", value, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdIn(List<String> values) {
            addCriterion("EXT_OFFER_ID in", values, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdNotIn(List<String> values) {
            addCriterion("EXT_OFFER_ID not in", values, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdBetween(String value1, String value2) {
            addCriterion("EXT_OFFER_ID between", value1, value2, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andExtOfferIdNotBetween(String value1, String value2) {
            addCriterion("EXT_OFFER_ID not between", value1, value2, "extOfferId");
            return (Criteria) this;
        }

        public Criteria andOfferDescIsNull() {
            addCriterion("OFFER_DESC is null");
            return (Criteria) this;
        }

        public Criteria andOfferDescIsNotNull() {
            addCriterion("OFFER_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andOfferDescEqualTo(String value) {
            addCriterion("OFFER_DESC =", value, "offerDesc");
            return (Criteria) this;
        }

        public Criteria andOfferDescNotEqualTo(String value) {
            addCriterion("OFFER_DESC <>", value, "offerDesc");
            return (Criteria) this;
        }

        public Criteria andOfferDescGreaterThan(String value) {
            addCriterion("OFFER_DESC >", value, "offerDesc");
            return (Criteria) this;
        }

        public Criteria andOfferDescGreaterThanOrEqualTo(String value) {
            addCriterion("OFFER_DESC >=", value, "offerDesc");
            return (Criteria) this;
        }

        public Criteria andOfferDescLessThan(String value) {
            addCriterion("OFFER_DESC <", value, "offerDesc");
            return (Criteria) this;
        }

        public Criteria andOfferDescLessThanOrEqualTo(String value) {
            addCriterion("OFFER_DESC <=", value, "offerDesc");
            return (Criteria) this;
        }

        public Criteria andOfferDescLike(String value) {
            addCriterion("OFFER_DESC like", value, "offerDesc");
            return (Criteria) this;
        }

        public Criteria andOfferDescNotLike(String value) {
            addCriterion("OFFER_DESC not like", value, "offerDesc");
            return (Criteria) this;
        }

        public Criteria andOfferDescIn(List<String> values) {
            addCriterion("OFFER_DESC in", values, "offerDesc");
            return (Criteria) this;
        }

        public Criteria andOfferDescNotIn(List<String> values) {
            addCriterion("OFFER_DESC not in", values, "offerDesc");
            return (Criteria) this;
        }

        public Criteria andOfferDescBetween(String value1, String value2) {
            addCriterion("OFFER_DESC between", value1, value2, "offerDesc");
            return (Criteria) this;
        }

        public Criteria andOfferDescNotBetween(String value1, String value2) {
            addCriterion("OFFER_DESC not between", value1, value2, "offerDesc");
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

        public Criteria andManageGradeIsNull() {
            addCriterion("MANAGE_GRADE is null");
            return (Criteria) this;
        }

        public Criteria andManageGradeIsNotNull() {
            addCriterion("MANAGE_GRADE is not null");
            return (Criteria) this;
        }

        public Criteria andManageGradeEqualTo(String value) {
            addCriterion("MANAGE_GRADE =", value, "manageGrade");
            return (Criteria) this;
        }

        public Criteria andManageGradeNotEqualTo(String value) {
            addCriterion("MANAGE_GRADE <>", value, "manageGrade");
            return (Criteria) this;
        }

        public Criteria andManageGradeGreaterThan(String value) {
            addCriterion("MANAGE_GRADE >", value, "manageGrade");
            return (Criteria) this;
        }

        public Criteria andManageGradeGreaterThanOrEqualTo(String value) {
            addCriterion("MANAGE_GRADE >=", value, "manageGrade");
            return (Criteria) this;
        }

        public Criteria andManageGradeLessThan(String value) {
            addCriterion("MANAGE_GRADE <", value, "manageGrade");
            return (Criteria) this;
        }

        public Criteria andManageGradeLessThanOrEqualTo(String value) {
            addCriterion("MANAGE_GRADE <=", value, "manageGrade");
            return (Criteria) this;
        }

        public Criteria andManageGradeLike(String value) {
            addCriterion("MANAGE_GRADE like", value, "manageGrade");
            return (Criteria) this;
        }

        public Criteria andManageGradeNotLike(String value) {
            addCriterion("MANAGE_GRADE not like", value, "manageGrade");
            return (Criteria) this;
        }

        public Criteria andManageGradeIn(List<String> values) {
            addCriterion("MANAGE_GRADE in", values, "manageGrade");
            return (Criteria) this;
        }

        public Criteria andManageGradeNotIn(List<String> values) {
            addCriterion("MANAGE_GRADE not in", values, "manageGrade");
            return (Criteria) this;
        }

        public Criteria andManageGradeBetween(String value1, String value2) {
            addCriterion("MANAGE_GRADE between", value1, value2, "manageGrade");
            return (Criteria) this;
        }

        public Criteria andManageGradeNotBetween(String value1, String value2) {
            addCriterion("MANAGE_GRADE not between", value1, value2, "manageGrade");
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

        public Criteria andOfferProviderIdIsNull() {
            addCriterion("OFFER_PROVIDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdIsNotNull() {
            addCriterion("OFFER_PROVIDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdEqualTo(String value) {
            addCriterion("OFFER_PROVIDER_ID =", value, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdNotEqualTo(String value) {
            addCriterion("OFFER_PROVIDER_ID <>", value, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdGreaterThan(String value) {
            addCriterion("OFFER_PROVIDER_ID >", value, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdGreaterThanOrEqualTo(String value) {
            addCriterion("OFFER_PROVIDER_ID >=", value, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdLessThan(String value) {
            addCriterion("OFFER_PROVIDER_ID <", value, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdLessThanOrEqualTo(String value) {
            addCriterion("OFFER_PROVIDER_ID <=", value, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdLike(String value) {
            addCriterion("OFFER_PROVIDER_ID like", value, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdNotLike(String value) {
            addCriterion("OFFER_PROVIDER_ID not like", value, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdIn(List<String> values) {
            addCriterion("OFFER_PROVIDER_ID in", values, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdNotIn(List<String> values) {
            addCriterion("OFFER_PROVIDER_ID not in", values, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdBetween(String value1, String value2) {
            addCriterion("OFFER_PROVIDER_ID between", value1, value2, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andOfferProviderIdNotBetween(String value1, String value2) {
            addCriterion("OFFER_PROVIDER_ID not between", value1, value2, "offerProviderId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNull() {
            addCriterion("BRAND_ID is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("BRAND_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Long value) {
            addCriterion("BRAND_ID =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Long value) {
            addCriterion("BRAND_ID <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Long value) {
            addCriterion("BRAND_ID >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Long value) {
            addCriterion("BRAND_ID >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Long value) {
            addCriterion("BRAND_ID <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Long value) {
            addCriterion("BRAND_ID <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Long> values) {
            addCriterion("BRAND_ID in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Long> values) {
            addCriterion("BRAND_ID not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Long value1, Long value2) {
            addCriterion("BRAND_ID between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Long value1, Long value2) {
            addCriterion("BRAND_ID not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagIsNull() {
            addCriterion("VALUE_ADDED_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagIsNotNull() {
            addCriterion("VALUE_ADDED_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagEqualTo(String value) {
            addCriterion("VALUE_ADDED_FLAG =", value, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagNotEqualTo(String value) {
            addCriterion("VALUE_ADDED_FLAG <>", value, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagGreaterThan(String value) {
            addCriterion("VALUE_ADDED_FLAG >", value, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagGreaterThanOrEqualTo(String value) {
            addCriterion("VALUE_ADDED_FLAG >=", value, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagLessThan(String value) {
            addCriterion("VALUE_ADDED_FLAG <", value, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagLessThanOrEqualTo(String value) {
            addCriterion("VALUE_ADDED_FLAG <=", value, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagLike(String value) {
            addCriterion("VALUE_ADDED_FLAG like", value, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagNotLike(String value) {
            addCriterion("VALUE_ADDED_FLAG not like", value, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagIn(List<String> values) {
            addCriterion("VALUE_ADDED_FLAG in", values, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagNotIn(List<String> values) {
            addCriterion("VALUE_ADDED_FLAG not in", values, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagBetween(String value1, String value2) {
            addCriterion("VALUE_ADDED_FLAG between", value1, value2, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andValueAddedFlagNotBetween(String value1, String value2) {
            addCriterion("VALUE_ADDED_FLAG not between", value1, value2, "valueAddedFlag");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueIsNull() {
            addCriterion("INITIAL_CRED_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueIsNotNull() {
            addCriterion("INITIAL_CRED_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueEqualTo(Long value) {
            addCriterion("INITIAL_CRED_VALUE =", value, "initialCredValue");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueNotEqualTo(Long value) {
            addCriterion("INITIAL_CRED_VALUE <>", value, "initialCredValue");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueGreaterThan(Long value) {
            addCriterion("INITIAL_CRED_VALUE >", value, "initialCredValue");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueGreaterThanOrEqualTo(Long value) {
            addCriterion("INITIAL_CRED_VALUE >=", value, "initialCredValue");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueLessThan(Long value) {
            addCriterion("INITIAL_CRED_VALUE <", value, "initialCredValue");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueLessThanOrEqualTo(Long value) {
            addCriterion("INITIAL_CRED_VALUE <=", value, "initialCredValue");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueIn(List<Long> values) {
            addCriterion("INITIAL_CRED_VALUE in", values, "initialCredValue");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueNotIn(List<Long> values) {
            addCriterion("INITIAL_CRED_VALUE not in", values, "initialCredValue");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueBetween(Long value1, Long value2) {
            addCriterion("INITIAL_CRED_VALUE between", value1, value2, "initialCredValue");
            return (Criteria) this;
        }

        public Criteria andInitialCredValueNotBetween(Long value1, Long value2) {
            addCriterion("INITIAL_CRED_VALUE not between", value1, value2, "initialCredValue");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdIsNull() {
            addCriterion("PRICING_PLAN_ID is null");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdIsNotNull() {
            addCriterion("PRICING_PLAN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdEqualTo(Long value) {
            addCriterion("PRICING_PLAN_ID =", value, "pricingPlanId");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdNotEqualTo(Long value) {
            addCriterion("PRICING_PLAN_ID <>", value, "pricingPlanId");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdGreaterThan(Long value) {
            addCriterion("PRICING_PLAN_ID >", value, "pricingPlanId");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PRICING_PLAN_ID >=", value, "pricingPlanId");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdLessThan(Long value) {
            addCriterion("PRICING_PLAN_ID <", value, "pricingPlanId");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdLessThanOrEqualTo(Long value) {
            addCriterion("PRICING_PLAN_ID <=", value, "pricingPlanId");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdIn(List<Long> values) {
            addCriterion("PRICING_PLAN_ID in", values, "pricingPlanId");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdNotIn(List<Long> values) {
            addCriterion("PRICING_PLAN_ID not in", values, "pricingPlanId");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdBetween(Long value1, Long value2) {
            addCriterion("PRICING_PLAN_ID between", value1, value2, "pricingPlanId");
            return (Criteria) this;
        }

        public Criteria andPricingPlanIdNotBetween(Long value1, Long value2) {
            addCriterion("PRICING_PLAN_ID not between", value1, value2, "pricingPlanId");
            return (Criteria) this;
        }

        public Criteria andIsIndependentIsNull() {
            addCriterion("IS_INDEPENDENT is null");
            return (Criteria) this;
        }

        public Criteria andIsIndependentIsNotNull() {
            addCriterion("IS_INDEPENDENT is not null");
            return (Criteria) this;
        }

        public Criteria andIsIndependentEqualTo(String value) {
            addCriterion("IS_INDEPENDENT =", value, "isIndependent");
            return (Criteria) this;
        }

        public Criteria andIsIndependentNotEqualTo(String value) {
            addCriterion("IS_INDEPENDENT <>", value, "isIndependent");
            return (Criteria) this;
        }

        public Criteria andIsIndependentGreaterThan(String value) {
            addCriterion("IS_INDEPENDENT >", value, "isIndependent");
            return (Criteria) this;
        }

        public Criteria andIsIndependentGreaterThanOrEqualTo(String value) {
            addCriterion("IS_INDEPENDENT >=", value, "isIndependent");
            return (Criteria) this;
        }

        public Criteria andIsIndependentLessThan(String value) {
            addCriterion("IS_INDEPENDENT <", value, "isIndependent");
            return (Criteria) this;
        }

        public Criteria andIsIndependentLessThanOrEqualTo(String value) {
            addCriterion("IS_INDEPENDENT <=", value, "isIndependent");
            return (Criteria) this;
        }

        public Criteria andIsIndependentLike(String value) {
            addCriterion("IS_INDEPENDENT like", value, "isIndependent");
            return (Criteria) this;
        }

        public Criteria andIsIndependentNotLike(String value) {
            addCriterion("IS_INDEPENDENT not like", value, "isIndependent");
            return (Criteria) this;
        }

        public Criteria andIsIndependentIn(List<String> values) {
            addCriterion("IS_INDEPENDENT in", values, "isIndependent");
            return (Criteria) this;
        }

        public Criteria andIsIndependentNotIn(List<String> values) {
            addCriterion("IS_INDEPENDENT not in", values, "isIndependent");
            return (Criteria) this;
        }

        public Criteria andIsIndependentBetween(String value1, String value2) {
            addCriterion("IS_INDEPENDENT between", value1, value2, "isIndependent");
            return (Criteria) this;
        }

        public Criteria andIsIndependentNotBetween(String value1, String value2) {
            addCriterion("IS_INDEPENDENT not between", value1, value2, "isIndependent");
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

        public Criteria andManageRegionIdIsNull() {
            addCriterion("MANAGE_REGION_ID is null");
            return (Criteria) this;
        }

        public Criteria andManageRegionIdIsNotNull() {
            addCriterion("MANAGE_REGION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andManageRegionIdEqualTo(Long value) {
            addCriterion("MANAGE_REGION_ID =", value, "manageRegionId");
            return (Criteria) this;
        }

        public Criteria andManageRegionIdNotEqualTo(Long value) {
            addCriterion("MANAGE_REGION_ID <>", value, "manageRegionId");
            return (Criteria) this;
        }

        public Criteria andManageRegionIdGreaterThan(Long value) {
            addCriterion("MANAGE_REGION_ID >", value, "manageRegionId");
            return (Criteria) this;
        }

        public Criteria andManageRegionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MANAGE_REGION_ID >=", value, "manageRegionId");
            return (Criteria) this;
        }

        public Criteria andManageRegionIdLessThan(Long value) {
            addCriterion("MANAGE_REGION_ID <", value, "manageRegionId");
            return (Criteria) this;
        }

        public Criteria andManageRegionIdLessThanOrEqualTo(Long value) {
            addCriterion("MANAGE_REGION_ID <=", value, "manageRegionId");
            return (Criteria) this;
        }

        public Criteria andManageRegionIdIn(List<Long> values) {
            addCriterion("MANAGE_REGION_ID in", values, "manageRegionId");
            return (Criteria) this;
        }

        public Criteria andManageRegionIdNotIn(List<Long> values) {
            addCriterion("MANAGE_REGION_ID not in", values, "manageRegionId");
            return (Criteria) this;
        }

        public Criteria andManageRegionIdBetween(Long value1, Long value2) {
            addCriterion("MANAGE_REGION_ID between", value1, value2, "manageRegionId");
            return (Criteria) this;
        }

        public Criteria andManageRegionIdNotBetween(Long value1, Long value2) {
            addCriterion("MANAGE_REGION_ID not between", value1, value2, "manageRegionId");
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