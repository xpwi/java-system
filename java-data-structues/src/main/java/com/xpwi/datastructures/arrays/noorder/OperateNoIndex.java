package com.xpwi.datastructures.arrays.noorder;

/**
 * 用来演示直接使用数据操作无序数组, 存放不可重复的值
 * 所有文件编码为：UTF-8，若中文乱码请设置开发工具编码
 *
 * @author github.com/xpwi
 */
public class OperateNoIndex {
    private int[] datas = null;
    private int currentIndex = 0;

    /**
     * 构造方法
     * 这里自在内部实例化，可能会提示
     *
     * @param length 长度
     * @author github.com/xpwi
     */
    public OperateNoIndex(int length) {
        datas = new int[length];
    }

    public int insert(int data) {
        datas[currentIndex] = data;
        currentIndex++;
        return currentIndex - 1;
    }

    private int getIndex(int data) {
        int index = -1;
        for (int i = 0; i < currentIndex; i++) {
            if (datas[i] == data) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void remove(int data) {
        //1：查找这个数据对应的索引
        int index = this.getIndex(data);
        //2：同前一个演示
        for (int i = index; i < currentIndex; i++) {
            datas[i] = datas[i + 1];
        }
        currentIndex--;
    }

    public int searchOne(int data) {
        //1：查找这个数据对应的索引
        int index = this.getIndex(data);
        //2：如果有，就返回datas中的数据
        if (index >= 0) {
            return datas[index];
        }
        //3：如果没有，就返回0；
        return 0;
    }

    public void printDatas() {
        System.out.println("======================================>");
        for (int d : datas) {
            System.out.println(d);
        }
    }

    public static void main(String[] args) {
        OperateNoIndex t = new OperateNoIndex(10);

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
