package com.jw.pattern.proxy.log;

import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: tax_complex_entrance com.ly.ic.tax.complex.entrance.util.common.MethodLog
 * @Package: com.ly.ic.tax.complex.entrance.util.log
 * @ClassName: MethodLog
 * @Author: james.guo
 * @Date: 2019/8/22 19:40
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 TCLY All rights reserved.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodLog {
    String value() default StringUtils.EMPTY;
}
