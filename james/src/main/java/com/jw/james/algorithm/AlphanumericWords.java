package com.jw.james.algorithm;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Map;

/**
 * Description: guoyy
 * com.jw.algorithm.AlphanumericWords
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/4/5 08:40
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class AlphanumericWords {

    public static void main(String[] args) {
        AlphanumericWords obj = new AlphanumericWords();

        String src = "cbaebabacd";
        String target = "abc";

        List<Integer> ret = obj.algorithm(src, target);

        System.out.println("ret: " + JSON.toJSONString(ret));
    }

    private List<Integer> algorithm(String src, String target) {
        List<Integer> ret = Lists.newArrayList();

        int srcLen = src.length();
        int targetLen = target.length();

        if (srcLen < targetLen) {
            return ret;
        }

        int left = 0, right = 0;
        int start = 0, len = Integer.MAX_VALUE;

        Map<Character, Integer> needsMap = Maps.newHashMapWithExpectedSize(targetLen);
        Map<Character, Integer> validMap = Maps.newHashMapWithExpectedSize(targetLen);

        for (int i = 0; i < targetLen; i++) {
            needsMap.put(target.charAt(i), 1);
        }

        while (right < srcLen) {
            char rc = src.charAt(right++);
            if (needsMap.containsKey(rc)) {
                if (validMap.containsKey(rc)) {
                    validMap.put(rc, validMap.get(rc) + 1);
                } else {
                    validMap.put(rc, 1);
                }
            }

            while (right - left >= targetLen) {
                if (validMap.size() == needsMap.size()) {
                    ret.add(left);
                }
                char lc = src.charAt(left++);
                if (needsMap.containsKey(lc)) {
                    Integer cnt = validMap.get(lc);
                    if (cnt == 1) {
                        validMap.remove(lc);
                    } else {
                        validMap.put(lc, cnt - 1);
                    }
                }
            }
        }

        return ret;
    }

}
