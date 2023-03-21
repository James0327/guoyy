package com.jw.james.comm;

/**
 * guoyy com.jw.comm
 *
 * @Description: ByteCodeDemo
 * @Author: guoyiyong/james
 * @Date: 2019-12-18 21:40
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class ByteCodeDemo {
    private final int a = 1;

    public int add() {
        int b = 2;
        int c = a + b;

        System.out.println(c);

        return c;
    }
}
