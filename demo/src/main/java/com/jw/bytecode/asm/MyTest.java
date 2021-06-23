package com.jw.bytecode.asm;

import com.jw.bytecode.MyClassLoader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

/**
 * Description: guoyy
 * com.jw.bytecode.asm.MyTest
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/5/17 21:27
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class MyTest {

    public static void main(String[] args) throws Exception {
        ClassWriter classWriter = new ClassWriter(0);

        // 通过visit方法确定类的头部信息
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "com/jw/dto/Foo2", null, "java/lang/Object", null);

        // 创建构造函数
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        // 定义code方法
        MethodVisitor code = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "code", "()V", null, null);
        code.visitCode();
        code.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        code.visitLdcInsn("I am a Programmer, Just Coding ... ");
        code.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        code.visitInsn(Opcodes.RETURN);
        code.visitMaxs(2, 2);
        code.visitEnd();

        classWriter.visitEnd();

        String filePath = new File("").getCanonicalFile() + "/demo/target/classes/com/jw/dto/Foo2.class";
        File file = new File(filePath);

        byte[] bytes = classWriter.toByteArray();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();

        MyClassLoader classLoader = new MyClassLoader();
        Class<?> foo2 = classLoader.defineMyClass(null, bytes, 0, bytes.length);
        System.out.println("name: " + foo2.getCanonicalName());
        Object obj = foo2.newInstance();
        Method mCode = foo2.getMethod("code");
        mCode.invoke(obj);
        System.out.println("~~~~~~~~~");
    }

}
