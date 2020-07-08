package com.jw;

import com.alibaba.fastjson.JSON;
import com.jw.fsm.persist.Events;
import com.jw.fsm.persist.Order;
import com.jw.fsm.persist.Status;
import com.jw.hbase.service.HBaseService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;

import javax.annotation.Resource;
import java.util.UUID;

@SpringBootTest(classes = GuoyyApplication.class)
public class DemoApplicationTests {
    @Resource
    private HBaseService hBaseService;
    @Resource
    private StateMachine<com.jw.fsm.Status, com.jw.fsm.Events> stateMachine;
    @Resource
    private StateMachineFactory<com.jw.fsm.turnstile.Status, com.jw.fsm.turnstile.Events> turnsStateMachineFactory;
    @Resource
    private StateMachine<Status, Events> persistStateMachine;
    @Resource
    private StateMachinePersister<Status, Events, Order> stateMachinePersister;

    @Test
    void contextLoads() {
        String ret = hBaseService.hello("hello");
        System.out.println("ret: " + ret);

        System.out.println(StringUtils.leftPad("*", 20, "*"));

        stateMachine.start();
        stateMachine.sendEvent(com.jw.fsm.Events.PAY);
        stateMachine.sendEvent(com.jw.fsm.Events.RECEIVE);
        stateMachine.stop();

        System.out.println(StringUtils.leftPad("*", 30, "*"));
        StateMachine<com.jw.fsm.turnstile.Status, com.jw.fsm.turnstile.Events>
                turnsStateMachine = turnsStateMachineFactory.getStateMachine(UUID.randomUUID());
        turnsStateMachine.start();
        com.jw.fsm.turnstile.Events[] opers = {
                com.jw.fsm.turnstile.Events.COIN,
                com.jw.fsm.turnstile.Events.COIN,
                com.jw.fsm.turnstile.Events.PUSH,
                com.jw.fsm.turnstile.Events.PUSH,
        };
        for (int i = 0, len = opers.length; i < len; i++) {
            turnsStateMachine.sendEvent(opers[i]);
        }
        turnsStateMachine.stop();

        System.out.println(StringUtils.leftPad("*", 30, "*"));
        // 在BizStateMachinePersist的restore过程中，绑定turnstileStateMachine状态机相关事件监听
        try {
            Order order = new Order();
            order.setId(1);
            order.setStatus(Status.PROCESSING);
            stateMachinePersister.restore(persistStateMachine, order);
            Status status = persistStateMachine.getState().getId();
            System.out.println("restore status: " + status);
            persistStateMachine.sendEvent(Events.SEND);
            stateMachinePersister.persist(persistStateMachine, order);
            System.out.println("order:" + JSON.toJSONString(order));
            Message<Events> message = MessageBuilder
                    .withPayload(Events.DELIVER)
                    .setHeader("bizId", 1).build();
            persistStateMachine.sendEvent(message);
            stateMachinePersister.persist(persistStateMachine, order);
            System.out.println("order:" + JSON.toJSONString(order));

            persistStateMachine.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
