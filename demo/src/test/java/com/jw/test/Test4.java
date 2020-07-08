package com.jw.test;

import java.util.BitSet;

/**
 * guoyy com.jw.test
 *
 * @Description: com.jw.test.Test4
 * @Author: guoyiyong/james
 * @Date: 2020-01-15 11:09
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Test4 {

    public static void main(String[] args) {
        // |a| = (a + (a >> 31)) ^ (a >> 31)
        int i1 = -999;
        int i2 = i1 >> 31;
        int i3 = (i1 + i2) ^ i2;
        System.out.println(Integer.toBinaryString(i1));
        System.out.println(Integer.toBinaryString(i2));
        System.out.println(Integer.toBinaryString(i3));

        System.out.println("~~~~~~~~~");

        System.out.println(7 & (8 - 1));

        BitSet bitSet = new BitSet();
        bitSet.set(1);
        bitSet.set(100);
        bitSet.set(10000);

        boolean b = bitSet.get(1);
        System.out.println(b);

        int i = bitSet.nextSetBit(1);
        System.out.println(i);
    }
}
