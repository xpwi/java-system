package com.xiaopengwei.algorithm.a01sort;

import com.xiaopengwei.algorithm.a01sort.base.SortBase;

/**
 * <p>
 *
 * @author XiaoPengwei
 * @since 2019-08-14
 */
public class MergeSort extends SortBase {

    private static Integer[] mergeSort(Integer[] a, int beg, int end) {

        if (end - beg < 2) {
            return a;
        }

        int mid = (int) Math.floor(end - beg >> 1);

        return merge(mergeSort(a, 0, mid), mergeSort(a, mid, end));
    }

    private static Integer[] merge(Integer[] a, Integer[] b) {

        Integer[] res = new Integer[10];
        int resIndex = 0;
        int aIndex = 0;
        int bIndex = 0;
        int minLength = less(a.length, b.length) ? a.length : b.length;

        while (less(0, minLength)) {

            if (less(a[aIndex], b[bIndex])) {
                res[resIndex] = a[aIndex];
                aIndex++;
            } else if (less(b[bIndex], a[aIndex])) {
                res[resIndex] = b[bIndex];
                bIndex++;
            }
            minLength--;
        }

        return res;
    }


    public static void main(String[] args) {


        Integer[] a = {2, 7, 1, 3, 6, 4};
        mergeSort(a, 0, a.length);

        show(a);
    }


}
