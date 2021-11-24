package com.jw.james;

import com.jw.james.fdfs.client.FastDfsClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;

/**
 * Description: guoyy
 * com.jw.FastDfsTest
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/11/23 20:12
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@SpringBootTest(classes = GuoyyApplication.class)
public class FastDfsTest {
    @Resource
    private FastDfsClient fastDfsClient;

    @Test
    public void test() {
        String path = getClass().getClassLoader().getResource("huashanglunjian.jpeg").getPath();
        System.out.println("path: " + path);
        File file = new File(path);

        String ret = fastDfsClient.uploadFile(file);

        System.out.println("ret: " + ret);
    }

}
