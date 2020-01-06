package com.jw.eda.sync.impl;

import com.jw.eda.sync.Channel;
import com.jw.eda.sync.DynamicRouter;
import com.jw.eda.sync.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * guoyy com.jw.eda.sync.impl
 *
 * @Description: com.jw.eda.sync.impl.EventDispatcher
 * @Author: guoyiyong/james
 * @Date: 2019-09-25 00:09
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class EventDispatcher implements DynamicRouter<Message> {
    private final Map<Class<? extends Message>, Channel> routerTable;

    public EventDispatcher() {
        this.routerTable = new HashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Message> messageType, Channel<? extends Message> channel) {
        this.routerTable.put(messageType, channel);
    }

    @Override
    public void dispatch(Message message) {
        if (routerTable.containsKey(message.getType())) {
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new RuntimeException("cannot match channel for [" + message.getType() + "] type.");
        }
    }
}
