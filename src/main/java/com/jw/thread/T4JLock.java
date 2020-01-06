package com.jw.thread;

/**
 * guoyy com.jw.thread
 *
 * @Description: com.jw.thread.T4JLock
 * @Author: guoyiyong/james
 * @Date: 2019-12-09 00:04
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class T4JLock {

    final static JLock lock = new JLock();
    static int count = 0;

    public static void main(String[] args) throws Exception {
        Runnable runnable = () -> {
            try {
                lock.lock();
                for (int i = 0; i < 100000; i++) {
                    count++;
                }
            } finally {
                lock.unlock();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(count);

    }

}
