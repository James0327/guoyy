package com.jw.algorithm.sort;

import org.apache.commons.lang3.StringUtils;

/**
 * guoyy com.jw.algorithm.sort
 *
 * @Description: com.jw.algorithm.sort.ShellSort
 * @Author: guoyiyong/james
 * @Date: 2020-09-24 20:54
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] source = {12, 3710, 34, 70, 14, 312, 0, 413, 413, 14};
        int[] arr = shellSort(source);
        System.out.println(StringUtils.join(arr, ' '));
    }

    public static int[] shellSort(int[] source) {
        int[] arr = source;
        int len = arr.length;
        int gap = len / 2;
        // 增量
        while (gap >= 1) {
            // 组数
            for (int i = 0; i < gap; i++) {
                // 每组数据进行插入排序
                for (int j = gap; j < len; j += gap) {
                    int k = j;
                    int p = arr[k];
                    while (k >= gap && p < arr[k - gap]) {
                        arr[k] = arr[k - gap];
                        k -= gap;
                    }
                    if (k < j) {
                        arr[k] = p;
                    }
                }

            }
            gap /= 2;
        }
        return arr;
    }

}
