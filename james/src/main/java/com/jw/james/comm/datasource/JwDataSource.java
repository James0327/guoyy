package com.jw.james.comm.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * guoyy com.jw.comm.datasource
 *
 * @Description: com.jw.comm.datasource.DruidDataSource
 * @Author: guoyiyong/james
 * @Date: 2020-07-27 21:48
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Configuration
public class JwDataSource {

    @Bean
    @ConditionalOnMissingBean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://10.100.173.222:3500/TCFlyIntServiceFee?characterEncoding=UTF-8");
        druidDataSource.setUsername("TCFlyIntServiceFee");
        druidDataSource.setPassword("ueW5ZMGIwEPAS7XcqKolHmQF3");
        druidDataSource.setInitialSize(3);
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxActive(10);
        druidDataSource.setMaxWait(100);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("select 1");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxOpenPreparedStatements(50);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        try {
            druidDataSource.setFilters("stat,wall,log4j2,default");
            druidDataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return druidDataSource;
    }

}
