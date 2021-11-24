package com.jw.james.jmh.demo;

import com.jw.james.jmh.demo.impl.ParallelStreamCal;
import com.jw.james.jmh.demo.impl.SingleStreamCal;
import com.jw.james.jmh.demo.impl.SingleThreadCal;
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
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * guoyy com.jw.jmh.demo
 *
 * @Description: com.jw.jmh.demo.DemoJmhTest
 * @Author: guoyiyong/james
 * @Date: 2020-01-08 12:09
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode({/*Mode.SampleTime,*/ Mode.AverageTime})
public class DemoJmhTest {

    public static void main(String[] args) throws RunnerException {
        Options build = new OptionsBuilder().include(DemoJmhTest.class.getSimpleName())
                .warmupIterations(3)
                .measurementIterations(3)
                .timeout(TimeValue.seconds(30))
                // jmh-visual-chart支持上传JMH的JSON结果文件然后解析成图表
                .result("demo_jmh_test.json")
                .resultFormat(ResultFormatType.JSON)
                .forks(1).build();
        new Runner(build).run();
    }

    private Calculator singleStreamCal;
    private Calculator singleThreadCal;
    private Calculator parallelStreamCal;

    @Param({"1000000", "10000000"})
    private int len;
    private int[] numbers;

    @Benchmark
    public void parallelStreamCal(Blackhole b) {
        b.consume(parallelStreamCal.sum(numbers));
        b.consumeCPU(1);
    }

    @Benchmark
    public void singleThreadCal(Blackhole b) {
        b.consume(singleThreadCal.sum(numbers));
    }

    @Benchmark
    public void singleStreamCal(Blackhole b) {
        b.consume(singleStreamCal.sum(numbers));
    }

    @Setup
    public void init() {
        numbers = IntStream.rangeClosed(1, len).toArray();
        singleStreamCal = new SingleStreamCal();
        singleThreadCal = new SingleThreadCal();
        parallelStreamCal = new ParallelStreamCal();
        System.out.println("numbers size: " + numbers.length);
    }

    @TearDown
    public void shutdown() {
        singleStreamCal = null;
        singleThreadCal = null;
        parallelStreamCal = null;
    }

}
