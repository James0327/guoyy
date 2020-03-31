package com.jw.thread.sync;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description: test com.jw.thread.sync.T1
 * @Package: com.jw.thread.sync
 * @ClassName: T1
 * @Author: james.guo
 * @Date: 2019/10/28 16:46
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class T1 {

	public static void main(String[] args) {
		int cpu = Runtime.getRuntime().availableProcessors() * 10;

		BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("pool-%d").build();

		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(cpu, 2 * cpu, 6, TimeUnit.MINUTES,
				new LinkedBlockingQueue<>(1000 * cpu), threadFactory, new ThreadPoolExecutor.AbortPolicy());

		LocalDateTime start = LocalDateTime.now();

		List<Integer> list = IntStream.range(1, 30).boxed().collect(Collectors.toList());
		CountDownLatch latch = new CountDownLatch(list.size());

		CompletableFuture<ImmutablePair<Boolean, Exception>>[] futures = list.stream().map(task -> {
			CompletableFuture<ImmutablePair<Boolean, Exception>> future = CompletableFuture.supplyAsync(() -> {
				System.out.println("Thread name: " + Thread.currentThread().getName());
				try {
					if (task == 11) {
						long i = latch.getCount();
						for (int j = 0; j < i; j++) {
							latch.countDown();
						}

						latch.countDown();
					} else {
						// LockSupport.parkNanos(1000000000);
						Thread.sleep(10000);
					}
					return ImmutablePair.of(true, null);
				} catch (Exception e) {
					System.err.println("Thread name err: " + Thread.currentThread().getName());
					return ImmutablePair.of(false, e);
				} finally {
					latch.countDown();
				}
			}, threadPool);
			return future;
		}).toArray(size -> new CompletableFuture[size]);

		try {
			boolean r = latch.await(3, TimeUnit.SECONDS);
			System.out.println("r: " + r);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Arrays.stream(futures).forEach(f -> f.cancel(true));

		LocalDateTime end = LocalDateTime.now();

		System.out.println(Duration.between(start, end).toMillis());

		System.out.println(ToStringBuilder.reflectionToString(ForkJoinPool.commonPool()));

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

	}
}
