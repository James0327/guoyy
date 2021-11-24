package com.jw.james;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * guoyy com.jw.demo
 *
 * @Description: Test1
 * @Author: guoyiyong/james
 * @Date: 2019-08-04 17:01
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
@Slf4j
public final class Test1 {

    public Test1() {
        System.out.println("Test1 ...");
    }

    @Data
    class T {
        private int age;
        private String name;
    }

    enum E {
        A, B
    }

    @Test
    public void t() {
        Pattern p = Pattern.compile("(\\d{1,2}(?:\\-\\d{1,2})?)\\s+(\\d+)(PC|K)\\s+(?:(\\d+)(PC|K))?\\s+(\\d+(?![z-zA-Z]))\\s+\\S+\\s+(\\S+).*");
        Matcher matcher = p.matcher("01    1PC               0100000    0LN    NX   01  41557   ");
        if (matcher.matches()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(i + ": " + matcher.group(i));
            }
        }

        System.exit(1);

        Map<String, String> map = Maps.newConcurrentMap();
        for (int i = 0; i < 10; i++) {
            map.put("key-" + i, "value-" + i);
        }
        String[] arr0 = map.entrySet().stream().flatMap(e -> Stream.of(e.getKey(), e.getValue())).toArray(String[]::new);
        System.out.println("arr: " + arr0);

        E e = null;
        if (e == E.A) {
            System.out.println("XXX");
        }

        String date = String.format("%04d-%02d", 110, 3);
        System.out.println("date: " + date);

        int millisOfDay = LocalTime.now().getMillisOfDay();
        long l = TimeUnit.DAYS.toMillis(1);
        System.out.println(String.format("millisOfDay:%s, l:%s.", millisOfDay, l));

        Date currentDate = new Date();
        Calendar calendar = new Calendar.Builder().setInstant(currentDate).build();
        Calendar instance = Calendar.getInstance();
        calendar.setTime(currentDate);
        System.out.println(instance);

        long changeAmt = -8800;

        String json = "{\"0~30\":-1,\"-30~-500\":120,\"-500~-999999999\":240}";
        Map<String, Integer> expireTimeMap = JSON.parseObject(json, Map.class);
        Iterator<String> iterator = expireTimeMap.keySet().iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            String[] arr = StringUtils.split(StringUtils.replace(k, " ", ""), "~");
            if (Long.parseLong(arr[0]) <= changeAmt && changeAmt < Long.parseLong(arr[1])) {
                System.out.println(Boolean.TRUE);
                return;
            }
        }
        System.out.println(Boolean.FALSE);
    }

    public static void main(String[] args) throws Exception {
        Long zero = new Long(0);
        System.out.println(zero.equals(0L));
        System.out.println(zero == 0);

        System.exit(1);

        Map<Integer, List<T>> map1 = Maps.newHashMap();
        map1.put(1, null);

        String s = Optional.ofNullable(map1.get(1)).map(e -> e.get(0)).map(e -> e.getName()).orElse("");
        System.out.println("s: " + s);

        System.exit(1);

        int cardinality = 99;
        int batch = 100;

        int cnt = cardinality / (cardinality / batch + 1) + 1;

        System.out.println("cnt: " + cnt);

        List<List<Integer>> requestList = Lists.newArrayList();
        List<Integer> atpcoMsgs = Lists.newArrayList();
        requestList.add(atpcoMsgs);
        int maxCnt = 50, idxCnt = 0;
        for (int i = 0; i < 101; i++) {
            atpcoMsgs.add(i);
            idxCnt++;
            if (idxCnt % maxCnt == 0) {
                atpcoMsgs = Lists.newArrayList();
                requestList.add(atpcoMsgs);
            }
        }
        System.out.println("requestList: " + requestList);

        Map<String, Object> cache = Maps.newHashMap();
        Object o = cache.get(null);
        System.out.println(o);

        String json = "{\"0-30\":10,\"30-100\":20,\"500-999999\":30}";
        Map<String, Integer> map = JSON.parseObject(json, Map.class);
        System.out.println(map);

        String json2 = "{\"9\":10,\"30\":10,\"10\":10,\"40\":200,\"500\":30}";
        Map<String, Integer> map2 = JSON.parseObject(json2, Map.class);
        System.out.println(map2);

        Test1 test1 = new Test1();

        Date startTime = new Date();

        Date date = LocalDate.fromDateFields(startTime).toDate();

        System.out.println(date);

        List<T> list = Lists.newArrayList();
        list.add(test1.new T());
        list.add(test1.new T());
        list.add(test1.new T());

        list.stream().collect(Collectors.toMap(T::getAge, T::getName));

        test1.test();
    }

    public void test() throws Exception {

        BitSet bs = new BitSet();
        boolean b = bs.get(9);
        System.out.println(b);

        FileInputStream fis = new FileInputStream("file1");
        ObjectInputStream ois = new ObjectInputStream(fis);

        ois.readObject();

    }

    private enum Singleton {
        INSTANCE;
        private Test1 test1;

        Singleton() {
            this.test1 = new Test1();
            System.out.println("Singleton ..., " + this.test1);
        }

        private static Test1 getInstance() {
            System.out.println("Singleton getInstance," + INSTANCE.test1);
            return INSTANCE.test1;
        }
    }

    public static Test1 getInstance() {
        System.out.println("Test1 getInstance");
        return Singleton.getInstance();
    }

}
