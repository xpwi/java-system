package com.xpwi.datastructures.arrays.object;

/**
 * 用来演示直接使用数据操作有序数组,存放不重复的 对象
 *
 * @author xpwi
 */
public class OperateOrderNoIndexObject {
    private UserModel[] datas;
    private int currentIndex = 0;

    public OperateOrderNoIndexObject(int length) {
        datas = new UserModel[length];
    }

    public int insert(UserModel data) {
        //假设顺序是升序
        int index = 0;
        //1：查找数据data应该存放的位置
        for (index = 0; index < currentIndex; index++) {
            if (datas[index].getUuid() > data.getUuid()) {
                break;
            }
        }
        //2：把这个位置及其后面的数据，向后移动一位
        for (int i = currentIndex; i > index; i--) {
            datas[i] = datas[i - 1];
        }
        //3：把data设置到应该存放的位置
        datas[index] = data;

        currentIndex++;
        return currentIndex - 1;
    }

    private int getIndex(int uuid) {
        int index = -1;
        for (int i = 0; i < currentIndex; i++) {
            if (datas[i].getUuid() == uuid) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void remove(int uuid) {
        //1：查找这个数据对应的索引
        int index = this.getIndex(uuid);
        //2：同前一个演示
        for (int i = index; i < currentIndex; i++) {
            datas[i] = datas[i + 1];
        }
        currentIndex--;
    }

    public UserModel searchOne(int uuid) {
        //1：查找这个数据对应的索引
        int index = this.getIndex(uuid);
        //2：如果有，就返回datas中的数据
        if (index >= 0) {
            return datas[index];
        }
        //3：如果没有，就返回null
        return null;
    }

    public void printDatas() {
        System.out.println("======================================>");
        for (UserModel d : datas) {
            System.out.println(d);
        }
    }

    public static void main(String[] args) {
        OperateOrderNoIndexObject t = new OperateOrderNoIndexObject(10);

        t.insert(new UserModel(1, "张三1", 1));
        t.insert(new UserModel(6, "张三6", 6));
        t.insert(new UserModel(3, "张三3", 3));
        t.insert(new UserModel(2, "张三2", 2));

        t.printDatas();

        t.remove(1);
        t.printDatas();

        UserModel ret = t.searchOne(2);
        System.out.println("ret==" + ret);
    }
}
