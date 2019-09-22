package com.xpwi.leetcode;

/**
 * <p>
 * 最长会问子串
 *
 * @author xpwi
 * @since 2019-08-31
 */
public class LC05Palindrome {

    /**
     * absdbdab
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;

        // 找出最长回文子串的开始结束位置
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);
            // 如果长度大于保存的长度，更新
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        // substring 子串带前不带后
        return s.substring(start, end + 1);
    }

    /**
     * absdbdab
     */
    private static int expandAroundCenter(String s, int left, int right) {
        int l = left, r = right;

        // 直到越界，或者左边的元素不等于右边的元素
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        return r - l - 1;
    }

    public static void main(String[] args) {

        String str = "absdbdab";

        String s = longestPalindrome(str);
        System.out.println(s);

    }

}
