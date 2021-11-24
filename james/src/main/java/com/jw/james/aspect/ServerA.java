package com.jw.james.aspect;

import com.jw.james.dto.Foo;
import org.springframework.stereotype.Service;

/**
 * Description: guoyy
 * com.jw.aspect.ServerA
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/28 15:26
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Service
public class ServerA {

    @Monitor(level = 1)
    public void method1(Foo foo) {
        System.out.println("running is here [ServerA#method1] request:" + foo);
    }

}
