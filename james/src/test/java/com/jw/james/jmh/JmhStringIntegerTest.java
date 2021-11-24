package com.jw.james.jmh;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
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
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * guoyy com.jw.jmh
 *
 * @Description: com.jw.jmh.JmhStringIntegerTest
 * @Author: guoyiyong/james
 * @Date: 2021-01-17 16:05
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode({Mode.AverageTime})
public class JmhStringIntegerTest {

    /**
     * Benchmark                     (len)  Mode  Cnt       Score        Error  Units
     * JmhStringIntegerTest.iVal   1000000  avgt    3    6367.693 ±    591.167  us/op
     * JmhStringIntegerTest.sVal   1000000  avgt    3   31627.384 ±   8038.054  us/op
     * JmhStringIntegerTest.iVal  10000000  avgt    3   65795.010 ±  15292.617  us/op
     * JmhStringIntegerTest.sVal  10000000  avgt    3  324051.305 ± 128249.746  us/op
     */
    public static void main(String[] args) throws RunnerException {
        Options build = new OptionsBuilder().include(JmhStringIntegerTest.class.getSimpleName())
                .warmupIterations(3)
                .measurementIterations(3)
                .timeout(TimeValue.seconds(30))
                .forks(1).build();
        new Runner(build).run();
    }

    @Data
    @AllArgsConstructor
    private class Foo {
        private String sVal;
        private int iVal;
    }

    @Param({"1000000", "10000000"})
    private int len;
    private List<Foo> vals = Lists.newArrayList();

    @Benchmark
    public void iVal(Blackhole b) {
        for (int i = 0, len = vals.size(); i < len; i++) {
            Foo foo = vals.get(i);
            if (foo.getIVal() <= 4) {
                b.consume(i);
            }
        }
    }

    @Benchmark
    public void sVal(Blackhole b) {
        for (int i = 0, len = vals.size(); i < len; i++) {
            Foo foo = vals.get(i);
            if (Integer.parseInt(foo.getSVal()) <= 4) {
                b.consume(i);
            }
        }
    }

    @Setup
    public void init() {
        int[] arr = IntStream.rangeClosed(1, len).toArray();
        for (int i = 0; i < len; i++) {
            vals.add(new Foo("" + arr[i], arr[i]));
        }
        System.out.println("vals size: " + vals.size());
    }

    @TearDown
    public void shutdown() {
        vals.clear();
    }

}
