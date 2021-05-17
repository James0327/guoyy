package com.jw;

import com.google.common.collect.Maps;
import com.jw.comm.Lz4Util;
import com.scottlogic.util.SortedList;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.assertj.core.util.Lists;
import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Description: guoyy
 * com.jw.Test99
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/4/7 12:36
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class Test99 {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final Pattern COMPILE = Pattern.compile("\\w{2}|(?<!\\[)\\w{3}(?!\\])");

    @Data
    class Foo {
        private int id;
        private String seqNo;
    }

    @Test
    public void test1() throws Exception {
        List<Foo> fooList = Collections.synchronizedList(new SortedList<>((o1, o2) -> StringUtils.compare(o1.getSeqNo(), o2.getSeqNo())));
        System.out.println(fooList);
        List<Foo> fooList2 = Collections.synchronizedList(new SortedList<>(Comparator.comparing(Foo::getSeqNo)));
        System.out.println(fooList2);

        long timestamp = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1);
        System.out.println("timestamp: " + timestamp);
        System.out.println("date: " + new Date(timestamp));

        File file = new File("/data/logs/skynet-flight.inter.inb-iflight.java.dsf.tax.complex.entrance/app");
        file.listFiles((dir, fileName) -> {
            System.out.println(String.format("dir:%s, fileName:%s.", dir, fileName));
            return true;
        });

        System.out.println("~~~~~~~~~");

        file.listFiles((f) -> {
            System.out.println(f);
            System.out.println(ToStringBuilder.reflectionToString(f));
            return true;
        });

        return;
    }

    @Test
    public void test() {
        double d = 25 / 2;
        System.out.println("d: " + d);

        int cnt = 1 << 7;
        System.out.println("cnt: " + cnt);

        // String src = "wuh-h1,h2[ctu,icn]-pv1/pv2-ho,ho[pek,pvg]-mfm|mfm-h3,h4[gmp,tao]-pvg/pvg-h5,h6[gmp,nrt]-wuh";
        String src = "wuh-ho,ho-pvg/pvg-hi,hi-mfm";

        int len = src.length();

        TreeMap<Integer, String> map = Maps.newTreeMap();
        List<Integer> pointIdx = Lists.newArrayList();
        String lastPoint = null;

        int i = 0, j = 0, idx = 0;
        boolean skip = false;
        for (; j < len; j++) {
            if (j == len - 1) {
                String s = src.substring(i, len);
                pointIdx.add(idx);
                map.put(idx++, s);
                break;
            }
            char ch = src.charAt(j);
            if (ch == '[') {
                map.put(idx++, src.substring(i, j));
                skip = true;
            }
            if (ch == ']') {
                skip = false;
                i = j + 2;
                j = j + 1;
            }
            if (skip) {
                continue;
            }
            if (ch == '-' || ch == ',' || ch == '/' || ch == '|') {
                String s = src.substring(i, j);
                if (lastPoint == null || !StringUtils.equals(lastPoint, s)) {
                    if (s.length() == 3) {
                        pointIdx.add(idx);
                    }
                    map.put(idx++, s);
                }
                lastPoint = s;
                i = j + 1;
            }
            if (ch == '|') {
                buildOdAndCarrier(map, pointIdx);
                lastPoint = null;
                map = Maps.newTreeMap();
                pointIdx = Lists.newArrayList();
                i = j = j + 1;
            }
        }
        buildOdAndCarrier(map, pointIdx);
    }

    private void buildOdAndCarrier(TreeMap<Integer, String> map, List<Integer> pointIdx) {
        for (int i = 0, len = pointIdx.size(); i < len; i++) {
            int startIdx = pointIdx.get(i);
            for (int j = i + 1; j < len; j++) {
                int endIdx = pointIdx.get(j);
                Set<String> carriers = map
                        .tailMap(startIdx, false)
                        .headMap(endIdx)
                        .values()
                        .stream()
                        .filter(e -> e.length() == 2)
                        .collect(Collectors.toSet());
                String o = map.get(startIdx);
                String d = map.get(endIdx);
                System.out.println(String.format("od:[%s~%s][%s]", o, d, carriers));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String str0 = "wuh-ho,ho[ctu,icn]-pvg/pvg-ho,ho[pek,pvg]-mfm|mfm-ho,ho[gmp,tao]-pvg/pvg-ho,ho[gmp,nrt]-wuh";
        String[] arr0 = StringUtils.split(str0, "|");
        Matcher matcher = COMPILE.matcher(arr0[0]);
        while (matcher.find()) {
            String str = matcher.group();
            System.out.println("str: " + str);
        }

        org.joda.time.format.DateTimeFormatter yyyyMMdd = DateTimeFormat.forPattern("yyyyMMdd");
        String sDate = org.joda.time.LocalDateTime.fromDateFields(new Date()).toString(yyyyMMdd);
        System.out.println("sDate: " + sDate);

        Date date0 = new Date();
        Instant instant = date0.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        String d = localDateTime.format(FORMATTER);
        System.out.println("d: " + d);

        String cacheStr = "999TF1686CNY_T//@04091825";
        int i = cacheStr.indexOf("T//@");
        if (i != -1) {
            cacheStr = cacheStr.substring(0, i);
        }
        String[] arr1 = StringUtils.splitPreserveAllTokens(cacheStr, "_");
        System.out.println("arr:" + arr1);

        String tracerID = "TC20210408192805DNS788JU3JVVYJF1RBUGM7TOJE04LNYSDE3H8IRYJLJPUVUZ|906322176";
        String caller = "1T";
        String ret0 = Base64.getEncoder().encodeToString((tracerID + caller).getBytes());

        byte[] compress = Lz4Util.instance().compress((tracerID + caller).getBytes());
        String ret2 = Base64.getEncoder().encodeToString(compress);

        System.out.println(tracerID.length() + "|" + ret0.length() + "/" + ret0);
        System.out.println(tracerID.length() + "|" + ret2.length() + "/" + ret2);

        String date = "2021-04-09 10:12:31";
        char[] chars = new char[6];
        date.getChars(2, 4, chars, 0);
        date.getChars(5, 7, chars, 2);
        date.getChars(8, 10, chars, 4);

        System.out.println(new String(chars));

        String dateStr = LocalDate.now(ZoneId.of("-12:00")).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        System.out.println("dateStr: " + dateStr);

        String field = "CR_1_2543,8575,8576,2512_20210406,20210522,20210524,20210524_J,I,V,B_ILE52BN1,VLE02CS1_1E_SZV_#";

        String[] arr = StringUtils.split(field, "_");
        String depDate = StringUtils.substring(arr[3], 0, 8);

        System.out.println("depDate: " + depDate);

        if (StringUtils.compare(depDate, dateStr) < 0) {
            System.out.println("xxxxxx");
        }

    }

}
