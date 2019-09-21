package com.xpwi.algorithm.leetcode;

/**
 * <p>
 * 04 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * @author XiaoPengwei
 * @since 2019-07-30
 */
public class LC04FindMedian {

    public static void main(String[] args) {

        int[] numsA = {1, 3, 7, 9, 6};
        int[] numsB = {2, 8};

        double medianSortedArrays = findMedianSortedArrays(numsA, numsB);

        System.out.println(medianSortedArrays);

    }

    /**
     * @param A int[]
     * @param B int[]
     * @return double
     */
    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;

        /* 确保 m<=n
         * 为什么要确保 m<=n？
         */
        if (m > n) {
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }

        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;

        while (iMin <= iMax) {

            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                // i is too small
                iMin = i + 1;
            } else if (i > iMin && A[i - 1] > B[j]) {
                // i is too big
                iMax = i - 1;
            } else {
                // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

}
