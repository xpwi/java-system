package com.xiaopengwei.datastructures.queue;

/**
 * 演示优先级队列
 *
 * @author xpwi
 */
public class PriorityQueue {
    /**
     * 存放队列数据的数组
     */
    private int[] queue;
    /**
     * 记录当前队列里面存放的元素个数，也相当于是索引
     */
    private int nItems;

    public PriorityQueue(int length) {
        queue = new int[length];
        nItems = 0;
    }

    public void insert(int data) {
        //1：队列里面没有数据项的话，直接赋值
        if (nItems == 0) {
            queue[nItems] = data;
            nItems++;
        } else {
            //2：队列里面有数据项的话，就需要进行比较，排序后插入数据
            int i = 0;
            for (i = nItems - 1; i >= 0; i--) {
                if (data < queue[i]) {
                    queue[i + 1] = queue[i];
                } else {
                    break;
                }
            }
            queue[i + 1] = data;
            nItems++;
        }
    }

    public int remove() {
        nItems--;
        int temp = queue[nItems];
        queue[nItems] = 0;
        return temp;
    }

    public int peekFront() {
        return queue[nItems - 1];
    }

    public boolean isEmpty() {
        return nItems == 0;
    }

    public boolean isFull() {
        return nItems == queue.length;
    }

    public void printQueue() {
        System.out.println("=======================>");
        for (int d : queue) {
            System.out.println(d);
        }
    }

    public static void main(String[] args) {
        PriorityQueue t = new PriorityQueue(5);

        t.insert(5);
        t.insert(6);
        t.insert(3);
        t.insert(4);
        t.insert(1);

        t.printQueue();

        int ret = t.peekFront();
        System.out.println("now ret==" + ret);

        t.remove();
        int ret2 = t.remove();
        System.out.println("now ret2==" + ret2);

        t.printQueue();

    }
}
