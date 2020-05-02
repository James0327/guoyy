package com.jw.guoyy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * springboot 默认扫描的路径，是工程application启动类所在包以及子包下的所有文件
 */
@SpringBootApplication(scanBasePackages = "com.jw", exclude = {DataSourceAutoConfiguration.class})
public class GuoyyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuoyyApplication.class, args);
    }

}
