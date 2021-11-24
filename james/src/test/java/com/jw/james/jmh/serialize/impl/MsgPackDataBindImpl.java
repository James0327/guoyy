package com.jw.james.jmh.serialize.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jw.james.jmh.serialize.Formatter;
import com.jw.james.jmh.serialize.bo.Person;
import org.msgpack.jackson.dataformat.MessagePackFactory;

import java.io.IOException;

/**
 * guoyy com.jw.jmh.serialize.impl
 *
 * @Description: com.jw.jmh.serialize.impl.MsgPackDataBindImpl
 * @Author: guoyiyong/james
 * @Date: 2020-03-29 23:54
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class MsgPackDataBindImpl implements Formatter<Person> {
    // Instantiate ObjectMapper for MessagePack
    private final ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());

    @Override
    public byte[] searialize(Person obj) throws IOException {
        return objectMapper.writeValueAsBytes(obj);
    }

    @Override
    public Person desearialize(byte[] data) throws IOException {
        return objectMapper.readValue(data, Person.class);
    }
}
