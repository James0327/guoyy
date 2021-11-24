package com.jw.james.t;

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
public class Foo3 {
    private Object lock2 = new Object();
    private Object lock3 = new Object();

    public static void main(String[] args) {
        Foo3 foo = new Foo3();
        new Thread(() -> foo.three()).start();
        new Thread(() -> foo.two()).start();
        new Thread(() -> foo.one()).start();
    }

    public Foo3() {
    }

    public void one() {
        System.out.println("one");
        synchronized (this) {
            lock2.notifyAll();
        }
    }

    public void two() {
        try {
            synchronized (this) {
                lock2.wait();
            }
            System.out.println("two");
            synchronized (this) {
                lock3.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void three() {
        try {
            synchronized (this) {
                lock3.wait();
            }
            System.out.println("three");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
