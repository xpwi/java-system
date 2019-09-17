package com.xiaopengwei.algorithm;

/**
 * <p>
 *
 * @author xpwi
 * @since 2019-09-17
 */
public class MaxSubStringSum {
    public static void main(String[] args) {
        int[] a = { -2, 4, -3, 5, 7, -1, 8, 1 };
        int max = maxSubSum1(a);
        System.out.println(max);
        // 21
    }

    private static int maxSubSum1(int[] a) {
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) {
                    thisSum += a[k];
                }
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }
}