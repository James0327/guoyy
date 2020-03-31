package com.jw;

import java.util.BitSet;
import java.util.Random;

/**
 * @Description: test com.jw.BitSetTest
 * @Package: com.jw
 * @ClassName: BitSetTest
 * @Author: james.guo
 * @Date: 2019/5/13 20:26
 * @Version: 1.0
 */
public class BitSetTest {

    public static void main(String[] args) {

        Random r = new Random();

        BitSet bs = new BitSet();
        for (int i = 0; i < 10; i++) {
            bs.set(r.nextInt(10000));
        }

        System.out.println("length:" + bs.length()
                + ", size:" + bs.size()
                + ", cardinality:" + bs.cardinality()
                + "][" + bs.toString());

        int i = -1;
        while (true) {
            i = bs.nextSetBit(i + 1);
            if (i == -1) {
                break;
            }
            System.out.print("\t" + i);
        }
    }

}
