package com.xiaopengwei.datastructures.hash.hash;

/**
 * 封装hash化的数据对象
 *
 * @author xpwi
 */
public class DataItem {
    private int id;
    private int businessData;

    public DataItem(int id, int businessData) {
        this.id = id;
        this.businessData = businessData;
    }

    public int getKey() {
        return this.id;
    }

    @Override
    public String toString() {
        return "[id=" + id + "," + businessData + "]";
    }


}
