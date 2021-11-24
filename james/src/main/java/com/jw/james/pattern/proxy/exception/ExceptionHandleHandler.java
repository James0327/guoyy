package com.jw.james.pattern.proxy.exception;

import com.jw.james.pattern.proxy.BaseMethodAdviceHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * guoyy com.jw.pattern.proxy.exception
 *
 * @Description: com.jw.pattern.proxy.exception.ExceptionHandleHandler
 * @Author: guoyiyong/james
 * @Date: 2021-01-25 12:34
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Component
public class ExceptionHandleHandler extends BaseMethodAdviceHandler<Object> {

    /**
     * 抛出异常时的处理
     */
    @Override
    public void onThrow(ProceedingJoinPoint point, Throwable e) {
        super.onThrow(point, e);
        // 发送异常到邮箱或者钉钉的逻辑
    }

    /**
     * 抛出异常时的返回值
     */
    @Override
    public Object getOnThrow(ProceedingJoinPoint point, Throwable e) {
        // 获得返回值类型
        Class<?> returnType = getTargetMethod(point).getReturnType();

        // 如果返回值类型是 Map 或者其子类
        if (Map.class.isAssignableFrom(returnType)) {
            Map<String, Object> result = new HashMap<>(4);
            result.put("success", false);
            result.put("message", "调用出错");

            return result;
        }

        return null;
    }

}
