package com.jw.demo;

import com.jw.hbase.service.HBaseService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {
    @Resource
    private HBaseService hBaseService;

    @Test
    void contextLoads() {
        String ret = hBaseService.hello("hello");
        System.out.println("ret: " + ret);
    }

}
