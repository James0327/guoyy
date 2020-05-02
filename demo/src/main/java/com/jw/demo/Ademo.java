package com.jw.demo;

import com.jw.demo.fsm.turnstile.Events;
import com.jw.demo.fsm.turnstile.Status;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import javax.annotation.Resource;

/**
 * guoyy com.jw.demo
 *
 * @Description: com.jw.demo.Ademo
 * @Author: guoyiyong/james
 * @Date: 2020-04-25 23:42
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@SpringBootApplication
public class Ademo implements CommandLineRunner {
    @Resource
    private StateMachineFactory<Status, Events> stateMachineFactory;

    public static void main(String[] args) {
        SpringApplication.run(Ademo.class, args);
    }

    @Override
    public void run(String... args) {
        StateMachine<Status, Events> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.start();

        stateMachine.sendEvent(com.jw.demo.fsm.turnstile.Events.COIN);
        stateMachine.sendEvent(com.jw.demo.fsm.turnstile.Events.PUSH);
        stateMachine.sendEvent(com.jw.demo.fsm.turnstile.Events.COIN);
        stateMachine.sendEvent(com.jw.demo.fsm.turnstile.Events.PUSH);

        stateMachine.stop();
    }
}
