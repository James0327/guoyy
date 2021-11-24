package com.jw.james.jmh.loccache.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.jw.james.jmh.loccache.LocCache;
import com.jw.james.jmh.loccache.Foo;

/**
 * guoyy com.jw.jmh.loccache.impl
 *
 * @Description: com.jw.jmh.loccache.impl.GuavaCache
 * @Author: guoyiyong/james
 * @Date: 2020-09-11 11:32
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class GuavaCache implements LocCache<Foo> {
    private Cache<String, Foo> cache = null;

    @Override
    public void init(int size) {
        cache = CacheBuilder.newBuilder().initialCapacity(size).concurrencyLevel(1).build();
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
