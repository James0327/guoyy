package com.jw.bytecode.javassist;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * guoyy com.jw.bytecode.javassist
 *
 * @Description: com.jw.bytecode.javassist.Base
 * @Author: guoyiyong/james
 * @Date: 2019-12-19 23:00
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Base {

    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        System.out.println("name:" + name + ",pid:" + pid);
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                break;
            }
            doWork();
        }
    }

    private static void doWork() {
        System.out.println("doWork ...");
    }

    public void process() {
        System.out.println("process /javassist/ ... ");
    }
}
