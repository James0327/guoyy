package com.jw.james.exception.impl;

import com.jw.james.exception.ExceptionHandler;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Service;

/**
 * Description: guoyy
 * com.jw.exception.impl.ExceptionHandlerImpl
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/3 15:46
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Service
public class ExceptionHandlerImpl implements ExceptionHandler {

    @Override
    public Object exec(Object[] request) {
        System.out.println("request: " + ToStringBuilder.reflectionToString(request));

        return null;
    }
}
