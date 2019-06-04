// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExceptionUtil.java

package com.al.nppm.common.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.al.nppm.common.utils.StringUtil;

// Referenced classes of package com.linkage.bss.commons.util:
//			StringUtil

public class ExceptionUtil
{

	public ExceptionUtil()
	{
	}

	public static String buildMessage(String message, Throwable cause)
	{
		if (cause != null)
		{
			StringBuilder buf = new StringBuilder();
			if (message != null)
				buf.append(message).append("; ");
			buf.append("nested exception is ").append(cause);
			return buf.toString();
		} else
		{
			return message;
		}
	}

	public static String getExceptionString(Throwable e, Integer length)
	{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		e.printStackTrace(ps);
		String msg = os.toString();
		if (length.intValue() != 0 && msg.length() > length.intValue())
			msg = StringUtil.getSubStr(msg, length.intValue());
		return msg;
	}

	public static String getExceptionString(Exception e, Integer length)
	{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);
		e.printStackTrace(ps);
		String msg = os.toString();
		if (length.intValue() != 0 && msg.length() > length.intValue())
			msg = StringUtil.getSubStr(msg, length.intValue());
		return msg;
	}
}
