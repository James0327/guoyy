package com.jw.james.cache;

import com.alibaba.fastjson.JSON;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.jw.james.dto.Person;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description: test CaffeineCache
 * @Package: com.jw.cache
 * @ClassName: CaffeineCache
 * @Author: james.guo
 * @Date: 2019/3/25 11:58
 * @Version: 1.0
 */
public class CaffeineCache {

    public static void main(String[] args) {
        Cache<String, Person> cache = Caffeine.newBuilder()
                .maximumSize(100_0000)
                // .expireAfterWrite(5, TimeUnit.MINUTES)
                .refreshAfterWrite(5, TimeUnit.SECONDS)
                .build(k -> load(k));

        String key = "James";

        Person p = new Person();
        p.setName("name");
        p.setAge(18);
        p.setAddr("addr");

        cache.put(key, p);

        Person person0 = cache.getIfPresent(key);

        System.out.println(ToStringBuilder.reflectionToString(person0));

        cache.invalidate(key);
        Person person1 = cache.getIfPresent(key);
        System.out.println("person1:" + JSON.toJSONString(person1));
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(6L));
        Person person2 = cache.getIfPresent(key);
        cache.get(key, k -> load(k));
        System.out.println("person2:" + JSON.toJSONString(person2));

        try {
            Kryo kryo = new Kryo();
            kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(
                    new StdInstantiatorStrategy()));
            Output output = new Output(new FileOutputStream("file.bin"));

            kryo.writeObject(output, p);
            output.close();

            Input input = new Input(new FileInputStream("file.bin"));
            Person person = kryo.readObject(input, Person.class);

            System.out.println(String.format("person:%s", ToStringBuilder.reflectionToString(person, ToStringStyle.SHORT_PREFIX_STYLE)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Person load(String k) {
        Person p = new Person();
        p.setName("name" + k);
        p.setAge(18);
        p.setAddr("addr");
        return p;
    }
}
