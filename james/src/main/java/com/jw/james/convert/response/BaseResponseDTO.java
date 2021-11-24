package com.jw.james.convert.response;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Description: trade-postsalecore-parent
 * com.ly.travel.mall.trade.postsalecore.facade.model.response.BaseResponseDTO
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/10/17 15:11
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class BaseResponseDTO<T> implements Serializable {
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("msg", msg).append("data", data).append("success", success).append("status", status).toString();
    }
}
