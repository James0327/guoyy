package com.jw.james.test;

/**
 * guoyy com.jw.test
 *
 * @Description: com.jw.test.SubTest
 * @Author: guoyiyong/james
 * @Date: 2020-01-29 23:04
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class SubTest extends Test {
    protected void rowDataHandler(String input) {
        System.out.println("input[subtest]: " + input);
    }

    public static void main(String[] args) {
        new SubTest().cacheDataHandler("aaa");
    }

}
