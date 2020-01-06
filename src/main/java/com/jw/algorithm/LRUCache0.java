package com.jw.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * guoyy com.jw.algorithm
 *
 * @Description: com.jw.algorithm.LRUCache0
 * @Author: guoyiyong/james
 * @Date: 2019-08-28 23:56
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class LRUCache0<K, V> {

    private LinkedList<K> orderKey = new LinkedList<>();
    private Map<K, V> cache = new HashMap<>();

    private int capacity = -1;

    public LRUCache0(int size) {
        capacity = size;
    }

    public V put(K k, V v) {
        if (orderKey.size() >= capacity) {
            K k1 = orderKey.removeFirst();
            cache.remove(k1);
        }
        if (orderKey.contains(k)) {
            orderKey.remove(k);
        }
        orderKey.addLast(k);
        cache.put(k, v);
        return v;
    }

    public V get(K k, Callback<V> callback) {
        boolean succ = orderKey.remove(k);
        if (succ) {
            orderKey.addLast(k);
            return cache.get(k);
        } else {
            return this.put(k, callback.load());
        }
    }

    public interface Callback<V> {
        public abstract V load();
    }

}
