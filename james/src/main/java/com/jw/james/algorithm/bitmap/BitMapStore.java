package com.jw.james.algorithm.bitmap;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * guoyy com.jw.algorithm.bitmap
 *
 * @Description: com.jw.algorithm.bitmap.BitMapStore
 * @Author: guoyiyong/james
 * @Date: 2020-07-08 16:52
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class BitMapStore {

    @Test
    public void test() {
        final int size = 19;

        for (int i = 0; i <= size; i++) {
            add(i);
        }
        System.out.println("20: " + contain(20));
        System.out.println("16: " + contain(16));

        System.out.println(Arrays.toString(getBooleanArray(bitArr[0])));
        System.out.println(Arrays.toString(getBooleanArray(bitArr[1])));
        System.out.println(Arrays.toString(getBooleanArray(bitArr[2])));
        System.out.println(Arrays.toString(getBooleanArray(bitArr[3])));
        System.out.println(StringUtils.leftPad("=", 100, "="));

        for (int i = size; i >= 0; i--) {
            clear(i);
        }
        System.out.println(Arrays.toString(getBooleanArray(bitArr[0])));
        System.out.println(Arrays.toString(getBooleanArray(bitArr[1])));
        System.out.println(Arrays.toString(getBooleanArray(bitArr[2])));
        System.out.println(Arrays.toString(getBooleanArray(bitArr[3])));

    }

    /**
     * 一个int 4个字节，一个字节8bit 每个bit可以表示一个数字是否存在
     * 也就是说一个byte能存8个数，如果16个数 正好需要2byte byte[0]存0~7；byte[1]存8~16个数
     */
    private byte[] bitArr = new byte[8];

    public void clear(int i) {
        bitArr[idx(i)] &= ~(1 << position(i));
    }

    public void add(int i) {
        bitArr[idx(i)] |= 1 << position(i);
    }

    public boolean contain(int i) {
        return ((bitArr[idx(i)] >> position(i)) & 1) == 1;
    }

    /**
     * 存哪个byte
     */
    private int idx(int i) {
        return i >> 3;
    }

    /**
     * byte中的第几位(从右往左数)
     */
    private int position(int i) {
        return i & (0x07);
    }

    private byte[] getBooleanArray(byte b) {
        byte[] ret = new byte[8];
        for (int i = 7; i >= 0; i--) {
            ret[i] = (byte)(b & 1);
            b = (byte)(b >> 1);
        }
        return ret;
    }

}
