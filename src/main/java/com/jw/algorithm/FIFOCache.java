package com.jw.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * guoyy com.jw.algorithm
 *
 * @Description: com.jw.algorithm.FIFOCache
 * @Author: guoyiyong/james
 * @Date: 2019-08-12 01:00
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class FIFOCache<K, V> extends LinkedHashMap<K, V> {
    private final int MAX_CACHE_SIZE;

    public FIFOCache(int MAX_CACHE_SIZE) {
        super(MAX_CACHE_SIZE, 0.75f, false);
        this.MAX_CACHE_SIZE = MAX_CACHE_SIZE;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > MAX_CACHE_SIZE;
    }

    public static void main(String[] args) {
        FIFOCache<Integer, Integer> cache = new FIFOCache<>(2);
        cache.put(1, 1);
        System.out.println(JSON.toJSONString(cache));
        cache.put(2, 1);
        System.out.println(JSON.toJSONString(cache));
        cache.put(3, 1);
        System.out.println(JSON.toJSONString(cache));
        cache.put(4, 1);
        System.out.println(JSON.toJSONString(cache));

    }

}
