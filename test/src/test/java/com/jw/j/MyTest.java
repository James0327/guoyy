package com.jw.j;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Description: guoyy
 * com.jw.j.MyTest
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/12/6 16:55
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class MyTest {

    @Test
    public void test() {
        String ret1 = String.format("%+d%%", -1);
        System.out.println(ret1);

        String ret2 = String.format("%+.2f", 1D / 100);
        System.out.println(ret2);
    }

}
