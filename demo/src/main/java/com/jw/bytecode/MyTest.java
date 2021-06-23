package com.jw.bytecode;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * Description: guoyy
 * com.jw.bytecode.MyTest
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/5/17 21:00
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class MyTest {

    public static void main(String[] args) throws Exception {
        // 读取本地的class文件内的字节码，转换成字节码数组
        File file = new File("");
        String filePath = file.getCanonicalFile() + "/demo/target/classes/com/jw/dto/Foo.class";
        FileInputStream fis = new FileInputStream(filePath);
        byte[] bytes = new byte[fis.available()];
        int i = fis.read(bytes);
        fis.close();
        System.out.println(bytes.length + "/" + i);

        MyClassLoader classLoader = new MyClassLoader();
        Class<?> foo = classLoader.defineMyClass(null, bytes, 0, i);
        System.out.println(foo.getCanonicalName());

        Object obj = foo.newInstance();

        Method setTraceId = foo.getMethod("setTraceId", String.class);
        setTraceId.invoke(obj, "xxx-xxx");

        System.out.println(ToStringBuilder.reflectionToString(obj));
    }

}
