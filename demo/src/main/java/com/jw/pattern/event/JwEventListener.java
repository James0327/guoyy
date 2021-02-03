package com.jw.pattern.event;

import java.util.EventListener;
import java.util.concurrent.ThreadLocalRandom;

/**
 * guoyy com.jw.pattern.event
 *
 * @Description: com.jw.pattern.event.JwEventListener
 * @Author: guoyiyong/james
 * @Date: 2021-01-29 10:53
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public abstract class JwEventListener implements EventListener {

    protected abstract Object onEvent(JwEventObject obj);

    public static class EventOneListener extends JwEventListener {
        @Override
        protected Object onEvent(JwEventObject obj) {
            if (obj instanceof JwEventObject.EventOne) {
                Object source = obj.getSource();
                System.out.println("EventOne onEvent: " + source);
                return ThreadLocalRandom.current().nextLong();
            }
            return null;
        }
    }

    public static class EventTwoListener extends JwEventListener {
        @Override
        protected Object onEvent(JwEventObject obj) {
            if (obj instanceof JwEventObject.EventTwo) {
                Object source = obj.getSource();
                System.out.println("EventTwo onEvent: " + source);
                return ThreadLocalRandom.current().nextLong();
            }
            return null;
        }
    }
}

