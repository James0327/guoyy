package com.jw.tcly.test.mybatis;

import com.alibaba.fastjson.JSON;
import com.jw.tcly.test.mybatis.dao.UserDAO;
import com.jw.tcly.test.mybatis.dto.UserDO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.Reader;
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

    @Test
    public void testSelect() throws Exception {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        Configuration configuration = sqlSessionFactory.getConfiguration();
        System.out.println(ToStringBuilder.reflectionToString(configuration));

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
        List<UserDO> rets = userDAO.select();
        System.out.println(JSON.toJSONString(rets));

        System.out.println("-----------------------------------");
        UserDO ret = userDAO.selectById(99L);
        System.out.println(JSON.toJSONString(ret));

        sqlSession.close();
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
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserDAO userDAO = sqlSession.getMapper(UserDAO.class);

        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < 999; i++) {
            UserDO userDO = new UserDO();
            userDO.setUserName("name" + i);
            userDO.setAccount(random.nextLong());
            userDO.setPassword("xxx" + i);

            int cnt = userDAO.insert(userDO);
            System.out.println("insert " + (cnt >= 1 ? "true" : "false"));
        }

        sqlSession.commit();
        sqlSession.close();
    }

}
