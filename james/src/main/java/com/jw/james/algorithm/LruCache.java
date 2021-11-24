package com.jw.james.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

/**
 * guoyy com.jw.algorithm
 *
 * @Description: LruCache
 * @Author: guoyiyong/james
 * @Date: 2019-08-12 00:13
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class LruCache {
    private int maxSize;

    private Queue<Integer> queue;

    private LongAdder sum = new LongAdder();

    public LruCache(int maxSize) {
        this.maxSize = maxSize;
        queue = new LinkedList<>();
    }

    public void add(int i) {
        boolean exists = queue.contains(i);
        if (exists) {
            sum.increment();
            queue.remove(i);
            queue.add(i);
        } else {
            if (queue.size() < maxSize) {
                queue.add(i);
            } else {
                queue.remove();
                queue.add(i);
            }
        }
    }

    public static void main(String[] args) {
        LruCache cache = new LruCache(5);

        for (int i = 0; i < 100000; i++) {
            cache.add(ThreadLocalRandom.current().nextInt(0, 100));
        }

        System.out.println(cache.sum.sum());
    }

}
