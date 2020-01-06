package com.jw.algorithm.bitset;

import org.roaringbitmap.FastRankRoaringBitmap;
import org.roaringbitmap.RoaringBitmap;

/**
 * guoyy com.jw.algorithm.bitset
 *
 * @Description: com.jw.algorithm.bitset.RoaringBitMapTest
 * @Author: guoyiyong/james
 * @Date: 2019-12-03 13:45
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class RoaringBitMapTest {
    public static void main(String[] args) {
        RoaringBitmap bitmap = new RoaringBitmap();
        RoaringBitmap bitmap2 = new FastRankRoaringBitmap();
        // rangeStart=9223372036854775806 should be in [0, 0xffffffff]
        bitmap2.add(Long.MAX_VALUE - 1, Long.MAX_VALUE);

        boolean contains = bitmap2.contains(Long.MAX_VALUE - 1, Long.MAX_VALUE);

        System.out.println(contains);
    }
}
