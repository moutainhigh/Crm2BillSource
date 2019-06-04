package com.al.nppm.common.constant;

import java.util.Arrays;
import java.util.List;


/**
 * @since 2013/6/8
 * 常量定义
 */
public class Domain{
	
	//资费包模板自动增加空白行
	public static final String ADDEMPTY = "1";
	
	public static final ConstManager CONST_MANAGER = ConstManagerFactory.getConstManagerImpl();

	public static final String APIS_FLAG = "/apis/";
	// APP 服务头
	public static final String API_HEADER = "/apis/v1";
	
	// 首页登陆成功传递的编码
	public static final List<String> LOGIN_SUCCESS_CDS = Arrays.asList("1", "11");
	// 首页校验密码过期返回的编码
	public static final List<Integer> LOGIN_PWD_EXPIRE_CDS = Arrays.asList(4, 5 ,6, 11);

	
	// 过期时长:2H
	public static final long EXPIRE_TIME = 60*60*2;
	
	
	//邮件链接地址访问代办
	public static final String SMTP_TASK_URL = CONST_MANAGER.getString("CONST_SMTP_TASK_URL");
	
	//重置密码访问地址
	public static final String RESET_PASSWORD_URL= CONST_MANAGER.getString("CONST_RESET_PASSWORD_URL");
	
	// 测试：JSESSIONID
	// 生产：ppmNewSESSIONID
	public static final String SESSION_NAME_IN_COOKIE = CONST_MANAGER.getString("CONST_SESSION_IN_COOKIE");
	// usercode in COOKIE
	public static final String USER_CODE_IN_COOKIE = "CK_PORTAL_USER_NO1";
	
	public static final String SECRET_KEY = "10000_YEARS_PPM";
	
	// ======== 登录校验码 start=======
	// 默认
	public static final int STATUS_DEFAULT = 0;
	// 成功
	public static final int STATUS_SUCCESS = 1;
	// 无效
	public static final int STATUS_NOT_VALID = 2;
	// 手机未生成验证码
	public static final int STATUS_CODE_NOT_GEN = 3;
	// 未支持的验证方式
	public static final int STATUS_NOT_SUPPORT = 4;
	// 手机号码未匹配到
	public static final int STATUS_TEL_NUMBER_NOT_FOUND = 5;
	// 密码错误
	public static final int STATUS_PASSWORD_ERROR = 6;
	
	// ======== 登录校验码 end=======
}
