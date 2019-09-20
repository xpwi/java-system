package com.xiaopengwei.datastructures.hash.hash3;

/**
 * 用来封装链表的一个节点对象
 *
 * @author xpwi
 */
public class Link {
    private int id;
    private int businessData;

    private Link next;

    public Link(int id, int businessData) {
        this.id = id;
        this.businessData = businessData;
    }

    public int getKey() {
        return id;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return id + ", ";
    }

}
