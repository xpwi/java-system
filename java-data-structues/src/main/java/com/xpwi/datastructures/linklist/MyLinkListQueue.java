package com.xpwi.datastructures.linklist;

/**
 * 演示链表来实现基本的队列
 *
 * @author xpwi
 */
public class MyLinkListQueue {
    private FirstLastList theList = new FirstLastList();

    public void insert(int id) {
        theList.insertLast(id);
    }

    public int remove() {
        return theList.removeFirst().getId();
    }

    public int peekFront() {
        return theList.peekFirst().getId();
    }

    public boolean isEmpty() {
        return theList.isEmpty();
    }

    public boolean isFull() {
        return false;
    }

    public void printQueue() {
        theList.displayList();
    }

    public static void main(String[] args) {
        MyLinkListQueue t = new MyLinkListQueue();

        t.insert(1);
        t.insert(2);
        t.insert(3);
        t.insert(4);
        t.insert(5);

        t.printQueue();

        int ret = t.peekFront();
        System.out.println("now ret==" + ret);

        t.remove();
        int ret2 = t.remove();
        System.out.println("now ret2==" + ret2);

        t.printQueue();

        t.insert(6);
        t.insert(7);
        t.insert(8);
        t.printQueue();
    }
}
