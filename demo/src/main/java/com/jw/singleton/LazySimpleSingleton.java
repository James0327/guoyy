package com.jw.singleton;

/**
 * guoyy com.jw.singleton
 *
 * @Description: com.jw.singleton.LazySimpleSingleton
 * @Author: guoyiyong/james
 * @Date: 2020-09-09 20:52
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class LazySimpleSingleton {

    private static LazySimpleSingleton instance;

    private LazySimpleSingleton() {}

    /* 效率低，性能低：A线程进入getInstance方法，B线程则无法进入 */
    public final synchronized static LazySimpleSingleton getInstance() {
        if (instance == null) {
            instance = new LazySimpleSingleton();
        }
        return instance;
    }

}
