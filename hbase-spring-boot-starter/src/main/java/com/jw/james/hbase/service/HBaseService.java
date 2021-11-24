package com.jw.james.hbase.service;

import com.alibaba.fastjson.JSON;
import com.jw.james.hbase.conf.HBaseConf;

/**
 * guoyy com.jw.hbase.service
 *
 * @Description: com.jw.hbase.service.HBaseService
 * @Author: guoyiyong/james
 * @Date: 2020-04-25 22:44
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class HBaseService {
    private HBaseConf hBaseConf;

    public HBaseService(HBaseConf hBaseConf) {
        this.hBaseConf = hBaseConf;
    }

    public String hello(String in) {
        System.out.println("in:" + in);
        return JSON.toJSONString(hBaseConf);
    }

}
