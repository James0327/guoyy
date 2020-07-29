package com.jw.tcly;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @Description: test com.tcly.test.Person
 * @Package: com.tcly.test
 * @ClassName: Person
 * @Author: james.guo
 * @Date: 2019/8/26 18:24
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
@Setter
@Getter
public class Person {

	private String name;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;

	private List<Person> persons;
}
