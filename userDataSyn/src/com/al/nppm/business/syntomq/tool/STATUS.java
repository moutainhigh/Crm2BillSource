package com.al.nppm.business.syntomq.tool;

public enum STATUS{
	CONNECTERR(1,"连接服务器失败"),SENDOK(2,"发送成功"),SENDERR(3,"发送失败"),MSGCHECKERR(4,"消息检查不过关");
	
	private Integer code;
	private String msg;
	
	private STATUS(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}