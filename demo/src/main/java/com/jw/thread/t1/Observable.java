package com.jw.thread.t1;

/**
 * guoyy com.jw.thread.t1
 *
 * @Description: Observable
 * @Author: guoyiyong/james
 * @Date: 2019-08-11 11:02
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public interface Observable {

    enum Cycle {
        STARTED, RUNNING, DONE, ERROR
    }

    Cycle getCycle();


    void start();

    void interrupt();

}
