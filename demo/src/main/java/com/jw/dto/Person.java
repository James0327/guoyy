package com.jw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * guoyy com.jw.dto
 *
 * @Description: Person
 * @Author: guoyiyong/james
 * @Date: 2020-01-07 11:25
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Person {
    private int id;
    private String name;
    private int age;
    private byte sex;
    private String phone;
    private String addr;
}
