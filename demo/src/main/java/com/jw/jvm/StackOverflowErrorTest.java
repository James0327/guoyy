package com.jw.jvm;

import java.util.concurrent.locks.LockSupport;

/**
 * guoyy com.jw.jvm
 *
 * @Description: com.jw.jvm.StackOverflowErrorTest
 * @Author: guoyiyong/james
 * @Date: 2020-07-15 16:41
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class StackOverflowErrorTest {

    private int depth = 0;

    private void sofM() {
        depth++;
        sofM();
    }

    /**
     * 栈空间不足时，需要分下面两种情况处理：
     * 线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError
     * 虚拟机在扩展栈深度时无法申请到足够的内存空间，将抛出OutOfMemberError
     * <p>
     * -Xms20m -Xmx20m -Xss256k -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=logs -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:logs/gc.log
     */
    public static void main(String[] args) {
        StackOverflowErrorTest stackOverflowErrorTest = null;
        try {
            stackOverflowErrorTest = new StackOverflowErrorTest();
            stackOverflowErrorTest.sofM();
        } finally {
            System.out.println("递归次数：" + stackOverflowErrorTest.depth);
            LockSupport.park();
        }
    }

}
