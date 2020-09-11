package com.jw.jmh.loccache.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.jw.jmh.loccache.Foo;
import com.jw.jmh.loccache.LocCache;

/**
 * guoyy com.jw.jmh.loccache.impl
 *
 * @Description: com.jw.jmh.loccache.impl.CaffeineCache
 * @Author: guoyiyong/james
 * @Date: 2020-09-11 11:52
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class CaffeineCache implements LocCache<Foo> {
    private Cache<String, Foo> cache = null;

    @Override
    public void init(int size) {
        cache = Caffeine.newBuilder().initialCapacity(size).build();
    }

    @Override
    public Foo get(String key) {
        return cache.getIfPresent(key);
    }

    @Override
    public Foo put(String key, Foo val) {
        cache.put(key, val);
        return val;
    }
}
