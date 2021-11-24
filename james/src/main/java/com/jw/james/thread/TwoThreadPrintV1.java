package com.jw.james.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
public class TwoThreadPrintV1 {

    private final Lock lock = new ReentrantLock();
    private final Condition exec1 = lock.newCondition();
    private final Condition exec2 = lock.newCondition();

    @Test
    public void test() {
        AtomicInteger positive = new AtomicInteger();
        AtomicInteger negatve = new AtomicInteger();
        int size = 20;

        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < size; i++) {
                    System.out.println(Thread.currentThread().getName() + "][" + positive.incrementAndGet());
                    exec2.signal();
                    exec1.await();
                }
                exec2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < size; i++) {
                    exec2.await();
                    System.out.println(Thread.currentThread().getName() + "][" + negatve.decrementAndGet());
                    exec1.signal();
                }
                exec1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2");

        t2.start();
        t1.start();

    }

}
