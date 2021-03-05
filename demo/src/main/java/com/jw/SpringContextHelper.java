package com.jw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * tax_complex_entrance com.ly.ic.tax.complex.entrance.util
 *
 * @Description: com.ly.ic.tax.complex.entrance.util.SpringContextHelper
 * @Author: guoyiyong/james.guo
 * @Date: 2020/3/11 15:14
 * @Version: 1.0
 * Copyright (C) 2020 JW All rights reserved.
 */
@Component
public class SpringContextHelper implements ApplicationContextAware {
    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return context.getBean(name, clazz);
    }

}