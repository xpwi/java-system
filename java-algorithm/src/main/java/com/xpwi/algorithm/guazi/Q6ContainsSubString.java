package com.xpwi.algorithm.guazi;

import java.util.Scanner;

/**
 * <p>
 *     判断一个无序数组中是否存在长度为3的递增子序列。（不要求连续）（满足O(n)的时间复杂度和O(1)的空间复杂度。
 *
 * @author github.com/xpwi
 * @since 2019-09-16
 */
public class Q6ContainsSubString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for ( int i = 0; i < n; i++ ) {
            nums[i] = scanner.nextInt();
        }
        System.out.println(solution(nums));
    }

    public static boolean solution(int[] nums){
        if ( nums == null || nums.length < 3 ){
            return false;
        }
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        //寻找最小的二元递增组
        for ( int num : nums ) {
            if ( first > num ){
                first = num;
            }else if ( first < num && second > num ){
                second = num;
            }else if ( num > second ){
                return true;
            }
        }


        return false;
    }
}
