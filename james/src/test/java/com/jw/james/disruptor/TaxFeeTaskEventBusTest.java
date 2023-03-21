package com.jw.james.disruptor;

import com.jw.james.disruptor.task.TaxFeeTaskEventBus;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.logging.log4j.core.util.CyclicBuffer;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description: guoyy
 * com.jw.james.disruptor.TaxFeeTaskEventBusTest
 *
 * @author guoyiyong/james
 * Date: 2022/10/20 11:59
 * Version: 1.0
 *
 * Copyright (C) 2022 JW All rights reserved.
 */
public class TaxFeeTaskEventBusTest {

    long timeOutMillSeconds = 500;
    final int size = 30;
    int size1 = 1999;

    String mainThread = Thread.currentThread().toString();
    DateTime processStart = DateTime.now();

    String traceId = UUID.randomUUID().toString();
    String caller = "test";
    Object request = new Object(), response = new Object();

    ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 50, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(8192), new BasicThreadFactory.Builder().namingPattern("TaxfeeThreadPool-%d").build(),
            (Runnable r, ThreadPoolExecutor e) -> {
                String msg = String.format("Thread pool is EXHAUSTED! Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %s, %d (completed: %d), Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)",
                        Thread.currentThread().getName(), e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(), r.toString(), e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating());
                throw new RejectedExecutionException(msg);
            });

    @Test
    public void test2() throws Exception {

        DateTime time1 = DateTime.now();

        for (int j = 0; j < size1; j++) {
            CompletableFuture<Object>[] feeTaxMsgRets = process(request, response, traceId + j, caller, processStart, mainThread);
            CompletableFuture<Void> all = CompletableFuture.allOf(feeTaxMsgRets);
            try {
                all.get(timeOutMillSeconds, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                System.out.println("税费计算部分超时: " + j);
            }
        }

        DateTime time2 = DateTime.now();

        System.out.printf("test2 estimate time %sms.%n", time2.getMillis() - time1.getMillis());

    }

    @Test
    public void test3() throws Exception {

        DateTime time1 = DateTime.now();

        for (int j = 0; j < size1; j++) {
            CountDownLatch latch = new CountDownLatch(size);
            for (int i = 0; i < size; i++) {
                TaxFeeTaskEventBus.publishEvent2(request, response, latch, traceId, caller, processStart, mainThread);
            }
            boolean ret = latch.await(timeOutMillSeconds, TimeUnit.MILLISECONDS);
            if (!ret) {
                System.out.println("税费计算部分超时: " + latch.getCount() + "/" + size);
            }
        }

        DateTime time2 = DateTime.now();

        System.out.printf("test1 estimate time %sms.%n", time2.getMillis() - time1.getMillis());

    }

    @Test
    public void test4() throws Exception {

        DateTime time1 = DateTime.now();

        for (int j = 0; j < size1; j++) {
            List<CompletableFuture<Object>> futureList = Collections.nCopies(size, new CompletableFuture<>());
            for (int i = 0; i < size; i++) {
                TaxFeeTaskEventBus.publishEvent3(request, response, futureList.get(i), traceId, caller, processStart, mainThread);
            }
            CompletableFuture<Void> all = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]));
            try {
                all.get(timeOutMillSeconds, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                System.out.println("税费计算部分超时: " + j);
            }
        }

        DateTime time2 = DateTime.now();
        System.out.printf("test4 estimate time %sms.%n", time2.getMillis() - time1.getMillis());

    }

    @Test
    public void test5() throws Exception {

        CompletableFuture<Object>[] futures = new CompletableFuture[size * size1];
        for (int i = 0; i < size1 * size; i++) {
            futures[i] = new CompletableFuture<>();
        }

        DateTime time1 = DateTime.now();

        for (int j = 0; j < size1; j++) {
            for (int i = 0; i < size; i++) {
                TaxFeeTaskEventBus.publishEvent3(request, response, futures[j * size + i], traceId, caller, processStart, mainThread);
            }
        }
        CompletableFuture<Void> all = CompletableFuture.allOf(futures);
        try {
            all.get(size1 * timeOutMillSeconds, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println("税费计算部分超时");
        }

        DateTime time2 = DateTime.now();
        System.out.printf("test4 estimate time %sms.%n", time2.getMillis() - time1.getMillis());

    }

    @Test
    public void test6() throws Exception {

        CyclicBuffer<CyclicBarrier> buffer = new CyclicBuffer<>(CyclicBarrier.class, 1024);
        buffer.isEmpty();

        CyclicBarrier barrier = new CyclicBarrier(30);
        barrier.reset();

        barrier.isBroken();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        lock.unlock();

        DateTime time1 = DateTime.now();

        for (int j = 0; j < size1; j++) {
            for (int i = 0; i < size; i++) {
                //                TaxFeeTaskEventBus.publishEvent3(request, response, futures[j * size + i], traceId, caller, processStart, mainThread);
            }
        }
        //        CompletableFuture<Void> all = CompletableFuture.allOf(futures);
        //        try {
        //            all.get(size1 * timeOutMillSeconds, TimeUnit.MILLISECONDS);
        //        } catch (Exception e) {
        //            System.out.println("税费计算部分超时");
        //        }

        DateTime time2 = DateTime.now();
        System.out.printf("test4 estimate time %sms.%n", time2.getMillis() - time1.getMillis());

    }

    @Test
    public void test99() {

        CompletableFuture<Object> future = new CompletableFuture<>();

        future.cancel(true);
        System.out.println(future.isCancelled() + "/" + future.isDone());

        future.complete(null);
        System.out.println(future.isCancelled() + "/" + future.isDone());

        future.cancel(true);
        System.out.println(future.isCancelled() + "/" + future.isDone());

        future.complete(null);
        System.out.println(future.isCancelled() + "/" + future.isDone());

    }

    @Test
    public void test1() throws Exception {

        DateTime time1 = DateTime.now();

        Semaphore semaphore = new Semaphore(0);

        for (int j = 0; j < size1; j++) {
            //            final Semaphore semaphore = new Semaphore(0);
            //            final Semaphore semaphore = semaphores[j];
            //            CountDownLatch latch = new CountDownLatch(size);
            for (int i = 0; i < size; i++) {
                TaxFeeTaskEventBus.publishEvent(request, response, semaphore, traceId, caller + i, processStart, mainThread);
                //                TaxFeeTaskEventBus.publishEvent2(request, response, latch, traceId, caller, processStart, mainThread);
            }
            //            boolean ret = semaphore.tryAcquire(size, timeOutMillSeconds, TimeUnit.MILLISECONDS);
            //            //            boolean ret = latch.await(timeOutMillSeconds, TimeUnit.MILLISECONDS);
            //            if (!ret) {
            //                System.out.println("税费计算部分超时: " + semaphore.availablePermits() + "/" + size);
            //                //                System.out.println("税费计算部分超时: " + latch.getCount() + "/" + size);
            //            }
        }

        boolean ret = semaphore.tryAcquire(size1 * size, size1 * timeOutMillSeconds, TimeUnit.MILLISECONDS);
        if (!ret) {
            System.out.println("税费计算部分超时: " + semaphore.availablePermits() + "/" + size);
        }

        DateTime time2 = DateTime.now();

        System.out.printf("test1 estimate time %sms.%n", time2.getMillis() - time1.getMillis());

    }

    @Test
    public void test11() throws Exception {

        Semaphore[] semaphores = new Semaphore[size1];
        for (int j = 0; j < size1; j++) {
            semaphores[j] = new Semaphore(0);
        }

        DateTime time1 = DateTime.now();

        for (int j = 0; j < size1; j++) {
            final Semaphore semaphore = semaphores[j];
            for (int i = 0; i < size; i++) {
                TaxFeeTaskEventBus.publishEvent(request, response, semaphore, traceId, caller + i, processStart, mainThread);
            }
            boolean ret = semaphore.tryAcquire(size, timeOutMillSeconds, TimeUnit.MILLISECONDS);
            if (!ret) {
                System.out.println("税费计算部分超时: " + semaphore.availablePermits() + "/" + size);
            }
        }

        DateTime time2 = DateTime.now();

        System.out.printf("test11 estimate time %sms.%n", time2.getMillis() - time1.getMillis());

    }

    @Test
    public void test12() throws Exception {

        DateTime time1 = DateTime.now();

        LinkedList<Semaphore> semaphores = new LinkedList<>();

        for (int j = 0; j < size1; j++) {
            Semaphore semaphore = semaphores.pollFirst();
            if (semaphore != null) {
                semaphore.drainPermits();
            } else {
                semaphore = new Semaphore(0);
            }

            for (int i = 0; i < size; i++) {
                TaxFeeTaskEventBus.publishEvent(request, response, semaphore, traceId, caller + i, processStart, mainThread);
            }
            boolean ret = semaphore.tryAcquire(size, timeOutMillSeconds, TimeUnit.MILLISECONDS);
            if (!ret) {
                System.out.println("税费计算部分超时: " + semaphore.availablePermits() + "/" + size);
            }
            semaphores.addLast(semaphore);
        }

        DateTime time2 = DateTime.now();

        System.out.println("size: " + semaphores.size());

        System.out.printf("test12 estimate time %sms.%n", time2.getMillis() - time1.getMillis());
    }

    private CompletableFuture<Object>[] process(Object request, Object response, String traceId,
                                                String caller, DateTime processStart, String mainThread) {

        CompletableFuture<Object>[] feeTaxMsgRets = new CompletableFuture[size];
        for (int i = 0; i < size; i++) {
            // 异步计算
            CompletableFuture<Object> feeTaxMsgRet = CompletableFuture.supplyAsync(() -> {
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(10));
                return response;
            }, executor);
            feeTaxMsgRets[i] = feeTaxMsgRet;
        }

        return feeTaxMsgRets;
    }

}
