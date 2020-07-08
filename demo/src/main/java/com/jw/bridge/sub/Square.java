package com.jw.bridge.sub;

import com.jw.bridge.Shape;
import com.jw.bridge.Color;

/**
 * guoyy com.jw.pattern.bridge.sub
 *
 * @Description: Square
 * @Author: guoyiyong/james
 * @Date: 2019-09-22 22:51
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    @Override
    public String getName() {
        return "正方形";
    }

    @Override
    public void draw() {
        color.bepaint(this);
    }
}
