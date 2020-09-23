package com.jw.t;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: test Test01
 * @Package: com.jw.t
 * @ClassName: Test01
 * @Author: james.guo
 * @Date: 2019/9/20 19:14
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test01 {
    private final Pattern compile = Pattern.compile("(?<![\\w])[\\w]{2}(?!=[\\w])");

    @Test
    public void test() {
        Integer i = 128;

        System.out.println(i.equals(128));

        _1();
    }

    public void _1() {

        String str = "sha-ca,ca-lax/lax-aa,aa-bjs";

        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    public static void main(String[] args) {
        int t = -1;
        System.out.println("t:" + Integer.toBinaryString(t));
        int i = 1;
        System.out.println("i:" + Integer.toBinaryString(i));
        int i2 = 1 << 1;
        System.out.println("i2:" + Integer.toBinaryString(i2));
        int i4 = 1 << 2;
        System.out.println("i4:" + Integer.toBinaryString(i4));

        t = t ^ i2;

        System.out.println("t[" + Integer.toBinaryString(t));

    }

}
