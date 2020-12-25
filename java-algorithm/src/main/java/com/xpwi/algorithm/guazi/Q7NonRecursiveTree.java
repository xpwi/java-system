package com.xpwi.algorithm.guazi;

import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 *
 * @author github.com/xpwi
 * @since 2019-09-16
 */
public class Q7NonRecursiveTree {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] trees = new int[2 * n];
        for (int i = 0; i < trees.length; i++) {
            trees[i] = sc.nextInt();
        }
        TreeNode root = TreeNode.genTree(trees);
        preOrder(root);
        inOrder(root);
        postOrder(root);
        BFS(root);
    }

    public static void preOrder(TreeNode root){
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            root = stack.pop();
            System.out.print(root.val + " ");
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }
        System.out.println();
    }

    public static void inOrder(TreeNode root){
        Deque<TreeNode> stack = new LinkedList<>();
        do {
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.print(root.val + " ");
            root = root.right;
        } while (root != null || !stack.isEmpty());
        System.out.println();
    }
    public static void postOrder(TreeNode root){
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left != null ? root.left : root.right;
            }
            root = stack.pop();
            System.out.print(root.val + " ");
            TreeNode last = stack.peek();
            if (!stack.isEmpty() && last.left == root)
                root = last.right;
            else
                root = null;
        }
        System.out.println();
    }

    public static void BFS(TreeNode root){
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int currentSize = queue.size();
        while (!queue.isEmpty()){
            while (currentSize-- > 0){
                root = queue.poll();
                System.out.print(root.val + " ");
                if (root.left != null || root.right != null){
                    if (root.left != null)
                        queue.offer(root.left);
                    if (root.right != null)
                        queue.offer(root.right);
                }

            }
            currentSize = queue.size();
        }
    }
}

//
//class TreeNode{
//    TreeNode left;
//    TreeNode right;
//    int val;
//    public TreeNode(int val){
//        this.val = val;
//    }
//    //生成树
//    public static TreeNode genTree(int[] array){
//        Map<Integer, TreeNode> tmp = new HashMap<>();
//        TreeNode root = new TreeNode(1);
//        TreeNode head = root;
//        for (int i = 0, layer = 1; i < array.length; i += 2, layer++){
//
//            int left = array[i];
//            int right = array[i + 1];
//
//            if (tmp.containsKey(layer))
//                root = tmp.get(layer);
//
//            if (left == 0)
//                root.left = null;
//            else {
//                root.left = new TreeNode(left);
//                tmp.put(left, root.left); //若左子树不为空将编号映射，在对应层数在赋值
//            }
//
//            if (right == 0)
//                root.right = null;
//            else {
//                root.right = new TreeNode(right);
//                tmp.put(right, root.right);//若右子树不为空将编号映射，在对应层数在赋值
//            }
//        }
//        return head;
//    }
//}
