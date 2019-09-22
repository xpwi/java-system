package com.xpwi.algorithm.a01sort;

/**
 * <p>
 * 选择排序
 *
 * @author xpwi
 * @since 2019-08-14
 */
public class SelectionSort {


    private static void selectionSort(int[] a) {

        int temp;

        for (int i = 0; i < a.length; i++) {
            int min = getMin(a, i, a.length);
            temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
    }

    /**
     * 求最小值
     * @return int 最小下标
     */
    private static int getMin(int[] a, int i, int length) {
        int res = i;

        for (int m = i; m < length; m++) {
            if (a[m] < a[res] ) {
                res = m;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 1, 3, 6, 4};
        selectionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
