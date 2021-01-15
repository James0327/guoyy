package com.jw.dubbo;

import org.apache.dubbo.common.extension.SPI;

/**
 * guoyy com.jw.dubbo
 *
 * @Description: com.jw.dubbo.InjectExt
 * @Author: guoyiyong/james
 * @Date: 2020-11-28 20:26
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@SPI("injection")
public interface InjectExt {
    String echo(String msg);
}
