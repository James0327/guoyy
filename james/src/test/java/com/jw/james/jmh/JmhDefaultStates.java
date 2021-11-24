package com.jw.james.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;

/**
 * guoyy com.jw.jmh
 * <p>
 * 在Main类中直接使用@State作为注解，是Main类直接成为“PropertyHolder”
 *
 * @Description: com.jw.jmh.JmhDefaultStates
 * @Author: guoyiyong/james
 * @Date: 2020-01-06 00:01
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@State(Scope.Thread)
public class JmhDefaultStates {

    double x = Math.PI;

    @Benchmark
    public void measure() {
        x++;
    }

    public static void main(String[] args) throws Exception {
        Options build = new OptionsBuilder()
                .include(JmhDefaultStates.class.getSimpleName())
                .forks(1).build();
        Collection<RunResult> ret = new Runner(build).run();
        System.out.println(ret);
    }

}
