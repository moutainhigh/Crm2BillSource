// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BssLoggerWrapper.java

package com.al.nppm.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BssLoggerWrapper
{

	private Logger _logger;
	
	public BssLoggerWrapper(String name)
	{
		_logger = LoggerFactory.getLogger(name);
	}

	public void debug(String message, Object args[])
	{
		_logger.debug(message, args);
	}

	public void info(String message, Object args[])
	{
		_logger.info(message, args);
	}

	public void warn(String message, Object args[])
	{
		_logger.warn(message, args);
	}
	
	public void warn(String message, Throwable e)
	{
		_logger.error(message, e);
	}

	public void trace(String message, Object args[])
	{
		_logger.trace(message, args);
	}

	public void error(String message, Object args[])
	{
		_logger.error(message, args);
	}
	
	public void debug(String message)
	{
		_logger.debug(message);
	}

	public void info(String message)
	{
		_logger.info(message);
	}

	public void warn(String message)
	{
		_logger.warn(message);
	}

	public void trace(String message)
	{
		_logger.trace(message);
	}

	public void error(String message)
	{
		_logger.error(message);
	}
	
	public void error(String message, Throwable e)
	{
		_logger.error(message, e);
	}

	public boolean isDebugEnabled()
	{
		if (_logger == null)
			return false;
		else
			return _logger.isDebugEnabled();
	}

	public boolean isErrorEnabled()
	{
		if (_logger == null)
			return false;
		else
			return _logger.isErrorEnabled();
	}

	public boolean isInfoEnabled()
	{
		if (_logger == null)
			return false;
		else
			return _logger.isInfoEnabled();
	}

	public boolean isTraceEnabled()
	{
		if (_logger == null)
			return false;
		else
			return _logger.isTraceEnabled();
	}

	public boolean isWarnEnabled()
	{
		if (_logger == null)
			return false;
		else
			return _logger.isWarnEnabled();
	}
}
