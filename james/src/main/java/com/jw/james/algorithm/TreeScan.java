package com.jw.james.algorithm;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * guoyy com.jw.algorithm
 *
 * @Description: com.jw.algorithm.TreeScan
 * @Author: guoyiyong/james
 * @Date: 2020-07-26 16:07
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class TreeScan {
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

        List<String> ret = Lists.newArrayList();
        print(ret, root);
        System.out.println("ret: " + ret);

        List<String> dlr = Lists.newArrayList();
        dlr(dlr, root);
        System.out.println("dlr: " + dlr);

        List<String> ldr = Lists.newArrayList();
        ldr(ldr, root);
        System.out.println("ldr: " + ldr);

        List<String> lrd = Lists.newArrayList();
        lrd(lrd, root);
        System.out.println("lrd: " + lrd);

        List<String> bfs = Lists.newArrayList();
        bfs(bfs, root);
        System.out.println("bfs: " + bfs);
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

    /**
     * 层序遍历：广度优先遍历
     *
     * @param ret
     * @param node
     */
    private void bfs(List<String> ret, Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node n = queue.poll();
            ret.add(n.value);

            Node left = n.left;
            Node right = n.right;
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
        }
    }

    /**
     * LDR--中序遍历（根在中，从左往右，一棵树的左子树永远在根前面，根永远在右子树前面）
     *
     * @param ret
     * @param node
     */
    private void ldr(List<String> ret, Node node) {
        if (node == null) {
            return;
        }
        ldr(ret, node.left);
        ret.add(node.value);
        ldr(ret, node.right);
    }

    /**
     * DLR--前序遍历（根在前，从左往右，一棵树的根永远在左子树前面，左子树又永远在右子树前面 ）
     *
     * @param ret
     * @param node
     */
    private void dlr(List<String> ret, Node node) {
        if (node == null) {
            return;
        }
        ret.add(node.value);
        dlr(ret, node.left);
        dlr(ret, node.right);
    }

    /**
     * LRD--后序遍历（根在后，从左往右，一棵树的左子树永远在右子树前面，右子树永远在根前面）
     *
     * @param ret
     * @param node
     */
    private void lrd(List<String> ret, Node node) {
        if (node == null) {
            return;
        }
        lrd(ret, node.left);
        lrd(ret, node.right);
        ret.add(node.value);
    }

}
