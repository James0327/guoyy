package com.jw.fsm.persist;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

/**
 * guoyy com.jw.demo.fsm.persist
 *
 * @Description: BizStateMachinePersist
 * @Author: guoyiyong/james
 * @Date: 2020-05-02 16:50
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Component
@Slf4j
public class BizStateMachinePersist implements StateMachinePersist<Status, Events, Order> {

    @Override
    public void write(StateMachineContext<Status, Events> context, Order order) {
        log.info("order to save:{}, context:{}", JSON.toJSONString(order), JSON.toJSONString(context));
        order.setStatus(context.getState());
    }

    @Override
    public StateMachineContext<Status, Events> read(Order order) {
        // 状态机的初识状态与配置中定义的一致
        return new DefaultStateMachineContext<>(order == null ? Status.PLACED : order.getStatus(),
                null, null, null, null, "persist");
    }
}
