package com.jw;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * guoyy com.jw.demo
 *
 * @Description: Test3
 * @Author: guoyiyong/james
 * @Date: 2019-08-04 18:45
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public final class Test3 {

    public static void main(String[] args) {

        String ret = org.joda.time.LocalDateTime.now(DateTimeZone.UTC).toString();
        System.out.println("ret: " + ret);
        String ret2 = org.joda.time.LocalDateTime.now(DateTimeZone.forID("-05:00")).toString();
        System.out.println("ret2: " + ret2);
        String ret3 = org.joda.time.LocalDateTime.now(DateTimeZone.forID("+08:00")).toString();
        System.out.println("ret3: " + ret3);

        System.out.println("~~~~~~~~~");

        // ZoneId.of("-05:00")  <==>  ZoneId.of("UTC-05:00")  <==>  ZoneId.of("GMT-05:00")
        String gmt = LocalDateTime.now(ZoneId.of("-05:00")).toString();
        String utc = LocalDateTime.now(ZoneId.of("-05:00")).toString();
        String now = LocalDateTime.now(ZoneId.of("+08:00")).toString();

        System.out.println("gmt: " + gmt);
        System.out.println("utc: " + utc);
        System.out.println("now: " + now);

        System.out.println("~~~~~~~~~");

        DateTime local = new DateTime();
        DateTime utc0 = new DateTime(DateTimeZone.UTC);

        System.out.println("local zone = " + local.getZone());
        System.out.println("  utc zone = " + utc0.getZone());

        DateTimeFormatter format1 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMddHH'00'");
        System.out.println(" local: " + format.print(local));
        System.out.println("   utc: " + format.print(utc0));
        System.out.println("millis: " + utc0.getMillis());
    }

    private static class Singleton {
        private static final Test3 test3 = new Test3();
    }

    public static Test3 getInstance() {
        return Singleton.test3;
    }

}
