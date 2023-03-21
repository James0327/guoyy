package com.jw.james.disruptor;

/**
 * Description: atpco_fuel_cost
 * com.ly.ic.atpco.fuel.cost.event.log.EventBusUtil
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/7 10:09
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class EventBusUtil {

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * Returns a power of two size for the given target capacity.
     */
    public static final int getBufferSize(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        if (n < 0) {
            return 1;
        }
        if (n >= MAXIMUM_CAPACITY) {
            return MAXIMUM_CAPACITY;
        }
        return n + 1;
    }
}
