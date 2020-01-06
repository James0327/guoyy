package com.jw.pattern.bridge;

import com.jw.pattern.bridge.impl.Gray;
import com.jw.pattern.bridge.impl.White;
import com.jw.pattern.bridge.sub.Circle;

/**
 * guoyy com.jw.pattern.bridge
 *
 * @Description: com.jw.pattern.bridge.Client
 * @Author: guoyiyong/james
 * @Date: 2019-09-22 22:57
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Client {

    /**
     * 桥接模式主要包含如下几个角色：
     * Abstraction：抽象类
     * RefinedAbstraction：扩充抽象类
     * Implementor：实现类接口
     * ConcreteImplementor：具体实现类
     */
    public static void main(String[] args) {
        White white = new White();
        Circle circle = new Circle(white);
        circle.draw();

        Gray gray = new Gray();
        circle.setColor(gray);
        circle.draw();
    }
}
