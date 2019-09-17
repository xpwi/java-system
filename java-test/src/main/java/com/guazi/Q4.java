package com.guazi;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * <p>
 *     https://www.nowcoder.com/profile/3909462/test/27548379/587675
 *
 * @author xpwi
 * @since 2019-09-15
 */
public class Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodeNum1 = sc.nextInt();
        int nodeNum2 = sc.nextInt();

        int[] treeArray1 = new int[3 * nodeNum1];
        int[] treeArray2 = new int[3 * nodeNum2];
        for (int i = 0; i < treeArray1.length; i++) {
            treeArray1[i] = sc.nextInt();
        }
        for (int i = 0; i < treeArray2.length; i++) {
            treeArray2[i] = sc.nextInt();
        }
        sc.close();

        TreeNode t1 = TreeNode.genTree(treeArray1);
        TreeNode t2 = TreeNode.genTree(treeArray2);

        TreeNode merge = mergeTree(t1, t2);
        BFS(merge);


    }

    public static TreeNode mergeTree(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 != null) {
            t1.left = mergeTree(t1.left, t2.left);
            t1.right = mergeTree(t1.right, t2.right);
            t1.val += t2.val;
            return t1;
        }
        return t1 == null ? t2 : t1;
    }

    //层次遍历
    public static void BFS(TreeNode root) {
        if (root == null)
            return;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int currentSize = queue.size();
        while (!queue.isEmpty()) {
            while (currentSize-- > 0) {
                root = queue.poll();
                System.out.print(root.val + " ");
                if (root.left != null || root.right != null) {
                    if (root.left != null) {
                        queue.offer(root.left);
                    }
                    if (root.right != null) {
                        queue.offer(root.right);
                    }
                }

            }
            currentSize = queue.size();
        }
    }

}


class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;

    public TreeNode(int val) {
        this.val = val;
    }

    //生成树
    public static TreeNode genTree(int[] array) {
        Map<Integer, TreeNode> tmp = new HashMap<>();
        TreeNode root = new TreeNode(0);
        TreeNode head = root;
        for (int i = 0, layer = 1; i < array.length; i += 3, layer++) {

            int left = array[i];
            int right = array[i + 1];
            int val = array[i + 2];

            if (tmp.containsKey(layer))
                root = tmp.get(layer);

            root.val = val;

            if (left == 0)
                root.left = null;
            else {
                root.left = new TreeNode(0);
                tmp.put(left, root.left); //若左子树不为空将编号映射，在对应层数在赋值
            }

            if (right == 0)
                root.right = null;
            else {
                root.right = new TreeNode(0);
                tmp.put(right, root.right);//若右子树不为空将编号映射，在对应层数在赋值
            }
        }
        return head;
    }
}