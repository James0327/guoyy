package com.jw.james.disruptor.task;

import com.alibaba.fastjson.JSON;
import com.jw.james.disruptor.EventBusUtil;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.joda.time.DateTime;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Description: atpco_fuel_cost
 * com.ly.ic.atpco.fuel.cost.event.task.TaxFeeTaskEventBus
 *
 * @author guoyiyong/james
 * Date: 2022/9/22 22:38
 * Version: 1.0
 *
 * Copyright (C) 2022 JW All rights reserved.
 */
@Slf4j
public enum TaxFeeTaskEventBus {
    ;

    private static final RingBuffer<TaxFeeTaskEvent> RING_BUFFER;

    static {
        int cpu = Runtime.getRuntime().availableProcessors();
        int maxConsumeThread = 50;
        TaskEventHandler[] handlers = new TaskEventHandler[maxConsumeThread];
        for (int i = 0; i < maxConsumeThread; i++) {
            handlers[i] = new TaskEventHandler();
        }
        Disruptor<TaxFeeTaskEvent> disruptor = new Disruptor<>(TaxFeeTaskEvent::new,
                EventBusUtil.getBufferSize(1024 * 1024), new BasicThreadFactory
                .Builder().namingPattern("TaxFeeTaskEventBus-Thread-%d").build(),
                ProducerType.MULTI, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new EventMonitorBusExceptionHandler());
        disruptor.handleEventsWithWorkerPool(handlers);
        disruptor.start();

        RING_BUFFER = disruptor.getRingBuffer();

        // 销毁队列
        Runtime.getRuntime().addShutdownHook(new Thread(disruptor::shutdown));
    }

    public static void publishEvent(Object atpcoMsg, Object feeTaxMsg,
                                    Semaphore semaphore, String traceId, String caller,
                                    DateTime processRequestTime, String mainThread) {
        final long sequence = RING_BUFFER.next();
        try {
            TaxFeeTaskEvent event = RING_BUFFER.get(sequence);
            event.setSemaphore(semaphore);
            event.setAtpcoMsg(atpcoMsg);
            event.setFeeTaxMsg(feeTaxMsg);
            event.setTraceId(traceId);
            event.setCaller(caller);
            event.setProcessRequestTime(processRequestTime);
            event.setMainThread(mainThread);
        } finally {
            RING_BUFFER.publish(sequence);
        }
    }

    public static void publishEvent2(Object atpcoMsg, Object feeTaxMsg,
                                     CountDownLatch latch, String traceId, String caller,
                                     DateTime processRequestTime, String mainThread) {
        final long sequence = RING_BUFFER.next();
        try {
            TaxFeeTaskEvent event = RING_BUFFER.get(sequence);
            event.setLatch(latch);
            event.setAtpcoMsg(atpcoMsg);
            event.setFeeTaxMsg(feeTaxMsg);
            event.setTraceId(traceId);
            event.setCaller(caller);
            event.setProcessRequestTime(processRequestTime);
            event.setMainThread(mainThread);
        } finally {
            RING_BUFFER.publish(sequence);
        }
    }

    public static void publishEvent3(Object request, Object response, CompletableFuture<Object> future,
                                     String traceId, String caller, DateTime processStart, String mainThread) {
        final long sequence = RING_BUFFER.next();
        try {
            TaxFeeTaskEvent event = RING_BUFFER.get(sequence);
            event.setFuture(future);
            event.setAtpcoMsg(request);
            event.setFeeTaxMsg(response);
            event.setTraceId(traceId);
            event.setCaller(caller);
            event.setProcessRequestTime(processStart);
            event.setMainThread(mainThread);
        } finally {
            RING_BUFFER.publish(sequence);
        }
    }

    private static class TaskEventHandler implements WorkHandler<TaxFeeTaskEvent> {
        @Override
        public void onEvent(TaxFeeTaskEvent event) {
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(30));

            Semaphore semaphore = event.getSemaphore();
            CountDownLatch latch = event.getLatch();
            CompletableFuture<Object> future = event.getFuture();
            if (semaphore != null) {
                semaphore.release();
            }
            if (future != null) {
                future.complete(null);
            }
            if (latch != null) {
                latch.countDown();
            }
        }
    }

    private static class EventMonitorBusExceptionHandler implements ExceptionHandler<TaxFeeTaskEvent> {
        @Override
        public void handleEventException(Throwable ex, long sequence, TaxFeeTaskEvent event) {
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
