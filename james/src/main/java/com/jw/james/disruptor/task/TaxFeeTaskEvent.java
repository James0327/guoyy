package com.jw.james.disruptor.task;

import lombok.Data;
import org.joda.time.DateTime;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Description: atpco_fuel_cost
 * com.ly.ic.atpco.fuel.cost.event.task.TaxFeeTaskEvent
 *
 * @author guoyiyong/james
 * Date: 2022/9/22 22:19
 * Version: 1.0
 *
 * Copyright (C) 2022 JW All rights reserved.
 */
@Data
public class TaxFeeTaskEvent {
    /**
     * 信号量
     */
    private Semaphore semaphore;
    private CountDownLatch latch;
    private CompletableFuture<Object> future;
    /**
     * 请求入参
     */
    private Object atpcoMsg;
    /**
     * 响应报文
     */
    private Object feeTaxMsg;
    /**
     * 跟踪号
     */
    private String traceId;
    /**
     * 请求方
     */
    private String caller;
    /**
     * 请求时间
     */
    private DateTime processRequestTime;
    /**
     * 主线程
     */
    private String mainThread;
}
