package com.xpwi.datastructures.linklist;

/**
 * 结点 2
 *
 * @author xpwi
 */
public class LinkNode2 {
    private int id;
    private LinkNode2 next;
    private LinkNode2 previous;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkNode2 getNext() {
        return next;
    }

    public void setNext(LinkNode2 next) {
        this.next = next;
    }

    public LinkNode2 getPrevious() {
        return previous;
    }

    public void setPrevious(LinkNode2 previous) {
        this.previous = previous;
    }

    public LinkNode2(int id) {
        super();
        this.id = id;
    }

    public void printLink() {
        String s = "{id=" + id;
        if (previous != null) {
            s += " , previous=" + previous.getId();
        }
        if (next != null) {
            s += " , next=" + next.getId();
        }
        s += "}";
        System.out.println(s);
    }
}
