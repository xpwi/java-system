package com.xiaopengwei.algorithm.a01sort;

import com.xiaopengwei.algorithm.a01sort.base.SortBase;

/**
 * <p>
 * 选择排序
 *
 * @author XiaoPengwei
 * @since 2019-08-14
 */
public class SelectionSort extends SortBase {


    private static void selectionSort(Integer[] a) {

        Integer temp;

        for (int i = 0; i < a.length; i++) {
            Integer min = getMin(a, i, a.length);
            temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }

    }


    public static void main(String[] args) {


        Integer[] a = {2, 7, 1, 3, 6, 4};
        selectionSort(a);

        show(a);
    }

}
