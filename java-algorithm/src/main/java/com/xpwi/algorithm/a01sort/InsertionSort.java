package com.xpwi.algorithm.a01sort;

/**
 * <p>
 * 插入排序
 *
 * @author xpwi
 * @since 2019-08-14
 */
public class InsertionSort{

    private static void insertionSort(int[] a) {

        // 当前元素值
        Integer current;

        // 从第 2 个开始，挨个选择，插入合适位置
        for (int i = 1; i < a.length; i++) {
            current = a[i];
            // 有序 j
            for (int j = i - 1; j >= 0; j--) {
                if (current<a[j]) {
                    a[j + 1] = a[j];
                    a[j] = current;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 2, 7, 1, 3, 6, 4};
        insertionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


}
