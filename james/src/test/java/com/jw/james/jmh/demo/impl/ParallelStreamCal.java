package com.jw.james.jmh.demo.impl;

import com.jw.james.jmh.demo.Calculator;

import java.util.stream.IntStream;

/**
 * guoyy com.jw.jmh.demo.impl
 *
 * @Description: com.jw.jmh.demo.impl.ParallelStreamCal
 * @Author: guoyiyong/james
 * @Date: 2020-01-08 11:54
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class ParallelStreamCal implements Calculator {
    @Override
    public long sum(int[] numbers) {
        // IntStream stream = Arrays.stream(numbers);
        return IntStream.of(numbers).parallel().sum();
    }
}
