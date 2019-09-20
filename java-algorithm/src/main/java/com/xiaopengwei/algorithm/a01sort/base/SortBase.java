package com.xiaopengwei.algorithm.a01sort.base;

/**
 * <p>
 *
 * @author XiaoPengwei
 * @since 2019-08-09
 */
public class SortBase {

    /**
     * @param a a
     * @return min 下标
     */
    protected static int getMin(Integer[] a, int beg, int end) {

        int min = beg;

        for (int i = beg; i < end; i++) {
            if (a[i] < a[min]) {
                min = i;
            }
        }
        return min;
    }


    /**
     * v < m true
     * v > m false
     *
     * @param v v
     * @param m m
     * @return boolean
     */
    protected static boolean less(Comparable v, Comparable m) {
        return v.compareTo(m) < 0;
    }

    /**
     * v < m true
     * v > m false
     *
     * @param v v
     * @param m m
     * @return boolean
     */
    protected static boolean larg(Comparable v, Comparable m) {
        return v.compareTo(m) > 0;
    }


    /**
     * 交换
     *
     * @param a Comparable[]
     * @param i i
     * @param j j
     */
    protected static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 打印
     *
     * @param a
     */
    protected static void show(Comparable[] a) {
        for (Comparable anA : a) {
            System.out.print(anA + " ");
        }
        System.out.println();
    }

    /**
     * 判断是否为从小到大的顺序
     *
     * @param a
     * @return
     */
    protected static boolean isSorted(Comparable[] a) {
        // 遍历，如果存在大于就退出
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

}
