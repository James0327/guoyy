package com.jw.james.jmh.threadpool;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Description: guoyy
 * com.jw.james.jmh.threadpool.JmhThreadTest
 *
 * @author guoyiyong/james
 * Date: 2023/4/4 00:13
 * Version: 1.0
 *
 * Copyright (C) 2023 JW All rights reserved.
 */
@State(Scope.Group)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JmhThreadTest {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(JmhThreadTest.class.getSimpleName())
                .timeout(TimeValue.seconds(30))
                .warmupIterations(2)
                .measurementIterations(2)
                .forks(1) // N轮测试
                .jvmArgs("-ea")
                .resultFormat(ResultFormatType.TEXT)
                .build();
        new Runner(options).run();
    }

    private ForkJoinPool forkJoinPool;
    private ThreadPoolExecutor threadPool;

    @Param({"7", "30"})
    private int num;

    @Setup
    public void init() {
        forkJoinPool = new ForkJoinPool(num);
        threadPool = new ThreadPoolExecutor(num, num, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1024),
                new BasicThreadFactory.Builder().namingPattern("thread-pool-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @TearDown
    public void shutdown() {
        forkJoinPool.shutdown();
        threadPool.shutdown();
        forkJoinPool = null;
        threadPool = null;
    }

    //    @Group("commThreadPool")
    //    @GroupThreads(10)
    //    @Benchmark
    public void commThreadPool(Blackhole b) {
        CountDownLatch latch = new CountDownLatch(30);
        for (int j = 0; j < 30; j++) {
            int max = j < 10 ? 99999 : j < 20 ? 9999999 : 669999;

            threadPool.submit(() -> {
                long ret = 0;
                for (int i = 0; i < max; i++) {
                    ret += i;
                }
                b.consume(ret);
                latch.countDown();
            });
        }
        try {
            boolean await = latch.await(1, TimeUnit.SECONDS);
            b.consume(await);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Group("forkJoinPool0")
    @GroupThreads(10)
    @Benchmark
    public void forkJoinPool0(Blackhole b) {
        IntStream.range(0, 30).parallel()
                .forEach(j -> {
                    long ret = 0;
                    for (int i = 0; i < 669999; i++) {
                        ret += i;
                    }
                    b.consume(ret);
                });
    }

    @Group("forkJoinPool")
    @GroupThreads(10)
    @Benchmark
    public void forkJoinPool(Blackhole b) {
        IntStream.range(0, 30).parallel()
                .forEach(j -> {
                    int max = j < 10 ? 99999 : j < 20 ? 9999999 : 669999;
                    long ret = 0;
                    for (int i = 0; i < max; i++) {
                        ret += i;
                    }
                    b.consume(ret);
                });
    }

    //    @Group("forkJoinPool2")
    //    @GroupThreads(10)
    //    @Benchmark
    public void forkJoinPool2(Blackhole b) {
        ForkJoinTask forkJoinTask = new ForkJoinTask(30);
        forkJoinPool.submit(forkJoinTask);
        forkJoinTask.join();
    }

    //    @Group("forkJoinPool3")
    //    @GroupThreads(10)
    //    @Benchmark
    public void forkJoinPool3(Blackhole b) {
        CountDownLatch latch = new CountDownLatch(30);
        for (int j = 0; j < 30; j++) {
            int max = j < 10 ? 99999 : j < 20 ? 9999999 : 669999;

            forkJoinPool.submit(() -> {
                long ret = 0;
                for (int i = 0; i < max; i++) {
                    ret += i;
                }
                b.consume(ret);
                latch.countDown();
            });
        }
        try {
            boolean await = latch.await(1, TimeUnit.SECONDS);
            b.consume(await);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ForkJoinTask extends RecursiveAction {
        private int size;

        public ForkJoinTask(int size) {
            this.size = size;
        }

        @Override
        protected void compute() {
            if (size == 1) {
                long ret = 0;
                for (int i = 0; i < 999999; i++) {
                    ret += i;
                }
                assert ret > 0;
                return;
            }
            ForkJoinTask[] tasks = new ForkJoinTask[size];
            for (int i = 0; i < size; i++) {
                tasks[i] = new ForkJoinTask(1);
            }
            invokeAll(tasks);
        }
    }

    public void futureAndCommThreadPool(Blackhole b) {
        CompletableFuture[] futures = new CompletableFuture[30];
        for (int j = 0; j < 30; j++) {
            futures[j] = CompletableFuture.runAsync(() -> {
                long ret = 0;
                for (int i = 0; i < 999; i++) {
                    ret += i;
                }
                b.consume(ret);
            }, threadPool);
        }
        try {
            CompletableFuture<Void> future = CompletableFuture.allOf(futures);
            Object o = future.get(1, TimeUnit.SECONDS);
            b.consume(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void futureAndForkJoinPool(Blackhole b) {
        CompletableFuture[] futures = new CompletableFuture[30];
        for (int j = 0; j < 30; j++) {
            futures[j] = CompletableFuture.runAsync(() -> {
                long ret = 0;
                for (int i = 0; i < 999; i++) {
                    ret += i;
                }
                b.consume(ret);
            }, forkJoinPool);
        }
        try {
            CompletableFuture<Void> future = CompletableFuture.allOf(futures);
            Object o = future.get(1, TimeUnit.SECONDS);
            b.consume(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
