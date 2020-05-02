package com.jw.demo.fsm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * guoyy com.jw.demo.fsm
 *
 * @Description: com.jw.demo.fsm.StateMachineConfig
 * @Author: guoyiyong/james
 * @Date: 2020-04-25 23:53
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Slf4j
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<Status, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<Status, Events> config) throws Exception {
        config.withConfiguration().machineId("order")
                .listener(new StateMachineListenerAdapter<Status, Events>() {
                    @Override
                    public void transition(Transition<Status, Events> transition) {
                        if (transition.getTarget().getId() == Status.UNPAID) {
                            log.info("订单创建，待支付");
                            return;
                        }

                        if (transition.getSource().getId() == Status.UNPAID
                                && transition.getTarget().getId() == Status.WAITING_RECEIVE) {
                            log.info("用户完成支付，待收货");
                            return;
                        }

                        if (transition.getSource().getId() == Status.WAITING_RECEIVE
                                && transition.getTarget().getId() == Status.DONE) {
                            log.info("用户已收货，订单完成");
                            return;
                        }
                    }
                });
    }

    @Override
    public void configure(StateMachineStateConfigurer<Status, Events> states) throws Exception {
        // 定义状态机中的状态
        states.withStates()
                // 初始状态
                .initial(Status.UNPAID)
                .states(EnumSet.allOf(Status.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Status, Events> transitions) throws Exception {
        transitions.withExternal()
                .source(Status.UNPAID)
                .target(Status.WAITING_RECEIVE)
                .event(Events.PAY)
                .and()
                .withExternal()
                .source(Status.WAITING_RECEIVE)
                .target(Status.DONE)
                .event(Events.RECEIVE)
        ;

    }
}
