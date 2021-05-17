package com.jw.aspect;

import com.jw.GuoyyApplication;
import com.jw.dto.Foo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * test com.jw.aspect
 *
 * @Description: T
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/18 18:52
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
@Slf4j
@SpringBootTest(classes = GuoyyApplication.class)
public class T {
    @Resource
    private ServerA serverA;

    @Test
    public void test1() {
        Foo foo = new Foo();
        foo.setTraceId("xxx");
        foo.setId(1L);
        foo.setDesc("测试");
        foo.setRemark("remark");

        serverA.method1(foo);
    }

}
