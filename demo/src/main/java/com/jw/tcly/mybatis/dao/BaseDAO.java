package com.jw.tcly.mybatis.dao;

import com.jw.tcly.mybatis.DAOException;
import com.jw.tcly.mybatis.dto.BaseObject;

import java.util.List;

/**
 * guoyy com.jw.tcly.mybatis.dao
 *
 * @Description: com.jw.tcly.mybatis.dao.BaseDAO
 * @Author: guoyiyong/james
 * @Date: 2020-07-27 18:52
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public interface BaseDAO<Obj extends BaseObject> {
    /**
     * 插入数据
     */
    Long insert(Obj object) throws DAOException;

    /**
     * 批量插入
     */
    void batchInsert(List<Obj> list) throws DAOException;

    /**
     * 更新记录
     *
     * @return 受影响的行数
     */
    Integer update(Obj object) throws DAOException;

    /**
     * 批量更新
     */
    void batchUpdate(final List<Obj> objLst) throws DAOException;

}
