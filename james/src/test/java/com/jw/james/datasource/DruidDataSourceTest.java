package com.jw.james.datasource;

import com.jw.james.GuoyyApplication;
import com.jw.james.comm.datasource.JwDataSource;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * guoyy com.jw.datasource
 *
 * @Description: com.jw.datasource.DruidDataSourceTest
 * @Author: guoyiyong/james
 * @Date: 2020-07-27 21:56
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@SpringBootTest(classes = GuoyyApplication.class)
public class DruidDataSourceTest {
    @Resource
    private JwDataSource jwDataSource;

    @Test
    public void test() throws Exception {
        DataSource dataSource = jwDataSource.druidDataSource();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        /** 设置mybatis configuration 扫描路径 */
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        /** 设置datasource */
        sqlSessionFactoryBean.setDataSource(dataSource);
        /** 设置typeAlias 包扫描路径 */
        sqlSessionFactoryBean.setTypeAliasesPackage("com.jw.tcly.mybatis.dto");

    }

}
