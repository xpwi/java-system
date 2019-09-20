package com.xiaopengwei.datastructures.arrays.noorder;

/**
 * 用来演示通过索引操作无序数组
 *
 * @author xpwi
 */
public class OperateWithIndex {
    private int[] datas = null;
    private int currentIndex = 0;

    public OperateWithIndex(int length) {
        datas = new int[length];
    }

    public int insert(int data) {
        datas[currentIndex] = data;
        currentIndex++;
        return currentIndex - 1;
    }

    public void remove(int index) {
        for (int i = index; i < currentIndex; i++) {
            datas[i] = datas[i + 1];
        }
        currentIndex--;
    }

    public int searchOne(int index) {
        return datas[index];
    }

    public void printDatas() {
        System.out.println("======================================>");
        for (int d : datas) {
            System.out.println(d);
        }
    }

    public static void main(String[] args) {
        OperateWithIndex t = new OperateWithIndex(20);
        t.insert(3);
        t.insert(6);
        t.insert(1);
        t.insert(2);

        t.printDatas();

        t.remove(1);
        t.printDatas();

        int ret = t.searchOne(1);
        System.out.println("ret==" + ret);
    }
}
