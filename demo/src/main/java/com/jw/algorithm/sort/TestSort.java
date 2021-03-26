package com.jw.algorithm.sort;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

/**
 * Description: guoyy
 * com.jw.algorithm.sort.Test
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/3/18 16:29
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class TestSort {

    @Test
    public void test() {
        int[] arr = {12, 3710, 34, 70, 999, 14, 312, 0, 0, 413, 413, 14, 9};

        testBubble(arr);
        testSelection(arr);
        testInsertion1(arr);
        testInsertion2(arr);
        testMergeSort(arr);
    }

    private void testMergeSort(int[] arr0) {
        int[] arr = mergeSort(arr0);
        System.out.println("arr: " + JSON.toJSONString(arr));
    }

    private int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, right.length);

        // System.out.println("arr:" + JSON.toJSONString(arr));
        // System.out.println("left:" + JSON.toJSONString(left));
        // System.out.println("right:" + JSON.toJSONString(right));

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private int[] merge(int[] upArr, int[] downArr) {
        int upIdx = 0, downIdx = 0, idx = 0;
        int upLen = upArr.length, downLen = downArr.length;
        int upMaxIdx = upLen - 1, downMaxIdx = downLen - 1;
        int[] ret = new int[upLen + downLen];
        while (true) {
            if (upIdx >= upLen && downIdx >= downLen) {
                break;
            }
            // 如果downArr元素已全部合并，则把upArr剩余元素直接合并
            if (downIdx > downMaxIdx) {
                System.arraycopy(upArr, upIdx, ret, idx, upLen - upIdx);
                break;
            }
            // 如果upArr元素已全部合并，则把downArr剩余元素直接合并
            if (upIdx > upMaxIdx) {
                System.arraycopy(downArr, downIdx, ret, idx, downLen - downIdx);
                break;
            }
            // up > down 放入 down元素，否则放入up元素
            ret[idx++] = upArr[upIdx] > downArr[downIdx] ? downArr[downIdx++] : upArr[upIdx++];
        }
        return ret;
    }

    private void testInsertion2(int[] arr) {
        for (int i = 1, len = arr.length; i < len; i++) {
            // 当前待排序数据
            int p = arr[i];
            // 初始比较位置（即当前位置的前一位）
            int j = i - 1;
            // 当前位置往前看 比较的元素比当前元数大则后移一位，否则跳出（即找到待插入的位置）
            for (; j >= 0; j--) {
                if (p >= arr[j]) {
                    break;
                }
                arr[j + 1] = arr[j];
            }
            // 有数据移动，插入当前位置
            if (j + 1 < i) {
                arr[j + 1] = p;
            }
        }
        System.out.println("arr: " + JSON.toJSONString(arr));
    }

    private void testInsertion1(int[] arr) {
        for (int i = 1, len = arr.length; i < len; i++) {
            // 当前待排序数据
            int p = arr[i];
            for (int j = 0; j < i; j++) {
                // 找到待插入位置
                if (p < arr[j]) {
                    // 有序数据中：待插入位置后的数据后移一格，并将待插入数据插入
                    for (int k = i; k > j; k--) {
                        arr[k] = arr[k - 1];
                    }
                    arr[j] = p;
                    break;
                }
            }
        }
        System.out.println("arr: " + JSON.toJSONString(arr));
    }

    private void testSelection(int[] arr) {
        for (int i = 0, len = arr.length; i < len; i++) {
            // 最小值索引
            int minIdx = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }
            // 交换
            if (i != minIdx) {
                arr[i] = arr[i] ^ arr[minIdx];
                arr[minIdx] = arr[i] ^ arr[minIdx];
                arr[i] = arr[i] ^ arr[minIdx];
            }
        }
        System.out.println("arr: " + JSON.toJSONString(arr));
    }

    private void testBubble(int[] arr) {
        for (int i = 0, len = arr.length; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] > arr[j]) {
                    // 交换
                    arr[i] = arr[i] ^ arr[j];
                    arr[j] = arr[i] ^ arr[j];
                    arr[i] = arr[i] ^ arr[j];
                }
            }
        }
        System.out.println("arr: " + JSON.toJSONString(arr));
    }

}
