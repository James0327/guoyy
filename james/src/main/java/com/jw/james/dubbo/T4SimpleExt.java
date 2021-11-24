package com.jw.james.dubbo;

import com.google.common.collect.Maps;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * guoyy com.jw.dubbo
 *
 * @Description: com.jw.dubbo.T4SimpleExt
 * @Author: guoyiyong/james
 * @Date: 2020-11-26 23:25
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class T4SimpleExt {

    public static void main(String[] args) {

        SimpleExt simpleExt = ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();

        try {
            // 在没有url参数的时候，抛出异常
            simpleExt.echo(null, "haha");
        } catch (IllegalArgumentException e) {
            assertEquals("url == null", e.getMessage());
        }

        Map<String, String> map = Maps.newHashMap();
        URL url = new URL("p1", "1.2.3.4", 1010, "path1", map);
        String echo = simpleExt.echo(url, "haha");

        // 由于SimpleExt在定义时指定默认的自适应类（@SPI("impl1")）, 于是即使没有显式指明自适应的类，Dubbo也会为我们生成SimpleExtImpl1。
        assertEquals("Ext1Impl1-echo", echo);

        Map<String, String> map2 = Maps.newHashMap();
        map2.put("simple.ext", "impl2");
        URL url2 = new URL("p1", "1.2.3.4", 1010, "path1", map2);
        String echo2 = simpleExt.echo(url2, "haha");
        assertEquals("Ext1Impl2-echo", echo2);

        // 显式指明了要实现的自适应类impl2，于是最终生成的是SimpleExtImpl2.
        // yell 方法自定义了key1，key2，于是就不能再使用"simple.ext"作为key了。
        Map<String, String> map3 = Maps.newHashMap();
        map3.put("key2", "impl2");
        URL url3 = new URL("p1", "1.2.3.4", 1010, "path1", map3);
        String echo3 = simpleExt.yell(url3, "haha");
        assertEquals("Ext1Impl2-yell", echo3);

        // note: URL is value's type
        url3 = url3.addParameter("key2", "impl3");
        echo3 = simpleExt.yell(url3, "haha");
        assertEquals("Ext1Impl3-yell", echo3);
    }

}
