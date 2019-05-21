package com.al.nppm.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 根据错误代码、错误信息创建异常信息
 * 支持返回出错的文件名、行号和方法名
 * @author Billow
 * date:2016-03-17
 * 参考材料：http://www.zhixing123.cn/jsp/40451.html
 *
 */
public class AiException extends RuntimeException
{
    private static final long serialVersionUID = -2927216675933025L;
    
    private Throwable cause;
    private String code;//错误代码
    private String filename;//抛出异常的文件名
    private int line;//抛出异常的行号
    private String method;//抛出异常的函数名
    
    /**
     * 构造函数
     * @param code  错误代码
     * @param msg   错误信息
     */
    public AiException(String code, String msg) {
        super(msg);
        
        this.code = code;
        //获得异常所在的文件,行号和方法
        Throwable throwable = getCause();
        //StackTraceElement stackTraceElement = throwable.getStackTrace()[throwable.getStackTrace().length-1];
        StackTraceElement stackTraceElement = throwable.getStackTrace()[0];
        if (null != stackTraceElement){
            this.line = stackTraceElement.getLineNumber();
            this.filename = stackTraceElement.getFileName();
            this.method = stackTraceElement.getMethodName();
        }
        else {
            this.line = -1;
            this.filename = "未知";
            this.method = "未知";
        }
    }
    
    /**
     * 构造函数
     * @param code  错误代码
     * @param msg   错误信息
     * @param ex  异常类
     */
    public AiException(String code, String msg, Throwable ex) {
        super(msg);
        
        this.code = code;
        this.cause = ex;
        //获得异常所在的文件,行号和方法
        Throwable throwable = getCause();
        //StackTraceElement stackTraceElement = throwable.getStackTrace()[throwable.getStackTrace().length-1];
        StackTraceElement stackTraceElement = throwable.getStackTrace()[0];
        if (null != stackTraceElement){
            this.line = stackTraceElement.getLineNumber();
            this.filename = stackTraceElement.getFileName();
            this.method = stackTraceElement.getMethodName();
        }
        else {
            this.line = -1;
            this.filename = "未知";
            this.method = "未知";
        }
    }
    
    public String getFilename() {
        return filename;
    }

    public int getLine() {
        return line;
    }

    public Throwable getCause() {
        return this.cause == null ? this : this.cause;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getMethod(){
        return this.method;
    }
    
    public String getMsg(){
        return super.getMessage();
    }
    
    public String getSimpleErrInfo(){
        return this.code + " | " + super.getMessage(); 
    }
    
    public String getErrInfo(){
        return this.code + " | " + super.getMessage() + " | " + this.filename + ":" + this.line + "(" + this.method + ")"; 
    }
    
    public String getMessage() {
        String message = this.getClass().getName() + " | " + super.getMessage();
        
        Throwable throwable = getCause();
        if (!(throwable instanceof AiException)) {
            message += " | " + throwable.getMessage();
        }
        
        return message;
    }
    
    public void printStackTrace(PrintStream ps) {
        Throwable throwable = getCause();
        
        if (throwable == null) {
            super.printStackTrace(ps);
        } else {
            if (!(throwable instanceof AiException)) {
                throwable.printStackTrace(ps);
            }
        }
    }
    
    public void printStackTrace(PrintWriter pw) {
        Throwable throwable = getCause();
        
        if (throwable == null) {
            super.printStackTrace(pw);
        } else {
            if (!(throwable instanceof AiException)) {
                throwable.printStackTrace(pw);
            }
        }
    }
    
    public void printStackTrace() {
        printStackTrace(System.err);
    }
}
