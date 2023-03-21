package com.jw.james.disruptor.event;

import lombok.Data;

/**
 * Description: atpco_fuel_cost
 * com.ly.ic.atpco.fuel.cost.event.log.EventWrapper
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/7 10:10
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Data
public class EventWrapper<T> {
    /**
     * Object
     */
    private T value;
}
