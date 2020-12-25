package com.xpwi.algorithm.guazi;

import java.util.Scanner;

/**
 * <p>
 * 动态规划
 * 为了防止溢出，请将答案 Mod 1000000007
 * 1000000007 是最小的十位质数。模 1000000007，可以保证值永远在 int 的范围内。
 *
 * @author github.com/xpwi
 * @since 2019-09-14
 */
public class Q2MinimumCoins {
    public static void main(String args[]) {
        int[] coins = {1, 2, 5, 10};
        Scanner sc = new Scanner(System.in);
        int target = sc.nextInt();
        // 为什么是 target+1?
        int[] dp = new int[target + 1];

        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            // 小于 target+1 就是说，需要判断等于 target 的情况
            for (int j = coins[i]; j < target + 1; j++) {
                dp[j] = (dp[j] + dp[j - coins[i]]) % 1000000007;
                System.out.println("coin=" + coins[i] + "");
                System.out.println("dp["+j + "]:" + dp[j]);
            }
            System.out.println();
        }

        System.out.println();
        for (int k = 0; k < dp.length; k++) {
            System.out.println(dp[k]);
        }

        System.out.println();
        System.out.print(dp[target]);
    }
}
