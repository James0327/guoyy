package com.jw.james.jmh.disruptor;

import com.jw.james.disruptor.task.TaxFeeTaskEventBus;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.joda.time.DateTime;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
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

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Description: guoyy
 * com.jw.james.jmh.disruptor.TaxFeeCalTest
 *
 * @author guoyiyong/james
 * Date: 2023/2/2 17:58
 * Version: 1.0
 *
 * Copyright (C) 2023 JW All rights reserved.
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class TaxFeeCalTest {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(TaxFeeCalTest.class.getSimpleName())
                .jvmArgs("-ea")
                .resultFormat(ResultFormatType.TEXT)
                .timeout(TimeValue.seconds(30))
                .result("TaxFeeCalTest.txt")
                .forks(1)
                .threads(1)
                .measurementBatchSize(2)
                .warmupForks(1)
                .warmupBatchSize(2)
                .warmupIterations(1)
                .measurementIterations(2)
                .build();

        new Runner(options).run();
    }

    String mainThread = Thread.currentThread().toString();
    DateTime processStart = DateTime.now();

    String traceId = UUID.randomUUID().toString();
    String caller = "test";
    Object request = new Object(), response = new Object();

    long timeOutMillSeconds = 500;

    ThreadPoolExecutor executor;

    @Param({"30", "100"})
    private int size;

    @Setup(Level.Trial)
    public void init() {
        executor = new ThreadPoolExecutor(50, 50, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10000), new BasicThreadFactory.Builder().namingPattern("TaxfeeThreadPool-%d").build(),
                (Runnable r, ThreadPoolExecutor e) -> {
                    String msg = String.format("Thread pool is EXHAUSTED! Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %s, %d (completed: %d), Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)",
                            Thread.currentThread().getName(), e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(), r.toString(), e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating());
                    throw new RejectedExecutionException(msg);
                });
    }

    @TearDown(Level.Trial)
    public void destroy() {
        executor = null;
    }

    @Benchmark
    public void testThreadPool(Blackhole b) {

        CompletableFuture<Object>[] feeTaxMsgRets = process(request, response, traceId, caller, processStart, mainThread);
        CompletableFuture<Void> all = CompletableFuture.allOf(feeTaxMsgRets).thenAccept((obj) -> {

        });
        try {
            all.get(timeOutMillSeconds, TimeUnit.MILLISECONDS);
            b.consume(response);
        } catch (Exception e) {

        }

    }

    @Benchmark
    public void testDisruptor(Blackhole b) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(0);
        for (int i = 0; i < size; i++) {
            TaxFeeTaskEventBus.publishEvent(request, response,
                    semaphore, traceId, caller, processStart, mainThread);
        }
        boolean ret = semaphore.tryAcquire(size, timeOutMillSeconds, TimeUnit.MILLISECONDS);

        b.consume(ret);

    }

    private CompletableFuture<Object>[] process(Object request, Object response, String traceId,
                                                String caller, DateTime processStart, String mainThread) {

        CompletableFuture<Object>[] feeTaxMsgRets = new CompletableFuture[size];
        for (int i = 0; i < size; i++) {
            // 异步计算
            CompletableFuture<Object> feeTaxMsgRet = CompletableFuture.supplyAsync(() -> {
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200));
                return response;
            }, executor);
            feeTaxMsgRets[i] = feeTaxMsgRet;
        }

        return feeTaxMsgRets;
    }

}
