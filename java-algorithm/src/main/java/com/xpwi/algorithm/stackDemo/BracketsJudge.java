package com.xpwi.algorithm.stackDemo;/** * <p> * 找出字符串中重复的下标 * </p> * * @author xpwi * @since 2019-04-20 */public class BracketsJudge {    public static void main(String[] args) {        int[] ints = getFisrtRepeated("abbbbcrgrg");        System.out.println("\nThe repeated characters are begin " + ints[0] + " to " + ints[1]);    }    /**     * 输出从头开始重复个数大于 3 的下标     *     * @param str     * @return int[]     * @author xpwi     */    public static int[] getFisrtRepeated(String str) {        char[] chars = str.toCharArray();        //只要多于两个就打印下标段        for (int i = 0; i < chars.length; i++) {            if (chars[i] == chars[i++]) {                for (int j = i + 1; j < chars.length; j++) {                    if (chars[i] != chars[j] && j - i > 2) {                        return new int[]{i, j};                    }                }            }        }        throw new IllegalArgumentException("not have");    }}