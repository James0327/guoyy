package com.jw.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * guoyy com.jw.demo.disruptor
 *
 * @Description: LongEventFactory
 * @Author: guoyiyong/james.guo
 * @Date: 2020/5/8 21:35
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
