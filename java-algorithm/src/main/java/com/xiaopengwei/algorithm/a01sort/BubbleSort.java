package com.xiaopengwei.algorithm.a01sort;

import com.xiaopengwei.algorithm.a01sort.base.SortBase;

/**
 * <p>
 * 冒泡排序
 *
 * @author XiaoPengwei
 * @since 2019-08-14
 */
public class BubbleSort extends SortBase {

    public static void bubbleSort(Integer[] a) {

        Integer temp;
        // 每次比较相邻元素
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[i])) {
                    temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {2, 7, 1, 3, 6, 4};
        bubbleSort(a);
        show(a);
    }

}
