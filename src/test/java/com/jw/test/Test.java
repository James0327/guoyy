package com.jw.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * guoyy com.jw.test
 *
 * @Description: com.jw.test.Test
 * @Author: guoyiyong/james
 * @Date: 2020-01-29 23:02
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Test {

    protected void rowDataHandler(String input) {
        System.out.println("input[test]: " + input);
    }

    protected void cacheDataHandler(String input) {
        this.rowDataHandler(input);
        System.out.println("---------");

        try {
            Method method = Test.class.getDeclaredMethod("rowDataHandler", String.class);
            method.invoke(this, input);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}

