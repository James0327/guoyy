package com.jw.james.t;

import org.apache.commons.collections.MapUtils;
import org.joda.time.LocalDate;

import java.util.NavigableMap;
import java.util.TreeMap;
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
    private static final ReentrantLock lock = new ReentrantLock();
    Condition f1 = lock.newCondition();
    Condition f2 = lock.newCondition();
    Condition f3 = lock.newCondition();

    private volatile int idx = 1;

    public Foo() {
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int monthOfYear = now.getMonthOfYear();
        System.out.println("year:" + year);
        System.out.println("monthOfYear:" + monthOfYear);

        TreeMap<Integer, Integer> cache = new TreeMap<>();
        cache.put(3, 1);
        cache.put(6, 2);
        cache.put(9, 3);
        cache.put(12, 4);

        for (int i = 1; i <= 12; i++) {
            NavigableMap<Integer, Integer> submap = cache.headMap(i, true);
            if (MapUtils.isEmpty(submap)) {
                System.out.println(i + "/" + 1);
                continue;
            }
            Integer q = submap.lastEntry().getValue();
            System.out.println(i + "/" + q);
        }
        System.out.println();

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
