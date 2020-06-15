package com.jw.demo.disruptor.dsfjob;

import lombok.Data;

import java.util.Map;

/**
 * taxbag-batch-core com.ly.ic.taxbag.batch.core.client.dto
 *
 * @Description: com.ly.ic.taxbag.batch.core.client.dto.JobRespDTO
 * @Author: guoyiyong/james.guo
 * @Date: 2020/5/19 17:59
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
@Data
public class JobRespDTO {
    private String traceId;
    private Boolean success;
    private Map<String, Object> context;
}
