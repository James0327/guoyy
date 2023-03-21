package com.jw.james.algorithm.dp;

/**
 * guoyy com.jw.algorithm.dp
 * <p>
 * 最长公共子序列（longest common subsequence，LCS）
 *
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，“ace” 是 “abcde” 的子序列，但 “aec” 不是 “abcde” 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 给定两个序列
 * X = { x1 , x2 , ... , xm }
 * Y = { y1 , y2 , ... , yn }
 * 求X和Y的一个最长公共子序列
 *
 * 举例
 * X = { a , b , c , b , d , a , b }
 * Y = { b , d , c , a , b , a }
 * 最长公共子序列为
 * LSC = { b , c , b , a }
 *
 * 分析：
 *
 * 最长公共子序列问题具有最优子结构性质
 *
 * 设
 * X = { x1 , ... , xm }
 * Y = { y1 , ... , yn }
 * 及它们的最长子序列
 * Z = { z1 , ... , zk }
 *
 * 则
 * 1、若 xm = yn ， 则 zk = xm = yn，且Z[k-1] 是 X[m-1] 和 Y[n-1] 的最长公共子序列
 * 2、若 xm != yn ，且 zk != xm , 则 Z 是 X[m-1] 和 Y 的最长公共子序列
 * 3、若 xm != yn , 且 zk != yn , 则 Z 是 Y[n-1] 和 X 的最长公共子序列
 *
 * 由性质导出子问题的递归结构
 * 当 i = 0 , j = 0 时 ,        c[i][j] = 0
 * 当 i , j > 0 ; xi = yi 时 ,  c[i][j] = c[i-1][j-1] + 1
 * 当 i , j > 0 ; xi != yi 时 , c[i][j] = max { c[i][j-1] , c[i-1][j] }
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
        return Math.max(len1, len2);
    }

}
