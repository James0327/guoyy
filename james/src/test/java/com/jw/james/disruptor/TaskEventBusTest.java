package com.jw.james.disruptor;

import com.jw.james.disruptor.event.TaskEventBus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Description: guoyy
 * com.jw.james.disruptor.TaskEventBusTest
 *
 * @author guoyiyong/james
 * Date: 2022/9/15 16:26
 * Version: 1.0
 *
 * Copyright (C) 2022 JW All rights reserved.
 */
public class TaskEventBusTest {

    public static void main(String[] args) {

        for (int i = 0; i < 99999; i++) {
            Foo foo = new Foo();
            foo.setId(i);

            LocalDateTime start = LocalDateTime.now();
            TaskEventBus.publishEvent(foo);
            LocalDateTime end = LocalDateTime.now();
            System.out.println("start:" + start + ",end:" + end + ",rt:" + Duration.between(start, end).toMillis());

            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(1));
        }

    }

}
