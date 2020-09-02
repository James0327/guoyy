package com.jw.algorithm.time;

import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * guoyy com.jw.algorithm.time
 *
 * @Description: com.jw.algorithm.time.T1
 * @Author: guoyiyong/james
 * @Date: 2020-08-18 11:22
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class T1 {

    public static void main(String[] args) {

        Date date = new Date();

        int i = 1 << 2;
        System.out.println("1 << 2: " + i);

        long time = date.getTime();

        long day = 24 * 60 * 60 * 1000;
        long hour = 60 * 60 * 1000;
        long min = 60 * 1000;
        long second = 1000;

        long t1 = time - (time % day);
        System.out.println(String.format("time:%s, t1:%s.", time, t1));

        long millis = DateTime.now().withTimeAtStartOfDay().getMillis();
        System.out.println("millis: " + millis);

        System.out.println(date);
        System.out.println(new Date(t1));

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateTime = now.truncatedTo(ChronoUnit.DAYS);
        System.out.println("dateTime: " + dateTime);

        LocalDateTime localDateTime = LocalDateTime.from(now);
        System.out.println("localDateTime: " + localDateTime);

    }

}
