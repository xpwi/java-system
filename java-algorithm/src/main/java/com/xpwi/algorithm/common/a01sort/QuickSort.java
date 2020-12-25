package com.xpwi.algorithm.common.a01sort;

/**
 * <p>
 * 快速排序
 *
 * @author github.com/xpwi
 * @since 2019-05-14
 */
public class QuickSort {

    private static void quickSort(int[] arr, int low, int high) {
        // 递归结束条件
        if (low > high) {
            return;
        }
        int i, j, temp, t;
        i = low;
        j = high;
        // temp 就是基准位
        temp = arr[low];

        while (i < j) {
            // 先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            // 再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }

        // 最后将基准为与 i 和 j 相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        // 递归调用左半数组
        quickSort(arr, low, j - 1);
        // 递归调用右半数组
        quickSort(arr, j + 1, high);
    }


    public static void main(String[] args) {
        // 10
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
