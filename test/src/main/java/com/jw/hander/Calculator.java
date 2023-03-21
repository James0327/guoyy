package com.jw.hander;

import java.util.Scanner;

/**
 * Description: guoyy
 * com.jw.hander.Calculator
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2022/4/1 20:28
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2022 JW All rights reserved.
 */
public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator roboot = new Calculator();
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = roboot.add(a, b);
            System.out.println(c);
        }
    }

    public int add(int a, int b) {
        return a + b;
    }

}
