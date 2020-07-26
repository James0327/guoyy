package com.jw.t;

import java.util.concurrent.Semaphore;

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
public class Foo2 {
    private Semaphore s2 = new Semaphore(0);
    private Semaphore s3 = new Semaphore(0);

    public static void main(String[] args) {
        Foo2 foo = new Foo2();
        new Thread(() -> foo.three()).start();
        new Thread(() -> foo.two()).start();
        new Thread(() -> foo.one()).start();
    }

    public Foo2() {
    }

    public void one() {
        System.out.println("one");
    }

    public void two() {
        try {
            s2.acquire();
            System.out.println("two");
            s3.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void three() {
        try {
            s3.acquire();
            System.out.println("three");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
