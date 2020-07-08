package com.jw.disruptor.dsfjob.wrapper;

import lombok.Data;

/**
 * taxbag-batch-core com.ly.ic.tcschedule.dsfjob.wrapper
 *
 * @Description: com.ly.ic.tcschedule.dsfjob.wrapper.EventWrapper
 * @Author: guoyiyong/james
 * @Date: 2020-06-10 11:31
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Data
public class EventWrapper<T> {
    private T value;
}
