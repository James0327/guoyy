package com.jw.james.tcly.mybatis;

import com.alibaba.fastjson.JSON;
import com.jw.james.tcly.mybatis.dao.Tbl186DAO;
import com.jw.james.tcly.mybatis.dao.UserDAO;
import com.jw.james.tcly.mybatis.dto.Tbl186;
import com.jw.james.tcly.mybatis.dto.UserDO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.joda.time.DateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description: test com.tcly.test.mybatis.T4Mybatis
 * @Package: com.tcly.test.mybatis
 * @ClassName: T4Mybatis
 * @Author: james.guo
 * @Date: 2019/10/16 22:22
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class T4Mybatis {
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private SqlSession sqlSession = null;

    @BeforeEach
    public void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        Configuration configuration = sqlSessionFactory.getConfiguration();
        System.out.println(ToStringBuilder.reflectionToString(configuration));

        sqlSession = sqlSessionFactory.openSession();
    }

    @AfterEach
    public void destory() {
        sqlSession.close();
    }

    @Test
    public void selectTbl186() {
        Tbl186DAO tbl186DAO = sqlSession.getMapper(Tbl186DAO.class);
        Tbl186 tbl186 = tbl186DAO.queryById(5284L);
        System.out.println("tbl186: " + JSON.toJSONStringWithDateFormat(tbl186, JSON.DEFFAULT_DATE_FORMAT));
    }

    @Test
    public void testSelect() {
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
        List<UserDO> rets = userDAO.select();
        System.out.println(JSON.toJSONString(rets));

        System.out.println("-----------------------------------");
        UserDO ret = userDAO.selectById(2L);
        System.out.println(JSON.toJSONStringWithDateFormat(ret, JSON.DEFFAULT_DATE_FORMAT));
    }

    @Test
    public void testDelete() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
        int i = userDAO.deleteById(2L);

        System.out.println(i);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsert() throws Exception {
        System.out.println(System.getProperty("user.timezone"));

        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);

        insert1(userDAO);
        insert2(userDAO);

        sqlSession.commit();
        sqlSession.close();
    }

    private void insert2(UserDAO userDAO) {
        UserDO userDO = new UserDO();
        userDO.setUserName("name2");
        userDO.setAccount(random.nextLong());
        userDO.setPassword("xxx2");

        Date date = DateTime.now().withTimeAtStartOfDay().toDate();
        System.out.println("date: " + date);

        userDO.setTDate(date);
        userDO.setTTime(date);
        userDO.setTDatetime(date);
        userDO.setTTimestamp(date);

        int cnt = userDAO.insert(userDO);
        System.out.println("insert " + (cnt >= 1 ? "true" : "false"));
    }

    private void insert1(UserDAO userDAO) {
        UserDO userDO = new UserDO();
        userDO.setUserName("name1");
        userDO.setAccount(random.nextLong());
        userDO.setPassword("xxx1");

        Date date = DateTime.now().toDate();
        System.out.println("date: " + date);

        userDO.setTDate(date);
        userDO.setTTime(date);
        userDO.setTDatetime(date);
        userDO.setTTimestamp(date);

        int cnt = userDAO.insert(userDO);
        System.out.println("insert " + (cnt >= 1 ? "true" : "false"));
    }

}
