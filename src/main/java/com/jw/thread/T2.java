package com.jw.thread;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: test com.jw.thread.T2
 * @Package: com.jw.thread
 * @ClassName: T2
 * @Author: james.guo
 * @Date: 2019/7/23 16:13
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class T2 {

	public static void main(String[] args) throws InterruptedException {

		BasicThreadFactory factory = new BasicThreadFactory.Builder().namingPattern("test thread queue-%d").build();
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 5,
				1, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3),
				factory, new ThreadPoolExecutor.CallerRunsPolicy());
		pool.allowCoreThreadTimeOut(true);

		for (int i = 0; i < 3; i++) {
			pool.execute(() -> {
				try {
					System.out.println(Thread.currentThread().getName());
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		for (int i = 0; i < 10; i++) {
			System.out.println(pool.toString());
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println("XXX");

		pool.allowCoreThreadTimeOut(false);

		CompletableFuture.runAsync(() -> {
			try {
				for (int i = 0; i < 300; i++) {
					System.out.println(pool.toString());
					TimeUnit.MILLISECONDS.sleep(50);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		for (int i = 0; i < 20; i++) {
			pool.execute(() -> {
				try {
					System.out.println(Thread.currentThread().getName());
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		try {
			pool.shutdown();
		} catch (Exception e) {
			pool.shutdownNow();
		}

	}

}
