package com.jw.bridge;

/**
 * guoyy com.jw.pattern.bridge
 *
 * @Description: Shape
 * @Author: guoyiyong/james
 * @Date: 2019-09-22 22:30
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public abstract class Shape {
    protected Color color;

    protected Shape(Color color) {
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract String getName();

    public abstract void draw();
}
