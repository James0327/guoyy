package com.jw.james.dubbo.impl;

import com.jw.james.dubbo.InjectExt;
import com.jw.james.dubbo.SimpleExt;
import org.apache.dubbo.common.extension.DisableInject;

/**
 * guoyy com.jw.dubbo.impl
 *
 * @Description: com.jw.dubbo.impl.InjectExtImpl
 * @Author: guoyiyong/james
 * @Date: 2020-11-28 20:28
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class InjectExtImpl implements InjectExt {
    private SimpleExt simpleExt;
    private SimpleExt simpleExt1;
    private Object genericType;

    public SimpleExt getSimpleExt() {
        return simpleExt;
    }

    @DisableInject
    public void setSimpleExt(SimpleExt simpleExt) {
        this.simpleExt = simpleExt;
    }

    public SimpleExt getSimpleExt1() {
        return simpleExt1;
    }

    public void setSimpleExt1(SimpleExt simpleExt) {
        this.simpleExt1 = simpleExt;
    }

    public Object getGenericType() {
        return genericType;
    }

    public void setGenericType(Object genericType) {
        this.genericType = genericType;
    }

    @Override
    public String echo(String msg) {
        return String.format("InjectExtImpl, msg: %s.", msg);
    }
}
