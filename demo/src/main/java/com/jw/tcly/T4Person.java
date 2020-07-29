package com.jw.tcly;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: test com.tcly.test.T4Person
 * @Package: com.tcly.test
 * @ClassName: T4Person
 * @Author: james.guo
 * @Date: 2019/8/26 18:26
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class T4Person {

	public static void main(String[] args) {

		Map<String, List<String>> routingGroupMap = Maps.newHashMap();
		routingGroupMap.put("1", Lists.newArrayList(Collections.nCopies(1, null)));
		routingGroupMap.put("10", Lists.newArrayList(Collections.nCopies(10, null)));
		routingGroupMap.put("100", Lists.newArrayList(Collections.nCopies(100, null)));

		int sum = routingGroupMap.values().stream().map(List::size).reduce(0, Integer::sum);
		System.out.println("sum: " + sum);
		int sum2 = routingGroupMap.values().stream().mapToInt(List::size).sum();
		System.out.println("sum2: " + sum2);

		Person p = new Person();
		p.setBirthday(new Date());

		Person person = new Person();
		person.setName("xxx");
		person.setBirthday(new Date());
		person.setPersons(Lists.newArrayList(p));

		String req = JSON.toJSONString(person);
		System.out.println("req: " + req);

		req = "{\"birthday\":\"2019-08-28 19:39:16\",\"name\":\"xxx\",\"persons\":[{\"birthday\":\"2019-08-26 19:11:16\"}]}";
		Person resp = JSON.parseObject(req, Person.class);
		System.out.println("resp: " + resp);

	}
}
