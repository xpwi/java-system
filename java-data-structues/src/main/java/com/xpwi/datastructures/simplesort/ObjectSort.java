package com.xpwi.datastructures.simplesort;

import com.xpwi.datastructures.simplesort.model.UserModel;

/**
 * 排序
 *
 * @author xpwi
 */
public class ObjectSort {
    public void objectSort(UserModel[] ums) {
        //假定第一个位置是排好序
        int j = 0;
        for (int i = 1; i < ums.length; i++) {
            UserModel temp = ums[i];
            for (j = i; j > 0; j--) {
                if (ums[j - 1].getUuid() >= temp.getUuid()) {
                    ums[j] = ums[j - 1];
                } else {
                    break;
                }
            }
            ums[j] = temp;
        }
    }

    public void objectSort2(UserModel[] ums) {
        //假定第一个位置是排好序
        int j = 0;
        for (int i = 1; i < ums.length; i++) {
            UserModel temp = ums[i];
            for (j = i; j > 0; j--) {
                if (ums[j - 1].getNaem().compareTo(temp.getNaem()) < 0) {
                    ums[j] = ums[j - 1];
                } else {
                    break;
                }
            }
            ums[j] = temp;
        }
    }

    private void printDatas(UserModel[] ums) {
        System.out.println("======================>");
        for (UserModel d : ums) {
            System.out.println(d);
        }
    }

    public static void main(String[] args) {
        ObjectSort t = new ObjectSort();

        UserModel[] ums = new UserModel[]{
                new UserModel(1, "张三风1", 1),
                new UserModel(6, "里斯丰6", 6),
                new UserModel(3, "张三3", 3),
                new UserModel(2, "张三2", 2)
        };

        t.objectSort2(ums);

        t.printDatas(ums);
    }
}
