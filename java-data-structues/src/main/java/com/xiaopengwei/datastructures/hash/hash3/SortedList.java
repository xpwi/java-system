package com.xiaopengwei.datastructures.hash.hash3;

/**
 * 封装链表对象
 *
 * @author xpwi
 */
public class SortedList {
    private Link first;

    /**
     * 新增Link对象
     *
     * @param link
     */
    public void insert(Link link) {
        Link pre = null;
        Link current = first;
        while (current != null && link.getKey() > current.getKey()) {
            pre = current;
            current = current.getNext();
        }

        if (pre == null) {
            first = link;
        } else {
            pre.setNext(link);
        }

        link.setNext(current);
    }

    /**
     * 删除Link对象
     *
     * @param key
     */
    public void remove(int key) {
        Link pre = null;
        Link current = first;
        while (current != null && key != current.getKey()) {
            pre = current;
            current = current.getNext();
        }

        if (pre == null) {
            first = first.getNext();
        } else {
            pre.setNext(current.getNext());
        }
    }

    /**
     * 查找Link对象
     *
     * @param key
     * @return
     */
    public Link findLink(int key) {
        Link current = first;
        while (current != null && key >= current.getKey()) {
            if (current.getKey() == key) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public void displayList() {
        Link current = first;
        while (current != null) {
            System.out.print(current.toString());
            current = current.getNext();
        }
        System.out.println("");
    }
}