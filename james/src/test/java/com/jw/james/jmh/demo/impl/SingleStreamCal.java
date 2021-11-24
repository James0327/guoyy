package com.jw.james.jmh.demo.impl;

import com.jw.james.jmh.demo.Calculator;

import java.util.stream.IntStream;

/**
 * guoyy com.jw.jmh.demo.impl
 *
 * @Description: com.jw.jmh.demo.impl.SingleStreamCal
 * @Author: guoyiyong/james
 * @Date: 2020-01-08 12:06
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class SingleStreamCal implements Calculator {
    @Override
    public long sum(int[] numbers) {
        return IntStream.of(numbers).sum();
    }
}
