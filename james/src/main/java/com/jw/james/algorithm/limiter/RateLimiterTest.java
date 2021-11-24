package com.jw.james.algorithm.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * guoyy com.jw.algorithm.limiter
 *
 * @Description: com.jw.algorithm.limiter.RateLimiterTest
 * @Author: guoyiyong/james
 * @Date: 2020-07-29 22:50
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class RateLimiterTest {

    public static void main(String[] args) throws InterruptedException {

        RateLimiter limiter = RateLimiter.create(100);

        TimeUnit.SECONDS.sleep(3);

        double rate = limiter.getRate();
        System.out.println(rate);

        boolean ret = limiter.tryAcquire(301);
        System.out.println(ret);

        for (int i = 0; i < 100; i++) {
            boolean ret0 = limiter.tryAcquire(100);
            System.out.println(i + "][" + ret0);
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(500));
        }

    }

}
