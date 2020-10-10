package com.jw.algorithm.sort;

import org.apache.commons.lang3.StringUtils;

/**
 * guoyy com.jw.algorithm.sort
 *
 * @Description: com.jw.algorithm.sort.BubbleSort
 * @Author: guoyiyong/james
 * @Date: 2020-09-24 14:36
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] source = {12, 3710, 34, 70, 14, 312, 0, 413, 413, 14};
        int[] arr = bubbleSort(source);
        System.out.println(StringUtils.join(arr, ' '));
    }

    public static int[] bubbleSort(int[] source) {
        int[] arr = source;
        for (int i = 0, len = arr.length; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[i]) {
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
        return arr;
    }

}
