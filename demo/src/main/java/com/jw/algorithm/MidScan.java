package com.jw.algorithm;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * guoyy com.jw.algorithm
 *
 * @Description: com.jw.algorithm.MidScan
 * @Author: guoyiyong/james
 * @Date: 2020-07-26 16:07
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class MidScan {
    public class Node {
        private Node left;
        private Node right;
        private String value;
    }

    @Test
    public void test() {
        /**
         *        a
         *      /    \
         *     b      c
         *    /  \   /
         *   d   e  f
         *    \      \
         *     g     h
         */
        List<String> ret = Lists.newArrayList();
        Node root = new Node();
        root.value = "a";
        Node b = new Node();
        b.value = "b";
        Node c = new Node();
        c.value = "c";
        Node d = new Node();
        d.value = "d";
        Node e = new Node();
        e.value = "e";
        Node f = new Node();
        f.value = "f";
        Node g = new Node();
        g.value = "g";
        Node h = new Node();
        h.value = "h";

        root.left = b;
        root.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        d.right = g;
        f.right = h;

        print(ret, root);

        System.out.println(ret);
    }

    private void print(List<String> ret, Node root) {
        Node left = root.left;
        if (left != null) {
            print(ret, left);
        }
        ret.add(root.value);
        Node ritht = root.right;
        if (ritht != null) {
            print(ret, ritht);
        }
    }

}
