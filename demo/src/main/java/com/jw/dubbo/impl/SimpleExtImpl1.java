package com.jw.dubbo.impl;

import com.jw.dubbo.SimpleExt;
import org.apache.dubbo.common.URL;

/**
 * guoyy com.jw.dubbo.impl
 *
 * @Description: com.jw.dubbo.impl.SimpleExtImpl1
 * @Author: guoyiyong/james
 * @Date: 2020-11-26 23:30
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class SimpleExtImpl1 implements SimpleExt {

    @Override
    public String echo(URL url, String s) {
        return "Ext1Impl1-echo";
    }

    @Override
    public String yell(URL url, String s) {
        return "Ext1Impl1-yell";
    }

    @Override
    public String bang(URL url, String s) {
        return "Ext1Impl1-bang";
    }
}
