package com.jw.james.jmh.serialize.impl;

import com.jw.james.jmh.serialize.Formatter;
import com.jw.james.jmh.serialize.bo.Person;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

/**
 * guoyy com.jw.jmh.serialize.impl
 *
 * @Description: com.jw.jmh.serialize.impl.ProtostuffImpl
 * @Author: guoyiyong/james
 * @Date: 2020-03-30 00:36
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class ProtostuffImpl implements Formatter<Person> {
    private final Schema<Person> schema = RuntimeSchema.getSchema(Person.class);
    private final Objenesis objenesis = new ObjenesisStd(true);

    @Override
    public byte[] searialize(Person obj) {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public Person desearialize(byte[] data) {
        final Person person = objenesis.newInstance(Person.class);
        ProtostuffIOUtil.mergeFrom(data, person, schema);
        return person;
    }
}
