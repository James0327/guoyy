package com.jw.dubbo;

import com.google.common.collect.Maps;
import com.jw.dubbo.impl.InjectExtImpl;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.jupiter.api.Assertions;

import java.util.Map;

/**
 * guoyy com.jw.dubbo
 *
 * @Description: com.jw.dubbo.T4InjectExt
 * @Author: guoyiyong/james
 * @Date: 2020-11-28 20:32
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class T4InjectExt {

    public static void main(String[] args) {
        InjectExt injection = ExtensionLoader.getExtensionLoader(InjectExt.class).getExtension("injection");

        InjectExtImpl injectExtImpl = (InjectExtImpl)injection;

        SimpleExt simpleExt1 = injectExtImpl.getSimpleExt1();
        Assertions.assertNotNull(simpleExt1);
        Assertions.assertNull(injectExtImpl.getSimpleExt());
        Assertions.assertNull(injectExtImpl.getGenericType());

        Map<String, String> map = Maps.newHashMap();
        URL url = new URL("p1", "1.2.3.4", 1010, "path1", map);
        String echo = simpleExt1.echo(url, "haha");
        System.out.println("echo: " + echo);
    }

}
