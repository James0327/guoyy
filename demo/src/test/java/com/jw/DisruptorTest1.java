package com.jw;

import com.jw.disruptor.LongEvent;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * guoyy com.jw.demo
 *
 * @Description: DisruptorTest1
 * @Author: guoyiyong/james.guo
 * @Date: 2020/5/8 21:14
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
public class DisruptorTest1 {

    public static void main(String[] args) throws InterruptedException {
        // Specify the size of the ring buffer, must be power of 2.
        final int bufferSize = 1024;
        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
        // Construct the Disruptor with a SingleProducerSequencer
        Disruptor<LongEvent> disruptor2 = new Disruptor(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BlockingWaitStrategy());

        // Connect the handler
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event: " + event));
        disruptor2.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event2: " + event));
        // Start the Disruptor, starts all threads running
        disruptor.start();
        disruptor2.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        RingBuffer<LongEvent> ringBuffer2 = disruptor2.getRingBuffer();
        ByteBuffer buffer = ByteBuffer.allocate(8);
        for (int l = 0; true; l++) {
            buffer.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buf) -> event.set(buf.getLong(0)), buffer);
            ringBuffer2.publishEvent((event, sequence, buf) -> event.set(buf.getLong(0)), buffer);
            TimeUnit.SECONDS.sleep(1);
        }

    }
}
