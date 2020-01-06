package com.jw.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.LinkedList;
import java.util.List;

/**
 * guoyy com.jw.jmh
 *
 * @Description: com.jw.jmh.JmhBatchSize
 * @Author: guoyiyong/james
 * @Date: 2020-01-06 00:37
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@State(Scope.Thread)
public class JmhBatchSize {

    List<String> list = new LinkedList<>();

    // 每个iteration中做5000次Invocation
    @Benchmark
    @Warmup(iterations = 5, batchSize = 5000)
    @Measurement(iterations = 5, batchSize = 5000)
    @BenchmarkMode(Mode.SingleShotTime)
    public List<String> measureRight() {
        list.add(list.size() / 2, "something");
        return list;
    }

    @Setup(Level.Iteration)
    public void setup() {
        list.clear();
    }

    public static void main(String[] args) throws RunnerException {
        Options build = new OptionsBuilder().include(JmhBatchSize.class.getSimpleName()).forks(1).build();
        new Runner(build).run();
    }

}
