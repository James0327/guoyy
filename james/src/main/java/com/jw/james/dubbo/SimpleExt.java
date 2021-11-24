package com.jw.james.dubbo;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * guoyy com.jw.dubbo
 *
 * @Description: com.jw.dubbo.SimpleExt
 * @Author: guoyiyong/james
 * @Date: 2020-11-26 23:17
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@SPI("impl1")
public interface SimpleExt {

    @Adaptive
    String echo(URL url, String s);

    @Adaptive({"key1", "key2"})
    String yell(URL url, String s);

    String bang(URL url, String s);
}
