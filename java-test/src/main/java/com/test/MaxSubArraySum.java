package com.test;

import java.util.Scanner;

/**
 * <p>
 * 连续子数组最大和
 *
 * @author xpwi
 * @since 2019-09-09
 */
public class MaxSubArraySum {

    public static int maxSubArraySum(int[] a) {
        int frameSetSum = a[0];
        int biggestSum = a[0];
        for (int i = 1; i < a.length; i++) {
            //第一个变量大则框集前移，第二个变量大则框集扩增
            frameSetSum = Math.max(a[i], frameSetSum + a[i]);
            //记录遍历过程中最大的子数组和
            biggestSum = Math.max(biggestSum, frameSetSum);
        }
        return biggestSum;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int size = in.nextInt();

        int[] a = new int[size];

        for (int i = 0; i < size; i++) {
            a[i] = in.nextInt();
        }
        int i = maxSubArraySum(a);
        System.out.println(i);

    }

}
