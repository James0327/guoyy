package com.jw.james;

import java.util.Random;

/**
 * @Description: test com.jw.ReservoirSamplingUtil
 * @Package: com.jw
 * @ClassName: ReservoirSamplingUtil
 * @Author: james.guo
 * @Date: 2019/6/21 15:01
 * @Version: 1.0
 */
public class ReservoirSamplingUtil {
	private static final Random random = new Random();

	/**
	 * @param items 样本空间
	 * @param rate  抽样比例
	 * @return
	 */
	public int[] sampling(int[] items, double rate) {
		int len = items.length;
		double d = len * rate;
		System.out.println(d);
		int n = (int) Math.floor(len * rate);
		return sampling(items, n);
	}

	/**
	 * @param items 样本空间
	 * @param n     采样
	 * @return
	 */
	public int[] sampling(int[] items, int n) {
		int len = items.length;
		if (n >= len) {
			return items;
		}

		int[] ret = new int[n];
		// 前n个数 存入ret
		for (int i = 0; i < n; i++) {
			ret[i] = items[i];
		}
		// 从n+1 个数开始概率采样
		for (int i = n + 1; i < len; i++) {
			int r = random.nextInt(i);
			if (r < n) {
				ret[r] = items[i - 1];
			}
		}

		return ret;
	}

}
