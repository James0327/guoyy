package com.jw.tc.test.proxy.impl;

import com.jw.tc.test.proxy.Person;

/**
 * @Description: test com.tc.test.proxy.impl.SoftwareEngineer
 * @Package: com.tc.test.proxy.impl
 * @ClassName: SoftwareEngineer
 * @Author: james.guo
 * @Date: 2019/10/17 11:39
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class SoftwareEngineer implements Person {
    private String name;

    public SoftwareEngineer(String name) {
        this.name = name;
    }

    @Override
    public void goWorking(String name, String dst) {
        System.out.println(String.format("姓名：%s，去%s工作", name, dst));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
