package com.jw.pattern.proxy.record;

import com.jw.pattern.proxy.BaseMethodAspect;
import com.jw.pattern.proxy.MethodAdviceHandler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * guoyy com.jw.pattern.proxy.record
 *
 * @Description: com.jw.pattern.proxy.record.InvokeRecordAspect
 * @Author: guoyiyong/james
 * @Date: 2021-01-23 18:54
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class InvokeRecordAspect extends BaseMethodAspect {

    /**
     * 指定切点（处理打上 InvokeRecordAnno 的方法）
     */
    @Override
    @Pointcut("@annotation(com.jw.pattern.proxy.record.InvokeRecordAnno)")
    protected void pointcut() {}

    /**
     * 指定该切面绑定的方法切面处理器为 InvokeRecordHandler
     */
    @Override
    protected Class<? extends MethodAdviceHandler<?>> getAdviceHandlerType() {
        log.info("getAdviceHandlerType InvokeRecordHandler");
        return InvokeRecordHandler.class;
    }
}
