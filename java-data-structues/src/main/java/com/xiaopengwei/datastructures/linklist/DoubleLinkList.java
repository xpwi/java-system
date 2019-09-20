package com.xiaopengwei.datastructures.linklist;

/**
 * 演示双向链表
 *
 * @author xpwi
 */
public class DoubleLinkList {
    private LinkNode2 first;
    private LinkNode2 last;

    public boolean isEmpty() {
        return first == null;
    }

    public LinkNode2 peekFirst() {
        return first;
    }

    public void insertFirst(int id) {
        LinkNode2 newLink = new LinkNode2(id);

        if (isEmpty()) {
            last = newLink;
        } else {
            first.setPrevious(newLink);
        }
        newLink.setNext(first);

        first = newLink;
    }

    public void insertLast(int id) {
        LinkNode2 newLink = new LinkNode2(id);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.setNext(newLink);
            newLink.setPrevious(last);
        }
        last = newLink;
    }

    public LinkNode2 removeFirst() {
        LinkNode2 temp = first;
        if (first.getNext() == null) {
            last = null;
        } else {
            first.getNext().setPrevious(null);
        }
        first = first.getNext();
        return temp;
    }

    public LinkNode2 find(int id) {
        LinkNode2 node = first;
        //从first节点向下查找
        while (node.getId() != id) {
            if (node.getNext() == null) {
                return null;
            } else {
                node = node.getNext();
            }
        }
        return node;
    }

    public void displayListForward() {
        System.out.println("====================================>");
        LinkNode2 tempNode = first;
        while (tempNode != null) {
            tempNode.printLink();
            tempNode = tempNode.getNext();
        }
    }

    ////////
    public LinkNode2 removeLast() {
        LinkNode2 temp = last;

        if (last.getPrevious() == null) {
            first = null;
        } else {
            last.getPrevious().setNext(null);
        }
        last = last.getPrevious();

        return temp;
    }

    public LinkNode2 remove(int key) {
        //1：先找到这个要删除的结点
        LinkNode2 node = this.find(key);
        //2：如果这个结点是first，那么就把它的next作为新的first
        if (node == first) {
            first = node.getNext();
        } else {
            //2.1 否则 就把这个节点的 previous节点 的 next，设置成为这个节点的next
            node.getPrevious().setNext(node.getNext());
        }
        //3：如果这个结点是last，那么就把它的previous结点做为新的last
        if (node == last) {
            last = node.getPrevious();
        } else {
            //3.1：否则 就把这个节点的 next节点 的previouse，设置成为这个节点的previous
            node.getNext().setPrevious(node.getPrevious());
        }

        return node;
    }

    public boolean insertAfter(int key, int id) {
        //1：查找到要在其后面添加新结点 的 结点
        LinkNode2 node = this.find(key);
        //2：分别设置这三个结点，要在其后添加新节点的结点、新结点、要在其后添加新节点的 后继结点
        LinkNode2 newLink = new LinkNode2(id);

        if (node == last) {
            newLink.setNext(null);
            last = newLink;
        } else {
            newLink.setNext(node.getNext());
            node.getNext().setPrevious(newLink);
        }

        newLink.setPrevious(node);
        node.setNext(newLink);

        return true;
    }

    public void displayListBackward() {
        System.out.println("====================================>");
        LinkNode2 tempNode = last;
        while (tempNode != null) {
            tempNode.printLink();
            tempNode = tempNode.getPrevious();
        }
    }

    public static void main(String[] args) {
        DoubleLinkList t = new DoubleLinkList();

        t.insertFirst(1);
        t.insertFirst(2);
        t.insertFirst(3);

        t.displayListForward();
        t.displayListBackward();

        t.insertLast(4);
        t.insertLast(5);
        t.insertLast(6);

        t.displayListForward();
        t.displayListBackward();

        t.removeFirst();
        t.displayListForward();
        t.displayListBackward();

        t.removeLast();
        t.displayListForward();
        t.displayListBackward();

        t.remove(1);
        t.displayListForward();
        t.displayListBackward();

        t.insertAfter(2, 77);
        t.displayListForward();
        t.displayListBackward();
    }
}