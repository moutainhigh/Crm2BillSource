// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BssRuntimeException.java

package com.al.nppm.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

// Referenced classes of package com.linkage.bss.commons.exception:
//			Result

public class BssRuntimeException extends RuntimeException
{

	private static final long serialVersionUID = 0x960694a6717b455bL;
	private Result result;

	public BssRuntimeException(Result result, Throwable cause)
	{
		super(result.getMsg(), cause);
		this.result = result;
	}

	public BssRuntimeException(int code, String msg)
	{
		super(msg);
		result = new Result(code, msg);
	}

	public BssRuntimeException(Result result, String detail)
	{
		super((new StringBuilder(String.valueOf(result.getMsg()))).append(",").append(detail).toString());
		this.result = new Result(result.getCode(), (new StringBuilder(String.valueOf(result.getMsg()))).append(",").append(detail).toString());
	}

	public BssRuntimeException(Result result, String detail, Throwable cause)
	{
		super((new StringBuilder(String.valueOf(result.getMsg()))).append(",").append(detail).toString(), cause);
		this.result = new Result(result.getCode(), (new StringBuilder(String.valueOf(result.getMsg()))).append(",").append(detail).toString());
	}

	public BssRuntimeException(int code, String msg, Throwable cause)
	{
		super(msg, cause);
		result = new Result(code, msg);
	}

	public BssRuntimeException(int code, Throwable cause)
	{
		super(cause);
		result = new Result(code, null);
	}

	public String getMessage()
	{
		return ExceptionUtil.buildMessage(super.getMessage(), getCause());
	}

	public String toXmlString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<exception>");
		if (getResult() != null)
			sb.append(result.toString());
		sb.append("<exceptionTrace>");
		sb.append(getMessage());
		sb.append("</exceptionTrace>");
		sb.append("<exception/>");
		return sb.toString();
	}

	public void printStackTrace(PrintStream ps)
	{
		ps.print("<exception>");
		if (getResult() != null)
			ps.print(result.toString());
		ps.append("<exceptionTrace>");
		Throwable cause = getCause();
		if (cause == null)
		{
			super.printStackTrace(ps);
		} else
		{
			ps.println(this);
			ps.print("Caused by: ");
			cause.printStackTrace(ps);
		}
		ps.append("</exceptionTrace>");
		ps.println("</exception>");
	}

	public void printStackTrace(PrintWriter pw)
	{
		pw.print("<exception>");
		if (getResult() != null)
			pw.print(result.toString());
		pw.append("<exceptionTrace>");
		Throwable cause = getCause();
		if (cause == null)
		{
			super.printStackTrace(pw);
		} else
		{
			pw.println(this);
			pw.print("Caused by: ");
			cause.printStackTrace(pw);
		}
		pw.append("</exceptionTrace>");
		pw.println("</exception>");
	}

	public Result getResult()
	{
		return result;
	}

	public void setResult(Result result)
	{
		this.result = result;
	}
}
