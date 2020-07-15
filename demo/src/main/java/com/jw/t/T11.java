package com.jw.t;

import java.util.regex.Pattern;

/**
 * guoyy com.jw.t
 *
 * @Description: com.jw.t.T11
 * @Author: guoyiyong/james
 * @Date: 2020-07-14 15:58
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class T11 {
    private static final Pattern JOB_REGEX = Pattern.compile("[a-zA-Z]|([a-zA-Z]+[\\w]*[a-zA-Z]+)");

    public static void main(String[] args) {
        String[] arr = {"a", "aa", "aaadddd", "aaa3ddd33fff", "0", "a9", "9a"};

        for (String val : arr) {
            boolean matches = JOB_REGEX.matcher(val).matches();
            System.out.println(String.format("val:%s,%s", val, matches));
        }

    }

}
