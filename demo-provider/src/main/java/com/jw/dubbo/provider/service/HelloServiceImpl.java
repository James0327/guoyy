package com.jw.dubbo.provider.service;

import com.jw.dubbo.api.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * guoyy com.jw.demoprovider
 *
 * @Description: HelloServiceImpl
 * @Author: guoyiyong/james
 * @Date: 2020-08-03 21:28
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@DubboService
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

}
