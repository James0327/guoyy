package com.jw;

import java.util.BitSet;

/**
 * @Description: test com.jw.BitSetDemo
 * @Package: com.jw
 * @ClassName: BitSetDemo
 * @Author: james.guo
 * @Date: 2019/3/18 11:07
 * @Version: 1.0
 */
public class BitSetDemo {
    /**
     * BitSet是位操作的对象，值只有0或1即false和true，内部维护了一个long数组，初始只有一个long，所以BitSet最小的size是64，当随着存储的元素越来越多，BitSet内部会动态扩充，最终内部是由N个long来存储，这些针对操作都是透明的。
     * <p>
     * 用1位来表示一个数据是否出现过，0为没有出现过，1表示出现过。使用用的时候既可根据某一个是否为0表示，此数是否出现过。
     * <p>
     * 一个1G的空间，有 8*1024*1024*1024=8.58*10^9bit，也就是可以表示85亿个不同的数
     * <p>
     * 使用场景
     * 常见的应用是那些需要对海量数据进行一些统计工作的时候，比如日志分析、用户数统计等等*
     * 如统计40亿个数据中没有出现的数据，将40亿个不同数据进行排序等。
     * 现在有1千万个随机数，随机数的范围在1到1亿之间。现在要求写出一种算法，将1到1亿之间没有在随机数中的数求出来
     */
    public static void main(String[] args) {

        BitSetTest.containChars("adfal;jaldskjflkjfal;kdfj123401834147304712380000abcd");

        BitSetTest.computePrime();

        BitSetTest.sortArray();
    }

    private static class BitSetTest {
        /**
         * 求一个字符串包含的不重复的字符
         *
         * @param str
         */
        public static void containChars(String str) {
            BitSet used = new BitSet();

            for (int i = 0, len = str.length(); i < len; i++) {
                used.set(str.charAt(i));
            }

            StringBuilder strB = new StringBuilder();
            strB.append("[");
            int size = used.size();
            for (int i = 0; i < size; i++) {
                if (used.get(i)) {
                    strB.append((char) i);
                }
            }
            strB.append("]");
            System.out.println(strB.toString());
        }

        /**
         * 求素数 有无限个。一个大于1的自然数，如果除了1和它本身外，
         * 不能被其他自然数整除(除0以外）的数称之为素数(质数） 否则称为合数
         */
        private static void computePrime() {
            BitSet sieve = new BitSet(1024);
            int size = sieve.size();
            for (int i = 2; i < size; i++) {
                sieve.set(i);
            }
            int finalBit = (int) Math.sqrt(sieve.size());

            for (int i = 2; i < finalBit; i++) {
                if (sieve.get(i)) {
                    // 去除 i 的倍数
//                    for (int j = 2 * i; j < size; j += i) {
//                        sieve.clear(j);
//                    }
                    for (int j = 2, len = size / i; j < len; j++) {
                        sieve.clear(j * i);
                    }
                }
            }

            int counter = 0;
            for (int i = 1; i < size; i++) {
                if (sieve.get(i)) {
                    System.out.printf("%5d", i);
                    if (++counter % 15 == 0) {
                        System.out.println();
                    }
                }
            }
            System.out.println();
        }

        private static void sortArray() {
            int[] array = new int[]{423, 700, 9999, 2323, 356, 6400, 99, 0, 2, 3, 2, 2, 2, 2};
            BitSet bitSet = new BitSet(2 << 13);
            // 虽然可以自动扩容，但尽量在构造时指定估算大小,默认为64
            System.out.println("BitSet size: " + bitSet.size());

            for (int i = 0, len = array.length; i < len; i++) {
                bitSet.set(array[i]);
            }
            System.out.println(bitSet);

            // 剔除重复数字后的元素个数
            int bitLen = bitSet.cardinality();
            System.out.println(String.format("logical size:%s, bits of space actually in use:%s, bitLen:%s.", bitSet.length(), bitSet.size(), bitLen));

            int[] orderedArr = new int[bitLen];

            // nextSetBit:
            // Returns the index of the first bit that is set to {@code true}
            // that occurs on or after the specified starting index. If no such
            // bit exists then {@code -1} is returned.

//            int k = 0;
//            for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
//                System.out.println("bitSet.nextSetBit(" + i + ")=>" + bitSet.nextSetBit(i));
//                orderedArr[k++] = i;
//            }

            int rIdx = 0, wIdx = 0;
            while (true) {
                rIdx = bitSet.nextSetBit(rIdx);
                if (rIdx < 0) {
                    break;
                }
                orderedArr[wIdx++] = rIdx++;
            }

            for (int i = 0; i < bitLen; i++) {
                System.out.print(orderedArr[i] + "\t");
            }
            System.out.println();

            System.out.println("iterate over the true bits in a BitSet");
            //或直接迭代BitSet中bit为true的元素iterate over the true bits in a BitSet
            for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
                System.out.print(i + "\t");
            }
            System.out.println("---------------------------");
        }

    }


}
