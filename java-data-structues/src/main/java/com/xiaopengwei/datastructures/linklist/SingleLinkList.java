package com.xiaopengwei.datastructures.linklist;

/**
 * 演示单向链表的基本操作
 *
 * @author xpwi
 */
public class SingleLinkList {
    private LinkNode firstNode;

    public void insertFirst(int id) {
        LinkNode newLink = new LinkNode(id);
        newLink.setNext(firstNode);
        firstNode = newLink;
    }

    public LinkNode removeFirst() {
        LinkNode temp = firstNode;
        firstNode = firstNode.getNext();
        return temp;
    }

    public LinkNode peekFirst() {
        return firstNode;
    }

    public boolean isEmpty() {
        return firstNode == null;
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

    public LinkNode remove(int id) {
        LinkNode needDelete = firstNode;
        LinkNode previous = firstNode;
        //查找需要删除的结点，以及它的前驱结点
        while (needDelete.getId() != id) {
            if (needDelete.getNext() == null) {
                return null;
            } else {
                previous = needDelete;
                needDelete = needDelete.getNext();
            }
        }
        //重新设置结点的关系
        if (needDelete.equals(firstNode)) {
            firstNode = firstNode.getNext();
        } else {
            previous.setNext(needDelete.getNext());
        }

        return needDelete;
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
        SingleLinkList t = new SingleLinkList();

        t.insertFirst(2);
        t.insertFirst(6);
        t.insertFirst(3);
        t.insertFirst(9);

        t.displayList();

        t.removeFirst();

        t.displayList();

        LinkNode ret1 = t.find(6);
        System.out.println("now ret1===");
        ret1.printLink();
        t.displayList();

        t.remove(6);
        t.displayList();
    }
}
