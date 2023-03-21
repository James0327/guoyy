package com.jw.james.algorithm.sort;

import org.apache.commons.lang3.StringUtils;

/**
 * guoyy com.jw.algorithm.sort
 *
 * @Description: com.jw.algorithm.sort.InsertionSort
 * @Author: guoyiyong/james
 * @Date: 2020-09-24 17:22
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] source = {12, 3710, 34, 70, 14, 312, 0, 413, 413, 14};
        int[] source2 = {12, 3710, 34, 70, 14, 312, 0, 413, 413, 14};
        int[] arr = insertionSort(source);
        System.out.println(StringUtils.join(arr, ' '));

        int[] arr2 = insertionSort2(source2);
        System.out.println(StringUtils.join(arr2, ' '));
    }

    public static int[] insertionSort2(int[] arr) {
        if (arr == null) {
            return null;
        }
        int len = arr.length;
        if (len <= 1) {
            return arr;
        }
        // 0~0 有序 0~i想有序
        for (int i = 1; i < len; i++) {
            // 终止条件 j<0 说明前面没有元素了
            // arr[j] <= arr[j + 1] 说明j+1及前面的数据有序
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] insertionSort(int[] source) {
        int[] arr = source;
        for (int i = 1, len = arr.length; i < len; i++) {
            // 当前点索引
            int j = i;
            // 当前点
            int p = arr[i];
            while (j >= 1 && p < arr[j - 1]) {
                // 将前一个节点往后移动一位
                arr[j] = arr[j - 1];
                j--;
            }
            // 有数据移动
            if (j < i) {
                // j 表示需插入的位置
                arr[j] = p;
            }
        }

        return arr;
    }

}
