package com.jw.james.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * Description: guoyy
 * com.jw.algorithm.MyLinkedList
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/5/18 19:47
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class MyLinkedList {

    @Test
    public void test() {
        Node head = createNode(20);
        System.out.println("head: " + head);

        Node reverse = reverse(createNode(20));
        System.out.println("reverse: " + reverse);
        Node reverse2 = reverse2(createNode(20));
        System.out.println("reverse2: " + reverse2);

        Node n6 = new Node(6);

        Node ret = reverseRange(createNode(20), n6);
        Node ret2 = reverseRange2(createNode(20), n6);
        System.out.println("ret: " + ret);
        System.out.println("ret2: " + ret2);

        Node node = algorithm(createNode(20), 3);
        System.out.println("node: " + node);
    }

    private Node algorithm(Node head, int k) {
        return reverseKGroup(head, k);
    }

    private Node reverseKGroup(Node node, int k) {
        if (node == null) {
            return null;
        }
        Node end = node;
        for (int i = 0; i < k; i++) {
            if (end == null) {
                return node;
            }
            end = end.next;
        }
        Node next = reverseRange2(node, end);
        node.next = reverseKGroup(end, k);

        return next;
    }

    /**
     * 反转 [start, end)
     *
     * @param start
     * @param end
     * @return
     */
    private Node reverseRange2(Node start, Node end) {
        Node p = null, c = start;
        while (true) {
            if (c == null || Objects.equals(c, end)) {
                break;
            }
            Node tmp = c.next;
            c.next = p;
            p = c;
            c = tmp;
        }
        return p;
    }

    /**
     * 反转范围: [start, end)
     *
     * @param start
     * @param end
     * @return
     */
    private Node reverseRange(Node start, Node end) {
        if (start == null) {
            return null;
        }
        Node pre = null, cur = start, next = cur.next;
        while (!Objects.equals(cur, end)) {
            cur.next = pre;
            pre = cur;
            cur = next;
            if (next == null) {
                break;
            }
            next = next.next;
        }
        return pre;
    }

    private Node reverse2(Node node) {
        Node p = null, c = node;
        while (true) {
            if (c == null) {
                break;
            }
            Node tmp = c.next;
            c.next = p;
            p = c;
            c = tmp;
        }
        return p;
    }

    private Node reverse(Node node) {
        Node pre = null, cur = node, next = node.next;
        while (next != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = next.next;
        }
        cur.next = pre;
        return cur;
    }

    private class Node {
        private int val;
        private Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public Node setVal(int val) {
            this.val = val;
            return this;
        }

        public Node getNext() {
            return next;
        }

        public Node setNext(Node next) {
            this.next = next;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node)o;
            return val == node.val;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(val);
        }

        @Override
        public String toString() {
            StringBuilder strBuf = new StringBuilder();
            Node node = this;
            while (node != null) {
                strBuf.append(node.val);
                node = node.next;
                if (node != null) {
                    strBuf.append("->");
                }
            }
            return strBuf.toString();
        }
    }

    private Node createNode(int size) {
        Node p = null;
        for (int i = size; i >= 0; i--) {
            Node node = new Node(i);
            node.next = p;
            p = node;
        }
        return p;
    }

}
