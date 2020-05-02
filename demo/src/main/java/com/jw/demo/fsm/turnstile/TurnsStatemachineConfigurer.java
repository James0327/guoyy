package com.jw.demo.fsm.turnstile;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * guoyy com.jw.demo.fsm.turnstile
 *
 * @Description: com.jw.demo.fsm.turnstile.TurnsStatemachineConfigurer
 * @Author: guoyiyong/james
 * @Date: 2020-05-02 07:36
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Configuration
@EnableStateMachineFactory
public class TurnsStatemachineConfigurer extends EnumStateMachineConfigurerAdapter<Status, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<Status, Events> config) throws Exception {
        config.withConfiguration().machineId("turnstitleStateMachine");
    }

    @Override
    public void configure(StateMachineStateConfigurer<Status, Events> states) throws Exception {
        states.withStates()
                .initial(Status.LOCKED)
                .states(EnumSet.allOf(Status.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Status, Events> transitions) throws Exception {
        transitions.withExternal()
                .source(Status.LOCKED)
                .target(Status.UNLOCKED)
                .event(Events.COIN)
                .and()
                .withExternal()
                .source(Status.UNLOCKED)
                .target(Status.LOCKED)
                .event(Events.PUSH);
    }
}
