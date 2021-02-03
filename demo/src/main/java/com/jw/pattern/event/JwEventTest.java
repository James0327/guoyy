package com.jw.pattern.event;

import java.util.List;

/**
 * guoyy com.jw.pattern.event
 *
 * @Description: com.jw.pattern.event.JwEventTest
 * @Author: guoyiyong/james
 * @Date: 2021-01-29 11:48
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class JwEventTest {

    public static void main(String[] args) {

        JwEventSource jwEventSource = new JwEventSource();
        jwEventSource.addEventListener(new JwEventListener.EventOneListener());
        jwEventSource.addEventListener(new JwEventListener.EventTwoListener());

        Object obj1 = new Object();
        Object obj2 = new Object();

        List<Object> obj2Ret = jwEventSource.action(new JwEventObject.EventTwo(obj2));
        System.out.println("obj2Ret: " + obj2Ret);

        List<Object> obj1Ret = jwEventSource.action(new JwEventObject.EventOne(obj1));
        System.out.println("obj1Ret: " + obj1Ret);

    }

}
