package com.jw.algorithm.bitmap;

import org.roaringbitmap.FastRankRoaringBitmap;
import org.roaringbitmap.RoaringBitmap;

/**
 * guoyy com.jw.algorithm.bitmap
 *
 * @Description: RoaringBitMapTest
 * @Author: guoyiyong/james
 * @Date: 2019-12-03 13:45
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class RoaringBitMapTest {

    public static void main(String[] args) {
        RoaringBitmap rr = RoaringBitmap.bitmapOf(1, 2, 3, 1000);
        RoaringBitmap rr2 = new RoaringBitmap();

        rr2.add(4000L, 4255L);
        int select = rr.select(3);// would return the third value or 1000
        System.out.println("select: " + select);
        int rank = rr.rank(2);// would return the rank of 2, which is index 1
        System.out.println("rank: " + rank);
        boolean contains1 = rr.contains(1000);// will return true
        System.out.println("contains1: " + contains1);
        boolean contains2 = rr.contains(7);// will return false
        System.out.println("contains2: " + contains2);

        RoaringBitmap rror = RoaringBitmap.or(rr, rr2);// new bitmap
        rr.or(rr2); //in-place computation
        boolean equals = rror.equals(rr);// true
        if (!equals) {
            throw new RuntimeException("bug");
        }
        // number of values stored?
        long cardinality = rr.getLongCardinality();
        System.out.println(cardinality);
        // a "forEach" is faster than this loop, but a loop is possible:
        for (int i : rr) {
            System.out.println(i);
        }

        RoaringBitmap bitmap2 = new FastRankRoaringBitmap();
        // rangeStart: [0, (1L << 32)-1]
        // rangeEnd: [0, 1 << 32]
        bitmap2.add(0L, 0xffffffff + 1);

        boolean contains = bitmap2.contains(0, 0xffffffff + 1);

        System.out.println(contains);
    }
}
