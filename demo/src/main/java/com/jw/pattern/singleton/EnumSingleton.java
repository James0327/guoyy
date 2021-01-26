package com.jw.pattern.singleton;

/**
 * guoyy com.jw.singleton
 * <p>
 * 优点：线程安全，提高了性能，解决反射破坏问题，优雅
 * 缺点：不能避免内存浪费
 *
 * @Description: com.jw.singleton.EnumSingleton
 * @Author: guoyiyong/james
 * @Date: 2020-09-09 21:44
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public enum EnumSingleton {
    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
