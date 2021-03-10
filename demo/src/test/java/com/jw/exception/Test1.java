package com.jw.exception;

import com.jw.GuoyyApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * Description: guoyy
 * com.jw.exception.Test1
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/3 10:41
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@SpringBootTest(classes = GuoyyApplication.class)
public class Test1 {
    @Resource
    private Foo foo;

    @Test
    public void test1() {
        foo.fire();
    }

    @Test
    public void test2() throws Exception {
        foo.fire2();
    }

    @Test
    public void test() throws Throwable {
        // Set the max attempts including the initial attempt before retrying
        // and retry on all exceptions (this is the default):
        SimpleRetryPolicy policy = new SimpleRetryPolicy(5, Collections.singletonMap(Exception.class, true));

        // Use the policy...
        RetryTemplate template = new RetryTemplate();
        template.setRetryPolicy(policy);
        template.execute(new RetryCallback<Foo, Throwable>() {
            public Foo doWithRetry(RetryContext context) {
                // business logic here
                return null;
            }
        });

    }

}
