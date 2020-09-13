package com.jw.aspect;

/**
 * test com.jw.aspect
 *
 * @Description: T
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/18 18:52
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
public class T {

    @Monitor
    public void r() {
        System.out.println("running is here [r].");
    }

    public static void main(String[] args) {
        MonitorAspect monitor = new MonitorAspect();
        System.out.println(monitor.hashCode());
        new T().r();
    }

}
