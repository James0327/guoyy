package com.jw.aspect;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * test com.jw.aspect
 *
 * @Description: com.jw.aspect.MonitorAspect
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/18 17:40
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
@Aspect
public class MonitorAspect {

    @Around("@annotation(monitor)")
    private Object monitor(ProceedingJoinPoint jointPoint, Monitor monitor) throws Throwable {

        Class<?> clazz = jointPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) jointPoint.getSignature();

        Object[] args = jointPoint.getArgs();
        String[] parameterNames = signature.getParameterNames();
        Class[] parameterTypes = signature.getParameterTypes();

        System.out.println(clazz.getSimpleName() + "][" + clazz.getName());
        System.out.println(ToStringBuilder.reflectionToString(args));
        System.out.println(ToStringBuilder.reflectionToString(parameterNames));
        System.out.println(ToStringBuilder.reflectionToString(parameterTypes));

        Object obj = jointPoint.proceed();

        System.out.println(obj);

        return obj;
    }

    @Around("point() && @annotation(monitor)")
    private Object monitorV2(ProceedingJoinPoint jointPoint, Monitor monitor) throws Throwable {
        Class<?> clazz = jointPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) jointPoint.getSignature();

        Object[] args = jointPoint.getArgs();
        String[] parameterNames = signature.getParameterNames();
        Class[] parameterTypes = signature.getParameterTypes();

        System.out.println(clazz.getSimpleName() + "][" + clazz.getName());
        System.out.println(ToStringBuilder.reflectionToString(args));
        System.out.println(ToStringBuilder.reflectionToString(parameterNames));
        System.out.println(ToStringBuilder.reflectionToString(parameterTypes));

        Object obj = jointPoint.proceed();

        System.out.println(obj);

        return obj;
    }

    @Pointcut("@annotation(Monitor)")
    private void point() {
    }

}
