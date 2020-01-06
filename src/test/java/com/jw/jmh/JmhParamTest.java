package com.jw.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * guoyy com.jw.jmh
 *
 * @Description: com.jw.jmh.JmhParamTest
 * @Author: guoyiyong/james
 * @Date: 2020-01-06 00:09
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@State(Scope.Benchmark)
public class JmhParamTest {
    @Param({"1", "2", "3"})
    int testNum;

    @Benchmark
    public String test() {
        return String.valueOf(testNum);
    }

    public static void main(String[] args) throws RunnerException {
        Options build = new OptionsBuilder()
                .include(JmhParamTest.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(build).run();
    }

}
