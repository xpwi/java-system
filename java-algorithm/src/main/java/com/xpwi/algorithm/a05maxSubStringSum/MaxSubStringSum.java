package com.xpwi.algorithm.a05maxSubStringSum;

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

    /**
     * 依次从左向右，只要不小于 0 就累加
     */
    private static int maxSubSum1(int[] a) {
        int maxSum = 0;
        int thisSum = 0;
        for (int i = 0; i < a.length; i++) {
            thisSum +=  a[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }

    private static int maxSubSum2(int[] a) {
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    private static int maxSubSum3(int[] a) {
        // 递归初始化参数
        return maxSumRec(a, 0, a.length - 1);
    }

    private static int maxSumRec(int[] a, int left, int right) {
        // 判断是否只有一个元素
        if (left == right) {
            if (a[left] > 0) {
                return a[left];
            } else {
                return 0;
            }
        }
        int center = (left + right) / 2;
        int maxLeftSum = maxSumRec(a, left, center);
        int maxRightSum = maxSumRec(a, center + 1, right);

        // 左端处理
        int maxLeftBorderSum = 0;
        int leftBoarderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBoarderSum += a[i];
            if (leftBoarderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBoarderSum;
            }
        }
        // 右端处理
        int maxRightBoarderSum = 0;
        int rightBoarderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBoarderSum += a[i];
            if (rightBoarderSum > maxRightBoarderSum) {
                maxRightBoarderSum = rightBoarderSum;
            }
        }
        // 返回最大值
        return Math.max(Math.max(maxLeftSum, maxRightSum), maxLeftBorderSum + maxRightBoarderSum);

    }
}