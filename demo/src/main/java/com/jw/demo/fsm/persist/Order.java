package com.jw.demo.fsm.persist;

import lombok.Data;

/**
 * guoyy com.jw.demo.fsm.persist
 *
 * @Description: com.jw.demo.fsm.persist.Order
 * @Author: guoyiyong/james
 * @Date: 2020-05-02 14:40
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Data
public class Order {
    private int id;
    private Status status;
}
