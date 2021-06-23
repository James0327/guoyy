package com.jw.bytecode;

/**
 * Description: guoyy
 * com.jw.bytecode.MyClassLoader
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/5/17 20:56
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> defineMyClass(String name, byte[] b, int off, int len) {
        return super.defineClass(name, b, off, len);
    }

}
