package com.al.nppm.common;

public enum ErrResultCode {

	// 数据路由异常
	ROUTING_NO_KEY(99001, "路由：入参未发现路由标识异常_"), 
	DBROUTING_NO_MATCHING(99002, "数据路由：无匹配数据源异常_"),
	PARAM_RESOLVER_GET_PARAM_E(99003, "请求参数解析：获取参数异常_"),
	PARAM_RESOLVER_ANALYZE_DBROUTING_E(99004, "请求参数解析：解析分库标识发生异常_"),
	JSON_PARSE_SYSTEM_ERROR(99005, "JSON解析：JSON字符串解析异常_"),
	RESULT_RESOLVER_PARAM_E(99006, "返回结果参数解析：JSON转换异常_"),
	SERVICE_ROUTING_NO_MATCHING(99002, "服务路由路由：无匹配服务地址异常_"),
	//非法字符入参
	SPECIAL_CHARECTER_E(99016,"请求入参中存在非法字符异常");
	private int code;

	private String desc;

	private ErrResultCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String toString() {
		return String.valueOf(code);
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static void main(String args[]) {

	}

}
