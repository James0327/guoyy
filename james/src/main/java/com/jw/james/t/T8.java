package com.jw.james.t;

import java.util.BitSet;

/**
 * @Description: test T8
 * @Package: com.jw
 * @ClassName: T8
 * @Author: james.guo
 * @Date: 2019/4/19 18:41
 * @Version: 1.0
 */
public class T8 {

    public static void main(String[] args) {
        int i1 = 0x01;
        int i2 = 0x10;

        System.out.println((i1 & i2) + "][" + Integer.toHexString(i1 & i2));
        System.out.println((i1 | i2) + "][" + Integer.toHexString(i1 | i2));

        BitSet bs = new BitSet(2);
        System.out.println(bs.get(0));
        System.out.println(bs.get(1));

        String str = "aaaa_bbbbb_cccc_dddd_eeee_fffff";

        BitSet flag = new BitSet();

        int cardinality = flag.cardinality();

        System.out.println(flag);
        System.out.println(cardinality);

        flag.set(0);
        flag.set(3);
        flag.set(5);
        flag.set(7);

        BitSet flag2 = new BitSet();
        flag2.set(7);
        flag.and(flag2);

        System.out.println(flag);

        BitSet flag3 = new BitSet();
        flag.and(flag3);

        System.out.println(flag + "][" + flag.isEmpty());

    }

}
