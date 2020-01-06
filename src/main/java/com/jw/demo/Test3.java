package com.jw.demo;

/**
 * guoyy com.jw.demo
 *
 * @Description: com.jw.demo.Test3
 * @Author: guoyiyong/james
 * @Date: 2019-08-04 18:45
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public final class Test3 {

    private static class Singleton {
        private static final Test3 test3 = new Test3();
    }

    public static Test3 getInstance() {
        return Singleton.test3;
    }

}
