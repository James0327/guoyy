package com.jw.james.tcly.hbase.exception;

/**
 * tax_complex_entrance com.ly.ic.taxbag.complex.hbase.exception
 *
 * @Description: com.ly.ic.taxbag.complex.hbase.exception.HBaseBusinessException
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/20 10:40
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
public class HBaseBusinessException extends RuntimeException {
    private String code;
    private String msg;

    public HBaseBusinessException(String code, String msg) {
        super(msg, null, false, false);
        this.code = code;
        this.msg = msg;
    }

    public HBaseBusinessException(String message, Throwable cause) {
        super(message, cause, false, false);
    }
}
