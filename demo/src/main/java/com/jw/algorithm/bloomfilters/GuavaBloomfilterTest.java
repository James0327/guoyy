package com.jw.algorithm.bloomfilters;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.jw.dto.Person;
import org.apache.commons.text.RandomStringGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * guoyy com.jw.algorithm.bloomfilters
 *
 * @Description: com.jw.algorithm.bloomfilters.GuavaBloomfilterTest
 * @Author: guoyiyong/james
 * @Date: 2020-01-07 14:10
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class GuavaBloomfilterTest {

    public static void main(String[] args) {

        // 正确估计预期插入数量是很关键的一个参数。当插入的数量接近或高于预期值的时候，布隆过滤器将会填满，这样的话，它会产生很多无用的误报点
        Funnel<Person> funnel = (person, primitiveSink) -> primitiveSink.putString(JSON.toJSONString(person), Charsets.UTF_8);
        BloomFilter<Person> filter = BloomFilter.create(funnel, 1024 * 1024 * 32, 0.0000001d);

        List<Person> objs = new ArrayList<>(100_000);
        RandomStringGenerator build = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            objs.add(new Person(i, build.generate(16), random.nextInt(30), (byte) random.nextInt(1), build.generate(11), build.generate(11)));
        }
        for (Person obj : objs) {
            boolean succ = filter.put(obj);
            if (!succ) {
                System.out.println(String.format("obj:%s,%s", obj, succ));
            }
        }

        for (int i = 0; i < 1000; i++) {
            Person person = objs.get(random.nextInt(objs.size()));
            Boolean isExists = filter.mightContain(person);
            if (!isExists) {
                System.out.println(String.format("person:%s,%s", JSON.toJSONString(person), isExists));
            }
        }

    }

}
