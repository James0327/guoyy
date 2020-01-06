package com.jw.thread.t2;

/**
 * guoyy com.jw.thread.t2
 *
 * @Description: com.jw.thread.t2.T1
 * @Author: guoyiyong/james
 * @Date: 2019-12-06 21:19
 * @Version: 1.0
 * <p>
 * Copyright (C) 2019 JW All rights reserved.
 */

public class T1 {

    static class Node {
        public Node() {
        }

        public Node(int idx) {
            this.idx = idx;
        }

        int idx;
        Node prev;
        Node next;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = null;

        n1.prev = null;
        n2.prev = n1;
        n3.prev = n2;
        n4.prev = n3;
        n5.prev = n4;
        n6.prev = n5;

        Node n = n1;
        while (n.next != null) {
            System.out.print(n.idx + "\t");
            n = n.next;
        }
        System.out.println(n.idx);
        n = n6;
        while (n.prev != null) {
            System.out.print(n.idx + "\t");
            n = n.prev;
        }
        System.out.println(n.idx);

        n3 = n3.prev;
        n4.prev = n3;
        //  等效于  n4.prev = n3 = n3.prev;
        n3.next = n4;

        n = n1;
        while (n.next != null) {
            System.out.print(n.idx + "\t");
            n = n.next;
        }
        System.out.println(n.idx);
        n = n6;
        while (n.prev != null) {
            System.out.print(n.idx + "\t");
            n = n.prev;
        }
        System.out.println(n.idx);

    }

}
