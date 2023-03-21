package com.jw.james.tcly.hbase.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * tax_complex_entrance com.ly.ic.tax.complex.entrance.hbase
 *
 * @Description: com.ly.ic.tax.complex.entrance.hbase.dto.HDataDTO
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/19 16:01
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
@Setter
@Getter
public class HDataDTO {
    private String rowKey;
    private List<ColumnFamilie> columnFamilies;

    @Setter
    @Getter
    public static class ColumnFamilie {
        private String familyName;
        private List<Column> columns;

        @Setter
        @Getter
        public static class Column {
            private String qualifier;
            private byte[] value;
        }
    }
}
