package com.jw.tcly.mybatis.dao;

import com.jw.tcly.mybatis.dto.Tbl186;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * guoyy com.jw.tcly.mybatis.dao
 *
 * @Description: com.jw.tcly.mybatis.dao.Tbl186DAO
 * @Author: guoyiyong/james
 * @Date: 2020-07-27 18:51
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public interface Tbl186DAO extends BaseDAO<Tbl186> {
    int getDataCount(String tblNo);

    List<Tbl186> getEffectData();

    /**
     * 按日期过来有效数据
     *
     * @param date
     * @return
     */
    List<Tbl186> getEffectDataV2(@Param("date") Date date);

    Tbl186 queryById(@Param("id") Long id);
}
