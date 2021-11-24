package com.jw.james.algorithm.sort;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description: test QuickSort
 * @Package: com.jw.t.algorithm
 * @ClassName: QuickSort
 * @Author: james.guo
 * @Date: 2019/9/23 17:54
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class QuickSort {

    @Test
    public void test() {
        int[] arr = ThreadLocalRandom.current().ints(20, 0, 1000).toArray();
        System.out.println(ToStringBuilder.reflectionToString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println(ToStringBuilder.reflectionToString(arr));
    }

    /**
     * 快速排序（3数取中值）
     *
     * @param arr
     * @param left
     * @param right
     */
    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            dealPivot(arr, left, right);
            int pivot = right - 1;
            int i = left;
            int j = right - 1;
            while (true) {
                while (arr[++i] < arr[pivot] && i < right) {
                }
                while (j > left && arr[--j] > arr[pivot]) {
                }
                if (i < j) {
                    swap(arr, i, j);
                } else {
                    break;
                }
            }
            if (i < right) {
                swap(arr, i, right - 1);
            }
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }

    private void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[mid] > arr[right]) {
            swap(arr, mid, right);
        }
        swap(arr, mid, right - 1);
    }

    private void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

}
