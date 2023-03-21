package com.jw.enums;

/**
 * Description: atpco-farerecord-process-parent
 * com.ly.flight.atpco.compare.farerecord.biz.enums.CabinOpenCloseEnum
 *
 * @author guoyiyong/james
 * Date: 2022/9/2 20:05
 * Version: 1.0
 *
 * Copyright (C) 2022 JW All rights reserved.
 */
public enum CabinOpenCloseEnum {
    // 开
    OPEN(1, "开"),
    // 关
    CLOSE(0, "关"),
    // 未知
    UNKOWN(-1, "未知");

    private final int code;
    private final String desc;

    CabinOpenCloseEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
