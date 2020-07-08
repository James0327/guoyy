package com.jw.comm;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * guoyy com.jw.comm
 *
 * @Description: Test4
 * @Author: guoyiyong/james
 * @Date: 2019-12-16 21:19
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Test4 {

    public static void main(String[] args) {
        String flightTime = "2019-12-16 21:20";
        String year = flightTime.substring(0, 4);
        String month = flightTime.substring(5, 7);
        String day = flightTime.substring(8, 10);
        String hour = flightTime.substring(11, 13);
        String min = flightTime.substring(14, 16);

        System.out.println(String.format("%s,%s,%s,%s,%s", year, month, day, hour, min));

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();

        try {
            writeLock.lock();
            System.out.println("writeLock locked");
        } finally {
            writeLock.unlock();
        }
        try {
            readLock.lock();
            System.out.println("readLock locked");
        } finally {
            readLock.unlock();
        }

    }

}
