package com.jw.hander;

/**
 * Description: guoyy
 * com.jw.hander.Counter
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2022/4/1 20:40
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2022 JW All rights reserved.
 */
public class Counter {

    // 总数
    private static int totalCount = 0;

    public int add(int num) throws Exception {
        totalCount += num;
        sleep();
        return totalCount;
    }

    public void sleep() throws Exception {
        Thread.sleep(1000);
    }

}
