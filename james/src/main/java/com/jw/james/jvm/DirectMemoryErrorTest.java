package com.jw.james.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * guoyy com.jw.jvm
 *
 * @Description: com.jw.jvm.DirectMemoryErrorTest
 * @Author: guoyiyong/james
 * @Date: 2020-07-15 18:33
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class DirectMemoryErrorTest {

    /**
     * IDE集成开发环境，可能会因为内存不足结束执行程序
     *
     * -Xms20m -Xmx20m
     * -XX:MaxDirectMemorySize=10m
     * -XX:+HeapDumpOnOutOfMemoryError
     * -XX:HeapDumpPath=logs
     * -XX:+PrintGCDetails
     * -XX:+PrintGCTimeStamps
     * -Xloggc:logs/gc.log
     */
    public static void main(String[] args) {
        int i = 0;
        try {
            Field field = Unsafe.class.getDeclaredFields()[0];
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe)field.get(null);
            while (true) {
                unsafe.allocateMemory(1024 * 1024);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("运行次数：" + i);
        }
    }

}
