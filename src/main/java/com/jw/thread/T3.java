package com.jw.thread;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;

import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description: test com.jw.thread.T3
 * @Package: com.jw.thread
 * @ClassName: T3
 * @Author: james.guo
 * @Date: 2019/8/5 10:28
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class T3 {

	@Test
	public void test() {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS,
				new SynchronousQueue<>(), new BasicThreadFactory.Builder().namingPattern("test-%d").build(),
				new ThreadPoolExecutor.AbortPolicy());

		Future<?> future = executor.submit(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println("! Thread.currentThread().isInterrupted");
				LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100));
			}
			System.out.println("Thread.currentThread().isInterrupted");
		});
		try {
			future.get(1, TimeUnit.SECONDS);
		} catch (Exception e) {
			future.cancel(true);
		}

		executor.shutdown();
		executor.shutdownNow();
	}

}
