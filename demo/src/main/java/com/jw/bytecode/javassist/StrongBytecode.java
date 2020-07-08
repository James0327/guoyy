package com.jw.bytecode.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * guoyy com.jw.bytecode.javassist
 *
 * @Description: StrongBytecode
 * @Author: guoyiyong/james
 * @Date: 2019-12-19 22:57
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class StrongBytecode {

    public static void main(String[] args) throws Exception {
        ClassPool classPool = ClassPool.getDefault();
        CtClass cc = classPool.get("Base");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{System.out.println(\"start\");}");
        m.insertAfter("{System.out.println(\"end\");}");
        Class<?> c = cc.toClass();
        cc.writeFile("target/classes/");
        Base o = (Base)c.newInstance();
        o.process();
    }

}
