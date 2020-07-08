package com.jw.thread;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description: test LRUcache
 * @Package: com.jw.thread
 * @ClassName: LRUcache
 * @Author: james.guo
 * @Date: 2019/8/12 20:08
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class LRUcache {
	private volatile int[] vals;
	private final int size;
	private final AtomicInteger offset = new AtomicInteger();

	public LRUcache(int size) {
		this.size = size;
		vals = new int[size + 1];
	}

	public synchronized boolean cache(int val) {
		int i = 0;
		boolean miss = false;
		for (; i < offset.get(); i++) {
			if (val == vals[i]) {
				miss = true;
				break;
			}
		}
		if (miss) {
			for (int j = i; j < offset.get(); j++) {
				vals[j] = vals[j + 1];
			}
			vals[offset.get() - 1] = val;
			return true;
		}
		if (offset.get() < size) {
			vals[offset.getAndIncrement()] = val;
		} else {
			System.arraycopy(vals, 1, vals, 0, size - 1);
			vals[offset.get() - 1] = val;
		}
		return false;
	}

	public static void main(String[] args) {
		LRUcache cache = new LRUcache(1000);
		LongAdder cnt = new LongAdder();
		int total = 1000000;
		for (int i = 0; i < total; i++) {
			int i1 = ThreadLocalRandom.current().nextInt(1, 5000);
			boolean flag = cache.cache(i1);
			if (flag) {
				cnt.increment();
			}
		}

		System.out.println(String.format("cnt/total:%s/%s:%s", cnt, total, cnt.sum() / (total + 0D)));
	}

}
