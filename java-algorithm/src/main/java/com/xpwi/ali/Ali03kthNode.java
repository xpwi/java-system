package com.xpwi.ali;

/**
 * <p>
 *
 * @author XiaoPengwei
 * @since 2019-08-28
 */
public class Ali03kthNode {

    private static ResultType kthSmallestHelper(TreeNode root, int k) {

        if (root == null) {
            return new ResultType(false, 0);
        }

        ResultType left = kthSmallestHelper(root.getLeft(), k);
        if (left.isFound()) {
            return new ResultType(true, left.getVal());
        }
        // 左子树的节点数目 = K-1，结果为 root 的值
        if (k - left.getVal() == 1) {
            return new ResultType(true, root.getVal());
        }

        ResultType right = kthSmallestHelper(root.getRight(), k - left.getVal() - 1);
        if (right.isFound()) {
            return new ResultType(true, right.getVal());
        }
        // 没找到，返回节点总数
        return new ResultType(false, left.getVal() + 1 + right.getVal());
    }

    public static void main(String[] args) {

        // 1、构建树
        TreeNode root = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(6);

        root.setRight(treeNode6);
        root.setLeft(treeNode3);

        treeNode3.setRight(treeNode4);
        treeNode3.setLeft(treeNode2);

        treeNode2.setLeft(treeNode1);

        // 2、查找
        ResultType resultType = kthSmallestHelper(root, 3);

        if (resultType.isFound()){
            System.out.println(resultType.getVal());
        }else {
            System.out.println("not found");
        }

    }
}

class TreeNode {
    private int val;
    private TreeNode left;
    private TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
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

class ResultType {
    /**
     * 是否找到
     */
    private boolean found;

    /**
     * 节点数目
     */
    private int val;

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    ResultType(boolean found, int val) {
        this.found = found;
        this.val = val;
    }
}