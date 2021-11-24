package com.jw.james.algorithm;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Description: guoyy
 * com.jw.algorithm.StringCheckInclusion
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/4/5 07:18
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class StringCheckInclusion {

    public static void main(String[] args) {
        StringCheckInclusion obj = new StringCheckInclusion();

        String src = "HELLOWORLD";
        String target = "OOW";

        boolean ret = obj.algorithm(src, target);

        System.out.println("ret: " + ret);
    }

    private boolean algorithm(String src, String target) {
        int srcLen = src.length();
        int targetLen = target.length();

        if (srcLen < targetLen) {
            return false;
        }

        Map<Character, Integer> needsMap = Maps.newHashMapWithExpectedSize(targetLen);
        for (int i = 0; i < targetLen; i++) {
            needsMap.put(target.charAt(i), 1);
        }
        Map<Character, Integer> validatedMap = Maps.newHashMapWithExpectedSize(targetLen);

        int left = 0, right = 0;
        int start = 0, len = Integer.MAX_VALUE;

        while (right < srcLen) {
            char ch = src.charAt(right++);
            if (needsMap.containsKey(ch)) {
                if (validatedMap.containsKey(ch)) {
                    validatedMap.put(ch, validatedMap.get(ch) + 1);
                } else {
                    validatedMap.put(ch, 1);
                }
            }

            System.out.println("cache:" + JSON.toJSONString(validatedMap));
            System.out.println(String.format("windown:[%d,%d).", left, right));

            while (right - left >= targetLen) {
                if (needsMap.size() == validatedMap.size()) {
                    return true;
                }
                char c = src.charAt(left++);
                if (needsMap.containsKey(c)) {
                    Integer cnt = validatedMap.get(c);
                    if (cnt <= 1) {
                        validatedMap.remove(c);
                    } else {
                        validatedMap.put(c, cnt - 1);
                    }
                }
            }
        }

        return false;
    }
}
