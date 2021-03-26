package com.jw.t;

import lombok.Data;

/**
 * Description: guoyy
 * com.jw.t.PccFlowMonitorDTO
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/25 23:46
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Data
public class PccFlowMonitorDTO {

    /**
     * 查询量
     */
    private Integer shopping;

    /**
     * 查询量
     */
    private Integer generalmsg;

    /**
     * 航段量
     */
    private Integer booking;

    /**
     * 退票量
     */
    private Integer refund;

    public PccFlowMonitorDTO() {
        this.shopping = 0;
        this.generalmsg = 0;
        this.booking = 0;
        this.refund = 0;
    }

    public PccFlowMonitorDTO incrementShopping(Integer shopping) {
        this.shopping += shopping;
        return this;
    }

    public PccFlowMonitorDTO incrementGeneralmsg(Integer generalmsg) {
        this.generalmsg += generalmsg;
        return this;
    }

    public PccFlowMonitorDTO incrementBooking(Integer booking) {
        this.booking += booking;
        return this;
    }

    public PccFlowMonitorDTO incrementRefund(Integer refund) {
        this.refund += refund;
        return this;
    }
}
