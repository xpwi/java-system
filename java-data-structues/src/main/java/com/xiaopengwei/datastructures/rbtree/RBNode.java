package com.xiaopengwei.datastructures.rbtree;

/**
 * 用来封装红黑树的节点对象
 *
 * @author xpwi
 */
public class RBNode {
    private int id;
    private int data;
    private RBNode leftChild;
    private RBNode rightChild;

    //add
    private boolean red = true;
    private RBNode parent;

    public RBNode(int id, int data, boolean red) {
        super();
        this.id = id;
        this.data = data;
        this.red = red;
    }


    public boolean isRed() {
        return red;
    }


    public void setRed(boolean red) {
        this.red = red;
    }


    public RBNode getParent() {
        return parent;
    }


    public void setParent(RBNode parent) {
        this.parent = parent;
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

    public RBNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(RBNode leftChild) {
        this.leftChild = leftChild;
    }

    public RBNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(RBNode rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
//		return "Node [id=" + id + ", data=" + data + ", leftChild=" + leftChild
//				+ ", rightChild=" + rightChild + "]";
        return "Node [id=" + id + ", data=" + data + ", red=" + red + "]";
    }

}
