package com.jw.james.tcly.mybatis.dao;

import com.jw.james.tcly.mybatis.dto.UserDO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: test com.tcly.test.mybatis.dao.UserDAO
 * @Package: com.tcly.test.mybatis.dao
 * @ClassName: UserDAO
 * @Author: james.guo
 * @Date: 2019/10/15 11:27
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public interface UserDAO {

    int insert(UserDO userDO);

    int update(UserDO userDO);

    @Delete("delete from user_info where id = #{id}")
    int deleteById(Long id);

    @Select("select * from user_info")
    List<UserDO> select();

    // @Select("select * from user_info where id = #{id}")
    UserDO selectById(Long id);
}
