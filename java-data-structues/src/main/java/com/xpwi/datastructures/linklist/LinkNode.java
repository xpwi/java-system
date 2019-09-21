package com.xpwi.datastructures.linklist;

/**
 * 链表结点
 *
 * @author xpwi
 */
public class LinkNode {
    private int id;
    private LinkNode next;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public LinkNode(int id) {
        super();
        this.id = id;
    }

    public void printLink() {
        String s = "{id=" + id;
        if (next != null) {
            s += " , next=" + next.getId();
        }
        s += "}";
        System.out.println(s);
    }
}