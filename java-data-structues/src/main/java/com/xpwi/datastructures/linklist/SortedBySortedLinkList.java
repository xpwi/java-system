package com.xpwi.datastructures.linklist;

/**
 * 排序
 *
 * @author github.com/xpwi
 */
public class SortedBySortedLinkList {
    public int[] sort(int[] as) {
        int[] ret = new int[as.length];

        //1：把数据依次加入到有序链表
        SortedLinkList list = new SortedLinkList();
        for (int a : as) {
            list.insertFirst(a);
        }
        //2：依次从有序链表中取出数据
        int index = 0;
        while (!list.isEmpty()) {
            ret[index] = list.removeFirst().getId();
            index++;
        }
        return ret;
    }

    public static void main(String[] args) {
        SortedBySortedLinkList t = new SortedBySortedLinkList();
        int[] ret = t.sort(new int[]{5, 3, 7, 6});

        for (int a : ret) {
            System.out.println("ret==" + a);
        }

    }
}
