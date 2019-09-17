package com.test;

import java.util.*;

/**
 * <p>
 * https://www.nowcoder.com/discuss/257702
 * 例如：363562
 *
 *
 * @author XiaoPengwei
 * @since 2019-09-13
 */
public class DivideCookie {
    public static final int MIN_MONEY = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // key 为分数，value 为列表
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        // 分数数组
        int[] people = new int[n];

        for (int i = 0; i < n; i++) {
            people[i] = scanner.nextInt();
            if (!map.containsKey(people[i])) {
                map.put(people[i], new ArrayList<>());
            }
            // 列表第一个元素初始化为 学生的序号
            map.get(people[i]).add(i);
        }
        for (Map.Entry i :map.entrySet()){
            System.out.print(i.getKey()+":");
            for (Integer m: (List<Integer>)i.getValue()){
                System.out.print(m+" ");
            }
            System.out.println();
        }

        System.out.println("------");

        // 饼干数
        int[] money = new int[n];
        int[] tmp = new int[n];
        while (!map.isEmpty()) {
            int lowestKey = map.firstKey();
            // key 从小开始，遍历 List
            for (int i : map.get(lowestKey)) {
                if (i == 0) {
                    // i 为 0 说明：该元素位于第 1 个位置；
                    // 把后一个数 + 1 存起来
                    tmp[i] = money[i + 1] + 1;
                    System.out.println(tmp[i]);
                    System.out.println("0"+tmp[i]);

                } else if (i == n - 1) {
                    // i 为 0 说明：该元素位于最后位置；
                    // 把倒数底数第二个数 + 1 存起来
                    tmp[i] = money[i - 1] + 1;
                    System.out.println("n-1"+tmp[i]);

                } else {
                    tmp[i] = Math.max(money[i - 1], money[i + 1]) + 1;
                    System.out.println("else"+tmp[i]);

                }
                System.out.println("=====");
            }
            for (int i : map.get(lowestKey)) {
                System.out.println(tmp[i]);
                money[i] = tmp[i];
            }
            map.remove(lowestKey);
        }

        // 求和
        int sum = 0;
        System.out.println("饼干数");
        for (int i : money) {
            System.out.print(i+" ");
            sum += i;
        }
        System.out.println();
        System.out.println(sum);
    }
}