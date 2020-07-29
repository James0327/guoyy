package com.jw.tcly.proxy;

import net.sf.cglib.proxy.FixedValue;

import java.util.concurrent.ThreadLocalRandom;

/**
 * guoyy com.jw.tcly.proxy
 *
 * @Description: com.jw.tcly.proxy.ConcreteClassFixedValue
 * @Author: guoyiyong/james
 * @Date: 2020-07-15 21:26
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class ConcreteClassFixedValue implements FixedValue {

    @Override
    public Object loadObject() {
        System.out.println("ConcreteClassFixedValue loadObject ...");
        int random = ThreadLocalRandom.current().nextInt();
        System.out.println("random: " + random);
        return random;
    }
}
