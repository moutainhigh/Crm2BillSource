package com.al.nppm.common.exception;


/**
 * PPM异常(uncheck)类
 * 
 * @author KAN Zoen-ngai
 *
 */
public class UtilException extends BssRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7527360340242118338L;

	/**
	 * 错误列表枚举型
	 */
	private Err errorList;

	/**
	 * 错误发生的模块编码
	 */
	private int modular;
	/**
	 * 错误编码
	 */
	private Integer errCode;
		
	
	/**
	 * @param errorList
	 */
	public UtilException(Err errorList){
		super(errorList.getCode(),errorList.getDesc());
		this.errorList = errorList;
	}
	
	/**
	 * @param errorList
	 * @param detail
	 */
	public UtilException(Err errorList, String detail){
		super(errorList.getCode(),errorList.getDesc() + "," + detail);
		this.errorList = errorList;
	}

	/**
	 * @param errorList
	 * @param cause
	 */
	public UtilException(Err errorList,Throwable cause){
		super(errorList.getCode(),errorList.getDesc(),cause);
		this.errorList = errorList;
	}

	/**
	 * @param errorList
	 * @param detail
	 * @param cause
	 */
	public UtilException(Err errorList, String detail, Throwable cause){
		super(errorList.getCode(),errorList.getDesc() + "," + detail,cause);
		this.errorList = errorList;
	}

	public UtilException(int modular,Integer errCode,String message){
		super(errCode, message);
		this.modular = modular;
		this.errCode = errCode;
	}
	public UtilException(int modular,Integer errCode,String message, Throwable e){
		super(errCode, message, e);
		this.modular = modular;
		this.errCode = errCode;
	}	
	
    /**
     * @return ErrorList
     */
    public Err getErrorList(){
    	return errorList;
    }

	
    public int getModular(){
    	return modular;
    }

	
    public Integer getErrCode(){
    	return errCode;
    }
}
