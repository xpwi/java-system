package com.xpwi.leetcode;import java.util.HashMap;/** * <p> * 01：两数之和 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。 * </p> * * @since 2019-04-21 * @author xpwi */public class LC01TwoSum {    public static void main(String[] args) {        int[] testArr = {2, 7, 10, 32, 21};        int target = 9;        System.out.println("--> the method 1");        int[] ints1 = method1(testArr, target);        for (int i1 : ints1) {            System.out.println(i1);        }        System.out.println("--> the method 2");        int[] ints2 = method2(testArr, target);        for (int i2 : ints2) {            System.out.println(i2);        }        System.out.println("--> the method 3");        int[] ints3 = method3(testArr, target);        for (int i3 : ints3) {            System.out.println(i3);        }    }    /**     * 方法一：暴力法     * 两层循环。外层先拿出左侧第 1 个元素，内层依次判断右侧其余元素与它的和如果等于目标值则返回，如果不等于则继续下一个，如果全部遍历完不存在则抛出异常。     *     * @param nums 数组     * @param target 和     * @return int[] 下标数组     */    public static int[] method1(int[] nums, int target) {        for (int i = 0; i < nums.length; i++) {            for (int j = i + 1; j < nums.length; j++) {                if (nums[j] + nums[i] == target) {                    return new int[]{i, j};                }            }        }        throw new IllegalArgumentException("Method1: No two sum solution");    }    /**     * 方法二：用 Hash 表     * （1）构造一个哈希表，key 是所有待选数组元素，value 是数组下标；     * （2）然后从左向右遍历，对于元素 i，判断 target - nums[i] 是否包含在哈希表中，如果存在则拿出下标，如果不存在则继续。     *     * @param nums 数组     * @param target 和     * @return int[] 下标数组     */    public static int[] method2(int[] nums, int target) {        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();        for (int i = 0; i < nums.length; i++) {            map.put(nums[i], i);        }        for (int i = 0; i < nums.length; i++) {            int temp = target - nums[i];            if (map.containsKey(temp) && map.get(temp) != i) {                return new int[]{i, map.get(temp)};            }        }        throw new IllegalArgumentException("Method2: No two sum solution");    }    /**     * 方法三：用 Hash 表     * 可以不单独构造哈希表。这样显然在第一个元素时，不可能匹配。为什么这样也可以呢？     * 其实上面我们程序结束的条件是对 2，查找是否包含 7。而这里是对 7，查找包含 2。同样可以实现。     *     * @param nums 数组     * @param target 和     * @return int[] 下标数组     */    public static int[] method3(int[] nums, int target) {        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();        for (int i = 0; i < nums.length; i++) {            int temp = target - nums[i];            if (map.containsKey(temp)) {                return new int[]{                        map.get(temp), i};            }            map.put(nums[i], i);        }        throw new IllegalArgumentException("Method3: No two sum solution");    }}