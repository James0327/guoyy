package com.jw.algorithm.bloomfilters;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * guoyy com.jw.algorithm.bloomfilters
 *
 * @Description: com.jw.algorithm.bloomfilters.RedisConf
 * @Author: guoyiyong/james
 * @Date: 2020-01-05 18:22
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConf {
    // spring.redis.client-name
    private String clientName;

    // spring.redis.cluster
    private RedisClusterConf cluster;
}
