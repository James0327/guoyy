package com.jw.james.algorithm;

import java.util.Random;

/**
 * guoyy com.jw.algorithm
 * <p>
 * 跳表中存储的是正整数，并且存储的是不重复的
 *
 * @Description: com.jw.algorithm.SkipList
 * @Author: guoyiyong/james
 * @Date: 2020-07-09 13:54
 * @Version: 1.0
 * <p>
 * Copyright (C) 2020 JW All rights reserved.
 */
public class SkipList {

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(1);
        skipList.print();

        skipList.insert(100);
        skipList.print();

        skipList.insert(1000);
        skipList.print();

        skipList.insert(1000000);
        skipList.print();

        Node node9 = skipList.find(9);
        System.out.println(node9);
        Node node100 = skipList.find(100);
        System.out.println(node100);

        skipList.delete(1);
        Node node1 = skipList.find(1);
        System.out.println(node1);
    }

    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;

    private final Node head = new Node();

    private final Random r = new Random();

    public void insert(int value) {
        int level = randomLevel();
        Node node = new Node();
        node.data = value;
        node.maxLevel = level;

        Node[] update = new Node[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }

        // record every level largest value which smaller than insert value in update[]
        Node p = head;
        for (int i = level - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }

        // in search path node next node become new node forwords(next)
        for (int i = 0; i < level; i++) {
            node.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = node;
        }

        // update node hight
        if (levelCount < level) {
            levelCount = level;
        }
        System.out.println(this);
    }

    public Node find(int value) {
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
        }
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            return p.forwards[0];
        }
        return null;
    }

    public void delete(int value) {
        Node[] update = new Node[levelCount];
        Node p = head;
        for (int i = levelCount - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;
        }
        if (p.forwards[0] != null && p.forwards[0].data == value) {
            for (int i = levelCount - 1; i >= 0; --i) {
                if (update[i].forwards[i] != null && update[i].forwards[i].data == value) {
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
    }

    /**
     * 随机 level 次，如果是奇数层数 +1，防止伪随机
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 0; i < MAX_LEVEL; i++) {
            if ((r.nextInt() & 1) == 1) {
                level++;
            }
        }
        return level;
    }

    private void print() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.println(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    private class Node {
        private int data = -1;
        private final Node[] forwards = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", maxLevel=" + maxLevel +
                    '}';
        }
    }

}
