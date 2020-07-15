package com.jw.jvm;

/**
 * guoyy com.jw.jvm
 *
 * @Description: com.jw.jvm.StackOOMTest
 * @Author: guoyiyong/james
 * @Date: 2020-07-15 17:44
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class StackOOMTest {

    /**
     * -Xms20m -Xmx20m -Xss256k -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=logs -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:logs/gc.log
     */
    public static void main(String[] args) {
        StackOOMTest test = new StackOOMTest();
        test.oomMethod();
    }

    public void oomMethod() {
        while (true) {
            new Thread(() -> loopMethod()).start();
        }
    }

    private void loopMethod() {
        while (true) {

        }
    }
}
