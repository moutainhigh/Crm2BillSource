// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DaoException.java

package com.al.nppm.common.exception;


// Referenced classes of package com.linkage.bss.commons.exception:
//			BssRuntimeException, Result

public class DaoException extends BssRuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6756853225674346257L;

	public DaoException(Result result, Throwable cause)
	{
		super(result, cause);
	}

	public DaoException(int code, String msg)
	{
		super(code, msg);
	}

	public DaoException(Result result, String detail)
	{
		super(result, detail);
	}

	public DaoException(Result result, String detail, Throwable cause)
	{
		super(result, detail, cause);
	}

	public DaoException(int code, String msg, Throwable cause)
	{
		super(code, msg, cause);
	}

	public DaoException(int code, Throwable cause)
	{
		super(code, cause);
	}
}
