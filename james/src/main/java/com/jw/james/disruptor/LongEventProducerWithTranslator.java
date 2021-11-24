package com.jw.james.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * guoyy com.jw.demo.disruptor
 *
 * @Description: LongEventProducerWithTranslator
 * @Author: guoyiyong/james.guo
 * @Date: 2020/5/8 21:41
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
public class LongEventProducerWithTranslator {
    private final RingBuffer<LongEvent> ringBuffer;

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR
            = (event, sequence, buffer) -> event.set(buffer.getLong(0));

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer buffer) {
        ringBuffer.publishEvent(TRANSLATOR, buffer);
    }
}
