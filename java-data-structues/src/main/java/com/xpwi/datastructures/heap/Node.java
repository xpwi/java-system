package com.xpwi.datastructures.heap;

/**
 * 用来封装树中的节点对象
 *
 * @author xpwi
 */
public class Node {
    private int id;
    private int businessData;

    public Node(int id, int businessData) {
        this.id = id;
        this.businessData = businessData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusinessData() {
        return businessData;
    }

    public void setBusinessData(int businessData) {
        this.businessData = businessData;
    }

    @Override
    public String toString() {
        return "Node [id=" + id + ", businessData=" + businessData + "]";
    }


}
