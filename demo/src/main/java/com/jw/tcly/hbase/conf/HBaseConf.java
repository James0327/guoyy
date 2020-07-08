package com.jw.tcly.hbase.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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
@Component("HBaseConf")
@PropertySource("classpath:taxbag_hbase_config.properties")
public class HBaseConf {
    @Value("${taxbag.hbase.token}")
    private String token;
    @Value("${taxbag.hbase.content-type}")
    private String contentType;
    @Value("${taxbag.hbase.domain}")
    private String domain;
    @Value("${taxbag.hbase.app-uk}")
    private String appUk;
    @Value("${taxbag.hbase.family-name}")
    private String familyName;
    @Value("${taxbag.hbase.table-name}")
    private String tableName;
}
