package com.jw.test;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * guoyy com.jw.test
 *
 * @Description: com.jw.test.Test3
 * @Author: guoyiyong/james
 * @Date: 2019-08-04 20:45
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test3 {

    @Test
    public void test() {

        CompletableFuture<com.jw.demo.Test3>[] futures = IntStream.range(0, 10).boxed().map(i -> {
            CompletableFuture<com.jw.demo.Test3> future = CompletableFuture.supplyAsync(() -> {
                com.jw.demo.Test3 test3 = com.jw.demo.Test3.getInstance();
                return test3;
            });
            return future;
        }).toArray(size -> new CompletableFuture[size]);

        CompletableFuture<Void> all = CompletableFuture.allOf(futures).thenRun(() -> {
            System.out.println("all task done.");
        });
        try {
            all.get(10, TimeUnit.SECONDS);
            Stream.of(futures).forEach(future -> {
                try {
                    System.out.println("future:" + future.get());
                } catch (Exception e) {
                }
            });
        } catch (Exception e) {
            Stream.of(futures).forEach(future -> future.cancel(true));
            all.cancel(true);
        }

        for (int i = 0; i < 10; i++) {
            com.jw.demo.Test3 test3 = com.jw.demo.Test3.getInstance();
            System.out.println("for:" + test3);
        }
    }

}
