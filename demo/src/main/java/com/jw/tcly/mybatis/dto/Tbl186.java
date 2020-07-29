package com.jw.tcly.mybatis.dto;

import org.apache.commons.lang3.StringUtils;

/**
 * guoyy com.jw.tcly.mybatis.dao
 *
 * @Description: com.jw.tcly.mybatis.dto.Tbl186
 * @Author: guoyiyong/james
 * @Date: 2020-07-27 18:49
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class Tbl186 extends BaseTblObject {
    private Integer segs;
    private String mcOcFlt1Flt2;

    public Integer getSegs() {
        return segs;
    }

    public void setSegs(Integer segs) {
        this.segs = segs;
    }

    public String getMcOcFlt1Flt2() {
        return mcOcFlt1Flt2;
    }

    public void setMcOcFlt1Flt2(String mcOcFlt1Flt2) {
        this.mcOcFlt1Flt2 = StringUtils.trimToEmpty(mcOcFlt1Flt2);
    }
}
