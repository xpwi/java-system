package com.xpwi.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 03:给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 *
 * @author github.com/xpwi
 * @since 2019-07-14
 */
public class LC03LongestSubstring {

    public static void main(String[] args) {

        // 该代表性示例，最长为 4，即 abcd
        // String str = "abcabcdbcs";
        String str = "abba";

        int lengthByMethod1 = lengthOfLongestSubstringMethod1(str);
        System.out.println("lengthByMethod1-->" + lengthByMethod1);

        int lengthByMethod2 = lengthOfLongestSubstringMethod2(str);
        System.out.println("lengthByMethod2-->" + lengthByMethod2);


    }

    /**
     * 方法一：
     * 求不含有重复字符的最长子串的长度
     * 思路：创建一个 pre 数组表示长度，从左到右遍历字符串数组，查看
     *
     * @param s 字符串参数
     * @return int 长度
     */
    public static int lengthOfLongestSubstringMethod1(String s) {

        // 数组没有赋值的时，所有元素会初始化为 0
        // 字符为下标时，会将 ASCII 码作为下标
        int[] pre = new int[128];

        int max = 0, t = 0;

        // i 表示当前处理的第 i 个字符
        for (int i = 0; i < s.length(); i++) {

            // c 为依次取出的单个字符
            char c = s.charAt(i);

            /* 如果 pre[c] 不等于 0 表示数组中该位置被修改过，也就代表前面有重复字符
             */
            if (pre[c] != 0 && pre[c] > t) {

                // 更新 max 最大值
                // i - t 重复元素下标 - 上一次没重复的下标
                max = Math.max(max, i - t);

                // t 是为求下一个子串长度做准备，因为要求出的是最长的子串长度
                // 更新 t，上一次没重复的下标
                t = pre[c];

            }

            // 如果 pre[c] 为 0，或者 pre[c] <= t
            pre[c] = i + 1;

        }

        return Math.max(max, s.length() - t);
    }

    /**
     * 方法二：
     * 定义一个 map 数据结构存储 (k, v)，其中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复
     * 我们定义不重复子串的开始位置为 start，结束位置为 end； [start, end] 可理解为滑动窗口
     * 随着 end 不断遍历向后，会遇到与 [start, end] 区间内字符相同的情况，此时将字符作为 key 值，获取其 value 值，并更新 start，此时 [start, end] 区间内不存在重复字符
     * 无论是否更新 start，都会更新其 map 数据结构和结果 max。
     * 时间复杂度：O(n)
     *
     * @param s 字符串参数
     * @return int 长度
     * @author github.com/xpwiguanpengchn
     * @link https://leetcode-cn.com/problems/two-sum/solution/hua-jie-suan-fa-3-wu-zhong-fu-zi-fu-de-zui-chang-z/
     */
    public static int lengthOfLongestSubstringMethod2(String s) {

        // max 表示所求的最大长度
        int max = 0;

        /* Map 中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复
         * 同一个字符 key，在 map 中只存在一个。当重复时更新它的值。
         */
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        /* start 指向不重复子串的第一个字符的下标。
         * 当存在重复字符时 start 指向后面一个重复字符，指向下一个不重复子串的第一个字符的下标
         */
        for (int start = 0, end = 0; end < s.length(); end++) {

            char c = s.charAt(end);

            if (map.containsKey(c)) {

                /* 如果含有重复字符串，将滑动窗户的开始位置更新，后移
                 * map.get(c) 的值表示该出现重复的字符，上一次出现时下标+1
                 * 什么时候会出现 start<map.get(c)？
                 * 答：map.get(c) 该重复字符出现的位置不一定，如果重复字符出现的顺序和之前一样，
                 * 比如：abcabcd，先重 a，再重 b
                 * 什么时候会出现 start>map.get(c)？
                 * 答：第二次出现重复时，如果重复的字符在第一次出现重复的字符前面
                 * 比如 abba，先重 b，再重 a。出现 a 重复时，start 为 2 > map.get('a') 为 1
                 */
                start = Math.max(map.get(c), start);
            }

            /* 不管是否重复这里都会执行
             * 每次执行判断一次滑动窗口长度是否超过当前最大长度，是则更新
             * end - start + 1 表示滑动窗口长度，子串长度，不重复子串最短也为 1
             * 为什么要 + 1？
             * 当 start 和 end 指向一个是元素时，下标一样，end-start 为 0，此时存在一个不重复子串为 1 个元素
             * 当 end 指向 start 后面相邻一个，end-start 为 1，此时不重复子串为 2 个元素
             */
            max = Math.max(max, end - start + 1);

            /* 不管是否重复这里都会执行
             * 如果不重复，会新添加一个 key，值为：位置下标+1
             * 如果重复，会更新这个 key 对应的值为：后面又重复出现的该字符的位置下标+1
             */
            map.put(c, end + 1);
        }

        return max;
    }

}
