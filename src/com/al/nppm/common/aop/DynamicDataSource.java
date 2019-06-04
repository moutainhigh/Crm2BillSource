/*
 * com.ailk.common.datasource.DynamicDataSource.java
 *
 * 该类源代码归属福州计费研发部，仅限部门内部使用，谢绝传播。
 *
 * Copyright (c) 2013 亚信联创科技(南京)有限公司
 * All rights reserved.
 *
 */
package com.al.nppm.common.aop;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author thd
 * @email tongdd@asiainfo-linkage.com
 * @date 2013-9-5
 * @version 1.0
 * @description 
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	protected Object determineCurrentLookupKey() {
		return DataSourceContext.getDataSource();
	}

}
