package com.jw.j;

import com.jw.TestApplication;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description: guoyy
 * com.jw.j.Test1
 *
 * @author guoyiyong/james
 * Date: 2022/9/15 15:57
 * Version: 1.0
 *
 * Copyright (C) 2022 JW All rights reserved.
 */
@SpringBootTest(classes = TestApplication.class)
public class Test1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test1.class);

    @Test
    public void test1() {
        LOGGER.info("test1");

    }

}
