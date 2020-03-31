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
