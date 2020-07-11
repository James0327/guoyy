package com.jw.thread.queue;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.LockSupport;

/**
 * guoyy com.jw.thread.queue
 *
 * @Description: MyTest
 * @Author: guoyiyong/james
 * @Date: 2020-07-02 19:51
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class MyTest {

    class Person {
        private long id;
        private int age;
        private String addr;
        private String name;
        private boolean man;
        private double weight;
        private double height;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = StringUtils.trimToEmpty(addr);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = StringUtils.trimToEmpty(name);
        }

        public boolean isMan() {
            return man;
        }

        public void setMan(boolean man) {
            this.man = man;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    Person obj = queue.take();
                    System.out.println("object：" + JSON.toJSONString(obj));
                    LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(ThreadLocalRandom.current().nextInt(2, 6)));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Producter implements Runnable {
        @Override
        public void run() {
            try {
                int size = 10;
                for (int i = 0; i < size; i++) {
                    Person person = new Person();
                    person.setId(idGenerator.intValue());
                    person.setName(Thread.currentThread().getName());
                    System.out.println("producter: " + JSON.toJSONString(person));
                    queue.put(person);
                    idGenerator.increment();
                    TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(9));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final SynchronousQueue<Person> queue = new SynchronousQueue<>();
    private final LongAdder idGenerator = new LongAdder();

    @Test
    public void test() {
        new Consumer().start();
        new Thread(new Producter(), "p1").start();
        new Thread(new Producter(), "p2").start();

        LockSupport.park();
    }

}
