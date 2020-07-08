package com.jw;

import com.jw.fsm.turnstile.Events;
import com.jw.fsm.turnstile.Status;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import javax.annotation.Resource;

/**
 * springboot 默认扫描的路径，是工程application启动类所在包以及子包下的所有文件
 */
@SpringBootApplication(scanBasePackages = "com.jw", exclude = {DataSourceAutoConfiguration.class})
public class GuoyyApplication implements CommandLineRunner {

    @Resource
    private StateMachineFactory<Status, Events> stateMachineFactory;

    public static void main(String[] args) {
        SpringApplication.run(GuoyyApplication.class, args);
    }

    @Override
    public void run(String... args) {
        StateMachine<Status, Events> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.start();

        stateMachine.sendEvent(Events.COIN);
        stateMachine.sendEvent(Events.PUSH);
        stateMachine.sendEvent(Events.COIN);
        stateMachine.sendEvent(Events.PUSH);

        stateMachine.stop();
    }

}
