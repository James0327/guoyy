package com.jw.james.eda.sync;

/**
 * guoyy com.jw.eda.sync
 *
 * @Description: DynamicRouter
 * @Author: guoyiyong/james
 * @Date: 2019-09-25 00:03
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public interface DynamicRouter<E extends Message> {
    /**
     * 针对每一种message type 注册相关的channel，只有找到合适的channel该message才会被处理
     *
     * @param messageType
     * @param channel
     */
    void registerChannel(Class<? extends E> messageType, Channel<? extends E> channel);

    /**
     * 为相应的channel 分配message
     *
     * @param message
     */
    void dispatch(E message);
}
