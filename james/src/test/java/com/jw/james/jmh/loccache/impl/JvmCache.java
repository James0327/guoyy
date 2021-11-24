package com.jw.james.jmh.loccache.impl;

import com.jw.james.jmh.loccache.LocCache;
import com.jw.james.jmh.loccache.Foo;

import java.util.HashMap;
import java.util.Map;

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
public class JvmCache implements LocCache<Foo> {
    private Map<String, Foo> cache = null;

    @Override
    public void init(int size) {
        cache = new HashMap<>(size);
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
