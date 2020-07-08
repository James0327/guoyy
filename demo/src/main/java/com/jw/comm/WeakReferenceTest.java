package com.jw.comm;

import com.jw.dto.Foo;

import java.lang.ref.WeakReference;
import java.time.LocalDateTime;
import java.util.WeakHashMap;

/**
 * 弱引用关联对象何时被回收
 * <p>
 * guoyy com.jw.comm
 *
 * @Description: WeakReferenceTest
 * @Author: guoyiyong/james
 * @Date: 2019-12-01 00:01
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class WeakReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        // 1MB=1024KB=1024*1024B
        byte[] cacheData = new byte[100 * 1024 * 1024];
        WeakReference<byte[]> cacheRef = new WeakReference<>(cacheData);

        System.out.println("before first gc: " + cacheData);
        System.out.println("before first gc: " + cacheRef.get());

        System.gc();
        // waiting gc
        Thread.sleep(1000);

        System.out.println("after first gc: " + cacheData);
        System.out.println("after first gc: " + cacheRef.get());

        // 去除强引用
        cacheData = null;
        System.gc();
        Thread.sleep(1000);

        System.out.println("after second gc: " + cacheData);
        System.out.println("after second gc: " + cacheRef.get());

        System.out.println("---------");
        Object obj = new Object();

        WeakHashMap<String, Foo> map = new WeakHashMap<>();
        ThreadLocal<Foo> threadLocal = new ThreadLocal<>();

        System.out.println(map);
        System.out.println(threadLocal);

        System.out.println(LocalDateTime.now());
        synchronized (obj) {
            obj.wait(5 * 1000);
        }
        System.out.println(LocalDateTime.now());
    }

}
