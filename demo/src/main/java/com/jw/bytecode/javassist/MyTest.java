package com.jw.bytecode.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;

/**
 * Description: guoyy
 * com.jw.bytecode.javassist.MyTest
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/5/17 22:09
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class MyTest {

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeClass("com.jw.dto.Foo3");
        CtMethod method = CtMethod.make("public void code(){}", ctClass);
        method.insertBefore("System.out.println(\"I'm a Programmer,Just Coding.....\");");
        ctClass.addMethod(method);

        // String filePath = new File("").getCanonicalFile() + "/demo/target/classes";
        // ctClass.writeFile(filePath);

        Class<?> foo3 = ctClass.toClass();
        Object obj = foo3.newInstance();
        Method code = foo3.getMethod("code");
        code.invoke(obj);
    }

}
