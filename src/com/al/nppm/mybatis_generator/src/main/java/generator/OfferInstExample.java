package generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfferInstExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Long offset;

    public OfferInstExample() {
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

        public Criteria andOwnerCustIdIsNull() {
            addCriterion("OWNER_CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andOwnerCustIdIsNotNull() {
            addCriterion("OWNER_CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerCustIdEqualTo(Long value) {
            addCriterion("OWNER_CUST_ID =", value, "ownerCustId");
            return (Criteria) this;
        }

        public Criteria andOwnerCustIdNotEqualTo(Long value) {
            addCriterion("OWNER_CUST_ID <>", value, "ownerCustId");
            return (Criteria) this;
        }

        public Criteria andOwnerCustIdGreaterThan(Long value) {
            addCriterion("OWNER_CUST_ID >", value, "ownerCustId");
            return (Criteria) this;
        }

        public Criteria andOwnerCustIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OWNER_CUST_ID >=", value, "ownerCustId");
            return (Criteria) this;
        }

        public Criteria andOwnerCustIdLessThan(Long value) {
            addCriterion("OWNER_CUST_ID <", value, "ownerCustId");
            return (Criteria) this;
        }

        public Criteria andOwnerCustIdLessThanOrEqualTo(Long value) {
            addCriterion("OWNER_CUST_ID <=", value, "ownerCustId");
            return (Criteria) this;
        }

        public Criteria andOwnerCustIdIn(List<Long> values) {
            addCriterion("OWNER_CUST_ID in", values, "ownerCustId");
            return (Criteria) this;
        }

        public Criteria andOwnerCustIdNotIn(List<Long> values) {
            addCriterion("OWNER_CUST_ID not in", values, "ownerCustId");
            return (Criteria) this;
        }

        public Criteria andOwnerCustIdBetween(Long value1, Long value2) {
            addCriterion("OWNER_CUST_ID between", value1, value2, "ownerCustId");
            return (Criteria) this;
        }

        public Criteria andOwnerCustIdNotBetween(Long value1, Long value2) {
            addCriterion("OWNER_CUST_ID not between", value1, value2, "ownerCustId");
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

        public Criteria andOfferAgreeIdIsNull() {
            addCriterion("OFFER_AGREE_ID is null");
            return (Criteria) this;
        }

        public Criteria andOfferAgreeIdIsNotNull() {
            addCriterion("OFFER_AGREE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOfferAgreeIdEqualTo(Long value) {
            addCriterion("OFFER_AGREE_ID =", value, "offerAgreeId");
            return (Criteria) this;
        }

        public Criteria andOfferAgreeIdNotEqualTo(Long value) {
            addCriterion("OFFER_AGREE_ID <>", value, "offerAgreeId");
            return (Criteria) this;
        }

        public Criteria andOfferAgreeIdGreaterThan(Long value) {
            addCriterion("OFFER_AGREE_ID >", value, "offerAgreeId");
            return (Criteria) this;
        }

        public Criteria andOfferAgreeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("OFFER_AGREE_ID >=", value, "offerAgreeId");
            return (Criteria) this;
        }

        public Criteria andOfferAgreeIdLessThan(Long value) {
            addCriterion("OFFER_AGREE_ID <", value, "offerAgreeId");
            return (Criteria) this;
        }

        public Criteria andOfferAgreeIdLessThanOrEqualTo(Long value) {
            addCriterion("OFFER_AGREE_ID <=", value, "offerAgreeId");
            return (Criteria) this;
        }

        public Criteria andOfferAgreeIdIn(List<Long> values) {
            addCriterion("OFFER_AGREE_ID in", values, "offerAgreeId");
            return (Criteria) this;
        }

        public Criteria andOfferAgreeIdNotIn(List<Long> values) {
            addCriterion("OFFER_AGREE_ID not in", values, "offerAgreeId");
            return (Criteria) this;
        }

        public Criteria andOfferAgreeIdBetween(Long value1, Long value2) {
            addCriterion("OFFER_AGREE_ID between", value1, value2, "offerAgreeId");
            return (Criteria) this;
        }

        public Criteria andOfferAgreeIdNotBetween(Long value1, Long value2) {
            addCriterion("OFFER_AGREE_ID not between", value1, value2, "offerAgreeId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdIsNull() {
            addCriterion("CREATE_ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdIsNotNull() {
            addCriterion("CREATE_ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdEqualTo(Long value) {
            addCriterion("CREATE_ORG_ID =", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdNotEqualTo(Long value) {
            addCriterion("CREATE_ORG_ID <>", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdGreaterThan(Long value) {
            addCriterion("CREATE_ORG_ID >", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_ORG_ID >=", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdLessThan(Long value) {
            addCriterion("CREATE_ORG_ID <", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_ORG_ID <=", value, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdIn(List<Long> values) {
            addCriterion("CREATE_ORG_ID in", values, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdNotIn(List<Long> values) {
            addCriterion("CREATE_ORG_ID not in", values, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdBetween(Long value1, Long value2) {
            addCriterion("CREATE_ORG_ID between", value1, value2, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andCreateOrgIdNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_ORG_ID not between", value1, value2, "createOrgId");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodIsNull() {
            addCriterion("EXP_PROC_METHOD is null");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodIsNotNull() {
            addCriterion("EXP_PROC_METHOD is not null");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodEqualTo(String value) {
            addCriterion("EXP_PROC_METHOD =", value, "expProcMethod");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodNotEqualTo(String value) {
            addCriterion("EXP_PROC_METHOD <>", value, "expProcMethod");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodGreaterThan(String value) {
            addCriterion("EXP_PROC_METHOD >", value, "expProcMethod");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodGreaterThanOrEqualTo(String value) {
            addCriterion("EXP_PROC_METHOD >=", value, "expProcMethod");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodLessThan(String value) {
            addCriterion("EXP_PROC_METHOD <", value, "expProcMethod");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodLessThanOrEqualTo(String value) {
            addCriterion("EXP_PROC_METHOD <=", value, "expProcMethod");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodLike(String value) {
            addCriterion("EXP_PROC_METHOD like", value, "expProcMethod");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodNotLike(String value) {
            addCriterion("EXP_PROC_METHOD not like", value, "expProcMethod");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodIn(List<String> values) {
            addCriterion("EXP_PROC_METHOD in", values, "expProcMethod");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodNotIn(List<String> values) {
            addCriterion("EXP_PROC_METHOD not in", values, "expProcMethod");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodBetween(String value1, String value2) {
            addCriterion("EXP_PROC_METHOD between", value1, value2, "expProcMethod");
            return (Criteria) this;
        }

        public Criteria andExpProcMethodNotBetween(String value1, String value2) {
            addCriterion("EXP_PROC_METHOD not between", value1, value2, "expProcMethod");
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

        public Criteria andRegionIdIsNull() {
            addCriterion("REGION_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegionIdIsNotNull() {
            addCriterion("REGION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegionIdEqualTo(Long value) {
            addCriterion("REGION_ID =", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotEqualTo(Long value) {
            addCriterion("REGION_ID <>", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThan(Long value) {
            addCriterion("REGION_ID >", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("REGION_ID >=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThan(Long value) {
            addCriterion("REGION_ID <", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdLessThanOrEqualTo(Long value) {
            addCriterion("REGION_ID <=", value, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdIn(List<Long> values) {
            addCriterion("REGION_ID in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotIn(List<Long> values) {
            addCriterion("REGION_ID not in", values, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdBetween(Long value1, Long value2) {
            addCriterion("REGION_ID between", value1, value2, "regionId");
            return (Criteria) this;
        }

        public Criteria andRegionIdNotBetween(Long value1, Long value2) {
            addCriterion("REGION_ID not between", value1, value2, "regionId");
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

        public Criteria andBusiModDateIsNull() {
            addCriterion("BUSI_MOD_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBusiModDateIsNotNull() {
            addCriterion("BUSI_MOD_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBusiModDateEqualTo(Date value) {
            addCriterion("BUSI_MOD_DATE =", value, "busiModDate");
            return (Criteria) this;
        }

        public Criteria andBusiModDateNotEqualTo(Date value) {
            addCriterion("BUSI_MOD_DATE <>", value, "busiModDate");
            return (Criteria) this;
        }

        public Criteria andBusiModDateGreaterThan(Date value) {
            addCriterion("BUSI_MOD_DATE >", value, "busiModDate");
            return (Criteria) this;
        }

        public Criteria andBusiModDateGreaterThanOrEqualTo(Date value) {
            addCriterion("BUSI_MOD_DATE >=", value, "busiModDate");
            return (Criteria) this;
        }

        public Criteria andBusiModDateLessThan(Date value) {
            addCriterion("BUSI_MOD_DATE <", value, "busiModDate");
            return (Criteria) this;
        }

        public Criteria andBusiModDateLessThanOrEqualTo(Date value) {
            addCriterion("BUSI_MOD_DATE <=", value, "busiModDate");
            return (Criteria) this;
        }

        public Criteria andBusiModDateIn(List<Date> values) {
            addCriterion("BUSI_MOD_DATE in", values, "busiModDate");
            return (Criteria) this;
        }

        public Criteria andBusiModDateNotIn(List<Date> values) {
            addCriterion("BUSI_MOD_DATE not in", values, "busiModDate");
            return (Criteria) this;
        }

        public Criteria andBusiModDateBetween(Date value1, Date value2) {
            addCriterion("BUSI_MOD_DATE between", value1, value2, "busiModDate");
            return (Criteria) this;
        }

        public Criteria andBusiModDateNotBetween(Date value1, Date value2) {
            addCriterion("BUSI_MOD_DATE not between", value1, value2, "busiModDate");
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

        public Criteria andExtOfferInstIdIsNull() {
            addCriterion("EXT_OFFER_INST_ID is null");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdIsNotNull() {
            addCriterion("EXT_OFFER_INST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdEqualTo(String value) {
            addCriterion("EXT_OFFER_INST_ID =", value, "extOfferInstId");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdNotEqualTo(String value) {
            addCriterion("EXT_OFFER_INST_ID <>", value, "extOfferInstId");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdGreaterThan(String value) {
            addCriterion("EXT_OFFER_INST_ID >", value, "extOfferInstId");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdGreaterThanOrEqualTo(String value) {
            addCriterion("EXT_OFFER_INST_ID >=", value, "extOfferInstId");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdLessThan(String value) {
            addCriterion("EXT_OFFER_INST_ID <", value, "extOfferInstId");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdLessThanOrEqualTo(String value) {
            addCriterion("EXT_OFFER_INST_ID <=", value, "extOfferInstId");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdLike(String value) {
            addCriterion("EXT_OFFER_INST_ID like", value, "extOfferInstId");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdNotLike(String value) {
            addCriterion("EXT_OFFER_INST_ID not like", value, "extOfferInstId");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdIn(List<String> values) {
            addCriterion("EXT_OFFER_INST_ID in", values, "extOfferInstId");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdNotIn(List<String> values) {
            addCriterion("EXT_OFFER_INST_ID not in", values, "extOfferInstId");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdBetween(String value1, String value2) {
            addCriterion("EXT_OFFER_INST_ID between", value1, value2, "extOfferInstId");
            return (Criteria) this;
        }

        public Criteria andExtOfferInstIdNotBetween(String value1, String value2) {
            addCriterion("EXT_OFFER_INST_ID not between", value1, value2, "extOfferInstId");
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