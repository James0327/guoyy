package com.jw.james.algorithm;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Description: guoyy
 * com.jw.algorithm.MinCoverString
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/4/4 23:43
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class MinCoverString {

    public static void main(String[] args) {
        MinCoverString obj = new MinCoverString();

        String src = "ADBECFEBANC";
        String target = "ABC";

        String ret = obj.algorithm(src, target);

        System.out.println("ret: " + ret);
    }

    private String algorithm(String src, String target) {
        int srcLen = src.length();
        int targetLen = target.length();
        if (srcLen < targetLen) {
            return "";
        }

        Map<Character, Integer> needsMap = Maps.newHashMapWithExpectedSize(targetLen);
        for (int i = 0; i < targetLen; i++) {
            needsMap.put(target.charAt(i), 1);
        }
        Map<Character, Integer> validatedMap = Maps.newHashMapWithExpectedSize(targetLen);

        int slow = 0, fast = 0;
        int start = 0, len = Integer.MAX_VALUE;

        while (fast < srcLen) {
            char ch = src.charAt(fast++);
            if (needsMap.containsKey(ch)) {
                if (validatedMap.containsKey(ch)) {
                    validatedMap.put(ch, validatedMap.get(ch) + 1);
                } else {
                    validatedMap.put(ch, 1);
                }
            }

            System.out.println("cache:" + JSON.toJSONString(validatedMap));
            System.out.println(String.format("windown:[%d,%d).", slow, fast));

            // [0,fast) 区间包含了所有的target字符
            while (needsMap.size() == validatedMap.size()) {
                // 更新子串
                if (fast - slow < len) {
                    start = slow;
                    len = fast - slow;
                }
                // 缩小左边界 slow指针往右移动
                char c = src.charAt(slow++);
                if (needsMap.containsKey(c)) {
                    Integer cnt = validatedMap.get(c);
                    if (cnt == 1) {
                        validatedMap.remove(c);
                    } else {
                        validatedMap.put(c, cnt - 1);
                    }
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : new String(src.getBytes(), start, len);
    }

}
