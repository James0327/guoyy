package com.jw.algorithm.dp;

import org.junit.jupiter.api.Test;

/**
 * guoyy com.jw.algorithm.dp
 *
 * @Description: com.jw.algorithm.dp.LCS
 * @Author: guoyiyong/james
 * @Date: 2020-07-26 12:29
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class LCS {

    @Test
    public void test() {
        char[] l = "BDCABA".toCharArray();
        char[] s = "ABCBDAB".toCharArray();

        char[] ret = new char[l.length > s.length ? l.length : s.length];
        int max = lcs(ret, 0, l, l.length - 1, s, s.length - 1);
        System.out.println(String.format("max:%s, ret:%s", max, new String(ret)));
    }

    private int lcs(char[] ret, int idx, char[] l, int i, char[] s, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (l[i] == s[j]) {
            ret[idx++] = l[i];
            return lcs(ret, idx, l, i - 1, s, j - 1) + 1;
        }
        int len1 = lcs(ret, idx, l, i, s, j - 1);
        int len2 = lcs(ret, idx, l, i - 1, s, j);
        return len1 > len2 ? len1 : len2;
    }

}
