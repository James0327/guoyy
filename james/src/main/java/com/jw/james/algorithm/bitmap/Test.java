package com.jw.james.algorithm.bitmap;

import org.joda.time.DateTime;

/**
 * guoyy com.jw.algorithm.bitmap
 *
 * @Description: com.jw.algorithm.bitmap.Test
 * @Author: guoyiyong/james
 * @Date: 2020-07-08 16:52
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Test {

    public static void main(String[] args) {
        Runtime r = Runtime.getRuntime();
        long totalMemory = r.totalMemory();
        long freeMemory = r.freeMemory();
        long maxMemory = r.maxMemory();

        System.out.println(String.format("maxMemory:%s,totalMemory:%s,freeMemory:%s",
                maxMemory / 1024D / 1024, totalMemory / 1024D / 1024, freeMemory / 1024D / 1024));

        DateTime t1 = DateTime.now();

        int size = 10_00_000_000;

        // 一个int 4个字节
        int bytes = Integer.SIZE / Byte.SIZE;

        System.out.println("size(M): " + (size / 1024D / 1024 * bytes));
        System.out.println("min size(M): " + size / 8 / 1024D / 1024);

        int[] bigArr = new int[size];

        for (int i = 0; i < size; i++) {
            bigArr[i] = i;
        }

        DateTime t2 = DateTime.now();

        System.out.println("estimated time [ms]: " + (t2.getMillis() - t1.getMillis()));

    }

}
