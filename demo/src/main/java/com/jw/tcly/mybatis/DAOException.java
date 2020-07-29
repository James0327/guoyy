package com.jw.tcly.mybatis;

/**
 * guoyy com.jw.tcly.mybatis
 *
 * @Description: com.jw.tcly.mybatis.DAOException
 * @Author: guoyiyong/james
 * @Date: 2020-07-27 18:53
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class DAOException extends RuntimeException {
    private static final long serialVersionUID = -6981227132975111900L;

    public DAOException(String message) {
        super(message);
    }
}
