package com.jw.james.jmh.serialize.impl;

import com.jw.james.jmh.serialize.Formatter;
import com.jw.james.jmh.serialize.bo.Person;
import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * guoyy com.jw.jmh.serialize.impl
 *
 * @Description: com.jw.jmh.serialize.impl.MsgPackImpl
 * @Author: guoyiyong/james
 * @Date: 2020-03-30 23:44
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class MsgPackImpl implements Formatter<Person> {
    private final MessagePack messagePack = new MessagePack();

    @Override
    public byte[] searialize(Person obj) throws IOException {
        return messagePack.write(obj);
    }

    @Override
    public Person desearialize(byte[] data) throws IOException {
        return messagePack.read(data, Person.class);
    }
}
