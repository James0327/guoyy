package com.jw.james.disruptor;

import com.jw.james.disruptor.dsfjob.JobReqDTO;
import com.jw.james.disruptor.dsfjob.TcScheduleDsfJobHelper;
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
public class TcScheduleDsfJobHelperTest {

    @Test
    public void test() throws InterruptedException {
        TcScheduleDsfJobHelper helper = new TcScheduleDsfJobHelper();

        for (int i = 0; i < 30; i++) {
            JobReqDTO jobReqDTO = new JobReqDTO();
            jobReqDTO.setTraceId("trace-" + i);
            jobReqDTO.setJobName("job-" + i);
            helper.consume(jobReqDTO);
            Thread.sleep(1000);
        }

    }

}
