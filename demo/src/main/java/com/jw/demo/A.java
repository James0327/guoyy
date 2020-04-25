package com.jw.demo;

import com.jw.demo.fsm.Events;
import com.jw.demo.fsm.States;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

import javax.annotation.Resource;

/**
 * guoyy com.jw.demo
 *
 * @Description: com.jw.demo.A
 * @Author: guoyiyong/james
 * @Date: 2020-04-25 23:42
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@SpringBootApplication
public class A implements CommandLineRunner {
    @Resource
    private StateMachine<States, Events> stateMachine;

    public static void main(String[] args) {
        SpringApplication.run(A.class, args);
    }

    @Override
    public void run(String... args) {
        stateMachine.start();

        stateMachine.sendEvent(Events.PAY);
        stateMachine.sendEvent(Events.RECEIVE);

    }
}
