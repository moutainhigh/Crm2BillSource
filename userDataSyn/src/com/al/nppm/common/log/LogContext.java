// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogContext.java

package com.al.nppm.common.log;

import java.io.Serializable;

public class LogContext
	implements Serializable
{

	private static final long serialVersionUID = 0xe6ac9ed051a76960L;
	private String staff;
	private boolean logSwitch;

	public LogContext(String staff, boolean logSwitch)
	{
		this.staff = staff;
		this.logSwitch = logSwitch;
	}

	public String getStaff()
	{
		return staff;
	}

	public void setStaff(String staff)
	{
		this.staff = staff;
	}

	public boolean getLogSwitch()
	{
		return logSwitch;
	}

	public void setLogSwitch(boolean logSwitch)
	{
		this.logSwitch = logSwitch;
	}
}
