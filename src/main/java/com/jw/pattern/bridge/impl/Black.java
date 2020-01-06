package com.jw.pattern.bridge.impl;

import com.jw.pattern.bridge.Color;
import com.jw.pattern.bridge.Shape;

/**
 * guoyy com.jw.pattern.bridge.impl
 *
 * @Description: com.jw.pattern.bridge.impl.Black
 * @Author: guoyiyong/james
 * @Date: 2019-09-22 22:57
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Black implements Color {
    @Override
    public void bepaint(Shape shape) {
        System.out.println("黑色的" + shape.getName());
    }
}
