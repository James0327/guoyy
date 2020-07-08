package com.jw.bytecode.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * guoyy com.jw.bytecode.javassist
 *
 * @Description: MyTransformer
 * @Author: guoyiyong/james
 * @Date: 2019-12-20 16:58
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class MyTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass cc = classPool.get("Base");
            CtMethod m = cc.getDeclaredMethod("doWork");
            m.insertBefore("{System.out.println(\"start\");}");
            m.insertAfter("{System.out.println(\"end\");}");
            return cc.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}