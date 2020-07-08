package com.jw;

/**
 * guoyy com.jw.demo
 *
 * @Description: Test2
 * @Author: guoyiyong/james
 * @Date: 2019-08-04 17:54
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public final class Test2 {

    private Test2() {
        System.out.println("test2 ...");
    }

    private enum Singleton {
        ;

        Singleton() {
            System.out.println("Singleton ... ");
        }

        private static final Test2 test2 = new Test2();
    }

    public static Test2 getInstance() {
        return Singleton.test2;
    }

}
