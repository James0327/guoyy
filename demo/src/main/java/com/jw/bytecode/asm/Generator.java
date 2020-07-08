package com.jw.bytecode.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

/**
 * guoyy com.jw.bytecode.asm
 *
 * @Description: Generator
 * @Author: guoyiyong/james
 * @Date: 2019-12-19 16:44
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Generator {

    public static void main(String[] args) throws Exception {
        new Base().process();
        System.out.println("---------");

        ClassReader classReader = new ClassReader("com/jw/bytecode/asm/Base");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        MyClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);

        byte[] bytes = classWriter.toByteArray();
        File f = new File("target/classes/com/jw/bytecode/asm/Base.class");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bytes);
        fos.close();

        System.out.println("now generator xx success.");
    }

}
