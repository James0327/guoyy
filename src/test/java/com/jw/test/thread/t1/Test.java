package com.jw.test.thread.t1;

import com.jw.thread.t1.Observable;
import com.jw.thread.t1.ObservableThread;
import com.jw.thread.t1.TaskLifecycle;

import java.util.concurrent.TimeUnit;

/**
 * guoyy com.jw.test.thread.t1
 *
 * @Description: com.jw.test.thread.t1.Test
 * @Author: guoyiyong/james
 * @Date: 2019-08-11 11:43
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Observable observable = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("finished done.");
            return null;
        });
        observable.start();

        Thread.currentThread().join();
    }

    @org.junit.jupiter.api.Test
    public void test() throws Exception {
        Observable observable = new ObservableThread<>(new TaskLifecycle.EmptyLifecycle<String>() {
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("finish, Result:" + result);
            }
        }, () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("finished done.");
            return "Hello observable!";
        });
        observable.start();

        Thread.currentThread().join();
    }

}
