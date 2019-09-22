package com.xpwi.algorithm.a01sort;

/**
 * <p>
 * 冒泡排序
 *
 * @author xpwi
 * @since 2019-08-14
 */
public class BubbleSort {

    public static void bubbleSort(int[] a) {

        Integer temp;
        // 每次比较相邻元素
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[i]) {
                    temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 1, 3, 6, 4};
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
