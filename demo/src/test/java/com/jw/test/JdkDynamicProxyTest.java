package com.jw.test;

import com.jw.tcly.proxy.Person;
import com.jw.tcly.proxy.PersonInvocationhandler;
import com.jw.tcly.proxy.impl.SoftwareEngineer;
import sun.misc.ProxyGenerator;

import java.lang.reflect.Proxy;
import java.util.Base64;

/**
 * @Description: test PACKAGE_NAME.JdkDynamicProxyTest
 * @Package: PACKAGE_NAME
 * @ClassName: JdkDynamicProxyTest
 * @Author: james.guo
 * @Date: 2019/10/17 12:02
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class JdkDynamicProxyTest {

    /**
     * JDK动态代理特点总结：
     * 1、生成的代理类：$Proxy0 extends Proxy implements Person，我们看到代理类继承了Proxy类，Java的继承机制决定了JDK动态代理类们无法实现对 类 的动态代理。所以也就决定了java动态代理只能对接口进行代理
     * 2、每个生成的动态代理实例都会关联一个调用处理器对象，可以通过 Proxy 提供的静态方法 getInvocationHandler 去获得代理类实例的调用处理器对象。在代理类实例上调用其代理的接口中所声明的方法时，这些方法最终都会由调用处理器的 invoke 方法执行
     * 3、代理类的根类 java.lang.Object 中有三个方法也同样会被分派到调用处理器的 invoke 方法执行，它们是 hashCode，equals 和 toString，可能的原因有：一是因为这些方法为 public 且非 final 类型，能够被代理类覆盖； 二是因为这些方法往往呈现出一个类的某种特征属性，具有一定的区分度，所以为了保证代理类与委托类对外的一致性，这三个方法也应该被调用处理器分派到委托类执行
     * <p>
     * 基于CGlib 技术动态代理代理类实现 (基于继承)：不能对final修饰的类进行代理，final修饰的类不可继承
     */
    public static void main(String[] args) {
        Person person = new SoftwareEngineer("James");
        PersonInvocationhandler<Person> handler = new PersonInvocationhandler<>(person);

        Person personProxy = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, handler);

        personProxy.goWorking(personProxy.getName(), "XXXXXX");

        Class<?> proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class[]{Person.class});

        byte[] data = ProxyGenerator.generateProxyClass("$PersonProxy0", proxyClass.getInterfaces());
        System.out.println(Base64.getEncoder().encodeToString(data));
    }
}
