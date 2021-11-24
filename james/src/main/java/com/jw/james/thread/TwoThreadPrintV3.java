package com.jw.james.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

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
public class TwoThreadPrintV3 {
    private Thread t1, t2;

    @Test
    public void test() {
        AtomicInteger positive = new AtomicInteger();
        AtomicInteger negatve = new AtomicInteger();
        int size = 20;

        t1 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                System.out.println(Thread.currentThread().getName() + "][" + positive.incrementAndGet());
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");
        t2 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + "][" + negatve.decrementAndGet());
                LockSupport.unpark(t1);
            }
        }, "t2");

        t2.start();
        t1.start();

    }

}
