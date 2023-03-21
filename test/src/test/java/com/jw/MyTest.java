package com.jw;

import com.alibaba.fastjson.JSON;
import com.jw.anno.TableFileType;
import com.jw.domain.Foo;
import com.jw.enums.FileTypeEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Description: guoyy
 * PACKAGE_NAME.com.jw.MyTest
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/10/19 15:19
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@SpringBootTest(classes = TestApplication.class)
public class MyTest {
    @Resource
    private DataLoadHandler dataLoadHandler;

    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();
        int cnt = (int)Math.floor(i * 0.9);
        int cnt1 = (int)Math.floor(8 * 0.9);
        int cnt2 = (int)Math.floor(16 * 0.9);
        System.out.println(i + "][" + cnt);
        System.out.println(cnt1 + "][" + cnt2);

        System.out.println("int max: " + Integer.MAX_VALUE);

        Foo[] foos = new Foo[30];

        List<Foo> fooList = Arrays.asList(foos);

        System.out.println(CollectionUtils.isEmpty(fooList) + "][" + fooList);
        System.out.println(fooList.stream().filter(Objects::nonNull).count() + "][" + fooList);

        foos[3] = new Foo();

        System.out.println(CollectionUtils.isEmpty(fooList) + "][" + fooList);
        System.out.println(fooList.stream().filter(Objects::nonNull).count() + "][" + fooList);

        long t = (86400000 - LocalTime.parse("23:59:00", DateTimeFormat.forPattern("HH:mm:ss")).getMillisOfDay()) / 1000L;
        System.out.println("t: " + t);
        System.out.println("t: " + t / 60D / 60);

        LocalDateTime now = LocalDateTime.now();

        int seconds = Seconds.secondsBetween(now, now.plusDays(1)).getSeconds();
        System.out.println("seconds: " + seconds);
        int millisOfDay = LocalTime.now().getMillisOfDay();
        System.out.println((24 * 60 * 60D - millisOfDay / 1000) / 60 / 60);
        int millisOfDay1 = LocalDateTime.now().getMillisOfDay();
        System.out.println((24 * 60 * 60D - millisOfDay1 / 1000) / 60 / 60);

        long standardSeconds = Duration.standardSeconds(System.currentTimeMillis() / 1000).getStandardSeconds();
        System.out.println("standardSeconds: " + standardSeconds);

        List<Foo> fareParameterList = new ArrayList<>(Collections.nCopies(4, null));
        Foo obj = new Foo();
        obj.setId(1L);
        fareParameterList.set(2, obj);

        fareParameterList.removeIf(Objects::isNull);

