package com.jw.james.jmh;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
public class JmhBitSetHashSetTest {

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder().include(JmhBitSetHashSetTest.class.getSimpleName())
                .warmupIterations(3)
                .measurementIterations(3)
                .timeout(TimeValue.seconds(30))
                .forks(1).build();
        new Runner(options).run();
    }

    @Benchmark
    public void hashset1(Blackhole b) {
        for (int i = 0, len = vals.size(); i < len; i++) {
            char[] dayWeek = vals.get(i).toCharArray();
            Set<Integer> effectiveDayWeek = new HashSet<>();
            for (int j = 0, len2 = dayWeek.length; j < len2; j++) {
                effectiveDayWeek.add(Integer.parseInt(dayWeek[j] + ""));
            }
            b.consume(effectiveDayWeek.contains(3));
        }
    }

    @Benchmark
    public void hashset2(Blackhole b) {
        for (int i = 0, len = vals.size(); i < len; i++) {
            String dayWeek = vals.get(i);
            Set<Integer> effectiveDayWeek = new HashSet<>();
            for (int j = 0, len2 = dayWeek.length(); j < len2; j++) {
                effectiveDayWeek.add(dayWeek.charAt(j) - 48);
            }
            b.consume(effectiveDayWeek.contains(3));
        }
    }

    @Benchmark
    public void bitset1(Blackhole b) {
        for (int i = 0, len = vals.size(); i < len; i++) {
            char[] dayWeek = vals.get(i).toCharArray();
            BitSet effectiveDayWeek = new BitSet();
            for (int j = 0, len2 = dayWeek.length; j < len2; j++) {
                effectiveDayWeek.set(Integer.parseInt(dayWeek[j] + ""));
            }
            b.consume(effectiveDayWeek.get(3));
        }
    }

    @Benchmark
    public void bitset2(Blackhole b) {
        for (int i = 0, len = vals.size(); i < len; i++) {
            String dayWeek = vals.get(i);
            BitSet effectiveDayWeek = new BitSet();
            for (int j = 0, len2 = dayWeek.length(); j < len2; j++) {
                effectiveDayWeek.set(dayWeek.charAt(j) - 48);
            }
            b.consume(effectiveDayWeek.get(3));
        }
    }

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            int[] ints = IntStream.rangeClosed(ThreadLocalRandom.current().nextInt(1, 7),
                    ThreadLocalRandom.current().nextInt(1, 7)).toArray();
            String ret = Arrays.stream(ints).mapToObj(Integer::toString).collect(Collectors.joining());
            vals.add(ret);
        }
        System.out.println("vals size: " + vals.size());
    }

    @Param({"1000000", "10000000"})
    private int len;
    private List<String> vals = Lists.newArrayList();

    @Setup
    public void init() {
        int cnt = 0;
        while (true) {
            if (cnt >= len) {
                break;
            }
            int[] ints = IntStream.rangeClosed(ThreadLocalRandom.current().nextInt(1, 7),
                    ThreadLocalRandom.current().nextInt(1, 7)).toArray();
            String ret = Arrays.stream(ints).mapToObj(Integer::toString).collect(Collectors.joining());
            if (StringUtils.isNotEmpty(ret)) {
                vals.add(ret);
                cnt++;
            }
        }
        System.out.println("vals size: " + vals.size());
    }

    @TearDown
    public void shutdown() {
        vals.clear();
    }

}
