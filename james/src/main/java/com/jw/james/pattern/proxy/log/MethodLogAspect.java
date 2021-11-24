package com.jw.james.pattern.proxy.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * guoyy com.jw.pattern.proxy.log
 *
 * @Description: com.jw.pattern.proxy.log.MethodLogAspect
 * @Author: guoyiyong/james
 * @Date: 2021-01-25 12:03
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Slf4j
@Aspect
@Component
public class MethodLogAspect {

    @Around("@annotation(methodLog)")
    private Object writeLog(ProceedingJoinPoint jointPoint, MethodLog methodLog) {
        try {
            Class<?> clazz = jointPoint.getTarget().getClass();
            MethodSignature signature = (MethodSignature)jointPoint.getSignature();
            String mName = signature.getName();
            String prefix = String.format("%s %s#%s", methodLog.value(), clazz.getSimpleName(), mName);

            Object[] args = jointPoint.getArgs();
            if (args != null && args.length >= 1) {
                String[] pNames = signature.getParameterNames();
                String logPrefix = new StringBuilder().append(prefix).append(" request, ")
                        .append(StringUtils.join(pNames, ":{},")).append(":{}").toString();
                String[] vals = Stream.of(args).map(obj -> {
                    if (obj instanceof String) {
                        return (String)obj;
                    }
                    return JSON.toJSONString(obj);
                }).toArray(size -> new String[size]);
                log.info(logPrefix, vals);
            }

            Object obj = jointPoint.proceed();
            log.info(prefix + " response: {}", JSON.toJSONString(obj));

            return obj;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}