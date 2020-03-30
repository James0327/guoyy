package com.jw.t;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description: test com.jw.t.T12
 * @Package: com.jw.t
 * @ClassName: T12
 * @Author: james.guo
 * @Date: 2019/8/9 15:40
 * @Version: 1.0
 *
 * Copyright (C) 2019 JW All rights reserved.
 */
public class T12 {

	public static void main(String[] args) {

		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.MONTH, 11);
		c1.set(Calendar.DAY_OF_MONTH, 1);
		c1.set(Calendar.HOUR_OF_DAY, 10);
		c1.set(Calendar.MINUTE, 20);

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.MONTH, 11);
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.set(Calendar.HOUR_OF_DAY, 5);
		c2.set(Calendar.MINUTE, 13);

		Date d1 = c1.getTime();
		Date d2 = c2.getTime();

		int h = (int) (d1.getTime() - d2.getTime()) / 3600000;
		System.out.println(h);

		int i = 1;
		int j = 1 << 1;
		int k = i | j;
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		System.out.println("==================");
		System.out.println(0 | 1);
		System.out.println(0 | 2);
		System.out.println(1 | 2);

		System.out.println(0 | 0);
		System.out.println(1 | 1);
		System.out.println(2 | 2);

		System.out.println("--------------------------");
		System.out.println((3 & 1));
		System.out.println("--------------------------");
		System.out.println((3 & 2));

	}
}
