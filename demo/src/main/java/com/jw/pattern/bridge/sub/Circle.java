package com.jw.pattern.bridge.sub;

import com.jw.pattern.bridge.Shape;
import com.jw.pattern.bridge.Color;

/**
 * guoyy com.jw.pattern.bridge.sub
 *
 * @Description: Circle
 * @Author: guoyiyong/james
 * @Date: 2019-09-22 22:42
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Circle extends Shape {

    public Circle(Color color) {
        super(color);
    }

    @Override
    public String getName() {
        return "圆形";
    }

    @Override
    public void draw() {
        color.bepaint(this);
    }
}
