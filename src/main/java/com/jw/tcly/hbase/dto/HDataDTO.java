package com.jw.tcly.hbase.dto;

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
    @Setter
    @Getter
    public static class ColumnFamilie {
        @Setter
        @Getter
        public static class Column {
            private String qualifier;
            private byte[] value;
        }

        private String familyName;
        private List<Column> columns;
    }

    private String rowKey;
    private List<ColumnFamilie> columnFamilies;
}
