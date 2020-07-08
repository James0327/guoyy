package com.jw.aspect;

/**
 * test com.jw.aspect
 *
 * @Description: Monitor
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/18 17:39
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
public @interface Monitor {
    int level() default 0;
}
