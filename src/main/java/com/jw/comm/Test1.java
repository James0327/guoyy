package com.jw.comm;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * guoyy com.jw.comm
 *
 * @Description: com.jw.comm.Test1
 * @Author: guoyiyong/james
 * @Date: 2019-11-16 20:43
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test1 {

    static class BitSetExt {
        private Long maxId = 0x3FFFFFFFFFFFFFFFL;

        private BitSet lh = new BitSet();
        private BitSet ll = new BitSet();
        private BitSet i = new BitSet();

        public void set(long num) {
            if (num <= Integer.MAX_VALUE) {
                boolean b = i.get((int)num);
                System.out.println(b);
            }
            if (num > maxId) {
                return;
            }
            int n1 = (int)(num >> 31);
            int n2 = (int)(num & 0x7FFFFFFF);
            System.out.println(n1 + "][" + Integer.toBinaryString(n1));
            System.out.println(n2 + "][" + Integer.toBinaryString(n2));

            System.out.println(lh.get(n1));
            System.out.println(ll.get(n2));

            lh.set(n1);
            ll.set(n2);

            System.out.println("---------");
            System.out.println(lh.get(n1));
            System.out.println(ll.get(n2));
        }
    }

    public static void main(String[] args) {
        BitSetExt bitSetExt = new BitSetExt();
        bitSetExt.set(0x3FFFFFFFFFFFFFFFL);
        bitSetExt.set(0x3FFFFFFFFFFFFFFFL);

        bitSetExt.set(Integer.MAX_VALUE);
        bitSetExt.set(Integer.MAX_VALUE);

        System.exit(1);

        BitSet b = new BitSet();
        b.set(Integer.MAX_VALUE);
        System.out.println(b.length());
        System.out.println(b.nextSetBit(0));
        System.out.println(b.previousSetBit(0));

        long maxLong = Long.MAX_VALUE;
        int maxInt = Integer.MAX_VALUE;

        System.out.println(maxLong + "][" + Long.SIZE);
        System.out.println(maxInt + "][" + Integer.SIZE);
        System.out.println(maxLong / maxInt);

        boolean flag = (maxLong / maxInt) > maxInt;

        System.out.println("flag: " + flag);

        String time = Long.toString(201911271615L);

        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        c.set(Calendar.YEAR, Integer.parseInt(time.substring(0, 4)));
        c.set(Calendar.MONTH, Integer.parseInt(time.substring(4, 6)) - 1);
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(time.substring(6, 8)));
        c.set(Calendar.HOUR, Integer.parseInt(time.substring(8, 10)));
        c.set(Calendar.MINUTE, Integer.parseInt(time.substring(10, 12)));

        System.out.println("c: " + c);

        BigDecimal num1 = new BigDecimal(1);
        BigDecimal num2 = new BigDecimal(2);

        num1.add(num2);

        System.out.println(num1);
        System.out.println(num2);

        String s = JSON.toJSONString(time);
        System.out.println("s:" + s);

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        System.out.println(JSON.toJSONString(list));

        list.set(0, null);
        list.set(1, null);
        list.set(2, null);

        System.out.println(JSON.toJSONString(list));

    }

}
