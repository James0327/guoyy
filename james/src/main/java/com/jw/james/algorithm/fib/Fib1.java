package com.jw.james.algorithm.fib;

/**
 * guoyy com.jw.algorithm.fib
 *
 * @Description: Fib1
 * @Author: guoyiyong/james
 * @Date: 2020-04-13 08:20
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Fib1 {

    public static void main(String[] args) {
        Fib1 fib1 = new Fib1();

        long ret2 = fib1.cal2(91);
        System.out.println(ret2);
        System.out.println("~~~~~~~~~~~~~~~");

        long ret = fib1.cal(91);
        System.out.println(ret);
    }

    private long[] cache;

    private long cal(int n) {
        if (cache == null) {
            cache = new long[n];
        }
        if (n <= 1) {
            return n;
        }
        long c = -1;
        if (cache[n - 1] != 0) {
            c = cache[n - 1];
        } else {
            c = cal(n - 1);
            cache[n - 1] = c;
        }
        return c + cal(n - 2);
    }

    private long cal2(int max) {
        if (max <= 2) {
            return max;
        }

        long f1 = 1;
        long f2 = 1;
        long ret = 0;

        for (int i = 3; i <= max; i++) {
            ret = f1 + f2;
            f1 = f2;
            f2 = ret;
        }

        return ret;
    }

}
