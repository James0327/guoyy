package com.jw.james.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * guoyy com.jw.demo
 *
 * @Description: DisruptorTest2
 * @Author: guoyiyong/james
 * @Date: 2020-06-03 16:20
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class DisruptorTest2 {

    public static void main(String[] args) {
        // The ThreadFactory for create producer thread.

        // The factory for the event
        LongEventFactory eventFactory = new LongEventFactory();

        Disruptor<LongEvent> disruptor = new Disruptor<>(
                eventFactory, 8,
                DaemonThreadFactory.INSTANCE, ProducerType.SINGLE,
                new BlockingWaitStrategy());

        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
        ByteBuffer buffer = ByteBuffer.allocate(8);
        long l = 0;
        while (true) {
            buffer.putLong(0, l++);
            producer.onData(buffer);
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(10));
        }

    }

}
