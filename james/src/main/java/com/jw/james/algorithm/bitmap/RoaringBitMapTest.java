package com.jw.james.algorithm.bitmap;

import com.jw.james.disruptor.dsfjob.wrapper.EventWrapper;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.commons.lang3.StringUtils;
import org.roaringbitmap.FastRankRoaringBitmap;
import org.roaringbitmap.RoaringBitmap;

import java.util.BitSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

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

        BitSet bs = new BitSet();
        System.out.println(bs);

        StringUtils.split("", ",");

        EventWrapper<TreeSet<Long>> event = new EventWrapper<>();
        TreeSet<Long> obj = new TreeSet<>();
        obj.add(1L);
        event.setValue(obj);

        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        RuntimeSchema schema = RuntimeSchema.createFrom(event.getClass());
        byte[] data = ProtostuffIOUtil.toByteArray(event, schema, buffer);
        buffer.clear();

        System.out.println(data);

        RoaringBitmap rr3 = new RoaringBitmap();

        rr3.add(Integer.MIN_VALUE, -Integer.MIN_VALUE, 0, Integer.MAX_VALUE, -Integer.MAX_VALUE);
        String s = rr3.toString();
        System.out.println("s: " + s);
        System.out.println(Integer.MIN_VALUE + "][" + rr3.contains(Integer.MIN_VALUE));
        System.out.println((-Integer.MIN_VALUE) + "][" + rr3.contains(-Integer.MIN_VALUE));
        System.out.println(Integer.MAX_VALUE + "][" + rr3.contains(Integer.MAX_VALUE));
        System.out.println((-Integer.MAX_VALUE) + "][" + rr3.contains(-Integer.MAX_VALUE));

        LinkedHashSet<Long> linkedHashSet = new LinkedHashSet<>();
        System.out.println(linkedHashSet);
    }
}
