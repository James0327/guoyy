package com.jw.pattern.proxy.exception;

import com.jw.pattern.proxy.BaseMethodAspect;
import com.jw.pattern.proxy.MethodAdviceHandler;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * guoyy com.jw.pattern.proxy.exception
 *
 * @Description: com.jw.pattern.proxy.exception.ExceptionHandleAspect
 * @Author: guoyiyong/james
 * @Date: 2021-01-25 12:36
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */

@Aspect
@Order(Byte.MAX_VALUE)
@Component
public class ExceptionHandleAspect extends BaseMethodAspect {

    /**
     * 指定切点（处理打上 ExceptionHandleAnno 的方法）
     */
    @Override
    @Pointcut("@annotation(com.jw.pattern.proxy.exception.ExceptionHandleAnno)")
    protected void pointcut() { }

    /**
     * 指定该切面绑定的方法切面处理器为 ExceptionHandleHandler
     */
    @Override
    protected Class<? extends MethodAdviceHandler<?>> getAdviceHandlerType() {
        return ExceptionHandleHandler.class;
    }

}

