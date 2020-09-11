package com.jw.t;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * guoyy com.jw.t
 *
 * @Description: com.jw.t.T13
 * @Author: guoyiyong/james
 * @Date: 2020-09-10 20:28
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class T13 {
    private static final int MAX_THREADS = 10;
    private static CountDownLatch latch = new CountDownLatch(MAX_THREADS);
    private static CountDownLatch cdl = new CountDownLatch(MAX_THREADS);

    private static class Listener extends Semaphore implements Cloneable {
        public Listener(int permits) {
            super(permits);
        }

        @Override
        public Listener clone() {
            try {
                return (Listener)super.clone();
            } catch (Exception e) {
                e.printStackTrace();
                return new Listener(0);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Listener listener = new Listener(0);
        Listener listener2 = listener.clone();

        System.out.println(listener == listener2);
        System.out.println(listener.equals(listener2));
        System.out.println(listener.getClass() == listener2.getClass());

        for (int i = 0; i < MAX_THREADS; i++) {
            new Thread(() -> {
                latch.countDown();
                try {
                    latch.await();
                    System.out.println(System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();
                }
            }).start();
        }

        cdl.await();
    }
}
