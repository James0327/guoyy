package com.jw.dubbo.consumer.controller;

import com.jw.dubbo.api.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * guoyy com.jw.dubbo.consumer.controller
 *
 * @Description: com.jw.dubbo.consumer.controller.DemoController
 * @Author: guoyiyong/james
 * @Date: 2020-08-03 21:42
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@RestController
public class DemoController {

    @DubboReference
    private HelloService helloService;

    @GetMapping
    private String fire(@RequestParam("content") String content) {
        String ret = helloService.sayHello(content);
        System.out.println("ret: " + ret);

        return "OK";
    }

}
