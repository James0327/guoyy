package com.jw.fsm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * guoyy com.jw.demo.fsm
 *
 * @Description: com.jw.demo.fsm.TurnsEventConfig
 * @Author: guoyiyong/james
 * @Date: 2020-04-26 00:39
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@WithStateMachine(id = "order")
public class EventConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @OnTransition(target = "UNPAID")
    public void create() {
        logger.info("~~~ 订单创建，待支付");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_RECEIVE")
    public void pay() {
        logger.info("~~~ 用户完成支付，待收货");
    }

    @OnTransition(source = "WAITING_RECEIVE", target = "DONE")
    public void receive() {
        logger.info("~~~ 用户已收货，订单完成");
    }

}