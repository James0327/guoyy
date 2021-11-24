package com.jw.james.algorithm.bloomfilters;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * guoyy com.jw.algorithm.bloomfilters
 *
 * @Description: RedisConf
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
    private String host;
    private int port;
    private int timeout;
    // spring.redis.client-name
    private String clientName;
    // spring.redis.cluster
    private RedisClusterConf cluster;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate();
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        GenericFastJsonRedisSerializer valueSerializer = new GenericFastJsonRedisSerializer();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(keySerializer);
        template.setValueSerializer(valueSerializer);
        template.setHashKeySerializer(keySerializer);
        template.setHashValueSerializer(valueSerializer);
        return template;
    }

}
