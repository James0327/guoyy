package com.jw.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * guoyy com.jw.algorithm
 *
 * @Description: com.jw.algorithm.LruCache2
 * @Author: guoyiyong/james
 * @Date: 2019-08-12 00:59
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class LruCache2<K, V> extends LinkedHashMap<K, V> {
    private static int MAX_CACHE_SIZE;

    public LruCache2(int MAX_CACHE_SIZE) {
        super(MAX_CACHE_SIZE, 0.75f, true);
        this.MAX_CACHE_SIZE = MAX_CACHE_SIZE;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.size() > MAX_CACHE_SIZE;
    }

    public static void main(String[] args) {

        LruCache2<Integer, Integer> cache = new LruCache2<>(3);
        cache.put(1, 1);
        System.out.println(JSON.toJSONString(cache));
        cache.put(2, 1);
        System.out.println(JSON.toJSONString(cache));
        System.out.println(JSON.toJSONString(cache));
        cache.put(3, 1);
        System.out.println(JSON.toJSONString(cache));
        cache.put(4, 1);
        System.out.println(JSON.toJSONString(cache));
        cache.get(1);
        cache.get(2);
        System.out.println(JSON.toJSONString(cache));

    }

}
