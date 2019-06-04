/*
 * com.ailk.common.datasource.DataSource.java
 *
 * 该类源代码归属福州计费研发部，仅限部门内部使用，谢绝传播。
 *
 * Copyright (c) 2013 亚信联创科技(南京)有限公司
 * All rights reserved.
 *
 */
package com.al.nppm.common.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhuhaoying
 * @email zhuhy5@asiainfo.com
 * @date 2014-8-14
 * @version 1.0
 * @description 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	
	String name() default ""; 

}
