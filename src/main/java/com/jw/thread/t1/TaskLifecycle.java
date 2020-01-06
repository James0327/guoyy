package com.jw.thread.t1;

/**
 * guoyy com.jw.thread.t1
 *
 * @Description: com.jw.thread.t1.TaskLIfecycle
 * @Author: guoyiyong/james
 * @Date: 2019-08-11 11:06
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public interface TaskLifecycle<T> {

    void onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread, T result);

    void onError(Thread thread);

    class EmptyLifecycle<T> implements TaskLifecycle<T> {
        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread) {

        }
    }

}
