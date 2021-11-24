package com.jw.james.jmh.loccache;

import com.jw.james.jmh.loccache.impl.JvmCache;
import com.jw.james.jmh.loccache.impl.CaffeineCache;
import com.jw.james.jmh.loccache.impl.GuavaCache;
import com.jw.james.jmh.loccache.impl.JvmCache2;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
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
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * guoyy com.jw.jmh.loccache
 *
 * @Description: com.jw.jmh.loccache.LocCacheTest
 * @Author: guoyiyong/james
 * @Date: 2020-09-11 13:52
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class LocCacheTest2 {

    @Test
    public void test() {
        Foo foo = new Foo();
        foo.setAmt(2D);
        foo.setSex((byte)0);
        foo.setFlag('a');
        foo.setName("name");
        foo.setPhone("18500000000");
        foo.setSerNo(System.currentTimeMillis());

        int size = 2000000;
        Map<String, Foo> hashmap = new HashMap<>(size);
        Map<String, Foo> chashmap = new ConcurrentHashMap<>(size);

        StopWatch watch = new StopWatch("TEST");

        watch.start("hashmap");
        for (int i = 0; i < size; i++) {
            Foo tmp = foo.clone();
            tmp.setIdx(i);
            hashmap.put(Integer.toString(i), tmp);
        }
        watch.stop();
        watch.start("concurrenthashmap");
        for (int i = 0; i < size; i++) {
            Foo tmp = foo.clone();
            tmp.setIdx(i);
            chashmap.put(Integer.toString(i), tmp);
        }
        watch.stop();

        System.out.println(watch.prettyPrint());
    }

    public static void main(String[] args) throws RunnerException {

        System.out.println("~~~~~~~~~ ~~~~~~~~~ ~~~~~~~~~ ~~~~~~~~~ ~~~~~~~~~ ~~~~~~~~~ ~~~~~~~~~");

        /**
         * Benchmark                     (size)  Mode  Cnt    Score     Error  Units
         * LocCacheTest2.caffeineCache   100000  avgt    3  403.119 ± 589.215  ns/op
         * LocCacheTest2.caffeineCache  1000000  avgt    3  391.900 ±  34.249  ns/op
         * LocCacheTest2.caffeineCache  5000000  avgt    3  341.385 ±  44.704  ns/op
         * LocCacheTest2.guavaCache      100000  avgt    3  661.334 ± 103.675  ns/op
         * LocCacheTest2.guavaCache     1000000  avgt    3  734.311 ± 265.831  ns/op
         * LocCacheTest2.guavaCache     5000000  avgt    3  759.762 ± 546.126  ns/op
         * LocCacheTest2.jvmCache        100000  avgt    3   92.054 ± 168.439  ns/op
         * LocCacheTest2.jvmCache       1000000  avgt    3   89.470 ±   1.717  ns/op
         * LocCacheTest2.jvmCache       5000000  avgt    3   86.418 ±   6.083  ns/op
         * LocCacheTest2.jvmCacheV2      100000  avgt    3  387.409 ± 180.160  ns/op
         * LocCacheTest2.jvmCacheV2     1000000  avgt    3  369.822 ±  22.249  ns/op
         * LocCacheTest2.jvmCacheV2     5000000  avgt    3  423.392 ±  31.094  ns/op
         */
        new Runner(new OptionsBuilder()
                .include("LocCacheTest2\\.")
                .timeout(TimeValue.seconds(30))
                .warmupIterations(3)
                .measurementIterations(3)
                .threads(4).forks(1)
                .resultFormat(ResultFormatType.JSON)
                .result("LocCacheTest2.json")
                .build()).run();
    }

    @Benchmark
    public void jvmCache(Blackhole hole) {
        hole.consume(jvmCache.put(key, value));
    }

    @Benchmark
    public void jvmCacheV2(Blackhole hole) {
        hole.consume(jvmCache2.put(key, value));
    }

    @Benchmark
    public void guavaCache(Blackhole hole) {
        hole.consume(guavaCache.put(key, value));
    }

    @Benchmark
    public void caffeineCache(Blackhole hole) {
        hole.consume(caffeineCache.put(key, value));
    }

    private LocCache<Foo> jvmCache;
    private LocCache<Foo> jvmCache2;
    private LocCache<Foo> guavaCache;
    private LocCache<Foo> caffeineCache;

    @Param({"100000", "1000000", "5000000"})
    private int size;
    private String key;
    private Foo value;

    @Setup
    public void init() {
        jvmCache = new JvmCache();
        jvmCache2 = new JvmCache2();
        guavaCache = new GuavaCache();
        caffeineCache = new CaffeineCache();

        jvmCache.init(size);
        jvmCache2.init(size);
        guavaCache.init(size);
        caffeineCache.init(size);

        key = Integer.toString(ThreadLocalRandom.current().nextInt(size));

        Foo foo = new Foo();
        foo.setAmt(2D);
        foo.setSex((byte)0);
        foo.setFlag('a');
        foo.setName("name");
        foo.setPhone("18500000000");
        foo.setSerNo(System.currentTimeMillis());

        value = foo;
    }

    @TearDown
    public void desctory() {
        jvmCache = null;
        guavaCache = null;
        caffeineCache = null;
        value = null;
    }

}
