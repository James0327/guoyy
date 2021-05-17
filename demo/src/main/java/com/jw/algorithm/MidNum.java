package com.jw.algorithm;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.jupiter.api.Test;

/**
 * Description: guoyy
 * com.jw.algorithm.MidNum
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/4/19 16:30
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class MidNum {

    @Test
    public void test() {
        int[] s = new int[]{1, 3, 5};
        int[] t = new int[]{2, 4, 6};
        int[] ret = algorithm(s, t);

        System.out.println("ret: " + ToStringBuilder.reflectionToString(ret));
    }

    private int[] algorithm(int[] s, int[] t) {
        int sLen = s.length, tLen = t.length;
        int[] ret = new int[sLen + tLen];

        int left = 0, right = 0, idx = 0;
        while (left < sLen || right < tLen) {
            if (left >= sLen) {
                ret[idx++] = t[right++];
                continue;
            }
            if (right >= tLen) {
                ret[idx++] = s[left++];
                continue;
            }
            if (s[left] < t[right]) {
                ret[idx++] = s[left++];
            } else if (s[left] == t[right]) {
                ret[idx++] = s[left++];
                ret[idx++] = t[right++];
            } else {
                ret[idx++] = t[right++];
            }
        }

        System.out.println("ret: " + ArrayUtils.toString(ret));
        int i = ret.length / 2;
        if ((ret.length & 1) == 0) {
            return new int[]{ret[i - 1], ret[i]};
        }
        return new int[]{ret[i]};
    }

}
