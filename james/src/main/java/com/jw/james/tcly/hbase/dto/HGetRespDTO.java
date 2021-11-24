package com.jw.james.tcly.hbase.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * tax_complex_entrance com.ly.ic.tax.complex.entrance.hbase
 *
 * @Description: com.ly.ic.tax.complex.entrance.hbase.dto.HGetRespDTO
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/19 16:02
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
@Setter
@Getter
public class HGetRespDTO {
    private String code;
    private String message;
    private HDataDTO result;
}
