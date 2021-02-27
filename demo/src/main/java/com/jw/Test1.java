package com.jw;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.BitSet;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Data
    class T {
        private int age;
        private String name;
    }

    public static void main(String[] args) throws Exception {

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

    private Test1() {
        System.out.println("Test1 ...");
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
