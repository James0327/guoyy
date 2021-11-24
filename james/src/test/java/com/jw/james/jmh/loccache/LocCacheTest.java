package com.jw.james.jmh.loccache;

import com.jw.james.jmh.loccache.impl.CaffeineCache;
import com.jw.james.jmh.loccache.impl.GuavaCache;
import com.jw.james.jmh.loccache.impl.JvmCache;
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
public class LocCacheTest {

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

        for (int i = 0; i < size; i++) {
            Foo tmp = foo.clone();
            tmp.setIdx(i);
            hashmap.put(Integer.toString(i), tmp);
            chashmap.put(Integer.toString(i), tmp);
        }

        StopWatch watch = new StopWatch("TEST");

        watch.start("hashmap");
        for (int i = size - 1; i >= 0; i--) {
            hashmap.get(Integer.toString(i));
        }
        watch.stop();
        watch.start("concurrenthashmap");
        for (int i = size - 1; i >= 0; i--) {
            chashmap.get(Integer.toString(i));
        }
        watch.stop();

        System.out.println(watch.prettyPrint());
    }

    public static void main(String[] args) throws RunnerException {
        for (int i = 0; i < 127; i++) {
            System.out.println(i + "][" + (char)i);
        }

        final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
        System.out.println("HASH_BITS: " + HASH_BITS);

        System.out.println("~~~~~~~~~ ~~~~~~~~~ ~~~~~~~~~ ~~~~~~~~~ ~~~~~~~~~ ~~~~~~~~~ ~~~~~~~~~");

        /**
         * Benchmark                    (size)  Mode  Cnt    Score    Error  Units
         * LocCacheTest.caffeineCache   100000  avgt    3   12.704 ± 12.594  ns/op
         * LocCacheTest.caffeineCache  1000000  avgt    3   12.801 ±  3.876  ns/op
         * LocCacheTest.caffeineCache  5000000  avgt    3   12.234 ±  1.374  ns/op
         * LocCacheTest.guavaCache      100000  avgt    3  170.173 ± 12.462  ns/op
         * LocCacheTest.guavaCache     1000000  avgt    3  169.041 ± 16.063  ns/op
         * LocCacheTest.guavaCache     5000000  avgt    3  270.504 ±  1.160  ns/op
         * LocCacheTest.jvmCache        100000  avgt    3   10.305 ±  2.412  ns/op
         * LocCacheTest.jvmCache       1000000  avgt    3   11.232 ±  0.828  ns/op
         * LocCacheTest.jvmCache       5000000  avgt    3   11.055 ±  0.110  ns/op
         * LocCacheTest.jvmCacheV2      100000  avgt    3    3.863 ±  0.613  ns/op
         * LocCacheTest.jvmCacheV2     1000000  avgt    3    3.770 ±  0.452  ns/op
         * LocCacheTest.jvmCacheV2     5000000  avgt    3    3.828 ±  0.763  ns/op
         */
        new Runner(new OptionsBuilder()
                .include("LocCacheTest\\.")
                .timeout(TimeValue.seconds(30))
                .warmupIterations(3)
                .measurementIterations(3)
                .threads(4).forks(1)
                .resultFormat(ResultFormatType.JSON)
                .result("LocCacheTest.json")
                .build()).run();
    }

    @Benchmark
    public void jvmCache(Blackhole hole) {
        Foo foo = jvmCache.get(key);
        hole.consume(foo);
    }

    @Benchmark
    public void jvmCacheV2(Blackhole hole) {
        Foo foo = jvmCache2.get(key);
        hole.consume(foo);
    }

    @Benchmark
    public void guavaCache(Blackhole hole) {
        Foo foo = guavaCache.get(key);
        hole.consume(foo);
    }

    @Benchmark
    public void caffeineCache(Blackhole hole) {
        Foo foo = caffeineCache.get(key);
        hole.consume(foo);
    }

    private LocCache<Foo> jvmCache;
    private LocCache<Foo> jvmCache2;
    private LocCache<Foo> guavaCache;
    private LocCache<Foo> caffeineCache;

    @Param({"100000", "1000000", "5000000"})
    private int size;
    private String key;

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

        for (int i = 0; i < size; i++) {
            Foo tmp = foo.clone();
            tmp.setIdx(i);
            jvmCache.put(Integer.toString(i), tmp);
            guavaCache.put(Integer.toString(i), tmp);
            caffeineCache.put(Integer.toString(i), tmp);
        }
    }

    @TearDown
    public void desctory() {
        jvmCache = null;
        guavaCache = null;
        caffeineCache = null;
    }

}
