package com.jw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: test com.jw.Fillers
 * @Package: com.jw
 * @ClassName: Fillers
 * @Author: james.guo
 * @Date: 2019/4/23 16:26
 * @Version: 1.0
 */
public class Fillers {

    public static void main(String[] args) throws InterruptedException {
        final int TRAKTORISTOV = 300;
        CountDownLatch cdl = new CountDownLatch(TRAKTORISTOV);
        for (int t = 0; t < TRAKTORISTOV; t++) {
            new Thread(() -> allocateAndWait(cdl)).start();
        }
        cdl.await();
        List<Object> l = new ArrayList<>();
        new Thread(() -> allocateAndDie(l)).start();
    }

    public static void allocateAndWait(CountDownLatch cdl) {
        Object o = new Object();  // 请求一个 TLAB 对象
        cdl.countDown();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                break;
            }
        }
        System.out.println(o); // 使用对象
    }

    public static void allocateAndDie(Collection<Object> c) {
        while (true) {
            c.add(new Object());
        }
    }

}
