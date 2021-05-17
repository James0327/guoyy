package com.jw.algorithm;

import org.apache.commons.lang3.StringUtils;

/**
 * Description: guoyy
 * com.jw.algorithm.StringReverse
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/4/4 23:20
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class StringReverse {

    public static void main(String[] args) {
        StringReverse obj = new StringReverse();

        String src = "abcdefg";

        String ret = obj.algorithm(src );

        System.out.println("ret: " + ret);
        System.out.println("ret: " + StringUtils.reverse(src));
    }

    private String algorithm(String src) {
        char[] chs = src.toCharArray();
        int left = 0, right = chs.length - 1;
        while (left < right) {
            char tmp = chs[left];
            chs[left] = chs[right];
            chs[right] = tmp;
            left++;
            right--;
        }
        return new String(chs);
    }

}
