package com.xpwi.algorithm.a04reverse;

/**
 * <p>
 *
 * @author xpwi
 * @since 2019-09-22
 */
public class ListReverse {

    /***
     * 非递归实现
     */
    private static Node reverseByIterator(Node head) {

        if (head == null || head.getNext() == null) {
            return head;
        }

        Node pre = head;
        Node cur = head.getNext();
        Node tmp;

        while (cur != null) {
            tmp = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = tmp;
        }

        head.setNext(null);
        return pre;
    }

    /***
     * 递归实现
     */
    private static Node reverseByRecursion(Node head) {

        if (head == null || head.getNext() == null) {
            return head;
        }

        Node newHead = reverseByRecursion(head.getNext());

        head.getNext().setNext(head);
        head.setNext(null);

        return newHead;
    }

    public static void main(String[] args) {

        // 1、构建单链表
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);

        // 2、反转
        Node newHead = reverseByRecursion(head.getNext());

        // 3、打印
        while (newHead != null) {
            System.out.print(newHead.getData() + " ");
            newHead = newHead.getNext();
        }
    }
}

class Node {

    private int data;
    private Node next;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int Data) {
        this.data = Data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node Next) {
        this.next = Next;
    }
}
