package com.jw.james.test;

import com.jw.james.Test2;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * test com.jw.test
 *
 * @Description: com.jw.test.Test2
 * @Author: guoyiyong/james
 * @Date: 2019-07-21 00:16
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test02 {

    @Test
    public void test() throws Exception {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new BasicThreadFactory.Builder().namingPattern("test-%d").daemon(true).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        CountDownLatch latch = new CountDownLatch(10);

        CompletionService<Integer> service = new ExecutorCompletionService<>(executor);

        List<Future<Integer>> futureList = IntStream.range(0, 10).boxed().map(i -> {
            Future<Integer> future = service.submit(() -> {
                Test2 test2 = Test2.getInstance();
                System.out.println(test2);
                latch.countDown();
                return i;
            });
            return future;
        }).collect(Collectors.toList());

        for (int i = 0, len = futureList.size(); i < len; i++) {
            Future<Integer> future = service.poll();
            System.out.println("service:" + future.get());
        }

        latch.await(10, TimeUnit.SECONDS);
        futureList.stream().forEach(e -> {
            try {
                Integer i = e.get();
                System.out.println("futureList:" + i);
            } catch (Exception e1) {
                e.cancel(true);
            }
        });

        // 关闭task提交
        executor.shutdown();
        executor.shutdownNow();

        for (int i = 0; i < 10; i++) {
            Test2 test2 = Test2.getInstance();
            System.out.println("test2:" + test2);
        }

    }

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        List<String> strings = list.subList(0, 2);

        System.out.println(strings);
        System.out.println(list);

        list.subList(2, list.size()).clear();

        System.out.println(list);

    }

}
