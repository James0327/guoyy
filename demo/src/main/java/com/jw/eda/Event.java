package com.jw.eda;

/**
 * guoyy com.jw.demo.eda
 *
 * @Description: Event
 * @Author: guoyiyong/james
 * @Date: 2019-09-24 23:31
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Event {
    private final String type;
    private final String data;

    public Event(String type, String data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
