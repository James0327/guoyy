package com.jw.james.pattern.event;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Vector;

/**
 * guoyy com.jw.pattern.event
 * <p>
 * 事件源类。表明谁触发了事件，用于作为EventObject类的构造参数，在listener中作路由
 *
 * @Description: com.jw.pattern.event.JwEventSource
 * @Author: guoyiyong/james
 * @Date: 2021-01-29 10:52
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class JwEventSource {

    private final Vector<JwEventListener> listeners = new Vector<>();

    public void addEventListener(JwEventListener listener) {
        listeners.add(listener);
    }

    public List<Object> action(JwEventObject input) {
        System.out.println("input: " + input);

        List<Object> retList = Lists.newArrayList();

        for (int i = 0, len = listeners.size(); i < len; i++) {
            JwEventListener listener = listeners.elementAt(i);
            Object ret = listener.onEvent(input);
            retList.add(ret);
        }

        return retList;
    }

}
