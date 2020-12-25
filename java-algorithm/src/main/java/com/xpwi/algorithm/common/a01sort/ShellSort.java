package com.xpwi.algorithm.common.a01sort;

/**
 * <p>
 * 希尔排序
 *
 * @author github.com/xpwi
 * @since 2019-08-14
 */
public class ShellSort {

    private static void shellSort(int[] a, int[] stepArr) {

        Integer temp;

        for (int i = 0; i < stepArr.length; i++) {
            //stepArr[i] 步长
            for (int j = 0; j < a.length; j++) {

                if (j - stepArr[i] >= 0) {
                    if (a[j] < a[j - stepArr[i]]) {
                        temp = a[j];
                        a[j] = a[j - stepArr[i]];
                        a[j - stepArr[i]] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = {9, 2, 7, 1, 3, 6, 4, 5};
        // 1274369
        int[] stepArr = {3, 2, 1};
        shellSort(arr, stepArr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
