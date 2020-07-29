package com.jw;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

/**
 * guoyy com.jw.demo
 *
 * @Description: com.jw.demo.Test1
 * @Author: guoyiyong/james
 * @Date: 2020-06-28 23:16
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class MyTest {

    public static void main(String[] args) {
        java.time.format.DateTimeFormatter dateTimeFormat0 = java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHH00");
        String date0 = java.time.LocalDateTime.now().minusHours(12).format(dateTimeFormat0);
        System.out.println(date0);

        org.joda.time.format.DateTimeFormatter dateTimeFormat1 = org.joda.time.format.DateTimeFormat.forPattern("yyyyMMddHH00");
        String date1 = org.joda.time.DateTime.now().minusHours(12).toString(dateTimeFormat1);
        System.out.println(date1);

        BitSet b1 = new BitSet();

        b1.set(5, 8);

        System.out.println(b1.size());
        System.out.println(b1.cardinality());
        System.out.println(b1.length());
        byte[] bytes = b1.toByteArray();

        System.out.println(Integer.toBinaryString(bytes[0]));

        int i1 = b1.nextSetBit(0);

        int i2 = b1.previousClearBit(b1.length());

        System.out.println(b1);
        System.out.println(String.format("i1:%d,i2:%d.", i1, i2));

        StringUtils.trimToEmpty(null);
    }

    @Test
    public void test1() {
        int i0 = 0;
        i0 |= 1 << 0;
        i0 |= 1 << 1;
        i0 |= 1 << 2;
        i0 |= 1 << 3;
        i0 |= 1 << 4;
        i0 |= 1 << 5;

        int i1 = 0;
        i1 |= 1 << 0;
        i1 |= 1 << 2;
        i1 |= 1 << 3;

        int i2 = 0;
        i2 |= 1 << 4;
        i2 |= 1 << 5;

        System.out.println(Integer.toBinaryString(i0));
        System.out.println(Integer.toBinaryString(i1));
        System.out.println(Integer.toBinaryString(i2));

        System.out.println(minFltIdx(i0));
        System.out.println(minFltIdx(i1));
        System.out.println(minFltIdx(i2));

        System.out.println("~~~~~~~~~");

        System.out.println(maxFltIdx(i0));
        System.out.println(maxFltIdx(i1));
        System.out.println(maxFltIdx(i2));

        System.out.println(Integer.toBinaryString(i1));
        System.out.println(Integer.toBinaryString(-i1));
        System.out.println(Integer.toBinaryString(~i1));

        System.out.println(Integer.toBinaryString(i2));
        System.out.println(Integer.toBinaryString(-i2));
        System.out.println(Integer.toBinaryString(~i2));

    }

    private int minFltIdx(int set) {
        if (set <= 0) {
            return -1;
        }
        int t = set;
        int idx = 1;
        while (true) {
            if ((t & 1) == 1) {
                break;
            }
            t = t >> 1;
            idx++;
        }
        return idx;
    }

    private int maxFltIdx(int set) {
        if (set <= 0) {
            return -1;
        }
        int t = set;
        int idx = 1;
        while (true) {
            System.out.println("t: " + t);
            if (t >> idx == 0) {
                break;
            }
            idx++;
        }
        return idx;
    }

}
