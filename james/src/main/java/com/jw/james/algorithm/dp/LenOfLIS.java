package com.jw.james.algorithm.dp;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.jupiter.api.Test;

/**
 * guoyy com.jw.algorithm.dp
 * <p>
 * 最长上升子序列(LIS)：什么是最长上升子序列？ 就是给你一个序列，请你在其中求出一段不断严格上升的部分，它不一定要连续。
 * 就像这样：2,3,4,7和2,3,4,6就是序列2 5 3 4 1 7 6的两种选取方案。最长的长度是4.
 * <p>
 * 求最长上升子序列(LIS)长度
 *
 * @Description: com.jw.algorithm.dp.LenOfLIS
 * @Author: guoyiyong/james
 * @Date: 2020-07-26 11:58
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class LenOfLIS {

    @Test
    public void test() {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int[] arr = lis(nums);
        System.out.println("ret: " + ToStringBuilder.reflectionToString(arr));
    }

    /**
     * dp[i]: 表示以 nums[i] 这个数结尾的最长递增子序列的长度
     * dp[i]: 以i结尾（一定包括 i）所能形成的最长上升子序列长度, 答案是 max(dp[i])，其中 i = 0,1,2, ..., n - 1
     */
    private int[] lis(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                // 看以前的，比它小的，说明可以接在后面形成一个更长的子序列
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
        }
        int ret = 0, lastItem = 0;
        for (int i = 0; i < len; i++) {
            if (dp[i] > ret) {
                ret = dp[i];
                lastItem = nums[i];
            }
        }

        return new int[]{ret, lastItem};
    }

}
