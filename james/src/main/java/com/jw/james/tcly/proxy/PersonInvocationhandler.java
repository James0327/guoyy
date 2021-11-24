package com.jw.james.tcly.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Description: test com.tcly.test.proxy.PersonInvocationhandler
 * @Package: com.tcly.test.proxy
 * @ClassName: PersonInvocationhandler
 * @Author: james.guo
 * @Date: 2019/10/17 11:42
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class PersonInvocationhandler<T> implements InvocationHandler {
	private T target;

	/**
	 * 被代理对象引用，invoke方法里面method需要使用这个被代理对象
	 * 委托类
	 */
	public PersonInvocationhandler(T target) {
		this.target = target;
	}

	/**
	 * @param proxy  代理类
	 * @param method 委托类的接口方法 与 代理类接口方法名一致
	 * @param args   接口入参
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("被动态代理类回调执行, 代理类 proxyClass =" + proxy.getClass()
				+ " 方法名: " + method.getName() + "方法. 方法返回类型：" + method.getReturnType()
				+ " 接口方法入参数组: " + (args == null ? "null" : Arrays.toString(args)));

		System.out.println("开始。。。");
		LocalDateTime start = LocalDateTime.now();

		Object obj = method.invoke(target, args);

		LocalDateTime end = LocalDateTime.now();
		System.out.println("结束");

		System.out.println(Duration.between(start, end).toMillis());

		return obj;
	}
}
