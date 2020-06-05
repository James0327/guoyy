package com.jw.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * guoyy com.jw.demo
 *
 * @Description: com.jw.demo.Test1
 * @Author: guoyiyong/james
 * @Date: 2019-08-04 17:01
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
@Slf4j
public final class Test1 {

    private Test1() {
        System.out.println("Test1 ...");
    }

    private enum Singleton {
        INSTANCE;
        private Test1 test1;

        Singleton() {
            this.test1 = new Test1();
            System.out.println("Singleton ..., " + this.test1);
        }

        private static Test1 getInstance() {
            System.out.println("Singleton getInstance," + INSTANCE.test1);
            return INSTANCE.test1;
        }
    }

    public static Test1 getInstance() {
        System.out.println("Test1 getInstance");
        return Singleton.getInstance();
    }

}
