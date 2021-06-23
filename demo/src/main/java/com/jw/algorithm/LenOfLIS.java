package com.jw.algorithm;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Description: guoyy
 * com.jw.algorithm.LenOfLIS
 * 最长递增子序列（非连续字符）
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/4/5 15:14
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class LenOfLIS {

    public static void main(String[] args) {
        LenOfLIS obj = new LenOfLIS();

        int[] nums = new int[]{1, 4, 3, 4, 2};
        int ret = obj.algorithm(nums);
        System.out.println("ret:" + ret);

        nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        ret = obj.algorithm(nums);
        System.out.println("ret:" + ret);
    }

    private int algorithm(int[] nums) {
        int len = nums.length;

        // 设F(i)表示L中以Ai为末元素的最长递增子序列的长度
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        for (int i = 0; i < len; i++) {
            // dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return IntStream.of(dp).max().getAsInt();
    }

}
