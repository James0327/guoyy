package com.jw.disruptor;

import com.lmax.disruptor.EventHandler;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * guoyy com.jw.demo.disruptor
 *
 * @Description: LongEventHandler
 * @Author: guoyiyong/james.guo
 * @Date: 2020/5/8 21:33
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    /**
     * @param event
     * @param sequence   环形数组中的序列号
     * @param endOfBatch
     * @throws Exception
     */
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(ToStringBuilder.reflectionToString(event));
    }
}
