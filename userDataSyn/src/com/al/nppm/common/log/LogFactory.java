// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Log.java

package com.al.nppm.common.log;

import java.io.IOException;

import com.al.nppm.common.exception.Err;
import com.al.nppm.common.exception.Result;

public class LogFactory
{
	private static final class LogModel
	{
		public static final LogModel RunTimeOpen;
		public static final LogModel RunTimeClose;
		public static final LogModel Develop;
		private static final LogModel ENUM$VALUES[];

		public static LogModel[] values()
		{
			LogModel alogmodel[];
			int i;
			LogModel alogmodel1[];
			System.arraycopy(alogmodel = ENUM$VALUES, 0, alogmodel1 = new LogModel[i = alogmodel.length], 0, i);
			return alogmodel1;
		}

		static 
		{
			RunTimeOpen = new LogModel("RunTimeOpen", 0);
			RunTimeClose = new LogModel("RunTimeClose", 1);
			Develop = new LogModel("Develop", 2);
			ENUM$VALUES = (new LogModel[] {
				RunTimeOpen, RunTimeClose, Develop
			});
		}

		private LogModel(String s, int i)
		{
			super();
		}
	}


	private BssLoggerWrapper log;
	private static final String defaultLogName = "com.linkage.bss.commons.util.Log";
	private Result result;
	
	private LogFactory(String name)
	{
		log = new BssLoggerWrapper(name);
	}

	public static LogFactory getLog(String name)
	{
		String s = name;
		if (s == null)
			s = "com.ai.nppm.common.Log";
		return new LogFactory(s);
	}

	public static LogFactory getLog(Class clazz)
	{
		String s = "com.ai.nppm.common.Log";
		if (clazz != null)
			s = clazz.getName();
		return getLog(s);
	}

	private static LogModel getLogModel()
	{
		LogModel model = LogModel.RunTimeClose;
		LogContext logContext = LogContextHolder.getLogContext();
		if (logContext != null)
		{
			if (logContext.getLogSwitch())
				model = LogModel.RunTimeOpen;
			else
				model = LogModel.RunTimeClose;
		} else
		{
			model = LogModel.Develop;
		}
		return model;
	}

	private static boolean isLogOpen(LogModel model)
	{
		return model == LogModel.RunTimeOpen || model == LogModel.Develop;
	}

	private static boolean isLogOpen()
	{
		return isLogOpen(getLogModel());
	}

	private static String buildLogContextInfo()
	{
		long threadId = Thread.currentThread().getId();
		String staff = null;
		LogContext logContext = LogContextHolder.getLogContext();
		if (logContext != null)
			staff = logContext.getStaff();
		StringBuilder sb = new StringBuilder();
		sb.append("BSS_LOGGER:threadId=");
		sb.append(threadId);
		sb.append(",staff=");
		sb.append(staff);
		sb.append(",info=");
		return sb.toString();
	}
	
	public void debug(String message)
	{
		try
		{
			LogModel model = getLogModel();
			if (model == LogModel.RunTimeOpen)
				log.debug((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString());
			else
				log.debug(message);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	public void debug(String message, Object arg)
	{
		Object args[] = {arg};
		try
		{
			LogModel model = getLogModel();
			if (isLogOpen(model))
				if (model == LogModel.RunTimeOpen)
					log.debug((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString(), args);
				else
					log.debug(message, args);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	public void debug(String message, Object args[])
	{
		try
		{
			LogModel model = getLogModel();
			if (isLogOpen(model))
				if (model == LogModel.RunTimeOpen)
					log.debug((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString(), args);
				else
					log.debug(message, args);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	public void info(String message, Object...args)
	{
		try
		{
			LogModel model = getLogModel();
			if (isLogOpen(model))
				if (model == LogModel.RunTimeOpen)
					log.info((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString(), args);
				else
					log.info(message, args);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	public void info(String message, Object arg)
	{
		Object args[] = {arg};
		try
		{
			LogModel model = getLogModel();
			if (isLogOpen(model))
				if (model == LogModel.RunTimeOpen)
					log.info((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString(), args);
				else
					log.info(message, args);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	public void warn(String message, Object args[])
	{
		try
		{
			LogModel model = getLogModel();
			if (isLogOpen(model))
				if (model == LogModel.RunTimeOpen)
					log.warn((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString(), args);
				else
					log.warn(message, args);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}
	

	public void warn(String message, Throwable e) {
		try
		{
			LogModel model = getLogModel();
			if (model == LogModel.RunTimeOpen)
				log.warn((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString(), e);
			else
				log.warn(message, e);
		}
		catch (Throwable error)
		{
			error.printStackTrace();
		}
	}
	
	public void trace(String message, Object arg)
	{
		Object args[] = {arg};
		try
		{
			LogModel model = getLogModel();
			if (isLogOpen(model))
				if (model == LogModel.RunTimeOpen)
					log.trace((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString(), args);
				else
					log.trace(message, args);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	public void trace(String message, Object args[])
	{
		try
		{
			LogModel model = getLogModel();
			if (isLogOpen(model))
				if (model == LogModel.RunTimeOpen)
					log.trace((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString(), args);
				else
					log.trace(message, args);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	public void error(String message)
	{
		try
		{
			LogModel model = getLogModel();
			if (model == LogModel.RunTimeOpen)
				log.error((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString());
			else
				log.error(message);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	public void error(Err errorList, Object arg) {
		Object args[] = {arg};
		this.error(errorList.getDesc()+":{}",args);
	}
	
	public void error(String message, Object ... args) {
		try
		{
			LogModel model = getLogModel();
			if (model == LogModel.RunTimeOpen)
				log.error((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString(), args);
			else
				log.error(message, args);
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}

	public void error(String message, Throwable e)
	{
		try
		{
			LogModel model = getLogModel();
			if (model == LogModel.RunTimeOpen)
				log.error((new StringBuilder(String.valueOf(buildLogContextInfo()))).append(message).toString(), e);
			else
				log.error(message, e);
		}
		catch (Throwable error)
		{
			error.printStackTrace();
		}
	}

	public boolean isDebugEnabled()
	{
		if (log == null)
			return false;
		if (isLogOpen() && log.isDebugEnabled())
			return true;
		return false;
	}

	public boolean isErrorEnabled()
	{
		if (log == null)
			return false;
		return log.isErrorEnabled();
	}

	public boolean isInfoEnabled()
	{
		if (log == null)
			return false;
		if (isLogOpen() && log.isInfoEnabled())
			return true;
		return false;
	}

	public boolean isTraceEnabled()
	{
		if (log == null)
			return false;
		if (isLogOpen() && log.isTraceEnabled())
			return true;
		return false;
	}

	public boolean isWarnEnabled()
	{
		if (log == null)
			return false;
		if (isLogOpen() && log.isWarnEnabled())
			return true;
		return false;
	}
}
