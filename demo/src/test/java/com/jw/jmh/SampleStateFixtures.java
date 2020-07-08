package com.jw.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * guoyy com.jw.jmh
 *
 * @Description: com.jw.jmh.SampleStateFixtures
 * @Author: guoyiyong/james
 * @Date: 2020-01-06 00:08
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@State(Scope.Thread)
public class SampleStateFixtures {
    double x;

    @Setup
    public void prepare() {
        x = Math.PI;
    }

    @TearDown
    public void check() {
        assert x > Math.PI : "Nothing changed?";
    }

    @Benchmark
    public void measureRight() {
        x++;
    }

    /**
     * assertion 默认关闭，java -ea 打开该功能，java -da 关闭该功能
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SampleStateFixtures.class.getSimpleName())
                .forks(1)
                .jvmArgs("-ea")
                .build();

        new Runner(opt).run();
    }
}
