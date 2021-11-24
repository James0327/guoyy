package com.jw.james.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * guoyy com.jw.jmh
 *
 * @Description: com.jw.jmh.JmhCompilerControl
 * @Author: guoyiyong/james
 * @Date: 2020-01-06 00:48
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class JmhCompilerControl {

    public void targetBlank() {}

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void targetDontInline() {}

    @CompilerControl(CompilerControl.Mode.INLINE)
    public void targetInline() {}

    @Benchmark
    public void baseline() {}

    @Benchmark
    public void dontInline() {
        targetDontInline();
    }

    @Benchmark
    public void inline() {
        targetInline();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JmhCompilerControl.class.getSimpleName())
                .warmupIterations(0)
                .measurementIterations(3)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
