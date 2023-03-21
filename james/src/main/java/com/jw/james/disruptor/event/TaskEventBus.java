package com.jw.james.disruptor.event;

import com.alibaba.fastjson.JSON;
import com.jw.james.disruptor.EventBusUtil;
import com.jw.james.disruptor.Foo;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.joda.time.LocalDateTime;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Description: guoyy
 * com.jw.james.disruptor.event.TaskEventBus
 *
 * @author guoyiyong/james
 * Date: 2022/9/15 16:09
 * Version: 1.0
 *
 * Copyright (C) 2022 JW All rights reserved.
 */
@Slf4j
public enum TaskEventBus {
    ;
    private static final int MAX_CONSUME_THREAD = 1 << 6;
    private static final RingBuffer<EventWrapper<Foo>> RING_BUFFER;

    static {
        RtEventHandler[] handlers = new RtEventHandler[MAX_CONSUME_THREAD];
        for (int i = 0; i < MAX_CONSUME_THREAD; i++) {
            handlers[i] = new RtEventHandler();
        }
        Disruptor<EventWrapper<Foo>> disruptor = new Disruptor<>(EventWrapper::new, EventBusUtil.getBufferSize(MAX_CONSUME_THREAD << 1 << 1),
                new BasicThreadFactory.Builder().namingPattern("EventBus-Thread-%d").daemon(true).build(),
                ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new EventMonitorBusExceptionHandler());
        disruptor.handleEventsWithWorkerPool(handlers);
        disruptor.start();
        // 销毁队列
        Runtime.getRuntime().addShutdownHook(new Thread(disruptor::shutdown));

        RING_BUFFER = disruptor.getRingBuffer();
    }

    public static void publishEvent(Foo foo) {
        try {
            RING_BUFFER.publishEvent((event, sequence, data) -> event.setValue(data), foo);
        } catch (Exception e) {
            log.error("Publish Event Error", e);
        }
    }

    private static class RtEventHandler implements WorkHandler<EventWrapper<Foo>> {
        @Override
        public void onEvent(EventWrapper<Foo> event) {
            Foo foo = event.getValue();
            System.out.println(LocalDateTime.now() + "][" + JSON.toJSONString(foo));
            log.info("foo: {}", JSON.toJSONString(foo));
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(3));
        }
    }

    private static class EventMonitorBusExceptionHandler implements ExceptionHandler<EventWrapper<Foo>> {
        @Override
        public void handleEventException(Throwable ex, long sequence, EventWrapper<Foo> event) {
            log.error("HandleEventException: {}", JSON.toJSONString(event), ex);
        }

        @Override
        public void handleOnStartException(Throwable ex) {
            log.error("HandleOnStartException", ex);
        }

        @Override
        public void handleOnShutdownException(Throwable ex) {
            log.error("HandleOnShutdownException", ex);
        }
    }

}