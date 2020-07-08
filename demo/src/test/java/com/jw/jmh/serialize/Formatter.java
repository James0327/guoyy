package com.jw.jmh.serialize;

import java.io.IOException;

/**
 * guoyy com.jw.jmh.serialize
 *
 * @Description: com.jw.jmh.serialize.Formatter
 * @Author: guoyiyong/james
 * @Date: 2020-03-29 20:55
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public interface Formatter<T> {

    byte[] searialize(T obj) throws IOException;

    T desearialize(byte[] data) throws IOException;
}
