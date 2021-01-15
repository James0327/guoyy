package com.jw.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ly.flight.atpco.core.model.routingmap.RouteComboVO;
import com.ning.compress.lzf.LZFDecoder;
import com.ning.compress.lzf.LZFEncoder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * guoyy PACKAGE_NAME
 *
 * @Description: PACKAGE_NAME.com.jw.test.Test1
 * @Author: guoyiyong/james
 * @Date: 2019-06-09 11:18
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test1 {

    public static void main(String[] args) throws Exception {
        String str = "qwertqwertyuioplkjhgfdsaqwertyuioplkjhgfdsayuioplkjhqwertyuioplkjhgfdsaqwertyuioplkjhgfdsagfdsaqwqwertyuioplkjhgfdsaqwertyuqwertyuioplkjhgfdsaqwertyuioplkjhgfdsaioplkjhgfdsaertyuioplkjhgfdsa";
        System.out.println(str);

        byte[] encode = LZFEncoder.encode(str.getBytes());
        String s1 = Base64.getEncoder().encodeToString(encode);
        System.out.println(s1);

        byte[] decode = LZFDecoder.decode(Base64.getDecoder().decode(s1.getBytes()));
        System.out.println(new String(decode));
    }

    private static final Pattern PATTERN = Pattern.compile("[0]+");

    @Test
    public void test() throws Exception {

        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("test-%d").daemon(true).build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS,
                new SynchronousQueue<>(), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        CountDownLatch latch = new CountDownLatch(10);

        // IntStream.range(0, 10) ==> [0,10)
        List<Callable<com.jw.Test1>> tasks = IntStream.range(0, 10).boxed().map(i -> new Callable<com.jw.Test1>() {
            public com.jw.Test1 call() {
                com.jw.Test1 test1 = com.jw.Test1.getInstance();
                latch.countDown();
                return test1;
            }
        }).collect(Collectors.toList());

        List<Future<com.jw.Test1>> futures = executor.invokeAll(tasks);
        System.out.println("time1:" + System.nanoTime());
        // executor不能感知task执行状态,必须等待10s
        // executor.awaitTermination(10, TimeUnit.SECONDS);
        latch.await(10, TimeUnit.SECONDS);
        System.out.println("time2:" + System.nanoTime());
        try {
            executor.shutdown();
        } catch (Exception e) {
            executor.shutdownNow();
        }
        System.out.println("time3:" + System.nanoTime());

        futures.stream().forEach(e -> {
            try {
                System.out.println(e.get());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }

    @Test
    public void test1() {

        com.jw.Test1 test1 = com.jw.Test1.getInstance();
        System.out.println("test1: " + test1);
        com.jw.Test1 test2 = com.jw.Test1.getInstance();
        System.out.println("test2: " + test2);
        com.jw.Test1 test3 = com.jw.Test1.getInstance();
        System.out.println("test3: " + test3);

        Object obj = "0";
        Matcher matcher = PATTERN.matcher(obj.toString());
        System.out.println("flag: " + matcher.matches());
    }

    @Test
    public void test2() {
        String routing0 = "PEK-CA,CA-LHR/LHR-AA,BA-JFK|JFK-AA,BA-LHR/LHR-CA,CA-PEK";

        String routing1 = "PEK-AA,AB-LHR/LHR-BA,BC-JFK/JFK-CA,CC-LHT/LHT-DA,DD-PEL";

        System.out.println(String.format("routing0:%s,routing1:%s.", routing0, routing1));

        Map<String, Integer> lMap = new LinkedHashMap<>();
        TreeMap<Integer, String> cMap = new TreeMap<>();
        Set<String> lSet = new LinkedHashSet<>();

        Pattern compile = Pattern.compile("[0-9a-zA-Z]{2,3}");

        Matcher matcher = compile.matcher(routing1);

        while (matcher.find()) {
            String str = matcher.group();
            int len = str.length();
            if (len == 3) {
                lMap.put(str, matcher.end());
                lSet.add(str);
            } else if (len == 2) {
                cMap.put(matcher.end(), str);
            }
        }
        List<String> lList = new ArrayList<>(lSet);

        System.out.println(JSON.toJSONString(lMap));
        System.out.println(JSON.toJSONString(cMap));
        System.out.println(JSON.toJSONString(lList));

        for (int i = 0, len0 = lList.size() - 1; i < len0; i++) {
            String start = lList.get(i);
            for (int j = i + 1, len1 = len0 + 1; j < len1; j++) {
                String end = lList.get(j);
                SortedMap<Integer, String> headMap = cMap.headMap(lMap.get(end));
                SortedMap<Integer, String> tailMap = headMap.tailMap(lMap.get(start));
                System.out.println(String.format("%s-%s->%s", start, tailMap.values().stream().collect(Collectors.toSet()), end));
            }
        }

    }

    @Test
    public void test3() throws IOException {
        String json = FileUtils.readFileToString(new File(this.getClass().getClassLoader()
                .getResource("json/RouteComboVO-OW-LAX_PAR_20201029.json").getFile()));

        Type type = new TypeReference<List<RouteComboVO>>() {}.getType();
        System.out.println(type);

        List<RouteComboVO> routeComboVOS = JSON.parseArray(json, RouteComboVO.class);

        System.out.println(routeComboVOS);

    }

}
