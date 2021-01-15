package com.jw.dubbo;

import com.jw.dubbo.impl.InjectExtImpl;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.jupiter.api.Assertions;

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

        Assertions.assertNotNull(injectExtImpl.getSimpleExt());
        Assertions.assertNull(injectExtImpl.getSimpleExt1());
        Assertions.assertNull(injectExtImpl.getGenericType());
    }

}
