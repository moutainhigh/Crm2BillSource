// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Result.java

package com.al.nppm.common.exception;

import java.io.Serializable;

public class Result
implements Serializable
{
	private static final long serialVersionUID = 0x9f62164720c51834L;
	public static final Result BSS_SUCCESS = new Result(0, "成功");
	public static final Result BSS_SYS_ERROR = new Result(-9999, "系统错误");
	private int code;
	private String msg;
	
	public Result(int code, String msg)
	{
		this.code = 0;
		this.msg = "成功";
		this.code = code;
		this.msg = msg;
	}
	
	public Result(Result result)
	{
		code = 0;
		msg = "成功";
		code = result.getCode();
		msg = result.getMsg();
	}
	
	public int getCode()
	{
		return code;
	}
	
	public void setCode(int code)
	{
		this.code = code;
	}
	
	public String getMsg()
	{
		return msg;
	}
	
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	public boolean equals(Object r)
	{
		boolean b = false;
		if (r instanceof Result)
		{
			if (getCode() == ((Result)r).getCode())
				b = true;
			else
				b = false;
		} else
		{
			b = super.equals(r);
		}
		return b;
	}
	
	public int hashCode()
	{
		return super.hashCode();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<code>");
		sb.append(getCode());
		sb.append("</code>");
		sb.append("<msg>");
		sb.append(getMsg());
		sb.append("</msg>");
		return sb.toString();
	}
}