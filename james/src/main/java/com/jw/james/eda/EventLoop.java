package com.jw.james.eda;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * guoyy com.jw.demo.eda
 *
 * @Description: EventLoop
 * @Author: guoyiyong/james
 * @Date: 2019-09-24 23:36
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class EventLoop {
    private LinkedBlockingQueue<Event> events = new LinkedBlockingQueue<>();

    public void add(Event event) {
        events.add(event);
    }

    public void handlerA(Event event) {
        System.out.println(event.getData().toLowerCase());
    }

    public void handlerB(Event event) {
        System.out.println(event.getData().toUpperCase());
    }

    {
        new Thread(() -> {
            try {
                while (true) {
                    Event event = events.take();
                    switch (event.getType()) {
                        case "A":
                            handlerA(event);
                            break;
                        case "B":
                            handlerB(event);
                            break;
                        default:
                            break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "event-loop").start();
    }

}
