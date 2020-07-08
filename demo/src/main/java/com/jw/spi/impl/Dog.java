package com.jw.spi.impl;

import com.jw.spi.IShout;

/**
 * guoyy com.jw.demo.spi.impl
 *
 * @Description: Dog
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
