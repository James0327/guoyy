package com.jw.james.t;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: test Test01
 * @Package: com.jw.t
 * @ClassName: Test01
 * @Author: james.guo
 * @Date: 2019/9/20 19:14
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test01 {
    private final Pattern compile = Pattern.compile("(?<![\\w])[\\w]{2}(?!=[\\w])");
    private final AtomicBoolean doT1 = new AtomicBoolean(true);
    private final AtomicBoolean doT2 = new AtomicBoolean(false);
    private Thread t1 = null, t2 = null;
    private volatile int num = 1;

    public static void main(String[] args) {
        int t = -1;
        System.out.println("t:" + Integer.toBinaryString(t));
        int i = 1;
        System.out.println("i:" + Integer.toBinaryString(i));
        int i2 = 1 << 1;
        System.out.println("i2:" + Integer.toBinaryString(i2));
        int i4 = 1 << 2;
        System.out.println("i4:" + Integer.toBinaryString(i4));

        t = t ^ i2;

        System.out.println("t[" + Integer.toBinaryString(t));

    }

    @Test
    public void test5() {
        t1 = new Thread(() -> {
            while (true) {
                if (doT1.compareAndSet(true, false)) {
                    System.out.println("A");
                    doT2.set(true);
                }
            }
        });
        t2 = new Thread(() -> {
            while (true) {
                if (doT2.compareAndSet(true, false)) {
                    System.out.println("B");
                    doT1.set(true);
                }
            }
        });

        t2.start();
        t1.start();
    }

    @Test
    public void test4() {
        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        t1 = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    if (num == 2) {
                        try {
                            c1.await();
                        } catch (InterruptedException e) {
                        }
                    }
                    System.out.println("A");
                    num = 2;
                    c2.signal();
                } finally {
                    lock.unlock();
                }
            }
        });
        t2 = new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    if (num == 1) {
                        try {
                            c2.await();
                        } catch (InterruptedException e) {
                        }
                    }
                    System.out.println("B");
                    num = 1;
                    c1.signal();
                } finally {
                    lock.unlock();
                }
            }
        });

        t2.start();
        t1.start();

        LockSupport.park();
    }

    @Test
    public void test3() {
        Semaphore sA = new Semaphore(1);
        Semaphore sB = new Semaphore(0);

        t1 = new Thread(() -> {
            while (true) {
                try {
                    sA.acquire();
                } catch (InterruptedException e) {
                }
                System.out.println("A");
                sB.release();
            }
        });
        t2 = new Thread(() -> {
            while (true) {
                try {
                    sB.acquire();
                } catch (InterruptedException e) {
                }
                System.out.println("B");
                sA.release();
            }
        });

        t2.start();
        t1.start();
    }

    @Test
    public void test2() {
        t1 = new Thread(() -> {
            while (true) {
                System.out.println("A");
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });
        t2 = new Thread(() -> {
            while (true) {
                LockSupport.park();
                System.out.println("B");
                LockSupport.unpark(t1);
            }
        });

        t2.start();
        t1.start();
    }

    @Test
    public void test() {
        String str = null;
        if (str instanceof String) {
            System.out.println("str: " + str);
        }
        System.out.println(JSON.toJSONString(str));

        System.exit(1);

        Integer i = 128;

        System.out.println(i.equals(128));

        _1();
    }

    public void _1() {
        String str = "sha-ca,ca-lax/lax-aa,aa-bjs";

        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

}
