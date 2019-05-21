package com.al.nppm.common.exception;

/**
 * PPM错误信息统一在此定义
 * 
 * @author KAN Zoen-ngai
 * @version 1.10, 02/1/13
 *
 */
public enum Err{
	
	Check(500,"数据校验异常"),
	Sys(160000,"系统其他错误"),
	CheckBizFormat(1990,"校验业务体格式错误（节点名称错误）"),
	SysDataNotDefine(1991,"所填参数主数据中没有定义"),
	NodeMandatory(1992,"必选节点为空或未填值"),
		
	//Json
	JsonAlyze(2000, "Json解析异常"),  
	
	//文件
	File_300000(300000,"下载文件出错"),
	File_300001(300001,"上传文件出错"),
	File_300002(300002,"读取文件出错"),
	File_300003(300003,"保存文件信息出错"),
	File_300004(300004,"文件没发现"),
	File_300005(300005,"IO错误"),
	File_300006(300006,"写文件到本地服务器失败"),
	File_300007(300007,"保存下载记录出错"),
	File_300008(300008,"Excel文件数据为空"),
	File_300009(300009,"解析Excel文件失败"),
	File_300010(300010,"解析Excel格式的Json失败"),
	
	SEQ_4000001(4000001,"在序列配置SEQ_GEN_RULE中不存在!"),
	SEQ_4000002(4000002,"表达式所需的参数不存在!"),
	SEQ_4000003(4000003,"不属于集团目录节点!"),
	SEQ_4000004(4000004,"根据表达式创建ID异常!"),
	SEQ_4000005(4000005,"生成销售品编码异常！"),
	SEQ_4000006(4000006,"目录对应code长度不合规"),
	SEQ_4000007(4000007,"目录关联销售品的最大manage_code为空"),
	SEQ_4000008(4000008,"目录关联销售品的最大manage_code序号长度不为16位"),
	SEQ_4000009(4000009,"目录关联销售品的最大manage_code不是数字"),
	SEQ_4000010(4000010,"新生成manage_code与库中重复"),
	SEQ_4000011(4000011,"新生成manage_code中序列值超过999"),
	
	Flow_500000(500000,"启动流程失败"),
	Flow_500001(500001,"结束流程失败"),
	
	
	SQL_6000001(6000001,"根据映射关系创建参数失败"),
	SQL_6000002(6000002,"保存SQL脚本记录失败"),
	SQL_6000003(6000003,"根据SQL_GEN_CONFIG模板解析生成json失败"),
	SQL_6000004(6000004,"json解析失败"),
	SQL_6000005(6000005,"生成执行脚本失败"),
	SQL_6000006(6000006,"配置的映射关系错误"),
	SQL_6000007(6000007,"映射key值对应的json数据不可为空"),
	
	SPRINGMETHOD_7000001(7000001,"配置的BEANID不完整或不存在方法名"),
	SPRINGMETHOD_7000002(7000002,"未查找到对应的方法"),
	SPRINGMETHOD_7000003(7000003,"调用方法失败"),
	//-------------------------------------------------------------------------------------------

	;
	
	
	/**
	 * 错误代码
	 */
	private final Integer code;
	/**
	 * 错误描述
	 */
	private String desc;

	Err(Integer code,String desc){
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode(){
		return code;
	}

	public String getDesc(){
		return desc;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args){
	   	 for(Err err :Err.values()){
	   		 if(err.equals(Err.SysDataNotDefine)){
	   			 System.
	   			 out.println("============");
	   		 }
	   		 if(err.name().equals(Err.Sys.name())){
	   			 System.
	   			 out.println("/////////");
	   		 }
	   		 System.
	   		 out.println(err+" : " + err.getCode() + "," + err.getDesc());
	   	 }
	}

}
