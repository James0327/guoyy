package com.jw.tcly.mybatis.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: test com.tcly.test.mybatis.dao.UserDO
 * @Package: com.tcly.test.mybatis.dao
 * @ClassName: UserDO
 * @Author: james.guo
 * @Date: 2019/10/15 11:29
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
@Data
public class UserDO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userName;
    private String password;
    private Long account;
    private Date tDate;
    private Date tTime;
    private Date tDatetime;
    private Date tTimestamp;
}
