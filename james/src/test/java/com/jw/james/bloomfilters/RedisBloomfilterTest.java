package com.jw.james.bloomfilters;

import com.alibaba.fastjson.JSON;
import com.jw.james.GuoyyApplication;
import com.jw.james.dto.Person;
import org.apache.commons.text.RandomStringGenerator;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * guoyy com.jw.algorithm.bloomfilters
 *
 * @Description: com.jw.algorithm.bloomfilters.RedisBloomfilterTest
 * @Author: guoyiyong/james
 * @Date: 2020-01-07 11:14
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@SpringBootTest(classes = GuoyyApplication.class)
public class RedisBloomfilterTest {
    @Resource
    private RedisTemplate<String, Person> redisTemplate;

    @Test
    public void test() {
        /**
         * EVAL script numkeys key [key ...] arg [arg ...]
         * > eval "return {KEYS[1],KEYS[2],ARGV[1],ARGV[2]}" 2 key1 key2 first second
         * 1) "key1"
         * 2) "key2"
         * 3) "first"
         * 4) "second"
         *
         * a = b and c
         * <====>
         * if not b then a = b else a = c end
         * 0 and 3 => 3
         * 2 and 1 => 1
         * nil and 7 => nil
         * false and 1 => false
         *
         * a = b or c
         * <====>
         * if b then a=b else a=c end
         * 4 or 5 => 4
         * 0 or 5 => 0
         * nil or 1 => 1
         * false or 2 => 2
         */
        DefaultRedisScript<Boolean> script_init = new DefaultRedisScript<>("return (redis.call('EXISTS',KEYS[1])==1 or redis.call('BF.RESERVE',KEYS[1],KEYS[2],KEYS[3]))", Boolean.class);
        DefaultRedisScript<Boolean> script_add = new DefaultRedisScript<>("return redis.call('BF.ADD',KEYS[1],ARGV[1])==1", Boolean.class);
        DefaultRedisScript<Boolean> script_exists = new DefaultRedisScript<>("return redis.call('BF.EXISTS',KEYS[1],ARGV[1])==1", Boolean.class);

        // 这里调用方法 execute(RedisScript<T> script, List<K> keys, Object... args)
        Boolean initSucc = redisTemplate.execute(script_init, Lists.newArrayList("redisBloomfilter", "0.0003", "100000"));
        System.out.println("initSucc: " + initSucc);
        if (!initSucc) {
            System.exit(1);
        }

        List<String> keys = Lists.newArrayList("redisBloomfilter");

        List<Person> objs = new ArrayList<>(100_000);
        RandomStringGenerator build = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 100_000; i++) {
            objs.add(new Person(i, build.generate(16), random.nextInt(30), (byte)random.nextInt(1), build.generate(11), build.generate(128)));
        }
        for (int i = 0; i < 10_000; i++) {
            boolean succ = redisTemplate.execute(script_add, keys, objs.get(i));
            if (!succ) {
                System.out.println(String.format("obj:%s,%s", objs.get(i), succ));
            }
        }

        for (int i = 0; i < 10_000; i++) {
            Person person = objs.get(random.nextInt(10_000));
            Boolean isExists = redisTemplate.execute(script_exists, keys, person);
            if (!isExists) {
                System.out.println(String.format("person:%s,%s", JSON.toJSONString(person), isExists));
            }
        }
    }

}
