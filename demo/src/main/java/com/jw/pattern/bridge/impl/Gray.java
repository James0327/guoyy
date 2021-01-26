package com.jw.pattern.bridge.impl;

import com.jw.pattern.bridge.Shape;
import com.jw.pattern.bridge.Color;

/**
 * guoyy com.jw.pattern.bridge.impl
 *
 * @Description: Gray
 * @Author: guoyiyong/james
 * @Date: 2019-09-22 22:56
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Gray implements Color {
    @Override
    public void bepaint(Shape shape) {
        System.out.println("灰色的" + shape.getName());
    }
}
