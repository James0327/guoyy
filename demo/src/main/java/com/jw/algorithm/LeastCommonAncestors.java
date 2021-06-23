package com.jw.algorithm;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;

/**
 * Description: guoyy
 * com.jw.algorithm.LeastCommonAncestors
 * <p>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 定义：对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/6/10 10:12
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class LeastCommonAncestors {

    @Test
    public void tLCA() {
        /**
         * *          1
         * *        /  \
         * *       2    7
         * *      / \    \
         * *     3  4     8
         * *    / \       \
         * *   5  6        9
         * *              / \
         * *            10  11
         */
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);

        node1.left = node2;
        node1.right = node7;
        node2.left = node3;
        node2.right = node4;
        node4.left = node5;
        node4.right = node6;
        node7.right = node8;
        node8.right = node9;
        node9.left = node10;
        node9.right = node11;

        Node node = algorithm(node1, node6, node1);
        System.out.println(ToStringBuilder.reflectionToString(node, ToStringStyle.JSON_STYLE));
    }

    /**
     * 后序遍历，回溯
     * 对每个节点对应的子树，若该子树不含有p或q，返回null ptr；
     * 否则，如果p和q分别位于当前子树根节点两侧，则返回当前节点，
     * 否则（p和q在同一侧，或者只有某一侧有p或q）返回来自左边或右边的LCA。
     *
     * @param root
     * @param node1
     * @param node2
     * @return
     */
    private Node algorithm(Node root, Node node1, Node node2) {
        if (root == null || root == node1 || root == node2) {
            return root;
        }
        Node left = algorithm(root.left, node1, node2);
        Node right = algorithm(root.right, node1, node2);

        if (left != null && right != null) {
            return root;
        }

        return left == null ? right : left;
    }

    private class Node {
        int val;
        Node left, right;

        public Node(int val) {
            this.val = val;
        }
    }

}
