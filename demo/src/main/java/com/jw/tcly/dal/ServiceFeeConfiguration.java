package com.jw.tcly.dal;

import com.ly.dal.datasource.RoutableDataSource;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * taxbag-batch-core com.ly.ic.taxbag.batch.core.server.dal
 *
 * @Description: com.ly.ic.taxbag.batch.core.server.dal.ServiceFeeConfiguration
 * @Author: guoyiyong/james
 * @Date: 2020-05-17 23:33
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
@Configuration
@ConditionalOnProperty(value = "tcly.datasource.enable")
@MapperScan(basePackages = {"com.ly.ie.atpsfcommon.dao.yqyr", "com.ly.ie.atpsfcommon.dao.nation",
        "com.ly.ie.atpsfcommon.dao.foo", "com.ly.ie.atpsfcommon.dao.taxbag"},
        sqlSessionFactoryRef = "sqlSessionFactoryServiceFee")
public class ServiceFeeConfiguration {
    private static final String[] MAPPER_LOCATION = {
            "classpath:dal/yqyr/*.xml", "classpath:dal/nation/*.xml",
            "classpath:dal/foo/*.xml", "classpath:dal/taxbag/*.xml"
    };
    private static final String CONFIG_LOCATION = "classpath:/mybatis_config.xml";

    @Bean(name = "dataSourceServiceFee", initMethod = "init", destroyMethod = "close")
    public RoutableDataSource dataSource() {
        RoutableDataSource dataSource = new RoutableDataSource();
        dataSource.setDbName("TCFlyIntServiceFee");
        dataSource.setConnectionProperties(Stream.of(Pair.of("allowMultiQueries", "true"))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue)));

        return dataSource;
    }

    @Bean(name = "sqlSessionFactoryServiceFee")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceServiceFee") DataSource dataSource) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource(CONFIG_LOCATION));
        sqlSessionFactoryBean.setMapperLocations(buildResources(resolver));

        return sqlSessionFactoryBean.getObject();
    }

    private Resource[] buildResources(PathMatchingResourcePatternResolver resolver) throws IOException {
        Resource[] resources = new Resource[0];
        for (int i = 0, len = MAPPER_LOCATION.length; i < len; i++) {
            Resource[] resourceArr = resolver.getResources(MAPPER_LOCATION[i]);
            Resource[] newResourcesArr = new Resource[resources.length + resourceArr.length];
            System.arraycopy(resources, 0, newResourcesArr, 0, resources.length);
            System.arraycopy(resourceArr, 0, newResourcesArr, resources.length, resourceArr.length);
            resources = newResourcesArr;
        }
        return resources;
    }

    @Bean(name = "dataSourceTransactionManagerServiceFee")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSourceServiceFee") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
