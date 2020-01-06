package com.jw.demo;

import java.util.BitSet;
import java.util.TreeSet;

/**
 * guoyy com.jw.demo
 *
 * @Description: com.jw.demo.BitSetTest
 * @Author: guoyiyong/james
 * @Date: 2019-08-07 00:36
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class BitSetTest {

    public static void main(String[] args) {
        BitSet bs = new BitSet();

        bs.set(0);
        System.out.println(bs.size() + "][" + bs.length() + "][" + bs.previousSetBit(bs.size()));

        bs.set(1);
        bs.set(2);
        bs.set(3);

        System.out.println(bs.size() + "][" + bs.length() + "][" + bs.previousSetBit(bs.size()));

        bs.set(9);
        bs.set(99999);

        System.out.println(bs.size() + "][" + bs.length() + "][" + bs.previousSetBit(bs.length()));
        bs.set(29);
        bs.set(99);
        bs.set(99999);

        System.out.println(bs.size() + "][" + bs.length() + "][" + bs.previousSetBit(bs.length()) + "][" + bs.previousSetBit(bs.size()));
        bs.set(99999999);

        TreeSet<Long> longs = new TreeSet<>();
        longs.add(1L);
        longs.add(2L);
        longs.add(3L);

        System.out.println(longs.last());

        longs.add(9999L);
        System.out.println(longs.last());

    }
}
