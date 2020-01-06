package com.jw.demo.eda;

/**
 * guoyy com.jw.demo.eda
 *
 * @Description: com.jw.demo.eda.Client
 * @Author: guoyiyong/james
 * @Date: 2019-09-24 23:52
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Client {

    public static void main(String[] args) {

        EventLoop eventLoop = new EventLoop();

        eventLoop.add(new Event("A", "AAAAAAAAAAAAAbbbbbbbbbbbbbbb"));
        eventLoop.add(new Event("B", "CCCCCCCCCCCCCCCCCddddddddd"));

    }
}
