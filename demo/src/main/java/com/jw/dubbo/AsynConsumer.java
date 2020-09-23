package com.jw.dubbo;

import com.jw.dubbo.api.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;

/**
 * guoyy com.jw.dubbo
 *
 * @Description: com.jw.dubbo.AsynConsumer
 * @Author: guoyiyong/james
 * @Date: 2020-09-21 00:17
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class AsynConsumer {

    public static void main(String[] args) {
        ReferenceConfig<HelloService> config = new ReferenceConfig<>();
        config.setApplication(new ApplicationConfig(""));


    }

}
