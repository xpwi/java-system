package com.guazi;

import java.util.Scanner;

/**
 * <p>
 * https://www.nowcoder.com/profile/3909462/test/27548379/587674
 *
 * @author XiaoPengwei
 * @since 2019-09-14
 */
public class Q3GetLostString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lines = scanner.nextInt();
        scanner.nextLine();
        StringBuilder input = new StringBuilder();
        for (int i = 0; i < lines; i++) {
            input.append(scanner.nextLine());
        }
        if (lines == 1) {
            System.out.println(input.substring(1, 2) + input.substring(0, 1));
            return;
        }
        char[] inputCharArray = input.toString().toCharArray();
        int len = inputCharArray.length / lines;//一个字符串有多长
        StringBuilder res = new StringBuilder();
        for (int j = 0; j < len; j++) {
            char temp = 0;
            for (int k = j; k < inputCharArray.length; k += len) {
                temp ^= inputCharArray[k];
            }
            res.append(temp);
        }
        System.out.println(res.toString());
    }

}