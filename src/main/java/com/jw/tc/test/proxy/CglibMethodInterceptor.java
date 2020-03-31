package com.jw.tc.test.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @Description: test com.tc.test.proxy.CglibMethodInterceptor
 * @Package: com.tc.test.proxy
 * @ClassName: CglibMethodInterceptor
 * @Author: james.guo
 * @Date: 2019/10/17 13:49
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class CglibMethodInterceptor implements MethodInterceptor {

	/**
	 *
	 * @param obj 代表Cglib 生成的动态代理类 对象本身
	 * @param method 代理类中被拦截的接口方法 Method 实例
	 * @param args 接口方法参数
	 * @param methodProxy 用于调用父类真正的业务类方法。可以直接调用被代理类接口方法
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

		System.out.println("开始。。。");
		LocalDateTime start = LocalDateTime.now();

		Object ret = methodProxy.invokeSuper(obj, args);

		LocalDateTime end = LocalDateTime.now();
		System.out.println("结束");

		System.out.println(Duration.between(start, end).toMillis());

		return ret;
	}
}
