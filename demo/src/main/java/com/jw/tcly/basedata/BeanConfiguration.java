package com.jw.tcly.basedata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * guoyy com.jw.tcly.basedata
 *
 * @Description: com.jw.tcly.basedata.BeanConfiguration
 * @Author: guoyiyong/james
 * @Date: 2020-12-29 14:32
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Slf4j
@Configuration
@PropertySource({"classpath:tcbase.properties"})
@ComponentScan(value = "com.honghu.flight.basedata.client")
public class BeanConfiguration {

    public BeanConfiguration() {
        log.info("BeanConfiguration ...");
    }

}
