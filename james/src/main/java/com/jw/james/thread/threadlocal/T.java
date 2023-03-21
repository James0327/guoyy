package com.jw.james.thread.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * guoyy com.jw.thread.threadlocal
 *
 * @Description: com.jw.thread.threadlocal.T
 * @Author: guoyiyong/james
 * @Date: 2021-01-28 14:50
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class T {

    /**
     * 需要注意的是，使用TTL的时候，要想传递的值不出问题，线程池必须得用TTL加一层代理（下面会讲这样做的目的）
     */
    private static final ExecutorService executorService = TtlExecutors
            .getTtlExecutorService(new ThreadPoolExecutor(2, 8, 60, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(1024),
                    new BasicThreadFactory.Builder().namingPattern("Thread T %d").build(),
                    new ThreadPoolExecutor.AbortPolicy()));

    private final ThreadLocal<String> tl = new TransmittableThreadLocal<>();

    @Test
    public void test() {
        executorService.submit(() -> {
            System.out.println(Thread.currentThread() + ":" + tl.get());
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2));
        });

        while (true) {
            System.out.println(Thread.currentThread() + "/" + tl.get());
            int i = ThreadLocalRandom.current().nextInt(99);
            tl.set(Integer.toString(i));
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(3));

            for (int j = 0; j < 3; j++) {
                executorService.submit(() -> System.out.println(Thread.currentThread() + "::" + tl.get()));
            }
        }

    }

}
