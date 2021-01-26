package com.jw.pattern.proxy;

import com.jw.GuoyyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;

/**
 * guoyy com.jw.pattern.proxy
 *
 * @Description: com.jw.pattern.proxy.ProxyPatternTest
 * @Author: guoyiyong/james
 * @Date: 2021-01-23 19:04
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@SpringBootTest(classes = GuoyyApplication.class)
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class ProxyPatternTest {
    @Resource
    private ProxyCaller proxyCaller;

    @Test
    public void test() {
        proxyCaller.call("biz", "aaa");
        proxyCaller.call("abc", "aaa");
    }

}
