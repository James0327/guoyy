package com.jw.james.spi;

import java.util.ServiceLoader;

/**
 * guoyy com.jw.demo.spi
 *
 * @Description: Test
 * @Author: guoyiyong/james
 * @Date: 2020-07-03 21:40
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Test {

    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout s : shouts) {
            s.shout();
        }
    }
}
