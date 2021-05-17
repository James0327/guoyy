package com.jw.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * test com.jw.aspect
 *
 * @Description: Monitor
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/18 17:39
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Monitor {
    int level() default 0;
}
