package com.test;

import java.util.Scanner;

/**
 * <p>
 * 单词逆序
 *
 * @author XiaoPengwei
 * @since 2019-09-11
 */
public class WordReverse {

    public static StringBuilder wordReverse(String str) {

        String[] split = str.split("\\s+");

        String[] resArr = new String[split.length];

        for (int i = 0; i < split.length; i++) {
            resArr[split.length - 1 - i] = split[i];
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < split.length; i++) {
            res.append(resArr[i]);
            res.append(" ");
        }

        return res;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        StringBuilder rs = wordReverse(s);
        System.out.println(rs);

    }

}
