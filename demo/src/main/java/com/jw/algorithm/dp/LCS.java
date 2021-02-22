package com.jw.algorithm.dp;

/**
 * guoyy com.jw.algorithm.dp
 * <p>
 * 最长公共子序列（longest common subsequence，LCS）
 *
 * @Description: com.jw.algorithm.dp.LCS
 * @Author: guoyiyong/james
 * @Date: 2020-07-26 12:29
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class LCS {

    public int lcs(char[] ret, int idx, char[] l, int i, char[] s, int j) {
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
