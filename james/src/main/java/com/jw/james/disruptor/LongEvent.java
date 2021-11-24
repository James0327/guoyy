package com.jw.james.disruptor;

/**
 * guoyy com.jw.demo.disruptor
 * <p>
 * 定义事件，Event就是通过 Disruptor 进行交换的数据类型（事件监听模式)
 *
 * @Description: LongEvent
 * @Author: guoyiyong/james.guo
 * @Date: 2020/5/8 21:31
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("LongEvent{value=%s}", value);
    }
}
