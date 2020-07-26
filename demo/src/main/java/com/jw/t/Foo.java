package com.jw.t;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * guoyy com.jw.t
 *
 * @Description: com.jw.t.Foo
 * @Author: guoyiyong/james
 * @Date: 2020-07-21 21:14
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Foo {
    private static ReentrantLock lock = new ReentrantLock();
    Condition f1 = lock.newCondition();
    Condition f2 = lock.newCondition();
    Condition f3 = lock.newCondition();

    private volatile int idx = 1;

    public static void main(String[] args) {
        Foo foo = new Foo();
        new Thread(() -> {
            for (int i = 0; i < 99; i++) {
                foo.one();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 99; i++) {
                foo.two();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 99; i++) {
                foo.three();
            }
        }).start();
    }

    public Foo() {
    }

    public void one() {
        lock.lock();
        try {
            while (idx != 1) {
                f1.await();
            }
            System.out.println("one");
            idx = 2;
            f2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void two() {
        lock.lock();
        try {
            while (idx != 2) {
                f2.await();
            }
            System.out.println("two");
            idx = 3;
            f3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void three() {
        lock.lock();
        try {
            while (idx != 3) {
                f3.await();
            }
            System.out.println("three");
            idx = 1;
            f1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
