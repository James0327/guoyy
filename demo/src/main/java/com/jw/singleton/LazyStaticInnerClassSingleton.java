package com.jw.singleton;

import java.io.Serializable;

/**
 * guoyy com.jw.singleton
 * <p>
 * 优点：避免内存浪费，线程安全，提高了性能，解决反射破坏问题
 * 缺点：不够优雅
 *
 * @Description: com.jw.singleton.LazyStaticInnerClassSingleton
 * @Author: guoyiyong/james
 * @Date: 2020-09-09 21:22
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class LazyStaticInnerClassSingleton implements Serializable {

    private LazyStaticInnerClassSingleton() {
        if (LazyHolder.INSTANCE != null) {
            throw new UnsupportedOperationException();
        }
    }

    public static LazyStaticInnerClassSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final LazyStaticInnerClassSingleton INSTANCE = new LazyStaticInnerClassSingleton();
    }

    // preserving singleton-ness gives equals()/hashCode() for free
    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }
}
