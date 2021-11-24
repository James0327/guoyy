package com.jw.james.jmh.demo.impl;

import com.jw.james.jmh.demo.Calculator;

/**
 * guoyy com.jw.jmh.demo.impl
 *
 * @Description: com.jw.jmh.demo.impl.SingleThreadCal
 * @Author: guoyiyong/james
 * @Date: 2020-01-08 11:54
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class SingleThreadCal implements Calculator {
    @Override
    public long sum(int[] numbers) {
        long total = 0;
        for (int i = 0, len = numbers.length; i < len; i++) {
            total += numbers[i];
        }
        return total;
    }
}
