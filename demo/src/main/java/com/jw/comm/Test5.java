package com.jw.comm;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * guoyy com.jw.comm
 *
 * @Description: Test5
 * @Author: guoyiyong/james
 * @Date: 2019-12-23 16:25
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test5 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition task = lock.newCondition();

    private static AtomicBoolean isMq = new AtomicBoolean();

    public static void main(String[] args) {
        Runnable taskR = () -> {
            try {
                lock.lock();
                if (isMq.get()) {
                    System.out.println("current thread wait");
                    // Causes the current thread to wait until it is signalled or interrupted.
                    // The lock associated with this Condition is atomically released and the current thread
                    // becomes disabled for thread scheduling purposes and lies dormant.
                    task.await();
                }
                System.out.println("task running ... " + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        };
        Runnable mqR = () -> {
            try {
                lock.lock();
                isMq.set(true);
                System.out.println("mq running ... " + System.currentTimeMillis());
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(6));
                isMq.set(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("sigal task");
                task.signalAll();
                lock.unlock();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(mqR, "mqR-" + i).start();
            new Thread(taskR, "taskR-" + i).start();
        }

    }
}
