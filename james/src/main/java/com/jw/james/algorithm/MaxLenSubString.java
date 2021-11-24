package com.jw.james.algorithm;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Description: guoyy
 * com.jw.algorithm.MaxLenSubString
 * 最长无重复字符的子串（连续字符）
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/4/5 09:13
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class MaxLenSubString {

    public static void main(String[] args) {
        MaxLenSubString obj = new MaxLenSubString();

        String src = "aabab";

        int len = obj.algorithm(src);
        int len2 = obj.algorithm2(src);

        System.out.println("len: " + len);
        System.out.println("len2: " + len2);
    }

    private int algorithm2(String src) {
        int srcLen = src.length();
        if (srcLen == 1) {
            return 1;
        }

        int[] dp = new int[srcLen];
        Arrays.fill(dp, 1);

        for (int i = 1; i < srcLen; i++) {
            char p = src.charAt(i);
            boolean isrepeat = false;
            for (int j = 0; j < i; j++) {
                char c = src.charAt(j);
                if (c == p) {
                    isrepeat = true;
                    break;
                }
            }
            if (!isrepeat) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        return IntStream.of(dp).max().getAsInt();
    }

    private int algorithm(String src) {
        int srcLen = src.length();

        int left = 0, right = 0;
        int maxLen = -1;

        Map<Character, Integer> validMap = Maps.newHashMapWithExpectedSize(srcLen);

        while (right < srcLen) {
            char rc = src.charAt(right++);
            if (validMap.containsKey(rc)) {
                validMap.put(rc, validMap.get(rc) + 1);
            } else {
                validMap.put(rc, 1);
            }

            while (validMap.getOrDefault(rc, 0) > 1) {
                char lc = src.charAt(left++);
                if (validMap.containsKey(lc)) {
                    Integer cnt = validMap.get(lc);
                    if (cnt == 1) {
                        validMap.remove(lc);
                    } else {
                        validMap.put(lc, cnt - 1);
                    }
                }
            }

            maxLen = Math.max(maxLen, right - left);
        }

        return maxLen;
    }

}
