package com.jw.pattern.proxy.record;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * guoyy com.jw.pattern.proxy
 *
 * @Description: com.jw.pattern.proxy.record.InvokeRecordAnno
 * @Author: guoyiyong/james
 * @Date: 2021-01-23 18:47
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InvokeRecordAnno {

    /**
     * 调用说明
     */
    String value() default "";
}
