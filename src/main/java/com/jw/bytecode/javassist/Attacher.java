package com.jw.bytecode.javassist;

import com.sun.tools.attach.VirtualMachine;

/**
 * guoyy com.jw.bytecode.javassist
 *
 * @Description: com.jw.bytecode.javassist.Attacher
 * @Author: guoyiyong/james
 * @Date: 2019-12-20 19:31
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Attacher {

    // 1、执行Base main方法，获取PID
    // 2、执行本main方法 动态织入
    public static void main(String[] args) throws Exception {
        VirtualMachine attach = VirtualMachine.attach("25196");
        // attach.loadAgent("t2019.jar");
        attach.loadAgent("target/classes/t2019.jar");
    }
}
