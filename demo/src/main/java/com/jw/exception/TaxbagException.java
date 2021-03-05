package com.jw.exception;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: guoyy
 * com.jw.exception.TaxbagException
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/3 10:17
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TaxbagException {

    Class<? extends Throwable>[] throwable();

    Class<ExceptionHandler> handler();
}