        System.out.println(fareParameterList);
    }

    @Test
    public void test3() {
        System.out.println("a: " + (int)'a');
        System.out.println("A: " + (int)'A');
        System.out.println("z: " + (int)'z');
        System.out.println("Z: " + (int)'Z');

        String str = "你好aaa中国";
        byte[] bytes = str.getBytes(Charset.defaultCharset());

        boolean flag = bytes[4] >= 127;
        System.out.println(flag);
        byte[] bytes1 = Arrays.copyOfRange(bytes, 0, 4);
        System.out.println(new String(bytes1));

        byte[] arr = "{\"caller\":\"1T\",\"taxfeeRequestDTOList\":[{\"flightInfoDTOList\":[{\"airlineCode\":\"MU\",\"arrPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-30 16:35\",\"portCode\":\"YNT\",\"turnAround\":false},\"craftType\":\"320\",\"depPoint\":{\"cityCode\":\"SEL\",\"flightTime\":\"2023-03-30 15:55\",\"portCode\":\"ICN\",\"terminalCode\":\"1\",\"turnAround\":false},\"flightNo\":268,\"operatingAirline\":\"MU\"},{\"airlineCode\":\"FM\",\"arrPoint\":{\"cityCode\":\"HRB\",\"flightTime\":\"2023-03-31 17:20\",\"portCode\":\"HRB\",\"turnAround\":false},\"craftType\":\"73E\",\"depPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-31 15:30\",\"portCode\":\"YNT\",\"turnAround\":false},\"flightNo\":9561,\"operatingAirline\":\"FM\"}],\"iatano\":\"92220052\",\"jrInfoByPsg\":{\"ADT\":{\"cabinInfos\":[{\"cabinClass\":\"Y\",\"cabinCode\":\"E\",\"flightSegIdx\":0},{\"cabinClass\":\"Y\",\"cabinCode\":\"B\",\"flightSegIdx\":1}],\"fcInfos\":[{\"discount\":false,\"fareBasis\":\"ESE0WCS8\",\"fareRule\":\"CKR2\",\"fareType\":\"XEX\",\"fcIndexInPu\":0,\"flightNoGroup\":\"MU0268_20230330/FM9561_20230331\",\"flightSegments\":[0,1],\"puIndex\":0,\"saleAmount\":29865,\"tariff\":\"008\",\"ticketDesignator\":\"\",\"travelDirection\":\"outbound\"}],\"jrType\":\"OW\",\"passengerInfo\":{\"ptcType\":\"ADT\"},\"puInfos\":[{\"fcCount\":1,\"puIndex\":0,\"puType\":\"OW\"}],\"totalSaleAmount\":29865}},\"pcc\":\"MOWR228TQ\",\"saleCurrency\":\"RUB\",\"saleLocation\":\"MOW\",\"salesGds\":\"1A\",\"ticketCarrier\":\"MU\",\"ticketDate\":\"2023-03-10 13:43\",\"ticketLocation\":\"MOW\"},{\"flightInfoDTOList\":[{\"airlineCode\":\"MU\",\"arrPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-30 16:35\",\"portCode\":\"YNT\",\"turnAround\":false},\"craftType\":\"320\",\"depPoint\":{\"cityCode\":\"SEL\",\"flightTime\":\"2023-03-30 15:55\",\"portCode\":\"ICN\",\"terminalCode\":\"1\",\"turnAround\":false},\"flightNo\":268,\"operatingAirline\":\"MU\"},{\"airlineCode\":\"FM\",\"arrPoint\":{\"cityCode\":\"HRB\",\"flightTime\":\"2023-03-31 17:20\",\"portCode\":\"HRB\",\"turnAround\":false},\"craftType\":\"73E\",\"depPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-31 15:30\",\"portCode\":\"YNT\",\"turnAround\":false},\"flightNo\":9561,\"operatingAirline\":\"FM\"}],\"iatano\":\"67505524\",\"jrInfoByPsg\":{\"ADT\":{\"cabinInfos\":[{\"cabinClass\":\"Y\",\"cabinCode\":\"E\",\"flightSegIdx\":0},{\"cabinClass\":\"Y\",\"cabinCode\":\"B\",\"flightSegIdx\":1}],\"fcInfos\":[{\"discount\":false,\"fareBasis\":\"ESE0WCS8\",\"fareRule\":\"CKR2\",\"fareType\":\"XEX\",\"fcIndexInPu\":0,\"flightNoGroup\":\"MU0268_20230330/FM9561_20230331\",\"flightSegments\":[0,1],\"puIndex\":0,\"saleAmount\":533,\"tariff\":\"008\",\"ticketDesignator\":\"\",\"travelDirection\":\"outbound\"}],\"jrType\":\"OW\",\"passengerInfo\":{\"ptcType\":\"ADT\"},\"puInfos\":[{\"fcCount\":1,\"puIndex\":0,\"puType\":\"OW\"}],\"totalSaleAmount\":533}},\"pcc\":\"YTOC421G5\",\"saleCurrency\":\"CAD\",\"saleLocation\":\"YTO\",\"salesGds\":\"1A\",\"ticketCarrier\":\"MU\",\"ticketDate\":\"2023-03-10 13:43\",\"ticketLocation\":\"YTO\"},{\"flightInfoDTOList\":[{\"airlineCode\":\"MU\",\"arrPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-30 16:35\",\"portCode\":\"YNT\",\"turnAround\":false},\"craftType\":\"320\",\"depPoint\":{\"cityCode\":\"SEL\",\"flightTime\":\"2023-03-30 15:55\",\"portCode\":\"ICN\",\"terminalCode\":\"1\",\"turnAround\":false},\"flightNo\":268,\"operatingAirline\":\"MU\"},{\"airlineCode\":\"FM\",\"arrPoint\":{\"cityCode\":\"HRB\",\"flightTime\":\"2023-03-31 17:20\",\"portCode\":\"HRB\",\"turnAround\":false},\"craftType\":\"73E\",\"depPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-31 15:30\",\"portCode\":\"YNT\",\"turnAround\":false},\"flightNo\":9561,\"operatingAirline\":\"FM\"}],\"iatano\":\"78262435\",\"jrInfoByPsg\":{\"ADT\":{\"cabinInfos\":[{\"cabinClass\":\"Y\",\"cabinCode\":\"E\",\"flightSegIdx\":0},{\"cabinClass\":\"Y\",\"cabinCode\":\"B\",\"flightSegIdx\":1}],\"fcInfos\":[{\"discount\":false,\"fareBasis\":\"ESE0WCS8\",\"fareRule\":\"CKR2\",\"fareType\":\"XEX\",\"fcIndexInPu\":0,\"flightNoGroup\":\"MU0268_20230330/FM9561_20230331\",\"flightSegments\":[0,1],\"puIndex\":0,\"saleAmount\":368,\"tariff\":\"008\",\"ticketDesignator\":\"\",\"travelDirection\":\"outbound\"}],\"jrType\":\"OW\",\"passengerInfo\":{\"ptcType\":\"ADT\"},\"puInfos\":[{\"fcCount\":1,\"puIndex\":0,\"puType\":\"OW\"}],\"totalSaleAmount\":368}},\"pcc\":\"ALCZF2112\",\"saleCurrency\":\"EUR\",\"saleLocation\":\"ALC\",\"salesGds\":\"1A\",\"ticketCarrier\":\"MU\",\"ticketDate\":\"2023-03-10 13:43\",\"ticketLocation\":\"ALC\"},{\"flightInfoDTOList\":[{\"airlineCode\":\"MU\",\"arrPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-30 16:35\",\"portCode\":\"YNT\",\"turnAround\":false},\"craftType\":\"320\",\"depPoint\":{\"cityCode\":\"SEL\",\"flightTime\":\"2023-03-30 15:55\",\"portCode\":\"ICN\",\"terminalCode\":\"1\",\"turnAround\":false},\"flightNo\":268,\"operatingAirline\":\"MU\"},{\"airlineCode\":\"FM\",\"arrPoint\":{\"cityCode\":\"HRB\",\"flightTime\":\"2023-03-31 17:20\",\"portCode\":\"HRB\",\"turnAround\":false},\"craftType\":\"73E\",\"depPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-31 15:30\",\"portCode\":\"YNT\",\"turnAround\":false},\"flightNo\":9561,\"operatingAirline\":\"FM\"}],\"iatano\":\"92220052\",\"jrInfoByPsg\":{\"STU\":{\"cabinInfos\":[{\"cabinClass\":\"Y\",\"cabinCode\":\"E\",\"flightSegIdx\":0},{\"cabinClass\":\"Y\",\"cabinCode\":\"B\",\"flightSegIdx\":1}],\"fcInfos\":[{\"discount\":false,\"fareBasis\":\"ESE0WCS8\",\"fareRule\":\"CKR2\",\"fareType\":\"XEX\",\"fcIndexInPu\":0,\"flightNoGroup\":\"MU0268_20230330/FM9561_20230331\",\"flightSegments\":[0,1],\"puIndex\":0,\"saleAmount\":29865,\"tariff\":\"008\",\"ticketDesignator\":\"\",\"travelDirection\":\"outbound\"}],\"jrType\":\"OW\",\"passengerInfo\":{\"ptcType\":\"STU\"},\"puInfos\":[{\"fcCount\":1,\"puIndex\":0,\"puType\":\"OW\"}],\"totalSaleAmount\":29865}},\"pcc\":\"MOWR228TQ\",\"saleCurrency\":\"RUB\",\"saleLocation\":\"MOW\",\"salesGds\":\"1A\",\"ticketCarrier\":\"MU\",\"ticketDate\":\"2023-03-10 13:43\",\"ticketLocation\":\"MOW\"},{\"flightInfoDTOList\":[{\"airlineCode\":\"MU\",\"arrPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-30 16:35\",\"portCode\":\"YNT\",\"turnAround\":false},\"craftType\":\"320\",\"depPoint\":{\"cityCode\":\"SEL\",\"flightTime\":\"2023-03-30 15:55\",\"portCode\":\"ICN\",\"terminalCode\":\"1\",\"turnAround\":false},\"flightNo\":268,\"operatingAirline\":\"MU\"},{\"airlineCode\":\"FM\",\"arrPoint\":{\"cityCode\":\"HRB\",\"flightTime\":\"2023-03-31 17:20\",\"portCode\":\"HRB\",\"turnAround\":false},\"craftType\":\"73E\",\"depPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-31 15:30\",\"portCode\":\"YNT\",\"turnAround\":false},\"flightNo\":9561,\"operatingAirline\":\"FM\"}],\"iatano\":\"67505524\",\"jrInfoByPsg\":{\"STU\":{\"cabinInfos\":[{\"cabinClass\":\"Y\",\"cabinCode\":\"E\",\"flightSegIdx\":0},{\"cabinClass\":\"Y\",\"cabinCode\":\"B\",\"flightSegIdx\":1}],\"fcInfos\":[{\"discount\":false,\"fareBasis\":\"ESE0WCS8\",\"fareRule\":\"CKR2\",\"fareType\":\"XEX\",\"fcIndexInPu\":0,\"flightNoGroup\":\"MU0268_20230330/FM9561_20230331\",\"flightSegments\":[0,1],\"puIndex\":0,\"saleAmount\":533,\"tariff\":\"008\",\"ticketDesignator\":\"\",\"travelDirection\":\"outbound\"}],\"jrType\":\"OW\",\"passengerInfo\":{\"ptcType\":\"STU\"},\"puInfos\":[{\"fcCount\":1,\"puIndex\":0,\"puType\":\"OW\"}],\"totalSaleAmount\":533}},\"pcc\":\"YTOC421G5\",\"saleCurrency\":\"CAD\",\"saleLocation\":\"YTO\",\"salesGds\":\"1A\",\"ticketCarrier\":\"MU\",\"ticketDate\":\"2023-03-10 13:43\",\"ticketLocation\":\"YTO\"},{\"flightInfoDTOList\":[{\"airlineCode\":\"MU\",\"arrPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-30 16:35\",\"portCode\":\"YNT\",\"turnAround\":false},\"craftType\":\"320\",\"depPoint\":{\"cityCode\":\"SEL\",\"flightTime\":\"2023-03-30 15:55\",\"portCode\":\"ICN\",\"terminalCode\":\"1\",\"turnAround\":false},\"flightNo\":268,\"operatingAirline\":\"MU\"},{\"airlineCode\":\"FM\",\"arrPoint\":{\"cityCode\":\"HRB\",\"flightTime\":\"2023-03-31 17:20\",\"portCode\":\"HRB\",\"turnAround\":false},\"craftType\":\"73E\",\"depPoint\":{\"cityCode\":\"YNT\",\"flightTime\":\"2023-03-31 15:30\",\"portCode\":\"YNT\",\"turnAround\":false},\"flightNo\":9561,\"operatingAirline\":\"FM\"}],\"iatano\":\"78262435\",\"jrInfoByPsg\":{\"STU\":{\"cabinInfos\":[{\"cabinClass\":\"Y\",\"cabinCode\":\"E\",\"flightSegIdx\":0},{\"cabinClass\":\"Y\",\"cabinCode\":\"B\",\"flightSegIdx\":1}],\"fcInfos\":[{\"discount\":false,\"fareBasis\":\"ESE0WCS8\",\"fareRule\":\"CKR2\",\"fareType\":\"XEX\",\"fcIndexInPu\":0,\"flightNoGroup\":\"MU0268_20230330/FM9561_20230331\",\"flightSegments\":[0,1],\"puIndex\":0,\"saleAmount\":368,\"tariff\":\"008\",\"ticketDesignator\":\"\",\"travelDirection\":\"outbound\"}],\"jrType\":\"OW\",\"passengerInfo\":{\"ptcType\":\"STU\"},\"puInfos\":[{\"fcCount\":1,\"puIndex\":0,\"puType\":\"OW\"}],\"totalSaleAmount\":368}},\"pcc\":\"ALCZF2112\",\"saleCurrency\":\"EUR\",\"saleLocation\":\"ALC\",\"salesGds\":\"1A\",\"ticketCarrier\":\"MU\",\"ticketDate\":\"2023-03-10 13:43\",\"ticketLocation\":\"ALC\"}],\"traceId\":\"TC20230310134352MAM333LZRXI9OCZR52EBA0OLNVKQQY2VZ76F2EBPF8ATGSYW\"}".getBytes(StandardCharsets.UTF_8);

        int maxLen = 1000, arrLen = arr.length;

        for (int i = 0, len = (arrLen / maxLen + (arrLen % maxLen == 0 ? 0 : 1)); i < len; i++) {
            final byte[] copy;
            if (maxLen * (i + 1) > arrLen) {
                copy = new byte[arrLen - (maxLen * i)];
            } else {
                copy = new byte[maxLen];
            }

            System.arraycopy(arr, maxLen * i, copy, 0, copy.length);

            System.out.println(new String(copy));
        }

    }

    @Test
    public void test2() {
        List<Foo> fooList = Lists.newArrayList();
        Foo f = new Foo();
        fooList.add(f);
        f.setId(1L);

        String ret = Optional.ofNullable(fooList)
                .filter(CollectionUtils::isNotEmpty)
                .map(e -> e.stream().map(Foo::getId).collect(Collectors.toList()))
                .map(JSON::toJSONString).orElse(null);

        System.out.println(ret);
    }

    @Test
    public void test1() throws InterruptedException {
        String left = StringUtils.right(StringUtils.leftPad("0009", 3, '0'), 3);
        System.out.println(left);

        DecimalFormat flightNoFormat = new DecimalFormat("000");
        String format = flightNoFormat.format(99);
        System.out.println(format);
        System.exit(0);

        ThreadPoolExecutor pool = new ThreadPoolExecutor(50, 50, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(300), new BasicThreadFactory.Builder().build(), new ThreadPoolExecutor.AbortPolicy());

        DateTime time1 = DateTime.now();

        for (int j = 0; j < 999; j++) {
            int size = 30;
            final Semaphore semaphore = new Semaphore(0);
            for (int i = 0; i < size; i++) {
                CompletableFuture.runAsync(() -> {
                    // LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                    semaphore.release();
                }, pool);
            }

            // semaphore.acquire(size);
            boolean ret = semaphore.tryAcquire(size, 900, TimeUnit.MILLISECONDS);
            if (!ret) {
                System.out.println("税费计算部分超时: " + semaphore.availablePermits() + "/" + size);
            }
        }

        DateTime time2 = DateTime.now();
        // estimate time 203779ms.
        System.out.printf("estimate time %sms.%n", time2.getMillis() - time1.getMillis());

    }

    @Test
    public void test() {
        System.out.println("running is here ... ");
        Class<? extends DataLoadHandler> clazz = dataLoadHandler.getClass();

        TableFileType tableFileType = clazz.getAnnotation(TableFileType.class);
        if (tableFileType == null) {
            return;
        }
        FileTypeEnum[] fileTypeEnums = tableFileType.value();

        Map<String, List<DataLoadHandler>> cache = new HashMap<>();

        for (FileTypeEnum fileTypeEnum : fileTypeEnums) {
            cache.computeIfAbsent(fileTypeEnum.getKey(), k -> new ArrayList<>()).add(dataLoadHandler);
        }

        boolean load = dataLoadHandler.load();
        System.out.println("load: " + load);

        System.out.println(cache);
    }

}
