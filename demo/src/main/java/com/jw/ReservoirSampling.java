package com.jw;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * @Description: test com.jw.ReservoirSampling
 * @Package: com.jw
 * @ClassName: ReservoirSampling
 * @Author: james.guo
 * @Date: 2019/6/21 15:01
 * @Version: 1.0
 */
public class ReservoirSampling {

	public static void main(String[] args) {

		ReservoirSamplingUtil util = new ReservoirSamplingUtil();

		int[] items = IntStream.range(1000, 100000).toArray();

		util.sampling(items, 0.99999999999D);

		Map<Integer, LongAdder> map = Maps.newHashMap();

		for (int i = 0; i < 100000; i++) {
			int[] ret = util.sampling(items, 20);
			Arrays.stream(ret).forEach(k -> {
				LongAdder longAdder = map.putIfAbsent(k, new LongAdder());
				if (longAdder == null) {
					longAdder = map.get(k);
				}
				longAdder.increment();
			});
		}

		map.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
	}

}
