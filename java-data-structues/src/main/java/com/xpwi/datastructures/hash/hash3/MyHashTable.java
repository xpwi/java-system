package com.xpwi.datastructures.hash.hash3;

/**
 * 演示采用链地址法解决冲突的Hash表
 *
 * @author xpwi
 */
public class MyHashTable {
    private SortedList[] hasArray;
    private int arraySize;

    public MyHashTable(int size) {
        this.arraySize = size;
        this.hasArray = new SortedList[this.arraySize];
        //初始化每个链表
        for (int i = 0; i < this.arraySize; i++) {
            this.hasArray[i] = new SortedList();
        }
    }

    /**
     * hash函数
     *
     * @param key
     * @return
     */
    public int hashFun(int key) {
        return key % this.arraySize;
    }

    /**
     * 新增数据对象
     *
     * @param item
     */
    public void insert(Link link) {
        int hashVal = this.hashFun(link.getKey());
        this.hasArray[hashVal].insert(link);
    }

    /**
     * 删除数据对象
     *
     * @param key
     */
    public void remove(int key) {
        int hashVal = this.hashFun(key);
        this.hasArray[hashVal].remove(key);
    }

    /**
     * 根据key获取数据对象
     *
     * @param key
     * @return
     */
    public Link findData(int key) {
        int hashVal = this.hashFun(key);
        return this.hasArray[hashVal].findLink(key);
    }

    public void displayHashTable() {
        for (SortedList d : this.hasArray) {
            d.displayList();
        }
        System.out.println("=======================33333");
    }

    public static void main(String[] args) {
        MyHashTable t = new MyHashTable(10);

        for (int i = 1; i <= 10; i++) {
            Link d = new Link(i, i + 100);
            t.insert(d);
        }

        String s = "";

        t.displayHashTable();

        t.remove(3);

        t.displayHashTable();
    }
}
