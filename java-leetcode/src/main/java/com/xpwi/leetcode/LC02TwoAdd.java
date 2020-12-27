package com.xpwi.leetcode;

/**
 * 题目：https://leetcode-cn.com/problems/add-two-numbers
 * <p>
 * 02: 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * @author github.com/xpwi
 * @since 2019-07-14
 */
public class LC02TwoAdd {

    public static void main(String[] args) {

        // 链表 1
        ListNode list1Node1 = new ListNode(2);
        ListNode list1Node2 = new ListNode(4);
        ListNode list1Node3 = new ListNode(3);
        list1Node1.next = list1Node2;
        list1Node2.next = list1Node3;

        // 链表 2
        ListNode list2Node1 = new ListNode(5);
        ListNode list2Node2 = new ListNode(6);
        ListNode list2Node3 = new ListNode(4);
        list2Node1.next = list2Node2;
        list2Node2.next = list2Node3;

        System.out.println("addTwoNumbers1 ==>");
        ListNode resListNode1 = addTwoNumbers1(list1Node1, list2Node1);

        listNodePrint(resListNode1);

        System.out.println("addTwoNumbers2 ==>");
        ListNode resListNode2 = addTwoNumbers2(list1Node1, list2Node1);

        listNodePrint(resListNode2);

    }

    /**
     * 方法一： 注意逆序、注意引用类型
     *
     * @param node1 链表 1
     * @param node2 链表 2
     * @return listNode
     */
    private static ListNode addTwoNumbers1(ListNode node1, ListNode node2) {

        // 这里一方面是为了提高可读性，一方面是为了保留原来的 node1
        // 保留 list1Head 是为了最后的返回，因为 node1 会走到 node1 最后的结点，要返回的是头结点
        ListNode list1Head = node1;

        // temp 表示十位的数，即表示进位
        int temp = 0;

        // 直到 node2.next == null
        while (node2 != null) {

            // 真正的和
            int sumAll = temp + node1.val + node2.val;
            node1.val = sumAll % 10;

            // 下一次进位
            temp = sumAll / 10;

            // 因为链表长度不一定相等，如果有一个到头，另一个不到头，则将到头链表的空的位置补 0
            if (node1.next == null && node2.next != null) {
                node1.next = new ListNode(0);
            }
            if (node1.next != null && node2.next == null) {
                node2.next = new ListNode(0);
            }

            // 如果两个链表都到头了，且还有未处理的进位，则将进位挂在后面（即最后一个，即最高位）
            if (node1.next == null && temp != 0) {
                node1.next = new ListNode(temp);
            }

            node1 = node1.next;
            node2 = node2.next;
        }
        return list1Head;
    }

    /**
     * 方法二： 递归
     *
     * @param node1 链表 1
     * @param node2 链表 2
     * @return listNode
     */
    private static ListNode addTwoNumbers2(ListNode node1, ListNode node2) {

        return getSumListHeadNode(node1, node2, 0);
    }

    /**
     * （1）定义函数要做什么？获取两个存储逆序整数的链表，求和后的链表的头结点
     * （2）定义出入参？出参：ListNode；入参：两个链表的头结点、进位
     * （3）函数的结束条件？两个链表都到头，并计算进位
     * （4）进入下一层后范围怎样变化？结点都往后移动一位，直到之后
     * （5）每一层要处理的内容？计算进位
     * （6）验证结束条件是否完整（防止死循环）
     */
    private static ListNode getSumListHeadNode(ListNode node1, ListNode node2, int temp) {
        // 两个链表都为空、且进位为 0 时结束
        if (node1 == null && node2.next == null && temp == 0) {
            return null;
        }
        // 递过程的处理
        int value1 = node1 != null ? node1.val : 0;
        int value2 = node2 != null ? node2.val : 0;

        // 求和、进位
        int sum =  value1 + value2 + temp;
        temp = sum > 9 ? 1 : 0;

        ListNode resListNode = new ListNode(sum % 10);

        ListNode list1NextNode = node1 != null ? node1.next : null;
        ListNode list2NextNode = node2 != null ? node2.next : null;
        ListNode resNextNode = getSumListHeadNode(list1NextNode, list2NextNode, temp);

        // 归过程的处理
        resListNode.next = resNextNode;
        return resListNode;
    }

    /**
     * 递归打印
     *
     * @param listNode 需打印的链表
     */
    private static void listNodePrint(ListNode listNode) {

        if (listNode == null) {
            return;
        }

        if (listNode.next == null) {
            System.out.println(listNode.val);
            return;
        }

        System.out.println(listNode.val);
        listNodePrint(listNode.next);
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
