package com.jw.james.exception;

import org.springframework.stereotype.Service;

/**
 * Description: guoyy
 * com.jw.exception.Foo
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/3 10:41
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Service
public class Foo {

    @TaxbagException(throwable = Exception.class, handler = ExceptionHandler.class)
    //    @Retryable(value = Exception.class, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    public void fire() {
        System.out.println("fire ... " + System.currentTimeMillis());
        throw new RuntimeException();
    }

    @TaxbagException(throwable = Exception.class, handler = ExceptionHandler.class)
    public void fire2() throws Exception {
        System.out.println("fire ... " + System.currentTimeMillis());
        throw new Exception();
    }

}
