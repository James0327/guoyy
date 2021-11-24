package com.jw.james.hbase.conf;

import com.jw.james.hbase.service.HBaseService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * guoyy com.jw.hbase.conf
 *
 * @Description: com.jw.hbase.conf.HBaseAutoConfiguration
 * @Author: guoyiyong/james
 * @Date: 2020-04-25 22:44
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(HBaseService.class)
@ConditionalOnProperty(value = "hbase.enable")
@EnableConfigurationProperties(HBaseConf.class)
public class HBaseAutoConfiguration {
    @Resource
    private HBaseConf hBaseConf;

    @Bean
    @ConditionalOnMissingBean
    public HBaseService setup() {
        return new HBaseService(hBaseConf);
    }

}
