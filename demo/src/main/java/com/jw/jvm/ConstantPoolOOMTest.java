package com.jw.jvm;

import com.google.common.collect.Lists;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.List;
import java.util.UUID;

/**
 * guoyy com.jw.jvm
 *
 * @Description: com.jw.jvm.ConstantPoolOOMTest
 * @Author: guoyiyong/james
 * @Date: 2020-07-15 17:47
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class ConstantPoolOOMTest {

    /**
     * java.lang.OutOfMemoryError: Java heap space
     * Dumping heap to logs/java_pid62426.hprof ...
     * Heap dump file created [20384824 bytes in 0.141 secs]
     * run tiems: 134861
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     *
     * java.lang.OutOfMemoryError: GC overhead limiter exceeded
     * Dumping heap to logs/java_pid62503.hprof ...
     * Heap dump file created [22758962 bytes in 0.128 secs]
     * run tiems: 152721
     * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limiter exceeded
     */
    /**
     * 永久代溢出可以分为两种情况，第一种是常量池溢出，第二种是方法区溢出（方法区存放Class的相关信息，下面借助CGLib直接操作字节码，生成大量的动态类）。
     * <p>
     * -Xms20m -Xmx20m -Xss256k -XX:+HeapDumpOnOutOfMemoryError
     * ### -XX:PermSize=10m -XX:MaxPermSize=10m
     * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
     * -XX:HeapDumpPath=logs -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:logs/gc.log
     * <p>
     * String.intern()方法的实现不再是在常量池中创建与此String内容相同的字符串，而在常量池中记录Java Heap中首次出现的该字符串的引用，并返回该引用。
     * 简单来说，就是对象实际存储在堆上面
     */
    public static void main(String[] args) {
        //        test0();
        test1();
    }

    private static void test0() {
        List<String> data = Lists.newArrayList();

        int i = 0;
        try {
            while (true) {
                data.add(UUID.randomUUID().toString());
                i++;
            }
        } finally {
            System.out.println("run tiems: " + i);
        }
    }

    /**
     * java.lang.OutOfMemoryError: Metaspace
     * Dumping heap to logs/java_pid62529.hprof ...
     * Heap dump file created [4397343 bytes in 0.028 secs]
     * class cnt: 520
     * Exception in thread "main" java.lang.OutOfMemoryError: Metaspace
     * at net.sf.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:348)
     * at net.sf.cglib.proxy.Enhancer.generate(Enhancer.java:492)
     * at net.sf.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:117)
     * at net.sf.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:294)
     * at net.sf.cglib.proxy.Enhancer.createHelper(Enhancer.java:480)
     * at net.sf.cglib.proxy.Enhancer.create(Enhancer.java:305)
     * at com.jw.jvm.ConstantPoolOOMTest.test1(ConstantPoolOOMTest.java:73)
     * at com.jw.jvm.ConstantPoolOOMTest.main(ConstantPoolOOMTest.java:48)
     */
    private static void test1() {
        int i = 0;
        try {
            while (true) {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMObject.class);
                enhancer.setUseCache(false);
                enhancer.setCallback((MethodInterceptor)(obj, method, args, proxy) -> proxy.invokeSuper(obj, args));
                enhancer.create();
                i++;
            }
        } finally {
            System.out.println("class cnt: " + i);
        }

    }

    public static class OOMObject {

    }

}
