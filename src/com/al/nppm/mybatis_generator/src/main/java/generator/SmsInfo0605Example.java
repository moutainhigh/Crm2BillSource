package generator;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SmsInfo0605Example {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SmsInfo0605Example() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMsisdnIsNull() {
            addCriterion("MSISDN is null");
            return (Criteria) this;
        }

        public Criteria andMsisdnIsNotNull() {
            addCriterion("MSISDN is not null");
            return (Criteria) this;
        }

        public Criteria andMsisdnEqualTo(String value) {
            addCriterion("MSISDN =", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotEqualTo(String value) {
            addCriterion("MSISDN <>", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnGreaterThan(String value) {
            addCriterion("MSISDN >", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnGreaterThanOrEqualTo(String value) {
            addCriterion("MSISDN >=", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnLessThan(String value) {
            addCriterion("MSISDN <", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnLessThanOrEqualTo(String value) {
            addCriterion("MSISDN <=", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnLike(String value) {
            addCriterion("MSISDN like", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotLike(String value) {
            addCriterion("MSISDN not like", value, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnIn(List<String> values) {
            addCriterion("MSISDN in", values, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotIn(List<String> values) {
            addCriterion("MSISDN not in", values, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnBetween(String value1, String value2) {
            addCriterion("MSISDN between", value1, value2, "msisdn");
            return (Criteria) this;
        }

        public Criteria andMsisdnNotBetween(String value1, String value2) {
            addCriterion("MSISDN not between", value1, value2, "msisdn");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("FLAG =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("FLAG <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("FLAG >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FLAG >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("FLAG <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("FLAG <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("FLAG like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("FLAG not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("FLAG in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("FLAG not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("FLAG between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("FLAG not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andMsgIsNull() {
            addCriterion("MSG is null");
            return (Criteria) this;
        }

        public Criteria andMsgIsNotNull() {
            addCriterion("MSG is not null");
            return (Criteria) this;
        }

        public Criteria andMsgEqualTo(String value) {
            addCriterion("MSG =", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotEqualTo(String value) {
            addCriterion("MSG <>", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThan(String value) {
            addCriterion("MSG >", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThanOrEqualTo(String value) {
            addCriterion("MSG >=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThan(String value) {
            addCriterion("MSG <", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThanOrEqualTo(String value) {
            addCriterion("MSG <=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLike(String value) {
            addCriterion("MSG like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotLike(String value) {
            addCriterion("MSG not like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgIn(List<String> values) {
            addCriterion("MSG in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotIn(List<String> values) {
            addCriterion("MSG not in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgBetween(String value1, String value2) {
            addCriterion("MSG between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotBetween(String value1, String value2) {
            addCriterion("MSG not between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andStsIsNull() {
            addCriterion("STS is null");
            return (Criteria) this;
        }

        public Criteria andStsIsNotNull() {
            addCriterion("STS is not null");
            return (Criteria) this;
        }

        public Criteria andStsEqualTo(String value) {
            addCriterion("STS =", value, "sts");
            return (Criteria) this;
        }

        public Criteria andStsNotEqualTo(String value) {
            addCriterion("STS <>", value, "sts");
            return (Criteria) this;
        }

        public Criteria andStsGreaterThan(String value) {
            addCriterion("STS >", value, "sts");
            return (Criteria) this;
        }

        public Criteria andStsGreaterThanOrEqualTo(String value) {
            addCriterion("STS >=", value, "sts");
            return (Criteria) this;
        }

        public Criteria andStsLessThan(String value) {
            addCriterion("STS <", value, "sts");
            return (Criteria) this;
        }

        public Criteria andStsLessThanOrEqualTo(String value) {
            addCriterion("STS <=", value, "sts");
            return (Criteria) this;
        }

        public Criteria andStsLike(String value) {
            addCriterion("STS like", value, "sts");
            return (Criteria) this;
        }

        public Criteria andStsNotLike(String value) {
            addCriterion("STS not like", value, "sts");
            return (Criteria) this;
        }

        public Criteria andStsIn(List<String> values) {
            addCriterion("STS in", values, "sts");
            return (Criteria) this;
        }

        public Criteria andStsNotIn(List<String> values) {
            addCriterion("STS not in", values, "sts");
            return (Criteria) this;
        }

        public Criteria andStsBetween(String value1, String value2) {
            addCriterion("STS between", value1, value2, "sts");
            return (Criteria) this;
        }

        public Criteria andStsNotBetween(String value1, String value2) {
            addCriterion("STS not between", value1, value2, "sts");
            return (Criteria) this;
        }

        public Criteria andGetDateIsNull() {
            addCriterion("GET_DATE is null");
            return (Criteria) this;
        }

        public Criteria andGetDateIsNotNull() {
            addCriterion("GET_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andGetDateEqualTo(Date value) {
            addCriterionForJDBCDate("GET_DATE =", value, "getDate");
            return (Criteria) this;
        }

        public Criteria andGetDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("GET_DATE <>", value, "getDate");
            return (Criteria) this;
        }

        public Criteria andGetDateGreaterThan(Date value) {
            addCriterionForJDBCDate("GET_DATE >", value, "getDate");
            return (Criteria) this;
        }

        public Criteria andGetDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("GET_DATE >=", value, "getDate");
            return (Criteria) this;
        }

        public Criteria andGetDateLessThan(Date value) {
            addCriterionForJDBCDate("GET_DATE <", value, "getDate");
            return (Criteria) this;
        }

        public Criteria andGetDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("GET_DATE <=", value, "getDate");
            return (Criteria) this;
        }

        public Criteria andGetDateIn(List<Date> values) {
            addCriterionForJDBCDate("GET_DATE in", values, "getDate");
            return (Criteria) this;
        }

        public Criteria andGetDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("GET_DATE not in", values, "getDate");
            return (Criteria) this;
        }

        public Criteria andGetDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("GET_DATE between", value1, value2, "getDate");
            return (Criteria) this;
        }

        public Criteria andGetDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("GET_DATE not between", value1, value2, "getDate");
            return (Criteria) this;
        }

        public Criteria andPara1IsNull() {
            addCriterion("PARA1 is null");
            return (Criteria) this;
        }

        public Criteria andPara1IsNotNull() {
            addCriterion("PARA1 is not null");
            return (Criteria) this;
        }

        public Criteria andPara1EqualTo(String value) {
            addCriterion("PARA1 =", value, "para1");
            return (Criteria) this;
        }

        public Criteria andPara1NotEqualTo(String value) {
            addCriterion("PARA1 <>", value, "para1");
            return (Criteria) this;
        }

        public Criteria andPara1GreaterThan(String value) {
            addCriterion("PARA1 >", value, "para1");
            return (Criteria) this;
        }

        public Criteria andPara1GreaterThanOrEqualTo(String value) {
            addCriterion("PARA1 >=", value, "para1");
            return (Criteria) this;
        }

        public Criteria andPara1LessThan(String value) {
            addCriterion("PARA1 <", value, "para1");
            return (Criteria) this;
        }

        public Criteria andPara1LessThanOrEqualTo(String value) {
            addCriterion("PARA1 <=", value, "para1");
            return (Criteria) this;
        }

        public Criteria andPara1Like(String value) {
            addCriterion("PARA1 like", value, "para1");
            return (Criteria) this;
        }

        public Criteria andPara1NotLike(String value) {
            addCriterion("PARA1 not like", value, "para1");
            return (Criteria) this;
        }

        public Criteria andPara1In(List<String> values) {
            addCriterion("PARA1 in", values, "para1");
            return (Criteria) this;
        }

        public Criteria andPara1NotIn(List<String> values) {
            addCriterion("PARA1 not in", values, "para1");
            return (Criteria) this;
        }

        public Criteria andPara1Between(String value1, String value2) {
            addCriterion("PARA1 between", value1, value2, "para1");
            return (Criteria) this;
        }

        public Criteria andPara1NotBetween(String value1, String value2) {
            addCriterion("PARA1 not between", value1, value2, "para1");
            return (Criteria) this;
        }

        public Criteria andPara2IsNull() {
            addCriterion("PARA2 is null");
            return (Criteria) this;
        }

        public Criteria andPara2IsNotNull() {
            addCriterion("PARA2 is not null");
            return (Criteria) this;
        }

        public Criteria andPara2EqualTo(String value) {
            addCriterion("PARA2 =", value, "para2");
            return (Criteria) this;
        }

        public Criteria andPara2NotEqualTo(String value) {
            addCriterion("PARA2 <>", value, "para2");
            return (Criteria) this;
        }

        public Criteria andPara2GreaterThan(String value) {
            addCriterion("PARA2 >", value, "para2");
            return (Criteria) this;
        }

        public Criteria andPara2GreaterThanOrEqualTo(String value) {
            addCriterion("PARA2 >=", value, "para2");
            return (Criteria) this;
        }

        public Criteria andPara2LessThan(String value) {
            addCriterion("PARA2 <", value, "para2");
            return (Criteria) this;
        }

        public Criteria andPara2LessThanOrEqualTo(String value) {
            addCriterion("PARA2 <=", value, "para2");
            return (Criteria) this;
        }

        public Criteria andPara2Like(String value) {
            addCriterion("PARA2 like", value, "para2");
            return (Criteria) this;
        }

        public Criteria andPara2NotLike(String value) {
            addCriterion("PARA2 not like", value, "para2");
            return (Criteria) this;
        }

        public Criteria andPara2In(List<String> values) {
            addCriterion("PARA2 in", values, "para2");
            return (Criteria) this;
        }

        public Criteria andPara2NotIn(List<String> values) {
            addCriterion("PARA2 not in", values, "para2");
            return (Criteria) this;
        }

        public Criteria andPara2Between(String value1, String value2) {
            addCriterion("PARA2 between", value1, value2, "para2");
            return (Criteria) this;
        }

        public Criteria andPara2NotBetween(String value1, String value2) {
            addCriterion("PARA2 not between", value1, value2, "para2");
            return (Criteria) this;
        }

        public Criteria andPara3IsNull() {
            addCriterion("PARA3 is null");
            return (Criteria) this;
        }

        public Criteria andPara3IsNotNull() {
            addCriterion("PARA3 is not null");
            return (Criteria) this;
        }

        public Criteria andPara3EqualTo(String value) {
            addCriterion("PARA3 =", value, "para3");
            return (Criteria) this;
        }

        public Criteria andPara3NotEqualTo(String value) {
            addCriterion("PARA3 <>", value, "para3");
            return (Criteria) this;
        }

        public Criteria andPara3GreaterThan(String value) {
            addCriterion("PARA3 >", value, "para3");
            return (Criteria) this;
        }

        public Criteria andPara3GreaterThanOrEqualTo(String value) {
            addCriterion("PARA3 >=", value, "para3");
            return (Criteria) this;
        }

        public Criteria andPara3LessThan(String value) {
            addCriterion("PARA3 <", value, "para3");
            return (Criteria) this;
        }

        public Criteria andPara3LessThanOrEqualTo(String value) {
            addCriterion("PARA3 <=", value, "para3");
            return (Criteria) this;
        }

        public Criteria andPara3Like(String value) {
            addCriterion("PARA3 like", value, "para3");
            return (Criteria) this;
        }

        public Criteria andPara3NotLike(String value) {
            addCriterion("PARA3 not like", value, "para3");
            return (Criteria) this;
        }

        public Criteria andPara3In(List<String> values) {
            addCriterion("PARA3 in", values, "para3");
            return (Criteria) this;
        }

        public Criteria andPara3NotIn(List<String> values) {
            addCriterion("PARA3 not in", values, "para3");
            return (Criteria) this;
        }

        public Criteria andPara3Between(String value1, String value2) {
            addCriterion("PARA3 between", value1, value2, "para3");
            return (Criteria) this;
        }

        public Criteria andPara3NotBetween(String value1, String value2) {
            addCriterion("PARA3 not between", value1, value2, "para3");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNull() {
            addCriterion("SEND_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSendDateIsNotNull() {
            addCriterion("SEND_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSendDateEqualTo(Date value) {
            addCriterionForJDBCDate("SEND_DATE =", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("SEND_DATE <>", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThan(Date value) {
            addCriterionForJDBCDate("SEND_DATE >", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SEND_DATE >=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThan(Date value) {
            addCriterionForJDBCDate("SEND_DATE <", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SEND_DATE <=", value, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateIn(List<Date> values) {
            addCriterionForJDBCDate("SEND_DATE in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("SEND_DATE not in", values, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SEND_DATE between", value1, value2, "sendDate");
            return (Criteria) this;
        }

        public Criteria andSendDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SEND_DATE not between", value1, value2, "sendDate");
            return (Criteria) this;
        }

        public Criteria andMsgIdIsNull() {
            addCriterion("MSG_ID is null");
            return (Criteria) this;
        }

        public Criteria andMsgIdIsNotNull() {
            addCriterion("MSG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMsgIdEqualTo(String value) {
            addCriterion("MSG_ID =", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotEqualTo(String value) {
            addCriterion("MSG_ID <>", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdGreaterThan(String value) {
            addCriterion("MSG_ID >", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdGreaterThanOrEqualTo(String value) {
            addCriterion("MSG_ID >=", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLessThan(String value) {
            addCriterion("MSG_ID <", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLessThanOrEqualTo(String value) {
            addCriterion("MSG_ID <=", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdLike(String value) {
            addCriterion("MSG_ID like", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotLike(String value) {
            addCriterion("MSG_ID not like", value, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdIn(List<String> values) {
            addCriterion("MSG_ID in", values, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotIn(List<String> values) {
            addCriterion("MSG_ID not in", values, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdBetween(String value1, String value2) {
            addCriterion("MSG_ID between", value1, value2, "msgId");
            return (Criteria) this;
        }

        public Criteria andMsgIdNotBetween(String value1, String value2) {
            addCriterion("MSG_ID not between", value1, value2, "msgId");
            return (Criteria) this;
        }

        public Criteria andPriIsNull() {
            addCriterion("PRI is null");
            return (Criteria) this;
        }

        public Criteria andPriIsNotNull() {
            addCriterion("PRI is not null");
            return (Criteria) this;
        }

        public Criteria andPriEqualTo(Integer value) {
            addCriterion("PRI =", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotEqualTo(Integer value) {
            addCriterion("PRI <>", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriGreaterThan(Integer value) {
            addCriterion("PRI >", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriGreaterThanOrEqualTo(Integer value) {
            addCriterion("PRI >=", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriLessThan(Integer value) {
            addCriterion("PRI <", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriLessThanOrEqualTo(Integer value) {
            addCriterion("PRI <=", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriIn(List<Integer> values) {
            addCriterion("PRI in", values, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotIn(List<Integer> values) {
            addCriterion("PRI not in", values, "pri");
            return (Criteria) this;
        }

        public Criteria andPriBetween(Integer value1, Integer value2) {
            addCriterion("PRI between", value1, value2, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotBetween(Integer value1, Integer value2) {
            addCriterion("PRI not between", value1, value2, "pri");
            return (Criteria) this;
        }

        public Criteria andAccIdIsNull() {
            addCriterion("ACC_ID is null");
            return (Criteria) this;
        }

        public Criteria andAccIdIsNotNull() {
            addCriterion("ACC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAccIdEqualTo(Short value) {
            addCriterion("ACC_ID =", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotEqualTo(Short value) {
            addCriterion("ACC_ID <>", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdGreaterThan(Short value) {
            addCriterion("ACC_ID >", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdGreaterThanOrEqualTo(Short value) {
            addCriterion("ACC_ID >=", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdLessThan(Short value) {
            addCriterion("ACC_ID <", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdLessThanOrEqualTo(Short value) {
            addCriterion("ACC_ID <=", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdIn(List<Short> values) {
            addCriterion("ACC_ID in", values, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotIn(List<Short> values) {
            addCriterion("ACC_ID not in", values, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdBetween(Short value1, Short value2) {
            addCriterion("ACC_ID between", value1, value2, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotBetween(Short value1, Short value2) {
            addCriterion("ACC_ID not between", value1, value2, "accId");
            return (Criteria) this;
        }

        public Criteria andRecvIsNull() {
            addCriterion("RECV is null");
            return (Criteria) this;
        }

        public Criteria andRecvIsNotNull() {
            addCriterion("RECV is not null");
            return (Criteria) this;
        }

        public Criteria andRecvEqualTo(String value) {
            addCriterion("RECV =", value, "recv");
            return (Criteria) this;
        }

        public Criteria andRecvNotEqualTo(String value) {
            addCriterion("RECV <>", value, "recv");
            return (Criteria) this;
        }

        public Criteria andRecvGreaterThan(String value) {
            addCriterion("RECV >", value, "recv");
            return (Criteria) this;
        }

        public Criteria andRecvGreaterThanOrEqualTo(String value) {
            addCriterion("RECV >=", value, "recv");
            return (Criteria) this;
        }

        public Criteria andRecvLessThan(String value) {
            addCriterion("RECV <", value, "recv");
            return (Criteria) this;
        }

        public Criteria andRecvLessThanOrEqualTo(String value) {
            addCriterion("RECV <=", value, "recv");
            return (Criteria) this;
        }

        public Criteria andRecvLike(String value) {
            addCriterion("RECV like", value, "recv");
            return (Criteria) this;
        }

        public Criteria andRecvNotLike(String value) {
            addCriterion("RECV not like", value, "recv");
            return (Criteria) this;
        }

        public Criteria andRecvIn(List<String> values) {
            addCriterion("RECV in", values, "recv");
            return (Criteria) this;
        }

        public Criteria andRecvNotIn(List<String> values) {
            addCriterion("RECV not in", values, "recv");
            return (Criteria) this;
        }

        public Criteria andRecvBetween(String value1, String value2) {
            addCriterion("RECV between", value1, value2, "recv");
            return (Criteria) this;
        }

        public Criteria andRecvNotBetween(String value1, String value2) {
            addCriterion("RECV not between", value1, value2, "recv");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateIsNull() {
            addCriterion("SAVE_HIS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateIsNotNull() {
            addCriterion("SAVE_HIS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateEqualTo(Date value) {
            addCriterionForJDBCDate("SAVE_HIS_DATE =", value, "saveHisDate");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("SAVE_HIS_DATE <>", value, "saveHisDate");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateGreaterThan(Date value) {
            addCriterionForJDBCDate("SAVE_HIS_DATE >", value, "saveHisDate");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SAVE_HIS_DATE >=", value, "saveHisDate");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateLessThan(Date value) {
            addCriterionForJDBCDate("SAVE_HIS_DATE <", value, "saveHisDate");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("SAVE_HIS_DATE <=", value, "saveHisDate");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateIn(List<Date> values) {
            addCriterionForJDBCDate("SAVE_HIS_DATE in", values, "saveHisDate");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("SAVE_HIS_DATE not in", values, "saveHisDate");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SAVE_HIS_DATE between", value1, value2, "saveHisDate");
            return (Criteria) this;
        }

        public Criteria andSaveHisDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("SAVE_HIS_DATE not between", value1, value2, "saveHisDate");
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