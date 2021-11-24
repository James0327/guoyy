package com.jw.james.algorithm.sort;

import org.apache.commons.lang3.StringUtils;

/**
 * guoyy com.jw.algorithm.sort
 *
 * @Description: com.jw.algorithm.sort.SelectionSort
 * @Author: guoyiyong/james
 * @Date: 2020-09-24 14:55
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] source = {12, 3710, 34, 70, 14, 312, 0, 413, 413, 14};
        int[] arr = selectionSort(source);
        System.out.println(StringUtils.join(arr, ' '));
    }

    public static int[] selectionSort(int[] source) {
        int[] arr = source;
        for (int i = 0, len = arr.length; i < len; i++) {
            int minIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int tmp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = tmp;
            }
        }
        return arr;
    }

}
