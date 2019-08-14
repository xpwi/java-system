package com.xiaopengwei.algorithm.sort;

import com.xiaopengwei.algorithm.sort.base.SortBase;

/**
 * <p>
 * 希尔排序
 *
 * @author XiaoPengwei
 * @since 2019-08-14
 */
public class ShellSort extends SortBase {

    private static void shellSort(Integer[] a, Integer[] stepArr) {

        Integer temp;

        for (int i = 0; i < stepArr.length; i++) {

            //stepArr[i] 步长
            for (int j = 0; j < a.length; j++) {

                if (j - stepArr[i] >= 0) {
                    if (less(a[j], a[j - stepArr[i]])) {
                        temp = a[j];
                        a[j] = a[j - stepArr[i]];
                        a[j - stepArr[i]] = temp;
                    }
                }

            }
        }
    }


    public static void main(String[] args) {

        Integer[] a = {9, 2, 7, 1, 3, 6, 4, 5};
        // 1274369

        Integer[] stepArr = {3, 2, 1};
        shellSort(a, stepArr);

        show(a);
    }
}
