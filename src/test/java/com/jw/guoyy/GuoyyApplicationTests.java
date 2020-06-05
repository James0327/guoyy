package com.jw.guoyy;

import com.alibaba.fastjson.JSON;
import com.jw.demo.Test1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration
@Slf4j
public class GuoyyApplicationTests {

    @Test
    void contextLoads() {
        Test1 test1 = Test1.getInstance();
        log.info("test1:{}", JSON.toJSONString(test1));
    }

}
