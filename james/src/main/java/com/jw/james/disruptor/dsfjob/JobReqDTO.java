package com.jw.james.disruptor.dsfjob;

import lombok.Data;

import java.util.Map;

/**
 * taxbag-batch-core com.ly.ic.taxbag.batch.core.client.dto
 *
 * @Description: com.ly.ic.taxbag.batch.core.client.dto.JobReqDTO
 * @Author: guoyiyong/james.guo
 * @Date: 2020/5/19 18:00
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
@Data
public class JobReqDTO {
    private String traceId;
    private String jobName;
    private Map<String, Object> context;
}
