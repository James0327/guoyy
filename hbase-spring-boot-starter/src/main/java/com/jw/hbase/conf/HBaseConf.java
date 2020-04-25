package com.jw.hbase.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * guoyy com.jw.hbase
 *
 * @Description: com.jw.hbase.conf.HBaseConf
 * @Author: guoyiyong/james
 * @Date: 2020-04-25 22:36
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "hbase")
public class HBaseConf {
    private String tableName;
    private String columnFamily;
    private String token;
    private String zookeeper;
    private int port;
}
