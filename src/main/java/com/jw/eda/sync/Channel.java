package com.jw.eda.sync;

/**
 * guoyy com.jw.eda.sync
 *
 * @Description: com.jw.eda.sync.Channel
 * @Author: guoyiyong/james
 * @Date: 2019-09-25 00:01
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public interface Channel<E extends Message> {
    /**
     * dispatch 负责message 调度
     *
     * @param message
     */
    void dispatch(E message);
}
