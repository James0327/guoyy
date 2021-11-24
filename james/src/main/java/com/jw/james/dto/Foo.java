package com.jw.james.dto;

import lombok.Data;

import java.util.Map;

/**
 * guoyy com.jw
 *
 * @Description: com.jw.Foo
 * @Author: guoyiyong/james
 * @Date: 2019-12-02 11:34
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
@Data
public class Foo {

    private String traceId;

    private Long id;

    private String desc;

    private String remark;

    private Map<String, Object> context;
}
