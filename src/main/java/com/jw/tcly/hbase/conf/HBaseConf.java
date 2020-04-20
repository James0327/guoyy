package com.jw.tcly.hbase.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * tax_complex_entrance com.ly.ic.taxbag.complex.hbase.conf
 *
 * @Description: com.ly.ic.taxbag.complex.hbase.conf.HBaseConf
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/20 9:43
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
@Data
@ConfigurationProperties(prefix = "taxbag.hbase")
public class HBaseConf {
    private String token;
    private String contentType;
    private String domain;
    private String appUk;
    private String familyName;
    private String tableName;
}
