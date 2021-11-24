package com.jw.james.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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

    class A implements Runnable {
        private Integer cnt = 0;
        private AtomicInteger idx = new AtomicInteger();

        @Override
        public void run() {
            cnt = cnt + 1;
            idx.incrementAndGet();
            // synchronized (cnt) { // 使用cnt 并不能锁住 因为cnt 经过拆箱包箱后发生了变化
            synchronized (idx) {
                System.out.println("cnt: " + String.format("%s, %s", cnt, new Date()));
                System.out.println("idx: " + String.format("%s, %s", idx.get(), new Date()));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Long l = null;
        String s = l + "";
        System.out.println(s);

        Test test = new Test();
        A a = test.new A();
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(a);

        t1.start();
        t2.start();
    }

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

