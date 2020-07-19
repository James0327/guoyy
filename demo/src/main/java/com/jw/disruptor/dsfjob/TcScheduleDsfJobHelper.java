package com.jw.disruptor.dsfjob;

import com.alibaba.fastjson.JSON;
import com.jw.disruptor.dsfjob.wrapper.EventWrapper;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * taxbag-batch-core com.ly.ic.tcschedule.dsfjob
 *
 * @Description: com.ly.ic.tcschedule.dsfjob.TCScheduleDsfJobHelper
 * @Author: guoyiyong/james
 * @Date: 2020-06-09 23:40
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Slf4j
public class TcScheduleDsfJobHelper {

    private final RingBuffer<EventWrapper<JobReqDTO>> ringBuffer;

    public TcScheduleDsfJobHelper() {

        // 初始化队列
        Disruptor<EventWrapper<JobReqDTO>> disruptor = new Disruptor<>(EventWrapper::new, 1 << 7,
                new BasicThreadFactory.Builder().namingPattern("TcScheduleDsfJob-exec-%d").daemon(true).build(),
                ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.setDefaultExceptionHandler(new TcScheduleDsfJobExceptionHandler());

        TcScheduleDsfJobEventHandler tcScheduleDsfJobEventHandler = new TcScheduleDsfJobEventHandler();

        WorkHandler<EventWrapper<JobReqDTO>>[] workHandlers = new WorkHandler[6];
        workHandlers[0] = tcScheduleDsfJobEventHandler;
        for (int i = 1; i < 6; i++) {
            workHandlers[i] = tcScheduleDsfJobEventHandler;
        }
        disruptor.handleEventsWithWorkerPool(workHandlers);
        //        disruptor.handleEventsWith(new TcScheduleDsfJobEventHandler());

        disruptor.start();
        // 销毁队列
        Runtime.getRuntime().addShutdownHook(new Thread(() -> disruptor.shutdown()));

        ringBuffer = disruptor.getRingBuffer();
    }

    public JobRespDTO consume(JobReqDTO jobReqDTO) {
        JobRespDTO jobRespDTO = new JobRespDTO();
        if (jobReqDTO != null && StringUtils.isNotBlank(jobReqDTO.getJobName()) && StringUtils.isNotBlank(jobReqDTO.getTraceId())) {
            try {
                ringBuffer.publishEvent((event, sequence, data) -> event.setValue(data), jobReqDTO);
                System.out.println("ringBuffer: " + ringBuffer);
            } catch (Exception ex) {
                jobRespDTO.setSuccess(false);
            }
        } else {
            jobRespDTO.setSuccess(false);
        }

        return jobRespDTO;
    }

    /**
     * 事件处理
     */
    private class TcScheduleDsfJobEventHandler implements EventHandler<EventWrapper<JobReqDTO>>, WorkHandler<EventWrapper<JobReqDTO>> {
        @Override
        public void onEvent(EventWrapper<JobReqDTO> event, long sequence, boolean endOfBatch) {
            onEvent(event);
        }

        @Override
        public void onEvent(EventWrapper<JobReqDTO> event) {
            final JobReqDTO jobReqDTO = event.getValue();
            final String jobName = jobReqDTO.getJobName();

            final DateTime start = DateTime.now();
            System.out.println(Thread.currentThread().getName() + "消费逻辑。。。" + jobName);
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(3));
            final DateTime end = DateTime.now();
            // 更新状态为就绪
            log.info("更新状态为就绪,JobName:{},{}=>{}", jobName, "DOING", "READY");
            log.info("estimated time(ms): ", end.getMillis() - start.getMillis());
        }
    }

    /**
     * 异常处理
     */
    private class TcScheduleDsfJobExceptionHandler implements ExceptionHandler<EventWrapper<JobReqDTO>> {
        @Override
        public void handleEventException(Throwable ex, long sequence, EventWrapper<JobReqDTO> event) {
            log.error(String.format("HandleEventException:%s", JSON.toJSONString(event)), ex);
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
