package com.jw.eda.async;

/**
 * guoyy com.jw.eda.sync.async
 *
 * @Description: com.jw.eda.sync.async.Message
 * @Author: guoyiyong/james
 * @Date: 2019-09-24 23:58
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public interface Message {
    /**
     * 返回message type
     *
     * @return
     */
    Class<? extends Message> getType();
}
