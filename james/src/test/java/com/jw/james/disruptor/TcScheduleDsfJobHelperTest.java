package com.jw.james.disruptor;

import com.alibaba.fastjson.JSON;
import com.jw.james.disruptor.dsfjob.JobReqDTO;
import com.jw.james.disruptor.dsfjob.TcScheduleDsfJobHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * guoyy com.jw.test
 *
 * @Description: com.jw.disruptor.TcScheduleDsfJobHelperTest
 * @Author: guoyiyong/james
 * @Date: 2020-07-13 18:55
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Slf4j
public class TcScheduleDsfJobHelperTest {

    @Test
    public void test() throws InterruptedException {
        TcScheduleDsfJobHelper helper = new TcScheduleDsfJobHelper();

        for (int i = 0; i < 30; i++) {
            JobReqDTO jobReqDTO = new JobReqDTO();
            jobReqDTO.setTraceId("trace-" + i);
            jobReqDTO.setJobName("job-" + i);
            helper.consume(jobReqDTO);
            log.info("consume: {}", JSON.toJSONString(jobReqDTO));
            Thread.sleep(1000);
        }

    }

}
