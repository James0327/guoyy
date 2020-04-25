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
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
                .listener(new StateMachineListenerAdapter<States, Events>() {
                    @Override
                    public void transition(Transition<States, Events> transition) {
                        if (transition.getTarget().getId() == States.UNPAID) {
                            log.info("订单创建，待支付");
                            return;
                        }

                        if (transition.getSource().getId() == States.UNPAID
                                && transition.getTarget().getId() == States.WAITING_RECEIVE) {
                            log.info("用户完成支付，待收货");
                            return;
                        }

                        if (transition.getSource().getId() == States.WAITING_RECEIVE
                                && transition.getTarget().getId() == States.DONE) {
                            log.info("用户已收货，订单完成");
                            return;
                        }
                    }
                });
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        // 定义状态机中的状态
        states.withStates()
                // 初始状态
                .initial(States.UNPAID)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions.withExternal()
                .source(States.UNPAID)
                .target(States.WAITING_RECEIVE)
                .event(Events.PAY)
                .and()
                .withExternal()
                .source(States.WAITING_RECEIVE)
                .target(States.DONE)
                .event(Events.RECEIVE)
        ;

    }
}
