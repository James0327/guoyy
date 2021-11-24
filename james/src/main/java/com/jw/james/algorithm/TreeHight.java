package com.jw.james.algorithm;

import org.junit.jupiter.api.Test;

/**
 * Description: guoyy
 * com.jw.algorithm.TreeHight
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/4/12 21:14
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class TreeHight {

    @Test
    public void test() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");

        a.left = b;
        a.right = c;

        c.left = d;
        c.right = e;

        e.left = f;
        f.right = g;

        int h = scan(a);

        System.out.println("h: " + h);
    }

    private int scan(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int left = scan(node.left);
        int right = scan(node.right);
        return (left > right ? left : right) + 1;
    }

    private class Node {
        private String val;
        private Node left, right;

        public Node(String val) {
            this.val = val;
        }

        public void print() {
            System.out.println("val:" + val);
        }
    }

}
