/*
 * Created on 2006-11-6
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.al.nppm.common.constant;

import java.util.Map;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ConstManager {
	public Object getConst(Object key);
	public String getString(Object key);
	public Integer getInteger(Object key);
	public int getInt(Object key);
	@SuppressWarnings("unchecked")
	public Map getConstMap();
}
