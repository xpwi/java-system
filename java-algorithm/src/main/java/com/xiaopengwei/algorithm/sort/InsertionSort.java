package com.xiaopengwei.algorithm.sort;

import com.xiaopengwei.algorithm.sort.base.SortBase;

/**
 * <p>
 * 插入排序
 *
 * @author XiaoPengwei
 * @since 2019-08-14
 */
public class InsertionSort extends SortBase {

    private static void insertionSort(Integer[] a) {

        // 当前元素值
        Integer current;

        // 挨个选择，插入合适位置
        for (int i = 1; i < a.length; i++) {

            current = a[i];

            // 有序 j
            for (int j = i - 1; j >= 0; j--) {
                if (less(current, a[j])) {
                    a[j + 1] = a[j];
                    a[j] = current;
                }
            }

        }

    }


    public static void main(String[] args) {

        Integer[] a = {9, 2, 7, 1, 3, 6, 4};
        insertionSort(a);

        show(a);
    }


}
