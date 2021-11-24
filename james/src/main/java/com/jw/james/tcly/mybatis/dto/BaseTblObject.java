package com.jw.james.tcly.mybatis.dto;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * guoyy com.jw.tcly.mybatis.dao
 *
 * @Description: com.jw.tcly.mybatis.dto.BaseTblObject
 * @Author: guoyiyong/james
 * @Date: 2020-07-27 18:47
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class BaseTblObject extends BaseObject {
    /**
     * 自增主键
     */
    private Long id;
    /**
     * TBL_NO
     */
    private String tblNo;
    /**
     * 插入该记录的Schedule ID
     */
    private Long createId;
    /**
     * 逻辑删除该记录的Schedule ID. (默认值0表示有效，即未逻辑删除)
     */
    private Long deleteId;
    private String action;
    private Integer segIndex;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 数据加载时间
     */
    private LocalDateTime dataLoadTime = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTblNo() {
        return tblNo;
    }

    public void setTblNo(String tblNo) {
        this.tblNo = tblNo == null ? null : tblNo.trim();
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getDeleteId() {
        return deleteId;
    }

    public void setDeleteId(Long deleteId) {
        this.deleteId = deleteId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getSegIndex() {
        return segIndex;
    }

    public void setSegIndex(Integer segIndex) {
        this.segIndex = segIndex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getDataLoadTime() {
        return dataLoadTime;
    }

    public void setDataLoadTime(LocalDateTime dataLoadTime) {
        this.dataLoadTime = dataLoadTime;
    }
}
