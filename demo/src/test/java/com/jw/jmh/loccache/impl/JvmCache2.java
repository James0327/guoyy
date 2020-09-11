package com.jw.jmh.loccache.impl;

import com.jw.jmh.loccache.Foo;
import com.jw.jmh.loccache.LocCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * guoyy com.jw.jmh.loccache.impl
 *
 * @Description: com.jw.jmh.loccache.impl.JvmCache
 * @Author: guoyiyong/james
 * @Date: 2020-09-11 11:29
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class JvmCache2 implements LocCache<Foo> {
    private Map<String, Foo> cache = null;

    @Override
    public void init(int size) {
        cache = new ConcurrentHashMap<>(size);
    }

    @Override
    public Foo get(String key) {
        return cache.get(key);
    }

    @Override
    public Foo put(String key, Foo val) {
        cache.put(key, val);
        return val;
    }

}
