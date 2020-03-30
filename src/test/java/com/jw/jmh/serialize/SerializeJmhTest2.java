package com.jw.jmh.serialize;

import com.jw.jmh.serialize.bo.Person;
import com.jw.jmh.serialize.impl.FastJsonImpl;
import com.jw.jmh.serialize.impl.MsgPackImpl;
import com.jw.jmh.serialize.impl.ProtostuffImpl;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.text.RandomStringGenerator;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
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

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * guoyy com.jw.jmh.serialize
 *
 * @Description: com.jw.jmh.serialize.SerializeJmhTest
 * @Author: guoyiyong/james
 * @Date: 2020-03-30 01:01
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode({Mode.SampleTime, Mode.Throughput, Mode.AverageTime})
public class SerializeJmhTest2 {
    private final RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();

    private Person obj;

    final FastJsonImpl fastJson = new FastJsonImpl();
    final MsgPackImpl msgPack = new MsgPackImpl();
    final ProtostuffImpl protostuff = new ProtostuffImpl();

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder()
                .include(SerializeJmhTest2.class.getSimpleName())
                .warmupIterations(3)
                .measurementIterations(3)
                .measurementTime(TimeValue.minutes(5))
                .result("serialize_test_jmh2.json")
                .resultFormat(ResultFormatType.JSON)
                .jvmArgs("-ea")
                .forks(1)
                .build()).run();
    }

    @Benchmark
    @BenchmarkMode({Mode.SingleShotTime})
    @Measurement(batchSize = 100_000_000)
    public void jsonSerialize(Blackhole b) {
        b.consume(fastJson.searialize(obj));
    }

    @Benchmark
    @BenchmarkMode({Mode.SingleShotTime})
    @Measurement(batchSize = 100_000_000)
    public void msgPackSerialize(Blackhole b) throws IOException {
        b.consume(msgPack.searialize(obj));
    }

    @Benchmark
    @BenchmarkMode({Mode.SingleShotTime})
    @Measurement(batchSize = 100_000_000)
    public void protostuffSerialize(Blackhole b) {
        b.consume(protostuff.searialize(obj));
    }

    @Setup
    public void init() {
        Person person = new Person();
        person.setName(generator.generate(6, 8));
        person.setAddr(generator.generate(128));
        person.setAge(generator.generate(1).charAt(0));
        person.setOccupational(generator.generate(64));
        person.setSex((byte) 0);

        this.obj = person;

        System.out.println(ToStringBuilder.reflectionToString(this.obj));
    }

    @TearDown
    public void destroy() {
        obj = null;
    }

}
