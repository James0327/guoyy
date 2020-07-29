package com.jw.tcly.mybatis.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * guoyy com.jw.tcly.mybatis.dao
 *
 * @Description: com.jw.tcly.mybatis.dto.BaseObject
 * @Author: guoyiyong/james
 * @Date: 2020-07-27 18:48
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class BaseObject implements Serializable {
    private static final long serialVersionUID = -265710721622940023L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
