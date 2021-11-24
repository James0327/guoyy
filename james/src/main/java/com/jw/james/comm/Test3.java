package com.jw.james.comm;

/**
 * guoyy com.jw.comm
 *
 * @Description: Test3
 * @Author: guoyiyong/james
 * @Date: 2019-12-16 00:23
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test3 {
    public synchronized  void doSomething(){
        System.out.println("doSomething");
        doOther();
    }

    public synchronized void doOther(){
        System.out.println("doOther");
    }

}
