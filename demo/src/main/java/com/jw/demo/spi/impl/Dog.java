package com.jw.demo.spi.impl;

import com.jw.demo.spi.IShout;

/**
 * guoyy com.jw.demo.spi.impl
 *
 * @Description: com.jw.demo.spi.impl.Dog
 * @Author: guoyiyong/james
 * @Date: 2020-07-03 21:38
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Dog implements IShout {

    @Override
    public void shout() {
        System.out.println("wang wang");
    }
}
