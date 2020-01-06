package com.jw.algorithm.bitset;

import java.util.BitSet;

/**
 * guoyy com.jw.algorithm.bitset
 *
 * @Description: com.jw.algorithm.bitset.BitSetExt
 * @Author: guoyiyong/james
 * @Date: 2019-12-03 10:50
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class BitSetExt {
    private BitSet lh = new BitSet();
    private BitSet ll = new BitSet();
    private BitSet i = new BitSet();

    public void set(long num) {
        if (num <= Integer.MAX_VALUE) {
            i.set((int)num);
            return;
        }
        if (num > 0x3FFFFFFFFFFFFFFFL /* maxId */) {
            throw new RuntimeException("数值太大，超过了0x3FFFFFFFFFFFFFFFL");
        }
        int n1 = (int)(num >> 31);
        int n2 = (int)(num & 0x7FFFFFFF);

        lh.set(n1);
        ll.set(n2);
    }

}
