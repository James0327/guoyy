package com.jw.thread;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.jupiter.api.Test;

/**
 * Description: guoyy
 * com.jw.thread.TestJVM
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/5 23:36
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class TestJVM {
    @Data
    private class Foo {
        private String name;
        private String remark;
        private boolean exec;
    }

    @Test
    public void test() {
        Foo foo = new Foo();

        new Thread(() -> {
            foo.setName("name1");
            System.out.println("thread1:" + JSON.toJSONString(foo));

        }, "Thread1").start();

        new Thread(() -> {
            foo.setRemark("remark");
            System.out.println("thread2:" + JSON.toJSONString(foo));
        }, "Thread2").start();

        foo.setExec(true);
        String json = JSON.toJSONString(foo);
        System.out.println(json);
    }

}
