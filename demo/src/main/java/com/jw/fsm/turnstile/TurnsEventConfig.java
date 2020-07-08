package com.jw.fsm.turnstile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * guoyy com.jw.demo.fsm.turnstile
 *
 * @Description: TurnsEventConfig
 * @Author: guoyiyong/james
 * @Date: 2020-05-02 07:42
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Slf4j
@WithStateMachine(id = "turnstitleStateMachine")
public class TurnsEventConfig {

    @OnTransition(source = "LOCKED")
    public void fromLocked() {
        log.info("from LOCKED");
    }

    @OnTransition(source = "UNLOCKED")
    public void fromUnlocked() {
        log.info("from UNLOCKED");
    }

    @OnTransition(source = "LOCKED", target = "UNLOCKED")
    public void coin() {
        log.info("coin locked->unlocked");
    }

    @OnTransition(source = "UNLOCKED", target = "LOCKED")
    public void push() {
        log.info("push unlocked->locked");
    }

}
