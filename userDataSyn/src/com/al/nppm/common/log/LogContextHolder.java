// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogContextHolder.java

package com.al.nppm.common.log;


// Referenced classes of package com.linkage.bss.commons.log:
//			LogContext

public class LogContextHolder
{

	private static final ThreadLocal logContextHolder = new InheritableThreadLocal();

	public LogContextHolder()
	{
	}

	public static void clearLogContext()
	{
		setLogContext(null);
		logContextHolder.remove();
	}

	public static void setLogContext(LogContext logContext)
	{
		logContextHolder.set(logContext);
	}

	public static LogContext getLogContext()
	{
		return (LogContext)logContextHolder.get();
	}

}
