package com.jw.james;

import com.alibaba.fastjson.JSON;
import com.jw.hbase.service.HBaseService;
import com.jw.james.fsm.persist.Order;
import com.jw.james.fsm.persist.Status;
import com.jw.james.fsm.turnstile.Events;
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
    private StateMachine<com.jw.james.fsm.Status, com.jw.james.fsm.Events> stateMachine;
    @Resource
    private StateMachineFactory<Status, Events> turnsStateMachineFactory;
    @Resource
    private StateMachine<Status, com.jw.james.fsm.persist.Events> persistStateMachine;
    @Resource
    private StateMachinePersister<Status, com.jw.james.fsm.persist.Events, Order> stateMachinePersister;

    @Test
    void contextLoads() {
        String ret = hBaseService.hello("hello");
        System.out.println("ret: " + ret);

        System.out.println(StringUtils.leftPad("*", 20, "*"));

        stateMachine.start();
        stateMachine.sendEvent(com.jw.james.fsm.Events.PAY);
        stateMachine.sendEvent(com.jw.james.fsm.Events.RECEIVE);
        stateMachine.stop();

        System.out.println(StringUtils.leftPad("*", 30, "*"));
        StateMachine<Status, Events> turnsStateMachine = turnsStateMachineFactory.getStateMachine(UUID.randomUUID());
        turnsStateMachine.start();
        Events[] opers = {
                Events.COIN,
                Events.COIN,
                Events.PUSH,
                Events.PUSH,
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
            persistStateMachine.sendEvent(com.jw.james.fsm.persist.Events.SEND);
            stateMachinePersister.persist(persistStateMachine, order);
            System.out.println("order:" + JSON.toJSONString(order));
            Message<com.jw.james.fsm.persist.Events> message = MessageBuilder
                    .withPayload(com.jw.james.fsm.persist.Events.DELIVER)
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
