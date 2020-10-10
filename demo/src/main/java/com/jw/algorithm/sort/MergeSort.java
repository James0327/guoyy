package com.jw.algorithm.sort;

import org.apache.commons.lang3.StringUtils;

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

    public static int[] mergeSort(int[] source) {
        int[] arr = source;

        return arr;
    }

}
