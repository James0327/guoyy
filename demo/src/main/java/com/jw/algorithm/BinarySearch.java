package com.jw.algorithm;

import java.util.stream.IntStream;

/**
 * Description: guoyy
 * com.jw.algorithm.BinarySearch
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/4/2 21:07
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();

        int[] nums = new int[]{0, 1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 4, 4, 8, 9};
        int[] arr = IntStream.of(nums).distinct().toArray();

        for (int i = 0, len = arr.length; i < len; i++) {
            int idx1 = binarySearch.search1(nums, arr[i]);
            System.out.println(arr[i] + ", idx1: " + idx1);

            int idx2 = binarySearch.search2(nums, arr[i]);
            System.out.println(arr[i] + ", idx2: " + idx2);

            int idx3 = binarySearch.search3(nums, arr[i]);
            System.out.println(arr[i] + ", idx3: " + idx3);
        }

    }

    /**
     * 最后边的匹配项
     *
     * @param nums
     * @param target
     * @return
     */
    private int search3(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println(String.format("search3 left:%s,right:%s,mid:%s.item:%s.", left, right, mid, nums[mid]));
            if (nums[mid] > target) {
                // mid位置表示第一个大于target的索引
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                left = mid + 1;
            }
        }
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }

    /**
     * 最左边的匹配项
     *
     * @param nums
     * @param target
     * @return
     */
    private int search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println(String.format("search2 left:%s,right:%s,mid:%s.item:%s.", left, right, mid, nums[mid]));
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                // mid位置表示最后一个小于target的索引
                left = mid + 1;
            } else if (nums[mid] == target) {
                right = mid - 1;
            }
        }
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 任意一个匹配项的索引
     *
     * @param nums
     * @param target
     * @return
     */
    private int search1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println(String.format("search1 left:%s,right:%s,mid:%s.item:%s.", left, right, mid, nums[mid]));
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                return mid;
            }
        }
        return -1;
    }

}
