package com.jw.w;

import org.joda.time.LocalDateTime;

import java.util.BitSet;
import java.util.Date;

/**
 * Description: guoyy
 * com.jw.w.Test1
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2022/3/29 17:33
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2022 JW All rights reserved.
 */
public class Test1 {

    public static void main(String[] args) {
        Date flightTime = new Date();
        LocalDateTime localDateTime = LocalDateTime.fromDateFields(flightTime);
        int curYear = localDateTime.getYear(), curMon = localDateTime.getMonthOfYear();
        System.out.println(curYear + "/" + curMon);

        BitSet cache = new BitSet();
        cache.set(0, 300);

        for (int i = cache.nextSetBit(0); i >= 0; i = cache.nextSetBit(i + 1)) {
            System.out.println(i);
            cache.clear(Math.abs(200 - i));
        }
        System.out.println(cache);

    }

}
