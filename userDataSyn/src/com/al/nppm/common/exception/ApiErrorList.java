package com.al.nppm.common.exception;

/**
 * 
 * 
 * @author wucl5
 *
 */
public enum ApiErrorList {
	
	CheckApiVersion(500,"API版本校验失败，请联系管理员"),
	CheckBeanCfg(404,"处理类型未匹配到，请检查配置信息"),
	CheckAPICfg(404,"请查询API信息"),
	AccountError(404,"Account valid Error"),
	// 身份校验
	AuthError(401,"Authorization认证信息缺失，请联系管理员"),
	InvalidTokenError(401,"Invalid Token,Signature Exception"),
	TokenExpError(401,"身份令牌(TOKEN)过期"),
	OtherError(401,"身份令牌(TOKEN)无法解析"),
	
	IdentifyCodeNotNullError(401,"Identify Code Can't Be Null"),
	IdentifyTypeNotNullError(401,"Identify Type Can't Be Null"),
	TelNoCodeNotNullError(401,"Tel Number Can't Be Null"),
	IdentifyCodeNotValidError(401,"验证码错误"),
	IdentifyCodeNotGenError(401,"Identify Code Not Gen"),
	IdentifyTypeNotSupportError(401,"不支持的登录方式"),
	PasswordError(401,"用户名或密码错误"),
	UserNameError(401,"用户不存在"),
	TelNoNotFoundError(401,"Tel Number Not Found Error"),
//	TelNoNotExistError(401,"Tel Number Not Exists"),
	
	IdentifyInternalError(401,"登录校验出错，请联系管理员"),
	
	ParamsNullError(500,"参数不可为空"),
	
	CheckBeanMethodCfg(404,"处理方法未匹配到，请检查配置信息"),
	MethodInvokeError(500,"方法调用异常,未匹配到对应的方法"),
	MethodParamsMatchError(500,"方法参数不匹配"),
	DisPatcherError(500,"方法参数不匹配"),
	ReqIOReadError(500,"请求数据流读取异常");
	
	
	
	/**
	 * 错误代码
	 */
	private final Integer code;
	/**
	 * 错误描述
	 */
	private String desc;

	ApiErrorList(Integer code,String desc){
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode(){
		return code;
	}

	public String getDesc(){
		return desc;
	}
}
