package com.jw.jvm;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * guoyy com.jw.jvm
 *
 * @Description: com.jw.jvm.OutOfMemoryErrorTest
 * @Author: guoyiyong/james
 * @Date: 2020-07-15 16:42
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class OutOfMemoryErrorTest {

    /**
     * 创建对象时如果没有可以分配的堆内存，JVM就会抛出OutOfMemoryError:java heap space异常。
     *
     * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=logs -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:logs/gc.log
     */
    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();

        System.out.println(String.format("totalMemory(m):%s", totalMemory / 1024D / 1024));
        System.out.println(String.format("maxMemory(m):%s", maxMemory / 1024D / 1024));
        System.out.println(String.format("freeMemory(m):%s", freeMemory / 1024D / 1024));

        List<byte[]> list = Lists.newArrayList();
        int i = 0;
        while (true) {
            // 5*1024*1024 5M
            list.add(new byte[5 * 1024 * 1024]);
            System.out.println("分配次数：" + (++i));
        }

    }

}
