package com.jw.james.jmh.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.jw.james.jmh.serialize.Formatter;
import com.jw.james.jmh.serialize.bo.Person;

/**
 * guoyy com.jw.jmh.serialize.impl
 *
 * @Description: com.jw.jmh.serialize.impl.FastJsonImpl
 * @Author: guoyiyong/james
 * @Date: 2020-03-29 23:33
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class FastJsonImpl implements Formatter<Person> {
    @Override
    public byte[] searialize(Person obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public Person desearialize(byte[] data) {
        return JSON.parseObject(data, Person.class);
    }
}
