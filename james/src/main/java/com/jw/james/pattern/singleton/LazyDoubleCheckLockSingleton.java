package com.jw.james.pattern.singleton;

/**
 * guoyy com.jw.singleton
 * <p>
 * 优点：避免了内存浪费，线程安全，提高了性能
 * 缺点：可读性差，不优雅
 *
 * @Description: com.jw.singleton.LazyDoubleCheckLockSingleton
 * @Author: guoyiyong/james
 * @Date: 2020-09-09 21:04
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class LazyDoubleCheckLockSingleton {

    // volatile作用： 1、可见性 2、禁止指令重排
    private static volatile LazyDoubleCheckLockSingleton singleton;

    private LazyDoubleCheckLockSingleton() {}

    /* Double Check Lock Singleton */
    public static final LazyDoubleCheckLockSingleton getSingleton() {
        // 控制上锁条件
        if (singleton == null) {
            synchronized (LazyDoubleCheckLockSingleton.class) {
                // 控制创建对象条件
                if (singleton == null) {
                    singleton = new LazyDoubleCheckLockSingleton();
                    // CPU 指令重排
                }
            }
        }
        return singleton;
    }
}
