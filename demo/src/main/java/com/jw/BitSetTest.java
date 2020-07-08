package com.jw;

import java.util.BitSet;
import java.util.Random;
import java.util.TreeSet;

/**
 * guoyy com.jw.demo
 *
 * @Description: BitSetTest
 * @Author: guoyiyong/james
 * @Date: 2019-08-07 00:36
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class BitSetTest {

    public static void main(String[] args) {

        Random r = new Random();

        BitSet bs1 = new BitSet();
        for (int i = 0; i < 10; i++) {
            bs1.set(r.nextInt(10000));
        }

        System.out.println("length:" + bs1.length()
                + ", size:" + bs1.size()
                + ", cardinality:" + bs1.cardinality()
                + "][" + bs1.toString());

        int i = -1;
        while (true) {
            i = bs1.nextSetBit(i + 1);
            if (i == -1) {
                break;
            }
            System.out.print("\t" + i);
        }

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
