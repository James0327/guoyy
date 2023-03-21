package com.jw.james.tcly.proxy;

/**
 * @Description: test com.tcly.test.proxy.Person
 * @Package: com.tcly.test.proxy
 * @ClassName: Person
 * @Author: james.guo
 * @Date: 2019/10/17 11:37
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public interface Person {
    void goWorking(String name, String dst);

    String getName();

    void setName(String name);
}
