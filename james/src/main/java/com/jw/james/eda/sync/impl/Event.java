package com.jw.james.eda.sync.impl;

import com.jw.james.eda.sync.Message;

/**
 * guoyy com.jw.eda.sync.impl
 *
 * @Description: Event
 * @Author: guoyiyong/james
 * @Date: 2019-09-25 00:08
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Event implements Message {

    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
