package com.jw.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @Description: test Clazz
 * @Package: com.jw.dto
 * @ClassName: Clazz
 * @Author: james.guo
 * @Date: 2019/9/26 11:38
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
@Setter
@Getter
public class Clazz {
	private Map<Integer, Map<String, SuperClass>> map;
}
