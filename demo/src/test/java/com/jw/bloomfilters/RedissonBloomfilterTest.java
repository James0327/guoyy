package com.jw.bloomfilters;

import com.alibaba.fastjson.JSON;
import com.jw.GuoyyApplication;
import com.jw.algorithm.bloomfilters.RedisConf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.TransportMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * guoyy com.jw.algorithm.bloomfilters
 * <p>
 * 布隆过滤器是一种概率数据结构：能确认元素不存在于集合中，但只能提供元素出现在集合中的概率
 *
 * @Description:com.jw.algorithm.bloomfilters.RedissonBloomfilterTest
 * @Author:guoyiyong/james
 * @Date:2019-12-30 14:19
 * @Version:1.0 <p>
 * Copyright(C)2019JW All rights reserved.
 */
@SpringBootTest(classes = GuoyyApplication.class)
//Spring boot 2.1.x之后，@SpringBootTest @ExtendWith({SpringExtension.class})
public class RedissonBloomfilterTest {
    @Resource
    private RedisConf redisConf;

    @Test
    public void test1() {
        long l = TimeUnit.MILLISECONDS.toNanos(1);
        // 1 ms = 1000_000 ns
        System.out.println(String.format("1 ms = %s ns", l));

        Config config = new Config();
        config.setTransportMode(TransportMode.NIO)
                .setNettyThreads(128).setThreads(128);
        config.useClusterServers()
                .setClientName(redisConf.getClientName())
                .addNodeAddress(redisConf.getCluster().getNodes())
                .setRetryAttempts(redisConf.getCluster().getMaxRedirects())
                .setReadMode(ReadMode.MASTER);
        RedissonClient client = Redisson.create(config);

        this.doWork(client);
    }

    @Test
    public void test() {
        RedissonClient client = Redisson.create();
        this.doWork(client);
    }

    private void doWork(RedissonClient client) {
        RBloomFilter<Person> filter = client.getBloomFilter("sample");

        StopWatch stopWatch = new StopWatch("redisson");
        stopWatch.start("filter.tryInit");
        // 初始化布隆过滤器
        // expectedInsertions = 55000000
        // falseProbability = 0.03
        filter.tryInit(100_000, 0.03);
        stopWatch.stop();

        stopWatch.start("build objs");
        List<Person> objs = new ArrayList<>(10_000);
        RandomStringGenerator build = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 10_000; i++) {
            objs.add(new Person(build.generate(16), random.nextInt(30), (byte)random.nextInt(1), build.generate(11)));
        }
        stopWatch.stop();

        stopWatch.start("add filter");
        for (int i = 0, len = 10_000; i < len; i++) {
            filter.add(objs.get(i));
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());

        long count = filter.count();
        System.out.println("count: " + count);

        for (int i = 0; i < 10_000; i++) {
            Person person = new Person(build.generate(16), random.nextInt(30), (byte)random.nextInt(1), build.generate(11));
            System.out.println("person: " + JSON.toJSONString(person));
            try {
                boolean contains = filter.contains(person);
                System.out.println("contains: " + contains);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        client.shutdown();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Accessors(chain = true)
    private static class Person implements Serializable {
        private String name;
        private int age;
        private byte sex;
        private String phone;
    }

}
