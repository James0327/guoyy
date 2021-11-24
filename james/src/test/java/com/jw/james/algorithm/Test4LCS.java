package com.jw.james.algorithm;

import com.jw.james.algorithm.dp.LCS;
import org.junit.jupiter.api.Test;

/**
 * guoyy com.jw.algorithm
 *
 * @Description: com.jw.algorithm.Test4LCS
 * @Author: guoyiyong/james
 * @Date: 2021/2/22 10:10
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class Test4LCS {

    @Test
    public void test() {
        LCS lcs = new LCS();

        char[] l = "BDCABA".toCharArray();
        char[] s = "ABCBDAB".toCharArray();

        char[] ret = new char[l.length > s.length ? l.length : s.length];
        int max = lcs.lcs(ret, 0, l, l.length - 1, s, s.length - 1);
        System.out.println(String.format("max:%s, ret:%s", max, new String(ret)));
    }

}
