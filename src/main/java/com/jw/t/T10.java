package com.jw.t;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: test com.jw.t.T10
 * @Package: com.jw
 * @ClassName: T10
 * @Author: james.guo
 * @Date: 2019/5/14 19:36
 * @Version: 1.0
 */
public class T10 {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Random r = new Random();

        AtomicLong i = new AtomicLong();
        CompletableFuture<Long>[] futures = list.stream().map(idx -> {
            CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(r.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long l = i.incrementAndGet();
                System.out.println("idx:" + idx + "][" + l);
                return l;
            });
            return future;
        }).toArray(size -> new CompletableFuture[size]);

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);

        allOf.get(100, TimeUnit.SECONDS);

        for (int j = 0, len = futures.length; j < len; j++) {
            System.out.println(j + ":" + futures[j].get() + "][" + JSON.toJSONString(futures[j]));
        }

    }

}
