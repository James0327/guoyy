package com.jw.aspect;

import com.jw.dto.Foo;
import org.springframework.stereotype.Service;

/**
 * Description: guoyy
 * com.jw.aspect.ServerB
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/28 16:02
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Service
public class ServerB {

    @Monitor(level = 1)
    public void method2(Foo foo) {
        System.out.println("running is here [ServerB#method2] request:" + foo);
    }

}
