package com.guazi;

import java.util.Scanner;

/**
 * <p>
 *
 * @author xpwi
 * @since 2019-09-16
 */
public class Q9toSecondStringDP {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext()) {
            String src = sc.next();
            String dst = sc.next();
            if (src.length() > 0 && src.length() <= 1000 && dst.length() > 0 && dst.length() <= 1000) {
                if (src.equals(dst)) System.out.println(0);
                else {
                    // dp[i][j]: 指示将字符串src[1..i]变更为dst[1..j]所需的最少操作次数
                    int[][] dp = new int[src.length() + 1][dst.length() + 1];
                    // 对二维数组dp初始化
                    for (int i = 0; i < src.length() + 1; i++) {
                        // src[1..i] -> 空字符串：只需删除i个字母即可
                        dp[i][0] = i;
                    }
                    for (int j = 0; j < dst.length() + 1; j++) {
                        // 空字符串 -> dst[1..j]：只需增加j个字母即可
                        dp[0][j] = j;
                    }
                    /* 状态转移过程（共3种情况）:
                     * 1. src[1..i]修改为dst[1..j-1] => src[1..i]修改为dst[1..j]: dp[i][j-1]+1（增加一个字母）
                     * 2. src[1..i-1]修改为dst[1..j] => src[1..i]修改为dst[1..j]: dp[i-1][j]+1（删除一个字母）
                     * 3. src[1..i-1]修改为dst[1..j-1] => src[1..i]修改为dst[1..j]:
                     * 若src.charAt(i-1) == dst.charAt(j-1)，则令modifyStep = dp[i-1][j-1]，
                     * 否则modifyStep = dp[i-1][j-1]+1（修改一个字母）。
                     * dp[i][j]为dp[i][j-1]+1、dp[i-1][j]+1、modifyStep三者的最小值。
                     * 此即为本问题的状态转移方程。
                     */
                    for (int i = 1; i < src.length() + 1; i++) {
                        for (int j = 1; j < dst.length() + 1; j++) {
                            int appendStep = dp[i][j - 1] + 1;
                            int removeStep = dp[i - 1][j] + 1;
                            int modifyStep = (src.charAt(i - 1) == dst.charAt(j - 1)) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                            dp[i][j] = minVal(appendStep, removeStep, modifyStep);
                        }
                    }
                    System.out.println(dp[src.length()][dst.length()]);
                }
            }
        }
        sc.close();
    }

    public static int minVal(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

}
