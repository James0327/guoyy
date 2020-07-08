package com.jw.test;


import com.jw.tcly.test.proxy.CglibMethodInterceptor;
import com.jw.tcly.test.proxy.Dog;
import net.sf.cglib.proxy.Enhancer;

/**
 * @Description: test PACKAGE_NAME.CglibProxyTest
 * @Package: PACKAGE_NAME
 * @ClassName: CglibProxyTest
 * @Author: james.guo
 * @Date: 2019/10/17 14:20
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class CglibProxyTest {

    /**
     * CGlib可以传入接口也可以传入普通的类，接口使用实现的方式,普通类使用会使用继承的方式生成代理类
     * 由于是继承方式,如果是 static方法,private方法,final方法等描述的方法是不能被代理
     * 做了方法访问优化，使用建立方法索引的方式避免了传统JDK动态代理需要通过Method方法反射调用
     * 提供callback 和filter设计，可以灵活地给不同的方法绑定不同的callback,编码更方便灵活
     * CGLIB会默认代理Object中equals,toString,hashCode,clone等方法,比JDK代理多了clone
     */
    public static void main(String[] args) {
        // 创建加强器，用来创建动态代理类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dog.class);
        CglibMethodInterceptor methodInterceptor = new CglibMethodInterceptor();
        enhancer.setCallback(methodInterceptor);
        Dog dog = (Dog) enhancer.create();
        String ret = dog.call();
        System.out.println(ret);
    }

}
