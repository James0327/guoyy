package com.jw.james.aspect;

import com.alibaba.fastjson.JSON;
import com.jw.james.dto.Foo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * test com.jw.aspect
 *
 * @Description: MonitorAspect
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/18 17:40
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
@Aspect
@Component
public class MonitorAspect {

    @Around("@annotation(monitor) && args(foo)")
    private Object monitor(ProceedingJoinPoint jointPoint, Foo foo, Monitor monitor) throws Throwable {
        System.out.println(JSON.toJSONString(foo));
        System.out.println(JSON.toJSONString(monitor));

        MethodSignature signature = (MethodSignature)jointPoint.getSignature();

        Object[] args = jointPoint.getArgs();
        String[] parameterNames = signature.getParameterNames();
        Class[] parameterTypes = signature.getParameterTypes();

        Class<?> clazz = jointPoint.getTarget().getClass();
        System.out.println(clazz.getSimpleName() + "][" + clazz.getName());
        System.out.println(ToStringBuilder.reflectionToString(args));
        System.out.println(ToStringBuilder.reflectionToString(parameterNames));
        System.out.println(ToStringBuilder.reflectionToString(parameterTypes));

        Object obj = jointPoint.proceed();

        System.out.println(obj);

        return obj;
    }

    @Around("point()")
    private Object monitorV2(ProceedingJoinPoint jointPoint) throws Throwable {

        MethodSignature signature = (MethodSignature)jointPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Class[] parameterTypes = signature.getParameterTypes();

        Class<?> clazz = jointPoint.getTarget().getClass();
        Object[] args = jointPoint.getArgs();
        System.out.println(clazz.getSimpleName() + "][" + clazz.getName());
        System.out.println(ToStringBuilder.reflectionToString(args));
        System.out.println(ToStringBuilder.reflectionToString(parameterNames));
        System.out.println(ToStringBuilder.reflectionToString(parameterTypes));

        Object obj = jointPoint.proceed();

        System.out.println(obj);

        return obj;
    }

    @Pointcut("@annotation(com.jw.james.aspect.Monitor) && args(com.jw.james.dto.Foo)")
    private void point() {
    }

}
