package com.xiaopengwei.datastructures.linklist;

/**
 * 演示双端链表
 *
 * @author XiaoPengwei.com
 */
public class FirstLastList {
    private LinkNode firstNode;
    //同时还记录着结尾的结点
    private LinkNode lastNode;

    public boolean isEmpty() {
        return firstNode == null && lastNode == null;
    }

    public LinkNode peekFirst() {
        return firstNode;
    }

    public void insertFirst(int id) {
        LinkNode newLink = new LinkNode(id);

        //判断是否有节点
        if (firstNode == null) {
            lastNode = newLink;
        }

        newLink.setNext(firstNode);
        firstNode = newLink;
    }

    public void insertLast(int id) {
        LinkNode newLink = new LinkNode(id);
        //判断是否有节点
        if (firstNode == null) {
            firstNode = newLink;
        } else {
            lastNode.setNext(newLink);
        }
        lastNode = newLink;
    }

    public LinkNode removeFirst() {
        LinkNode temp = firstNode;
        //判断是否删到最后
        if (firstNode.getNext() == null) {
            lastNode = null;
        }

        firstNode = firstNode.getNext();
        return temp;
    }

    public LinkNode find(int id) {
        LinkNode node = firstNode;
        //从firstNode节点向下查找
        while (node.getId() != id) {
            if (node.getNext() == null) {
                return null;
            } else {
                node = node.getNext();
            }
        }
        return node;
    }

    public void displayList() {
        System.out.println("====================================>");
        LinkNode tempNode = firstNode;
        while (tempNode != null) {
            tempNode.printLink();
            tempNode = tempNode.getNext();
        }
    }

    public static void main(String[] args) {
        FirstLastList t = new FirstLastList();

        t.insertFirst(1);
        t.insertFirst(2);
        t.insertFirst(3);

        t.displayList();

        t.insertLast(4);
        t.insertLast(5);
        t.insertLast(6);

        t.displayList();

        LinkNode ret = t.removeFirst();
        ret.printLink();

    }
}
