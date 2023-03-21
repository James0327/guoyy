package com.jw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jw.enums.CabinOpenCloseEnum;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;

import static com.jw.enums.CabinOpenCloseEnum.CLOSE;
import static com.jw.enums.CabinOpenCloseEnum.OPEN;

/**
 * Description: guoyy
 * com.jw.Test1
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/12/25 12:07
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class Test1 {
    private final int blockSize = 32;
    private final ByteBuffer buffer = ByteBuffer.allocate(blockSize);

    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyyMMdd");

    public static void main(String[] args) {
        String t = "2000-01-01";
        LocalDate parse = LocalDate.parse(t);
        System.out.println(parse);

        double d = Math.pow(2, 32) - 1;
        System.out.println("flag: " + (d > Integer.MAX_VALUE));

        long i = -1;
        while (true) {
            System.out.println(i++);
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        }
    }

    @Test
    public void test11() {
        int ret = 65792 & 33055;
        System.out.println(ret);

        System.out.println(Integer.toBinaryString(65792));
        System.out.println(Integer.toBinaryString(33055));
    }

    @Test
    public void test10() {
        org.joda.time.LocalDateTime localDateTimeNow = org.joda.time.LocalDateTime.parse("2023-02-02 00:00:00",
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        Date now = localDateTimeNow.toDate();

        System.out.println(now.getTime());
    }

    @Test
    public void test9() {
        String fsiDetail = "FSI2ADT*01WPLUS1*02WPLUS1/NX//.01DEC22//SZV///#C*MGTO32TCG04   \rS NX  0001E07DEC PEK1610 2000MFM0S    738          #DJOYEBMUHQVWX#CCDZIGTLRPNK  S NX  0002E11DEC MFM1105 1510PEK0S    738          #DJOYEBMUHQVWX#CCDZIGTLRPNK  FARE TOTAL               3556 CNY                    INCL TAX   \r1,1 WPLUS1           指定的票价不符合运价规则   \r*SPECIFIED FARE BASIS CODE IS NOT APPLICABLE\r*SYSTEM DEFAULT-CHECK OPERATING CARRIER \r*ACCOMPANIED VALIDATION - ALL PAX MUST BE TICKETED AT SAME TIME \r*ID REQUIRED\r*ATTN PRICED ON 01DEC22*1339\rFSKY/1E/4LYVNJV44YCXA22/FCC=D/  \r\u001e";
        List<String> retList = Optional.ofNullable(fsiDetail).filter(StringUtils::isNotEmpty)
                .map(str -> Arrays.stream(StringUtils.split(str, '\r'))
                        .filter(s -> StringUtils.startsWith(s, "S "))
                        .collect(Collectors.joining(" ")))
                .map(val -> Arrays.stream(StringUtils.split(val, " "))
                        .filter(s -> StringUtils.startsWithAny(s, "#D", "#C"))
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        System.out.println(retList);

        Map<Integer, Map<Character, CabinOpenCloseEnum>> fsiCabinOpen = Maps.newHashMap();
        for (int i = 0, size = retList.size(); i < size; i++) {
            String ret = retList.get(i);
            System.out.println("ret: " + ret);
            int idx1 = ret.indexOf("#D"), idx2 = ret.indexOf("#C"), idx0 = ret.length();
            String open = idx1 == -1 ? null : ret.substring(idx1 + 2, idx2 != -1 ? idx2 : idx0);
            String close = idx2 == -1 ? null : ret.substring(idx2 + 2, idx0);

            System.out.println("open: " + open);
            System.out.println("close: " + close);

            if (StringUtils.isNotEmpty(open)) {
                for (int j = 0, len = open.length(); j < len; j++) {
                    fsiCabinOpen.computeIfAbsent(i, k -> Maps.newHashMap()).put(open.charAt(j), OPEN);
                }
            }
            if (StringUtils.isNotEmpty(close)) {
                for (int j = 0, len = close.length(); j < len; j++) {
                    fsiCabinOpen.computeIfAbsent(i, k -> Maps.newHashMap()).put(close.charAt(j), CLOSE);
                }
            }
        }

        System.out.println(fsiCabinOpen);
    }

    @Test
    public void test8() {
        Map<String, Object> map = splitSourceToDay(null, "20221127", "20221229", "12345");
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
    }

    protected <T> Map<String, T> splitSourceToDay(T obj, String startDate, String endDate, String dayOfOperation) {
        Map<String, T> map = Maps.newHashMap();

        final BitSet coll = new BitSet();
        if (StringUtils.isEmpty(dayOfOperation)) {
            coll.set(1, 8, true);
        } else {
            for (char ch : dayOfOperation.toCharArray()) {
                coll.set(ch - 48);
            }
        }

        org.joda.time.LocalDateTime startDateTime = org.joda.time.LocalDateTime.parse(startDate, DATE_TIME_FORMATTER);
        org.joda.time.LocalDateTime endDateTime = org.joda.time.LocalDateTime.parse(endDate, DATE_TIME_FORMATTER);
        Days days = Days.daysBetween(startDateTime, endDateTime);
        int maxSplitDays = 365;
        org.joda.time.LocalDateTime lastDateTime = days.getDays() > maxSplitDays ? startDateTime.plusDays(maxSplitDays) : endDateTime;

        for (int i = coll.nextSetBit(1); i >= 0; i = coll.nextSetBit(i + 1)) {
            org.joda.time.LocalDateTime tmp = startDateTime.withDayOfWeek(i);
            if (tmp.isBefore(startDateTime)) {
                tmp = tmp.plusWeeks(1);
            }
            while (true) {
                if (tmp.isAfter(lastDateTime)) {
                    break;
                }
                map.put(tmp.toString(DATE_TIME_FORMATTER), obj);
                tmp = tmp.plusWeeks(1);
            }
        }

        return map;
    }

    @Test
    public void test7() throws InterruptedException {

        List<String> list = Lists.newArrayList();
        list.add("aaa");
        list.add("bbb");
        String[] mems = new String[0];
        String[] arr2 = list.toArray(mems);

        System.out.println(JSON.toJSONString(mems));
        System.out.println(JSON.toJSONString(arr2));

        Semaphore semaphore = new Semaphore(0);

        semaphore.release(3);

        boolean ret = semaphore.tryAcquire(5, 1, TimeUnit.SECONDS);

        System.out.println(ret + "/" + semaphore.availablePermits() + "/" + semaphore.drainPermits());
    }

    @Test
    public void test6() {
        String str = "yyyyMMddHHmm";
        System.out.println(str.substring(8));

        final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMddHHmm");
        org.joda.time.LocalDateTime nextDepTime = org.joda.time.LocalDateTime.parse("202210011010", dateTimeFormatter);
        org.joda.time.LocalDateTime currArrTime = org.joda.time.LocalDateTime.parse("202210011820", dateTimeFormatter);

        boolean equals = currArrTime.toLocalDate().equals(nextDepTime.toLocalDate());

        System.out.println(equals);
    }

    @Test
    public void test5() throws Exception {
        FileOutputStream fos = new FileOutputStream("file.out");
        FileChannel fc = fos.getChannel();

        FileLock lock = fc.lock();

        String str = FileUtils.readFileToString(new File(getClass().getClassLoader().getResource("1.txt").getPath()), "utf-8");
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        int cnt = 0;
        while (true) {
            buffer.clear();
            int offset = (cnt++) * blockSize;
            int remain = bytes.length - offset;
            if (remain == 0) {
                break;
            }
            int len = Math.min(remain, blockSize);
            buffer.put(bytes, offset, len);

            buffer.flip();
            fc.write(buffer);

            if (remain < blockSize) {
                break;
            }
        }
        lock.release();
    }

    @Test
    public void test4() {
        LocalDateTime now = LocalDateTime.now().minusHours(21);

        System.out.println(now);
        System.out.println(now.getHour());
    }

    @Test
    public void test3() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("a", "aaaaaaaaa");
        map.put("b", "bbbbbbbbb");
        map.put("b.c.d", "bbbbbbbbb");

        StandardEvaluationContext context = new StandardEvaluationContext(map);
        context.setVariable("context", map);
        context.setVariable("AAA", "111");
        context.setVariables(map);

        SpelExpressionParser parser = new SpelExpressionParser();

        String value2 = parser.parseExpression("#root['b.c.d']").getValue(context, String.class);
        System.out.println(value2);

        Expression a1 = parser.parseExpression("#b");
        Object val1 = a1.getValue(context, String.class);
        System.out.println(val1);

        Expression a = parser.parseExpression("#context['a']");
        Object val = a.getValue(context, String.class);
        System.out.println(val);

        Expression a2 = parser.parseExpression("#context['b.c.d']");
        Object val2 = a2.getValue(context, String.class);
        System.out.println("val2: " + val2);

        Expression a3 = parser.parseExpression("#context[b]");
        Object val3 = a3.getValue(context, String.class);
        System.out.println("val3: " + val3);

        Expression expression = parser.parseExpression("#AAA");
        String value = expression.getValue(context, String.class);
        System.out.println(value);

        Expression expression1 = parser.parseExpression("#BBBB");
        String value1 = expression1.getValue(context, String.class);
        System.out.println(value1);
    }

    @Test
    public void test2() {
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j < 20; j++) {
                System.out.print(String.format("%s * %s = %s\t", j, i, i * j));
            }
            System.out.println();
        }
    }

    @Test
    public void test1() {
        boolean travelDateFirstUnrestricted = true;
        boolean travelDateLastUnrestricted = true;
        String service7DateFirst = "121201";
        String service7DateLast = "  0201";

        // [1, 12]
        LocalDate now = LocalDate.now();
        int curYear = now.getYear(), curMon = now.getMonthValue();

        int fYear = curYear, fMonth = 1, fDay = 1;
        if (!travelDateFirstUnrestricted) {
            String y = service7DateFirst.substring(0, 2);
            String m = service7DateFirst.substring(2, 4);
            String d = service7DateFirst.substring(4, 6);
            fMonth = Integer.parseInt(m);
            if (StringUtils.isNotBlank(d)) {
                fDay = Integer.parseInt(d);
            }
            if (StringUtils.isNotBlank(y)) {
                fYear = 2000 + Integer.parseInt(y);
            } else {
                fYear = curMon < fMonth ? curYear - 1 : curYear;
            }
        }
        int lYear = 9999, lMonth = 12, lDay = 31;
        if (!travelDateLastUnrestricted) {
            String y = service7DateLast.substring(0, 2);
            String m = service7DateLast.substring(2, 4);
            String d = service7DateLast.substring(4, 6);
            lMonth = Integer.parseInt(m);
            if (StringUtils.isNotBlank(d)) {
                lDay = Integer.parseInt(d);
            }
            if (StringUtils.isNotBlank(y)) {
                lYear = 2000 + Integer.parseInt(y);
            } else {
                if (lMonth < fMonth || (lMonth == fMonth && lDay < fDay)) {
                    lYear = fYear + 1;
                } else {
                    lYear = fYear;
                }
            }
        }

        String first = Integer.toString(fYear * 10000 + fMonth * 100 + fDay);
        String last = Integer.toString(lYear * 10000 + lMonth * 100 + lDay);

        System.out.println(first + " ~ " + last);

    }

}
