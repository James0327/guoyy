package com.jw.t;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: test Regexp
 * @Package: com.jw.t
 * @ClassName: Regexp
 * @Author: james.guo
 * @Date: 2019/8/27 12:14
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class Regexp {
	private static final Pattern p = Pattern.compile("[^\\d]*(\\d+)(?:\\s*)(件|(?:公斤))");

	@Test
	public void test() {

		String str = "无免费行李额";
		System.out.println(str.contains("免费"));

		List<String> textlist = Lists.newArrayList();
		textlist.add("1件");
		textlist.add("1 件");
		textlist.add("2件");
		textlist.add("2 件");
		textlist.add("23件");
		textlist.add("23 件");
		textlist.add("这个歌2KG");
		textlist.add("这个歌2公斤");
		textlist.add("这个歌23公斤");
		textlist.add("这个歌23 公斤");

		textlist.stream().forEach(e -> match(e));
	}

	private void match(String text) {
		Matcher matcher = p.matcher(text);
		System.out.print(text + ":");
		if (matcher.matches()) {
			System.out.printf("[%s]", matcher.group(1));
			System.out.printf("[%s]", matcher.group(2));
		} else {
			System.out.printf("[%s]", matcher.toMatchResult());
		}

		int groupCount = matcher.groupCount();
		System.out.println("groupCount:" + groupCount);
		if (groupCount <= 1) {
			System.err.println("ERROR");
		}
	}

}
