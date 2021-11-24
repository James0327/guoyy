package com.jw.james.pattern.event;

import java.util.EventObject;

/**
 * guoyy com.jw.pattern.event
 *
 * @Description: com.jw.pattern.event.JwEventObject
 * @Author: guoyiyong/james
 * @Date: 2021-01-29 10:53
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public abstract class JwEventObject extends EventObject {

    private JwEventObject(Object source) {
        super(source);
    }

    public static class EventOne extends JwEventObject {
        public EventOne(Object source) {
            super(source);
        }
    }

    public static class EventTwo extends JwEventObject {
        public EventTwo(Object source) {
            super(source);
        }
    }

}
