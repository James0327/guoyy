package com.jw.james.t;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description: test T7
 * @Package: com.jw
 * @ClassName: T7
 * @Author: james.guo
 * @Date: 2019/4/1 21:53
 * @Version: 1.0
 */
public class T7 {

    public static void main(String[] args) {
        Map<String, List<Integer>> map = Maps.newHashMap();

        Random r = new Random();
        int bound = 10;
        int size = 5;

        for (int i = 0; i < size; i++) {
            String key = "list" + i;
            for (int j = 0, len = r.nextInt(bound); j < len; j++) {
                map.computeIfAbsent(key, k -> Lists.newArrayList()).add(j);
            }
        }
        System.out.println("map: " + map);

        LongAdder sum = new LongAdder();
        map.values().stream().forEach(v -> {
            System.out.println(v.size() + "/" + v);
            sum.add(v.size());
        });
        System.out.println("sum: " + sum.sum());

        map.values().stream().flatMap(v -> v.stream()).forEach(e -> System.out.println(e));

        long count = map.values().stream().flatMap(v -> v.stream()).count();
        System.out.println("sum: " + count);
        long totalSize = map.values().stream().mapToLong(v -> v.size()).sum();
        System.out.println("sum: " + totalSize);

        System.out.println("map: " + map);
        map.values().stream().reduce((k, v) -> {
            System.out.println("k: " + k);
            System.out.println("v: " + v);
            return v;
        });

        Integer sum1 = map.values()
                .stream()
                .reduce(Lists.newArrayList(), (k, v) -> {
                    k.add(v.size());
                    return k;
                })
                .stream()
                .reduce(0, (k, v) -> k + v);
        System.out.println("sum: " + sum1);
    }

}
