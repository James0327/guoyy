package com.jw.james.pattern.proxy.exception;

/**
 * guoyy com.jw.pattern.proxy.exception
 *
 * @Description: com.jw.pattern.proxy.exception.ExceptionHandleAnno
 * @Author: guoyiyong/james
 * @Date: 2021-01-25 12:34
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于异常处理的注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExceptionHandleAnno {
}