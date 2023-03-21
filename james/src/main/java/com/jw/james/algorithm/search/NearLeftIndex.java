package com.jw.james.algorithm.search;

/**
 * Description: guoyy
 * com.jw.james.algorithm.search.NearLeftIndex
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2022/3/12 23:57
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2022 JW All rights reserved.
 */
public class NearLeftIndex {

    public static void main(String[] args) {
        int[] arr = {0, 12, 14, 14, 34, 70, 312, 413, 413, 3710};
        int idx = nearestIdx(arr, 14);
        System.out.println(idx);
    }

    /**
     * 在arr上找满足>=num的最左位置
     */
    private static int nearestIdx(int[] arr, int num) {
        int idx = -1;
        int len = arr.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= num) {
                idx = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return idx;
    }

}
