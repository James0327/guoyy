package com.jw.james.fsm.persist;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import javax.annotation.Resource;
import java.util.EnumSet;

/**
 * guoyy com.jw.demo.fsm.persist
 *
 * @Description: PersistStateMachineConfig
 * @Author: guoyiyong/james
 * @Date: 2020-05-02 14:43
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Configuration
@EnableStateMachine
@Slf4j
public class PersistStateMachineConfig extends EnumStateMachineConfigurerAdapter<Status, Events> {

    @Resource
    private BizStateMachinePersist bizStateMachinePersist;

    @Bean
    public StateMachinePersister<Status, Events, Order> init() {
        return new DefaultStateMachinePersister<>(bizStateMachinePersist);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<Status, Events> config) throws Exception {
        config.withConfiguration().machineId("persist");
    }

    @Override
    public void configure(StateMachineStateConfigurer<Status, Events> states) throws Exception {
        states.withStates().initial(Status.PLACED).states(EnumSet.allOf(Status.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Status, Events> transitions) throws Exception {
        transitions.withExternal()
                .source(Status.PLACED).target(Status.PROCESSING).event(Events.PROCESS)
                .action(context -> log.info("{}, {}", context.getStage(), "PLACED->PROCESSING"))
                .and().withExternal()
                .source(Status.PROCESSING).target(Status.SENT).event(Events.SEND)
                .action(context -> log.info("{}, {}", context.getStage(), "PROCESSING->SENT"))
                .and().withExternal()
                .source(Status.SENT).target(Status.DELIVERED).event(Events.DELIVER)
                .action(context -> {
                    log.info("{}, {}", context.getStage(), "SENT->DELIVER");
                    Message<Events> message = context.getMessage();
                    log.info("message:{}", JSON.toJSONString(message));
                    Object bizId = context.getMessageHeader("bizId");
                    log.info("bizId: " + bizId);
                });
    }
}
