package com.jw.james.convert.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: trade-postsalecore-parent
 * com.ly.travel.mall.trade.postsalecore.facade.model.response.BasePostsaleResponseDTO
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/10/17 15:11
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Data
public class BasePostsaleResponseDTO<T> implements Serializable {
    /**
     * 跟踪号
     */
    private String  traceId;
    /**
     * 消息
     */
    private String  msg;
    /**
     * 业务数据返回
     */
    private T       data;
    /**
     * 成功/失败
     */
    private Boolean success;
    /**
     * 状态码
     */
    private Integer status;
}
