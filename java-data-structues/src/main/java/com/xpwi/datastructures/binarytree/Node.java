package com.xpwi.datastructures.binarytree;

/**
 * 用来封装二叉树的节点对象
 *
 * @author xpwi
 */
public class Node {
	private int id;
	private int data;
	private Node leftChild;
	private Node rightChild;

    public Node(int id, int data) {
        super();
        this.id = id;
        this.data = data;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getData() {
        return data;
    }
    public void setData(int data) {
        this.data = data;
    }
    public Node getLeftChild() {
        return leftChild;
    }
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }
    public Node getRightChild() {
        return rightChild;
    }
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
    @Override
    public String toString() {
        return "Node [id=" + id + ", data=" + data + ", leftChild=" + leftChild
                + ", rightChild=" + rightChild + "]";
    }


}
