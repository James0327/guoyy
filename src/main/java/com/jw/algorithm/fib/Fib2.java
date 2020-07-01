package com.jw.algorithm.fib;

/**
 * guoyy com.jw.algorithm.fib
 *
 * @Description: com.jw.algorithm.fib.Fib2
 * @Author: guoyiyong/james
 * @Date: 2020-06-16 01:00
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Fib2 {

    public static void main(String[] args) {
        Fib2 fib2 = new Fib2();
        int ret1 = fib2.fib(1);
        int ret2 = fib2.fib(2);
        int ret3 = fib2.fib(3);
        int ret4 = fib2.fib(4);
        int ret5 = fib2.fib(5);
        int ret6 = fib2.fib(6);
        int ret7 = fib2.fib(7);

        System.out.println(ret1);
        System.out.println(ret2);
        System.out.println(ret3);
        System.out.println(ret4);
        System.out.println(ret5);
        System.out.println(ret6);
        System.out.println(ret7);
    }

    private int fib(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        int first = 1;
        int second = 1;
        int curr = -1;
        for (int i = 3; i <= n; i++) {
            curr = first + second;
            first = second;
            second = curr;
        }
        return curr;
    }

}
