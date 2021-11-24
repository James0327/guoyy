package com.jw.james.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;
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
public class TwoThreadPrintV0 {

    @Test
    public void test() {
        AtomicInteger positive = new AtomicInteger();
        AtomicInteger negatve = new AtomicInteger();
        int size = 20;

        Semaphore exec1 = new Semaphore(1);
        Semaphore exec2 = new Semaphore(0);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                try {
                    exec1.acquire();
                    System.out.println(Thread.currentThread().getName() + "][" + positive.incrementAndGet());
                    exec2.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < size; i++) {
                try {
                    exec2.acquire();
                    System.out.println(Thread.currentThread().getName() + "][" + negatve.decrementAndGet());
                    exec1.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        t2.start();

    }

}
