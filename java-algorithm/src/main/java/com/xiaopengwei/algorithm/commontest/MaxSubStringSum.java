package com.xiaopengwei.algorithm.commontest;

/**
 * <p>
 * 最大连续连续子序列和
 *
 * @author xpwi
 * @since 2019-09-17
 */
public class MaxSubStringSum {
    public static void main(String[] args) {
        int[] a = {-2, 4, -3, 5, 7, -1, 8, 1};
        int max = maxSubSum1(a);
        System.out.println(max);
    }

    private static int maxSubSum1(int[] a) {
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;

            for (int j = i; j < a.length; j++) {
                thisSum += a[j];
            }
            if (thisSum > maxSum) {
                maxSum = thisSum;
            }
        }


        return maxSum;
    }
}