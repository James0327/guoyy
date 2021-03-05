package com.jw.exception;

import com.jw.SpringContextHelper;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Description: guoyy
 * com.jw.exception.ExceptionHandlerAspect
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/3 14:49
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@DependsOn("springContextHelper")
@Component
@Aspect
public class ExceptionHandlerAspect {

    @Around("@annotation(taxbagException)")
    private Object exec(ProceedingJoinPoint joinPoint, TaxbagException taxbagException) throws Throwable {
        try {
            Object obj = joinPoint.proceed();
            System.out.println("obj: " + ToStringBuilder.reflectionToString(obj));
            return obj;
        } catch (Throwable e) {
            Class<? extends Throwable>[] throwableArr = taxbagException.throwable();
            Boolean hit = Arrays.stream(throwableArr)
                    .map(throwable -> throwable.isAssignableFrom(e.getClass()))
                    .anyMatch(Boolean::booleanValue);
            //                    .reduce(false, (r1, r2) -> r1 || r2);
            System.out.println("hit: " + hit);
            if (hit) {
                Class<ExceptionHandler> handlerClass = taxbagException.handler();
                ExceptionHandler handler = SpringContextHelper.getBean(handlerClass);
                Object ret = handler.exec(joinPoint.getArgs());
                if (ret != null) {
                    return ret;
                }
            }
            throw e;
        }
    }

}
