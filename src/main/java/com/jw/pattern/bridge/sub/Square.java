package com.jw.pattern.bridge.sub;

import com.jw.pattern.bridge.Color;
import com.jw.pattern.bridge.Shape;

/**
 * guoyy com.jw.pattern.bridge.sub
 *
 * @Description: com.jw.pattern.bridge.sub.Square
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
