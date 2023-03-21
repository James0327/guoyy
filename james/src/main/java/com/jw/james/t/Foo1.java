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
public class Foo1 {
    private volatile int idx = 1;

    public Foo1() {
    }

    public static void main(String[] args) {
        Foo1 foo = new Foo1();
        new Thread(() -> foo.three()).start();
        new Thread(() -> foo.two()).start();
        new Thread(() -> foo.one()).start();
    }

    public void three() {
        while (idx != 3) {
        }
        System.out.println("three");
    }

    public void two() {
        while (idx != 2) {
        }
        System.out.println("two");
        idx = 3;
    }

    public void one() {
        while (idx != 1) {
        }
        System.out.println("one");
        idx = 2;
    }
}
