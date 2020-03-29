package com.jw.jmh.serialize.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * guoyy com.jw.jmh.serialize.bo
 *
 * @Description: com.jw.jmh.serialize.bo.Person
 * @Author: guoyiyong/james
 * @Date: 2020-03-29 20:45
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Data
public class Person implements Serializable {
    private String name;
    private String addr;
    private byte sex;
    private String phone;
    private int age;
    private String occupational;
}
