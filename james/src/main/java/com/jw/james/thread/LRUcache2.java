package com.jw.james.thread;

import java.util.concurrent.ThreadLocalRandom;
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
public class LRUcache2 {
	private final int[] vals;
	private final int size;
	private volatile int offset = 0;

	public LRUcache2(int size) {
		this.size = size;
		vals = new int[size];
	}

	public synchronized boolean cache(int val) {
		int i = 0;
		boolean miss = false;
		for (; i < offset; i++) {
			if (val == vals[i]) {
				miss = true;
				break;
			}
		}
		if (miss) {
			for (int j = i; j < offset - 1; j++) {
				vals[j] = vals[j + 1];
			}
			vals[offset - 1] = val;
			return true;
		}
		if (offset < size) {
			vals[offset++] = val;
		} else {
			System.arraycopy(vals, 1, vals, 0, size - 1);
			vals[offset - 1] = val;
		}
		return false;
	}

	public static void main(String[] args) {
		LRUcache2 cache = new LRUcache2(100);
		LongAdder cnt = new LongAdder();
		int total = 1000000;
		for (int i = 1; i < total; i++) {
			int i0 = ThreadLocalRandom.current().nextInt(1, total);
			boolean flag = cache.cache(i0);
			if (flag) {
				cnt.increment();
			}
		}

		System.out.println(String.format("cnt/total:%s/%s:%s", cnt, total, cnt.sum() / (total + 0D)));
	}

}
