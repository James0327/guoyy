package com.jw.james.json;

import lombok.Data;

/**
 * Description: guoyy
 * com.jw.json.Foo1
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/16 10:59
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Data
public class Foo<T1, T2> {
    private T1 t1;
    private T2 t2;
}
