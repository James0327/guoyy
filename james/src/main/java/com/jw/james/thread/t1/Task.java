package com.jw.james.thread.t1;

/**
 * guoyy com.jw.thread.t1
 *
 * @Description: Task
 * @Author: guoyiyong/james
 * @Date: 2019-08-11 11:15
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
@FunctionalInterface
public interface Task<T> {
    T call();
}
