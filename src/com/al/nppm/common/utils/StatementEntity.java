package com.al.nppm.common.utils;

import java.util.List;

/**
 * 存数数据参数实体
 * 
 * @author lindl
 * @version 2012-7-19
 */
public class StatementEntity{

	private Float seq = -1F;
	private String statementName;
	private Object parameterObject;

	public Float getSeq(){
		return seq;
	}

	public void setSeq(Float seq){
		this.seq = seq;
	}

	public String getStatementName(){
		return statementName;
	}

	public void setStatementName(String statementName){
		this.statementName = statementName;
	}

	public Object getParameterObject(){
		return parameterObject;
	}

	public void setParameterObject(Object parameterObject){
		this.parameterObject = parameterObject;
	}

	public String toString(){
		return "seq:" + seq + "-statementName:" + statementName + "-parameterObject:" + parameterObject;
	}

	// 构建-操作实体
	public static StatementEntity buildStatemen(Float seq,String statementName,Object parameterObject,
	        List<StatementEntity> statemenList){
		StatementEntity statement = buildStatemen(seq,statementName,parameterObject);
		if(statement != null){
			statemenList.add(statement);
		}
		return statement;
	}

	@SuppressWarnings("rawtypes")
    public static StatementEntity buildStatemen(Float seq,String statementName,Object parameterObject){
		if(parameterObject == null || ((parameterObject instanceof List) && ((List)parameterObject).size() == 0)){
			return null;
		}
		StatementEntity statement = new StatementEntity();
		statement.setSeq(seq);
		statement.setStatementName(statementName);
		statement.setParameterObject(parameterObject);
		return statement;
	}

}
