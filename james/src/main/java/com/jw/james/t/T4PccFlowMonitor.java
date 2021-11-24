package com.jw.james.t;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Description: guoyy
 * com.jw.t.T4PccFlowMonitor
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/25 23:46
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class T4PccFlowMonitor {
    private static final DateTimeFormatter YYYY_MM = DateTimeFormat.forPattern("yyyy-MM");

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        PccFlowMonitorDTO pccFlowMonitorDTO = new PccFlowMonitorDTO();
        List<PccFlowMonitorDTO> flow = Lists.newArrayList();
        String maxMon = now.toString(YYYY_MM);
        for (int i = 1; i <= 12; i++) {
            String date = String.format("%04d-%02d", year, i);
            if (StringUtils.compare(date, maxMon) > 0) {
                break;
            }
            flow.add(new PccFlowMonitorDTO()
                    .incrementShopping(ThreadLocalRandom.current().nextInt(0, i))
                    .incrementBooking(ThreadLocalRandom.current().nextInt(0, i))
                    .incrementGeneralmsg(ThreadLocalRandom.current().nextInt(0, i))
                    .incrementRefund(ThreadLocalRandom.current().nextInt(0, i)));
        }
        flow.stream().reduce(pccFlowMonitorDTO, (obj1, obj2) ->
                obj1.incrementShopping(obj2.getShopping())
                        .incrementGeneralmsg(obj2.getGeneralmsg())
                        .incrementBooking(obj2.getBooking())
                        .incrementRefund(obj2.getRefund()));

        System.out.println(pccFlowMonitorDTO);
    }
}
