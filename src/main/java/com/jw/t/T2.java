package com.jw.t;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.LongAdder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: test com.jw.t.T2
 * @Package: com.jw
 * @ClassName: T2
 * @Author: james.guo
 * @Date: 2019/3/14 17:58
 * @Version: 1.0
 */
public class T2 {
	private static class Couter {
		private LongAdder longAdder = new LongAdder();

		public void incre() {
			longAdder.increment();
		}

		public void decre() {
			longAdder.decrement();
		}

		public Long get() {
			return longAdder.sum();
		}
	}

	private static final Pattern compile = Pattern.compile("[a-zA-Z]{3}(?!_)");

	public static void main(String[] args) {
		String str = "bag_xmn-mf,mf-can/can-nh,nh-nrt|hnd-nh,nh-can/can-mf,mf-xmn";
		Matcher matcher = compile.matcher(str);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
		str = "b_can-cz,cz-cdg/cdg-af,af-tlv|tlv-su,su-svo/svo-cz,cz-can";
		matcher = compile.matcher(str);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}

		int idx = str.indexOf("_");
		matcher.find(idx);
		String src = str.substring(idx + 1);
		String[] strings = StringUtils.split(src, "-");

		System.out.println(strings);

		Map<Integer, Couter> map = Maps.newHashMap();

		int size = 3;

		for (int i = 0; i < size; i++) {
			map.put(i, new Couter());
		}

		Random r = new Random();
		for (int i = 0; i < 1000; i++) {
			map.get(r.nextInt(size)).incre();
			map.get(r.nextInt(size)).decre();
		}

		for (int i = 0; i < size; i++) {
			System.out.println("i:" + map.get(i).get());
		}

	}
}
