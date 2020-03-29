package com.jw.algorithm.bitset;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * guoyy com.jw.algorithm.bitset
 *
 * @Description: com.jw.algorithm.bitset.T1
 * @Author: guoyiyong/james
 * @Date: 2020-01-15 16:40
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class T1 {

    public static void main(String[] args) {

        List<String> ret1 = Stream.of(StringUtils.split("2,4,20,3,4,6", ","))
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .collect(Collectors.toList());

        List<String> ret2 = Stream.of(StringUtils.split("3,2,4,20,4,6", ","))
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .collect(Collectors.toList());

        List<String> ret3 = Stream.of(StringUtils.split("2,4,20,4,6", ","))
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .collect(Collectors.toList());

        System.out.println(ret1);
        System.out.println(ret2);
        System.out.println(ret3);

        System.out.println(ret1.equals(ret2));
        System.out.println(ret1.equals(ret3));

        String[] split = StringUtils.split(" adfa   * dd*f* aa", "* ");
        String[] split1 = StringUtils.split(" adfa    dd ", " *");

        System.out.println(split);
        System.out.println(split1);

        LocalDateTime date = LocalDateTime.now();
        int mon = date.getMonth().getValue();
        int day = date.getDayOfMonth();

        System.out.println("mon:" + mon);
        System.out.println("day:" + day);

        System.out.println(String.format("[  %02d%02d]", mon, day));
        System.out.println(String.format("[  %02d%02d]", 1, 1));
    }

}
