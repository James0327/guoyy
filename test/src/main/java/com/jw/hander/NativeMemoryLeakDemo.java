package com.jw.hander;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Description: guoyy
 * com.jw.hander.NativeMemoryLeakDemo
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2022/3/31 18:02
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2022 JW All rights reserved.
 */
public class NativeMemoryLeakDemo {

    private static final String str = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    private static final String str1 = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    private static final String str2 = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    private static final String str3 = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    public static void main(String[] args) throws Exception {

        while (true) {
            //            Enhancer enhancer = new Enhancer();
            //            enhancer.setUseCache(false);
            //            enhancer.setSuperclass(NativeMemoryLeakDemo.class);
            //            enhancer.setCallback(new MethodInterceptor() {
            //                @Override
            //                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            //                    return null;
            //                }
            //            });
            //            enhancer.create();

            //            test();

            str.intern();
            str1.intern();
            str2.intern();
            str3.intern();

            (str + ThreadLocalRandom.current().nextLong()).intern();
            (str1 + ThreadLocalRandom.current().nextLong()).intern();
            (str2 + ThreadLocalRandom.current().nextLong()).intern();
            (str3 + ThreadLocalRandom.current().nextLong()).intern();
        }
    }

    private static void test() throws Exception {
        String path = NativeMemoryLeakDemo.class.getClassLoader().getResource("Simple-Line-Icons.ttf").getPath();
        Font rawFont = Font.createFont(Font.TRUETYPE_FONT, new File(path));
        Font usedFont = rawFont.deriveFont(Font.PLAIN, 300);
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.setFont(usedFont);
        g2.drawString("hello world", 16, 35);
    }

}
