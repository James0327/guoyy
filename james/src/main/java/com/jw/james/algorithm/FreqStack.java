package com.jw.james.algorithm;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * guoyy com.jw.algorithm
 * <p>
 * FreqStack stk = new FreqStack();
 * <p>
 * // 向最大频率栈中添加元素
 * stk.push(2); stk.push(7); stk.push(2);
 * stk.push(7); stk.push(2); stk.push(4);
 * // 栈中元素：[2,7,2,7,2,4]
 * <p>
 * stk.pop() // 返回 2
 * // 因为 2 出现了三次
 * // 栈中元素：[2,7,2,7,4]
 * <p>
 * stk.pop() // 返回 7
 * // 2 和 7 都出现了两次，但 7 是最近添加的
 * // 栈中元素：[2,7,2,4]
 * <p>
 * stk.pop() // 返回 2
 * // 栈中元素：[2,7,4]
 * <p>
 * stk.pop() // 返回 4
 * // 栈中元素：[2,7]
 *
 * @Description: com.jw.algorithm.FreqStack
 * @Author: guoyiyong/james
 * @Date: 2021-01-13 14:07
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class FreqStack {

    public static void main(String[] args) {
        FreqStack stk = new FreqStack();

        int pop0 = stk.pop();
        System.out.println(pop0);

        stk.push(2);
        stk.push(7);
        stk.push(2);
        stk.push(7);
        stk.push(2);
        stk.push(4);

        System.out.println(stk);

        stk.push(7);
        stk.push(2);
        stk.push(5);
        stk.push(7);
        stk.push(2);
        stk.push(5);
        stk.push(9);
        stk.push(4);
        stk.push(3);
        stk.push(9);
        stk.push(3);
        stk.push(9);

        System.out.println(stk);

        for (int i = 0, len = stk.maxLevel << 1 << 1; i < len; i++) {
            int pop = stk.pop();
            System.out.println(String.format("pop[%s]:%s.", i, pop));
        }
    }

    private int maxLevel = 0;
    private final Map<Integer, AtomicInteger> valMaxLevel = Maps.newConcurrentMap();
    private final Map<Integer, Stack<Integer>> levelVals = Maps.newConcurrentMap();

    /**
     * 在栈中加入一个元素 val
     */
    public void push(int val) {
        int level = valMaxLevel.computeIfAbsent(val, k -> new AtomicInteger()).incrementAndGet();
        if (level > maxLevel) {
            maxLevel = level;
        }
        levelVals.computeIfAbsent(level, k -> new Stack<>()).add(val);
    }

    /**
     * 从栈中删除并返回出现频率最高的元素
     * 如果频率最高的元素不止一个，
     * 则返回最近添加的那个元素
     */
    public int pop() {
        if (maxLevel <= 0) {
            return -1;
        }
        Stack<Integer> stack = levelVals.get(maxLevel);
        Integer value = stack.pop();
        valMaxLevel.get(value).decrementAndGet();
        if (stack.isEmpty()) {
            maxLevel--;
        }
        return value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("maxLevel", maxLevel)
                .append("valMaxLevel", valMaxLevel)
                .append("levelVals", levelVals)
                .toString();
    }
}
