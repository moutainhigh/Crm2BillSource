package com.al.nppm.common.utils.mybatis;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.ibatis.session.SqlSession;
import com.al.nppm.common.exception.UtilException;
import com.al.nppm.common.log.LogFactory;
import com.al.nppm.common.utils.StatementEntity;

public class SQLScriptUtil{
	
	final LogFactory log = LogFactory.getLog(SQLScriptUtil.class);
	
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public String cteateSqlScript(StatementEntity statementEntity) throws UtilException{
		List<String> sqlScriptList = new ArrayList<String>();
		Object parameter = statementEntity.getParameterObject();
		JSONArray paramArray = JSONArray.fromObject(parameter);
		for(Object param: paramArray){
			String sqlScript = SqlHelper.getMapperSql(sqlSession,statementEntity.getStatementName(),param);
			sqlScriptList.add(sqlScript);
		}
		log.debug("获取的脚本：{}",sqlScriptList.toString());
		return sqlScriptList.toString();
	}
}
