package com.jw.algorithm;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

/**
 * test com.jw.t.algorithm
 *
 * @Description: com.jw.t.algorithm.MinHeap
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/23 19:25
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
public class MinHeap {

    static class MyMinHeap {
        private PriorityQueue<Integer> queue;
        private int size;

        public MyMinHeap(int size) {
            this.size = size;
            queue = new PriorityQueue<>(size);
        }

        public Integer peek() {
            return queue.peek();
        }

        public void add(Integer item) {
            if (queue.size() < size) {
                queue.offer(item);
                return;
            }
            if (queue.peek() < item) {
                queue.poll();
                queue.offer(item);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[99];
        for (int i = 0; i < 99; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(99);
        }
        System.out.println(ToStringBuilder.reflectionToString(arr));
        MyMinHeap myMinHeap = new MyMinHeap(3);
        for (int i = 0, len = arr.length; i < len; i++) {
            myMinHeap.add(arr[i]);
            Integer peek = myMinHeap.peek();
            System.out.println("peek: " + peek + "/" + myMinHeap.queue.toString());
        }

    }

}
