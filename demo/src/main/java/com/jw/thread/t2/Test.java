package com.jw.thread.t2;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * guoyy com.jw.thread.t2
 *
 * @Description: Test
 * @Author: guoyiyong/james
 * @Date: 2019-08-26 00:21
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test {

    public static void main(String[] args) throws Exception {

        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        try {
            try {
                boolean b = lock.tryLock();
                System.out.println("b:" + b);
            } finally {
                lock.unlock();
            }
        } finally {
            lock.unlock();
        }

        //        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100),
        //                new BasicThreadFactory.Builder().namingPattern("%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100),
                new BasicThreadFactory.Builder().namingPattern("%d").build(), (r, e) -> {
            System.out.println("queue full:" + e.getQueue());
        });

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("@@@@@@@");
        }, executor);

        System.out.println("I xxx");
        future.get();
        future.cancel(true);

        LockSupport.unpark(new Thread(() -> {
            System.out.println("running is here");
            System.out.println();
        }));

        executor.shutdown();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

}
