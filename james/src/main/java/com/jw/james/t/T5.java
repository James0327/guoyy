package com.jw.james.t;

/**
 * @Description: test T5
 * @Package: com.jw
 * @ClassName: T5
 * @Author: james.guo
 * @Date: 2019/3/19 11:21
 * @Version: 1.0
 */
public class T5 {
    public static void main(String[] args) {
        for (int i = 0; i < 2999; i++) {
            T5.t(i);
        }
    }

    private static void t(long rt) {
        String key4 = rt <= 20 ? "0-20" : rt <= 50 ? "21-50" : rt <= 100 ? "51-100" : rt <= 300 ? "101-300" : rt <= 1000 ? "301-1000" : rt <= 2000 ? "1001-2000" : "2000+";
        System.out.println(String.format("rt:%s, key4:%s.", rt, key4));
    }

}
