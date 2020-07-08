package com.jw.bridge.impl;

import com.jw.bridge.Shape;
import com.jw.bridge.Color;

/**
 * guoyy com.jw.pattern.bridge.impl
 *
 * @Description: White
 * @Author: guoyiyong/james
 * @Date: 2019-09-22 22:54
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class White implements Color {

    @Override
    public void bepaint(Shape shape) {
        System.out.println("白色的" + shape.getName());
    }
}
