package com.jw.t;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: test com.jw.t.T3
 * @Package: com.jw
 * @ClassName: T3
 * @Author: james.guo
 * @Date: 2019/3/18 0:40
 * @Version: 1.0
 */
public class T3 {

	public static void main(String[] args) {
		new T3().t();

		List<Integer> list = new ArrayList<>(3);
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

	}

	private int age = 18;
	private String name = "xxx";

	public void setVar(int age) {
		age = 99;
	}

	public void setVar(T3 people) {
		people.age = 100;
	}

	public void t() {
		int age = 28;
		T3 t3 = new T3();
		System.out.println("age=" + age);

		this.setVar(age);
		this.setVar(t3);

		System.out.println("age=" + age);
		System.out.println("t3.age=" + t3.age);
	}

}
