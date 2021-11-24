package com.jw.james.random;

import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * @Description: test R
 * @Package: com.jw.random
 * @ClassName: R
 * @Author: james.guo
 * @Date: 2019/6/20 12:00
 * @Version: 1.0
 */
public class R {
	public static int[] reservoir(int[] array, int m) {
		int[] result = new int[m];
		int n = array.length;
		for (int i = 0; i < n; i++) {
			int current_num = array[i];
			if (i < m) {
				result[i] = current_num;
			} else {
				int tmp = RandomUtils.nextInt(0, i + 1);
				if (tmp < m) {
					result[tmp] = current_num;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int i1 = ThreadLocalRandom.current().nextInt();
		System.out.println("i1:" + i1);

		int[] array = IntStream.range(0, 1000).toArray();
		System.out.println("array:" + array);
		int m = 10;
		Map<Integer, Integer> map = new HashMap();
		for (int i = 0; i < 100000; i++) {
			int[] result = reservoir(array, m);
			for (int each : result) {
				map.put(each, map.getOrDefault(each, 0) + 1);
			}
		}
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

	}

}
