package com.jw.hander;

import java.util.Random;

/**
 * Description: guoyy
 * com.jw.hander.BTraceTest
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2022/4/1 20:40
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2022 JW All rights reserved.
 */
public class BTraceTest {

    public static void main(String[] args) throws Exception {
        Random random = new Random();

        // 计数器
        Counter counter = new Counter();
        while (true) {
            // 每次增加随机值
            counter.add(random.nextInt(10));
            Thread.sleep(1000);
        }

    }

}
