package com.jw.james.bean;

import com.github.tobato.fastdfs.FdfsClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * guoyy com.jw.tcly.basedata
 *
 * @Description: com.jw.bean.BeanConfiguration
 * @Author: guoyiyong/james
 * @Date: 2020-12-29 14:32
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Slf4j
@Configuration
@PropertySource({"classpath:tcbase.properties"})
@Import(FdfsClientConfig.class)
public class BeanConfiguration {

    public BeanConfiguration() {
        log.info("BeanConfiguration ...");
    }

}
