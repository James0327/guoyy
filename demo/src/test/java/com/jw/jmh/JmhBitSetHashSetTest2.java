package com.jw.jmh;

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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * guoyy com.jw.jmh
 *
 * @Description: com.jw.jmh.JmhBitSetHashSetTest
 * @Author: guoyiyong/james
 * @Date: 2021-02-02 18:13
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
public class JmhBitSetHashSetTest2 {

    /**
     * Benchmark                      (dayweek)  Mode  Cnt    Score    Error  Units
     * JmhBitSetHashSetTest2.bitset     7612345  avgt    3   16.572 ±  1.839  ns/op
     * JmhBitSetHashSetTest2.hashset    7612345  avgt    3  108.601 ± 51.460  ns/op
     * JmhBitSetHashSetTest2.bitset       71452  avgt    3   12.836 ±  3.263  ns/op
     * JmhBitSetHashSetTest2.hashset      71452  avgt    3   85.105 ± 68.533  ns/op
     */
    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder().include(JmhBitSetHashSetTest2.class.getSimpleName())
                .warmupIterations(3)
                .measurementIterations(3)
                .timeout(TimeValue.seconds(30))
                .forks(1).build();
        new Runner(options).run();
    }

    @Benchmark
    public void hashset(Blackhole b) {
        Set<Integer> effectiveDayWeek = new HashSet<>();
        for (int j = 0, len2 = dayweek.length(); j < len2; j++) {
            int idx = dayweek.charAt(j) - 48;
            effectiveDayWeek.add(idx);
        }
        b.consume(effectiveDayWeek.contains(3));
    }

    @Benchmark
    public void bitset(Blackhole b) {
        BitSet effectiveDayWeek = new BitSet(7);
        for (int j = 0, len2 = dayweek.length(); j < len2; j++) {
            int idx = dayweek.charAt(j) - 48;
            effectiveDayWeek.set(idx);
        }
        b.consume(effectiveDayWeek.get(3));
    }

    @Param({"7612345", "71452"})
    private String dayweek;

    @Setup
    public void init() {
        System.out.println("dayweek: " + dayweek);
    }

    @TearDown
    public void shutdown() {
        dayweek = null;
    }

}
