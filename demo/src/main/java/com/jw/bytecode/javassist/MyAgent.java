package com.jw.bytecode.javassist;

import java.lang.instrument.Instrumentation;

/**
 * guoyy com.jw.bytecode.javassist
 *
 * @Description: MyAgent
 * @Author: guoyiyong/james
 * @Date: 2019-12-20 16:06
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class MyAgent {
    public static void agentmain(String args, Instrumentation inst) {
        // 指定我们自己定义的Transformer，在其中利用Javassist做字节码替换
        inst.addTransformer(new MyTransformer(), true);

        try {
            inst.retransformClasses(Base.class);
            System.out.println("agent load done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }
    }
}
