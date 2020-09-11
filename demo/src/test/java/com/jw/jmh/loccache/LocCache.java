package com.jw.jmh.loccache;

/**
 * guoyy com.jw.jmh.loccache
 *
 * @Description: com.jw.jmh.loccache.LocCache
 * @Author: guoyiyong/james
 * @Date: 2020-09-11 11:17
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public interface LocCache<T> {

    void init(int size);

    T get(String key);

    T put(String key, T val);
}
