package com.jw.james.thread.sync;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description: test T2
 * @Package: com.jw.thread.sync
 * @ClassName: T2
 * @Author: james.guo
 * @Date: 2019/10/30 10:36
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class T2 {
	public static void main(String[] args) {
		final LocalDateTime start = LocalDateTime.now();

		int cpu = Runtime.getRuntime().availableProcessors();
		BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("T2-%d").build();
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10 * cpu, 30 * cpu, 6, TimeUnit.SECONDS, new LinkedBlockingQueue<>(500 * cpu), threadFactory, new ThreadPoolExecutor.AbortPolicy());

		List<Integer> list = IntStream.range(1, 30).boxed().collect(Collectors.toList());
		Semaphore semaphore = new Semaphore(0);

		CompletableFuture<ImmutablePair<Boolean, Exception>>[] futures = list.stream().map(task -> {
			CompletableFuture<ImmutablePair<Boolean, Exception>> future = CompletableFuture.supplyAsync(() -> {
				System.out.println("Thread name: " + Thread.currentThread().getName());
				try {
					if (task == 11) {
						semaphore.release(list.size());
					} else {
						try {
							// LockSupport.parkNanos(1000000000);
							Thread.sleep(10000);
						} catch (Exception e) {
							System.err.println(e.toString());
						}
					}
					return ImmutablePair.of(true, null);
				} catch (Exception e) {
					semaphore.release(list.size());
					e.printStackTrace();
					System.err.println("Thread name err: " + Thread.currentThread().getName());
					return ImmutablePair.of(false, e);
				} finally {
					semaphore.release();
				}
			}, threadPool);
			return future;
		}).toArray(size -> new CompletableFuture[size]);

		try {
			boolean r = semaphore.tryAcquire(list.size(), 3, TimeUnit.SECONDS);
			System.out.println("r: " + r);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Arrays.stream(futures).forEach(f -> f.cancel(true));

		LocalDateTime end = LocalDateTime.now();

		System.out.println(Duration.between(start, end).toMillis());

		for (int i = 0, len = futures.length; i < len; i++) {
			boolean cancelled = futures[i].isCancelled();
			System.out.println("cancelled: " + cancelled + "/" + i);
			if (!cancelled) {
				try {
					Pair<Boolean, Exception> pair = futures[i].get();
					System.out.println(String.format("%s r:%s,e:%s",
							ToStringBuilder.reflectionToString(futures[i]), pair.getLeft(), pair.getValue()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		threadPool.shutdown();
		threadPool.shutdownNow();

	}
}
