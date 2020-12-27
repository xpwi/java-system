package com.xpwi.algorithm.common.a07snackPrintTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 * 蛇形打印二叉树
 * 1
 * 2   3
 * 4 5 6 7
 *
 * @author github.com/xpwi
 * @since 2019-09-21
 */
public class SnackTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        ArrayList<ArrayList<Integer>> arr = print(root);
        System.out.println();
        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                System.out.print(arr.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    private static ArrayList<ArrayList<Integer>> print(TreeNode root) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> array1 = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return array1;
        }
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.addLast(root);
        boolean res = true;    //标记判断从哪段进出
        TreeNode last = root; //当前行最右结点
        TreeNode nlast = null;//下一行打印结果的最右结点
        TreeNode p = null;
        while (!queue.isEmpty()) {
            if (res == true) {
                p = queue.pollFirst();    //出队
                array.add(p.data);        //加入到数组
                if (p.left != null) {
                    nlast = nlast == null ? p.left : nlast;//修改nlast
                    queue.addLast(p.left);
                }
                if (p.right != null) {
                    nlast = nlast == null ? p.right : nlast;
                    queue.addLast(p.right);
                }
            } else {
                p = queue.pollLast();
                array.add(p.data);
                if (p.right != null) {
                    nlast = nlast == null ? p.right : nlast;
                    queue.addFirst(p.right);
                }
                if (p.left != null) {
                    nlast = nlast == null ? p.left : nlast;
                    queue.addFirst(p.left);
                }
            }
            if (last == p && !queue.isEmpty()) {//换行
                res = !res;
                last = nlast;
                nlast = null;
                array1.add(array);
                array = new ArrayList<Integer>();
            }
        }
        array1.add(array);//最后一组加入结果数组
        return array1;
    }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
    }

    public TreeNode(int data, TreeNode left, TreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
