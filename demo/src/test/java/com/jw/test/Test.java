package com.jw.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ThreadLocalRandom;

/**
 * guoyy com.jw.test
 *
 * @Description: com.jw.test.Test
 * @Author: guoyiyong/james
 * @Date: 2020-01-29 23:02
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Test {

    @org.junit.jupiter.api.Test
    public void test() {
        boolean flag = getLock("key", "value", 3);
        System.out.println("flag: " + flag);
    }

    private boolean getLock(String key, String value, int retryTimes) {
        System.out.println(String.format("key:%s,value:%s,retryTimes:%s", key, value, retryTimes));
        if (retryTimes > 0) {
            Boolean success = ThreadLocalRandom.current().nextBoolean();
            System.out.println(success);
            if (Boolean.TRUE.equals(success)) {
                return true;
            }
            return getLock(key, value, retryTimes - 1);
        }
        return false;
    }

    protected void rowDataHandler(String input) {
        System.out.println("input[test]: " + input);
    }

    protected void cacheDataHandler(String input) {
        this.rowDataHandler(input);
        System.out.println("---------");

        try {
            Method method = Test.class.getDeclaredMethod("rowDataHandler", String.class);
            method.invoke(this, input);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}

