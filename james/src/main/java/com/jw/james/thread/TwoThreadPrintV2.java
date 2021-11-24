package com.jw.james.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * guoyy com.jw.thread
 *
 * @Description: com.jw.thread.TwoThreadPrintV1
 * @Author: guoyiyong/james
 * @Date: 2020-09-13 23:06
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class TwoThreadPrintV2 {
    private volatile boolean exec2 = false;

    @Test
    public void test() {
        AtomicInteger positive = new AtomicInteger();
        AtomicInteger negatve = new AtomicInteger();
        int size = 20;

        Object obj = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                try {
                    for (int i = 0; i < size; i++) {
                        System.out.println(Thread.currentThread().getName() + "][" + positive.incrementAndGet());
                        if (i == 0) {
                            exec2 = true;
                        }
                        obj.notify();
                        obj.wait();
                    }
                    obj.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            synchronized (obj) {
                while (!exec2) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    for (int i = 0; i < size; i++) {
                        System.out.println(Thread.currentThread().getName() + "][" + negatve.decrementAndGet());
                        obj.notify();
                        obj.wait();
                    }
                    obj.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t2.start();
        t1.start();

    }

}
