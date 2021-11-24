package com.jw.james.algorithm.bloomfilters;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * guoyy com.jw.algorithm.bloomfilters
 *
 * @Description: RedisClusterConf
 * @Author: guoyiyong/james
 * @Date: 2020-01-06 22:07
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterConf {
    private String[] nodes;
    private int maxRedirects;
}
