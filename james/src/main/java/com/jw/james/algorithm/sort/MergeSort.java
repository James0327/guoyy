package com.jw.james.algorithm.sort;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * guoyy com.jw.algorithm.sort
 *
 * @Description: com.jw.algorithm.sort.MergeSort
 * @Author: guoyiyong/james
 * @Date: 2020-09-24 22:29
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] source = {12, 3710, 34, 70, 14, 312, 0, 413, 413, 14};
        int[] arr = mergeSort(source);
        System.out.println(StringUtils.join(arr, ' '));
    }

    /**
     * 归并排序
     */
    public static int[] mergeSort(int[] source) {
        int[] arr = source;

        if (arr.length <= 1) {
            return arr;
        }

        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    /**
     * 归并两个有序数组
     */
    private static int[] merge(int[] up, int[] down) {
        int upLen = up.length, downLen = down.length;
        int[] ret = new int[upLen + downLen];

        int upIdx = 0, downIdx = 0;

        for (int k = 0, len = ret.length; k < len; k++) {
            // 边界判断  上数组 遍历完成，直接填充下数组
            if (upIdx >= upLen) {
                ret[k] = down[downIdx];
                downIdx++; // 指针向右偏移一位
                continue;
            }
            // 边界判断  下数组 遍历完成，直接填充上数组
            if (downIdx >= downLen) {
                ret[k] = up[upIdx];
                upIdx++; // 指针向右偏移一位
                continue;
            }
            // 上数组元素大 则填入下数组元素
            // 下数组元素大 则填入上数组元素
            if (up[upIdx] > down[downIdx]) {
                ret[k] = down[downIdx];
                downIdx++;
            } else {
                ret[k] = up[upIdx];
                upIdx++;
            }
        }

        return ret;
    }

}
