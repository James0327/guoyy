package com.jw.test;

import com.jw.disruptor.dsfjob.JobReqDTO;
import com.jw.disruptor.dsfjob.TcScheduleDsfJobHelper;
import org.junit.jupiter.api.Test;

/**
 * guoyy com.jw.test
 *
 * @Description: com.jw.test.TcScheduleDsfJobHelperTest
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

        for (int i = 0; i < 100; i++) {
            JobReqDTO jobReqDTO = new JobReqDTO();
            jobReqDTO.setTraceId("trace-" + i);
            jobReqDTO.setJobName("job-" + i);
            helper.consume(jobReqDTO);
            Thread.sleep(3000);
        }

    }

}
